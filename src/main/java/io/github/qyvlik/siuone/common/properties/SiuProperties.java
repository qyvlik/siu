package io.github.qyvlik.siuone.common.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "siu")
public class SiuProperties {

    private GenId genId;

    public GenId getGenId() {
        return genId;
    }

    public void setGenId(GenId genId) {
        this.genId = genId;
    }

    public static class GenId {
        private Integer allocSegmentCount;
        private String allocCron;
        private Integer segmentSize;

        public Integer getAllocSegmentCount() {
            return allocSegmentCount;
        }

        public void setAllocSegmentCount(Integer allocSegmentCount) {
            this.allocSegmentCount = allocSegmentCount;
        }

        public String getAllocCron() {
            return allocCron;
        }

        public void setAllocCron(String allocCron) {
            this.allocCron = allocCron;
        }

        public Integer getSegmentSize() {
            return segmentSize;
        }

        public void setSegmentSize(Integer segmentSize) {
            this.segmentSize = segmentSize;
        }
    }
}
