package com.msk.order.service;

import com.msk.order.bean.param.ISO151423RestParam;
import com.msk.order.bean.result.ISO151423RestResult;

/**
 * 卖家，买手，管家快捷信息查询接口SERVICE
 * Created by wang_jianzhou on 2016/8/22.
 */
public interface ISO151423Service {

    ISO151423RestResult getSellerResultInfo(ISO151423RestParam param, String type);
}
