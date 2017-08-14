package com.msk.seller.logic;


import org.springframework.beans.factory.annotation.Autowired;

import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;

public class Sl241113Logic extends BaseLogic {

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }
}
