package com.msk.product.bean;


import com.msk.common.bean.RsPageResult;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * IPD141143RsResult.神农客价盘通道同步接口
 *
 * @author xhy 2016-4-8
 */
@ApiModel(value = "IPD141143RsResult", description = "result")
public class IPD141143RsResult extends RsPageResult {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "searchList")
    private List<IPD141143Orders> searchList;

    /**
     * Getter method for property <tt>searchList</tt>.
     *
     * @return property value of searchList
     */
    public List<IPD141143Orders> getSearchList() {
        return searchList;
    }

    /**
     * Setter method for property <tt>searchList</tt>.
     *
     * @param searchList value to be assigned to property searchList
     */
    public void setSearchList(List<IPD141143Orders> searchList) {
        this.searchList = searchList;
    }
}