package com.msk.seller.controller;

import com.hoperun.core.bean.BaseParam;
import com.msk.common.base.BaseController;
import com.msk.core.entity.PdClassestreeMat;
import com.msk.product.bean.PDInfoParam;
import com.msk.seller.bean.SL24112801Bean;
import com.msk.seller.bean.SL24112801Param;
import com.msk.seller.logic.SL24112801Logic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

/**
 * 省级卖家池列表controller
 *
 * @author pxg
 */
@Controller
@RequestMapping(value = "SL24112801")
public class SL24112801Controller extends BaseController {
    @Autowired
    private SL24112801Logic sl24112801Logic;

    /**
     * 实例化页面
     *
     * @param model model
     * @return 界面
     * @author pxg
     */
    @RequestMapping(value = "init",
            method = RequestMethod.POST)
    public String init(Model model) {
        BaseParam param = new BaseParam();
        return "sl/SL24112801";
    }

    /**
     * 检索原料描述数据
     *
     * @param classCodeThree classCodeThree
     * @return 界面
     * @author pxg
     */
    @RequestMapping(value = "queryData", method = RequestMethod.POST)
    public String queryData(@RequestParam(value = "classCodeThree", required = false) String classCodeThree, @RequestParam(value = "classCodeTwo", required = false) String classCodeTwo, Model mav) {
        PdClassestreeMat pdClassestreeMat = null;
        List<SL24112801Param> listMat = new ArrayList<>();
        List<SL24112801Bean> sl24112801Beans = new ArrayList<SL24112801Bean>();

        if (null != classCodeThree) {
            pdClassestreeMat = sl24112801Logic.queryClassData(classCodeThree);
            listMat = sl24112801Logic.queryData(classCodeThree, null);
            if (!CollectionUtils.isEmpty(listMat)) {
                sl24112801Beans = sl24112801Logic.querySlProduct(classCodeThree);
            }
        } else {
            pdClassestreeMat = sl24112801Logic.queryClassData(classCodeTwo);
            listMat = sl24112801Logic.queryData(null, classCodeTwo);
            if (!CollectionUtils.isEmpty(listMat)) {
                sl24112801Beans = sl24112801Logic.querySlProduct(classCodeTwo);
            }
        }

        mav.addAttribute("pdClassestreeMat", pdClassestreeMat);
        mav.addAttribute("listMat", listMat);
        mav.addAttribute("sl24112801Beans", sl24112801Beans);
        return "sl/SL24112801";
    }

}
