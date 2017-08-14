package com.msk.ssc.logic;

import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.common.logic.CommonLogic;
import com.msk.ssc.bean.SSC1131502Bean;
import com.msk.ssc.bean.SSC1131502Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by xia_xiaojie on 2016/8/22.
 */
@Service
public class SSC1131502Logic extends BaseLogic {
    @Autowired
    private CommonLogic commonLogic;

    /**
     * 注入DAO
     */
    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    @Transactional(readOnly = true)
    public List<SSC1131502Bean> findProductHistories(SSC1131502Param ssc1131502Param) {
        return super.findPageList(ssc1131502Param, SSC1131502Bean.class);
    }

    @Transactional
    public long saveProductHistory(SSC1131502Bean ssc1131502Bean) {
        long id = commonLogic.maxId("ssc_delivery_confirm_pd_history", "ID");
        ssc1131502Bean.setId(id);
        ssc1131502Bean.setCrtTime(new Date());
        super.save(ssc1131502Bean);
        return id;
    }

}
