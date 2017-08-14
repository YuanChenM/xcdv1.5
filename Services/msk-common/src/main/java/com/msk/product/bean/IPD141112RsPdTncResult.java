package com.msk.product.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.msk.core.entity.PdTncStd;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 封装参数
 * IPD141112RsPdTncParam.
 *
 * @author xhy
 */
@ApiModel(value = "IPD141112RsPdTncResult", description = "result")
@JsonIgnoreProperties(value = {"delFlg", "crtId", "crtTime", "updId", "updTime", "ver", "actId"})
public class IPD141112RsPdTncResult extends PdTncStd {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "分类指标名称")
    private String tncStdItemName;


    /**
     * 获得tncStdItemName
     */
    public String getTncStdItemName() {
        return tncStdItemName;
    }

    /**
     * 设置tncStdItemName
     */
    public void setTncStdItemName(String tncStdItemName) {
        this.tncStdItemName = tncStdItemName;
    }
}