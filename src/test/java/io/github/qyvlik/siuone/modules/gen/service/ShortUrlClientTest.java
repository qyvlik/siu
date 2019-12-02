package io.github.qyvlik.siuone.modules.gen.service;

import io.github.qyvlik.siuone.common.utils.BatchRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class ShortUrlClientTest {


    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private GenIdService genIdService;

    @Test
    public void alloc() throws Exception {

        genIdService.createPendingList(128, 128);

        Long machineId = 1000L;
        ShortUrlClient shortUrlClient = new ShortUrlClient(machineId, genIdService);

        int taskCount = 128 * 4 * 5;
        Executor executor = Executors.newFixedThreadPool(4);
        BatchRunner batchRunner = new BatchRunner(executor, taskCount);
        int count = taskCount;
        while (count-- > 0) {
            batchRunner.submit(new Runnable() {
                @Override
                public void run() {
                    ShortUrlClient.ShortUrlInfo url = shortUrlClient.allocShortUrl();
                    logger.info("id:{}, url:{}", url.getId(), url.getShortUrl());
                }
            });
        }

        batchRunner.waitDone();
        logger.info("done");
    }

    @Test
    public void allocShortUrl() throws Exception {

        Long machineId = 1000L;
        ShortUrlClient shortUrlClient = new ShortUrlClient(machineId, genIdService);

        ShortUrlClient.ShortUrlInfo url = shortUrlClient.allocShortUrl();

        logger.info("url:{}", url);
    }

}