package com.msk.order.bean.result;

import com.msk.common.bean.result.BaseResult;

/**
 * ISO151801_迟收退货数据接收接口
 * Created by chu_jian on 2016/8/2.
 */
public class ISO151801RestResult extends BaseResult {
    private Long returnId;
    private String returnCode;
    private String createTime;
    private Integer returnStatus;
    private Integer ver;

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getReturnStatus() {
        return returnStatus;
    }

    public void setReturnStatus(Integer returnStatus) {
        this.returnStatus = returnStatus;
    }

    public Integer getVer() {
        return ver;
    }

    public void setVer(Integer ver) {
        this.ver = ver;
    }

    public Long getReturnId() {
        return returnId;
    }

    public void setReturnId(Long returnId) {
        this.returnId = returnId;
    }
}
