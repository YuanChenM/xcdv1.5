package com.msk.seller.utils;

import com.msk.core.entity.SlProduct;
import com.msk.product.bean.PDInfoParam;
import com.msk.product.bean.PDInfoResult;
import com.msk.product.bean.ProductBeanResult;
import com.msk.seller.bean.SL241116Bean;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by guan_zhongheng on 2016/7/7.
 */
public class SlProductUtils {
    /**
     * 根据代码，批量查询名称。
     * Created by xia_xiaojie on 2016/6/27.
     */
    public static Map<String, Map<String, String>> mappingCodeToName(SlProduct slProduct) {
        List<PDInfoParam> classesCodes = new ArrayList<PDInfoParam>();
        List<PDInfoParam> machiningCodes = new ArrayList<PDInfoParam>();
        List<PDInfoParam> breedCodes = new ArrayList<PDInfoParam>();
        List<PDInfoParam> featureCodes = new ArrayList<PDInfoParam>();
        List<PDInfoParam> weightCodes = new ArrayList<PDInfoParam>();

        PDInfoParam classesParam = new PDInfoParam();
        classesParam.setClassesCode(slProduct.getPdClassesCode());
        classesCodes.add(classesParam);

        PDInfoParam machiningParam = new PDInfoParam();
        machiningParam.setClassesCode(slProduct.getPdClassesCode());
        machiningParam.setMachiningCode(slProduct.getMachiningCode());
        machiningCodes.add(machiningParam);

        PDInfoParam breedParam = new PDInfoParam();
        breedParam.setClassesCode(slProduct.getPdClassesCode());
        breedParam.setMachiningCode(slProduct.getMachiningCode());
        breedParam.setBreedCode(slProduct.getPdBreedCode());
        breedCodes.add(breedParam);

        PDInfoParam featureParam = new PDInfoParam();
        featureParam.setClassesCode(slProduct.getPdClassesCode());
        featureParam.setMachiningCode(slProduct.getMachiningCode());
        featureParam.setBreedCode(slProduct.getPdBreedCode());
        featureParam.setFeatureCode(slProduct.getPdFeatureCode());
        featureCodes.add(featureParam);

        PDInfoParam weightParam = new PDInfoParam();
        weightParam.setClassesCode(slProduct.getPdClassesCode());
        weightParam.setMachiningCode(slProduct.getMachiningCode());
        weightParam.setBreedCode(slProduct.getPdBreedCode());
        weightParam.setFeatureCode(slProduct.getPdFeatureCode());
        weightParam.setWeightCode(slProduct.getWeightCode());
        weightCodes.add(weightParam);

        PDInfoParam pdParam = new PDInfoParam();
        pdParam.setClassesCodeList(classesCodes);
        pdParam.setMachiningCodeList(machiningCodes);
        pdParam.setBreedCodeList(breedCodes);
        pdParam.setFeatureCodeList(featureCodes);
        pdParam.setWeightCodeList(weightCodes);
        ProductBeanResult result = SLControllerUtil.getProductBatchNames(pdParam);

        Map<String, Map<String, String>> map = new HashMap<String, Map<String, String>>();
        Map<String, String> classesMap = new HashMap<String, String>();
        Map<String, String> machiningMap = new HashMap<String, String>();
        Map<String, String> breedMap = new HashMap<String, String>();
        Map<String, String> featureMap = new HashMap<String, String>();
        Map<String, String> weightMap = new HashMap<String, String>();
        Map<String, String> normsMap = new HashMap<String, String>();

        if (null != result) {
            Map<String, List<PDInfoResult>> pdMap = result.getPdInfo();

            List<PDInfoResult> classesList = pdMap.get("classes");
            if (!CollectionUtils.isEmpty(classesList)) {
                for (PDInfoResult p : classesList) {
                    classesMap.put(p.getClassesCode(), p.getClassesName());
                }
            }

            List<PDInfoResult> machiningList = pdMap.get("machining");
            if (!CollectionUtils.isEmpty(machiningList)) {
                for (PDInfoResult p : machiningList) {
                    machiningMap.put(p.getClassesCode() + p.getMachiningCode(), p.getMachiningName());
                }
            }

            List<PDInfoResult> breedList = pdMap.get("breed");
            if (!CollectionUtils.isEmpty(breedList)) {
                for (PDInfoResult p : breedList) {
                    breedMap.put(p.getClassesCode() + p.getMachiningCode() + p.getBreedCode(), p.getBreedName());
                }
            }

            List<PDInfoResult> featureList = pdMap.get("feature");
            if (!CollectionUtils.isEmpty(featureList)) {
                for (PDInfoResult p : featureList) {
                    featureMap.put(p.getClassesCode() + p.getMachiningCode() + p.getBreedCode() + p.getFeatureCode(), p.getFeatureName());
                }
            }

            List<PDInfoResult> weightList = pdMap.get("weight");
            if (!CollectionUtils.isEmpty(weightList)) {
                for (PDInfoResult p : weightList) {
                    weightMap.put(p.getClassesCode() + p.getMachiningCode() + p.getBreedCode() + p.getFeatureCode() + p.getWeightCode(), p.getWeightName());
                }
            }
        }
        map.put("classes", classesMap);
        map.put("machining", machiningMap);
        map.put("breed", breedMap);
        map.put("feature", featureMap);
        map.put("weight", weightMap);
        return map;
    }
}
