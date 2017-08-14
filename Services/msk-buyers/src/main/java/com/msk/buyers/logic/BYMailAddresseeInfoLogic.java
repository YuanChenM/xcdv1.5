package com.msk.buyers.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.buyers.bean.BYMailAddresseeInfoBean;
import com.msk.common.base.BaseLogic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by zhang_jian4 on 20172/9.
 */
@Service
public class BYMailAddresseeInfoLogic extends BaseLogic {
    private static Logger logger = LoggerFactory.getLogger(BYMailAddresseeInfoLogic.class);
    interface SqlId{
        //获取发送、抄送邮箱列表
        static String SQL_GET_ADDRESSEE_LIST = "getAddressEmailList";
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
     * 获取邮箱列表
     */
    @Transactional(readOnly = true)
    public List<BYMailAddresseeInfoBean> getMailAddressList(BaseParam param){
        return super.findList(SqlId.SQL_GET_ADDRESSEE_LIST, param);
    }


}
