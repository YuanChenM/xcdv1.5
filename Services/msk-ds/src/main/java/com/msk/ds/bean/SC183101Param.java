package com.msk.ds.bean;

import com.hoperun.core.bean.BaseParam;
import java.math.BigDecimal;
import java.util.List;

/**
 * zhou_yajun on 2016/1/28.
 */
public class SC183101Param extends BaseParam {
    /** serialVersionUID */
    private static final long serialVersionUID = 1L;
    /**产品名称*/
    private String productName;
    /**点击的半旬*/
    private String currentHalfCode;
    /**计划类型*/
    private String planType;
    /**调整时间*/
    private String adjustDate;
    /**入口标志1:供货商月度管控画面，2:供应商产品计划调整一览画面*/
    private String entryMark;
    /**地区名称 */
    private String areaName;
    /**地区编码 */
    private String areaCode;
    /**供应商编码 */
    private String supplierCode;
    /**供应商名称*/
    private String supplierName;
    /**期*/
    private String distMonth;
    /**当前日期的半旬编码*/
    private String halfCode;
    /**按钮名称*/
    private String buttonName;
    /**产品编码 */
    private String productCode;
    /**包装编码 */
    private String normsCode;
    /**调整前值*/
    private BigDecimal changeBeforeNum;
    /**调整后值 */
    private BigDecimal changeOverNum;
    /**供应分销流水ID */
    private Long suppDsId;
    /**虚拟库存计划流水ID */
    private Long virtualStockPlanId;
    /**虚拟库存商品列表*/
    private List<SC183101ProductParam> productParamList;

    public Long getSuppDsId() {
        return suppDsId;
    }

    public void setSuppDsId(Long suppDsId) {
        this.suppDsId = suppDsId;
    }

    public List<SC183101ProductParam> getProductParamList() {
        return productParamList;
    }

    public void setProductParamList(List<SC183101ProductParam> productParamList) {
        this.productParamList = productParamList;
    }
    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
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

    public String getDistMonth() {
        return distMonth;
    }

    public void setDistMonth(String distMonth) {
        this.distMonth = distMonth;
    }

    public String getHalfCode() {
        return halfCode;
    }

    public void setHalfCode(String halfCode) {
        this.halfCode = halfCode;
    }

    public String getButtonName() {
        return buttonName;
    }

    public void setButtonName(String buttonName) {
        this.buttonName = buttonName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCurrentHalfCode() {
        return currentHalfCode;
    }

    public void setCurrentHalfCode(String currentHalfCode) {
        this.currentHalfCode = currentHalfCode;
    }

    public String getPlanType() {
        return planType;
    }

    public void setPlanType(String planType) {
        this.planType = planType;
    }

    public String getAdjustDate() {
        return adjustDate;
    }

    public void setAdjustDate(String adjustDate) {
        this.adjustDate = adjustDate;
    }

    public String getEntryMark() {
        return entryMark;
    }

    public void setEntryMark(String entryMark) {
        this.entryMark = entryMark;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getNormsCode() {
        return normsCode;
    }

    public void setNormsCode(String normsCode) {
        this.normsCode = normsCode;
    }

    public BigDecimal getChangeBeforeNum() {
        return changeBeforeNum;
    }

    public void setChangeBeforeNum(BigDecimal changeBeforeNum) {
        this.changeBeforeNum = changeBeforeNum;
    }

    public BigDecimal getChangeOverNum() {
        return changeOverNum;
    }

    public void setChangeOverNum(BigDecimal changeOverNum) {
        this.changeOverNum = changeOverNum;
    }

    public Long getVirtualStockPlanId() {
        return virtualStockPlanId;
    }

    public void setVirtualStockPlanId(Long virtualStockPlanId) {
        this.virtualStockPlanId = virtualStockPlanId;
    }
}
