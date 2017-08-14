package com.msk.product.bean;


import com.msk.common.bean.RsPageResult;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 
 * IPD141111RsResult.产品查询价盘
 *
 * @author zhou_ling
 */
@ApiModel(value = "IPD141112RsResult", description = "result")
public class IPD141112RsResult extends RsPageResult {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "产品信息列表")
    private List<IPD141112RsPdClaSubItemResult> pdList;


    /**
     * 获得pdList
     */
    public List<IPD141112RsPdClaSubItemResult> getPdList() {
        return pdList;
    }

    /**
     * 设置pdList
     */
    public void setPdList(List<IPD141112RsPdClaSubItemResult> pdList) {
        this.pdList = pdList;
    }
}