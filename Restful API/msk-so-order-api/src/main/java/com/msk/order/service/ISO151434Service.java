package com.msk.order.service;

import com.msk.order.bean.param.ISO151434RestParam;
import com.msk.order.bean.result.ISO151434RestResult;

/**
 * ISO151434_获取上半月分销量接口
 * Created by chu_jian on 2016/8/3.
 */
public interface ISO151434Service {
    ISO151434RestResult getHalfMonthCount(ISO151434RestParam iso151434RestParam);
}
