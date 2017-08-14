package com.msk.inventory.service;

import com.msk.inventory.bean.ISO152415ParamBean;

import java.util.Date;

/**
 * Created by zheng_xu on 2016/9/7.
 */
public interface IISO152415Service {

    void updateBuyerStockpile(ISO152415ParamBean sqlBean,String stockpileBy, Date stockpileTime) throws Exception;
}
