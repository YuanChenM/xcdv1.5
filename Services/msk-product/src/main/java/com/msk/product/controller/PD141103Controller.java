package com.msk.product.controller;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.StringUtil;
import com.msk.common.base.BaseController;
import com.msk.core.entity.PdBreed;
import com.msk.core.entity.PdClasses;
import com.msk.core.entity.PdClassestree;
import com.msk.core.entity.PdFeature;
import com.msk.product.bean.PD141103Param;
import com.msk.product.logic.PD141103Logic;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * 产品品种维护
 *
 * @author gyh
 */
@Controller
@RequestMapping("PD141103")
public class PD141103Controller extends BaseController {
    @Autowired
    private PD141103Logic pd141103Logic;

    /**
     * 实例化页面
     *
     * @param classesCode 参数
     * @param breedCode   参数
     * @param model       参数
     * @return 页面
     */
    @RequestMapping(value = "init",
            method = RequestMethod.POST)
    public String init(@RequestParam(required = false,
            name = "classesCode") String classesCode,
                       @RequestParam(required = false,
                               name = "breedCode") String breedCode,
                       @RequestParam(required = false,
                               name = "classestreeCode") String classestreeCode,
                       Model model) {
        PdBreed pdBreed = new PdBreed();
        pdBreed.setClassesCode(classesCode);
        pdBreed.setBreedCode("add");
        String editModel = "edit";
        boolean readonlyModel = true;
        PD141103Param pd141103ParamBean = new PD141103Param();
        PdClassestree treeBean = null;
        PdClassestree treeBean2 = null;
        PdClassestree treeBean3 = null;
        PdClassestree treeBean4 = null;
        PdClassestree treeBean5 = null;
        if (!StringUtil.isEmpty(breedCode)) {
            BaseParam param = new BaseParam();
            param.setFilter("classesCode", classesCode);
            param.setFilter("breedCode", breedCode);
            pdBreed = pd141103Logic.findOne(param);
            if (pdBreed.getClassesCode() == null || pdBreed.getBreedCode() == null) {
                throw new BusinessException("没有查询到该条数据!");
            }
            //数据回显
            BaseParam param2 = new BaseParam();
            if (StringUtils.isNotBlank(classestreeCode)) {
                param2.setFilter("classestreeCode", classestreeCode);
                treeBean = this.pd141103Logic.findOneTreeBean(param2);
                if (StringUtils.isNotBlank(treeBean.getParentCode())) {
                    param2.setFilter("classestreeCode", treeBean.getParentCode());
                    treeBean2 = this.pd141103Logic.findOneTreeBean(param2);
                    if (StringUtils.isNotBlank(treeBean2.getParentCode())) {
                        param2.setFilter("classestreeCode", treeBean2.getParentCode());
                        treeBean3 = this.pd141103Logic.findOneTreeBean(param2);
                        if (StringUtils.isNotBlank(treeBean3.getParentCode())) {
                            param2.setFilter("classestreeCode", treeBean3.getParentCode());
                            treeBean4 = this.pd141103Logic.findOneTreeBean(param2);
                            if (StringUtils.isNotBlank(treeBean4.getParentCode())) {
                                param2.setFilter("classestreeCode", treeBean4.getParentCode());
                                treeBean5 = this.pd141103Logic.findOneTreeBean(param2);
                            }
                        }
                    }
                }
            }
            if (treeBean5 != null) {
                pd141103ParamBean.setClassestreeCode1(treeBean5.getClassestreeCode());
                pd141103ParamBean.setClassestreeCode2(treeBean4.getClassestreeCode());
                pd141103ParamBean.setClassestreeCode3(treeBean3.getClassestreeCode());
                pd141103ParamBean.setClassestreeCode4(treeBean2.getClassestreeCode());
                pd141103ParamBean.setClassestreeCode5(treeBean.getClassestreeCode());

                pd141103ParamBean.setClassestreeName1(treeBean5.getLevelName());
                pd141103ParamBean.setClassestreeName2(treeBean4.getLevelName());
                pd141103ParamBean.setClassestreeName3(treeBean3.getLevelName());
                pd141103ParamBean.setClassestreeName4(treeBean2.getLevelName());
                pd141103ParamBean.setClassestreeName5(treeBean.getLevelName());

                model.addAttribute("classesName", treeBean5.getLevelName());


            } else if (treeBean4 != null) {
                pd141103ParamBean.setClassestreeCode1(treeBean4.getClassestreeCode());
                pd141103ParamBean.setClassestreeCode2(treeBean3.getClassestreeCode());
                pd141103ParamBean.setClassestreeCode3(treeBean2.getClassestreeCode());
                pd141103ParamBean.setClassestreeCode4(treeBean.getClassestreeCode());

                pd141103ParamBean.setClassestreeName1(treeBean4.getLevelName());
                pd141103ParamBean.setClassestreeName2(treeBean3.getLevelName());
                pd141103ParamBean.setClassestreeName3(treeBean2.getLevelName());
                pd141103ParamBean.setClassestreeName4(treeBean.getLevelName());

                model.addAttribute("classesName", treeBean4.getLevelName());
            } else if (treeBean3 != null) {
                pd141103ParamBean.setClassestreeCode1(treeBean3.getClassestreeCode());
                pd141103ParamBean.setClassestreeCode2(treeBean2.getClassestreeCode());
                pd141103ParamBean.setClassestreeCode3(treeBean.getClassestreeCode());

                pd141103ParamBean.setClassestreeName1(treeBean3.getLevelName());
                pd141103ParamBean.setClassestreeName2(treeBean2.getLevelName());
                pd141103ParamBean.setClassestreeName3(treeBean.getLevelName());

                model.addAttribute("classesName", treeBean3.getLevelName());
            } else if (treeBean2 != null) {
                pd141103ParamBean.setClassestreeCode1(treeBean2.getClassestreeCode());
                pd141103ParamBean.setClassestreeCode2(treeBean.getClassestreeCode());

                pd141103ParamBean.setClassestreeName1(treeBean2.getLevelName());
                pd141103ParamBean.setClassestreeName2(treeBean.getLevelName());

                model.addAttribute("classesName", treeBean2.getLevelName());
            } else if (treeBean != null) {
                pd141103ParamBean.setClassestreeCode1(treeBean.getClassestreeCode());
                pd141103ParamBean.setClassestreeName1(treeBean.getLevelName());

                model.addAttribute("classesName", treeBean.getLevelName());
            }
            model.addAttribute("pd141103ParamBean", pd141103ParamBean);
        } else {
            //查询类别信息
            BaseParam param = new BaseParam();
            param.setFilter("classesCode", classesCode);
            PdClasses classesBean = this.pd141103Logic.findOneClasses(param);
            editModel = "add";
            readonlyModel = false;
            model.addAttribute("classesName", classesBean.getClassesName());
            //查询二级类别
            List<PdClassestree> treeList = this.pd141103Logic.findClassesTreeList(param);
            if (treeList != null && treeList.size() > NumberConst.IntDef.INT_ZERO) {
                model.addAttribute("tree2List", treeList);
            }
        }

        model.addAttribute("breedCode", breedCode);
        model.addAttribute("classesCode", classesCode);
        model.addAttribute("editModel", editModel);
        model.addAttribute("pdBreed", pdBreed);
        model.addAttribute("readonlyModel", readonlyModel);
        return "pd/PD141103";
    }

    /**
     * 保存产品品种信息
     *
     * @param editModel
     * @param pdBreed
     * @return 页面
     */
    @RequestMapping(value = "{editModel}",
            method = RequestMethod.POST)
    public String save(@PathVariable("editModel") String editModel, PdBreed pdBreed, Model model, PD141103Param beans) {
        String classestreeCode = null;
        if (beans != null) {
            if (beans.getClassestreeCode1() != null && beans.getClassestreeCode1() != "")
                classestreeCode = beans.getClassestreeCode1();
            if (beans.getClassestreeCode2() != null && beans.getClassestreeCode2() != "")
                classestreeCode = beans.getClassestreeCode2();
            if (beans.getClassestreeCode3() != null && beans.getClassestreeCode3() != "")
                classestreeCode = beans.getClassestreeCode3();
            if (beans.getClassestreeCode4() != null && beans.getClassestreeCode4() != "")
                classestreeCode = beans.getClassestreeCode4();
            if (beans.getClassestreeCode5() != null && beans.getClassestreeCode5() != "")
                classestreeCode = beans.getClassestreeCode5();
        }

        /**Add: 横展开添加共通设置 2016/09/09   BY  任强  Start */
        super.setCommonParam(pdBreed);
        /**Add: 横展开添加共通设置 2016/09/09   BY  任强  End */
        BaseParam param = new BaseParam();
        param.setFilter("breedName", pdBreed.getBreedName());
        param.setFilter("classesCode", pdBreed.getClassesCode());
        param.setFilter("classestreeCode", classestreeCode);
        PdBreed pdBreed2 = pd141103Logic.findByName(param);

        if (pdBreed2 != null && !pdBreed2.getBreedCode().equals("")) {
            throw new BusinessException("该品种名称已经存在，请重新输入！");
        }
        if ("add".equals(editModel)) {
            BaseParam param1 = new BaseParam();
            param1.setFilter("classesCode", pdBreed.getClassesCode());
            PdBreed maxInfo = pd141103Logic.findMaxCode(param1);
            if (maxInfo == null || "".equals(maxInfo.getBreedCode())) {
                maxInfo = new PdBreed();
                maxInfo.setBreedCode("00");
            }
            Integer maxNo = Integer.parseInt(maxInfo.getBreedCode());
            if (maxNo >= NumberConst.IntDef.INT_NINETYNINE) {
                throw new BusinessException("产品品种已达到最大限度，请联系管理员！");
            }
            pdBreed.setBreedCode(String.format("%02d", maxNo + 1));
            pdBreed.setClassestreeCode(classestreeCode);
            /**Add: 创建时间，修改时间，生效时间横展开修改sysdate 2016/09/06   BY  杨春艳  Start */
            pdBreed.setCrtTime(new Date());
			/**Add: 创建时间，修改时间，生效时间横展开修改sysdate 2016/09/06   BY  杨春艳  End */
            pd141103Logic.save(pdBreed);
        } else {
            pdBreed.setUpdTime(new Date());
            pd141103Logic.modify(pdBreed);
        }
        model.addAttribute("pdFeature", new PdFeature());
        return "pd/PD141101";
    }

    /**
     * 查找三级类目
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "findListTree",
            method = RequestMethod.POST)
    public
    @ResponseBody
    List<PdClassestree> findListTree(BaseParam param) {
        return this.pd141103Logic.findClassesTreeList(param);
    }

}
