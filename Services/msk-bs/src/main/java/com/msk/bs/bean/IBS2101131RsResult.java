package com.msk.bs.bean;

import com.msk.core.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by gao_min on 2016/10/12.
 */
@ApiModel(value = "IBS2101131RsResult", description = "result")
public class IBS2101131RsResult extends BaseEntity {


    @ApiModelProperty(value = "管家服务心得")
    private List<IBS2101131RsBean> housekeeperList;

    public List<IBS2101131RsBean> getHousekeeperList() {
        return housekeeperList;
    }

    public void setHousekeeperList(List<IBS2101131RsBean> housekeeperList) {
        this.housekeeperList = housekeeperList;
    }
}
