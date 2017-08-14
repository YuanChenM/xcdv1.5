package com.msk.common.bean;

import com.msk.core.entity.BaseEntity;

import java.util.Date;

/**
 * 买家状态变更周期Bean（半旬）
 *
 * Created by zhang_jian4 on 2016/10/10.
 */
public class ChangeCycle extends BaseEntity {
    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    /** 变更周期开始日期 */
    private Date startDate;
    /** 变更周期结束日期 */
    private Date endDate;
    /** 变更周期开始日期 (format: yyyyMMdd) */
    private String startDateStr;
    /** 变更周期结束日期 (format: yyyyMMdd) */
    private String endDateStr;

    /** 变更周期名称(format: 开始日期-结束日期) */
    private String cycleName;

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getStartDateStr() {
        return startDateStr;
    }

    public void setStartDateStr(String startDateStr) {
        this.startDateStr = startDateStr;
    }

    public String getEndDateStr() {
        return endDateStr;
    }

    public void setEndDateStr(String endDateStr) {
        this.endDateStr = endDateStr;
    }

    public String getCycleName() {
        return cycleName;
    }

    public void setCycleName(String cycleName) {
        this.cycleName = cycleName;
    }
}
