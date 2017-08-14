package com.msk.common.bean;


import com.hoperun.core.utils.DateTimeUtil;
import com.msk.common.base.BaseBean;

import java.util.Date;

/**
 * 库存Bean.
 * 
 * @author yuan_chen
 */
public class PriceCycle extends BaseBean {
    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    /** 价盘周期开始日期 */
    private Date startDate;
    /** 价盘周期结束日期 */
    private Date endDate;
    /** 价盘周期开始日期 (format: yyyyMMdd) */
    private String startDateStr;
    /** 价盘周期结束日期 (format: yyyyMMdd) */
    private String endDateStr;
    /** 价盘周期编号(共5位: 年(2位) + 月(2位) + 半旬号(1位)) */
    private String cycleCode;
    /** 价盘周期名称(format: 开始日期-结束日期) */
    private String cycleName;
    /** 价盘周期中的第几天 */
    private int dayOfCycle;
    /**
     * 价盘周期(第几期)
     */
    private int dayAmount;
    
    /**
     * Get the startDate.
     *
     * @return startDate
     *
     * @author administator
     */
    public Date getStartDate() {
        return this.startDate;
    }

    /**
     * Set the startDate.
     *
     * @param startDate startDate
     *
     * @author administator
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
        this.startDateStr = DateTimeUtil.formatDate(DateTimeUtil.FORMAT_YYYYMMDD, startDate);
    }

    /**
     * Get the endDate.
     *
     * @return endDate
     *
     * @author administator
     */
    public Date getEndDate() {
        return this.endDate;
    }

    /**
     * Set the endDate.
     *
     * @param endDate endDate
     *
     * @author administator
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
        this.endDateStr = DateTimeUtil.formatDate(DateTimeUtil.FORMAT_YYYYMMDD, endDate);
    }

    /**
     * Get the cycleCode.
     *
     * @return cycleCode
     *
     * @author administator
     */
    public String getCycleCode() {
        return this.cycleCode;
    }

    /**
     * Set the cycleCode.
     *
     * @param cycleCode cycleCode
     *
     * @author administator
     */
    public void setCycleCode(String cycleCode) {
        this.cycleCode = cycleCode;
    }

    /**
     * Get the cycleName.
     *
     * @return cycleName
     *
     * @author administator
     */
    public String getCycleName() {
        this.cycleName = this.startDateStr + "-" + this.endDateStr;
        return this.cycleName;
    }

    /**
     * Get the startDateStr.
     *
     * @return startDateStr
     *
     * @author administator
     */
    public String getStartDateStr() {
        return this.startDateStr;
    }

    /**
     * Get the endDateStr.
     *
     * @return endDateStr
     *
     * @author administator
     */
    public String getEndDateStr() {
        return this.endDateStr;
    }

    /**
     * Getter method for property <tt>dayOfCycle</tt>.
     *
     * @return property value of dayOfCycle
     */
    public int getDayOfCycle() {
        return dayOfCycle;
    }

    /**
     * Setter method for property <tt>dayOfCycle</tt>.
     *
     * @param dayOfCycle value to be assigned to property dayOfCycle
     */
    public void setDayOfCycle(int dayOfCycle) {
        this.dayOfCycle = dayOfCycle;
    }

    public int getDayAmount() {
        return dayAmount;
    }

    public void setDayAmount(int dayAmount) {
        this.dayAmount = dayAmount;
    }
}
