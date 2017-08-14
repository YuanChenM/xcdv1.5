package com.msk.product.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.msk.core.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 查询产品主码 返回参数
 * IPD141105RsResult.
 *
 * @author xhy
 */
@ApiModel(value = "IPD141104RsResult", description = "查询产品主码 返回参数")
@JsonIgnoreProperties(value = { "delFlg", "crtId", "crtTime", "updId", "updTime", "ver", "actId", "actTime", "standardId" })
public class IPD141104RsResult extends BaseEntity {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "产品加工程度编码")
    private String machiningCode;
    @ApiModelProperty(value = "产品加工程度名称")
    private String machiningName;

    /**
     * Get the machiningCode.
     *
     * @return machiningCode
     *
     * @author xhy
     */
    public String getMachiningCode() {
        return this.machiningCode;
    }

    /**
     * Set the machiningCode.
     *
     * @param machiningCode machiningCode
     *
     * @author xhy
     */
    public void setMachiningCode(String machiningCode) {
        this.machiningCode = machiningCode;
    }

    /**
     * Get the machiningName.
     *
     * @return machiningName
     *
     * @author xhy
     */
    public String getMachiningName() {
        return this.machiningName;
    }

    /**
     * Set the machiningName.
     *
     * @param machiningName machiningName
     *
     * @author xhy
     */
    public void setMachiningName(String machiningName) {
        this.machiningName = machiningName;
    }
}