package com.msk.product.bean;


import com.msk.common.bean.RsPageResult;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * IPD141141RsResult.
 *
 * @author xhy
 */
@ApiModel(value = "IPD141141RsResult", description = "result")
public class IPD141141RsResult extends RsPageResult {
    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "物流区产品信息列表")
    List<LogiAreaPdBean> logiAreaList;

    /**
     * Getter method for property <tt>logiAreaList</tt>.
     *
     * @return property value of logiAreaList
     */
    public List<LogiAreaPdBean> getLogiAreaList() {
        return logiAreaList;
    }

    /**
     * Setter method for property <tt>logiAreaList</tt>.
     *
     * @param logiAreaList value to be assigned to property logiAreaList
     */
    public void setLogiAreaList(List<LogiAreaPdBean> logiAreaList) {
        this.logiAreaList = logiAreaList;
    }
}
