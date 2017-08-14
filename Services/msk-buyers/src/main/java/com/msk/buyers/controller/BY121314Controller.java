package com.msk.buyers.controller;

import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.utils.DbUtils;
import com.msk.buyers.bean.BY121314Param;
import com.msk.buyers.bean.BY121314RsPageResult;
import com.msk.buyers.bean.IBY121314RsBean;
import com.msk.common.base.BaseController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.business.constant.BuyersConstant;
import com.msk.common.config.CodeMasterManager;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.utils.RestClientUtil;
import com.msk.core.entity.ByBuyerDelivery;
import com.msk.core.entity.ByBuyerRecTime;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * Created by zhou_yajun on 2016/7/8.
 */
@Controller
@RequestMapping("BY121314")
public class BY121314Controller extends BaseController {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(BY121314Controller.class);

    /**
     * 买家收货信息画面
     *
     * @param buyerId
     * @return
     */
    @RequestMapping(value = "init/{buyerId}")
    public String init(@PathVariable("buyerId") String buyerId, Model model) {
        logger.info("买家收货信息管控初始化");
        model.addAttribute("buyerId", buyerId);
        //根据买家ID获取买家收货时间,买家习惯收货时间,最早时间,最晚时间
        RsRequest<BaseParam> paramRsRequest = new RsRequest<>();
        BaseParam param = new BaseParam();
        param.setFilter("buyerId", buyerId);
        paramRsRequest.setAuth("MSK00001");
        paramRsRequest.setLoginId("msk01");
        paramRsRequest.setSiteCode("1");
        paramRsRequest.setParam(param);
        //String url = "http://localhost:8089/api/by/deliveryTimeAndPay/query";
        String url = SystemServerManager.BuyersServerManage.getQueryDeliveryTimeAndPay();
        RsResponse<IBY121314RsBean> byRecTime = RestClientUtil.post(url, paramRsRequest, new TypeReference<RsResponse<IBY121314RsBean>>() {
        });
        //List<IBY121314RsBean> byBuyerRecTimeList = byRecTime.getResult().getByBuyerRecTimeList();
            model.addAttribute("byRecTime", byRecTime.getResult());

        //从redis中获取买家收货时间段
        //Map<String, String> recTime = redisDao.getRedisMapValue(BuyersConstant.ReceivePeriodType.TYPE);
        Map<String, String> recTime = CodeMasterManager.findCodeMasterMap(BuyersConstant.HabitReceivePeriodType.TYPE);
        Map<String,String> arrayMap = new TreeMap<>(
                new Comparator<String>() {
                    public int compare(String obj1, String obj2) {
                        return StringUtil.toInteger(obj1).compareTo(StringUtil.toInteger(obj2));
                    }
                });
        arrayMap.putAll(recTime);
        model.addAttribute("recTime", arrayMap);

        //最早收货时间
        List<String> earliestRecTime = new ArrayList<>();
        //最晚收货时间
        List<String> latestRecTime = new ArrayList<>();
        if((!StringUtil.isNullOrEmpty(byRecTime.getResult().getHabitRecTime()))){
            String earlyTime = recTime.get(byRecTime.getResult().getHabitRecTime()).split("-")[0];
            String subEarlyTime=earlyTime.substring(0,earlyTime.lastIndexOf(":"));
            Integer changeHour=Integer.valueOf(subEarlyTime)+1;
            String subEndEarlyTime=earlyTime.substring(earlyTime.lastIndexOf(":")+1);
            if(subEndEarlyTime.equals("00") && !subEarlyTime.equals("24")){
                if(changeHour<10){
                    earliestRecTime.add(earlyTime);
                    earliestRecTime.add(subEarlyTime+":30");
                    earliestRecTime.add("0"+changeHour+":00");
                    earliestRecTime.add("0"+changeHour+":30");
                    latestRecTime.add(earlyTime);
                    latestRecTime.add(subEarlyTime+":30");
                    latestRecTime.add("0"+changeHour+":00");
                    latestRecTime.add("0" + changeHour + ":30");
                }else{
                    earliestRecTime.add(earlyTime);
                    earliestRecTime.add(subEarlyTime+":30");
                    earliestRecTime.add(changeHour+":00");
                    earliestRecTime.add(changeHour+":30");
                    latestRecTime.add(earlyTime);
                    latestRecTime.add(subEarlyTime+":30");
                    latestRecTime.add(changeHour+":00");
                    latestRecTime.add(changeHour+":30");
                }
            }else if(subEndEarlyTime.equals("30")){
                if(changeHour<10){
                    earliestRecTime.add(earlyTime);
                    earliestRecTime.add("0"+changeHour+":00");
                    earliestRecTime.add("0"+changeHour+":30");
                    if((changeHour+1)>=10){
                        earliestRecTime.add((changeHour+1)+":00");
                    }else {
                        earliestRecTime.add("0"+(changeHour+1)+":00");
                    }
                    latestRecTime.add(earlyTime);
                    latestRecTime.add("0"+changeHour+":00");
                    latestRecTime.add("0"+changeHour+":30");
                    if((changeHour+1)>=10){
                        latestRecTime.add((changeHour+1)+":00");
                    }else {
                        latestRecTime.add("0"+(changeHour+1)+":00");
                    }
                }else{
                    earliestRecTime.add(earlyTime);
                    earliestRecTime.add(changeHour+":00");
                    earliestRecTime.add(changeHour+":30");
                    earliestRecTime.add((changeHour+1)+":00");
                    latestRecTime.add(earlyTime);
                    latestRecTime.add(changeHour+":00");
                    latestRecTime.add(changeHour+":30");
                    latestRecTime.add((changeHour+1)+":00");
                }
            }else{
                earliestRecTime.add(earlyTime);
                earliestRecTime.add(00+":30");
                earliestRecTime.add(01+":00");
                earliestRecTime.add(01+":30");
                latestRecTime.add(earlyTime);
                latestRecTime.add(00+":30");
                latestRecTime.add(01+":00");
                latestRecTime.add(01+":30");
            }
        }else{
            earliestRecTime=null;
            latestRecTime=null;
        }
        model.addAttribute("earliestRecTime", earliestRecTime);
        model.addAttribute("latestRecTime", latestRecTime);
        //支付方式
        Map<String, String> paymentMethod = CodeMasterManager.findCodeMasterMap(BuyersConstant.PaymentMethod.Type);
        TreeMap<String, String> treeMap = new TreeMap<>();
        treeMap.clear();
        treeMap.putAll(paymentMethod);
        model.addAttribute("paymentMethod", paymentMethod);
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
        model.addAttribute("paymentList", paymentList);

        return "buyers/BY121314";
    }

    /**
     * 分页查询数据
     *
     * @param param pageParam
     * @return 分页查询数据
     */
    @RequestMapping(value = "search", method = RequestMethod.POST)
    public
    @ResponseBody
    PageResult<ByBuyerDelivery> search(String buyerId, BasePageParam param) {
        logger.debug("买家收货信息查询");
        PageResult<ByBuyerDelivery> result = new PageResult<>();
        RsRequest<BY121314Param> paramRsRequest = new RsRequest<>();
/*        DbUtils.buildLikeCondition(param, "deliveryAddr", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(param, "referenceAddr", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(param, "manageAddr", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(param, "recPerName", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(param, "recPerTel", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(param, "recPerWechat", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(param, "recPerQq", DbUtils.LikeMode.PARTIAL);*/

        param.setFilter("buyerId",buyerId);
        BY121314Param requestParam = new BY121314Param();
        requestParam.setBuyerId(StringUtil.toString(param.getFilterMap().get("buyerId")));
        requestParam.setDeliveryAddr(StringUtil.toString(param.getFilterMap().get("deliveryAddr")));
        requestParam.setReferenceAddr(StringUtil.toString(param.getFilterMap().get("referenceAddr")));
        requestParam.setManageAddr(StringUtil.toString(param.getFilterMap().get("manageAddr")));
        requestParam.setRecPerName(StringUtil.toString(param.getFilterMap().get("recPerName")));
        requestParam.setRecPerTel(StringUtil.toString(param.getFilterMap().get("recPerTel")));
        requestParam.setRecPerWechat(String.valueOf(param.getFilterMap().get("recPerWechat")));
        requestParam.setRecPerQq(String.valueOf(param.getFilterMap().get("recPerQq")));
        requestParam.setPageCount(param.getPageSize());
        requestParam.setStartNo(param.getStartPos());

        paramRsRequest.setAuth("MSK00001");
        paramRsRequest.setLoginId("msk01");
        paramRsRequest.setSiteCode("1");
        paramRsRequest.setParam(requestParam);
//        String url = "http://localhost:8080/api/by/deliveryAddr/query";
        String url = SystemServerManager.BuyersServerManage.getQueryDeliveryAddr();
        RsResponse<BY121314RsPageResult> pageResultlist = RestClientUtil.post(url, paramRsRequest, new TypeReference<RsResponse<BY121314RsPageResult>>() {
        });
        if(!CollectionUtils.isEmpty(pageResultlist.getResult().getBrOBuyerInfoList())){
            List<ByBuyerDelivery> res = pageResultlist.getResult().getBrOBuyerInfoList();
            for(int i =0 ;i<res.size();i++){
                if("1".equals(res.get(i).getIsDefault())){
                    res.get(i).setIsDefault("默认地址");
                }else{
                    res.get(i).setIsDefault("-");
                }
            }
        }
        result.setData(pageResultlist.getResult().getBrOBuyerInfoList());
        result.setRecordsTotal(pageResultlist.getResult().getTotalCount());
        return result;
    }

    /**
     * 买家收货主信息保存
     *
     * @param iby121314RsBean
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public
    @ResponseBody
    void save(IBY121314RsBean iby121314RsBean) {
        //画面选择收货时间后更新DB
        String[] receiveTime = iby121314RsBean.getSelectRecTime();
        // 更新买家收货时间
        List<ByBuyerRecTime> recTimeList = new ArrayList<>();
        if (receiveTime == null) {
            ByBuyerRecTime byBuyerRecTime = new ByBuyerRecTime();
            byBuyerRecTime.setBuyerId(iby121314RsBean.getBuyerId());
            byBuyerRecTime.setRecPerType("");
            byBuyerRecTime.setTimeDescribe("");
            recTimeList.add(byBuyerRecTime);
        } else {
            //从redis中获取买家收货时间段
/*            Map<String, String> recTimeCommMap = CodeMasterManager.findCodeMasterMap(BuyersConstant.ReceivePeriodType.TYPE);
            for (int i = 0; i < receiveTime.length; i++) {
                if (recTimeCommMap.containsKey(receiveTime[i])) {
                    ByBuyerRecTime byBuyerRecTime = new ByBuyerRecTime();
                    byBuyerRecTime.setBuyerId(iby121314RsBean.getBuyerId());
                    byBuyerRecTime.setRecPerType(receiveTime[i]);
                    byBuyerRecTime.setTimeDescribe(recTimeCommMap.get(receiveTime[i]));
                    recTimeList.add(byBuyerRecTime);
                }
            }*/
        }
        RsRequest<List<ByBuyerRecTime>> param = new RsRequest<>();
        param.setSiteCode("1");
        param.setAuth("MSK00001");
        param.setLoginId("msk01");
        param.setParam(recTimeList);
        //String mourl = "http://localhost:8089/api/by/receiveTime/update";
        String mourl = SystemServerManager.BuyersServerManage.getBuyerReceiveTimeUpdate();
        RsResponse<String> oresponse = RestClientUtil.post(mourl, param, new TypeReference<RsResponse<String>>() {
        });
        //画面选择支付方式后,整理数据更新DB
        String paymentType[] = iby121314RsBean.getSelectPaymentType();
        if (null != paymentType) {
            String paymentTypeStr = paymentType[0];
            for (int i = 1; i < paymentType.length; i++) {
                paymentTypeStr = paymentTypeStr + "," + paymentType[i];
            }
            iby121314RsBean.setPaymentType(paymentTypeStr);
        }
        //更新买家基本信息表
        RsRequest<IBY121314RsBean> request = new RsRequest<>();
        request.setSiteCode("1");
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setParam(iby121314RsBean);
        //String url = "http://localhost:8080/api/by/deliveryTimeAndPay/update";
        String url = SystemServerManager.BuyersServerManage.getUpdateDeliveryTimeAndPay();
        RsResponse<Integer> response = RestClientUtil.post(url, request, new TypeReference<RsResponse<Integer>>() {
        });
    }

    @RequestMapping(value = "recTimeChange/{habitRec}",
            method = RequestMethod.POST)
    public
    @ResponseBody
    Map<String,List<String>> findRecTimeList(@PathVariable("habitRec") String habitRec) {
        Map<String, String> recTime = CodeMasterManager.findCodeMasterMap(BuyersConstant.HabitReceivePeriodType.TYPE);
        TreeMap<String, String> treeMap = new TreeMap<>();
        treeMap.putAll(recTime);

        Map<String, List<String>> resultMap = new HashMap<>();
        //整合收货时间数据
        //List<IBY121314RsBean> recTimeList = new ArrayList<>();
        //最早收货时间
        List<String> changeRecTime = new ArrayList<>();
        //最晚收货时间
        List<String> changeLateTime = new ArrayList<>();
        if((!StringUtil.isNullOrEmpty(treeMap.get(habitRec)))){
            String earlyTime = treeMap.get(habitRec).split("-")[0];
            String subEarlyTime=earlyTime.substring(0,earlyTime.lastIndexOf(":"));
            Integer changeHour=Integer.valueOf(subEarlyTime)+1;
            String subEndEarlyTime=earlyTime.substring(earlyTime.lastIndexOf(":")+1);
            if(subEndEarlyTime.equals("00") && !subEarlyTime.equals("24")){
                if(changeHour<10){
                    changeRecTime.add(earlyTime);
                    changeRecTime.add(subEarlyTime+":30");
                    changeRecTime.add("0"+changeHour+":00");
                    changeRecTime.add("0"+changeHour+":30");
                }else{
                    changeRecTime.add(earlyTime);
                    changeRecTime.add(subEarlyTime+":30");
                    changeRecTime.add(changeHour+":00");
                    changeRecTime.add(changeHour+":30");
                }
            }else if(subEndEarlyTime.equals("30")){
                if(changeHour<10){
                    changeRecTime.add(earlyTime);
                    changeRecTime.add("0"+changeHour+":00");
                    changeRecTime.add("0"+changeHour+":30");
                    if((changeHour+1)>=10){
                        changeRecTime.add((changeHour+1)+":00");
                    }else {
                        changeRecTime.add("0"+(changeHour+1)+":00");
                    }
                }else{
                    changeRecTime.add(earlyTime);
                    changeRecTime.add(changeHour+":00");
                    changeRecTime.add(changeHour+":30");
                    changeRecTime.add((changeHour+1)+":30");
                }
            }else{
                changeRecTime.add(earlyTime);
                changeRecTime.add("00:30");
                changeRecTime.add("01:00");
                changeRecTime.add("01:30");
            }
        }else{
            changeRecTime=null;
        }
        resultMap.put("early",changeRecTime);
        changeLateTime.addAll(changeRecTime);
        Collections.reverse(changeLateTime);
        resultMap.put("late",changeLateTime);
        return resultMap;
    }

}
