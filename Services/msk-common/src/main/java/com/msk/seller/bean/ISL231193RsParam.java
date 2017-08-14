package com.msk.seller.bean;


import com.hoperun.core.bean.BaseParam;
import com.msk.core.entity.SlSeller;

import java.util.List;

/**
 * Created by zhangchi on 2016/5/9.
 */
public class ISL231193RsParam extends ISL231193RsBean {


    /** 企业名称 */
    private String epName;

    /** 创建开始日期 */
    private String cycleStart;

    /** 创建结束日期  */
    private String cycleEnd;

    /**卖家ID*/
    private List<String> slCodeList;

    /**卖家信息list*/
    private List<SlSeller> condition;

    /**检索条件集合*/
    private List<ISL231193RsBean> paramList;

    /** 0:注册了账号的所有买手 1：账号和基本信息都注册了的买手" */
    private Integer slCodeFlag;

    public Integer getSlCodeFlag() {
        return slCodeFlag;
    }

    public void setSlCodeFlag(Integer slCodeFlag) {
        this.slCodeFlag = slCodeFlag;
    }
    /**
     * Getter method for property <tt>slCode</tt>.
     *
     * @return property value of slCode
     */
    public List<ISL231193RsBean> getParamList() {
        return paramList;
    }

    /**
     * Setter method for property <tt>slCode</tt>.
     *
     * @param paramList value to be assigned to property slCode
     */
    public void setParamList(List<ISL231193RsBean> paramList) {
        this.paramList = paramList;
    }


    public List<String> getSlCodeList() {
        return slCodeList;
    }

    public void setSlCodeList(List<String> slCodeList) {
        this.slCodeList = slCodeList;
    }

    public String getEpName() {
        return epName;
    }

    public void setEpName(String epName) {
        this.epName = epName;
    }

    public String getCycleStart() {
        return cycleStart;
    }

    public void setCycleStart(String cycleStart) {
        this.cycleStart = cycleStart;
    }

    public String getCycleEnd() {
        return cycleEnd;
    }

    public void setCycleEnd(String cycleEnd) {
        this.cycleEnd = cycleEnd;
    }

    public List<SlSeller> getCondition() {
        return condition;
    }

    public void setCondition(List<SlSeller> condition) {
        this.condition = condition;
    }
}
