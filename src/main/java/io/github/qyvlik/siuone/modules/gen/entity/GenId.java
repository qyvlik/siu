package io.github.qyvlik.siuone.modules.gen.entity;

import io.github.qyvlik.siuone.common.wapper.DataEntity;

public class GenId extends DataEntity {

    public static final int STATE_PENDING = 0;
    public static final int STATE_USING = 1;
    public static final int STATE_ENDING = 2;

    private Long id;
    private Long segmentBegin;
    private Integer segmentSize;
    private Long machineId;
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

    public Long getSegmentBegin() {
        return segmentBegin;
    }

    public void setSegmentBegin(Long segmentBegin) {
        this.segmentBegin = segmentBegin;
    }

    public Integer getSegmentSize() {
        return segmentSize;
    }

    public void setSegmentSize(Integer segmentSize) {
        this.segmentSize = segmentSize;
    }

    public Long getMachineId() {
        return machineId;
    }

    public void setMachineId(Long machineId) {
        this.machineId = machineId;
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
        return "GenId{" +
                "id=" + id +
                ", segmentBegin=" + segmentBegin +
                ", segmentSize=" + segmentSize +
                ", machineId=" + machineId +
                ", state=" + state +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", version=" + version +
                ", beginTime=" + beginTime +
                ", endTime=" + endTime +
                '}';
    }
}
