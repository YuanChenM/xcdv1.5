package com.msk.bms.order.service;

import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.utils.DbUtils;
import com.msk.bms.order.controller.common.SoRestUtil;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.business.constant.OrderCodeMasterDef;
import com.msk.common.config.CodeMasterManager;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.consts.BuyersConst;
import com.msk.common.service.ExcelAsyncPrintFasterService;
import com.msk.common.service.ExcelAsyncPrintService;
import com.msk.common.utils.RestClientUtil;
import com.msk.order.bean.ISO151501OrderAndDetailBean;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhang_qiang1 on 2016/8/11.
 */
@Component("SO151501ExportOrderAndDetailService")
public class SO151501ExportOrderAndDetailService extends ExcelAsyncPrintFasterService {

    private static Logger logger = LoggerFactory.getLogger(SO151501ExportOrderAndDetailService.class);

    @Override
    public List<Map<String, ?>> getDataSourceFaster(Object param) {
        List<Map<String, ?>> listMapParam=new ArrayList<>();
        BasePageParam baseParam = new BasePageParam();
        baseParam.getFilterMap().put("orderId", ((Map) param).get("orderId"));
        baseParam.getFilterMap().put("orderCode", ((Map) param).get("orderCode"));
        baseParam.getFilterMap().put("orderTimeStart", ((Map) param).get("orderTimeStart"));
        baseParam.getFilterMap().put("orderTimeEnd", ((Map) param).get("orderTimeEnd"));
        baseParam.getFilterMap().put("buyerCode", ((Map) param).get("buyerCode"));
        baseParam.getFilterMap().put("buyerName", ((Map) param).get("buyerName"));
        baseParam.getFilterMap().put("districtCode", ((Map) param).get("districtCode"));
        baseParam.getFilterMap().put("orderSource", ((Map) param).get("orderSource"));
        baseParam.getFilterMap().put("orderType", ((Map) param).get("orderType"));
        baseParam.getFilterMap().put("subOrderStatus", ((Map) param).get("subOrderStatus"));
        DbUtils.buildLikeCondition(baseParam, "orderCode", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(baseParam, "saName", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(baseParam, "buyerCode", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(baseParam, "buyerName", DbUtils.LikeMode.FRONT);
        logger.info("打印订单明细信息开始！");
        baseParam.setPaging(false);
        List<ISO151501OrderAndDetailBean> orderAndDetailResults = null;
        try {
            orderAndDetailResults = this.exportOrderAndDetail(baseParam);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("list", orderAndDetailResults);
        paramMap.put("sheetName","订单详细数据列表");
        listMapParam.add(paramMap);
        logger.info("打印订单明细信息结束！");
        return listMapParam;
    }

    /**
     * @param baseParam
     * @return
     */
    public List<ISO151501OrderAndDetailBean> exportOrderAndDetail(BasePageParam baseParam) throws Exception {
        RsRequest<BaseParam> request = new RsRequest<BaseParam>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(baseParam);
        String url = SystemServerManager.SoOrderApiServerManager.getExportOrderDetail();
        logger.info("打印订单明细信息url！" + url);
        RsResponse<ArrayList<Map<String, Object>>> rsResponse = RestClientUtil.post(url, request, new TypeReference<RsResponse<ArrayList<Map<String, Object>>>>() {
        });
        List<Map<String, Object>> listResult = rsResponse.getResult();
        List<ISO151501OrderAndDetailBean> result = new ArrayList<>();
        for (Map<String, Object> map : listResult) {
            ISO151501OrderAndDetailBean bean = new ISO151501OrderAndDetailBean();
            BeanUtils.populate(bean, map);
            this.setCodeMasterName(bean);
            result.add(bean);
        }
        return result;
    }


    /**
     * 设置 codeMaster
     *
     * @param bean
     */
    private void setCodeMasterName(ISO151501OrderAndDetailBean bean) {
        if (!StringUtil.isEmpty(bean.getOrderStatus())) {// 订单状态
            String orderStatusName = CodeMasterManager.getCodeMasterName(OrderCodeMasterDef.OrderStatus.TYPE, bean.getOrderStatus());
            bean.setOrderStatus(orderStatusName);
        }
        if (!StringUtil.isEmpty(bean.getOrderType())) {// 订单类型
            String orderTypeName = CodeMasterManager.getCodeMasterName(OrderCodeMasterDef.OrderType.TYPE, bean.getOrderType());
            bean.setOrderType(orderTypeName);
        }
        if (!StringUtil.isEmpty(bean.getBuyerType())) {// 买家类型
            String buyerTypeName = CodeMasterManager.getCodeMasterName(OrderCodeMasterDef.OrderBuyerType.TYPE, bean.getBuyerType());
            bean.setOrderType(buyerTypeName);
        }
    }

}
