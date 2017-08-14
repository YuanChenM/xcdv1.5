package com.msk.seller.bean;

import com.msk.core.entity.BaseEntity;
import com.msk.core.entity.SlEpBrandHonor;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/3/1.
 */
public class ISL231180SlEpBrandList extends BaseEntity{
    /**企业ID*/
    private Long epId;
    /**品牌id*/
    private Long brandId;
    /**品牌名称*/
    private String brandName;
    /**商标注册证*/
    private String brandNo;
    /**有效期限开始*/
    private Date brandTermBegin;
    /**有效期限截止*/
    private Date brandTermEnd;
    /**企业产品品牌荣誉*/
    private List<SlEpBrandHonor> slEpBrandHonorList;
    /**企业产品品牌类型*/
    private String brandClass;

    /**
     * Getter method for property <tt>brandClass</tt>.
     *
     * @return property value of brandClass
     */
    public String getBrandClass() {
        return brandClass;
    }

    /**
     * Setter method for property <tt>brandClass</tt>.
     *
     * @param brandClass value to be assigned to property brandClass
     */
    public void setBrandClass(String brandClass) {
        this.brandClass = brandClass;
    }

    /**
     * Getter method for property <tt>epId</tt>.
     *
     * @return property value of epId
     */
    public Long getEpId() {
        return epId;
    }

    /**
     * Setter method for property <tt>epId</tt>.
     *
     * @param epId value to be assigned to property epId
     */
    public void setEpId(Long epId) {
        this.epId = epId;
    }

    /**
     * Getter method for property <tt>brandId</tt>.
     *
     * @return property value of brandId
     */
    public Long getBrandId() {
        return brandId;
    }

    /**
     * Setter method for property <tt>brandId</tt>.
     *
     * @param brandId value to be assigned to property brandId
     */
    public void setBrandId(Long brandId) {
        this.brandId = brandId;
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
     * Getter method for property <tt>brandNo</tt>.
     *
     * @return property value of brandNo
     */
    public String getBrandNo() {
        return brandNo;
    }

    /**
     * Setter method for property <tt>brandNo</tt>.
     *
     * @param brandNo value to be assigned to property brandNo
     */
    public void setBrandNo(String brandNo) {
        this.brandNo = brandNo;
    }

    /**
     * Getter method for property <tt>brandTermBegin</tt>.
     *
     * @return property value of brandTermBegin
     */
    public Date getBrandTermBegin() {
        return brandTermBegin;
    }

    /**
     * Setter method for property <tt>brandTermBegin</tt>.
     *
     * @param brandTermBegin value to be assigned to property brandTermBegin
     */
    public void setBrandTermBegin(Date brandTermBegin) {
        this.brandTermBegin = brandTermBegin;
    }

    /**
     * Getter method for property <tt>brandTermEnd</tt>.
     *
     * @return property value of brandTermEnd
     */
    public Date getBrandTermEnd() {
        return brandTermEnd;
    }

    /**
     * Setter method for property <tt>brandTermEnd</tt>.
     *
     * @param brandTermEnd value to be assigned to property brandTermEnd
     */
    public void setBrandTermEnd(Date brandTermEnd) {
        this.brandTermEnd = brandTermEnd;
    }

    /**
     * Getter method for property <tt>slEpBrandHonorList</tt>.
     *
     * @return property value of slEpBrandHonorList
     */
    public List<SlEpBrandHonor> getSlEpBrandHonorList() {
        return slEpBrandHonorList;
    }

    /**
     * Setter method for property <tt>slEpBrandHonorList</tt>.
     *
     * @param slEpBrandHonorList value to be assigned to property slEpBrandHonorList
     */
    public void setSlEpBrandHonorList(List<SlEpBrandHonor> slEpBrandHonorList) {
        this.slEpBrandHonorList = slEpBrandHonorList;
    }
}
