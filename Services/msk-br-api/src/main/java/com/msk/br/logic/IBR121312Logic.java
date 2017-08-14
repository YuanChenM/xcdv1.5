package com.msk.br.logic;

import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.br.bean.IBR121312RsParam;
import com.msk.common.base.BaseLogic;
import com.msk.core.entity.BrBuyerMarketingStatusHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by tao_zhifa on 2016/9/21.
 */
@Service
public class IBR121312Logic extends BaseLogic{

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    interface SqlId{
        static String FIND_BRBUYER_MARKETING_STATUS_HISTORY= "findBrBuyerMarketingStatusHistory";
        static String CHECK_COUNT = "checkCount";
        static String SAVE_BRBUYER_MARKETING_STATUS_HISTORY = "saveBrBuyerMarketingStatusHistory";
        static String UPDATE_BRBUYER_MARKETING_STATUS_HISTORY = "updateBrBuyerMarketingStatusHistory";
    }



    @Transactional(readOnly = true)
    public BrBuyerMarketingStatusHistory findBrBuyerMarketingStatusHistory(IBR121312RsParam param){
        return super.findOne(SqlId.FIND_BRBUYER_MARKETING_STATUS_HISTORY,param);
    }

    @Transactional(readOnly = true)
    public int checkCount(IBR121312RsParam param){
        return super.getCount(SqlId.CHECK_COUNT,param);
    }

    @Transactional
    public int saveBrBuyerMarketingStatusHistory(IBR121312RsParam param){
        return super.save(SqlId.SAVE_BRBUYER_MARKETING_STATUS_HISTORY,param);
    }

    @Transactional
    public int updateBrBuyerMarketingStatusHistory(IBR121312RsParam param){
        return super.modify(SqlId.UPDATE_BRBUYER_MARKETING_STATUS_HISTORY,param);
    }
}
