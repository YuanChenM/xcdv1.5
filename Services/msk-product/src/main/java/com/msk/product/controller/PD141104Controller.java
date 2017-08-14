package com.msk.product.controller;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.StringUtil;
import com.msk.common.base.BaseController;
import com.msk.common.logic.CommonLogic;
import com.msk.core.entity.PdFeature;
import com.msk.core.entity.PdStandard;
import com.msk.product.logic.PD141104Logic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

/**
 * 
 * 产品特征维护
 *
 * @author gyh
 */
@Controller
@RequestMapping("PD141104")
public class PD141104Controller extends BaseController {
    @Autowired
    private PD141104Logic pd141104Logic;

    @Autowired
    private CommonLogic commonLogic;

    /**
     * 实例化页面
     * 
     * @param classesCode 参数
     * @param breedCode 参数
     * @param featureCode 参数
     * @param model 参数
     * @return 页面
     */
    @RequestMapping(value = "init",
        method = RequestMethod.POST)
    public String init(@RequestParam(required = false,
        name = "classesCode") String classesCode,
        @RequestParam(required = false,
            name = "breedCode") String breedCode,
        @RequestParam(required = false,
            name = "featureCode") String featureCode,
        Model model) {
        PdFeature pdFeature = new PdFeature();
        pdFeature.setClassesCode(classesCode);
        pdFeature.setBreedCode(breedCode);
        pdFeature.setFeatureCode("add");
        String editModel = "edit";
        boolean readonlyModel = true;
        if (!StringUtil.isEmpty(featureCode)) {
            BaseParam param = new BaseParam();
            param.setFilter("classesCode", classesCode);
            param.setFilter("breedCode", breedCode);
            param.setFilter("featureCode", featureCode);
            pdFeature = pd141104Logic.findOne(param);
        } else {
            editModel = "add";
            readonlyModel = false;
        }
        model.addAttribute("classesCode", classesCode);
        model.addAttribute("breedCode", breedCode);
        model.addAttribute("editModel", editModel);
        model.addAttribute("pdFeature", pdFeature);
        model.addAttribute("readonlyModel", readonlyModel);
        return "pd/PD141104";
    }

    /**
     * 保存产品类别信息
     * 
     * @param editModel 参数
     * @param pdFeature 参数
     * @param model 参数
     * @return 页面
     */
    @RequestMapping(value = "{editModel}",
        method = RequestMethod.POST)
    public String save(@PathVariable("editModel") String editModel, PdFeature pdFeature, Model model) {
        /**Add: 横展开添加共通设置 2016/09/09   BY  任强  Start */
        super.setCommonParam(pdFeature);
        /**Add: 横展开添加共通设置 2016/09/09   BY  任强  End */
        BaseParam param = new BaseParam();
        param.setFilter("featureName", pdFeature.getFeatureName());
        param.setFilter("classesCode", pdFeature.getClassesCode());
        param.setFilter("breedCode", pdFeature.getBreedCode());
        PdFeature pdFeature2 = pd141104Logic.findByName(param);
        if (pdFeature2 != null && !pdFeature2.getFeatureCode().equals("")) {
            throw new BusinessException("该特征名称已经存在，请重新输入！");
        }
        if ("add".equals(editModel)) {
            BaseParam param1 = new BaseParam();
            param1.setFilter("classesCode", pdFeature.getClassesCode());
            param1.setFilter("breedCode", pdFeature.getBreedCode());
            PdFeature maxInfo = pd141104Logic.findMaxCode(param1);
            if (maxInfo == null || "".equals(maxInfo.getFeatureCode())) {
                maxInfo = new PdFeature();
                maxInfo.setFeatureCode("00");
            }
            Integer maxNo = Integer.parseInt(maxInfo.getFeatureCode());
            if (maxNo >= NumberConst.IntDef.INT_NINETYNINE) {
                throw new BusinessException("品种特征已达到最大限度，请联系管理员！");
            }
            pdFeature.setFeatureCode(String.format("%02d", maxNo + 1));
			/**Add: 创建时间，修改时间，生效时间横展开修改sysdate 2016/09/06   BY  杨春艳  Start */
            pdFeature.setCrtTime(new Date());
			/**Add: 创建时间，修改时间，生效时间横展开修改sysdate 2016/09/06   BY  杨春艳  End */
            pd141104Logic.save(pdFeature);
            //同时保存标准表中的数据
            Long maxId = this.commonLogic.maxId("PD_STANDARD", "STANDARD_ID");
            String maxS = maxId.toString();
            if (maxId< NumberConst.IntDef.INT_ONE){
                maxS = "1";
            }
            if (maxId>Integer.MAX_VALUE){
                throw new BusinessException("产品标准已达到最大限度，请联系管理员！");
            }
            PdStandard pd = new PdStandard();

            /**Add: 横展开添加共通设置 2016/09/09   BY  任强  Start */
            super.setCommonParam(pd);
            /**Add: 横展开添加共通设置 2016/09/09   BY  任强  End */

            pd.setStandardId(Long.valueOf(maxS));
            pd.setClassesCode(pdFeature.getClassesCode());
            pd.setBreedCode(pdFeature.getBreedCode());
            pd.setFeatureCode(pdFeature.getFeatureCode());
            /**Add: 创建时间，修改时间，生效时间横展开修改sysdate 2016/09/06   BY  杨春艳  Start */
            pd.setCrtTime(new Date());
            pd.setUpdTime(new Date());
            pd.setActTime(new Date());
            /**Add: 创建时间，修改时间，生效时间横展开修改sysdate 2016/09/06   BY  杨春艳  End */
            this.pd141104Logic.savePdStadard(pd);

        } else {
            /**Add: 创建时间，修改时间，生效时间横展开修改sysdate 2016/09/06   BY  杨春艳  Start */
            pdFeature.setUpdTime(new Date());
            /**Add: 创建时间，修改时间，生效时间横展开修改sysdate 2016/09/06   BY  杨春艳  End */
            pd141104Logic.modify(pdFeature);
        }
        model.addAttribute("pdFeature", new PdFeature());
        return "pd/PD141101";
    }

    /**
     * 废除和删除产品品种
     * 
     * @param pdFeature 参数
     * @param model 参数
     * @param editType 路径传入参数
     * @return 页面
     */
    @RequestMapping(value = "init/{editType}",
        method = RequestMethod.POST)
    public String init(PdFeature pdFeature, @PathVariable("editType") String editType, Model model) {
       String classesCode =  pdFeature.getClassesCode();
       String breedCode =  pdFeature.getBreedCode();
       String featureCode = pdFeature.getFeatureCode();
        if ("close".equals(editType)) {
            int rs = this.pd141104Logic.modifyFlgByCode(pdFeature);
            if (rs <= 0) {
                throw new BusinessException("废除失败，请重新操作！");
            }
        } else if ("delete".equals(editType)) {
            //修改原有删除方法(xhy) start

            //查询当前的产品标准id
            int rs =  this.pd141104Logic.deleteFeature(classesCode,breedCode,featureCode);
            if (rs <= 0) {
                throw new BusinessException("删除失败，请重新操作！");
            }
        }
        model.addAttribute("pdFeature", new PdFeature());
        return "pd/PD141101";
    }

}
