package io.github.qyvlik.siuone.modules.url.entity;

import io.github.qyvlik.siuone.common.wapper.DataEntity;

public class UrlBlock extends DataEntity {
    private Long id;
    private String url;
    private BlockType type;
    private Long createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public BlockType getType() {
        return type;
    }

    public void setType(BlockType type) {
        this.type = type;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public enum BlockType {
        full,
        domain,
        path,
        contains
    }

    @Override
    public String toString() {
        return "UrlBlock{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", type=" + type +
                ", createTime=" + createTime +
                '}';
    }
}
