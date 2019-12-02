package io.github.qyvlik.siuone.modules.url.service;

import io.github.qyvlik.siuone.common.utils.SnowFlake;
import io.github.qyvlik.siuone.modules.url.entity.UrlOrigin;
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
public class UrlOriginServiceTest {

    @Autowired
    private UrlOriginService urlOriginService;

    private Logger logger = LoggerFactory.getLogger(getClass());


    @Test
    public void saveAndGet() throws Exception {
        String originUrl = "https://github.com/qyvlik/siu";

        UrlOrigin urlOrigin = new UrlOrigin();
        urlOrigin.setOriginUrl(originUrl);
        urlOrigin.setCreateTime(System.currentTimeMillis());
        Long effect = urlOriginService.insert(urlOrigin);

        UrlOrigin urlOriginInDB =  urlOriginService.get(urlOrigin.getId());
        logger.info("saveAndGet:{}", urlOriginInDB);
    }
}