package com.msk.so.controller;

import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.utils.DbUtils;
import com.msk.common.base.BaseUploadController;
import com.msk.so.bean.SO153171Bean;
import com.msk.so.logic.SO153171Logic;
import com.msk.so.utils.CheckUtil;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 *
 * 账期设置
 *
 * @author wu_honglei
 * @version 1.0
 **/
@Controller
@RequestMapping("SO153171")
public class SO153171Controller extends BaseUploadController {

    private static Logger logger = LoggerFactory.getLogger(SO153171Controller.class);

    @Autowired
    private SO153171Logic sO153171Logic;

    /**
     *  账期设置一览
     *
     * @return String
     **/
    @RequestMapping(value = "init", method = RequestMethod.POST)
    public String init(BasePageParam param, Model model) {
        model.addAttribute("param", param);
        logger.debug("初始化页面");
        return "so/SO153171";
    }


    /**
     * @param pageParam pageParam
     * @return PageResult<SO153161Bean>
     */
    @RequestMapping(value = "search", method = RequestMethod.POST)
    @ResponseBody
    public PageResult<SO153171Bean> search(BasePageParam pageParam) {
        logger.info("账期设置一览");

        // 验证日期
        boolean lastPeriodFlag = CheckUtil.checkDate(StringUtil.toSafeString(pageParam.getFilterMap().get("lastPeriodEndStart"))
                , StringUtil.toSafeString(pageParam.getFilterMap().get("lastPeriodEndEnd")));
        boolean commFlag = CheckUtil.checkDate(StringUtil.toSafeString(pageParam.getFilterMap().get("commDateStart"))
                , StringUtil.toSafeString(pageParam.getFilterMap().get("commDateEnd")));
        if(!lastPeriodFlag || !commFlag){
            throw new BusinessException("日期数据不合理");
        }
        String periodStart = StringUtil.toSafeString(pageParam.getFilterMap().get("periodStart"));
        String periodEnd = StringUtil.toSafeString(pageParam.getFilterMap().get("periodEnd"));
        if (!CheckUtil.checkNum(periodStart, periodEnd)) {
            throw new BusinessException("输入的数字不合理");
        }

        DbUtils.buildLikeCondition(pageParam, "userNo", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(pageParam, "userName", DbUtils.LikeMode.PARTIAL);

        String userRole = StringUtil.toSafeString(pageParam.getFilterMap().get("userRole"));
        if (!StringUtil.isNullOrEmpty(userRole)) {
            String[] userRoles = userRole.split(",");
            pageParam.getFilterMap().put("userRoles", userRoles);
        }

        return sO153171Logic.findPageList(pageParam);
    }

}
