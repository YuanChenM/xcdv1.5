package com.msk.bs.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.bs.bean.BS2102112Bean;
import com.msk.common.base.BaseLogic;
import com.msk.core.entity.SlHouseAccount;

/**
 * Created by wang_haichun on 2016/8/23.
 */
@Service
public class BS2102112Logic extends BaseLogic {

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }


    interface SqlId {
        String SQL_ID_FIND_HOUSE_ACCOUNT_BUYER = "findHouseAccountBuyer";

    }


    @Transactional(readOnly = true)
    public BS2102112Bean findHouseAccountBuyer(SlHouseAccount houseAccount){
        return this.findOne(SqlId.SQL_ID_FIND_HOUSE_ACCOUNT_BUYER,houseAccount);
    }


}
