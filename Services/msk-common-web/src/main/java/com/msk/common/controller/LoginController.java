package com.msk.common.controller;

import org.springframework.stereotype.Controller;

import com.msk.common.base.BaseController;

/**
 * LoginController
 *
 * @author jiang_nan
 * @version 1.0
 **/
@Controller
public class LoginController extends BaseController {
//    /**
//     * logger
//     */
//    private static Logger logger = LoggerFactory.getLogger(LoginController.class);
//
//    @Autowired
//    private LoginLogic loginLogic;
//
//    @Autowired
//    private SystemLogic systemLogic;
//    private static Map<String, String> SYSTEM_CODE_MODEL_MAP = new HashMap<String, String>();
//
//    public final static boolean needAuthCheck = ConfigManager.needAuthCheck();
//
//    /**
//     * 初始化系统Code和模块对于Map数据
//     */
//    static {
//        SYSTEM_CODE_MODEL_MAP.put(SystemCode.SYSTEM_CODE_BY, SystemModel.SYSTEM_MODEL_BY);
//        SYSTEM_CODE_MODEL_MAP.put(SystemCode.SYSTEM_CODE_PD, SystemModel.SYSTEM_MODEL_PD);
//        SYSTEM_CODE_MODEL_MAP.put(SystemCode.SYSTEM_CODE_SL, SystemModel.SYSTEM_MODEL_SL);
//        SYSTEM_CODE_MODEL_MAP.put(SystemCode.SYSTEM_CODE_SO, SystemModel.SYSTEM_MODEL_SO);
//        SYSTEM_CODE_MODEL_MAP.put(SystemCode.SYSTEM_CODE_MS, SystemModel.SYSTEM_MODEL_MS);
//        SYSTEM_CODE_MODEL_MAP.put(SystemCode.SYSTEM_CODE_DS, SystemModel.SYSTEM_MODEL_DS);
//        // SYSTEM_CODE_MODEL_MAP.put(SystemCode.SYSTEM_CODE_MD,SystemModel.SYSTEM_MODEL_MD);
//        SYSTEM_CODE_MODEL_MAP.put(SystemCode.SYSTEM_CODE_BS, SystemModel.SYSTEM_MODEL_BS);
//        SYSTEM_CODE_MODEL_MAP.put(SystemCode.SYSTEM_CODE_SP, SystemModel.SYSTEM_MODEL_SP);
//        // 提供其他系统接口的定义
//        // 供应商用户用
//        SYSTEM_CODE_MODEL_MAP.put(SystemCode.SYSTEM_CODE_DS_SUPP, SystemModel.SYSTEM_MODEL_DS_SUPP);
//        SYSTEM_CODE_MODEL_MAP.put(SystemCode.SYSTEM_CODE_CC, SystemModel.SYSTEM_MODEL_CC);
//        SYSTEM_CODE_MODEL_MAP.put(SystemCode.SYSTEM_CODE_SP_SUPP, SystemModel.SYSTEM_MODEL_SP_SUPP);
//    }
//
//    interface SystemCode {
//        String SYSTEM_CODE_BY = "302";
//        String SYSTEM_CODE_PD = "304";
//        String SYSTEM_CODE_SL = "303";
//        String SYSTEM_CODE_SO = "305";
//        String SYSTEM_CODE_MS = "301";
//        String SYSTEM_CODE_DS = "308";
//        // String SYSTEM_CODE_MD = "399";
//        String SYSTEM_CODE_BS = "310";
//        // 提供其他系统接口的定义
//        String SYSTEM_CODE_DS_SUPP = "308"; // 供应商用户用
//        // Call Center
//        String SYSTEM_CODE_CC = "901";
//        // snk_price
//        String SYSTEM_CODE_SP = "315";
//        String SYSTEM_CODE_SP_SUPP = "316"; // 供应商用户用
//
//    }
//
//    interface SystemModel {
//        String SYSTEM_MODEL_BY = "BY";
//        String SYSTEM_MODEL_PD = "PD";
//        String SYSTEM_MODEL_SL = "SL";
//        String SYSTEM_MODEL_SO = "SO";
//        String SYSTEM_MODEL_MS = "MS";
//        String SYSTEM_MODEL_DS = "DS";
//        // String SYSTEM_MODEL_MD = "MD";
//        String SYSTEM_MODEL_BS = "BS";
//
//        // 提供其他系统接口的定义
//        String SYSTEM_MODEL_DS_SUPP = "saler"; // 供应商用户用
//        // Call Center
//        String SYSTEM_MODEL_CC = "CC";
//        // snk_price
//        String SYSTEM_MODEL_SP = "SP";
//        String SYSTEM_MODEL_SP_SUPP = "SPS"; // 供应商用户用
//
//    }
//
//    /**
//     * 进入登陆页面
//     *
//     * @return 登陆页面
//     */
//    @RequestMapping(value = "login",
//            method = RequestMethod.GET)
//    public String login() {
//        return redirect("so/login");
//    }
//
//    /**
//     * 进入登陆页面
//     *
//     * @param type 系统模块
//     * @param model Model
//     * @return 登陆页面
//     */
//    @RequestMapping(value = "{type}/login",
//            method = RequestMethod.GET)
//    public String login(@PathVariable("type") String type, Model model, HttpServletResponse response) {
//        if (StringUtil.isEmpty(type)) {
//            type = CookieUtils.getCookieValue(request, "systemCode"); // 取Cookie
//        }
//        if (StringUtil.isEmpty(type)) {
//            type = SystemModel.SYSTEM_MODEL_SO; // 默认
//        }
//
//        String systemCode;
//        if (SystemModel.SYSTEM_MODEL_DS.equalsIgnoreCase(type)) {
//            systemCode = SystemCode.SYSTEM_CODE_DS;
//        } else if (SystemModel.SYSTEM_MODEL_SL.equalsIgnoreCase(type)) {
//            systemCode = SystemCode.SYSTEM_CODE_SL;
//        } else if (SystemModel.SYSTEM_MODEL_SO.equalsIgnoreCase(type)) {
//            systemCode = SystemCode.SYSTEM_CODE_SO;
//        } else if (SystemModel.SYSTEM_MODEL_MS.equalsIgnoreCase(type)) {
//            systemCode = SystemCode.SYSTEM_CODE_MS;
//        } else if (SystemModel.SYSTEM_MODEL_PD.equalsIgnoreCase(type)) {
//            systemCode = SystemCode.SYSTEM_CODE_PD;
//            // } else if (SystemModel.SYSTEM_MODEL_MD.equalsIgnoreCase(type)) {
//            // loginTitle = ConfigManager.getLoginMdImage();
//            // systemCode = SystemCode.SYSTEM_CODE_MD;
//        } else if (SystemModel.SYSTEM_MODEL_BS.equalsIgnoreCase(type)) {
//            systemCode = SystemCode.SYSTEM_CODE_BS;
//        } else if (SystemModel.SYSTEM_MODEL_BY.equalsIgnoreCase(type)) {
//            systemCode = SystemCode.SYSTEM_CODE_BY;
//        } else if (SystemModel.SYSTEM_MODEL_DS_SUPP.equalsIgnoreCase(type)) {
//            // 供应商用户用
//            systemCode = SystemCode.SYSTEM_CODE_DS_SUPP;
//        } else if (SystemModel.SYSTEM_MODEL_CC.equalsIgnoreCase(type)) {
//            // Call Center
//            systemCode = SystemCode.SYSTEM_CODE_CC;
//        } else if (SystemModel.SYSTEM_MODEL_SP.equalsIgnoreCase(type)) {
//            // snk_price
//            systemCode = SystemCode.SYSTEM_CODE_SP;
//        } else if (SystemModel.SYSTEM_MODEL_SP_SUPP.equalsIgnoreCase(type)) {
//            // snk_price
//            systemCode = SystemCode.SYSTEM_CODE_SP_SUPP;
//        } else {
//            throw new SystemException("系统异常,没有找到相应的模块");
//        }
//
//        // 系统名称，用于显示
//        String systemName = ConfigManager.getSysNameByCode(systemCode);
//
//        CookieUtils.setCookieValue(response, "systemCode", systemCode);
//        model.addAttribute("sysModel", type.toLowerCase());
//        model.addAttribute("type", type);
//        model.addAttribute("systemCode", systemCode);
//        model.addAttribute("systemName", systemName);
//
//        if (SystemModel.SYSTEM_MODEL_DS_SUPP.equalsIgnoreCase(type) || SystemModel.SYSTEM_MODEL_SP_SUPP.equalsIgnoreCase(type)) {
//            // 供应商系统用户用
//            return "/loginSupp";
//        } else {
//            return "/login";
//        }
//    }
//
//    /**
//     * 用户登陆操作
//     *
//     * @param loginParam 登录参数
//     * @param model Model
//     * @return 主页面
//     */
//    @RequestMapping(value = "login", method = RequestMethod.POST)
//    public String login(LoginParam loginParam, Model model, HttpServletResponse response) {
//        // logger.debug("登录操作");
//        // 登陸之前清除Session
//        SessionManger.getSessionManger(request).invalidate();
//
//        // 登录用户名
//        String userName = loginParam.getUserName();
//        // 登录密码
//        String password = loginParam.getPassword();
//        // 登录系统编码
//        String systemCode = loginParam.getSystemCode();
//        // 登录用户类型
//        String userType = loginParam.getUserType();
//
//        String systemModel = SYSTEM_CODE_MODEL_MAP.get(systemCode);
//        if (CodeMasterConst.LoginUserType.SUPPLIER_USER_TYPE.equals(userType)) {
//            if (!SystemModel.SYSTEM_MODEL_DS.equalsIgnoreCase(systemModel)) {
//                model.addAttribute("message", "登录失败：该用户无权访问本系统");
//                return this.login(systemModel, model, response);
//            }
//        }
//
//        String loginServicesUrl = ConfigManager.getMskOrgServices()+ConfigManager.getOrgLoginServices();
//        RsRequest<LoginParam> requestParam = new RsRequest<LoginParam>();
//        requestParam.setParam(loginParam);
//        RsResponse<LoginUser> loginUserResponse = RestClientUtil.post(loginServicesUrl,requestParam,new TypeReference<RsResponse<LoginUser>>(){});
//        LoginUser loginUser = loginUserResponse.getResult();
//
//        if (loginUser == null) {
//            model.addAttribute("message", "登录失败：用户名或者密码不正确");
//            return this.login(systemModel, model, response);
//        }
//        if (!ConfigManager.isDebug()) {
//            boolean exists = systemLogic.checkSystemExists(userName, systemCode);
//            if (!exists) {
//                model.addAttribute("message", "登录失败：该用户无权访问本系统");
//                return this.login(systemModel, model, response);
//            }
//        }
//        SessionManger.getSessionManger(request).setValue("systemCode", systemCode);
//        SessionManger.getSessionManger(request).setValue("systemModel", systemModel);
//        RsRequest<String> pageListParam = new RsRequest<String>();
//        pageListParam.setParam(systemCode);
//        String pageListServicesUrl = ConfigManager.getMskOrgServices()+ConfigManager.getOrgPageServices();
//        RsResponse<OrgPage[]> pageListResponse= RestClientUtil.post(pageListServicesUrl,pageListParam,new TypeReference<RsResponse<OrgPage[]>>(){});
//        OrgPage[] pageList = pageListResponse.getResult();
//        if (pageList==null||pageList.length== NumberConst.IntDef.INT_ZERO) {
//            model.addAttribute("message", "登录失败：该用户无权访问本系统");
//            return this.login(systemModel, model, response);
//        }
//        loginUser.setUserType(userType);
//
//        if(needAuthCheck) {
//            // 按钮权限
//            RsRequest<String> authParam = new RsRequest<String>();
//            authParam.setLoginId(userName);
//            String buttonAuthServicesUrl = SystemServerManager.OrgServerManager.getButtonAuth();
//            RsResponse<ActionAuthoritie[]> buttonAuthResponse = RestClientUtil.post(buttonAuthServicesUrl, authParam, new TypeReference<RsResponse<ActionAuthoritie[]>>() {
//            });
//            ActionAuthoritie[] buttonList = buttonAuthResponse.getResult();
//            List<ActionAuthoritie> buttonAuthList = new ArrayList<ActionAuthoritie>();
//            if (null != buttonList && buttonList.length > 0) {
//                buttonAuthList = Arrays.asList(buttonList);
//                loginUser.setActionList(buttonAuthList);
//            }
//
//            // 菜单权限
//            String menuAuthServicesUrl = SystemServerManager.OrgServerManager.getMenuAuth();
//            RsResponse<PageAuthoritie[]> menuAuthResponse = RestClientUtil.post(menuAuthServicesUrl, authParam, new TypeReference<RsResponse<PageAuthoritie[]>>() {
//            });
//            PageAuthoritie[] menuList = menuAuthResponse.getResult();
//            List<PageAuthoritie> menuAuthList = new ArrayList<PageAuthoritie>();
//            if (null != menuList && menuList.length > 0) {
//                menuAuthList = Arrays.asList(menuList);
//                loginUser.setMenuPageList(menuAuthList);
//            }
//
//            // 页面权限
//            String pageAuthServicesUrl = SystemServerManager.OrgServerManager.getPageAuth();
//            RsResponse<PageAuthoritie[]> pageAuthResponse = RestClientUtil.post(pageAuthServicesUrl, authParam, new TypeReference<RsResponse<PageAuthoritie[]>>() {
//            });
//            PageAuthoritie[] pageResponseList = pageAuthResponse.getResult();
//            List<PageAuthoritie> pageAuthList = new ArrayList<PageAuthoritie>();
//            if (null != pageResponseList && pageResponseList.length > 0) {
//                pageAuthList = Arrays.asList(pageResponseList);
//                loginUser.setPageList(pageAuthList);
//            }
//        }
//
//        SessionManger.getSessionManger(request).setValue(SessionManger.USER_SESSION_KEY, loginUser);
//        String systemListServicesUrl = ConfigManager.getMskOrgServices()+ConfigManager.getOrgMenuServices();
//        RsResponse<com.msk.org.bean.SystemModel[]> systemModelResponse = RestClientUtil.post(systemListServicesUrl,pageListParam,new TypeReference<RsResponse<com.msk.org.bean.SystemModel[]>>(){});
//        com.msk.org.bean.SystemModel[] systemModelList = systemModelResponse.getResult();
//        SessionManger.getSessionManger(request).setValue("systemList", systemModelList);
//        SessionManger.getSessionManger(request).setValue("pageList", pageList);
//        return this.redirect("main");
//    }
//
//    /**
//     * 用户登陆操作
//     *
//     * @param userName 用户名称
//     * @param password 登陆密码
//     * @param systemCode 系统编码
//     * @param model Model
//     * @return 主页面
//     */
//    @RequestMapping(value = "loginForSupp",
//            method = RequestMethod.POST)
//    public String loginForSupp(String userName, String password, String systemCode, String userType, Model model,
//                               HttpServletResponse response) {
//        // logger.debug("登录操作");
//        String systemModel = SYSTEM_CODE_MODEL_MAP.get(systemCode);
//        if (StringUtil.isEmpty(userName) || StringUtil.isEmpty(password)) {
//            model.addAttribute("message", "登录失败：请输入用户名和密码");
//            return this.login(systemModel, model, response);
//        }
//        //login info
//        LoginParam loginParam = new LoginParam();
//        loginParam.setUserName(userName);
//        loginParam.setPassword(password);
//        loginParam.setSystemCode(systemCode);
//        loginParam.setUserType(userType);
//        //调用登录接口
//        String loginServicesUrl = ConfigManager.getMskOrgServices()+ConfigManager.getOrgLoginSuppServices();
//        RsRequest<LoginParam> requestParam = new RsRequest<>();
//        requestParam.setParam(loginParam);
//
//        RsResponse<LoginUser> loginUserResponse = RestClientUtil.post(loginServicesUrl,requestParam,new TypeReference<RsResponse<LoginUser>>(){});
//        LoginUser loginUser = loginUserResponse.getResult();
//
//        if (loginUser == null) {
//            model.addAttribute("message", "登录失败：用户名或者密码不正确");
//            return this.login(systemModel, model, response);
//        }
//        SessionManger.getSessionManger(request).setValue("systemCode", systemCode);
//        SessionManger.getSessionManger(request).setValue("systemModel", systemModel);
//
//        RsRequest<String> pageListParamTo = new RsRequest<String>();
//        pageListParamTo.setParam(systemCode);
//
//        String pageListServicesUrl = ConfigManager.getMskOrgServices()+ConfigManager.getOrgPageServices();
//        RsResponse<OrgPage[]> pageListResponse= RestClientUtil.post(pageListServicesUrl,pageListParamTo,new TypeReference<RsResponse<OrgPage[]>>(){});
//        OrgPage[] pageList = pageListResponse.getResult();
//
//        if (pageList==null||pageList.length== NumberConst.IntDef.INT_ZERO) {
//            model.addAttribute("message", "登录失败：该用户无权访问本系统");
//            return this.login(systemModel, model, response);
//        }
//
//        loginUser.setUserType(userType);
//        if(needAuthCheck) {
//            // 按钮权限
//            RsRequest<String> authParam = new RsRequest<String>();
//            authParam.setLoginId(userName);
//            String buttonAuthServicesUrl = SystemServerManager.OrgServerManager.getButtonAuth();
//            RsResponse<ActionAuthoritie[]> buttonAuthResponse = RestClientUtil.post(buttonAuthServicesUrl, authParam, new TypeReference<RsResponse<ActionAuthoritie[]>>() {
//            });
//            ActionAuthoritie[] buttonList = buttonAuthResponse.getResult();
//            List<ActionAuthoritie> buttonAuthList = new ArrayList<ActionAuthoritie>();
//            if (null != buttonList && buttonList.length > 0) {
//                buttonAuthList = Arrays.asList(buttonList);
//                loginUser.setActionList(buttonAuthList);
//            }
//
//            // 菜单权限
//            String menuAuthServicesUrl = SystemServerManager.OrgServerManager.getMenuAuth();
//            RsResponse<PageAuthoritie[]> menuAuthResponse = RestClientUtil.post(menuAuthServicesUrl, authParam, new TypeReference<RsResponse<PageAuthoritie[]>>() {
//            });
//            PageAuthoritie[] menuList = menuAuthResponse.getResult();
//            List<PageAuthoritie> menuAuthList = new ArrayList<PageAuthoritie>();
//            if (null != menuList && menuList.length > 0) {
//                menuAuthList = Arrays.asList(menuList);
//                loginUser.setMenuPageList(menuAuthList);
//            }
//
//            // 页面权限
//            String pageAuthServicesUrl = SystemServerManager.OrgServerManager.getPageAuth();
//            RsResponse<PageAuthoritie[]> pageAuthResponse = RestClientUtil.post(pageAuthServicesUrl, authParam, new TypeReference<RsResponse<PageAuthoritie[]>>() {
//            });
//            PageAuthoritie[] pageResponseList = pageAuthResponse.getResult();
//            List<PageAuthoritie> pageAuthList = new ArrayList<PageAuthoritie>();
//            if (null != pageResponseList && pageResponseList.length > 0) {
//                pageAuthList = Arrays.asList(pageResponseList);
//                loginUser.setPageList(pageAuthList);
//            }
//        }
//        SessionManger.getSessionManger(request).setValue(SessionManger.USER_SESSION_KEY, loginUser);
//
//        String systemListServicesUrl = ConfigManager.getMskOrgServices()+ConfigManager.getOrgMenuServices();
//        RsResponse<com.msk.org.bean.SystemModel[]> systemModelResponse = RestClientUtil.post(systemListServicesUrl,pageListParamTo,new TypeReference<RsResponse<com.msk.org.bean.SystemModel[]>>(){});
//        com.msk.org.bean.SystemModel[] systemModelList = systemModelResponse.getResult();
//
//        SessionManger.getSessionManger(request).setValue("systemList",systemModelList);
//        // SessionManger.getSessionManger(request).setValue("pageList", pageList);
//        return this.redirect("main");
//    }
//
//    /**
//     * 退出操作
//     *
//     * @return 登陆页面
//     */
//    @RequestMapping(value = "logout",
//            method = RequestMethod.GET)
//    public String logout() {
//        String systemCode = StringUtil.toSafeString(SessionManger.getSessionManger(request).getValue("systemCode"));
//        String type = StringUtil.toSafeString(SYSTEM_CODE_MODEL_MAP.get(systemCode));
//
//        boolean isSupp = false; // 供应商标志
//        LoginUser loginUser = (LoginUser) SessionManger.getSessionManger(request)
//                .getValue(SessionManger.USER_SESSION_KEY);
//        if (loginUser != null && CodeMasterConst.LoginUserType.SUPPLIER_USER_TYPE.equals(loginUser.getUserType())) {
//            // 供应商用户时
//            isSupp = true;
//        }
//
//        if (SystemModel.SYSTEM_MODEL_DS_SUPP.equalsIgnoreCase(type)) {
//            if (isSupp) {
//                // 供应商时，目前供应商只能看到卖家供应链后台
//                type = SystemModel.SYSTEM_MODEL_DS_SUPP;
//            } else {
//                // 非供应商时
//                type = SystemModel.SYSTEM_MODEL_DS;
//            }
//        }
//
//        // 类型为空时转向默认
//        if (StringUtil.isEmpty(type)) {
//            type = SystemModel.SYSTEM_MODEL_SO;
//        }
//
//        // 清除Session值
//        SessionManger.getSessionManger(request).invalidate();
//        // return this.login(type, model, response);
//        return redirect(type.toLowerCase() + "/login");
//    }
}