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
@ApiModel(value = "IPD142001RsParam", description = "param")
@JsonIgnoreProperties(value = { "delFlg", "crtId", "crtTime", "updId", "updTime", "ver", "actId", "actTime" })
public class IPD142001RsParam extends BaseEntity {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "常量类型")
    private String constantType; //分CodeMaster和Status


    /**
     * Getter method for property <tt>constantType</tt>.
     *
     * @return property value of constantType
     */
    public String getConstantType() {
        return constantType;
    }

    /**
     * Setter method for property <tt>constantType</tt>.
     *
     * @param constantType value to be assigned to property constantType
     */
    public void setConstantType(String constantType) {
        this.constantType = constantType;
    }
}