package com.msk.order.service.impl;

import com.msk.common.data.jpa.BaseRepository;
import com.msk.common.data.jpa.condition.BaseOperatorEnum;
import com.msk.common.data.jpa.condition.Filter;
import com.msk.order.entity.SoOrder;
import com.msk.order.repository.SoOrderRepository;
import com.msk.order.service.BaseService;
import com.msk.order.service.ISO151502Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.JoinType;

/**
 * zhangqiang1
 */
@Service
public class ISO151502ServiceImpl extends BaseService<SoOrder, Long> implements ISO151502Service {
    @Autowired
    private SoOrderRepository soOrderRepository;


    /**
     * 根据id  查询对应的订单及相关信息
     *
     * @param orderId
     * @param delFlg
     * @return
     */
    @Override
    public SoOrder findByOrderIdAndDelFlg(Long orderId, String delFlg) {
        Filter<SoOrder> filter = new Filter<>();
         filter.joinRelative("soOrderShipDetailList", JoinType.LEFT);
//        filter.addNullColumn("soOrderShipDetailList.delFlg");
//        filter.add("soOrderShipDetailList.delFlg", BaseOperatorEnum.NOTIN, delFlg);
        filter.add("orderId", BaseOperatorEnum.EQUAL, orderId);
        filter.add("delFlg", BaseOperatorEnum.EQUAL, delFlg);
        SoOrder order = super.findOne(filter);
        return order;
    }


    @Override
    public BaseRepository getRepository() {
        return this.soOrderRepository;
    }


}
