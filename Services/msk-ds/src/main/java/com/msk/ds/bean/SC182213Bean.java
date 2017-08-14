package com.msk.ds.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.msk.core.entity.BaseEntity;


import java.util.Date;

/**
 * Created by air on 2016/5/11.
 */
public class SC182213Bean extends BaseEntity {
    private Long lotId; //'批次流水ID',
    private String serialId; //'流水ID',
    private String lotCode; //'阅读码',
    private String readCode;//'批次码',
    private Date crtTime;

    public Long getLotId() {
        return lotId;
    }

    public void setLotId(Long lotId) {
        this.lotId = lotId;
    }

    public String getSerialId() {
        return serialId;
    }

    public void setSerialId(String serialId) {
        this.serialId = serialId;
    }

    public String getLotCode() {
        return lotCode;
    }

    public void setLotCode(String lotCode) {
        this.lotCode = lotCode;
    }

    public String getReadCode() {
        return readCode;
    }

    public void setReadCode(String readCode) {
        this.readCode = readCode;
    }
    @Override
    @JsonFormat(timezone="GMT+8", pattern="yyyy-MM-dd")
    public Date getCrtTime() {
        return crtTime;
    }

    @Override
    public void setCrtTime(Date crtTime) {
        this.crtTime = crtTime;
    }
}
