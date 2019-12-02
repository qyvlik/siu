package io.github.qyvlik.siuone.common.wapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseService {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    public void sleep(long ms) {
        if (ms <= 0) {
            return;
        }
        try {
            Thread.sleep(ms);
        } catch (Exception e) {

        }
    }

}
