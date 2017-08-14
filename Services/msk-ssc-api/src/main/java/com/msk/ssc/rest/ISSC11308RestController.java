package com.msk.ssc.rest;

import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.business.constant.SscConstant;
import com.msk.common.logic.CommonLogic;
import com.msk.ssc.bean.*;
import com.msk.ssc.logic.SSC11308Logic;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * 付款申请
 */
@RestController
@Api(description = "付款申请RestController",tags = {"ISSC11308RestController"})
public class ISSC11308RestController extends BaseRsController {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(ISSC11308RestController.class);

    @Autowired
    private SSC11308Logic ssc11308Logic;

    @Autowired
    private CommonLogic commonLogic;
    /**
     * 查询支付申请
     *
     * @param param
     * @return
     */
    @ApiOperation(value = "查询",notes = "分页查询付款申请信息")
    @RequestMapping(value = "/ssc/findPaymentRequest",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<PageResult<SSC11308RsBean>> findPaymentRequest(@RequestBody RsRequest<SSC11308RsParam> param) {
        logger.info("查询付款申请");

        RsResponse<PageResult<SSC11308RsBean>> response=new  RsResponse<PageResult<SSC11308RsBean>>();

        PageResult<SSC11308RsBean> res =this.ssc11308Logic.findPage(param.getParam(), SSC11308RsBean.class);

        if(res!=null){
            response.setStatus(SystemConst.RsStatus.SUCCESS);
            response.setMessage("处理成功");
            response.setResult(res);
        }else{
            response.setStatus(SystemConst.RsStatus.FAIL);
            response.setMessage("数据不存在");
        }
        return response;
    }

    /**
     * 编辑or新增支付申请
     *
     * @param ssc11308RsBean
     * @return
     */
    @ApiOperation(value = "新增or修改",notes = "根据主键，新增or修改付款申请信息")
    @RequestMapping(value = "/ssc/saveOrModifyPaymentRequest",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<SSC11308RsBean> saveOrModifyPaymentRequest(@RequestBody RsRequest<SSC11308RsBean> ssc11308RsBean) throws Exception{

        RsResponse<SSC11308RsBean> response = new RsResponse<SSC11308RsBean>();
        Integer result = null;
        SSC11308RsBean bean = ssc11308RsBean.getParam();

        if(!StringUtil.isNullOrEmpty(bean.getAccountingDateStr())){
            SimpleDateFormat sdf = new SimpleDateFormat(DateTimeUtil.FORMAT_DATE_YYYYMMDD);
            bean.setAccountingDate(sdf.parse(bean.getAccountingDateStr()));
        }

        if(bean.getPaymentRequestId() != null || !StringUtil.isNullOrEmpty(bean.getIsRelate())){
            logger.info("支付申请修改");
            result = this.ssc11308Logic.modifyPayment(bean);
        } else{
            logger.info("支付申请新增");
            Long id = commonLogic.maxId("ssc_payment_request","PAYMENT_REQUEST_ID");
            bean.setApprovalFlag(String.valueOf(SscConstant.ApproveResult.AGREE));
            bean.setAuditingFlag(String.valueOf(SscConstant.ApproveResult.AGREE));
            bean.setPayedStatus(SscConstant.PaymentStatus.UNPAID);
            bean.setPaymentRequestId(id);
            bean.setAuditingStatus(SscConstant.AuditingStatus.UNAPPROVED);
            bean.setPaidAmount(new BigDecimal(NumberConst.IntDef.INT_ZERO));
            //生成付款单号
            bean.setPaymentRequestCode(ssc11308Logic.createPaymentRequestCode());
            //核销金额，判断是否为负数
            if(bean.getMoneyFlag()!=null && !bean.getMoneyFlag()){
               BigDecimal verificationAmount = bean.getVerificationAmount();
                //转负数
                bean.setVerificationAmount(verificationAmount.negate());
            }

            result = this.ssc11308Logic.savePayment(bean);
        }

        if(result > 0){
            response.setStatus(SystemConst.RsStatus.SUCCESS);
            response.setMessage("操作成功");
            response.setResult(bean);
        }else{
            response.setStatus(SystemConst.RsStatus.FAIL);
            response.setMessage("操作失败");
        }
        return response;
    }

    /**
     * 预付款按比例已支付金额
     *
     * @param param
     * @return
     */
    @ApiOperation(value = "查询",notes = "查询预付款按比例已支付金额")
    @RequestMapping(value = "/ssc/findDeliveryPDList",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<String> findDeliveryPDList(@RequestBody RsRequest<SSC1130802RsParam> param) {
        logger.info("查询发货产品对应合同的总箱数");

        RsResponse<String> response=new  RsResponse<String>();

        List<SSC1130802RsBean> deliveryPDList = this.ssc11308Logic.findDeliveryPDList(param.getParam());

        /** 预付款按比例已支付 */
        BigDecimal paidDownPaymentPercentage = this.ssc11308Logic.plusPaidDownPaymentPercentage(deliveryPDList);


        if(paidDownPaymentPercentage!=null){
            response.setStatus(SystemConst.RsStatus.SUCCESS);
            response.setMessage("处理成功");
            response.setResult(paidDownPaymentPercentage.toString());
        }else{
            response.setStatus(SystemConst.RsStatus.FAIL);
            response.setMessage("数据不存在");
        }
        return response;
    }

}


