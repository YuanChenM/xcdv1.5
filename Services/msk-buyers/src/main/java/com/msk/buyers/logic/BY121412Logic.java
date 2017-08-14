package com.msk.buyers.logic;


import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 菜场定性定级各阶段一览表 logic
 * Created by yuan_zhifei on 2016/7/13.
 */
@Service
public class BY121412Logic extends BaseLogic{

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(BY121412Logic.class);

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }
}
