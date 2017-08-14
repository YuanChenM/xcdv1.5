package com.msk.product.controller;

import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.utils.DbUtils;
import com.msk.common.base.BaseController;
import com.msk.core.entity.PdClassestree;
import com.msk.product.bean.PD142103Bean;
import com.msk.product.logic.PD141126Logic;
import com.msk.product.logic.PD142103Logic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 正式上线添加controller
 *
 * @author pxg
 */
@Controller
@RequestMapping(value = "PD142103")
public class PD142103Controller extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(PD142103Controller.class);
    @Autowired
    private PD142103Logic pd142103Logic;
    @Autowired
    private PD141126Logic pd141126Logic;
    /**
     * 实例化页面
     * @return 界面
     * @author pxg
     */
    @RequestMapping(value = "init",
            method = RequestMethod.POST)
    public String init(Model model,BaseParam param) {
        List<PdClassestree> listOne = pd141126Logic.queryOneClassify();
        model.addAttribute("listOne", listOne);
        return "pd/PD142103";
    }

    @RequestMapping(value = "search",
            method = RequestMethod.POST)
    public @ResponseBody
    PageResult<PD142103Bean> search(BasePageParam pageParam) {
        DbUtils.buildLikeCondition(pageParam, "machiningName", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(pageParam, "breedName", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(pageParam, "slLicAddr", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(pageParam, "slName", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(pageParam, "sllfAddr", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(pageParam, "sllfName", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(pageParam, "brandName", DbUtils.LikeMode.FRONT);
        return pd142103Logic.pageResult(pageParam);
    }

    @RequestMapping(value = "queryTwoClass",
            method = RequestMethod.POST)
    public @ResponseBody List<PdClassestree> search(String classCode) {
        List<PdClassestree> list=pd141126Logic.queryTwoClassify("2", classCode);
        return list;
    }
}
