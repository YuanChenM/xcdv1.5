package com.msk.ds.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by wangfan on 16/8/22.
 */
@ApiModel(value = "ISO152405ParamBean",
    description = "库存产品入库接口信息")
public class ISO152405ParamBean {

    @ApiModelProperty(value = "入库产品列表信息")
    private List<ISO152405InvParamBean> invList;

    public List<ISO152405InvParamBean> getInvList() {
        return invList;
    }

    public void setInvList(List<ISO152405InvParamBean> invList) {
        this.invList = invList;
    }
}
