package com.msk.buyers.logic;

import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.buyers.bean.IBY121223Param;
import com.msk.buyers.bean.IBY121223Result;
import com.msk.common.base.BaseLogic;
import com.msk.common.business.constant.BuyersConstant;
import com.msk.common.config.CodeMasterManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by zhu_kai1 on 2016/6/20.
 */
@Service
public class IBY121223Logic extends BaseLogic {

    private  static Logger logger = LoggerFactory.getLogger(IBY121223Logic.class);

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }


    /**
     *查询公共买家池买家信息
     * @param buyerHandParam
     * @return
     */
    @Transactional(readOnly = true)
    public List<IBY121223Result> getBuyerHand(IBY121223Param buyerHandParam){
        Map<String,String> marketingSatusMap = CodeMasterManager.findCodeMasterMap(BuyersConstant.MarketingsStatus.Type);
        if (!StringUtil.isNullOrEmpty(buyerHandParam.getMarketingsStatusName())){
            for(String key :marketingSatusMap.keySet()){
                // 模糊匹配营销/销售类型
                if(marketingSatusMap.get(key).indexOf(buyerHandParam.getMarketingsStatusName()) !=-1){
                    buyerHandParam.setMarketingsStatusCode(key);
                    break;
                }
            }
        }
        List<IBY121223Result> buyerHandList =super.findList(buyerHandParam);
        for(IBY121223Result iby121223Result : buyerHandList){
            if(marketingSatusMap.containsKey(iby121223Result.getMarketingsStatusCode())){
                iby121223Result.setMarketingsStatusName(marketingSatusMap.get(iby121223Result.getMarketingsStatusCode()));
            }else {
                // 防止为null时，页面产生告警。
                iby121223Result.setMarketingsStatusName("");
            }
        }
        return buyerHandList;
    }
}
