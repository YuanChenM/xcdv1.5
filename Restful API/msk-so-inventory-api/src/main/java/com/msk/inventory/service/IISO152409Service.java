package com.msk.inventory.service;

import com.msk.inventory.bean.ISO152409ParamBean;

import java.util.Date;

/**
 * Created by wangfan on 2016/8/23.
 */
public interface IISO152409Service {

    void occupyOrder(ISO152409ParamBean sqlBean, String allocatedBy, Date allocatedTime) throws Exception;

}
