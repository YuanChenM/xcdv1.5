package com.msk.bs.bean;

import com.msk.core.entity.BaseEntity;

import java.util.Date;

/**
 * Created by yang_chunyan on 2016/8/4.
 */
public class IBS121306RsBean extends BaseEntity {

    //买手店ID
    private String slCode;
    // 管家ID
    private String houseCode;
    //加入时间
    private Date joinTime;


    public String getSlCode() {
        return slCode;
    }

    public void setSlCode(String slCode) {
        this.slCode = slCode;
    }

    public String getHouseCode() {
        return houseCode;
    }

    public void setHouseCode(String houseCode) {
        this.houseCode = houseCode;
    }

    public Date getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(Date joinTime) {
        this.joinTime = joinTime;
    }
}

