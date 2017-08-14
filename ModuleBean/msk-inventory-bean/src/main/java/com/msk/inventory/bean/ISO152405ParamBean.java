package com.msk.inventory.bean;

import org.springframework.util.StringUtils;

import com.msk.inventory.entity.IvmInventoryDetail;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by wangfan on 16/8/22.
 */
public class ISO152405ParamBean {

    /** 入库产品列表信息 */
    private List<ISO152405InvParamBean> invList;

    public List<ISO152405InvParamBean> getInvList() {
        return invList;
    }

    public void setInvList(List<ISO152405InvParamBean> invList) {
        this.invList = invList;
    }
}
