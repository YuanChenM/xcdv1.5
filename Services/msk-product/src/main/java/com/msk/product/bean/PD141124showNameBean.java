package com.msk.product.bean;


import com.msk.common.base.BaseBean;

/**
 * PD141124Bean. 产品分类加工bean
 *
 * @author xhy
 */
public class PD141124showNameBean extends BaseBean {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    private String breedName;
    private String featureNames;

    /**
     * Getter method for property <tt>breedName</tt>.
     *
     * @return property value of breedName
     */
    public String getBreedName() {
        return breedName;
    }

    /**
     * Setter method for property <tt>breedName</tt>.
     *
     * @param breedName value to be assigned to property breedName
     */
    public void setBreedName(String breedName) {
        this.breedName = breedName;
    }

    /**
     * Getter method for property <tt>featureNames</tt>.
     *
     * @return property value of featureNames
     */
    public String getFeatureNames() {
        return featureNames;
    }

    /**
     * Setter method for property <tt>featureNames</tt>.
     *
     * @param featureNames value to be assigned to property featureNames
     */
    public void setFeatureNames(String featureNames) {
        this.featureNames = featureNames;
    }
}
