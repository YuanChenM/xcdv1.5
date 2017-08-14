package com.msk.product.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.core.entity.PdFeature;
import com.msk.core.entity.PdStandard;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * 产品特征Logic.
 *
 * @author gyh
 */
@Service
public class PD141104Logic extends BaseLogic {




    /**
     * SQL Map 中SQL ID定义
     * @author gyh
     */
    interface SqlId {
        String SQL_ID_MODIFY_FLG_BY_CODE = "modifyFlgByCode";
        String SQL_ID_FIND_NAME_BY_NAME = "findByName";
        String SQL_ID_FIND_MAX_CODE = "findMaxNo";
        String SQL_ID_SAVE_PD_STANDARD = "savePdStandard";
        String SQL_ID_FIND_STAND_ID = "findStandarId";//查询产品标准id
        String SQL_ID_DELETE_PD_TNC_STD_BY_STANDARID = "deleteTnc";//删除产品技术标准
        String SQL_ID_DELETE_PD_NORMS_STD_BY_STANDARID = "deleteNorms"; //删除产品包装表中数据
        String SQL_ID_DELETE_PD_QLT_STD_BY_STANDARID = "deleteQlt"; //删除产品质量标准数据
        String SQL_ID_DELETE_PD_STANDARD_BY_STANDARID = "deleteStd"; //删除产品标准数据
    }
    
    /**
     * 根据产品类别编码和分类编码和产品特征编号废除产品特征
     * @param pdFeature 参数
     * @return 修改行数
     */
    @Transactional
    public int modifyFlgByCode(PdFeature pdFeature){
        return super.modify(SqlId.SQL_ID_MODIFY_FLG_BY_CODE, pdFeature);
    }
    /**
     * 根据特征名称查询记录
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public PdFeature findByName(BaseParam param) {
        return super.findOne(SqlId.SQL_ID_FIND_NAME_BY_NAME, param);
    } 
    
    /**
     * 查询特征编码最大的记录
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public PdFeature findMaxCode(BaseParam param) {
        return super.findOne(SqlId.SQL_ID_FIND_MAX_CODE, param);
    }

    /**
     * 保存产品数据
     * @param pd
     */
    @Transactional(readOnly = false,
            propagation = Propagation.REQUIRED,
            rollbackFor = Exception.class)
    public void savePdStadard(PdStandard pd) {
        this.save(SqlId.SQL_ID_SAVE_PD_STANDARD,pd);
    }
    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }


    /**
     * 删除产品特征
     * @param classesCode
     * @param breedCode
     * @param featureCode
     * @return int
     */
    @Transactional(readOnly = false,
            propagation = Propagation.REQUIRED,
            rollbackFor = Exception.class)
    public int deleteFeature(String classesCode, String breedCode, String featureCode) {

        BaseParam param = new BaseParam();
        if(StringUtils.isNotEmpty(classesCode)&&StringUtils.isNotEmpty(featureCode)){
        param.setFilter("classesCode",classesCode);
        param.setFilter("breedCode",breedCode);
        param.setFilter("featureCode",featureCode);
        }else{
            throw new BusinessException("数据异常,请联系管理员");
        }
        //获取产品标准id
        PdStandard pdStandard = this.findOne(SqlId.SQL_ID_FIND_STAND_ID, param);

        //根据产品标准id删除对应数据 删除产品技术标准表 PD_TNC_STD
        this.remove(SqlId.SQL_ID_DELETE_PD_TNC_STD_BY_STANDARID,pdStandard);
        //根据产品标准id删除对应数据 删除产品包装表中数据 PD_NORMS_STD
        this.remove(SqlId.SQL_ID_DELETE_PD_NORMS_STD_BY_STANDARID,pdStandard);
        //根据产品标准id删除对应数据 删除产品质量标准数据 PD_QLT_STD
        this.remove(SqlId.SQL_ID_DELETE_PD_QLT_STD_BY_STANDARID,pdStandard);
        //删除产品标准数据
        this.remove(SqlId.SQL_ID_DELETE_PD_STANDARD_BY_STANDARID,pdStandard);
        //删除产品特征数据
        int e =  this.remove(param);
        return e;

    }

}


