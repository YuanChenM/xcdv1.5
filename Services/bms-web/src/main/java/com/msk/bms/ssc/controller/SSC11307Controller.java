package com.msk.bms.ssc.controller;

import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.utils.DbUtils;
import com.msk.bms.ssc.controller.common.ISSCPaymentRestUtil;
import com.msk.common.base.BaseController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.business.constant.SscConstant;
import com.msk.common.config.CodeMasterManager;
import com.msk.common.config.server.SystemServerManager;
import org.springframework.web.bind.annotation.PathVariable;
import com.msk.common.utils.RestClientUtil;
import com.msk.ssc.bean.*;
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
 * 付款申请一览画面Controller
 * Created by peng_hao on 2016/8/5.
 */
@Controller
@RequestMapping("SSC11307")
public class SSC11307Controller extends BaseController {

    private static Logger logger = getLogger(SSC11307Controller.class);

    /**
     * 初始化页面
     *
     * @param model
     * @return 页面
     */
    @RequestMapping(value = "init", method = RequestMethod.POST)
    public String init(SSC11307RsBean ssc11307RsBean,Model model) {
        logger.debug("付款申请一览页面初始化");

        //从redis获取 付款类型
        String paymentTypeKey = SscConstant.SscPaymentType.TYPE;
        Map<String, String> paymentTypeMap = CodeMasterManager.findCodeMasterMap(paymentTypeKey);
        TreeMap<String, String> treeMap = new TreeMap<>();
        treeMap.putAll(paymentTypeMap);
        List paymentTypeList = new ArrayList(treeMap.entrySet());
        model.addAttribute("paymentTypeList", paymentTypeList);

        //从redis获取 合同状态
        String contractStatusKey = SscConstant.SscOrderStatus.TYPE;
        Map<String, String> contractStatusMap = CodeMasterManager.findCodeMasterMap(contractStatusKey);
        TreeMap<String, String> treeMap2 = new TreeMap<>();
        treeMap2.putAll(contractStatusMap);
        List contractStatusList = new ArrayList(treeMap2.entrySet());
        model.addAttribute("contractStatusList", contractStatusList);

        //从redis获取 付款状态
        String payedStatusKey = SscConstant.PaymentStatus.TYPE;
        Map<String, String> payedStatusMap = CodeMasterManager.findCodeMasterMap(payedStatusKey);
        TreeMap<String, String> treeMap3 = new TreeMap<>();
        treeMap3.putAll(payedStatusMap);
        List payedStatusList = new ArrayList(treeMap3.entrySet());
        model.addAttribute("payedStatusList", payedStatusList);


        //从redis获取 审批审核状态
        String auditingStatusKey = SscConstant.AuditingStatus.TYPE;
        Map<String, String> auditingStatusMap = CodeMasterManager.findCodeMasterMap(auditingStatusKey);
        TreeMap<String, String> treeMap4 = new TreeMap<>();
        treeMap4.putAll(auditingStatusMap);
        List auditingStatusList = new ArrayList(treeMap4.entrySet());
        model.addAttribute("auditingStatusList", auditingStatusList);


        model.addAttribute("ssc11307RsBean",ssc11307RsBean);
        return "ssc/SSC11307";
    }

    /**
     * 分页查询
     *
     * @param ssc11307RsParam
     * @return 分页查询数据
     */
    @RequestMapping(value = "search", method = RequestMethod.POST)
    public
    @ResponseBody
    PageResult<SSC11307RsBean> search(SSC11307RsParam ssc11307RsParam) {
        logger.debug("付款申请查询");
        if(ssc11307RsParam.getDeliveryId()!=null){
            long deliveryId = ssc11307RsParam.getDeliveryId();//发货订单Id
            ssc11307RsParam.setDeliveryId(deliveryId);
        }else{
            //String paymentRequestCode = StringUtil.toString(ssc11307RsParam.getFilterMap().get("paymentRequestCode")).trim();  //付款申请单号
            //String paymentRequestName = StringUtil.toString(ssc11307RsParam.getFilterMap().get("paymentRequestName")).trim();  //付款申请名称
            //String deliveryCode = StringUtil.toString(ssc11307RsParam.getFilterMap().get("deliveryCode")).trim();  //发货订单code
            //String contractName = StringUtil.toString(ssc11307RsParam.getFilterMap().get("contractName")).trim();//合同名称
            //String supplierName = StringUtil.toString(ssc11307RsParam.getFilterMap().get("supplierName")).trim();//受款主体


            String paymentRequestCode = DbUtils.buildLikeCondition(String.valueOf(ssc11307RsParam.getFilterMap().get("paymentRequestCode")).trim(), DbUtils.LikeMode.PARTIAL);
            String paymentRequestName = DbUtils.buildLikeCondition(String.valueOf(ssc11307RsParam.getFilterMap().get("paymentRequestName")).trim(), DbUtils.LikeMode.PARTIAL);
            String deliveryCode = DbUtils.buildLikeCondition(String.valueOf(ssc11307RsParam.getFilterMap().get("deliveryCode")).trim(), DbUtils.LikeMode.PARTIAL);
            String contractName = DbUtils.buildLikeCondition(String.valueOf(ssc11307RsParam.getFilterMap().get("contractName")).trim(), DbUtils.LikeMode.PARTIAL);
            String supplierName = DbUtils.buildLikeCondition(String.valueOf(ssc11307RsParam.getFilterMap().get("supplierName")).trim(), DbUtils.LikeMode.PARTIAL);
            String remitTimeStr = DbUtils.buildLikeCondition(String.valueOf(ssc11307RsParam.getFilterMap().get("remitTimeStr")).trim(), DbUtils.LikeMode.PARTIAL);//最近付款时间YYYY-MM-dd

            String contractCode;
            if(!StringUtil.isNullOrEmpty(ssc11307RsParam.getContractCode())){
                contractCode= StringUtil.toString(ssc11307RsParam.getContractCode());//合同Code
            }else{
                contractCode = DbUtils.buildLikeCondition(String.valueOf(ssc11307RsParam.getFilterMap().get("contractCode")).trim(), DbUtils.LikeMode.PARTIAL);
            }


            String paymentType = StringUtil.toString(ssc11307RsParam.getFilterMap().get("paymentType")).trim();//付款类型
            String auditingStatus = StringUtil.toString(ssc11307RsParam.getFilterMap().get("auditingStatus")).trim();//审批审核状态
            String contractStatus = StringUtil.toString(ssc11307RsParam.getFilterMap().get("contractStatus")).trim();//合同状态
            String payedStatus = StringUtil.toString(ssc11307RsParam.getFilterMap().get("payedStatus")).trim();//付款状态
            String[] paymentStatusArr = null;
            String[] auditingStatusArr = null;
            String[] contractStatusArr = null;
            String[] paymentTypeArr = null;
            if(!StringUtil.isNullOrEmpty(payedStatus)) {
                paymentStatusArr = payedStatus.split(",");
                ssc11307RsParam.setPaymentStatusArr(paymentStatusArr);
            }
            if(!StringUtil.isNullOrEmpty(contractStatus)) {
                contractStatusArr = contractStatus.split(",");
                   ssc11307RsParam.setContractStatusArr(contractStatusArr);
            }
            if(!StringUtil.isNullOrEmpty(auditingStatus)) {
                auditingStatusArr = auditingStatus.split(",");
                ssc11307RsParam.setAuditingStatusArr(auditingStatusArr);
            }
            if(!StringUtil.isNullOrEmpty(paymentType)) {
                paymentTypeArr = paymentType.split(",");
                ssc11307RsParam.setPaymentTypeArr(paymentTypeArr);
            }

            ssc11307RsParam.setPaymentRequestCode(paymentRequestCode);
            ssc11307RsParam.setPaymentRequestName(paymentRequestName);
            ssc11307RsParam.setDeliveryCode(deliveryCode);
            ssc11307RsParam.setContractCode(contractCode);
            ssc11307RsParam.setContractName(contractName);
            ssc11307RsParam.setSupplierName(supplierName);
            ssc11307RsParam.setRemitTimeStr(remitTimeStr);
        }

        RsResponse<PageResult<SSC11307RsBean>> response = ISSCPaymentRestUtil.findSscPaymentRequest(ssc11307RsParam);

        return response.getResult();
    }


    /**
     * 根据ID删除付款单
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public String delete(SSC11307RsParam param,Model model) {

        super.setCommonParam(param);
        param.setDelFlg(SystemConst.DelFlg.DISABLE);
        param.setPayedStatus(SscConstant.PaymentStatus.DELETE);
        param.setUpdTime(DateTimeUtil.getCustomerDate());

        RsResponse<Integer> response = ISSCPaymentRestUtil.deletePaymentRequest(param);

        String status = response.getStatus();
        if (SystemConst.RsStatus.FAIL.equals(status)){
            throw new BusinessException(response.getMessage());
        }

        return this.init(null,model);
    }

}
