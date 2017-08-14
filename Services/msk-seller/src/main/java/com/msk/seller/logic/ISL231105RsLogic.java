package com.msk.seller.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.seller.bean.ISL231105RsParam;
import com.msk.seller.bean.ISL231105RsResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by zhang_chi on 2016/4/28.
 */
@Service
public class ISL231105RsLogic extends BaseLogic {

    /**
     * SQL Map 中SQL ID定义
     *
     * @author zhang_chi
     */
    interface SqlId {
        static final String SQL_ID_FIND_SL_ACCOUNT = "findSlAccount";
    }

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    @Transactional(readOnly = true)
    public ISL231105RsResult querySlAccount(ISL231105RsParam iSL231105RsParam) {
        BaseParam param = new BaseParam();
        if (StringUtil.isNullOrEmpty(iSL231105RsParam.getSlTel()) && StringUtil.isNullOrEmpty(iSL231105RsParam.getSlAccount())
                && StringUtil.isNullOrEmpty(iSL231105RsParam.getUserName())) {
            throw new BusinessException("卖家账号，手机号码二者必须有一");
        }
        param.setFilter("slAccount", iSL231105RsParam.getSlAccount());
        param.setFilter("accountPsd", iSL231105RsParam.getAccountPsd());
        param.setFilter("slTel", iSL231105RsParam.getSlTel());
        param.setFilter("userName", iSL231105RsParam.getUserName());
        ISL231105RsResult slAccounts = super.findOne(SqlId.SQL_ID_FIND_SL_ACCOUNT, param);
        return slAccounts;
    }
}
