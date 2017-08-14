package com.msk.order.service;

import com.msk.order.bean.param.ISO151405RestParam;
import com.msk.order.bean.result.ISO151405PdListRestResult;

import java.util.List;

/**
 * ISO151405_产品销量查询接口
 * Created by chu_jian on 2016/8/3.
 */
public interface ISO151405Service {

    List<ISO151405PdListRestResult> getPdSalesVolumn(ISO151405RestParam param);
}
