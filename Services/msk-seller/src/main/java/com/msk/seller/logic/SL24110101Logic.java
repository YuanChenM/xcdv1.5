package com.msk.seller.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.core.entity.SlAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * Created by fjm on 2016/1/28.
 */
@Service
public class SL24110101Logic extends BaseLogic{

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao){
        super.setBaseDao(baseDao);
    }

    @Autowired
    private SLAccountLogic slAccountLogic;

    interface SqlId{
        String SQL_ID_MODIFY_ONE = "modifyOne";
    }

    @Transactional
    public int update(SlAccount slAccount){
        slAccount.setUpdTime(DateTimeUtil.getCustomerDate());
        // 处理买手情况
        slAccountLogic.dealSLAccountBs(slAccount);
        // 更新 sl_account
        return super.modify(SqlId.SQL_ID_MODIFY_ONE, slAccount);
    }
}
