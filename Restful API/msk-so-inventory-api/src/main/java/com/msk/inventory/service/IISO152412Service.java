package com.msk.inventory.service;

import com.msk.inventory.bean.ISO152412ParamBean;
import com.msk.inventory.bean.ISO152412PdStockResultBean;

import java.util.List;

/**
 * Created by duan_kai on 2016/8/25.
 */
public interface IISO152412Service {

    List<ISO152412PdStockResultBean> findSlProductIvList(ISO152412ParamBean sqlBean);
}
