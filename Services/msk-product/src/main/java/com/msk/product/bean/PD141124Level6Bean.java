package com.msk.product.bean;


import com.msk.common.base.BaseBean;

/**
 * PD141124Bean. 产品分类加工bean
 * @author xhy
 */
public class PD141124Level6Bean extends BaseBean {
    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    private String  level6Name;

    private String  parentCode;

    private String  classesTreeCode;

    private String  level6Code;

    /**
     * Getter method for property <tt>level6Name</tt>.
     *
     * @return property value of level6Name
     */
    public String getLevel6Name() {
        return level6Name;
    }

    /**
     * Setter method for property <tt>level6Name</tt>.
     *
     * @param level6Name value to be assigned to property level6Name
     */
    public void setLevel6Name(String level6Name) {
        this.level6Name = level6Name;
    }

    /**
     * Getter method for property <tt>parentCode</tt>.
     *
     * @return property value of parentCode
     */
    public String getParentCode() {
        return parentCode;
    }

    /**
     * Setter method for property <tt>parentCode</tt>.
     *
     * @param parentCode value to be assigned to property parentCode
     */
    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

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
     * Getter method for property <tt>level6Code</tt>.
     *
     * @return property value of level6Code
     */
    public String getLevel6Code() {
        return level6Code;
    }

    /**
     * Setter method for property <tt>level6Code</tt>.
     *
     * @param level6Code value to be assigned to property level6Code
     */
    public void setLevel6Code(String level6Code) {
        this.level6Code = level6Code;
    }
}
