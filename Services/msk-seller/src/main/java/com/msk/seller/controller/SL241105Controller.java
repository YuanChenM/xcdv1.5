package com.msk.seller.controller;

import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.utils.DbUtils;
import com.msk.common.base.BaseController;
import com.msk.common.logic.CommonLogic;
import com.msk.core.entity.PdClassestreeMat;
import com.msk.core.entity.SlPdArtno;
import com.msk.seller.bean.*;
import com.msk.seller.logic.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

/**
 * SL241104Controller
 *
 * @author jiang_nan
 * @version 1.0
 */
@Controller
@RequestMapping("SL241105")
public class SL241105Controller extends BaseController {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(SL241105Controller.class);
    @Autowired
    private SL241105Logic sL241105Logic;
    @Autowired
    private Sl241101Logic sl241101Logic;
    @Autowired
    private SL241117Logic sl241117Logic;
    @Autowired
    private SL241106Logic sl241106Logic;
    @Autowired
    private Sl241132Logic sl241132Logic;
    @Autowired
    private ISL231191RsLogic isl231191Logic;
    @Autowired
    private CommonLogic commonLogic;
    private static String CHECK_TYPE = "check";
    private static String CHECK_RESULT_TYPE = "checkResult";

    /**
     * 初始化操作
     *
     * @param showType showType
     * @param type     type
     * @param model    model
     * @param bean     bean
     * @return SL241105
     * @author gyh
     */
    @RequestMapping(value = "init/{type}/{showType}",
            method = RequestMethod.POST)
    public String init(@PathVariable("type") String type, @PathVariable("showType") String showType, Model model,
                       SL241101Bean bean) {
        logger.debug("SL241105 Init");
        model.addAttribute("type", type);
        model.addAttribute("checkType", CHECK_TYPE);
        model.addAttribute("checkResultType", CHECK_RESULT_TYPE);
        model.addAttribute("isFeature", "0");
        BasePageParam param = new BasePageParam();
        param.setFilter("slCode", bean.getSlCode());
        param.setPaging(false);
        PageResult<SL241101Bean> sl241101Beans = sl241101Logic.findPageResult(param);
        if (!CollectionUtils.isEmpty(sl241101Beans.getData())) {
            model.addAttribute("slName", sl241101Beans.getData().get(0).getSlShowName());
        }
        model.addAttribute("slCode", bean.getSlCode());
        if ("1".equals(showType)) {
            return "sl/SL241104";
        } else if ("2".equals(showType)) {
            return "sl/SL241105";
        }
        return "sl/SL241105";
    }

    /**
     * 初始化操作
     *
     * @param showType  showType
     * @param model     model
     * @param baseparam baseparam
     * @return SL24110501
     * @author gyh
     */
    @RequestMapping(value = "featureInit/{showType}",
            method = RequestMethod.POST)
    public String featureInit(@PathVariable("showType") String showType, Model model, BaseParam baseparam) {
        logger.debug("SL24110501 Init");
        model.addAttribute("isFeature", "1");
        model.addAttribute("slCode", baseparam.getFilterMap().get("slCode"));
        model.addAttribute("classesCode", baseparam.getFilterMap().get("classesCode"));
        model.addAttribute("machiningCode", baseparam.getFilterMap().get("machiningCode"));
        model.addAttribute("breedCode", baseparam.getFilterMap().get("breedCode"));
        model.addAttribute("prodEpId", baseparam.getFilterMap().get("prodEpId"));
        model.addAttribute("brandEpId", baseparam.getFilterMap().get("brandEpId"));
        model.addAttribute("brandId", baseparam.getFilterMap().get("brandId"));
        if ("1".equals(showType)) {
            return "sl/SL24110401";
        }
        return "sl/SL24110501";
    }

    /**
     * 获取卖家产品质量标准信息.
     *
     * @param isFeature 是否查询特征
     * @param param     查询条件
     * @param model     model
     * @return 数据
     */
    @RequestMapping(value = "search/{isFeature}",
            method = RequestMethod.POST)
    public
    @ResponseBody
    PageResult<SL241105Bean> search(@PathVariable("isFeature") String isFeature,
                                    BasePageParam param, Model model) {
        DbUtils.buildLikeCondition(param, "prodEpName", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(param, "brandName", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(param, "productCode", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(param, "classesName", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(param, "breedName", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(param, "machiningName", DbUtils.LikeMode.PARTIAL);

        String slQltGradeCode = StringUtil.toSafeString(param.getFilterMap().get("slQltGradeCode"));
        String qltMonitorResult = StringUtil.toSafeString(param.getFilterMap().get("qltMonitorResult"));
        String slTncGradeCode = StringUtil.toSafeString(param.getFilterMap().get("slTncGradeCode"));
        String tncMonitorResult = StringUtil.toSafeString(param.getFilterMap().get("tncMonitorResult"));

        if (!StringUtil.isNullOrEmpty(slQltGradeCode)) {
            String[] slQltGradeCodes = slQltGradeCode.split(",");
            param.getFilterMap().put("slQltGradeCodes", slQltGradeCodes);
        }
        if (!StringUtil.isNullOrEmpty(qltMonitorResult)) {
            String[] qltMonitorResults = qltMonitorResult.split(",");
            param.getFilterMap().put("qltMonitorResults", qltMonitorResults);
        }
        if (!StringUtil.isNullOrEmpty(slTncGradeCode)) {
            String[] slTncGradeCodes = slTncGradeCode.split(",");
            param.getFilterMap().put("slTncGradeCodes", slTncGradeCodes);
        }
        if (!StringUtil.isNullOrEmpty(tncMonitorResult)) {
            String[] tncMonitorResults = tncMonitorResult.split(",");
            param.getFilterMap().put("tncMonitorResults", tncMonitorResults);
        }
        param.setFilter("pdFeatureCode", isFeature);
        return sL241105Logic.findPageResult(param);
    }


    /**
     * 获得卖家产品加工技术标准
     *
     * @param param param
     * @return 卖家产品加工技术标准信息
     * @author gyh
     */
    @RequestMapping(value = "findSlPdQltStd",
            method = RequestMethod.POST)
    public
    @ResponseBody
    List<SL241118Bean> findSlPdQltStd(BaseParam param) {
        return sL241105Logic.getMctStd(param);
    }

    /**
     * 技术验证确认操作
     *
     * @param param The SL241105 Page Param
     * @param model The Model
     * @return The Init Page
     */
    @RequestMapping(value = "verifyQly/{type}",
            method = RequestMethod.POST)
    public
    @ResponseBody
    String verifyQly(@PathVariable("type") String type, SL241105Bean param, Model model) {
        BasePageParam basePageParam = new BasePageParam();
        basePageParam.setPaging(false);
        basePageParam.setFilter("slCode", param.getSlCode());
        basePageParam.setFilter("classesCode", param.getPdClassesCode());
        basePageParam.setFilter("machiningCode", param.getMachiningCode());
        basePageParam.setFilter("breedCode", param.getPdBreedCode());
        basePageParam.getFilterMap().put("prodEpId", param.getProdEpId());
        basePageParam.getFilterMap().put("brandEpId", param.getBrandEpId());
        basePageParam.getFilterMap().put("brandId", param.getBrandId());
        List<SL241105Bean> beans = sL241105Logic.findPageResultList(basePageParam);
        basePageParam.getFilterMap().put("pdFeatureCode", "00");
        List<SL241105Bean> bean2s = sL241105Logic.findPageResultList(basePageParam);
        Integer slTncGradeCode = bean2s.get(0).getSlTncGradeCode();
        if (param.getSlQltGradeCode() != null) {
            if (param.getSlQltGradeCode() == 2) {
                BasePageParam basePageParam1 = new BasePageParam();
                basePageParam1.setPaging(false);
                for (SL241105Bean bean : beans) {
                    basePageParam1.setFilter("slCode", bean.getSlCode());
                    basePageParam1.getFilterMap().put("slPdId", bean.getSlPdId());
                    basePageParam1.getFilterMap().remove("machiningCode");
                    List<ISL231109RsSlPdPkg> pkgs = sl241106Logic.findSlPdPkgInfo(basePageParam1);
                    basePageParam1.setFilter("machiningCode", bean.getMachiningCode());
                    String checkRs = sl241117Logic.checkAgree(basePageParam1);
                    if (!"1".equals(checkRs)) {
                        throw new BusinessException(checkRs);
                    }
//                    if (!"4".equals(bean.getStatus()) && !"5".equals(bean.getStatus())) {
//                        throw new BusinessException("该品种下的产品标准存在不同意，请卖家在同意后再定级。");
//                    }
                    if (slTncGradeCode == 1 || slTncGradeCode == 2 || slTncGradeCode == 3) {
                        //根据1、类别2、加工类型3、品种4、特征5、产品等级6、包装净重编码7、销售平台8、包装净重编码查询是否存在货号
                        BasePageParam pageParam = new BasePageParam();
                        pageParam.setPaging(false);
                        pageParam.setFilter("slCode", bean.getSlCode());
                        pageParam.setFilter("classesCode", bean.getPdClassesCode());
                        pageParam.setFilter("machiningCode", bean.getMachiningCode());
                        pageParam.setFilter("breedCode", bean.getPdBreedCode());
                        pageParam.setFilter("featureCode", bean.getPdFeatureCode());
                        pageParam.setFilter("gradeCode", StringUtil.toSafeString(bean.getSlTncGradeCode()));
                        pageParam.setFilter("weightCode", bean.getWeightCode());
                        //包装编码
                        if (!CollectionUtils.isEmpty(pkgs) && pkgs.size() > 0) {
                            pageParam.setFilter("normsCode", pkgs.get(0).getPkgCode());
                        }
                        //卖家信息
                        List<SL241101Bean> sl241101BeansList = sl241101Logic.findPageResultList(pageParam);
                        //销售平台

                        if ("1".equals(bean.getDistFlg())) {
                            pageParam.setFilter("salesPlatform", "1");
                            ISL231191Result isl231191Result = isl231191Logic.findOne(pageParam);
                            //该卖家产品明细不存在货号
                            if (null == isl231191Result) {
                                SlPdArtno artno = new SlPdArtno();
                                artno.setArtnoId(this.commonLogic.maxId("SL_PD_ARTNO", "ARTNO_ID"));
                                artno.setSlCodeDis(sl241101BeansList.get(0).getSlCodeDis());
                                artno.setSlCode(sl241101BeansList.get(0).getSlCode());
                                artno.setClassesCode(bean.getPdClassesCode());
                                artno.setMachiningCode(bean.getMachiningCode());
                                artno.setBreedCode(bean.getPdBreedCode());
                                artno.setFeatureCode(bean.getPdFeatureCode());
                                artno.setWeightCode(bean.getWeightCode());
                                artno.setNormsCode(pkgs.get(0).getPkgCode());
                                artno.setGradeCode(StringUtil.toSafeString(bean.getSlTncGradeCode()));
                                artno.setSalePlatform("1");
                                artno.setBrandEpId(StringUtil.toSafeString(bean.getBrandEpId()));
                                artno.setBrandId(StringUtil.toSafeString(bean.getBrandId()));
                                artno.setManufactureCode(sl241101BeansList.get(0).getSlCodeManufacture());
                                artno.setSaleStatus(bean.getStatus());
                                artno.setPdCountry(sl241101BeansList.get(0).getSlConFlg());
                                String classTreeCode = bean.getPdClassesCode() + bean.getMachiningCode() + bean.getPdBreedCode();
                                //根据产品分类原料描述表产品分类CODE查询原料原产地
                                pageParam.setFilter("classesTreeCode", classTreeCode);
                                PdClassestreeMat mat = this.sL241105Logic.findOne(pageParam);
                                artno.setPdPlace(mat.getPlaceCurrent());
                                super.setCommonParam(artno);
                                //判断卖家是否存在货号
                                pageParam.setFilter("maxNo", "1");
                                List<SL241132Bean> sl241132Beans = sl241132Logic.findPageList(pageParam);
                                //货号累加
                                if (!CollectionUtils.isEmpty(sl241132Beans) && sl241132Beans.size() > 0) {
                                    artno.setSlPdArtno(StringUtil.toSafeString(Integer.parseInt("1" + sl241132Beans.get(0).getSlPdArtno()) + 1).substring(1));
                                } else {
                                    //不存在从00001开始
                                    artno.setSlPdArtno("00001");
                                }
                                //保存货号
                                artno.setCrtTime(DateTimeUtil.getCustomerDate());
                                artno.setCrtId(getLoginUser().getEmplId());
                                this.sL241105Logic.save(artno);
                            }
                        }
                        if ("1".equals(bean.getDistmskFlg())) {
                            pageParam.setFilter("salesPlatform", "2");
                            ISL231191Result isl231191Result = isl231191Logic.findOne(pageParam);
                            //该卖家产品明细不存在货号
                            if (null == isl231191Result) {
                                SlPdArtno artno = new SlPdArtno();
                                artno.setArtnoId(this.commonLogic.maxId("SL_PD_ARTNO", "ARTNO_ID"));
                                artno.setSlCodeDis(sl241101BeansList.get(0).getSlCodeDis());
                                artno.setSlCode(sl241101BeansList.get(0).getSlCode());
                                artno.setClassesCode(bean.getPdClassesCode());
                                artno.setMachiningCode(bean.getMachiningCode());
                                artno.setBreedCode(bean.getPdBreedCode());
                                artno.setFeatureCode(bean.getPdFeatureCode());
                                artno.setWeightCode(bean.getWeightCode());
                                artno.setNormsCode(pkgs.get(0).getPkgCode());
                                artno.setGradeCode(StringUtil.toSafeString(bean.getSlTncGradeCode()));
                                artno.setSalePlatform("2");
                                artno.setBrandEpId(StringUtil.toSafeString(bean.getBrandEpId()));
                                artno.setBrandId(StringUtil.toSafeString(bean.getBrandId()));
                                artno.setManufactureCode(sl241101BeansList.get(0).getSlCodeManufacture());
                                artno.setSaleStatus(bean.getStatus());
                                artno.setPdCountry(sl241101BeansList.get(0).getSlConFlg());
                                String classTreeCode = bean.getPdClassesCode() + bean.getMachiningCode() + bean.getPdBreedCode();
                                //根据产品分类原料描述表产品分类CODE查询原料原产地
                                pageParam.setFilter("classesTreeCode", classTreeCode);
                                PdClassestreeMat mat = this.sL241105Logic.findOne(pageParam);
                                artno.setPdPlace(mat.getPlaceCurrent());
                                super.setCommonParam(artno);
                                //判断卖家是否存在货号
                                pageParam.setFilter("maxNo", "1");
                                List<SL241132Bean> sl241132Beans = sl241132Logic.findPageList(pageParam);
                                //货号累加
                                if (!CollectionUtils.isEmpty(sl241132Beans) && sl241132Beans.size() > 0) {
                                    artno.setSlPdArtno(StringUtil.toSafeString(Integer.parseInt("1" + sl241132Beans.get(0).getSlPdArtno()) + 1).substring(1));
                                } else {
                                    //不存在从00001开始
                                    artno.setSlPdArtno("00001");
                                }
                                //保存货号
                                artno.setCrtTime(DateTimeUtil.getCustomerDate());
                                artno.setCrtId(getLoginUser().getEmplId());
                                this.sL241105Logic.save(artno);
                            }
                        }
                    }
                }
            }
        } else {
            throw new BusinessException("产品定级不能为空，请检查后提交。");
        }
        param.setQltAuditStatus(1);
        super.setCommonParam(param);
        param.setUpdId(getLoginUser().getEmplId());
        param.setUpdTime(DateTimeUtil.getCustomerDate());
        if (CHECK_TYPE.equals(type)) {
            if (!param.getSlQltGradeCode().equals(param.getSlQltGradeCodeOld())) {
                this.sL241105Logic.modifySlQlt(param);
            }
        } else {
            if (null == param.getQltMonitorResult()) {
                throw new BusinessException("监控人审核意见不能为空，请检查后提交。");
            }
            if (!param.getSlQltGradeCode().equals(param.getSlQltGradeCodeOld())) {
                this.sL241105Logic.modifySlQlt(param);
            }
            if (!param.getQltMonitorResult().equals(param.getQltMonitorResultOld())) {
                param.setUpdTime(DateTimeUtil.getCustomerDate());
                this.sL241105Logic.modify(param);
            }
        }
        SL241101Bean sl241101Bean = new SL241101Bean();
        sl241101Bean.setSlCode(param.getSlCode());
        return "1";
    }
    /**
     * 卖家产品加工质量审核确认操作
     *
     * @param param The SL241105 Page Param
     * @return The Init Page
     */
    @RequestMapping(value = "verifyTnc/{type}",
            method = RequestMethod.POST)
    public
    @ResponseBody
    String verifyTnc(@PathVariable("type") String type, SL241105Bean param) {
        BasePageParam basePageParam = new BasePageParam();
        basePageParam.setPaging(false);
        basePageParam.setFilter("slCode", param.getSlCode());
        basePageParam.setFilter("classesCode", param.getPdClassesCode());
        basePageParam.setFilter("machiningCode", param.getMachiningCode());
        basePageParam.setFilter("breedCode", param.getPdBreedCode());
        basePageParam.getFilterMap().put("prodEpId", param.getProdEpId());
        basePageParam.getFilterMap().put("brandEpId", param.getBrandEpId());
        basePageParam.getFilterMap().put("brandId", param.getBrandId());
        List<SL241105Bean> beans = sL241105Logic.findPageResultList(basePageParam);
        basePageParam.getFilterMap().put("pdFeatureCode", "00");
        List<SL241105Bean> bean2s = sL241105Logic.findPageResultList(basePageParam);
        Integer slQltGradeCode = bean2s.get(0).getSlQltGradeCode();
        if (param.getSlTncGradeCode() != null) {
            //判断等级是否为A1、A2、A3，是,校验其他标准是否符合
            if (param.getSlTncGradeCode() == 1 || param.getSlTncGradeCode() == 2 || param.getSlTncGradeCode() == 3) {
                BasePageParam basePageParam1 = new BasePageParam();
                basePageParam1.setPaging(false);
                for (SL241105Bean bean : beans) {
                    basePageParam1.setFilter("slCode", bean.getSlCode());
                    basePageParam1.getFilterMap().put("slPdId", bean.getSlPdId());
                    basePageParam1.getFilterMap().remove("machiningCode");
                    List<ISL231109RsSlPdPkg> pkgs = sl241106Logic.findSlPdPkgInfo(basePageParam1);
                    basePageParam1.setFilter("machiningCode", bean.getMachiningCode());
                    String checkRs = sl241117Logic.checkAgree(basePageParam1);
                    if (!"1".equals(checkRs)) {
                        throw new BusinessException(checkRs);
                    }
                    if (slQltGradeCode == 2) {
                        //根据1、类别2、加工类型3、品种4、特征5、产品等级6、包装净重编码7、销售平台8、包装净重编码查询是否存在货号
                        BasePageParam pageParam = new BasePageParam();
                        pageParam.setPaging(false);
                        pageParam.setFilter("slCode", bean.getSlCode());
                        pageParam.setFilter("classesCode", bean.getPdClassesCode());
                        pageParam.setFilter("machiningCode", bean.getMachiningCode());
                        pageParam.setFilter("breedCode", bean.getPdBreedCode());
                        pageParam.setFilter("featureCode", bean.getPdFeatureCode());
                        pageParam.setFilter("gradeCode", StringUtil.toSafeString(param.getSlTncGradeCode()));
                        pageParam.setFilter("weightCode", bean.getWeightCode());
                        //包装编码
                        if (!CollectionUtils.isEmpty(pkgs) && pkgs.size() > 0) {
                            pageParam.setFilter("normsCode", pkgs.get(0).getPkgCode());
                        }
                        //卖家信息
                        List<SL241101Bean> sl241101BeansList = sl241101Logic.findPageResultList(pageParam);
                        //销售平台

                        if ("1".equals(bean.getDistFlg())) {
                            pageParam.setFilter("salesPlatform", "1");
                            ISL231191Result isl231191Result = isl231191Logic.findOne(pageParam);
                            //该卖家产品明细不存在货号
                            if (null == isl231191Result) {
                                SlPdArtno artno = new SlPdArtno();
                                artno.setArtnoId(this.commonLogic.maxId("SL_PD_ARTNO", "ARTNO_ID"));
                                artno.setSlCodeDis(sl241101BeansList.get(0).getSlCodeDis());
                                artno.setSlCode(sl241101BeansList.get(0).getSlCode());
                                artno.setClassesCode(bean.getPdClassesCode());
                                artno.setMachiningCode(bean.getMachiningCode());
                                artno.setBreedCode(bean.getPdBreedCode());
                                artno.setFeatureCode(bean.getPdFeatureCode());
                                artno.setWeightCode(bean.getWeightCode());
                                artno.setNormsCode(pkgs.get(0).getPkgCode());
                                artno.setGradeCode(StringUtil.toSafeString(param.getSlTncGradeCode()));
                                artno.setSalePlatform("1");
                                artno.setBrandEpId(StringUtil.toSafeString(bean.getBrandEpId()));
                                artno.setBrandId(StringUtil.toSafeString(bean.getBrandId()));
                                artno.setManufactureCode(sl241101BeansList.get(0).getSlCodeManufacture());
                                artno.setSaleStatus(bean.getStatus());
                                artno.setPdCountry(sl241101BeansList.get(0).getSlConFlg());
                                String classTreeCode = bean.getPdClassesCode() + bean.getMachiningCode() + bean.getPdBreedCode();
                                //根据产品分类原料描述表产品分类CODE查询原料原产地
                                pageParam.setFilter("classesTreeCode", classTreeCode);
                                PdClassestreeMat mat = this.sL241105Logic.findOne(pageParam);
                                artno.setPdPlace(mat.getPlaceCurrent());
                                super.setCommonParam(artno);
                                //判断卖家是否存在货号
                                pageParam.setFilter("maxNo", "1");
                                List<SL241132Bean> sl241132Beans = sl241132Logic.findPageList(pageParam);
                                //货号累加
                                if (!CollectionUtils.isEmpty(sl241132Beans) && sl241132Beans.size() > 0) {
                                    artno.setSlPdArtno(StringUtil.toSafeString(Integer.parseInt("1" + sl241132Beans.get(0).getSlPdArtno()) + 1).substring(1));
                                } else {
                                    //不存在从00001开始
                                    artno.setSlPdArtno("00001");
                                }
                                //保存货号
                                artno.setCrtTime(DateTimeUtil.getCustomerDate());
                                artno.setCrtId(getLoginUser().getEmplId());
                                this.sL241105Logic.save(artno);
                            }
                        }
                        if ("1".equals(bean.getDistmskFlg())) {
                            pageParam.setFilter("salesPlatform", "2");
                            ISL231191Result isl231191Result = isl231191Logic.findOne(pageParam);
                            //该卖家产品明细不存在货号
                            if (null == isl231191Result) {
                                SlPdArtno artno = new SlPdArtno();
                                artno.setArtnoId(this.commonLogic.maxId("SL_PD_ARTNO", "ARTNO_ID"));
                                artno.setSlCodeDis(sl241101BeansList.get(0).getSlCodeDis());
                                artno.setSlCode(sl241101BeansList.get(0).getSlCode());
                                artno.setClassesCode(bean.getPdClassesCode());
                                artno.setMachiningCode(bean.getMachiningCode());
                                artno.setBreedCode(bean.getPdBreedCode());
                                artno.setFeatureCode(bean.getPdFeatureCode());
                                artno.setWeightCode(bean.getWeightCode());
                                artno.setNormsCode(pkgs.get(0).getPkgCode());
                                artno.setGradeCode(StringUtil.toSafeString(param.getSlTncGradeCode()));
                                artno.setSalePlatform("2");
                                artno.setBrandEpId(StringUtil.toSafeString(bean.getBrandEpId()));
                                artno.setBrandId(StringUtil.toSafeString(bean.getBrandId()));
                                artno.setManufactureCode(sl241101BeansList.get(0).getSlCodeManufacture());
                                artno.setSaleStatus(bean.getStatus());
                                artno.setPdCountry(sl241101BeansList.get(0).getSlConFlg());
                                String classTreeCode = bean.getPdClassesCode() + bean.getMachiningCode() + bean.getPdBreedCode();
                                //根据产品分类原料描述表产品分类CODE查询原料原产地
                                pageParam.setFilter("classesTreeCode", classTreeCode);
                                PdClassestreeMat mat = this.sL241105Logic.findOne(pageParam);
                                artno.setPdPlace(mat.getPlaceCurrent());
                                super.setCommonParam(artno);
                                //判断卖家是否存在货号
                                pageParam.setFilter("maxNo", "1");
                                List<SL241132Bean> sl241132Beans = sl241132Logic.findPageList(pageParam);
                                //货号累加
                                if (!CollectionUtils.isEmpty(sl241132Beans) && sl241132Beans.size() > 0) {
                                    artno.setSlPdArtno(StringUtil.toSafeString(Integer.parseInt("1" + sl241132Beans.get(0).getSlPdArtno()) + 1).substring(1));
                                } else {
                                    //不存在从00001开始
                                    artno.setSlPdArtno("00001");
                                }
                                //保存货号
                                artno.setCrtTime(DateTimeUtil.getCustomerDate());
                                artno.setCrtId(getLoginUser().getEmplId());
                                this.sL241105Logic.save(artno);
                            }
                        }
                    }

                }
            }
        } else {
            throw new BusinessException("产品定级不能为空，请检查后提交。");
        }
        param.setTncAuditStatus(1);
        param.setUpdId(getLoginUser().getEmplId());
        param.setUpdTime(DateTimeUtil.getCustomerDate());
        super.setCommonParam(param);
        if (CHECK_TYPE.equals(type)) {
            if (!param.getSlTncGradeCode().equals(param.getSlTncGradeCodeOld())) {
                this.sL241105Logic.modifySlTncGrade(param);
            }
        } else {
            if (null == param.getTncMonitorResult()) {
                throw new BusinessException("监控人审核意见不能为空，请检查后提交。");
            }
            if (!param.getSlTncGradeCode().equals(param.getSlTncGradeCodeOld())) {
                this.sL241105Logic.modifySlTncGrade(param);
            }
            if (!param.getTncMonitorResult().equals(param.getTncMonitorResultOld())) {
                this.sL241105Logic.modifySlTncMonitor(param);
            }
        }

        SL241101Bean sl241101Bean = new SL241101Bean();
        sl241101Bean.setSlCode(param.getSlCode());
        return "1";
    }
}
