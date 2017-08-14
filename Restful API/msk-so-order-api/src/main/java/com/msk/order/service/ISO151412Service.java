package com.msk.order.service;

        import com.msk.common.bean.RestRequest;
        import com.msk.common.bean.RestResponse;
        import com.msk.order.bean.param.ISO151412RestParam;
        import com.msk.order.bean.result.ISO151412RestResult;

/**
 * 订单删除 恢复
 * zhangqiang1
 */
public interface ISO151412Service {


    RestResponse<ISO151412RestResult> setOrderDelOrRecover(RestRequest<ISO151412RestParam> param);

}
