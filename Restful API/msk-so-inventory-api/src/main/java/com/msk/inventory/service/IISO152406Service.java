package com.msk.inventory.service;

import com.msk.inventory.bean.ISO152406ParamBean;

import java.util.Date;

/**
 * Created by zheng_xu on 2016/8/25.
 */
public interface IISO152406Service {

    void getOrderIdOfOccupy(ISO152406ParamBean paramBean, String allocatedBy, Date allocatedTime) throws Exception;
}
