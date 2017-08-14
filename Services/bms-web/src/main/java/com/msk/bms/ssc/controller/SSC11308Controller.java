package com.msk.bms.ssc.controller;

import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.DecimalUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.msk.bms.ssc.bean.SSC11327RsBean;
import com.msk.bms.ssc.bean.SSC11327RsPageResult;
import com.msk.bms.ssc.bean.seller.ISL231207Param;
import com.msk.bms.ssc.controller.common.ISSCContractRestUtil;
import com.msk.bms.ssc.controller.common.ISSCPaymentRestUtil;
import com.msk.bms.ssc.controller.common.ISSCRestUtil;
import com.msk.common.base.BaseController;
import com.msk.common.bean.LoginUser;
import com.msk.common.bean.RsResponse;
import com.msk.common.business.constant.SscConstant;
import com.msk.ssc.bean.*;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * 付款申请
 * @author
 */
@Controller
@RequestMapping("SSC11308")
public class SSC11308Controller extends BaseController {
    /**
     * logger
     */
    private static Logger logger = getLogger(SSC11308Controller.class);

    /**
     * 计算总金额，可结算金额
     * @param ssc11308RsBean
     * @return
     */
    private void plusAmount(SSC11308RsBean ssc11308RsBean){
        if(ssc11308RsBean.getPaymentRequestId()!=null){
            //计算合计
            SSC1130801RsParam ssc1130801RsParam = new SSC1130801RsParam();
            ssc1130801RsParam.setPaging(false);
            ssc1130801RsParam.setPaymentRequestId(ssc11308RsBean.getPaymentRequestId().toString());
            RsResponse<PageResult<SSC1130801RsBean>> response = ISSCPaymentRestUtil.searchPaymentRequestList(ssc1130801RsParam);
            List<SSC1130801RsBean> paymentInfoList = response.getResult().getData();
            BigDecimal totalApplyAmount = BigDecimal.ZERO;
            if(CollectionUtils.isNotEmpty(paymentInfoList)){
                for (SSC1130801RsBean paymentInfo:paymentInfoList){
                    totalApplyAmount = DecimalUtil.add(totalApplyAmount,paymentInfo.getAmount());
                }
                ssc11308RsBean.setTotalApplyAmount(totalApplyAmount);
            }
            //计算可申请金额
            BigDecimal allowApplyAmount = BigDecimal.ZERO;
            BigDecimal amount = ssc11308RsBean.getAmount();
            allowApplyAmount = DecimalUtil.subtract(amount,totalApplyAmount);
            ssc11308RsBean.setAllowApplyAmount(allowApplyAmount);
        }
    }


    /**
     * 付款申请初始化跳转页面    type:0 新增/编辑  type 1:合同跳转   type 2:发货跳转   type 3:核销跳转
     * @param model
     * @return
     */
    @RequestMapping(value = "init/{type}", method = RequestMethod.POST)
    public String init(SSC11308RsBean ssc11308RsBean,@PathVariable(value = "type")Integer type,Model model){
        logger.info("付款申请页面初始化");
        SSC11308RsParam ssc11308RsParam = new SSC11308RsParam();
        if(ssc11308RsBean.getDelFlg()!=null){
            ssc11308RsParam.setDelFlg(ssc11308RsBean.getDelFlg().toString());
        }
        if(ssc11308RsBean.getPaymentRequestId()!=null){

            String  paymentRequestId = ssc11308RsBean.getPaymentRequestId().toString();

            ssc11308RsParam.setPaymentRequestId(paymentRequestId);
            ssc11308RsBean = ISSCPaymentRestUtil.findPaymentRequestOne(ssc11308RsParam);
            //计算金额
            plusAmount(ssc11308RsBean);
            //设置核销金额
            setVerificationAmount(ssc11308RsBean);


        }else{
            if(type==NumberConst.IntDef.INT_ZERO){
                //新增
                logger.info("付款申请新增");

            }else if(type==NumberConst.IntDef.INT_ONE){
                //合同跳转
                ssc11308RsParam.setContractId(ssc11308RsBean.getContractId().toString());
                ssc11308RsParam.setPaymentType(SscConstant.SscPaymentType.DOWN_PAYMENT);

                SSC11308RsBean ssc11308RsBeanCheck = ISSCPaymentRestUtil.findPaymentRequestOne(ssc11308RsParam);

                if(ssc11308RsBeanCheck.getPaymentRequestId()!=null){
                    plusAmount(ssc11308RsBeanCheck);
                    model.addAttribute("type", type);
                    model.addAttribute("ssc11308RsBean", ssc11308RsBeanCheck);
                    return "ssc/SSC11308";
                }else{
                    setContractInfo(ssc11308RsBean);
                }


            }else if(type==NumberConst.IntDef.INT_TWO){
                //发货跳转
                ssc11308RsParam.setDeliveryId(ssc11308RsBean.getDeliveryId().toString());
                ssc11308RsParam.setPaymentType(SscConstant.SscPaymentType.PROGRESS_PAYMENT);

                SSC11308RsBean ssc11308RsBeanCheck = ISSCPaymentRestUtil.findPaymentRequestOne(ssc11308RsParam);
                if(ssc11308RsBeanCheck.getPaymentRequestId()!=null){
                    plusAmount(ssc11308RsBeanCheck);
                    model.addAttribute("type", type);
                    model.addAttribute("ssc11308RsBean", ssc11308RsBeanCheck);
                    return "ssc/SSC11308";
                }else{

                     setDeliveryInfo(ssc11308RsBean);
                    //存在合同信息，设置合同基础信息
//                    if(ssc11308RsBean.getContractId()!=null &&
//                            !StringUtil.isNullOrEmpty(ssc11308RsBean.getContractCode())){
//                        ssc11308RsBean =  setContractInfo(ssc11308RsBean);
//                    }
                }

            }else if(type==NumberConst.IntDef.INT_THREE){
                //核销跳转
                ssc11308RsParam.setVerificationId(ssc11308RsBean.getVerificationId());
                ssc11308RsParam.setPaymentType(SscConstant.SscPaymentType.BALANCE);
                SSC11308RsBean ssc11308RsBeanCheck = ISSCPaymentRestUtil.findPaymentRequestOne(ssc11308RsParam);
                if(ssc11308RsBeanCheck.getPaymentRequestId()!=null){
                    plusAmount(ssc11308RsBeanCheck);
                    model.addAttribute("type", type);
                    model.addAttribute("ssc11308RsBean", ssc11308RsBeanCheck);
                    return "ssc/SSC11308";
                }else{
                    setVerificationInfo(ssc11308RsBean);
                }
            }

            //设置银行卡信息
            setBankInfo(ssc11308RsBean);
            ssc11308RsBean.setPayedStatus(SscConstant.PaymentStatus.UNPAID);
        }

        //格式化金额
        if(ssc11308RsBean.getAmount()!=null){
            java.text.DecimalFormat df = new java.text.DecimalFormat("0.00"); //格式化，保留两位小数

            ssc11308RsBean.setAmount(new BigDecimal(df.format(ssc11308RsBean.getAmount())));
        }

        model.addAttribute("type", type);
        model.addAttribute("ssc11308RsBean", ssc11308RsBean);
        return "ssc/SSC11308";
    }



    private void setVerificationAmount(SSC11308RsBean ssc11308RsBean){
        if(ssc11308RsBean.getPaymentType()==SscConstant.SscPaymentType.BALANCE){
           BigDecimal verificationAmount = ssc11308RsBean.getVerificationAmount();
            int r = verificationAmount.compareTo(BigDecimal.ZERO);
            //和0比较:如果是负数
           if(r==NumberConst.IntDef.INT_N_ONE){
                //小于
                ssc11308RsBean.setMoneyFlag(false);
               ssc11308RsBean.setVerificationAmount( verificationAmount.abs());
            }
        }
    }

    /***
     * 设置合同信息
     * @param ssc11308RsBean
     * @return
     */
    private void setContractInfo(SSC11308RsBean ssc11308RsBean){

        Long contractId =  ssc11308RsBean.getContractId();
        String contractCode = ssc11308RsBean.getContractCode();

        ssc11308RsBean.setPaymentType(SscConstant.SscPaymentType.DOWN_PAYMENT);

        SSC11303RsParam ssc11303RsParam = new SSC11303RsParam();
        ssc11303RsParam.setContractId(contractId);
        ssc11303RsParam.setContractCode(contractCode);
        List<SSC11303RsBean> contractList =  findContractList(ssc11303RsParam);
        SSC11303RsBean contractBase = contractList.get(0);

        ssc11308RsBean.setSupplierId(contractBase.getSupplierId());
        ssc11308RsBean.setSupplierCode(contractBase.getSupplierCode());
        ssc11308RsBean.setSupplierName(contractBase.getSupplierName());
        ssc11308RsBean.setPurchaserId(contractBase.getPurchaserId());
        ssc11308RsBean.setPurchaserCode(contractBase.getPurchaserCode());
        ssc11308RsBean.setPurchaserName(contractBase.getPurchaserName());
        ssc11308RsBean.setContractTotalAmount(contractBase.getContractAmount());//合同总金额
        ssc11308RsBean.setContractId(contractId);
        ssc11308RsBean.setContractCode(contractCode);

        //获得合同下产品信息
        SSC11304Param ssc11304Param = new SSC11304Param();
        ssc11304Param.setContractId(contractId);
        ssc11304Param.setContractCode(contractCode);
        List<SSC11304ProductBean> contractPdList =  findContractOrderPD(ssc11304Param);

        BigDecimal contractFirstAmount =  BigDecimal.ZERO;
        if(CollectionUtils.isNotEmpty(contractPdList)){
            for (int i=0;i<contractPdList.size();i++){
                contractFirstAmount = DecimalUtil.add(contractFirstAmount,contractPdList.get(i).getPaymentAmount());
            }
        }
        ssc11308RsBean.setContractFirstAmount(contractFirstAmount);
        ssc11308RsBean.setAmount(contractFirstAmount);
        ssc11308RsBean.setAllowApplyAmount(contractFirstAmount);
    }

    /***
     * 设置发货订单信息
     * @param ssc11308RsBean
     * @return
     */
    private void setDeliveryInfo(SSC11308RsBean ssc11308RsBean){

        Long deliveryId = ssc11308RsBean.getDeliveryId();
        String deliveryCode = ssc11308RsBean.getDeliveryCode();

        ssc11308RsBean.setDeliveryId(deliveryId);
        ssc11308RsBean.setDeliveryCode(deliveryCode);

        //查询发货订单列表
        SSC11305RsParam ssc11305RsParam = new  SSC11305RsParam();
        ssc11305RsParam.setDeliveryId(deliveryId);
        ssc11305RsParam.setDeliveryCode(deliveryCode);
        List<SSC11305RsBean> deliveryList =  SSC1130802Controller.getDeliverList(ssc11305RsParam);
        SSC11305RsBean deliveryBase = deliveryList.get(0);


        ssc11308RsBean.setSupplierId(deliveryBase.getSupplierId());
        ssc11308RsBean.setSupplierName(deliveryBase.getSupplierName());

        ssc11308RsBean.setPurchaserId(deliveryBase.getPurchaserId());
        ssc11308RsBean.setPurchaserName(deliveryBase.getPurchaserName());

        ssc11308RsBean.setDeliverTotalAmount(deliveryBase.getAmount());
        ssc11308RsBean.setTransportAmount(deliveryBase.getTransportCost());

        ssc11308RsBean.setContractId(deliveryBase.getContractId());
//        ssc11308RsBean.setContractCode(deliveryBase.getContractCode());

        ssc11308RsBean.setPaymentType(SscConstant.SscPaymentType.PROGRESS_PAYMENT);

        //预付款按比例已支付
        String  paidDownPaymentPercentage =   findDeliveryPDList(ssc11308RsBean.getDeliveryId().toString());
        if(!StringUtil.isNullOrEmpty(paidDownPaymentPercentage)){
            ssc11308RsBean.setPaidDownPaymentPercentage(new BigDecimal(paidDownPaymentPercentage));
            BigDecimal amount = ssc11308RsBean.getDeliverTotalAmount().subtract(new BigDecimal(paidDownPaymentPercentage));
            if(amount!=null){
                java.text.DecimalFormat df = new java.text.DecimalFormat("0.00"); //格式化，保留两位小数

                ssc11308RsBean.setAmount(new BigDecimal(df.format(amount)));

                ssc11308RsBean.setAllowApplyAmount(new BigDecimal(df.format(amount)));
            }
        }
    }

    /***
     * 设置核销单信息
     * @param ssc11308RsBean
     * @return
     */
    private void setVerificationInfo(SSC11308RsBean ssc11308RsBean){
        Long verificationId = ssc11308RsBean.getVerificationId();
        String verificationCode = ssc11308RsBean.getVerificationCode();

        ssc11308RsBean.setPaymentType(SscConstant.SscPaymentType.BALANCE);
        SSC11321RsParam ssc11321RsParam = new SSC11321RsParam();
        ssc11321RsParam.setVerificationId(verificationId.toString());
        ssc11321RsParam.setVerificationCode(verificationCode);
        List<SSC11321RsBean> verificationList =  SSC1130802Controller.getVerificationList(ssc11321RsParam);
        SSC11321RsBean verificationInfo = verificationList.get(0);


        BigDecimal verificationAmount = verificationInfo.getVerificationAmount();

        Long contractId = verificationInfo.getContractId();
        SSC11303RsParam ssc11303RsParam = new SSC11303RsParam();
        ssc11303RsParam.setContractId(contractId);
        List<SSC11303RsBean> contractList = this.findContractList(ssc11303RsParam);
        SSC11303RsBean contract = contractList.get(0);
        int r = verificationAmount.compareTo(BigDecimal.ZERO);
        //和0比较:如果是正数，应该是甲方支付乙方。如果是负数，应该是乙方支付甲方
        if(r==NumberConst.IntDef.INT_ONE){
            //大于
            ssc11308RsBean.setSupplierId(contract.getSupplierId());
            ssc11308RsBean.setSupplierName(contract.getSupplierName());
            ssc11308RsBean.setPurchaserId(contract.getPurchaserId());
            ssc11308RsBean.setPurchaserName(contract.getPurchaserName());

        }else  if(r==NumberConst.IntDef.INT_N_ONE){
            //小于
            ssc11308RsBean.setSupplierId(contract.getPurchaserId());
            ssc11308RsBean.setSupplierName(contract.getPurchaserName());
            ssc11308RsBean.setPurchaserId(contract.getSupplierId());
            ssc11308RsBean.setPurchaserName(contract.getSupplierName());
            ssc11308RsBean.setMoneyFlag(false);
        }

        verificationAmount = verificationAmount.abs();
        ssc11308RsBean.setVerificationAmount(verificationAmount);
        ssc11308RsBean.setAmount(verificationAmount);
        ssc11308RsBean.setAllowApplyAmount(verificationAmount);
        ssc11308RsBean.setContractId(contractId);
    }

    /**
     * 编辑or新增
     * @param ssc11308RsBean
     * @param model
     * @return
     */
    @RequestMapping(value = "saveOrModifyPaymentRequest", method = RequestMethod.POST)
    public String saveOrModifyPayment(SSC11308RsBean ssc11308RsBean,Model model){
        ssc11308RsBean.setAuditingStatus(SscConstant.AuditingStatus.UNAPPROVED);

        super.setCommonParam(ssc11308RsBean);
        ssc11308RsBean.setUpdTime(DateTimeUtil.getCustomerDate());

        if(ssc11308RsBean.getPaymentRequestId()==null){
            ssc11308RsBean.setCrtTime(DateTimeUtil.getCustomerDate());
            //新增付款申请时，排他检测，检测合同 or 发货订单 or 核销单 是否已经生成过
            checkPaymentRequestExist(ssc11308RsBean);
        }else{
            changeAuditStatusDefault(ssc11308RsBean);
        }

        RsResponse<SSC11308RsBean> response = ISSCPaymentRestUtil.saveOrModifyPaymentRequest(ssc11308RsBean);

        //操作成功
        if (response.getStatus().equals(SystemConst.RsStatus.SUCCESS)) {
            return init(response.getResult(),ssc11308RsBean.getType(),model);
        } else {
            throw new BusinessException(response.getMessage());
        }
    }

    /**
     * 审批审核状态全部改为 未审批未审核
     * @param ssc11308RsBean
     */
    private  void changeAuditStatusDefault(SSC11308RsBean ssc11308RsBean){
        //审批审核状态全部置为0
        ssc11308RsBean.setAuditingStatus(SscConstant.AuditingStatus.UNAPPROVED);
    }

    /**
     * 0:审批/1:审核
     * @param ssc11308RsBean
     * @param model
     * @return
     */
    @RequestMapping(value = "approvalPayment", method = RequestMethod.POST)
    public String approvalPayment(SSC11308RsBean ssc11308RsBean,Model model){
        LoginUser loginUser=super.getLoginUser();
        super.setCommonParam(ssc11308RsBean);
        //审批
        if(ssc11308RsBean.getFlag().equals(NumberConst.IntDef.INT_ZERO)){
            if (!ssc11308RsBean.getApprovalFlag().equals(NumberConst.IntDef.INT_ZERO + "")){
                ssc11308RsBean.setAuditingStatus(SscConstant.AuditingStatus.APPROVE_NG);
            }else{
                ssc11308RsBean.setAuditingStatus(SscConstant.AuditingStatus.UNAUDITED);
            }
            ssc11308RsBean.setApprovalDate(DateTimeUtil.getCustomerDate());
            ssc11308RsBean.setApprovalPerson(loginUser.getEmplName());

        }
        //审核
        else{
            if (!ssc11308RsBean.getAuditingFlag().equals(NumberConst.IntDef.INT_ZERO + "")){
                ssc11308RsBean.setAuditingStatus(SscConstant.AuditingStatus.AUDIT_NG);
            }else{
                ssc11308RsBean.setAuditingStatus(SscConstant.AuditingStatus.AUDITED);
            }
            ssc11308RsBean.setAuditingDate(DateTimeUtil.getCustomerDate());
            ssc11308RsBean.setAuditingPerson(loginUser.getEmplName());
        }

        ssc11308RsBean.setUpdTime(DateTimeUtil.getCustomerDate());


        RsResponse<SSC11308RsBean> response = ISSCPaymentRestUtil.saveOrModifyPaymentRequest(ssc11308RsBean);

        //操作成功
        if (response.getStatus().equals(SystemConst.RsStatus.SUCCESS)) {
            return init(ssc11308RsBean,ssc11308RsBean.getType(),model);
        } else {
            throw new BusinessException(response.getMessage());
        }
    }

    /**
     * 分页查询数据
     *
     * @return 分页查询数据
     */
    @RequestMapping(value = "search", method = RequestMethod.POST)
    @ResponseBody
    public PageResult<SSC1130801RsBean> search(Model model,SSC1130801RsParam ssc1130801RsParam){

        ssc1130801RsParam.setPaymentRequestId(ssc1130801RsParam.getPaymentRequestId());

        RsResponse<PageResult<SSC1130801RsBean>> response = ISSCPaymentRestUtil.searchPaymentRequestList(ssc1130801RsParam);

        //第一次请求
        if(StringUtil.isNullOrEmpty(ssc1130801RsParam.getPaymentRequestId())){
            response.getResult().setData(new ArrayList<SSC1130801RsBean>());
            response.getResult().setRecordsTotal(0);
        }

        return response.getResult();
    }



    /**
     * 查询合同列表
     * @param ssc11303RsParam
     * @return
     */
    @RequestMapping(value = "getContractList", method = RequestMethod.POST)
    @ResponseBody
    public List<SSC11303RsBean> findContractList(SSC11303RsParam ssc11303RsParam) {

        RsResponse<PageResult<SSC11303RsBean>> response = ISSCContractRestUtil.findContractList(ssc11303RsParam);

        if(response.getResult()!=null && CollectionUtils.isNotEmpty(response.getResult().getData())){
            return response.getResult().getData();
        }
        return null;
    }

    /**
     * 获得合同产品信息
     * @param param
     * @return
     */
    @RequestMapping(value = "getContractOrderPD", method = RequestMethod.POST)
    @ResponseBody
    public List<SSC11304ProductBean> findContractOrderPD(SSC11304Param param) {

        RsResponse<SSC11304Result> response = ISSCContractRestUtil.findContractOrderPD(param);

        List<SSC11304ProductBean> pdList = response.getResult().getProducts();

        return pdList;
    }

    /**
     * 根据合同ID，判断是否已全部支付余款
     */
    @RequestMapping(value = "/assertAllBalancePaid", method = RequestMethod.POST)
    @ResponseBody
    public String assertAllBalancePaid(SSC11308RsParam ssc11308RsParam) {
        ssc11308RsParam.setPaymentType(SscConstant.SscPaymentType.BALANCE);
        SSC11308RsBean ssc11308RsBean = ISSCPaymentRestUtil.findPaymentRequestOne(ssc11308RsParam);
        if (null != ssc11308RsBean) {
            Integer status = ssc11308RsBean.getPayedStatus();
            if (null != status && status.equals(SscConstant.PaymentStatus.AL_PAID)) {
                return "all_paid";
            }
        }
        return "not_all_paid";
    }

    /**
     * 根据发货订单ID，运费结算方式返回
     */
    @RequestMapping(value = "/checkFreightSettleMethod", method = RequestMethod.POST)
    @ResponseBody
    public String checkFreightSettleMethod(SSC11308RsParam ssc11308RsParam) {
        Long deliveryId = Long.valueOf(ssc11308RsParam.getDeliveryId());
        //查询发货订单列表
        SSC11305RsParam ssc11305RsParam = new  SSC11305RsParam();
        ssc11305RsParam.setDeliveryId(deliveryId);
        List<SSC11305RsBean> deliveryList =  SSC1130802Controller.getDeliverList(ssc11305RsParam);
        SSC11305RsBean deliveryBase = deliveryList.get(0);
        //返回运费结算方式
        return deliveryBase.getFreightSettleMethod();
    }

    /***
     * 预付款按比例已支付金额
     * @param deliveryId
     * @return
     */
    @RequestMapping(value = "/findDeliveryPDList", method = RequestMethod.POST)
    @ResponseBody
    public String findDeliveryPDList(String deliveryId){
        SSC1130802RsParam param = new  SSC1130802RsParam();
        param.setDeliveryId(deliveryId);
        param.setContractRelationType(SscConstant.RelationType.NORMAL);
        RsResponse<String> response = ISSCPaymentRestUtil.findDeliveryPDList(param);
        if(response.getStatus().equals(SystemConst.RsStatus.SUCCESS)){
            return response.getResult();
        }else{
            return String.valueOf(BigDecimal.ZERO);
        }
    }


    /**
     * 调用卖家接口，设置开户银行、银行卡号信息
     * @param ssc11308RsBean
     * @return
     */
    private void setBankInfo(SSC11308RsBean ssc11308RsBean){

        ISL231207Param isl231207Param = new ISL231207Param();
        isl231207Param.getFilterMap().put("slCode",ssc11308RsBean.getSupplierCode());
        isl231207Param.getFilterMap().put("epId",ssc11308RsBean.getSupplierId());
        isl231207Param.setPageCount(NumberConst.IntDef.INT_ONE);
        isl231207Param.setPageNo(NumberConst.IntDef.INT_ONE);


        RsResponse< SSC11327RsPageResult > rsResponse = ISSCRestUtil.findEpPageList(isl231207Param);
        if(rsResponse!=null && rsResponse.getStatus().equals(SystemConst.RsStatus.SUCCESS)){
            List<SSC11327RsBean> epList= rsResponse.getResult().getEpList();
            if(CollectionUtils.isNotEmpty(epList) && epList.size()==1 ){
                SSC11327RsBean epInfo = epList.get(0);
                ssc11308RsBean .setSupplierBank(epInfo.getBalBank());
                ssc11308RsBean .setSupplierAccount(epInfo.getBalAccount());
            }
        }
    }




    /***
     * 支付管控新增
     * @param ssc1130801RsBean
     * @param model
     * @return
     */
    @RequestMapping(value = "saveOrModifyPaymentInfo", method = RequestMethod.POST)
    public String saveOrModifyPaymentInfo(SSC1130801RsBean ssc1130801RsBean,Model model){
        super.setCommonParam(ssc1130801RsBean);

        ssc1130801RsBean.setUpdTime(DateTimeUtil.getCustomerDate());
        if(ssc1130801RsBean.getPaymentId()== null){
            ssc1130801RsBean.setCrtTime(DateTimeUtil.getCustomerDate());
        }

        RsResponse<Integer> response = ISSCPaymentRestUtil.saveOrModifyPaymentInfo(ssc1130801RsBean);

        if(response.getStatus().equals(SystemConst.RsStatus.FAIL)){
            throw new BusinessException(response.getMessage());
        }


        //更新ssc_payment_request表paid_amount字段
        if(ssc1130801RsBean.getPaymentRequestId()!=null){

            SSC11308RsBean ssc11308RsBean = new SSC11308RsBean();
            ssc11308RsBean.setPaymentRequestId(ssc1130801RsBean.getPaymentRequestId());
            super.setCommonParam(ssc11308RsBean);

            ssc11308RsBean.setUpdTime(DateTimeUtil.getCustomerDate());

            //支付操作
            if(ssc1130801RsBean.getStatus()!=null
                    && ssc1130801RsBean.getPaymentId()!=null){

                SSC11308RsParam  ssc11308RsParam = new SSC11308RsParam();
                ssc11308RsParam.setPaymentRequestId(ssc1130801RsBean.getPaymentRequestId().toString());
                SSC11308RsBean paymentRequestBean = ISSCPaymentRestUtil.findPaymentRequestOne(ssc11308RsParam);

                BigDecimal paidAmount = paymentRequestBean.getPaidAmount();
                paidAmount = DecimalUtil.add(paidAmount,ssc1130801RsBean.getAmount());
                BigDecimal amount = paymentRequestBean.getAmount();

                Integer  payedStatus = SscConstant.PaymentStatus.UNPAID;//付款状态，默认为未支付
                if(amount.compareTo(paidAmount)==NumberConst.IntDef.INT_ONE){
                    payedStatus = SscConstant.PaymentStatus.PAR_PAID;
                }else if(amount.compareTo(paidAmount)==NumberConst.IntDef.INT_ZERO){
                    payedStatus = SscConstant.PaymentStatus.AL_PAID;
                }
                ssc11308RsBean.setPayedStatus(payedStatus);
                ssc11308RsBean.setPaidAmount(paidAmount);
            }else {
                //删除
                changeAuditStatusDefault(ssc11308RsBean);
            }

            RsResponse<SSC11308RsBean> response2 = ISSCPaymentRestUtil.saveOrModifyPaymentRequest(ssc11308RsBean);
            if (response.getStatus().equals(SystemConst.RsStatus.FAIL)) {
                throw new BusinessException(response.getMessage());
            }

        }

        //操作成功
        if (response.getStatus().equals(SystemConst.RsStatus.SUCCESS)) {
            SSC11308RsBean ssc11308InitBean = new SSC11308RsBean();
            ssc11308InitBean.setPaymentRequestId(ssc1130801RsBean.getPaymentRequestId());
            return init(ssc11308InitBean,Integer.parseInt(ssc1130801RsBean.getType()),model);
        } else {
            throw new BusinessException(response.getMessage());
        }

    }



    private void checkPaymentRequestExist(SSC11308RsBean bean){

        SSC11308RsParam param = new SSC11308RsParam();
        Integer paymentType = bean.getPaymentType();
        param.setPaymentType(paymentType);

        if(paymentType==SscConstant.SscPaymentType.DOWN_PAYMENT){

            param.setContractId(bean.getContractId().toString());

        }else if(paymentType==SscConstant.SscPaymentType.PROGRESS_PAYMENT){

            param.setDeliveryId(bean.getDeliveryId().toString());

        }else if(paymentType==SscConstant.SscPaymentType.BALANCE){

            param.setVerificationId(bean.getVerificationId());
        }

        RsResponse<PageResult<SSC11308RsBean>> response = ISSCPaymentRestUtil.checkExistPaymentRequest(param);

        if(response.getResult().getData()!=null
                && response.getResult().getData().size()>NumberConst.IntDef.INT_ZERO){
            //存在
             throw new BusinessException("数据已被使用，请刷新页面再试！");
        }

    }

    /**
     * 查询发货订单是否已付款
     */
    @RequestMapping(value = "/checkDeliveryOrderPayed", method = RequestMethod.POST)
    @ResponseBody
    public String checkDeliveryOrderPayed(SSC11308RsParam ssc11308RsParam) {
        String status = SystemConst.RsStatus.FAIL;
        ssc11308RsParam.setPaymentType(SscConstant.SscPaymentType.PROGRESS_PAYMENT);
        SSC11308RsBean bean = ISSCPaymentRestUtil.findPaymentRequestOne(ssc11308RsParam);
        //查询该发货订单ID，支付类型为进度款的数据，且已支付金额大于0
        if(bean != null){
            if(DecimalUtil.isGreater(bean.getPaidAmount(),BigDecimal.ZERO)){
                status = SystemConst.RsStatus.SUCCESS;
            }
        }

        return status;
    }


}
