package com.msk.order.service.impl;

import com.msk.common.constant.StringConstant;
import com.msk.common.constant.SystemConstant;
import com.msk.common.data.jpa.BaseRepository;
import com.msk.common.data.jpa.condition.BaseOperatorEnum;
import com.msk.common.data.jpa.condition.Filter;
import com.msk.common.utils.StringUtil;
import com.msk.order.bean.param.ISO151804RestParam;
import com.msk.order.bean.result.ISO151804RestReasonResult;
import com.msk.order.entity.SoReturnReason;
import com.msk.order.repository.SoReturnReasonRepository;
import com.msk.order.service.BaseService;
import com.msk.order.service.ISO151804Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 退货原因查询接口
 * Created by sun_jiaju on 2016/8/17.
 */
@Service
public class ISO151804ServiceImpl extends BaseService<SoReturnReason, Long> implements ISO151804Service {
    @Autowired
    private SoReturnReasonRepository soReturnReasonRepository;

    @Override
    public BaseRepository getRepository() {
        return soReturnReasonRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ISO151804RestReasonResult> searchReturnReason(ISO151804RestParam param) {
        List<ISO151804RestReasonResult> results = new ArrayList<>();
        List<SoReturnReason> returnReasons = null;
        Filter<SoReturnReason> filter = new Filter<>();
        filter.add("delFlg", BaseOperatorEnum.EQUAL, SystemConstant.DelFlg.ENABLE);
        if (StringUtil.isNullOrEmpty(param.getReturnType())) {
            returnReasons = super.findAll(filter);
        } else {
            filter.add("returnType", BaseOperatorEnum.LIKE, StringConstant.STAR + param.getReturnType() + StringConstant.STAR);
            returnReasons = super.findAll(filter);
        }
        for (SoReturnReason returnReason : returnReasons) {
            ISO151804RestReasonResult result = new ISO151804RestReasonResult();
            result.setReasonId(returnReason.getReasonId());
            result.setReasonName(returnReason.getReasonName());
            results.add(result);
        }
        return results;
    }
}
