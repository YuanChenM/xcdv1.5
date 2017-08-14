package com.msk.order.bean.param;


import com.msk.common.bean.param.BaseParam;

import java.util.List;

/**
 * 创建退货单Param
 * 
 * @author zhangqiang1
 */
public class ISO151408RestShipListParam extends BaseParam {
    /** 发货单号ID */
    private Long shipId;
    /** 退货产品信息 */
    private List<ISO151408RestProductListParam> productList;


    public Long getShipId() {
        return shipId;
    }

    public void setShipId(Long shipId) {
        this.shipId = shipId;
    }


    public List<ISO151408RestProductListParam> getProductList() {
        return productList;
    }

    public void setProductList(List<ISO151408RestProductListParam> productList) {
        this.productList = productList;
    }
}
