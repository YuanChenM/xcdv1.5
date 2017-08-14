package com.msk.product.bean;

import com.msk.core.entity.BaseEntity;

/**
 * PD14210101Bean
 *
 * @author xhy
 */
public class PD14210101Bean extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String machiningName;

    private String breedName;

    private String scientificName;

    private String localName;

    private String classestreeCode;

    /**
     * Getter method for property <tt>classestreeCode</tt>.
     *
     * @return property value of classestreeCode
     */
    public String getClassestreeCode() {
        return classestreeCode;
    }

    /**
     * Setter method for property <tt>classestreeCode</tt>.
     *
     * @param classestreeCode value to be assigned to property classestreeCode
     */
    public void setClassestreeCode(String classestreeCode) {
        this.classestreeCode = classestreeCode;
    }

    /**
     * Getter method for property <tt>machiningName</tt>.
     *
     * @return property value of machiningName
     */
    public String getMachiningName() {
        return machiningName;
    }

    /**
     * Setter method for property <tt>machiningName</tt>.
     *
     * @param machiningName value to be assigned to property machiningName
     */
    public void setMachiningName(String machiningName) {
        this.machiningName = machiningName;
    }

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
     * Getter method for property <tt>scientificName</tt>.
     *
     * @return property value of scientificName
     */
    public String getScientificName() {
        return scientificName;
    }

    /**
     * Setter method for property <tt>scientificName</tt>.
     *
     * @param scientificName value to be assigned to property scientificName
     */
    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    /**
     * Getter method for property <tt>localName</tt>.
     *
     * @return property value of localName
     */
    public String getLocalName() {
        return localName;
    }

    /**
     * Setter method for property <tt>localName</tt>.
     *
     * @param localName value to be assigned to property localName
     */
    public void setLocalName(String localName) {
        this.localName = localName;
    }


}
