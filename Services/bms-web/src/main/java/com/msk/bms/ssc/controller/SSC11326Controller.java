package com.msk.bms.ssc.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.msk.bms.ssc.controller.common.ISSCProducePlanRestUtil;
import com.msk.common.base.BaseController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.utils.RestClientUtil;
import com.msk.core.entity.SscContractBasic;
import com.msk.ssc.bean.*;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * 生产商计划管理
 * @author liu_yan2
 */
@Controller
@RequestMapping("SSC11326")
public class SSC11326Controller extends BaseController {
    /**
     * logger
     */
    private static Logger logger = getLogger(SSC11326Controller.class);


    /**
     * 初始化页面
     *
     * @param model Model
     * @return 页面
     */
    @RequestMapping(value = "init", method = RequestMethod.POST)
    public String init(Model model) {
        logger.debug("生产商计划管理页面初始化");
        return "ssc/SSC11326";
    }

    /**
     * 查询合同生效日及交货期中的最后交货日
     * @return
     */
    @RequestMapping(value = "search", method = RequestMethod.POST)
    public String search(SSC11326RsParam param, Model model) {
        model.addAttribute("ssc11326RsBean",ISSCProducePlanRestUtil.findContractPlanInfo(param));
        return "ssc/SSC11326";
    }

    /**
     * 更新实际生产日期及实际结束日期
     * @return
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    public String updateContractBasic(SSC11304RsParam param, Model model) throws ParseException {
        super.setCommonParam(param);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        if (!StringUtil.isEmpty(param.getRealProduceStartDateStr())) {
            param.setRealProduceStartDate(formatter.parse(param.getRealProduceStartDateStr()));
        }
        if (!StringUtil.isEmpty(param.getRealProduceEndDateStr())) {
            param.setRealProduceEndDate(formatter.parse(param.getRealProduceEndDateStr()));
        }

        return ISSCProducePlanRestUtil.updateContractBasic(param);
    }

    /**
     * 批量保存或更新生产期/待运期管控
     * @return
     */
    @RequestMapping(value = "batchSaveOrUpdate", method = RequestMethod.POST)
    public String batchSaveOrUpdate(SSC11326RsParam param, Model model) throws ParseException {
        super.setCommonParam(param);
        String jsonStr = param.getJsonStr();
        List<SSC11326RsBean> ssc11326RsBeans = new ArrayList<SSC11326RsBean>();
        if(!StringUtil.isNullOrEmpty(jsonStr)) {
            Map<String, SSC11326RsBean> map = JSON.parseObject(jsonStr, new TypeReference<Map<String, SSC11326RsBean>>() {
            });
            for (SSC11326RsBean bean : map.values()) {
                bean.setCrtId(param.getCrtId());
                bean.setCrtTime(DateTimeUtil.getCustomerDate());
                bean.setUpdId(param.getUpdId());
                bean.setUpdTime(DateTimeUtil.getCustomerDate());
                ssc11326RsBeans.add(bean);
            }
        }
        param.setSsc11326RsBeans(ssc11326RsBeans);
        param.setJsonStr(null);
        ISSCProducePlanRestUtil.batchSaveOrUpdateProducePlan(param);
        return this.showDetail(param, model);
    }

    /**
     * 批量保存或更新运输期管控信息
     * @return
     */
    @RequestMapping(value = "batchSaveOrUpdateDelivery", method = RequestMethod.POST)
    public String batchSaveOrUpdateDelivery(SSC11326RsParam param, Model model) throws ParseException {
        super.setCommonParam(param);
        String jsonStr = param.getJsonStr();
        List<SSC1132601RsBean> ssc1132601RsBeans = new ArrayList<SSC1132601RsBean>();
        if(!StringUtil.isNullOrEmpty(jsonStr)) {
            Map<String, SSC1132601RsBean> map = JSON.parseObject(jsonStr, new TypeReference<Map<String, SSC1132601RsBean>>() {
            });
            for (SSC1132601RsBean bean : map.values()) {
                bean.setCrtId(param.getCrtId());
                bean.setCrtTime(DateTimeUtil.getCustomerDate());
                bean.setUpdId(param.getUpdId());
                bean.setUpdTime(DateTimeUtil.getCustomerDate());
                ssc1132601RsBeans.add(bean);
            }
        }
        param.setDeliveryPdControls(ssc1132601RsBeans);
        param.setJsonStr(null);
        ISSCProducePlanRestUtil.batchSaveOrUpdatePdControl(param);
        return this.showDetail(param, model);
    }

    /**
     * 查看生产商生产计划详细
     * @return
     */
    @RequestMapping(value = "detail", method = RequestMethod.POST)
    public String showDetail(SSC11326RsParam param, Model model) throws ParseException {
        //获取合同基本信息
        SSC11326RsBean ssc11326RsBean = ISSCProducePlanRestUtil.findContractPlanInfo(param);
        if (ssc11326RsBean != null) {
            model.addAttribute("contractInfo",ssc11326RsBean);

            String startDate = ssc11326RsBean.getRealProduceStartDate();
            startDate = StringUtil.isNullOrEmpty(startDate) ? ssc11326RsBean.getContractActDate() : startDate;
            String endDate = ssc11326RsBean.getRealProduceEndDate();
            endDate = StringUtil.isNullOrEmpty(endDate) ? ssc11326RsBean.getLastDeliveryDate() : endDate;
            param.setRealProduceEndDate(endDate);
            param.setRealProduceStartDate(startDate);
            //获取产品周期
            List<String> produceDates = this.getAllProduceDates(startDate, endDate);
            model.addAttribute("produceDates", produceDates);

            //获取运输期产品管控
            model.addAttribute("deliveryControl",ISSCProducePlanRestUtil.findDeliveryPdControl(param).getDeliveryPdControls());
            //获取入库产品管控
            model.addAttribute("stockProducts",this.handleStockProductData(ISSCProducePlanRestUtil.findStockProductDetail(param)));

            //获取生产期产品管控
            param.setType("1");
            SSC11326RsResult produceResult = ISSCProducePlanRestUtil.findProducePdControl(param);
            List<SSC11326RsBean> producePlans = produceResult.getSsc11326RsBeanList();
            List<SSC11326RsBean> totalByDateList = produceResult.getTotalByDateList();
            Map<String, List<SSC11326RsBean>> produceMap = this.handlePlanData(produceDates, producePlans);
            model.addAttribute("produceMap", produceMap);
            SSC11326RsResult newProduceResult = new SSC11326RsResult();
            newProduceResult.setTotalByDateList(this.handleTotalByDateData(produceDates, totalByDateList));
            newProduceResult.setTotalByNameList(produceResult.getTotalByNameList());
            model.addAttribute("produceTotal", newProduceResult);

            //获取待运期产品管控
            param.setType("2");
            SSC11326RsResult readyGoResult = ISSCProducePlanRestUtil.findProducePdControl(param);
            List<SSC11326RsBean> readyGoPlans = readyGoResult.getSsc11326RsBeanList();
            List<SSC11326RsBean> readyGoTotalByDateList = readyGoResult.getTotalByDateList();
            Map<String, List<SSC11326RsBean>> readyGoMap = this.handlePlanData(produceDates, readyGoPlans);
            model.addAttribute("readyGoMap", readyGoMap);
            SSC11326RsResult newReadyGoResult = new SSC11326RsResult();
            newReadyGoResult.setTotalByDateList(this.handleTotalByDateData(produceDates, readyGoTotalByDateList));
            newReadyGoResult.setTotalByNameList(readyGoResult.getTotalByNameList());
            model.addAttribute("readyGoTotal", newReadyGoResult);

        }
        return "ssc/SSC1132601";
    }

    /**
     * 根据车次分组数据
     * @param stockList
     * @return
     */
    private Map<String,List<SSC11317PrePdBean>> handleStockProductData(List<SSC11317PrePdBean> stockList){
        Map<String, List<SSC11317PrePdBean>> map = new TreeMap<String, List<SSC11317PrePdBean>>();
        for(SSC11317PrePdBean bean: stockList) {
            if (map.containsKey(bean.getDeliveryBatch())) {
                map.get(bean.getDeliveryBatch()).add(bean);
            } else {
                List<SSC11317PrePdBean> temp = new ArrayList<SSC11317PrePdBean>();
                temp.add(bean);
                map.put(bean.getDeliveryBatch(), temp);
            }
        }
        return map;
    }

    /**
     * 根据日期填充统计数据
     * @param produceDates
     * @param produceTotalList
     * @return
     */
    private List<SSC11326RsBean> handleTotalByDateData(List<String> produceDates, List<SSC11326RsBean> produceTotalList) {
        List<SSC11326RsBean> newList = new ArrayList<SSC11326RsBean>();
        BigDecimal allTotalPlanNum = BigDecimal.ZERO;
        BigDecimal allTotalRealNum = BigDecimal.ZERO;
        for(String produceDate: produceDates) {
            boolean withOutValue = true;
            for(SSC11326RsBean bean: produceTotalList) {
                if (!StringUtil.isNullOrEmpty(bean.getProduceDateStr()) && produceDate.equals(bean.getProduceDateStr())) {
                    if (bean.getTotalRealNum() != null) {
                        allTotalRealNum = allTotalRealNum.add(bean.getTotalRealNum());
                    } else {
                        bean.setTotalRealNum(BigDecimal.ZERO);
                    }
                    if (bean.getTotalPlanNum() != null) {
                        allTotalPlanNum = allTotalPlanNum.add(bean.getTotalPlanNum());
                    } else {
                        bean.setTotalPlanNum(BigDecimal.ZERO);
                    }
                    newList.add(bean);
                    withOutValue = false;
                    break;
                }
            }
            if (withOutValue) {
                SSC11326RsBean rsBean = new SSC11326RsBean();
                rsBean.setProduceDateStr(produceDate);
                rsBean.setTotalRealNum(BigDecimal.ZERO);
                rsBean.setTotalPlanNum(BigDecimal.ZERO);
                newList.add(rsBean);
            }
        }
        SSC11326RsBean rsBean = new SSC11326RsBean();
        rsBean.setTotalPlanNum(allTotalPlanNum);
        rsBean.setTotalRealNum(allTotalRealNum);
        newList.add(rsBean);
        return newList;
    }
    /**
     * 按照产品和日期分类处理数据
     * @param produceDates
     * @param ssc11326RsBeanList
     * @return
     * @throws ParseException
     */
    private Map<String, List<SSC11326RsBean>> handlePlanData(List<String> produceDates, List<SSC11326RsBean> ssc11326RsBeanList) throws ParseException {

        //根据产品编码分组数据
        Map<String, List<SSC11326RsBean>> map = new HashMap<String, List<SSC11326RsBean>>();
        for(SSC11326RsBean bean: ssc11326RsBeanList) {
            String key = bean.getDetailId() + "_" + bean.getPdName();
            if (map.containsKey(key)) {
                map.get(key).add(bean);
            } else {
                List<SSC11326RsBean> temp = new ArrayList<SSC11326RsBean>();
                temp.add(bean);
                map.put(key, temp);
            }
        }

        Map<String, List<SSC11326RsBean>> newMap = new HashMap<String, List<SSC11326RsBean>>();

        //根据日期填充数据
        for(String key: map.keySet()) {
            List<SSC11326RsBean> tempList = map.get(key);
            List<SSC11326RsBean> newList = new ArrayList<SSC11326RsBean>();
            for(String produceDate: produceDates) {
                boolean withOutValue = true;
                boolean readonly = this.compareDate(produceDate);
                for(SSC11326RsBean bean: tempList) {
                    if (!StringUtil.isNullOrEmpty(bean.getProduceDateStr()) && produceDate.equals(bean.getProduceDateStr())) {
                        bean.setReadonly(readonly);
                        newList.add(bean);
                        withOutValue = false;
                        break;
                    }
                }
                if (withOutValue) {
                    SSC11326RsBean rsBean = new SSC11326RsBean();
                    rsBean.setProduceDateStr(produceDate);
                    rsBean.setReadonly(readonly);
                    newList.add(rsBean);
                }
            }
            newMap.put(key, newList);
        }
        return newMap;
    }





    /**
     * 获取从开始日期到结束日期中间的所有日期
     * @param startDate
     * @param endDate
     * @return
     */
    private List<String> getAllProduceDates(String startDate, String endDate) throws ParseException {
        List<String> produceDates= new ArrayList<String>();
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Calendar startDay = Calendar.getInstance();
        Calendar endDay = Calendar.getInstance();
        startDay.setTime(formatter.parse(startDate));
        endDay.setTime(formatter.parse(endDate));
        Calendar currentDay = startDay;
        while (true) {
            produceDates.add(formatter.format(currentDay.getTime()));
            currentDay.add(Calendar.DATE, 1);
            if (currentDay.compareTo(endDay) > NumberConst.IntDef.INT_ZERO) {
                break;
            }
        }
        return produceDates;
    }

    private boolean compareDate(String produceDate) throws ParseException {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDay = formatter.parse(formatter.format(new Date()));
        Date produceDay = formatter.parse(produceDate);
        return produceDay.compareTo(currentDay) >= NumberConst.IntDef.INT_ZERO ? false : true;
    }
}
