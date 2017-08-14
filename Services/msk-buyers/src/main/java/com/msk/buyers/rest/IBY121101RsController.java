package com.msk.buyers.rest;

import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.utils.StringUtil;
import com.msk.buyers.bean.IBY121101RsResult;
import com.msk.buyers.bean.IBY121314RsBean;
import com.msk.buyers.utils.BuyerTypeUtil;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.business.constant.BuyersConstant;
import com.msk.common.config.CodeMasterManager;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.utils.RestClientUtil;
import com.msk.core.entity.CommConstant;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * Created by tao_zhifa on 2016/7/13.
 */
@RestController
public class IBY121101RsController extends BaseRsController {
    /** logger */
    private static Logger logger = LoggerFactory.getLogger(IBY121101RsController.class);
    /**
     * master数据取得
     * @param param
     * @return
     */
    @RequestMapping(value = "/by/common/master",
            method = RequestMethod.POST,
            produces = { MediaType.APPLICATION_JSON_VALUE })
    public RsResponse<List<CommConstant>> getBuyersListValue(@RequestBody RsRequest<CommConstant> param){
        RsResponse<List<CommConstant>> rs = new RsResponse<>();
        Map<String,String> map;

        if(param.getParam().getConstantType().equals("BuyerType")) {
            map = BuyerTypeUtil.getInstance().getBuyerTypeMap();
        }else {
            map = CodeMasterManager.findCodeMasterMap(param.getParam().getConstantType());
        }

        List<CommConstant> list = new ArrayList();

        Set set = map.keySet();
        Iterator it = set.iterator();
        while(it.hasNext()){
            CommConstant commConstant = new CommConstant();
            String value = (String)it.next();
            commConstant.setConstantValue(value);
            commConstant.setConstantName(map.get(value));
            list.add(commConstant);
        }

        if(!CollectionUtils.isEmpty(list)){
            rs.setMessage(" master数据取得成功");
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setResult(list);
        }else{
            rs.setMessage(" master数据取得失败");
            rs.setStatus(SystemConst.RsStatus.FAIL);
        }
        return rs;

    }



    /**
     * 获取时间段
     * @param basicParam
     * @return
     */
    @RequestMapping(value = "/by/common/timesList",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<IBY121101RsResult> getBuyersTimesListValues(@RequestBody RsRequest<IBY121314RsBean> basicParam) {
        RsResponse<IBY121101RsResult> rs = new RsResponse<>();
        IBY121101RsResult by121106RePageRest = new IBY121101RsResult();
        //根据买家ID获取买家收货时间,买家习惯收货时间,最早时间,最晚时间
        RsRequest<BaseParam> paramRsRequest = new RsRequest<>();
        BaseParam param = new BaseParam();
        param.setFilter("buyerId", basicParam.getParam().getBuyerId());
        paramRsRequest.setAuth("MSK00001");
        paramRsRequest.setLoginId("msk01");
        paramRsRequest.setSiteCode("1");
        paramRsRequest.setParam(param);
        //String url = "http://localhost:8089/api/by/deliveryTimeAndPay/query";
        String url = SystemServerManager.BuyersServerManage.getQueryDeliveryTimeAndPay();
        RsResponse<IBY121314RsBean> byRecTime = RestClientUtil.post(url, paramRsRequest, new TypeReference<RsResponse<IBY121314RsBean>>() {
        });
        List<IBY121314RsBean> byBuyerRecTimeList = byRecTime.getResult().getByBuyerRecTimeList();

 /*       Map<String, String> map = CodeMasterManager.findCodeMasterMap(BuyersConstant.HabitReceivePeriodType.TYPE);
        TreeMap<String, String> treeMap = new TreeMap<>();
        treeMap.putAll(map);*/

        Map<String, String> recTime = CodeMasterManager.findCodeMasterMap(BuyersConstant.HabitReceivePeriodType.TYPE);
        Map<String,String> treeMap = new TreeMap<>(
                new Comparator<String>() {
                    public int compare(String obj1, String obj2) {
                        return StringUtil.toInteger(obj1).compareTo(StringUtil.toInteger(obj2));
                    }
                });
        treeMap.putAll(recTime);
        List<IBY121314RsBean> recTimeList = new ArrayList<>();
        List<String> earliestRecTime = new ArrayList<>();
        List<List<String>> latestRecTime = new ArrayList<>();
        for (String key : treeMap.keySet()) {
            IBY121314RsBean recBean = new IBY121314RsBean();
            recBean.setRecPerType(key);
            recBean.setTimeDescribe(treeMap.get(key));
            recBean.setIsChecked("0");
            recBean.setHabitRecTime(key);
            latestRecTime.add(earliestRecTime);
            if (!CollectionUtils.isEmpty(byBuyerRecTimeList)) {
                for (int j = NumberConst.IntDef.INT_ZERO; j < byBuyerRecTimeList.size(); j++) {
                    if (key.equals(byBuyerRecTimeList.get(j).getRecPerType())) {
                        recBean.setIsChecked("1");
                        String startTime = treeMap.get(key).split("-")[0];
                        String endTime = treeMap.get(key).split("-")[1];
                        String middenTime = startTime.split(":")[0];
                        if(middenTime.equals("24")){
                            earliestRecTime.add(startTime);
                            earliestRecTime.add("00:30");
                            earliestRecTime.add("01:00");
                        }else{
                            if(Integer.valueOf(middenTime) < 10){
                                if(startTime.split(":")[1].equals("00")){
                                    earliestRecTime.add(startTime);
                                    earliestRecTime.add((middenTime)+":30");
                                    earliestRecTime.add("0"+(Integer.valueOf(middenTime)+1) + ":00");
                                }else {
                                    earliestRecTime.add(startTime);
                                    earliestRecTime.add("0"+(Integer.valueOf(middenTime)+1) + ":00");
                                    earliestRecTime.add("0"+(Integer.valueOf(middenTime)+1) + ":30");
                                }
                            }else {
                                if(startTime.split(":")[1].equals("00")){
                                    earliestRecTime.add(startTime);
                                    earliestRecTime.add((middenTime)+":30");
                                    earliestRecTime.add(Integer.valueOf(middenTime)+1 + ":00");
                                }else {
                                    earliestRecTime.add(startTime);
                                    earliestRecTime.add(Integer.valueOf(middenTime)+1 + ":00");
                                    earliestRecTime.add(Integer.valueOf(middenTime)+1 + ":30");
                                }
                            }
                        }
                        break;
                    }
                }
            }
            recTimeList.add(recBean);
        }
        by121106RePageRest.setEarliestRecTime(earliestRecTime);
        by121106RePageRest.setLatestRecTime(latestRecTime);
        by121106RePageRest.setRecTimeList(recTimeList);
        rs.setResult(by121106RePageRest);
        rs.setMessage(" master数据取得成功");
        rs.setStatus(SystemConst.RsStatus.SUCCESS);
        return rs;
    }

    /**
     * 根据时间段获取买家习惯时间
     * @param basicParam
     * @return
     */
    @RequestMapping(value = "/by/common/oneTime",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<List<String>> getBuyersOneTimeValues(@RequestBody RsRequest<IBY121314RsBean> basicParam) {
        RsResponse<List<String>>  rs = new RsResponse<>();
        String timeDescribe =  basicParam.getParam().getTimeDescribe();
        List<String> earliestRecTime = new ArrayList<>();
        String startTime = timeDescribe.split("-")[0];
        String middenTime = startTime.split(":")[0];
        if(Integer.valueOf(middenTime) < 10){
            earliestRecTime.add(startTime);
            earliestRecTime.add((middenTime)+":30");
            earliestRecTime.add("0"+(Integer.valueOf(middenTime)+1) + ":00");
            earliestRecTime.add("0"+(Integer.valueOf(middenTime)+1) + ":30");
        }else {
            earliestRecTime.add(startTime);
            earliestRecTime.add((middenTime)+":30");
            earliestRecTime.add(Integer.valueOf(middenTime)+1 + ":00");
            earliestRecTime.add(Integer.valueOf(middenTime)+1 + ":30");
        }
        rs.setResult(earliestRecTime);
        rs.setMessage(" master数据取得成功");
        rs.setStatus(SystemConst.RsStatus.SUCCESS);
        return rs;
    }

    /**
     * 获取支付方式
     * @param basicParam
     * @return
     */
    @RequestMapping(value = "/by/common/payList",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<List<IBY121314RsBean>> getBuyerspalistValues(@RequestBody RsRequest<IBY121314RsBean> basicParam) {
        //支付方式
        RsResponse<List<IBY121314RsBean>> rs = new RsResponse<>();
        Map<String, String> paymentMethod =CodeMasterManager.findCodeMasterMap(BuyersConstant.PaymentMethod.Type);
        //根据买家ID获取买家收货时间,买家习惯收货时间,最早时间,最晚时间
        RsRequest<BaseParam> paramRsRequest = new RsRequest<>();
        BaseParam param = new BaseParam();
        param.setFilter("buyerId", basicParam.getParam().getBuyerId());
        paramRsRequest.setAuth("MSK00001");
        paramRsRequest.setLoginId("msk01");
        paramRsRequest.setSiteCode("1");
        paramRsRequest.setParam(param);
        //String url = "http://localhost:8089/api/by/deliveryTimeAndPay/query";
        String url = SystemServerManager.BuyersServerManage.getQueryDeliveryTimeAndPay();
        RsResponse<IBY121314RsBean> byRecTime = RestClientUtil.post(url, paramRsRequest, new TypeReference<RsResponse<IBY121314RsBean>>() {
        });
        TreeMap<String, String> treeMap = new TreeMap<>();
        treeMap.putAll(paymentMethod);
        //整合支付方式数据
        List<String> payTypeList = new ArrayList();
        if (!StringUtil.isNullOrEmpty(byRecTime.getResult().getPaymentType())) {
            //分解买家支付方式
            String payType[] = byRecTime.getResult().getPaymentType().split(",");
            payTypeList = Arrays.asList(payType);
        }

        List<IBY121314RsBean> paymentList = new ArrayList<>();

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

        rs.setResult(paymentList);
        rs.setMessage(" master数据取得成功");
        rs.setStatus(SystemConst.RsStatus.SUCCESS);
        return rs;
    }

    /**
     * 获取时间段
     * @param basicParam
     * @return
     */
    @RequestMapping(value = "/by/common/allTimesList",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<List<String>> getBuyersAllTimesList(@RequestBody RsRequest<IBY121314RsBean> basicParam) {
        RsResponse<List<String>> rs = new RsResponse();
        List<String> resTime = new ArrayList<>();
        String time = "00";
        String times = "";
        for(int i=0;i<24;i++){
            if(i<9){
                times = "0" +(Integer.valueOf(time) + 1 )+ ":00";
            }else {
                times =(Integer.valueOf(time) + 1 )+ ":00";
            }
            resTime.add(times);
            time = Integer.valueOf(time) + 1 +"";
        }
        rs.setResult(resTime);
        rs.setMessage(" master数据取得成功");
        rs.setStatus(SystemConst.RsStatus.SUCCESS);
        return rs;
    }

    /**
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/by/common/fileServerUrl",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<Map<String,String>> getFileServerIp(@RequestBody RsResponse<BaseParam> param){
        RsResponse<Map<String,String>> rs = new RsResponse<>();
        String fileUploadServerUrl = SystemServerManager.CommonServerManager.getGetFlieServerUploadForJsp();
        String fileDownLoadServerUrl = SystemServerManager.CommonServerManager.getMskFlieDownLoadServers();
        Map<String,String> urlMap = new HashMap<>();
        urlMap.put("fileUploadServerUrl",fileUploadServerUrl);
        urlMap.put("fileDownLoadServerUrl",fileDownLoadServerUrl);
        rs.setResult(urlMap);
        rs.setMessage("文件下载IP获取成功");
        rs.setStatus(SystemConst.RsStatus.SUCCESS);
        return rs;
    }
}
