package com.msk.bs.bean;

import com.msk.core.entity.SlHouseProduct;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "IBS2101104SlHouseProduct",description = "冻品管家产品分类list")
public class IBS2101104SlHouseProduct extends SlHouseProduct {

    @ApiModelProperty(value = "登陆者id")
    private String loginId;

    /**
     * Getter method for property <tt>loginId</tt>.
     *
     * @return property value of loginId
     */
    public String getLoginId() {
        return loginId;
    }

    /**
     * Setter method for property <tt>loginId</tt>.
     *
     * @param loginId value to be assigned to property loginId
     */
    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }
}
