/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表by_market_food_basic对应的ByMarketFoodBasic</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class ByMarketFoodBasic extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** ID */
    private Long id;
    /** 菜场ID */
    private String marketId;
    /** 菜场编码 */
    private String marketCode;
    /** 菜场所属地段类型 */
    private String sectionType;
    /** 菜场所属地段类型名称 */
    private String sectionTypeName;
    /** 辐射范围类型 */
    private String radiationRange;
    /** 辐射范围类型名称 */
    private String radiationRangeTypeName;
    /** 菜场评估性质 */
    private String marketAssessNature;
    /** 菜场评估性质名称 */
    private String marketAssessNatureName;
    /** 买家平均定价类型 */
    private String byAveragePriceType;
    /** 买家平均定价类型名称 */
    private String byAveragePriceTypeName;
    /** 菜场名称 */
    private String marketName;
    /** 菜场地址 */
    private String marketAddr;
    /** 菜场性质 */
    private String marketNature;
    /** 菜场性质名称 */
    private String marketNatureName;
    /** 物流区编码 */
    private String lgcsAreaCode;
    /** 物流区名称 */
    private String lgcsAreaName;
    /** 城市(地区)编码 */
    private String cityCode;
    /** 城市(地区)名称 */
    private String cityName;
    /** 区县编码 */
    private String districtCode;
    /** 区县名称 */
    private String districtName;
    /** 总商户数(户) */
    private Integer merchantTotalNo;
    /** 非目标买家总数(户) */
    private Integer ntargetBuyer;
    /** 非目标买家年交易额(万元) */
    private java.math.BigDecimal ntargetAnnualTurnover;
    /** 目标买家总数(户) */
    private Integer targetBuyer;
    /** 目标买家年交易额(万元) */
    private java.math.BigDecimal targetAnnualTurnover;
    /** 是否阶段最新数据 */
    private String isPhaseNew;
    /** 是否批发市场最新数据 */
    private String isMarketNew;
    /** 调研阶段 */
    private String researchPhase;
    /** 调研阶段名称 */
    private String researchPhaseName;
    /** 审批状态 */
    private String marketStatus;
    /**
     * <p>默认构造函数</p>
     */
    public ByMarketFoodBasic() {

    }

    /**
     * <p>ID</p>
     *
     * @return the ID
     */
    public Long getId() {
        return id;
    }

    /**
     * <p>ID</p>
     *
     * @param id ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * <p>菜场ID</p>
     *
     * @return the 菜场ID
     */
    public String getMarketId() {
        return marketId;
    }

    /**
     * <p>菜场ID</p>
     *
     * @param marketId 菜场ID
     */
    public void setMarketId(String marketId) {
        this.marketId = marketId;
    }

    /**
     * <p>菜场编码</p>
     *
     * @return the 菜场编码
     */
    public String getMarketCode() {
        return marketCode;
    }

    /**
     * <p>菜场编码</p>
     *
     * @param marketCode 菜场编码
     */
    public void setMarketCode(String marketCode) {
        this.marketCode = marketCode;
    }

    /**
     * <p>菜场所属地段类型</p>
     *
     * @return the 菜场所属地段类型
     */
    public String getSectionType() {
        return sectionType;
    }

    /**
     * <p>菜场所属地段类型</p>
     *
     * @param sectionType 菜场所属地段类型
     */
    public void setSectionType(String sectionType) {
        this.sectionType = sectionType;
    }

    /**
     * <p>菜场所属地段类型名称</p>
     *
     * @return the 菜场所属地段类型名称
     */
    public String getSectionTypeName() {
        return sectionTypeName;
    }

    /**
     * <p>菜场所属地段类型名称</p>
     *
     * @param sectionTypeName 菜场所属地段类型名称
     */
    public void setSectionTypeName(String sectionTypeName) {
        this.sectionTypeName = sectionTypeName;
    }

    /**
     * <p>辐射范围类型</p>
     *
     * @return the 辐射范围类型
     */
    public String getRadiationRange() {
        return radiationRange;
    }

    /**
     * <p>辐射范围类型</p>
     *
     * @param radiationRange 辐射范围类型
     */
    public void setRadiationRange(String radiationRange) {
        this.radiationRange = radiationRange;
    }

    /**
     * <p>辐射范围类型名称</p>
     *
     * @return the 辐射范围类型名称
     */
    public String getRadiationRangeTypeName() {
        return radiationRangeTypeName;
    }

    /**
     * <p>辐射范围类型名称</p>
     *
     * @param radiationRangeTypeName 辐射范围类型名称
     */
    public void setRadiationRangeTypeName(String radiationRangeTypeName) {
        this.radiationRangeTypeName = radiationRangeTypeName;
    }

    /**
     * <p>菜场评估性质</p>
     *
     * @return the 菜场评估性质
     */
    public String getMarketAssessNature() {
        return marketAssessNature;
    }

    /**
     * <p>菜场评估性质</p>
     *
     * @param marketAssessNature 菜场评估性质
     */
    public void setMarketAssessNature(String marketAssessNature) {
        this.marketAssessNature = marketAssessNature;
    }

    /**
     * <p>菜场评估性质名称</p>
     *
     * @return the 菜场评估性质名称
     */
    public String getMarketAssessNatureName() {
        return marketAssessNatureName;
    }

    /**
     * <p>菜场评估性质名称</p>
     *
     * @param marketAssessNatureName 菜场评估性质名称
     */
    public void setMarketAssessNatureName(String marketAssessNatureName) {
        this.marketAssessNatureName = marketAssessNatureName;
    }

    /**
     * <p>买家平均定价类型</p>
     *
     * @return the 买家平均定价类型
     */
    public String getByAveragePriceType() {
        return byAveragePriceType;
    }

    /**
     * <p>买家平均定价类型</p>
     *
     * @param byAveragePriceType 买家平均定价类型
     */
    public void setByAveragePriceType(String byAveragePriceType) {
        this.byAveragePriceType = byAveragePriceType;
    }

    /**
     * <p>买家平均定价类型名称</p>
     *
     * @return the 买家平均定价类型名称
     */
    public String getByAveragePriceTypeName() {
        return byAveragePriceTypeName;
    }

    /**
     * <p>买家平均定价类型名称</p>
     *
     * @param byAveragePriceTypeName 买家平均定价类型名称
     */
    public void setByAveragePriceTypeName(String byAveragePriceTypeName) {
        this.byAveragePriceTypeName = byAveragePriceTypeName;
    }

    /**
     * <p>菜场名称</p>
     *
     * @return the 菜场名称
     */
    public String getMarketName() {
        return marketName;
    }

    /**
     * <p>菜场名称</p>
     *
     * @param marketName 菜场名称
     */
    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    /**
     * <p>菜场地址</p>
     *
     * @return the 菜场地址
     */
    public String getMarketAddr() {
        return marketAddr;
    }

    /**
     * <p>菜场地址</p>
     *
     * @param marketAddr 菜场地址
     */
    public void setMarketAddr(String marketAddr) {
        this.marketAddr = marketAddr;
    }

    /**
     * <p>菜场性质</p>
     *
     * @return the 菜场性质
     */
    public String getMarketNature() {
        return marketNature;
    }

    /**
     * <p>菜场性质</p>
     *
     * @param marketNature 菜场性质
     */
    public void setMarketNature(String marketNature) {
        this.marketNature = marketNature;
    }

    /**
     * <p>菜场性质名称</p>
     *
     * @return the 菜场性质名称
     */
    public String getMarketNatureName() {
        return marketNatureName;
    }

    /**
     * <p>菜场性质名称</p>
     *
     * @param marketNatureName 菜场性质名称
     */
    public void setMarketNatureName(String marketNatureName) {
        this.marketNatureName = marketNatureName;
    }

    /**
     * <p>物流区编码</p>
     *
     * @return the 物流区编码
     */
    public String getLgcsAreaCode() {
        return lgcsAreaCode;
    }

    /**
     * <p>物流区编码</p>
     *
     * @param lgcsAreaCode 物流区编码
     */
    public void setLgcsAreaCode(String lgcsAreaCode) {
        this.lgcsAreaCode = lgcsAreaCode;
    }

    /**
     * <p>物流区名称</p>
     *
     * @return the 物流区名称
     */
    public String getLgcsAreaName() {
        return lgcsAreaName;
    }

    /**
     * <p>物流区名称</p>
     *
     * @param lgcsAreaName 物流区名称
     */
    public void setLgcsAreaName(String lgcsAreaName) {
        this.lgcsAreaName = lgcsAreaName;
    }

    /**
     * <p>城市(地区)编码</p>
     *
     * @return the 城市(地区)编码
     */
    public String getCityCode() {
        return cityCode;
    }

    /**
     * <p>城市(地区)编码</p>
     *
     * @param cityCode 城市(地区)编码
     */
    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    /**
     * <p>城市(地区)名称</p>
     *
     * @return the 城市(地区)名称
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * <p>城市(地区)名称</p>
     *
     * @param cityName 城市(地区)名称
     */
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    /**
     * <p>区县编码</p>
     *
     * @return the 区县编码
     */
    public String getDistrictCode() {
        return districtCode;
    }

    /**
     * <p>区县编码</p>
     *
     * @param districtCode 区县编码
     */
    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    /**
     * <p>区县名称</p>
     *
     * @return the 区县名称
     */
    public String getDistrictName() {
        return districtName;
    }

    /**
     * <p>区县名称</p>
     *
     * @param districtName 区县名称
     */
    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    /**
     * <p>总商户数(户)</p>
     *
     * @return the 总商户数(户)
     */
    public Integer getMerchantTotalNo() {
        return merchantTotalNo;
    }

    /**
     * <p>总商户数(户)</p>
     *
     * @param merchantTotalNo 总商户数(户)
     */
    public void setMerchantTotalNo(Integer merchantTotalNo) {
        this.merchantTotalNo = merchantTotalNo;
    }

    /**
     * <p>非目标买家总数(户)</p>
     *
     * @return the 非目标买家总数(户)
     */
    public Integer getNtargetBuyer() {
        return ntargetBuyer;
    }

    /**
     * <p>非目标买家总数(户)</p>
     *
     * @param ntargetBuyer 非目标买家总数(户)
     */
    public void setNtargetBuyer(Integer ntargetBuyer) {
        this.ntargetBuyer = ntargetBuyer;
    }

    /**
     * <p>非目标买家年交易额(万元)</p>
     *
     * @return the 非目标买家年交易额(万元)
     */
    public java.math.BigDecimal getNtargetAnnualTurnover() {
        return ntargetAnnualTurnover;
    }

    /**
     * <p>非目标买家年交易额(万元)</p>
     *
     * @param ntargetAnnualTurnover 非目标买家年交易额(万元)
     */
    public void setNtargetAnnualTurnover(java.math.BigDecimal ntargetAnnualTurnover) {
        this.ntargetAnnualTurnover = ntargetAnnualTurnover;
    }

    /**
     * <p>目标买家总数(户)</p>
     *
     * @return the 目标买家总数(户)
     */
    public Integer getTargetBuyer() {
        return targetBuyer;
    }

    /**
     * <p>目标买家总数(户)</p>
     *
     * @param targetBuyer 目标买家总数(户)
     */
    public void setTargetBuyer(Integer targetBuyer) {
        this.targetBuyer = targetBuyer;
    }

    /**
     * <p>目标买家年交易额(万元)</p>
     *
     * @return the 目标买家年交易额(万元)
     */
    public java.math.BigDecimal getTargetAnnualTurnover() {
        return targetAnnualTurnover;
    }

    /**
     * <p>目标买家年交易额(万元)</p>
     *
     * @param targetAnnualTurnover 目标买家年交易额(万元)
     */
    public void setTargetAnnualTurnover(java.math.BigDecimal targetAnnualTurnover) {
        this.targetAnnualTurnover = targetAnnualTurnover;
    }

    /**
     * <p>是否阶段最新数据</p>
     *
     * @return the 是否阶段最新数据
     */
    public String getIsPhaseNew() {
        return isPhaseNew;
    }

    /**
     * <p>是否阶段最新数据</p>
     *
     * @param isPhaseNew 是否阶段最新数据
     */
    public void setIsPhaseNew(String isPhaseNew) {
        this.isPhaseNew = isPhaseNew;
    }

    /**
     * <p>是否批发市场最新数据</p>
     *
     * @return the 是否批发市场最新数据
     */
    public String getIsMarketNew() {
        return isMarketNew;
    }

    /**
     * <p>是否批发市场最新数据</p>
     *
     * @param isMarketNew 是否批发市场最新数据
     */
    public void setIsMarketNew(String isMarketNew) {
        this.isMarketNew = isMarketNew;
    }

    /**
     * <p>调研阶段</p>
     *
     * @return the 调研阶段
     */
    public String getResearchPhase() {
        return researchPhase;
    }

    /**
     * <p>调研阶段</p>
     *
     * @param researchPhase 调研阶段
     */
    public void setResearchPhase(String researchPhase) {
        this.researchPhase = researchPhase;
    }

    /**
     * <p>调研阶段名称</p>
     *
     * @return the 调研阶段名称
     */
    public String getResearchPhaseName() {
        return researchPhaseName;
    }

    /**
     * <p>调研阶段名称</p>
     *
     * @param researchPhaseName 调研阶段名称
     */
    public void setResearchPhaseName(String researchPhaseName) {
        this.researchPhaseName = researchPhaseName;
    }

    /**
     * <p>审批状态</p>
     *
     * @return the 审批状态
     */
    public String getMarketStatus() {
        return marketStatus;
    }

    /**
     * <p>审批状态</p>
     *
     * @param marketStatus 审批状态
     */
    public void setMarketStatus(String marketStatus) {
        this.marketStatus = marketStatus;
    }

}
