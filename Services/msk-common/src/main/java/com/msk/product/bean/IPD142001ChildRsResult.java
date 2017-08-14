package com.msk.product.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.msk.core.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 销售状态一览对象
 * IPD141103RsParam.
 *
 * @author xhy
 */
@ApiModel(value = "IPD142001ChildRsResult", description = "commList")
@JsonIgnoreProperties(value = { "delFlg", "crtId", "crtTime", "updId", "updTime", "ver", "actId", "actTime" })
public class IPD142001ChildRsResult extends BaseEntity {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "常量类型值")
    private String constantValue;
    @ApiModelProperty(value = "常量类型名称")
    private String constantName;

    /**
     * Getter method for property <tt>constantName</tt>.
     *
     * @return property value of constantName
     */
    public String getConstantName() {
        return constantName;
    }

    /**
     * Setter method for property <tt>constantName</tt>.
     *
     * @param constantName value to be assigned to property constantName
     */
    public void setConstantName(String constantName) {
        this.constantName = constantName;
    }

    /**
     * Getter method for property <tt>constantValue</tt>.
     *
     * @return property value of constantValue
     */
    public String getConstantValue() {
        return constantValue;
    }

    /**
     * Setter method for property <tt>constantValue</tt>.
     *
     * @param constantValue value to be assigned to property constantValue
     */
    public void setConstantValue(String constantValue) {
        this.constantValue = constantValue;
    }
}