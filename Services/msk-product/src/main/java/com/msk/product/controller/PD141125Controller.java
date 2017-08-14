package com.msk.product.controller;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.msk.common.base.BaseController;
import com.msk.common.logic.CommonLogic;
import com.msk.core.entity.PdClassestree;
import com.msk.core.entity.PdClassestreeMat;
import com.msk.product.bean.PD141124showNameBean;
import com.msk.product.logic.PD141125Logic;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

/**
 * 产品分类加工信息controller
 *
 * @author fjm
 */
@Controller
@RequestMapping(value = "pd141125")
public class PD141125Controller extends BaseController {
    @Autowired
    private PD141125Logic pd141125Logic;

    @Autowired
    private CommonLogic commonLogic;

    /**
     * 实例化页面
     *
     * @param model model
     * @return 界面
     * @author fjm
     */
    @RequestMapping(value = "init",
            method = RequestMethod.POST)
    public String init(Model model, @RequestParam(value = "classestreeCode", required = false) String classestreeCode,
                       @RequestParam(value = "breedName", required = false) String breedName,
                       @RequestParam(value = "machiningName", required = false) String machiningName,
                       @RequestParam(value = "classesCode", required = false) String classesCode,
                       @RequestParam(value = "machiningCode", required = false) String machiningCode,
                       @RequestParam(value = "breedCode", required = false) String breedCode,
                       @RequestParam(value = "featureCode", required = false) String featureCode,
                       @RequestParam(value = "weightCode", required = false) String weightCode) {
        BaseParam param = new BaseParam();
        /** Modfiy: Bug #2442 : 系统长时间未操作后点击菜单报错系统异常   20160905   BY  杨春艳  Start */
        super.setCommonParam(param);
        /** Modfiy: Bug #2442 : 系统长时间未操作后点击菜单报错系统异常   20160905   BY  杨春艳  End */
        param.setFilter("classestreeCode", classestreeCode);
        PdClassestreeMat pd = this.pd141125Logic.findOne(param);
        if (StringUtils.isNotBlank(machiningName)) {
            model.addAttribute("machiningName", machiningName);
        } else {
            List<PD141124showNameBean> showName = this.pd141125Logic.findBreedNameAndFea(param);
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
        }
        if (null == pd || pd.equals(null)) {
            PdClassestreeMat s = new PdClassestreeMat();
            s.setClassestreeCode(classestreeCode);
            model.addAttribute("pd", s);
            if (StringUtils.isNotBlank(classesCode)) model.addAttribute("classesCode", classesCode);
            if(StringUtils.isNotBlank(machiningCode)) model.addAttribute("machiningCode", machiningCode);
            if(StringUtils.isNotBlank(breedCode)) model.addAttribute("breedCode", breedCode);
            if(StringUtils.isNotBlank(featureCode)) model.addAttribute("featureCode", featureCode);
            if(StringUtils.isNotBlank(weightCode)) model.addAttribute("weightCode", weightCode);
            return "pd/PD141125";
        }
        if (StringUtils.isNotBlank(classesCode)) model.addAttribute("classesCode", classesCode);
        if(StringUtils.isNotBlank(machiningCode)) model.addAttribute("machiningCode", machiningCode);
        if(StringUtils.isNotBlank(breedCode)) model.addAttribute("breedCode", breedCode);
        if(StringUtils.isNotBlank(featureCode)) model.addAttribute("featureCode", featureCode);
        if(StringUtils.isNotBlank(weightCode)) model.addAttribute("weightCode", weightCode);
        model.addAttribute("pd", pd);
        return "pd/PD141125";
    }


    @RequestMapping(value = "saveAndEdit",
            method = RequestMethod.POST)
    public String saveAndEdit(Model model, PdClassestreeMat bean, @RequestParam(value = "classesCode", required = false) String classesCode,
                              @RequestParam(value = "machiningCode", required = false) String machiningCode,
                              @RequestParam(value = "breedCode", required = false) String breedCode,
                              @RequestParam(value = "featureCode", required = false) String featureCode,
                              @RequestParam(value = "weightCode", required = false) String weightCode) {

        /**Add: 横展开添加共通设置 2016/09/09   BY  任强  Start */
        super.setCommonParam(bean);
        Date date = DateTimeUtil.getCustomerDate();
        bean.setActTime(date);
        bean.setUpdTime(date);
        bean.setActTime(date);
        /**Add: 横展开添加共通设置 2016/09/09   BY  任强  End */
        BaseParam param = new BaseParam();
        param.setFilter("classestreeCode", bean.getClassestreeCode());
        PdClassestreeMat pd = this.pd141125Logic.findOne(param);

        if (null == pd || pd.equals(null)) {
            //新建
            Long matId = this.commonLogic.maxId("pd_classestree_mat", "MAT_ID");
            bean.setMatId(matId);
            //根据classcode查出levelcode

            PdClassestree pdClassestree = this.pd141125Logic.findLevelCode(param);
            bean.setLevelCode(pdClassestree.getLevelCode());
            this.pd141125Logic.save(bean);
        } else {
            //修改
            this.pd141125Logic.modify(bean);
        }
        if (StringUtils.isNotBlank(classesCode)) model.addAttribute("classesCode", classesCode);
        if(StringUtils.isNotBlank(machiningCode)) model.addAttribute("machiningCode", machiningCode);
        if(StringUtils.isNotBlank(breedCode)) model.addAttribute("breedCode", breedCode);
        if(StringUtils.isNotBlank(featureCode)) model.addAttribute("featureCode", featureCode);
        if(StringUtils.isNotBlank(weightCode)) model.addAttribute("weightCode", weightCode);
            return "pd/PD141124";
    }

}
