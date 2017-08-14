/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.bms.ssc.bean.seller;

import com.msk.core.entity.SlEpCap;

/**
 *拼接厂房平面图bean
 *
 */
public class SlEpCapBean extends SlEpCap{

    // 资质描述厂房平面图
    private String aptitudeDesc;

    // 资质描述实验室
    private String aptitudeDesc2;
    // 资质描述设备
    private String aptitudeDesc3;
    //厂区图片路径
    private String workshopImgUrl;
    //库容图片路径
    private String warehouseImgUrl;
    //实验室图片路径
    private String labImgUrl;
    //检测设备_主要设备图片路径
    private String ddEquipmentImgUrl;
    //品控组织架构图图片路径
    private String controllerImgUrl;
    //质量控制系统图
    private String qualityImgUrl;

    //库容概括
    private String scapDesc;

    public String getAptitudeDesc3() {
        return aptitudeDesc3;
    }

    public void setAptitudeDesc3(String aptitudeDesc3) {
        this.aptitudeDesc3 = aptitudeDesc3;
    }

    public String getScapDesc() {
        return scapDesc;
    }

    public void setScapDesc(String scapDesc) {
        this.scapDesc = scapDesc;
    }

    public String getAptitudeDesc() {
        return aptitudeDesc;
    }

    public void setAptitudeDesc(String aptitudeDesc) {
        this.aptitudeDesc = aptitudeDesc;
    }

    public String getAptitudeDesc2() {
        return aptitudeDesc2;
    }

    public void setAptitudeDesc2(String aptitudeDesc2) {
        this.aptitudeDesc2 = aptitudeDesc2;
    }

    public String getWorkshopImgUrl() {
        return workshopImgUrl;
    }

    public void setWorkshopImgUrl(String workshopImgUrl) {
        this.workshopImgUrl = workshopImgUrl;
    }

    public String getWarehouseImgUrl() {
        return warehouseImgUrl;
    }

    public void setWarehouseImgUrl(String warehouseImgUrl) {
        this.warehouseImgUrl = warehouseImgUrl;
    }

    public String getLabImgUrl() {
        return labImgUrl;
    }

    public void setLabImgUrl(String labImgUrl) {
        this.labImgUrl = labImgUrl;
    }

    public String getDdEquipmentImgUrl() {
        return ddEquipmentImgUrl;
    }

    public void setDdEquipmentImgUrl(String ddEquipmentImgUrl) {
        this.ddEquipmentImgUrl = ddEquipmentImgUrl;
    }

    public String getControllerImgUrl() {
        return controllerImgUrl;
    }

    public void setControllerImgUrl(String controllerImgUrl) {
        this.controllerImgUrl = controllerImgUrl;
    }

    public String getQualityImgUrl() {
        return qualityImgUrl;
    }

    public void setQualityImgUrl(String qualityImgUrl) {
        this.qualityImgUrl = qualityImgUrl;
    }
}
