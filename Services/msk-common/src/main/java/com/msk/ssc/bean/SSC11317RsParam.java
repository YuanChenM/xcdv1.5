package com.msk.ssc.bean;

import com.hoperun.core.bean.BasePageParam;
import com.msk.core.entity.SscDeliveryPrePd;

import java.util.Date;
import java.util.List;

/**
 * Created by wang_shuai on 2016/7/8.
 */
public class SSC11317RsParam extends BasePageParam {
    /** 发货单编号 */
    private String deliveryCode;
    /** 发货预入库单ID */
    private java.lang.Long deliveryPreIntoId;
    /** 发货预入库单编号 */
    private java.lang.Long deliveryPreIntoCode;
    /** 合同id */
    private java.lang.Long contractId;
    /** 合同编号 */
    private java.lang.String contractCode;
    /** 发货确认单id */
    private java.lang.Long deliveryConfirmId;
    /** 发货确认单编号 */
    private java.lang.String deliveryConfirmCode;
    /** 司机名称 */
    private java.lang.String driverName;
    /** 司机手机号 */
    private java.lang.String driverTel;
    /** 发货责任人 */
    private java.lang.String deliveryResponsible;
    /** 发货责任人联系方式 */
    private java.lang.String deliveryResponsibleTel;
    /** 发货执行人 */
    private java.lang.String deliveryExecutor;
    /** 发货执行人联系方式 */
    private java.lang.String deliveryExecutorTel;
    /** 运输单位名称 */
    private java.lang.String trafficCompanyName;
    /** 运输单位责任人 */
    private java.lang.String trafficCompanyResponsible;
    /** 运输单位责任人联系电话 */
    private java.lang.String trafficCompanyResponsibleTel;
    /** 运输单位执行人 */
    private java.lang.String trafficCompanyExecutor;
    /** 运输单位执行人联系电话 */
    private java.lang.String trafficCompanyExecutorTel;
    /** 仓管负责人 */
    private java.lang.String warehouseKeeper;
    /** 仓管负责人联系电话 */
    private java.lang.String warehouseKeeperTel;
    /** 验收负责人 */
    private java.lang.String accepter;
    /** 验收负责人联系电话 */
    private java.lang.String accepterTel;
    /** 车牌号 */
    private java.lang.String licPlateNumber;
    /** 车辆类型 */
    private java.lang.String vehicleType;
    /** 实际到货日期 */
    private String arriveDate;
    /** 备注 */
    private String remark;

    /** 差异单ID 用于页面间的返回跳转*/
    private java.lang.Long differId;
    /** 发货订单ID */
    private Long deliveryId;
    /** 核销单ID */
    private Long verificationId;

    private List<SscDeliveryPrePd> prePdList;


    /** 发货备注*/
    private String sendRemark;
    /** 销售区域编码*/
    private String lgcsCode;
    /** 供应商编码*/
    private String supplierCode;
    /** 供应商名称*/
    private String supplierName;
    /** 计划到货时间*/
    private String startReceiptDate;
    /** 是否发车标识符*/
    private Boolean departureFlg;

    public String getDeliveryResponsible() {
        return deliveryResponsible;
    }

    public void setDeliveryResponsible(String deliveryResponsible) {
        this.deliveryResponsible = deliveryResponsible;
    }

    public String getDeliveryResponsibleTel() {
        return deliveryResponsibleTel;
    }

    public void setDeliveryResponsibleTel(String deliveryResponsibleTel) {
        this.deliveryResponsibleTel = deliveryResponsibleTel;
    }

    public String getDeliveryExecutor() {
        return deliveryExecutor;
    }

    public void setDeliveryExecutor(String deliveryExecutor) {
        this.deliveryExecutor = deliveryExecutor;
    }

    public String getDeliveryExecutorTel() {
        return deliveryExecutorTel;
    }

    public void setDeliveryExecutorTel(String deliveryExecutorTel) {
        this.deliveryExecutorTel = deliveryExecutorTel;
    }

    public String getTrafficCompanyName() {
        return trafficCompanyName;
    }

    public void setTrafficCompanyName(String trafficCompanyName) {
        this.trafficCompanyName = trafficCompanyName;
    }

    public String getTrafficCompanyResponsible() {
        return trafficCompanyResponsible;
    }

    public void setTrafficCompanyResponsible(String trafficCompanyResponsible) {
        this.trafficCompanyResponsible = trafficCompanyResponsible;
    }

    public String getTrafficCompanyResponsibleTel() {
        return trafficCompanyResponsibleTel;
    }

    public void setTrafficCompanyResponsibleTel(String trafficCompanyResponsibleTel) {
        this.trafficCompanyResponsibleTel = trafficCompanyResponsibleTel;
    }

    public String getTrafficCompanyExecutor() {
        return trafficCompanyExecutor;
    }

    public void setTrafficCompanyExecutor(String trafficCompanyExecutor) {
        this.trafficCompanyExecutor = trafficCompanyExecutor;
    }

    public String getTrafficCompanyExecutorTel() {
        return trafficCompanyExecutorTel;
    }

    public void setTrafficCompanyExecutorTel(String trafficCompanyExecutorTel) {
        this.trafficCompanyExecutorTel = trafficCompanyExecutorTel;
    }

    public String getWarehouseKeeper() {
        return warehouseKeeper;
    }

    public void setWarehouseKeeper(String warehouseKeeper) {
        this.warehouseKeeper = warehouseKeeper;
    }

    public String getWarehouseKeeperTel() {
        return warehouseKeeperTel;
    }

    public void setWarehouseKeeperTel(String warehouseKeeperTel) {
        this.warehouseKeeperTel = warehouseKeeperTel;
    }

    public String getAccepter() {
        return accepter;
    }

    public void setAccepter(String accepter) {
        this.accepter = accepter;
    }

    public String getAccepterTel() {
        return accepterTel;
    }

    public void setAccepterTel(String accepterTel) {
        this.accepterTel = accepterTel;
    }

    public String getDeliveryCode() {
        return deliveryCode;
    }

    public void setDeliveryCode(String deliveryCode) {
        this.deliveryCode = deliveryCode;
    }

    public Long getDeliveryPreIntoId() {
        return deliveryPreIntoId;
    }

    public void setDeliveryPreIntoId(Long deliveryPreIntoId) {
        this.deliveryPreIntoId = deliveryPreIntoId;
    }

    public Long getDeliveryPreIntoCode() {
        return deliveryPreIntoCode;
    }

    public void setDeliveryPreIntoCode(Long deliveryPreIntoCode) {
        this.deliveryPreIntoCode = deliveryPreIntoCode;
    }

    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    public String getContractCode() {
        return contractCode;
    }

    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }

    public Long getDeliveryConfirmId() {
        return deliveryConfirmId;
    }

    public void setDeliveryConfirmId(Long deliveryConfirmId) {
        this.deliveryConfirmId = deliveryConfirmId;
    }

    public String getDeliveryConfirmCode() {
        return deliveryConfirmCode;
    }

    public void setDeliveryConfirmCode(String deliveryConfirmCode) {
        this.deliveryConfirmCode = deliveryConfirmCode;
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

    public String getArriveDate() {
        return arriveDate;
    }

    public void setArriveDate(String arriveDate) {
        this.arriveDate = arriveDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<SscDeliveryPrePd> getPrePdList() {
        return prePdList;
    }

    public void setPrePdList(List<SscDeliveryPrePd> prePdList) {
        this.prePdList = prePdList;
    }

    public String getSendRemark() {
        return sendRemark;
    }

    public void setSendRemark(String sendRemark) {
        this.sendRemark = sendRemark;
    }

    public String getLgcsCode() {
        return lgcsCode;
    }

    public void setLgcsCode(String lgcsCode) {
        this.lgcsCode = lgcsCode;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getStartReceiptDate() {
        return startReceiptDate;
    }

    public void setStartReceiptDate(String startReceiptDate) {
        this.startReceiptDate = startReceiptDate;
    }

    public Boolean getDepartureFlg() {
        return departureFlg;
    }

    public void setDepartureFlg(Boolean departureFlg) {
        this.departureFlg = departureFlg;
    }

    public Long getDifferId() {
        return differId;
    }

    public void setDifferId(Long differId) {
        this.differId = differId;
    }

    public Long getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(Long deliveryId) {
        this.deliveryId = deliveryId;
    }

    public Long getVerificationId() {
        return verificationId;
    }

    public void setVerificationId(Long verificationId) {
        this.verificationId = verificationId;
    }
}
