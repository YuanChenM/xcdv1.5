package com.msk.ds.bean;

import com.msk.common.bean.RsPageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * xu_wei on 2016/03/24.
 */
@ApiModel(value = "ISC1892I1Param" ,description = "扫码入库虚拟库存变更")
public class ISC1892I1Param extends RsPageParam {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 发货入库管理ID
     */
    @ApiModelProperty(value = "发货入库管理ID")
    private Long deliveryStockId;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String stockMemo;
    /**
     * 实际入库时间
     */
    @ApiModelProperty(value = "实际入库时间")
    private String inputDate;

    /** liuyan 临时使用 后期会删*/
    private String tempProductList;

    /**部分发货或者部分收货对应的产品信息*/
    @ApiModelProperty(value = "部分发货或者部分收货对应的产品信息")
    private List<ISC1892I1RsParam> productList;

    public Long getDeliveryStockId() {
        return deliveryStockId;
    }

    public void setDeliveryStockId(Long deliveryStockId) {
        this.deliveryStockId = deliveryStockId;
    }

    public String getStockMemo() {
        return stockMemo;
    }

    public void setStockMemo(String stockMemo) {
        this.stockMemo = stockMemo;
    }

    public String getInputDate() {
        return inputDate;
    }

    public void setInputDate(String inputDate) {
        this.inputDate = inputDate;
    }

    public List<ISC1892I1RsParam> getProductList() {
        return productList;
    }

    public void setProductList(List<ISC1892I1RsParam> productList) {
        this.productList = productList;
    }

    public String getTempProductList() {
        return tempProductList;
    }

    public void setTempProductList(String tempProductList) {
        this.tempProductList = tempProductList;
    }
}
