/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表pd_tc_market对应的PdTcMarket。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class PdTcMarket extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 总控目录ID */
    private Long tcOnlineId;
    /** 一级分类编码 */
    private String classesCode;
    /** 二级分类编码 */
    private String machiningCode;
    /** 产品品种编码 */
    private String breedCode;
    /** 类别编码 */
    private String levelCode;
    /** 类别名称 */
    private String levelName;
    /** 产品特征编码 */
    private String featureCode;
    /** 产品特征名称 */
    private String featureName;
    /** 俗名 */
    private String localName;
    /** 0:在线处理；1：已注册 */
    private Integer featureFlg;
    /** 分类层级 */
    private Integer treeLevel;
    /**
     * <p>默认构造函数。</p>
     */
    public PdTcMarket() {

    }

    /**
     * <p>总控目录ID。</p>
     *
     * @return the 总控目录ID
     */
    public Long getTcOnlineId() {
        return tcOnlineId;
    }

    /**
     * <p>总控目录ID。</p>
     *
     * @param tcOnlineId 总控目录ID。
     */
    public void setTcOnlineId(Long tcOnlineId) {
        this.tcOnlineId = tcOnlineId;
    }

    /**
     * <p>一级分类编码。</p>
     *
     * @return the 一级分类编码
     */
    public String getClassesCode() {
        return classesCode;
    }

    /**
     * <p>一级分类编码。</p>
     *
     * @param classesCode 一级分类编码。
     */
    public void setClassesCode(String classesCode) {
        this.classesCode = classesCode;
    }

    /**
     * <p>二级分类编码。</p>
     *
     * @return the 二级分类编码
     */
    public String getMachiningCode() {
        return machiningCode;
    }

    /**
     * <p>二级分类编码。</p>
     *
     * @param machiningCode 二级分类编码。
     */
    public void setMachiningCode(String machiningCode) {
        this.machiningCode = machiningCode;
    }

    /**
     * <p>产品品种编码。</p>
     *
     * @return the 产品品种编码
     */
    public String getBreedCode() {
        return breedCode;
    }

    /**
     * <p>产品品种编码。</p>
     *
     * @param breedCode 产品品种编码。
     */
    public void setBreedCode(String breedCode) {
        this.breedCode = breedCode;
    }

    /**
     * <p>类别编码。</p>
     *
     * @return the 类别编码
     */
    public String getLevelCode() {
        return levelCode;
    }

    /**
     * <p>类别编码。</p>
     *
     * @param levelCode 类别编码。
     */
    public void setLevelCode(String levelCode) {
        this.levelCode = levelCode;
    }

    /**
     * <p>类别名称。</p>
     *
     * @return the 类别名称
     */
    public String getLevelName() {
        return levelName;
    }

    /**
     * <p>类别名称。</p>
     *
     * @param levelName 类别名称。
     */
    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    /**
     * <p>产品特征编码。</p>
     *
     * @return the 产品特征编码
     */
    public String getFeatureCode() {
        return featureCode;
    }

    /**
     * <p>产品特征编码。</p>
     *
     * @param featureCode 产品特征编码。
     */
    public void setFeatureCode(String featureCode) {
        this.featureCode = featureCode;
    }

    /**
     * <p>产品特征名称。</p>
     *
     * @return the 产品特征名称
     */
    public String getFeatureName() {
        return featureName;
    }

    /**
     * <p>产品特征名称。</p>
     *
     * @param featureName 产品特征名称。
     */
    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    /**
     * <p>俗名。</p>
     *
     * @return the 俗名
     */
    public String getLocalName() {
        return localName;
    }

    /**
     * <p>俗名。</p>
     *
     * @param localName 俗名。
     */
    public void setLocalName(String localName) {
        this.localName = localName;
    }

    /**
     * <p>0:在线处理；1：已注册。</p>
     *
     * @return the 0:在线处理；1：已注册
     */
    public Integer getFeatureFlg() {
        return featureFlg;
    }

    /**
     * <p>0:在线处理；1：已注册。</p>
     *
     * @param featureFlg 0:在线处理；1：已注册。
     */
    public void setFeatureFlg(Integer featureFlg) {
        this.featureFlg = featureFlg;
    }

    /**
     * <p>分类层级。</p>
     *
     * @return the 分类层级
     */
    public Integer getTreeLevel() {
        return treeLevel;
    }

    /**
     * <p>分类层级。</p>
     *
     * @param treeLevel 分类层级。
     */
    public void setTreeLevel(Integer treeLevel) {
        this.treeLevel = treeLevel;
    }

}
