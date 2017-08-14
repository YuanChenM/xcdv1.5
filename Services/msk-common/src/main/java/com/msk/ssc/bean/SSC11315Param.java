package com.msk.ssc.bean;

import com.hoperun.core.bean.BasePageParam;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 发货确认单明细画面param
 * <p/>
 * Created by sun_jiaju on 2016/07/4.
 */
public class SSC11315Param extends BasePageParam {
    // 发货确认单编号
    private String deliveryConfirmCode;
    /** 合同ID */
    private String contractId;
    /** 合同编号 */
    private String contractCode;
    // 合同名称
    private String contractName;
    // id
    private Long detailId;
    // deliveryConfirmId
    private Long deliveryConfirmId;
    // 产品发货箱数
    private Integer productConfirmBox;
    // 产品发货重量
    private BigDecimal productQua;
    // 货值
    private BigDecimal productValue;
    /** 发货订单ID */
    private String deliveryId;
    // 发货订单编号
    private String deliveryCode;
    // 发货确认单状态
    private String deliveryConfirmStatus;
    // 采购方确认原因
    private String byConfirmReason;
    // 仓库方确认原因
    private String whConfirmReason;
    // 生产方确认原因
    private String pdConfirmReason;
    // 采购方确认状态
    private String byConfirmStatus;
    // 仓库方确认状态
    private String whConfirmStatus;
    // 生产方确认状态
    private String pdConfirmStatus;
    // 发货日期
    private String etd;
    // 到货日期
    private String eta;
    //结算标准价
    /** 采购方确认ID */
    private java.lang.String byConfirmId;
    /** 采购方确认人 */
    private java.lang.String byConfirmName;
    /** 采购方确认时间 */
    private java.util.Date byConfirmTime;
    /** 仓库方确认ID */
    private java.lang.String whConfirmId;
    /** 仓库方确认人 */
    private java.lang.String whConfirmName;
    /** 仓库方确认时间 */
    private java.util.Date whConfirmTime;
    /** 生产方确认ID */
    private java.lang.String pdConfirmId;
    /** 生产方确认人 */
    private java.lang.String pdConfirmName;

    /** 生产方确认时间 */
    private java.util.Date pdConfirmTime;

    private BigDecimal settkementStandardPrice;

    private String deliveryPreIntoId;

    private String pdCodesStr;
    private String productPlanBoxesStr;

    private String[] productPlanBoxes;

    private String[] pdCodes;

    /** 司机名称 */
    private java.lang.String driverName;
    /** 司机手机号 */
    private java.lang.String driverTel;
    /** 车牌号 */
    private java.lang.String licPlateNumber;
    /** 车辆类型 */
    private java.lang.String vehicleType;
    /** 车辆类型 */
    private String deliveryBatch;

    /** 到货仓库 */
    private java.lang.String arriveWarehouse;
    /** 到货仓库地址 */
    private java.lang.String arriveWarehouseAddr;

    private String crtName; //创建者姓名


    /** 订单确认版本号备份*/
    private Integer deliveryConfirmBasicVer;


    public Integer getDeliveryConfirmBasicVer() {
        return deliveryConfirmBasicVer;
    }

    public void setDeliveryConfirmBasicVer(Integer deliveryConfirmBasicVer) {
        this.deliveryConfirmBasicVer = deliveryConfirmBasicVer;
    }

    public String getByConfirmId() {
        return byConfirmId;
    }

    public void setByConfirmId(String byConfirmId) {
        this.byConfirmId = byConfirmId;
    }

    public String getByConfirmName() {
        return byConfirmName;
    }

    public void setByConfirmName(String byConfirmName) {
        this.byConfirmName = byConfirmName;
    }

    public Date getByConfirmTime() {
        return byConfirmTime;
    }

    public void setByConfirmTime(Date byConfirmTime) {
        this.byConfirmTime = byConfirmTime;
    }

    public String getWhConfirmId() {
        return whConfirmId;
    }

    public void setWhConfirmId(String whConfirmId) {
        this.whConfirmId = whConfirmId;
    }

    public String getWhConfirmName() {
        return whConfirmName;
    }

    public void setWhConfirmName(String whConfirmName) {
        this.whConfirmName = whConfirmName;
    }

    public Date getWhConfirmTime() {
        return whConfirmTime;
    }

    public void setWhConfirmTime(Date whConfirmTime) {
        this.whConfirmTime = whConfirmTime;
    }

    public String getPdConfirmId() {
        return pdConfirmId;
    }

    public void setPdConfirmId(String pdConfirmId) {
        this.pdConfirmId = pdConfirmId;
    }

    public String getPdConfirmName() {
        return pdConfirmName;
    }

    public void setPdConfirmName(String pdConfirmName) {
        this.pdConfirmName = pdConfirmName;
    }

    public Date getPdConfirmTime() {
        return pdConfirmTime;
    }

    public void setPdConfirmTime(Date pdConfirmTime) {
        this.pdConfirmTime = pdConfirmTime;
    }

    public String getDeliveryConfirmCode() {
        return deliveryConfirmCode;
    }

    public void setDeliveryConfirmCode(String deliveryConfirmCode) {
        this.deliveryConfirmCode = deliveryConfirmCode;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public Long getDeliveryConfirmId() {
        return deliveryConfirmId;
    }

    public void setDeliveryConfirmId(Long deliveryConfirmId) {
        this.deliveryConfirmId = deliveryConfirmId;
    }

    public BigDecimal getProductValue() {
        return productValue;
    }

    public void setProductValue(BigDecimal productValue) {
        this.productValue = productValue;
    }

    public String getDeliveryCode() {
        return deliveryCode;
    }

    public void setDeliveryCode(String deliveryCode) {
        this.deliveryCode = deliveryCode;
    }

    public String getDeliveryConfirmStatus() {
        return deliveryConfirmStatus;
    }

    public void setDeliveryConfirmStatus(String deliveryConfirmStatus) {
        this.deliveryConfirmStatus = deliveryConfirmStatus;
    }

    public String getByConfirmReason() {
        return byConfirmReason;
    }

    public void setByConfirmReason(String byConfirmReason) {
        this.byConfirmReason = byConfirmReason;
    }

    public String getWhConfirmReason() {
        return whConfirmReason;
    }

    public void setWhConfirmReason(String whConfirmReason) {
        this.whConfirmReason = whConfirmReason;
    }

    public String getPdConfirmReason() {
        return pdConfirmReason;
    }

    public void setPdConfirmReason(String pdConfirmReason) {
        this.pdConfirmReason = pdConfirmReason;
    }

    public String getByConfirmStatus() {
        return byConfirmStatus;
    }

    public void setByConfirmStatus(String byConfirmStatus) {
        this.byConfirmStatus = byConfirmStatus;
    }

    public String getWhConfirmStatus() {
        return whConfirmStatus;
    }

    public void setWhConfirmStatus(String whConfirmStatus) {
        this.whConfirmStatus = whConfirmStatus;
    }

    public String getPdConfirmStatus() {
        return pdConfirmStatus;
    }

    public void setPdConfirmStatus(String pdConfirmStatus) {
        this.pdConfirmStatus = pdConfirmStatus;
    }

    public String getEtd() {
        return etd;
    }

    public void setEtd(String etd) {
        this.etd = etd;
    }

    public String getEta() {
        return eta;
    }

    public void setEta(String eta) {
        this.eta = eta;
    }

    public Long getDetailId() {
        return detailId;
    }

    public void setDetailId(Long detailId) {
        this.detailId = detailId;
    }

    public Integer getProductConfirmBox() {
        return productConfirmBox;
    }

    public void setProductConfirmBox(Integer productConfirmBox) {
        this.productConfirmBox = productConfirmBox;
    }

    public BigDecimal getProductQua() {
        return productQua;
    }

    public void setProductQua(BigDecimal productQua) {
        this.productQua = productQua;
    }

    public String[] getProductPlanBoxes() {
        return productPlanBoxes;
    }

    public void setProductPlanBoxes(String[] productPlanBoxes) {
        this.productPlanBoxes = productPlanBoxes;
    }

    public String[] getPdCodes() {
        return pdCodes;
    }

    public void setPdCodes(String[] pdCodes) {
        this.pdCodes = pdCodes;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverTel() {
        return driverTel;
    }

    public void setDriverTel(String driverTel) {
        this.driverTel = driverTel;
    }

    public String getLicPlateNumber() {
        return licPlateNumber;
    }

    public void setLicPlateNumber(String licPlateNumber) {
        this.licPlateNumber = licPlateNumber;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getPdCodesStr() {
        return pdCodesStr;
    }

    public void setPdCodesStr(String pdCodesStr) {
        this.pdCodesStr = pdCodesStr;
    }

    public String getProductPlanBoxesStr() {
        return productPlanBoxesStr;
    }

    public void setProductPlanBoxesStr(String productPlanBoxesStr) {
        this.productPlanBoxesStr = productPlanBoxesStr;
    }
    public BigDecimal getSettkementStandardPrice() {
        return settkementStandardPrice;
    }
    public void setSettkementStandardPrice(BigDecimal settkementStandardPrice) {
        this.settkementStandardPrice = settkementStandardPrice;
    }

    public String getArriveWarehouse() {
        return arriveWarehouse;
    }

    public void setArriveWarehouse(String arriveWarehouse) {
        this.arriveWarehouse = arriveWarehouse;
    }

    public String getArriveWarehouseAddr() {
        return arriveWarehouseAddr;
    }

    public void setArriveWarehouseAddr(String arriveWarehouseAddr) {
        this.arriveWarehouseAddr = arriveWarehouseAddr;
    }

    public String getDeliveryPreIntoId() {
        return deliveryPreIntoId;
    }

    public void setDeliveryPreIntoId(String deliveryPreIntoId) {
        this.deliveryPreIntoId = deliveryPreIntoId;
    }

    public String getDeliveryBatch() {
        return deliveryBatch;
    }

    public void setDeliveryBatch(String deliveryBatch) {
        this.deliveryBatch = deliveryBatch;
    }

    public String getCrtName() {
        return crtName;
    }

    public void setCrtName(String crtName) {
        this.crtName = crtName;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getContractCode() {
        return contractCode;
    }

    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }

    public String getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(String deliveryId) {
        this.deliveryId = deliveryId;
    }
}
