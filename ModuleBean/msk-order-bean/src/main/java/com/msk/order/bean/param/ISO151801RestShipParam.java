package com.msk.order.bean.param;

import com.msk.common.bean.param.BaseParam;

import java.util.List;

/**
 * ISO151801_迟收退货数据接收接口
 * Created by chu_jian on 2016/8/2.
 */
public class ISO151801RestShipParam extends BaseParam {
    private String deliverCode;
    private List<ISO151801RestProductParam> productList;

    public String getDeliverCode() {
        return deliverCode;
    }

    public void setDeliverCode(String deliverCode) {
        this.deliverCode = deliverCode;
    }

    public List<ISO151801RestProductParam> getProductList() {
        return productList;
    }

    public void setProductList(List<ISO151801RestProductParam> productList) {
        this.productList = productList;
    }
}
