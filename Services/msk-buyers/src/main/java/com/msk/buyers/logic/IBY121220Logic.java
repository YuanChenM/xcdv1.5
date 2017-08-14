package com.msk.buyers.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.buyers.bean.IBY121220Bean;
import com.msk.buyers.utils.RestCommUtil;
import com.msk.common.base.BaseLogic;
import com.msk.common.consts.CodeMasterConst;
import com.msk.product.bean.PDInfoParam;
import com.msk.product.bean.PDInfoResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 买家产品品种调研状态用Logic.
 *
 * @author yuan_chen
 */
@Service
public class IBY121220Logic extends BaseLogic {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(IBY121220Logic.class);

    /**
     * SQL Map 中SQL ID定义
     *
     * @author yuan_chen
     */
    public interface SqlId {
        static final String SQLID_COUNT_RESEARCH_NEW = "countResearchNew";
        static final String SQLID_GET_RESEARCH_NEW = "getResearchNew";
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

    /*@Autowired
    private ProductLogic productLogic;*/
    @Autowired
    private IBY121221Logic iby121221Logic;

    /**
     * 买家产品品种调研状态查询
     *
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public List<IBY121220Bean> findBuyerResearch(BaseParam param) {
        logger.debug("买家产品品种调研状态查询");
        //设定flg为查询目录中产品
        param.getFilterMap().put("isStandard", CodeMasterConst.IsStandard.YES);
        List<IBY121220Bean> iby121220Beans = new ArrayList<>();
        PDInfoParam pdInfoParam = new PDInfoParam();
        pdInfoParam.setActId(param.getActId());
        List<PDInfoResult> pdBreeds = RestCommUtil.getPdClassesList(pdInfoParam).getResult().getResult();

        IBY121220Bean iby121220Bean;
        PDInfoResult pdBreed;
        int pdBreedsSize = pdBreeds.size();
       /* for (int i = NumberConst.IntDef.INT_ZERO; i < pdBreedsSize; i++) {
            pdBreed = pdBreeds.get(i);
            iby121220Bean = new IBY121220Bean();
            iby121220Bean.setClassesCode(pdBreed.getClassesCode());
            iby121220Bean.setMachiningCode(pdBreed.getMachiningCode());
            iby121220Bean.setBreedCode(pdBreed.getBreedCode());
            iby121220Bean.setBreedName(pdBreed.getBreedName());
            iby121220Bean.setIsStandard(CodeMasterConst.IsStandard.YES);
            param.getFilterMap().put("breedCode", pdBreed.getBreedCode());
            List<IBY121221Bean> iby121221Beans = iby121221Logic.findBuyerResearch(param);
            int countNotResearch = NumberConst.IntDef.INT_ZERO;
            int countResearching = NumberConst.IntDef.INT_ZERO;
            for (IBY121221Bean iby121221Bean:iby121221Beans) {
                if(BusinessConst.ResearchStatus.NotResearch.equals(iby121221Bean.getResearchStatus())){
                    countNotResearch++;
                }
                if(BusinessConst.ResearchStatus.Researching.equals(iby121221Bean.getResearchStatus())){
                    countResearching++;
                }
            }
            if(countResearching > NumberConst.IntDef.INT_ZERO){
                iby121220Bean.setResearchStatus(BusinessConst.ResearchStatus.Researching);
            }else if (countNotResearch == NumberConst.IntDef.INT_ZERO){
                iby121220Bean.setResearchStatus(BusinessConst.ResearchStatus.AlreadyResearch);
            }else{
                iby121220Bean.setResearchStatus(BusinessConst.ResearchStatus.NotResearch);
            }
            iby121220Beans.add(iby121220Bean);
        }*/
        return iby121220Beans;
    }

    /**
     * 买家产品新品种调研状态查询
     *
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public List<IBY121220Bean> findBuyerResearchNew(BaseParam param) {
        logger.debug("买家产品新品种调研状态查询");
        //设定flg为查询非目录中产品(新产品)
        param.getFilterMap().put("isStandard", "0");
        List<IBY121220Bean> iby121220Beans = new ArrayList<>();
        int countNew = super.getCount(SqlId.SQLID_COUNT_RESEARCH_NEW, param);
        if (countNew > NumberConst.IntDef.INT_ZERO){
            iby121220Beans = super.findList(SqlId.SQLID_GET_RESEARCH_NEW, param);
        }
        return iby121220Beans;
    }

    /**
     * setParam
     * @param param
     * @return
     */
    public BaseParam setParam(IBY121220Bean param){
        BaseParam baseParam = new BaseParam();
        baseParam.getFilterMap().put("buyerId", param.getBuyerId());
        baseParam.getFilterMap().put("classesCode", param.getClassesCode());
        baseParam.getFilterMap().put("machiningCode", param.getMachiningCode());
        return baseParam;
    }
}
