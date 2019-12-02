package io.github.qyvlik.siuone.modules.url.service;

import io.github.qyvlik.siuone.modules.url.entity.UrlBlock;
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
public class UrlBlockServiceTest {

    @Autowired
    private UrlBlockService urlBlockService;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void insertAndFindALLListAndDelete() {
        UrlBlock urlBlock_localhost = new UrlBlock();
        urlBlock_localhost.setUrl("http://localhost:8222");
        urlBlock_localhost.setType(UrlBlock.BlockType.domain);
        urlBlock_localhost.setCreateTime(System.currentTimeMillis());
        urlBlockService.insert(urlBlock_localhost);

        UrlBlock urlBlock_dwz = new UrlBlock();
        urlBlock_dwz.setUrl("https://dwz.com:443");
        urlBlock_dwz.setType(UrlBlock.BlockType.domain);
        urlBlock_dwz.setCreateTime(System.currentTimeMillis());
        urlBlockService.insert(urlBlock_dwz);

        UrlBlock urlBlock_dwz_1 = new UrlBlock();
        urlBlock_dwz_1.setUrl("http://dwz.com:80");
        urlBlock_dwz_1.setType(UrlBlock.BlockType.domain);
        urlBlock_dwz_1.setCreateTime(System.currentTimeMillis());
        urlBlockService.insert(urlBlock_dwz_1);

        UrlBlock urlBlock_path = new UrlBlock();
        urlBlock_path.setUrl("/api/v1/");
        urlBlock_path.setType(UrlBlock.BlockType.path);
        urlBlock_path.setCreateTime(System.currentTimeMillis());
        urlBlockService.insert(urlBlock_path);

        List<UrlBlock> urlBlockList = urlBlockService.findAllList();

        logger.info("urlBlockList:{}", urlBlockList);

        urlBlockService.delete(urlBlock_localhost.getId());
        urlBlockService.delete(urlBlock_dwz.getId());
        urlBlockService.delete(urlBlock_dwz_1.getId());
        urlBlockService.delete(urlBlock_path.getId());
    }
}