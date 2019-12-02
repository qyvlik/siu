package io.github.qyvlik.siuone.modules.view.entity;

import io.github.qyvlik.siuone.common.wapper.DataEntity;

public class ViewUserAgent extends DataEntity {
    private Long id;
    private String userAgentHash;
    private String userAgent;
    private String browser;
    private String browserVersion;
    private String operatingSystem;
    private Long createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserAgentHash() {
        return userAgentHash;
    }

    public void setUserAgentHash(String userAgentHash) {
        this.userAgentHash = userAgentHash;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getBrowserVersion() {
        return browserVersion;
    }

    public void setBrowserVersion(String browserVersion) {
        this.browserVersion = browserVersion;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "ViewUserAgent{" +
                "id=" + id +
                ", userAgentHash='" + userAgentHash + '\'' +
                ", userAgent='" + userAgent + '\'' +
                ", browser='" + browser + '\'' +
                ", browserVersion='" + browserVersion + '\'' +
                ", operatingSystem='" + operatingSystem + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
