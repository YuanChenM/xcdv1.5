package com.msk.inventory.service;

import java.util.List;

import com.msk.inventory.bean.ISO152413PdStockResultBean;
import com.msk.inventory.bean.ISO152413ParamBean;

/**
 * Created by wangfan on 2016/8/23.
 */
public interface IISO152413Service {

    List<ISO152413PdStockResultBean> findSlProductIvList(ISO152413ParamBean sqlBean);

}
