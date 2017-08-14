package com.msk.order.bean.param;

import com.msk.common.annotation.valid.Required;
import com.msk.common.bean.param.BasePageParam;
import com.msk.common.bean.param.BaseParam;

import java.math.BigDecimal;

/**
 * 订单状态变更WebService接口参数
 * *@author zhangqiang1
 * *@version 1.0
 */
public class ISO151410RestPram extends BaseParam {
    /**
     * 订单ID
     */
    @Required(required = true, message = "订单id不能为空")
    private Long orderId;

    @Required(required = true, message = "订单版本号不能为空")
    private Integer ver;

    /**
     * 1.买家取消 2.不同意拼货的取消 3.不同意分批的取消神农客网站调用时默认是1.买家取消，CallCenter调用时有不同选择。
     */
    @Required(required = true, message = "订单取消类型不能为空")
    private Integer cancelType;
    /**
     * 取消原因
     */
    private String cancelReason;
    /**
     * 订单类型
     */
    @Required(required = true, message = "订单类型不能为空")
    private Integer orderType;


    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }


    public Integer getCancelType() {
        return cancelType;
    }

    public void setCancelType(Integer cancelType) {
        this.cancelType = cancelType;
    }

    public String getCancelReason() {
        return cancelReason;
    }

    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }


    @Override
    public Integer getVer() {
        return ver;
    }

    @Override
    public void setVer(Integer ver) {
        this.ver = ver;
    }
}
