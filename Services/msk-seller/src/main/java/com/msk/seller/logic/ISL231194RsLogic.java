package com.msk.seller.logic;


import com.hoperun.core.bean.BaseParam;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.seller.bean.ISL231194RsBankResult;
import com.msk.seller.bean.ISL231194RsResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangchi on 2016/5/13.
 */
@Service
public class ISL231194RsLogic extends BaseLogic {

    /**
     * logger
     */
    private Logger logger = LoggerFactory.getLogger(ISL231194RsLogic.class);

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     * SQL Map 中SQL ID定义
     *
     * @author gyh
     */
    interface SqlId {
        static final String SQL_ID_GET_SL_BANK = "getSlBank";
    }


    /**
     * 查询卖家银行卡信息
     * @return 查询全部卖家基本信息和卖家名下所有银行卡信息
     * @author zhangchi
     */
    @Transactional(readOnly = true)
    public List<ISL231194RsResult> querySlBank() {
        logger.debug("查询全部卖家基本信息和卖家名下所有银行卡信息");
        List<ISL231194RsResult> isl231194ResultList = new ArrayList<ISL231194RsResult>();

        BaseParam params=new BaseParam();
        // 查询数据
        return  super.findList(SqlId.SQL_ID_GET_SL_BANK, params);
    }


}
