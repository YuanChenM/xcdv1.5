package com.msk.bs.bean;

import com.msk.core.entity.SlHouseArea;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "IBS2101104SlHouseArea",description = "经营区域List")
public class IBS2101104SlHouseArea extends  SlHouseArea{

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
