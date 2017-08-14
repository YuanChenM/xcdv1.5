package com.msk.product.controller;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.exception.BusinessException;
import com.msk.common.base.BaseController;
import com.msk.core.entity.PdStandard;
import com.msk.product.bean.PD141124showNameBean;
import com.msk.product.bean.PD141150Param;
import com.msk.product.bean.PD141150ParentBean;
import com.msk.product.logic.PD141150Logic;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fjm on 2016/3/4.
 */

@Controller
@RequestMapping("pd141150")
public class PD141150Controller extends BaseController {

    @Autowired
    private PD141150Logic pd141150Logic;


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
        //拿树码去查出standardId
        PdStandard standBean = pd141150Logic.findOne(param);
        if (standBean == null) {
            throw new BusinessException("数据异常,请联系管理员(standardId找不到)!");
        }
        String standardId = standBean.getStandardId().toString();
        //拿到标准卡ID，放入页面隐藏域，供保存用
        model.addAttribute("standardId", standardId);
        //获取产品产品中源 和产品品种显示页面数量 xhy start
        List<PD141124showNameBean> showName = this.pd141150Logic.findBreedNameAndFea(param);
        if (showName.size() > NumberConst.IntDef.INT_ZERO) {
            PD141124showNameBean names = new PD141124showNameBean();
            String feaNames = "";
            for (PD141124showNameBean beans : showName) {
                feaNames += beans.getFeatureNames() + "/";
            }
            names.setFeatureNames(feaNames.substring(NumberConst.IntDef.INT_ZERO, feaNames.length() - NumberConst.IntDef.INT_ONE));
            model.addAttribute("feaNames", names.getFeatureNames());
        }
        model.addAttribute("breedName", breedName);
        //end

        param.setFilter("standardId", standardId);
        param.setFilter("levelId", String.valueOf(NumberConst.IntDef.INT_ONE));

        int size = this.pd141150Logic.getCountNum(param);

        //一级目录显示，数据根据查询结果决定
        if (size > NumberConst.IntDef.INT_ZERO) {

            List<PD141150ParentBean> list = this.pd141150Logic.findList(param);

            //有
            for (PD141150ParentBean parentbeans : list) {
                List<PD141150Param> list2 = new ArrayList<PD141150Param>();
                param.setFilter("parentId", parentbeans.getGnqStdItemId());

                List<PD141150Param> level2List = this.pd141150Logic.findListLevel2(param);
                for (PD141150Param level2 : level2List) {
                    PD141150Param pd141150Param = new PD141150Param();
                    param.setFilter("gnqStdItemId", level2.getGnqStdItemId());

                    pd141150Param = this.pd141150Logic.findOneSftStd(param);
                    if(null != pd141150Param){
                        pd141150Param.setGnqStdItemName(level2.getGnqStdItemName());
                        list2.add(pd141150Param);
                    }
                }
                parentbeans.setGnqList(list2);
            }
            model.addAttribute("list", list);

        } else {
            //pd-tsp-std表没有数据，数据显示空
            List<PD141150ParentBean> list = this.pd141150Logic.findList(param);
            for (PD141150ParentBean parentbeans : list) {
                param.setFilter("parentId", parentbeans.getGnqStdItemId());
                List<PD141150Param> level2List = this.pd141150Logic.findListLevel2(param);
                parentbeans.setGnqList(level2List);
            }
            model.addAttribute("list", list);
        }
        if (StringUtils.isNotBlank(classesCode)) model.addAttribute("classesCode", classesCode);
        if(StringUtils.isNotBlank(machiningCode)) model.addAttribute("machiningCode", machiningCode);
        if(StringUtils.isNotBlank(breedCode)) model.addAttribute("breedCode", breedCode);
        if(StringUtils.isNotBlank(featureCode)) model.addAttribute("featureCode", featureCode);
        if(StringUtils.isNotBlank(weightCode)) model.addAttribute("weightCode", weightCode);
        return "pd/PD141150";
    }

    /**
     * 保存修改数据 修改 xhy  16-4-15
     * 修改产品标准表标识
     * @param model
     * @param bean
     * @param classesCode
     * @return
     */
    @RequestMapping(value = "saveAndEdit",
            method = RequestMethod.POST)
    public String saveAndEdit(Model model, PD141150Param bean, @RequestParam(value = "classesCode", required = false) String classesCode) {
        BaseParam param = new BaseParam();
        super.setCommonParam(param);
        this.pd141150Logic.saveAndEdit(bean,param);
        if(StringUtils.isNotBlank(classesCode)) model.addAttribute("classesCode", classesCode);
        return "pd/PD141124";
    }
}
