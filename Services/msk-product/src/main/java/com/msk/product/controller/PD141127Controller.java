package com.msk.product.controller;

import com.msk.common.base.BaseController;
import com.msk.product.bean.PdTcOnlineParam;
import com.msk.product.logic.PD14112601Logic;
import com.msk.product.logic.PD141127Logic;
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
 * 正式上线添加controller
 *
 * @author pxg
 */
@Controller
@RequestMapping(value = "PD141127")
public class PD141127Controller extends BaseController {
    @Autowired
    private PD141127Logic pd141127Logic;

    @Autowired
    private PD14112601Logic pd14112601Logic;

    /**
     * 实例化页面
     *
     * @param getDivId getDivId
     * @return 界面
     * @author pxg
     */
    @RequestMapping(value = "init",
            method = RequestMethod.POST)
    public String init(Model model, @RequestParam(value = "classestreeCode", required = false) String classestreeCode, @RequestParam(value = "getDivId", required = false) String getDivId) {
        model.addAttribute("breedCode", classestreeCode);
        model.addAttribute("getDivId", getDivId);
        return "pd/PD141127";
    }


    /**
     * 保存正式上线数据
     * param
     *
     * @return
     */
    @RequestMapping(value = "save",
            method = RequestMethod.POST)
    public
    @ResponseBody
    List<PdTcOnlineParam> saveOnLine(PdTcOnlineParam pdTcOnlineParam, String getDivId) {
        /**Add: 横展开添加共通设置 2016/09/09   BY  任强  Start */
        super.setCommonParam(pdTcOnlineParam);
        /**Add: 横展开添加共通设置 2016/09/09   BY  任强  End */

        List<PdTcOnlineParam> listOnLine = new ArrayList<>();
        String breedCode = pdTcOnlineParam.getBreedCode();
        int count = pd141127Logic.findOnline(pdTcOnlineParam);
        if (count <= 0) {
            int num = pd141127Logic.saveOnLine(pdTcOnlineParam);
            if (num > 0) {
                listOnLine = pd14112601Logic.queryOnLineData(breedCode, getDivId);
            }
        } else {
            PdTcOnlineParam pdTcOnline = new PdTcOnlineParam();
            pdTcOnline.setMessage("1");
            listOnLine.add(pdTcOnline);
        }
        return listOnLine;
    }
}
