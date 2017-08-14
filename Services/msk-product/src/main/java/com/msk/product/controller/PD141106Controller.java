package com.msk.product.controller;

import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.msk.common.base.BaseController;
import com.msk.core.entity.PdBreed;
import com.msk.core.entity.PdClasses;
import com.msk.core.entity.PdStandard;
import com.msk.product.bean.PD141106Param;
import com.msk.product.logic.PD141105Logic;
import com.msk.product.logic.PD141101Logic;
import com.msk.product.logic.PD141102Logic;
import com.msk.product.logic.PD141106Logic;
import com.msk.product.logic.RealityMeasureLogic;
import com.msk.product.logic.StandardLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;
import java.util.List;

/**
 * 实际情况下产品质量数据录入页面
 * @author jiang_nan
 */
@Controller
@RequestMapping("PD141106")
public class PD141106Controller extends BaseController {
    @Autowired
    private StandardLogic standardLogic;
    @Autowired
    private PD141105Logic pd141105Logic;
    @Autowired
    private PD141101Logic PD141101Logic;
    @Autowired
    private PD141102Logic PD141102Logic;
    @Autowired
    private PD141106Logic PD141106Logic;
    @Autowired
    private RealityMeasureLogic realityMeasureLogic;
    /**
     * 初始化操作
     * @param model model
     * @return PD141106
     */
    @RequestMapping(value="init",method=RequestMethod.POST)
    public String init(Model model){
        BasePageParam pageParam = new BasePageParam();
        pageParam.setPaging(Boolean.FALSE);
        List<PdClasses> classesList = PD141101Logic.findPageList(pageParam, PdClasses.class);
        List<PdBreed> breedList = PD141102Logic.findPageList(pageParam, PdBreed.class);
        model.addAttribute("classesList", classesList);
        model.addAttribute("breedList", breedList);
        model.addAttribute("init", true);
        return "pd/PD141106";
    }
//    @RequestMapping(value="init/{pdRltMsrId}",method=RequestMethod.POST)
//    public String init(@PathVariable("pdRltMsrId")String pdRltMsrId,Model model){
//        BaseParam param = new BaseParam();
//        param.setFilter("pdRltMsrId", pdRltMsrId);
//        PdRealityMsr entity = this.realityMeasureLogic.findOne(param);
//        List<PdRealityQualityValue> realityQualityValueList = this.PD141106Logic.findList(param); 
//        model.addAttribute("entity", entity);
//        model.addAttribute("realityQualityValueList", realityQualityValueList);
//        model.addAttribute("readonly", "readonly='readonly'");
//        String classesCode = entity.getClassesCode();
//        String breedCode = entity.getBreedCode();
//        return this.loadStandard(classesCode, breedCode, StringConst.EMPTY, model);
//    }
    /**
     * 加载质量标准数据
     * @param classesCode 类别编码
     * @param breedCode 品种编码
     * @param sellerCode 卖家编码
     * @param model model
     * @return PD141106
     */
    @RequestMapping(value="loadStandard",method=RequestMethod.POST)
    public String loadStandard(String classesCode,String breedCode,String sellerCode,Model model){
        BasePageParam pageParam = new BasePageParam();
        pageParam.setPaging(Boolean.FALSE);
        pageParam.setFilter("classesCode", classesCode);
        List<PdClasses> classesList = PD141101Logic.findPageList(pageParam, PdClasses.class);
        pageParam.setFilter("breedCode", breedCode);
        List<PdBreed> breedList = PD141102Logic.findPageList(pageParam, PdBreed.class);
        PdClasses classes = new PdClasses();
        PdBreed breed = new PdBreed();
        
        if(!CollectionUtils.isEmpty(classesList)){
            classes = classesList.get(NumberConst.IntDef.INT_ZERO);
        }
        if(!CollectionUtils.isEmpty(breedList)){
            breed = breedList.get(NumberConst.IntDef.INT_ZERO);
        }
        PdStandard standard = this.standardLogic.findStandard(classesCode, breedCode, sellerCode);
        //String pdStdId = standard.getPdStdId();
        //List<QualityStandard> qualityStandardList = pd141105Logic.findListByPdStdId(pdStdId);
        String code = DateTimeUtil.formatDate(DateTimeUtil.FORMAT_YYMMDD_HHMMSS, DateTimeUtil.getCustomerDate());
        BaseParam param = new BaseParam();
        param.setFilter("classesCode", classesCode);
        param.setFilter("breedCode", breedCode);
        int count = this.realityMeasureLogic.getPageCount(param);
        boolean isFirst = (count==NumberConst.IntDef.INT_ZERO);
        model.addAttribute("standard", standard);
        //model.addAttribute("qualityStandardList", qualityStandardList);
        model.addAttribute("classes", classes);
        model.addAttribute("breed", breed);
        model.addAttribute("code", code);
        model.addAttribute("init", false);
        model.addAttribute("isFirst", isFirst);
        return "pd/PD141106";
    }
    @RequestMapping(value="save",method=RequestMethod.POST)
    public String save(PD141106Param param,Model model){
        /**Add: 横展开添加共通设置 2016/09/09   BY  任强  Start */
        super.setCommonParam(param);
        Date date = DateTimeUtil.getCustomerDate();
        param.setActTime(date);
        param.setUpdTime(date);
        /**Add: 横展开添加共通设置 2016/09/09   BY  任强  End */
        PD141106Logic.save(param);
        return this.init(model);
    }
    
    
}
