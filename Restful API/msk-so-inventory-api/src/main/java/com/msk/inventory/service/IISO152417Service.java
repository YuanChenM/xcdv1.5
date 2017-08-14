package com.msk.inventory.service;

import com.msk.inventory.bean.ISO152417ParamBean;
import com.msk.inventory.bean.ISO152417SupplierBean;

import java.util.List;

/**
 * Created by zheng_xu on 2016/9/12.
 */
public interface IISO152417Service {

    List<ISO152417SupplierBean> getOwnersInHistory(ISO152417ParamBean paramBean);
}
