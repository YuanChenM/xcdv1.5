package com.msk.ds.logic;

import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.ds.bean.SC183104Bean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * SC183104Logic.
 * @author fjm
 * @version 1.0
 **/
@Service
public class SC183104Logic extends BaseLogic {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(SC183104Logic.class);

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    interface SqlId {
        String SQL_ID_DELETE_ACLBEAN="deleteAclBean";

    }
    @Transactional
    public int deleteBean(SC183104Bean sc183104Bean){
        return super.remove(SqlId.SQL_ID_DELETE_ACLBEAN,sc183104Bean);
    }

}
