package com.msk.product.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.common.logic.CommonLogic;
import com.msk.product.bean.PdTcProviderContentParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * PD14112801Logic
 * @author pxg
 */
@Service
public class PD14112801Logic extends BaseLogic {

    /**
     * SQL Map 中SQL ID定义
     * @author pxg
     */
    interface SqlId {
        String SQL_ID_FIND_SAVE_PROVIDER_CONTENT = "findSaveProviderContent";
        String SQL_ID_FIND_QUERY_PROVIDER_CONTENT = "findQueryProviderContent";
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
    public int saveProviderContent(PdTcProviderContentParam pdTcProviderContentParam){
        Long marketId=commonLogic.maxId("pd_tc_provider_content", "TC_CONTENT_ID");
        String classesCode=null;
        String machiningCode=null;
        String breedCode=null;
        if(null!=pdTcProviderContentParam){
            pdTcProviderContentParam.setClassesCode(pdTcProviderContentParam.getBreedCode().substring(0, 2));
            pdTcProviderContentParam.setMachiningCode(pdTcProviderContentParam.getBreedCode().substring(2, 3));
            pdTcProviderContentParam.setBreedCode(pdTcProviderContentParam.getBreedCode().substring(3, 5));
        }
        pdTcProviderContentParam.setTcContentId(marketId);
        pdTcProviderContentParam.setDelFlg("0");
        pdTcProviderContentParam.setCrtTime(new Date());
        pdTcProviderContentParam.setFeatureFlg(NumberConst.IntDef.INT_ZERO);
        int num=super.save(SqlId.SQL_ID_FIND_SAVE_PROVIDER_CONTENT, pdTcProviderContentParam);
        return num;
    }

    /**
     * 查询数据是否已存在
     * @param pdTcProviderContentParam pdTcProviderContentParam
     * @return
     */
    @Transactional(readOnly = true)
    public int queryProviderContent(PdTcProviderContentParam pdTcProviderContentParam){
        BaseParam param=new BaseParam();
        String classesCode=null;
        String machiningCode=null;
        String breedCode=null;
        if(null!=pdTcProviderContentParam){
            classesCode=pdTcProviderContentParam.getBreedCode().substring(0, 2);
            machiningCode=pdTcProviderContentParam.getBreedCode().substring(2, 3);
            breedCode=pdTcProviderContentParam.getBreedCode().substring(3, 5);
        }
        param.setFilter("classesCode",classesCode);
        param.setFilter("machiningCode",machiningCode);
        param.setFilter("breedCode",breedCode);
        param.setFilter("featureName",pdTcProviderContentParam.getFeatureName());
        param.setFilter("delFlg","0");
        return super.getCount(SqlId.SQL_ID_FIND_QUERY_PROVIDER_CONTENT,param);
    }
}
