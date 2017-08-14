package com.msk.product.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.common.logic.CommonLogic;
import com.msk.product.bean.PdTcForbidParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * PD141128Logic
 * @author pxg
 */
@Service
public class PD141129Logic extends BaseLogic {

    /**
     * SQL Map 中SQL ID定义
     * @author pxg
     */
    interface SqlId {
        String SQL_ID_FIND_SAVE_TCFORBID = "findSaveTcForBid";
        String SQL_ID_FIND_QUERY_TCFORBID = "findQueryTcForBid";
    }

    @Autowired
    private CommonLogic commonLogic;

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }


    /**
     * 保存禁止准入产品
     * @return
     */
    @Transactional
    public int saveTcForbid(PdTcForbidParam pdTcForbidParam){
        Long tcOemId=commonLogic.maxId("pd_tc_forbid", "TC_FORBID_ID");
        String classesCode=null;
        String machiningCode=null;
        String breedCode=null;
        pdTcForbidParam.setTcForbidId(tcOemId);
        if(null!=pdTcForbidParam){
            pdTcForbidParam.setClassesCode(pdTcForbidParam.getBreedCode().substring(0, 2));
            pdTcForbidParam.setMachiningCode(pdTcForbidParam.getBreedCode().substring(2, 3));
            pdTcForbidParam.setBreedCode(pdTcForbidParam.getBreedCode().substring(3, 5));
        }
        pdTcForbidParam.setDelFlg("0");
        pdTcForbidParam.setCrtTime(new Date());
        pdTcForbidParam.setFeatureFlg(NumberConst.IntDef.INT_ZERO);
        int num=super.save(SqlId.SQL_ID_FIND_SAVE_TCFORBID, pdTcForbidParam);
        return num;
    }

    /**
     * 查询数据是否已存在
     * @param pdTcForbidParam pdTcForbidParam
     * @return
     */
    @Transactional(readOnly = true)
    public int queryTcForbid(PdTcForbidParam pdTcForbidParam){
        BaseParam param=new BaseParam();
        String classesCode=null;
        String machiningCode=null;
        String breedCode=null;
        if(null!=pdTcForbidParam){
            classesCode=pdTcForbidParam.getBreedCode().substring(0, 2);
            machiningCode=pdTcForbidParam.getBreedCode().substring(2, 3);
            breedCode=pdTcForbidParam.getBreedCode().substring(3, 5);
        }
        param.setFilter("classesCode",classesCode);
        param.setFilter("machiningCode",machiningCode);
        param.setFilter("breedCode",breedCode);
        param.setFilter("featureName",pdTcForbidParam.getFeatureName());
        param.setFilter("delFlg","0");
        return super.getCount(SqlId.SQL_ID_FIND_QUERY_TCFORBID,param);
    }

}
