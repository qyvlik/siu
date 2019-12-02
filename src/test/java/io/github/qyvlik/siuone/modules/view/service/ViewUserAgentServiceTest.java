package io.github.qyvlik.siuone.modules.view.service;

import io.github.qyvlik.siuone.common.utils.SHA256Utils;
import io.github.qyvlik.siuone.common.utils.SnowFlake;
import io.github.qyvlik.siuone.modules.view.entity.ViewUserAgent;
import nl.basjes.parse.useragent.UserAgent;
import nl.basjes.parse.useragent.UserAgentAnalyzer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class ViewUserAgentServiceTest {

    @Autowired
    private ViewUserAgentService viewUserAgentService;
    @Autowired
    private SnowFlake snowFlake;
    @Autowired
    private UserAgentAnalyzer userAgentAnalyzer;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void saveAndGetByHash() throws Exception {
        String userAgent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.108 Safari/537.36";

        UserAgent agent = userAgentAnalyzer.parse(userAgent);

        for (String fieldName : agent.getAvailableFieldNamesSorted()) {
            logger.info("{} = {}", fieldName, agent.getValue(fieldName));
        }

        String userAgentHash = SHA256Utils.hash(userAgent);

        ViewUserAgent viewUserAgent = new ViewUserAgent();
        viewUserAgent.setId(snowFlake.nextId());
        viewUserAgent.setUserAgentHash(userAgentHash);
        viewUserAgent.setUserAgent(userAgent);
        viewUserAgent.setBrowser(agent.getValue(UserAgent.AGENT_NAME));
        viewUserAgent.setBrowserVersion(agent.getValue(UserAgent.AGENT_NAME_VERSION));
        viewUserAgent.setOperatingSystem(agent.getValue(UserAgent.OPERATING_SYSTEM_NAME_VERSION));
        viewUserAgent.setCreateTime(System.currentTimeMillis());

        viewUserAgentService.insert(viewUserAgent);

        ViewUserAgent viewUserAgentInDB = viewUserAgentService.getByHash(userAgentHash);
        logger.info("viewUserAgentInDB:{}", viewUserAgentInDB);
    }

}