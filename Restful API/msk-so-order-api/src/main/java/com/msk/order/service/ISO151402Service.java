package com.msk.order.service;

import com.msk.common.bean.RestRequest;
import com.msk.order.bean.param.ISO151402RestParam;
import com.msk.order.bean.result.ISO151402RestResult;

/**
 * ISO151402_打印查询订单详细信息PDF接口
 * Created by chu_jian on 2016/8/3.
 */

public interface ISO151402Service {
    ISO151402RestResult selectSoProDetail(RestRequest<ISO151402RestParam> param);
}
