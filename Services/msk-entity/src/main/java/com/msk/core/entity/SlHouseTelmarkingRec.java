/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表sl_house_telmarking_rec对应的SlHouseTelmarkingRec。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class SlHouseTelmarkingRec extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 营销ID */
    private Long telId;
    /** 区划(6)+顺序码(4) */
    private String slCode;
    /** 用于登录的卖家账号 */
    private String houseCode;
    /** 买家ID */
    private Long byerId;
    /** 沟通日期 */
    private java.util.Date telDate;
    /** 沟通时长 */
    private String telCostTime;
    /** 营销沟通内容 */
    private String marketContent;
    /** 客户需求 */
    private String pdFeatureCode;
    /** 问题沟通 */
    private String problemContent;
    /** 基本信息修正 */
    private String basicInfoCorrect;
    /** 上线概率评分 */
    private String onlineRec;
    /**
     * <p>默认构造函数。</p>
     */
    public SlHouseTelmarkingRec() {

    }

    /**
     * <p>营销ID。</p>
     *
     * @return the 营销ID
     */
    public Long getTelId() {
        return telId;
    }

    /**
     * <p>营销ID。</p>
     *
     * @param telId 营销ID。
     */
    public void setTelId(Long telId) {
        this.telId = telId;
    }

    /**
     * <p>区划(6)+顺序码(4)。</p>
     *
     * @return the 区划(6)+顺序码(4)
     */
    public String getSlCode() {
        return slCode;
    }

    /**
     * <p>区划(6)+顺序码(4)。</p>
     *
     * @param slCode 区划(6)+顺序码(4)。
     */
    public void setSlCode(String slCode) {
        this.slCode = slCode;
    }

    /**
     * <p>用于登录的卖家账号。</p>
     *
     * @return the 用于登录的卖家账号
     */
    public String getHouseCode() {
        return houseCode;
    }

    /**
     * <p>用于登录的卖家账号。</p>
     *
     * @param houseCode 用于登录的卖家账号。
     */
    public void setHouseCode(String houseCode) {
        this.houseCode = houseCode;
    }

    /**
     * <p>买家ID。</p>
     *
     * @return the 买家ID
     */
    public Long getByerId() {
        return byerId;
    }

    /**
     * <p>买家ID。</p>
     *
     * @param byerId 买家ID。
     */
    public void setByerId(Long byerId) {
        this.byerId = byerId;
    }

    /**
     * <p>沟通日期。</p>
     *
     * @return the 沟通日期
     */
    public java.util.Date getTelDate() {
        return telDate;
    }

    /**
     * <p>沟通日期。</p>
     *
     * @param telDate 沟通日期。
     */
    public void setTelDate(java.util.Date telDate) {
        this.telDate = telDate;
    }

    /**
     * <p>沟通时长。</p>
     *
     * @return the 沟通时长
     */
    public String getTelCostTime() {
        return telCostTime;
    }

    /**
     * <p>沟通时长。</p>
     *
     * @param telCostTime 沟通时长。
     */
    public void setTelCostTime(String telCostTime) {
        this.telCostTime = telCostTime;
    }

    /**
     * <p>营销沟通内容。</p>
     *
     * @return the 营销沟通内容
     */
    public String getMarketContent() {
        return marketContent;
    }

    /**
     * <p>营销沟通内容。</p>
     *
     * @param marketContent 营销沟通内容。
     */
    public void setMarketContent(String marketContent) {
        this.marketContent = marketContent;
    }

    /**
     * <p>客户需求。</p>
     *
     * @return the 客户需求
     */
    public String getPdFeatureCode() {
        return pdFeatureCode;
    }

    /**
     * <p>客户需求。</p>
     *
     * @param pdFeatureCode 客户需求。
     */
    public void setPdFeatureCode(String pdFeatureCode) {
        this.pdFeatureCode = pdFeatureCode;
    }

    /**
     * <p>问题沟通。</p>
     *
     * @return the 问题沟通
     */
    public String getProblemContent() {
        return problemContent;
    }

    /**
     * <p>问题沟通。</p>
     *
     * @param problemContent 问题沟通。
     */
    public void setProblemContent(String problemContent) {
        this.problemContent = problemContent;
    }

    /**
     * <p>基本信息修正。</p>
     *
     * @return the 基本信息修正
     */
    public String getBasicInfoCorrect() {
        return basicInfoCorrect;
    }

    /**
     * <p>基本信息修正。</p>
     *
     * @param basicInfoCorrect 基本信息修正。
     */
    public void setBasicInfoCorrect(String basicInfoCorrect) {
        this.basicInfoCorrect = basicInfoCorrect;
    }

    /**
     * <p>上线概率评分。</p>
     *
     * @return the 上线概率评分
     */
    public String getOnlineRec() {
        return onlineRec;
    }

    /**
     * <p>上线概率评分。</p>
     *
     * @param onlineRec 上线概率评分。
     */
    public void setOnlineRec(String onlineRec) {
        this.onlineRec = onlineRec;
    }

}
