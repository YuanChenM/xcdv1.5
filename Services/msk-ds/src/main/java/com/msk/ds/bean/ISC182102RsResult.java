package com.msk.ds.bean;





import com.msk.common.base.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by li_kai1 on 2016/8/5.
 */
@ApiModel(value = "ISC182102RsResult" ,description = "发货入库明细PDF打印")
public class ISC182102RsResult extends BaseBean {
    private static final long serialVersionUID=1L;

    /** 发货入库页面信息 */
    @ApiModelProperty(value = "发货入库页面信息")
    private SC182101Bean sc182102Info;

    /** 发货入库单明细list */
    @ApiModelProperty(value = "发货入库单明细list")
    private List<SC182102Param> detailList;


    public SC182101Bean getSc182102Info() {
        return sc182102Info;
    }

    public void setSc182102Info(SC182101Bean sc182102Info) {
        this.sc182102Info = sc182102Info;
    }

    public List<SC182102Param> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<SC182102Param> detailList) {
        this.detailList = detailList;
    }
}
