package com.msk.seller.controller;

import com.hoperun.core.bean.BasePageParam;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.utils.DbUtils;
import com.msk.common.base.BaseUploadController;
import com.msk.seller.bean.SL241132Bean;
import com.msk.seller.logic.Sl241132Logic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 卖家货号列表Controller.
 *
 * @author pxg
 */
@Controller
@RequestMapping(value = "SL241132")
public class SL241132Controller extends BaseUploadController {
    @Autowired
    private Sl241132Logic sl241132Logic;
    /**
     * 实例化页面
     *
     * @return 卖家货号信息页面
     */
    @RequestMapping(value = "init",
            method = RequestMethod.POST)
    private String init(Model model,String slCode) {
        model.addAttribute("slCode", slCode);
        return "sl/SL241132";
    }

    /**
     * Deprecated by xia_xiaojie on 2016/6/20.
     *
     * 卖家货号列表
     *
     * @param basePageParam basePageParam
     * @return 信息
     * @author pxg
     */
    @RequestMapping(value = "search",
            method = RequestMethod.POST)
    public
    @ResponseBody
    PageResult<SL241132Bean> search(BasePageParam basePageParam) {
        DbUtils.buildLikeCondition(basePageParam, "slPdArtno", DbUtils.LikeMode.FRONT);
//        *//*DbUtils.buildLikeCondition(basePageParam, "slCodeDis", DbUtils.LikeMode.FRONT);*//*
        return sl241132Logic.findPage(basePageParam);
    }

//    /**
//     * 卖家货号列表。替代旧search()。
//     * Created by xia_xiaojie on 2016/6/21.
//     */
//    @RequestMapping(value = "search", method = RequestMethod.POST)
//    @ResponseBody
//    public PageResult<SL241132Bean> search(BasePageParam basePageParam) {
//        DbUtils.buildLikeCondition(basePageParam, "slPdArtno", DbUtils.LikeMode.FRONT);
//        PageResult<SL241132Bean> pageResult = sl241132Logic.findPage(basePageParam, SL241132Bean.class);
//        List<SL241132Bean> beans = pageResult.getData();
//
//        List<PDInfoParam> classesCodes = new ArrayList<PDInfoParam>();
//        List<PDInfoParam> machiningCodes = new ArrayList<PDInfoParam>();
//        List<PDInfoParam> breedCodes = new ArrayList<PDInfoParam>();
//        List<PDInfoParam> featureCodes = new ArrayList<PDInfoParam>();
//        List<PDInfoParam> weightCodes = new ArrayList<PDInfoParam>();
//        List<String> normsCodes = new ArrayList<String>();
//
//        for (SL241132Bean bean : beans) {
//            normsCodes.add(bean.getNormsCode());
//
//            PDInfoParam classesParam = new PDInfoParam();
//            classesParam.setClassesCode(bean.getClassesCode());
//            classesCodes.add(classesParam);
//
//            PDInfoParam machiningParam = new PDInfoParam();
//            machiningParam.setClassesCode(bean.getClassesCode());
//            machiningParam.setMachiningCode(bean.getMachiningCode());
//            machiningCodes.add(machiningParam);
//
//            PDInfoParam breedParam = new PDInfoParam();
//            breedParam.setClassesCode(bean.getClassesCode());
//            breedParam.setMachiningCode(bean.getMachiningCode());
//            breedParam.setBreedCode(bean.getBreedCode());
//            breedCodes.add(breedParam);
//
//            PDInfoParam featureParam = new PDInfoParam();
//            featureParam.setClassesCode(bean.getClassesCode());
//            featureParam.setMachiningCode(bean.getMachiningCode());
//            featureParam.setBreedCode(bean.getBreedCode());
//            featureParam.setFeatureCode(bean.getFeatureCode());
//            featureCodes.add(featureParam);
//
//            PDInfoParam weightParam = new PDInfoParam();
//            weightParam.setClassesCode(bean.getClassesCode());
//            weightParam.setMachiningCode(bean.getMachiningCode());
//            weightParam.setBreedCode(bean.getBreedCode());
//            weightParam.setFeatureCode(bean.getFeatureCode());
//            weightParam.setWeightCode(bean.getWeightCode());
//            weightCodes.add(weightParam);
//        }
//
//        PDInfoParam pdiParam = new PDInfoParam();
//        pdiParam.setClassesCodeList(classesCodes);
//        pdiParam.setMachiningCodeList(machiningCodes);
//        pdiParam.setBreedCodeList(breedCodes);
//        pdiParam.setFeatureCodeList(featureCodes);
//        pdiParam.setWeightCodeList(weightCodes);
//        pdiParam.setNormCodes(normsCodes);
//        Map<String, Map<String, String>> map = this.mappingCodeToName(pdiParam);
//
//        Map<String, String> classesMap = map.get("classes");
//        Map<String, String> machiningMap = map.get("machining");
//        Map<String, String> breedMap = map.get("breed");
//        Map<String, String> featureMap = map.get("feature");
//        Map<String, String> weightMap = map.get("weight");
//        Map<String, String> normsMap = map.get("norms");
//
//        for (SL241132Bean bean : beans) {
//            bean.setPdClassesName(StringUtil.nullToEmpty(classesMap.get(bean.getClassesCode())));
//            bean.setMachiningName(StringUtil.nullToEmpty(machiningMap.get(bean.getMachiningCode())));
//            bean.setPdBreedName(StringUtil.nullToEmpty(breedMap.get(bean.getBreedCode())));
//            bean.setPdFeatureName(StringUtil.nullToEmpty(featureMap.get(bean.getFeatureCode())));
//            bean.setWeightName(StringUtil.nullToEmpty(weightMap.get(bean.getWeightCode())));
//            bean.setNormsName(StringUtil.nullToEmpty(normsMap.get(bean.getNormsCode())));
//        }
//        return pageResult;
//    }
//
//    /**
//     * 根据代码，批量查询名称。
//     * Created by xia_xiaojie on 2016/6/21.
//     */
//    private Map<String, Map<String, String>> mappingCodeToName(PDInfoParam param) {
//        Map<String, Map<String, String>> map = new HashMap<String, Map<String, String>>();
//        Map<String, String> classesMap = new HashMap<String, String>();
//        Map<String, String> machiningMap = new HashMap<String, String>();
//        Map<String, String> breedMap = new HashMap<String, String>();
//        Map<String, String> featureMap = new HashMap<String, String>();
//        Map<String, String> weightMap = new HashMap<String, String>();
//        Map<String, String> normsMap = new HashMap<String, String>();
//
//        ProductBeanResult result = SLControllerUtil.getProductBatchNames(param);
//        if (null != result) {
//            Map<String, List<PDInfoResult>> pdMap = result.getPdInfo();
//
//            List<PDInfoResult> classesList = pdMap.get("classes");
//            if (!CollectionUtils.isEmpty(classesList)) {
//                for (PDInfoResult p : classesList) {
//                    classesMap.put(p.getClassesCode(), p.getClassesName());
//                }
//            }
//
//            List<PDInfoResult> machiningList = pdMap.get("machining");
//            if (!CollectionUtils.isEmpty(machiningList)) {
//                for (PDInfoResult p : machiningList) {
//                    machiningMap.put(p.getMachiningCode(), p.getMachiningName());
//                }
//            }
//
//            List<PDInfoResult> breedList = pdMap.get("breed");
//            if (!CollectionUtils.isEmpty(breedList)) {
//                for (PDInfoResult p : breedList) {
//                    breedMap.put(p.getBreedCode(), p.getBreedName());
//                }
//            }
//
//            List<PDInfoResult> featureList = pdMap.get("feature");
//            if (!CollectionUtils.isEmpty(featureList)) {
//                for (PDInfoResult p : featureList) {
//                    featureMap.put(p.getFeatureCode(), p.getFeatureName());
//                }
//            }
//
//            List<PDInfoResult> weightList = pdMap.get("weight");
//            if (!CollectionUtils.isEmpty(weightList)) {
//                for (PDInfoResult p : weightList) {
//                    weightMap.put(p.getWeightCode(), p.getWeightName());
//                }
//            }
//
//            List<PDInfoResult> normsList = pdMap.get("norms");
//            if (!CollectionUtils.isEmpty(normsList)) {
//                for (PDInfoResult p : normsList) {
//                    normsMap.put(p.getNormsCode(), p.getNormsName());
//                }
//            }
//        }
//
//        map.put("classes", classesMap);
//        map.put("machining", machiningMap);
//        map.put("breed", breedMap);
//        map.put("feature", featureMap);
//        map.put("weight", weightMap);
//        map.put("norms", normsMap);
//        return map;
//    }

}
