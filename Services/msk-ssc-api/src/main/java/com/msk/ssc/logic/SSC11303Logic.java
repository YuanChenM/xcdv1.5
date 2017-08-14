package com.msk.ssc.logic;


import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.core.entity.SscBusinessTerms;
import com.msk.ssc.bean.SSC11303RsParam;
import com.msk.ssc.bean.SSC11314RsParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by tao_zhifa on 2016/6/29.
 * 合同管理画面 logic
 */
@Service
public class SSC11303Logic extends BaseLogic{

    /**
     * Logger
     */
    private Logger logger = LoggerFactory.getLogger(SSC11303Logic.class);

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     * 根据ID删除合同
     * @param param
     * @return
     */
    @Transactional
    public int deleteContractBasic(SSC11303RsParam param) {
        String[] primaryKey = {"CONTRACT_ID"};
        Object[] primaryKeyValue = {param.getContractId()};
        super.versionValidator("ssc_contract_basic", primaryKey, primaryKeyValue, param.getVer());
        param.setUpdTime(DateTimeUtil.getCustomerDate());
        return super.modify(SqlId.SQL_ID_DELETE_CONTRACT_BASIC, param);
    }

    interface SqlId {
        String SQL_ID_DELETE_CONTRACT_BASIC = "deleteContractBasic"; //根据ID删除合同
    }


}
