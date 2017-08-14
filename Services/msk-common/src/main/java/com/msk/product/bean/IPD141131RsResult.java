package com.msk.product.bean;


import com.msk.common.bean.RsPageResult;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * IPD141128RsResult.产品品种接口查询
 *
 * @author xhy
 */
@ApiModel(value = "IPD141131RsResult", description = "result")
public class IPD141131RsResult extends RsPageResult {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "searchList")
    private List<IPD141131RsBean> searchList;


    /**
     * Getter method for property <tt>searchList</tt>.
     *
     * @return property value of searchList
     */
    public List<IPD141131RsBean> getSearchList() {
        return searchList;
    }

    /**
     * Setter method for property <tt>searchList</tt>.
     *
     * @param searchList value to be assigned to property searchList
     */
    public void setSearchList(List<IPD141131RsBean> searchList) {
        this.searchList = searchList;
    }
}