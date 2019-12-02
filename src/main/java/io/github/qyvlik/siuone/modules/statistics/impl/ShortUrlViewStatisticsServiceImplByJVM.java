package io.github.qyvlik.siuone.modules.statistics.impl;

import com.google.common.collect.Queues;
import io.github.qyvlik.siuone.common.wapper.BaseService;
import io.github.qyvlik.siuone.modules.shorturl.entity.ShortUrlView;
import io.github.qyvlik.siuone.modules.shorturl.service.ShortUrlViewService;
import io.github.qyvlik.siuone.modules.statistics.ShortUrlViewProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Queue;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Profile("statisticsByJVM")
@Service("shortUrlViewStatisticsServiceImplByJVM")
public class ShortUrlViewStatisticsServiceImplByJVM extends BaseService implements ShortUrlViewProducer {
    @Autowired
    private ShortUrlViewService shortUrlViewService;
    private Queue<ShortUrlView> queue = Queues.newConcurrentLinkedQueue();
    private Executor executor = Executors.newSingleThreadExecutor();

    @Override
    public void send(ShortUrlView shortUrlView) {
        if (queue != null) {
            this.queue.add(shortUrlView);
        }
    }

    @PostConstruct
    void start() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                while (!Thread.interrupted()) {
                    ShortUrlView view = queue.poll();
                    if (view != null) {
                        shortUrlViewService.handleShortUrlView(view);
                    } else {
                        sleep(1);
                    }
                }
            }
        });
    }
}
