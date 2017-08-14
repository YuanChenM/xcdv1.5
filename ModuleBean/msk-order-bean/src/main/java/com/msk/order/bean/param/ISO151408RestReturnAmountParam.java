package com.msk.order.bean.param;


import com.msk.common.entity.BaseEntity;

import java.math.BigDecimal;

/**
 * 创建退货单Bean
 * ISO151408ReturnAmountRsParam
 *
 * @author zhang_qiang1
 */
public class ISO151408RestReturnAmountParam extends BaseEntity {
    /**
     * 退货金额
     */
    private BigDecimal returnAmount;

    public BigDecimal getReturnAmount() {
        return returnAmount;
    }

    public void setReturnAmount(BigDecimal returnAmount) {
        this.returnAmount = returnAmount;
    }
}
