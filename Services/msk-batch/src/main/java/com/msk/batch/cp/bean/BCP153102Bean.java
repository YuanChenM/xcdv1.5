package com.msk.batch.cp.bean;

import com.msk.core.entity.BaseEntity;

import java.util.List;

/**
 * 账套,账期设置
 * zhang_chi
 */
public class BCP153102Bean extends BaseEntity {

    /** 卖家编码*/
    private String slCode;
    /** 卖家显示编码*/
    private String slCodeDis;
    /** 卖家类型*/
    private String slMainClass;
    /** 卖家名称*/
    private String slName;
    /** 买手名称*/
    private String slShowName;

    /** 买手信息列表*/
    private List<BCP153102Bean> buyershopList;

    public List<BCP153102Bean> getBuyershopList() {
        return buyershopList;
    }

    public void setBuyershopList(List<BCP153102Bean> buyershopList) {
        this.buyershopList = buyershopList;
    }

    public String getSlShowName() {
        return slShowName;
    }

    public void setSlShowName(String slShowName) {
        this.slShowName = slShowName;
    }

    public String getSlCode() {
        return slCode;
    }

    public void setSlCode(String slCode) {
        this.slCode = slCode;
    }

    public String getSlCodeDis() {
        return slCodeDis;
    }

    public void setSlCodeDis(String slCodeDis) {
        this.slCodeDis = slCodeDis;
    }

    public String getSlMainClass() {
        return slMainClass;
    }

    public void setSlMainClass(String slMainClass) {
        this.slMainClass = slMainClass;
    }

    public String getSlName() {
        return slName;
    }

    public void setSlName(String slName) {
        this.slName = slName;
    }


}
