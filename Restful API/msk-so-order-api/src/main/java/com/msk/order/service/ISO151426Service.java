package com.msk.order.service;

import com.msk.order.bean.param.ISO151426RestParam;
import com.msk.order.bean.result.ISO151426RestResult;

/**
 * Created by wang_shuai on 2016/8/22.
 */
public interface ISO151426Service {
    ISO151426RestResult findPageResult(ISO151426RestParam param);
}
