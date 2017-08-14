package com.msk.seller.bean;

import com.msk.core.entity.BaseEntity;

/**
 * Created by Administrator on 2016/2/1.
 */
public class SL241103005Bean extends BaseEntity{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /** 企业ID */
    private Integer epId;
    /** 车间ID */
    private Integer workshopId;
    /** 车间名称 */
    private String workshopName;
    /** 生产产品 */
    private String product;
    /** 工艺流特点 */
    private String process;
    /** 车间名称 */
    private String workshopName0;
    /** 生产产品 */
    private String product0;
    /** 工艺流特点 */
    private String process0;

    /**
     * 获得serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * 获得epId
     */
    public Integer getEpId() {
        return epId;
    }

    /**
     * 设置epId
     */
    public void setEpId(Integer epId) {
        this.epId = epId;
    }

    /**
     * 获得workshopId
     */
    public Integer getWorkshopId() {
        return workshopId;
    }

    /**
     * 设置workshopId
     */
    public void setWorkshopId(Integer workshopId) {
        this.workshopId = workshopId;
    }

    /**
     * 获得workshopName
     */
    public String getWorkshopName() {
        return workshopName;
    }

    /**
     * 设置workshopName
     */
    public void setWorkshopName(String workshopName) {
        this.workshopName = workshopName;
    }

    /**
     * 获得product
     */
    public String getProduct() {
        return product;
    }

    /**
     * 设置product
     */
    public void setProduct(String product) {
        this.product = product;
    }

    /**
     * 获得process
     */
    public String getProcess() {
        return process;
    }

    /**
     * 设置process
     */
    public void setProcess(String process) {
        this.process = process;
    }

    /**
     * 获得workshopName0
     */
    public String getWorkshopName0() {
        return workshopName0;
    }

    /**
     * 设置workshopName0
     */
    public void setWorkshopName0(String workshopName0) {
        this.workshopName0 = workshopName0;
    }

    /**
     * 获得product0
     */
    public String getProduct0() {
        return product0;
    }

    /**
     * 设置product0
     */
    public void setProduct0(String product0) {
        this.product0 = product0;
    }

    /**
     * 获得process0
     */
    public String getProcess0() {
        return process0;
    }

    /**
     * 设置process0
     */
    public void setProcess0(String process0) {
        this.process0 = process0;
    }

}
