package com.msk.order.service;

import com.msk.order.bean.param.ISO151409RestParam;
import com.msk.order.bean.result.ISO151409RestResult;

/**查询退货单
 * zhangqiang1
 */
public interface ISO151409Service {

  ISO151409RestResult findReturnOrder(ISO151409RestParam rsParam);


}
