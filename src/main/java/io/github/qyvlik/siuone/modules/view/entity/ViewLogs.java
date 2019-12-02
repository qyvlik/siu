package io.github.qyvlik.siuone.modules.view.entity;

import io.github.qyvlik.siuone.common.wapper.DataEntity;

public class ViewLogs extends DataEntity {
    private Long id;
    private Long shortUrlId;
    private Long originUrlId;
    private Long referrerUrlId;
    private Long userAgentId;
    private String ipAddress;
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

    public Long getShortUrlId() {
        return shortUrlId;
    }

    public void setShortUrlId(Long shortUrlId) {
        this.shortUrlId = shortUrlId;
    }

    public Long getOriginUrlId() {
        return originUrlId;
    }

    public void setOriginUrlId(Long originUrlId) {
        this.originUrlId = originUrlId;
    }

    public Long getReferrerUrlId() {
        return referrerUrlId;
    }

    public void setReferrerUrlId(Long referrerUrlId) {
        this.referrerUrlId = referrerUrlId;
    }

    public Long getUserAgentId() {
        return userAgentId;
    }

    public void setUserAgentId(Long userAgentId) {
        this.userAgentId = userAgentId;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
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
        return "ViewLogs{" +
                "id=" + id +
                ", shortUrlId=" + shortUrlId +
                ", originUrlId=" + originUrlId +
                ", referrerUrlId=" + referrerUrlId +
                ", userAgentId=" + userAgentId +
                ", ipAddress='" + ipAddress + '\'' +
                ", createTime=" + createTime +
                ", beginTime=" + beginTime +
                ", endTime=" + endTime +
                '}';
    }
}
