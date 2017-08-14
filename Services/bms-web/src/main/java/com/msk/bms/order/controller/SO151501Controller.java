package com.msk.bms.order.controller;

import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.utils.DbUtils;
import com.msk.bms.order.controller.common.SoRestUtil;
import com.msk.common.base.BaseController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.business.constant.OrderCodeMasterDef;
import com.msk.common.config.CodeMasterManager;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.utils.RestClientUtil;
import com.msk.common.utils.StringUtil;
import com.msk.order.bean.ISO151501Bean;
import com.msk.order.bean.param.ISO151414DistrictParam;
import com.msk.order.bean.result.DistrictResult;
import com.msk.order.bean.result.LgcsAreaBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;
import java.util.Map;

/**
 * 订单列表页面
 * zhangqiang1
 */
@Controller
@RequestMapping("SO151501")
@SessionAttributes("param")
public class SO151501Controller extends BaseController {
    /**
     * logger
     *
     * @author zhangqiang1
     */
    private static Logger logger = LoggerFactory.getLogger(SO151501Controller.class);


    /**
     * 加载订单列表界面
     *
     * @return String
     */
    @RequestMapping(value = "init",
            method = RequestMethod.POST)
    public String init(Long orderId, Model model) {
        if (orderId != null) {// 从退货单跳到订单列表传的参数
            model.addAttribute("orderId", orderId);
        }
        List<LgcsAreaBean> districtList = this.queryDistrict();//物流区
        Map<String, String> subStatusMap = this.getSubOrderStatusMap();// 分批订单状态
        Map<String, String> orderTypeMap = this.getOrderTypeMap();// 订单类型
        Map<String, String> orderSourceMap = this.getOrderSourceMap();// 订单来源
        model.addAttribute("orderTypeMap", orderTypeMap);
        model.addAttribute("orderSourceMap", orderSourceMap);
        model.addAttribute("subStatusMap", subStatusMap);
        model.addAttribute("districtList", districtList);
        logger.info("订单页面初始化");
        return "order/SO151501";
    }

    /**
     * 订单页面查询
     *
     * @param baseParam
     * @return
     */
    @RequestMapping(value = "search",
            method = RequestMethod.POST)
    public
    @ResponseBody
    PageResult<ISO151501Bean> search(BasePageParam baseParam) {
        logger.info("查询数据库");
        DbUtils.buildLikeCondition(baseParam, "orderCode", DbUtils.LikeMode.FRONT);
      /*  DbUtils.buildLikeCondition(baseParam, "orderId", DbUtils.LikeMode.FRONT);*/
        DbUtils.buildLikeCondition(baseParam, "saName", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(baseParam, "buyerCode", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(baseParam, "buyerName", DbUtils.LikeMode.FRONT);
        return this.findAllOrders(baseParam);
    }


    /**
     * 获取分批订单信息
     *
     * @param baseParam
     * @return
     */
    public PageResult<ISO151501Bean> findAllOrders(BasePageParam baseParam) {
        logger.info("获取分批订单信息");
        RsRequest<BaseParam> request = new RsRequest<BaseParam>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(baseParam);
        String url = SystemServerManager.SoOrderApiServerManager.getFindPageOrderList();
     /*   String url1="http://localhost:8889/msk-order-api/api/so/order/page/_find";*/
        RsResponse<PageResult<ISO151501Bean>> rsResponse = RestClientUtil.post(url, request, new TypeReference<RsResponse<PageResult<ISO151501Bean>>>() {
        });
        return rsResponse.getResult();
    }


    /**
     * 查询物流区
     *
     * @return
     */
    public List<LgcsAreaBean> queryDistrict() {
        ISO151414DistrictParam ISO151414DistrictParam = new ISO151414DistrictParam();
        RsResponse<com.msk.order.bean.result.DistrictResult> resultRsResponse = SoRestUtil.getLogisticsAreaList(ISO151414DistrictParam);
        DistrictResult districtResult = resultRsResponse.getResult();
        List<LgcsAreaBean> list = districtResult.getLgcsAreaList();
        return list;
    }


    /**
     * 获取 分批订单状态map
     *
     * @return
     */
    public Map<String, String> getSubOrderStatusMap() {
        Map<String, String> resultMap = CodeMasterManager.findCodeMasterMap(OrderCodeMasterDef.SubOrderStatus.TYPE);
        return resultMap;
    }


    /**
     * 获取 订单类型map
     * @return
     */
    public Map<String, String> getOrderTypeMap() {
        Map<String, String> resultMap = CodeMasterManager.findCodeMasterMap(OrderCodeMasterDef.OrderType.TYPE);
        return resultMap;
    }

    /**
     * 获取订单来源map
     * @return
     */
    public Map<String, String> getOrderSourceMap() {
        Map<String, String> resultMap = CodeMasterManager.findCodeMasterMap(OrderCodeMasterDef.OrderSource.TYPE);
        return resultMap;
    }
}