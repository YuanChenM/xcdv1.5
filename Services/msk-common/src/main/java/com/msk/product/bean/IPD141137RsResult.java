package com.msk.product.bean;


import com.msk.common.bean.RsPageResult;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * IPD141137RsResult.
 *
 * @author xhy
 */
@ApiModel(value = "IPD141137RsResult", description = "result")
public class IPD141137RsResult extends RsPageResult {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "searchList")
    private List<IPD141137RsBean> searchList;

    /**
     * Getter method for property <tt>searchList</tt>.
     *
     * @return property value of searchList
     */
    public List<IPD141137RsBean> getSearchList() {
        return searchList;
    }

    /**
     * Setter method for property <tt>searchList</tt>.
     *
     * @param searchList value to be assigned to property searchList
     */
    public void setSearchList(List<IPD141137RsBean> searchList) {
        this.searchList = searchList;
    }
}
