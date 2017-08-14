package com.msk.seller.bean;


import com.msk.common.base.BaseBean;

/**
 * rwf
 * 企业生产能力
 */
public class SL2411030302Bean extends BaseBean {

    /** serialVersionUID */
    private static final long serialVersionUID = 1L;
    // 企业ID
    private String epId;
    // 厂区总资产
    private String ftyAsset;
    // 厂区注册资本
    private String ftyRegCapital;
    // 厂区占地面积
    private String ftyLandArea;
    // 厂区厂房面积
    private String ftyFloorArea;
    // 厂区主要设备
    private String ftyEquipment;
    // 厂区设计产能
    private String ftyDesignCap;
    // 厂区实际产能
    private String ftyActualCap;
    // 厂区外贸销售占比
    private String ftyFtRate;
    // 厂区直销占比
    private String ftyDsRate;
    // 厂区代理销售占比
    private String ftyAsRate;
    // 库容概括_原料库容
    private String scapMaterial;
    // 库容概括成品库容
    private String scapProduct;
    //库容概括
    private String scapDesc;
    // 实验室_面积
    private String labArea;
    // 实验室功能
    private String labFunction;
    // 实验室投资
    private String labInvestment;
    // 实验室人员
    private Integer labMember;
    // 检测设备_主要设备及用途
    private String ddEquipment;
    // 资质描述实验室
    private String aptitudeDesc;
    // 资质描述设备
    private String aptitudeDesc2;
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

    /**
     * 获得workshopImgUrl
     */
    public String getWorkshopImgUrl() {
        return workshopImgUrl;
    }

    /**
     * 设置workshopImgUrl
     */
    public void setWorkshopImgUrl(String workshopImgUrl) {
        this.workshopImgUrl = workshopImgUrl;
    }

    /**
     * 获得warehouseImgUrl
     */
    public String getWarehouseImgUrl() {
        return warehouseImgUrl;
    }

    /**
     * 设置warehouseImgUrl
     */
    public void setWarehouseImgUrl(String warehouseImgUrl) {
        this.warehouseImgUrl = warehouseImgUrl;
    }

    /**
     * 获得labImgUrl
     */
    public String getLabImgUrl() {
        return labImgUrl;
    }

    /**
     * 设置labImgUrl
     */
    public void setLabImgUrl(String labImgUrl) {
        this.labImgUrl = labImgUrl;
    }

    /**
     * 获得ddEquipmentImgUrl
     */
    public String getDdEquipmentImgUrl() {
        return ddEquipmentImgUrl;
    }

    /**
     * 设置ddEquipmentImgUrl
     */
    public void setDdEquipmentImgUrl(String ddEquipmentImgUrl) {
        this.ddEquipmentImgUrl = ddEquipmentImgUrl;
    }

    /**
     * 获得controllerImgUrl
     */
    public String getControllerImgUrl() {
        return controllerImgUrl;
    }

    /**
     * 设置controllerImgUrl
     */
    public void setControllerImgUrl(String controllerImgUrl) {
        this.controllerImgUrl = controllerImgUrl;
    }

    /**
     * 获得qualityImgUrl
     */
    public String getQualityImgUrl() {
        return qualityImgUrl;
    }

    /**
     * 设置qualityImgUrl
     */
    public void setQualityImgUrl(String qualityImgUrl) {
        this.qualityImgUrl = qualityImgUrl;
    }

    /**
     * Get the epId.
     *
     * @return epId
     *
     * @author rwf
     */
    public String getEpId() {
        return this.epId;
    }

    /**
     * Set the epId.
     *
     * @param epId epId
     *
     * @author rwf
     */
    public void setEpId(String epId) {
        this.epId = epId;
    }

    /**
     * Get the ftyAsset.
     *
     * @return ftyAsset
     *
     * @author rwf
     */
    public String getFtyAsset() {
        return this.ftyAsset;
    }

    /**
     * Set the ftyAsset.
     *
     * @param ftyAsset ftyAsset
     *
     * @author rwf
     */
    public void setFtyAsset(String ftyAsset) {
        this.ftyAsset = ftyAsset;
    }

    /**
     * Get the ftyRegCapital.
     *
     * @return ftyRegCapital
     *
     * @author rwf
     */
    public String getFtyRegCapital() {
        return this.ftyRegCapital;
    }

    /**
     * Set the ftyRegCapital.
     *
     * @param ftyRegCapital ftyRegCapital
     *
     * @author rwf
     */
    public void setFtyRegCapital(String ftyRegCapital) {
        this.ftyRegCapital = ftyRegCapital;
    }

    /**
     * Get the ftyLandArea.
     *
     * @return ftyLandArea
     *
     * @author rwf
     */
    public String getFtyLandArea() {
        return this.ftyLandArea;
    }

    /**
     * Set the ftyLandArea.
     *
     * @param ftyLandArea ftyLandArea
     *
     * @author rwf
     */
    public void setFtyLandArea(String ftyLandArea) {
        this.ftyLandArea = ftyLandArea;
    }

    /**
     * Get the ftyFloorArea.
     *
     * @return ftyFloorArea
     *
     * @author rwf
     */
    public String getFtyFloorArea() {
        return this.ftyFloorArea;
    }

    /**
     * Set the ftyFloorArea.
     *
     * @param ftyFloorArea ftyFloorArea
     *
     * @author rwf
     */
    public void setFtyFloorArea(String ftyFloorArea) {
        this.ftyFloorArea = ftyFloorArea;
    }

    /**
     * Get the ftyEquipment.
     *
     * @return ftyEquipment
     *
     * @author rwf
     */
    public String getFtyEquipment() {
        return this.ftyEquipment;
    }

    /**
     * Set the ftyEquipment.
     *
     * @param ftyEquipment ftyEquipment
     *
     * @author rwf
     */
    public void setFtyEquipment(String ftyEquipment) {
        this.ftyEquipment = ftyEquipment;
    }

    /**
     * Get the ftyDesignCap.
     *
     * @return ftyDesignCap
     *
     * @author rwf
     */
    public String getFtyDesignCap() {
        return this.ftyDesignCap;
    }

    /**
     * Set the ftyDesignCap.
     *
     * @param ftyDesignCap ftyDesignCap
     *
     * @author rwf
     */
    public void setFtyDesignCap(String ftyDesignCap) {
        this.ftyDesignCap = ftyDesignCap;
    }

    /**
     * Get the ftyActualCap.
     *
     * @return ftyActualCap
     *
     * @author rwf
     */
    public String getFtyActualCap() {
        return this.ftyActualCap;
    }

    /**
     * Set the ftyActualCap.
     *
     * @param ftyActualCap ftyActualCap
     *
     * @author rwf
     */
    public void setFtyActualCap(String ftyActualCap) {
        this.ftyActualCap = ftyActualCap;
    }

    /**
     * Get the ftyFtRate.
     *
     * @return ftyFtRate
     *
     * @author rwf
     */
    public String getFtyFtRate() {
        return this.ftyFtRate;
    }

    /**
     * Set the ftyFtRate.
     *
     * @param ftyFtRate ftyFtRate
     *
     * @author rwf
     */
    public void setFtyFtRate(String ftyFtRate) {
        this.ftyFtRate = ftyFtRate;
    }

    /**
     * Get the ftyDsRate.
     *
     * @return ftyDsRate
     *
     * @author rwf
     */
    public String getFtyDsRate() {
        return this.ftyDsRate;
    }

    /**
     * Set the ftyDsRate.
     *
     * @param ftyDsRate ftyDsRate
     *
     * @author rwf
     */
    public void setFtyDsRate(String ftyDsRate) {
        this.ftyDsRate = ftyDsRate;
    }

    /**
     * Get the ftyAsRate.
     *
     * @return ftyAsRate
     *
     * @author rwf
     */
    public String getFtyAsRate() {
        return this.ftyAsRate;
    }

    /**
     * Set the ftyAsRate.
     *
     * @param ftyAsRate ftyAsRate
     *
     * @author rwf
     */
    public void setFtyAsRate(String ftyAsRate) {
        this.ftyAsRate = ftyAsRate;
    }

    /**
     * Get the scapMaterial.
     *
     * @return scapMaterial
     *
     * @author rwf
     */
    public String getScapMaterial() {
        return this.scapMaterial;
    }

    /**
     * Set the scapMaterial.
     *
     * @param scapMaterial scapMaterial
     *
     * @author rwf
     */
    public void setScapMaterial(String scapMaterial) {
        this.scapMaterial = scapMaterial;
    }

    /**
     * Get the scapProduct.
     *
     * @return scapProduct
     *
     * @author rwf
     */
    public String getScapProduct() {
        return this.scapProduct;
    }

    /**
     * Set the scapProduct.
     *
     * @param scapProduct scapProduct
     *
     * @author rwf
     */
    public void setScapProduct(String scapProduct) {
        this.scapProduct = scapProduct;
    }

    /**
     * Get the labArea.
     *
     * @return labArea
     *
     * @author rwf
     */
    public String getLabArea() {
        return this.labArea;
    }

    /**
     * Set the labArea.
     *
     * @param labArea labArea
     *
     * @author rwf
     */
    public void setLabArea(String labArea) {
        this.labArea = labArea;
    }

    /**
     * Get the labFunction.
     *
     * @return labFunction
     *
     * @author rwf
     */
    public String getLabFunction() {
        return this.labFunction;
    }

    /**
     * Set the labFunction.
     *
     * @param labFunction labFunction
     *
     * @author rwf
     */
    public void setLabFunction(String labFunction) {
        this.labFunction = labFunction;
    }

    /**
     * Get the labInvestment.
     *
     * @return labInvestment
     *
     * @author rwf
     */
    public String getLabInvestment() {
        return this.labInvestment;
    }

    /**
     * Set the labInvestment.
     *
     * @param labInvestment labInvestment
     *
     * @author rwf
     */
    public void setLabInvestment(String labInvestment) {
        this.labInvestment = labInvestment;
    }

    /**
     * Get the labMember.
     *
     * @return labMember
     *
     * @author rwf
     */
    public Integer getLabMember() {
        return this.labMember;
    }

    /**
     * Set the labMember.
     *
     * @param labMember labMember
     *
     * @author rwf
     */
    public void setLabMember(Integer labMember) {
        this.labMember = labMember;
    }

    /**
     * Get the ddEquipment.
     *
     * @return ddEquipment
     *
     * @author rwf
     */
    public String getDdEquipment() {
        return this.ddEquipment;
    }

    /**
     * Set the ddEquipment.
     *
     * @param ddEquipment ddEquipment
     *
     * @author rwf
     */
    public void setDdEquipment(String ddEquipment) {
        this.ddEquipment = ddEquipment;
    }

    /**
     * Get the aptitudeDesc.
     *
     * @return aptitudeDesc
     *
     * @author rwf
     */
    public String getAptitudeDesc() {
        return this.aptitudeDesc;
    }

    /**
     * Set the aptitudeDesc.
     *
     * @param aptitudeDesc aptitudeDesc
     *
     * @author rwf
     */
    public void setAptitudeDesc(String aptitudeDesc) {
        this.aptitudeDesc = aptitudeDesc;
    }

    /**
     * Get the aptitudeDesc2.
     *
     * @return aptitudeDesc2
     *
     * @author rwf
     */
    public String getAptitudeDesc2() {
        return this.aptitudeDesc2;
    }

    /**
     * Set the aptitudeDesc2.
     *
     * @param aptitudeDesc2 aptitudeDesc2
     *
     * @author rwf
     */
    public void setAptitudeDesc2(String aptitudeDesc2) {
        this.aptitudeDesc2 = aptitudeDesc2;
    }

    /**
     * Get the scapDesc.
     *
     * @return scapDesc
     *
     * @author rwf
     */
    public String getScapDesc() {
        return this.scapDesc;
    }

    /**
     * Set the scapDesc.
     *
     * @param scapDesc scapDesc
     *
     * @author rwf
     */
    public void setScapDesc(String scapDesc) {
        this.scapDesc = scapDesc;
    }

}
