package com.msk.product.bean;


import com.msk.common.base.BaseBean;

import java.util.List;

/**
 * PD141124Bean. 产品分类加工bean
 * @author xhy
 */
public class PD141124Level5Bean extends BaseBean {
    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    private String  level5Name;

    private String  parentCode;

    private String  classesTreeCode;

    private String  level5Code;

    private List<PD141124Level6Bean> level6Beans;


    /**
     * Getter method for property <tt>level6Beans</tt>.
     *
     * @return property value of level6Beans
     */
    public List<PD141124Level6Bean> getLevel6Beans() {
        return level6Beans;
    }

    /**
     * Setter method for property <tt>level6Beans</tt>.
     *
     * @param level6Beans value to be assigned to property level6Beans
     */
    public void setLevel6Beans(List<PD141124Level6Bean> level6Beans) {
        this.level6Beans = level6Beans;
    }

    /**
     * Getter method for property <tt>level5Code</tt>.
     *
     * @return property value of level5Code
     */
    public String getLevel5Code() {
        return level5Code;
    }

    /**
     * Setter method for property <tt>level5Code</tt>.
     *
     * @param level5Code value to be assigned to property level5Code
     */
    public void setLevel5Code(String level5Code) {
        this.level5Code = level5Code;
    }

    /**
     * Getter method for property <tt>isFeature</tt>.
     *
     * @return property value of isFeature
     */


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
     * 获得level5Name
     */
    public String getLevel5Name() {
        return level5Name;
    }

    /**
     * 设置level5Name
     */
    public void setLevel5Name(String level5Name) {
        this.level5Name = level5Name;
    }
}
