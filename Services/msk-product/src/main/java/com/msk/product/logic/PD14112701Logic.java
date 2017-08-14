package com.msk.product.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.common.logic.CommonLogic;
import com.msk.product.bean.PdTcOnlineOemParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * PD141128Logic
 * @author pxg
 */
@Service
public class PD14112701Logic extends BaseLogic {

    /**
     * SQL Map 中SQL ID定义
     * @author pxg
     */
    interface SqlId {
        String SQL_ID_FIND_SAVE_ONLINEOEM = "findSaveOnlineOem";
        String SQL_ID_FIND_QUERY_ONLINEOEM = "findQueryOnlineOem";
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
    public int saveOnlineOem(PdTcOnlineOemParam pdTcOnlineOemParam){
        Long tcOemId=commonLogic.maxId("pd_tc_online_oem", "TC_OEM_ID");
        String classesCode=null;
        String machiningCode=null;
        String breedCode=null;
        pdTcOnlineOemParam.setTcOemId(tcOemId);
        if(null!=pdTcOnlineOemParam){
            pdTcOnlineOemParam.setClassesCode(pdTcOnlineOemParam.getBreedCode().substring(0, 2));
            pdTcOnlineOemParam.setMachiningCode(pdTcOnlineOemParam.getBreedCode().substring(2, 3));
            pdTcOnlineOemParam.setBreedCode(pdTcOnlineOemParam.getBreedCode().substring(3, 5));
        }
        pdTcOnlineOemParam.setDelFlg("0");
        pdTcOnlineOemParam.setCrtTime(new Date());
        pdTcOnlineOemParam.setFeatureFlg(NumberConst.IntDef.INT_ZERO);
        int num=super.save(SqlId.SQL_ID_FIND_SAVE_ONLINEOEM, pdTcOnlineOemParam);
        return num;
    }

    /**
     * 查询数据是否已存在
     * @param pdTcOnlineOemParam pdTcOnlineOemParam
     * @return
     */
    @Transactional(readOnly = true)
    public int queryOnlineOem(PdTcOnlineOemParam pdTcOnlineOemParam){
        BaseParam param=new BaseParam();
        String classesCode=null;
        String machiningCode=null;
        String breedCode=null;
        if(null!=pdTcOnlineOemParam){
            classesCode=pdTcOnlineOemParam.getBreedCode().substring(0, 2);
            machiningCode=pdTcOnlineOemParam.getBreedCode().substring(2, 3);
            breedCode=pdTcOnlineOemParam.getBreedCode().substring(3, 5);
        }
        param.setFilter("classesCode",classesCode);
        param.setFilter("machiningCode",machiningCode);
        param.setFilter("breedCode",breedCode);
        param.setFilter("featureName",pdTcOnlineOemParam.getFeatureName());
        param.setFilter("delFlg","0");
        return super.getCount(SqlId.SQL_ID_FIND_QUERY_ONLINEOEM,param);
    }

}
