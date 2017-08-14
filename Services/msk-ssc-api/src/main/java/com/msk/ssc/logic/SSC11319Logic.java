package com.msk.ssc.logic;

import com.hoperun.core.bean.BasePageParam;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.common.business.constant.SscConstant;
import com.msk.ssc.bean.SSC11319RsBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 发货订单一览Logic
 *
 */
@Service
public class SSC11319Logic extends BaseLogic {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(SSC11319Logic.class);


    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     * 查询资金池列表
     * @param pageParam
     * @return
     */
    @Transactional(readOnly = true)
    public PageResult<SSC11319RsBean> findSSC11319List(BasePageParam pageParam) {
        logger.info("查询资金池列表");
        PageResult<SSC11319RsBean> result = this.findPage(pageParam, SSC11319RsBean.class);
        List<SSC11319RsBean> list = result.getData();
        for (SSC11319RsBean bean : list) {
            String paymentStr = "";
            //预付款
            if(String.valueOf(SscConstant.SscPaymentType.DOWN_PAYMENT).equals(bean.getPaymentType())){
                paymentStr = "合同编号: " + bean.getContractCode();
            }
            //进度款
            else if(String.valueOf(SscConstant.SscPaymentType.PROGRESS_PAYMENT).equals(bean.getPaymentType())){
                paymentStr = "发货订单号: " + bean.getDeliveryCode();
            }
            //余款
            else if(String.valueOf(SscConstant.SscPaymentType.BALANCE).equals(bean.getPaymentType())){
                paymentStr = "核销单号: " + bean.getVerificationCode();
            }
            bean.setPaymentStr(paymentStr);
        }
        return result;
    }

}


