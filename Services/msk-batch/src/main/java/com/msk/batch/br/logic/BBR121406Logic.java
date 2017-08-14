package com.msk.batch.br.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.StringConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.batch.br.bean.BBR121406Bean;
import com.msk.br.bean.IBR121413RsBean;
import com.msk.common.base.BaseLogic;
import com.msk.common.logic.CommonLogic;
import com.msk.core.entity.BrBuyerPoolRelationship;
import com.msk.core.entity.BrOBuyerInfo;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * BBR121405Logic
 *
 * @author zhou_yajun
 */
@Service
public class BBR121406Logic extends BaseLogic {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(BBR121406Logic.class);

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    @Autowired
    private CommonLogic commonLogic;

    interface SqlId{
        static String FIND_O_BY_HK_RELATION_SHIP = "findOByHkRelationShip";
        static String MODIFY_BUYER_POOL_TYPE = "modifyBuyerPoolType";
        static String FIND_BY_HK_RELATION_SHIP = "findByHkRelationShip";
        static String MODIFY_BY_HK_RELATION_SHIP = "modifyByHkRelationShip";
        static String COUNT_BY_HK_RELATION_SHIP = "countByHkRelationShip";
        static String SAVE_BY_HK_RELATION_SHIP = "saveByHkRelationShip";
    }

    /**
     * 同步买家买家池关系
     * @param param
     */
    @Transactional
    public void synByPoolRelationShip(BaseParam param){
        List<BBR121406Bean> byOHkRelationShipList = this.findList(SqlId.FIND_O_BY_HK_RELATION_SHIP,param);
        if(!CollectionUtils.isEmpty(byOHkRelationShipList)){
            for (BBR121406Bean oByHkRelationShip: byOHkRelationShipList){
                oByHkRelationShip.setUpdId(param.getUpdId());
                oByHkRelationShip.setUpdTime(param.getUpdTime());
                this.modify(SqlId.MODIFY_BUYER_POOL_TYPE,oByHkRelationShip);
            }
        }
    }

    /**
     * 同步买家管家关系
     * @param param
     */
    @Transactional
    public void synByHkRelationShip(BaseParam param){
        List<BBR121406Bean> byHkRelationShipList = this.findList(SqlId.FIND_BY_HK_RELATION_SHIP,param);
        if(!CollectionUtils.isEmpty(byHkRelationShipList)){
            for (BBR121406Bean byHkRelationShip: byHkRelationShipList){
                byHkRelationShip.setCrtId(param.getCrtId());
                byHkRelationShip.setCrtTime(param.getCrtTime());
                byHkRelationShip.setUpdId(param.getUpdId());
                byHkRelationShip.setUpdTime(param.getUpdTime());
                byHkRelationShip.setActId(param.getActId());
                byHkRelationShip.setActTime(param.getActTime());
                //如果存在则更新买家与管家关系,不存在则插入买家与管家关系
                int exsitCount = this.getCount(SqlId.COUNT_BY_HK_RELATION_SHIP,byHkRelationShip);
                if(exsitCount > NumberConst.IntDef.INT_ZERO){
                    this.modify(SqlId.MODIFY_BY_HK_RELATION_SHIP,byHkRelationShip);
                }else{
                    Long byHkRsId = commonLogic.maxId("br_buyer_hk_relationship","ID");
                    byHkRelationShip.setByHkRsId(byHkRsId);
                    this.save(SqlId.SAVE_BY_HK_RELATION_SHIP,byHkRelationShip);
                }
            }
        }
    }
}
