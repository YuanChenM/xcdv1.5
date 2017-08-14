package com.msk.order.service.impl;

import com.msk.common.data.jpa.BaseRepository;
import com.msk.common.data.jpa.condition.BaseOperatorEnum;
import com.msk.common.data.jpa.condition.Filter;
import com.msk.order.service.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msk.common.bean.RestRequest;
import com.msk.common.constant.SystemConstant;
import com.msk.order.bean.param.ISO151404RsParam;
import com.msk.order.bean.result.ISO151404RsResult;
import com.msk.order.entity.SoReturn;
import com.msk.order.repository.SoReturnRepository;
import com.msk.order.service.ISO151404Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * ISO151404_验证退货单号接口
 * Created by chu_jian on 2016/8/3.
 */

@Service
public class ISO151404ServiceImpl extends BaseService implements ISO151404Service {
    private static Logger logger = LoggerFactory.getLogger(ISO151404ServiceImpl.class);
    @Autowired
    private SoReturnRepository returnRepository;

    @Override
    @Transactional(readOnly = true)
    public ISO151404RsResult selectCount(RestRequest<ISO151404RsParam> requestBody) {
        logger.debug("验证退货单号接口");
        ISO151404RsResult result = new ISO151404RsResult();
        ISO151404RsParam param = requestBody.getParam();
        Filter<SoReturn> filter = new Filter<>();
        filter.add("returnCode", BaseOperatorEnum.EQUAL, param.getBackNo());
        filter.add("orderCode", BaseOperatorEnum.EQUAL, param.getTransCode());
        filter.add("delFlg", BaseOperatorEnum.EQUAL, SystemConstant.DelFlg.ENABLE);
        Long count = this.count(filter);
        result.setCount(count.intValue());
        return result;
    }

    @Override
    public BaseRepository getRepository() {
        return returnRepository;
    }
}
