package com.msk.buyers.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.msk.core.entity.ByResearchCategory;

/**
 * IBY121222RsParam.
 *
 * @author zhou_ling
 */
@JsonIgnoreProperties(value = { "crtTime","updTime"})
public class IBY121222RsParam extends ByResearchCategory {

    private static final long serialVersionUID = 1L;
    /** 产品分类CODE */
    private String classesTreeCode;
    /** 产品照片ID */
    private String productId;
    /** 产品照片后缀名 */
    private String productSuf;
    /** 品牌名称 */
    private String brandName;
    /** 图片路径*/
    private String picturePath;


    /**
     * Getter method for property <tt>classesTreeCode</tt>.
     *
     * @return property value of classesTreeCode
     */
    public String getClassesTreeCode() {
        return classesTreeCode;
    }

    /**
     * Setter method for property <tt>classesTreeCode</tt>.
     *
     * @param classesTreeCode value to be assigned to property classesTreeCode
     */
    public void setClassesTreeCode(String classesTreeCode) {
        this.classesTreeCode = classesTreeCode;
    }

    /**
     * Getter method for property <tt>productId</tt>.
     *
     * @return property value of productId
     */
    public String getProductId() {
        return productId;
    }

    /**
     * Setter method for property <tt>productId</tt>.
     *
     * @param productId value to be assigned to property productId
     */
    public void setProductId(String productId) {
        this.productId = productId;
    }

    /**
     * Getter method for property <tt>productSuf</tt>.
     *
     * @return property value of productSuf
     */
    public String getProductSuf() {
        return productSuf;
    }

    /**
     * Setter method for property <tt>productSuf</tt>.
     *
     * @param productSuf value to be assigned to property productSuf
     */
    public void setProductSuf(String productSuf) {
        this.productSuf = productSuf;
    }

    /**
     * Getter method for property <tt>brandName</tt>.
     *
     * @return property value of brandName
     */
    public String getBrandName() {
        return brandName;
    }

    /**
     * Setter method for property <tt>brandName</tt>.
     *
     * @param brandName value to be assigned to property brandName
     */
    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    /**
     * Getter method for property <tt>picturePath</tt>.
     *
     * @return property value of picturePath
     */
    public String getPicturePath() {
        return picturePath;
    }

    /**
     * Setter method for property <tt>picturePath</tt>.
     *
     * @param picturePath value to be assigned to property picturePath
     */
    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }
}