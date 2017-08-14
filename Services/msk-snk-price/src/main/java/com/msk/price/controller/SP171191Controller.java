package com.msk.price.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.utils.DbUtils;
import com.msk.common.base.BaseController;
import com.msk.common.config.CodeMasterManager;
import com.msk.price.bean.SP171170Bean;
import com.msk.price.bean.SP171191Bean;
import com.msk.price.bean.SP171191Result;
import com.msk.price.bean.SP171193Result;
import com.msk.price.logic.SP171191Logic;
import com.msk.price.logic.SP171193Logic;
import com.msk.price.utils.CommRestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 物流区产品等级信息管理
 * zhang_qiang1
 */

@Controller
@RequestMapping("SP171191")
public class SP171191Controller extends BaseController {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(SP171191Controller.class);

    @Autowired
    private SP171191Logic sp171191Logic;
    @Autowired
    private SP171193Logic sp171193Logic;

    /**
     * 初始化
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "init")
    public String init(Model model) {
        logger.info("一览页面初始化");
        //物流下拉框
        model.addAttribute("lgcsinfo", CommRestUtil.getLogiticsAreaList());
        //通道等级下拉
        BasePageParam basePageParam = new BasePageParam();
        PageResult<SP171193Result> pageResult = sp171193Logic.findWayDetail(basePageParam);
        if (null != pageResult && null != pageResult.getData() && pageResult.getData().size() > 0) {
            model.addAttribute("wayList", pageResult.getData());
        }
        return "sp/SP171191";
    }


    /**
     * @param basePageParam basePageParam
     * @return PageResult<BP112214Bean>
     * @author zhang_qiang1
     */
    @RequestMapping(value = "search", method = RequestMethod.POST)
    @ResponseBody
    public PageResult<SP171191Bean> search(BasePageParam basePageParam) {
        logger.info("查询数据库");
        DbUtils.buildLikeCondition(basePageParam, "pdName", DbUtils.LikeMode.PARTIAL);// 产品名称
        DbUtils.buildLikeCondition(basePageParam, "pdCode", DbUtils.LikeMode.PARTIAL);// 产品编码
        DbUtils.buildLikeCondition(basePageParam, "wayName", DbUtils.LikeMode.PARTIAL);// 通道标准名称
        DbUtils.buildLikeCondition(basePageParam, "wayCode", DbUtils.LikeMode.PARTIAL);// 通道标准编号
        DbUtils.buildLikeCondition(basePageParam, "marketingName", DbUtils.LikeMode.PARTIAL);// 营销状态名称
        //多地区条件设置
        String lgcsCode = StringUtil.toSafeString(basePageParam.getFilterMap().get("lgcsName"));
        if (!StringUtil.isNullOrEmpty(lgcsCode)) {
            String[] lgcsCodes = lgcsCode.split(",");
            basePageParam.getFilterMap().put("lgcsCodes", lgcsCodes);
        }
        //多等级条件设置
        String gradeCode = StringUtil.toSafeString(basePageParam.getFilterMap().get("pdGrage"));
        if (!StringUtil.isNullOrEmpty(gradeCode)) {
            String[] gradeCodes = gradeCode.split(",");
            basePageParam.getFilterMap().put("gradeCodes", gradeCodes);
        }
        //多单位条件设置
        String unit = StringUtil.toSafeString(basePageParam.getFilterMap().get("units"));
        if (!StringUtil.isNullOrEmpty(unit)) {
            String[] units = unit.split(",");
            basePageParam.getFilterMap().put("units", units);
        }

        PageResult<SP171191Bean> pageResult = this.sp171191Logic.getPageR(basePageParam);
        return pageResult;
    }


    /**
     * @param jsonStr
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public
    @ResponseBody
    String save(Model model, String jsonStr) {
        BaseParam baseParam = new BaseParam();
        super.setCommonParam(baseParam);
        Map<String, SP171191Bean> map = JSON.parseObject(jsonStr, new TypeReference<Map<String, SP171191Bean>>() {
        });
        sp171191Logic.btachSaveOrUpdate(map.values(), baseParam);
        SP171191Result result = new SP171191Result();
        result.setFlag(true);
        return "S";
    }

    /**
     * @param jsonStr
     * @return
     */
    @RequestMapping(value = "saveWay/{flag}", method = RequestMethod.POST)
    public String saveWay(@PathVariable Integer flag, Model model, BasePageParam basePageParam, String wayCode, String MarketingName) {
        if (flag == 0) {
            MarketingName = null;
        } else {
            wayCode = null;
        }
        DbUtils.buildLikeCondition(basePageParam, "pdName", DbUtils.LikeMode.PARTIAL);// 产品名称
        DbUtils.buildLikeCondition(basePageParam, "pdCode", DbUtils.LikeMode.PARTIAL);// 产品编码
        DbUtils.buildLikeCondition(basePageParam, "wayName", DbUtils.LikeMode.PARTIAL);// 通道标准名称
        DbUtils.buildLikeCondition(basePageParam, "wayCode", DbUtils.LikeMode.PARTIAL);// 通道标准编号
        DbUtils.buildLikeCondition(basePageParam, "marketingName", DbUtils.LikeMode.PARTIAL);// 营销状态名称
        //多地区条件设置
        String lgcsCode = StringUtil.toSafeString(basePageParam.getFilterMap().get("lgcsName"));
        if (!StringUtil.isNullOrEmpty(lgcsCode)) {
            String[] lgcsCodes = lgcsCode.split(",");
            basePageParam.getFilterMap().put("lgcsCodes", lgcsCodes);
        }
        //多等级条件设置
        String gradeCode = StringUtil.toSafeString(basePageParam.getFilterMap().get("pdGrage"));
        if (!StringUtil.isNullOrEmpty(gradeCode)) {
            String[] gradeCodes = gradeCode.split(",");
            basePageParam.getFilterMap().put("gradeCodes", gradeCodes);
        }
        //多单位条件设置
        String unit = StringUtil.toSafeString(basePageParam.getFilterMap().get("units"));
        if (!StringUtil.isNullOrEmpty(unit)) {
            String[] units = unit.split(",");
            basePageParam.getFilterMap().put("units", units);
        }
        this.setCommonParam(basePageParam);
        sp171191Logic.saveWay(basePageParam, wayCode, MarketingName);
        return this.init(model);
    }


    /**
     * @param list
     */
    public void setCommon(List<SP171191Bean> list) {
        for (SP171191Bean bean : list) {
            this.setCommonParam(bean);
        }
    }
}
