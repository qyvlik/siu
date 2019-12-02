package io.github.qyvlik.siuone.modules.url.entity;

import io.github.qyvlik.siuone.common.wapper.DataEntity;

public class UrlOrigin extends DataEntity {
    private Long id;
    private String originUrl;
    private Long createTime;

    // vo
    private Long beginTime;
    private Long endTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOriginUrl() {
        return originUrl;
    }

    public void setOriginUrl(String originUrl) {
        this.originUrl = originUrl;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Long beginTime) {
        this.beginTime = beginTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "UrlOrigin{" +
                "id=" + id +
                ", originUrl='" + originUrl + '\'' +
                ", createTime=" + createTime +
                ", beginTime=" + beginTime +
                ", endTime=" + endTime +
                '}';
    }
}
