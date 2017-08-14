package com.msk.br.logic;

import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 同步订单数据
 * Created by yuan_zhifei on 2016/7/22.
 */
@Service
public class IBR12130401Logic extends BaseLogic {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(IBR12130401Logic.class);

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

}
