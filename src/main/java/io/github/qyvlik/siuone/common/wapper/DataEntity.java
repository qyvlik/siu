package io.github.qyvlik.siuone.common.wapper;

import java.io.Serializable;

public class DataEntity implements Serializable {
    private String sharedId;            // 分表ID

    public String getSharedId() {
        return sharedId;
    }

    public void setSharedId(String sharedId) {
        this.sharedId = sharedId;
    }
}
