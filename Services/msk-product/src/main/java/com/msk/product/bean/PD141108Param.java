package com.msk.product.bean;

/**
 * PD141108 Controller Param
 * 
 * @author yuan_chen
 *
 */
public class PD141108Param extends RealityMeasureParam {

    private static final long serialVersionUID = 1L;

    private String[] pdTncStdIdArray;
    private String[] reaContentArray;

    /**
     * Get the pdTncStdIdArray.
     *
     * @return pdTncStdIdArray
     *
     * @author yuan_chen
     */
    public String[] getPdTncStdIdArray() {
        return this.pdTncStdIdArray;
    }

    /**
     * Set the pdTncStdIdArray.
     *
     * @param pdTncStdIdArray pdTncStdIdArray
     *
     * @author yuan_chen
     */
    public void setPdTncStdIdArray(String[] pdTncStdIdArray) {
        this.pdTncStdIdArray = pdTncStdIdArray;
    }

    /**
     * Get the reaContentArray.
     *
     * @return reaContentArray
     *
     * @author yuan_chen
     */
    public String[] getReaContentArray() {
        return this.reaContentArray;
    }

    /**
     * Set the reaContentArray.
     *
     * @param reaContentArray reaContentArray
     *
     * @author yuan_chen
     */
    public void setReaContentArray(String[] reaContentArray) {
        this.reaContentArray = reaContentArray;
    }

}
