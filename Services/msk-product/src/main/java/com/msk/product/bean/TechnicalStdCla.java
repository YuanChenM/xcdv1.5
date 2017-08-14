package com.msk.product.bean;

import java.util.List;

/**
 * 技术标准分类
 * 
 * @author yuan_chen
 */
public class TechnicalStdCla{

    /** size */
    private int size;

    /** technicalStdClaList */
    private List<TechnicalStdCla> technicalStdClaList;

    /** technicalStandard */
    private TechnicalStandard technicalStandard;

    /**
     * <p>
     * 默认构造函数。
     * </p>
     */
    public TechnicalStdCla() {

    }

    /**
     * Get the size.
     *
     * @return size
     *
     * @author gyh
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Set the size.
     *
     * @param size size
     *
     * @author gyh
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * Get the technicalStdClaList.
     *
     * @return technicalStdClaList
     *
     * @author gyh
     */
    public List<TechnicalStdCla> getTechnicalStdClaList() {
        return this.technicalStdClaList;
    }

    /**
     * Set the technicalStdClaList.
     *
     * @param technicalStdClaList technicalStdClaList
     *
     * @author gyh
     */
    public void setTechnicalStdClaList(List<TechnicalStdCla> technicalStdClaList) {
        this.technicalStdClaList = technicalStdClaList;
    }

    /**
     * Get the technicalStandard.
     *
     * @return technicalStandard
     *
     * @author gyh
     */
    public TechnicalStandard getTechnicalStandard() {
        return this.technicalStandard;
    }

    /**
     * Set the technicalStandard.
     *
     * @param technicalStandard technicalStandard
     *
     * @author gyh
     */
    public void setTechnicalStandard(TechnicalStandard technicalStandard) {
        this.technicalStandard = technicalStandard;
    }
}
