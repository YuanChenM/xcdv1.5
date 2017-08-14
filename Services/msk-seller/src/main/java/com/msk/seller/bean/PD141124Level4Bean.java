package com.msk.seller.bean;

import com.msk.common.base.BaseBean;

import java.util.List;

/**
 * PD141124Bean. 产品分类加工bean
 *
 * @author xhy
 */
public class PD141124Level4Bean extends BaseBean {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;


    private List<PD141124Level5Bean> level5Beans;

    private String level4Name;

    private String parentCode;

    private String classesTreeCode;

    private String isFeature;

    private String  level4Code;


    /**
     * Getter method for property <tt>level4Code</tt>.
     *
     * @return property value of level4Code
     */
    public String getLevel4Code() {
        return level4Code;
    }

    /**
     * Setter method for property <tt>level4Code</tt>.
     *
     * @param level4Code value to be assigned to property level4Code
     */
    public void setLevel4Code(String level4Code) {
        this.level4Code = level4Code;
    }

    public void setClassesTreeCode(String classesTreeCode) {
        this.classesTreeCode = classesTreeCode;
    }

    /**
     * 获得classesTreeCode
     */
    public String getClassesTreeCode() {
        return classesTreeCode;
    }

    /**
     * Getter method for property <tt>isFeature</tt>.
     *
     * @return property value of isFeature
     */
    public String getIsFeature() {
        return isFeature;
    }

    /**
     * Setter method for property <tt>isFeature</tt>.
     *
     * @param isFeature value to be assigned to property isFeature
     */
    public void setIsFeature(String isFeature) {
        this.isFeature = isFeature;
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
     * 获得level5Beans
     */
    public List<PD141124Level5Bean> getLevel5Beans() {
        return level5Beans;
    }

    /**
     * 设置level5Beans
     */
    public void setLevel5Beans(List<PD141124Level5Bean> level5Beans) {
        this.level5Beans = level5Beans;
    }


    /**
     * 获得level4Name
     */
    public String getLevel4Name() {
        return level4Name;
    }

    /**
     * 设置level4Name
     */
    public void setLevel4Name(String level4Name) {
        this.level4Name = level4Name;
    }
}
