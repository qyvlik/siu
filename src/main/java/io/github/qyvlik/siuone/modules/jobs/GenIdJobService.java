package io.github.qyvlik.siuone.modules.jobs;

import io.github.qyvlik.siuone.common.properties.SiuProperties;
import io.github.qyvlik.siuone.common.wapper.BaseService;
import io.github.qyvlik.siuone.modules.gen.service.GenIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;


@Profile("genId")
@Service
public class GenIdJobService extends BaseService {

    @Autowired
    private GenIdService genIdService;

    @Autowired
    private SiuProperties properties;

    @PostConstruct
    @Scheduled(cron = "${siu.gen-id.alloc-cron}")
    public void allocSegment() {
        Long available = genIdService.available();
        if (available < properties.getGenId().getAllocSegmentCount()) {
            genIdService.createPendingList(
                    properties.getGenId().getSegmentSize(),
                    properties.getGenId().getAllocSegmentCount()
            );
            logger.info("allocSegment count:{}", properties.getGenId().getAllocSegmentCount());
        }
    }
}
