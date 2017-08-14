package com.msk.buyers.logic;


import com.hoperun.core.bean.BaseParam;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.buyers.bean.IBY121314RsBean;
import com.msk.common.base.BaseLogic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * 买家收货信息管控logic
 * Created by zhou_yajun on 2016/7/8.
 */
@Service
public class BY121314Logic extends BaseLogic{

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(BY121314Logic.class);

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     * 根据买家ID获取买家收货时间段List
     * 根据买家ID获取买家习惯收货时间和最早最晚收货时间
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public IBY121314RsBean findByRecTime(BaseParam param){
        //买家收货时间段
        List<IBY121314RsBean> buyerRecTimeList = this.findList(param);
        //买家习惯收货时间和最早最晚收货时间
        IBY121314RsBean buyerHabitRecTime = this.findOne(param);
        buyerHabitRecTime.setByBuyerRecTimeList(buyerRecTimeList);
        return buyerHabitRecTime;
    }
}
