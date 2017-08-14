package com.msk.product.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.core.entity.PdBreed;
import com.msk.core.entity.PdClasses;
import com.msk.core.entity.PdFeature;
import com.msk.core.entity.PdStandard;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 
 * 产品类别维护和产品品种信息展示Logic.
 *
 * @author gyh
 */
@Service
public class PD141102Logic extends BaseLogic {


    /**
     * SQL Map 中SQL ID定义
     * 
     * @author gyh
     */
    interface SqlId {
        String SQL_ID_MODIFY_FLG_BY_CODE = "modifyFlgByCode";
        String SQL_ID_FIND_NAME_BY_NAME = "findByName";
        String SQL_ID_FIND_MAX_CODE = "findMaxNo";
        String SQL_ID_FIND_lIST_PD_FEATURE = "findListFeature";
        String SQL_ID_FIND_STAND_ID = "findStandarId";//查询产品标准id
        String SQL_ID_DELETE_PD_TNC_STD_BY_STANDARID = "deleteTnc";//删除产品技术标准
        String SQL_ID_DELETE_PD_NORMS_STD_BY_STANDARID = "deleteNorms"; //删除产品包装表中数据
        String SQL_ID_DELETE_PD_QLT_STD_BY_STANDARID = "deleteQlt"; //删除产品质量标准数据
        String SQL_ID_DELETE_PD_STANDARD_BY_STANDARID = "deleteStd"; //删除产品标准数据
        String SQL_ID_DELETE_PD_FEATURE = "deleteFea";//删除产品特征数据
        String SQL_ID_DELETE_PD_Breed_BY_ID = "deleteBreed"; //删除产品品种数据

    }

    /**
     * 根据产品类别编码和产品品种编码废除产品品种
     * 
     * @param pdBreed 参数
     * @return 修改行数
     */
    @Transactional
    public int modifyFlgByCode(PdBreed pdBreed) {
        return super.modify(SqlId.SQL_ID_MODIFY_FLG_BY_CODE, pdBreed);
    }

    /**
     * 根据类别名称查询记录
     * 
     * @param param param
     * @return 类别
     */
    @Transactional(readOnly = true)
    public PdClasses findByName(BaseParam param) {
        return super.findOne(SqlId.SQL_ID_FIND_NAME_BY_NAME, param);
    }

    /**
     * 查询类别编码最大的记录
     * 
     * @param param param
     * @return 类别
     */
    @Transactional(readOnly = true)
    public PdClasses findMaxCode(BaseParam param) {
        return super.findOne(SqlId.SQL_ID_FIND_MAX_CODE, param);
    }

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     * 删除产品品种数据(xhy)
     * @param pdBreed1
     * @return int
     */
    @Transactional(readOnly = false,
            propagation = Propagation.REQUIRED,
            rollbackFor = Exception.class)
    public int deleteBreed(PdBreed pdBreed1) {

        String breedCode = pdBreed1.getBreedCode();
        String classesCode = pdBreed1.getClassesCode();
        BaseParam param = new BaseParam();
        if(StringUtils.isNotEmpty(breedCode)&&StringUtils.isNotEmpty(classesCode)){
            param.setFilter("classesCode",classesCode);
            param.setFilter("breedCode",breedCode);
        }else {
            throw new BusinessException("数据异常,请联系管理员");
        }
        //获取当前产品数据的所有特征数据
        List<PdFeature> features = this.findList(SqlId.SQL_ID_FIND_lIST_PD_FEATURE,param);
        //循环删除数据
        for (PdFeature feature : features){
            BaseParam param1 = new BaseParam();
            param1.setFilter("classesCode",feature.getClassesCode());
            param1.setFilter("breedCode",feature.getBreedCode());
            param1.setFilter("featureCode",feature.getFeatureCode());

            //获取产品标准id
           PdStandard pdStandard = this.findOne(SqlId.SQL_ID_FIND_STAND_ID, param1);
            //根据产品标准id删除对应数据 删除产品技术标准表 PD_TNC_STD
           this.remove(SqlId.SQL_ID_DELETE_PD_TNC_STD_BY_STANDARID,pdStandard);
            //根据产品标准id删除对应数据 删除产品包装表中数据 PD_NORMS_STD
           this.remove(SqlId.SQL_ID_DELETE_PD_NORMS_STD_BY_STANDARID,pdStandard);
            //根据产品标准id删除对应数据 删除产品质量标准数据 PD_QLT_STD
           this.remove(SqlId.SQL_ID_DELETE_PD_QLT_STD_BY_STANDARID,pdStandard);
            //删除产品标准数据
           this.remove(SqlId.SQL_ID_DELETE_PD_STANDARD_BY_STANDARID,pdStandard);
            //删除产品特征数据
           this.remove(SqlId.SQL_ID_DELETE_PD_FEATURE,param1);
        }
           int breedNuber =  this.remove(SqlId.SQL_ID_DELETE_PD_Breed_BY_ID,param);
           return breedNuber;
    }
}
