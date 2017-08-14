/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表sl_ep_brand对应的SlEpBrand。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class SlEpBrand extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 企业ID */
    private Long epId;
    /** 品牌ID */
    private Long brandId;
    /** 品牌名称 */
    private String brandName;
    /** 0:卖家独立品牌,1 :神农先生联合,2:神农客联合3:神农人家联合 */
    private String brandClass;
    /** 商标注册证 */
    private String brandNo;
    /** 有效期限开始 */
    private java.util.Date brandTermBegin;
    /** 有效期限截止 */
    private java.util.Date brandTermEnd;
    /**
     * <p>默认构造函数。</p>
     */
    public SlEpBrand() {

    }

    /**
     * <p>企业ID。</p>
     *
     * @return the 企业ID
     */
    public Long getEpId() {
        return epId;
    }

    /**
     * <p>企业ID。</p>
     *
     * @param epId 企业ID。
     */
    public void setEpId(Long epId) {
        this.epId = epId;
    }

    /**
     * <p>品牌ID。</p>
     *
     * @return the 品牌ID
     */
    public Long getBrandId() {
        return brandId;
    }

    /**
     * <p>品牌ID。</p>
     *
     * @param brandId 品牌ID。
     */
    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    /**
     * <p>品牌名称。</p>
     *
     * @return the 品牌名称
     */
    public String getBrandName() {
        return brandName;
    }

    /**
     * <p>品牌名称。</p>
     *
     * @param brandName 品牌名称。
     */
    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    /**
     * <p>0:卖家独立品牌,1 :神农先生联合,2:神农客联合3:神农人家联合。</p>
     *
     * @return the 0:卖家独立品牌,1 :神农先生联合,2:神农客联合3:神农人家联合
     */
    public String getBrandClass() {
        return brandClass;
    }

    /**
     * <p>0:卖家独立品牌,1 :神农先生联合,2:神农客联合3:神农人家联合。</p>
     *
     * @param brandClass 0:卖家独立品牌,1 :神农先生联合,2:神农客联合3:神农人家联合。
     */
    public void setBrandClass(String brandClass) {
        this.brandClass = brandClass;
    }

    /**
     * <p>商标注册证。</p>
     *
     * @return the 商标注册证
     */
    public String getBrandNo() {
        return brandNo;
    }

    /**
     * <p>商标注册证。</p>
     *
     * @param brandNo 商标注册证。
     */
    public void setBrandNo(String brandNo) {
        this.brandNo = brandNo;
    }

    /**
     * <p>有效期限开始。</p>
     *
     * @return the 有效期限开始
     */
    public java.util.Date getBrandTermBegin() {
        return brandTermBegin;
    }

    /**
     * <p>有效期限开始。</p>
     *
     * @param brandTermBegin 有效期限开始。
     */
    public void setBrandTermBegin(java.util.Date brandTermBegin) {
        this.brandTermBegin = brandTermBegin;
    }

    /**
     * <p>有效期限截止。</p>
     *
     * @return the 有效期限截止
     */
    public java.util.Date getBrandTermEnd() {
        return brandTermEnd;
    }

    /**
     * <p>有效期限截止。</p>
     *
     * @param brandTermEnd 有效期限截止。
     */
    public void setBrandTermEnd(java.util.Date brandTermEnd) {
        this.brandTermEnd = brandTermEnd;
    }

}
