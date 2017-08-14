package com.msk.seller.controller;

import com.msk.common.base.BaseUploadController;
import com.msk.core.entity.PdClassestree;
import com.msk.product.bean.PDInfoResult;
import com.msk.seller.logic.SL241128Logic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * 省级卖家池列表Controller.
 *
 * @author pxg
 */
@Controller
@RequestMapping(value = "SL241128")
public class SL241128Controller extends BaseUploadController {

    @Autowired
    private SL241128Logic sl241128Logic;

    /**
     * 实例化页面
     *
     * @param model model
     * @return 界面
     * @author pxg
     */
    @RequestMapping(value = "init", method = RequestMethod.POST)
    public String init(Model model) {
        List<PDInfoResult> listOne = sl241128Logic.queryClassify(null,"1");
        model.addAttribute("listOne",listOne);
        return "sl/SL241128";
    }

    /**
     * 实例化页面
     *
     * @param treeLevel treeLevel
     * @param classestreeCode classestreeCode
     * @return 界面
     * @author pxg
     */
    @RequestMapping(value = "query", method = RequestMethod.POST)
    public @ResponseBody
    List<PDInfoResult> queryTwo(String classestreeCode,String treeLevel) {
        List<PDInfoResult> listTwo = sl241128Logic.queryClassify(classestreeCode, treeLevel);
        return listTwo;
    }
}
