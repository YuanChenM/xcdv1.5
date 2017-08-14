package com.msk.common.controller;

import org.springframework.stereotype.Controller;

import com.msk.common.base.BaseController;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * MainController
 *
 * @author jiang_nan
 * @version 1.0
 **/
@Controller
public class MainController extends BaseController {


    @RequestMapping(value = "{module}/mainDetail", method = {RequestMethod.POST,RequestMethod.GET})
    public String main(@PathVariable("module") String module,String url, String data, String title, Model model){
        //请求url
        model.addAttribute("url", url);
        //请求数据
        model.addAttribute("data", data);
        //窗口title
        model.addAttribute("title", title);
        return "mainDetail";
    }
//    /**
//     * logger
//     */
//    private static Logger logger = LoggerFactory.getLogger(MainController.class);
//    @RequestMapping(value = "{module}/main", method = {RequestMethod.POST,RequestMethod.GET})
//    public String main(@PathVariable("module") String module, String sysCode, Model model){
//        logger.info("System Code:"+sysCode);
//        RsRequest<String> requestBody = new RsRequest<>();
//        requestBody.setParam(sysCode);
//        String url = SystemServerManager.OrgServerManager.getSearchMenuList();
//        url = "http://localhost:9999/api/org/menu/search";
//        RsResponse<ArrayList<OrgSystemResult>> responseBody = RestClientUtil.post(url,requestBody,new TypeReference<RsResponse<ArrayList<OrgSystemResult>>>(){});
//        List<OrgSystemResult> menuList = responseBody.getResult();
//        model.addAttribute("menuList",menuList);
//        model.addAttribute("sysModel",module);
//        this.setLoginUserSession(request);
//        return "/main/main";
//    }
//
//    private void initLoginUserMapSession(HttpServletRequest request, Map<String,String> ssoUerInfoMap){
//        Map<String,Object> loginUserInfoMap = AssertionUtils.getLoginUserInfo(ssoUerInfoMap);
//        HttpSession session = request.getSession(false);
//        session.setAttribute(SwitchSystemServlet.SESSION_LOGIN_USER_KEY,loginUserInfoMap);
//    }
//    public void setLoginUserSession(HttpServletRequest request){
//        Map<String,String> ssoUerInfoMap = AssertionUtils.getSsoUserInfo(request);
//        this.initLoginUserMapSession(request,ssoUerInfoMap);
//        HttpSession session = request.getSession(false);
//        Map<String,Object> loginUserInfoMap = (Map<String,Object>)session.getAttribute(SwitchSystemServlet.SESSION_LOGIN_USER_KEY);
//        this.setEmploySession(request,loginUserInfoMap);
//    }
//    public void setEmploySession(HttpServletRequest request,Map<String,Object> loginUserInfoMap){
//        String employCode = (String) loginUserInfoMap.get("employCode");
//        String employName = (String)loginUserInfoMap.get("employName");
//        LoginUser loginUser = new LoginUser();
//        loginUser.setEmplNo(employCode);
//        loginUser.setEmplName(employName);
//        SessionManger.getSessionManger(request).setValue(SessionManger.USER_SESSION_KEY,loginUser);
//    }
//
//
////    @RequestMapping(value = "main")
////    public String main(Model model) {
////        LoginUser loginUser = this.getLoginUser();
////        if (loginUser == null) {
////            return "/login";
////        }
////        model.addAttribute("version", String.valueOf(System.currentTimeMillis()));
////        model.addAttribute("userType", this.getLoginUser().getUserType());
////
////        // 系统名
////        String sysCode = StringUtil.toString(SessionManger.getSessionManger(request).getValue("systemCode"));
////        String sysName = StringUtil.toSafeString(ConfigManager.getSysNameByCode(sysCode));
////        model.addAttribute("sysName", sysName);
////        String sysModel = StringUtil.toSafeString(SessionManger.getSessionManger(request).getValue("systemModel"))
////                .toLowerCase();
////        model.addAttribute("sysModel", sysModel);
////        // 系统版本
////        model.addAttribute("systemVersion", ConfigManager.getSystemVersion());
////        logger.debug("进入Main Controller");
////        return "/main/index";
////    }
}