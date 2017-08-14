package com.msk.order.bean.param;

import com.msk.common.annotation.valid.Required;
import com.msk.common.bean.param.BaseParam;

import java.util.List;

/**
 * ISO151802_现场退货数据接收接口
 * Created by chu_jian on 2016/8/3.
 */
public class ISO151802RestShipParam extends BaseParam {
    private String deliverCode;
    private List<ISO151802RestShipDetailParam> productList;
    private Long returnId;
    private String returnCode;

    public Long getReturnId()
    {
        return returnId;
    }

    public void setReturnId(Long returnId)
    {
        this.returnId = returnId;
    }

    public String getReturnCode()
    {
        return returnCode;
    }

    public void setReturnCode(String returnCode)
    {
        this.returnCode = returnCode;
    }

    public String getDeliverCode() {
        return deliverCode;
    }

    public void setDeliverCode(String deliverCode) {
        this.deliverCode = deliverCode;
    }

    public List<ISO151802RestShipDetailParam> getProductList() {
        return productList;
    }

    public void setProductList(List<ISO151802RestShipDetailParam> productList) {
        this.productList = productList;
    }
}
