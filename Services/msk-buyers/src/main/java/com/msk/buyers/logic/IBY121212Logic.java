package com.msk.buyers.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.buyers.utils.RestCommUtil;
import com.msk.common.base.BaseLogic;
import com.msk.common.bean.RsResponse;
import com.msk.core.entity.ByResearchStdOrg;
import com.msk.product.bean.PDInfoParam;
import com.msk.product.bean.ProductStdResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 买家产品原种种源标准调研用Logic.
 *
 * @author yuan_chen
 */
@Service
public class IBY121212Logic extends BaseLogic {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(IBY121212Logic.class);

    /**
     * SQL Map 中SQL ID定义
     *
     * @author yuan_chen
     */
    public interface SqlId {
        static final String SQLID_GET_PD_ORG = "getPdOrg";
        static final String SQLID_GET_PD_ORG_STD = "getPdOrgStd";
        static final String SQLID_COUNT_BY_RESEARCH_ORG = "countResearchOrg";
        static final String SQLID_GET_BY_RESEARCH_ORG = "getResearchOrg";
    }

    /**
     * (non-Javadoc)
     *
     * @see BaseLogic#setBaseDao(BaseDao)
     */
    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     * 产品原种种源标准查询
     * 
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public List<ByResearchStdOrg> findBuyerOrgList(BaseParam param) {
        logger.debug("产品原种种源标准查询");
        List<ByResearchStdOrg> list = new ArrayList<ByResearchStdOrg>();
        PDInfoParam pdParam = new PDInfoParam();
        pdParam.setClassesCode(StringUtil.toString(param.getFilterMap().get("classesCode")));
        pdParam.setMachiningCode(StringUtil.toString(param.getFilterMap().get("machiningCode")));
        pdParam.setBreedCode(StringUtil.toString(param.getFilterMap().get("breedCode")));
        pdParam.setTreeCode(StringUtil.toString(param.getFilterMap().get("treeCode")));
        pdParam.setType(2);
        RsResponse<ProductStdResult> response = RestCommUtil.findBuyerProductStd(pdParam);

        if(response.getStatus().equals(SystemConst.RsStatus.SUCCESS)){
            ProductStdResult bean = response.getResult();
            list = bean.getByResearchStdOrgs();
        }
       /* ByResearchStdOrg byResearchStdOrg = super.findOne(SqlId.SQLID_GET_PD_ORG_STD, param);
        long standardId = byResearchStdOrg.getStandardId();
        param.getFilterMap().put("standardId", standardId);
        int count = super.getCount(SqlId.SQLID_COUNT_BY_RESEARCH_ORG, param);
        if (count > NumberConst.IntDef.INT_ZERO){
            return super.findList(SqlId.SQLID_GET_BY_RESEARCH_ORG, param);
        }else {
            return super.findList(SqlId.SQLID_GET_PD_ORG, param);
        }*/
        return list;
    }

    /**
     * 产品原种种源标准更新
     *
     * @param byResearchStdOrg byResearchStdOrg
     * @return
     */
    @Transactional
    public int updateBuyerOrg(ByResearchStdOrg byResearchStdOrg) {
        logger.debug("产品原种种源标准更新");

       /* byResearchStdOrg.setUpdTime(DateTimeUtil.getCustomerDate());*/
        byResearchStdOrg.setDelFlg(SystemConst.DelFlg.ENABLE);
        byResearchStdOrg.setVer(NumberConst.IntDef.INT_ONE);
        BaseParam param = new BaseParam();
        param.getFilterMap().put("buyerId", byResearchStdOrg.getBuyerId());
        param.getFilterMap().put("standardId", byResearchStdOrg.getStandardId());
        int count = super.getCount(SqlId.SQLID_COUNT_BY_RESEARCH_ORG, param);
        if (count == NumberConst.IntDef.INT_ZERO){
            super.save(byResearchStdOrg);
        }
        return super.modify(byResearchStdOrg);
    }
}
