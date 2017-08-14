package com.msk.ssc.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.common.logic.CommonLogic;
import com.msk.ssc.bean.SSC11307RsBean;
import com.msk.ssc.bean.SSC11307RsParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class SSC11307Logic extends BaseLogic {

    /** Logger */
    private Logger logger = LoggerFactory.getLogger(SSC11307Logic.class);

    @Autowired
    public CommonLogic commonLogic;

    interface SqlId {
        String DELETE_PAYMENT_REQUSET = "deletePaymentRequest";
        String GET_TOTAL_PAID_AMOUNT = "getTotalPaidAmount";
        String GET_MAX_REMIT_TIME= "getMaxRemitTime";
    }

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     * 删除付款单数据接口Logic
     * @param ssc11307RsParam
     * @return
     */
    @Transactional
    public int deletePaymentRequest(SSC11307RsParam ssc11307RsParam) {
        logger.info("删除付款单");
        // 排他检测
        if(ssc11307RsParam.getVer()!=null){
            super.versionValidator("SSC_PAYMENT_REQUEST", new String[]{"PAYMENT_REQUEST_ID"},
                    new Object[]{ssc11307RsParam.getPaymentRequestId()}, ssc11307RsParam.getVer());

        }

        return super.modify(SqlId.DELETE_PAYMENT_REQUSET, ssc11307RsParam);
    }
    /**
     * 获得已支付金额Logic
     * @param ssc11307RsParam
     * @return
     */
    @Transactional(readOnly = true)
    public List<SSC11307RsBean> getTotalPaidAmount(SSC11307RsParam ssc11307RsParam) {
        logger.info("获得已支付金额");
        return super.findList(SqlId.GET_TOTAL_PAID_AMOUNT, ssc11307RsParam);
    }

    /**
     * 获得最近支付时间Logic
     * @param
     * @return
     */
    @Transactional(readOnly = true)
    public List<SSC11307RsBean> getMaxRemitTime(SSC11307RsParam ssc11307RsParam) {
        logger.info("获得最近支付时间");
        return super.findList(SqlId.GET_MAX_REMIT_TIME, ssc11307RsParam);
    }


}