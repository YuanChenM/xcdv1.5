package com.msk.so.controller;

import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.utils.DbUtils;
import com.msk.common.base.BaseController;
import com.msk.so.bean.SOCp153151Bean;
import com.msk.so.logic.SO153151Logic;
import com.msk.so.utils.CheckUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;

/**
 *
 * 支付一览
 *
 * @author yang_yang
 * @version 1.0
 **/
@Controller
@RequestMapping("SO153151")
public class SO153151Controller extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(SO153151Controller.class);
    @Autowired
    private SO153151Logic sO153151Logic;

    /**
     * 支付一览
     *
     * @return String
     **/
    @RequestMapping(value = "init", method = RequestMethod.POST)
    public String init(BasePageParam param, Model model) {
        model.addAttribute("param", param);
        logger.debug("初始化页面");
        return "so/SO153151";
    }

    /**
     * @param pageParam pageParam
     * @return PageResult<SO153151Bean>
     */
    @RequestMapping(value = "search", method = RequestMethod.POST)
    @ResponseBody
    public PageResult<SOCp153151Bean> search(BasePageParam pageParam) {
        // 支付一览
        logger.info("支付一览");

        return sO153151Logic.findSO153151List(pageParam);
    }
//    /**  页面添加导出按钮 modify by renyi on 2016/8/10 start */
//    /**
//     * 导出excel
//     */
//    @RequestMapping(value = "EXPORT", method = RequestMethod.POST)
//    public void export(BasePageParam pageParam,HttpServletRequest request, HttpServletResponse response) throws Exception {
//        // 设置参数
//        pageParam = sO153151Logic.setBasePageParam(pageParam);
//        String fileName = "Payment_" + DateTimeUtil.formatDate(DateTimeUtil.FORMAT_DATE_YYYYMMDD, DateTimeUtil.getCustomerDate()) + ".xlsx";
//        response.reset();
//        response.setContentType("application/vnd.ms-excel;charset=utf-8");
//        response.addHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("GBK"), "ISO8859-1"));
//        OutputStream ouputStream = response.getOutputStream();
//        this.sO153151Logic.export(ouputStream, pageParam);
//
//
//    }
//    /**  页面添加导出按钮 modify by renyi on 2016/8/10 end */

}
