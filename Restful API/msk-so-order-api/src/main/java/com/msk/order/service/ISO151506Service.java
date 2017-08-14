package com.msk.order.service;

import com.msk.common.bean.param.BasePageParam;
import com.msk.common.bean.result.PageResult;
import com.msk.order.entity.SoReturn;

/**
 * ISO151506_退货单列表
 * Created by wang_shuai on 2016/8/11.
 */
public interface ISO151506Service {
    PageResult<SoReturn> findPageList(BasePageParam basePageParam);
}
