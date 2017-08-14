package com.msk.inventory.service;

import com.msk.inventory.bean.ISO152408ParamBean;

import java.util.Date;

/**
 * Created by zheng_xu on 2016/8/25.
 */
public interface IISO152408Service {

    void updateDecreaseQty(ISO152408ParamBean paramBean, String allocatedBy, Date allocatedTime) throws Exception;
}
