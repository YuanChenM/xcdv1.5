package com.msk.bms.ssc.controller;

import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.DecimalUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.msk.bms.ssc.controller.common.ISSCContractRestUtil;
import com.msk.bms.ssc.controller.common.ISSCDifferRestUtil;
import com.msk.bms.ssc.controller.common.ISSCPaymentRestUtil;
import com.msk.bms.ssc.controller.common.ISSCVerificationRestUtil;
import com.msk.common.base.BaseController;
import com.msk.common.bean.LoginUser;
import com.msk.common.bean.RsResponse;
import com.msk.common.business.constant.SscConstant;
import com.msk.core.entity.SscContractBasic;
import com.msk.ssc.bean.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.*;

/**
 * 业务控制器：核销单详情
 * Created by xia_xiaojie on 2016/8/9.
 */
@Controller
@RequestMapping("/SSC11322")
public class SSC11322Controller extends BaseController {
    /** 日志 */
    private static final Logger logger = LoggerFactory.getLogger(SSC11322Controller.class);

    /**
     * 根据合同ID，从合同详情页面跳转到核销单详情页面
     */
    @RequestMapping(value = "/show", method = RequestMethod.POST)
    public String show(SSC11321RsBean verification, Model model) {
        long contractId = verification.getContractId();
        SscContractBasic contract = this.findContractById(contractId);

        verification.setContractCode(contract.getContractCode());
        verification.setContractName(contract.getContractName());
        verification.setContractAmount(contract.getContractAmount());
        verification.setContractAmountPaid(this.calculateContractAmountPaid(contractId));
        verification.setContractActDate(DateTimeUtil.formatDate(DateTimeUtil.FORMAT_DATE_YYYYMMDD, contract.getContractActDate()));
        verification.setPurchaserId(contract.getPurchaserId());
        verification.setPurchaserCode(contract.getPurchaserCode());
        verification.setPurchaserName(contract.getPurchaserName());
        verification.setSupplierId(contract.getSupplierId());
        verification.setSupplierCode(contract.getSupplierCode());
        verification.setSupplierName(contract.getSupplierName());
        verification.setStatus(SscConstant.VerificationStatus.UNTREATED);
        verification.setAuditStatus(SscConstant.VerificationAuditStatus.PENDING_CONFIRM);

        List<SSC11322ProductValueBean> productValueBeans = ISSCVerificationRestUtil.calculateProductValueDifference(contractId);
        List<SSC11322TransportCostBean> transportCostBeans = ISSCVerificationRestUtil.calculateTransportCostDifference(contractId);
        this.cancelAfterVerification(verification, productValueBeans, transportCostBeans);

        model.addAttribute("verification", verification);
        model.addAttribute("productValueBeans", productValueBeans);
        model.addAttribute("transportCostBeans", transportCostBeans);
        model.addAttribute("navigation", verification.getNavigation());
        return "ssc/SSC11322";
    }

    /**
     * CRUD成功后，根据合同ID，刷新核销单详情页面
     */
    @RequestMapping(value = "/reload", method = RequestMethod.POST)
    public String reload(SSC11321RsBean verification, Model model) {
        verification = this.findVerificationByContractId(verification.getContractId());
        return this.init(verification, model);
    }

    /**
     * 根据核销单ID，从核销单一览页面跳转到详情页面
     */
    @RequestMapping(value = "/init", method = RequestMethod.POST)
    public String init(SSC11321RsBean verification, Model model) {
        this.navigate(verification, model);

        long verificationId = verification.getVerificationId();
        verification = this.findVerificationById(verificationId);
        long contractId = verification.getContractId();

        List<SSC11322ProductValueBean> productValueBeans = ISSCVerificationRestUtil.calculateProductValueDifference(contractId);
        List<SSC11322TransportCostBean> transportCostBeans = ISSCVerificationRestUtil.calculateTransportCostDifference(contractId);
        this.copyFreightDeal(verificationId, transportCostBeans);
        this.cancelAfterVerification(verification, productValueBeans, transportCostBeans);

        model.addAttribute("verification", verification);
        model.addAttribute("productValueBeans", productValueBeans);
        model.addAttribute("transportCostBeans", transportCostBeans);
        return "ssc/SSC11322";
    }

    /**
     * 导航栏
     */
    private void navigate(SSC11321RsBean verification, Model model) {
        //资金池一览跳转
        String paymentId = verification.getPaymentId();
        if (!StringUtil.isNullOrEmpty(paymentId)) {
            model.addAttribute("navigation", "payment");
        }

        //取出资金池详细跳转id
        long paymentDetailId = 0L;
        if (verification.getPaymentDetailId() != null) {
            paymentDetailId = verification.getPaymentDetailId();
        }
        //放入资金池详细跳转id
        verification.setPaymentDetailId(paymentDetailId);
        //资金池详细跳转
        if (verification.getPaymentDetailId() != null && verification.getPaymentDetailId() != 0) {
            model.addAttribute("navigation", "paymentDetail");
        }
    }

    /**
     * 根据差异单ID，弹出差异单详情对话框
     */
    @RequestMapping(value = "/diff", method = RequestMethod.POST)
    public String diff(SSC11312Param ssc11312Param, Model model) {
        RsResponse<SSC11312Result> respResult = ISSCDifferRestUtil.queryDifferDetails(ssc11312Param);
        List<SSC11312Bean> differDetails = respResult.getResult().getSscDifferDetails();
        model.addAttribute("differDetails", differDetails);
        return "ssc/SSC1132201";
    }

    /**
     * 新增或更新核销单及其详情
     */
    @RequestMapping(value = "/saveOrUpdateVerification", method = RequestMethod.POST)
    @ResponseBody
    public String saveOrUpdateVerification(SSC11321RsBean ssc11321RsBean) {
        LoginUser loginUser = super.getLoginUser();
        String userId = loginUser.getEmplId();
        Date now = DateTimeUtil.getCustomerDate();

        ssc11321RsBean.setChargerId(userId);
        ssc11321RsBean.setChargerName(loginUser.getEmplName());
        ssc11321RsBean.setCrtId(userId);
        ssc11321RsBean.setCrtTime(now);
        ssc11321RsBean.setUpdId(userId);
        ssc11321RsBean.setUpdTime(now);

        RsResponse<SSC11322Result> respResult = ISSCVerificationRestUtil.saveOrUpdateVerification(ssc11321RsBean);
        if (SystemConst.RsStatus.FAIL.equals(respResult.getStatus())) {
            throw new BusinessException(respResult.getMessage());
        }
        return respResult.getStatus();
    }

    /**
     * 关闭合同和核销单
     */
    @RequestMapping(value = "/closeContract", method = RequestMethod.POST)
    @ResponseBody
    public String closeContract(SSC11322Bean ssc11322Bean) {
        ssc11322Bean.setUpdId(super.getLoginUser().getEmplId());
        RsResponse<SSC11304Result> respResult = ISSCVerificationRestUtil.closeContract(ssc11322Bean);
        if (SystemConst.RsStatus.FAIL.equals(respResult.getStatus())) {
            throw new BusinessException(respResult.getMessage());
        }
        return respResult.getStatus();
    }

    /**
     * 根据合同ID，查询合同
     */
    private SscContractBasic findContractById(long contractId) {
        SSC11304Param ssc11304Param = new SSC11304Param();
        ssc11304Param.setContractId(contractId);
        return ISSCContractRestUtil.findSscContractBasic(ssc11304Param);
    }

    /**
     * 根据核销单ID，查询核销单
     */
    private SSC11321RsBean findVerificationById(long verificationId) {
        SSC11321RsParam ssc11321RsParam = new SSC11321RsParam();
        ssc11321RsParam.setVerificationId(String.valueOf(verificationId));
        PageResult<SSC11321RsBean> pageResult = ISSCVerificationRestUtil.findVerifications(ssc11321RsParam);

        List<SSC11321RsBean> ssc11321RsBeans = pageResult.getData();
        if (CollectionUtils.isEmpty(ssc11321RsBeans)) {
            return null;
        }
        return ssc11321RsBeans.get(NumberConst.IntDef.INT_ZERO);
    }

        /**
         * 根据合同ID，查询核销单
         */
    private SSC11321RsBean findVerificationByContractId(long contractId) {
        SSC11321RsParam ssc11321RsParam = new SSC11321RsParam();
        ssc11321RsParam.setContractId(String.valueOf(contractId));
        PageResult<SSC11321RsBean> pageResult = ISSCVerificationRestUtil.findVerifications(ssc11321RsParam);

        List<SSC11321RsBean> ssc11321RsBeans = pageResult.getData();
        if (CollectionUtils.isEmpty(ssc11321RsBeans)) {
            return null;
        }
        return ssc11321RsBeans.get(NumberConst.IntDef.INT_ZERO);
    }

    private List<SSC11322Bean> findVerificationDetailsByVerificationId(long verificationId) {
        SSC11322Param ssc11322Param = new SSC11322Param();
        ssc11322Param.setVerificationId(verificationId);
        SSC11322Result ssc11322Result = ISSCVerificationRestUtil.findVerificationDetails(ssc11322Param);
        return ssc11322Result.getVerificationDetails();
    }

    /**
     * 根据合同ID，计算合同已付款总额
     */
    private BigDecimal calculateContractAmountPaid(long contractId) {
        SSC11308RsParam  ssc11308RsParam = new SSC11308RsParam();
        ssc11308RsParam.setContractId(String.valueOf(contractId));
        RsResponse<PageResult<SSC11308RsBean>> respResult = ISSCPaymentRestUtil.findPaymentRequestList(ssc11308RsParam);
        List<SSC11308RsBean> ssc11308RsBeans = respResult.getResult().getData();

        BigDecimal contractAmountPaid = this.scale2(null);
        for (SSC11308RsBean ssc11308RsBean : ssc11308RsBeans) {
            BigDecimal amount = ssc11308RsBean.getPaidAmount();
            //若为余款，且核销金额小于0
            if (SscConstant.SscPaymentType.BALANCE == ssc11308RsBean.getPaymentType() && DecimalUtil.isLess(ssc11308RsBean.getVerificationAmount(), BigDecimal.ZERO)) {
                BigDecimal minusOne = new BigDecimal(NumberConst.IntDef.INT_N_ONE);
                amount = DecimalUtil.multiply(amount, minusOne);
            }
            contractAmountPaid = DecimalUtil.add(contractAmountPaid, amount);
        }
        return contractAmountPaid;
    }

    /**
     * 根据合同ID，查询合同已付预付款总额
     */
    private BigDecimal calculateFirstAmountPaid(long contractId) {
        SSC11308RsParam ssc11308RsParam = new SSC11308RsParam();
        ssc11308RsParam.setContractId(String.valueOf(contractId));
        ssc11308RsParam.setPaymentType(SscConstant.SscPaymentType.DOWN_PAYMENT);
        RsResponse<PageResult<SSC11308RsBean>> respResult = ISSCPaymentRestUtil.findPaymentRequestList(ssc11308RsParam);

        List<SSC11308RsBean> ssc11308RsBeans = respResult.getResult().getData();
        if (CollectionUtils.isEmpty(ssc11308RsBeans)) {
            return this.scale2(null);
        }
        return ssc11308RsBeans.get(0).getPaidAmount();
    }

    /**
     * 保留两位小数，四舍五入
     */
    private BigDecimal scale2(BigDecimal bd) {
        if (null == bd) {
            return BigDecimal.ZERO.setScale(NumberConst.IntDef.INT_TWO);
        }
        return bd.setScale(NumberConst.IntDef.INT_TWO, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 从核销单一览页面跳转到详情页面，查询运费实际支付额
     */
    private void copyFreightDeal(long verificationId, List<SSC11322TransportCostBean> transportCostBeans) {
        List<SSC11322Bean> verificationDetails = this.findVerificationDetailsByVerificationId(verificationId);
        Map<Long, SSC11322Bean> freightMap = new HashMap<Long, SSC11322Bean>();
        for (SSC11322Bean ssc11322Bean : verificationDetails) {
            freightMap.put(ssc11322Bean.getDeliveryId(), ssc11322Bean);
        }

        BigDecimal totalFreightDeal = this.scale2(null);
        BigDecimal totalFreightDiff = this.scale2(null);

        for (SSC11322TransportCostBean transportCostBean : transportCostBeans) {
            BigDecimal freightDeal = transportCostBean.getFreightDeal();
            BigDecimal freightPaid = transportCostBean.getFreightPaid();
            BigDecimal freightDiff = transportCostBean.getFreightDiff();

            SSC11322Bean ssc11322Bean = freightMap.get(transportCostBean.getDeliveryId());
            if (null != ssc11322Bean) {
                freightDeal = this.scale2(ssc11322Bean.getFreightActualPaid());
                freightDiff = DecimalUtil.subtract(freightDeal, freightPaid);
            }

            totalFreightDeal = DecimalUtil.add(totalFreightDeal, freightDeal);
            totalFreightDiff = DecimalUtil.add(totalFreightDiff, freightDiff);

            transportCostBean.setVerificationDetailId(ssc11322Bean.getVerificationDetailId());
            transportCostBean.setFreightDeal(freightDeal);
            transportCostBean.setFreightDiff(freightDiff);
        }

        if (!CollectionUtils.isEmpty(transportCostBeans)) {
            SSC11322TransportCostBean transportCostBean = transportCostBeans.get(NumberConst.IntDef.INT_ZERO);
            transportCostBean.setTotalFreightDeal(totalFreightDeal);
            transportCostBean.setTotalFreightDiff(totalFreightDiff);
        }
    }

    /**
     * 核销
     */
    private void cancelAfterVerification(SSC11321RsBean verification, List<SSC11322ProductValueBean> productValueBeans, List<SSC11322TransportCostBean> transportCostBeans) {
        BigDecimal totalValueNeed = this.scale2(null);
        BigDecimal totalValuePaid = this.scale2(null);
        BigDecimal totalValueDiff = this.scale2(null);
        BigDecimal totalFreightDeal = this.scale2(null);
        BigDecimal totalFreightPaid = this.scale2(null);
        BigDecimal totalFreightDiff = this.scale2(null);

        if (!CollectionUtils.isEmpty(productValueBeans)) {
            SSC11322ProductValueBean productValueBean = productValueBeans.get(NumberConst.IntDef.INT_ZERO);
            totalValueNeed = productValueBean.getTotalValueNeed();
            totalValuePaid = productValueBean.getTotalValuePaid();
            totalValueDiff = productValueBean.getTotalValueDiff();
        }
        if (!CollectionUtils.isEmpty(transportCostBeans)) {
            SSC11322TransportCostBean transportCostBean = transportCostBeans.get(NumberConst.IntDef.INT_ZERO);
            totalFreightDeal = transportCostBean.getTotalFreightDeal();
            totalFreightPaid = transportCostBean.getTotalFreightPaid();
            totalFreightDiff = transportCostBean.getTotalFreightDiff();
        }

        long contractId = verification.getContractId();
        BigDecimal contractAmountPaid = this.calculateContractAmountPaid(contractId);
        BigDecimal firstAmountPaid = this.calculateFirstAmountPaid(contractId);
        BigDecimal contractAmountDeal = DecimalUtil.add(totalValueNeed, totalFreightDeal);
        BigDecimal verificationAmount = DecimalUtil.subtract(DecimalUtil.add(totalValueDiff, totalFreightDiff), firstAmountPaid);   //合计金额差 = 发货入库差异金额 + 运输差异金额 - 已付预付款

        verification.setFirstAmountPaid(firstAmountPaid);
        verification.setVerificationAmount(verificationAmount);
        verification.setContractAmountDeal(contractAmountDeal);
        verification.setContractAmountPaid(contractAmountPaid);
        verification.setTotalValueNeed(totalValueNeed);
        verification.setTotalValuePaid(totalValuePaid);
        verification.setTotalValueDiff(totalValueDiff);
        verification.setTotalFreightDeal(totalFreightDeal);
        verification.setTotalFreightPaid(totalFreightPaid);
        verification.setTotalFreightDiff(totalFreightDiff);
    }

}
