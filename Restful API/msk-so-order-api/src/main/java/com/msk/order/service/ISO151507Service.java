package com.msk.order.service;

import com.msk.common.bean.param.BaseParam;
import com.msk.order.bean.result.ISO151507BaseReturnResult;
import com.msk.order.bean.result.SO15150701BeanResult;

import java.io.IOException;
import java.util.List;

/**
 * ISO151506_退货单详细
 * Created by wang_shuai on 2016/8/11.
 */
public interface ISO151507Service {
    List<SO15150701BeanResult> findReturnDetailList(BaseParam param) throws IOException;
    ISO151507BaseReturnResult findBaseReturnInfo(BaseParam baseParam) throws IOException;
}
