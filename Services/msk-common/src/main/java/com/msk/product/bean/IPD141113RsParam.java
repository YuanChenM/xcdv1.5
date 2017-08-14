package com.msk.product.bean;


import com.msk.common.bean.RsPageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * IPD141113RsParam.
 *
 * @author yuan_chen
 */
@ApiModel(value = "IPD141113RsParam", description = "param")
public class IPD141113RsParam extends RsPageParam {
    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "LGCS_CODE")
    private String lgcsCode;

    /**
     * Getter method for property <tt>lgcsCode</tt>.
     *
     * @return property value of lgcsCode
     */
    public String getLgcsCode() {
        return lgcsCode;
    }

    /**
     * Setter method for property <tt>lgcsCode</tt>.
     *
     * @param lgcsCode value to be assigned to property lgcsCode
     */
    public void setLgcsCode(String lgcsCode) {
        this.lgcsCode = lgcsCode;
    }
}
