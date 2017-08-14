/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表pd_classestree_mat对应的PdClassestreeMat。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class PdClassestreeMat extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 产品分类原料ID */
    private java.lang.Long matId;
    /** 产品分类CODE */
    private java.lang.String classestreeCode;
    /** 类别编码 */
    private java.lang.String levelCode;
    /** 类别名称 */
    private java.lang.String levelName;
    /** 原料学名 */
    private java.lang.String scientificName;
    /** 俗名 */
    private java.lang.String localName;
    /** 销售名 */
    private java.lang.String salesName;
    /** 原料原产地 */
    private java.lang.String placeOrigin;
    /** 现产地 */
    private java.lang.String placeCurrent;
    /** 原料种源 */
    private java.lang.String source;
    /** 雏类 */
    private java.lang.String childType;
    /** 饲养方式 */
    private java.lang.String feedType;
    /** 饲养周期 */
    private java.lang.String feedPeriod;
    /** 备注 */
    private java.lang.String remark;
    /** 父分类编码 */
    private java.lang.String parentCode;
    /** 父分类名称 */
    private java.lang.String parentName;
    /** 分类层级 */
    private java.lang.Integer treeLevel;
    /**
     * <p>默认构造函数。</p>
     */
    public PdClassestreeMat() {

    }

    /**
     * <p>产品分类原料ID。</p>
     *
     * @return the 产品分类原料ID
     */
    public java.lang.Long getMatId() {
        return matId;
    }

    /**
     * <p>产品分类原料ID。</p>
     *
     * @param matId 产品分类原料ID。
     */
    public void setMatId(java.lang.Long matId) {
        this.matId = matId;
    }

    /**
     * <p>产品分类CODE。</p>
     *
     * @return the 产品分类CODE
     */
    public java.lang.String getClassestreeCode() {
        return classestreeCode;
    }

    /**
     * <p>产品分类CODE。</p>
     *
     * @param classestreeCode 产品分类CODE。
     */
    public void setClassestreeCode(java.lang.String classestreeCode) {
        this.classestreeCode = classestreeCode;
    }

    /**
     * <p>类别编码。</p>
     *
     * @return the 类别编码
     */
    public java.lang.String getLevelCode() {
        return levelCode;
    }

    /**
     * <p>类别编码。</p>
     *
     * @param levelCode 类别编码。
     */
    public void setLevelCode(java.lang.String levelCode) {
        this.levelCode = levelCode;
    }

    /**
     * <p>类别名称。</p>
     *
     * @return the 类别名称
     */
    public java.lang.String getLevelName() {
        return levelName;
    }

    /**
     * <p>类别名称。</p>
     *
     * @param levelName 类别名称。
     */
    public void setLevelName(java.lang.String levelName) {
        this.levelName = levelName;
    }

    /**
     * <p>原料学名。</p>
     *
     * @return the 原料学名
     */
    public java.lang.String getScientificName() {
        return scientificName;
    }

    /**
     * <p>原料学名。</p>
     *
     * @param scientificName 原料学名。
     */
    public void setScientificName(java.lang.String scientificName) {
        this.scientificName = scientificName;
    }

    /**
     * <p>俗名。</p>
     *
     * @return the 俗名
     */
    public java.lang.String getLocalName() {
        return localName;
    }

    /**
     * <p>俗名。</p>
     *
     * @param localName 俗名。
     */
    public void setLocalName(java.lang.String localName) {
        this.localName = localName;
    }

    /**
     * <p>销售名。</p>
     *
     * @return the 销售名
     */
    public java.lang.String getSalesName() {
        return salesName;
    }

    /**
     * <p>销售名。</p>
     *
     * @param salesName 销售名。
     */
    public void setSalesName(java.lang.String salesName) {
        this.salesName = salesName;
    }

    /**
     * <p>原料原产地。</p>
     *
     * @return the 原料原产地
     */
    public java.lang.String getPlaceOrigin() {
        return placeOrigin;
    }

    /**
     * <p>原料原产地。</p>
     *
     * @param placeOrigin 原料原产地。
     */
    public void setPlaceOrigin(java.lang.String placeOrigin) {
        this.placeOrigin = placeOrigin;
    }

    /**
     * <p>现产地。</p>
     *
     * @return the 现产地
     */
    public java.lang.String getPlaceCurrent() {
        return placeCurrent;
    }

    /**
     * <p>现产地。</p>
     *
     * @param placeCurrent 现产地。
     */
    public void setPlaceCurrent(java.lang.String placeCurrent) {
        this.placeCurrent = placeCurrent;
    }

    /**
     * <p>原料种源。</p>
     *
     * @return the 原料种源
     */
    public java.lang.String getSource() {
        return source;
    }

    /**
     * <p>原料种源。</p>
     *
     * @param source 原料种源。
     */
    public void setSource(java.lang.String source) {
        this.source = source;
    }

    /**
     * <p>雏类。</p>
     *
     * @return the 雏类
     */
    public java.lang.String getChildType() {
        return childType;
    }

    /**
     * <p>雏类。</p>
     *
     * @param childType 雏类。
     */
    public void setChildType(java.lang.String childType) {
        this.childType = childType;
    }

    /**
     * <p>饲养方式。</p>
     *
     * @return the 饲养方式
     */
    public java.lang.String getFeedType() {
        return feedType;
    }

    /**
     * <p>饲养方式。</p>
     *
     * @param feedType 饲养方式。
     */
    public void setFeedType(java.lang.String feedType) {
        this.feedType = feedType;
    }

    /**
     * <p>饲养周期。</p>
     *
     * @return the 饲养周期
     */
    public java.lang.String getFeedPeriod() {
        return feedPeriod;
    }

    /**
     * <p>饲养周期。</p>
     *
     * @param feedPeriod 饲养周期。
     */
    public void setFeedPeriod(java.lang.String feedPeriod) {
        this.feedPeriod = feedPeriod;
    }

    /**
     * <p>备注。</p>
     *
     * @return the 备注
     */
    public java.lang.String getRemark() {
        return remark;
    }

    /**
     * <p>备注。</p>
     *
     * @param remark 备注。
     */
    public void setRemark(java.lang.String remark) {
        this.remark = remark;
    }

    /**
     * <p>父分类编码。</p>
     *
     * @return the 父分类编码
     */
    public java.lang.String getParentCode() {
        return parentCode;
    }

    /**
     * <p>父分类编码。</p>
     *
     * @param parentCode 父分类编码。
     */
    public void setParentCode(java.lang.String parentCode) {
        this.parentCode = parentCode;
    }

    /**
     * <p>父分类名称。</p>
     *
     * @return the 父分类名称
     */
    public java.lang.String getParentName() {
        return parentName;
    }

    /**
     * <p>父分类名称。</p>
     *
     * @param parentName 父分类名称。
     */
    public void setParentName(java.lang.String parentName) {
        this.parentName = parentName;
    }

    /**
     * <p>分类层级。</p>
     *
     * @return the 分类层级
     */
    public java.lang.Integer getTreeLevel() {
        return treeLevel;
    }

    /**
     * <p>分类层级。</p>
     *
     * @param treeLevel 分类层级。
     */
    public void setTreeLevel(java.lang.Integer treeLevel) {
        this.treeLevel = treeLevel;
    }

}
