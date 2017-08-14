package com.msk.inventory.service;

import com.msk.inventory.bean.ISO152407ParamBean;

import java.util.Date;

/**
 * Created by wangfan on 2016/8/23.
 */
public interface IISO152407Service {

    void occupyOrder(ISO152407ParamBean sqlBean, String allocatedBy, Date allocatedTime) throws Exception;

}
