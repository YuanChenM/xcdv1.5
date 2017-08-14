package com.msk.ssc.bean;

import com.hoperun.core.bean.BasePageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by xia_xiaojie on 2016/7/8.
 */
@ApiModel(value = "SSC11316Param",description = "预入库通知单入参")
public class SSC11316Param extends BasePageParam {
    /** 序列号 */
    private static final long serialVersionUID = 1L;

    /** 到货仓库 */
    @ApiModelProperty(value = "到货仓库")
    private String arriveWarehouse;

    /** 合同编号 */
    @ApiModelProperty(value = "合同编号")
    private String contractCode;

    /** 合同名称 */
    @ApiModelProperty(value = "合同名称")
    private String contractName;

    /** 司机手机号 */
    @ApiModelProperty(value = "司机手机号")
    private String driverTel;

    /** 入库单号 */
    @ApiModelProperty(value = "入库单号")
    private String intoStoreCode;

    /** 车牌号 */
    @ApiModelProperty(value = "车牌号 ")
    private String licPlateNumber;

    /** 收货状态 */
    @ApiModelProperty(value = "收货状态")
    private String productRecvStatus;

    /** 采购商 */
    @ApiModelProperty(value = "采购商名称")
    private String purchaserName;

    /** 生产商 */
    @ApiModelProperty(value = "生产商名称")
    private String supplierName;

    /** 收货状态 */
    @ApiModelProperty(value = "收货状态")
    private List<String> productRecvStatuses;

    /** 发货确认单ID */
    @ApiModelProperty(value = "发货确认单id")
    private String deliveryConfirmId;

    /** 发货预入库单ID */
    @ApiModelProperty(value = "发货预入库单id")
    private String deliveryPreIntoId;

    /** 预计发货日期 */
    @ApiModelProperty(value = "预计发货日期")
    private String expectDeliveryDate;

    /** 预计到货日期 */
    @ApiModelProperty(value = "预计到货日期")
    private String expectArriveDate;

    /** 发货预入库单编号 */
    @ApiModelProperty(value = "发货预入库单编号")
    private String deliveryPreIntoCode;

    /** 1 没有生成预入库通知单  2 已发车  3  未发车 */
    @ApiModelProperty(value = "标志位:1 没有生成预入库通知单  2 已发车  3  未发车")
    private String flag;

    /** 预入库编号集合 */
    @ApiModelProperty(value = "预入库编号集合")
    private List<String> preIntoCodeList;

    /** 发货订单编号 */
    @ApiModelProperty(value = "货订单编号")
    private String deliveryCode;

    /**发货订单id*/
    @ApiModelProperty(value = "发货订单id")
    private Long deliveryId;

    /** 发货确认单编号 */
    @ApiModelProperty(value = "发货确认单编号")
    private String deliveryConfirmCode;

    /** 发车批次 */
    @ApiModelProperty(value = "发车批次")
    private String deliveryBatch;

    /**发货车次*/
    @ApiModelProperty(value = "发货车次")
    private String vehicleNumber;

    /** 物流区编码 */
    @ApiModelProperty(value = "物流区编码")
    private String lgcsAreaCode;

    /** 物流区名称 */
    @ApiModelProperty(value = "物流区名称")
    private String lgcsAreaName;

    @ApiModelProperty(value = "发货订单id集合")
    private List<Long> deliveryIds;

    /** 一组发货预入库单ID */
    @ApiModelProperty(value = "发货预入库单id集合")
    private List<String> deliveryPreIntoIds;

    public String getDeliveryCode() {
        return deliveryCode;
    }

    public void setDeliveryCode(String deliveryCode) {
        this.deliveryCode = deliveryCode;
    }

    public String getDeliveryConfirmCode() {
        return deliveryConfirmCode;
    }

    public void setDeliveryConfirmCode(String deliveryConfirmCode) {
        this.deliveryConfirmCode = deliveryConfirmCode;
    }

    public String getArriveWarehouse() {
        return arriveWarehouse;
    }

    public void setArriveWarehouse(String arriveWarehouse) {
        this.arriveWarehouse = arriveWarehouse;
    }

    public String getContractCode() {
        return contractCode;
    }

    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public String getDriverTel() {
        return driverTel;
    }

    public void setDriverTel(String driverTel) {
        this.driverTel = driverTel;
    }

    public String getIntoStoreCode() {
        return intoStoreCode;
    }

    public void setIntoStoreCode(String intoStoreCode) {
        this.intoStoreCode = intoStoreCode;
    }

    public String getLicPlateNumber() {
        return licPlateNumber;
    }

    public void setLicPlateNumber(String licPlateNumber) {
        this.licPlateNumber = licPlateNumber;
    }

    public String getProductRecvStatus() {
        return productRecvStatus;
    }

    public void setProductRecvStatus(String productRecvStatus) {
        this.productRecvStatus = productRecvStatus;
    }

    public String getPurchaserName() {
        return purchaserName;
    }

    public void setPurchaserName(String purchaserName) {
        this.purchaserName = purchaserName;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public List<String> getProductRecvStatuses() {
        return productRecvStatuses;
    }

    public void setProductRecvStatuses(List<String> productRecvStatuses) {
        this.productRecvStatuses = productRecvStatuses;
    }

    public String getDeliveryConfirmId() {
        return deliveryConfirmId;
    }

    public void setDeliveryConfirmId(String deliveryConfirmId) {
        this.deliveryConfirmId = deliveryConfirmId;
    }

    public String getDeliveryPreIntoId() {
        return deliveryPreIntoId;
    }

    public void setDeliveryPreIntoId(String deliveryPreIntoId) {
        this.deliveryPreIntoId = deliveryPreIntoId;
    }

    public String getExpectDeliveryDate() {
        return expectDeliveryDate;
    }

    public void setExpectDeliveryDate(String expectDeliveryDate) {
        this.expectDeliveryDate = expectDeliveryDate;
    }

    public String getExpectArriveDate() {
        return expectArriveDate;
    }

    public void setExpectArriveDate(String expectArriveDate) {
        this.expectArriveDate = expectArriveDate;
    }

    public String getDeliveryPreIntoCode() {
        return deliveryPreIntoCode;
    }

    public void setDeliveryPreIntoCode(String deliveryPreIntoCode) {
        this.deliveryPreIntoCode = deliveryPreIntoCode;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public List<String> getPreIntoCodeList() {
        return preIntoCodeList;
    }

    public void setPreIntoCodeList(List<String> preIntoCodeList) {
        this.preIntoCodeList = preIntoCodeList;
    }

    public String getDeliveryBatch() {
        return deliveryBatch;
    }

    public void setDeliveryBatch(String deliveryBatch) {
        this.deliveryBatch = deliveryBatch;
    }

    public List<Long> getDeliveryIds() {
        return deliveryIds;
    }

    public void setDeliveryIds(List<Long> deliveryIds) {
        this.deliveryIds = deliveryIds;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public Long getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(Long deliveryId) {
        this.deliveryId = deliveryId;
    }

    public List<String> getDeliveryPreIntoIds() {
        return deliveryPreIntoIds;
    }

    public void setDeliveryPreIntoIds(List<String> deliveryPreIntoIds) {
        this.deliveryPreIntoIds = deliveryPreIntoIds;
    }

    public String getLgcsAreaCode() {
        return lgcsAreaCode;
    }

    public void setLgcsAreaCode(String lgcsAreaCode) {
        this.lgcsAreaCode = lgcsAreaCode;
    }

    public String getLgcsAreaName() {
        return lgcsAreaName;
    }

    public void setLgcsAreaName(String lgcsAreaName) {
        this.lgcsAreaName = lgcsAreaName;
    }
}
