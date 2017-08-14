package com.msk.order.bean.result;

import com.msk.common.bean.result.BaseResult;

import java.util.Date;

/**
 * 
 * ISO151408RsResult.
 *
 * @author zhangqiang
 */
public class ISO151408RestResult extends BaseResult{
    /** 退货单ID */
    private Long returnId;
    /** 退货单编码 */
    private String returnCode;
    /** 退货单创建时间 */
    private Date crtTime;
    /** 退货单状态 */
    private Integer returnStatus;
    /** 退货单版本号 */
    private Integer ver;

    /**
     * 获得returnId
     */
    public Long getReturnId() {
        return returnId;
    }

    /**
     * 设置returnId
     */
    public void setReturnId(Long returnId) {
        this.returnId = returnId;
    }

    /**
     * Get the returnCode.
     *
     * @return returnCode
     *
     * @author Administrator
     */
    public String getReturnCode() {
        return this.returnCode;
    }

    /**
     * Set the returnCode.
     *
     * @param returnCode returnCode
     *
     * @author Administrator
     */
    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    /**
     * Get the crtTime.
     *
     * @return crtTime
     *
     * @author Administrator
     */
    public Date getCrtTime() {
        return this.crtTime;
    }

    /**
     * Set the crtTime.
     *
     * @param crtTime crtTime
     *
     * @author Administrator
     */
    public void setCrtTime(Date crtTime) {
        this.crtTime = crtTime;
    }

    /**
     * Get the returnStatus.
     *
     * @return returnStatus
     *
     * @author Administrator
     */
    public Integer getReturnStatus() {
        return this.returnStatus;
    }

    /**
     * Set the returnStatus.
     *
     * @param returnStatus returnStatus
     *
     * @author Administrator
     */
    public void setReturnStatus(Integer returnStatus) {
        this.returnStatus = returnStatus;
    }

    /**
     * Get the ver.
     *
     * @return ver
     *
     * @author Administrator
     */
    public Integer getVer() {
        return this.ver;
    }

    /**
     * Set the ver.
     *
     * @param ver ver
     *
     * @author Administrator
     */
    public void setVer(Integer ver) {
        this.ver = ver;
    }

}
