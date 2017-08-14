/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表sl_house_buyer_product对应的SlHouseBuyerProduct</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class SlHouseBuyerProduct extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 关系ID */
    private Long relationId;
    /** 管家一级分类编码 */
    private String houseCategoryCode;
    /** 管家二级分类编码 */
    private String houseReclassifyCode;
    /** 买家产品一级分类编码 */
    private String byPdClassesCode;
    /** 买家产品二级分类编码 */
    private String byPdMachiningCode;
    /**
     * <p>默认构造函数</p>
     */
    public SlHouseBuyerProduct() {

    }

    /**
     * <p>关系ID</p>
     *
     * @return the 关系ID
     */
    public Long getRelationId() {
        return relationId;
    }

    /**
     * <p>关系ID</p>
     *
     * @param relationId 关系ID
     */
    public void setRelationId(Long relationId) {
        this.relationId = relationId;
    }

    /**
     * <p>管家一级分类编码</p>
     *
     * @return the 管家一级分类编码
     */
    public String getHouseCategoryCode() {
        return houseCategoryCode;
    }

    /**
     * <p>管家一级分类编码</p>
     *
     * @param houseCategoryCode 管家一级分类编码
     */
    public void setHouseCategoryCode(String houseCategoryCode) {
        this.houseCategoryCode = houseCategoryCode;
    }

    /**
     * <p>管家二级分类编码</p>
     *
     * @return the 管家二级分类编码
     */
    public String getHouseReclassifyCode() {
        return houseReclassifyCode;
    }

    /**
     * <p>管家二级分类编码</p>
     *
     * @param houseReclassifyCode 管家二级分类编码
     */
    public void setHouseReclassifyCode(String houseReclassifyCode) {
        this.houseReclassifyCode = houseReclassifyCode;
    }

    /**
     * <p>买家产品一级分类编码</p>
     *
     * @return the 买家产品一级分类编码
     */
    public String getByPdClassesCode() {
        return byPdClassesCode;
    }

    /**
     * <p>买家产品一级分类编码</p>
     *
     * @param byPdClassesCode 买家产品一级分类编码
     */
    public void setByPdClassesCode(String byPdClassesCode) {
        this.byPdClassesCode = byPdClassesCode;
    }

    /**
     * <p>买家产品二级分类编码</p>
     *
     * @return the 买家产品二级分类编码
     */
    public String getByPdMachiningCode() {
        return byPdMachiningCode;
    }

    /**
     * <p>买家产品二级分类编码</p>
     *
     * @param byPdMachiningCode 买家产品二级分类编码
     */
    public void setByPdMachiningCode(String byPdMachiningCode) {
        this.byPdMachiningCode = byPdMachiningCode;
    }

}
