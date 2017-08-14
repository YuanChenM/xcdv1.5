package com.msk.seller.logic;


import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.hoperun.jdbc.utils.DbUtils;
import com.msk.common.base.BaseLogic;
import com.msk.common.bean.RsRequest;
import com.msk.common.business.constant.SellerConstant;
import com.msk.common.config.CodeMasterManager;
import com.msk.core.entity.SlProduct;
import com.msk.core.entity.SoSalesRanking;
import com.msk.seller.bean.*;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangchi on 2016/5/9.
 */
@Service
public class ISL231193RsLogic extends BaseLogic {

    /**
     * logger
     */
    private Logger logger = LoggerFactory.getLogger(ISL231193RsLogic.class);

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     * SQL Map 中SQL ID定义
     *
     * @author gyh
     */
    interface SqlId {
        static final String SQL_ID_GET_SL_PRODUCT = "getSlProduct";
        static final String SQL_ID_GET_SL_SELLER_PRODUCT = "getSlSellerProduct";
        static final String SQL_ID_GET_SL_ENTERPRISE = "getSlEnterprise";
        static final String SQL_ID_GET_SL_ENTERPRISE_BY_ACCOUNT = "getSlEnterpriseByAccount";
        static final String SQL_ID_GET_SL_EP_DATALIST = "getSlEpDataList";
        static final String SQL_ID_FIND_SL_PRODUCT = "findSlProduct";
        static final String SQL_ID_FIND_SL_SELLER_CODE = "findSlSellerCode";
    }


    /**
     * 根据卖家ID和物流区查询卖家产品编码
     *
     * @param param param
     * @return 根据卖家ID和物流区查询卖家产品编码
     * @author zhangchi
     */
    @Transactional(readOnly = true)
    public List<ISL231193RsResult> querySlProduct(RsRequest<ISL231193RsParam> param) {
        logger.debug("根据卖家ID和物流区查询卖家产品编码");
        ISL231193RsParam isl231193RsParam = param.getParam();
        List<ISL231193RsResult> isl231193Result = new ArrayList<ISL231193RsResult>();
        if (null != isl231193RsParam) {
            BaseParam params = new BaseParam();
            params.setFilter("slCode", isl231193RsParam.getSlCode());
            params.setFilter("lgcsAreaCode", isl231193RsParam.getLgcsAreaCode());
            isl231193Result = super.findList(SqlId.SQL_ID_GET_SL_PRODUCT, params);
        }
        return isl231193Result;
    }

    /**
     * 查询创建时间范围内的卖家用户
     *
     * @param param param
     * @return 查询创建时间范围内的卖家用户
     * @author zhangchi
     */
    @Transactional(readOnly = true)
    public List<SoSalesRanking> querySlSellerProduct(RsRequest<ISL231193RsParam> param) {
        logger.debug("查询创建时间范围内的卖家用户");
        ISL231193RsParam isl231193RsParam = param.getParam();
        List<SoSalesRanking> soSalesRanking = new ArrayList<SoSalesRanking>();
        if (null != isl231193RsParam) {
            BaseParam params = new BaseParam();
            params.setFilter("cycleStart", isl231193RsParam.getCycleStart());
            params.setFilter("cycleEnd", isl231193RsParam.getCycleEnd());
            soSalesRanking = super.findList(SqlId.SQL_ID_GET_SL_SELLER_PRODUCT, params);
        }
        return soSalesRanking;
    }


    /**
     * 根据卖家ID查询供应商名称
     *
     * @param param param
     * @return 根据卖家ID查询供应商名称
     * @author zhangchi
     */
    @Transactional(readOnly = true)
    public List<ISL231193RsResult> querySlEnterprise(RsRequest<ISL231193RsParam> param) {
        logger.debug("根据卖家ID查询供应商名称");
        ISL231193RsParam isl231193RsParam = param.getParam();
        List<ISL231193RsResult> isl231193Result = new ArrayList<ISL231193RsResult>();
        if (null != isl231193RsParam) {
            BaseParam params = new BaseParam();
            params.setFilterObject("slCodeList", isl231193RsParam.getSlCodeList());
            params.setFilterObject("epName", isl231193RsParam.getEpName());
            if (!StringUtil.isNullOrEmpty(isl231193RsParam.getEpName())) {
                DbUtils.buildLikeCondition(params, "epName", DbUtils.LikeMode.PARTIAL);
            }
            isl231193Result = super.findList(SqlId.SQL_ID_GET_SL_ENTERPRISE, params);
        }
        return isl231193Result;
    }

    /**
     * 根据卖家ID查询供应商信息
     *
     * @param param param
     * @return 根据卖家Account查询供应商名称
     * @author zhangchi
     */
    @Transactional(readOnly = true)
    public ISL231193RsResult queryslEpData(RsRequest<ISL231193RsParam> param) {
        logger.debug("根据卖家Account查询供应商信息");
        ISL231193RsParam isl231193RsParam = param.getParam();
        ISL231193RsResult isl231193Result = new ISL231193RsResult();
        if (null != isl231193RsParam) {
            BaseParam params = new BaseParam();
            params.setFilter("slAccount", isl231193RsParam.getSlAccount());
            isl231193Result = super.findOne(SqlId.SQL_ID_GET_SL_ENTERPRISE_BY_ACCOUNT, params);
        }
        return isl231193Result;
    }

    /**
     * 批量查询卖家身份企业信息
     *
     * @param param param
     * @return 批量查询卖家身份企业信息
     * @author zhangchi
     */
    @Transactional(readOnly = true)
    public List<ISL231193RsResult> querySlEpDataList(RsRequest<ISL231193RsParam> param) {
        logger.debug("批量查询卖家身份企业信息");
        ISL231193RsParam isl231193RsParam = param.getParam();
        List<ISL231193RsBean> isl231193RsBean = isl231193RsParam.getParamList();
        BaseParam params = new BaseParam();
        params.setFilterObject("isl231193RsBean", isl231193RsBean);
        List<ISL231193RsResult> isl231193Result = super.findList(SqlId.SQL_ID_GET_SL_EP_DATALIST, params);
        return isl231193Result;
    }

    /**
     * 查询卖家产品信息
     *
     * @param param param
     * @return 根据卖家ID、产品一级分类编码、产品二级分类编码、产品品种编码、产品特征编码、净重编码查询对应的卖家产品信息
     */
    @Transactional(readOnly = true)
    public List<SlProductBean> findSlProduct(RsRequest<SlPdArtnoBean> param) {
        logger.debug("查询卖家产品信息");
        SlPdArtnoBean slPdArtnoBean = param.getParam();
        List<SlProductBean> slProductBean = new ArrayList<SlProductBean>();
        if (null != slPdArtnoBean) {
            BaseParam params = new BaseParam();
            params.setFilterObject("slCode", slPdArtnoBean.getSlCode());
            params.setFilterObject("classesCode", slPdArtnoBean.getClassesCode());
            params.setFilterObject("machiningCode", slPdArtnoBean.getMachiningCode());
            params.setFilterObject("breedCode", slPdArtnoBean.getBreedCode());
            params.setFilterObject("featureCode", slPdArtnoBean.getFeatureCode());
            params.setFilterObject("weightCode", slPdArtnoBean.getWeightCode());
            slProductBean = this.findList(SqlId.SQL_ID_FIND_SL_PRODUCT, params);

            if(CollectionUtils.isNotEmpty(slProductBean)){
                // 从redis  获取 产品技术标准定级(加工质量标准)
                Map<String, String> slTncGradeCodeMap = CodeMasterManager.findCodeMasterMap(SellerConstant.SlTncGradeCode.TYPE);
                // 从redis  获取 产品质量标准定级
                Map<String, String> slQltGradeCodeMap = CodeMasterManager.findCodeMasterMap(SellerConstant.SlQltGradeCode.TYPE);
                // 从redis  获取 是否
                Map<String, String> yesNoMap = CodeMasterManager.findCodeMasterMap(SellerConstant.YESNO.TYPE);
                for(SlProductBean bean :slProductBean){
                    // 处理 slTncGradeName
                    if(!StringUtil.isNullOrEmpty(bean.getSlTncGradeCode()+"")){
                        String value = slTncGradeCodeMap.get(bean.getSlTncGradeCode()+"");
                        if(null != value){
                            bean.setSlTncGradeName(value);
                        }
                    }
                    // 处理 slQltGradeName
                    if(!StringUtil.isNullOrEmpty(bean.getSlQltGradeCode()+"")){
                        String value = slQltGradeCodeMap.get(bean.getSlQltGradeCode()+"");
                        if(null != value){
                            bean.setSlQltGradeName(value);
                        }
                    }
                    // 处理 distFlg
                    if(!StringUtil.isNullOrEmpty(bean.getDistFlg())){
                        String value = yesNoMap.get(bean.getDistFlg());
                        if(null != value){
                            bean.setDistFlg(value);
                        }
                    }
                }
            }
        }
        return slProductBean;
    }

    /**
     * 查询卖家（显示）编码
     *
     * @param isl231193RsParam isl231193RsParam
     * @return 查询卖家（显示）编码
     */
    @Transactional(readOnly = true)
    public List<ISL231193RsResult> findSlSellerCode(ISL231193RsParam isl231193RsParam) {
        logger.debug("查询卖家（显示）编码");
        List<ISL231193RsResult> isl231193Result = new ArrayList<ISL231193RsResult>();
        if (null != isl231193RsParam) {
            BaseParam params = new BaseParam();
            params.setFilterObject("slCodeDis", isl231193RsParam.getSlCodeDis());
            if (!StringUtil.isNullOrEmpty(isl231193RsParam.getSlCodeDis())) {
                DbUtils.buildLikeCondition(params, "slCodeDis", DbUtils.LikeMode.FRONT);
            }
            params.setFilterObject("slCodeList", isl231193RsParam.getSlCodeList());
            isl231193Result = this.findList(SqlId.SQL_ID_FIND_SL_SELLER_CODE, params);
        }
        return isl231193Result;
    }


}
