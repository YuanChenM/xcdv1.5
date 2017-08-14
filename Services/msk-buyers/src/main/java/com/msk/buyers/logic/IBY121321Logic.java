package com.msk.buyers.logic;

import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.br.bean.IBR121412RsBean;
import com.msk.buyers.bean.IBY121319RsParam;
import com.msk.common.base.BaseLogic;
import com.msk.core.entity.ByBuyerBasicInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 批量根据买家ID更新买家上线状态
 *
 * @author zhou_yajun
 */
@Service
public class IBY121321Logic extends BaseLogic {
    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    interface SqlId{
        static String MODIFY_MARKET_STATUS_BY_BUYER_ID = "modifyMarketStatusByBuyerId";
    }

    /**
     * 批量根据买家ID更新买家上线状态
     */
    @Transactional
    public int modifyMarketStatusList(List<IBR121412RsBean> buyerList){
        int count = 0;

        for (IBR121412RsBean buyer: buyerList){
            count = count + this.modify(SqlId.MODIFY_MARKET_STATUS_BY_BUYER_ID,buyer);
        }
        return count;
    }
}
