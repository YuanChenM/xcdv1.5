/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表sl_house_type对应的SlHouseType。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class SlHouseType extends BaseEntity{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /** 自增 */
    private java.lang.Long typeId;
    /** 类型CODE */
    private java.lang.String typeCode;
    /** 类型名称 */
    private java.lang.String typeName;
    /** 子类才有父类CODE */
    private java.lang.String parentTypeCode;
    /** 0:一级分类 ；1：二级分类 */
    private java.lang.String typeLever;
    /**
     * <p>默认构造函数。</p>
     */
    public SlHouseType() {

    }

    /**
     * <p>自增。</p>
     *
     * @return the 自增
     */
    public java.lang.Long getTypeId() {
        return typeId;
    }

    /**
     * <p>自增。</p>
     *
     * @param typeId 自增。
     */
    public void setTypeId(java.lang.Long typeId) {
        this.typeId = typeId;
    }

    /**
     * <p>类型CODE。</p>
     *
     * @return the 类型CODE
     */
    public java.lang.String getTypeCode() {
        return typeCode;
    }

    /**
     * <p>类型CODE。</p>
     *
     * @param typeCode 类型CODE。
     */
    public void setTypeCode(java.lang.String typeCode) {
        this.typeCode = typeCode;
    }

    /**
     * <p>类型名称。</p>
     *
     * @return the 类型名称
     */
    public java.lang.String getTypeName() {
        return typeName;
    }

    /**
     * <p>类型名称。</p>
     *
     * @param typeName 类型名称。
     */
    public void setTypeName(java.lang.String typeName) {
        this.typeName = typeName;
    }

    /**
     * <p>子类才有父类CODE。</p>
     *
     * @return the 子类才有父类CODE
     */
    public java.lang.String getParentTypeCode() {
        return parentTypeCode;
    }

    /**
     * <p>子类才有父类CODE。</p>
     *
     * @param parentTypeCode 子类才有父类CODE。
     */
    public void setParentTypeCode(java.lang.String parentTypeCode) {
        this.parentTypeCode = parentTypeCode;
    }

    /**
     * <p>0:一级分类 ；1：二级分类。</p>
     *
     * @return the 0:一级分类 ；1：二级分类
     */
    public java.lang.String getTypeLever() {
        return typeLever;
    }

    /**
     * <p>0:一级分类 ；1：二级分类。</p>
     *
     * @param typeLever 0:一级分类 ；1：二级分类。
     */
    public void setTypeLever(java.lang.String typeLever) {
        this.typeLever = typeLever;
    }

}
