package com.msk.order.service.impl;

import com.msk.common.utils.DateTimeUtil;
import com.msk.order.bean.param.ISO151407RestParam;
import com.msk.order.bean.result.ISO151407RestOrderCountResult;
import com.msk.order.bean.result.ISO151407RestOrderResult;
import com.msk.order.repository.SoOrderRepository;
import com.msk.order.service.ISO151407Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * ISO151407_买家平台下单数量统计
 * Created by sunjiaju on 16/8/24.
 */
@Service
public class ISO151407ServiceImpl implements ISO151407Service {
    private static Logger logger = LoggerFactory.getLogger(ISO151407ServiceImpl.class);

    @Autowired
    private SoOrderRepository soOrderRepository;

    /**
     * 买家平台下单数量查询
     *
     * @param param
     */
    @Override
    @Transactional(readOnly = true)
    public List<ISO151407RestOrderResult> searchOrderSourceCount(ISO151407RestParam param) {
        logger.info("买家平台下单数量统计 查询开始");
        List<ISO151407RestOrderResult> results = new ArrayList<>();
        Date startDate = null;
        Date endDate = null;
        if (param.getStartDate() != null){
            startDate = DateTimeUtil.parseDate(DateTimeUtil.formatDate(DateTimeUtil.FORMAT_DATE_YYYYMMDD, param.getStartDate()) + " 00:00:00", "yyyy-MM-dd HH:mm:ss");
        }
        if (param.getStartDate() != null){
            endDate = DateTimeUtil.parseDate(DateTimeUtil.formatDate(DateTimeUtil.FORMAT_DATE_YYYYMMDD, param.getEndDate()) + " 23:59:59", "yyyy-MM-dd HH:mm:ss");
        }
        List<ISO151407RestOrderCountResult> entityList = soOrderRepository.findOrderCount(param.getBuyerId(), startDate, endDate);
        for (ISO151407RestOrderCountResult entity : entityList) {
            ISO151407RestOrderResult result = new ISO151407RestOrderResult();
            result.setOrderPlatform(String.valueOf(entity.getOrderPlatform()));
            result.setOrderCount((int) entity.getOrderCount());
            results.add(result);
        }
        logger.info("买家平台下单数量统计 查询结束");
        return results;
    }
}
