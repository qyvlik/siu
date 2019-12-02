package io.github.qyvlik.siuone.modules.gen.service;

import io.github.qyvlik.siuone.common.utils.Base62;
import io.github.qyvlik.siuone.modules.gen.entity.GenId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShortUrlClient {

    private Logger logger = LoggerFactory.getLogger(getClass());
    private ThreadLocal<GenIdInfo> localGenId;
    private Long machineId;
    private GenIdService genIdService;

    public ShortUrlClient(Long machineId, GenIdService genIdService) {
        this.machineId = machineId;
        this.genIdService = genIdService;
        this.initGenIdInfo();
    }

    private static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (Exception e) {
        }
    }

    public ShortUrlInfo allocShortUrl() {
        Long id = alloc();
        return new ShortUrlInfo(id, Base62.fromBase10(id));
    }

    public Long alloc() {
        GenIdInfo genIdInfo = localGenId.get();
        if (genIdInfo == null || genIdInfo.getCurrentId() + 1 >= genIdInfo.getMaxId()) {
            localGenId.remove();
            genIdInfo = localGenId.get();
        }
        if (genIdInfo == null) {
            throw new RuntimeException("alloc failure : genIdInfo is null");
        }
        return allocInternal(genIdInfo);
    }

    private Long allocInternal(GenIdInfo genIdInfo) {
        Long returnId = null;
        if (genIdInfo.getCurrentId() == -1) {
            returnId = genIdInfo.getGenId().getSegmentBegin();
        } else {
            returnId = genIdInfo.getCurrentId() + 1;
        }
        genIdInfo.setCurrentId(returnId);
        return returnId;
    }

    private void initGenIdInfo() {
        this.localGenId = new ThreadLocal<GenIdInfo>() {
            @Override
            public GenIdInfo initialValue() {
                final int maxTryCount = 3;
                int tryCount = 0;

                GenId genId = genIdService.randomAlloc(machineId);
                // 冲突高
                while (genId == null && tryCount++ < maxTryCount) {
                    sleep(10);
                    genId = genIdService.randomAlloc(machineId);
                }

                if (genId == null) {
                    logger.warn("initGenIdInfo failure : randomAlloc return null, machineId:{}", machineId);
                    return null;
                }

                return new GenIdInfo(genId);
            }
        };
    }

    public static class GenIdInfo {
        private GenId genId;
        private Long currentId;
        private Long maxId;

        public GenIdInfo(GenId genId) {
            this.genId = genId;
            this.currentId = -1L;
            this.maxId = genId.getSegmentBegin() + genId.getSegmentSize();
        }

        public GenId getGenId() {
            return genId;
        }

        public void setGenId(GenId genId) {
            this.genId = genId;
        }

        public Long getCurrentId() {
            return currentId;
        }

        public void setCurrentId(Long currentId) {
            this.currentId = currentId;
        }

        public Long getMaxId() {
            return maxId;
        }

        public void setMaxId(Long maxId) {
            this.maxId = maxId;
        }
    }

    public static class ShortUrlInfo {
        private Long id;
        private String shortUrl;

        public ShortUrlInfo(Long id, String shortUrl) {
            this.id = id;
            this.shortUrl = shortUrl;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getShortUrl() {
            return shortUrl;
        }

        public void setShortUrl(String shortUrl) {
            this.shortUrl = shortUrl;
        }
    }
}
