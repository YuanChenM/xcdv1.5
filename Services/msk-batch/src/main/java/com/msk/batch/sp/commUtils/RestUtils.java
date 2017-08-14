package com.msk.batch.sp.commUtils;

import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.DateTimeUtil;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.config.ConfigManager;
import com.msk.common.utils.RestClientUtil;
import com.msk.ds.bean.ISC1891I1RsParam;
import com.msk.ds.bean.ISC1891I1RsResult;
import com.msk.order.bean.ISO151434OrderRsResult;
import com.msk.order.bean.ISO151434RsParam;
import com.msk.price.bean.PriceCycleParam;
import com.msk.price.bean.PriceCycleResult;
import com.msk.price.utils.PriceCycleUtil;
import com.msk.stock.bean.StockResult;
import com.msk.stock.bean.StockRsParam;
import com.msk.stock.bean.StockRsParamList;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by zhu_kai1 on 2016/5/17.
 */
public class RestUtils {

    /**
     * 获取上半旬销售排名
     * @param iso151434RsParam
     * @return
     */
    public static RsResponse<ISO151434OrderRsResult> halfMonthOrder(ISO151434RsParam iso151434RsParam){
        RsRequest<ISO151434RsParam> request = new RsRequest<ISO151434RsParam>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(iso151434RsParam);
       /* String url = "http://localhost:8081/msk-order/api/so/order/halfmonthcount";*/
        String url = ConfigManager.getMskOrderService()+ ConfigManager.getOrderHalfMonthService();
        RsResponse<ISO151434OrderRsResult> seller = RestClientUtil.post(url, request, new TypeReference<RsResponse<ISO151434OrderRsResult>>() {
        });
        return seller;
    }

    /**
     * 接口获取本月上半月末期末库存
     * @return
     */
    public static  RsResponse<StockResult> getStockCnt(){
        //设置请求参数
        RsRequest<StockRsParamList> requestParam = new RsRequest<StockRsParamList>();
        requestParam.setAuth("MSK00001");
        requestParam.setLoginId("msk01");
        requestParam.setSiteCode("1");
        StockRsParamList paramList = new StockRsParamList();
        StockRsParam param= new StockRsParam();
        param.setSalePlatform("1");
        paramList.getPdList().add(param);
        requestParam.setParam(paramList);

        //url
        String url = ConfigManager.getMskStockService() + ConfigManager.getSnkPriceQueryStockBySellerListService();
        //请求接口
        // http://localhost:8080/api/so/queryStockBySupplier/list
        RsResponse<StockResult> response= RestClientUtil.post(url, requestParam, new TypeReference<RsResponse<StockResult>>(){});
        return response;
    }

    /**
     * 接口获取截止本月15日止的已列入供应计划尚未入库的供应量
     * @param param
     * @return
     */
    public static RsResponse<ISC1891I1RsResult> queryProductLotInfo(ISC1891I1RsParam param){
        RsRequest<ISC1891I1RsParam> request = new RsRequest<ISC1891I1RsParam>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(param);
        // http://localhost:8080/msk-ds/sc/undonum
        //请求接口
        String url = ConfigManager.getMskDsServices() + ConfigManager.getUndonumService();
        RsResponse<ISC1891I1RsResult> response= RestClientUtil.post(url, request, new TypeReference<RsResponse<ISC1891I1RsResult>>(){});
        return response;
    }

    /**
     * 根据当前日期获得价盘周期
     * @return 价盘周期
     */
    public static String getPriceCyclePeriod() {
        //设置请求参数
        RsRequest<PriceCycleParam> requestParam = new RsRequest<PriceCycleParam>();
        requestParam.setAuth("MSK00001");
        requestParam.setLoginId("msk01");
        requestParam.setSiteCode("1");
        //接口地址type=next 那么就传当前时间就行了
        Date date = DateTimeUtil.getCustomerDate();
        PriceCycleParam param = new PriceCycleParam();
        param.setCurrentDate(date);
        requestParam.setParam(param);

        //url
        String url = ConfigManager.getMskSnkPriceService() + ConfigManager.getSnkPricePriceCycleService();
        //请求接口
        RsResponse<PriceCycleResult> response = RestClientUtil.post(url, requestParam, new TypeReference<RsResponse<PriceCycleResult>>(){});
        if(null != response.getResult() && "S".equals(response.getStatus())) {
            return response.getResult().getCycleCode();
        } else {
            throw new BusinessException("请求返回价盘周期编码失败");
        }
    }
}
