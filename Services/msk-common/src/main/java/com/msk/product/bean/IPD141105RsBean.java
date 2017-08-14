package com.msk.product.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.msk.core.entity.BaseEntity;

/**
 * 查询产品主码
 * IPD141105RsParam.
 *
 * @author xhy
 */
@JsonIgnoreProperties(value = {"delFlg", "crtId", "crtTime", "updId", "updTime", "ver", "actId", "actTime", "breedCode", "featureCode", "gradeCode", "breedName", "featureName", "gradeName"})
public class IPD141105RsBean extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String classesCode; // 类别编码
    private String classesName; // 类别名称
    private String pdCode; // 指定位数产品主码
    private String pdName; // 指定位数产品名称

    /**
     * Getter method for property <tt>classesCode</tt>.
     *
     * @return property value of classesCode
     */
    public String getClassesCode() {
        return classesCode;
    }

    /**
     * Setter method for property <tt>classesCode</tt>.
     *
     * @param classesCode value to be assigned to property classesCode
     */
    public void setClassesCode(String classesCode) {
        this.classesCode = classesCode;
    }

    /**
     * Getter method for property <tt>classesName</tt>.
     *
     * @return property value of classesName
     */
    public String getClassesName() {
        return classesName;
    }

    /**
     * Setter method for property <tt>classesName</tt>.
     *
     * @param classesName value to be assigned to property classesName
     */
    public void setClassesName(String classesName) {
        this.classesName = classesName;
    }

    /**
     * Getter method for property <tt>pdCode</tt>.
     *
     * @return property value of pdCode
     */
    public String getPdCode() {
        return pdCode;
    }

    /**
     * Setter method for property <tt>pdCode</tt>.
     *
     * @param pdCode value to be assigned to property pdCode
     */
    public void setPdCode(String pdCode) {
        this.pdCode = pdCode;
    }

    /**
     * Getter method for property <tt>pdName</tt>.
     *
     * @return property value of pdName
     */
    public String getPdName() {
        return pdName;
    }

    /**
     * Setter method for property <tt>pdName</tt>.
     *
     * @param pdName value to be assigned to property pdName
     */
    public void setPdName(String pdName) {
        this.pdName = pdName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IPD141105RsBean that = (IPD141105RsBean) o;

        if (!getClassesCode().equals(that.getClassesCode())) return false;
        if (!getClassesName().equals(that.getClassesName())) return false;
        if (!getPdCode().equals(that.getPdCode())) return false;
        return getPdName().equals(that.getPdName());

    }

    @Override
    public int hashCode() {
        int result = getClassesCode().hashCode();
        result = 31 * result + getClassesName().hashCode();
        result = 31 * result + getPdCode().hashCode();
        result = 31 * result + getPdName().hashCode();
        return result;
    }
}