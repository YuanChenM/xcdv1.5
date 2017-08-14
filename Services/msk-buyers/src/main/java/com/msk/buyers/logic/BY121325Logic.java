package com.msk.buyers.logic;


import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.buyers.bean.*;
import com.msk.buyers.utils.RestCommUtil;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.business.constant.BuyersConstant;
import com.msk.common.config.CodeMasterManager;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.service.ExcelAsyncPrintService;
import com.msk.common.utils.RestClientUtil;
import com.msk.core.entity.ByBuyerDelivery;
import com.msk.order.bean.ISO151407RsResult;
import com.msk.order.bean.OrderCountBean;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *
 */
@Component("BY121325Report")
public class BY121325Logic extends ExcelAsyncPrintService {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(BY121325Logic.class);

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }


    @Override
    public Map<String, ?> getDataSource(Object objectParam) {
        Map<String, Object> excelMap = new HashMap<>();
        Map<String, String> temp = (Map) objectParam;
        String buyerId = temp.get("buyerId");
        String fileStartTime = "";
        String fileEndTime = "";
        if(temp.get("fileStartTime") == "" || temp.get("fileStartTime") == null){
             fileStartTime = temp.get("fileStartTime");
        }else {
             fileStartTime = temp.get("fileStartTime")+" 00:00:00";
        }

        if(temp.get("fileEndTime") == "" || temp.get("fileEndTime") == null){
            fileEndTime = temp.get("fileEndTime");
        }else {
            fileEndTime = temp.get("fileEndTime") +  " 23:59:59";
        }
        String lgcsAreaCode = temp.get("lgcsAreaCode");
        String cityCode = temp.get("cityCode");
        //获取下单信息
        List<BY121321Bean> singleMode = this.findSingleMode(buyerId, fileStartTime, fileEndTime);

        //查询配送信息
        List<ByBuyerDelivery> distributionInformation = this.findDistributionInformation(buyerId);
        BY121325Bean by121325Bean = this.findResTime(buyerId);


        String paymentList = "";
        for (int j = 0; j < by121325Bean.getPaymentList().size(); j++) {
            if (by121325Bean.getPaymentList().get(j).getIsPayChecked() == "1") {
                if (paymentList == "") {
                    paymentList = paymentList + by121325Bean.getPaymentList().get(j).getPayMethodName();
                } else {
                    paymentList = paymentList + "," + by121325Bean.getPaymentList().get(j).getPayMethodName();
                }
            }
        }


        //查询冻品管家管控信息
        BY121322RsParam rsParam = new BY121322RsParam();
        rsParam.setBuyerId(buyerId);
        rsParam.setCityCode(cityCode);
        SimpleDateFormat formats = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        rsParam.setLgcsAreaCode(lgcsAreaCode);
        rsParam.setStartTime(fileStartTime);
        rsParam.setEndTime(fileEndTime);
        rsParam.setSearchType("1");
        rsParam.setPaging(false);
        List<BY121122Bean> by121122BeanList = new ArrayList<BY121122Bean>();
        RsResponse<BY121322RsResult> by121322RsResult = RestCommUtil.getSaleMarketInfoForBuyerPool(rsParam);
        List<BY121322Bean> SaleMarketInfoForBuyerPool = by121322RsResult.getResult().getSlHouseSaleList();

        for(int i=0;i<SaleMarketInfoForBuyerPool.size();i++){
            BY121122Bean by121122Bean = new BY121122Bean();
            if(SaleMarketInfoForBuyerPool.get(i).getStartTime() != null){
                by121122Bean.setStartTime(formats.format(SaleMarketInfoForBuyerPool.get(i).getStartTime()));
            }

            if(SaleMarketInfoForBuyerPool.get(i).getEndTime() != null){
                by121122Bean.setEndTime(formats.format(SaleMarketInfoForBuyerPool.get(i).getEndTime()));
            }
            by121122Bean.setBuyerPoolName(SaleMarketInfoForBuyerPool.get(i).getBuyerPoolName());
            by121122Bean.setChangeReason(SaleMarketInfoForBuyerPool.get(i).getChangeReason());
            by121122Bean.setHouseShowName(SaleMarketInfoForBuyerPool.get(i).getHouseShowName());
            by121122Bean.setDevelopmentWay(SaleMarketInfoForBuyerPool.get(i).getDevelopmentWay());
            by121122BeanList.add(by121122Bean);
        }

        excelMap.put("singleMode", singleMode);
        excelMap.put("distributionInformation", distributionInformation);
        excelMap.put("earliestRecTime", by121325Bean.getEarliestRecTime());
        excelMap.put("latestRecTime", by121325Bean.getLatestRecTime());
        excelMap.put("paymentList", paymentList);
        excelMap.put("recTime", by121325Bean.getRecTimeValue());
        excelMap.put("by121122BeanList", by121122BeanList);


        return excelMap;
    }

    /**
     * 获取下单次数
     *
     * @param buyerId
     * @param fileStartTime
     * @param fileEndTime
     * @return
     */
    public List<BY121321Bean> findSingleMode(String buyerId, String fileStartTime, String fileEndTime) {
        logger.info("调用下单方式数量接口");
        RsRequest<BY121321Param> request = new RsRequest<BY121321Param>();
        BY121321Param param = new BY121321Param();
        param.setBuyerId(buyerId);
        param.setStartDate(DateTimeUtil.parseDate(fileStartTime, "yyyy-MM-dd HH:mm:ss"));
        param.setEndDate(DateTimeUtil.parseDate(fileEndTime, "yyyy-MM-dd HH:mm:ss"));
        param.setBuyerId(buyerId);
        request.setSiteCode("1");
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setParam(param);
        //      String url = "http://10.20.16.84:8080/so-order/api/so/orderSource/search";
        String url = SystemServerManager.SoOrderServerManage.getQueryOrderSourceCount();
        RsResponse<ISO151407RsResult> beanRsResponse = RestClientUtil.post(url, request, new TypeReference<RsResponse<ISO151407RsResult>>() {
        });
        List<BY121321Bean> resutList = new ArrayList<>();
        BY121321Bean by121321Bean = new BY121321Bean();
        by121321Bean.setOrderType("下单方式");
      /*  List<OrderCountBean> list= ;*/
        if (beanRsResponse.getStatus().equals(SystemConst.RsStatus.SUCCESS)) {
            if (!CollectionUtils.isEmpty(beanRsResponse.getResult().getOrderCountInfoList())) {
                for (OrderCountBean bean : beanRsResponse.getResult().getOrderCountInfoList()) {
                    String orderPlatform = bean.getOrderPlatform();
                    Integer count = bean.getOrderCount();
                    if (orderPlatform.equals("1")) {
                        by121321Bean.setFrozen(count);
                    }
                    if (orderPlatform.equals("2")) {
                        by121321Bean.setFrozenB2b(count);
                    }
                    if (orderPlatform.equals("3")) {
                        by121321Bean.setOrderMicroMall(count);
                    }
                    if (orderPlatform.equals("4")) {
                        by121321Bean.setBuyerApp(count);
                    }
                }
                //云冻品和云冻品b2b属于pc端
            }
           /* else {
                by121321Bean.setFrozen(0);
                by121321Bean.setFrozenB2b(0);
                by121321Bean.setOrderMicroMall(0);
                by121321Bean.setBuyerApp(0);
            }*/
        }
        if (by121321Bean.getFrozen() == null) {
            by121321Bean.setFrozen(0);
        }
        if (by121321Bean.getFrozenB2b() == null) {
            by121321Bean.setFrozenB2b(0);
        }
        if (by121321Bean.getOrderMicroMall() == null) {
            by121321Bean.setOrderMicroMall(0);
        }
        if (by121321Bean.getBuyerApp() == null) {
            by121321Bean.setBuyerApp(0);
        }
        by121321Bean.setOrderPC(by121321Bean.getFrozen() + by121321Bean.getFrozenB2b());
        by121321Bean.setOrderQq(0);
        by121321Bean.setOrderWeChat(0);
        by121321Bean.setOrderTel(0);
        Integer count = by121321Bean.getOrderPC() + by121321Bean.getOrderMicroMall() +
                by121321Bean.getOrderQq() + by121321Bean.getOrderWeChat() + by121321Bean.getOrderTel();
        by121321Bean.setTotalOrder(count);
        by121321Bean.setTotalOrder(count);
        resutList.add(by121321Bean);
        return resutList;
    }

    /**
     * 查询配送信息
     *
     * @param buyerId
     * @return
     */
    public List<ByBuyerDelivery> findDistributionInformation(String buyerId) {

        List<ByBuyerDelivery> result = new ArrayList<>();
        RsRequest<BY121314Param> paramRsRequest = new RsRequest<>();
        BY121314Param by121314Param = new BY121314Param();
        by121314Param.setBuyerId(buyerId);
        by121314Param.setPageCount(10);
        by121314Param.setStartNo(0);
        paramRsRequest.setAuth("MSK00001");
        paramRsRequest.setLoginId("msk01");
        paramRsRequest.setSiteCode("1");
        paramRsRequest.setParam(by121314Param);
//        String url = "http://localhost:8480/msk-buyers/api/by/deliveryAddr/query";
        String url = SystemServerManager.BuyersServerManage.getQueryDeliveryAddr();
        RsResponse<BY121314RsPageResult> pageResultlist = RestClientUtil.post(url, paramRsRequest, new TypeReference<RsResponse<BY121314RsPageResult>>() {
        });

        if (!CollectionUtils.isEmpty(pageResultlist.getResult().getBrOBuyerInfoList())) {
            List<ByBuyerDelivery> res = pageResultlist.getResult().getBrOBuyerInfoList();
            for (int i = 0; i < res.size(); i++) {
                if (res.get(i).getIsDefault().equals("1")) {
                    res.get(i).setIsDefault("默认地址");
                } else {
                    res.get(i).setIsDefault("-");
                }
            }
        }
        result.addAll(pageResultlist.getResult().getBrOBuyerInfoList());
        return result;
    }

    /**
     * 查询买家时间段
     *
     * @param buyerId
     * @return
     */
    public BY121325Bean findResTime(String buyerId) {
        //根据买家ID获取买家收货时间,买家习惯收货时间,最早时间,最晚时间
        RsRequest<BaseParam> paramRsRequest = new RsRequest<>();
        BaseParam param = new BaseParam();
        param.setFilter("buyerId", buyerId);
        paramRsRequest.setAuth("MSK00001");
        paramRsRequest.setLoginId("msk01");
        paramRsRequest.setSiteCode("1");
        paramRsRequest.setParam(param);
//        String url = "http://localhost:8480/msk-buyers/api/by/deliveryTimeAndPay/query";
        String url = SystemServerManager.BuyersServerManage.getQueryDeliveryTimeAndPay();
        RsResponse<IBY121314RsBean> byRecTime = RestClientUtil.post(url, paramRsRequest, new TypeReference<RsResponse<IBY121314RsBean>>() {
        });

        Map<String, String> recTime = CodeMasterManager.findCodeMasterMap(BuyersConstant.HabitReceivePeriodType.TYPE);
        TreeMap<String, String> treeMap = new TreeMap<>();
        treeMap.putAll(recTime);
        String RectimeValue = "";
        for(String key : recTime.keySet()){
            if(key.equals(byRecTime.getResult().getHabitRecTime())){
                RectimeValue = recTime.get(key);
            }
        }
        //支付方式
        Map<String, String> paymentMethod = CodeMasterManager.findCodeMasterMap(BuyersConstant.PaymentMethod.Type);
        treeMap.clear();
        treeMap.putAll(paymentMethod);
        //整合支付方式数据
        List<String> payTypeList = new ArrayList();
        if (!StringUtil.isNullOrEmpty(byRecTime.getResult().getPaymentType())) {
            //分解买家支付方式
            String payType[] = byRecTime.getResult().getPaymentType().split(",");
            payTypeList = Arrays.asList(payType);
        }
        List<IBY121314RsBean> paymentList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(payTypeList)) {
            for (String key : treeMap.keySet()) {
                IBY121314RsBean recBean = new IBY121314RsBean();
                recBean.setPayMethod(key);
                recBean.setPayMethodName(treeMap.get(key));
                recBean.setIsPayChecked("0");
                for (int j = 0; j < payTypeList.size(); j++) {
                    if (key.equals(payTypeList.get(j))) {
                        recBean.setIsPayChecked("1");
                        break;
                    }
                }
                paymentList.add(recBean);
            }
        }



        IBY121314RsBean result = byRecTime.getResult();
        BY121325Bean bean = new BY121325Bean();
        bean.setEarliestRecTime(byRecTime.getResult().getEarliestRecTime());
        bean.setIby121314RsBean(result);
        bean.setRecTimeValue(RectimeValue);
        bean.setLatestRecTime(byRecTime.getResult().getLatestRecTime());
        bean.setPaymentList(paymentList);

        return bean;
    }


}
