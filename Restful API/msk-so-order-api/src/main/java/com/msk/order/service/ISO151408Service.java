package com.msk.order.service;

import com.msk.order.bean.param.ISO151408RestParam;
import com.msk.order.bean.result.ISO151408RestResult;

/**
 * zhangqiang1
 */
public interface ISO151408Service {


  ISO151408RestResult createReturnOrder(ISO151408RestParam rsParam);

}
