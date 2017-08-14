package com.msk.product.controller;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.msk.common.base.BaseController;
import com.msk.core.entity.PdBreed;
import com.msk.core.entity.PdClasses;
import com.msk.core.entity.PdFeature;
import com.msk.product.logic.PD141102Logic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

/**
 * 
 * 产品类别维护.
 *
 * @author gyh
 */
@Controller
@RequestMapping("PD141102")
public class PD141102Controller extends BaseController {
    @Autowired
    private PD141102Logic pd141102Logic;

    /**
     * 实例化页面
     * 
     * @param pk 参数
     * @param model 参数
     * @return 类别维护页面
     */
    @RequestMapping(value = "init",
        method = RequestMethod.POST)
    public String init(@RequestParam(required = false,
        name = "pk") String pk, Model model
            ) {
        PdClasses pdClasses = new PdClasses();
        pdClasses.setClassesCode("add");
        String editModel = "edit";
        String showList = "visible";
        boolean readonlyModel = true;
        if (!StringUtil.isEmpty(pk)) {
            BaseParam param = new BaseParam();
            param.setFilter("classesCode", pk);
            pdClasses = pd141102Logic.findOne(param);
        } else {
            editModel = "add";
            readonlyModel = false;
            showList = "hidden";
        }
        model.addAttribute("editModel", editModel);
        model.addAttribute("pdClasses", pdClasses);
        model.addAttribute("readonlyModel", readonlyModel);
        model.addAttribute("showList", showList);
        return "pd/PD141102";
    }

    /**
     * 保存产品类别信息
     * 
     * @param editModel
     * @param pdClasses
     * @return 页面
     */
    @RequestMapping(value = "{editModel}",
        method = RequestMethod.POST)
    public String save(@PathVariable("editModel") String editModel, PdClasses pdClasses, Model model) {
        BaseParam param = new BaseParam();
        param.setFilter("classesName", pdClasses.getClassesName());
        /**Add: 横展开添加共通设置 2016/09/09   BY  任强  Start */
        super.setCommonParam(pdClasses);
        Date date = DateTimeUtil.getCustomerDate();
        pdClasses.setCrtTime(date);
        pdClasses.setUpdTime(date);
        /**Add: 横展开添加共通设置 2016/09/09   BY  任强  End */
        super.setCommonParam(pdClasses);
        PdClasses pdClasses2 = pd141102Logic.findByName(param);
        if (pdClasses2 != null && !pdClasses2.getClassesCode().equals("")) {
            throw new BusinessException("该类别名称已经存在，请重新输入！");
        }
        if ("add".equals(editModel)) {
            BaseParam param1 = new BaseParam();
            PdClasses maxInfo = pd141102Logic.findMaxCode(param1);
            if (maxInfo == null || "".equals(maxInfo.getClassesCode())) {
                maxInfo = new PdClasses();
                maxInfo.setClassesCode("00");
            }
            Integer maxNo = Integer.parseInt(maxInfo.getClassesCode());
            if (maxNo >= NumberConst.IntDef.INT_NINETYNINE) {
                throw new BusinessException("类别已达到最大限度，请联系管理员！");
            }
            pdClasses.setClassesCode(String.format("%02d", maxNo + 1));
            /**Add: 创建时间，修改时间，生效时间横展开修改sysdate 2016/09/06   BY  杨春艳  Start */
            pdClasses.setCrtTime(new Date());
            /**Add: 创建时间，修改时间，生效时间横展开修改sysdate 2016/09/06   BY  杨春艳  End */
            pd141102Logic.save(pdClasses);
        } else {
            pdClasses.setUpdTime(new Date());
            /**Add: 创建时间，修改时间，生效时间横展开修改sysdate 2016/09/06   BY  杨春艳  Start */
            pd141102Logic.modify(pdClasses);
            /**Add: 创建时间，修改时间，生效时间横展开修改sysdate 2016/09/06   BY  杨春艳  End */
        }
        model.addAttribute("pdFeature", new PdFeature());
        return "pd/PD141101";
    }

    /**
     * 废除和删除产品品种
     * 
     * @param pdBreed1 对象
     * @param editType  修改操作
     * @param model 页面模型
     * @return 页面
     */
    @Transactional(readOnly = false,
            propagation = Propagation.REQUIRED,
            rollbackFor = Exception.class)
    @RequestMapping(value = "init/{editType}",
        method = RequestMethod.POST)
    public String init(PdBreed pdBreed1, @PathVariable("editType") String editType, Model model) {
        /**Add: 横展开添加共通设置 2016/09/09   BY  任强  Start */
        super.setCommonParam(pdBreed1);
        Date date = DateTimeUtil.getCustomerDate();
        pdBreed1.setActTime(date);
        pdBreed1.setUpdTime(date);
        /**Add: 横展开添加共通设置 2016/09/09   BY  任强  End */
        if ("close".equals(editType)) {
            int rs = this.pd141102Logic.modifyFlgByCode(pdBreed1);
            if (rs <= 0) {
                throw new BusinessException("废除失败，请重新操作！");
            }
        } else if ("delete".equals(editType)) {
            int rs = this.pd141102Logic.deleteBreed(pdBreed1);
            if (rs <= 0) {
                throw new BusinessException("删除失败，请重新操作！");
            }
        }
        model.addAttribute("pdFeature", new PdFeature());
        return "pd/PD141101";
    }

}
