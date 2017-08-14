package com.msk.order.service.impl;

import com.msk.common.constant.SystemConstant;
import com.msk.common.constant.business.CapitalPoolConst;
import com.msk.common.constant.business.OrderCodeMasterDef;
import com.msk.common.data.jpa.BaseRepository;
import com.msk.common.data.jpa.condition.BaseOperatorEnum;
import com.msk.common.data.jpa.condition.CommonSpecification;
import com.msk.common.data.jpa.condition.Filter;
import com.msk.common.exception.BusinessException;
import com.msk.order.bean.param.ISO151403RestParam;
import com.msk.order.bean.param.ISO151403SellerRestParam;
import com.msk.order.bean.result.ISO151403SellerRestResult;
import com.msk.order.bean.result.ISO151403SupplierRestResult;
import com.msk.order.entity.SoOrder;
import com.msk.order.entity.SoOrderShipDetail;
import com.msk.order.entity.SoSubOrder;
import com.msk.order.repository.SoOrderRepository;
import com.msk.order.repository.SoOrderShipDetailRepository;
import com.msk.order.service.BaseService;
import com.msk.order.service.ISO151403Service;
import com.msk.order.util.SoRestUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ISO151403_查询供应商列表接口
 * Created by chu_jian on 2016/8/3.
 */
@Service
public class ISO151403ServiceImpl extends BaseService<SoOrder, Long> implements ISO151403Service {
    @Autowired
    private SoOrderRepository orderRepository;
    @Autowired
    private SoOrderShipDetailRepository orderShipDetailRepository;

    @Override
    public BaseRepository getRepository() {
        return orderRepository;
    }

    /**
     * 查询供应商列表
     *
     * @param iso151403RestParam
     * @return 返回结果
     * @author chu_jian
     */
    @Override
    public List<ISO151403SupplierRestResult> findSellers(ISO151403RestParam iso151403RestParam) {
        Filter<SoOrder> filter = new Filter<>();
        filter.add("orderCode", BaseOperatorEnum.EQUAL, iso151403RestParam.getTransCode());
        filter.add("delFlg", BaseOperatorEnum.EQUAL, SystemConstant.DelFlg.ENABLE);
        SoOrder soOrder = super.findOne(filter);
        if (soOrder == null) {
            throw new BusinessException("该订单编号无记录:" + iso151403RestParam.getTransCode());
        }
        Map<String, ISO151403SupplierRestResult> resultMap = new HashMap<String, ISO151403SupplierRestResult>();
        List<String> sellerCodeList = new ArrayList<String>();
        for (SoSubOrder soSubOrder : soOrder.getSoSubOrders()) {
            // 获取买手级别信息
            if (soSubOrder.getOrderType() == OrderCodeMasterDef.OrderType.BUYER_SALE_ORDER
                    || soSubOrder.getOrderType() == OrderCodeMasterDef.OrderType.THIRD_BUYER_SALE_ORDER) {
                ISO151403SupplierRestResult result = new ISO151403SupplierRestResult();
                result.setUserId(soSubOrder.getSellerCode());
                result.setUserRole(Integer.valueOf(CapitalPoolConst.RoleType.ROLE_BUYERE));
                resultMap.put(soSubOrder.getSellerCode(), result);
                sellerCodeList.add(soSubOrder.getSellerCode());
            } else {
                // 获取供应商级别信息
                Filter<SoOrderShipDetail> detailFilter = new Filter<>();
                detailFilter.add("subOrderId", BaseOperatorEnum.EQUAL, soSubOrder.getSubOrderId());
                detailFilter.add("delFlg", BaseOperatorEnum.EQUAL, SystemConstant.DelFlg.ENABLE);
                CommonSpecification<SoOrderShipDetail> conditionSoOrderShipDetail = new CommonSpecification<>(detailFilter);
                List<SoOrderShipDetail> soOrderShipList = orderShipDetailRepository.findAll(conditionSoOrderShipDetail);
                for (SoOrderShipDetail soOrderShipDetail : soOrderShipList) {
                    ISO151403SupplierRestResult result = new ISO151403SupplierRestResult();
                    result.setUserId(soOrderShipDetail.getSupplierCode());
                    result.setUserRole(Integer.valueOf(CapitalPoolConst.RoleType.ROLE_SELLER));
                    resultMap.put(soOrderShipDetail.getSupplierCode(), result);
                    sellerCodeList.add(soOrderShipDetail.getSupplierCode());
                }
            }
        }
        // 调用接口，整合返回结果
        if (CollectionUtils.isNotEmpty(sellerCodeList)) {
            ISO151403SellerRestParam sellerParam = new ISO151403SellerRestParam();
            sellerParam.setSellCodeList(sellerCodeList);
            List<ISO151403SellerRestResult> sellerRsResults = SoRestUtil.querySellerList(sellerParam);
            if (CollectionUtils.isNotEmpty(sellerRsResults)) {
                for (ISO151403SellerRestResult sellerRsResult : sellerRsResults) {
                    resultMap.get(sellerRsResult.getSlCode()).setUserNo(sellerRsResult.getSlCodeDis());
                    resultMap.get(sellerRsResult.getSlCode()).setUserName(sellerRsResult.getSlShowName());
                }
            } else {
                throw new BusinessException("查询卖家接口没有返回信息！");
            }
        }
        List<ISO151403SupplierRestResult> results = new ArrayList<>();
        for (ISO151403SupplierRestResult result : resultMap.values()) {
            results.add(result);
        }
        return results;
    }
}
