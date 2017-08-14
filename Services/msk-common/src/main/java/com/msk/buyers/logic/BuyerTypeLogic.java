package com.msk.buyers.logic;

import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yuan_chen1 on 2016/7/15.
 */
@Service("buyerTypeLogic")
public class BuyerTypeLogic extends BaseLogic {

    /** logger */
    private static Logger logger = LoggerFactory.getLogger(BuyerTypeLogic.class);

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }
}
