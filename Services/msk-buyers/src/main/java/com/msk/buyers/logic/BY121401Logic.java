package com.msk.buyers.logic;


import com.hoperun.core.bean.BaseParam;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.buyers.bean.BY121401Param;
import com.msk.buyers.bean.BY121413Bean;
import com.msk.common.base.BaseLogic;
import com.msk.core.entity.ByMarketTerminalBasic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * 批发市场一览 logic
 * Created by tao_zhifa on 2016/7/7.
 */
@Service
public class BY121401Logic extends BaseLogic{

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(BY121401Logic.class);

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    @Transactional
    public int deleteDataBase(ByMarketTerminalBasic entity){
        return super.modify(SqlId.DELETE_DATA_BASE,entity);
    }

    @Transactional
    public int  deleteStoreByMarketId(ByMarketTerminalBasic entity){
        return super.modify(SqlId.DELETE_STORE_BY_MARKETID,entity);
    }
    @Transactional(readOnly = true)
    public List<BY121413Bean> findStoreByMarketId(BY121401Param param){
        return super.findList(SqlId.find_Store_By_MarketId,param);
    }

    @Transactional
    public int deleteSalePd(BY121401Param param){
        return super.modify(SqlId.delete_Sale_Pd,param);
    }


    interface  SqlId{
       static String DELETE_DATA_BASE = "deleteDataBase";
       static String DELETE_STORE_BY_MARKETID = "deleteStoreByMarketId";

        static String find_Store_By_MarketId = "findStoreByMarketId";
        static String delete_Sale_Pd = "deleteSalePd";

    }
}
