package com.msk.buyers.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.buyers.utils.RestCommUtil;
import com.msk.common.base.BaseLogic;
import com.msk.common.logic.CommonLogic;
import com.msk.core.entity.ByResearchCategory;
import com.msk.product.bean.PDInfoParam;
import com.msk.product.bean.StdResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * IBY121210Logic.
 *
 * @author zhou_ling
 */
@Service
public class IBY121210Logic extends BaseLogic {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(IBY121210Logic.class);

    @Autowired
    private CommonLogic commonLogic;

    /**
     * SQL Map 中SQL ID定义
     *
     * @author zhou_ling
     */
    interface SqlId {
        // 判断产品品种是否已经被调研
        static  String SQL_JUDGE_RESEARCH = "judgeResearch";
        //插入产品品种
        static String SQL_INSERT_RESEARCH_CATEGORY = "insertResearchCategory";
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
     * 买家产品品种类型是否存在
     *
     * @param byResearchCategory
     * @return
     * @author zhou_ling
     */

    public boolean researchCategoryJudge(ByResearchCategory byResearchCategory){
        String classTreeCode = byResearchCategory.getClassesCode() + byResearchCategory.getMachiningCode();
        // 产品品种类型
        PDInfoParam pdInfoParam = new PDInfoParam();
        pdInfoParam.setClassesTreeCode(classTreeCode);
        pdInfoParam.setSalesName(byResearchCategory.getSaleName());
        pdInfoParam.setDelFlg("0");
        pdInfoParam.setLocalName(byResearchCategory.getPopularName());
        pdInfoParam.setScientificName(byResearchCategory.getScientificName());
        // 判断产品品种是否存在
        List<StdResult> pdClassesTreeMat = RestCommUtil.getPdInfoList(pdInfoParam).getResult().getProductList();
        boolean judeFlag = true;
        if(pdClassesTreeMat.isEmpty()){
            judeFlag = false;
        }
        return judeFlag;
    }

    /**
     * 买家产品品种类型是否被调研
     *
     * @param byResearchCategory
     * @return
     * @author zhou_ling
     */
    @Transactional(readOnly = true)
    public boolean researchJudge(ByResearchCategory byResearchCategory){
        BaseParam inParam = new BaseParam();
        inParam.setFilter("buyerId", byResearchCategory.getBuyerId());
        inParam.setFilter("classesCode", byResearchCategory.getClassesCode());
        inParam.setFilter("machiningCode", byResearchCategory.getMachiningCode());
        inParam.setFilter("saleName", byResearchCategory.getSaleName());
        inParam.setFilter("scientificName", byResearchCategory.getScientificName());
        inParam.setFilter("popularName",byResearchCategory.getPopularName());
        inParam.setFilter("delFlg","0");
        ByResearchCategory result = new ByResearchCategory();
        result = super.findOne(SqlId.SQL_JUDGE_RESEARCH,inParam);
        boolean judeFlag = true;
        if(null == result){
            judeFlag = false;
        }
        return judeFlag;
    }

    /**
     * 买家产品品类调研表插入
     * @param insertParam
     * @return
     * @author zhou_ling
     */
    @Transactional
    public void researchCategorySave(ByResearchCategory insertParam){
        Long id = commonLogic.maxId("by_research_category","CATEGORY_ID");
        insertParam.setCategoryId(id);
        insertParam.setCrtId(insertParam.getUpdId());
        insertParam.setCrtTime(DateTimeUtil.getCustomerDate());
        insertParam.setUpdTime(DateTimeUtil.getCustomerDate());
        insertParam.setActId(insertParam.getUpdId());
        insertParam.setActTime(DateTimeUtil.getCustomerDate());
        insertParam.setUpdId(insertParam.getUpdId());
        super.save(SqlId.SQL_INSERT_RESEARCH_CATEGORY,insertParam);
    }

}
