package com.msk.bs.bean;

import com.msk.common.bean.RsPageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by gyh on 2016/4/12.
 */
@ApiModel(value = "IBS2101111RsParam",description = "param")
public class IBS2101111RsParam extends RsPageParam {
    @ApiModelProperty(value = "联盟主买手ID")
    private String ownerSlCode;
    @ApiModelProperty(value = "联盟方买手ID")
    private String allianceSlCode;

    /**
     * Getter method for property <tt>ownerSlCode</tt>.
     *
     * @return property value of ownerSlCode
     */
    public String getOwnerSlCode() {
        return ownerSlCode;
    }

    /**
     * Setter method for property <tt>ownerSlCode</tt>.
     *
     * @param ownerSlCode value to be assigned to property ownerSlCode
     */
    public void setOwnerSlCode(String ownerSlCode) {
        this.ownerSlCode = ownerSlCode;
    }

    /**
     * Getter method for property <tt>allianceSlCode</tt>.
     *
     * @return property value of allianceSlCode
     */
    public String getAllianceSlCode() {
        return allianceSlCode;
    }

    /**
     * Setter method for property <tt>allianceSlCode</tt>.
     *
     * @param allianceSlCode value to be assigned to property allianceSlCode
     */
    public void setAllianceSlCode(String allianceSlCode) {
        this.allianceSlCode = allianceSlCode;
    }
}
