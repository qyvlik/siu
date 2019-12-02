package io.github.qyvlik.siuone.modules.shorturl.service;

import io.github.qyvlik.siuone.common.utils.SHA256Utils;
import io.github.qyvlik.siuone.common.utils.SnowFlake;
import io.github.qyvlik.siuone.modules.shorturl.entity.ShortUrlView;
import io.github.qyvlik.siuone.modules.url.entity.UrlOrigin;
import io.github.qyvlik.siuone.modules.url.service.UrlOriginService;
import io.github.qyvlik.siuone.modules.view.entity.ViewLogs;
import io.github.qyvlik.siuone.modules.view.entity.ViewUserAgent;
import io.github.qyvlik.siuone.modules.view.service.ViewLogsService;
import io.github.qyvlik.siuone.modules.view.service.ViewUserAgentService;
import nl.basjes.parse.useragent.UserAgent;
import nl.basjes.parse.useragent.UserAgentAnalyzer;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ShortUrlViewService {

    @Autowired
    private UrlOriginService urlOriginService;
    @Autowired
    private ViewUserAgentService viewUserAgentService;
    @Autowired
    private ViewLogsService viewLogsService;
    @Autowired
    private UserAgentAnalyzer userAgentAnalyzer;
    @Autowired
    private SnowFlake snowFlake;

    @Transactional(readOnly = false)
    public void handleShortUrlView(ShortUrlView shortUrlView) {
        // todo cache origin url

        Long referrerUrlId = -1L;
        if (StringUtils.isNotBlank(shortUrlView.getReferrerUrl())) {
            UrlOrigin referrerUrl = new UrlOrigin();
            referrerUrl.setId(snowFlake.nextId());
            referrerUrl.setOriginUrl(shortUrlView.getReferrerUrl());
            referrerUrl.setCreateTime(System.currentTimeMillis());
            urlOriginService.insert(referrerUrl);
            referrerUrlId = referrerUrl.getId();
        }

        UserAgent agent = userAgentAnalyzer.parse(shortUrlView.getUserAgent());

        String userAgentHash = SHA256Utils.hash(shortUrlView.getUserAgent());

        Long userAgentId = viewUserAgentService.getIdByHash(userAgentHash);
        if (userAgentId == null) {
            ViewUserAgent viewUserAgent = new ViewUserAgent();
            viewUserAgent.setId(snowFlake.nextId());
            viewUserAgent.setUserAgentHash(userAgentHash);
            viewUserAgent.setUserAgent(shortUrlView.getUserAgent());
            viewUserAgent.setBrowser(agent.getValue(UserAgent.AGENT_NAME));
            viewUserAgent.setBrowserVersion(agent.getValue(UserAgent.AGENT_NAME_VERSION));
            viewUserAgent.setOperatingSystem(agent.getValue(UserAgent.OPERATING_SYSTEM_NAME_VERSION));
            viewUserAgent.setCreateTime(System.currentTimeMillis());
            viewUserAgentService.insert(viewUserAgent);
            userAgentId = viewUserAgent.getId();
        }

        ViewLogs viewLogs = new ViewLogs();
        viewLogs.setId(snowFlake.nextId());
        viewLogs.setShortUrlId(shortUrlView.getShortUrlId());
        viewLogs.setOriginUrlId(shortUrlView.getOriginUrlId());
        viewLogs.setReferrerUrlId(referrerUrlId);
        viewLogs.setUserAgentId(userAgentId);
        viewLogs.setIpAddress(shortUrlView.getIpAddress());
        viewLogs.setCreateTime(System.currentTimeMillis());

        viewLogsService.insert(viewLogs);
    }
}
