package io.github.qyvlik.siuone.modules.shorturl.service;

import io.github.qyvlik.siuone.common.utils.Base62;
import io.github.qyvlik.siuone.common.utils.SnowFlake;
import io.github.qyvlik.siuone.modules.gen.service.ShortUrlClient;
import io.github.qyvlik.siuone.modules.url.entity.UrlBlock;
import io.github.qyvlik.siuone.modules.url.entity.UrlOrigin;
import io.github.qyvlik.siuone.modules.url.entity.UrlShort;
import io.github.qyvlik.siuone.modules.url.service.UrlBlockService;
import io.github.qyvlik.siuone.modules.url.service.UrlOriginService;
import io.github.qyvlik.siuone.modules.url.service.UrlShortService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;

@Service("shortUrlService")
public class ShortUrlService {
    @Autowired
    private UrlShortService urlShortService;
    @Autowired
    private UrlOriginService urlOriginService;
    @Autowired
    private ShortUrlClient shortUrlClient;
    @Autowired
    private UrlBlockService urlBlockService;
    @Autowired
    private UrlCacheService urlCacheService;
    @Autowired
    private SnowFlake snowFlake;

    private void checkOriginUrl(String originUrl) {
        if (StringUtils.isBlank(originUrl)) {
            throw new RuntimeException("originUrl is blank");
        }

        if (!originUrl.startsWith("https://") && !originUrl.startsWith("http://")) {
            throw new RuntimeException("originUrl must start with https:// or http://");
        }
        URI uri = null;
        try {
            uri = new URI(originUrl);
        } catch (URISyntaxException e) {
            throw new RuntimeException("originUrl is invalidate url");
        }

        UrlBlock urlBlockQueryForDomain = new UrlBlock();
        urlBlockQueryForDomain.setUrl(uri.getHost() + ":" + uri.getPort());
        urlBlockQueryForDomain.setType(UrlBlock.BlockType.domain);
        Long domainCount = urlBlockService.count(urlBlockQueryForDomain);
        if (domainCount > 0) {
            throw new RuntimeException("originUrl is black domain");
        }

        UrlBlock urlBlockQueryForPath = new UrlBlock();
        urlBlockQueryForPath.setUrl(uri.getRawPath());
        urlBlockQueryForPath.setType(UrlBlock.BlockType.path);
        Long pathCount = urlBlockService.count(urlBlockQueryForPath);
        if (pathCount > 0) {
            throw new RuntimeException("originUrl contains black path");
        }
    }

    /**
     * 获取短链接
     *
     * @param originUrl 原始链接
     * @return 短链接
     */
    public String createShortUrl(String originUrl) {
        checkOriginUrl(originUrl);

        Long originUrlId = urlCacheService.getOriginUrlId(originUrl);
        if (originUrlId == null) {
            UrlOrigin urlOrigin = new UrlOrigin();
            urlOrigin.setId(snowFlake.nextId());
            urlOrigin.setOriginUrl(originUrl);
            urlOrigin.setCreateTime(System.currentTimeMillis());
            urlOriginService.insert(urlOrigin);

            originUrlId = urlOrigin.getId();

            urlCacheService.setOriginUrlId(urlOrigin);
        }

        Long shortUrlId = urlCacheService.getShortUrlIdByOriginUrlId(originUrlId);
        if (shortUrlId == null) {
            shortUrlId = urlShortService.getIdByOriginUrlId(originUrlId);
            if (shortUrlId == null) {
                ShortUrlClient.ShortUrlInfo shortUrlInfo = shortUrlClient.allocShortUrl();

                UrlShort urlShort = new UrlShort();
                urlShort.setId(shortUrlInfo.getId());
                urlShort.setShortUrl(shortUrlInfo.getShortUrl());
                urlShort.setOriginUrlId(originUrlId);
                urlShort.setState(UrlShort.STATE_ENABLE);
                urlShort.setCreateTime(System.currentTimeMillis());
                urlShort.setUpdateTime(System.currentTimeMillis());
                urlShort.setVersion(0);

                urlShortService.insert(urlShort);

                urlCacheService.setShortUrlId(urlShort);

                return shortUrlInfo.getShortUrl();
            }
        }

        return Base62.fromBase10(shortUrlId);
    }

    public UrlOrigin getOriginUrl(String shortUrl) {
        return urlShortService.getOriginUrl(shortUrl);
    }
}
