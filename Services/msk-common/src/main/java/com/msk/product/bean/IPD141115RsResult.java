package com.msk.product.bean;


import com.msk.common.bean.RsPageResult;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 产品信息接口
 * Created by Administrator on 2016/1/26.
 */
@ApiModel(value = "IPD141115RsResult", description = "result")
public class IPD141115RsResult extends RsPageResult {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "产品信息列表")
    private List<IPD141115PdInfoRsResult> pdList;

    /**
     * Getter method for property <tt>pdList</tt>.
     *
     * @return property value of pdList
     */
    public List<IPD141115PdInfoRsResult> getPdList() {
        return pdList;
    }

    /**
     * Setter method for property <tt>pdList</tt>.
     *
     * @param pdList value to be assigned to property pdList
     */
    public void setPdList(List<IPD141115PdInfoRsResult> pdList) {
        this.pdList = pdList;
    }
}
