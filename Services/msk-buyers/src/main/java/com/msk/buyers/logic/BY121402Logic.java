package com.msk.buyers.logic;

import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.common.logic.CommonLogic;
import com.msk.core.entity.ByMarketTerminalBasic;
import com.msk.core.entity.ByMarketTerminalFileInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 *
 */
@Service
public class BY121402Logic extends BaseLogic {

    private  static Logger logger = LoggerFactory.getLogger(BY121402Logic.class);
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
     *根据marketId查询信息
     * @return
     */
    @Transactional(readOnly = true)
    public ByMarketTerminalBasic findByMarketTerminalBasic(String marketId){
        ByMarketTerminalBasic basic = new ByMarketTerminalBasic();
        basic.setMarketId(marketId);
        basic = super.findOne(SqlId.FIND_BY_MARKET_TERMINA_BASIC, basic);
        return basic;
    }

    /**
     * 插入数据
     * @param FileInfo
     * @return
     */
    public void saveFileInfo(ByMarketTerminalFileInfo FileInfo ,String emplNo){
        //假定值，先设一个
        /*String crtId = "ctrName";*/
        Long id = commonLogic.maxId("by_market_terminal_file_info","ID");
        Date currentDate = DateTimeUtil.getCustomerDate();
        FileInfo.setId(id);
        FileInfo.setCrtId(emplNo);
        FileInfo.setUpdId(emplNo);
        FileInfo.setActId(emplNo);
        FileInfo.setCrtTime(currentDate);
        FileInfo.setUpdTime(currentDate);
        FileInfo.setActTime(currentDate);
        super.save(FileInfo);
    }
}
