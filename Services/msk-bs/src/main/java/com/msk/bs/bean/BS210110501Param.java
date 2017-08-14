package com.msk.bs.bean;

import com.hoperun.core.bean.BaseParam;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by yang_chunyan on 2016/8/1.
 */
public class BS210110501Param extends BaseParam implements Serializable {
    private static final long serialVersionUID = 1L;
    private String slCode;
    private String houseCode;

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
