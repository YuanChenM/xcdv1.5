package com.msk.product.controller;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.DateTimeUtil;
import com.msk.common.base.BaseController;
import com.msk.core.entity.PdMctStdItem;
import com.msk.core.entity.PdStandard;
import com.msk.product.bean.PD141124showNameBean;
import com.msk.product.bean.PD141148Bean;
import com.msk.product.bean.PD141148MctProBean;
import com.msk.product.logic.PD141148Logic;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 产品加工技术标准指标controller
 *
 * @author xhy
 */
@Controller
@RequestMapping(value = "pd141148")
public class PD141148Controller extends BaseController {
    @Autowired
    private PD141148Logic pd141148Logic;

    /**
     * 实例化页面
     *
     * @param model model
     * @return 界面
     * @author xhy
     */
    @RequestMapping(value = "init",
            method = RequestMethod.POST)
    public String init(Model model, @RequestParam(value = "classestreeCode", required = false) String classestreeCode,
                       @RequestParam(value = "breedName", required = false) String breedName,
                       @RequestParam(value = "classesCode", required = false) String classesCode,
                       @RequestParam(value = "machiningCode", required = false) String machiningCode,
                       @RequestParam(value = "breedCode", required = false) String breedCode,
                       @RequestParam(value = "featureCode", required = false) String featureCode,
                       @RequestParam(value = "weightCode", required = false) String weightCode) {
        BaseParam param = new BaseParam();
        param.setFilter("classestreeCode", classestreeCode);
        PdStandard standBean = this.pd141148Logic.findOne(param);
        if (standBean == null) {
            throw new BusinessException("数据异常,请联系管理员(standardId找不到)!");
        }
        String standardId = standBean.getStandardId().toString();
        model.addAttribute("standardId", standardId);
        param.setFilter("standardId", standardId);
        //获取产品产品中源 和产品品种显示页面数量 xhy start
        List<PD141124showNameBean> showName= this.pd141148Logic.findBreedNameAndFea(param);
        if(showName.size()> NumberConst.IntDef.INT_ZERO){
            PD141124showNameBean names = new PD141124showNameBean();
            String feaNames  = "";
            for(PD141124showNameBean beans:showName){
                feaNames += beans.getFeatureNames()+"/";
            }
            names.setFeatureNames(feaNames.substring(NumberConst.IntDef.INT_ZERO, feaNames.length() - NumberConst.IntDef.INT_ONE));
            model.addAttribute("feaNames",names.getFeatureNames());
        }
        model.addAttribute("breedName", breedName);
        //end
        List<PD141148Bean> list = this.pd141148Logic.findList(param);
        if (list.size() > NumberConst.IntDef.INT_ZERO) {
            for(PD141148Bean beans:list){
                param.setFilter("mctStdItemId",beans.getMctStdItemId());
                param.setFilter("standardId",standardId);
                List<PD141148MctProBean> mctProList = this.pd141148Logic.findListMctPro(param);
                if(mctProList.size()> NumberConst.IntDef.INT_ZERO){
                    for(PD141148MctProBean mctProBean:mctProList){
                        mctProBean.setFixDateShow(DateTimeUtil.formatDate(DateTimeUtil.FORMAT_DATE_YYYYMMDD, mctProBean.getFixDate()));
                        mctProBean.setRaiseDateShow(DateTimeUtil.formatDate(DateTimeUtil.FORMAT_DATE_YYYYMMDD, mctProBean.getRaiseDate()));
                    }
                    beans.setMctProList(mctProList);
                }
            }
            model.addAttribute("list", list);
        } else {
            List<PdMctStdItem> itemList = this.pd141148Logic.findListPdMctItemStd(param);
            if (itemList.size() > NumberConst.IntDef.INT_ZERO) {
                for (PdMctStdItem beans : itemList) {
                    PD141148Bean stdNull = new PD141148Bean();
                    stdNull.setStandardId(standBean.getStandardId());
                    stdNull.setMctStdItemId(beans.getMctStdItemId());
                    stdNull.setMctStdItemName(beans.getMctStdItemName());
                    list.add(stdNull);
                }
                model.addAttribute("list", list);
            }
        }
        if(StringUtils.isNotBlank(classesCode)) model.addAttribute("classesCode", classesCode);
        if(StringUtils.isNotBlank(machiningCode)) model.addAttribute("machiningCode", machiningCode);
        if(StringUtils.isNotBlank(breedCode)) model.addAttribute("breedCode", breedCode);
        if(StringUtils.isNotBlank(featureCode)) model.addAttribute("featureCode", featureCode);
        if(StringUtils.isNotBlank(weightCode)) model.addAttribute("weightCode", weightCode);
        return "pd/PD141148";
    }

    /**
     * 保存修改数据
     * @param model
     * @param bean
     * @param classesCode
     * @return
     */
    @RequestMapping(value = "saveAndEdit",
            method = RequestMethod.POST)
    public String saveAndEdit(Model model, PD141148Bean bean, @RequestParam(value = "classesCode", required = false) String classesCode,
                              @RequestParam(value = "machiningCode", required = false) String machiningCode,
                              @RequestParam(value = "breedCode", required = false) String breedCode,
                              @RequestParam(value = "featureCode", required = false) String featureCode,
                              @RequestParam(value = "weightCode", required = false) String weightCode) {
        BaseParam param = new BaseParam();
        super.setCommonParam(param);
        this.pd141148Logic.saveAndEdit(bean,param);
        if(StringUtils.isNotBlank(classesCode)) model.addAttribute("classesCode", classesCode);
        if(StringUtils.isNotBlank(machiningCode)) model.addAttribute("machiningCode", machiningCode);
        if(StringUtils.isNotBlank(breedCode)) model.addAttribute("breedCode", breedCode);
        if(StringUtils.isNotBlank(featureCode)) model.addAttribute("featureCode", featureCode);
        if(StringUtils.isNotBlank(weightCode)) model.addAttribute("weightCode", weightCode);
        return "pd/PD141146";
    }
}