package io.github.qyvlik.siuone.common.wapper;

import java.io.Serializable;
import java.util.List;

public class PageResult<T> implements Serializable {
    private String orderBy;
    private Integer pageSize = 25;               // 每页多少个元素
    private Integer pageNo = 1;                  // 页码
    private Long total;                          // 元素总个数
    private Integer pages;                       // 总页码
    private List<T> list;                        // 元素列表

    public PageResult() {

    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "PageResult{" +
                "orderBy='" + orderBy + '\'' +
                ", pageSize=" + pageSize +
                ", pageNo=" + pageNo +
                ", total=" + total +
                ", pages=" + pages +
                ", list=" + list +
                '}';
    }
}
