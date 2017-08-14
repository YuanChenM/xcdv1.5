package com.msk.buyers.logic;


import com.hoperun.core.bean.BaseParam;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.buyers.bean.BY121316Bean;
import com.msk.common.base.BaseLogic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 */
@Service
public class BY121316Logic extends BaseLogic {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(BY121316Logic.class);

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    interface SqlId {
        String DELETE_ACCOUNT_INFO = "deleteAccountInfo";
        String DELETE_BASIC_INFO = "deleteBasicInfo";
    }

    /**
     * 删除账号信息
     *
     * @param param
     * @return
     */
    @Transactional
    public int deleteByAccount(BY121316Bean param) {
        logger.info("删除账号信息");
        return super.modify(SqlId.DELETE_ACCOUNT_INFO, param);
    }


    /**
     * 删除基本信息表维系和qq
     *
     * @param param
     * @return
     */
    @Transactional
    public int deleteByBasicInfo(BY121316Bean param) {
        logger.info("删除基本信息表维系和qq");
        return super.modify(SqlId.DELETE_BASIC_INFO, param);
    }


}
