package com.msk.product.bean;


import com.msk.common.base.BaseBean;

import java.util.List;

/**
 * PD141124Bean. 产品分类加工bean
 *
 * @author xhy
 */
public class PD141124Level2Bean extends BaseBean {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;


    private List<PD141124Level3Bean> level3Beans;

    private String level2Name;

    private String parentCode;

    private String classesTreeCode;

    private String level2Code;


    /**
     * Getter method for property <tt>level2Code</tt>.
     *
     * @return property value of level2Code
     */
    public String getLevel2Code() {
        return level2Code;
    }

    /**
     * Setter method for property <tt>level2Code</tt>.
     *
     * @param level2Code value to be assigned to property level2Code
     */
    public void setLevel2Code(String level2Code) {
        this.level2Code = level2Code;
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
     * 设置classesTreeCode
     */
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
     * 获得level3Beans
     */
    public List<PD141124Level3Bean> getLevel3Beans() {
        return level3Beans;
    }

    /**
     * 设置level3Beans
     */
    public void setLevel3Beans(List<PD141124Level3Bean> level3Beans) {
        this.level3Beans = level3Beans;
    }


    /**
     * 获得level2Name
     */
    public String getLevel2Name() {
        return level2Name;
    }

    /**
     * 设置level2Name
     */
    public void setLevel2Name(String level2Name) {
        this.level2Name = level2Name;
    }
}
