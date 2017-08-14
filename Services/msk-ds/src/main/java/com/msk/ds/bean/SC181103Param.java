package com.msk.ds.bean;

import com.hoperun.core.bean.BasePageParam;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 月度管控（临时）param
 * @author li_kai1 on 2016/6/15
 */
public class SC181103Param extends BasePageParam {
    /** serialVersionUID */
    private static final long serialVersionUID = 1L;
    /**供应分销流水id*/
    private Long suppDsId;
    /**虚拟库存实际流水Id*/
    private Long stockActualId;
    /**虚拟库存计划流水Id*/
    private Long stockPlanId;
    /**物流区编码*/
    private String lgcsCode;
    /**物流区名称*/
    private String lgcsName;
    /**供应商编码 */
    private String suppCode;
    /**供应商名称 */
    private String suppName;
    /**分销月度*/
    private String distMonth;
    /**产品编码 */
    private String pdCode;
    /**产品名称 */
    private String pdName;
    /**品牌类型 */
    private Integer brandType;
    /**品牌类型名称 */
    private String brandTypeName;
    /**分销通道*/
    private Integer distType;
    /**分销数量*/
    private BigDecimal distNum;

    /**产品库存类型*/
    private String pdStockType;
    /**半旬编码*/
    private String halfCode;
    /**半旬名称*/
    private String halfName;
    /**产品类别编码*/
    private String classesCode;
    /**产品类别名称*/
    private String classesName;
    /**产品加工程度编码*/
    private String machiningCode;
    /**产品加工程度名称*/
    private String machiningName;
    /**产品品种编码 */
    private String breedCode;
    /**产品品种名称 */
    private String breedName;
    /**产品特征编码*/
    private String featureCode;
    /**产品特征名称 */
    private String featureName;
    /**产品净重编码*/
    private String weightCode;
    /**产品净重名称*/
    private String weightName;
    /**产品等级编码 */
    private String gradeCode;
    /**产品等级名称 */
    private String gradeName;
    /**产品外包装规格名称 */
    private String outSpec;
    /**外包装净重 */
    private BigDecimal outNetWeight;
    /**包装编码 */
    private String normsCode;
    /**包装名称 */
    private String normsName;
    /**发货箱数 */
    private String packNum;
    /**原实际数量*/
    private BigDecimal oldActualNum;
    /**新实际数量*/
    private BigDecimal newActualNum;
    /**原始计划数量*/
    private BigDecimal origPlanNum;
    /**原计划数量*/
    private BigDecimal oldPlanNum;
    /**新计划数量*/
    private BigDecimal newPlanNum;
    /**计划调整日期*/
    private Date adJustDate;
    /**实际录入日期*/
    private Date inputDate;

    /**产品标准质量定级*/
    private Integer slQltGradeCode;
    /**slMainClass
     * 0.生产商1.自产型,2:代理型,3:OEM型
     */
    private Integer slMainClass;


    public Long getSuppDsId() {
        return suppDsId;
    }

    public void setSuppDsId(Long suppDsId) {
        this.suppDsId = suppDsId;
    }

    public Long getStockActualId() {
        return stockActualId;
    }

    public void setStockActualId(Long stockActualId) {
        this.stockActualId = stockActualId;
    }

    public Long getStockPlanId() {
        return stockPlanId;
    }

    public void setStockPlanId(Long stockPlanId) {
        this.stockPlanId = stockPlanId;
    }

    public String getLgcsCode() {
        return lgcsCode;
    }

    public void setLgcsCode(String lgcsCode) {
        this.lgcsCode = lgcsCode;
    }

    public String getLgcsName() {
        return lgcsName;
    }

    public void setLgcsName(String lgcsName) {
        this.lgcsName = lgcsName;
    }

    public String getSuppCode() {
        return suppCode;
    }

    public void setSuppCode(String suppCode) {
        this.suppCode = suppCode;
    }

    public String getSuppName() {
        return suppName;
    }

    public void setSuppName(String suppName) {
        this.suppName = suppName;
    }

    public String getDistMonth() {
        return distMonth;
    }

    public void setDistMonth(String distMonth) {
        this.distMonth = distMonth;
    }

    public String getPdCode() {
        return pdCode;
    }

    public void setPdCode(String pdCode) {
        this.pdCode = pdCode;
    }

    public String getPdName() {
        return pdName;
    }

    public void setPdName(String pdName) {
        this.pdName = pdName;
    }

    public Integer getBrandType() {
        return brandType;
    }

    public void setBrandType(Integer brandType) {
        this.brandType = brandType;
    }

    public String getBrandTypeName() {
        return brandTypeName;
    }

    public void setBrandTypeName(String brandTypeName) {
        this.brandTypeName = brandTypeName;
    }

    public Integer getDistType() {
        return distType;
    }

    public void setDistType(Integer distType) {
        this.distType = distType;
    }

    public BigDecimal getDistNum() {
        return distNum;
    }

    public void setDistNum(BigDecimal distNum) {
        this.distNum = distNum;
    }

    public String getPdStockType() {
        return pdStockType;
    }

    public void setPdStockType(String pdStockType) {
        this.pdStockType = pdStockType;
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

    public String getFeatureCode() {
        return featureCode;
    }

    public void setFeatureCode(String featureCode) {
        this.featureCode = featureCode;
    }

    public String getFeatureName() {
        return featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
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

    public String getOutSpec() {
        return outSpec;
    }

    public void setOutSpec(String outSpec) {
        this.outSpec = outSpec;
    }

    public BigDecimal getOutNetWeight() {
        return outNetWeight;
    }

    public void setOutNetWeight(BigDecimal outNetWeight) {
        this.outNetWeight = outNetWeight;
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

    public String getPackNum() {
        return packNum;
    }

    public void setPackNum(String packNum) {
        this.packNum = packNum;
    }

    public BigDecimal getOldActualNum() {
        return oldActualNum;
    }

    public void setOldActualNum(BigDecimal oldActualNum) {
        this.oldActualNum = oldActualNum;
    }

    public BigDecimal getNewActualNum() {
        return newActualNum;
    }

    public void setNewActualNum(BigDecimal newActualNum) {
        this.newActualNum = newActualNum;
    }

    public BigDecimal getOrigPlanNum() {
        return origPlanNum;
    }

    public void setOrigPlanNum(BigDecimal origPlanNum) {
        this.origPlanNum = origPlanNum;
    }

    public BigDecimal getOldPlanNum() {
        return oldPlanNum;
    }

    public void setOldPlanNum(BigDecimal oldPlanNum) {
        this.oldPlanNum = oldPlanNum;
    }

    public BigDecimal getNewPlanNum() {
        return newPlanNum;
    }

    public void setNewPlanNum(BigDecimal newPlanNum) {
        this.newPlanNum = newPlanNum;
    }

    public Date getAdJustDate() {
        return adJustDate;
    }

    public void setAdJustDate(Date adJustDate) {
        this.adJustDate = adJustDate;
    }

    public Date getInputDate() {
        return inputDate;
    }

    public void setInputDate(Date inputDate) {
        this.inputDate = inputDate;
    }

    public Integer getSlQltGradeCode() {
        return slQltGradeCode;
    }

    public void setSlQltGradeCode(Integer slQltGradeCode) {
        this.slQltGradeCode = slQltGradeCode;
    }

    public Integer getSlMainClass() {
        return slMainClass;
    }

    public void setSlMainClass(Integer slMainClass) {
        this.slMainClass = slMainClass;
    }
}
