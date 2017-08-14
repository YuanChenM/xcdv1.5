package com.msk.product.bean;

import com.msk.core.entity.PdNormsStd;

/**
 * 
 * PD141112Param.
 *
 * @author silent
 */
public class PD141112Param extends PdNormsStd {
    
    /** serialVersionUID */
    private static final long serialVersionUID = 1L;
    /** 类别名称 */
    private String classesName;
    /** 品种名称 */
    private String breedName;
    /** 卖家名称 */
    private String sellerName;
    /**
     * get/set方法
     *
     * @return
     *
     * @author pxg
     */
    /**
     * Get the classesName.
     *
     * @return classesName
     *
     * @author Administrator
     */
    public String getClassesName() {
        return this.classesName;
    }
    /**
     * Set the classesName.
     *
     * @param classesName classesName
     *
     * @author Administrator
     */
    public void setClassesName(String classesName) {
        this.classesName = classesName;
    }
    /**
     * Get the breedName.
     *
     * @return breedName
     *
     * @author Administrator
     */
    public String getBreedName() {
        return this.breedName;
    }
    /**
     * Set the breedName.
     *
     * @param breedName breedName
     *
     * @author Administrator
     */
    public void setBreedName(String breedName) {
        this.breedName = breedName;
    }
    /**
     * Get the sellerName.
     *
     * @return sellerName
     *
     * @author Administrator
     */
    public String getSellerName() {
        return this.sellerName;
    }
    /**
     * Set the sellerName.
     *
     * @param sellerName sellerName
     *
     * @author Administrator
     */
    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }
}
