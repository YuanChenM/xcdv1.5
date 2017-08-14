package com.msk.order.service.impl;

import com.msk.common.constant.NumberConstant;
import com.msk.common.constant.SystemConstant;
import com.msk.common.constant.business.CapitalPoolConst;
import com.msk.common.constant.business.OrderCodeMasterDef;
import com.msk.common.data.jpa.BaseRepository;
import com.msk.common.data.jpa.condition.BaseOperatorEnum;
import com.msk.common.data.jpa.condition.CommonSpecification;
import com.msk.common.data.jpa.condition.Filter;
import com.msk.common.utils.DateTimeUtil;
import com.msk.order.bean.param.ISO151510CpTransactionRestParam;
import com.msk.order.bean.param.ISO151510RestParam;
import com.msk.order.bean.result.ISO151510RestBean;
import com.msk.order.bean.result.ISO151510RestBeanList;
import com.msk.order.bean.result.SO151510OrderShipDetail;
import com.msk.order.entity.*;
import com.msk.order.repository.*;
import com.msk.order.service.AsyncPostService;
import com.msk.order.service.BaseService;
import com.msk.order.service.ISO151510Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * ISO151510_发货单取消后台接口IMPL
 * Created by wang_jianzhou on 2016/8/4.
 */
@Service
public class ISO151510ServiceImpl extends BaseService<SoOrderShip,Long> implements ISO151510Service {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(ISO151510ServiceImpl.class);


    @Autowired
    private SoOrderShipRepository soOrderShipRepository;

    @Autowired
    private SoOrderShipDetailRepository soOrderShipDetailRepository;

    @Autowired
    private SoOrderRepository soOrderRepository;

    @Autowired
    private SoOrderDetailRepository soOrderDetailRepository;

    @Autowired
    private SoSubOrderDetailRepository soSubOrderDetailRepository;

    @Autowired
    private AsyncPostService asyncPostService;

    @Override
    public BaseRepository<SoOrderShip,Long> getRepository() {
        return soOrderShipRepository;
    }

    /**
     * 发货单信息
     *
     * @param param
     * @return 发货单信息
     */
    @Override
    public ISO151510RestBeanList findOrderShipInfo(ISO151510RestParam param) {

        logger.debug("查询发货单");
        ISO151510RestParam iso151510RestParam = new ISO151510RestParam();
        Long subOrderId = param.getSubOrderId();
        //查询条件
        Filter<SoOrderShip> soOrderShipFilter = new Filter<SoOrderShip>();
        soOrderShipFilter.add("subOrderId", BaseOperatorEnum.EQUAL,subOrderId);
        soOrderShipFilter.add("delFlg", BaseOperatorEnum.EQUAL, SystemConstant.DelFlg.ENABLE);
        //调用共通方法
        List<SoOrderShip> orderShips = this.findAll(soOrderShipFilter);
        List<ISO151510RestBean> iso151510RestBeanList = new ArrayList<ISO151510RestBean>();
        for(SoOrderShip soOrderShip :orderShips){
            ISO151510RestBean iso151510RestBean = new ISO151510RestBean();
            iso151510RestBean.setDeliveryTypeName(String.valueOf(soOrderShip.getDeliveryType()));
            iso151510RestBean.setForecastSendTimeStr(DateTimeUtil.formatDate("yyyy-MM-dd",soOrderShip.getForecastSendTime()));
            iso151510RestBean.setForecastReceiveTimeStr(DateTimeUtil.formatDate("yyyy-MM-dd",soOrderShip.getForecastReceiveTime()));
            iso151510RestBean.setPaymentTypeName(String.valueOf(soOrderShip.getPaymentType()));
            iso151510RestBean.setShipStatus(soOrderShip.getShipStatus());
            iso151510RestBean.setShipStatusName(String.valueOf(soOrderShip.getShipStatus()));
            iso151510RestParam.setShipId(soOrderShip.getShipId());
            BeanUtils.copyProperties(soOrderShip, iso151510RestBean);
            List<SO151510OrderShipDetail> soOrderShipDetails = this.findOrderShipDetailByShipId(soOrderShip.getShipId());
            iso151510RestBean.setSoOrderShipDetails(soOrderShipDetails);
            iso151510RestBeanList.add(iso151510RestBean);
        }
        ISO151510RestBeanList so151410RsBeans = new ISO151510RestBeanList();
        so151410RsBeans.setBeanList(iso151510RestBeanList);
        return so151410RsBeans;
    }


    /**
     * 查询发货单明细
     *
     * @param shipId
     * @return List<SoOrderShipDetail>
     */
    @Transactional
    public List<SO151510OrderShipDetail> findOrderShipDetailByShipId(Long shipId){
        logger.debug("查询发货单明细");
        Filter<SoOrderShipDetail> soOrderShipDetailFilter = new Filter<SoOrderShipDetail>();
        soOrderShipDetailFilter.add("shipId",BaseOperatorEnum.EQUAL,shipId);
        soOrderShipDetailFilter.add("delFlg", BaseOperatorEnum.EQUAL, SystemConstant.DelFlg.ENABLE);
        CommonSpecification<SoOrderShipDetail> subOrderCondition = new CommonSpecification<SoOrderShipDetail>(
                soOrderShipDetailFilter);
        List<SoOrderShipDetail> soOrderShipDetails = soOrderShipDetailRepository.findAll(subOrderCondition);
        List<SO151510OrderShipDetail> details = new ArrayList<SO151510OrderShipDetail>();
        for(SoOrderShipDetail soOrderShipDetail : soOrderShipDetails){
            SO151510OrderShipDetail detail = new SO151510OrderShipDetail();
            BeanUtils.copyProperties(soOrderShipDetail, detail);
            Filter<SoOrderDetail> soOrderDetailFilter = new Filter<SoOrderDetail>();
            soOrderDetailFilter.add("orderDetailId",BaseOperatorEnum.EQUAL,soOrderShipDetail.getOrderDetailId());
            soOrderDetailFilter.add("delFlg", BaseOperatorEnum.EQUAL, SystemConstant.DelFlg.ENABLE);
            CommonSpecification<SoOrderDetail> orderDetailCondition = new CommonSpecification<SoOrderDetail>(
                    soOrderDetailFilter);
            SoOrderDetail orderDetail = soOrderDetailRepository.findOne(orderDetailCondition);
            detail.setPdGradeName(orderDetail.getPdGradeName());
            details.add(detail);
        }
        return details;
    }


    /**
     * 发货单取消
     *
     * @param iso151510RestParam
     * @return 发货单取消
     */
    @Override
    @Transactional
    public void cancelOrderShip(ISO151510RestParam iso151510RestParam) {

        SoOrderShip soOrderShip = this.modifyOrderShipByShipId(OrderCodeMasterDef.ShipStatus.WAIT_CANCEL, iso151510RestParam);

        List<SoOrderShipDetail> soOrderShipDetails = this.modifyOrderShipDetailByShipId(iso151510RestParam);

        List<SoOrderDetail> details = this.modifyOrderDetailByShipId(iso151510RestParam);

        this.modifySoSubOrderDetailByShipId(iso151510RestParam,details);

    }


    /**
     * 更新发货单表
     *
     * @param iso151510RestParam 接口入参
     * @param status 更新的状态
     * @return 发货单取消
     */
    @Transactional
    public SoOrderShip modifyOrderShipByShipId(Integer status,ISO151510RestParam iso151510RestParam){
        SoOrderShip soOrderShip = new SoOrderShip();
        Filter<SoOrderShip> filter = new Filter<SoOrderShip>();
        filter.add("shipId",BaseOperatorEnum.EQUAL, iso151510RestParam.getShipId());
        filter.add("delFlg", BaseOperatorEnum.EQUAL, SystemConstant.DelFlg.ENABLE);
        CommonSpecification<SoOrderShip> condition = new CommonSpecification<SoOrderShip>(
                filter);
        soOrderShip = soOrderShipRepository.findOne(condition);
        soOrderShip.setShipStatus(status);
        soOrderShip.setCancelTime(DateTimeUtil.getCustomerDate());
        soOrderShip.setCancelManId(iso151510RestParam.getCrtId());
        soOrderShip.setUpdId(iso151510RestParam.getCrtId());
        soOrderShip.setCancelManName(iso151510RestParam.getCrtId());
        soOrderShip.setUpdTime(DateTimeUtil.getCustomerDate());
        soOrderShip.setVer(soOrderShip.getVer() + NumberConstant.IntDef.INT_ONE);
        soOrderShip = soOrderShipRepository.save(soOrderShip);
        return soOrderShip;
    }

    /**
     * 更新发货单明细表
     *
     * @param iso151510RestParam 接口入参
     * @return 发货单取消
     */
    @Transactional
    public List<SoOrderShipDetail> modifyOrderShipDetailByShipId(ISO151510RestParam iso151510RestParam){
        List<SoOrderShipDetail> soOrderShipDetails = new ArrayList<SoOrderShipDetail>();
        Filter<SoOrderShipDetail> filter = new Filter<SoOrderShipDetail>();
        filter.add("shipId",BaseOperatorEnum.EQUAL, iso151510RestParam.getShipId());
        filter.add("delFlg", BaseOperatorEnum.EQUAL, SystemConstant.DelFlg.ENABLE);
        CommonSpecification<SoOrderShipDetail> condition = new CommonSpecification<SoOrderShipDetail>(
                filter);
        soOrderShipDetails = soOrderShipDetailRepository.findAll(condition);
        for(SoOrderShipDetail soOrderShipDetail : soOrderShipDetails){
            soOrderShipDetail.setCancelQty(soOrderShipDetail.getSuppQty());
            soOrderShipDetail.setSendQty(BigDecimal.ZERO);
            soOrderShipDetail.setUpdId(iso151510RestParam.getCrtId());
            soOrderShipDetail.setUpdTime(DateTimeUtil.getCustomerDate());
            soOrderShipDetail.setVer(soOrderShipDetail.getVer() + NumberConstant.IntDef.INT_ONE);
            soOrderShipDetailRepository.save(soOrderShipDetail);
        }

        return soOrderShipDetails;
    }

    /**
     * 更新订单明细表
     *
     * @param iso151510RestParam 接口入参
     * @return 发货单取消
     */
    @Transactional
    public List<SoOrderDetail> modifyOrderDetailByShipId(ISO151510RestParam iso151510RestParam){
        Long shipId = iso151510RestParam.getShipId();
        String updId = iso151510RestParam.getUpdId();
        Filter<SoOrderDetail> filter = new Filter<SoOrderDetail>();
        filter.add("soOrderShipDetailList.shipId",BaseOperatorEnum.EQUAL, iso151510RestParam.getShipId());
        filter.add("soOrderShipDetailList.delFlg",BaseOperatorEnum.EQUAL, SystemConstant.DelFlg.ENABLE);
        filter.add("delFlg",BaseOperatorEnum.EQUAL, SystemConstant.DelFlg.ENABLE);
        CommonSpecification<SoOrderDetail> condition = new CommonSpecification<SoOrderDetail>(
                filter);
        List<SoOrderDetail> details = this.soOrderDetailRepository.findAll(condition);
        for(SoOrderDetail detail : details){
            detail.setUpdTime(DateTimeUtil.getCustomerDate());
            detail.setUpdId(updId);
            detail.setCancelQty(detail.getOrderQty());
            detail.setSendQty(BigDecimal.ZERO);
            detail.setVer(detail.getVer() + NumberConstant.IntDef.INT_ONE);
        }
        soOrderDetailRepository.save(details);
        return details;
    }

    /**
     * 更新分批订单明细表
     *
     * @param iso151510RestParam 接口入参
     * @return 发货单取消
     */
    @Transactional
    public void modifySoSubOrderDetailByShipId(ISO151510RestParam iso151510RestParam,List<SoOrderDetail> soOrderDetails){
        String updId = iso151510RestParam.getUpdId();
        Filter<SoSubOrderDetail> filter = new Filter<SoSubOrderDetail>();
        filter.add("delFlg",BaseOperatorEnum.EQUAL, SystemConstant.DelFlg.ENABLE);
        List<Long> orderDetailIds = new ArrayList<Long>();
        for(SoOrderDetail detail : soOrderDetails){
            orderDetailIds.add(detail.getOrderDetailId());
        }
        Long [] orderDetailIdArr = new Long[soOrderDetails.size()];
        filter.add("orderDetailId",BaseOperatorEnum.IN, orderDetailIds.toArray(orderDetailIdArr));
        CommonSpecification<SoSubOrderDetail> condition = new CommonSpecification<SoSubOrderDetail>(
                filter);
        List<SoSubOrderDetail> subOrderDetails = this.soSubOrderDetailRepository.findAll(condition);

        for(SoSubOrderDetail soSubOrderDetail : subOrderDetails){
            soSubOrderDetail.setUpdId(updId);
            soSubOrderDetail.setUpdTime(DateTimeUtil.getCustomerDate());
            soSubOrderDetail.setCancelQty(soSubOrderDetail.getOrderQty());
            soSubOrderDetail.setSendQty(BigDecimal.ZERO);
            soSubOrderDetail.setVer(soSubOrderDetail.getVer() + NumberConstant.IntDef.INT_ONE);
        }
        this.soSubOrderDetailRepository.save(subOrderDetails);
    }
}

