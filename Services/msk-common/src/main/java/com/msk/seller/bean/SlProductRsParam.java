package com.msk.seller.bean;

import com.hoperun.core.bean.BaseParam;

/**
 * Created by liu_yan2 on 2016/6/21.
 */
public class SlProductRsParam extends BaseParam {


    /** 卖家主分类 */
    private String slMainClass;

    /** 卖家地区 */
    private String slLicAddr;

    /** 卖家名称 */
    private String slName;

    /** 生产商地区 */
    private String sllfAddr;

    /** 生产商名称 */
    private String sllfName;

    /** 品牌类型 */
    private String brandClass;

    /** 品牌名称 */
    private String brandName;

    public String getSlMainClass() {
        return slMainClass;
    }

    public void setSlMainClass(String slMainClass) {
        this.slMainClass = slMainClass;
    }

    public String getSlLicAddr() {
        return slLicAddr;
    }

    public void setSlLicAddr(String slLicAddr) {
        this.slLicAddr = slLicAddr;
    }

    public String getSlName() {
        return slName;
    }

    public void setSlName(String slName) {
        this.slName = slName;
    }

    public String getSllfAddr() {
        return sllfAddr;
    }

    public void setSllfAddr(String sllfAddr) {
        this.sllfAddr = sllfAddr;
    }

    public String getSllfName() {
        return sllfName;
    }

    public void setSllfName(String sllfName) {
        this.sllfName = sllfName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getBrandClass() {
        return brandClass;
    }

    public void setBrandClass(String brandClass) {
        this.brandClass = brandClass;
    }
}
