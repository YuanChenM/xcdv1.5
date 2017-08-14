package com.msk.order.service;

import com.msk.order.bean.param.ISO151422RestParam;
import com.msk.order.bean.result.ISO151422RestResult;

/**
 * ISO151422_退货入库
 * Created by wang_jianzhou on 2016/8/17.
 */
public interface ISO151422Service {

    ISO151422RestResult doInbound(ISO151422RestParam param);
}
