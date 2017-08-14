package com.msk.seller.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.msk.core.entity.BaseEntity;

import java.util.Date;

/**
 * ISL231201RsResult.
 *
 * @author gyh
 */
@JsonIgnoreProperties(value = {"delFlg", "crtId", "crtTime", "updId", "updTime", "actId", "actTime"})
public class ISL231201RsResult extends BaseEntity {
    private Integer chapId;//章节ID
    private Integer chapNo;//章节编号
    private String chapTitle;//章节标题

    /**
     * 获得chapId
     */
    public Integer getChapId() {
        return chapId;
    }

    /**
     * 设置chapId
     */
    public void setChapId(Integer chapId) {
        this.chapId = chapId;
    }

    /**
     * 获得chapNo
     */
    public Integer getChapNo() {
        return chapNo;
    }

    /**
     * 设置chapNo
     */
    public void setChapNo(Integer chapNo) {
        this.chapNo = chapNo;
    }

    /**
     * 获得chapTitle
     */
    public String getChapTitle() {
        return chapTitle;
    }

    /**
     * 设置chapTitle
     */
    public void setChapTitle(String chapTitle) {
        this.chapTitle = chapTitle;
    }
}
