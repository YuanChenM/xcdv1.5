package com.msk.product.logic;

import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.hoperun.core.bean.BaseParam;
import com.msk.common.bean.RsRequest;
import com.msk.core.entity.*;
import com.msk.product.bean.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xhy
 * @version 创建时间：2016年3月10日 下午15:26:20
 *          产品技术标准接口  返回技术列表
 */
@Service
public class ProductStdLogic extends BaseLogic {

    /** logger */
    private static Logger logger = LoggerFactory.getLogger(ProductStdLogic.class);

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    interface SqlId {
        static final String SQL_ID_FIND_MAT_STD = "findListMatStd";
        static final String SQL_ID_GET_MCT_STD_INFO = "getMctStdInfo";
        static final String SQL_ID_GET_TNC_STD_INFO = "getTncStdInfo";
        static final String SQL_ID_GET_ORG_STD_INFO = "getOrgStdInfo";
        static final String SQL_ID_GET_FED_STD_INFO = "getFedStdInfo";
        static final String SQL_ID_GET_GNQ_STD_INFO = "getGnqStdInfo";
        static final String SQL_ID_GET_TSP_STD_INFO = "getTspStdInfo";
        static final String SQL_ID_GET_SFT_STD_INFO = "getSftStdInfo";
        static final String SQL_ID_FIND_PD_STANDARD = "findPdStandard";
    }

    /**
     * 取得产品加工质量标准数据
     *
     * @param rsparam param
     * @return 产品加工质量标准
     */
    @Transactional
    public List<TncStdBean> getTncStdInfo(PDInfoParam rsparam) {
        logger.debug("查询产品加工质量标准数据");
        List<TncStdBean> tncStdBeans = null;
        if(StringUtils.hasLength(rsparam.getClassesCode())
                && StringUtils.hasLength(rsparam.getMachiningCode())
                && StringUtils.hasLength(rsparam.getBreedCode())){
            List<PdStandard> pdStandards = this.findList(SqlId.SQL_ID_FIND_PD_STANDARD,rsparam);
            if (CollectionUtils.isEmpty(pdStandards) || pdStandards.size() < NumberConst.IntDef.INT_ONE) {
                return null;
            }
            PdStandard pdStandard = pdStandards.get(NumberConst.IntDef.INT_ZERO);
            rsparam.setStandardId(StringUtil.toSafeString(pdStandard.getStandardId()));
            rsparam.setLevelId("1");
            tncStdBeans = super.findList(SqlId.SQL_ID_GET_TNC_STD_INFO, rsparam);
        }else{
            if(!StringUtils.hasLength(rsparam.getLevelId()))
                rsparam.setLevelId("1");
            tncStdBeans = super.findList(SqlId.SQL_ID_GET_TNC_STD_INFO, rsparam);
        }
        return tncStdBeans;
    }

    /**
     * 取得加工技术标准详细页面数据
     *
     * @param rsparam rsparam
     * @return 加工技术标准数据
     */
    @Transactional
    public List<MctStdBean> getMctStd(PDInfoParam rsparam) {
        logger.debug("查询产品加工技术标准数据");
        List<MctStdBean> mctStdBeans = null;
        if(StringUtils.hasLength(rsparam.getClassesCode())
                && StringUtils.hasLength(rsparam.getMachiningCode())
                && StringUtils.hasLength(rsparam.getBreedCode())){
            List<PdStandard> pdStandards  = this.findList(SqlId.SQL_ID_FIND_PD_STANDARD,rsparam);
            if (CollectionUtils.isEmpty(pdStandards) || pdStandards.size() < NumberConst.IntDef.INT_ONE) {
                return null;
            }
            PdStandard pdStandard = pdStandards.get(NumberConst.IntDef.INT_ZERO);
            rsparam.setStandardId(StringUtil.toSafeString(pdStandard.getStandardId()));
            if(!StringUtils.hasLength(rsparam.getLevelId()))
                rsparam.setLevelId("1");
            mctStdBeans = super.findList(SqlId.SQL_ID_GET_MCT_STD_INFO, rsparam);
        }else{
            if(!StringUtils.hasLength(rsparam.getLevelId()))
                rsparam.setLevelId("1");
            mctStdBeans = super.findList(SqlId.SQL_ID_GET_MCT_STD_INFO, rsparam);
        }
        return mctStdBeans;
    }

    /**
     * 取得卖家产品原种种源标准数据
     *
     * @param rsparam rsparam
     * @return 卖家产品原种种源标准
     */
    @Transactional
    public List<OrgStdBean> getOrgStdInfo(PDInfoParam rsparam) {
        logger.debug("查询产品原种种源标准数据");
        rsparam.setLevelId("1");
        List<OrgStdBean> orgStdBeans = super.findList(SqlId.SQL_ID_GET_ORG_STD_INFO, rsparam);
        return orgStdBeans;
    }

    /**
     * 取得卖家产品饲养标准数据
     *
     * @param rsparam rsparam
     * @return 卖家产品饲养标准
     */
    @Transactional
    public List<FedStdBean> getFedStdInfo(PDInfoParam rsparam) {
        logger.debug("查询产品饲养标准数据");
        rsparam.setLevelId("1");
        List<FedStdBean> fedStdBeans = super.findList(SqlId.SQL_ID_GET_FED_STD_INFO, rsparam);
        return fedStdBeans;
    }

    /**
     * 取得卖家产品通用质量标准数据
     *
     * @param rsparam rsparam
     * @return 卖家产品通用质量标准
     */
    @Transactional
    public List<GnqStdBean> getGnqStdInfo(PDInfoParam rsparam) {
        logger.debug("查询产品通用质量标准数据");
        rsparam.setLevelId("1");
        List<GnqStdBean> gnqStdBeans = super.findList(SqlId.SQL_ID_GET_GNQ_STD_INFO, rsparam);
        //改善 #2328 卖家添加产品，加工技术标准”、“加工质量标准”、“其他标准 ” 标准找不到的时候的提示 统一修改为 “对不起，没有查询到记录！” 2016/10/09 by 杨春艳 start
        List<GnqStdBean> gnqStdList = new ArrayList<>();
        for (GnqStdBean bean : gnqStdBeans) {
            if ("0".equals(bean.getIsCatalog())) {
                rsparam.setParentId(bean.getGnqStdItemId());
                rsparam.setLevelId("2");
                List<GnqStdBean> beanList = super.findList(SqlId.SQL_ID_GET_GNQ_STD_INFO, rsparam);
                if(!CollectionUtils.isEmpty(beanList)){
                    bean.setPdGnqStds(beanList);
                    gnqStdList.add(bean);
                }
            }
        }
        return gnqStdList;
        //改善 #2328 卖家添加产品，加工技术标准”、“加工质量标准”、“其他标准 ” 标准找不到的时候的提示 统一修改为 “对不起，没有查询到记录！” 2016/10/09 by 杨春艳 end
    }

    /**
     * 取得卖家产品储存运输标准数据
     *
     * @param rsparam rsparam
     * @return 卖家产品储存运输标准
     */
    @Transactional
    public List<TspStdBean> getTspStdInfo(PDInfoParam rsparam) {
        logger.debug("查询产品储存运输标准数据");
        rsparam.setLevelId("1");
        List<TspStdBean> tspStdBeans = super.findList(SqlId.SQL_ID_GET_TSP_STD_INFO, rsparam);
        //改善 #2328 卖家添加产品，加工技术标准”、“加工质量标准”、“其他标准 ” 标准找不到的时候的提示 统一修改为 “对不起，没有查询到记录！” 2016/10/09 by 杨春艳 start
        List<TspStdBean> tspStdList = new ArrayList<>();
        for (TspStdBean bean : tspStdBeans) {
            if ("0".equals(bean.getIsCatalog())) {
                rsparam.setParentId(bean.getTspStdItemId());
                rsparam.setLevelId("2");
                List<TspStdBean> beanList = super.findList(SqlId.SQL_ID_GET_TSP_STD_INFO, rsparam);
                if(!CollectionUtils.isEmpty(beanList)){
                    bean.setPdTspStds(beanList);
                    tspStdList.add(bean);
                }
            }
        }
        return tspStdList;
        //改善 #2328 卖家添加产品，加工技术标准”、“加工质量标准”、“其他标准 ” 标准找不到的时候的提示 统一修改为 “对不起，没有查询到记录！” 2016/10/09 by 杨春艳 end
    }

    /**
     * 取得卖家产品安全标准数据
     *
     * @param rsparam rsparam
     * @return 卖家产品安全标准
     */
    @Transactional
    public List<SftStdBean> getSftStdInfo(PDInfoParam rsparam) {
        logger.debug("查询产品安全标准数据");
        rsparam.setLevelId("1");
        List<SftStdBean> sftStdBeans = super.findList(SqlId.SQL_ID_GET_SFT_STD_INFO, rsparam);
        //改善 #2328 卖家添加产品，加工技术标准”、“加工质量标准”、“其他标准 ” 标准找不到的时候的提示 统一修改为 “对不起，没有查询到记录！” 2016/10/09 by 杨春艳 start
        List<SftStdBean> sftStdList = new ArrayList<>();
        for (SftStdBean bean : sftStdBeans) {
            if ("0".equals(bean.getIsCatalog())) {
                rsparam.setParentId(bean.getSftStdItemId());
                rsparam.setLevelId("2");
                List<SftStdBean> beanList = super.findList(SqlId.SQL_ID_GET_SFT_STD_INFO, rsparam);
                if(!CollectionUtils.isEmpty(beanList)){
                    bean.setPdSftStds(beanList);
                    sftStdList.add(bean);
                }
            }
        }
        return sftStdList;
        //改善 #2328 卖家添加产品，加工技术标准”、“加工质量标准”、“其他标准 ” 标准找不到的时候的提示 统一修改为 “对不起，没有查询到记录！” 2016/10/09 by 杨春艳 end
    }

    /**
     * 查询原料种源信息
     * @param param
     * @return
     */
    @Transactional
    public List<PdClassestreeMat> selectSourceMat(RsRequest<PDInfoParam> param){
        // 创建输入参数
        PDInfoParam pdInfoParam = param.getParam();

        //开始查询
        BaseParam params = new BaseParam();

        if(StringUtils.hasLength(pdInfoParam.getClassesTreeCode())) {
            params.setFilter("classestreeCode", pdInfoParam.getClassesTreeCode());
        }
        else
        {
            if(StringUtils.hasLength(pdInfoParam.getBreedCode())){
                String classestreeCode = pdInfoParam.getClassesCode()+pdInfoParam.getMachiningCode();
                params.setFilter("classestreeCode", StringUtil.toSafeString(classestreeCode));
            }else{
                String classestreeCode = pdInfoParam.getClassesCode()+pdInfoParam.getMachiningCode()+pdInfoParam.getBreedCode();
                params.setFilter("classestreeCode", StringUtil.toSafeString(classestreeCode));
            }
        }
        if(!StringUtils.hasLength(pdInfoParam.getTreeCode())) {
            pdInfoParam.setTreeCode("0");
        }
        params.setFilter("delFlg",pdInfoParam.getDelFlag());
        List<PdClassestreeMat> results = super.findList(SqlId.SQL_ID_FIND_MAT_STD,params);

        return results;
    }

}