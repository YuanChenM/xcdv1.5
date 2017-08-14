package com.msk.buyers.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.hoperun.jdbc.redis.BaseRedisDao;
import com.msk.buyers.bean.IBY121221Bean;
import com.msk.common.base.BaseLogic;
import com.msk.common.config.CodeMasterManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 买家产品品种调研状态用Logic.
 *
 * @author yuan_chen
 */
@Service
public class IBY121221Logic extends BaseLogic {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(IBY121221Logic.class);

    /**
     * SQL Map 中SQL ID定义
     *
     * @author yuan_chen
     */
    public interface SqlId {
        static final String SQLID_COUNT_PD_STD = "countPdStd";
        static final String SQLID_GET_RESEARCH_STATUS = "getResearchStatus";
        static final String SQLID_COUNT_RESEARCH_CAT = "countResearchCat";
        static final String SQLID_COUNT_RESEARCH_CAT_DETAIL = "countResearchCatDetail";
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
     * 买家产品品种调研状态查询
     *
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public List<IBY121221Bean> findBuyerResearch(BaseParam param) {
        logger.debug("买家产品品种调研状态查询");
        List<IBY121221Bean> iby121221Beans = new ArrayList<>();
        Map<String, String> byTableNamesMap = createByTableNamesMap();
        Map<String, String> pdTableNamesMap = createPdTableNamesMap();
        Map<String,String> codeMasterMap = CodeMasterManager.findCodeMasterMap("ResearchType");
      /*  List<CommConstant> researchTypeList = commonLogic.findConstantList(BusinessConst.ResearchType.Type);
        String isStandard = String.valueOf(param.getFilterMap().get("isStandard"));

        IBY121221Bean iby121221Bean;
        for (CommConstant researchType : researchTypeList) {
            if (BusinessConst.ResearchType.CAT.equals(researchType.getConstantValue())) {
                iby121221Bean = new IBY121221Bean();
                int catCount = super.getCount(SqlId.SQLID_COUNT_RESEARCH_CAT, param);
                int catDetailCount = super.getCount(SqlId.SQLID_COUNT_RESEARCH_CAT_DETAIL, param);
                if (catCount > NumberConst.IntDef.INT_ZERO) {
                    if (catDetailCount > NumberConst.IntDef.INT_ZERO) {
                        iby121221Bean.setResearchStatus(BusinessConst.ResearchStatus.AlreadyResearch);
                    } else {
                        iby121221Bean.setResearchStatus(BusinessConst.ResearchStatus.Researching);
                    }
                } else {
                    iby121221Bean.setResearchStatus(BusinessConst.ResearchStatus.NotResearch);
                }
            } else {
                if (!StringUtil.equals(isStandard, CodeMasterConst.IsStandard.YES)) {
                    continue;
                }
                param.getFilterMap().put("pdTableName", pdTableNamesMap.get(researchType.getConstantValue()));
                int pdCount = super.getCount(SqlId.SQLID_COUNT_PD_STD, param);
                if (pdCount == NumberConst.IntDef.INT_ZERO) {
                    iby121221Bean = new IBY121221Bean();
                    iby121221Bean.setResearchStatus(BusinessConst.ResearchStatus.UnResearch);
                } else {
                    param.getFilterMap().put("byTableName", byTableNamesMap.get(researchType.getConstantValue()));
                    iby121221Bean = super.findOne(SqlId.SQLID_GET_RESEARCH_STATUS, param);
                }
            }

            iby121221Bean.setResearchType(researchType.getConstantValue());
            iby121221Bean.setResearchTypeName(researchType.getConstantName());

            iby121221Beans.add(iby121221Bean);
        }*/

        return iby121221Beans;
    }

    /**
     * 创建调研标准卡表名Map
     * 
     * @return
     */
    private Map<String, String> createByTableNamesMap() {
        Map<String, String> tableNamesMap = new HashMap<>();
        tableNamesMap.put("2", "by_research_std_org");
        tableNamesMap.put("3", "by_research_std_mct");
        tableNamesMap.put("4", "by_research_std_tnc");
        tableNamesMap.put("5", "by_research_std_fed");
        tableNamesMap.put("6", "by_research_std_gnq");
        tableNamesMap.put("7", "by_research_std_sft");
        tableNamesMap.put("8", "by_research_std_tsp");
        tableNamesMap.put("9", "by_research_std_nor");
        return tableNamesMap;
    }

    /**
     * 创建产品标准卡表名Map
     * 
     * @return
     */
    private Map<String, String> createPdTableNamesMap() {
        Map<String, String> tableNamesMap = new HashMap<>();
        tableNamesMap.put("2", "pd_org_std");
        tableNamesMap.put("3", "pd_mct_std");
        tableNamesMap.put("4", "pd_tnc_std");
        tableNamesMap.put("5", "pd_fed_std");
        tableNamesMap.put("6", "pd_gnq_std");
        tableNamesMap.put("7", "pd_sft_std");
        tableNamesMap.put("8", "pd_tsp_std");
        tableNamesMap.put("9", "pd_norms_std");
        return tableNamesMap;
    }
}
