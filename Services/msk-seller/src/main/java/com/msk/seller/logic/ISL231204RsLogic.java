package com.msk.seller.logic;


import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhou_yajun on 2016/5/25.
 */
@Service
public class ISL231204RsLogic extends BaseLogic {

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

}
