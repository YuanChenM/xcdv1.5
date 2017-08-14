package com.msk.buyers.logic;

import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.buyers.bean.IBY121324RsParam;
import com.msk.common.base.BaseLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by tao_zhifa on 2016/10/11.
 */
@Service

public class IBY121324Logic extends BaseLogic{


    @Override
    @Autowired
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    interface Sql{
        String UPDATE_DELETE = "updateDelete";
        String UPDATE_INVOICE = "updateInvoice";
    }

    @Transactional
    public int updateDelete(IBY121324RsParam param){
        return super.modify(Sql.UPDATE_DELETE,param);
    }

    @Transactional
    public int updateInvoice(IBY121324RsParam param){
        return super.modify(Sql.UPDATE_INVOICE,param);
    }
}
