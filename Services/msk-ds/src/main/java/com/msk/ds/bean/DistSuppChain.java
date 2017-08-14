package com.msk.ds.bean;

import com.msk.common.base.BaseBean;
import java.math.BigDecimal;
import java.util.List;

public class DistSuppChain extends BaseBean {
    /** serialVersionUID */
    private static final long serialVersionUID = 1L;
    /**产品名称 */
    private String productName;
    /**产品编码 */
    private String productCode;
    /**地区名称 */
    private String areaName;
    /**地区编码 */
    private String areaCode;
    /**地区信息List*/
    private List<DistSuppChain> areaInfoList;
    /**供应商编码 */
    private String supplierCode;
    /**供应商名称*/
    private String supplierName;
    /**供应商信息List*/
    private List<DistSuppChain> supplierInfoList;
    /**期*/
    private String distMonth;
    /**期信息List*/
    private List<DistSuppChain> distMonthList;
    /**选中期的前一个月*/
    private String dataSelectBefore;
    /**选中期的月*/
    private String dataSelect;
    /**半旬编码*/
    private String halfCode;
    /**当前日期半旬编码*/
    private int currentHalfCode;
    /**半旬名称*/
    private String halfName;
    /**半旬名称List*/
    private List<DistSuppChain> halfNameList;
    /**计划类型*/
    private String planType;
    /**原始计划数量*/
    private BigDecimal origPlanNum;
    /**新计划数量*/
    private BigDecimal newPlanNum;
    /**新实际数量*/
    private BigDecimal newActualNum;
    /**原始计划合计数量*/
    private BigDecimal sumOrigPlanNum;
    /**计划合计数量*/
    private BigDecimal sumPlanNum;
    /**实际合计数量*/
    private BigDecimal sumActualNum;
    /**原始计划合计数量合计*/
    private BigDecimal sumSumOrigPlanNum;
    /**计划合计数量合计*/
    private BigDecimal sumSumPlanNum;
    /**实际合计数量合计*/
    private BigDecimal sumSumActualNum;
    /**产品信息List*/
    private List<DistSuppChain> productList;
    /**产品数量信息List*/
    private List<DistSuppChain> productNumList;
    /**产品数量合计信息List*/
    private List<DistSuppChain> productSumNumList;
    /**产品数量合计信息合计List*/
    private List<DistSuppChain> productSumSumNumList;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
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

    public List<DistSuppChain> getAreaInfoList() {
        return areaInfoList;
    }

    public void setAreaInfoList(List<DistSuppChain> areaInfoList) {
        this.areaInfoList = areaInfoList;
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

    public List<DistSuppChain> getSupplierInfoList() {
        return supplierInfoList;
    }

    public void setSupplierInfoList(List<DistSuppChain> supplierInfoList) {
        this.supplierInfoList = supplierInfoList;
    }

    public String getDistMonth() {
        return distMonth;
    }

    public void setDistMonth(String distMonth) {
        this.distMonth = distMonth;
    }

    public List<DistSuppChain> getDistMonthList() {
        return distMonthList;
    }

    public void setDistMonthList(List<DistSuppChain> distMonthList) {
        this.distMonthList = distMonthList;
    }

    public String getDataSelectBefore() {
        return dataSelectBefore;
    }

    public void setDataSelectBefore(String dataSelectBefore) {
        this.dataSelectBefore = dataSelectBefore;
    }

    public String getDataSelect() {
        return dataSelect;
    }

    public void setDataSelect(String dataSelect) {
        this.dataSelect = dataSelect;
    }

    public String getHalfCode() {
        return halfCode;
    }

    public void setHalfCode(String halfCode) {
        this.halfCode = halfCode;
    }

    public int getCurrentHalfCode() {
        return currentHalfCode;
    }

    public void setCurrentHalfCode(int currentHalfCode) {
        this.currentHalfCode = currentHalfCode;
    }

    public String getHalfName() {
        return halfName;
    }

    public void setHalfName(String halfName) {
        this.halfName = halfName;
    }

    public List<DistSuppChain> getHalfNameList() {
        return halfNameList;
    }

    public void setHalfNameList(List<DistSuppChain> halfNameList) {
        this.halfNameList = halfNameList;
    }

    public String getPlanType() {
        return planType;
    }

    public void setPlanType(String planType) {
        this.planType = planType;
    }

    public BigDecimal getNewPlanNum() {
        return newPlanNum;
    }

    public void setNewPlanNum(BigDecimal newPlanNum) {
        if(newPlanNum == null){
            this.newPlanNum = BigDecimal.ZERO;
        }else {
            this.newPlanNum = newPlanNum.setScale(2);
        }
    }

    public BigDecimal getNewActualNum() {
        return newActualNum;
    }

    public void setNewActualNum(BigDecimal newActualNum) {
        if(newActualNum == null){
            this.newActualNum = BigDecimal.ZERO;
        }else {
            this.newActualNum = newActualNum.setScale(2);
        }
    }

    public BigDecimal getSumPlanNum() {
        return sumPlanNum;
    }

    public void setSumPlanNum(BigDecimal sumPlanNum) {
        if(sumPlanNum == null){
            this.sumPlanNum = BigDecimal.ZERO;
        }else {
            this.sumPlanNum = sumPlanNum.setScale(2);
        }
    }

    public BigDecimal getSumActualNum() {
        return sumActualNum;
    }

    public void setSumActualNum(BigDecimal sumActualNum) {
        this.sumActualNum = sumActualNum;
    }

    public BigDecimal getSumSumOrigPlanNum() {
        return sumSumOrigPlanNum;
    }

    public void setSumSumOrigPlanNum(BigDecimal sumSumOrigPlanNum) {
        this.sumSumOrigPlanNum = sumSumOrigPlanNum;
    }

    public BigDecimal getSumSumPlanNum() {
        return sumSumPlanNum;
    }

    public void setSumSumPlanNum(BigDecimal sumSumPlanNum) {
        this.sumSumPlanNum = sumSumPlanNum;
    }

    public BigDecimal getSumSumActualNum() {
        return sumSumActualNum;
    }

    public void setSumSumActualNum(BigDecimal sumSumActualNum) {
        this.sumSumActualNum = sumSumActualNum;
    }

    public List<DistSuppChain> getProductList() {
        return productList;
    }

    public void setProductList(List<DistSuppChain> productList) {
        this.productList = productList;
    }

    public List<DistSuppChain> getProductNumList() {
        return productNumList;
    }

    public void setProductNumList(List<DistSuppChain> productNumList) {
        this.productNumList = productNumList;
    }

    public List<DistSuppChain> getProductSumNumList() {
        return productSumNumList;
    }

    public void setProductSumNumList(List<DistSuppChain> productSumNumList) {
        this.productSumNumList = productSumNumList;
    }

    public List<DistSuppChain> getProductSumSumNumList() {
        return productSumSumNumList;
    }

    public void setProductSumSumNumList(List<DistSuppChain> productSumSumNumList) {
        this.productSumSumNumList = productSumSumNumList;
    }

    public BigDecimal getOrigPlanNum() {
        return origPlanNum;
    }

    public void setOrigPlanNum(BigDecimal origPlanNum) {
        if(origPlanNum == null){
            this.origPlanNum = BigDecimal.ZERO;
        } else {
            this.origPlanNum = origPlanNum.setScale(2);
        }
    }

    public BigDecimal getSumOrigPlanNum() {
        return sumOrigPlanNum;
    }

    public void setSumOrigPlanNum(BigDecimal sumOrigPlanNum) {
        if(sumOrigPlanNum == null){
            this.sumOrigPlanNum = BigDecimal.ZERO;
        } else {
            this.sumOrigPlanNum = sumOrigPlanNum.setScale(2);
        }
    }
}
