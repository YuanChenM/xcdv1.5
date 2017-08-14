/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表pd_classestree对应的PdClassestree。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class PdClassestree extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 产品分类ID */
    private java.lang.Long classestreeId;
    /** 产品分类CODE */
    private java.lang.String classestreeCode;
    /** 类别编码 */
    private java.lang.String levelCode;
    /** 类别名称 */
    private java.lang.String levelName;
    /** 父分类编码 */
    private java.lang.String parentCode;
    /** 父分类名称 */
    private java.lang.String parentName;
    /** 分类层级 */
    private java.lang.Integer treeLevel;
    /** 0:否，1：是 */
    private java.lang.Integer isBreed;
    /** 0:否，1：是 */
    private java.lang.Integer isFeature;
    /**
     * <p>默认构造函数。</p>
     */
    public PdClassestree() {

    }

    /**
     * <p>产品分类ID。</p>
     *
     * @return the 产品分类ID
     */
    public java.lang.Long getClassestreeId() {
        return classestreeId;
    }

    /**
     * <p>产品分类ID。</p>
     *
     * @param classestreeId 产品分类ID。
     */
    public void setClassestreeId(java.lang.Long classestreeId) {
        this.classestreeId = classestreeId;
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

    /**
     * <p>0:否，1：是。</p>
     *
     * @return the 0:否，1：是
     */
    public java.lang.Integer getIsBreed() {
        return isBreed;
    }

    /**
     * <p>0:否，1：是。</p>
     *
     * @param isBreed 0:否，1：是。
     */
    public void setIsBreed(java.lang.Integer isBreed) {
        this.isBreed = isBreed;
    }

    /**
     * <p>0:否，1：是。</p>
     *
     * @return the 0:否，1：是
     */
    public java.lang.Integer getIsFeature() {
        return isFeature;
    }

    /**
     * <p>0:否，1：是。</p>
     *
     * @param isFeature 0:否，1：是。
     */
    public void setIsFeature(java.lang.Integer isFeature) {
        this.isFeature = isFeature;
    }

}
