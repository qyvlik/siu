package io.github.qyvlik.siuone.modules.gen.service;

import io.github.qyvlik.siuone.modules.gen.entity.GenId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class GenIdServiceTest {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private GenIdService genIdService;

    @Test
    public void createPendingList() throws Exception {
        List<GenId> genIds = genIdService.createPendingList(128, 10);
        logger.info("createPendingList:{}", genIds);
    }

    @Test
    public void getPendingList() throws Exception {
        List<GenId> genIds = genIdService.getPendingList(20);
        logger.info("getPendingList:{}", genIds);
    }

    @Test
    public void alloc() throws Exception {
        long machineId = 1000L;
        List<GenId> genIds = genIdService.alloc(machineId, 5);
        logger.info("alloc:{}", genIds);
    }
}