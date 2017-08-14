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
import com.msk.order.entity.*;
import com.msk.order.repository.SoReturnRepository;
import com.msk.order.repository.SoReturnStatusRepository;
import com.msk.order.service.BaseService;
import com.msk.order.service.ReturnOrderStatusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhang_qiang1 on 2016/9/2.
 */
@Service
public class ReturnOrderStatusServiceImpl extends BaseService<SoReturnStatus, Long> implements ReturnOrderStatusService {

    private static Logger logger = LoggerFactory.getLogger(ReturnOrderStatusServiceImpl.class);

    @Autowired
    private SoReturnRepository returnRepository;


    @Autowired
    private SoReturnStatusRepository returnStatusRepository;

    @Override
    public BaseRepository<SoReturnStatus, Long> getRepository() {
        return this.returnStatusRepository;
    }


    /**
     * 根据订单id,批量更新退货单单状态履历
     *
     * @param orderId
     * @param returnStatus
     * @param crtId
     * @return
     */
    @Override
    @Transactional
    public List<SoReturnStatus> saveReturnStatusByOrderId(Long orderId, Integer returnStatus, String crtId) {
        logger.info("根据订单id,批量更新退货单单状态履历");
        Filter<SoReturn> returnFilter = new Filter<>();
        returnFilter.add("orderId", BaseOperatorEnum.EQUAL, orderId);
        returnFilter.add("delFlg", BaseOperatorEnum.EQUAL, SystemConstant.DelFlg.ENABLE);
        CommonSpecification<SoReturn> spe = new CommonSpecification<>(returnFilter);
        List<SoReturn> soReturnList = this.returnRepository.findAll(spe);

        Integer returnOrderSize = soReturnList.size();
        Long maxId = this.maxId("so_return_status", returnOrderSize);
        Long minId = maxId - returnOrderSize;
        List<SoReturnStatus> returnStatusList = new ArrayList<>();
        for (SoReturn savedOrUpdateReturnOrder : soReturnList) {
            savedOrUpdateReturnOrder.setReturnStatus(returnStatus);
            SoReturnStatus soReturnStatus = this.saveReturnStatusByReturnOrder(savedOrUpdateReturnOrder, crtId, minId);
            returnStatusList.add(soReturnStatus);
            minId++;
        }
        return returnStatusList;
    }


    /**
     * 根据退货单id, 更新退货单状态履历
     *
     * @param returnId
     * @param returnStatus
     * @param crtId
     * @return
     */
    @Override
    @Transactional
    public SoReturnStatus saveReturnStatusByReturnOrderId(Long returnId, Integer returnStatus, String crtId) {
        logger.info("根据退货单id, 更新退货单状态履历");
        Filter<SoReturn> returnFilter = new Filter<>();
        returnFilter.add("returnId", BaseOperatorEnum.EQUAL, returnId);
        returnFilter.add("delFlg", BaseOperatorEnum.EQUAL, SystemConstant.DelFlg.ENABLE);
        CommonSpecification<SoReturn> spe = new CommonSpecification<>(returnFilter);
        SoReturn soReturn = this.returnRepository.findOne(spe);
        if (soReturn == null) {
            throw new BusinessException("根据对应的returnId:" + returnId + "找不到对应的退货单");
        }
        soReturn.setReturnStatus(returnStatus);
        return this.saveReturnStatusByReturnOrder(soReturn, crtId);
    }

    /**
     * @param savedOrUpdatedSoReturn 退货单的退货状态已经更改过
     * @param crtId
     * @return
     */
    @Override
    @Transactional
    public SoReturnStatus saveReturnStatusByReturnOrder(SoReturn savedOrUpdatedSoReturn, String crtId) {
        logger.info("根据退货单单更新订单状态履历");
        Long statusId = this.maxId("so_return_status");
        return this.saveReturnStatusByReturnOrder(savedOrUpdatedSoReturn, crtId, statusId);
    }


    /**
     * @param savedOrUpdatedSoReturn
     * @param crtId
     * @param statusId
     * @return
     */
    private SoReturnStatus saveReturnStatusByReturnOrder(SoReturn savedOrUpdatedSoReturn, String crtId, Long statusId) {
        Filter<SoReturnStatus> filter = new Filter<>();
        Long returnId = savedOrUpdatedSoReturn.getReturnId();
        String returnCode = savedOrUpdatedSoReturn.getReturnCode();
        Integer returnStatus = savedOrUpdatedSoReturn.getReturnStatus();
        filter.add("returnId", BaseOperatorEnum.EQUAL, returnId);
        filter.add("delFlg", BaseOperatorEnum.EQUAL, SystemConstant.DelFlg.ENABLE);
        CommonSpecification<SoReturnStatus> spec = new CommonSpecification<>(filter);
        List<SoReturnStatus> returnStatusList = returnStatusRepository.findAll(spec);
        for (SoReturnStatus status : returnStatusList) {
            status.setDelFlg(SystemConstant.DelFlg.DISABLE);
            status.setVer(status.getVer() + NumberConstant.IntDef.INT_ONE);
            status.setUpdId(crtId);
            status.setUpdTime(DateTimeUtil.getCustomerDate());
            this.returnStatusRepository.save(status);
        }
        SoReturnStatus newReturnOrderStatus = new SoReturnStatus();
        newReturnOrderStatus.setStatusId(statusId);
        newReturnOrderStatus.setReturnId(returnId);
        newReturnOrderStatus.setReturnCode(returnCode);
        newReturnOrderStatus.setReturnStatus(returnStatus);
        newReturnOrderStatus.setVer(NumberConstant.IntDef.INT_ONE);
        newReturnOrderStatus.setDelFlg(SystemConstant.DelFlg.ENABLE);
        if (StringUtil.isEmpty(crtId)) {
            newReturnOrderStatus.setCrtId(savedOrUpdatedSoReturn.getUpdId());
        } else {
            newReturnOrderStatus.setCrtId(crtId);
        }
        newReturnOrderStatus.setCrtTime(DateTimeUtil.getCustomerDate());
        this.returnStatusRepository.save(newReturnOrderStatus);
        return newReturnOrderStatus;
    }

}
