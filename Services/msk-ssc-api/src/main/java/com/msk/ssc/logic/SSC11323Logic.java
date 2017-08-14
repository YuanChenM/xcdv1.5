package com.msk.ssc.logic;


import com.hoperun.core.consts.SystemConst;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.ssc.bean.SSC11323Bean;
import com.msk.ssc.bean.SSC11323Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created on 2016/8/3.
 */
@Service
public class SSC11323Logic extends BaseLogic {

    private static Logger logger = LoggerFactory.getLogger(SSC11323Logic.class);

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }
    @Transactional
    public String deletePreInto(SSC11323Param ssc11323Param){
        if(ssc11323Param.getInvoiceRequestId() != null && ssc11323Param.getInvoiceRequestId() != 0){
            super.versionValidator("SSC_INVOICE_REQUEST", new String[]{"INVOICE_REQUEST_ID"}, new Object[]{ssc11323Param.getInvoiceRequestId()}, ssc11323Param.getVer());
        }
        String flag = SystemConst.RsStatus.FAIL;
        int result = super.modify(SqlId.SQL_ID_DELETE_INVOICE_REQUEST,ssc11323Param);
        if(result > 0){
            flag = SystemConst.RsStatus.SUCCESS;
        }

        return flag;
    }

    interface SqlId{
       String SQL_ID_DELETE_INVOICE_REQUEST="deleteInvoiceRequest";
    }



}


