package com.msk.seller.bean;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.msk.core.entity.SlPdTncStdNew;

/**
 * Created by gyh on 2016/1/11.
 */
@JsonIgnoreProperties(value = {"delFlg", "crtId", "crtTime", "updId", "updTime", "actId", "actTime"})
public class SL24110502Bean extends SlPdTncStdNew {

    /**神农客质量标准分类信息*/
    private List<SL24110502Bean> qltStdSubNames;
    /**
     * 获得qltStdSubNames
     */
    public List<SL24110502Bean> getQltStdSubNames() {
        return qltStdSubNames;
    }

    /**
     * 设置qltStdSubNames
     */
    public void setQltStdSubNames(List<SL24110502Bean> qltStdSubNames) {
        this.qltStdSubNames = qltStdSubNames;
    }

}
