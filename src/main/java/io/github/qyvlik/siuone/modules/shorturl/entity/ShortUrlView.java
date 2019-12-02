package io.github.qyvlik.siuone.modules.shorturl.entity;

public class ShortUrlView {
    private Long shortUrlId;
    private Long originUrlId;
    private String ipAddress;
    private String userAgent;
    private String referrerUrl;

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

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getReferrerUrl() {
        return referrerUrl;
    }

    public void setReferrerUrl(String referrerUrl) {
        this.referrerUrl = referrerUrl;
    }
}
