package com.msk.bs.bean;

import com.msk.core.entity.SlBsBuyerLeagues;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by gyh on 2016/4/8.
 */
@ApiModel(value = "IBS210111001RsParam",description = "买手联盟List")
public class IBS210111001RsParam extends SlBsBuyerLeagues{
    @ApiModelProperty(value = "创建者ID/更新者ID")
    private String loginId;
    @ApiModelProperty(value = "履历ID")
    private Long hisId;

    /**
     * Getter method for property <tt>hisId</tt>.
     *
     * @return property value of hisId
     */
    public Long getHisId() {
        return hisId;
    }

    /**
     * Setter method for property <tt>hisId</tt>.
     *
     * @param hisId value to be assigned to property hisId
     */
    public void setHisId(Long hisId) {
        this.hisId = hisId;
    }

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
