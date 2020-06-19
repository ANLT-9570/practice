package com.dg.main.vo;

import org.apache.commons.compress.utils.Lists;

import java.sql.Timestamp;
import java.util.List;


public class RecordVo{

    private Long recordId;

    private Long userId;

    private Long specificationsId;

    private Timestamp browseTime;

private List<RecordItemVo> items= Lists.newArrayList();

    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getSpecificationsId() {
        return specificationsId;
    }

    public void setSpecificationsId(Long specificationsId) {
        this.specificationsId = specificationsId;
    }

    public Timestamp getBrowseTime() {
        return browseTime;
    }

    public void setBrowseTime(Timestamp browseTime) {
        this.browseTime = browseTime;
    }

    public List<RecordItemVo> getItems() {
        return items;
    }

    public void setItems(List<RecordItemVo> items) {
        this.items = items;
    }
}
