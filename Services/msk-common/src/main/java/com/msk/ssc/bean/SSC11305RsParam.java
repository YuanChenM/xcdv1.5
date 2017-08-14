package com.msk.ssc.bean;

import com.hoperun.core.bean.BasePageParam;
import com.msk.core.entity.SscDeliveryOrderBasic;
import com.msk.core.entity.SscDeliveryOrderPd;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 发货订单一览画面param
 * <p/>
 * Created by peng_hao on 2016/07/05.
 */
public class SSC11305RsParam extends BasePageParam {

    /** 发货产品Id */
    private Long detailId;
    /** 发货订单Id */
    private Long deliveryId;
    /** 发货订单编号 */
    private String deliveryCode;
    /** 合同ID */
    private Long contractId;
    /** 合同编号 */
    private String contractCode;
    /** 合同名称 */
    private String contractName;
    /** 发货批次 */
    private String deliveryBatch;
    /** 生产商ID */
    private Long supplierId;
    /** 生产商（供应商） */
    private String supplierName;
    /** 生产商编码 */
    private String supplierCode;
    /** 采购商ID */
    private String purchaserId;
    /** 采购商 */
    private String purchaserName;
    /** 采购商编码*/
    private String purchaserCode;

    /** 发货日期 yyyy/MM/dd 格式 */
    private String etdStr;

    /** 到货日期 yyyy/MM/dd 格式 */
    private String etaStr;
    /** 发货仓库 */
    private String deliveryWarehouse;
    /** 发货仓库地址 */
    private String deliveryWarehouseAddr;
    /** 到货仓库 */
    private String arriveWarehouse;
    /** 到货仓库地址 */
    private String arriveWarehouseAddr;
    /** 发货订单状态 */
    private String deliveryStatus;
    /** 运费结算方式*/
    private String freightSettleMethod;
    /** 运费单价*/
    private BigDecimal freightUnit;
    /** 运费里程*/
    private BigDecimal mileage;
    /** 每吨运费*/
    private BigDecimal transportUnit;
    /** 运费*/
    private BigDecimal transportCost;
    /** 备注 */
    private String remark;
    /** 产品编码 */
    private String pdCode;
    /** 产品描述 */
    private String pdDesc;
    /** 产品类型编码 */
    private String classesCode;
    /** 产品类型名称 */
    private String classesName;
    /** 产品加工类型编码 */
    private String machiningCode;
    /** 产品加工类型名称 */
    private String machiningName;
    /** 产品品种编码 */
    private String breedCode;
    /** 产品品种名称 */
    private String breedName;
    /** 产品净重编码 */
    private String weightCode;
    /** 产品净重名称 */
    private String weightName;
    /** 产品包装规格编码 */
    private String normsCode;
    /** 产品包装规格名称 */
    private String normsName;
    /** 产品等级编码 */
    private String gradeCode;
    /** 产品等级名称 */
    private String gradeName;
    /** 产品质量标准 */
    private String productQualityStandard;
    /** 产品重量 */
    private BigDecimal productQua;
    /** 产品箱数 */
    private Integer productBox;
    /** 不含包装离岸价 */
    private BigDecimal fobFreePackage;
    /** 包材成本 */
    private BigDecimal packageCost;
    /** 含包装离岸价 */
    private BigDecimal fobIncludePackage;
    /** 干线运费 */
    private BigDecimal trunkFreight;
    /** 到岸价 */
    private BigDecimal cif;
    /** 本次结算标准价 */
    private BigDecimal settkementStandardPrice;
    /** 首付比例 */
    private String downPayMent;
    /** 付款额*/
    private Integer paymentAmount;
    /** 货值 */
    private BigDecimal productValue;
    /** 产品表备注 */
    private String pdremark;
    /** 一组订单状态*/
    private String[] statusArr;
    /** 一组关联合同类型*/
    private String[] relationTypeArr;
    /** 物流区编码 */
    private String lgcsAreaCode;
    /** 物流区名称 */
    private String lgcsAreaName;
    /** 是否生成发货确认单 */
    private String isDeliveryConfirm;
    /** 是否生成付款申请 */
    private String isPaymentRequest;
    /**发货订单编号 文本框id*/
    private String deliveryCodeId;
    /** 支付类型 */
    private Integer paymentType;

    public String[] getStatusArr() {
        return statusArr;
    }

    public void setStatusArr(String[] statusArr) {
        this.statusArr = statusArr;
    }

    public Long getDetailId() {
        return detailId;
    }

    public void setDetailId(Long detailId) {
        this.detailId = detailId;
    }

    private List<SscDeliveryOrderBasic> basicList;

    private List<SscDeliveryOrderPd> pdList;

    private List<SSC11305RsParam> paramList;

    public List<SSC11305RsParam> getParamList() {
        return paramList;
    }

    public void setParamList(List<SSC11305RsParam> paramList) {
        this.paramList = paramList;
    }

    public List<SscDeliveryOrderBasic> getBasicList() {
        return basicList;
    }

    public void setBasicList(List<SscDeliveryOrderBasic> basicList) {
        this.basicList = basicList;
    }

    public List<SscDeliveryOrderPd> getPdList() {
        return pdList;
    }

    public void setPdList(List<SscDeliveryOrderPd> pdList) {
        this.pdList = pdList;
    }

    public Long getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(Long deliveryId) {
        this.deliveryId = deliveryId;
    }

    public String getDeliveryCode() {
        return deliveryCode;
    }

    public void setDeliveryCode(String deliveryCode) {
        this.deliveryCode = deliveryCode;
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

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public String getDeliveryBatch() {
        return deliveryBatch;
    }

    public void setDeliveryBatch(String deliveryBatch) {
        this.deliveryBatch = deliveryBatch;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getPurchaserId() {
        return purchaserId;
    }

    public void setPurchaserId(String purchaserId) {
        this.purchaserId = purchaserId;
    }

    public String getPurchaserName() {
        return purchaserName;
    }

    public void setPurchaserName(String purchaserName) {
        this.purchaserName = purchaserName;
    }

    public String getPurchaserCode() {
        return purchaserCode;
    }

    public void setPurchaserCode(String purchaserCode) {
        this.purchaserCode = purchaserCode;
    }

    public String getEtdStr() {
        return etdStr;
    }

    public void setEtdStr(String etdStr) {
        this.etdStr = etdStr;
    }

    public String getEtaStr() {
        return etaStr;
    }

    public void setEtaStr(String etaStr) {
        this.etaStr = etaStr;
    }

    public String getDeliveryWarehouse() {
        return deliveryWarehouse;
    }

    public void setDeliveryWarehouse(String deliveryWarehouse) {
        this.deliveryWarehouse = deliveryWarehouse;
    }

    public String getDeliveryWarehouseAddr() {
        return deliveryWarehouseAddr;
    }

    public void setDeliveryWarehouseAddr(String deliveryWarehouseAddr) {
        this.deliveryWarehouseAddr = deliveryWarehouseAddr;
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

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public String getFreightSettleMethod() {
        return freightSettleMethod;
    }

    public void setFreightSettleMethod(String freightSettleMethod) {
        this.freightSettleMethod = freightSettleMethod;
    }

    public BigDecimal getFreightUnit() {
        return freightUnit;
    }

    public void setFreightUnit(BigDecimal freightUnit) {
        this.freightUnit = freightUnit;
    }

    public BigDecimal getMileage() {
        return mileage;
    }

    public void setMileage(BigDecimal mileage) {
        this.mileage = mileage;
    }

    public BigDecimal getTransportUnit() {
        return transportUnit;
    }

    public void setTransportUnit(BigDecimal transportUnit) {
        this.transportUnit = transportUnit;
    }

    public BigDecimal getTransportCost() {
        return transportCost;
    }

    public void setTransportCost(BigDecimal transportCost) {
        this.transportCost = transportCost;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPdCode() {
        return pdCode;
    }

    public void setPdCode(String pdCode) {
        this.pdCode = pdCode;
    }

    public String getPdDesc() {
        return pdDesc;
    }

    public void setPdDesc(String pdDesc) {
        this.pdDesc = pdDesc;
    }

    public String getClassesCode() {
        return classesCode;
    }

    public void setClassesCode(String classesCode) {
        this.classesCode = classesCode;
    }

    public String getClassesName() {
        return classesName;
    }

    public void setClassesName(String classesName) {
        this.classesName = classesName;
    }

    public String getMachiningCode() {
        return machiningCode;
    }

    public void setMachiningCode(String machiningCode) {
        this.machiningCode = machiningCode;
    }

    public String getMachiningName() {
        return machiningName;
    }

    public void setMachiningName(String machiningName) {
        this.machiningName = machiningName;
    }

    public String getBreedCode() {
        return breedCode;
    }

    public void setBreedCode(String breedCode) {
        this.breedCode = breedCode;
    }

    public String getBreedName() {
        return breedName;
    }

    public void setBreedName(String breedName) {
        this.breedName = breedName;
    }

    public String getWeightCode() {
        return weightCode;
    }

    public void setWeightCode(String weightCode) {
        this.weightCode = weightCode;
    }

    public String getWeightName() {
        return weightName;
    }

    public void setWeightName(String weightName) {
        this.weightName = weightName;
    }

    public String getNormsCode() {
        return normsCode;
    }

    public void setNormsCode(String normsCode) {
        this.normsCode = normsCode;
    }

    public String getNormsName() {
        return normsName;
    }

    public void setNormsName(String normsName) {
        this.normsName = normsName;
    }

    public String getGradeCode() {
        return gradeCode;
    }

    public void setGradeCode(String gradeCode) {
        this.gradeCode = gradeCode;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public String getProductQualityStandard() {
        return productQualityStandard;
    }

    public void setProductQualityStandard(String productQualityStandard) {
        this.productQualityStandard = productQualityStandard;
    }

    public BigDecimal getProductQua() {
        return productQua;
    }

    public void setProductQua(BigDecimal productQua) {
        this.productQua = productQua;
    }

    public Integer getProductBox() {
        return productBox;
    }

    public void setProductBox(Integer productBox) {
        this.productBox = productBox;
    }

    public BigDecimal getFobFreePackage() {
        return fobFreePackage;
    }

    public void setFobFreePackage(BigDecimal fobFreePackage) {
        this.fobFreePackage = fobFreePackage;
    }

    public BigDecimal getPackageCost() {
        return packageCost;
    }

    public void setPackageCost(BigDecimal packageCost) {
        this.packageCost = packageCost;
    }

    public BigDecimal getFobIncludePackage() {
        return fobIncludePackage;
    }

    public void setFobIncludePackage(BigDecimal fobIncludePackage) {
        this.fobIncludePackage = fobIncludePackage;
    }

    public BigDecimal getTrunkFreight() {
        return trunkFreight;
    }

    public void setTrunkFreight(BigDecimal trunkFreight) {
        this.trunkFreight = trunkFreight;
    }

    public BigDecimal getCif() {
        return cif;
    }

    public void setCif(BigDecimal cif) {
        this.cif = cif;
    }

    public BigDecimal getSettkementStandardPrice() {
        return settkementStandardPrice;
    }

    public void setSettkementStandardPrice(BigDecimal settkementStandardPrice) {
        this.settkementStandardPrice = settkementStandardPrice;
    }

    public String getDownPayMent() {
        return downPayMent;
    }

    public void setDownPayMent(String downPayMent) {
        this.downPayMent = downPayMent;
    }

    public Integer getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(Integer paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public BigDecimal getProductValue() {
        return productValue;
    }

    public void setProductValue(BigDecimal productValue) {
        this.productValue = productValue;
    }

    public String getPdremark() {
        return pdremark;
    }

    public void setPdremark(String pdremark) {
        this.pdremark = pdremark;
    }

    public String[] getRelationTypeArr() {
        return relationTypeArr;
    }

    public void setRelationTypeArr(String[] relationTypeArr) {
        this.relationTypeArr = relationTypeArr;
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

    public String getIsDeliveryConfirm() {
        return isDeliveryConfirm;
    }

    public void setIsDeliveryConfirm(String isDeliveryConfirm) {
        this.isDeliveryConfirm = isDeliveryConfirm;
    }

    public String getIsPaymentRequest() {
        return isPaymentRequest;
    }

    public void setIsPaymentRequest(String isPaymentRequest) {
        this.isPaymentRequest = isPaymentRequest;
    }

    public String getDeliveryCodeId() {
        return deliveryCodeId;
    }

    public void setDeliveryCodeId(String deliveryCodeId) {
        this.deliveryCodeId = deliveryCodeId;
    }

    public Integer getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
    }
}
