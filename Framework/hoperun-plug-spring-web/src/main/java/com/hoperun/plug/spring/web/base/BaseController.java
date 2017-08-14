package com.hoperun.plug.spring.web.base;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;



/**
 * Controller的基类
 */
public class BaseController {
    /** 数据库调试开关，打开时不再进行数据库访问 */
    public final static boolean isDebug = false; // TODO 改为配置
    /** 请求 */
    @Autowired
    protected HttpServletRequest request;
    /**
     * initBinder 实现对日期对象与字符串之间的转换
     *
     * @param binder WebDataBinder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        CustomDateEditor editor = new CustomDateEditor(df, false);
        binder.registerCustomEditor(Date.class, editor);
    }
    /**
     * 页面forward跳转
     *
     * @param url 目标url
     * @return 页面跳转字符串
     */
    protected String forward(String url) {
        return "forward:" + url;
    }
    /**
     * 页面redirect跳转
     *
     * @param url 目标url
     * @return 页面跳转字符串
     */
    protected String redirect(String url) {
        return "redirect:" + url;
    }
}
