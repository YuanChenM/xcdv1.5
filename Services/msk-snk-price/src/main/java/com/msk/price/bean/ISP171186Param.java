package com.msk.price.bean;

import com.hoperun.core.bean.BaseParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by ren_qiang on 2016/9/14.
 */
@ApiModel(value = "ISP171186Param", description = "供应商信息req")
public class ISP171186Param extends BaseParam {

    @ApiModelProperty(value = "价盘周期（格式：yyMM1）")
    private String pricePeriod;

    @ApiModelProperty(value = "物流区编号")
    private String lgcsCode;

    @ApiModelProperty(value = "供应商code")
    private String supplierCode;

    @ApiModelProperty(value = "产品编码")
    private String pdCode;

    @ApiModelProperty(value = "分销通道(即价盘等级：1:标准订单,2:大额订单,3:大宗订单,4:超级大宗订单)")
    private String sellWayCode;

    public String getPricePeriod() {
        return pricePeriod;
    }

    public void setPricePeriod(String pricePeriod) {
        this.pricePeriod = pricePeriod;
    }

    public String getLgcsCode() {
        return lgcsCode;
    }

    public void setLgcsCode(String lgcsCode) {
        this.lgcsCode = lgcsCode;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getPdCode() {
        return pdCode;
    }

    public void setPdCode(String pdCode) {
        this.pdCode = pdCode;
    }

    public String getSellWayCode() {
        return sellWayCode;
    }

    public void setSellWayCode(String sellWayCode) {
        this.sellWayCode = sellWayCode;
    }
}
