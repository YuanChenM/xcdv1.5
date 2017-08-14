package com.msk.order.service;

import com.msk.common.bean.RestRequest;
import com.msk.order.bean.param.ISO151404RsParam;
import com.msk.order.bean.result.ISO151404RsResult;

/**
 * ISO151404_验证退货单号接口
 * Created by chu_jian on 2016/8/3.
 */
public interface ISO151404Service {

    ISO151404RsResult selectCount(RestRequest<ISO151404RsParam> param);
}
