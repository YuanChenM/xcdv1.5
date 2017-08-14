package com.msk.buyers.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.common.logic.CommonLogic;
import com.msk.core.entity.ByBuyerBasicInfo;
import com.msk.core.entity.ByMarketTerminalFileInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 */
@Service
public class BY121311Logic extends BaseLogic {

    private  static Logger logger = LoggerFactory.getLogger(BY121311Logic.class);
    @Autowired
    private CommonLogic commonLogic;
    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }


    interface SqlId{
        static String FIND_BY_MARKET_TERMINA_BASIC = "findTermainalInfo";
    }
    /**
     *根据buyerId查询信息
     * @return
     */
    @Transactional(readOnly = true)
    public ByBuyerBasicInfo findByBuyerBasicInfo(String buyerId){
        BaseParam param = new BaseParam();
        param.setFilter("buyerId",buyerId);
        return super.findOne(param);
    }

}
