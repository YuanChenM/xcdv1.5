package com.msk.district.controller;


import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.StringConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.utils.DbUtils;
import com.msk.common.base.BaseController;
import com.msk.district.bean.CityBean;
import com.msk.district.logic.CityLogic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 行政区域管理画面-城市地区
 * 
 * @author yuan_chen
 *
 */
@Controller
@RequestMapping("BY121104")
public class BY121104Controller extends BaseController {

    /** logger */
    private static Logger logger = LoggerFactory.getLogger(BY121104Controller.class);

    @Autowired
    private CityLogic by121104Logic;

    /**
     * 初始化页面
     *
     * @param model Model
     * @return 页面
     */
    @RequestMapping(value = "init",
        method = RequestMethod.POST)
    public String init(Model model) {
        logger.debug("城市地区列表初始化");
        model.addAttribute("provinceId", StringConst.ALL);
        model.addAttribute("lgcsAreaId", StringConst.ALL);
        return "district/BY121104";
    }

    /**
     * 初始化页面
     *
     * @param provinceId provinceId
     * @param lgcsAreaId lgcsAreaId
     * @param model Model
     * @return 页面
     */
    @RequestMapping(value = "init/{provinceId}/{lgcsAreaId}",
        method = RequestMethod.POST)
    public String init(@PathVariable("provinceId") String provinceId, @PathVariable("provinceId") String lgcsAreaId,
        Model model) {
        logger.debug("城市地区列表初始化");
        model.addAttribute("provinceId", provinceId);
        model.addAttribute("lgcsAreaId", lgcsAreaId);
        return "district/BY121104";
    }

    /**
     * 分页查询数据
     *
     * @param provinceId provinceId
     * @param lgcsAreaId lgcsAreaId
     * @param param pageParam
     * @return 分页查询数据
     */
    @RequestMapping(value = "search/{provinceId}/{lgcsAreaId}",
        method = RequestMethod.POST)
    public @ResponseBody
    PageResult<CityBean> search(@PathVariable("provinceId") String provinceId,
        @PathVariable("lgcsAreaId") String lgcsAreaId, BasePageParam param) {
        logger.debug("城市地区列表初始化");
        if (!StringUtil.isNullOrEmpty(provinceId) && !StringConst.ALL.equals(provinceId)) {
            param.getFilterMap().put("provinceId", provinceId);
        }
        if (!StringUtil.isNullOrEmpty(lgcsAreaId) && !StringConst.ALL.equals(lgcsAreaId)) {
            param.getFilterMap().put("lgcsAreaId", lgcsAreaId);
        }
        DbUtils.buildLikeCondition(param, "cityCode", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(param, "cityName", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(param, "spell", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(param, "shortSpell", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(param, "shortCodeP", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(param, "shortNameP", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(param, "shortCodeL", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(param, "shortNameL", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(param, "fullCodeP", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(param, "fullNameP", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(param, "fullCodeL", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(param, "fullNameL", DbUtils.LikeMode.PARTIAL);
        String delFlg = (String) param.getFilterMap().get("delFlg");
        if(!StringUtil.isNullOrEmpty(delFlg)){
            String[] delFlgAry = delFlg.split(",");
            if (delFlgAry.length == NumberConst.IntDef.INT_TWO) {
                param.setFilter("delFlg", null);
            }
        }
        return by121104Logic.findPage(param, CityBean.class);
    }

    /**
     * 修改
     * 
     * @param mdCity mdCity
     * @param model model
     * @return 页面
     */
    @RequestMapping(value = "modify",
        method = RequestMethod.POST)
    public String modify(CityBean mdCity, Model model) {
        mdCity.setUpdId("admin");
        mdCity.setUpdTime(DateTimeUtil.getCustomerDate());
        by121104Logic.modify(mdCity);
        return this.init(model);
    }

    /**
     * 废除
     * 
     * @param mdCity mdCity
     * @param model model
     * @return 页面
     */
    @RequestMapping(value = "abolish",
        method = RequestMethod.POST)
    public String abolish(CityBean mdCity, Model model) {
        mdCity.setUpdId("admin");
        mdCity.setUpdTime(DateTimeUtil.getCustomerDate());
        by121104Logic.abolish(mdCity);
        return this.init(model);
    }

    /**
     * 恢复
     * 
     * @param mdCity mdCity
     * @param model model
     * @return 页面
     */
    @RequestMapping(value = "restore",
        method = RequestMethod.POST)
    public String restore(CityBean mdCity, Model model) {
        mdCity.setUpdId("admin");
        mdCity.setUpdTime(DateTimeUtil.getCustomerDate());
        by121104Logic.restore(mdCity);
        return this.init(model);
    }
}
