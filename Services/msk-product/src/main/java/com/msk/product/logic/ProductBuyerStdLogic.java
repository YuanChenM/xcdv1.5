package com.msk.product.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.common.bean.RsRequest;
import com.msk.core.entity.*;
import com.msk.product.bean.PDInfoParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Created by yang_chunyan on 2016/6/27.
 */
@Service
public class ProductBuyerStdLogic extends BaseLogic{
    /** logger */
    private static Logger logger = LoggerFactory.getLogger(ProductBuyerStdLogic.class);

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    interface SqlId {


        static final String SQLID_GET_PD_MCT = "getPdMct";
        static final String SQLID_GET_PD_MCT_STD = "getPdMctStd";
        static final String SQLID_GET_PD_MODIFY_MCT = "getPdModifyMct";

        static final String SQLID_GET_PD_TNC = "getPdTnc";
        static final String SQLID_GET_PD_TNC_STD = "getPdTncStd";

        static final String SQLID_GET_PD_ORG = "getPdOrg";
        static final String SQLID_GET_PD_ORG_STD = "getPdOrgStd";

        static final String SQLID_GET_PD_FED = "getPdFed";
        static final String SQLID_GET_PD_FED_STD = "getPdFedStd";

        static final String SQLID_GET_PD_GNQ = "getPdGnq";
        static final String SQLID_GET_PD_GNQ_STD = "getPdGnqStd";

        static final String SQLID_GET_PD_SFT = "getPdSft";
        static final String SQLID_GET_PD_SFT_STD = "getPdSftStd";

        static final String SQLID_GET_PD_TSP= "getPdTsp";
        static final String SQLID_GET_PD_TSP_STD = "getPdTspStd";

        static final String SQLID_GET_PD_NOR = "getPdNor";
    }


    /**
     * 买家产品加工质量标准查询
     *
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public List<ByResearchStdTnc> findBuyerTncList(BaseParam param) {
        logger.debug("买家产品加工质量标准查询");
        // 查询产品的加工质量标准
        return super.findList(SqlId.SQLID_GET_PD_TNC, param);
    }

    /**
     * 获取产品加工质量标准查询
     *
     * @param param standardId
     * @return
     */
    @Transactional(readOnly = true)
    public List<ByResearchStdTnc> findModifyBuyerTncList(BaseParam param) {
        logger.debug("买家产品加工质量标准查询");
        // 查询产品的加工质量标准
        return super.findList(SqlId.SQLID_GET_PD_MODIFY_MCT, param);
    }

    /**
     * 买家产品加工质量标准查询
     *
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public ByResearchStdTnc findBuyerTnc(BaseParam param) {
        logger.debug("产品加工质量标准查询");
        // 查询产品加工质量标准id
        ByResearchStdTnc byResearchStdTnc = super.findOne(SqlId.SQLID_GET_PD_TNC_STD, param);
        return byResearchStdTnc;
    }


    /**
     * 买家产品加工技术标准查询
     *
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public List<ByResearchStdMct> getPdMct(BaseParam param) {
        logger.debug("买家产品加工质量标准查询");
        // 查询产品的加工技术标准
        return super.findList(SqlId.SQLID_GET_PD_MCT, param);
    }

    /**
     * 产品加工质量标准查询
     *
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public ByResearchStdMct findBuyerMct(BaseParam param) {
        logger.debug("产品加工质量标准查询");
        // 查询产品加工质量标准id
        ByResearchStdMct byResearchStdMct = super.findOne(SqlId.SQLID_GET_PD_MCT_STD, param);
        return byResearchStdMct;
    }


    /**
     * 买家产品原种种源标准查询
     *
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public List<ByResearchStdOrg> findBuyerOrgList(BaseParam param) {
        logger.debug("买家产品原种种源标准查询");
        return super.findList(SqlId.SQLID_GET_PD_ORG, param);
    }

    /**
     * 产品原种种源标准查询
     *
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public ByResearchStdOrg findBuyerOrg(BaseParam param) {
        logger.debug("产品原种种源标准查询");
        // 查询产品加工质量标准id
        ByResearchStdOrg byResearchStdOrg = super.findOne(SqlId.SQLID_GET_PD_ORG_STD, param);
        return byResearchStdOrg;
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
        // 查询产品饲养标准
        return super.findList(SqlId.SQLID_GET_PD_FED, param);
    }

    /**
     * 产品原种种源标准查询
     *
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public ByResearchStdFed findBuyerFed(BaseParam param) {
        logger.debug("产品饲养标准查询");
        // 查询产品加工质量标准id
        ByResearchStdFed byResearchStdFed = super.findOne(SqlId.SQLID_GET_PD_FED_STD, param);
        return byResearchStdFed;
    }

    /**
     * 买家产品品种通用质量标准查询
     *
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public List<ByResearchStdGnq> findBuyerGnqList(BaseParam param) {
        logger.debug("买家产品品种通用质量标准查询");
        // 查询产品品种通用质量标准
        return super.findList(SqlId.SQLID_GET_PD_GNQ, param);
    }

    /**
     * 产品原种种源标准查询
     *
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public ByResearchStdGnq findBuyerGnq(BaseParam param) {
        logger.debug("产品品种通用质量标准查询");
        // 查询产品加工质量标准id
        ByResearchStdGnq byResearchStdGnq = super.findOne(SqlId.SQLID_GET_PD_GNQ_STD, param);
        return byResearchStdGnq;
    }


    /**
     * 买家产品品种储存运输标准调研查询
     *
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public List<ByResearchStdTsp> findBuyerTspList(BaseParam param) {
        logger.debug("买家产品品种储存运输标准调研查询");
        // 查询产品品种储存运输标准
        return super.findList(SqlId.SQLID_GET_PD_TSP, param);
    }

    /**
     * 产品品种储存运输标准调研查询
     *
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public ByResearchStdTsp findBuyerTsp(BaseParam param) {
        logger.debug("产品品种通用质量标准查询");
        // 查询产品加工质量标准id
        ByResearchStdTsp byResearchStdTsp = super.findOne(SqlId.SQLID_GET_PD_TSP_STD, param);
        return byResearchStdTsp;
    }

    /**
     * 买家产品品种安全标准调研查询
     *
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public List<ByResearchStdSft> findBuyerSftList(BaseParam param) {
        logger.debug("买家产品品种安全标准调研查询");
        // 查询产品品种安全标准
        return super.findList(SqlId.SQLID_GET_PD_SFT, param);
    }

    /**
     * 产品品种安全标准调研查询
     *
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public ByResearchStdSft findBuyerSft(BaseParam param) {
        logger.debug("产品品种安全标准调研查询");
        // 查询产品加工质量标准id
        ByResearchStdSft byResearchStdSft = super.findOne(SqlId.SQLID_GET_PD_SFT_STD, param);
        return byResearchStdSft;
    }


    /**
     * 买家产品品种包装标准调研查询
     *
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public List<ByResearchStdNor> findBuyerNorList(BaseParam param) {
        logger.debug("买家产品品种包装标准调研查询");
        // 查询产品品种包装标准
        return super.findList(SqlId.SQLID_GET_PD_NOR, param);
    }


}
