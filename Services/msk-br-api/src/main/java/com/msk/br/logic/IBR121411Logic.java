package com.msk.br.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.br.bean.IBR12141101RsBean;
import com.msk.common.base.BaseLogic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 根据买家ID获取所属买家池
 */
@Service
public class IBR121411Logic extends BaseLogic {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(IBR121411Logic.class);


    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

   interface SqlId{
       static String GET_BR_BUYER_POOL_LIST = "getBrBuyerPoolList";
   }

    public List<IBR12141101RsBean>  getBrBuyerPoolList(BaseParam param){
        return super.findList(SqlId.GET_BR_BUYER_POOL_LIST,param);
    }



}
