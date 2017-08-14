package com.msk.order.bean.param;


import com.msk.common.annotation.valid.Required;
import com.msk.common.bean.param.BaseParam;

/**
 * 订单删除/恢复Param
 *
 * @author zhangqiang1
 */
public class ISO151412RestParam extends BaseParam {
    @Required(required=true,message="订单ID不能为空")
    private Long orderId;//订单ID
    @Required(required=true,message="订单版本号不能为空")
    private Integer ver;//订单版本号
    @Required(required=true,message="操作类型不能为空")
    private Integer operateType;//操作类型,0:删除,1:恢复,2:彻底删除


    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    @Override
    public Integer getVer() {
        return ver;
    }

    @Override
    public void setVer(Integer ver) {
        this.ver = ver;
    }

    public Integer getOperateType() {
        return operateType;
    }

    public void setOperateType(Integer operateType) {
        this.operateType = operateType;
    }
}
