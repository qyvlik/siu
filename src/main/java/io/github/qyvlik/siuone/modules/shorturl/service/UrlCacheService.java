package io.github.qyvlik.siuone.modules.shorturl.service;

import io.github.qyvlik.siuone.common.utils.SHA256Utils;
import io.github.qyvlik.siuone.modules.url.entity.UrlOrigin;
import io.github.qyvlik.siuone.modules.url.entity.UrlShort;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class UrlCacheService {

    private static final String SIU_ORIGIN_URL = "siu:ourl:";
    private static final String SIU_SHORT_URL = "siu:surl:";
    private static final int TIMEOUT = 60 * 60;             // unit is second

    @Autowired
    private StringRedisTemplate redisTemplate;

    public Long getOriginUrlId(String originUrl) {
        String originUrlHash = SHA256Utils.hash(originUrl);

        String originUrlIdString = redisTemplate.opsForValue().get(SIU_ORIGIN_URL + originUrlHash);

        if (StringUtils.isBlank(originUrlIdString)) {
            return null;
        }

        return Long.parseLong(originUrlIdString);
    }

    public void setOriginUrlId(UrlOrigin originUrl) {
        String originUrlHash = SHA256Utils.hash(originUrl.getOriginUrl());
        String key = SIU_ORIGIN_URL + originUrlHash;
        redisTemplate.opsForValue().set(key, originUrl.getId() + "", TIMEOUT, TimeUnit.SECONDS);
    }


    public Long getShortUrlIdByOriginUrlId(Long originUrlId) {
        String shortUrlId = redisTemplate.opsForValue().get(SIU_SHORT_URL + originUrlId);

        if (StringUtils.isBlank(shortUrlId)) {
            return null;
        }

        return Long.parseLong(shortUrlId);
    }

    public void setShortUrlId(UrlShort urlShort) {
        String key = SIU_SHORT_URL + urlShort.getOriginUrlId();
        redisTemplate.opsForValue().set(key, urlShort.getId() + "", TIMEOUT, TimeUnit.SECONDS);
    }
}
