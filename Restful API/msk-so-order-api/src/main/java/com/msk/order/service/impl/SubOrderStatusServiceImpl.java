package com.msk.order.service.impl;

import com.msk.common.constant.NumberConstant;
import com.msk.common.constant.SystemConstant;
import com.msk.common.data.jpa.BaseRepository;
import com.msk.common.data.jpa.condition.BaseOperatorEnum;
import com.msk.common.data.jpa.condition.CommonSpecification;
import com.msk.common.data.jpa.condition.Filter;
import com.msk.common.exception.BusinessException;
import com.msk.common.utils.DateTimeUtil;
import com.msk.common.utils.StringUtil;
import com.msk.order.entity.SoSubOrder;
import com.msk.order.entity.SoSubOrderStatus;
import com.msk.order.repository.SoSubOrderRepository;
import com.msk.order.repository.SoSubOrderStatusRepository;
import com.msk.order.service.BaseService;
import com.msk.order.service.SubOrderStatusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhang_qiang1 on 2016/9/1.
 */
@Service
public class SubOrderStatusServiceImpl extends BaseService<SoSubOrder, Long> implements SubOrderStatusService {

    private static Logger logger = LoggerFactory.getLogger(SubOrderStatusServiceImpl.class);

    @Autowired
    private SoSubOrderRepository subOrderRepository;

    @Autowired
    private SoSubOrderStatusRepository subOrderStatusRepository;


    @Override
    public BaseRepository<SoSubOrder, Long> getRepository() {
        return this.subOrderRepository;
    }

    /**
     * @param orderId
     * @param subOrderStatus
     * @param crtId
     * @return
     */
    @Override
    @Transactional
    public List<SoSubOrderStatus> saveSubOrderStatusListByOrderId(Long orderId, Integer subOrderStatus, String crtId) {
        logger.info("根据订单id, 批量更新分批订单状态履历");
        Filter<SoSubOrder> filter = new Filter<>();
        filter.add("orderId", BaseOperatorEnum.EQUAL, orderId);
        filter.add("delFlg", BaseOperatorEnum.EQUAL, SystemConstant.DelFlg.ENABLE);
        CommonSpecification<SoSubOrder> spec = new CommonSpecification<>(filter);
        List<SoSubOrder> subOrderList = this.subOrderRepository.findAll(spec);
        if (subOrderList == null) {
            throw new BusinessException("订单id为" + orderId + "没有对应的子订单");
        }
        Integer subOrderSize = subOrderList.size();
        Long maxId = this.maxId("so_sub_order_status", subOrderSize + NumberConstant.IntDef.INT_ONE);
        Long minId = maxId - subOrderSize;
        List<SoSubOrderStatus> subOrderStatusList = new ArrayList<>();
        for (SoSubOrder savedOrUpdatedSubOrder : subOrderList) {
            savedOrUpdatedSubOrder.setSubOrderStatus(subOrderStatus);
            SoSubOrderStatus soSubOrderStatus = this.saveSubStatusBySubOrder(savedOrUpdatedSubOrder, minId, crtId);
            subOrderStatusList.add(soSubOrderStatus);
            minId++;
        }
        return subOrderStatusList;
    }


    /**
     * @param subOrderId
     * @param subOrderStatus
     * @param crtId
     * @return
     */
    @Override
    @Transactional
    public SoSubOrderStatus saveSubStatusBySubOrderId(Long subOrderId, Integer subOrderStatus, String crtId) {
        logger.info("根据分批订单id, 更新分批订单状态履历");
        Filter<SoSubOrder> filter = new Filter<>();
        filter.add("subOrderId", BaseOperatorEnum.EQUAL, subOrderId);
        filter.add("delFlg", BaseOperatorEnum.EQUAL, SystemConstant.DelFlg.ENABLE);
        SoSubOrder subOrder = this.findOneForce(filter);
        subOrder.setSubOrderStatus(subOrderStatus);
        return this.saveSubStatusBySubOrder(subOrder, crtId);
    }


    /**
     * @param savedOrUpdatedSoSubOrder
     * @param crtId
     * @return
     */
    @Override
    @Transactional
    public SoSubOrderStatus saveSubStatusBySubOrder(SoSubOrder savedOrUpdatedSoSubOrder, String crtId) {
        logger.info("根据已经修改过的分批订单, 更新分批订单状态履历");
        Long statusId = this.maxId("so_sub_order_status");
        return this.saveSubStatusBySubOrder(savedOrUpdatedSoSubOrder, statusId, crtId);
    }

    /**
     * @param savedOrUpdatedSoSubOrder
     * @param statusId
     * @param crtId
     * @return
     */
    private SoSubOrderStatus saveSubStatusBySubOrder(SoSubOrder savedOrUpdatedSoSubOrder, Long statusId, String crtId) {
        Filter<SoSubOrderStatus> filter = new Filter<>();
        Long subOrderId = savedOrUpdatedSoSubOrder.getSubOrderId();
        Long orderId = savedOrUpdatedSoSubOrder.getOrderId();
        Integer status = savedOrUpdatedSoSubOrder.getSubOrderStatus();
        filter.add("subOrderId", BaseOperatorEnum.EQUAL, subOrderId);
        filter.add("delFlg", BaseOperatorEnum.EQUAL, SystemConstant.DelFlg.ENABLE);
        CommonSpecification<SoSubOrderStatus> spec = new CommonSpecification<>(filter);
        List<SoSubOrderStatus> subOrderStatusList = this.subOrderStatusRepository.findAll(spec);
        for (SoSubOrderStatus soSubOrderStatus : subOrderStatusList) {
            soSubOrderStatus.setDelFlg(SystemConstant.DelFlg.DISABLE);
            soSubOrderStatus.setUpdId(crtId);
            soSubOrderStatus.setUpdTime(DateTimeUtil.getCustomerDate());
            soSubOrderStatus.setVer(NumberConstant.IntDef.INT_ONE + soSubOrderStatus.getVer());
            this.subOrderStatusRepository.save(soSubOrderStatus);
        }

        SoSubOrderStatus newSoSubOrderStatus = new SoSubOrderStatus();
        newSoSubOrderStatus.setSubOrderId(subOrderId);
        newSoSubOrderStatus.setOrderId(orderId);
        newSoSubOrderStatus.setSubOrderStatus(status);
        newSoSubOrderStatus.setVer(NumberConstant.IntDef.INT_ONE);
        newSoSubOrderStatus.setStatusId(statusId);
        newSoSubOrderStatus.setDelFlg(SystemConstant.DelFlg.ENABLE);
        if (StringUtil.isEmpty(crtId)) {
            newSoSubOrderStatus.setCrtId(savedOrUpdatedSoSubOrder.getUpdId());
        } else {
            newSoSubOrderStatus.setCrtId(crtId);
        }
        newSoSubOrderStatus.setCrtTime(DateTimeUtil.getCustomerDate());
        this.subOrderStatusRepository.save(newSoSubOrderStatus);
        return newSoSubOrderStatus;
    }
}
