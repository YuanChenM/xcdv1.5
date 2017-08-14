package com.msk.order.service;


import com.msk.common.bean.param.BasePageParam;
import com.msk.common.bean.result.PageResult;
import com.msk.order.bean.ISO151501Bean;

import java.util.List;
import java.util.Map;

/**
 * 订单页面接口
 * Created by zhangqiang1 on 16/8/10.
 */
public interface ISO151501Service {

    PageResult<ISO151501Bean> findAllOrders(BasePageParam baseParam)throws Exception;


    List<Map<String, Object>> exportOrderAndDetail(BasePageParam baseParam);


}
