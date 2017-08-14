package com.msk.product.bean;


import com.msk.common.base.BaseBean;

import java.util.List;

/**
 * PD141124Bean. 产品分类加工bean
 *
 * @author xhy
 */
public class PD141124Level3Bean extends BaseBean {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;


    private List<PD141124Level4Bean> level4Beans;

    private String level3Name;

    private String level3Code;

    private String parentCode;

    private String classesTreeCode;

    private String isBreed;


    /**
     * Getter method for property <tt>level3Code</tt>.
     *
     * @return property value of level3Code
     */
    public String getLevel3Code() {
        return level3Code;
    }

    /**
     * Setter method for property <tt>level3Code</tt>.
     *
     * @param level3Code value to be assigned to property level3Code
     */
    public void setLevel3Code(String level3Code) {
        this.level3Code = level3Code;
    }

    public void setClassesTreeCode(String classesTreeCode) {
        this.classesTreeCode = classesTreeCode;
    }

    /**
     * Getter method for property <tt>isBreed</tt>.
     *
     * @return property value of isBreed
     */
    public String getIsBreed() {
        return isBreed;
    }

    /**
     * Setter method for property <tt>isBreed</tt>.
     *
     * @param isBreed value to be assigned to property isBreed
     */
    public void setIsBreed(String isBreed) {
        this.isBreed = isBreed;
    }

    /**
     * 获得classesTreeCode
     */
    public String getClassesTreeCode() {
        return classesTreeCode;
    }

    /**
     * 获得parentCode
     */
    public String getParentCode() {
        return parentCode;
    }

    /**
     * 设置parentCode
     */
    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }


    /**
     * 获得level4Beans
     */
    public List<PD141124Level4Bean> getLevel4Beans() {
        return level4Beans;
    }

    /**
     * 设置level4Beans
     */
    public void setLevel4Beans(List<PD141124Level4Bean> level4Beans) {
        this.level4Beans = level4Beans;
    }


    /**
     * 获得level3Name
     */
    public String getLevel3Name() {
        return level3Name;
    }

    /**
     * 设置level3Name
     */
    public void setLevel3Name(String level3Name) {
        this.level3Name = level3Name;
    }
}
