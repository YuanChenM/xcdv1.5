package com.msk.bs.bean;

import com.msk.core.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * 库存返回实体
 * Created by ni_shaotang on 2016/9/22.
 */
@ApiModel(value = "IBA2141107RsBean", description = "bean")
public class IBA2141107RsBean extends BaseEntity {

    @ApiModelProperty(value = "平台Code或者卖家买手ID")
    private String slCode;

    @ApiModelProperty(value = "物流区编码")
    private Integer lgcsCode;

    @ApiModelProperty(value = "物流区名称")
    private String lgcsName;

    @ApiModelProperty(value = "产品编码（10位）")
    private String pdCode;

    @ApiModelProperty(value = "产品名称")
    private String pdName;

    @ApiModelProperty(value = "产品库存")
    private BigDecimal stockCnt;

    public String getSlCode() {
        return slCode;
    }

    public void setSlCode(String slCode) {
        this.slCode = slCode;
    }

    public Integer getLgcsCode() {
        return lgcsCode;
    }

    public void setLgcsCode(Integer lgcsCode) {
        this.lgcsCode = lgcsCode;
    }

    public String getLgcsName() {
        return lgcsName;
    }

    public void setLgcsName(String lgcsName) {
        this.lgcsName = lgcsName;
    }

    public String getPdCode() {
        return pdCode;
    }

    public void setPdCode(String pdCode) {
        this.pdCode = pdCode;
    }

    public String getPdName() {
        return pdName;
    }

    public void setPdName(String pdName) {
        this.pdName = pdName;
    }

    public BigDecimal getStockCnt() {
        if (null == stockCnt) {
            stockCnt = BigDecimal.ZERO;
        }
        return stockCnt.setScale(0, BigDecimal.ROUND_HALF_UP);
    }

    public void setStockCnt(BigDecimal stockCnt) {
        this.stockCnt = stockCnt;
    }
}
