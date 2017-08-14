package com.msk.ds.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 发货入库PDF打印param
 * Created by li_kai1 on 2016/8/5.
 */
@ApiModel(value = "ISC182102Param" ,description = "发货入库明细PDF打印")
public class ISC182102Param {
    private static final long serialVersionUID = 1L;
    /** 库存id */
    @ApiModelProperty(value = "库存id")
    private String deliveryStockId;
    /** 发货入库明细页面来源 */
    @ApiModelProperty(value = "发货入库明细页面来源")
    private Boolean isEdit;
    /** 发货入库来源标识 */
    @ApiModelProperty(value = "发货入库来源标识")
    private String sourceFlg;

    public String getDeliveryStockId() {
        return deliveryStockId;
    }

    public void setDeliveryStockId(String deliveryStockId) {
        this.deliveryStockId = deliveryStockId;
    }

    public Boolean getEdit() {
        return isEdit;
    }

    public void setEdit(Boolean edit) {
        isEdit = edit;
    }

    public String getSourceFlg() {
        return sourceFlg;
    }

    public void setSourceFlg(String sourceFlg) {
        this.sourceFlg = sourceFlg;
    }
}
