package com.msk.product.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.common.logic.CommonLogic;
import com.msk.product.bean.PdTcOnlineParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * PD141127Logic
 * @author pxg
 */
@Service
public class PD141127Logic extends BaseLogic {

    /**
     * SQL Map 中SQL ID定义
     * @author pxg
     */
    interface SqlId {
        String SQL_ID_FIND_ON_LINE = "findOnLine";
        String SQL_ID_FIND_SAVE_ON_LINE = "findSaveOnLine";
    }

    @Autowired
    private CommonLogic commonLogic;
    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }


    /**
     * 查询正式上线记录是否存在
     * @param pdTcOnlineParam pdTcOnlineParam
     * @return
     */
    @Transactional(readOnly = true)
    public int findOnline(PdTcOnlineParam pdTcOnlineParam){
        BaseParam param=new BaseParam();
        String classesCode=null;
        String machiningCode=null;
        String breedCode=null;
        if(null!=pdTcOnlineParam){
            classesCode=pdTcOnlineParam.getBreedCode().substring(0, 2);
            machiningCode=pdTcOnlineParam.getBreedCode().substring(2, 3);
            breedCode=pdTcOnlineParam.getBreedCode().substring(3,5);
        }
        param.setFilter("classesCode",classesCode);
        param.setFilter("machiningCode",machiningCode);
        param.setFilter("breedCode",breedCode);
        param.setFilter("featureName",pdTcOnlineParam.getFeatureName());
        param.setFilter("delFlg","0");
        return super.getCount(SqlId.SQL_ID_FIND_ON_LINE,param);
    }

    /**
     * 保存正式上线数据
     * @param pdTcOnlineParam pdTcOnlineParam
     * @return
     */
    @Transactional
    public int saveOnLine(PdTcOnlineParam pdTcOnlineParam){
        Long onLineId=commonLogic.maxId("pd_tc_online", "TC_ONLINE_ID");
        String classesCode=null;
        String machiningCode=null;
        String breedCode=null;
        if(null!=pdTcOnlineParam){
            pdTcOnlineParam.setClassesCode(pdTcOnlineParam.getBreedCode().substring(0, 2));
            pdTcOnlineParam.setMachiningCode(pdTcOnlineParam.getBreedCode().substring(2, 3));
            pdTcOnlineParam.setBreedCode(pdTcOnlineParam.getBreedCode().substring(3,5));
        }
        pdTcOnlineParam.setTcOnlineId(onLineId);
        pdTcOnlineParam.setDelFlg("0");
        pdTcOnlineParam.setCrtTime(new Date());
        pdTcOnlineParam.setFeatureFlg(NumberConst.IntDef.INT_ZERO);
        return super.save(SqlId.SQL_ID_FIND_SAVE_ON_LINE,pdTcOnlineParam);
    }
}
