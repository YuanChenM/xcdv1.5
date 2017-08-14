package com.msk.buyers.logic;

import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.buyers.bean.BY121310Bean;
import com.msk.buyers.bean.BY121310Param;
import com.msk.common.base.BaseLogic;
import com.msk.core.entity.ByBuyerBasicInfo;
import com.msk.core.entity.ByBuyerPdCla;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 买家买家池归属
 * Created by tao_zhifa on 2016/7/20.
 */
@Service
public class BY121310Logic extends BaseLogic {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(BY121310Logic.class);

    /**
     * (non-Javadoc)
     *
     * @see BaseLogic#setBaseDao(BaseDao)
     */
    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    interface  SqlId{
        static  String UPDATE_MACINING = "updateMacining";
        static  String UPDATE_DEL_FLG = "updateDelFlg";
        static  String FIND_DEL_FLG = "findDelFlg";
        String FIND_SUPERIOR_TYPE = "findSuperiorType";
    }

    @Transactional
    public int updateMacining(BY121310Param param){
        return super.modify(SqlId.UPDATE_MACINING,param);
    }

    @Transactional
    public int updateDelFlg(BY121310Param param){
        return super.modify(SqlId.UPDATE_DEL_FLG,param);
    }

    @Transactional(readOnly = true)
    public List<ByBuyerPdCla> findDelFlg(String buyersId){
        BY121310Param param = new BY121310Param();
        param.setBuyerId(buyersId);
        return super.findList(SqlId.FIND_DEL_FLG,param);
    }


    @Transactional(readOnly = true)
    public BY121310Bean findSuperiorType(BY121310Param param){
        return super.findOne(SqlId.FIND_SUPERIOR_TYPE,param);
    }

}


