package com.msk.seller.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.common.bean.RsResponse;
import com.msk.common.business.constant.SellerConstant;
import com.msk.common.config.CodeMasterManager;
import com.msk.core.entity.SlPdArtno;
import com.msk.core.entity.SlProduct;
import com.msk.core.entity.SlProductStatusHis;
import com.msk.product.bean.*;
import com.msk.seller.bean.*;
import com.msk.seller.utils.ISLRestUtil;
import com.msk.seller.utils.SLControllerUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by liu_yan2 on 2016/6/21.
 */
@Service
public class ISLProductRsLogic extends BaseLogic {

    interface SqlId {
        String SQL_ID_GET_SL_PRO_INFO = "getSlProInfo";
        String SQL_ID_GET_SL_PRODUCT_INFO = "getSlProductInfo";
        String SQL_ID_GET_PD_CODE = "getPdCode";
        String SQL_ID_GET_PRODUCT_LIST = "getProductList";
    }

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }


    /**
     * 查询卖家产品目录报表
     *
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public List<SlProductRsBean> getSlProInfo(SlProductRsParam param) {
        List<SlProductRsBean> lists = this.findList(SqlId.SQL_ID_GET_SL_PRO_INFO, param);
        if (CollectionUtils.isNotEmpty(lists)) {
            // 从redis  卖家主分类
            Map<String, String> slMainClassMap = CodeMasterManager.findCodeMasterMap(SellerConstant.SlMainClass.TYPE);
            // 从redis  品牌分类
            Map<String, String> brandClassMap = CodeMasterManager.findCodeMasterMap(SellerConstant.BrandClass.TYPE);

            for (SlProductRsBean bean : lists) {
                // 处理 slMainClassName
                if (!StringUtil.isNullOrEmpty(bean.getSlMainClass())) {
                    String value = slMainClassMap.get(bean.getSlMainClass());
                    if (null != value) {
                        bean.setSlMainClassName(value);
                    }
                }
                // 处理 brandClassName
                if (!StringUtil.isNullOrEmpty(bean.getBrandClass())) {
                    String value = brandClassMap.get(bean.getBrandClass());
                    if (null != value) {
                        bean.setBrandClassName(value);
                    }
                }
            }
        }
        return lists;
    }


    /**
     * 查询卖家产品信息
     *
     * @param params
     * @return
     */
    @Transactional(readOnly = true)
    public List<SlProductRsBean> getSlProductInfo(ISLSellerRsParam params) {
        BaseParam param = new BaseParam();
        if (!StringUtil.isEmpty(params.getSlCode())) {
            param.getFilterMap().put("slCode", params.getSlCode());
            String prodEpId = StringUtil.toSafeString(params.getProdEpId());
            param.getFilterMap().put("prodEpId", prodEpId);
            List<SlProductRsBean> dataList = this.findList(SqlId.SQL_ID_GET_SL_PRODUCT_INFO, param);
            if (!CollectionUtils.isEmpty(dataList)) {
                dataList = dealData(dataList, prodEpId);
            }
            return dataList;
        } else {
            return new ArrayList<SlProductRsBean>();
        }
    }

    /**
     * 处理查询到的数据
     *
     * @param dataList
     * @return
     */
    @Transactional(readOnly = true)
    private List<SlProductRsBean> dealData(List<SlProductRsBean> dataList, String prodEpId) {
        // 准备数据
        PDInfoParam pdInfoParam = new PDInfoParam();
        List<StdItem> stdParams = new ArrayList<StdItem>();
        List<PDInfoParam> weightCodes = new ArrayList<PDInfoParam>();

        // 从redis  获取 产品技术标准定级(加工质量标准)
        Map<String, String> slTncGradeCodeMap = CodeMasterManager.findCodeMasterMap(SellerConstant.SlTncGradeCode.TYPE);

        for (SlProductRsBean bean : dataList) {
            // 处理 slTncGradeName
            if (!StringUtil.isNullOrEmpty(bean.getSlTncGradeCode() + "")) {
                String value = slTncGradeCodeMap.get(bean.getSlTncGradeCode() + "");
                if (null != value) {
                    bean.setSlTncGradeName(value);
                }
            }

            StdItem stdItem = new StdItem();
            stdItem.setStandardId(StringUtil.toSafeString(bean.getStandardId()));
            stdItem.setNormsCode(bean.getNormsCode());
            stdParams.add(stdItem);

            if (!StringUtil.isEmpty(bean.getPdClassesCode()) && !StringUtil.isEmpty(bean.getMachiningCode()) && !StringUtil.isEmpty(bean.getPdBreedCode()) &&
                    !StringUtil.isEmpty(bean.getPdFeatureCode()) && !StringUtil.isEmpty(bean.getWeightCode())) {
                PDInfoParam weightParam = new PDInfoParam();
                weightParam.setClassesCode(bean.getPdClassesCode());
                weightParam.setMachiningCode(bean.getMachiningCode());
                weightParam.setBreedCode(bean.getPdBreedCode());
                weightParam.setFeatureCode(bean.getPdFeatureCode());
                weightParam.setWeightCode(bean.getWeightCode());
                weightCodes.add(weightParam);
            }
        }

        // 调接口查 净重数值 拼 map
        Map<String, BigDecimal> weightMap = dealWeightMap(pdInfoParam, weightCodes);
        // 调接口查 查询 外包装规格 拼 map
        Map<String, PDInfoResult> maps = dealMap(pdInfoParam, stdParams);

        // 组装数据
        for (SlProductRsBean bean : dataList) {
            if (!StringUtil.isEmpty(prodEpId)) {
                // 产品 描述
                bean.setPdDesc(bean.getPdName() + "/" + bean.getSlTncGradeName() + "/" + bean.getBrandName());
            }
            // 产品名称
            String key = bean.getStandardId() + bean.getNormsCode();
            PDInfoResult pdInfoResult = maps.get(key);
            bean.setPdCode(bean.getPdCode() + bean.getSlTncGradeCode());
            if (null != pdInfoResult) {
                bean.setNormsOut(pdInfoResult.getNormsOut());
                bean.setPdName(bean.getPdName() + "/" + pdInfoResult.getNormsOut() + "/" + bean.getSlTncGradeName());
            } else {
                bean.setNormsOut("");
                bean.setPdName(bean.getPdName() + "/" + "/" + bean.getSlTncGradeName());
            }
            // 产品 净重数值
            String keyV = bean.getPdClassesCode() + bean.getMachiningCode() + bean.getPdBreedCode() + bean.getPdFeatureCode() + bean.getWeightCode();
            BigDecimal valueV = weightMap.get(keyV);
            if (null != valueV) {
                bean.setWeightVal(valueV);
            }
        }
        // pdCode 排序
        Collections.sort(dataList);
        return dataList;
    }


    /**
     * 调接口查 净重数值 拼 map
     *
     * @param pdInfoParam
     * @param weightCodes
     * @return
     */
    @Transactional(readOnly = true)
    private Map<String, BigDecimal> dealWeightMap(PDInfoParam pdInfoParam, List<PDInfoParam> weightCodes) {
        // 调接口查 净重数值 拼 map
        Map<String, BigDecimal> weightMap = new HashMap<String, BigDecimal>();
        if (!CollectionUtils.isEmpty(weightCodes)) {
            pdInfoParam.setWeightCodeList(weightCodes);
            ProductBeanResult result = SLControllerUtil.getProductBatchNames(pdInfoParam);
            if (null != result) {
                Map<String, List<PDInfoResult>> pdMap = result.getPdInfo();
                List<PDInfoResult> weightList = pdMap.get("weight");
                if (!CollectionUtils.isEmpty(weightList)) {
                    for (PDInfoResult p : weightList) {
                        weightMap.put(p.getClassesCode() + p.getMachiningCode() + p.getBreedCode() + p.getFeatureCode() + p.getWeightCode(), p.getWeightVal());
                    }
                }
            }
        }
        return weightMap;
    }


    /**
     * 调接口查 查询 外包装规格 拼 map
     *
     * @param pdInfoParam
     * @param stdParams
     * @return
     */
    @Transactional(readOnly = true)
    private Map<String, PDInfoResult> dealMap(PDInfoParam pdInfoParam, List<StdItem> stdParams) {
        List<PDInfoResult> resultList = new ArrayList<PDInfoResult>();
        pdInfoParam.setStdParams(stdParams);
        RsResponse<ProductBeanResult> rspe = ISLRestUtil.findpdNormsInfos(pdInfoParam);
        ProductBeanResult productBeanResult = rspe.getResult();
        if (null != productBeanResult) {
            resultList = productBeanResult.getResult();
        }
        // 拼 map
        Map<String, PDInfoResult> maps = new HashMap<String, PDInfoResult>();
        if (!CollectionUtils.isEmpty(resultList)) {
            for (PDInfoResult bean : resultList) {
                String key = bean.getStandardId() + bean.getNormsCode();
                maps.put(key, bean);
            }
        }
        return maps;
    }

    /**
     * 查询卖家产品属性
     *
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public ISLProductRsResult getPdCode(ISLProductRsParam param) {
        List<SlPdArtno> resultList = super.findList(SqlId.SQL_ID_GET_PD_CODE, param);
        ISLProductRsResult result = new ISLProductRsResult();
        result.setProductList(resultList);
        return result;
    }

    /**
     * 查询产品信息
     *
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public List<SlProduct> getProductList(ISLProductRsParam param) {
        List<SlProduct> resultList = super.findList(SqlId.SQL_ID_GET_PRODUCT_LIST, param);
        return resultList;
    }

    /**
     * 记录产品信息
     *
     * @param hisList
     * @return
     */
    @Transactional(readOnly = false)
    public int saveProductStatusHis(List<SlProductStatusHis> hisList) {
        return super.batchSave(hisList);
    }

}
