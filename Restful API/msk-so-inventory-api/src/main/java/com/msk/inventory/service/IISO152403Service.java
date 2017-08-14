package com.msk.inventory.service;

import com.msk.inventory.bean.ISO152403ParamBean;
import com.msk.inventory.bean.ISO152403StockResultBean;

import java.util.List;

/**
 * Created by zheng_xu on 2016/8/24.
 */
public interface IISO152403Service {

    List<ISO152403StockResultBean> getIso152413StockList(ISO152403ParamBean restParam);

}
