package com.msk.buyers.logic;

import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.buyers.bean.BYMailSendHistoryBean;
import com.msk.common.base.BaseLogic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by zhang_jian4 on 20172/9.
 */
@Service
public class BYMailSendHistoryLogic extends BaseLogic {
    private static Logger logger = LoggerFactory.getLogger(BYMailSendHistoryLogic.class);
    interface SqlId{
        //插入邮件履历表
        static String SQL_INSERT_EMAIL_SEND_HISTORY = "insertEmailSendHistory";
    }
    /**
     * (non-Javadoc)
     *
     * @see BaseLogic#setBaseDao(com.hoperun.jdbc.mybatis.BaseDao)
     */
    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     * 插入邮件履历
     */
    @Transactional(readOnly = true)
    public int getMailAddressList(BYMailSendHistoryBean bean){
        return super.save(SqlId.SQL_INSERT_EMAIL_SEND_HISTORY, bean);
    }


}
