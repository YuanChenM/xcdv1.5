package com.msk.buyers.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.common.logic.CommonLogic;
import com.msk.core.entity.ByMarketFoodBasic;
import com.msk.core.entity.ByMarketFoodFileInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 */
@Service
public class BY121408Logic extends BaseLogic {

    private  static Logger logger = LoggerFactory.getLogger(BY121408Logic.class);
    @Autowired
    private CommonLogic commonLogic;
    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }


    interface SqlId{
        //插入数据
        static String BY_MARKET_TERMINAL_FILE_INFO = "byMarketFoodFileInfoSave";
    }
    /**
     *根据marketId查询信息
     * @return
     */
    @Transactional(readOnly = true)
    public ByMarketFoodBasic findOne(String marketId){
        BaseParam baseParam = new BaseParam();
        baseParam.getFilterMap().put("marketId",marketId);
        return super.findOne(baseParam);
    }

    /**
     * 插入数据
     * @param FileInfo
     * @return
     */
    public int saveFileInfo(ByMarketFoodFileInfo FileInfo){

        //假定值，先设一个
       /* String crtId = "ctrName";*/
        Long id = commonLogic.maxId("by_market_Food_file_info","ID");
        FileInfo.setId(id);
/*        FileInfo.setCrtId(crtId);
        FileInfo.setCrtTime(DateTimeUtil.getCustomerDate());*/
        return super.save(SqlId.BY_MARKET_TERMINAL_FILE_INFO,FileInfo);
    }
}
