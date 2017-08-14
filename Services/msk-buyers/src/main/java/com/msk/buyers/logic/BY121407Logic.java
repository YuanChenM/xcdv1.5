package com.msk.buyers.logic;

import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.buyers.bean.BY121407Param;
import com.msk.common.base.BaseLogic;
import com.msk.core.entity.ByMarketFoodBasic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 菜场一览 logic
 * Created by tao_zhifa on 2016/7/8.
 */
@Service
public class BY121407Logic extends BaseLogic{

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(BY121401Logic.class);

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     * 删除菜场一览
     * @param param
     * @return
     */
    @Transactional
    public int deleteDataBase(ByMarketFoodBasic param){
        return super.modify(SqlId.DELETE_DATA_BASE,param);
    }

    interface  SqlId{
        static String DELETE_DATA_BASE = "deleteDataBase";
    }
}
