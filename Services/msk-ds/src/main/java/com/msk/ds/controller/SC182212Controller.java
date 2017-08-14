package com.msk.ds.controller;

import com.hoperun.core.bean.BasePageParam;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.utils.DbUtils;
import com.msk.common.base.BaseController;
import com.msk.ds.bean.SC182212Bean;
import com.msk.ds.logic.SC182212Logic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by FjM on 2016/4/28.
 */


@Controller
@RequestMapping("SC182212")
public class SC182212Controller extends BaseController{
    /** logger */
    private static Logger logger = LoggerFactory.getLogger(SC182212Controller.class);

    @Autowired
    private SC182212Logic sc182212Logic;

    @RequestMapping(value = "init",method = RequestMethod.POST)
    public String init(){
        logger.info("查询所有批次号画面初始化");
        return "ds/SC182212";
    }


    @RequestMapping(value = "search",
            method = RequestMethod.POST)
    public @ResponseBody
    PageResult<SC182212Bean> search(BasePageParam basePageParam) {
        logger.info("查询所有批次号画面查询");
        DbUtils.buildLikeCondition(basePageParam, "slCodeDis", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(basePageParam, "slCodeManufacture", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(basePageParam, "salesPlatform", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(basePageParam, "dateCode", DbUtils.LikeMode.FRONT);

        DbUtils.buildLikeCondition(basePageParam, "gradeCode", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(basePageParam, "weightCode", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(basePageParam, "featureCode", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(basePageParam, "breedCode", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(basePageParam, "machiningCode", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(basePageParam, "classesCode", DbUtils.LikeMode.FRONT);
        basePageParam.setFilter("lotId", "");
        return this.sc182212Logic.findPage(basePageParam, SC182212Bean.class);
    }

    @RequestMapping(value = "delete",
            method = RequestMethod.POST)
    public String delete(Long lotId) {
        logger.info("删除信息");
        this.sc182212Logic.deleteInfo(lotId);
        return "ds/SC182212";
    }

    @RequestMapping(value = "deleteMore",
            method = RequestMethod.POST)
    public String deleteMore(Long lotId) {
        logger.info("删除信息");
        this.sc182212Logic.deleteInfo2(lotId);
        return "ds/SC182212";
    }

}
