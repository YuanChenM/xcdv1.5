package com.msk.seller.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.StringConst;
import com.hoperun.core.utils.StringUtil;
import com.msk.common.bean.RsResponse;
import com.msk.product.bean.PDInfoParam;
import com.msk.product.bean.PDInfoResult;
import com.msk.product.bean.ProductBeanResult;
import com.msk.seller.bean.ISL231119RsParam;
import com.msk.seller.bean.ISL231119RsResult;
import com.msk.seller.bean.SlLogiAreaPdBean;
import com.msk.seller.utils.SLControllerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.hoperun.core.consts.NumberConst;

/**
 * ISL231119Logic.
 *
 * @author yuan_chen
 */
public class ISL231119Logic extends BaseLogic {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(ISL231119Logic.class);

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     * 物流区产品信息查询
     *
     * @param param param
     * @return 分页结果
     */
    @Transactional(readOnly = true)
    public ISL231119RsResult findRsResult(ISL231119RsParam param) {
        logger.info("物流区卖家产品信息查询");
        if (param == null) {
            param = new ISL231119RsParam();
        }

        String lgcsCode = StringUtil.toSafeString(param.getLgcsCode());
        if (!StringUtil.isNullOrEmpty(lgcsCode)) {
            String[] lgcsCodes = lgcsCode.split(StringConst.COMMA);
            param.getFilterMap().put("lgcsCodes", lgcsCodes);
        }

        ISL231119RsResult rsResult = new ISL231119RsResult();
        List<SlLogiAreaPdBean> logiAreaPdBeans = new ArrayList<SlLogiAreaPdBean>();

        rsResult.setTotalCount(super.getCount(param));
        rsResult.setPageNo(param.getPageNo());
        if (rsResult.getTotalCount() != NumberConst.IntDef.INT_ZERO) {
            logiAreaPdBeans = super.findList(param);
            PDInfoParam reqParam = new PDInfoParam();

            reqParam = this.findClass(logiAreaPdBeans, reqParam);

            reqParam = this.findBreed(logiAreaPdBeans, reqParam);

            reqParam = this.findFeature(logiAreaPdBeans, reqParam);

            ProductBeanResult result = SLControllerUtil.getProductBatchNames(reqParam);
            Map<String, List<PDInfoResult>> map = result.getPdInfo();
            List<PDInfoResult> classesResults = map.get("classes");
            List<PDInfoResult> breedResults = map.get("breed");
            List<PDInfoResult> featureResults = map.get("feature");

            Map<String, String> classesMap = new HashMap<>();
            for (PDInfoResult classesResult : classesResults) {
                classesMap.put(classesResult.getClassesCode(), classesResult.getClassesName());
            }

            Map<String, String> breedMap = new HashMap<>();
            for (PDInfoResult breedResult : breedResults) {
                breedMap.put(breedResult.getClassesCode() + ":" + breedResult.getBreedCode(),
                    breedResult.getBreedName());
            }

            Map<String, String> featureMap = new HashMap<>();
            for (PDInfoResult featureResult : featureResults) {
                featureMap.put(featureResult.getClassesCode() + ":" + featureResult.getBreedCode() + ":"
                        + featureResult.getFeatureCode(),
                    featureResult.getFeatureName());
            }

            for (SlLogiAreaPdBean logiAreaPdBean : logiAreaPdBeans) {
                String pdName = null;
                String ClassesName = classesMap.get(logiAreaPdBean.getClassesCode());
                if (ClassesName != null) {
                    pdName = ClassesName;
                } else {
                    pdName = logiAreaPdBean.getClassesCode();
                }

                String breedName = breedMap.get(logiAreaPdBean.getClassesCode() + ":" + logiAreaPdBean.getBreedCode());
                if (ClassesName != null) {
                    pdName = pdName + '/' + breedName;
                } else {
                    pdName = pdName + '/' + logiAreaPdBean.getBreedCode();
                }

                String featureName = featureMap.get(logiAreaPdBean.getClassesCode() + ":"
                        + logiAreaPdBean.getBreedCode() + ":" + logiAreaPdBean.getFeatureCode());
                if (featureName != null) {
                    pdName = pdName + '/' + featureName;
                } else {
                    pdName = pdName + '/' + logiAreaPdBean.getFeatureCode();
                }

                logiAreaPdBean.setPdName(pdName);
            }
            rsResult.setTotalPage(rsResult.getTotalCount(), param.getPageCount());
        }

        rsResult.setSlLogiAreaPdBeanList(logiAreaPdBeans);
        return rsResult;
    }

    private PDInfoParam findClass(List<SlLogiAreaPdBean> logiAreaPdBeans, PDInfoParam reqParam) {
        List<PDInfoParam> pdiParams = new ArrayList<PDInfoParam>();
        for (SlLogiAreaPdBean logiAreaPdBean : logiAreaPdBeans) {
            String classesCode = logiAreaPdBean.getClassesCode();
            PDInfoParam pdiParam = new PDInfoParam();
            pdiParam.setClassesCode(classesCode);
            pdiParams.add(pdiParam);
        }
        reqParam.setClassesCodeList(pdiParams);
        return reqParam;
    }

    private PDInfoParam findBreed(List<SlLogiAreaPdBean> logiAreaPdBeans, PDInfoParam reqParam) {
        List<PDInfoParam> pdiParams = new ArrayList<PDInfoParam>();
        for (SlLogiAreaPdBean logiAreaPdBean : logiAreaPdBeans) {
            String classesCode = logiAreaPdBean.getClassesCode();
            String breedCode = logiAreaPdBean.getBreedCode();
            PDInfoParam pdiParam = new PDInfoParam();
            pdiParam.setClassesCode(classesCode);
            pdiParam.setBreedCode(breedCode);
            pdiParams.add(pdiParam);
        }
        reqParam.setBreedCodeList(pdiParams);
        return reqParam;
    }

    private PDInfoParam findFeature(List<SlLogiAreaPdBean> logiAreaPdBeans, PDInfoParam reqParam) {
        List<PDInfoParam> pdiParams = new ArrayList<PDInfoParam>();
        for (SlLogiAreaPdBean logiAreaPdBean : logiAreaPdBeans) {
            String classesCode = logiAreaPdBean.getClassesCode();
            String breedCode = logiAreaPdBean.getBreedCode();
            String featureCode = logiAreaPdBean.getFeatureCode();
            PDInfoParam pdiParam = new PDInfoParam();
            pdiParam.setClassesCode(classesCode);
            pdiParam.setBreedCode(breedCode);
            pdiParam.setFeatureCode(featureCode);
            pdiParams.add(pdiParam);
        }
        reqParam.setFeatureCodeList(pdiParams);
        return reqParam;
    }

}
