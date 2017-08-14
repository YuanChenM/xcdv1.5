package com.msk.product.bean;

import com.msk.core.entity.PdPriceprdLogiarea;

import java.math.BigDecimal;

/**
 * PD141120Bean.
 *
 * @author gyh
 */
public class PD141120Bean extends PdPriceprdLogiarea {

    //价盘周期的日期
    private String pricecycleDate;
    //价盘周期
    private String pricecycle;
    //    公斤价
    private BigDecimal priceofkg1;
    private BigDecimal priceofkg2;
    private BigDecimal priceofkg3;
    private BigDecimal priceofkg4;
    private BigDecimal priceofkg5;
    private BigDecimal priceofkg6;
    private BigDecimal priceofkg7;
    private BigDecimal priceofkg8;
    private BigDecimal priceofkg9;
    private BigDecimal priceofkg10;
    private String wayCode;//通道等级编码
    private String[] orderlevelCodeArray;//订单等级编码数组
    private String[] orderlevelNameArray;//订单等级名称数组
    private BigDecimal[] priceofkgArray;//公斤价数组
    private BigDecimal[] pricePercentArray;//报价平衡系数数组
    private String[] boxCntArray;//箱数范围数组
    private Integer[] boxCntminArray;//箱数范围下限数组
    private Integer[] boxCntmaxArray;//箱数范围上限数组
    private BigDecimal pricePercent;//报价平衡系数
    private String boxCnt;//箱数范围
    private Integer boxCntmin;//箱数范围下限
    private Integer boxCntmax;//箱数范围上限

    /**
     * Getter method for property <tt>pricecycleDate</tt>.
     *
     * @return property value of pricecycleDate
     */
    public String getPricecycleDate() {
        return pricecycleDate;
    }

    /**
     * Setter method for property <tt>pricecycleDate</tt>.
     *
     * @param pricecycleDate value to be assigned to property pricecycleDate
     */
    public void setPricecycleDate(String pricecycleDate) {
        this.pricecycleDate = pricecycleDate;
    }

    /**
     * Getter method for property <tt>pricecycle</tt>.
     *
     * @return property value of pricecycle
     */
    public String getPricecycle() {
        return pricecycle;
    }

    /**
     * Setter method for property <tt>pricecycle</tt>.
     *
     * @param pricecycle value to be assigned to property pricecycle
     */
    public void setPricecycle(String pricecycle) {
        this.pricecycle = pricecycle;
    }

    /**
     * Getter method for property <tt>priceofkg1</tt>.
     *
     * @return property value of priceofkg1
     */
    public BigDecimal getPriceofkg1() {
        return priceofkg1;
    }

    /**
     * Setter method for property <tt>priceofkg1</tt>.
     *
     * @param priceofkg1 value to be assigned to property priceofkg1
     */
    public void setPriceofkg1(BigDecimal priceofkg1) {
        this.priceofkg1 = priceofkg1;
    }

    /**
     * Getter method for property <tt>priceofkg2</tt>.
     *
     * @return property value of priceofkg2
     */
    public BigDecimal getPriceofkg2() {
        return priceofkg2;
    }

    /**
     * Setter method for property <tt>priceofkg2</tt>.
     *
     * @param priceofkg2 value to be assigned to property priceofkg2
     */
    public void setPriceofkg2(BigDecimal priceofkg2) {
        this.priceofkg2 = priceofkg2;
    }

    /**
     * Getter method for property <tt>priceofkg3</tt>.
     *
     * @return property value of priceofkg3
     */
    public BigDecimal getPriceofkg3() {
        return priceofkg3;
    }

    /**
     * Setter method for property <tt>priceofkg3</tt>.
     *
     * @param priceofkg3 value to be assigned to property priceofkg3
     */
    public void setPriceofkg3(BigDecimal priceofkg3) {
        this.priceofkg3 = priceofkg3;
    }

    /**
     * Getter method for property <tt>priceofkg4</tt>.
     *
     * @return property value of priceofkg4
     */
    public BigDecimal getPriceofkg4() {
        return priceofkg4;
    }

    /**
     * Setter method for property <tt>priceofkg4</tt>.
     *
     * @param priceofkg4 value to be assigned to property priceofkg4
     */
    public void setPriceofkg4(BigDecimal priceofkg4) {
        this.priceofkg4 = priceofkg4;
    }

    /**
     * Getter method for property <tt>priceofkg5</tt>.
     *
     * @return property value of priceofkg5
     */
    public BigDecimal getPriceofkg5() {
        return priceofkg5;
    }

    /**
     * Setter method for property <tt>priceofkg5</tt>.
     *
     * @param priceofkg5 value to be assigned to property priceofkg5
     */
    public void setPriceofkg5(BigDecimal priceofkg5) {
        this.priceofkg5 = priceofkg5;
    }

    /**
     * Getter method for property <tt>priceofkg6</tt>.
     *
     * @return property value of priceofkg6
     */
    public BigDecimal getPriceofkg6() {
        return priceofkg6;
    }

    /**
     * Setter method for property <tt>priceofkg6</tt>.
     *
     * @param priceofkg6 value to be assigned to property priceofkg6
     */
    public void setPriceofkg6(BigDecimal priceofkg6) {
        this.priceofkg6 = priceofkg6;
    }

    /**
     * Getter method for property <tt>priceofkg7</tt>.
     *
     * @return property value of priceofkg7
     */
    public BigDecimal getPriceofkg7() {
        return priceofkg7;
    }

    /**
     * Setter method for property <tt>priceofkg7</tt>.
     *
     * @param priceofkg7 value to be assigned to property priceofkg7
     */
    public void setPriceofkg7(BigDecimal priceofkg7) {
        this.priceofkg7 = priceofkg7;
    }

    /**
     * Getter method for property <tt>priceofkg8</tt>.
     *
     * @return property value of priceofkg8
     */
    public BigDecimal getPriceofkg8() {
        return priceofkg8;
    }

    /**
     * Setter method for property <tt>priceofkg8</tt>.
     *
     * @param priceofkg8 value to be assigned to property priceofkg8
     */
    public void setPriceofkg8(BigDecimal priceofkg8) {
        this.priceofkg8 = priceofkg8;
    }

    /**
     * Getter method for property <tt>priceofkg9</tt>.
     *
     * @return property value of priceofkg9
     */
    public BigDecimal getPriceofkg9() {
        return priceofkg9;
    }

    /**
     * Setter method for property <tt>priceofkg9</tt>.
     *
     * @param priceofkg9 value to be assigned to property priceofkg9
     */
    public void setPriceofkg9(BigDecimal priceofkg9) {
        this.priceofkg9 = priceofkg9;
    }

    /**
     * Getter method for property <tt>priceofkg10</tt>.
     *
     * @return property value of priceofkg10
     */
    public BigDecimal getPriceofkg10() {
        return priceofkg10;
    }

    /**
     * Setter method for property <tt>priceofkg10</tt>.
     *
     * @param priceofkg10 value to be assigned to property priceofkg10
     */
    public void setPriceofkg10(BigDecimal priceofkg10) {
        this.priceofkg10 = priceofkg10;
    }

    /**
     * Getter method for property <tt>wayCode</tt>.
     *
     * @return property value of wayCode
     */
    public String getWayCode() {
        return wayCode;
    }

    /**
     * Setter method for property <tt>wayCode</tt>.
     *
     * @param wayCode value to be assigned to property wayCode
     */
    public void setWayCode(String wayCode) {
        this.wayCode = wayCode;
    }

    /**
     * Getter method for property <tt>orderlevelCodeArray</tt>.
     *
     * @return property value of orderlevelCodeArray
     */
    public String[] getOrderlevelCodeArray() {
        return orderlevelCodeArray;
    }

    /**
     * Setter method for property <tt>orderlevelCodeArray</tt>.
     *
     * @param orderlevelCodeArray value to be assigned to property orderlevelCodeArray
     */
    public void setOrderlevelCodeArray(String[] orderlevelCodeArray) {
        this.orderlevelCodeArray = orderlevelCodeArray;
    }

    /**
     * Getter method for property <tt>orderlevelNameArray</tt>.
     *
     * @return property value of orderlevelNameArray
     */
    public String[] getOrderlevelNameArray() {
        return orderlevelNameArray;
    }

    /**
     * Setter method for property <tt>orderlevelNameArray</tt>.
     *
     * @param orderlevelNameArray value to be assigned to property orderlevelNameArray
     */
    public void setOrderlevelNameArray(String[] orderlevelNameArray) {
        this.orderlevelNameArray = orderlevelNameArray;
    }

    /**
     * Getter method for property <tt>priceofkgArray</tt>.
     *
     * @return property value of priceofkgArray
     */
    public BigDecimal[] getPriceofkgArray() {
        return priceofkgArray;
    }

    /**
     * Setter method for property <tt>priceofkgArray</tt>.
     *
     * @param priceofkgArray value to be assigned to property priceofkgArray
     */
    public void setPriceofkgArray(BigDecimal[] priceofkgArray) {
        this.priceofkgArray = priceofkgArray;
    }

    /**
     * Getter method for property <tt>pricePercentArray</tt>.
     *
     * @return property value of pricePercentArray
     */
    public BigDecimal[] getPricePercentArray() {
        return pricePercentArray;
    }

    /**
     * Setter method for property <tt>pricePercentArray</tt>.
     *
     * @param pricePercentArray value to be assigned to property pricePercentArray
     */
    public void setPricePercentArray(BigDecimal[] pricePercentArray) {
        this.pricePercentArray = pricePercentArray;
    }

    /**
     * Getter method for property <tt>boxCntArray</tt>.
     *
     * @return property value of boxCntArray
     */
    public String[] getBoxCntArray() {
        return boxCntArray;
    }

    /**
     * Setter method for property <tt>boxCntArray</tt>.
     *
     * @param boxCntArray value to be assigned to property boxCntArray
     */
    public void setBoxCntArray(String[] boxCntArray) {
        this.boxCntArray = boxCntArray;
    }

    /**
     * Getter method for property <tt>boxCntminArray</tt>.
     *
     * @return property value of boxCntminArray
     */
    public Integer[] getBoxCntminArray() {
        return boxCntminArray;
    }

    /**
     * Setter method for property <tt>boxCntminArray</tt>.
     *
     * @param boxCntminArray value to be assigned to property boxCntminArray
     */
    public void setBoxCntminArray(Integer[] boxCntminArray) {
        this.boxCntminArray = boxCntminArray;
    }

    /**
     * Getter method for property <tt>boxCntmaxArray</tt>.
     *
     * @return property value of boxCntmaxArray
     */
    public Integer[] getBoxCntmaxArray() {
        return boxCntmaxArray;
    }

    /**
     * Setter method for property <tt>boxCntmaxArray</tt>.
     *
     * @param boxCntmaxArray value to be assigned to property boxCntmaxArray
     */
    public void setBoxCntmaxArray(Integer[] boxCntmaxArray) {
        this.boxCntmaxArray = boxCntmaxArray;
    }

    /**
     * Getter method for property <tt>pricePercent</tt>.
     *
     * @return property value of pricePercent
     */
    public BigDecimal getPricePercent() {
        return pricePercent;
    }

    /**
     * Setter method for property <tt>pricePercent</tt>.
     *
     * @param pricePercent value to be assigned to property pricePercent
     */
    public void setPricePercent(BigDecimal pricePercent) {
        this.pricePercent = pricePercent;
    }

    /**
     * Getter method for property <tt>boxCnt</tt>.
     *
     * @return property value of boxCnt
     */
    public String getBoxCnt() {
        return boxCnt;
    }

    /**
     * Setter method for property <tt>boxCnt</tt>.
     *
     * @param boxCnt value to be assigned to property boxCnt
     */
    public void setBoxCnt(String boxCnt) {
        this.boxCnt = boxCnt;
    }

    /**
     * Getter method for property <tt>boxCntmin</tt>.
     *
     * @return property value of boxCntmin
     */
    public Integer getBoxCntmin() {
        return boxCntmin;
    }

    /**
     * Setter method for property <tt>boxCntmin</tt>.
     *
     * @param boxCntmin value to be assigned to property boxCntmin
     */
    public void setBoxCntmin(Integer boxCntmin) {
        this.boxCntmin = boxCntmin;
    }

    /**
     * Getter method for property <tt>boxCntmax</tt>.
     *
     * @return property value of boxCntmax
     */
    public Integer getBoxCntmax() {
        return boxCntmax;
    }

    /**
     * Setter method for property <tt>boxCntmax</tt>.
     *
     * @param boxCntmax value to be assigned to property boxCntmax
     */
    public void setBoxCntmax(Integer boxCntmax) {
        this.boxCntmax = boxCntmax;
    }
}
