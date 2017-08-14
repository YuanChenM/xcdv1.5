package com.msk.bms.ssc.controller;


import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.utils.DbUtils;
import com.msk.bms.ssc.controller.common.ISSCContractRestUtil;
import com.msk.bms.ssc.controller.common.ISSCDeliveryOrderRestUtil;
import com.msk.bms.ssc.controller.common.ISSCPaymentRestUtil;
import com.msk.bms.ssc.controller.common.ISSCVerificationRestUtil;
import com.msk.common.base.BaseUploadController;
import com.msk.common.bean.RsResponse;
import com.msk.common.config.CodeMasterManager;
import com.msk.core.entity.SscContractBasic;
import com.msk.ssc.bean.*;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static org.slf4j.LoggerFactory.getLogger;


/**
 * 合同管理一览画面Controller
 * Created by tao_zhifa on 2016/6/28.
 */
@Controller
@RequestMapping("SSC11303")
public class SSC11303Controller extends BaseUploadController {

    private static Logger logger = getLogger(SSC11303Controller.class);

    /**
     * 合同管理一览页面初始化
     */
    @RequestMapping(value = "/init", method = RequestMethod.POST)
    public String init(Model model) {
        List contractStatuses = this.getConstant("SscOrderStatus");
        List relationTypes = this.getConstant("RelationType");
        model.addAttribute("contractStatuses", contractStatuses);
        model.addAttribute("relationTypes", relationTypes);
        return "ssc/SSC11303";
    }

    private List getConstant(String type) {
        Map<String, String> codeMasterMap = CodeMasterManager.findCodeMasterMap(type);
        TreeMap<String, String> codeMasterTree = new TreeMap<String, String>();
        codeMasterTree.putAll(codeMasterMap);
        return new ArrayList(codeMasterTree.entrySet());
    }

    /**
     * 分页查询
     *
     * @param ssc11303RsParam
     * @return 分页查询数据
     */
    @RequestMapping(value = "search", method = RequestMethod.POST)
    @ResponseBody
    public PageResult<SSC11303RsBean> search(SSC11303RsParam ssc11303RsParam) {
        logger.debug("查询合同资料");
        Map<String, Object> filterMap = ssc11303RsParam.getFilterMap();
        String contractName = DbUtils.buildLikeCondition(StringUtil.toString(filterMap.get("contractName")).trim(), DbUtils.LikeMode.PARTIAL);
        String bidProjectNo = DbUtils.buildLikeCondition(StringUtil.toString(filterMap.get("bidProjectNo")).trim(), DbUtils.LikeMode.PARTIAL);
        String contractCode = DbUtils.buildLikeCondition(StringUtil.toString(filterMap.get("contractCode")).trim(), DbUtils.LikeMode.PARTIAL);
        String purchaserName = DbUtils.buildLikeCondition(StringUtil.toString(filterMap.get("purchaserName")).trim(), DbUtils.LikeMode.PARTIAL);
        String supplierName = DbUtils.buildLikeCondition(StringUtil.toString(filterMap.get("supplierName")).trim(), DbUtils.LikeMode.PARTIAL);

        ssc11303RsParam.setContractName(contractName);
        ssc11303RsParam.setBidProjectNo(bidProjectNo);
        ssc11303RsParam.setContractCode(contractCode);
        ssc11303RsParam.setPurchaserName(purchaserName);
        ssc11303RsParam.setSupplierName(supplierName);

        ssc11303RsParam.setContractActDate(StringUtil.toString(filterMap.get("contractActDateStr")).trim());

        String contractStatus = StringUtil.toString(filterMap.get("contractStatus"));
        if (!StringUtil.isNullOrEmpty(contractStatus)) {
            String[] contractStatusArr = contractStatus.split(",");
            ssc11303RsParam.setContractStatusArr(contractStatusArr);
        }

        String bidRelationType = StringUtil.toString(filterMap.get("bidRelationType"));
        if (!StringUtil.isNullOrEmpty(bidRelationType)) {
            String[] bidRelationTypeArr = bidRelationType.split(",");
            ssc11303RsParam.setBidRelationTypeArr(bidRelationTypeArr);
        }

        RsResponse<PageResult<SSC11303RsBean>> response = ISSCContractRestUtil.findContractList(ssc11303RsParam);
        return response.getResult();
    }

    /**
     * 判断合同是否可以被删除
     */
    @RequestMapping(value = "/checkBeforeDeleting", method = RequestMethod.POST)
    @ResponseBody
    public String checkBeforeDeleting(SSC11303RsParam ssc11303RsParam) {
        long contractId = ssc11303RsParam.getContractId();
        boolean deliveryOrderFlag = this.existDeliveryOrders(contractId);
        boolean paymentRequestFlag = this.existPaymentRequests(contractId);
        boolean verificationFlag = this.existVerification(contractId);

        String contractDelStr = null;
        if (deliveryOrderFlag && paymentRequestFlag && verificationFlag) {
            contractDelStr = "HT_DELETABLE";
        }
        else {
            if (!verificationFlag) {
                contractDelStr = "HX_EXIST";
            }
            else if (!paymentRequestFlag) {
                contractDelStr = "FK_EXIST";
            }
            else if (!deliveryOrderFlag) {
                contractDelStr = "FH_EXIST";
            }
        }
        return contractDelStr;
    }

    /**
     * 判断合同是否存在发货订单
     */
    private boolean existDeliveryOrders(long contractId) {
        SSC11305RsParam ssc11305RsParam = new SSC11305RsParam();
        ssc11305RsParam.setContractId(contractId);
        RsResponse<SSC11305RsPageResult> respResult = ISSCDeliveryOrderRestUtil.findOrderBasicList(ssc11305RsParam);
        SSC11305RsPageResult pageResult = respResult.getResult();
        List<SSC11305RsBean> ssc11305RsBeans = new ArrayList<SSC11305RsBean>();
        if (null != pageResult) {
            ssc11305RsBeans = pageResult.getSSC11305RsBeanPageResult();
        }

        if (CollectionUtils.isEmpty(ssc11305RsBeans)) {
            return true;
        }
        return false;
    }

    /**
     * 判断合同是否存在付款申请单
     */
    private boolean existPaymentRequests(long contractId) {
        SSC11308RsParam ssc11308RsParam = new SSC11308RsParam();
        ssc11308RsParam.setContractId(StringUtil.toString(contractId));
        RsResponse<PageResult<SSC11308RsBean>> respResult = ISSCPaymentRestUtil.findPaymentRequestList(ssc11308RsParam);
        PageResult<SSC11308RsBean> pageResult = respResult.getResult();
        List<SSC11308RsBean> ssc11308RsBeans = new ArrayList<SSC11308RsBean>();
        if (null != pageResult) {
            ssc11308RsBeans = pageResult.getData();
        }

        if (CollectionUtils.isEmpty(ssc11308RsBeans)) {
            return true;
        }
        return false;
    }

    /**
     * 判断合同是否存在核销单
     */
    private boolean existVerification(long contractId) {
        SSC11321RsParam ssc11321RsParam = new SSC11321RsParam();
        ssc11321RsParam.setContractId(StringUtil.toString(contractId));
        PageResult<SSC11321RsBean> pageResult = ISSCVerificationRestUtil.findVerifications(ssc11321RsParam);
        List<SSC11321RsBean> ssc11321RsBeans = new ArrayList<SSC11321RsBean>();
        if (null != pageResult) {
            ssc11321RsBeans = pageResult.getData();
        }

        if (CollectionUtils.isEmpty(ssc11321RsBeans)) {
            return true;
        }
        return false;
    }

    /**
     * 根据ID删除合同
     */
    @RequestMapping(value = "deleteContractBasic", method = RequestMethod.POST)
    @ResponseBody
    public String deleteContractBasic(SSC11303RsParam ssc11303RsParam) {
        ssc11303RsParam.setUpdId(super.getLoginUser().getEmplId());
        RsResponse<Integer> rsResponse = ISSCContractRestUtil.deleteContractBasic(ssc11303RsParam);
        if (SystemConst.RsStatus.FAIL.equals(rsResponse.getStatus())) {
            throw new BusinessException(rsResponse.getMessage());
        }
        return rsResponse.getStatus();
    }

    /**
     * 更新合同状态为待审核，使合同相关信息可以再次修改
     */
    @RequestMapping(value = "enableToModify", method = RequestMethod.POST)
    @ResponseBody
    public String enableToModify(SSC11304Param ssc11304Param) {
        long contractId = ssc11304Param.getContractId();
        boolean deliveryOrderFlag = this.existDeliveryOrders(contractId);
        boolean paymentRequestFlag = this.existPaymentRequests(contractId);
        boolean verificationFlag = this.existVerification(contractId);

        String result = null;
        if (deliveryOrderFlag && paymentRequestFlag && verificationFlag) {
            SscContractBasic contract = new SscContractBasic();
            contract.setContractId(contractId);
            contract.setUpdId(this.getLoginUser().getEmplId());
            RsResponse<Integer> respResult = ISSCContractRestUtil.enableToModify(ssc11304Param);
            result = respResult.getStatus();
        }
        else {
            if (!verificationFlag) {
                result = "HX_EXIST";
            }
            else if (!paymentRequestFlag) {
                result = "FK_EXIST";
            }
            else if (!deliveryOrderFlag) {
                result = "FH_EXIST";
            }
        }
        return result;
    }

}
