package com.msk.ssc.logic;

import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by liu_yan2 on 2016/7/4
 */
@Service
public class SSC11310Logic extends BaseLogic {

    /** Logger */
    private Logger logger = LoggerFactory.getLogger(SSC11310Logic.class);

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }
}