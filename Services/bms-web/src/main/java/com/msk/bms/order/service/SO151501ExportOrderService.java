package com.msk.bms.order.service;

import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
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
import com.msk.common.utils.DecimalUtil;
import com.msk.common.utils.RestClientUtil;
import com.msk.order.bean.ISO151501Bean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhang_qiang1 on 2016/8/11.
 */
@Component("SO151501ExportOrderService")
public class SO151501ExportOrderService extends ExcelAsyncPrintFasterService {

    private static Logger logger = LoggerFactory.getLogger(SO151501ExportOrderService.class);

    @Override
    public List<Map<String, ?>> getDataSourceFaster(Object param) {
        List<Map<String, ?>> listMapParam = new ArrayList<>();
        Map<String, String> temp = (Map) param;
        BasePageParam baseParam = new BasePageParam();
        baseParam.getFilterMap().put("orderId", ((Map) temp).get("orderId"));
        baseParam.getFilterMap().put("orderCode", ((Map) temp).get("orderCode"));
        baseParam.getFilterMap().put("orderTimeStart", ((Map) temp).get("orderTimeStart"));
        baseParam.getFilterMap().put("orderTimeEnd", ((Map) temp).get("orderTimeEnd"));
        baseParam.getFilterMap().put("buyerCode", ((Map) temp).get("buyerCode"));
        baseParam.getFilterMap().put("buyerName", ((Map) temp).get("buyerName"));
        baseParam.getFilterMap().put("districtCode", ((Map) temp).get("districtCode"));
        baseParam.getFilterMap().put("orderSource", ((Map) temp).get("orderSource"));
        baseParam.getFilterMap().put("orderType", ((Map) temp).get("orderType"));
        baseParam.getFilterMap().put("subOrderStatus", ((Map) temp).get("subOrderStatus"));
        DbUtils.buildLikeCondition(baseParam, "orderCode", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(baseParam, "saName", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(baseParam, "buyerCode", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(baseParam, "buyerName", DbUtils.LikeMode.FRONT);
        PageResult<ISO151501Bean> pageResult = this.findAllOrders(baseParam);
        List<ISO151501Bean> list = pageResult.getData();
        logger.info("打印订单列表信息开始！");
        DecimalFormat qtyFormat = new DecimalFormat("###,###");
        DecimalFormat payAmountFormat = new DecimalFormat("###,##0.00");
        payAmountFormat.setRoundingMode(RoundingMode.FLOOR);
        List<ISO151501Bean> iso151501BeanList = new ArrayList<>();
        for (ISO151501Bean iso151501Bean : list){
            iso151501Bean.setOrderQtyFmt(qtyFormat.format(iso151501Bean.getOrderQty()));
            iso151501Bean.setOrderAmountFmt(payAmountFormat.format(iso151501Bean.getOrderAmount()));
            iso151501BeanList.add(iso151501Bean);
        }
        this.setCodeMasterName(iso151501BeanList);
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("list", iso151501BeanList);
        if (!CollectionUtils.isEmpty(iso151501BeanList)) {
            paramMap.put("allOrderQty", qtyFormat.format(iso151501BeanList.get(NumberConst.IntDef.INT_ZERO).getCurrentPageQty()));
            paramMap.put("allOrderPayAmount", payAmountFormat.format(iso151501BeanList.get(NumberConst.IntDef.INT_ZERO).getCurrentPageAmount()));
        }
        paramMap.put("sheetName","订单列表数据");
        listMapParam.add(paramMap);
        logger.info("打印订单列表信息结束！");
        return listMapParam;
    }


    /**
     * 设置codeMaster
     *
     * @param list
     */
    private void setCodeMasterName(List<ISO151501Bean> list) {
        if (!CollectionUtils.isEmpty(list)) {
            for (ISO151501Bean bean : list) {
                if (bean.getOrderStatus() != null) {// 订单状态
                    String orderStatusName = CodeMasterManager.getCodeMasterName(OrderCodeMasterDef.SubOrderStatus.TYPE, StringUtil.formatNum(bean.getOrderStatus()));
                    bean.setOrderStatusName(orderStatusName);
                }
                if (bean.getOrderType() != null) {// 订单类型
                    String orderTypeName = CodeMasterManager.getCodeMasterName(OrderCodeMasterDef.OrderType.TYPE, StringUtil.formatNum(bean.getOrderType()));
                    bean.setOrderTypeName(orderTypeName);
                }
                if (bean.getBuyerType() != null) {// 买家类型
                    String buyerTypeName = CodeMasterManager.getCodeMasterName(OrderCodeMasterDef.OrderBuyerType.TYPE, StringUtil.formatNum(bean.getBuyerType()));
                    bean.setBuyerTypeName(buyerTypeName);
                }
                if (bean.getOrderSource() != null) {// 订单来源
                    String orderSourceName = CodeMasterManager.getCodeMasterName(OrderCodeMasterDef.OrderSource.TYPE, StringUtil.formatNum(bean.getOrderSource()));
                    bean.setOrderSourceName(orderSourceName);
                }
            }
        }
    }

    /**
     * @param baseParam
     * @return
     */
    public PageResult<ISO151501Bean> findAllOrders(BasePageParam baseParam) {
        RsRequest<BaseParam> request = new RsRequest<BaseParam>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(baseParam);
        String url = SystemServerManager.SoOrderApiServerManager.getFindPageOrderList();
        RsResponse<PageResult<ISO151501Bean>> rsResponse = RestClientUtil.post(url, request, new TypeReference<RsResponse<PageResult<ISO151501Bean>>>() {
        });
        return rsResponse.getResult();
    }


}
