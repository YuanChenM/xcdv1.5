package com.msk.product.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.hoperun.jdbc.redis.BaseRedisDao;
import com.msk.common.base.BaseLogic;
import com.msk.common.config.CodeMasterManager;
import com.msk.common.consts.RedisDataBaseDef;
import com.msk.product.bean.IPD141149Param;
import com.msk.product.bean.IPD141149Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * @version 创建时间：2016年7月27日 下午18:34:01
 *          查询产品运营状态
 * 
 */
@Service
public class IPD141149Logic extends BaseLogic {


    /**
     * SQL Map 中SQL ID定义
     * 
     * @author ren_qiang
     */
    interface SqlId {
        static final String SQL_ID_FIND_LIST_PD_PRO_YY_STATUS = "searchProYyStatus";
    }

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     *查询产品运营状态
     * @return IPD141149RsResult
     * @author renqiang
     */
    @Transactional(readOnly = true)
    public List<IPD141149Result> searchProYyStatus(IPD141149Param param) {
        if (param == null) {
            param = new IPD141149Param();
        }
        BaseParam baseParam = new BaseParam();
        if(StringUtils.hasLength(param.getLgcsCode())){
            baseParam.setFilter("lgcsCode",param.getLgcsCode());
        }
        if(StringUtils.hasLength(param.getLgcsName())){
            baseParam.setFilter("lgcsName",param.getLgcsName());
        }
        if(StringUtils.hasLength(param.getClassesCode())){
            baseParam.setFilter("classesCode",param.getClassesCode());
        }
        if(StringUtils.hasLength(param.getMachiningCode())){
            baseParam.setFilter("machiningCode",param.getMachiningCode());
        }
        if(StringUtils.hasLength(param.getBreedCode())){
            baseParam.setFilter("breedCode",param.getBreedCode());
        }
        if(StringUtils.hasLength(param.getFeatureCode())){
            baseParam.setFilter("featureCode",param.getFeatureCode());
        }
        if(StringUtils.hasLength(param.getWeightCode())){
            baseParam.setFilter("weightCode",param.getWeightCode());
        }
        if(StringUtils.hasLength(param.getGradeCode())){
            baseParam.setFilter("gradeCode",param.getGradeCode());
        }
        if(StringUtils.hasLength(param.getPdMarketCode())){
            baseParam.setFilter("pdMarketCode",param.getPdMarketCode());
        }
        List<IPD141149Result> findList = super.findList(SqlId.SQL_ID_FIND_LIST_PD_PRO_YY_STATUS, baseParam);

        Map<String,String> pdMarketNameMap = CodeMasterManager.findCodeMasterMap("ProductMarketType");

        for(IPD141149Result ipd141149Result:findList){
            String proCode = ipd141149Result.getClassesCode()+ipd141149Result.getMachiningCode()+ipd141149Result.getBreedCode()+ipd141149Result.getFeatureCode()
                    +ipd141149Result.getWeightCode()+ipd141149Result.getGradeCode();
            ipd141149Result.setProCode(proCode);
            String pdMarketName = pdMarketNameMap.get(ipd141149Result.getPdMarketCode());
            ipd141149Result.setPdMarketName(pdMarketName);
        }
        return findList;
    }
}
