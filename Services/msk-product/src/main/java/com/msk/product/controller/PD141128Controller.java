package com.msk.product.controller;

import com.msk.common.base.BaseController;
import com.msk.product.bean.PD141128Param;
import com.msk.product.logic.PD14112601Logic;
import com.msk.product.logic.PD141128Logic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * 市场需求审核注册修改添加controller
 *
 * @author pxg
 */
@Controller
@RequestMapping(value = "PD141128")
public class PD141128Controller extends BaseController {
    @Autowired
    private PD141128Logic pd141128Logic;

    @Autowired
    private PD14112601Logic pd14112601Logic;

    /**
     * 实例化页面
     *
     * @param model model
     * @return 界面
     * @author pxg
     */
    @RequestMapping(value = "init",
            method = RequestMethod.POST)
    public String init(Model model, @RequestParam(value = "classestreeCode", required = false) String classestreeCode, @RequestParam(value = "getDivId", required = false) String getDivId) {
        model.addAttribute("breedCode", classestreeCode);
        model.addAttribute("getDivId", getDivId);
        return "pd/PD141128";
    }

    /**
     * 保存数据
     *
     * @return 界面
     * @author pxg
     */
    @RequestMapping(value = "save",
            method = RequestMethod.POST)
    public
    @ResponseBody
    List<PD141128Param> save(PD141128Param pdTcMarket, String getDivId) {
        /**Add: 横展开添加共通设置 2016/09/09   BY  任强  Start */
        super.setCommonParam(pdTcMarket);
        /**Add: 横展开添加共通设置 2016/09/09   BY  任强  End */

        List<PD141128Param> listMat = new ArrayList<>();
        String breedCode = pdTcMarket.getBreedCode();
        int count = pd141128Logic.queryData(pdTcMarket);
        if (count <= 0) {
            int num = pd141128Logic.saveMarket(pdTcMarket);
            if (num > 0) {
                listMat = pd14112601Logic.queryMarketData(breedCode, getDivId);
            }
        } else {
            PD141128Param pd141128Param = new PD141128Param();
            pd141128Param.setMessage("1");
            listMat.add(pd141128Param);
        }
        return listMat;
    }
}
