package com.msk.product.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.core.entity.PdStandard;
import com.msk.product.bean.PdTcProviderPackageParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * PD141155Logic
 * @author pxg
 */
@Service
public class PD141155Logic extends BaseLogic {

    /**
     * SQL Map 中SQL ID定义
     * @author pxg
     */
    interface SqlId {
        String SQL_ID_FIND_QUERY_ONE_DATA = "queryOneData";
        String SQL_ID_FIND_SAVE_DATA_MODE = "saveDataMode";
        String SQL_ID_FIND_FIND_BREEDNAME = "findBreedName";
        String SQL_ID_FIND_FIND_FEATURENAME = "findFeatureName";
        String SQL_ID_FIND_FIND_WEIGHTNAME = "findWeightName";
        String SQL_ID_FIND_FIND_STANDARDID="findStandardId";
        String SQL_ID_FIND_FIND_PDNORMS="findPdNorms";
    }

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }


    /**
     * 根据id查询卖家供应商备案注册包装管理表
     * @param tcProviderId tcProviderId
     * @return
     */
    @Transactional(readOnly = true)
    public PdTcProviderPackageParam queryOneData(String tcProviderId){
        BaseParam param=new BaseParam();
        param.setFilter("tcProviderId",tcProviderId);
        return super.findOne(SqlId.SQL_ID_FIND_QUERY_ONE_DATA, param);
    }

    /**
     * 根据id保存审核意见并且更新审核状态
     * @param pdTcProviderPackage pdTcProviderPackage
     * @return
     */
    @Transactional
    public int saveDataStatus(PdTcProviderPackageParam pdTcProviderPackage){
        BaseParam param=new BaseParam();
        param.setFilter("auditMemo",pdTcProviderPackage.getAuditMemo());
        param.setFilter("tcProviderId", StringUtil.toSafeString(pdTcProviderPackage.getTcProviderId()));
        param.setFilter("auditStatus",StringUtil.toSafeString(pdTcProviderPackage.getAuditStatus()));
        /**Add: 横展开添加共通设置 2016/09/21   BY  任强  Start */
        param.setUpdId(pdTcProviderPackage.getUpdId());
        param.setUpdTime(pdTcProviderPackage.getUpdTime());
        /**Add: 横展开添加共通设置 2016/09/21   BY  任强  End */
        int num= super.modify(SqlId.SQL_ID_FIND_SAVE_DATA_MODE, param);
        return num;
    }

    /**
     * 根据品种名称查询记录是否存在
     * @param breedName breedName
     * @return
     */
    @Transactional(readOnly = true)
    public int findBreedName(String classesCode,String machiningCode,String breedName){
        BaseParam param=new BaseParam();
        param.setFilter("classesCode",classesCode);
        param.setFilter("machiningCode",machiningCode);
        param.setFilter("breedName",breedName);
        int num= super.getCount(SqlId.SQL_ID_FIND_FIND_BREEDNAME, param);
        return num;
    }

    /**
     * 根据特征名称查询记录是否存在
     * @param featureName featureName
     * @return
     */
    @Transactional(readOnly = true)
    public int findFeatureName(String classesCode,String machiningCode,String breedCode,String featureName){
        BaseParam param=new BaseParam();
        param.setFilter("classesCode",classesCode);
        param.setFilter("machiningCode",machiningCode);
        param.setFilter("breedCode",breedCode);
        param.setFilter("featureName",featureName);
        int num= super.getCount(SqlId.SQL_ID_FIND_FIND_FEATURENAME, param);
        return num;
    }

    /**
     * 根据净重名称查询记录是否存在
     * @param featureCode featureCode
     * @return
     */
    @Transactional(readOnly = true)
    public int findWeightName(String classesCode,String machiningCode,String breedCode,String featureCode,String weightName){
        BaseParam param=new BaseParam();
        param.setFilter("classesCode",classesCode);
        param.setFilter("machiningCode",machiningCode);
        param.setFilter("breedCode",breedCode);
        param.setFilter("featureCode",featureCode);
        param.setFilter("weightName",weightName);
        int num= super.getCount(SqlId.SQL_ID_FIND_FIND_WEIGHTNAME, param);
        return num;
    }

    /**
     * 根据五级分类code查询包装标准ID
     * @param featureCode featureCode
     * @return
     */
    @Transactional(readOnly = true)
    public int findStandardId(String classesCode,String machiningCode,String breedCode,String featureCode,String weightCode,String normsOut){
        BaseParam param=new BaseParam();
        param.setFilter("classesCode",classesCode);
        param.setFilter("machiningCode",machiningCode);
        param.setFilter("breedCode",breedCode);
        param.setFilter("featureCode",featureCode);
        param.setFilter("weightCode",weightCode);
        PdStandard pdStandard= super.findOne(SqlId.SQL_ID_FIND_FIND_STANDARDID, param);
        int num=0;
        if(null!=pdStandard){
            num=this.findPdNorms(StringUtil.toSafeString(pdStandard.getStandardId()),normsOut);
        }
        return num;
    }
    /**
     * 根据包装标准ID和包装规格查询记录是否存在
     * @param standardId standardId
     * @return
     */
    @Transactional(readOnly = true)
    public int findPdNorms(String standardId,String normsOut){
        BaseParam param=new BaseParam();
        param.setFilter("standardId",standardId);
        param.setFilter("normsOut",normsOut);
        int num= super.getCount(SqlId.SQL_ID_FIND_FIND_PDNORMS, param);
        return num;
    }
}
