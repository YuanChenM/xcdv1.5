package com.msk.product.controller;

import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.StringConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.msk.common.base.BaseController;
import com.msk.core.entity.PdBreed;
import com.msk.core.entity.PdClasses;
import com.msk.core.entity.PdRealityMsr;
import com.msk.core.entity.PdStandard;
import com.msk.product.bean.PD141107Bean;
import com.msk.product.bean.PD141108Param;
import com.msk.product.logic.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;
import java.util.List;

/**
 * 实际情况下产品技术数据录入页面
 * @author yuan_chen
 */
@Controller
@RequestMapping("PD141108")
public class PD141108Controller extends BaseController {

    /** logger */
    private static Logger logger = LoggerFactory.getLogger(PD141108Controller.class);

    @Autowired
    private StandardLogic standardLogic;
    @Autowired
    private PD141107Logic pd141107Logic;
    @Autowired
    private PD141101Logic pd141101Logic;
    @Autowired
    private PD141102Logic pd141102Logic;
    @Autowired
    private PD141108Logic pd141108Logic;
    @Autowired
    private RealityMeasureLogic realityMeasureLogic;

    /**
     * 初始化操作
     * @param model model
     * @return PD141108
     */
    @RequestMapping(value = "init",
        method = RequestMethod.POST)
    public String init(Model model) {
        logger.debug("实际产品技术数据录入页面页面初始化");
        BasePageParam pageParam = new BasePageParam();
        pageParam.setPaging(Boolean.FALSE);
        List<PdClasses> classesList = pd141101Logic.findPageList(pageParam, PdClasses.class);
        List<PdBreed> breedList = pd141102Logic.findPageList(pageParam, PdBreed.class);
        model.addAttribute("classesList", classesList);
        model.addAttribute("breedList", breedList);
        model.addAttribute("init", true);
        return "pd/PD141108";
    }

    /**
     * 初始化操作
     * @param pdRltMsrId pdRltMsrId
     * @param model model
     * @return PD141108
     */
    @RequestMapping(value = "init/{pdRltMsrId}",
        method = RequestMethod.POST)
    public String init(@PathVariable("pdRltMsrId") String pdRltMsrId, Model model) {
        logger.debug("实际产品技术数据录入页面页面初始化");

        BaseParam param = new BaseParam();
        param.setFilter("pdRltMsrId", pdRltMsrId);
        PdRealityMsr entity = this.realityMeasureLogic.findOne(param);
        //List<PdRealityTechnical> realityTechnicalList = this.pd141108Logic.findList(param);

        model.addAttribute("entity", entity);
        //model.addAttribute("realityTechnicalList", realityTechnicalList);
        model.addAttribute("readonly", "readonly='readonly'");

        String classesCode = "";//entity.getClassesCode();
        String breedCode = "";//entity.getBreedCode();

        return this.loadStandard(classesCode, breedCode, StringConst.EMPTY, model);
    }

    /**
     * 加载技术标准数据
     * @param classesCode 类别编码
     * @param breedCode 品种编码
     * @param sellerCode 卖家编码
     * @param model model
     * @return PD141108
     */
    @RequestMapping(value = "loadStandard",
        method = RequestMethod.POST)
    public String loadStandard(String classesCode, String breedCode, String sellerCode, Model model) {

        BasePageParam pageParam = new BasePageParam();
        pageParam.setPaging(Boolean.FALSE);
        pageParam.setFilter("classesCode", classesCode);
        List<PdClasses> classesList = pd141101Logic.findPageList(pageParam, PdClasses.class);
        pageParam.setFilter("classesCode", null);
        pageParam.setFilter("breedCode", breedCode);
        List<PdBreed> breedList = pd141102Logic.findPageList(pageParam, PdBreed.class);
        PdClasses classes = new PdClasses();
        PdBreed breed = new PdBreed();

        if (!CollectionUtils.isEmpty(classesList)) {
            classes = classesList.get(NumberConst.IntDef.INT_ZERO);
        }
        if (!CollectionUtils.isEmpty(breedList)) {
            breed = breedList.get(NumberConst.IntDef.INT_ZERO);
        }

        int stdCount = this.standardLogic.getStdCount(classesCode, breedCode, sellerCode);
        if (0 == stdCount) {
            this.standardLogic.save(classesCode, breedCode, sellerCode);
        }
        PdStandard standard = this.standardLogic.findStandard(classesCode, breedCode, sellerCode);
//        Integer pdStdId = standard.getStandardId();
        String code = DateTimeUtil.formatDate(DateTimeUtil.FORMAT_YYMMDD_HHMMSS, DateTimeUtil.getCustomerDate());
        BaseParam param = new BaseParam();
        param.setFilter("classesCode", classesCode);
        param.setFilter("breedCode", breedCode);
        int count = this.realityMeasureLogic.getPageCount(param);
        boolean isFirst = (count == NumberConst.IntDef.INT_ZERO);

        List<PD141107Bean> tscList =pd141107Logic.getTncStd(pageParam);

        model.addAttribute("technicalStdClaList", tscList);
        model.addAttribute("standard", standard);
        model.addAttribute("classes", classes);
        model.addAttribute("breed", breed);
        model.addAttribute("code", code);
        model.addAttribute("init", false);
        model.addAttribute("isFirst", isFirst);
        return "pd/PD141108";
    }

    /**
     * 保存技术标准数据
     * @param param param
     * @param model model
     * @return PD141108
     */
    @RequestMapping(value = "save",
        method = RequestMethod.POST)
    public String save(PD141108Param param, Model model) {
        /**Add: 横展开添加共通设置 2016/09/09   BY  任强  Start */
        super.setCommonParam(param);
        Date date = DateTimeUtil.getCustomerDate();
        param.setActTime(date);
        param.setUpdTime(date);
        /**Add: 横展开添加共通设置 2016/09/09   BY  任强  End */

        pd141108Logic.save(param);
        return "pd/PD141115";
    }

}
