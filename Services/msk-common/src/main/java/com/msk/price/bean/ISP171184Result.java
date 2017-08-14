package com.msk.price.bean;

import com.msk.core.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by peng_hao on 2016/6/8.
 */
@ApiModel(value = "ISP171184Result", description = "价盘通道rsp")
public class ISP171184Result extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "查询结果总数")
    private int totalCount;

    @ApiModelProperty(value = "查询结果总页数")
    private int totalPage;

    @ApiModelProperty(value = "查询结果当前页数")
    private int pageNo;

    @ApiModelProperty(value = "searchList")
    private List<ISP171184Bean> searchList;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public List<ISP171184Bean> getSearchList() {
        return searchList;
    }

    public void setSearchList(List<ISP171184Bean> searchList) {
        this.searchList = searchList;
    }
}
