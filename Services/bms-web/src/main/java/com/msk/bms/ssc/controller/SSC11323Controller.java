package com.msk.bms.ssc.controller;

import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.utils.DbUtils;
import com.msk.bms.ssc.controller.common.ISSCInvoiceRequestUtil;
import com.msk.common.base.BaseUploadController;
import com.msk.common.bean.LoginUser;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.business.constant.SscConstant;
import com.msk.common.config.CodeMasterManager;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.utils.RestClientUtil;
import com.msk.ssc.bean.SSC11323Bean;
import com.msk.ssc.bean.SSC11323Param;
import com.msk.ssc.bean.SSC11324Bean;
import com.msk.ssc.bean.SSC11324Param;
import com.sun.org.apache.xpath.internal.operations.*;
import org.joda.time.DateTime;

import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.String;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created on 2016/8/3.
 */
@Controller
@RequestMapping("SSC11323")
public class SSC11323Controller extends BaseUploadController {
    /**
     * logger
     */
    private static Logger logger = getLogger(SSC11323Controller.class);
    /**
     * 初始化页面
     *
     * @param model Model
     * @return 页面
     */
    @RequestMapping(value = "init", method = RequestMethod.POST)
    public String init(Model model,SSC11323Param param) {
        logger.debug("发票申请一览初始化");
        Map<String, String> invoiceType = CodeMasterManager.findCodeMasterMap("InvoiceType");
        TreeMap<String, String> treeMap1 = new TreeMap<>();
        treeMap1.putAll(invoiceType);
        List InvoiceType = new ArrayList(treeMap1.entrySet());

        Map<String, String> invoiceStatus = CodeMasterManager.findCodeMasterMap("InvoiceStatus");
        TreeMap<String, String> treeMap2 = new TreeMap<>();
        treeMap2.putAll(invoiceStatus);
        List InvoiceStatus = new ArrayList(treeMap2.entrySet());

        model.addAttribute("invoiceType", InvoiceType);
        model.addAttribute("invoiceStatus", InvoiceStatus);




        return "ssc/SSC11323";
    }
    @RequestMapping(value = "search", method = RequestMethod.POST)
    @ResponseBody
    public PageResult<SSC11323Bean> search(SSC11323Param param) {
        logger.info("发票申请一览");
        PageResult<SSC11323Bean> result = new PageResult<>();

        Map<String, Object> filterMap = param.getFilterMap();

        Object invoiceRequestCode = filterMap.get("invoiceRequestCode");
        if (null != invoiceRequestCode) {
            param.setInvoiceRequestCode(DbUtils.buildLikeCondition(String.valueOf(invoiceRequestCode).trim(), DbUtils.LikeMode.PARTIAL));
        }

        Object contractName = filterMap.get("contractName");
        if (null != contractName) {
            param.setContractName(DbUtils.buildLikeCondition(String.valueOf(contractName).trim(), DbUtils.LikeMode.PARTIAL));
        }
        Object contractCode = filterMap.get("contractCode");
        if (null != contractCode) {
            param.setContractCode(DbUtils.buildLikeCondition(String.valueOf(contractCode).trim(), DbUtils.LikeMode.PARTIAL));
        }

//        Object invoiceType = filterMap.get("invoiceType");
//        if (null != invoiceType) {
//            param.setInvoiceType(DbUtils.buildLikeCondition(String.valueOf(invoiceType).trim(), DbUtils.LikeMode.PARTIAL));
//        }
        if (!StringUtil.isEmpty((String) param.getFilterMap().get("invoiceType"))) {
            param.setInvoiceType(StringUtil.toString(param.getFilterMap().get("invoiceType")).trim());
        }
        Object payer = filterMap.get("payer");
        if (null != payer) {
            param.setPayer(DbUtils.buildLikeCondition(String.valueOf(payer).trim(), DbUtils.LikeMode.PARTIAL));
        }

        Object receiving = filterMap.get("receiving");
        if (null != receiving) {
            param.setReceiving(DbUtils.buildLikeCondition(String.valueOf(receiving).trim(), DbUtils.LikeMode.PARTIAL));
        }

        Object invoiceRequestDesc = filterMap.get("invoiceRequestDesc");
        if (null != invoiceRequestDesc) {
            param.setInvoiceRequestDesc(DbUtils.buildLikeCondition(String.valueOf(invoiceRequestDesc).trim(), DbUtils.LikeMode.PARTIAL));
        }
        Object contractId = filterMap.get("contractId");
        if (null != contractId) {
            param.setContractId((Long.getLong(DbUtils.buildLikeCondition(String.valueOf(contractId).trim(), DbUtils.LikeMode.PARTIAL))));
        }

        if (!StringUtil.isEmpty((String) param.getFilterMap().get("status"))) {
            param.setStatus(StringUtil.toString(param.getFilterMap().get("status")).trim());
            }
        if (!StringUtil.isEmpty(StringUtil.toString(param.getFilterMap().get("requestTimeStr")))) {
            param.setRequestTimeStr((StringUtil.toString(param.getFilterMap().get("requestTimeStr"))));
        }
        String invoiceType = StringUtil.toString(param.getFilterMap().get("invoiceType"));
        if (!StringUtil.isNullOrEmpty(invoiceType)) {
            String[] invoiceTypeArr = invoiceType.split(",");
            param.setInvoiceTypeArr(invoiceTypeArr);
        }
        String status = StringUtil.toString(param.getFilterMap().get("status"));
        if (!StringUtil.isNullOrEmpty(status)) {
            String[] statusArr = status.split(",");
            param.setStatusArr(statusArr);
        }

        RsResponse<PageResult<SSC11323Bean>> bee=ISSCInvoiceRequestUtil.searchInvoiceRequest(param);

        return bee.getResult();

    }


    @RequestMapping(value = "chooseInit", method = RequestMethod.POST)
    public String chooseInit(Model model) {
        logger.info("选择合同页面初始化");
        return "ssc/SSC1132301";
    }
    /**
     * 根据ID删除发货订单
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public String delete(SSC11324Param queryParam) {
        RsRequest<SSC11324Param> request = new RsRequest<>();

        LoginUser loginUser=super.getLoginUser();
        queryParam.setUpdId(loginUser.getEmplId());
        queryParam.setUpdTime(DateTimeUtil.getCustomerDate());
        queryParam.setStatus(StringUtil.toString(SscConstant.InvoiceStatus.DELETE));
        ISSCInvoiceRequestUtil.delete(queryParam);
        return "ssc/SSC11323";
    }


    @RequestMapping(value = "show", method = RequestMethod.POST)
    public String show(Model model,SSC11323Param param) {
        logger.debug("发票申请一览");
        model.addAttribute("contractInfo",param);
        model.addAttribute("disableBtn","1");
        return init(model,param);
    }

}
