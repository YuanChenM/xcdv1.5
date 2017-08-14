package com.msk.district.controller;

import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.utils.DbUtils;
import com.msk.common.base.BaseController;
import com.msk.district.bean.AreaBean;
import com.msk.district.logic.AreaLogic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 行政区域管理画面-大区
 * 
 * @author yuan_chen
 *
 */
@Controller
@RequestMapping("BY121101")
public class BY121101Controller extends BaseController {

    /** logger */
    private static Logger logger = LoggerFactory.getLogger(BY121101Controller.class);

    @Autowired
    private AreaLogic logic;

    /**
     * 初始化页面
     *
     * @param model Model
     * @return 页面
     */
    @RequestMapping(value = "init",
        method = RequestMethod.POST)
    public String init(Model model) {
        logger.debug("大区列表初始化");
        return "district/BY121101";
    }

    /**
     * 分页查询数据
     *
     * @param param pageParam
     * @return 分页查询数据
     */
    @RequestMapping(value = "search",
        method = RequestMethod.POST)
    public @ResponseBody
    PageResult<AreaBean> search(BasePageParam param) {
        logger.debug("大区列表列表查询");
        DbUtils.buildLikeCondition(param, "areaCode", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(param, "areaName", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(param, "spell", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(param, "shortSpell", DbUtils.LikeMode.PARTIAL);
        String delFlg = (String) param.getFilterMap().get("delFlg");
        if(!StringUtil.isNullOrEmpty(delFlg)){
            String[] delFlgAry = delFlg.split(",");
            if (delFlgAry.length == NumberConst.IntDef.INT_TWO) {
                param.setFilter("delFlg", null);
            }
        }
        return logic.findPage(param, AreaBean.class);
    }

    /**
     * 修改
     * 
     * @param mdArea mdArea
     * @return 页面
     */
    @RequestMapping(value = "modify",
        method = RequestMethod.POST)
    public String modify(AreaBean mdArea, Model model) {
        mdArea.setUpdId("admin");
        mdArea.setUpdTime(DateTimeUtil.getCustomerDate());
        logic.modify(mdArea);
        return this.init(model);
    }

    /**
     * 废除
     * 
     * @param mdArea mdArea
     * @param model model
     * @return 页面
     */
    @RequestMapping(value = "abolish",
        method = RequestMethod.POST)
    public String abolish(AreaBean mdArea, Model model) {
        mdArea.setUpdId("admin");
        mdArea.setUpdTime(DateTimeUtil.getCustomerDate());
        logic.abolish(mdArea);
        return this.init(model);
    }

    /**
     * 恢复
     * 
     * @param mdArea mdArea
     * @param model model
     * @return 页面
     */
    @RequestMapping(value = "restore",
        method = RequestMethod.POST)
    public String restore(AreaBean mdArea, Model model) {
        mdArea.setUpdId("admin");
        mdArea.setUpdTime(DateTimeUtil.getCustomerDate());
        logic.restore(mdArea);
        return this.init(model);
    }
}
