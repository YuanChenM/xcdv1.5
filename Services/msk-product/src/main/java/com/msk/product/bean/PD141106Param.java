package com.msk.product.bean;

/**
 * PD141105 Controller Param
 * @author jiang_nan
 *
 */
public class PD141106Param extends RealityMeasureParam {
    private static final long serialVersionUID = 1L;

    private String [] pdQuaStdValIdArray;
    private String [] pdReaValArray;
    private String [] remarkArray;
    
    /**
     * @return the pdQuaStdValIdArray
     */
    public String[] getPdQuaStdValIdArray() {
        return pdQuaStdValIdArray;
    }
    /**
     * @param pdQuaStdValIdArray the pdQuaStdValIdArray to set
     */
    public void setPdQuaStdValIdArray(String[] pdQuaStdValIdArray) {
        this.pdQuaStdValIdArray = pdQuaStdValIdArray;
    }
    /**
     * @return the pdReaValArray
     */
    public String[] getPdReaValArray() {
        return pdReaValArray;
    }
    /**
     * @param pdReaValArray the pdReaValArray to set
     */
    public void setPdReaValArray(String[] pdReaValArray) {
        this.pdReaValArray = pdReaValArray;
    }
    /**
     * @return the remarkArray
     */
    public String[] getRemarkArray() {
        return remarkArray;
    }
    /**
     * @param remarkArray the remarkArray to set
     */
    public void setRemarkArray(String[] remarkArray) {
        this.remarkArray = remarkArray;
    }
}
