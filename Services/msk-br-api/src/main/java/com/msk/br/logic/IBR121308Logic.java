package com.msk.br.logic;

import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.br.bean.IBR121307RsParam;
import com.msk.br.bean.IBR121308RsBean;
import com.msk.br.bean.IBR121308RsParam;
import com.msk.common.base.BaseLogic;
import com.msk.common.logic.CommonLogic;
import com.msk.core.entity.BrMPdClasses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 查询公共买家池买家信息接口
 * Created by tao_zhifa on 2016/8/10.
 */
@Service
public class IBR121308Logic extends BaseLogic{

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(IBR121308Logic.class);
    @Autowired
    private CommonLogic commonLogic;
    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    interface SqlId{
        static String FIND_BY_BUYER_SALES_TARGET_LIST = "findByBuyerSalestargetList";
        static String FIND_BY_BUYER_PD_CLA_LIST = "findbyBuyerPdClaList";
    }

    @Transactional(readOnly = true)
    public List<IBR121308RsBean> findByBuyerSalestargetList(IBR121308RsParam rsParam){
        return super.findList(SqlId.FIND_BY_BUYER_SALES_TARGET_LIST,rsParam);
    }

    @Transactional(readOnly = true)
    public List<IBR121308RsBean> findbyBuyerPdClaList(IBR121308RsParam rsParam){
        return super.findList(SqlId.FIND_BY_BUYER_PD_CLA_LIST,rsParam);
    }

}
