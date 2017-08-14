package com.msk.ds.bean;

import com.msk.common.base.BaseBean;

import java.math.BigDecimal;
import java.util.List;

/**
 * zhou_yajun on 2016/1/28.
 */
public class ActualInput extends BaseBean {
    /** serialVersionUID */
    private static final long serialVersionUID = 1L;
    /**产品名称 */
    private String productName;
    /**产品编码 */
    private String productCode;
    /**包装编码 */
    private String normsCode;
    /**产品类别名称 */
    private String className;
    /**产品类别编码 */
    private String classCode;
    /**产品品种名称*/
    private String breedName;
    /**产品品种编码*/
    private String breedCode;
    /**产品特征名称*/
    private String featureName;
    /**产品特征编码*/
    private String featureCode;
    /**产品等级名称*/
    private String gradeName;
    /**产品等级编码*/
    private String gradeCode;
    /**地区名称 */
    private String areaName;
    /**地区编码 */
    private String areaCode;
    /**供应商编码 */
    private String supplierCode;
    /**供应商名称*/
    private String supplierName;
    /**生产类型*/
    private String planType;
    /**计划类型名称*/
    private String planTypeName;
    /**计划类型List*/
    private List<ActualInput> planList;
    /**期*/
    private String distMonth;
    /**半旬编码*/
    private String halfCode;
    /**半旬*/
    private String halfName;
    /**调整时间*/
    private String adjustDate;
    /**调整时间*/
    private String adjustDateOld;
    /**调整前值*/
    private BigDecimal changeBeforeNum;
    /**调整前值合计*/
    private BigDecimal sumChangeBeforeNum;
    /**调整值*/
    private BigDecimal changeNum;
    /**调整后值 */
    private BigDecimal changeOverNum;
    /**调整产品List*/
    private List<ActualInput> planChangeProductList;
    /**入口标志1:供货商月度管控画面，2:供应商产品计划调整一览画面*/
    private String entryMark;

    /**供应分销流水ID */
    private Long suppDsId;
    /**虚拟库存计划流水ID */
    private Long virtualStockActualId;

    //Add for 3487 at 2016/10/27 by likai Start
    private BigDecimal pdOutNw;
    //Add for 3487 at 2016/10/27 by likai End

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

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

    public String getNormsCode() {
        return normsCode;
    }

    public void setNormsCode(String normsCode) {
        this.normsCode = normsCode;
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

    public String getHalfName() {
        return halfName;
    }

    public void setHalfName(String halfName) {
        this.halfName = halfName;
    }

    public String getPlanType() {
        return planType;
    }

    public void setPlanType(String planType) {
        this.planType = planType;
    }

    public BigDecimal getChangeBeforeNum() {
        return changeBeforeNum;
    }

    public void setChangeBeforeNum(BigDecimal changeBeforeNum) {
        this.changeBeforeNum = changeBeforeNum;
    }

    public BigDecimal getChangeNum() {
        return changeNum;
    }

    public void setChangeNum(BigDecimal changeNum) {
        this.changeNum = changeNum;
    }

    public BigDecimal getChangeOverNum() {
        return changeOverNum;
    }

    public void setChangeOverNum(BigDecimal changeOverNum) {
        this.changeOverNum = changeOverNum;
    }

    public List<ActualInput> getPlanChangeProductList() {
        return planChangeProductList;
    }

    public void setPlanChangeProductList(List<ActualInput> planChangeProductList) {
        this.planChangeProductList = planChangeProductList;
    }
    public String getAdjustDate() {
        return adjustDate;
    }
    public void setAdjustDate(String adjustDate) {
        this.adjustDate = adjustDate;
    }

    public String getAdjustDateOld() {
        return adjustDateOld;
    }

    public void setAdjustDateOld(String adjustDateOld) {
        this.adjustDateOld = adjustDateOld;
    }

    public List<ActualInput> getPlanList() {
        return planList;
    }

    public void setPlanList(List<ActualInput> planList) {
        this.planList = planList;
    }

    public String getPlanTypeName() {
        return planTypeName;
    }

    public void setPlanTypeName(String planTypeName) {
        this.planTypeName = planTypeName;
    }

    public String getEntryMark() {
        return entryMark;
    }

    public void setEntryMark(String entryMark) {
        this.entryMark = entryMark;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public String getBreedName() {
        return breedName;
    }

    public void setBreedName(String breedName) {
        this.breedName = breedName;
    }

    public String getBreedCode() {
        return breedCode;
    }

    public void setBreedCode(String breedCode) {
        this.breedCode = breedCode;
    }

    public String getFeatureName() {
        return featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    public String getFeatureCode() {
        return featureCode;
    }

    public void setFeatureCode(String featureCode) {
        this.featureCode = featureCode;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public String getGradeCode() {
        return gradeCode;
    }

    public void setGradeCode(String gradeCode) {
        this.gradeCode = gradeCode;
    }

    public BigDecimal getSumChangeBeforeNum() {
        return sumChangeBeforeNum;
    }

    public void setSumChangeBeforeNum(BigDecimal sumChangeBeforeNum) {
        this.sumChangeBeforeNum = sumChangeBeforeNum;
    }

    public Long getSuppDsId() {
        return suppDsId;
    }

    public void setSuppDsId(Long suppDsId) {
        this.suppDsId = suppDsId;
    }

    public Long getVirtualStockActualId() {
        return virtualStockActualId;
    }

    public void setVirtualStockActualId(Long virtualStockActualId) {
        this.virtualStockActualId = virtualStockActualId;
    }

    public BigDecimal getPdOutNw() {
        return pdOutNw;
    }

    public void setPdOutNw(BigDecimal pdOutNw) {
        this.pdOutNw = pdOutNw;
    }
}
