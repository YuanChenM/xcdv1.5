package com.msk.br.logic;

import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.br.bean.IBR121415RsParam;
import com.msk.common.base.BaseLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by tao_zhifa on 2016/10/24.
 */
@Service
public class IBR121415Logic extends BaseLogic {

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    interface SqlId{
        String FIND_BR_BUYER_BOOLRELATIONSHIP = "findBrBuyerBoolrelationship";
        String SAVE_BR_BUYER_HKRELATIONSHIP = "saveBrBuyerHkRelationship";
        String UPDATE_BR_BUYER_POOL_RELATIONSHIP= "updateBrBuyerPoolRelationship";
        String FIND_BR_BUYER_HK_RELATIONSHIP= "findBrBuyerHkRelationship";
        String UPDATE_BR_BUYER_HK_RELATIONSHIP = "updateBrBuyerHkRelationship";
        String FIND_BR_O_BUYER_PD_CLA = "findBrOBuyerPdCla";
        String FIND_BR_O_BUYER_INFO = "findBrOBuyerInfo";
    }

    /**
     * 判断买家买家池关系表有没有数据
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public Integer findBrBuyerBoolrelationship(IBR121415RsParam param){
        return super.getCount(SqlId.FIND_BR_BUYER_BOOLRELATIONSHIP,param);
    }


    @Transactional(readOnly = true)
    public Integer findBrBuyerHkRelationship(IBR121415RsParam param){
        return super.getCount(SqlId.FIND_BR_BUYER_HK_RELATIONSHIP,param);
    }

    @Transactional(readOnly = true)
    public Integer findBrOBuyerInfo(IBR121415RsParam param){
        return super.getCount(SqlId.FIND_BR_O_BUYER_INFO,param);
    }

    @Transactional(readOnly = true)
    public Integer findBrOBuyerPdCla(IBR121415RsParam param){
        return super.getCount(SqlId.FIND_BR_O_BUYER_PD_CLA,param);
    }

    @Transactional
    public Integer saveBrBuyerHkRelationship(IBR121415RsParam param){
        return super.save(SqlId.SAVE_BR_BUYER_HKRELATIONSHIP,param);
    }

    @Transactional
    public Integer updateBrBuyerPoolRelationship(IBR121415RsParam param){
        return super.modify(SqlId.UPDATE_BR_BUYER_POOL_RELATIONSHIP,param);
    }

    @Transactional
    public Integer updateBrBuyerHkRelationship(IBR121415RsParam param){
        return super.modify(SqlId.UPDATE_BR_BUYER_HK_RELATIONSHIP,param);
    }


}
