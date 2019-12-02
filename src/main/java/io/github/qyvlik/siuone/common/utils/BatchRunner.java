package io.github.qyvlik.siuone.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

public class BatchRunner {

    private Logger logger = LoggerFactory.getLogger(getClass());
    private Executor executor;
    private CountDownLatch countDownLatch;

    public BatchRunner(Executor executor, int size) {
        this.executor = executor;
        this.countDownLatch = new CountDownLatch(size);
    }

    public void submit(Runnable runnable) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    runnable.run();
                } finally {
                    countDownLatch.countDown();
                }
            }
        });
    }

    public void waitDone() {
        try {
            if (countDownLatch.getCount() > 0) {
                countDownLatch.await();
            }
        } catch (Exception e) {
            logger.error("waitDone await failure:{}", e.getMessage());
        }
    }

    public void waitDone(long timeoutMillis) {
        try {
            if (countDownLatch.getCount() > 0) {
                countDownLatch.await(timeoutMillis, TimeUnit.MILLISECONDS);
            }
        } catch (Exception e) {
            logger.error("waitDone await failure:timeoutMillis:{}ms, error:{}", timeoutMillis, e.getMessage());
        }
    }

    public long getCount() {
        return countDownLatch.getCount();
    }
}
