package com.msk.br.logic;

import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.br.bean.IBR121310Bean;
import com.msk.br.bean.IBR121310RsParam;
import com.msk.common.base.BaseLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by tao_zhifa on 2016/8/26.
 */
@Service
public class IBR121310Logic extends BaseLogic {

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    interface Sql {
        static String FIND_BUYER_POOL_ID = "findBuyerPoolId";
        static String FIND_EXISTENCE_COUNT = "findExistenceCount";
        static String MODIFY_DEL_FLG_FLASE = "modifyDelFlgFlase";
        static String MODIFY_DEL_FLG_TRUE = "modifyDelFlgTrue";


    }

    @Transactional(readOnly = true)
    public IBR121310Bean findBuyerPoolId(IBR121310RsParam param) {
        return super.findOne(Sql.FIND_BUYER_POOL_ID, param);
    }


    @Transactional(readOnly = true)
    public int findExistenceCount(IBR121310RsParam param){
        return super.getCount(Sql.FIND_EXISTENCE_COUNT,param);
    }

    @Transactional
    public int modifyDelFlgFlase(IBR121310RsParam param){
        return  super.modify(Sql.MODIFY_DEL_FLG_FLASE,param);
    }



    @Transactional
    public int modifyDelFlgTrue(IBR121310RsParam param){
        return  super.modify(Sql.MODIFY_DEL_FLG_TRUE,param);
    }



}
