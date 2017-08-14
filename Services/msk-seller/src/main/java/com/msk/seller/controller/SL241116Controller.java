package com.msk.seller.controller;

import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.utils.DbUtils;
import com.msk.common.base.BaseUploadController;
import com.msk.common.bean.RsResponse;
import com.msk.common.consts.CodeMasterConst;
import com.msk.common.logic.CommonLogic;
import com.msk.core.entity.*;
import com.msk.product.bean.PDInfoParam;
import com.msk.product.bean.PDInfoResult;
import com.msk.product.bean.ProductBeanResult;
import com.msk.seller.bean.SL241101Bean;
import com.msk.seller.bean.SL241116Bean;
import com.msk.seller.bean.SLUploadFile;
import com.msk.seller.logic.ProductLogic;
import com.msk.seller.logic.SL241117Logic;
import com.msk.seller.logic.Sl241101Logic;
import com.msk.seller.logic.Sl241116Logic;
import com.msk.seller.utils.BusinessConst;
import com.msk.seller.utils.SLControllerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 卖家审批审核列表Controller.
 *
 * @author gyh
 */
@Controller
@RequestMapping(value = "SL241116")
public class SL241116Controller extends BaseUploadController {
    @Autowired
    private Sl241116Logic sl241116Logic;
    @Autowired
    private Sl241101Logic sl241101Logic;
    @Autowired
    private ProductLogic productLogic;
    @Autowired
    private CommonLogic commonLogic;
    @Autowired
    private SL241117Logic sl241117Logic;

    /**
     * 实例化页面
     *
     * @return 卖家产品信息页面
     */
    @RequestMapping(value = "init/{slCode}",
        method = RequestMethod.POST)
    private String init(Model model, @PathVariable("slCode") String slCode) {
        BasePageParam basePageParam = new BasePageParam();
        basePageParam.setPaging(false);
        basePageParam.setFilter("slCode", slCode);
        List<SL241101Bean> sl241101Beans = this.sl241101Logic.findPageResultList(basePageParam);
        if (!CollectionUtils.isEmpty(sl241101Beans)) {
            if (sl241101Beans.size() > 0) {
                model.addAttribute("slShowName", sl241101Beans.get(0).getSlShowName());
            }
        }
        BaseParam param = new BaseParam();
        param.setFilter("slCode", slCode);
        // 产品类别信息
        List<PdClasses> pdClassess = productLogic.findPdClasses(param);
        model.addAttribute("pdClasses", pdClassess);

        List<SlEnterprise> slEnterprises = this.sl241116Logic.findEpInfo(param);
        model.addAttribute("slEnterprise", slEnterprises);
        List<SlPdBrand> slPdBrands = this.sl241116Logic.findSlPdBrand(param);
        model.addAttribute("slPdBrand", slPdBrands);
        model.addAttribute("slCode", slCode);
        model.addAttribute("slProduct", new SL241116Bean());
        return "sl/SL241116";
    }

    /**
     * 查询二级分类信息
     *
     * @param param param
     * @return
     */
    @RequestMapping(value = "findPdMachining",
        method = RequestMethod.POST)
    public @ResponseBody List<PdMachining> findPdMachining(BaseParam param) {
        return this.productLogic.findPdMachining(param);
    }

    /**
     * 查询品种信息
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "findBreed",
        method = RequestMethod.POST)
    public @ResponseBody List<PdBreed> findBreed(BaseParam param) {
        return this.productLogic.findPdBreed(param);
    }

    /**
     * 查询特征信息
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "findFeature",
        method = RequestMethod.POST)
    public @ResponseBody List<PdFeature> findFeature(BaseParam param) {
        return this.productLogic.findPdFeature(param);
    }

    /**
     * 查询包装净重信息
     *
     * @param param param
     * @return
     */
    @RequestMapping(value = "findPdWeight",
        method = RequestMethod.POST)
    public @ResponseBody List<PdWeight> findPdWeight(BaseParam param) {
        return this.productLogic.findPdWeight(param);
    }

    /**
     * 查看产品品种图片信息
     *
     * @param sl241116Bean 参数
     * @param model model
     * @return 结果
     */
    @RequestMapping(value = "showImage",
        method = RequestMethod.POST)
    public String showImage(SL241116Bean sl241116Bean, Model model) {
        model.addAttribute("sl241116Bean", sl241116Bean);
        return "sl/SL24111601";
    }

    /**
     * 保存卖家产品状态
     *
     * @param slProduct 参数
     */
    @RequestMapping(value = "upSlPdStatus",
        method = RequestMethod.POST)
    public @ResponseBody String upSlPdStatus(SL241116Bean slProduct) {
        BasePageParam param = new BasePageParam();
        param.setPaging(false);
        param.setFilter("slCode", slProduct.getSlCode());
        param.getFilterMap().put("slPdId", slProduct.getSlPdId());
        param.setFilter("machiningCode", slProduct.getMachiningCode());
        if ("4".equals(slProduct.getStatus()) || "5".equals(slProduct.getStatus())) {
            // 全部同意判断其他标准是否符合要求
            param.setFilter("auditStatus", "1");
            String checkRs = sl241117Logic.checkAgree(param);
            if (!"1".equals(checkRs)) {
                return checkRs;
            }
        }
        SlProduct product = slProduct;
        // product.setUpdId(this.getLoginUser().getUpdId());
        this.sl241116Logic.upSlPdStatus(product);
        return "1";
    }

    /**
     * 保存卖家产品信息
     *
     * @param slProduct 参数
     * @return 卖家产品页面
     */
    @RequestMapping(value = "save",
        method = RequestMethod.POST)
    public void save(Model model, SL241116Bean slProduct, HttpServletRequest request, HttpServletResponse response)
        throws IOException {
        String concatInfo = slProduct.getConcatInfo();
        slProduct.setCrtTime(DateTimeUtil.getCustomerDate());
        slProduct.setUpdTime(DateTimeUtil.getCustomerDate());
        String[] concatInfos = concatInfo.split("-");
        if (concatInfos.length <= 1) {
            throw new BusinessException("品牌不能为空!");
        }
        if (slProduct.getProdEpId() == null || slProduct.getProdEpId() <= 0) {
            throw new BusinessException("生产商不能为空");
        }
        // Modify by zhang_jiaxing start from 2016/9/5
        if (StringUtil.isEmpty(slProduct.getPdClassesCode())) {
            throw new BusinessException("产品必须选择类别");
        }

        if (StringUtil.isEmpty(slProduct.getMachiningCode())) {
            throw new BusinessException("产品必须选择二级分类");
        }

        if (StringUtil.isEmpty(slProduct.getPdBreedCode())) {
            throw new BusinessException("产品必须选择到品种");
        }

        if (StringUtil.isEmpty(slProduct.getPdFeatureCode())) {
            throw new BusinessException("产品必须选择到特征");
        }

        if (StringUtil.isEmpty(slProduct.getWeightCode())) {
            throw new BusinessException("产品必须选择到净重");
        }
        // Modify by zhang_jiaxing end in 2016/9/5
        slProduct.setBrandEpId(Integer.parseInt(concatInfos[1]));
        slProduct.setBrandId(Integer.parseInt(concatInfos[0]));
        //slProduct.setSlTncGradeCode(2);
        // slProduct.setDistFlg("1");
        // 初始状态为申请中
        slProduct.setStatus(CodeMasterConst.slProductStatus.SQZ);
        BasePageParam basePageParam = new BasePageParam();
        basePageParam.setPaging(false);
        basePageParam.setFilter("slCode", slProduct.getSlCode());
        basePageParam.setFilter("prodEpId", StringUtil.toSafeString(slProduct.getProdEpId()));
        basePageParam.setFilter("brandEpId", StringUtil.toSafeString(slProduct.getBrandEpId()));
        basePageParam.setFilter("brandId", StringUtil.toSafeString(slProduct.getBrandId()));
        basePageParam.setFilter("pdClassesCode", slProduct.getPdClassesCode());
        basePageParam.setFilter("machiningCode", slProduct.getMachiningCode());
        basePageParam.setFilter("pdBreedCode", slProduct.getPdBreedCode());
        basePageParam.setFilter("pdFeatureCode", slProduct.getPdFeatureCode());
        basePageParam.setFilter("weightCode", slProduct.getWeightCode());
        List<SL241116Bean> sl241116Beans = this.sl241116Logic.findPageResultList(basePageParam);
        if (!CollectionUtils.isEmpty(sl241116Beans)) {
            throw new BusinessException("该产品已经存在！");
        } else {
            Map<String, Map<String, String>> map = this.mappingCodeToName(slProduct);
            Map<String, String> classesMap = map.get("classes");
            Map<String, String> machiningMap = map.get("machining");
            Map<String, String> breedMap = map.get("breed");
            Map<String, String> featureMap = map.get("feature");
            Map<String, String> weightMap = map.get("weight");
            int maxId = commonLogic.maxId("SL_PRODUCT", "SL_PD_ID").intValue();
            slProduct.setSlPdId(maxId);
            slProduct.setPdClassesName(classesMap.get(slProduct.getPdClassesCode()));
            slProduct.setMachiningName(machiningMap.get(slProduct.getPdClassesCode() + slProduct.getMachiningCode()));
            slProduct.setPdBreedName(
                breedMap.get(slProduct.getPdClassesCode() + slProduct.getMachiningCode() + slProduct.getPdBreedCode()));
            slProduct.setPdFeatureName(featureMap.get(slProduct.getPdClassesCode() + slProduct.getMachiningCode()
                    + slProduct.getPdBreedCode() + slProduct.getPdFeatureCode()));
            slProduct.setWeightName(weightMap.get(slProduct.getPdClassesCode() + slProduct.getMachiningCode()
                    + slProduct.getPdBreedCode() + slProduct.getPdFeatureCode() + slProduct.getWeightCode()));
            // slProduct.setCrtId(this.getLoginUser().getCrtId());
            if (!StringUtils.hasText(slProduct.getDistFlg())) {
                slProduct.setDistFlg("0"); // 0：否
            }
            if (!StringUtils.hasText(slProduct.getDistmskFlg())) {
                slProduct.setDistmskFlg("0"); // 0：否
            }
            slProduct.setCrtTime(DateTimeUtil.getCustomerDate());
            slProduct.setCrtId(getLoginUser().getEmplId());
            this.sl241116Logic.save(slProduct);

            // 新增一条 PD_FEATURE_CODE = '00' 的数据
            basePageParam.setFilter("pdFeatureCode", "00");
            basePageParam.setFilter("weightCode", "");
            int count = sl241116Logic.getPageCount(basePageParam);
            if (count == 0) {
                int newMaxId = commonLogic.maxId("SL_PRODUCT", "SL_PD_ID").intValue();
                slProduct.setSlPdId(newMaxId);
                slProduct.setPdFeatureCode("00");
                this.sl241116Logic.save(slProduct);
            }
        }
        // Modified end.
        String uploadFilePath = BusinessConst.SLPath.SERVICEIMAGEPATH + "/" + BusinessConst.SLPath.SLIMAGEPATH + "/"
                + slProduct.getProdEpId() + "/" + BusinessConst.SLPath.PD + "/" + slProduct.getPdClassesCode() + "/"
                + slProduct.getMachiningCode() + "/" + slProduct.getPdBreedCode() + '/';
        /** 保存盘装图片 */
        if (slProduct.getLabFile1().getSize() != 0) {
            String uploadFileName = BusinessConst.SLPath.INTRAYFIGURE;
            SLUploadFile slUploadFile = new SLUploadFile();
            slUploadFile.saveUploadFile(slProduct.getLabFile1(), uploadFilePath, uploadFileName);
        }
        /** 保存内袋图片 */

        if (slProduct.getLabFile2().getSize() != 0) {
            String uploadFileName = BusinessConst.SLPath.INSIDEOFFIGURE;
            SLUploadFile slUploadFile = new SLUploadFile();
            slUploadFile.saveUploadFile(slProduct.getLabFile2(), uploadFilePath, uploadFileName);
        }
        /** 保存外箱开箱图片 */
        if (slProduct.getLabFile3().getSize() != 0) {
            String uploadFileName = BusinessConst.SLPath.OUTSIDEBOXFIGURE;
            SLUploadFile slUploadFile = new SLUploadFile();
            slUploadFile.saveUploadFile(slProduct.getLabFile3(), uploadFilePath, uploadFileName);
        }
        /** 保存外箱外观图片 */
        if (slProduct.getLabFile4().getSize() != 0) {
            String uploadFileName = BusinessConst.SLPath.CARTONAPPEARANCEFIGURE;
            SLUploadFile slUploadFile = new SLUploadFile();
            slUploadFile.saveUploadFile(slProduct.getLabFile4(), uploadFilePath, uploadFileName);
        }
        super.callBack(null, "保存成功", response);
    }

    /**
     * 根据代码，批量查询名称。
     * Created by xia_xiaojie on 2016/6/27.
     */
    private Map<String, Map<String, String>> mappingCodeToName(SL241116Bean slProduct) {
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
                    featureMap.put(p.getClassesCode() + p.getMachiningCode() + p.getBreedCode() + p.getFeatureCode(),
                        p.getFeatureName());
                }
            }

            List<PDInfoResult> weightList = pdMap.get("weight");
            if (!CollectionUtils.isEmpty(weightList)) {
                for (PDInfoResult p : weightList) {
                    weightMap.put(p.getClassesCode() + p.getMachiningCode() + p.getBreedCode() + p.getFeatureCode()
                            + p.getWeightCode(),
                        p.getWeightName());
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

    /**
     * 查询审批审核列表
     *
     * @param basePageParam basePageParam
     * @return 信息
     * @author gyh
     */
    @RequestMapping(value = "search/{slCode}",
        method = RequestMethod.POST)
    public @ResponseBody PageResult<SL241116Bean> search(BasePageParam basePageParam,
        @PathVariable(value = "slCode") String slCode) {
        basePageParam.setFilter("slCode", slCode);
        DbUtils.buildLikeCondition(basePageParam, "slPdArtNo", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(basePageParam, "prodEpName", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(basePageParam, "brandName", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(basePageParam, "pdClassesName", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(basePageParam, "machiningName", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(basePageParam, "pdBreedName", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(basePageParam, "pdFeatureName", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(basePageParam, "weightName", DbUtils.LikeMode.PARTIAL);
        basePageParam.setFilter("searchArtno", "1");
        String statusName = StringUtil.toSafeString(basePageParam.getFilterMap().get("status"));
        if (!StringUtil.isNullOrEmpty(statusName)) {
            String[] statusNames = statusName.split(",");
            basePageParam.getFilterMap().put("statusNames", statusNames);
        }
        return this.sl241116Logic.findPageResult(basePageParam);
    }

}
