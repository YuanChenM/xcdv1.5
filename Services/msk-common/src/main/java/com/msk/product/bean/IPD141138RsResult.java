package com.msk.product.bean;

import com.msk.core.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * IPD141129RsResult.产品特征接口查询
 *
 * @author xhy
 */
@ApiModel(value = "IPD141138RsResult", description = "result")
public class IPD141138RsResult extends BaseEntity {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "产品净重编码")
    private String weightCode;
    @ApiModelProperty(value = "产品净重名称")
    private String weightName;

    /**
     * Getter method for property <tt>weightCode</tt>.
     *
     * @return property value of weightCode
     */
    public String getWeightCode() {
        return weightCode;
    }

    /**
     * Setter method for property <tt>weightCode</tt>.
     *
     * @param weightCode value to be assigned to property weightCode
     */
    public void setWeightCode(String weightCode) {
        this.weightCode = weightCode;
    }

    /**
     * Getter method for property <tt>weightName</tt>.
     *
     * @return property value of weightName
     */
    public String getWeightName() {
        return weightName;
    }

    /**
     * Setter method for property <tt>weightName</tt>.
     *
     * @param weightName value to be assigned to property weightName
     */
    public void setWeightName(String weightName) {
        this.weightName = weightName;
    }
}