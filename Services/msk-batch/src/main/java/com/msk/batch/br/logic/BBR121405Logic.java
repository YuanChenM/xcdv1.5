package com.msk.batch.br.logic;

import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.StringConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.br.bean.IBR121413RsBean;
import com.msk.common.base.BaseLogic;
import com.msk.common.logic.CommonLogic;
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
public class BBR121405Logic extends BaseLogic {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(BBR121405Logic.class);

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    @Autowired
    private CommonLogic commonLogic;

    interface SqlId{
        static String COUNT_BUYER_POOL_RELATIONSHIP = "countBuyerPoolRelationShip";
        static String SAVE_BUYER_POOL_RELATIONSHIP = "saveBuyerPoolRelationShip";
        static String MODIFY_BUYER_POOL_RELATIONSHIP = "modifyBuyerPrship";
        static String FIND_BUYER_POOL_ID = "findBuyerPoolId";
    }
    @Transactional
    public void synBuyerPdClasses(List<IBR121413RsBean> buyerPdClassesList){
        for (IBR121413RsBean buyerPdClasses: buyerPdClassesList){
            int existCount = this.getCount(SqlId.COUNT_BUYER_POOL_RELATIONSHIP,buyerPdClasses);
            if(existCount == NumberConst.IntDef.INT_ZERO){
                setParam(buyerPdClasses);
            }else {
                modifyBuyerPoolCode(buyerPdClasses);
            }
        }
    }

    @Transactional(readOnly = true)
    private void setParam(IBR121413RsBean buyerPdClasses){
        Long maxId = commonLogic.maxId("br_buyer_pool_relationship","ID");
        IBR121413RsBean buyerPoolId = this.findOne(SqlId.FIND_BUYER_POOL_ID,buyerPdClasses);
        if(null != buyerPoolId){
            Date currentDate = DateTimeUtil.getCustomerDate();
            buyerPdClasses.setId(maxId);
            buyerPdClasses.setJoinTime(currentDate);
            buyerPdClasses.setBuyerPoolId(buyerPoolId.getBuyerPoolId());
            String buyerCode = buyerPdClasses.getBuyerCode();
            String classesCode = buyerPdClasses.getClassesCode();
            String byPoolMachiningCode = buyerPdClasses.getByPoolMachiningCode();
            String poolBuyerCode = buyerCode;
            if(buyerCode.length() >= 15){
                poolBuyerCode = buyerCode.substring(0,15) + StringConst.MIDDLE_LINE + classesCode + byPoolMachiningCode + buyerCode.substring(buyerCode.length() - 3);
            }
            buyerPdClasses.setPoolBuyerCode(poolBuyerCode);
            buyerPdClasses.setCrtId("admin");
            buyerPdClasses.setCrtTime(currentDate);
            buyerPdClasses.setActId("admin");
            buyerPdClasses.setActTime(currentDate);
            buyerPdClasses.setUpdId("admin");
            buyerPdClasses.setUpdTime(currentDate);
            this.save(SqlId.SAVE_BUYER_POOL_RELATIONSHIP,buyerPdClasses);
        }
    }



    @Transactional
    private void modifyBuyerPoolCode(IBR121413RsBean buyerPdClasses){
        IBR121413RsBean buyerPoolId= this.findOne(SqlId.FIND_BUYER_POOL_ID,buyerPdClasses);
        if(null != buyerPoolId){
            Date currentDate = DateTimeUtil.getCustomerDate();
            String buyerCode = buyerPdClasses.getBuyerCode();
            String classesCode = buyerPdClasses.getClassesCode();
            String byPoolMachiningCode = buyerPdClasses.getByPoolMachiningCode();
            String poolBuyerCode = buyerCode;
            if(buyerCode == null){
                buyerCode = "";
            }
            if(buyerCode.length() >= 15){
                poolBuyerCode = buyerCode.substring(0,15) + StringConst.MIDDLE_LINE + classesCode + byPoolMachiningCode + buyerCode.substring(buyerCode.length() - 3);
            }
            buyerPdClasses.setPoolBuyerCode(poolBuyerCode);
            buyerPdClasses.setUpdId("admin");
            buyerPdClasses.setUpdTime(currentDate);
            buyerPdClasses.setBuyerPoolId(buyerPoolId.getBuyerPoolId());
            this.modify(SqlId.MODIFY_BUYER_POOL_RELATIONSHIP, buyerPdClasses);

        }
    }

}
