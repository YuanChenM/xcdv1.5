package com.msk.buyers.logic;

import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by tao_zhifa on 2016/9/13.
 */
@Service
public class IBY121323Logic extends BaseLogic{

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }
}
