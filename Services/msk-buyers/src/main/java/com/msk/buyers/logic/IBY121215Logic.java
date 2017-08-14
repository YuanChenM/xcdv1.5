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
import com.msk.core.entity.ByResearchStdFed;
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
 * 买家产品饲养标准调研用Logic.
 *
 * @author zhou_ling
 */
@Service
public class IBY121215Logic extends BaseLogic {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(IBY121215Logic.class);

    /**
     * SQL Map 中SQL ID定义
     *
     * @author zhou_Ling
     */
    public interface SqlId {
        static final String SQLID_GET_PD_FED_STD = "getPdFedStd";
        static final String SQLID_COUNT_BY_RESEARCH_FED= "countResearchFed";
        static final String SQLID_GET_BY_RESEARCH_FED = "getResearchFed";
        static final String SQLID_GET_PD_FED = "getPdFed";
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
     * 买家产品饲养标准标准查询
     * 
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public List<ByResearchStdFed> findBuyerFedList(BaseParam param) {
        logger.debug("买家产品饲养标准查询");
        List<ByResearchStdFed> list = new ArrayList<ByResearchStdFed>();
        PDInfoParam pdParam = new PDInfoParam();
        pdParam.setClassesCode(StringUtil.toString(param.getFilterMap().get("classesCode")));
        pdParam.setMachiningCode(StringUtil.toString(param.getFilterMap().get("machiningCode")));
        pdParam.setBreedCode(StringUtil.toString(param.getFilterMap().get("breedCode")));
        pdParam.setTreeCode(StringUtil.toString(param.getFilterMap().get("treeCode")));
        pdParam.setType(3);
        RsResponse<ProductStdResult> response = RestCommUtil.findBuyerProductStd(pdParam);

        if(response.getStatus().equals(SystemConst.RsStatus.SUCCESS)){
            ProductStdResult bean = response.getResult();
            list = bean.getByResearchStdFeds();
        }
        return list;
       /* // 查询产品加工质量标准id
        ByResearchStdFed byResearchStdFed = super.findOne(SqlId.SQLID_GET_PD_FED_STD, param);
        long standardId = byResearchStdFed.getStandardId();
        param.getFilterMap().put("standardId", standardId);

        // 买家产品饲养标准是否有数据
        int count = super.getCount(SqlId.SQLID_COUNT_BY_RESEARCH_FED, param);
        if (count > NumberConst.IntDef.INT_ZERO){
            // 查询买家产品饲养标准
            return super.findList(SqlId.SQLID_GET_BY_RESEARCH_FED, param);
        }else {
            // 查询产品饲养标准
            return super.findList(SqlId.SQLID_GET_PD_FED, param);
        }*/
    }

    /**
     * 买家产品饲养标准更新
     *
     * @param byResearchStdFed byResearchStdFed
     * @return
     */
    @Transactional
    public int updateBuyerFed(ByResearchStdFed byResearchStdFed) {
        logger.debug("买家产品饲养标准更新");

       /* byResearchStdFed.setUpdTime(DateTimeUtil.getCustomerDate());*/
        byResearchStdFed.setDelFlg(SystemConst.DelFlg.ENABLE);
        byResearchStdFed.setVer(NumberConst.IntDef.INT_ONE);
        BaseParam param = new BaseParam();
        param.getFilterMap().put("buyerId", byResearchStdFed.getBuyerId());
        param.getFilterMap().put("standardId", byResearchStdFed.getStandardId());
        int count = super.getCount(SqlId.SQLID_COUNT_BY_RESEARCH_FED, param);
        if (count == NumberConst.IntDef.INT_ZERO){
            super.save(byResearchStdFed);
        }
        return super.modify(byResearchStdFed);
    }
}
