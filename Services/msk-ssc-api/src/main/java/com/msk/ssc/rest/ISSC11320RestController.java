package com.msk.ssc.rest;

import com.hoperun.core.consts.SystemConst;

import com.hoperun.core.utils.DecimalUtil;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.business.constant.SscConstant;
import com.msk.ssc.bean.*;
import com.msk.ssc.logic.SSC11320Logic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by peng_hao
 */
@RestController
public class ISSC11320RestController extends BaseRsController {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(ISSC11320RestController.class);

    @Autowired
    private SSC11320Logic ssc11320Logic;

    /**
     * 先通过付款记录id查询付款记录，再查询付款申请单(资金池详细)
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/ssc/findSscCashPoolingDetail",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<SSC11320RsBean> findSscCashPoolingDetail(@RequestBody RsRequest<SSC11320RsParam> param) {
        logger.info("查询付款申请单");
        RsResponse<SSC11320RsBean> response = new RsResponse<>();
        SSC11320RsBean  bean= this.ssc11320Logic.findInfoById(param.getParam());
        if(bean != null){
            param.getParam().setPaymentRequestId(bean.getPaymentRequestId());
            SSC11320RsBean res = this.ssc11320Logic.findRequestById(param.getParam());
            bean.setContractTotalAmount(res.getContractTotalAmount());
            bean.setPaymentType(res.getPaymentType());
            bean.setApprovalPerson(res.getApprovalPerson());
            bean.setApprovalDate(res.getApprovalDate());
            bean.setAuditingPerson(res.getAuditingPerson());
            bean.setAuditingDate(res.getAuditingDate());
            bean.setPaymentTerm(res.getPaymentTerm());
            bean.setContractCode(res.getContractCode());
            bean.setVerificationCode(res.getVerificationCode());
            bean.setDeliveryCode(res.getDeliveryCode());
            bean.setContractId(res.getContractId());
            bean.setDeliveryId(res.getDeliveryId());
            bean.setVerificationId(res.getVerificationId());
            bean.setTotalAmount(res.getTotalAmount());
            //参数塞入付款参数为已付款（2）
            param.getParam().setStatus(SscConstant.PaymentStatus.PAID);
            //获得该付款申请累计付款金额
            BigDecimal accumulateAmount=this.ssc11320Logic.getAccumulateAmount(param.getParam());
            //获得尚余付款金额（本次付款申请金额-累计付款金额）
            BigDecimal remainAmount= DecimalUtil.subtract(bean.getTotalAmount(), accumulateAmount);
            //判断尚余付款金额是否大于0，小于0赋值0
            int remainAmountRes=remainAmount.compareTo(BigDecimal.ZERO);
            if(remainAmountRes>0){
                bean.setRemainAmount(remainAmount);
            }else{
                bean.setRemainAmount(new BigDecimal(0));
            }
            bean.setAccumulateAmount(accumulateAmount);
            response.setStatus(SystemConst.RsStatus.SUCCESS);
            response.setMessage("查询成功");
            response.setResult(bean);
        }else{
            response.setStatus(SystemConst.RsStatus.FAIL);
            response.setMessage("数据不存在");
        }
        return response;
    }

}


