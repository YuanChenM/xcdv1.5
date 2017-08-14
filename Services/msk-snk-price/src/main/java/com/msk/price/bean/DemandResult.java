package com.msk.price.bean;

import com.msk.common.base.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * DemandResult
 *
 * @author yang_yang
 * @version 1.0
 **/
@ApiModel(value = "DemandResult<T>",description = "result")
public class DemandResult<T> extends BaseBean {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "物流区编号")
    private String areaCode;

    @ApiModelProperty(value = " 结果集")
    private List<T> supList;

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public List<T> getSupList() {
        return supList;
    }

    public void setSupList(List<T> supList) {
        this.supList = supList;
    }
}
