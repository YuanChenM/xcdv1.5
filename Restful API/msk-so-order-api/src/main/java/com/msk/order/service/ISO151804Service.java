package com.msk.order.service;

import com.msk.order.bean.param.ISO151804RestParam;
import com.msk.order.bean.result.ISO151804RestReasonResult;

import java.util.List;

/**
 * Created by sunjiaju on 16/8/17.
 */
public interface ISO151804Service {
    List<ISO151804RestReasonResult> searchReturnReason(ISO151804RestParam param);
}
