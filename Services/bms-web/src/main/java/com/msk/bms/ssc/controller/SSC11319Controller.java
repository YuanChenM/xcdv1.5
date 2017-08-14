package com.msk.bms.ssc.controller;

import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.utils.DbUtils;
import com.msk.bms.ssc.controller.common.ISSCCashPoolingRestUtil;
import com.msk.common.base.BaseController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.business.constant.SscConstant;
import com.msk.common.config.CodeMasterManager;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.utils.RestClientUtil;
import com.msk.ssc.bean.SSC11319RsBean;
import com.msk.ssc.bean.SSC11319RsParam;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * 资金池一览Controller
 *
 * @author yang_yang
 */
@Controller
@RequestMapping("SSC11319")
public class SSC11319Controller extends BaseController {
    /**
     * logger
     */
    private static Logger logger = getLogger(SSC11319Controller.class);

    /**
     * 初始化页面
     *
     * @param model Model
     * @return 页面
     */
    @RequestMapping(value = "init", method = RequestMethod.POST)
    public String init(Model model) {
        logger.debug("资金池一览初始化");
        //从redis获取付款类型
        Map<String, String> paymentTypeMap = CodeMasterManager.findCodeMasterMap("SscPaymentType");
        TreeMap<String, String> treeMap = new TreeMap<>();
        treeMap.putAll(paymentTypeMap);
        List paymentTypeList = new ArrayList(treeMap.entrySet());
        model.addAttribute("paymentTypeList", paymentTypeList);
        return "ssc/SSC11319";
    }

    /**
     * 分页查询数据
     *
     * @return 分页查询数据
     */
    @RequestMapping(value = "search", method = RequestMethod.POST)
    @ResponseBody
    public PageResult<SSC11319RsBean> search(SSC11319RsParam ssc11319RsParam) {
        RsRequest<SSC11319RsParam> request = new RsRequest<SSC11319RsParam>();
        if(!StringUtil.isNullOrEmpty((String)ssc11319RsParam.getFilterMap().get("contractCode"))){
            ssc11319RsParam.getFilterMap().put("contractCode",DbUtils.buildLikeCondition(String.valueOf((String) ssc11319RsParam.getFilterMap().get("contractCode")).trim(), DbUtils.LikeMode.PARTIAL));
        }
        if(!StringUtil.isNullOrEmpty((String)ssc11319RsParam.getFilterMap().get("contractName"))){
            ssc11319RsParam.getFilterMap().put("contractName",DbUtils.buildLikeCondition(String.valueOf((String) ssc11319RsParam.getFilterMap().get("contractName")).trim(), DbUtils.LikeMode.PARTIAL));
        }
        if(!StringUtil.isNullOrEmpty((String)ssc11319RsParam.getFilterMap().get("paymentRequestCode"))){
            ssc11319RsParam.getFilterMap().put("paymentRequestCode",DbUtils.buildLikeCondition(String.valueOf((String) ssc11319RsParam.getFilterMap().get("paymentRequestCode")).trim(), DbUtils.LikeMode.PARTIAL));
        }
        if(!StringUtil.isNullOrEmpty((String)ssc11319RsParam.getFilterMap().get("payer"))){
            ssc11319RsParam.getFilterMap().put("payer",DbUtils.buildLikeCondition(String.valueOf((String) ssc11319RsParam.getFilterMap().get("payer")).trim(), DbUtils.LikeMode.PARTIAL));
        }
        if(!StringUtil.isNullOrEmpty((String)ssc11319RsParam.getFilterMap().get("receiving"))){
            ssc11319RsParam.getFilterMap().put("receiving",DbUtils.buildLikeCondition(String.valueOf((String) ssc11319RsParam.getFilterMap().get("receiving")).trim(), DbUtils.LikeMode.PARTIAL));
        }

        //只显示已付款的数据
        ssc11319RsParam.getFilterMap().put("status", SscConstant.PaymentStatus.PAID);

        PageResult<SSC11319RsBean> result = ISSCCashPoolingRestUtil.findSscCashPoolingInfoList(ssc11319RsParam);

        return result;
    }

}
