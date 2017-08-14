package com.msk.district.controller;

import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.StringConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.utils.DbUtils;
import com.msk.common.base.BaseController;
import com.msk.district.bean.ProvinceBean;
import com.msk.district.logic.ProvinceLogic;
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
 * 行政区域管理画面-省
 * 
 * @author yuan_chen
 *
 */
@Controller
@RequestMapping("BY121102")
public class BY121102Controller extends BaseController {

    /** logger */
    private static Logger logger = LoggerFactory.getLogger(BY121102Controller.class);

    @Autowired
    private ProvinceLogic by121102Logic;

    /**
     * 初始化页面
     *
     * @param model Model
     * @return 页面
     */
    @RequestMapping(value = "init",
        method = RequestMethod.POST)
    public String init(Model model) {
        logger.debug("省列表初始化");
        model.addAttribute("areaId", StringConst.ALL);
        return "district/BY121102";
    }

    /**
     * 初始化页面
     *
     * @param areaId areaId
     * @param model Model
     * @return 页面
     */
    @RequestMapping(value = "init/{areaId}",
        method = RequestMethod.POST)
    public String init(@PathVariable("areaId") String areaId, Model model) {
        logger.debug("省列表初始化");
        model.addAttribute("areaId", areaId);
        return "district/BY121102";
    }

    /**
     * 分页查询数据
     *
     * @param areaId areaId
     * @param param pageParam
     * @return 分页查询数据
     */
    @RequestMapping(value = "search/{areaId}",
        method = RequestMethod.POST)
    public @ResponseBody
    PageResult<ProvinceBean> search(@PathVariable("areaId") String areaId, BasePageParam param) {
        logger.debug("省列表列表查询");
        if (!StringUtil.isNullOrEmpty(areaId) && !StringConst.ALL.equals(areaId)) {
            param.getFilterMap().put("areaId", areaId);
        }

        DbUtils.buildLikeCondition(param, "provinceCode", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(param, "provinceName", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(param, "spell", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(param, "shortSpell", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(param, "fullCode", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(param, "fullName", DbUtils.LikeMode.PARTIAL);
        String delFlg = (String) param.getFilterMap().get("delFlg");
        if(!StringUtil.isNullOrEmpty(delFlg)){
            String[] delFlgAry = delFlg.split(",");
            if (delFlgAry.length == NumberConst.IntDef.INT_TWO) {
                param.setFilter("delFlg", null);
            }
        }
        return by121102Logic.findPage(param, ProvinceBean.class);
    }

    /**
     * 修改
     * 
     * @param mdProvince mdProvince
     * @param model model
     * @return 页面
     */
    @RequestMapping(value = "modify",
        method = RequestMethod.POST)
    public String modify(ProvinceBean mdProvince, Model model) {
        mdProvince.setUpdId("admin");
        mdProvince.setUpdTime(DateTimeUtil.getCustomerDate());
        by121102Logic.modify(mdProvince);
        return this.init(model);
    }

    /**
     * 废除
     * 
     * @param mdProvince mdProvince
     * @param model model
     * @return 页面
     */
    @RequestMapping(value = "abolish",
        method = RequestMethod.POST)
    public String abolish(ProvinceBean mdProvince, Model model) {
        mdProvince.setUpdId("admin");
        mdProvince.setUpdTime(DateTimeUtil.getCustomerDate());
        by121102Logic.abolish(mdProvince);
        return this.init(model);
    }

    /**
     * 恢复
     * 
     * @param mdProvince mdProvince
     * @param model model
     * @return 页面
     */
    @RequestMapping(value = "restore",
        method = RequestMethod.POST)
    public String restore(ProvinceBean mdProvince, Model model) {
        mdProvince.setUpdId("admin");
        mdProvince.setUpdTime(DateTimeUtil.getCustomerDate());
        by121102Logic.restore(mdProvince);
        return this.init(model);
    }
}
