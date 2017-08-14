package com.msk.ds.bean;

import com.msk.core.entity.BaseEntity;

import java.util.List;

/**
 * geng_xingdong.
 */
public class SC182206Bean extends BaseEntity {

    private String pdCode;
    /**产品编码 */
    private String slCodeManufacture;
    /**2位年份，2位月份，1位半旬号 */
    private String dateCode;
    /**10位卖家编号，前5位地区编号，后两位00001开始翻番 */
    private String slCode;
    /** 批次ID*/
    private String LotId;

    private List<SC182206Bean>  sc182206BeanList;

    public List<SC182206Bean> getSc182206BeanList() {
        return sc182206BeanList;
    }

    public void setSc182206BeanList(List<SC182206Bean> sc182206BeanList) {
        this.sc182206BeanList = sc182206BeanList;
    }

    public String getPdCode() {
        return pdCode;
    }

    public void setPdCode(String pdCode) {
        this.pdCode = pdCode;
    }

    public String getSlCodeManufacture() {
        return slCodeManufacture;
    }

    public void setSlCodeManufacture(String slCodeManufacture) {
        this.slCodeManufacture = slCodeManufacture;
    }

    public String getDateCode() {
        return dateCode;
    }

    public void setDateCode(String dateCode) {
        this.dateCode = dateCode;
    }

    public String getSlCode() {
        return slCode;
    }

    public void setSlCode(String slCode) {
        this.slCode = slCode;
    }

    public String getLotId() {
        return LotId;
    }

    public void setLotId(String lotId) {
        LotId = lotId;
    }
}
