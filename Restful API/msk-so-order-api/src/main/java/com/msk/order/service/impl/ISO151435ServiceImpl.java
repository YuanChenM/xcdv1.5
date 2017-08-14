package com.msk.order.service.impl;

import com.msk.common.data.jpa.condition.BaseOperatorEnum;
import com.msk.common.data.jpa.condition.CommonSpecification;
import com.msk.common.data.jpa.condition.Filter;
import com.msk.common.exception.BusinessException;
import com.msk.common.constant.StringConstant;
import com.msk.common.constant.business.OrderCodeMasterDef;
import com.msk.common.data.jpa.BaseRepository;
import com.msk.order.bean.param.ISO151435RestParam;
import com.msk.order.bean.result.ISO151435RestResult;
import com.msk.order.entity.SoOrder;
import com.msk.order.entity.SoOrderReceiveDemand;
import com.msk.order.repository.SoOrderRepository;
import com.msk.order.service.BaseService;
import com.msk.order.service.ISO151435Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ISO151435_根据订单号(订单编码)获取订单相关信息
 * Created by chu_jian on 2016/8/3.
 */
@Service
public class ISO151435ServiceImpl extends BaseService implements ISO151435Service {

    @Autowired
    private SoOrderRepository soOrderRepository;
    private static Logger logger = LoggerFactory.getLogger(ISO151435ServiceImpl.class);

    @Override
    public BaseRepository getRepository() {
        return soOrderRepository;
    }

    /**
     * 根据订单号(订单编码)获取订单相关信息
     *
     * @return 返回结果
     * @author chu_jian
     */
    @Override
    public ISO151435RestResult getPartOrderDetail(ISO151435RestParam iso251435RsParam) {
        logger.debug("获取订单相关信息");
        ISO151435RestResult rs = new ISO151435RestResult();
        Filter<SoOrder> filterSoOrder = new Filter<>();
        filterSoOrder.add("orderCode", BaseOperatorEnum.EQUAL, iso251435RsParam.getOrderCode());
        CommonSpecification<SoOrder> conditionSoOrder = new CommonSpecification<>(filterSoOrder);
        SoOrder soOrder = soOrderRepository.findOne(conditionSoOrder);
        BeanUtils.copyProperties(soOrder, rs);
        SoOrderReceiveDemand soOrderReceiveDemand = soOrder.getSoOrderReceiveDemand();
        rs.setOrderReceiveAddr(soOrderReceiveDemand.getReceiverAddr());
        rs.setRemark(soOrderReceiveDemand.getRemark());
        rs.setFinishTime(soOrder.getUpdTime());
        rs.setOrderStatus(soOrder.getOrderStatus() + StringConstant.EMPTY);
        if (rs == null) {
            throw new BusinessException("该订单编码无记录:" + iso251435RsParam.getOrderCode());
        }
        int status = Integer.parseInt(rs.getOrderStatus());
        //只有当订单状态为完成，全部退货货，取消时才算完结
        if (status != OrderCodeMasterDef.OrderDetailStatus.ALL_RETURN
                && status != OrderCodeMasterDef.OrderStatus.CANCEL
                && status != OrderCodeMasterDef.OrderStatus.ALL_RECEIPT
                ) {
            rs.setFinishTime(null);
        }
        return rs;
    }
}
