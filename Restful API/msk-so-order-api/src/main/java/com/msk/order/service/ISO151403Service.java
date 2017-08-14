package com.msk.order.service;

import com.msk.order.bean.param.ISO151403RestParam;
import com.msk.order.bean.result.ISO151403SupplierRestResult;

import java.util.List;

/**
 * ISO151403_查询供应商列表接口
 * Created by chu_jian on 2016/8/3.
 */
public interface ISO151403Service {

    List<ISO151403SupplierRestResult> findSellers(ISO151403RestParam param);
}
