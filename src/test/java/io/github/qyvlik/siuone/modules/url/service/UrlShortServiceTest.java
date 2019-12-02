package io.github.qyvlik.siuone.modules.url.service;

import io.github.qyvlik.siuone.modules.gen.service.ShortUrlClient;
import io.github.qyvlik.siuone.modules.gen.service.GenIdService;
import io.github.qyvlik.siuone.modules.url.entity.UrlShort;
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
public class UrlShortServiceTest {

    @Autowired
    private UrlShortService urlShortService;

    @Autowired
    private GenIdService genIdService;

    private ShortUrlClient shortUrlClient;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void saveAndGet() throws Exception {
        Long machineId = 1000L;
        shortUrlClient = new ShortUrlClient(machineId, genIdService);

        ShortUrlClient.ShortUrlInfo shortUrlInfo = shortUrlClient.allocShortUrl();
        UrlShort urlShort = new UrlShort();
        urlShort.setId(shortUrlInfo.getId());
        urlShort.setShortUrl(shortUrlInfo.getShortUrl());
        urlShort.setOriginUrlId(-1L);
        urlShort.setState(UrlShort.STATE_DISABLE);
        urlShort.setCreateTime(System.currentTimeMillis());
        urlShort.setUpdateTime(System.currentTimeMillis());
        urlShort.setVersion(0);

        Long id = urlShortService.insert(urlShort);

        UrlShort urlShortInDB = urlShortService.get(shortUrlInfo.getShortUrl());
        logger.info("saveAndGet urlShortInDB:{}", urlShortInDB);
    }
}