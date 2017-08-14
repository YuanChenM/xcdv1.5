package com.msk.product.bean;


import com.msk.common.bean.RsPageResult;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * IPD141140RsResult.原料种源信息同步接口
 *
 * @author xhy
 */
@ApiModel(value = "IPD141140RsResult", description = "result")
public class IPD141140RsResult extends RsPageResult {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "searchList")
    private List<IPD141140RsBean> searchList;

    /**
     * Getter method for property <tt>searchList</tt>.
     *
     * @return property value of searchList
     */
    public List<IPD141140RsBean> getSearchList() {
        return searchList;
    }

    /**
     * Setter method for property <tt>searchList</tt>.
     *
     * @param searchList value to be assigned to property searchList
     */
    public void setSearchList(List<IPD141140RsBean> searchList) {
        this.searchList = searchList;
    }
}