package com.msk.ssc.logic;

import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.common.logic.CommonLogic;
import com.msk.ssc.bean.SSC1130801RsBean;
import com.msk.ssc.bean.SSC1130801RsParam;
import com.msk.ssc.bean.SSC11308RsBean;
import com.msk.ssc.bean.SSC11308RsParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * 付款记录
 */
@Service
public class SSC1130801Logic extends BaseLogic {

    /** Logger */
    private Logger logger = LoggerFactory.getLogger(SSC1130801Logic.class);

    @Autowired
    private CommonLogic commonLogic;


    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    interface SqlId {
        String FIND_PAYMENT_INFO_BY_PAYMENT_ID = "findPaymentInfoById";

    }

    @Transactional(readOnly = true)
    public SSC1130801RsBean findPaymentInfoBase(SSC1130801RsParam param){
        return  super.findOne(SqlId.FIND_PAYMENT_INFO_BY_PAYMENT_ID,param);
    }

    @Transactional
    public int savePaymentInfo(SSC1130801RsBean bean){
        return super.save(bean);
    }

    @Transactional
    public int modifyPaymentInfo(SSC1130801RsBean bean){

        // 排他检测
        if(bean.getVer()!=null){
            super.versionValidator("SSC_PAYMENT_INFO", new String[]{"PAYMENT_ID"},
                    new Object[]{bean.getPaymentId()}, bean.getVer());
        }

        return super.modify(bean);
    }

}