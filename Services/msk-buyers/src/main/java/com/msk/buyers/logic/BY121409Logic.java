package com.msk.buyers.logic;


import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.buyers.bean.BY121409Bean;
import com.msk.common.base.BaseLogic;
import com.msk.common.logic.CommonLogic;
import com.msk.core.entity.ByMarketFoodBasic;
import com.msk.core.entity.ByMarketTerminalBasic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * 批发市场新增 logic
 * Created by zhou_yajun on 2016/7/8.
 */
@Service
public class BY121409Logic extends BaseLogic{

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(BY121409Logic.class);

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }
    @Autowired
    private CommonLogic commonLogic;
    interface SqlId{
        String MODIFY_OLD_PHASE = "modifyOldPhase";
        String MODIFY_NEW_PHASE = "modifyNewPhase";
    }
    /**
     * 菜场基本信息编辑
     * @param by121409Bean
     * @return
     */
    @Transactional
    public BY121409Bean foodBasicModify(BY121409Bean by121409Bean,BY121409Bean returnObject){
        logger.info("菜场信息编辑");

        int resultCount;

        //如果保存为已审批状态时,将既存数据的菜场编码和菜场性质设为新数据的编码和性质
        if("1".equals(by121409Bean.getMarketStatus())){
            BaseParam param = new BaseParam();
            param.setFilter("marketId",by121409Bean.getMarketId());
            ByMarketFoodBasic marketFoodBasic = this.findOne(param);
            by121409Bean.setMarketCode(marketFoodBasic.getMarketCode());
            by121409Bean.setMarketNature(marketFoodBasic.getMarketNature());
            by121409Bean.setMarketNatureName(marketFoodBasic.getMarketNatureName());
        }
        //根据旧阶段和批发市场编码 更新IsPhaseNew
        this.modify(SqlId.MODIFY_OLD_PHASE,by121409Bean);
        //根据新阶段和批发市场编码 更新IsPhaseNew
        this.modify(SqlId.MODIFY_NEW_PHASE,by121409Bean);

        //当前调研阶段和旧调研阶段相同时
        if(by121409Bean.getOldResearchPhase().equals(by121409Bean.getResearchPhase())){
            by121409Bean.setIsPhaseNew("0");
        }else{
            //当前调研阶段和旧调研阶段不同时
            by121409Bean.setIsPhaseNew("1");
        }
        int updateCount = this.modify(by121409Bean);
        if(updateCount == NumberConst.IntDef.INT_ONE){
            Long maxId = commonLogic.maxId("by_market_food_basic","ID");
            by121409Bean.setId(maxId);
            resultCount = this.save(by121409Bean);
            if(resultCount == NumberConst.IntDef.INT_ONE){
                returnObject.setActionType("modify");
                returnObject.setResearchPhase(by121409Bean.getResearchPhase());
                return returnObject;
            }
        }
        throw new BusinessException("编辑失败,未找到该菜场!");
    }

    /**
     * 根据输入的批发市场名称查询批发市场信息
     * @param by121409Bean
     * @return
     */
    public String findMarketByName(BY121409Bean by121409Bean){

        logger.info("判断输入的菜场名称是否已存在");

        BaseParam param = new BaseParam();

        param.setFilter("marketName",by121409Bean.getMarketName());
        param.setFilter("lgcsAreaCode",by121409Bean.getLgcsAreaCode());
        param.setFilter("cityCode",by121409Bean.getCityCode());
        param.setFilter("districtCode",by121409Bean.getDistrictCode());

        int count = this.getCount(param);

        if(count > NumberConst.IntDef.INT_ZERO){
            return "输入的菜场已存在,请重新录入";
        }
        return null;
    }
}
