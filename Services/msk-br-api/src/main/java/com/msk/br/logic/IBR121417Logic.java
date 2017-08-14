package com.msk.br.logic;

import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.core.entity.BrOBuyerInfo;
import com.msk.core.entity.BrOBuyerPdCla;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by yuan_zhifei on 2016/11/07.
 */
@Service
public class IBR121417Logic extends BaseLogic {

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    interface SqlId{
        String SAVE_BUYER_PD_CLA = "saveBuyerPdCla";
        String COUNT_O_BUYER_INFO = "countOBuyerInfo";
        String COUNT_O_BUYER_PD_CLA = "countOBuyerPdCla";
        String MODIFY_O_BUYER_INFO = "modifyOBuyerInfo";
        String MODIFY_O_BUYER_PD_CLA = "modifyOBuyerPdCla";
    }

    @Transactional
    public int saveBuyerPdCla(BrOBuyerPdCla oBuyerPdCla){
        return this.save(SqlId.SAVE_BUYER_PD_CLA,oBuyerPdCla);
    }

    @Transactional(readOnly = true)
    public int countOBuyerInfo(BrOBuyerInfo oBuyerInfo){
        return this.getCount(SqlId.COUNT_O_BUYER_INFO,oBuyerInfo);
    }

    @Transactional(readOnly = true)
    public int countOBuyerPdCla(BrOBuyerPdCla oBuyerPdCla){
        return this.getCount(SqlId.COUNT_O_BUYER_PD_CLA,oBuyerPdCla);
    }

    @Transactional
    public int modifyOBuyerInfo(BrOBuyerInfo oBuyerInfo){
        return this.modify(SqlId.MODIFY_O_BUYER_INFO,oBuyerInfo);
    }

    @Transactional
    public int modifyOBuyerPdCla(BrOBuyerPdCla oBuyerPdCla){
        return this.modify(SqlId.MODIFY_O_BUYER_PD_CLA,oBuyerPdCla);
    }
}
