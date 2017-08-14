package com.msk.product.bean;

import com.msk.core.entity.PdStandard;

public class Standard extends PdStandard {
    private static final long serialVersionUID = 1L;
    private String breedName;
    private String classesName;

    /**
     * @return the breedName
     */
    public String getBreedName() {
        return breedName;
    }

    /**
     * @param breedName the breedName to set
     */
    public void setBreedName(String breedName) {
        this.breedName = breedName;
    }

    /**
     * @return the classesName
     */
    public String getClassesName() {
        return classesName;
    }

    /**
     * @param classesName the classesName to set
     */
    public void setClassesName(String classesName) {
        this.classesName = classesName;
    }

}
