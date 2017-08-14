package com.msk.batch.inventory.logic;


import com.hoperun.core.bean.BaseParam;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by duan_kai on 2016/8/16.
 */
@Service
public class BIV152501Logic extends BaseLogic {
    private static Logger logger = LoggerFactory.getLogger(BIV152501Logic.class);

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    @Transactional
    public void touchOwnerIVFromIVDetail(BaseParam param) {
        super.modify("touchOwnerIVFromIVDetail", param);
    }

}
