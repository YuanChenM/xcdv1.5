package com.msk.ssc.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.DecimalUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.common.logic.CommonLogic;
import com.msk.ssc.bean.SSC1130802RsBean;
import com.msk.ssc.bean.SSC1130802RsParam;
import com.msk.ssc.bean.SSC11308RsBean;
import com.msk.ssc.bean.SSC11308RsParam;
import org.apache.commons.collections.CollectionUtils;
import org.apache.velocity.runtime.directive.Foreach;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;


/**
 * 付款申请
 */
@Service
public class SSC11308Logic extends BaseLogic {

    /** Logger */
    private Logger logger = LoggerFactory.getLogger(SSC11308Logic.class);

    @Autowired
    private CommonLogic commonLogic;

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    interface SqlId {
        String FIND_PAYMENT_BY_PAYMENT_REQUEST_ID = "findPaymentByPaymentRequestId";
        String FIND_MAX_PAYMENT_REQUEST_CODE = "findMaxPaymentRequestCode";
        String FIND_DELIVERY_PD_LIST = "findDeliveryPDList";
    }

    @Transactional(readOnly = true)
    public SSC11308RsBean findPaymentBase(SSC11308RsParam param){
        return  super.findOne(SqlId.FIND_PAYMENT_BY_PAYMENT_REQUEST_ID,param);
    }

    @Transactional
    public int savePayment(SSC11308RsBean bean){
        return super.save(bean);
    }

    @Transactional
    public int modifyPayment(SSC11308RsBean bean){
        // 排他检测
        if(bean.getVer()!=null){
            super.versionValidator("SSC_PAYMENT_REQUEST", new String[]{"PAYMENT_REQUEST_ID"},
                    new Object[]{bean.getPaymentRequestId()}, bean.getVer());
        }

        return super.modify(bean);
    }

    /***
     * 生成付款单号
     * @return
     */
    public String createPaymentRequestCode(){

        String paymentRequestCode = (String)super.findObject(SqlId.FIND_MAX_PAYMENT_REQUEST_CODE,new BaseParam());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String today = sdf.format(DateTimeUtil.getCustomerDate());
        if(StringUtil.isNullOrEmpty(paymentRequestCode)){
            paymentRequestCode = "ZF" + today +  NumberConst.IntDef.INT_THOUSAND;
        }else{
            if(paymentRequestCode.length()== NumberConst.IntDef.INT_FOURTEEN){
                String todayDB = paymentRequestCode.substring(2, 10);
                if(today.equals(todayDB)){
                    Integer number = Integer.parseInt(paymentRequestCode.substring(10));
                    number ++;
                    paymentRequestCode = paymentRequestCode.substring(0 ,10)+number;
                }else{
                    paymentRequestCode = "ZF" + today +"1000";
                }
            }
        }
        return paymentRequestCode;
    }

    /**
     * 查询发货产品对应合同的总箱数
     * @param ssc1130802RsParam
     * @return
     */
    public List<SSC1130802RsBean> findDeliveryPDList(SSC1130802RsParam ssc1130802RsParam){
        return super.findList(SqlId.FIND_DELIVERY_PD_LIST,ssc1130802RsParam);
    }

    /**
     * 计算 预付款按比例已支付金额
     * @param deliveryPdList
     * @return
     */
    public BigDecimal plusPaidDownPaymentPercentage(List<SSC1130802RsBean> deliveryPdList){


        if (CollectionUtils.isNotEmpty(deliveryPdList)){
            /** 预付款按比例已支付 */
            BigDecimal paidDownPaymentPercentage = new BigDecimal(0.00);
            for(SSC1130802RsBean delivery : deliveryPdList){
                BigDecimal deliveryProductValue = delivery.getDeliveryProductValue();
                BigDecimal downPayment = delivery.getDownPayment().divide(new BigDecimal(NumberConst.IntDef.INT_HUNDRED));
                BigDecimal temp = deliveryProductValue.multiply(downPayment);
                paidDownPaymentPercentage = DecimalUtil.add(temp,paidDownPaymentPercentage);
            }
            java.text.DecimalFormat df = new java.text.DecimalFormat("0.00"); //格式化，保留两位小数
            return new BigDecimal(df.format(paidDownPaymentPercentage));
        }else{
            return null;
        }

    }


}