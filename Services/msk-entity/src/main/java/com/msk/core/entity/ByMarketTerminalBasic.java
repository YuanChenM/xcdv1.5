/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表by_market_terminal_basic对应的ByMarketTerminalBasic</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class ByMarketTerminalBasic extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** ID */
    private Long id;
    /** 批发市场ID */
    private String marketId;
    /** 批发市场编码 */
    private String marketCode;
    /** 批发市场名称 */
    private String marketName;
    /** 批发市场地址 */
    private String marketAddr;
    /** 批发市场等级 */
    private String marketLevel;
    /** 批发市场等级名称 */
    private String marketLevelName;
    /** 占地面积 */
    private java.math.BigDecimal areaCovered;
    /** 占地面积定级 */
    private String areaCoveredLevel;
    /** 是否依托大型冷库 */
    private String isLargeStorage;
    /** 辐射范围类型名称 */
    private String radiationRangeTypeName;
    /** 辐射范围定级 */
    private String radiationRangeLevel;
    /** 面向买家类型名称 */
    private String faceBuyerTypeName;
    /** 面向买家定级 */
    private String faceBuyerLevel;
    /** 批发市场性质 */
    private String marketNature;
    /** 批发市场性质偏重产品 */
    private String marketNatureEmohasis;
    /** 物流区编码 */
    private String lgcsAreaCode;
    /** 物流区名称 */
    private String lgcsAreaName;
    /** 城市(地区)编码 */
    private String cityCode;
    /** 城市(地区)名称 */
    private String cityName;
    /** 最大分类目标买家 */
    private String maxClassBuyerType;
    /** 最大分类目标买家户数 */
    private Integer maxClassBuyerNum;
    /** 最大分类目标买家定级 */
    private String maxClassBuyerLevel;
    /** 非目标买家总数(户) */
    private Integer ntargetBuyer;
    /** 非目标买家年交易额(万元) */
    private java.math.BigDecimal ntargetAnnualTurnover;
    /** 目标买家总数(户) */
    private Integer targetBuyer;
    /** 目标买家总数定级 */
    private String targetBuyerLevel;
    /** 目标买家年交易额(万元) */
    private java.math.BigDecimal targetAnnualTurnover;
    /** 目标买家交易额定级 */
    private String targetAnnualTurnoverLevel;
    /** 是否存在最新商户数据(1:表示存在新的商户数据,0:表示不存在) */
    private String isMerchantNew;
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
    public ByMarketTerminalBasic() {

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
     * <p>批发市场ID</p>
     *
     * @return the 批发市场ID
     */
    public String getMarketId() {
        return marketId;
    }

    /**
     * <p>批发市场ID</p>
     *
     * @param marketId 批发市场ID
     */
    public void setMarketId(String marketId) {
        this.marketId = marketId;
    }

    /**
     * <p>批发市场编码</p>
     *
     * @return the 批发市场编码
     */
    public String getMarketCode() {
        return marketCode;
    }

    /**
     * <p>批发市场编码</p>
     *
     * @param marketCode 批发市场编码
     */
    public void setMarketCode(String marketCode) {
        this.marketCode = marketCode;
    }

    /**
     * <p>批发市场名称</p>
     *
     * @return the 批发市场名称
     */
    public String getMarketName() {
        return marketName;
    }

    /**
     * <p>批发市场名称</p>
     *
     * @param marketName 批发市场名称
     */
    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    /**
     * <p>批发市场地址</p>
     *
     * @return the 批发市场地址
     */
    public String getMarketAddr() {
        return marketAddr;
    }

    /**
     * <p>批发市场地址</p>
     *
     * @param marketAddr 批发市场地址
     */
    public void setMarketAddr(String marketAddr) {
        this.marketAddr = marketAddr;
    }

    /**
     * <p>批发市场等级</p>
     *
     * @return the 批发市场等级
     */
    public String getMarketLevel() {
        return marketLevel;
    }

    /**
     * <p>批发市场等级</p>
     *
     * @param marketLevel 批发市场等级
     */
    public void setMarketLevel(String marketLevel) {
        this.marketLevel = marketLevel;
    }

    /**
     * <p>批发市场等级名称</p>
     *
     * @return the 批发市场等级名称
     */
    public String getMarketLevelName() {
        return marketLevelName;
    }

    /**
     * <p>批发市场等级名称</p>
     *
     * @param marketLevelName 批发市场等级名称
     */
    public void setMarketLevelName(String marketLevelName) {
        this.marketLevelName = marketLevelName;
    }

    /**
     * <p>占地面积</p>
     *
     * @return the 占地面积
     */
    public java.math.BigDecimal getAreaCovered() {
        return areaCovered;
    }

    /**
     * <p>占地面积</p>
     *
     * @param areaCovered 占地面积
     */
    public void setAreaCovered(java.math.BigDecimal areaCovered) {
        this.areaCovered = areaCovered;
    }

    /**
     * <p>占地面积定级</p>
     *
     * @return the 占地面积定级
     */
    public String getAreaCoveredLevel() {
        return areaCoveredLevel;
    }

    /**
     * <p>占地面积定级</p>
     *
     * @param areaCoveredLevel 占地面积定级
     */
    public void setAreaCoveredLevel(String areaCoveredLevel) {
        this.areaCoveredLevel = areaCoveredLevel;
    }

    /**
     * <p>是否依托大型冷库</p>
     *
     * @return the 是否依托大型冷库
     */
    public String getIsLargeStorage() {
        return isLargeStorage;
    }

    /**
     * <p>是否依托大型冷库</p>
     *
     * @param isLargeStorage 是否依托大型冷库
     */
    public void setIsLargeStorage(String isLargeStorage) {
        this.isLargeStorage = isLargeStorage;
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
     * <p>辐射范围定级</p>
     *
     * @return the 辐射范围定级
     */
    public String getRadiationRangeLevel() {
        return radiationRangeLevel;
    }

    /**
     * <p>辐射范围定级</p>
     *
     * @param radiationRangeLevel 辐射范围定级
     */
    public void setRadiationRangeLevel(String radiationRangeLevel) {
        this.radiationRangeLevel = radiationRangeLevel;
    }

    /**
     * <p>面向买家类型名称</p>
     *
     * @return the 面向买家类型名称
     */
    public String getFaceBuyerTypeName() {
        return faceBuyerTypeName;
    }

    /**
     * <p>面向买家类型名称</p>
     *
     * @param faceBuyerTypeName 面向买家类型名称
     */
    public void setFaceBuyerTypeName(String faceBuyerTypeName) {
        this.faceBuyerTypeName = faceBuyerTypeName;
    }

    /**
     * <p>面向买家定级</p>
     *
     * @return the 面向买家定级
     */
    public String getFaceBuyerLevel() {
        return faceBuyerLevel;
    }

    /**
     * <p>面向买家定级</p>
     *
     * @param faceBuyerLevel 面向买家定级
     */
    public void setFaceBuyerLevel(String faceBuyerLevel) {
        this.faceBuyerLevel = faceBuyerLevel;
    }

    /**
     * <p>批发市场性质</p>
     *
     * @return the 批发市场性质
     */
    public String getMarketNature() {
        return marketNature;
    }

    /**
     * <p>批发市场性质</p>
     *
     * @param marketNature 批发市场性质
     */
    public void setMarketNature(String marketNature) {
        this.marketNature = marketNature;
    }

    /**
     * <p>批发市场性质偏重产品</p>
     *
     * @return the 批发市场性质偏重产品
     */
    public String getMarketNatureEmohasis() {
        return marketNatureEmohasis;
    }

    /**
     * <p>批发市场性质偏重产品</p>
     *
     * @param marketNatureEmohasis 批发市场性质偏重产品
     */
    public void setMarketNatureEmohasis(String marketNatureEmohasis) {
        this.marketNatureEmohasis = marketNatureEmohasis;
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
     * <p>最大分类目标买家</p>
     *
     * @return the 最大分类目标买家
     */
    public String getMaxClassBuyerType() {
        return maxClassBuyerType;
    }

    /**
     * <p>最大分类目标买家</p>
     *
     * @param maxClassBuyerType 最大分类目标买家
     */
    public void setMaxClassBuyerType(String maxClassBuyerType) {
        this.maxClassBuyerType = maxClassBuyerType;
    }

    /**
     * <p>最大分类目标买家户数</p>
     *
     * @return the 最大分类目标买家户数
     */
    public Integer getMaxClassBuyerNum() {
        return maxClassBuyerNum;
    }

    /**
     * <p>最大分类目标买家户数</p>
     *
     * @param maxClassBuyerNum 最大分类目标买家户数
     */
    public void setMaxClassBuyerNum(Integer maxClassBuyerNum) {
        this.maxClassBuyerNum = maxClassBuyerNum;
    }

    /**
     * <p>最大分类目标买家定级</p>
     *
     * @return the 最大分类目标买家定级
     */
    public String getMaxClassBuyerLevel() {
        return maxClassBuyerLevel;
    }

    /**
     * <p>最大分类目标买家定级</p>
     *
     * @param maxClassBuyerLevel 最大分类目标买家定级
     */
    public void setMaxClassBuyerLevel(String maxClassBuyerLevel) {
        this.maxClassBuyerLevel = maxClassBuyerLevel;
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
     * <p>目标买家总数定级</p>
     *
     * @return the 目标买家总数定级
     */
    public String getTargetBuyerLevel() {
        return targetBuyerLevel;
    }

    /**
     * <p>目标买家总数定级</p>
     *
     * @param targetBuyerLevel 目标买家总数定级
     */
    public void setTargetBuyerLevel(String targetBuyerLevel) {
        this.targetBuyerLevel = targetBuyerLevel;
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
     * <p>目标买家交易额定级</p>
     *
     * @return the 目标买家交易额定级
     */
    public String getTargetAnnualTurnoverLevel() {
        return targetAnnualTurnoverLevel;
    }

    /**
     * <p>目标买家交易额定级</p>
     *
     * @param targetAnnualTurnoverLevel 目标买家交易额定级
     */
    public void setTargetAnnualTurnoverLevel(String targetAnnualTurnoverLevel) {
        this.targetAnnualTurnoverLevel = targetAnnualTurnoverLevel;
    }

    /**
     * <p>是否存在最新商户数据(1:表示存在新的商户数据,0:表示不存在)</p>
     *
     * @return the 是否存在最新商户数据(1:表示存在新的商户数据,0:表示不存在)
     */
    public String getIsMerchantNew() {
        return isMerchantNew;
    }

    /**
     * <p>是否存在最新商户数据(1:表示存在新的商户数据,0:表示不存在)</p>
     *
     * @param isMerchantNew 是否存在最新商户数据(1:表示存在新的商户数据,0:表示不存在)
     */
    public void setIsMerchantNew(String isMerchantNew) {
        this.isMerchantNew = isMerchantNew;
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
