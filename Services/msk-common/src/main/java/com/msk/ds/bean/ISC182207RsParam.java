package com.msk.ds.bean;

import com.hoperun.core.bean.BaseParam;
import com.msk.core.entity.DsDeliveryStock;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by li_kai1 on 2016/7/4.
 */
@ApiModel(value = "ISC182207RsParam" ,description = "生成美迪福接口xml文件接口")
public class ISC182207RsParam extends DsDeliveryStock {
    /** 入库单号*/
    @ApiModelProperty(value = "入库单号")
    Long receipt;
    /** 供应商名称*/
    @ApiModelProperty(value = "供应商名称")
    String suppName;
    /** 计划发货时间*/
    @ApiModelProperty(value = "计划发货时间")
    String scheduledDate;
    /** 发货详细 */
    @ApiModelProperty(value = "发货详细")
    private List<ISC182207DetailRsParam> productList;

    public String getSuppName() {
        return suppName;
    }

    public void setSuppName(String suppName) {
        this.suppName = suppName;
    }

    public String getScheduledDate() {
        return scheduledDate;
    }

    public void setScheduledDate(String scheduledDate) {
        this.scheduledDate = scheduledDate;
    }

    public List<ISC182207DetailRsParam> getProductList() {
        return productList;
    }

    public void setProductList(List<ISC182207DetailRsParam> productList) {
        this.productList = productList;
    }

    public Long getReceipt() {
        return receipt;
    }

    public void setReceipt(Long receipt) {
        this.receipt = receipt;
    }
}
