package com.msk.seller.bean;

import com.msk.core.entity.BaseEntity;
import com.msk.core.entity.SlSeller;

import java.util.List;

/**
 * Created by Administrator on 2016/2/15.
 */
public class ISL231122RsResult extends BaseEntity{
    /** 状态 */
    private String status;
    /** 返回代码 */
    private String returnCode;
    /** 结果消息 */
    private String message;

    /** 卖家基本信息列表 */
    private List<SlSeller> slSellerList;

    public List<SlSeller> getSlSellerList() {
        return slSellerList;
    }

    public void setSlSellerList(List<SlSeller> slSellerList) {
        this.slSellerList = slSellerList;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
