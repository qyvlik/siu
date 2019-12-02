package io.github.qyvlik.siuone.modules.view.service;

import io.github.qyvlik.siuone.common.utils.SnowFlake;
import io.github.qyvlik.siuone.modules.view.entity.ViewLogs;
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
public class ViewLogsServiceTest {

    @Autowired
    private ViewLogsService viewLogsService;

    @Autowired
    private SnowFlake snowFlake;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void saveAndGet() throws Exception {

        ViewLogs viewLogs = new ViewLogs();
        viewLogs.setId(snowFlake.nextId());
        viewLogs.setShortUrlId(-1L);
        viewLogs.setOriginUrlId(-1L);
        viewLogs.setReferrerUrlId(-1L);
        viewLogs.setUserAgentId(-1L);
        viewLogs.setIpAddress("127.0.0.1");
        viewLogs.setCreateTime(System.currentTimeMillis());

        viewLogsService.insert(viewLogs);

        ViewLogs viewLogsInDB = viewLogsService.get(viewLogs.getId());
        logger.info("viewLogsInDB:{}", viewLogsInDB);
    }
}