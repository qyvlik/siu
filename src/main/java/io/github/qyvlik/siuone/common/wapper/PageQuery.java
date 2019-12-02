package io.github.qyvlik.siuone.common.wapper;

import java.io.Serializable;

// 分页查询
public class PageQuery implements Serializable {
    private String orderBy;
    private Long startId;
    private Integer pageSize;               // 每页多少个元素
    private Integer pageNo;                 // 页码

    public PageQuery() {

    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public Long getStartId() {
        return startId;
    }

    public void setStartId(Long startId) {
        this.startId = startId;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "PageQuery{" +
                "orderBy='" + orderBy + '\'' +
                ", startId=" + startId +
                ", pageSize=" + pageSize +
                ", pageNo=" + pageNo +
                '}';
    }
}
