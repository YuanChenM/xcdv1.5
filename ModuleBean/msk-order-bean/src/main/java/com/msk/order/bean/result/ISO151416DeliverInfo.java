package com.msk.order.bean.result;

import com.msk.common.bean.result.BaseResult;

import java.util.Date;

/**
 * Created by liu_tao2 on 2016/9/5.
 */
public class ISO151416DeliverInfo extends BaseResult {

    private Date operationDate;

    private String operationDescribe;

    public Date getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(Date operationDate) {
        this.operationDate = operationDate;
    }

    public String getOperationDescribe() {
        return operationDescribe;
    }

    public void setOperationDescribe(String operationDescribe) {
        this.operationDescribe = operationDescribe;
    }
}
