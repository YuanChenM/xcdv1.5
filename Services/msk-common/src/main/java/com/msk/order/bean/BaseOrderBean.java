package com.msk.order.bean;

import com.msk.common.base.BaseBean;
import com.msk.core.entity.SoOrder;
import com.msk.core.entity.SoOrderDetail;

import java.util.List;

/**
 * BaseOrderBean
 *
 * @author jiang_nan
 * @version 1.0
 **/
public class BaseOrderBean extends BaseBean {
    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    /** 订单基本信息 */
    private SoOrder soOrder;

    /** 订单详细信息列表 */
    private List<SoOrderDetail> soOrderDetails;

    /**
     * Getter method for property <tt>soOrder</tt>.
     *
     * @return property value of soOrder
     */
    public SoOrder getSoOrder() {
        return soOrder;
    }

    /**
     * Setter method for property <tt>soOrder</tt>.
     *
     * @param soOrder value to be assigned to property soOrder
     */
    public void setSoOrder(SoOrder soOrder) {
        this.soOrder = soOrder;
    }

    /**
     * Getter method for property <tt>soOrderDetails</tt>.
     *
     * @return property value of soOrderDetails
     */
    public List<SoOrderDetail> getSoOrderDetails() {
        return soOrderDetails;
    }

    /**
     * Setter method for property <tt>soOrderDetails</tt>.
     *
     * @param soOrderDetails value to be assigned to property soOrderDetails
     */
    public void setSoOrderDetails(List<SoOrderDetail> soOrderDetails) {
        this.soOrderDetails = soOrderDetails;
    }
}
