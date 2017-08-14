package com.msk.buyers.logic;

import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.buyers.bean.BY121409Bean;
import com.msk.common.base.BaseLogic;
import com.msk.core.entity.ByMarketFood;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * BY121306Logic.
 *
 * @author zhou_ling
 */
@Service
public class BY121306Logic extends BaseLogic {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(BY121306Logic.class);

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

    @Autowired
    private BY121411Logic by121411Logic;

    /**
     * SQL Map 中SQL ID定义
     *
     * @author zhou_Ling
     */
    interface SqlId {
        //菜场详细信息获取
        static String SQL_FOOD_MARKET_DETAIL_INFO_FIND = "findFoodMarketInfo";
        //菜场详细信息更新
        static String SQL_FOOD_MARKET_DETAIL_INFO_SAVE = "saveFoodMarketInfo";
        //菜场详细信息增加
        static String SQL_FODMARKET_DETAIL_INFO_ADD = "addFodMarketInfo";
        //查询菜场个数
        static String SQL_FODMARKET_DETAIL_INFO_COUNT = "getFodMarketInfoCount";
        //查询菜场Id
        static String SQL_GET_FOD_MARKET_ID = "getFodMarketId";

    }

    /**
     * 菜场详细信息查询接口
     *
     * @param fodMarketId fodMarketId
     * @return
     */
    @Transactional(readOnly = true)
    public ByMarketFood findMarketFood(String fodMarketId) {
        BasePageParam inParam = new BasePageParam();
        inParam.setFilter("fodMarketId", fodMarketId);
        ByMarketFood byMarketFood = super.findOne(SqlId.SQL_FOOD_MARKET_DETAIL_INFO_FIND, inParam);
        return byMarketFood;
    }

    /**
     * 菜场详细信息更新
     * @param byMarketFood
     * @return
     */
    @Transactional
    public int byMarketFoodModify(ByMarketFood byMarketFood){

        // 更新市场详细信息
        /*byMarketFood.setUpdId("admin");
        byMarketFood.setUpdTime(DateTimeUtil.getCustomerDate());*/
        int count = super.save(SqlId.SQL_FOOD_MARKET_DETAIL_INFO_SAVE, byMarketFood);

        BY121409Bean by121409Bean = new BY121409Bean();
        by121409Bean.setMarketId(byMarketFood.getFodMarketId());
        by121409Bean.setMarketCode(byMarketFood.getMarketCode());
        by121409Bean.setMarketNatureName(byMarketFood.getMarketTypeName());
        by121411Logic.modifyBuyerCode(by121409Bean);

        return count;
    }

    /**
     * 菜场详细信息增加
     * @param byMarketFood
     * @return
     */
    @Transactional
    public int byMarketFodAdd(ByMarketFood byMarketFood){

        // 增加菜场详细信息
       /* byMarketFood.setCrtId("admin");
        byMarketFood.setCrtTime(DateTimeUtil.getCustomerDate());*/
        int count = super.save(SqlId.SQL_FODMARKET_DETAIL_INFO_ADD, byMarketFood);
        return count;
    }

    /**
     * 查询菜场个数
     * @param byMarketFood
     * @return
     */
    public int byMarketFodCount(ByMarketFood byMarketFood){

        int count = super.getCount(SqlId.SQL_FODMARKET_DETAIL_INFO_COUNT, byMarketFood);
        return count;
    }

    /**
     * 查询菜场id
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public List<ByMarketFood> getFodMarketId(BaseParam param){
        return super.findList(SqlId.SQL_GET_FOD_MARKET_ID, param);
    }


}
