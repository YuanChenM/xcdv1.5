package com.msk.buyers.controller;

import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.msk.buyers.bean.BY121321Bean;
import com.msk.buyers.bean.BY121321Param;
import com.msk.common.base.BaseController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.utils.RestClientUtil;
import com.msk.order.bean.ISO151407RsResult;
import com.msk.order.bean.OrderCountBean;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 */
@Controller
@RequestMapping("BY121321")
public class BY121321Controller extends BaseController {


    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(BY121321Controller.class);


    /**
     * 销售期分销买家平台下单方式管控  页面初始化
     *
     * @param buyerId
     * @param model
     * @return
     */
    @RequestMapping(value = "init/{buyerId}",
            method = RequestMethod.POST)
    public String init(@PathVariable("buyerId") String buyerId, Model model) {
        logger.debug("销售期分销买家平台下单方式管控 页面初始化");
        model.addAttribute("buyerId", buyerId);
        return "buyers/BY121321";
    }

    /**
     * 分页查询数据
     *
     * @return 分页查询数据
     */
    @RequestMapping(value = "search/{buyerId}", method = RequestMethod.POST)
    public
    @ResponseBody
    PageResult<BY121321Bean> search(@PathVariable("buyerId") String buyerId, BY121321Param by121321Param) {
        logger.info("调用下单方式数量接口");
        PageResult<BY121321Bean> result = new PageResult<BY121321Bean>();
        RsRequest<BY121321Param> request = new RsRequest<BY121321Param>();
        BY121321Param param = new BY121321Param();
        param.setStartDate(DateTimeUtil.parseDate(by121321Param.getStartTime()+ " 00:00:00", "yyyy-MM-dd HH:mm:ss"));
        param.setEndDate(DateTimeUtil.parseDate(by121321Param.getEndTime()+ " 23:59:59","yyyy-MM-dd HH:mm:ss"));
        param.setBuyerId(buyerId);
        request.setSiteCode("1");
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setParam(param);
//      String url = "http://10.20.16.84:8080/so-order/api/so/orderSource/search";
        String url = SystemServerManager.SoOrderServerManage.getQueryOrderSourceCount();
        RsResponse<ISO151407RsResult> beanRsResponse = RestClientUtil.post(url, request, new TypeReference<RsResponse<ISO151407RsResult>>() {
        });
        List<BY121321Bean> resultList = new ArrayList<>();
        BY121321Bean by121321Bean = new BY121321Bean();
        by121321Bean.setOrderType("下单次数(次)");
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
        resultList.add(by121321Bean);
   /*     result.setRecordsTotal(NumberConst.IntDef.INT_ZERO);*/
        result.setData(resultList);
        return result;
    }
}
