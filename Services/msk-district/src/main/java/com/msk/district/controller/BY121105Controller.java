package com.msk.district.controller;

import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.StringConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.utils.DbUtils;
import com.msk.common.base.BaseController;
import com.msk.district.bean.DistrictBean;
import com.msk.district.logic.DistrictLogic;
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
 * 行政区域管理画面-区
 * 
 * @author yuan_chen
 *
 */
@Controller
@RequestMapping("BY121105")
public class BY121105Controller extends BaseController {

    /** logger */
    private static Logger logger = LoggerFactory.getLogger(BY121105Controller.class);

    @Autowired
    private DistrictLogic by121105Logic;

    /**
     * 初始化页面
     *
     * @param model Model
     * @return 页面
     */
    @RequestMapping(value = "init",
        method = RequestMethod.POST)
    public String init(Model model) {
        logger.debug("区列表初始化");
        model.addAttribute("cityId", StringConst.ALL);
        return "district/BY121105";
    }

    /**
     * 初始化页面
     *
     * @param cityId cityId
     * @param model Model
     * @return 页面
     */
    @RequestMapping(value = "init/{cityId}",
        method = RequestMethod.POST)
    public String init(@PathVariable("cityId") String cityId, Model model) {
        logger.debug("区列表初始化");
        model.addAttribute("cityId", cityId);
        return "district/BY121105";
    }

    /**
     * 分页查询数据
     *
     * @param cityId cityId
     * @param param pageParam
     * @return 分页查询数据
     */
    @RequestMapping(value = "search/{cityId}",
        method = RequestMethod.POST)
    public @ResponseBody
    PageResult<DistrictBean> search(@PathVariable("cityId") String cityId, BasePageParam param) {
        logger.debug("物流区列表列表查询");
        if (!StringUtil.isNullOrEmpty(cityId) && !StringConst.ALL.equals(cityId)) {
            param.getFilterMap().put("cityId", cityId);
        }
        DbUtils.buildLikeCondition(param, "districtCode", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(param, "districtName", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(param, "spell", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(param, "shortSpell", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(param, "shortCode", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(param, "shortName", DbUtils.LikeMode.PARTIAL);
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
        return by121105Logic.findPage(param, DistrictBean.class);
    }

    /**
     * 修改
     * 
     * @param mdDistrict mdDistrict
     * @param model model
     * @return 页面
     */
    @RequestMapping(value = "modify",
        method = RequestMethod.POST)
    public String modify(DistrictBean mdDistrict, Model model) {
        mdDistrict.setUpdId("admin");
        mdDistrict.setUpdTime(DateTimeUtil.getCustomerDate());
        by121105Logic.modify(mdDistrict);
        return this.init(model);
    }

    /**
     * 废除
     * 
     * @param mdDistrict mdDistrict
     * @param model model
     * @return 页面
     */
    @RequestMapping(value = "abolish",
        method = RequestMethod.POST)
    public String abolish(DistrictBean mdDistrict, Model model) {
        mdDistrict.setUpdId("admin");
        mdDistrict.setUpdTime(DateTimeUtil.getCustomerDate());
        by121105Logic.abolish(mdDistrict);
        return this.init(model);
    }

    /**
     * 恢复
     * 
     * @param mdDistrict mdDistrict
     * @param model model
     * @return 页面
     */
    @RequestMapping(value = "restore",
        method = RequestMethod.POST)
    public String restore(DistrictBean mdDistrict, Model model) {
        mdDistrict.setUpdId("admin");
        mdDistrict.setUpdTime(DateTimeUtil.getCustomerDate());
        by121105Logic.restore(mdDistrict);
        return this.init(model);
    }
}
