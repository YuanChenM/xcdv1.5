package com.msk.product.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.common.logic.CommonLogic;
import com.msk.product.bean.PD141128Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * PD141128Logic
 * @author pxg
 */
@Service
public class PD141128Logic extends BaseLogic {

    /**
     * SQL Map 中SQL ID定义
     * @author pxg
     */
    interface SqlId {
        String SQL_ID_FIND_SAVE_MARKET = "findSaveMarket";
        String SQL_ID_FIND_QUERY_DATA = "findQueryMarket";
    }

    @Autowired
    private CommonLogic commonLogic;

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }


    /**
     * 保存市场需求审核注册值
     * @return
     */
    @Transactional
    public int saveMarket(PD141128Param pdTcMarket){
        Long marketId=commonLogic.maxId("pd_tc_market", "TC_ONLINE_ID");
        String classesCode=null;
        String machiningCode=null;
        String breedCode=null;
        pdTcMarket.setTcOnlineId(marketId);
        if(null!=pdTcMarket){
            pdTcMarket.setClassesCode(pdTcMarket.getBreedCode().substring(0,2));
            pdTcMarket.setMachiningCode(pdTcMarket.getBreedCode().substring(2,3));
            pdTcMarket.setBreedCode(pdTcMarket.getBreedCode().substring(3,5));
        }
        pdTcMarket.setDelFlg("0");
        pdTcMarket.setCrtTime(new Date());
        pdTcMarket.setFeatureFlg(NumberConst.IntDef.INT_ZERO);
        int num=super.save(SqlId.SQL_ID_FIND_SAVE_MARKET, pdTcMarket);
        return num;
    }

    /**
     * 查询数据是否已存在
     * @param pd141128Param
     * @return
     */
    @Transactional(readOnly = true)
    public int queryData(PD141128Param pd141128Param){
        BaseParam param=new BaseParam();
        String classesCode=null;
        String machiningCode=null;
        String breedCode=null;
        if(null!=pd141128Param){
            classesCode=pd141128Param.getBreedCode().substring(0,2);
            machiningCode=pd141128Param.getBreedCode().substring(2,3);
            breedCode=pd141128Param.getBreedCode().substring(3,5);
        }
        param.setFilter("classesCode",classesCode);
        param.setFilter("machiningCode",machiningCode);
        param.setFilter("breedCode",breedCode);
        param.setFilter("featureName",pd141128Param.getFeatureName());
        param.setFilter("delFlg","0");
        return super.getCount(SqlId.SQL_ID_FIND_QUERY_DATA,param);
    }

}
