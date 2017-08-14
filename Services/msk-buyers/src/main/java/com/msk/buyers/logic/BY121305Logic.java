package com.msk.buyers.logic;

import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.buyers.bean.BY121403Bean;
import com.msk.common.base.BaseLogic;
import com.msk.core.entity.ByMarketTerminal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * BY121305Logic.
 *
 * @author zhou_ling
 */
@Service
public class BY121305Logic extends BaseLogic {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(BY121305Logic.class);


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
    private BY121405Logic by121405Logic;

    /**
     * SQL Map 中SQL ID定义
     *
     * @author zhou_Ling
     */
    interface SqlId {
        //批发市场详细信息获取
        static String SQL_TERMARKET_DETAIL_INFO_FIND = "findTerMarketInfo";
        //批发市场详细信息更新
        static String SQL_TERMARKET_DETAIL_INFO_SAVE = "saveTerMarketInfo";
        //批发市场详细信息增加
        static String SQL_TERMARKET_DETAIL_INFO_ADD = "addTerMarketInfo";
        //查询批发市场的个数
        static String SQL_TERMARKET_DETAIL_INFO_COUNT = "findTerMarketInfoCount";

        static String SQL_get_TerMarket_Id = "getTerMarketId";




    }

    /**
     * 批发市场详细信息查询接口
     *
     * @param terMarketId terMarketId
     * @return
     */
    @Transactional(readOnly = true)
    public ByMarketTerminal findMarketTerminal(String terMarketId) {
        BasePageParam inParam = new BasePageParam();
        inParam.setFilter("terMarketId", terMarketId);
        ByMarketTerminal byMarketTerminal = super.findOne(SqlId.SQL_TERMARKET_DETAIL_INFO_FIND, inParam);
        return byMarketTerminal;
    }

    /**
     * 批发市场详细信息更新
     * @param byMarketTerminal
     * @return
     */
    @Transactional
    public int byMarketTerminalModify(ByMarketTerminal byMarketTerminal){

        // 更新市场详细信息

      /*  byMarketTerminal.setUpdId("admin");
        byMarketTerminal.setUpdTime(DateTimeUtil.getCustomerDate());*/
        int count = super.save(SqlId.SQL_TERMARKET_DETAIL_INFO_SAVE, byMarketTerminal);

        //同步买家基本表修改批发市场信息和买家编码
        BY121403Bean by121403Bean = new BY121403Bean();
        by121403Bean.setMarketId(byMarketTerminal.getTerMarketId());
        by121403Bean.setMarketCode(byMarketTerminal.getMarketCode());
        by121403Bean.setMarketLevelName(byMarketTerminal.getMarketLevelName());
        by121405Logic.modifyBuyerCode(by121403Bean);

        return count;
    }

    /**
     * 批发市场详细信息增加
     * @param byMarketTerminal
     * @return
     */
    @Transactional
    public int byMarketTerminalAdd(ByMarketTerminal byMarketTerminal){

        // 增加批发市场详细信息
       /* byMarketTerminal.setCrtId("admin");
        byMarketTerminal.setCrtTime(DateTimeUtil.getCustomerDate());*/
        int count = super.save(SqlId.SQL_TERMARKET_DETAIL_INFO_ADD, byMarketTerminal);
        return count;
    }

    /**
     * 查询对应物流区，地区的批发市场个数
     * @param byMarketTerminal
     * @return
     */
    public int byMarketTerminalCount(ByMarketTerminal byMarketTerminal){
        int count = super.getCount(SqlId.SQL_TERMARKET_DETAIL_INFO_COUNT, byMarketTerminal);
        return count;
    }
    /**
     * 新增批发市场时判断批发市场编码是否存在
     *
     * */
    @Transactional(readOnly = true)
    public List<ByMarketTerminal> getTerMarketId(BaseParam param){
        return  super.findList(SqlId.SQL_get_TerMarket_Id,param);
    }

}
