package com.msk.seller.bean;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.msk.core.entity.SlPdTncStdNew;

/**
 * Created by gyh on 2016/1/9.
 */
@JsonIgnoreProperties(value = {"delFlg", "crtId", "crtTime", "updId", "updTime", "actId", "actTime"})
public class SL24110501Bean extends SlPdTncStdNew {
    /**卖家产品卫生*/
    private List<SL24110501Bean> slPdTncStdList;

    /**
     * 获得slPdTncStdList
     */
    public List<SL24110501Bean> getSlPdTncStdList() {
        return slPdTncStdList;
    }

    /**
     * 设置slPdTncStdList
     */
    public void setSlPdTncStdList(List<SL24110501Bean> slPdTncStdList) {
        this.slPdTncStdList = slPdTncStdList;
    }
}
