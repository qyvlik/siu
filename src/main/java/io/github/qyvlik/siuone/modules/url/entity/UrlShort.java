package io.github.qyvlik.siuone.modules.url.entity;

import io.github.qyvlik.siuone.common.wapper.DataEntity;

public class UrlShort extends DataEntity {

    public static final int STATE_DISABLE = 0;
    public static final int STATE_ENABLE = 1;
    public static final int STATE_EXPIRED = 2;

    private Long id;
    private String shortUrl;
    private Long originUrlId;
    private Integer state;
    private Long createTime;
    private Long updateTime;
    private Integer version;

    // vo
    private Long beginTime;
    private Long endTime;

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

    public Long getOriginUrlId() {
        return originUrlId;
    }

    public void setOriginUrlId(Long originUrlId) {
        this.originUrlId = originUrlId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
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
        return "UrlShort{" +
                "id=" + id +
                ", shortUrl='" + shortUrl + '\'' +
                ", originUrlId=" + originUrlId +
                ", state=" + state +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", version=" + version +
                ", beginTime=" + beginTime +
                ", endTime=" + endTime +
                '}';
    }
}
