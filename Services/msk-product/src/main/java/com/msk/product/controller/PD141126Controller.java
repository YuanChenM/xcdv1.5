package com.msk.product.controller;

import com.hoperun.core.bean.BaseParam;
import com.msk.common.base.BaseController;
import com.msk.core.entity.PdClassestree;
import com.msk.product.logic.PD141126Logic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * 产品分类加工信息controller
 *
 * @author pxg
 */
@Controller
@RequestMapping(value = "PD141126")
public class PD141126Controller extends BaseController {
    @Autowired
    private PD141126Logic pd141126Logic;

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
        /** Modfiy: Bug #2442 : 系统长时间未操作后点击菜单报错系统异常   20160905   BY  杨春艳  Start */
        super.setCommonParam(new BaseParam());
        /** Modfiy: Bug #2442 : 系统长时间未操作后点击菜单报错系统异常   20160905   BY  杨春艳  End */
        List<PdClassestree> listOne = new ArrayList<>();
        listOne = pd141126Logic.queryOneClassify();
        model.addAttribute("listOne", listOne);
        return "pd/PD141126";
    }

    /**
     * 实例化页面
     *
     * @param treeLevel       treeLevel
     * @param classestreeCode classestreeCode
     * @return 界面
     * @author pxg
     */
    @RequestMapping(value = "query", method = RequestMethod.POST)
    public
    @ResponseBody
    List<PdClassestree> queryTwo(String classestreeCode, String treeLevel) {
        super.setCommonParam(new BaseParam());
        List<PdClassestree> listTwo = new ArrayList<>();
        listTwo = pd141126Logic.queryTwoClassify(treeLevel, classestreeCode);
        return listTwo;
    }
}
