package com.msk.ds.controller;

import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.utils.DbUtils;
import com.msk.common.base.BaseController;
import com.msk.common.consts.CommCodeMasterConst;
import com.msk.ds.bean.SC183102Bean;
import com.msk.ds.logic.SC183102Logic;
import com.msk.ds.rest.comm.RestUtil;
import com.msk.seller.bean.ISL231193RsParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * SC183102Controller
 *
 * @author fjm
 *
 */
@Controller
@RequestMapping("SC183102")
public class SC183102Controller extends BaseController {

    /** logger */
    private static Logger logger = LoggerFactory.getLogger(SC183102Controller.class);

    /** SC183102Logic */
    @Autowired
    private SC183102Logic sc183102Logic;

    /**
     * 初始化页面
     *
     * @param model Model
     * @return 页面
     */
    @RequestMapping(value = "init",
            method = RequestMethod.POST)
    public String init(Model model) {
        logger.debug("计划调整一览初始化");
        return "ds/SC183102";
    }

    /**
     * 分页查询数据
     *
     * @param  pageParam
     * @return 分页查询数据
     */
    @RequestMapping(value = "search",
            method = RequestMethod.POST)
    public @ResponseBody
    PageResult<SC183102Bean> search(BasePageParam pageParam) {
        logger.debug("计划调整一览查询");
        this.setCommonParam(pageParam);

        if(CommCodeMasterConst.LoginUserType.SUPPLIER_USER_TYPE.equals(pageParam.getUserType())){
            ISL231193RsParam param = new ISL231193RsParam();
            param.setSlAccount(pageParam.getCrtId());
            String slCode = RestUtil.queryslEpData(param).getSlCode();
            pageParam.getFilterMap().put("suppCode", slCode);
        }

        DbUtils.buildLikeCondition(pageParam, "suppDsId", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(pageParam, "planFlowId", DbUtils.LikeMode.FRONT);

        DbUtils.buildLikeCondition(pageParam, "distMonth", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(pageParam, "lgcsName", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(pageParam, "suppName", DbUtils.LikeMode.PARTIAL);
//        DbUtils.buildLikeCondition(pageParam, "pdStockType", DbUtils.LikeMode.FRONT);
//        DbUtils.buildLikeCondition(pageParam, "halfCode", DbUtils.LikeMode.FRONT);

        DbUtils.buildLikeCondition(pageParam, "classesCode", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(pageParam, "breedCode", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(pageParam, "featureCode", DbUtils.LikeMode.PARTIAL);
//        DbUtils.buildLikeCondition(pageParam, "gradeCode", DbUtils.LikeMode.PARTIAL);

        DbUtils.buildLikeCondition(pageParam, "outSpec", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(pageParam, "outNw", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(pageParam, "pdCode", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(pageParam, "normsCode", DbUtils.LikeMode.FRONT);

//        DbUtils.buildLikeCondition(pageParam, "adjustDate", DbUtils.LikeMode.FRONT);

        DbUtils.buildLikeCondition(pageParam, "oldPlanNum", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(pageParam, "newPlanNum", DbUtils.LikeMode.FRONT);

        String halfCode = StringUtil.toSafeString(pageParam.getFilterMap().get("halfCode"));
        String pdStockType = StringUtil.toSafeString(pageParam.getFilterMap().get("pdStockType"));
        String gradeCode = StringUtil.toSafeString(pageParam.getFilterMap().get("gradeCode"));
        if (!StringUtil.isNullOrEmpty(halfCode)) {
            String[] halfCodes = halfCode.split(",");
            pageParam.getFilterMap().put("halfCodes", halfCodes);
        }
        if (!StringUtil.isNullOrEmpty(pdStockType)) {
            String[] pdStockTypes = pdStockType.split(",");
            pageParam.getFilterMap().put("pdStockTypes", pdStockTypes);
        }
        // Add for 2781 at 2016/9/22 by likai Start
        if (!StringUtil.isNullOrEmpty(gradeCode)) {
            String[] gradeCodes = gradeCode.split(",");
            pageParam.getFilterMap().put("gradeCodes", gradeCodes);
        }
        // Add for 2781 at 2016/9/22 by likai End
        return sc183102Logic.findPage(pageParam, SC183102Bean.class);
    }

    /**
     * 删除数据
     * @param
     * @return
     */
    @RequestMapping(value = "delete",
            method = RequestMethod.POST)
    public String delete(@RequestParam(value = "suppDsId", required = false) Long suppDsId,@RequestParam(value = "planFlowId", required = false) Long planFlowId) {
        logger.debug("删除");
        SC183102Bean sc183102Bean = new SC183102Bean();
        sc183102Bean.setSuppDsId(suppDsId);
        sc183102Bean.setPlanFlowId(planFlowId);
        this.sc183102Logic.deleteBean(sc183102Bean);
        return "ds/SC183102";
    }

}
