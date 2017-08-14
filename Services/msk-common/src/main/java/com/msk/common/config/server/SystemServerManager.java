package com.msk.common.config.server;

import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.consts.StringConst;
import com.hoperun.core.utils.StringUtil;
import com.msk.common.bean.ConfigParam;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.cache.ConfigConstCacheManager;
import com.msk.common.utils.RestClientUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jackjiang on 16/6/28.
 */
public final class SystemServerManager {
    private static Logger logger = LoggerFactory.getLogger(SystemServerManager.class);

    /** Config Server URL */
    private static String CONFIG_SERVER;
    /** 环境 */
    private static String ENVIRONMENT;
    /** Config Constant URL */
    private static String CONFIG_LOAD_CONFIG_CONSTANT;
    /** Code Master URL */
    private static String CONFIG_LOAD_CODE_MASTER;

    private static String MODEL_NAME;

    public static void setModelName(String modelName) {
        MODEL_NAME = modelName;
    }

    /**
     * 设置Config Server Code Master URL,值来自Config Properties文件
     * 
     * @param configLoadCodeMaster config load code master
     */
    public static void setConfigLoadCodeMaster(String configLoadCodeMaster) {
        CONFIG_LOAD_CODE_MASTER = configLoadCodeMaster;
    }

    /**
     * 获得Config Load Code Master URL
     * 
     * @return config load code master
     */
    public static String getConfigLoadCodeMaster() {
        return CONFIG_SERVER + CONFIG_LOAD_CODE_MASTER;
    }

    /**
     * 设置环境值,值来自Config Properties文件
     * 
     * @param environment 环境值
     */
    public static void setEnvironment(String environment) {
        ENVIRONMENT = environment;
    }

    /**
     * Config Server URL,值来自Config Properties文件
     * 
     * @param configServer Config Server
     */
    public static void setConfigServer(String configServer) {
        CONFIG_SERVER = configServer;
    }

    /**
     * Config Constant URL,值来自Config Properties文件
     * 
     * @param configLoadConfigConstant Config Constant URL
     */
    public static void setConfigLoadConfigConstant(String configLoadConfigConstant) {
        CONFIG_LOAD_CONFIG_CONSTANT = configLoadConfigConstant;
    }

    /**
     * 获得Config Constant URL,这个URL包含Config Server URL和Constant URL
     * 
     * @return Config Constant URL
     */
    public static String getConfigConstantUrl() {
        return CONFIG_SERVER + CONFIG_LOAD_CONFIG_CONSTANT;
    }

    /**
     * 创建Config Param
     * 
     * @param moduleName 模块名称,来自常量定义
     * @param key 方法名称
     * @return Config Param
     */
    private static ConfigParam getConfigParam(String moduleName, String key) {
        // ConfigParam param = new ConfigParam();
        // param.setType("ConfigConstant");
        // param.setEnvironment(ENVIRONMENT);
        // param.setModelName(moduleName);
        // param.setKey(key);
        // return param;
        return getCommonConfigParam(moduleName, key, "ConfigConstant");
    }

    public static ConfigParam getCodeMasterParam(String moduleName, String key) {
        // ConfigParam param = new ConfigParam();
        // param.setType("CodeMaster");
        // param.setEnvironment(ENVIRONMENT);
        // param.setModelName(moduleName);
        // param.setKey(key);
        // return param;
        return getCommonConfigParam(moduleName, key, "CodeMaster");
    }

    private static ConfigParam getCommonConfigParam(String moduleName, String key, String type) {
        ConfigParam param = new ConfigParam();
        param.setType(type);
        param.setEnvironment(ENVIRONMENT);
        param.setModelName(moduleName);
        param.setKey(key);
        return param;
    }

    public static String getModuleRootUrl() {
        RsRequest<ConfigParam> request = new RsRequest<>();
        request.setParam(getConfigParam(MODEL_NAME, StringConst.EMPTY));
        TypeReference typeReference = new TypeReference<RsResponse<HashMap<String, String>>>() {};
        RsResponse<HashMap<String, String>> response = RestClientUtil.post(getConfigConstantUrl(), request,
            typeReference);
        HashMap<String, String> result = response.getResult();
        String moduleRootUrl = result.get(ENVIRONMENT) + result.get(MODEL_NAME);
        return moduleRootUrl.replaceAll("api", "").replaceAll("v1", "");

    }

    /**
     * 获取服务地址
     */
    private static String getServerUrl(String moduleName, String key) {
        String cacheKey = ENVIRONMENT + "_" + moduleName + "_" + key;
        // String cacheValue = ConfigConstCacheManager.getConfigConstCache(cacheKey);
        // logger.info("Environment:"+ENVIRONMENT+",ModuleName:"+moduleName+",Key:"+key);
        // if(!StringUtil.isEmpty(cacheValue)){
        // logger.info("从缓存中取数据");
        // return cacheValue;
        // }
        logger.info("从Config Server 中取数据");
        RsRequest<ConfigParam> request = new RsRequest<>();
        request.setParam(getConfigParam(moduleName, key));
        TypeReference typeReference = new TypeReference<RsResponse<HashMap<String, String>>>() {};
        RsResponse<HashMap<String, String>> response = RestClientUtil.post(getConfigConstantUrl(), request,
            typeReference);
        HashMap<String, String> result = response.getResult();
        String cacheValue = result.get(ENVIRONMENT) + result.get(moduleName) + result.get(key);
        // ConfigConstCacheManager.putConfigConstCache(cacheKey,cacheValue);
        return cacheValue;
    }

    /**
     * mq
     */
    private static String getMqQueues(String moduleName, String key) {
        String cacheKey = ENVIRONMENT + "_" + moduleName + "_" + key;
        // String cacheValue = ConfigConstCacheManager.getConfigConstCache(cacheKey);
        // logger.info("Environment:"+ENVIRONMENT+",ModuleName:"+moduleName+",Key:"+key);
        // if(!StringUtil.isEmpty(cacheValue)){
        // logger.info("从缓存中取数据");
        // return cacheValue;
        // }
        logger.info("从Config Server 中取数据");
        RsRequest<ConfigParam> request = new RsRequest<>();
        request.setParam(getConfigParam(moduleName, key));
        TypeReference typeReference = new TypeReference<RsResponse<HashMap<String, String>>>() {};
        RsResponse<HashMap<String, String>> response = RestClientUtil.post(getConfigConstantUrl(), request,
            typeReference);
        HashMap<String, String> result = response.getResult();
        String cacheValue = result.get(moduleName) + "-" + result.get(key);
        // ConfigConstCacheManager.putConfigConstCache(cacheKey,cacheValue);
        return cacheValue;
    }

    /**
     * Comm
     */
    private static String getCommonServerUrl(String moduleName, String key) {
        String cacheKey = ENVIRONMENT + "_" + moduleName + "_" + key;
        // String cacheValue = ConfigConstCacheManager.getConfigConstCache(cacheKey);
        // logger.info("Environment:"+ENVIRONMENT+",ModuleName:"+moduleName+",Key:"+key);
        // if(!StringUtil.isEmpty(cacheValue)){
        // logger.info("从缓存中取数据");
        // return cacheValue;
        // }
        logger.info("从Config Server 中取数据");
        RsRequest<ConfigParam> request = new RsRequest<>();
        request.setParam(getConfigParam(moduleName, key));
        TypeReference typeReference = new TypeReference<RsResponse<HashMap<String, String>>>() {};
        RsResponse<HashMap<String, String>> response = RestClientUtil.post(getConfigConstantUrl(), request,
            typeReference);
        HashMap<String, String> result = response.getResult();
        String cacheValue = result.get(key);
        // ConfigConstCacheManager.putConfigConstCache(cacheKey,cacheValue);
        return cacheValue;
    }

    private static String getV1ServerUrl(String moduleName, String key) {
        String cacheKey = ENVIRONMENT + "_" + moduleName + "_" + key;
        // String cacheValue = ConfigConstCacheManager.getConfigConstCache(cacheKey);
        // if(!StringUtil.isEmpty(cacheValue)){
        // return cacheValue;
        // }
        RsRequest<ConfigParam> request = new RsRequest<>();
        request.setParam(getConfigParam(moduleName, key));
        TypeReference typeReference = new TypeReference<RsResponse<HashMap<String, String>>>() {};
        RsResponse<HashMap<String, String>> response = RestClientUtil.post(getConfigConstantUrl(), request,
            typeReference);
        HashMap<String, String> result = response.getResult();
        String cacheValue = result.get(moduleName) + result.get(key);
        // ConfigConstCacheManager.putConfigConstCache(cacheKey,cacheValue);
        return cacheValue;
    }

    /**
     * Org Server Manager,里面包含ORG所有的服务地址
     */
    public static class OrgServerManager {
        public static String getLogin() {
            return getServerUrl(SystemServerDef.OrgServer.MODULE_NAME,
                SystemServerDef.OrgServer.USER_LOGIN_SERVER_NAME);
        }

        public static String getLoginEmployInfo() {
            return getServerUrl(SystemServerDef.OrgServer.MODULE_NAME,
                SystemServerDef.OrgServer.GET_LOGIN_EMPLOY_INFO_SERVER_NAME);
        }

        public static String getSearchMenuList() {
            return getServerUrl(SystemServerDef.OrgServer.MODULE_NAME,
                SystemServerDef.OrgServer.SEARCH_MENU_LIST_SERVER_NAME);
        }

        public static String getSearchPageList() {
            return getServerUrl(SystemServerDef.OrgServer.MODULE_NAME,
                SystemServerDef.OrgServer.SEARCH_PAGE_LIST_SERVER_NAME);
        }

        public static String getSearchSystemModule() {
            return getServerUrl(SystemServerDef.OrgServer.MODULE_NAME,
                SystemServerDef.OrgServer.SEARCH_SYSTEM_MODULE_SERVER_NAME);
        }

        public static String getLoginUserInfo() {
            return getServerUrl(SystemServerDef.OrgServer.MODULE_NAME,
                SystemServerDef.OrgServer.LOGIN_USER_INFO_SERVER);
        }

        public static String getDepartmentRole() {
            return getServerUrl(SystemServerDef.OrgServer.MODULE_NAME,
                SystemServerDef.OrgServer.DEPARTMENT_ROLE_SERVER);
        }

        /***/
        public static String getModifyPassword() {
            return getServerUrl(SystemServerDef.OrgServer.MODULE_NAME,
                SystemServerDef.OrgServer.MODIFY_PASSWORD_SERVER_NAME);
        }

    }

    /**
     * Bs Server Manager,里面包含所有的服务地址
     */
    public static class BsServerManage {
        /**
         * 获得菜单的URL
         *
         * @return 菜单URL
         */
        public static String getSearch() {
            return getServerUrl(SystemServerDef.BsServer.MODULE_NAME, SystemServerDef.BsServer.SEARCH_SERVER_NAME);
        }

        /**
         * 获得编辑卖家账号的URL
         *
         * @return 编辑卖家账号URL
         */
        public static String getEditAccount() {
            return getServerUrl(SystemServerDef.BsServer.MODULE_NAME,
                SystemServerDef.BsServer.EDIT_ACCOUNT_SERVER_NAME);
        }

        /**
         * 获得查询卖家店铺账号的URL
         *
         * @return 查询卖家店铺账号URL
         */
        public static String getQueryShopAccount() {
            return getServerUrl(SystemServerDef.BsServer.MODULE_NAME,
                SystemServerDef.BsServer.QUERY_SHOP_ACCOUNT_SERVER_NAME);
        }

        /**
         * 获得编辑卖家的URL
         *
         * @return 编辑卖家URL
         */
        public static String getEditSeller() {
            return getServerUrl(SystemServerDef.BsServer.MODULE_NAME, SystemServerDef.BsServer.EDIT_SELLER_SERVER_NAME);
        }

        /**
         * 获得编辑买手冻品管家的买家信息的URL
         *
         * @return 编辑买手冻品管家的买家信息URL
         */
        public static String getEditExclusive() {
            return getServerUrl(SystemServerDef.BsServer.MODULE_NAME,
                SystemServerDef.BsServer.EDIT_EXCLUSIVE_SERVER_NAME);
        }

        /**
         * 获得更新PSD的URL
         *
         * @return 更新PSDURL
         */
        public static String getUpdatePsd() {
            return getServerUrl(SystemServerDef.BsServer.MODULE_NAME, SystemServerDef.BsServer.UPDATE_PSD_SERVER_NAME);
        }

        /**
         * 获得查询买手冻品管家的买家信息的URL
         *
         * @return 查询买手冻品管家的买家信息URL
         */
        public static String getQueryExclusive() {
            return getServerUrl(SystemServerDef.BsServer.MODULE_NAME,
                SystemServerDef.BsServer.QUERY_EXCLUSIVE_SERVER_NAME);
        }

        /**
         * 查询买家的管家详情列表
         *
         * @return查询买家的管家详情列表
         */
        public static String getSearchHouseInfo() {
            return getServerUrl(SystemServerDef.BsServer.MODULE_NAME,
                SystemServerDef.BsServer.SEARCH_HOUSE_INFO_SERVER_NAME);
        }

        /**
         * 解除买手和冻品管家之间的关系
         *
         * @return解除买手和冻品管家之间的关系
         */
        public static String getReleaseBuyer() {
            return getServerUrl(SystemServerDef.BsServer.MODULE_NAME,
                SystemServerDef.BsServer.RELEASE_BUYER_SERVER_NAME);
        }

        /**
         *
         * @return
         */
        public static String getReleaseHouseKeeper() {
            return getServerUrl(SystemServerDef.BsServer.MODULE_NAME,
                SystemServerDef.BsServer.RELEASE_HOUSE_KEEPER_SERVER_NAME);
        }

        /**
         * 同步买家模块买手信息
         *
         * @return同步买家模块买手信息
         */
        public static String getSyncBuyerInfo() {
            return getServerUrl(SystemServerDef.BsServer.MODULE_NAME, SystemServerDef.BsServer.SYNC_BUYER_INFO);
        }

        /**
         * 查询买家对应的买手信息
         *
         * @return查询买家对应的买手信息
         */
        public static String getSearchBuyerId() {
            return getServerUrl(SystemServerDef.BsServer.MODULE_NAME, SystemServerDef.BsServer.SEARCH_BUYER_ID);
        }

        /**
         * 查询冻品管家销售信息
         *
         * @return查询冻品管家销售信息
         */
        public static String getGetSaleByBuyerId() {
            return getServerUrl(SystemServerDef.BsServer.MODULE_NAME,
                SystemServerDef.BsServer.GET_SALE_BY_BUYER_ID_SERVER_NAME);
        }

        /**
         * 查询冻品管家销售信息
         *
         * @return查询冻品管家销售信息
         */
        public static String getGetHouseInfoById() {
            return getServerUrl(SystemServerDef.BsServer.MODULE_NAME,
                SystemServerDef.BsServer.GET_HOUSE_INFO_BY_ID_SERVER_NAME);
        }

        /**
         * 根据用户帐号获得专属买手ID和CODE
         *
         * @return根据用户帐号获得专属买手ID和CODE
         */
        public static String getFindSlcodeAndDisList() {
            return getServerUrl(SystemServerDef.BsServer.MODULE_NAME,
                SystemServerDef.BsServer.FIND_SL_CODE_AND_DIS_LIST_SERVER_NAME);
        }

        /**
         * 更新买家和冻品管家关系有效期时间
         */
        public static String getUpdateBuyerValidDate() {
            return getServerUrl(SystemServerDef.BsServer.MODULE_NAME,
                SystemServerDef.BsServer.UPDATE_BUYER_VALID_DATE_SERVER_NAME);
        }

        /**
         * 找管家-我的服务经历以及心得查询
         */
        public static String getSearchHkExperience() {
            return getServerUrl(SystemServerDef.BsServer.MODULE_NAME,
                SystemServerDef.BsServer.SEARCH_HK_EXPERIENCE_SERVER_NAME);
        }

        /**
         * 买家管家关系解除接口
         */
        public static String getUnbindRelation() {
            return getServerUrl(SystemServerDef.BsServer.MODULE_NAME,
                SystemServerDef.BsServer.UNBIND_RELATION_SERVER_NAME);
        }

        /**
         * 查询买手账号和基本信息
         */
        public static String getSearchBsInfo() {
            return getServerUrl(SystemServerDef.BsServer.MODULE_NAME, SystemServerDef.BsServer.SEARCH_BS_INFO_SERVER_NAME);
        }

    }

    /**
     * BatchManage Server Manager,里面包含所有的服务地址
     */
    public static class BatchManageServerManage {
        /**
         * 获得启动Batch的URL
         *
         * @return 启动BatchURL
         */
        public static String getSaveBatch() {
            return getServerUrl(SystemServerDef.BatchManage.MODULE_NAME,
                SystemServerDef.BatchManage.SAVE_BATCH_SERVER_NAME);
        }

        /**
         * 获得修改batch的URL
         *
         * @return 修改batchURL
         */
        public static String getModifyBatch() {
            return getServerUrl(SystemServerDef.BatchManage.MODULE_NAME,
                SystemServerDef.BatchManage.MODIFY_BATCH_SERVER_NAME);
        }
    }

    /**
     * Buyers Server Manager,里面包含所有的服务地址
     */
    public static class BuyersServerManage {
        /**
         * 获得查询买家信息的URL
         *
         * @return 查询买家信息URL
         */
        public static String getSearchBuyerInfo() {
            return getServerUrl(SystemServerDef.Buyers.MODULE_NAME,
                SystemServerDef.Buyers.SEARCH_BUYER_INFO_SERVER_NAME);
        }

        /**
         * 获得查询买家列表的URL
         *
         * @return 查询买家列表URL
         */
        public static String getFindBuyerList() {
            return getServerUrl(SystemServerDef.Buyers.MODULE_NAME, SystemServerDef.Buyers.FIND_BUYER_LIST_SERVER_NAME);
        }

        /**
         * 获得查询市场列表的URL
         *
         * @return 查询市场列表URL
         */
        public static String getFindMarketList() {
            return getServerUrl(SystemServerDef.Buyers.MODULE_NAME,
                SystemServerDef.Buyers.FIND_MARKET_LIST_SERVER_NAME);
        }

        /**
         * 获得买家注册的URL
         *
         * @return 买家注册URL
         */
        public static String getBuyerRegister() {
            return getServerUrl(SystemServerDef.Buyers.MODULE_NAME, SystemServerDef.Buyers.BUYER_REGISTER_SERVER_NAME);
        }

        /**
         * 获得买家登录URL
         *
         * @return 买家登录URL
         */
        public static String getBuyerLogin() {
            return getServerUrl(SystemServerDef.Buyers.MODULE_NAME, SystemServerDef.Buyers.BUYER_LOGIN_SERVER_NAME);
        }

        /**
         * 获得修改密码URL
         *
         * @return 修改密码URL
         */
        public static String getResetPasswordUpdate() {
            return getServerUrl(SystemServerDef.Buyers.MODULE_NAME,
                SystemServerDef.Buyers.RESET_PASSWORD_UPDATE_SERVER_NAME);
        }

        /**
         * 获得买家更新URL
         *
         * @return 买家更新URL
         */
        public static String getBuyerUpdate() {
            return getServerUrl(SystemServerDef.Buyers.MODULE_NAME, SystemServerDef.Buyers.BUYER_UPDATE_SERVER_NAME);
        }

        /**
         * 获得前台手机验证成功后,通过手机号直接修改密码URL
         *
         * @return URL
         */
        public static String getBuyerUpdateByTel() {
            return getServerUrl(SystemServerDef.Buyers.MODULE_NAME,
                SystemServerDef.Buyers.BUYER_UPDATE_BYTEL_SERVER_NAME);
        }

        /**
         * 获得通路人员登录URL
         *
         * @return 通路人员登录URL
         */
        public static String getAccessLogin() {
            return getServerUrl(SystemServerDef.Buyers.MODULE_NAME, SystemServerDef.Buyers.ACCESS_LOGIN_SERVER_NAME);
        }

        /**
         * 获得SSO登录验证接口URL
         *
         * @return SSO登录验证接口URL
         */
        public static String getSsoLogin() {
            return getServerUrl(SystemServerDef.Buyers.MODULE_NAME, SystemServerDef.Buyers.SSO_LOGIN_SERVER_NAME);
        }

        /**
         * 获得买家基本信息更新接口URL
         *
         * @return 买家基本信息更新接口URL
         */
        public static String getBuyerInfoUpdate() {
            return getServerUrl(SystemServerDef.Buyers.MODULE_NAME,
                SystemServerDef.Buyers.BUYER_INFO_UPDATE_SERVER_NAME);
        }

        /**
         * 获得查询买家基本信息URL
         *
         * @return 查询买家基本信息URL
         */
        public static String getFindBuyerDetailInfo() {
            return getServerUrl(SystemServerDef.Buyers.MODULE_NAME,
                SystemServerDef.Buyers.find_buyer_detail_info_SERVER_NAME);
        }

        /**
         * 获得根据指定条件获取买家URL
         *
         * @return 根据指定条件获取买家URL
         */
        public static String getFindConditionBuyerList() {
            return getServerUrl(SystemServerDef.Buyers.MODULE_NAME,
                SystemServerDef.Buyers.FIND_CONDITION_BUYER_LIST_SERVER_NAME);
        }

        /**
         * 获得根据指定条件获取批发市场的URL
         *
         * @return 根据指定条件获取批发市场URL
         */
        public static String getFindConditionMarketTerminal() {
            return getServerUrl(SystemServerDef.Buyers.MODULE_NAME,
                SystemServerDef.Buyers.Find_Condition_Market_Terminal_SERVER_NAME);
        }

        /**
         * 获得根据指定条件获取菜场的URL
         *
         * @return 根据指定条件获取菜场URL
         */
        public static String getFindConditionMarketFood() {
            return getServerUrl(SystemServerDef.Buyers.MODULE_NAME,
                SystemServerDef.Buyers.Find_Condition_Market_Food_SERVER_NAME);
        }

        /**
         * 获得查询批发市场或菜场中的买家信息的URL
         *
         * @return 查询批发市场或菜场中的买家信息URL
         */
        public static String getFindBuyerByMarketId() {
            return getServerUrl(SystemServerDef.Buyers.MODULE_NAME,
                SystemServerDef.Buyers.FIND_BUYER_BY_MARKETID_SERVER_NAME);
        }

        /**
         * 获得买家基本和全部销售对象信息的URL
         *
         * @return 买家基本和全部销售对象信息URL
         */
        public static String getFindBasicInfo() {
            return getServerUrl(SystemServerDef.Buyers.MODULE_NAME, SystemServerDef.Buyers.FIND_BASIC_INFO_SERVER_NAME);
        }

        /**
         * 获得批发市场信息更新接口的URL
         *
         * @return 批发市场信息更新接口URL
         */
        public static String getByMarketTerUpdate() {
            return getServerUrl(SystemServerDef.Buyers.MODULE_NAME,
                SystemServerDef.Buyers.BY_MARKETTER_UPDATE_SERVER_NAME);
        }

        /**
         * 获得菜场信息更新的URL
         *
         * @return 菜场信息更新URL
         */
        public static String getByMarketFoodUpdate() {
            return getServerUrl(SystemServerDef.Buyers.MODULE_NAME,
                SystemServerDef.Buyers.BY_MARKET_FOOD_UPDATE_SERVER_NAME);
        }

        /**
         * 获得的买家经营产品类别更新URL
         *
         * @return 买家经营产品类别更新URL
         */
        public static String getBuyerPdClassificationUpdate() {
            return getServerUrl(SystemServerDef.Buyers.MODULE_NAME,
                SystemServerDef.Buyers.BUYER_PD_CLASSIFICATION_UPDATE_SERVER_NAME);
        }

        /**
         * 获得买家销售产品信息的URL
         *
         * @return 买家销售产品信息URL
         */
        public static String getBuyerPdClassificationFind() {
            return getServerUrl(SystemServerDef.Buyers.MODULE_NAME,
                SystemServerDef.Buyers.BUYER_PDCLASSIFICATION_FIND_SERVER_NAME);
        }

        /**
         * 获得买家销售对象更新的URL
         *
         * @return 买家销售对象更新URL
         */
        public static String getBuyerSalesTargetUpdate() {
            return getServerUrl(SystemServerDef.Buyers.MODULE_NAME,
                SystemServerDef.Buyers.BUYER_SALES_TARGET_UPDATE_SERVER_NAME);
        }

        /**
         * 获得买家销售对象查询接口的URL
         *
         * @return 买家销售对象查询接口URL
         */
        public static String getBuyerSalesTargetFindList() {
            return getServerUrl(SystemServerDef.Buyers.MODULE_NAME,
                SystemServerDef.Buyers.BUYER_SALES_TARGET_FIND_LIST_SERVER_NAME);
        }

        /**
         * 获得买家证照信息更新接口的URL
         *
         * @return 买家证照信息更新接口URL
         */
        public static String getBuyerLicenceUpdate() {
            return getServerUrl(SystemServerDef.Buyers.MODULE_NAME,
                SystemServerDef.Buyers.BUYER_LICENCE_UPDATE_SERVER_NAME);
        }

        /**
         * 获得买家证照信息查询接口的URL
         *
         * @return 买家证照信息查询接口URL
         */
        public static String getBuyerLicenceFind() {
            return getServerUrl(SystemServerDef.Buyers.MODULE_NAME,
                SystemServerDef.Buyers.BUYER_LICENCE_FIND_SERVER_NAME);
        }

        /**
         * 获得买家证照图片更新接口的URL
         *
         * @return 买家证照图片更新接口URL
         */
        public static String getBuyerLicencePicUpdate() {
            return getServerUrl(SystemServerDef.Buyers.MODULE_NAME,
                SystemServerDef.Buyers.BUYER_LICENCE_PIC_UPDATE_SERVER_NAME);
        }

        /**
         * 获得的买家证照图片信息URL
         *
         * @return 买家证照图片信息URL
         */
        public static String getBuyerLicencePicFind() {
            return getServerUrl(SystemServerDef.Buyers.MODULE_NAME,
                SystemServerDef.Buyers.BUYER_LICENCE_PIC_FIND_SERVER_NAME);
        }

        /**
         * 获得买家雇员信息更新接口的URL
         *
         * @return 买家雇员信息更新接口URL
         */
        public static String getBuyerEmployeeUpdate() {
            return getServerUrl(SystemServerDef.Buyers.MODULE_NAME,
                SystemServerDef.Buyers.BUYER_EMPLOYEE_UPDATE_SERVER_NAME);
        }

        /**
         * 获得买家雇员信息查询接口的URL
         *
         * @return 买家雇员信息查询接口URL
         */
        public static String getBuyerEmployeeFindList() {
            return getServerUrl(SystemServerDef.Buyers.MODULE_NAME,
                SystemServerDef.Buyers.BUYER_EMPLOYEE_FIND_LIST_SERVER_NAME);
        }

        /**
         * 获得买家收货地址更新接口的URL
         *
         * @return 买家收货地址更新接口URL
         */
        public static String getBuyerReceiveAddrUpdate() {
            return getServerUrl(SystemServerDef.Buyers.MODULE_NAME,
                SystemServerDef.Buyers.BUYER_RECEIVE_ADDR_UPDATE_SERVER_NAME);
        }

        /**
         * 获得买家收货地址查询接口的URL
         *
         * @return 买家收货地址查询接口URL
         */
        public static String getBuyerReceiveAddrFindList() {
            return getServerUrl(SystemServerDef.Buyers.MODULE_NAME,
                SystemServerDef.Buyers.BUYER_RECEIVE_ADDR_FIND_LIST_SERVER_NAME);
        }

        /**
         * 获得通路注册雇员信息新增的URL
         *
         * @return 通路注册雇员信息新增URL
         */
        public static String getBuyerEmployeePhoneSave() {
            return getServerUrl(SystemServerDef.Buyers.MODULE_NAME,
                SystemServerDef.Buyers.BUYER_EMPLOYEE_PHONE_SAVE_SERVER_NAME);
        }

        /**
         * 获得通路注册雇员信息删除的URL
         *
         * @return 通路注册雇员信息删除URL
         */
        public static String getBuyerReceiveAddrDelete() {
            return getServerUrl(SystemServerDef.Buyers.MODULE_NAME,
                SystemServerDef.Buyers.BUYER_RECEIVE_ADDR_DELETE_SERVER_NAME);
        }

        /**
         * 获得通路注册雇员信息更新的URL
         *
         * @return 通路注册雇员信息更新URL
         */
        public static String getBuyerReceiveTimeUpdate() {
            return getServerUrl(SystemServerDef.Buyers.MODULE_NAME,
                SystemServerDef.Buyers.BUYER_RECEIVETIME_UPDATE_SERVER_NAME);
        }

        /**
         * 获得通路注册雇员信息查询的URL
         *
         * @return 通路注册雇员信息查询URL
         */
        public static String getBuyerReceiveTimeFind() {
            return getServerUrl(SystemServerDef.Buyers.MODULE_NAME,
                SystemServerDef.Buyers.BUYER_RECEIVE_TIME_FIND_SERVER_NAME);
        }

        /**
         * 获得添加新品种需求接口的URL
         *
         * @return 添加新品种需求接口URL
         */
        public static String getAddNewCategory() {
            return getServerUrl(SystemServerDef.Buyers.MODULE_NAME,
                SystemServerDef.Buyers.ADD_NEW_CATEGORY_SERVER_NAME);
        }

        /**
         * 获得买家产品品类和需求调研明细查询的URL
         *
         * @return 买家产品品类和需求调研明细查询URL
         */
        public static String getFindBuyerCatDetail() {
            return getServerUrl(SystemServerDef.Buyers.MODULE_NAME,
                SystemServerDef.Buyers.FIND_BUYER_CAT_DETAIL_SERVER_NAME);
        }

        /**
         * 获得买家产品品类和需求调研明细表更新接口的URL
         *
         * @return 买家产品品类和需求调研明细表更新接口URL
         */
        public static String getResearchCatDetailUpdate() {
            return getServerUrl(SystemServerDef.Buyers.MODULE_NAME,
                SystemServerDef.Buyers.RESEARCH_CAT_DETAIL_UPDATE_SERVER_NAME);
        }

        /**
         * 获得买家产品原种种源标准更新接口的URL
         *
         * @return 买家产品原种种源标准更新接口URL
         */
        public static String getUpdateBuyerStdOrg() {
            return getServerUrl(SystemServerDef.Buyers.MODULE_NAME,
                SystemServerDef.Buyers.UPDATE_BUYER_STD_ORG_SERVER_NAME);
        }

        /**
         * 获得买家产品加工技术标准更新接口的URL
         *
         * @return 买家产品加工技术标准更新接口URL
         */
        public static String getUpdateBuyerStdMct() {
            return getServerUrl(SystemServerDef.Buyers.MODULE_NAME,
                SystemServerDef.Buyers.UPDATE_BUYER_STD_MCT_SERVER_NAME);
        }

        /**
         * 获得产品原种种源标准查询接口的URL
         *
         * @return 产品原种种源标准查询接口URL
         */
        public static String getFindBuyerStdOrg() {
            return getServerUrl(SystemServerDef.Buyers.MODULE_NAME,
                SystemServerDef.Buyers.FIND_BUYER_STD_ORG_SERVER_NAME);
        }

        /**
         * 获得买家产品加工质量标准更新接口的URL
         *
         * @return 买家产品加工质量标准更新接口URL
         */
        public static String getUpdateBuyerStdTnc() {
            return getServerUrl(SystemServerDef.Buyers.MODULE_NAME,
                SystemServerDef.Buyers.UPDATE_BUYER_STD_TNC_SERVER_NAME);
        }

        /**
         * 获得买家产品饲养标准调研接口的URL
         *
         * @return 买家产品饲养标准调研接口URL
         */
        public static String getFindBuyerStdFed() {
            return getServerUrl(SystemServerDef.Buyers.MODULE_NAME,
                SystemServerDef.Buyers.FIND_BUYER_STD_FED_SERVER_NAME);
        }

        /**
         * 获得买家产品饲养标准调研更新接口的URL
         *
         * @return 接口URL
         */
        public static String getUpdateBuyerStdFed() {
            return getServerUrl(SystemServerDef.Buyers.MODULE_NAME,
                SystemServerDef.Buyers.UPDATE_BUYER_STD_FED_SERVER_NAME);
        }

        /**
         * 获得买家产品品种通用质量标准调研接口的URL
         *
         * @return 买家产品品种通用质量标准调研接口URL
         */
        public static String getFindBuyerStdGnq() {
            return getServerUrl(SystemServerDef.Buyers.MODULE_NAME,
                SystemServerDef.Buyers.FIND_BUYER_STD_GNQ_SERVER_NAME);
        }

        /**
         * 获得买家产品品种通用质量标准更新接口的URL
         *
         * @return 买家产品品种通用质量标准更新接口URL
         */
        public static String getUpdateBuyerStdGnq() {
            return getServerUrl(SystemServerDef.Buyers.MODULE_NAME,
                SystemServerDef.Buyers.UPDATE_BUYER_STD_GNQ_SERVER_NAME);
        }

        /**
         * 获得买家产品品种安全标准调研接口的URL
         *
         * @return 买家产品品种安全标准调研接口URL
         */
        public static String getFindBuyerStdSft() {
            return getServerUrl(SystemServerDef.Buyers.MODULE_NAME,
                SystemServerDef.Buyers.FIND_BUYER_STD_SFT_SERVER_NAME);
        }

        /**
         * 获得买家产品品种安全标准更新接口的URL
         *
         * @return 买家产品品种安全标准更新接口URL
         */
        public static String getUpdateBuyerStdSft() {
            return getServerUrl(SystemServerDef.Buyers.MODULE_NAME,
                SystemServerDef.Buyers.UPDATE_BUYER_STD_SFT_SERVER_NAME);
        }

        /**
         * 获得买家产品品种储存运输标准调研接口的URL
         *
         * @return 买家产品品种储存运输标准调研接口URL
         */
        public static String getFindBuyerStdTsp() {
            return getServerUrl(SystemServerDef.Buyers.MODULE_NAME,
                SystemServerDef.Buyers.FIND_BUYER_STD_TSP_SERVER_NAME);
        }

        /**
         * 获得买家产品品种储存运输标准更新接口的URL
         *
         * @return 买家产品品种储存运输标准更新接口URL
         */
        public static String getUpdateBuyerStdTsp() {
            return getServerUrl(SystemServerDef.Buyers.MODULE_NAME,
                SystemServerDef.Buyers.UPDATE_BUYER_STD_TSP_SERVER_NAME);
        }

        /**
         * 获得买家产品品种包装标准调研接口的URL
         *
         * @return 买家产品品种包装标准调研接口URL
         */
        public static String getFindBuyerStdNor() {
            return getServerUrl(SystemServerDef.Buyers.MODULE_NAME,
                SystemServerDef.Buyers.FIND_BUYER_STD_NOR_SERVER_NAME);
        }

        /**
         * 获得买家产品品种安全标准更新接口的URL
         *
         * @return 买家产品品种安全标准更新接口URL
         */
        public static String getUpdateBuyerStdNor() {
            return getServerUrl(SystemServerDef.Buyers.MODULE_NAME,
                SystemServerDef.Buyers.UPDATE_BUYER_STD_NOR_SERVER_NAME);
        }

        /**
         * 获得买家产品新品种调研状态查询的URL
         *
         * @return 买家产品新品种调研状态查询URL
         */
        public static String getFindBuyerResearchNew() {
            return getServerUrl(SystemServerDef.Buyers.MODULE_NAME,
                SystemServerDef.Buyers.FIND_BUYER_RESEARCH_NEW_SERVER_NAME);
        }

        /**
         * 获得买家产品品种调研状态查询的URL
         *
         * @return 买家产品品种调研状态查询URL
         */
        public static String getFindBuyerResearch() {
            return getServerUrl(SystemServerDef.Buyers.MODULE_NAME,
                SystemServerDef.Buyers.FIND_BUYER_RESEARCH_SERVER_NAME);
        }

        /**
         * 获得买家产品第三方品牌调研查询接口的URL
         *
         * @return 买家产品第三方品牌调研查询接口URL
         */
        public static String getFindBuyerBrand() {
            return getServerUrl(SystemServerDef.Buyers.MODULE_NAME,
                SystemServerDef.Buyers.FIND_BUYER_BRAND_SERVER_NAME);
        }

        /**
         * 获得买家产品品类和需求调研明细表更新接口的URL
         *
         * @return 买家产品品类和需求调研明细表更新接口URL
         */
        public static String getResearchBrandUpdate() {
            return getServerUrl(SystemServerDef.Buyers.MODULE_NAME,
                SystemServerDef.Buyers.RESEARCH_BRAND_UPDATE_SERVER_NAME);
        }

        /**
         * 获得买家产品品类和需求调研明细表删除接口的URL
         *
         * @return 买家产品品类和需求调研明细表删除接口URL
         */
        public static String getResearchBrandDelete() {
            return getServerUrl(SystemServerDef.Buyers.MODULE_NAME,
                SystemServerDef.Buyers.RESEARCH_BRAND_DELETE_SERVER_NAME);
        }

        /**
         *
         * 取得买家类型
         * 
         * @return取得买家类型
         *
         */
        public static String getFindBuyerTypesList() {
            return getServerUrl(SystemServerDef.Buyers.MODULE_NAME,
                SystemServerDef.Buyers.FIND_BUYER_TYPES_LIST_SERVER_NAME);
        }

        /**
         * 买家上线状态管控接口
         *
         * @return买家上线状态管控接口
         */
        public static String getMarketingStatusControl() {
            return getServerUrl(SystemServerDef.Buyers.MODULE_NAME,
                SystemServerDef.Buyers.MARKETING_STATUS_CONTROL_SERVER_NAME);
        }

        /**
         * 买家上线状态更新接口
         * 
         * @return买家上线状态更新接口
         */
        public static String getModifyMarketingStatus() {
            return getServerUrl(SystemServerDef.Buyers.MODULE_NAME,
                SystemServerDef.Buyers.MODIFY_MARKETING_STATUS_SERVER_NAME);
        }

        /**
         * 买家收货信息查询
         * 
         * @return买家收货信息查询
         */
        public static String getQueryDeliveryTimeAndPay() {
            return getServerUrl(SystemServerDef.Buyers.MODULE_NAME,
                SystemServerDef.Buyers.QUERY_DELIVERY_TIME_AND_PAY_SERVER_NAME);
        }

        /**
         * 买家配送地址信息查询
         * 
         * @return买家配送地址信息查询
         */
        public static String getQueryDeliveryAddr() {
            return getServerUrl(SystemServerDef.Buyers.MODULE_NAME,
                SystemServerDef.Buyers.QUERY_DELIVERY_ADDR_SERVER_NAME);
        }

        /**
         * 买家收货信息更新
         * 
         * @return买家收货信息更新
         */
        public static String getUpdateDeliveryTimeAndPay() {
            return getServerUrl(SystemServerDef.Buyers.MODULE_NAME,
                SystemServerDef.Buyers.UPDATE_DELIVERY_TIME_AND_PAY_SERVER_NAME);
        }

        /**
         * 买家配送地址信息删除
         * 
         * @return买家配送地址信息删除
         */
        public static String getDeleteDeliveryAddr() {
            return getServerUrl(SystemServerDef.Buyers.MODULE_NAME,
                SystemServerDef.Buyers.DELETE_DELIVERY_ADDR_SERVER_NAME);
        }

        /**
         * 更新买家配送信息
         * 
         * @return更新买家配送信息
         */
        public static String getUpdateDeliveryAddr() {
            return getServerUrl(SystemServerDef.Buyers.MODULE_NAME,
                SystemServerDef.Buyers.UPDATE_DELIVERY_ADDR_SERVER_NAME);
        }

        /**
         * 根据id获取买家配送地址信息
         * 
         * @return根据id获取买家配送地址信息
         */
        public static String getQueryDdeliveryAddrById() {
            return getServerUrl(SystemServerDef.Buyers.MODULE_NAME,
                SystemServerDef.Buyers.QUERY_DDELIVERY_ADDR_BY_ID_SERVER_NAME);
        }

        /**
         *
         * @return
         */
        public static String getSearchBuyerShop() {
            return getServerUrl(SystemServerDef.Buyers.MODULE_NAME, SystemServerDef.Buyers.SEARCH_BUYER_SHOP);
        }

        /**
         *
         * @return
         */
        public static String getQuerySearchExclusive() {
            return getServerUrl(SystemServerDef.Buyers.MODULE_NAME, SystemServerDef.Buyers.SEARCH_EXCLUSIVE);
        }

        /**
         *
         * @return
         */
        public static String getSearchBuyer() {
            return getServerUrl(SystemServerDef.Buyers.MODULE_NAME, SystemServerDef.Buyers.SEARCH_BUYER);
        }

        /**
         * 通过手机号码查询买家基本信息**@return通过手机号码查询买家基本信息
         */
        public static String getQueryBuyeryBasicInfoByTel() {
            return getServerUrl(SystemServerDef.Buyers.MODULE_NAME,
                SystemServerDef.Buyers.QUERY_BUYERY_BASIC_INFO_BYTEL_SERVER_NAME);
        }

        /**
         * 通过buyerId 查询管理工具
         * 
         * @return通过buyerId 查询管理工具
         */
        public static String getFindToolToBuyerId() {
            return getServerUrl(SystemServerDef.Buyers.MODULE_NAME,
                SystemServerDef.Buyers.FIND_TOOL_TO_BUYER_ID_SERVER_NAME);
        }

        /**
         * 保存管控工具和时间
         * 
         * @return保存管控工具和时间
         */
        public static String getSaveTOOLToDataBase() {
            return getServerUrl(SystemServerDef.Buyers.MODULE_NAME,
                SystemServerDef.Buyers.SAVE_TOOL_TO_DATA_BASE_SERVER_NAME);
        }

        /**
         * 保存管控工具和时间
         * 
         * @return保存管控工具和时间
         */
        public static String getGetBuyersAllTimesList() {
            return getServerUrl(SystemServerDef.Buyers.MODULE_NAME,
                SystemServerDef.Buyers.GET_BUYERS_ALL_TIMES_LIST_SERVER_NAME);
        }

        /**
         * 获取买家报表信息
         *
         * @获取买家报表信息
         */
        public static String getByReportManageQuery() {
            return getServerUrl(SystemServerDef.Buyers.MODULE_NAME,
                SystemServerDef.Buyers.BY_REPORT_MANAGE_QUERY_SERVER_NAME);
        }

        /**
         * 生成excel文件
         *
         * @生成excel文件
         */
        public static String getExcelFileCreate() {
            return getServerUrl(SystemServerDef.Buyers.MODULE_NAME,
                SystemServerDef.Buyers.EXCEL_FILE_CREATE_SERVER_NAME);
        }

        /**
         * 删除excel文件
         *
         * @删除excel文件
         */
        public static String getByReportDelete() {
            return getServerUrl(SystemServerDef.Buyers.MODULE_NAME,
                SystemServerDef.Buyers.BY_REPORT_DELETE_SERVER_NAME);
        }

        /**
         * 删除excel文件
         *
         * @删除excel文件
         */
        public static String getQueryBuyerCodeWithRingCode() {
            return getServerUrl(SystemServerDef.Buyers.MODULE_NAME,
                SystemServerDef.Buyers.QUERY_BUYER_CODE_WITH_RING_CODE_SERVER_NAME);
        }

        /**
         * 删除excel文件
         *
         * @删除excel文件
         */
        public static String getQueryBuyerIdByBuyerCode() {
            return getServerUrl(SystemServerDef.Buyers.MODULE_NAME,
                SystemServerDef.Buyers.QUERY_BUYER_ID_BY_BUYER_CODE_SERVER_NAME);
        }

        /**
         * 根据买家ID更新买家上线状态
         *
         * @根据买家ID更新买家上线状态
         */
        public static String getModifyMarketStatusByBuyerId() {
            return getServerUrl(SystemServerDef.Buyers.MODULE_NAME,
                SystemServerDef.Buyers.MODIFY_MARKET_STATUS_BY_BUYER_ID_SERVER_NAME);
        }

        /**
         * 更新销售期买家产品表接口
         */
        public static String getUpdateSalePeriod() {
            return getServerUrl(SystemServerDef.Buyers.MODULE_NAME,
                SystemServerDef.Buyers.UPDATE_SALE_PERIOD_SERVER_NAME);
        }

        /**
         * 新增发票
         */
        public static String getAddInvoice() {
            return getServerUrl(SystemServerDef.Buyers.MODULE_NAME, SystemServerDef.Buyers.ADD_INVOICE_SERVER_NAME);
        }

        /**
         * 删除发票
         */
        public static String getDeleteInvoice() {
            return getServerUrl(SystemServerDef.Buyers.MODULE_NAME, SystemServerDef.Buyers.DELETE_INVOICE_SERVER_NAME);
        }

        /**
         * 修改发票
         */
        public static String getUpdateInvoice() {
            return getServerUrl(SystemServerDef.Buyers.MODULE_NAME, SystemServerDef.Buyers.UPDATE_INVOICE_SERVER_NAME);
        }

        /**
         * 查询发票
         */
        public static String getSearchInvoice() {
            return getServerUrl(SystemServerDef.Buyers.MODULE_NAME, SystemServerDef.Buyers.SEARCH_INVOICE_SERVER_NAME);
        }

        /**
         * 买家信息列表查询接口
         */
        public static String getQueryBuyers() {
            return getServerUrl(SystemServerDef.Buyers.MODULE_NAME, SystemServerDef.Buyers.QUERY_BUYERS_SERVER_NAME);
        }

        /** 删除批发市场文件信息 */
        public static String getUpdateMarketTerminalFileInfo() {
            return getServerUrl(SystemServerDef.Buyers.MODULE_NAME,
                SystemServerDef.Buyers.UPDATE_MARKET_TERMINAL_FILE_INFO_SERVER_NAME);
        }

        /** 删除菜场文件信息 */
        public static String getUpdateMarketFoodFileInfo() {
            return getServerUrl(SystemServerDef.Buyers.MODULE_NAME,
                SystemServerDef.Buyers.UPDATE_MARKET_FOOD_FILE_INFO_SERVER_NAME);
        }

        /**
         * 买家经营产品类别查询接口
         */
        public static String getBuyerClassificationFind() {
            return getServerUrl(SystemServerDef.Buyers.MODULE_NAME, SystemServerDef.Buyers.BUYER_PD_CLASSIFICATION_FIND_SERVER_NAME);
        }

    }

    /**
     * Buyers Server Manager,里面包含所有的服务地址
     */
    public static class DistrictServerManage {
        /**
         * 获得查询县区信息的URL
         *
         * @return 查询县区信息URL
         */
        public static String getGetDistricts() {
            return getServerUrl(SystemServerDef.District.MODULE_NAME,
                SystemServerDef.District.GET_DISTRICTS_SERVER_NAME);
        }

        /**
         * 根据名称查询编码
         *
         * @return根据名称查询编码
         */
        public static String getGetCodesFromNames() {
            return getServerUrl(SystemServerDef.District.MODULE_NAME,
                SystemServerDef.District.GET_CODES_FROM_NAMES_SERVER_NAME);
        }

        /**
         *
         *
         * @return
         */
        public static String getDistrictQueryArea() {
            return getServerUrl(SystemServerDef.District.MODULE_NAME, SystemServerDef.District.DISTRICT_QUERY_AREA);
        }

        /**
         *
         *
         * @return
         */
        public static String getDistrictQueryCity() {
            return getServerUrl(SystemServerDef.District.MODULE_NAME, SystemServerDef.District.DISTRICT_QUERY_CITY);
        }

        /**
         *
         *
         * @return
         */
        public static String getDistrictQueryLgcsArea() {
            return getServerUrl(SystemServerDef.District.MODULE_NAME, SystemServerDef.District.DISTRICT_QUERY_LGCSAREA);
        }

        /**
         *
         *
         * @return
         */
        public static String getDistrictQueryProvince() {
            return getServerUrl(SystemServerDef.District.MODULE_NAME, SystemServerDef.District.DISTRICT_QUERY_PROVINCE);
        }

        /**
         *
         *
         * @return
         */
        public static String getDistrictQueryRegion() {
            return getServerUrl(SystemServerDef.District.MODULE_NAME, SystemServerDef.District.DISTRICT_QUERY_REGION);
        }

        /**
         *
         *
         * @return
         */
        public static String getDistrictQueryDistrict() {
            return getServerUrl(SystemServerDef.District.MODULE_NAME, SystemServerDef.District.DISTRICT_QUERY_DISTRICT);
        }
    }

    /**
     * Ds Server Manager,里面包含所有的服务地址
     */
    public static class DsServerManage {
        /**
         * 获得接口获取截止本月15日止的已列入供应计划尚未入库的供应量的URL
         *
         * @return 接口获取截止本月15日止的已列入供应计划尚未入库的供应量URL
         */
        public static String getQueryProductLotInfo() {
            return getServerUrl(SystemServerDef.Ds.MODULE_NAME, SystemServerDef.Ds.QUERY_PRODUCT_LOT_INFO_SERVER_NAME);
        }

        /**
         * 获得URL
         *
         * @return URL
         */
        public static String getModify() {
            return getServerUrl(SystemServerDef.Ds.MODULE_NAME, SystemServerDef.Ds.MODIFY_SERVER_NAME);
        }

        /**
         * 获得卖家采供链生成美迪福接口xml文件URL
         *
         * @return 卖家采供链生成美迪福接口xml文件URL
         */
        public static String getCreateXML() {
            return getServerUrl(SystemServerDef.Ds.MODULE_NAME, SystemServerDef.Ds.CREATE_XML_SERVER_NAME);
        }

        /**
         * 发货入库详细列表PDF数据查询
         *
         * @return 发货入库详细列表PDF数据查询
         */
        public static String getGetDeliveryPDFData() {
            return getServerUrl(SystemServerDef.Ds.MODULE_NAME, SystemServerDef.Ds.GET_DELIVERY_PDF_DATA_SERVER_NAME);
        }
    }

    /**
     * Seller Server Manager,里面包含所有的服务地址
     */
    public static class SellerServerManage {
        /**
         * 获得查询卖家信息URL
         *
         * @return 查询卖家信息URL
         */
        public static String getQueryInfo() {
            return getServerUrl(SystemServerDef.Seller.MODULE_NAME, SystemServerDef.Seller.QUERY_INFO_SERVER_NAME);
        }

        /**
         * 获得创建账号URL
         *
         * @return 创建账号URL
         */
        public static String getCreateAccount() {
            return getServerUrl(SystemServerDef.Seller.MODULE_NAME, SystemServerDef.Seller.CREATE_ACCOUNT_SERVER_NAME);
        }

        /**
         * 获得更新账号URL
         *
         * @return 更新账号URL
         */
        public static String getUpdateAccount() {
            return getServerUrl(SystemServerDef.Seller.MODULE_NAME, SystemServerDef.Seller.UPDATE_ACCOUNT_SERVER_NAME);
        }

        /**
         * 获得修改账号URL
         *
         * @return 修改账号URL
         */
        public static String getQueryAccount() {
            return getServerUrl(SystemServerDef.Seller.MODULE_NAME, SystemServerDef.Seller.QUERY_ACCOUNT_SERVER_NAME);
        }

        /**
         * 获得编辑卖家产品标准URL
         *
         * @return 编辑卖家产品标准URL
         */
        public static String getNewQlt() {
            return getServerUrl(SystemServerDef.Seller.MODULE_NAME, SystemServerDef.Seller.NEW_QLT_SERVER_NAME);
        }

        /**
         * 获得查询URL
         *
         * @return 查询URL
         */
        public static String getQuery() {
            return getServerUrl(SystemServerDef.Seller.MODULE_NAME, SystemServerDef.Seller.QUERY_SERVER_NAME);
        }

        /**
         * 获得增加卖家基本信息接口URL
         *
         * @return 增加卖家基本信息接口URL
         */
        public static String getCreateSeller() {
            return getServerUrl(SystemServerDef.Seller.MODULE_NAME, SystemServerDef.Seller.CREATE_SELLER_SERVER_NAME);
        }

        /**
         * 获得更新卖家基本信息接口URL
         *
         * @return 更新卖家基本信息接口URL
         */
        public static String getUpdateSeller() {
            return getServerUrl(SystemServerDef.Seller.MODULE_NAME, SystemServerDef.Seller.UPDATE_SELLER_SERVER_NAME);
        }

        /**
         * 获得增加企业基本资质接口URL
         *
         * @return 增加企业基本资质接口URL
         */
        public static String getCreateEnterprise() {
            return getServerUrl(SystemServerDef.Seller.MODULE_NAME,
                SystemServerDef.Seller.CREATE_ENTERPRISE_SERVER_NAME);
        }

        /**
         * 获得更新企业基本资质接口URL
         *
         * @return 更新企业基本资质接口URL
         */
        public static String getUpdateEnterprise() {
            return getServerUrl(SystemServerDef.Seller.MODULE_NAME,
                SystemServerDef.Seller.UPDATE_ENTERPRISE_SERVER_NAME);
        }

        /**
         * 获得查询卖家基本信息接口URL
         *
         * @return 查询卖家基本信息接口URL
         */
        public static String getQuerySeller() {
            return getServerUrl(SystemServerDef.Seller.MODULE_NAME, SystemServerDef.Seller.QUERY_SELLER_SERVER_NAME);
        }

        /**
         * 获得增加新的企业专业资质证照URL
         *
         * @return 增加新的企业专业资质证照URL
         */
        public static String getCreateEpCertItem() {
            return getServerUrl(SystemServerDef.Seller.MODULE_NAME,
                SystemServerDef.Seller.CREATE_EP_CERTITEM_SERVER_NAME);
        }

        /**
         * 获得更新新的企业专业资质证照URL
         *
         * @return 更新新的企业专业资质证照URL
         */
        public static String getUpdateEpCertItem() {
            return getServerUrl(SystemServerDef.Seller.MODULE_NAME,
                SystemServerDef.Seller.UPDATE_EP_CERTITEM_SERVER_NAME);
        }

        /**
         * 获得查询新的企业专业资质证照URL
         *
         * @return 查询新的企业专业资质证照URL
         */
        public static String getQueryEpCertItem() {
            return getServerUrl(SystemServerDef.Seller.MODULE_NAME,
                SystemServerDef.Seller.QUERY_EP_CERTITEM_SERVER_NAME);
        }

        /**
         * 获得删除新的企业专业资质证照URL
         *
         * @return 删除新的企业专业资质证照URL
         */
        public static String getDeleteEpCert() {
            return getServerUrl(SystemServerDef.Seller.MODULE_NAME, SystemServerDef.Seller.DELETE_EP_CERT_SERVER_NAME);
        }

        /**
         * 获得增加企业管理团队接口URL
         *
         * @return 增加企业管理团队接口URL
         */
        public static String getCreateEpManager() {
            return getServerUrl(SystemServerDef.Seller.MODULE_NAME,
                SystemServerDef.Seller.CREATE_EP_MANAGER_SERVER_NAME);
        }

        /**
         * 获得更新企业管理团队接口URL
         *
         * @return 更新企业管理团队接口URL
         */
        public static String getUpdateEpManager() {
            return getServerUrl(SystemServerDef.Seller.MODULE_NAME,
                SystemServerDef.Seller.UPDATE_EP_MANAGER_SERVER_NAME);
        }

        /**
         * 获得增加卖家电商团队接口URL
         *
         * @return 增加卖家电商团队接口URL
         */
        public static String getCreateEcTeam() {
            return getServerUrl(SystemServerDef.Seller.MODULE_NAME, SystemServerDef.Seller.CREATE_EC_TEAM_SERVER_NAME);
        }

        /**
         * 获得更新卖家电商团队接口URL
         *
         * @return 更新卖家电商团队接口URL
         */
        public static String getUpdateEcTeam() {
            return getServerUrl(SystemServerDef.Seller.MODULE_NAME, SystemServerDef.Seller.UPDATE_EC_TEAM_SERVER_NAME);
        }

        /**
         * 获得删除卖家电商团队接口URL
         *
         * @return 删除卖家电商团队接口URL
         */
        public static String getDeleteEcTeam() {
            return getServerUrl(SystemServerDef.Seller.MODULE_NAME, SystemServerDef.Seller.DELETE_EC_TEAM_SERVER_NAME);
        }

        /**
         * 获得查询企业管理团队接口URL
         *
         * @return 查询企业管理团队接口URL
         */
        public static String getQueryEpManager() {
            return getServerUrl(SystemServerDef.Seller.MODULE_NAME,
                SystemServerDef.Seller.QUERY_EP_MANAGER_SERVER_NAME);
        }

        /**
         * 获得创建企业管理团队接口URL
         *
         * @return 创建企业管理团队接口URL
         */
        public static String getCreateEpBrandc() {
            return getServerUrl(SystemServerDef.Seller.MODULE_NAME,
                SystemServerDef.Seller.CREATE_EP_BRANDC_SERVER_NAME);
        }

        /**
         * 获得更新企业管理团队接口URL
         *
         * @return 更新企业管理团队接口URL
         */
        public static String getUpdateEpBrandc() {
            return getServerUrl(SystemServerDef.Seller.MODULE_NAME,
                SystemServerDef.Seller.UPDATE_EP_BRANDC_SERVER_NAME);
        }

        /**
         * 获得删除企业管理团队接口URL
         *
         * @return 删除企业管理团队接口URL
         */
        public static String getDeleteEpBrandc() {
            return getServerUrl(SystemServerDef.Seller.MODULE_NAME,
                SystemServerDef.Seller.DELETE_EP_BRANDC_SERVER_NAME);
        }

        /**
         * 获得查询企业产品品牌接口URL
         *
         * @return 查询企业产品品牌接口URL
         */
        public static String getQueryEpBrandc() {
            return getServerUrl(SystemServerDef.Seller.MODULE_NAME, SystemServerDef.Seller.QUERY_EP_BRANDC_SERVER_NAME);
        }

        /**
         * 获得创建企业产品品牌接口URL
         *
         * @return 创建企业产品品牌接口URL
         */
        public static String getCreatePdBrand() {
            return getServerUrl(SystemServerDef.Seller.MODULE_NAME, SystemServerDef.Seller.CREATE_PD_BRAND_SERVER_NAME);
        }

        /**
         * 获得删除卖家产品品牌URL
         *
         * @return 删除卖家产品品牌URL
         */
        public static String getDeletePdBrandc() {
            return getServerUrl(SystemServerDef.Seller.MODULE_NAME,
                SystemServerDef.Seller.DELETE_PD_BRANDC_SERVER_NAME);
        }

        /**
         * 获得查询卖家产品品牌信息URL
         *
         * @return 查询卖家产品品牌信息URL
         */
        public static String getPdBrandSearch() {
            return getServerUrl(SystemServerDef.Seller.MODULE_NAME,
                SystemServerDef.Seller.SL_PD_BRAND_SEARCH_SERVER_NAME);
        }

        /**
         * 获得增加企业产品品牌荣誉接口URL
         *
         * @return 增加企业产品品牌荣誉接口URL
         */
        public static String getCreateEpBrandHonor() {
            return getServerUrl(SystemServerDef.Seller.MODULE_NAME,
                SystemServerDef.Seller.CREATE_EP_BRAND_HONOR_SERVER_NAME);
        }

        /**
         * 获得修改卖家产品品牌接口URL
         *
         * @return 修改卖家产品品牌接口URL
         */
        public static String getUpdatePdBrandc() {
            return getServerUrl(SystemServerDef.Seller.MODULE_NAME,
                SystemServerDef.Seller.UPDATE_PD_BRANDC_SERVER_NAME);
        }

        /**
         * 获得删除企业产品品牌荣誉URL
         *
         * @return 删除企业产品品牌荣誉URL
         */
        public static String getDeleteEpBrandHonor() {
            return getServerUrl(SystemServerDef.Seller.MODULE_NAME,
                SystemServerDef.Seller.DELETE_EP_BRAND_HONOR_SERVER_NAME);
        }

        /**
         * 获得查询企业产品品牌荣誉URL
         *
         * @return 查询企业产品品牌荣誉URL
         */
        public static String getQueryEpBrandHonor() {
            return getServerUrl(SystemServerDef.Seller.MODULE_NAME,
                SystemServerDef.Seller.QUERY_EP_BRAND_HONOR_SERVER_NAME);
        }

        /**
         * 获得创建卖家产品类别URL
         *
         * @return 创建卖家产品类别URL
         */
        public static String getCreatePdClasses() {
            return getServerUrl(SystemServerDef.Seller.MODULE_NAME,
                SystemServerDef.Seller.CREATE_PD_CLASSES_SERVER_NAME);
        }

        /**
         * 获得删除卖家产品类别URL
         *
         * @return 删除卖家产品类别URL
         */
        public static String getDeletePdClasses() {
            return getServerUrl(SystemServerDef.Seller.MODULE_NAME,
                SystemServerDef.Seller.DELETE_PD_CLASSES_SERVER_NAME);
        }

        /**
         * 获得查询卖家产品类别URL
         *
         * @return 查询卖家产品类别URL
         */
        public static String getQueryPdClasses() {
            return getServerUrl(SystemServerDef.Seller.MODULE_NAME,
                SystemServerDef.Seller.QUERY_PD_CLASSES_SERVER_NAME);
        }

        /**
         * 获得创建卖家产品URL
         *
         * @return 创建卖家产品URL
         */
        public static String getCreateProduct() {
            return getServerUrl(SystemServerDef.Seller.MODULE_NAME, SystemServerDef.Seller.CREATE_PRODUCT_SERVER_NAME);
        }

        /**
         * 获得删除卖家产品URL
         *
         * @return 删除卖家产品URL
         */
        public static String getUpdateProduct() {
            return getServerUrl(SystemServerDef.Seller.MODULE_NAME, SystemServerDef.Seller.UPDATE_PRODUCT_SERVER_NAME);
        }

        /**
         * 获得卖家申请新产品品种/特征/净重URL
         *
         * @return 卖家申请新产品品种/特征/净重URL
         */
        public static String getCreatePdBfw() {
            return getServerUrl(SystemServerDef.Seller.MODULE_NAME, SystemServerDef.Seller.CREATE_PD_BFW_SERVER_NAME);
        }

        /**
         * 获得卖家申请新产品包装URL
         *
         * @return 卖家申请新产品包装URL
         */
        public static String getCreatePdPkg() {
            return getServerUrl(SystemServerDef.Seller.MODULE_NAME, SystemServerDef.Seller.CREATE_PD_PKG_SERVER_NAME);
        }

        /**
         * 获得删除卖家产品URL
         *
         * @return 删除卖家产品URL
         */
        public static String getDeleteProduct() {
            return getServerUrl(SystemServerDef.Seller.MODULE_NAME, SystemServerDef.Seller.DELETE_PRODUCT_SERVER_NAME);
        }

        /**
         * 获得删除企业管理团队接口URL
         *
         * @return 删除企业管理团队接口URL
         */
        public static String getDeleteEpManager() {
            return getServerUrl(SystemServerDef.Seller.MODULE_NAME,
                SystemServerDef.Seller.DELETE_EP_MANAGER_SERVER_NAME);
        }

        /**
         * 获得卖家账号密码修改URL
         *
         * @return 卖家账号密码修改
         */
        public static String getPsdUpdate() {
            return getServerUrl(SystemServerDef.Seller.MODULE_NAME, SystemServerDef.Seller.PSD_UPDATE_SERVER_NAME);
        }

        /**
         * 获得查询营业执照_注册号URL
         *
         * @return 查询营业执照_注册号URL
         */
        public static String getCheckLicNo() {
            return getServerUrl(SystemServerDef.Seller.MODULE_NAME, SystemServerDef.Seller.CHECK_LIC_NO_SERVER_NAME);
        }

        /**
         * 获得查询卖家区域的codeURL
         *
         * @return 查询卖家区域的codeURL
         */
        public static String getQueryCode() {
            return getServerUrl(SystemServerDef.Seller.MODULE_NAME, SystemServerDef.Seller.QUERY_CODE_SERVER_NAME);
        }

        /**
         * 获得查询卖家产品货号信息URL
         *
         * @return 查询卖家产品货号信息URL
         */
        public static String getPdArtnoSearch() {
            return getServerUrl(SystemServerDef.Seller.MODULE_NAME,
                SystemServerDef.Seller.SL_PD_ARTNO_SEARCH_SERVER_NAME);
        }

        /**
         * 获得查询创建时间范围内的卖家用户URL
         *
         * @return 查询创建时间范围内的卖家用户URL
         */
        public static String getSoSalesRankingSearch() {
            return getServerUrl(SystemServerDef.Seller.MODULE_NAME,
                SystemServerDef.Seller.SO_SALES_RANKING_SEARCH_SERVER_NAME);
        }

        /**
         * 获得查询供应商名称URL
         *
         * @return 查询供应商名称URL
         */
        public static String getEpNameSearch() {
            return getServerUrl(SystemServerDef.Seller.MODULE_NAME,
                SystemServerDef.Seller.SL_EP_NAME_SEARCH_SERVER_NAME);
        }

        /**
         * 获得查询卖家身份企业信息URL
         *
         * @return URL
         */
        public static String getEpDataSearch() {
            return getServerUrl(SystemServerDef.Seller.MODULE_NAME,
                SystemServerDef.Seller.Sl_EP_DATA_SEARCH_SERVER_NAME);
        }

        /**
         * 获得批量查询卖家身份企业信息URL
         *
         * @return 批量查询卖家身份企业信息URL
         */
        public static String getEpDataListSearch() {
            return getServerUrl(SystemServerDef.Seller.MODULE_NAME,
                SystemServerDef.Seller.SL_EP_DATA_LIST_SEARCH_SERVER_NAME);
        }

        /**
         * 获得查询产品URL
         *
         * @return 查询产品URL
         */
        public static String getSearchPd() {
            return getServerUrl(SystemServerDef.Seller.MODULE_NAME, SystemServerDef.Seller.SEARCH_PD_SERVER_NAME);
        }

        /**
         * 获得查询卖家产品信息URL
         *
         * @return 查询卖家产品信息URL
         */
        public static String getProductSearch() {
            return getServerUrl(SystemServerDef.Seller.MODULE_NAME,
                SystemServerDef.Seller.SL_PRODUCT_SEARCH_SERVER_NAME);
        }

        /**
         * 获得查询卖家（显示）编码URL
         *
         * @return 查询卖家（显示）编码URL
         */
        public static String getSellerCodeSearch() {
            return getServerUrl(SystemServerDef.Seller.MODULE_NAME,
                SystemServerDef.Seller.SL_SELLER_CODE_SEARCH_SERVER_NAME);
        }

        /**
         * 获得查询买手信息URL
         *
         * @return 查询买手信息URL
         */
        public static String getBsBuyerSearch() {
            return getServerUrl(SystemServerDef.Seller.MODULE_NAME,
                SystemServerDef.Seller.SL_BS_BUYER_SEARCH_SERVER_NAME);
        }

        /**
         * 获得更新买手店管家专属会员表URL
         *
         * @return 更新买手店管家专属会员表URL
         */
        public static String getSlBsBuyerUpdate() {
            return getServerUrl(SystemServerDef.Seller.MODULE_NAME,
                SystemServerDef.Seller.SLBS_BUYER_UPDATE_SERVER_NAME);
        }

        /**
         * 获得查询冻品管家账户信息URL
         *
         * @return 查询冻品管家账户信息URL
         */
        public static String getSlHouseAccountSearch() {
            return getServerUrl(SystemServerDef.Seller.MODULE_NAME,
                SystemServerDef.Seller.SL_HOUSE_ACCOUNT_SEARCH_SERVER_NAME);
        }

        /**
         * 获得新增卖家产品状态履历URL
         *
         * @return 新增卖家产品状态履历URL
         */
        public static String getCreateSlPdStatusHis() {
            return getServerUrl(SystemServerDef.Seller.MODULE_NAME,
                SystemServerDef.Seller.CREATE_SLPD_STATUS_HIS_SERVER_NAME);
        }

        /**
         * 获得查询卖家区域code和nameURL
         *
         * @return 查询卖家区域code和nameURL
         */
        public static String getSlPdCodeSearch() {
            return getServerUrl(SystemServerDef.Seller.MODULE_NAME, SystemServerDef.Seller.PD_CODE_SEARCH_SERVER_NAME);
        }

        /**
         * 获得查询卖家区域code和nameURL
         *
         * @return 查询卖家区域code和nameURL
         */
        public static String getSlPdInfoCodeSearch() {
            return getServerUrl(SystemServerDef.Seller.MODULE_NAME,
                SystemServerDef.Seller.SL_PD_INFO_CODE_SEARCH_SERVER_NAME);
        }

        /**
         * 获得查询卖家nameURL
         *
         * @return 查询卖家nameURL
         */
        public static String getSlPdCodeSearch1() {
            return getServerUrl(SystemServerDef.Seller.MODULE_NAME, SystemServerDef.Seller.PD_CODE_SEARCH1_SERVER_NAME);
        }

        /**
         * 获得查询卖家nameURL
         *
         * @return 查询卖家nameURL
         */
        public static String getSlPdCodeNameSearch() {
            return getServerUrl(SystemServerDef.Seller.MODULE_NAME,
                SystemServerDef.Seller.SL_PD_CODE_NAME_SEARCH_SERVER_NAME);
        }

        /**
         * 获得查询物流区供应商信息URL
         *
         * @return 查询物流区供应商信息URL
         */
        public static String getGetLgcsSellerInfoSearch() {
            return getServerUrl(SystemServerDef.Seller.MODULE_NAME,
                SystemServerDef.Seller.GET_LGCS_SELLER_INFO_SEARCH_SERVER_NAME);
        }

        /**
         * 获得修改神农客分销章程信息URL
         *
         * @return 修改神农客分销章程信息URL
         */
        public static String getModifyDbOrder() {
            return getServerUrl(SystemServerDef.Seller.MODULE_NAME, SystemServerDef.Seller.MODIFY_DB_ORDER_SERVER_NAME);
        }

        /**
         * 获得取得神农客分销章程信息URL
         *
         * @return 取得神农客分销章程信息URL
         */
        public static String getUpdateDbOrder() {
            return getServerUrl(SystemServerDef.Seller.MODULE_NAME, SystemServerDef.Seller.UPDATE_DB_ORDER_SERVER_NAME);
        }

        /**
         * 获得获取生产商和品牌IDURL
         *
         * @return 获取生产商和品牌IDURL
         */
        public static String getFindManuAndBrand() {
            return getServerUrl(SystemServerDef.Seller.MODULE_NAME,
                SystemServerDef.Seller.FIND_MANU_AND_BRAND_SERVER_NAME);
        }

        /**
         * 获得根据卖家ID查询卖家一级分类URL
         *
         * @return 根据卖家ID查询卖家一级分类URL
         */
        public static String getSlOneClassSearch() {
            return getServerUrl(SystemServerDef.Seller.MODULE_NAME,
                SystemServerDef.Seller.SL_ONE_CLASS_SEARCH_SERVER_NAME);
        }

        /**
         * 获得URL
         *
         * @return URL
         */
        public static String getSearch() {
            return getServerUrl(SystemServerDef.Seller.MODULE_NAME, SystemServerDef.Seller.SEARCH_SERVER_NAME);
        }

        /**
         * 获得查询企业名称和企业ID的URL
         *
         * @return 查询企业名称和企业ID的URL
         */
        public static String getSearchSlEnterprise() {
            return getServerUrl(SystemServerDef.Seller.MODULE_NAME,
                SystemServerDef.Seller.SEARCH_SLENTERPRISE_SERVER_NAME);
        }

        /**
         * 获得查询卖家产品编码和名称URL
         *
         * @return 查询卖家产品编码和名称URL
         */
        public static String getSearchSlProduct() {
            return getServerUrl(SystemServerDef.Seller.MODULE_NAME,
                SystemServerDef.Seller.SEARCH_SLPRODUCT_SERVER_NAME);
        }

        /**
         * 获得查询卖家产品编码和名称URL
         *
         * @return 查询卖家产品编码和名称URL
         */
        public static String getGetLgcsSellerProductInfoSearch() {
            return getServerUrl(SystemServerDef.Seller.MODULE_NAME,
                SystemServerDef.Seller.GET_LGCS_SELLER_PRODUCT_INFO_SEARCH_SERVER_NAME);
        }

        /**
         * 买手信息维护卖家同步
         *
         * @return 买手信息维护卖家同步
         */
        public static String getDealSlSellerAccount() {
            return getServerUrl(SystemServerDef.Seller.MODULE_NAME, SystemServerDef.Seller.DEAL_SL_SELLER_ACCOUNT);
        }

        /**
         * 买手信息维护卖家同步
         *
         * @return 买手信息维护卖家同步
         */
        public static String getQueryEPName() {
            return getServerUrl(SystemServerDef.Seller.MODULE_NAME, SystemServerDef.Seller.QUERY_EP_NAME_SERVER_NAME);
        }

        /**
         * 查询卖家产品属性
         *
         * @return 查询卖家产品属性
         */
        public static String getGetPdCode() {
            return getServerUrl(SystemServerDef.Seller.MODULE_NAME, SystemServerDef.Seller.GET_PD_CODE_SERVER_NAME);
        }

        /**
         * 根据卖家编码关联对应的生产商
         *
         * @return 根据卖家编码关联对应的生产商
         */
        public static String getGetSlEnterpriseBySlCode() {
            return getServerUrl(SystemServerDef.Seller.MODULE_NAME,
                SystemServerDef.Seller.GET_SL_ENTERPRISE_BY_SL_CODE_SERVER_NAME);
        }

        /**
         * 卖家产品状态变更记录
         *
         * @return 卖家产品状态变更记录
         */
        public static String getStatusChange() {
            return getServerUrl(SystemServerDef.Seller.MODULE_NAME, SystemServerDef.Seller.STATUS_CHANGE_SERVER_NAME);
        }

        /**
         * 根据卖家编码批量查询卖家信息
         *
         * @return 根据卖家编码批量查询卖家信息
         */
        public static String getQuerySellerList() {
            return getServerUrl(SystemServerDef.Seller.MODULE_NAME,
                SystemServerDef.Seller.QUERY_SELLER_LIST_SERVER_NAME);
        }

        /**
         * 根据卖家、销售平台、物流区域、产品查询产品对应的SKU信息
         */
        public static String getSkuCodeSearch() {
            return getServerUrl(SystemServerDef.Seller.MODULE_NAME, SystemServerDef.Seller.SKU_CODE_SEARCH_SERVER_NAME);
        }

        /**
         * 查询卖家所有信息
         */
        public static String getSearchAllSLEpManager() {
            return getServerUrl(SystemServerDef.Seller.MODULE_NAME,
                SystemServerDef.Seller.SEARCH_ALL_S_L_EP_MANAGER_SERVER_NAME);
        }

        /***/
        public static String getSearchSlProp() {
            return getServerUrl(SystemServerDef.Seller.MODULE_NAME, SystemServerDef.Seller.SEARCH_SL_PROP_SERVER_NAME);
        }

        /**
         * 分页获取所有企业信息列表
         */
        public static String getQueryEnterpriseInfo() {
            return getServerUrl(SystemServerDef.Seller.MODULE_NAME,
                SystemServerDef.Seller.QUERY_ENTERPRISE_INFO_SERVER_NAME);
        }

        /**
         * 分页获取企业产品信息列表
         */
        public static String getQueryProductInfo() {
            return getServerUrl(SystemServerDef.Seller.MODULE_NAME,
                SystemServerDef.Seller.QUERY_PRODUCT_INFO_SERVER_NAME);
        }

        /**
         * 查询供应商对应的分销资格
         */
        public static String getQuerySlSellerDisQua() {
            return getServerUrl(SystemServerDef.Seller.MODULE_NAME,
                SystemServerDef.Seller.QUERY_SL_SELLER_DIS_QUA_SERVER_NAME);
        }

        /**
         * 查询新增卖家对应产品信息
         */
        public static String getQuerySlSellerProduct() {
            return getServerUrl(SystemServerDef.Seller.MODULE_NAME,
                SystemServerDef.Seller.QUERY_SL_SELLER_PRODUCT_SERVER_NAME);
        }

        /***/
        public static String getCreateSLAccountByBuyer() {
            return getServerUrl(SystemServerDef.Seller.MODULE_NAME,
                SystemServerDef.Seller.CREATE_S_L_ACCOUNT_BY_BUYER_SERVER_NAME);
        }

        /***/
        public static String getCreateSLAccount() {
            return getServerUrl(SystemServerDef.Seller.MODULE_NAME,
                SystemServerDef.Seller.CREATE_S_L_ACCOUNT_SERVER_NAME);
        }

        /***/
        public static String getDeleteSLEpCert() {
            return getServerUrl(SystemServerDef.Seller.MODULE_NAME,
                SystemServerDef.Seller.DELETE_S_L_EP_CERT_SERVER_NAME);
        }

        /***/
        public static String getInsertSlEpCert() {
            return getServerUrl(SystemServerDef.Seller.MODULE_NAME,
                SystemServerDef.Seller.INSERT_SL_EP_CERT_SERVER_NAME);
        }

        /***/
        public static String getAddSlEpInfo() {
            return getServerUrl(SystemServerDef.Seller.MODULE_NAME, SystemServerDef.Seller.ADD_SL_EP_INFO_SERVER_NAME);
        }

        /***/
        public static String getDeleteSLEpManager() {
            return getServerUrl(SystemServerDef.Seller.MODULE_NAME,
                SystemServerDef.Seller.DELETE_S_L_EP_MANAGER_SERVER_NAME);
        }

        /***/
        public static String getDeleteSLProduct() {
            return getServerUrl(SystemServerDef.Seller.MODULE_NAME,
                SystemServerDef.Seller.DELETE_S_L_PRODUCT_SERVER_NAME);
        }

        /***/
        public static String getEditSLAccountInfo() {
            return getServerUrl(SystemServerDef.Seller.MODULE_NAME,
                SystemServerDef.Seller.EDIT_S_L_ACCOUNT_INFO_SERVER_NAME);
        }

        /***/
        public static String getFindSlSellerStd() {
            return getServerUrl(SystemServerDef.Seller.MODULE_NAME,
                SystemServerDef.Seller.FIND_SL_SELLER_STD_SERVER_NAME);
        }

        /***/
        public static String getFindDbOrderInfo() {
            return getServerUrl(SystemServerDef.Seller.MODULE_NAME,
                SystemServerDef.Seller.FIND_DB_ORDER_INFO_SERVER_NAME);
        }

        /***/
        public static String getQuerySLEpManager() {
            return getServerUrl(SystemServerDef.Seller.MODULE_NAME,
                SystemServerDef.Seller.QUERY_S_L_EP_MANAGER_SERVER_NAME);
        }

        /***/
        public static String getQuerySlEcTeam() {
            return getServerUrl(SystemServerDef.Seller.MODULE_NAME,
                SystemServerDef.Seller.QUERY_SL_EC_TEAM_SERVER_NAME);
        }

        /***/
        public static String getQuerySLSeller() {
            return getServerUrl(SystemServerDef.Seller.MODULE_NAME,
                SystemServerDef.Seller.QUERY_S_L_SELLER_SERVER_NAME);
        }

        /***/
        public static String getQuerySlEnterpriseInfo() {
            return getServerUrl(SystemServerDef.Seller.MODULE_NAME,
                SystemServerDef.Seller.QUERY_SL_ENTERPRISE_INFO_SERVER_NAME);
        }

        /***/
        public static String getSearchSlMstCertInfo() {
            return getServerUrl(SystemServerDef.Seller.MODULE_NAME,
                SystemServerDef.Seller.SEARCH_SL_MST_CERT_INFO_SERVER_NAME);
        }

        /***/
        public static String getSearchSlEpAuth() {
            return getServerUrl(SystemServerDef.Seller.MODULE_NAME,
                SystemServerDef.Seller.SEARCH_SL_EP_AUTH_SERVER_NAME);
        }

        /***/
        public static String getSearchSlArtNo() {
            return getServerUrl(SystemServerDef.Seller.MODULE_NAME,
                SystemServerDef.Seller.SEARCH_SL_ART_NO_SERVER_NAME);
        }

        /***/
        public static String getQuerySlPdArtnoInfo() {
            return getServerUrl(SystemServerDef.Seller.MODULE_NAME,
                SystemServerDef.Seller.QUERY_SL_PD_ARTNO_INFO_SERVER_NAME);
        }

        /***/
        public static String getUpdateSlPdBrandcTeam() {
            return getServerUrl(SystemServerDef.Seller.MODULE_NAME,
                SystemServerDef.Seller.UPDATE_SL_PD_BRANDC_TEAM_SERVER_NAME);
        }

        /***/
        public static String getUpdateSlEpBrandHonor() {
            return getServerUrl(SystemServerDef.Seller.MODULE_NAME,
                SystemServerDef.Seller.UPDATE_SL_EP_BRAND_HONOR_SERVER_NAME);
        }

    }

    /**
     * SnkPrice Server Manager,里面包含所有的服务地址
     */
    public static class SnkPriceServerManage {
        /**
         * 获得获取供应商分销数量URL
         *
         * @return 获取供应商分销数量URL
         */
        public static String getGetSupDistributeDemand() {
            return getServerUrl(SystemServerDef.SnkPrice.MODULE_NAME,
                SystemServerDef.SnkPrice.GET_SUP_DISTRIBUTE_DEMAND_SERVER_NAME);
        }

        /**
         * 神农客产品查询价盘接口调用URL
         *
         * @return 神农客产品查询价盘接口调用URL
         */
        public static String getGetPriceCycle() {
            return getServerUrl(SystemServerDef.SnkPrice.MODULE_NAME,
                SystemServerDef.SnkPrice.GET_PRICE_CYCLE_SERVER_NAME);
        }

        /**
         * 获得URL
         *
         * @return URL
         */
        public static String getSearch() {
            return getServerUrl(SystemServerDef.SnkPrice.MODULE_NAME, SystemServerDef.SnkPrice.SEARCH_SERVER_NAME);
        }

        /**
         * 获得URL
         *
         * @return URL
         */
        public static String getPriceCycle() {
            return getServerUrl(SystemServerDef.SnkPrice.MODULE_NAME, SystemServerDef.SnkPrice.PRICE_CYCLE);
        }

        /**
         * 获得URL
         *
         * @return URL
         */
        public static String getGetPriceWay() {
            return getServerUrl(SystemServerDef.SnkPrice.MODULE_NAME, SystemServerDef.SnkPrice.GET_PRICE_WAY);
        }

        /**
         * 获得URL
         *
         * @return URL
         */
        public static String getGetPriceWayList() {
            return getServerUrl(SystemServerDef.SnkPrice.MODULE_NAME,
                SystemServerDef.SnkPrice.GETPRICEWAYLIST_SERVER_NAME);
        }
    }

    /**
     * Seller Server Manager,里面包含所有的服务地址
     */
    public static class SoCashPoolingServerManage {
        /**
         * 获得URL
         *
         * @return URL
         */
        public static String getTransaction() {
            return getServerUrl(SystemServerDef.SoCashPooling.MODULE_NAME,
                SystemServerDef.SoCashPooling.TRANSACTION_SERVER_NAME);
        }

        /**
         * 获得URL
         *
         * @return URL
         */
        public static String getRunning() {
            return getServerUrl(SystemServerDef.SoCashPooling.MODULE_NAME,
                SystemServerDef.SoCashPooling.RUNNING_SERVER_NAME);
        }

        /**
         * 获得卖家计费项接口调用的URL
         *
         * @return 卖家计费项接口调用URL
         */
        public static String getSellerCharging() {
            return getServerUrl(SystemServerDef.SoCashPooling.MODULE_NAME,
                SystemServerDef.SoCashPooling.SELLER_CHARGING_SERVER_NAME);
        }

        /**
         * 获得查询卖家结算列表接口调用的URL
         *
         * @return 查询卖家结算列表接口调用URL
         */
        public static String getQueryBuyerList() {
            return getServerUrl(SystemServerDef.SoCashPooling.MODULE_NAME,
                SystemServerDef.SoCashPooling.QUERY_BUYER_LIST_SERVER_NAME);
        }

        /**
         * 买手详细页面打印pdf回调
         *
         * @return 买手详细页面打印pdf回调
         */
        public static String getQueryBuyerPDF() {
            return getServerUrl(SystemServerDef.SoCashPooling.MODULE_NAME,
                SystemServerDef.SoCashPooling.QUERY_BUYER_PDF);
        }

        /**
         * 卖家详细信息回调接口
         *
         * @return 卖家详细信息回调接口
         */
        public static String getQuerySellerPDF() {
            return getServerUrl(SystemServerDef.SoCashPooling.MODULE_NAME,
                SystemServerDef.SoCashPooling.QUERY_SELLER_PDF);
        }

    }

    /**
     * SoOrder Server Manager,里面包含所有的服务地址
     */
    public static class SoOrderServerManage {
        /*      *//**
                   * 获得URL
                   *
                   * @return URL
                   */
        /*
         * public static String getCancel() {
         * return getServerUrl(SystemServerDef.SoOrder.MODULE_NAME, SystemServerDef.SoOrder.CANCEL_SERVER_NAME);
         * }
         *//**
           * 获得更新订单状态URL
           *
           * @return 更新订单状态URL
           */
        /*
         * public static String getModifyOrderStatus() {
         * return getServerUrl(SystemServerDef.SoOrder.MODULE_NAME,
         * SystemServerDef.SoOrder.MODIFY_ORDER_STATUS_SERVER_NAME);
         * }
         *//**
           * 获得订单收发货操作URL
           *
           * @return 订单收发货操作URL
           */
        /*
         * public static String getOrderReceivingOrDeliver() {
         * return getServerUrl(SystemServerDef.SoOrder.MODULE_NAME,
         * SystemServerDef.SoOrder.ORDER_RECEIVING_OR_DELIVER_SERVER_NAME);
         * }
         *//**
           * 获得URL
           *
           * @return URL
           */
        /*
         * public static String getList() {
         * return getServerUrl(SystemServerDef.SoOrder.MODULE_NAME, SystemServerDef.SoOrder.LIST_SERVER_NAME);
         * }
         *//**
           * 获得获取卖家已卖出商品列表URL
           *
           * @return 获取卖家已卖出商品列表URL
           */
        /*
         * public static String getOrderProductList() {
         * return getServerUrl(SystemServerDef.SoOrder.MODULE_NAME,
         * SystemServerDef.SoOrder.ORDER_PRODUCT_LIST_SERVER_NAME);
         * }
         *//**
           * 获得分销订单统计URL
           *
           * @return 分销订单统计URL
           */
        /*
         * public static String getStatistics() {
         * return getServerUrl(SystemServerDef.SoOrder.MODULE_NAME, SystemServerDef.SoOrder.STATISTICS_SERVER_NAME);
         * }
         *//**
           * 获得修改退货单状态已经退款生成退款单URL
           *
           * @return 修改退货单状态已经退款生成退款单URL
           */
        /*
         * public static String getRetrunOrderInbound() {
         * return getServerUrl(SystemServerDef.SoOrder.MODULE_NAME,
         * SystemServerDef.SoOrder.RETRUN_ORDER_INBOUND_SERVER_NAME);
         * }
         *//**
           * 获得URL
           *
           * @return URL
           */
        /*
         * public static String getSearch() {
         * return getServerUrl(SystemServerDef.SoOrder.MODULE_NAME, SystemServerDef.SoOrder.SEARCH_SERVER_NAME);
         * }
         *//**
           * 获得查询卖家产品URL
           *
           * @return 查询卖家产品URL
           */
        /*
         * public static String getFindProduct() {
         * return getServerUrl(SystemServerDef.SoOrder.MODULE_NAME, SystemServerDef.SoOrder.FIND_PRODUCT_SERVER_NAME);
         * }
         *//**
           * 获得URL
           *
           * @return URL
           */
        /*
         * public static String getDetail() {
         * return getServerUrl(SystemServerDef.SoOrder.MODULE_NAME, SystemServerDef.SoOrder.DETAIL_SERVER_NAME);
         * }
         *//**
           * 获得获取上半旬销售排名URL
           *
           * @return 获取上半旬销售排名URL
           */
        /*
         * public static String getHalfMonthOrder() {
         * return getServerUrl(SystemServerDef.SoOrder.MODULE_NAME,
         * SystemServerDef.SoOrder.HALF_MONTH_ORDER_SERVER_NAME);
         * }
         *//**
           * 获得创建标准订单URL
           *
           * @return 创建标准订单URL
           */
        /*
         * public static String getCreateOrder() {
         * return getServerUrl(SystemServerDef.SoOrder.MODULE_NAME, SystemServerDef.SoOrder.CREATE_ORDER_SERVER_NAME);
         * }
         *//**
           * 获得创建标准订单标准分销订单URL
           *
           * @return 创建标准订单标准分销订单URL
           */
        /*
         * public static String getCreateDbOrder() {
         * return getServerUrl(SystemServerDef.SoOrder.MODULE_NAME,
         * SystemServerDef.SoOrder.CREATE_DB_ORDER_SERVER_NAME);
         * }
         *//**
           * 获得查询标准分销订单详细URL
           *
           * @return 查询标准分销订单详细URL
           */
        /*
         * public static String getQueryDbOrderDetailJson() {
         * return getServerUrl(SystemServerDef.SoOrder.MODULE_NAME,
         * SystemServerDef.SoOrder.QUERY_DB_ORDER_DETAIL_JSON_SERVER_NAME);
         * }
         *//**
           * 获得查询标准分销订单详细URL
           *
           * @return 查询标准分销订单详细URL
           */
        /*
         * public static String getQueryDbOrderDetailXml() {
         * return getServerUrl(SystemServerDef.SoOrder.MODULE_NAME,
         * SystemServerDef.SoOrder.QUERY_DB_ORDER_DETAIL_XML_SERVER_NAME);
         * }
         *//**
           * 获得查询标准分销订单详细URL
           *
           * @return 查询标准分销订单详细URL
           */
        /*
         * public static String getCancelDbOrder() {
         * return getServerUrl(SystemServerDef.SoOrder.MODULE_NAME,
         * SystemServerDef.SoOrder.CANCEL_DB_ORDER_SERVER_NAME);
         * }
         *//**
           * 获得查询卖家质量标准URL
           *
           * @return 查询卖家质量标准URL
           */
        /*
         * public static String getModifyDbOrder() {
         * return getServerUrl(SystemServerDef.SoOrder.MODULE_NAME,
         * SystemServerDef.SoOrder.MODIFY_DB_ORDER_SERVER_NAME);
         * }
         *//**
           * 查询供应商列表
           *
           * @return查询供应商列表
           */
        /*
        *//*
           * public static String getFindSellers() {
           * return getCommonServerUrl(SystemServerDef.SoOrderV1.MODULE_NAME,
           * SystemServerDef.SoOrderV1.FIND_SELLERS_SERVER_NAME);
           * }
           *//*
            
            *//**
               * 验证退货单号
               *
               * @return验证退货单号
               */
        /*
        *//*
           * public static String getCheckReturnCode() {
           * return getCommonServerUrl(SystemServerDef.SoOrderV1.MODULE_NAME,
           * SystemServerDef.SoOrderV1.CHECK_RETURN_CODE_SERVER_NAME);
           * }
           *//*
            
            *//**
               * 订单
               *
               * @return订单
               *//*
                 * public static String getSearchOrderSourceCount() {
                 * return getServerUrl(SystemServerDef.SoOrder.MODULE_NAME,
                 * SystemServerDef.SoOrder.SEARCH_ORDER_SOURCE_COUNT_SERVER_NAME);
                 * }
                 */

        /**
         * 查询冻品管家旗下买家月度销售额接口
         *
         */
        public static String getSearchSales() {
            return getV1ServerUrl(SystemServerDef.SoOrderV1.MODULE_NAME,
                SystemServerDef.SoOrderV1.SEARCH_SALES_SERVER_NAME);
        }

        /**
         * 创建买手囤货订单
         */
        public static String getCreateBuyerSdo() {
            return getV1ServerUrl(SystemServerDef.SoOrderV1.MODULE_NAME,
                SystemServerDef.SoOrderV1.CREATE_BUYER_SDO_SERVER_NAME);
        }

        /**
         * 管家下委托订单
         */
        public static String getCreateDistributionOrder() {
            return getV1ServerUrl(SystemServerDef.SoOrderV1.MODULE_NAME,
                SystemServerDef.SoOrderV1.CREATE_DISTRIBUTION_ORDER_SERVER_NAME);
        }

        /**
         * 查询委托订单
         */
        public static String getQuerySdoDetail() {
            return getV1ServerUrl(SystemServerDef.SoOrderV1.MODULE_NAME,
                SystemServerDef.SoOrderV1.QUERY_SDO_DETAIL_SERVER_NAME);
        }

        /**
         * 买手囤货订单列表接口
         */
        public static String getQueryBssgSdoList() {
            return getV1ServerUrl(SystemServerDef.SoOrderV1.MODULE_NAME,
                SystemServerDef.SoOrderV1.QUERY_BSSG_SDO_LIST_SERVER_NAME);
        }

        /**
         * 买手囤货订单明细接口
         */
        public static String getQueryBssgSdoDetai() {
            return getV1ServerUrl(SystemServerDef.SoOrderV1.MODULE_NAME,
                SystemServerDef.SoOrderV1.QUERY_BSSG_SDO_DETAI_SERVER_NAME);
        }

        /**
         * 买手销售订单列表接口
         */
        public static String getQueryBssSdoList() {
            return getV1ServerUrl(SystemServerDef.SoOrderV1.MODULE_NAME,
                SystemServerDef.SoOrderV1.QUERY_BSS_SDO_LIST_SERVER_NAME);
        }

        /**
         * 买手销售订单明细接口
         */
        public static String getQueryBssSdoDetail() {
            return getV1ServerUrl(SystemServerDef.SoOrderV1.MODULE_NAME,
                SystemServerDef.SoOrderV1.QUERY_BSS_SDO_DETAIL_SERVER_NAME);
        }

        /**
         * 收款方查询接口
         */
        public static String getQueryReceiptInfo() {
            return getV1ServerUrl(SystemServerDef.SoOrderV1.MODULE_NAME,
                SystemServerDef.SoOrderV1.QUERY_RECEIPT_INFO_SERVER_NAME);
        }

        /**
         * 验证退货单号
         */
        public static String getCheckReturnCode() {
            return getV1ServerUrl(SystemServerDef.SoOrderV1.MODULE_NAME,
                SystemServerDef.SoOrderV1.CHECK_RETURN_CODE_SERVER_NAME);
        }

        /**
         * 查询冻品管家旗下买家月度销售额
         */
        public static String getHouseAccountSalesSearch() {
            return getV1ServerUrl(SystemServerDef.SoOrderV1.MODULE_NAME,
                SystemServerDef.SoOrderV1.HOUSE_ACCOUNT_SALES_SEARCH_SERVER_NAME);
        }

        /**
         * 买家平台下单数量统计
         */
        public static String getQueryOrderSourceCount() {
            return getV1ServerUrl(SystemServerDef.SoOrderV1.MODULE_NAME,
                SystemServerDef.SoOrderV1.QUERY_ORDER_SOURCE_COUNT_SERVER_NAME);
        }

        /**
         * 产品库存查询接口
         */
        public static String getFindPdStock() {
            return getV1ServerUrl(SystemServerDef.SoOrderV1.MODULE_NAME,
                SystemServerDef.SoOrderV1.FIND_PD_STOCK_SERVER_NAME);
        }

        /**
         * 获取本月上半月分销量
         */
        public static String getQueryOrderHalfMonthCount() {
            return getV1ServerUrl(SystemServerDef.SoOrderV1.MODULE_NAME,
                SystemServerDef.SoOrderV1.QUERY_ORDER_HALF_MONTH_COUNT_SERVER_NAME);
        }

        /**
         * 买家订单明细接口
         */
        public static String getQueryBySdoDetail() {
            return getV1ServerUrl(SystemServerDef.SoOrderV1.MODULE_NAME,
                    SystemServerDef.SoOrderV1.QUERY_BY_SDO_DETAIL_SERVER_NAME);
        }
    }

    /**
     * SoOrder Server Manager,里面包含所有的服务地址
     */
    public static class SoStockServerManage {
        /**
         * 获得菜单的URL
         *
         * @return 菜单URL
         */
        public static String getSearch() {
            return getServerUrl(SystemServerDef.SoStock.MODULE_NAME, SystemServerDef.SoStock.SEARCH_SERVER_NAME);
        }

        /**
         * 获得查询卖家质量标准URL
         *
         * @return 查询卖家质量标准URL
         */
        public static String getModifyDbOrder() {
            return getServerUrl(SystemServerDef.SoStock.MODULE_NAME,
                SystemServerDef.SoStock.MODIFY_DB_ORDER_SERVER_NAME);
        }

        /**
         * 获得更新库存URL
         *
         * @return 更新库存URL
         */
        public static String getUpdateStock() {
            return getServerUrl(SystemServerDef.SoStock.MODULE_NAME, SystemServerDef.SoStock.UPDATE_STOCK_SERVER_NAME);
        }

        /**
         * 获得查询库存URL
         *
         * @return 查询库存URL
         */
        public static String getQueryStockQty() {
            return getServerUrl(SystemServerDef.SoStock.MODULE_NAME,
                SystemServerDef.SoStock.QUERY_STOCK_QTY_SERVER_NAME);
        }

        /**
         * 获得批量供应商库存数据URL
         *
         * @return 批量供应商库存数据URL
         */
        public static String getSaveStockOfSupplierList() {
            return getServerUrl(SystemServerDef.SoStock.MODULE_NAME,
                SystemServerDef.SoStock.SAVE_STOCK_OF_SUPPLIER_LIST_SERVER_NAME);
        }

        /**
         * 获得批量更新供应商库存数据URL
         *
         * @return 批量更新供应商库存数据URL
         */
        public static String getCancelFrozenStockSupp() {
            return getServerUrl(SystemServerDef.SoStock.MODULE_NAME,
                SystemServerDef.SoStock.CANCEL_FROZEN_STOCK_SUPP_SERVER_NAME);
        }

        /**
         * 获得获得卖家所有的产品和库存信息URL
         *
         * @return 获得卖家所有的产品和库存信息URL
         */
        public static String getGetUsedStock() {
            return getServerUrl(SystemServerDef.SoStock.MODULE_NAME,
                SystemServerDef.SoStock.GET_USED_STOCK_SERVER_NAME);
        }

    }

    /**
     * SellerSupplyChain Server Manager,里面包含ORG所有的服务地址
     */
    public static class SellerSupplyChainManage {
        /**
         * 查询中标成交确认书详细
         *
         * @return 查询中标成交确认书详细
         */
        public static String getFindBidProductDetail() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.FIND_BID_PRODUCT_DETAIL_SERVER_NAME);
        }

        /**
         * 查询中标成交确认书一览信息
         *
         * @return 查询中标成交确认书一览信息
         */
        public static String getfindSscBidBasicInfoList() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.FIND_SSC_BID_BASIC_INFO_LIST_SERVER_NAME);
        }

        /**
         *
         *
         * @return
         */
        public static String getFindContractBasic() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.FIND_CONTRACT_BASIC_SERVER_NAME);
        }

        /**
         *
         *
         * @return
         */
        public static String getFindSscDeliveryOrderBasicList() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.FIND_SSC_DELIVERY_ORDER_BASICLIST_SERVER_NAME);
        }

        /**
         *
         *
         * @return
         */
        public static String getSearchOrderPd() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.SEARCH_ORDERPD_SERVER_NAME);
        }

        /**
         *
         *
         * @return
         */
        public static String getModifyOrderBasic() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.MODIFY_ORDER_BASIC_SERVER_NAME);
        }

        /**
         *
         *
         * @return
         */
        public static String getModifyOrderBasicInfo() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.MODIFY_ORDER_BASIC_INFO_SERVER_NAME);
        }

        /**
         *
         *
         * @return
         */
        public static String getModifyOrderPd() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.MODIFY_ORDER_PD_SERVER_NAME);
        }

        /**
         *
         *
         * @return
         */
        public static String getModifyOrderPdInfo() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.MODIFY_ORDER_PD_INFO_SERVER_NAME);
        }

        /**
         *
         *
         * @return
         */
        public static String getSaveOrderBasic() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.SAVE_ORDER_BASIC_SERVER_NAME);
        }

        /**
         *
         *
         * @return
         */
        public static String getSaveOrderPd() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.SAVE_ORDER_PD_SERVER_NAME);
        }

        /**
         *
         *
         * @return
         */
        public static String getBatchSaveSscIntoBasic() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.BATCH_SAVE_SSC_INTO_BASIC_SERVER_NAME);
        }

        /**
         *
         *
         * @return
         */
        public static String getFindSscIntoBasic() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.FIND_SSC_INTO_BASIC_SERVER_NAME);
        }

        /**
         *
         *
         * @return
         */
        public static String getFindSscIntoDetail() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.FIND_SSC_INTO_DETAIL_SERVER_NAME);
        }

        /**
         *
         *
         * @return
         */
        public static String getModifySscIntoDetail() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.MODIFY_SSC_INTO_DETAIL_SERVER_NAME);
        }

        /**
         *
         *
         * @return
         */
        public static String getFindDeliveryConfirm() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.FIND_DELIVERY_CONFIRM_SERVER_NAME);
        }

        /**
         *
         *
         * @return
         */
        public static String getFindDeliveryConfirmDetailTotal() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.FIND_DELIVERY_CONFIRM_DETAIL_TOTAL_SERVER_NAME);
        }

        /**
         *
         *
         * @return
         */
        public static String getModifyDeliveryConfirm() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.MODIFY_DELIVERY_CONFIRM_SERVER_NAME);
        }

        /**
         *
         *
         * @return
         */
        public static String getModifyDeliveryConfirmDetail() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.MODIFY_DELIVERY_CONFIRM_DETAIL_SERVER_NAME);
        }

        /**
         *
         *
         * @return
         */
        public static String getFindDeliveryConfirmDetail() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.FIND_DELIVERY_CONFIRM_DETAIL_SERVER_NAME);
        }

        /**
         *
         *
         * @return
         */
        public static String getInsertDeliveryPreInto() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.INSERT_DELIVERY_PRE_INTO_SERVER_NAME);
        }

        /**
         *
         *
         * @return
         */
        public static String getDeleteProduct() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.DELETE_PRODUCT_SERVER_NAME);
        }

        /**
         *
         *
         * @return
         */
        public static String getModifyProduct() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.MODIFY_PRODUCT_SERVER_NAME);
        }

        /**
         *
         *
         * @return
         */
        public static String getModifyBidStatus() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.MODIFY_BID_STATUS_SERVER_NAME);
        }

        /**
         *
         *
         * @return
         */
        public static String getInsertBidBasicInfo() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.INSERT_BID_BASIC_INFO_SERVER_NAME);
        }

        /**
         *
         *
         * @return
         */
        public static String getInsertBidProductDetailt() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.INSERT_BID_PRODUCT_DETAILT_SERVER_NAME);
        }

        /**
         *
         *
         * @return
         */
        public static String getFindSscPaymentBasic() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.FIND_SSC_PAYMENT_BASIC_SERVER_NAME);
        }

        /**
         *
         *
         * @return
         */
        public static String getSavePayment() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.SAVE_PAYMENT_SERVER_NAME);
        }

        /**
         *
         *
         * @return
         */
        public static String getUpdatePayment() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.UPDATE_PAYMENT_SERVER_NAME);
        }

        /**
         *
         *
         * @return
         */
        public static String getFindPaymentDetail() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.FIND_PAYMENT_DETAIL_SERVER_NAME);
        }

        /**
         *
         *
         * @return
         */
        public static String gettoAddPaymentDetail() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.TO_ADD_PAYMENT_DETAIL_SERVER_NAME);
        }

        /**
         *
         *
         * @return
         */
        public static String getFindPaymentDetailById() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.FIND_PAYMENT_DETAIL_BY_ID_SERVER_NAME);
        }

        /**
         *
         *
         * @return
         */
        public static String getFindSscPaymentDetail() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.FIND_SSC_PAYMENT_DETAIL_SERVER_NAME);
        }

        /**
         *
         *
         * @return
         */
        public static String getSavePaymentDetail() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.SAVE_PAYMENT_DETAIL_SERVER_NAME);
        }

        /**
         *
         *
         * @return
         */
        public static String getFindContractPdDetailList() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.FIND_CONTRACT_PD_DETAIL_LIST_SERVER_NAME);
        }

        /**
         *
         *
         * @return
         */
        public static String getModifyContractPacking() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.MODIFY_CONTRACT_PACKING_SERVER_NAME);
        }

        /**
         *
         *
         * @return
         */
        public static String getFindDeliveryPlanList() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.FIND_DELIVERY_PLAN_LIST_SERVER_NAME);
        }

        /**
         *
         *
         * @return
         */
        public static String getSaveDeliveryPlan() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.SAVE_DELIVERY_PLAN_SERVER_NAME);
        }

        /**
         *
         *
         * @return
         */
        public static String getSaveContractOrder() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.SAVE_CONTRACT_ORDER_SERVER_NAME);
        }

        /**
         *
         *
         * @return
         */
        public static String getSaveContractPackageM() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.SAVE_CONTRACT_PACKAGE_M_SERVER_NAME);
        }

        /**
         *
         *
         * @return
         */
        public static String getModifyDeliveryPlan() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.MODIFY_DELIVERY_PLAN_SERVER_NAME);
        }

        /**
         *
         *
         * @return
         */
        public static String getUpdateContractOrder() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.UPDATE_CONTRACT_ORDER_SERVER_NAME);
        }

        /**
         *
         *
         * @return
         */
        public static String getUpdateContractBasic() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.UPDATE_CONTRACT_BASIC_SERVER_NAME);
        }

        /**
         *
         *
         * @return
         */
        public static String getDelContractPd() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.DEL_CONTRACT_PD_SERVER_NAME);
        }

        /**
         *
         *
         * @return
         */
        public static String getDelContractPackgeM() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.DEL_CONTRACT_PACKGE_M_SERVER_NAME);
        }

        /**
         *
         *
         * @return
         */
        public static String getSaveContractBasci() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.SAVE_CONTRACT_BASCI_SERVER_NAME);
        }

        /**
         *
         *
         * @return
         */
        public static String getCheckEffecBoxNum() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.CHECK_EFFEC_BOX_NUM_SERVER_NAME);
        }

        /**
         *
         *
         * @return
         */
        public static String getSaveContractBusiness() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.SAVE_CONTRACT_BUSINESS_SERVER_NAME);
        }

        /**
         *
         *
         * @return
         */
        public static String getUpdateContractBusiness() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.UPDATE_CONTRACT_BUSINESS_SERVER_NAME);
        }

        /**
         *
         *
         * @return
         */
        public static String getFindDeliveryConfirmInfoList() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.FIND_DELIVERY_CONFIRM_INFO_LIST_SERVER_NAME);
        }

        /**
         *
         *
         * @return
         */
        public static String getFindChooseContract() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.FIND_CHOOSE_CONTRACT_SERVER_NAME);
        }

        /**
         *
         *
         * @return
         */
        public static String getFindChooseDelivery() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.FIND_CHOOSE_DELIVERY_SERVER_NAME);
        }

        /**
         *
         *
         * @return
         */
        public static String getInsertDeliveryConfirm() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.INSERT_DELIVERY_CONFIRM_SERVER_NAME);
        }

        /**
         *
         *
         * @return
         */
        public static String getFindDeliveryPreInfo() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.FIND_DELIVERY_PRE_INFO_SERVER_NAME);
        }

        /**
         *
         *
         * @return
         */
        public static String getFindDeliveryPrePd() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.FIND_DELIVERY_PRE_PD_SERVER_NAME);
        }

        /**
         *
         *
         * @return
         */
        public static String getModifyDeliveryIntoInfo() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.MODIFY_DELIVERY_INTO_INFO_SERVER_NAME);
        }

        /**
         *
         *
         * @return
         */
        public static String getFindOrderBasic() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.FIND_ORDER_BASIC_SERVER_NAME);
        }

        /**
         *
         *
         * @return
         */
        public static String getFindSscContractBusiness() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.FIND_SSC_CONTRACT_BUSINESS_SERVER_NAME);
        }

        /**
         *
         *
         * @return
         */
        public static String getFindSscBidBasic() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.FIND_SSC_BID_BASIC_SERVER_NAME);
        }

        /**
         *
         *
         * @return
         */
        public static String getFindContractPackingList() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.FIND_CONTRACT_PACKING_LIST_SERVER_NAME);
        }

        /**
         *
         *
         * @return
         */
        public static String getFindOrderPd() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.FIND_ORDER_PD_SERVER_NAME);
        }

        /**
         * 根据合同id,pdCode 查询
         *
         * @return 根据合同id,pdCode 查询
         */
        public static String getFindPd() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.FIND_PD_SERVER_NAME);
        }

        /**
         *
         *
         * @return
         */
        public static String getCreateDiffer() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.CREATE_DIFFER_SERVER_NAME);
        }

        /**
         *
         *
         * @return
         */
        public static String getQueryDifferBasics() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.QUERY_DIFFER_BASICS_SERVER_NAME);
        }

        /**
         *
         *
         * @return
         */
        public static String getQueryDifferDetails() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.QUERY_DIFFER_DETAILS_SERVER_NAME);
        }

        /**
         *
         *
         * @return
         */
        public static String getUpdatePreInto() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.UPDATE_PREINTO_SERVER_NAME);
        }

        /**
         *
         *
         * @return
         */
        public static String getCreatContracts() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.CREAT_CONTRACTS_SERVER_NAME);
        }

        /**
         * 中标书生成合同判断bid 是否已经存在合同中*
         *
         * @return中标书生成合同判断bid 是否已经存在合同中
         */
        public static String getCheckBid() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.CHECK_BID_SERVER_NAME);
        }

        /**
         * 创建发货订单信息
         *
         * @return创建发货订单信息
         */
        public static String getCreateSscDeliveryOrderInfo() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.CREATE_SSC_DELIVERY_ORDER_INFO_SERVER_NAME);
        }

        /**
         * 查询发货订单列表
         *
         * @return查询发货订单列表
         */
        public static String getFindDeliveryBatchList() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.FIND_DELIVERY_BATCH_LIST_SERVER_NAME);
        }

        /**
         * 查询预入库基本表信息
         *
         * @return查询预入库基本表信息
         */
        public static String getQueryPreintoBasics() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.QUERY_PREINTO_BASICS_SERVER_NAME);
        }

        /**
         * 查询合同编号
         *
         * @return查询合同编号
         */
        public static String getFindDBContractCode() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.FIND_DB_CONTRACT_CODE_SERVER_NAME);
        }

        /**
         * 查询包材产品
         *
         * @return查询包材产品
         */
        public static String getFindPack() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.FIND_PACK_SERVER_NAME);
        }

        /**
         * 查询包材产品
         *
         * @return查询包材产品
         */
        public static String getFindPreInto() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.FIND_PRE_INTO_SERVER_NAME);
        }

        /**
         * 查询包材产品
         *
         * @return查询包材产品
         */
        public static String getDeletePreInto() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.DELETE_PRE_INTO);
        }

        /**
         * 查询包材产品
         *
         * @return查询包材产品
         */
        public static String getDeleteConfirmBasic() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.DELETE_CONFIRM_BASIC);
        }

        /**
         * 新增或更新交货期计划
         *
         * @return新增或更新交货期计划
         */
        public static String getSaveOrUpdateDP() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.SAVE_OR_UPDATE_DP_SERVER_NAME);
        }

        /**
         * 查询发货确认单中待装车产品列表
         *
         * @return查询发货确认单中待装车产品列表
         */
        public static String getChooseConfirmPds() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.CHOOSE_CONFIRM_PDS_SERVER_NAME);
        }

        /**
         * 检查确认单产品是否全部装车
         *
         *
         * @return检查确认单产品是否全部装车
         */
        public static String getCheckPdPlanBox() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.CHECK_PD_PLAN_BOX_SERVER_NAME);
        }

        /**
         * 根据合同 ID，查询合同订单中的产品明细，排除已有包材信息的产品
         *
         * @return根据合同 ID，查询合同订单中的产品明细，排除已有包材信息的产品
         */
        public static String getFindContractProducts() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.FIND_CONTRACT_PRODUCTS_SERVER_NAME);
        }

        /**
         * 查询合同是否有发货订单
         *
         * @return查询合同是否有发货订单
         */
        public static String getCheckHasOrderBasic() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.CHECK_HAS_ORDER_BASIC_SERVER_NAME);
        }

        /**
         * 查询合同是否有发货订单
         *
         * @return查询合同是否有发货订单
         */
        public static String getDeleteContractBasic() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.DELETE_CONTRACT_BASIC_SERVER_NAME);
        }

        /**
         * 查询合同是否有发货订单
         *
         * @return查询合同是否有发货订单
         */
        public static String getSavePreIntoFile() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.SAVE_PRE_INTO_FILE);
        }

        /**
         * 更新差异单主表信息
         *
         * @return更新差异单主表信息
         */
        public static String getUpdateDifferBasic() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.UPDATE_DIFFER_BASIC_SERVER_NAME);
        }

        /**
         * 查询合同是否生成
         *
         * @return查询合同是否生成
         */
        public static String getCheckIsContract() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.CHECK_IS_CONTRACT_SERVER_NAME);
        }

        /**
         * 删除中标成交书基础表
         *
         * @return删除中标成交书基础表
         */
        public static String getDeleteBidBase() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.DELETE_BID_BASE_SERVER_NAME);
        }

        /**
         * 根据中标id,pdCode查询产品是否存在
         *
         * @return根据中标id,pdCode查询产品是否存在
         */
        public static String getFindBidPd() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.FIND_BID_PD_SERVER_NAME);
        }

        /**
         * 批量新增交货计划
         *
         * @return批量新增交货计划
         */
        public static String getSaveBatchDps() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.SAVE_BATCH_DPS);
        }

        /**
         * 更新合同状态为待审核，使合同相关信息可以再次修改
         *
         * @return更新合同状态为待审核,使合同相关信息可以再次修改
         */
        public static String getEnableToModify() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.ENABLE_TO_MODIFY);
        }

        /**
         * 将差异单状更新为已确认
         *
         * @return将差异单状更新为已确认
         */
        public static String getConfirmDifferBasic() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.CONFIRM_DIFFER_BASIC_SERVER_NAME);
        }

        /**
         * 查询资金池列表**@return查询资金池列表
         */
        public static String getSearchPayment() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.SEARCH_PAYMENT_SERVER_NAME);
        }

        /**
         * 查询核销单列表**@return查询核销单列表
         */
        public static String getSearchContractVerification() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.SEARCH_CONTRACT_VERIFICATION_SERVER_NAME);
        }

        /**
         * 查询采供链资金池详细**@return查询采供链资金池详细
         */
        public static String getFindSscCashPoolingDetail() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.FIND_SSC_CASH_POO_LING_DETAIL_SERVER_NAME);
        }

        /**
         * 查询采供链付款申请一览**@return查询采供链付款申请一览
         */
        public static String getFindSscPaymentRequest() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.FIND_SSC_PAYMENT_REQUEST_SERVER_NAME);
        }

        /**
         * 查询支付申请列表**@return查询支付申请列表
         */
        public static String getFindPaymentRequest() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.FIND_PAYMENT_REQUEST_SERVER_NAME);
        }

        /**
         * 新增/编辑支付申请**@return新增/编辑支付申请
         */
        public static String getSaveOrModifyPaymentRequest() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.SAVE_OR_MODIFY_PAYMENT_REQUEST_SERVER_NAME);
        }

        /**
         * 查询付款记录列表**@return查询付款记录列表
         */
        public static String getFindPaymentInfo() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.FIND_PAYMENT_INFO_SERVER_NAME);
        }

        /**
         * 新增/编辑支付记录**@return新增/编辑支付记录
         */
        public static String getSaveOrModifyPaymentInfo() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.SAVE_OR_MODIFY_PAYMENT_INFO_SERVER_NAME);
        }

        /**
         * 删除付款申请**@return删除付款申请
         */
        public static String getDeleteSscPaymentRequest() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.DELETE_SSC_PAYMENT_REQUEST_SERVER_NAME);
        }

        /**
         * 核销发货入库差异**@return核销发货入库差异
         */
        public static String getCalcDelyIntoDiff() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.CALC_DELY_INTO_DIFF_SERVER_NAME);
        }

        /**
         * 核销运费差异**@return核销运费差异
         */
        public static String getCalcTranspExpDiff() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.CALC_TRANSP_EXP_DIFF_SERVER_NAME);
        }

        /**
         * 查询发票申请数据列表**@return查询发票申请数据列表
         */
        public static String getFindSscinvoiceRequestList() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.FIND_SSCINVOICE_REQUEST_LIST_SERVER_NAME);
        }

        /**
         * 删除发票申请数据**@return删除发票申请数据
         */
        public static String getModifyInvoiceRequest() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.MODIFY_INVOICE_REQUEST_SERVER_NAME);
        }

        /**
         * 通过合同创建新的发票申请单**@return通过合同创建新的发票申请单
         */
        public static String getContractToNewInvoiceRequestDetail() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.CONTRACT_TO_NEW_INVOICE_REQUEST_DETAIL_SERVER_NAME);
        }

        /**
         * 查找发票详细**@return查找发票详细
         */
        public static String getFindInvoiceRequestDetail() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.FIND_INVOICE_REQUEST_DETAIL_SERVER_NAME);
        }

        /**
         * 通过合同编号查询发票单是否存在**@return通过合同编号查询发票单是否存在
         */
        public static String getContractFindInvoiceRequestDetailExist() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.CONTRACT_FIND_INVOICE_REQUEST_DETAIL_EXIST_SERVER_NAME);
        }

        /**
         * 添加发票申请单**@return添加发票申请单
         */
        public static String getInsertInvoiceRequest() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.INSERT_INVOICE_REQUEST_SERVER_NAME);
        }

        /**
         * 更新发票信息**@return更新发票信息
         */
        public static String getModifyInvoiceRequestUp() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.MODIFY_INVOICE_REQUEST_UP_SERVER_NAME);
        }

        /**
         * 通过发票申请单号查询合同号与合同ID**@return通过发票申请单号查询合同号与合同ID
         */
        public static String getSearchContractForInvoice() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.SEARCH_CONTRACT_FOR_INVOICE_SERVER_NAME);
        }

        /**
         * 保存发票申请上传的文件信息**@return保存发票申请上传的文件信息
         */
        public static String getSaveInvoiceRequestFileInfo() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.SAVE_INVOICE_REQUEST_FILE_INFO_SERVER_NAME);
        }

        /**
         * 保存发票申请上传的文件信息**@return保存发票申请上传的文件信息
         */
        public static String getAuditVerification() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.AUDIT_VERIFICATION_SERVER_NAME);
        }

        /**
         * 保存发票申请上传的文件信息**@return保存发票申请上传的文件信息
         */
        public static String getSaveOrUpdateVerification() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.SAVE_OR_UPDATE_VERIFICATION_SERVER_NAME);
        }

        /**
         * 保存发票申请上传的文件信息**@return保存发票申请上传的文件信息
         */
        public static String getFindVerificationDetails() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.FIND_VERIFICATION_DETAILS_SERVER_NAME);
        }

        /**
         * 查询合同基本信息、生效日及交货期中的最后交货日**@return查询合同基本信息、生效日及交货期中的最后交货日
         */
        public static String getFindContractPlanInfo() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.FIND_CONTRACT_PLANINFO_SERVER_NAME);
        }

        /**
         * 查询生产期/待运期产品管控**@return查询生产期/待运期产品管控
         */
        public static String getFindProducePdControl() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.FIND_PRODUCE_PD_CONTROL_SERVER_NAME);
        }

        /**
         * 查询入库产品管控**@return查询入库产品管控
         */
        public static String getFindStockProductDetail() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.FIND_STOCK_PRODUCT_DETAIL_SERVER_NAME);
        }

        /**
         * 批量保存或更新生产期/待运期产品管控**@return批量保存或更新生产期/待运期产品管控
         */
        public static String getBatchSaveOrUpdateProducePlan() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.BATCH_SAVE_OR_UPDATE_PRODUCE_PLAN_SERVER_NAME);
        }

        /**
         * 批量保存或更新运输期产品监控**@return批量保存或更新运输期产品监控
         */
        public static String getBatchSaveOrUpdatePdControl() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.BATCH_SAVE_OR_UPDATE_PD_CONTROL_SERVER_NAME);
        }

        /**
         * 查询运输期产品管控
         */
        public static String getFindDeliveryPdControl() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.FIND_DELIVERY_PD_CONTROL_SERVER_NAME);
        }

        /**
         * 查询首付款按比例已支付金额
         */
        public static String getFindDeliveryPDList() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.FIND_DELIVERY_PD_LIST_SERVER_NAME);
        }

        /**
         * 查询首付款按比例已支付金额
         */
        public static String getFindProductHistories() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.FIND_PRODUCT_HISTORIES_SERVER_NAME);
        }

        /**
         * 查询首付款按比例已支付金额
         */
        public static String getFindNoRelatedBidBase() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.FIND_NO_RELATED_BID_BASE_SERVER_NAME);
        }

        /**
         * 查询首付款按比例已支付金额
         */
        public static String getCheckBidBaseExist() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.CHECK_BID_BASE_EXIST_SERVER_NAME);
        }

        /**
         * 查询发货订单关联的预入库单基本信息
         */
        public static String getFindPreIntoListByDeliveryId() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.FIND_PREINTO_LIST_BYDE_LIVERYID_SERVER_NAME);
        }

        /**
         * 关闭合同和核销单
         */
        public static String getCloseContract() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.CLOSE_CONTRACT_SERVER_NAME);
        }

        /**
         * 删除核销单
         */
        public static String getDeleteVerification() {
            return getServerUrl(SystemServerDef.SellerSupplyChain.MODULE_NAME,
                SystemServerDef.SellerSupplyChain.DELETE_VERIFICATION_SERVER_NAME);
        }

    }

    public static class BuyersReportServerManager {
        /**
         * 取得买家买家池已购产品
         * 
         * @return取得买家买家池已购产品
         */
        public static String getfindOrderInfoProductCatalog() {
            return getServerUrl(SystemServerDef.BuyersReport.MODULE_NAME,
                SystemServerDef.BuyersReport.FIND_ORDER_INFO_PRODUCT_CATALOG_SERVER_NAME);
        }

        /**
         * 取得买家买家池归属
         * 
         * @return取得买家买家池归属
         */
        public static String getfindPoolAttribution() {
            return getServerUrl(SystemServerDef.BuyersReport.MODULE_NAME,
                SystemServerDef.BuyersReport.FIND_POOL_ATTRIBUTION_SERVER_NAME);
        }

        /**
         * 取得买家订单汇总信息
         * 
         * @return取得买家订单汇总信息
         */
        public static String getfindOrderInfoList() {
            return getServerUrl(SystemServerDef.BuyersReport.MODULE_NAME,
                SystemServerDef.BuyersReport.FIND_ORDER_INFO_LIST_SERVER_NAME);
        }

        /**
         * 买家标准产品池列表查询接口**@return买家标准产品池列表查询接口
         */
        public static String getFindBrByPoolFileInfo() {
            return getServerUrl(SystemServerDef.BuyersReport.MODULE_NAME,
                SystemServerDef.BuyersReport.FIND_BR_BY_POOL_FILE_INFO_SERVER_NAME);
        }

        /**
         * 买家产品池配置管理列表查询接口**@return买家产品池配置管理列表查询接口
         */
        public static String getFindBrSetting() {
            return getServerUrl(SystemServerDef.BuyersReport.MODULE_NAME,
                SystemServerDef.BuyersReport.FIND_BR_SETTING_SERVER_NAME);
        }

        /**
         * 买家产品池配置管理列表编辑数据接口**@return买家产品池配置管理列表编辑数据接口
         */
        public static String getModifySetting() {
            return getServerUrl(SystemServerDef.BuyersReport.MODULE_NAME,
                SystemServerDef.BuyersReport.MODIFY_SETTING_SERVER_NAME);
        }

        /**
         * 买家产品池配置管理列表删除数据接口**@return买家产品池配置管理列表删除数据接口
         */
        public static String getDeleteSettingDate() {
            return getServerUrl(SystemServerDef.BuyersReport.MODULE_NAME,
                SystemServerDef.BuyersReport.DELETE_SETTING_DATE_SERVER_NAME);
        }

        /**
         * 买家产品池配置管理列表新增数据接口**@return买家产品池配置管理列表新增数据接口
         */
        public static String getInsertDemandPublishDetail() {
            return getServerUrl(SystemServerDef.BuyersReport.MODULE_NAME,
                SystemServerDef.BuyersReport.INSERT_DEMAND_PUBLISH_DETAIL_SERVER_NAME);
        }

        /**
         * 单一买家池列表查询接口**@return单一买家池列表查询接口
         */
        public static String getFindBrOBuyerInfoList() {
            return getServerUrl(SystemServerDef.BuyersReport.MODULE_NAME,
                SystemServerDef.BuyersReport.FIND_BRO_BUYERINFO_LIST_SERVER_NAME);
        }

        /**
         * 单一买家标准产品池列表查询接口**@return单一买家标准产品池列表查询接口
         */
        public static String getFindBrSingleByFileInfoList() {
            return getServerUrl(SystemServerDef.BuyersReport.MODULE_NAME,
                SystemServerDef.BuyersReport.FIND_BR_SINGLE_BY_FILE_INFO_LIST_SERVER_NAME);
        }

        /**
         * 生成单一买家标准产品池在线管控表文件接口**@return生成单一买家标准产品池在线管控表文件接口
         */
        public static String getFindDataResolve() {
            return getServerUrl(SystemServerDef.BuyersReport.MODULE_NAME,
                SystemServerDef.BuyersReport.FIND_DATA_RESOLVE_SERVER_NAME);
        }

        /**
         * 删除冻品管家组管家信息
         */
        public static String getDeleteHkListInHkGroup() {
            return getServerUrl(SystemServerDef.BuyersReport.MODULE_NAME,
                SystemServerDef.BuyersReport.DELETE_HK_LIST_IN_HK_GROUP);
        }

        /**
         * 查询冻品管家组管家信息
         */
        public static String getQueryHkListInHkGroup() {
            return getServerUrl(SystemServerDef.BuyersReport.MODULE_NAME,
                SystemServerDef.BuyersReport.QUERY_HK_LIST_IN_HK_GROUP);
        }

        /**
         * 查询冻品管家组信息
         */
        public static String getQueryHkGroupInfo() {
            return getServerUrl(SystemServerDef.BuyersReport.MODULE_NAME,
                SystemServerDef.BuyersReport.QUERY_HK_GROUP_INFO);
        }

        /**
         * 批量添加冻品管家组的冻品管家
         */
        public static String getUpdateHkGroupInfos() {
            return getServerUrl(SystemServerDef.BuyersReport.MODULE_NAME,
                SystemServerDef.BuyersReport.UPDATE_HK_GROUP_INFOS);
        }

        /**
         * 编辑冻品管家组名称
         */
        public static String getUpdateHkGroupName() {
            return getServerUrl(SystemServerDef.BuyersReport.MODULE_NAME,
                SystemServerDef.BuyersReport.UPDATE_HK_GROUP_NAME);
        }

        /**
         * 同步订单数据**@return同步订单数据
         */
        public static String getUpdateSynchronizedOrderData() {
            return getServerUrl(SystemServerDef.BuyersReport.MODULE_NAME,
                SystemServerDef.BuyersReport.UPDATE_SYNCHRONIZED_ORDER_DATA_SERVER_NAME);
        }

        /**
         * 同步买家数据**@return同步买家数据
         */
        public static String getUpdateSynchronizedBuyerData() {
            return getServerUrl(SystemServerDef.BuyersReport.MODULE_NAME,
                SystemServerDef.BuyersReport.UPDATE_SYNCHRONIZED_BUYER_DATA_SERVER_NAME);
        }

        /**
         * 同步卖家产品数据**@return同步卖家产品数据
         */
        public static String getUpdateSynchronizedSlProduct() {
            return getServerUrl(SystemServerDef.BuyersReport.MODULE_NAME,
                SystemServerDef.BuyersReport.UPDATE_SYNCHRONIZED_SL_PRODUCT_SERVER_NAME);
        }

        /**
         * 同步产品数据**@return同步产品数据
         */
        public static String getUpdateSynchronizedProduct() {
            return getServerUrl(SystemServerDef.BuyersReport.MODULE_NAME,
                SystemServerDef.BuyersReport.UPDATE_SYNCHRONIZED_PRODUCT_SERVER_NAME);
        }

        /**
         * 买家产品分类查询接口**@买家产品分类查询接口
         */
        public static String getFindMachiningCodeU() {
            return getServerUrl(SystemServerDef.BuyersReport.MODULE_NAME,
                SystemServerDef.BuyersReport.FIND_MACHINING_CODE_U_SERVER_NAME);
        }

        /**
         * 同步产品数据**@return同步产品数据
         */
        public static String getQueryHkGroupForHkInfo() {
            return getServerUrl(SystemServerDef.BuyersReport.MODULE_NAME,
                SystemServerDef.BuyersReport.QUERY_HK_GROUP_FOR_HK_INFO);
        }

        /**
         * 同步产品数据**@return同步产品数据
         */
        public static String getQueryFileBuyerPools() {
            return getServerUrl(SystemServerDef.BuyersReport.MODULE_NAME,
                SystemServerDef.BuyersReport.QUERY_FILE_BUYER_POOLS_SERVER_NAME);
        }

        /**
         * 同步产品数据**@return同步产品数据
         */
        public static String getGenerateAndUploadExcelFiles() {
            return getServerUrl(SystemServerDef.BuyersReport.MODULE_NAME,
                SystemServerDef.BuyersReport.GENERATE_AND_UPLOAD_EXCEL_FILES_SERVER_NAME);
        }

        /**
         * 同步产品数据**@return同步产品数据
         */
        public static String getGenerateAndUploadOnlineExcelFiles() {
            return getServerUrl(SystemServerDef.BuyersReport.MODULE_NAME,
                SystemServerDef.BuyersReport.GENERATE_AND_UPLOAD_ONLINE_EXCEL_FILES_SERVER_NAME);
        }

        /**
         * 同步产品数据**@return同步产品数据
         */
        public static String getByReportSearch() {
            return getServerUrl(SystemServerDef.BuyersReport.MODULE_NAME,
                SystemServerDef.BuyersReport.BY_REPORT_SEARCH_SERVER_NAME);
        }

        /**
         * 同步产品数据**@return同步产品数据
         */
        public static String getBuyerPoolFileCreate() {
            return getServerUrl(SystemServerDef.BuyersReport.MODULE_NAME,
                SystemServerDef.BuyersReport.BUYER_POOL_FILE_CREATE_SERVER_NAME);
        }

        /**
         * 同步产品数据**@return同步产品数据
         */
        public static String getFindMarketNameListByLevel() {
            return getServerUrl(SystemServerDef.BuyersReport.MODULE_NAME,
                SystemServerDef.BuyersReport.FIND_MARKET_NAME_LIST_BY_LEVEL_SERVER_NAME);
        }

        /**
         * 同步产品数据**@return同步产品数据
         */
        public static String getCreateMarketingPeriodExcel() {
            return getServerUrl(SystemServerDef.BuyersReport.MODULE_NAME,
                SystemServerDef.BuyersReport.CREATE_MARKETING_PERIOD_EXCEL_SERVER_NAME);
        }

        /**
         * 同步产品数据**@return同步产品数据
         */
        public static String getCreateSalesPeriodExcel() {
            return getServerUrl(SystemServerDef.BuyersReport.MODULE_NAME,
                SystemServerDef.BuyersReport.CREATE_SALES_PERIOD_EXCEL_SERVER_NAME);
        }

        /**
         * 同步产品数据**@return同步产品数据
         */
        public static String getSearchPubliBuyerPoolInformation() {
            return getServerUrl(SystemServerDef.BuyersReport.MODULE_NAME,
                SystemServerDef.BuyersReport.SEARCH_PUBLI_BUYER_POOL_INFORMATION_SERVER_NAME);
        }

        /**
         * 专属会员报表生成接口
         */
        public static String getExclusiveExcelFilesSave() {
            return getServerUrl(SystemServerDef.BuyersReport.MODULE_NAME,
                SystemServerDef.BuyersReport.EXCLUSIVE_EXCEL_FILES_SAVE_SERVER_NAME);
        }

        /**
         * 专属会员报表生成接口
         */
        public static String getGetSaleInfoByBuyerId() {
            return getServerUrl(SystemServerDef.BuyersReport.MODULE_NAME,
                SystemServerDef.BuyersReport.GET_SALE_INFO_BY_BUYER_ID_SERVER_NAME);
        }

        /**
         * 专属会员报表生成接口
         */
        public static String getQueryBrBuyerPoolByBuyerId() {
            return getServerUrl(SystemServerDef.BuyersReport.MODULE_NAME,
                SystemServerDef.BuyersReport.QUERY_BR_BUYER_POOL_BY_BUYER_ID_SERVER_NAME);
        }

        /**
         * 新增冻品管家组信息接口
         */
        public static String getAddHkGroup() {
            return getServerUrl(SystemServerDef.BuyersReport.MODULE_NAME,
                SystemServerDef.BuyersReport.ADD_HK_GROUP_SERVER_NAME);
        }

        /**
         * 获取同步上线状态买家列表
         */
        public static String getSynMarketingStatusByOrder() {
            return getServerUrl(SystemServerDef.BuyersReport.MODULE_NAME,
                SystemServerDef.BuyersReport.SYN_MARKETING_STATUS_BY_ORDER_SERVER_NAME);
        }

        /**
         * 更新营销期和销售期买家买家池关系接口
         */
        public static String getUpdateMarketingAndSalePeriod() {
            return getServerUrl(SystemServerDef.BuyersReport.MODULE_NAME,
                SystemServerDef.BuyersReport.UPDATE_MARKETING_PERIOD_SERVER_NAME);
        }

        /**
         * 根据订单获取买家所属买家池
         */
        public static String getSynBuyerPdClasses() {
            return getServerUrl(SystemServerDef.BuyersReport.MODULE_NAME,
                SystemServerDef.BuyersReport.SYN_BUYER_PD_CLASSES_SERVER_NAME);
        }

        /**
         * 根据管家查询所属管家组信息接口
         */
        public static String getQueryHouseKeeperOfHkGroup() {
            return getServerUrl(SystemServerDef.BuyersReport.MODULE_NAME,
                SystemServerDef.BuyersReport.QUERY_HOUSE_KEEPER_OF_HK_GROUP_SERVER_NAME);
        }

        /**
         * 更新买家上线状态履历表
         */
        public static String getUpdateBuyerMarketingStatusHistory() {
            return getServerUrl(SystemServerDef.BuyersReport.MODULE_NAME,
                SystemServerDef.BuyersReport.UPDATE_BUYER_MARKETING_STATUS_HISTORY_SERVER_NAME);
        }

        /**
         * 更新买家池产品分类基础表
         */
        public static String getUpdateMPdClasses() {
            return getServerUrl(SystemServerDef.BuyersReport.MODULE_NAME,
                SystemServerDef.BuyersReport.UPDATE_M_PD_CLASSES_SERVER_NAME);
        }

        /**
         * 查询买家池产品分类基础表
         */
        public static String getSelectMPdClasses() {
            return getServerUrl(SystemServerDef.BuyersReport.MODULE_NAME,
                SystemServerDef.BuyersReport.SELECT_M_PD_CLASSES_SERVER_NAME);
        }

        /**
         * 通过BuyerId批量获取所属买家池名称
         */
        public static String getQueryPoolsByBuyerIds() {
            return getServerUrl(SystemServerDef.BuyersReport.MODULE_NAME,
                SystemServerDef.BuyersReport.QUERY_POOLS_BY_BUYER_IDS_SERVER_NAME);
        }

        /**
         * 建立管家和买家关系接口
         */
        public static String getUpdateBindingRelationship() {
            return getServerUrl(SystemServerDef.BuyersReport.MODULE_NAME, SystemServerDef.BuyersReport.UPDATE_BINDING_RELATIONSHIP_SERVER_NAME);
        }

        /**
         * 解除管家和买家关系接口
         */
        public static String getUpdateUnBindingRelationship() {
            return getServerUrl(SystemServerDef.BuyersReport.MODULE_NAME, SystemServerDef.BuyersReport.UPDATE_UN_BINDING_RELATIONSHIP_SERVER_NAME);
        }

        /**
         * 更新买家池买家基本信息
         */
        public static String getUpdateBuyerReportInfo() {
            return getServerUrl(SystemServerDef.BuyersReport.MODULE_NAME, SystemServerDef.BuyersReport.UPDATE_BUYER_REPORT_INFO_SERVER_NAME);
        }
    }

    /**
     * Sso Server Manager,里面包含Sso所有的服务地址
     */
    public static class SsoServerManager {
        public static String getCasServerLogoutUrl() {

            // return getServerUrl(SystemServerDef.SsoServer.MODULE_NAME,
            // SystemServerDef.SsoServer.CAS_SERVER_LOGOUT_URL);
            return getCasServerUrlPrefix() + "/logout";
        }

        public static String getCasServerLoginUrl() {
            // return getServerUrl(SystemServerDef.SsoServer.MODULE_NAME,
            // SystemServerDef.SsoServer.CAS_SERVER_LOGIN_URL);
            return getCasServerUrlPrefix() + "/login";
        }

        public static String getCasServerUrlPrefix() {
            ConfigParam param = getConfigParam(SystemServerDef.SsoServer.MODULE_NAME,
                SystemServerDef.SsoServer.CAS_SERVER_URL_PREFIX_URL);
            RsRequest<ConfigParam> request = new RsRequest<>();
            request.setParam(param);
            TypeReference typeReference = new TypeReference<RsResponse<HashMap<String, String>>>() {};
            RsResponse<HashMap<String, String>> response = RestClientUtil.post(getConfigConstantUrl(), request,
                typeReference);
            HashMap<String, String> result = response.getResult();
            return result.get(SystemServerDef.SsoServer.CAS_SERVER_URL_PREFIX_URL);
        }

    }

    /**
     * Pd Server Manager,里面包含Pd所有的服务地址
     */
    public static class PdServerManager {
        /**
         * 产品等级一览查询接口
         *
         * @return产品等级一览查询接口
         */
        public static String getFindListGrades() {
            return getServerUrl(SystemServerDef.PdServer.MODULE_NAME,
                SystemServerDef.PdServer.FIND_LIST_GRADES_SERVER_NAME);
        }

        /**
         *
         *
         * @return
         */
        public static String getFindProductInfo() {
            return getServerUrl(SystemServerDef.PdServer.MODULE_NAME, SystemServerDef.PdServer.FIND_PRODUCT_INFO);
        }

        /**
         *
         *
         * @return
         */
        public static String getFindProductPackage() {
            return getServerUrl(SystemServerDef.PdServer.MODULE_NAME, SystemServerDef.PdServer.Find_Product_Package);
        }

        /**
         *
         *
         * @return
         */
        public static String getGetPdClassesTreeInfo() {
            return getServerUrl(SystemServerDef.PdServer.MODULE_NAME,
                SystemServerDef.PdServer.GET_PD_CLASSES_TREE_INFO);
        }

        /**
         *
         *
         * @return
         */
        public static String getGetPDSupp() {
            return getServerUrl(SystemServerDef.PdServer.MODULE_NAME, SystemServerDef.PdServer.GET_PD_SUPP);
        }

        /**
         *
         *
         * @return
         */
        public static String getGetPriceprdLogiarea() {
            return getServerUrl(SystemServerDef.PdServer.MODULE_NAME, SystemServerDef.PdServer.GET_PRICEPRD_LOGIAREA);
        }

        /**
         *
         *
         * @return
         */
        public static String getPdBatchName() {
            return getServerUrl(SystemServerDef.PdServer.MODULE_NAME, SystemServerDef.PdServer.PD_BATCH_NAME);
        }

        /**
         *
         *
         * @return
         */
        public static String getPdProductStd() {
            return getServerUrl(SystemServerDef.PdServer.MODULE_NAME, SystemServerDef.PdServer.PD_PRODUCT_STD);
        }

        /**
         *
         *
         * @return
         */
        public static String getPdTypeName() {
            return getServerUrl(SystemServerDef.PdServer.MODULE_NAME, SystemServerDef.PdServer.PD_TYPE_NAME);
        }

        /**
         *
         *
         * @return
         */
        public static String getFindAllPdCode() {
            return getServerUrl(SystemServerDef.PdServer.MODULE_NAME, SystemServerDef.PdServer.FIND_ALL_PD_CODE);
        }

        /**
         *
         *
         * @return
         */
        public static String getFindPdBreed() {
            return getServerUrl(SystemServerDef.PdServer.MODULE_NAME, SystemServerDef.PdServer.Find_Pd_Breed);
        }

        /**
         *
         *
         * @return
         */
        public static String getFindPdClasses() {
            return getServerUrl(SystemServerDef.PdServer.MODULE_NAME, SystemServerDef.PdServer.FIND_PD_CLASSES);
        }

        /**
         *
         *
         * @return
         */
        public static String getFindPdFeature() {
            return getServerUrl(SystemServerDef.PdServer.MODULE_NAME, SystemServerDef.PdServer.FIND_PD_FEATURE);
        }

        /**
         *
         *
         * @return
         */
        public static String getFindPdMachining() {
            return getServerUrl(SystemServerDef.PdServer.MODULE_NAME, SystemServerDef.PdServer.FIND_PD_MACHINING);
        }

        /**
         *
         *
         * @return
         */
        public static String getFindPdNormsStd() {
            return getServerUrl(SystemServerDef.PdServer.MODULE_NAME, SystemServerDef.PdServer.FIND_PD_NORMS_STD);
        }

        /**
         *
         *
         * @return
         */
        public static String getFindPdWeight() {
            return getServerUrl(SystemServerDef.PdServer.MODULE_NAME, SystemServerDef.PdServer.FIND_PD_WEIGHT);
        }

        /**
         *
         *
         * @return
         */
        public static String getFindProductInfos() {
            return getServerUrl(SystemServerDef.PdServer.MODULE_NAME, SystemServerDef.PdServer.FIND_PRODUCT_INFOS);
        }

        /**
         *
         *
         * @return
         */
        public static String getFindProductStandard() {
            return getServerUrl(SystemServerDef.PdServer.MODULE_NAME, SystemServerDef.PdServer.FIND_PRODUCT_STANDARD);
        }

        /**
         *
         *
         * @return
         */
        public static String getFindProviderList() {
            return getServerUrl(SystemServerDef.PdServer.MODULE_NAME, SystemServerDef.PdServer.FIND_PROVIDER_LIST);
        }

        /**
         *
         *
         * @return
         */
        public static String getGetPdClassesTreeMatInfo() {
            return getServerUrl(SystemServerDef.PdServer.MODULE_NAME,
                SystemServerDef.PdServer.GET_PD_CLASSES_TREE_MAT_INFO);
        }

        /**
         *
         *
         * @return
         */
        public static String getGetProviderPackageInfo() {
            return getServerUrl(SystemServerDef.PdServer.MODULE_NAME,
                SystemServerDef.PdServer.GET_PROVIDER_PACKAGE_INFO);
        }

        /**
         *
         *
         * @return
         */
        public static String getSaveMctProvider() {
            return getServerUrl(SystemServerDef.PdServer.MODULE_NAME, SystemServerDef.PdServer.SAVE_MCT_PROVIDER);
        }

        /**
         *
         *
         * @return
         */
        public static String getSaveProviderPackage() {
            return getServerUrl(SystemServerDef.PdServer.MODULE_NAME, SystemServerDef.PdServer.SAVE_PROVIDER_PACKAGE);
        }

        /**
         *
         *
         * @return
         */
        public static String getSaveTncStdDiscussProvider() {
            return getServerUrl(SystemServerDef.PdServer.MODULE_NAME,
                SystemServerDef.PdServer.SAVE_TNC_STD_DISCUSS_PROVIDER);
        }

        /**
         *
         *
         * @return
         */
        public static String getSearchProYyStatus() {
            return getServerUrl(SystemServerDef.PdServer.MODULE_NAME, SystemServerDef.PdServer.SEARCH_PRO_YY_STATUS);
        }

        /**
         * 根据产品编码查询档案卡
         *
         * @return 根据产品编码查询档案卡
         */
        public static String getPdStandardByCodes() {
            return getServerUrl(SystemServerDef.PdServer.MODULE_NAME,
                SystemServerDef.PdServer.GET_PD_STANDARD_BY_CODES);
        }

        /**
         * 查询所有产品分类，例如鸡产品，鸭产品
         */
        public static String getFindClasses() {
            return getServerUrl(SystemServerDef.PdServer.MODULE_NAME,
                SystemServerDef.PdServer.FIND_CLASSES_SERVER_NAME);
        }

        /**
         * 查询产品的定义等级和编码，例如A1，A2，A3
         */
        public static String getFindListGrade() {
            return getServerUrl(SystemServerDef.PdServer.MODULE_NAME,
                SystemServerDef.PdServer.FIND_LIST_GRADE_SERVER_NAME);
        }

        /**
         * 查询产品的销售状态定义一览，例如规划，研发，试销
         */
        public static String getFindListSaleStatus() {
            return getServerUrl(SystemServerDef.PdServer.MODULE_NAME,
                SystemServerDef.PdServer.FIND_LIST_SALE_STATUS_SERVER_NAME);
        }

        /**
         * 查询产品加工程度名称和编码
         */
        public static String getFindMachining() {
            return getServerUrl(SystemServerDef.PdServer.MODULE_NAME,
                SystemServerDef.PdServer.FIND_MACHINING_SERVER_NAME);
        }

        /**
         * 查询产品名称和主码，按照有无编码和有无生效日期来区分返回的数据量
         */
        public static String getFindStandard() {
            return getServerUrl(SystemServerDef.PdServer.MODULE_NAME,
                SystemServerDef.PdServer.FIND_STANDARD_SERVER_NAME);
        }

        /**
         * 查询指定产品品种的标准技术档案卡信息
         */
        public static String getFindPdQltStd() {
            return getServerUrl(SystemServerDef.PdServer.MODULE_NAME,
                SystemServerDef.PdServer.FIND_PD_QLT_STD_SERVER_NAME);
        }

        /**
         * 把卖家定义的包装标准添加到标准产品包装档案卡里
         */
        public static String getCreatePdNormsStd() {
            return getServerUrl(SystemServerDef.PdServer.MODULE_NAME,
                SystemServerDef.PdServer.CREATE_PD_NORMS_STD_SERVER_NAME);
        }

        /**
         * 1产品分类
         */
        public static String getFindPdClassesAndList() {
            return getServerUrl(SystemServerDef.PdServer.MODULE_NAME,
                SystemServerDef.PdServer.FIND_PD_CLASSES_AND_LIST_SERVER_NAME);
        }

        /**
         * 根据制定的物流区编码查询此物流区下的所有产品信息（待定义,供分销管理系统使用）
         */
        public static String getQueryBidLogiArea() {
            return getServerUrl(SystemServerDef.PdServer.MODULE_NAME,
                SystemServerDef.PdServer.QUERY_BID_LOGI_AREA_SERVER_NAME);
        }

        /**
         * 3产品信息
         */
        public static String getFindPdInformation() {
            return getServerUrl(SystemServerDef.PdServer.MODULE_NAME,
                SystemServerDef.PdServer.FIND_PD_INFORMATION_SERVER_NAME);
        }

        /**
         * 查询产品的加工技术档案卡信息
         */
        public static String getFindListMct() {
            return getServerUrl(SystemServerDef.PdServer.MODULE_NAME,
                SystemServerDef.PdServer.FIND_LIST_MCT_SERVER_NAME);
        }

        /**
         * 查询产品的原料种源信息
         */
        public static String getSelectMatSource() {
            return getServerUrl(SystemServerDef.PdServer.MODULE_NAME,
                SystemServerDef.PdServer.SELECT_MAT_SOURCE_SERVER_NAME);
        }

        /**
         * 查询产品的原种种源档案卡信息信息
         */
        public static String getSelectOrgSource() {
            return getServerUrl(SystemServerDef.PdServer.MODULE_NAME,
                SystemServerDef.PdServer.SELECT_ORG_SOURCE_SERVER_NAME);
        }

        /**
         * 查询产品的饲养指标档案卡信息
         */
        public static String getSelectFedSource() {
            return getServerUrl(SystemServerDef.PdServer.MODULE_NAME,
                SystemServerDef.PdServer.SELECT_FED_SOURCE_SERVER_NAME);
        }

        /**
         * 查询产品的通用质量指标档案卡信息
         */
        public static String getSelectGnqSource() {
            return getServerUrl(SystemServerDef.PdServer.MODULE_NAME,
                SystemServerDef.PdServer.SELECT_GNQ_SOURCE_SERVER_NAME);
        }

        /**
         * 查询产品的储存运输指标档案卡信息
         */
        public static String getSelectTspSource() {
            return getServerUrl(SystemServerDef.PdServer.MODULE_NAME,
                SystemServerDef.PdServer.SELECT_TSP_SOURCE_SERVER_NAME);
        }

        /**
         * 查询产品的安全卫生指标档案卡信息
         */
        public static String getFindPdGradeHealth() {
            return getServerUrl(SystemServerDef.PdServer.MODULE_NAME,
                SystemServerDef.PdServer.FIND_PD_GRADE_HEALTH_SERVER_NAME);
        }

        /**
         * 查询产品品种名称和编码
         */
        public static String getFindBreedList() {
            return getServerUrl(SystemServerDef.PdServer.MODULE_NAME,
                SystemServerDef.PdServer.FIND_BREED_LIST_SERVER_NAME);
        }

        /**
         * 提供神农客电商平台产品档案卡同步接口
         */
        public static String getFindOrdList() {
            return getServerUrl(SystemServerDef.PdServer.MODULE_NAME,
                SystemServerDef.PdServer.FIND_ORD_LIST_SERVER_NAME);
        }

        /**
         * 提供神农客电商平台产品档案卡同步接口
         */
        public static String getFindFedList() {
            return getServerUrl(SystemServerDef.PdServer.MODULE_NAME,
                SystemServerDef.PdServer.FIND_FED_LIST_SERVER_NAME);
        }

        /**
         * 提供神农客电商平台产品档案卡同步接口
         */
        public static String getFindPdMctList() {
            return getServerUrl(SystemServerDef.PdServer.MODULE_NAME,
                SystemServerDef.PdServer.FIND_PD_MCT_LIST_SERVER_NAME);
        }

        /**
         * 提供神农客电商平台产品档案卡同步接口
         */
        public static String getFindTncList() {
            return getServerUrl(SystemServerDef.PdServer.MODULE_NAME,
                SystemServerDef.PdServer.FIND_TNC_LIST_SERVER_NAME);
        }

        /**
         * 提供神农客电商平台产品档案卡同步接口
         */
        public static String getFindGnqList() {
            return getServerUrl(SystemServerDef.PdServer.MODULE_NAME,
                SystemServerDef.PdServer.FIND_GNQ_LIST_SERVER_NAME);
        }

        /**
         * 提供神农客电商平台产品档案卡同步接口
         */
        public static String getFindSftList() {
            return getServerUrl(SystemServerDef.PdServer.MODULE_NAME,
                SystemServerDef.PdServer.FIND_SFT_LIST_SERVER_NAME);
        }

        /**
         * 提供神农客电商平台产品档案卡同步接口
         */
        public static String getFindTspList() {
            return getServerUrl(SystemServerDef.PdServer.MODULE_NAME,
                SystemServerDef.PdServer.FIND_TSP_LIST_SERVER_NAME);
        }

        /**
         * 查询产品包装名称和编码，详细信息
         */
        public static String getFindPackageList() {
            return getServerUrl(SystemServerDef.PdServer.MODULE_NAME,
                SystemServerDef.PdServer.FIND_PACKAGE_LIST_SERVER_NAME);
        }

        /**
         * 提供美侍客电商平台产品同步接口
         */
        public static String getFindMatList() {
            return getServerUrl(SystemServerDef.PdServer.MODULE_NAME,
                SystemServerDef.PdServer.FIND_MAT_LIST_SERVER_NAME);
        }

        /**
         * 根据制定的物流区编码查询此物流区下的所有包含等级的产品信息
         */
        public static String getQueryPdGradeBidLogiArea() {
            return getServerUrl(SystemServerDef.PdServer.MODULE_NAME,
                SystemServerDef.PdServer.QUERY_PD_GRADE_BID_LOGI_AREA_SERVER_NAME);
        }

        /**
         * 提供美侍客电商平台StandardID产品同步接口
         */
        public static String getQueryPdStandardList() {
            return getServerUrl(SystemServerDef.PdServer.MODULE_NAME,
                SystemServerDef.PdServer.QUERY_PD_STANDARD_LIST_SERVER_NAME);
        }

        /**
         * 提供神农客电商平台价盘等级通道同步接口
         */
        public static String getFindPdPriceWay() {
            return getServerUrl(SystemServerDef.PdServer.MODULE_NAME,
                SystemServerDef.PdServer.FIND_PD_PRICE_WAY_SERVER_NAME);
        }

        /**
         * 提供卖家产品库存查询接口
         */
        public static String getFindPdStock() {
            return getServerUrl(SystemServerDef.PdServer.MODULE_NAME,
                SystemServerDef.PdServer.FIND_PD_STOCK_SERVER_NAME);
        }

        /**
         * 提供卖家申请产品审核状态查询
         */
        public static String getFindSlPdAuditStatus() {
            return getServerUrl(SystemServerDef.PdServer.MODULE_NAME,
                SystemServerDef.PdServer.FIND_SL_PD_AUDIT_STATUS_SERVER_NAME);
        }

        /**
         * 产品对应生产商信息查询
         */
        public static String getFindPdManufactureInfo() {
            return getServerUrl(SystemServerDef.PdServer.MODULE_NAME,
                SystemServerDef.PdServer.FIND_PD_MANUFACTURE_INFO_SERVER_NAME);
        }

        /**
         * 提供美迪福系统数据同步用的接口，返回产品包装信息
         */
        public static String getFindProductNormsStd() {
            return getServerUrl(SystemServerDef.PdServer.MODULE_NAME,
                SystemServerDef.PdServer.FIND_PRODUCT_NORMS_STD_SERVER_NAME);
        }

        /**
         * 查询产品特征名称和编码
         */
        public static String getFindProductFeature() {
            return getServerUrl(SystemServerDef.PdServer.MODULE_NAME,
                SystemServerDef.PdServer.FIND_PRODUCT_FEATURE_SERVER_NAME);
        }

        /**
         * 查询产品净重名称和编码
         */
        public static String getFindProductWeight() {
            return getServerUrl(SystemServerDef.PdServer.MODULE_NAME,
                SystemServerDef.PdServer.FIND_PRODUCT_WEIGHT_SERVER_NAME);
        }

        /**
         * 举报类型查询
         */
        public static String getSearchReportType() {
            return getServerUrl(SystemServerDef.PdServer.MODULE_NAME,
                SystemServerDef.PdServer.SEARCH_REPORT_TYPE_SERVER_NAME);
        }

        /**
         * 新增修改举报
         */
        public static String getInsertReportInfo() {
            return getServerUrl(SystemServerDef.PdServer.MODULE_NAME,
                SystemServerDef.PdServer.INSERT_REPORT_INFO_SERVER_NAME);
        }

        /**
         * 举报一览查询
         */
        public static String getSearchReportList() {
            return getServerUrl(SystemServerDef.PdServer.MODULE_NAME,
                SystemServerDef.PdServer.SEARCH_REPORT_LIST_SERVER_NAME);
        }

        /***/
        public static String getFindProductClasses() {
            return getServerUrl(SystemServerDef.PdServer.MODULE_NAME,
                SystemServerDef.PdServer.FIND_PRODUCT_CLASSES_SERVER_NAME);
        }

        /***/
        public static String getFindProductGrade() {
            return getServerUrl(SystemServerDef.PdServer.MODULE_NAME,
                SystemServerDef.PdServer.FIND_PRODUCT_GRADE_SERVER_NAME);
        }

        /***/
        public static String getFindProductMachining() {
            return getServerUrl(SystemServerDef.PdServer.MODULE_NAME,
                SystemServerDef.PdServer.FIND_PRODUCT_MACHINING_SERVER_NAME);
        }

        /***/
        public static String getFindProductBreed() {
            return getServerUrl(SystemServerDef.PdServer.MODULE_NAME,
                SystemServerDef.PdServer.FIND_PRODUCT_BREED_SERVER_NAME);
        }

    }

    /**
     * Common Server Manager,里面包含Common所有的服务地址
     */
    public static class CommonServerManager {
        /**
         *
         *
         * @return
         */
        public static String getFtpIp() {
            return getCommonServerUrl(SystemServerDef.CommonServer.MODULE_NAME, SystemServerDef.CommonServer.FTP_IP);
        }

        /**
         *
         *
         * @return
         */
        public static String getFtpUser() {
            return getCommonServerUrl(SystemServerDef.CommonServer.MODULE_NAME, SystemServerDef.CommonServer.FTP_USER);
        }

        /**
         *
         *
         * @return
         */
        public static String getFtpPwd() {
            return getCommonServerUrl(SystemServerDef.CommonServer.MODULE_NAME, SystemServerDef.CommonServer.FTP_PWD);
        }

        /**
         *
         *
         * @return
         */
        public static String getFtpImageRootPath() {
            return getCommonServerUrl(SystemServerDef.CommonServer.MODULE_NAME,
                SystemServerDef.CommonServer.FTP_IMAGE_ROOT_PATH);
        }

        /**
         *
         *
         * @return
         */
        public static String getWmsFtpIp() {
            return getCommonServerUrl(SystemServerDef.CommonServer.MODULE_NAME,
                SystemServerDef.CommonServer.WMS_FTP_IP);
        }

        /**
         *
         *
         * @return
         */
        public static String getWmsFtpUser() {
            return getCommonServerUrl(SystemServerDef.CommonServer.MODULE_NAME,
                SystemServerDef.CommonServer.WMS_FTP_USER);
        }

        /**
         *
         *
         * @return
         */
        public static String getWmsFtpPwd() {
            return getCommonServerUrl(SystemServerDef.CommonServer.MODULE_NAME,
                SystemServerDef.CommonServer.WMS_FTP_PWD);
        }

        /**
         *
         *
         * @return
         */
        public static String getFlieServerUpload() {
            return getCommonServerUrl(SystemServerDef.CommonServer.MODULE_NAME,
                SystemServerDef.CommonServer.FILE_SERVER_UPLOAD);
        }

        /**
         *
         *
         * @return
         */
        public static String getFlieServer() {
            return getCommonServerUrl(SystemServerDef.CommonServer.MODULE_NAME,
                SystemServerDef.CommonServer.FILE_SERVER);
        }

        /**
         *
         *
         * @return
         */
        public static String getFlieUploadServices() {
            return getCommonServerUrl(SystemServerDef.CommonServer.MODULE_NAME,
                SystemServerDef.CommonServer.FILE_UPLOAD_SERVICES);
        }

        /**
         *
         *
         * @return
         */
        public static String getMskFlieDownLoadServers() {
            return getCommonServerUrl(SystemServerDef.CommonServer.MODULE_NAME,
                SystemServerDef.CommonServer.MSK_FILE_DOWNLOAD_SERVICES);
        }

        /**
         *
         *
         * @return
         */
        public static String getSellerServer() {
            return getCommonServerUrl(SystemServerDef.CommonServer.MODULE_NAME,
                SystemServerDef.CommonServer.SELLER_SERVER);
        }

        /**
         * 文件服务器
         */
        public static String getGetFlieServerUploadForJsp() {
            return getCommonServerUrl(SystemServerDef.CommonServer.MODULE_NAME,
                SystemServerDef.CommonServer.GET_FLIE_SERVER_UPLOAD_FOR_JSP_SERVER_NAME);
        }

        /**
         * 内网
         */
        public static String getMskFlieDownLoad() {
            return getCommonServerUrl(SystemServerDef.CommonServer.MODULE_NAME,
                SystemServerDef.CommonServer.MSK_FLIE_DOWN_LOAD_SERVER_NAME);
        }

        /**
         *
         */
        public static String getFileServerDownloadProxy() {
            return getCommonServerUrl(SystemServerDef.CommonServer.MODULE_NAME,
                SystemServerDef.CommonServer.FILE_SERVER_DOWNLOAD_PROXY_SERVER_NAME);
        }

        /***/
        public static String getIsForceModifyPassword() {
            return getCommonServerUrl(SystemServerDef.CommonServer.MODULE_NAME,
                SystemServerDef.CommonServer.IS_FORCE_MODIFY_PASSWORD_SERVER_NAME);
        }

        /***/
        public static String getIsCheckPasswordTimeSeries() {
            return getCommonServerUrl(SystemServerDef.CommonServer.MODULE_NAME,
                SystemServerDef.CommonServer.IS_CHECK_PASSWORD_TIME_SERIES_SERVER_NAME);
        }

        /***/
        public static String getIsInitPassword() {
            return getCommonServerUrl(SystemServerDef.CommonServer.MODULE_NAME,
                SystemServerDef.CommonServer.IS_INIT_PASSWORD_SERVER_NAME);
        }

        /***/
        public static String getMailHtmlSend() {
            return getCommonServerUrl(SystemServerDef.CommonServer.MODULE_NAME,
                    SystemServerDef.CommonServer.MAIL_HTML_SEND_SERVER_NAME);
        }

        /***/
        public static String getMailSimpleSend() {
            return getCommonServerUrl(SystemServerDef.CommonServer.MODULE_NAME,
                    SystemServerDef.CommonServer.MAILSIMPLESEND_SERVER_NAME);
        }

        /***/
        public static String getMailtemplateSend() {
            return getCommonServerUrl(SystemServerDef.CommonServer.MODULE_NAME,
                    SystemServerDef.CommonServer.MAIL_TEMPLATE_SEND_SERVER_NAME);
        }
    }

    /**
     * Common Server Manager,里面包含Common所有的服务地址
     */
    public static class CacheServerManager {
        private static Logger logger = LoggerFactory.getLogger(CacheServerManager.class);

        private static String getServerUrl(String moduleName, String key) {
            RsRequest<ConfigParam> request = new RsRequest<>();
            request.setParam(getConfigParam(moduleName, key));
            TypeReference typeReference = new TypeReference<RsResponse<HashMap<String, String>>>() {};
            RsResponse<HashMap<String, String>> response = RestClientUtil.post(getConfigConstantUrl(), request,
                typeReference);
            HashMap<String, String> result = response.getResult();
            return result.get(ENVIRONMENT) + result.get(moduleName) + result.get(key);
        }

        public static String getPutConfigConstCache() {
            String moduleName = SystemServerDef.Cache.MODULE_NAME;
            String key = SystemServerDef.Cache.PUT_CONFIG_CONST_CACHE_SERVER_NAME;
            String cacheKey = ENVIRONMENT + "_" + moduleName + "_" + key;
            String putConfigConstCacheUrl = ConfigConstCacheManager.getCacheServerUrl(cacheKey);
            if (StringUtil.isEmpty(putConfigConstCacheUrl)) {
                logger.info("没有缓存数据,从Config Server中获得数据,缓存Key:" + cacheKey);
                putConfigConstCacheUrl = CacheServerManager.getServerUrl(SystemServerDef.Cache.MODULE_NAME,
                    SystemServerDef.Cache.PUT_CONFIG_CONST_CACHE_SERVER_NAME);
                ConfigConstCacheManager.setCacheServerUrl(cacheKey, putConfigConstCacheUrl);
            }
            logger.info("Key:" + cacheKey + " value:" + putConfigConstCacheUrl);

            return putConfigConstCacheUrl;
        }

        public static String getGetConfigConstCache() {
            String moduleName = SystemServerDef.Cache.MODULE_NAME;
            String key = SystemServerDef.Cache.GET_CONFIG_CONST_CACHE_SERVER_NAME;
            String cacheKey = ENVIRONMENT + "_" + moduleName + "_" + key;
            String getConfigConstCacheUrl = ConfigConstCacheManager.getCacheServerUrl(cacheKey);
            if (StringUtil.isEmpty(getConfigConstCacheUrl)) {
                logger.info("没有缓存数据,从Config Server中获得数据,缓存Key:" + cacheKey);
                getConfigConstCacheUrl = CacheServerManager.getServerUrl(moduleName, key);
                ConfigConstCacheManager.setCacheServerUrl(cacheKey, getConfigConstCacheUrl);
            }
            logger.info("Key:" + cacheKey + " value:" + getConfigConstCacheUrl);
            return getConfigConstCacheUrl;
        }

        public static String getRemoveConfigConstCacheValue() {
            String moduleName = SystemServerDef.Cache.MODULE_NAME;
            String key = SystemServerDef.Cache.REMOVE_CONFIG_CONST_CACHE_VALUE_SERVER_NAME;
            String cacheKey = ENVIRONMENT + "_" + moduleName + "_" + key;
            String removeConfigConstCacheUrl = ConfigConstCacheManager.getCacheServerUrl(cacheKey);
            if (StringUtil.isEmpty(removeConfigConstCacheUrl)) {
                logger.info("没有缓存数据,从Config Server中获得数据,缓存Key:" + cacheKey);
                removeConfigConstCacheUrl = CacheServerManager.getServerUrl(moduleName, key);
                ConfigConstCacheManager.setCacheServerUrl(cacheKey, removeConfigConstCacheUrl);
            }
            logger.info("Key:" + cacheKey + " value:" + removeConfigConstCacheUrl);
            return removeConfigConstCacheUrl;
        }

        public static String getRemoveAllConfigConstCache() {
            String moduleName = SystemServerDef.Cache.MODULE_NAME;
            String key = SystemServerDef.Cache.REMOVE_ALL_CONFIG_CONST_CACHE_SERVER_NAME;
            String cacheKey = ENVIRONMENT + "_" + moduleName + "_" + key;
            String removeAllConfigConstCacheUrl = ConfigConstCacheManager.getCacheServerUrl(cacheKey);
            if (StringUtil.isEmpty(removeAllConfigConstCacheUrl)) {
                logger.info("没有缓存数据,从Config Server中获得数据,缓存Key:" + cacheKey);
                removeAllConfigConstCacheUrl = CacheServerManager.getServerUrl(moduleName, key);
                ConfigConstCacheManager.setCacheServerUrl(cacheKey, removeAllConfigConstCacheUrl);
            }
            logger.info("Key:" + cacheKey + " value:" + removeAllConfigConstCacheUrl);
            return removeAllConfigConstCacheUrl;
        }

    }

    public static class MqServerManager {
        /**
         *
         *
         * @return
         */
        public static String getSendMqMessage() {
            return getServerUrl(SystemServerDef.Mq.MODULE_NAME, SystemServerDef.Mq.SEND_MQ_MESSAGE);
        }
    }

    public static class MqQueuesServerManager {
        /**
         *
         *
         * @return
         */
        public static String getSendMqMessage() {
            return getMqQueues(SystemServerDef.MqQueues.MODULE_NAME, SystemServerDef.MqQueues.ORDER_CREATE_QUEUE);
        }

        /**
         *
         *
         * @return
         */
        public static String getOrderPaymentQueue() {
            return getMqQueues(SystemServerDef.MqQueues.MODULE_NAME, SystemServerDef.MqQueues.ORDER_PAYMENT_QUEUE);
        }

        /**
         *
         *
         * @return
         */
        public static String getOrderDeliveryQueue() {
            return getMqQueues(SystemServerDef.MqQueues.MODULE_NAME, SystemServerDef.MqQueues.ORDER_DELIVERY_QUEUE);
        }
    }

    public static class PrintServerManager {
        /**
         *
         * 打印pdf
         * 
         * @return打印pdf
         */
        public static String getPrintPdf() {
            String networkAddress = getNetworkAddress();
            if (networkAddress != null) {
                return networkAddress + "/print/pdf/";
            }

            return getServerUrl(SystemServerDef.PrintServer.MODULE_NAME, SystemServerDef.PrintServer.PRINT_PDF);
        }

        public static String getNetworkAddress() {
            ConfigParam param = getConfigParam(SystemServerDef.PrintServer.MODULE_NAME,
                SystemServerDef.PrintServer.NETWORK_ADDRESS);
            RsRequest<ConfigParam> request = new RsRequest<>();
            request.setParam(param);
            TypeReference typeReference = new TypeReference<RsResponse<HashMap<String, String>>>() {};
            RsResponse<HashMap<String, String>> response = RestClientUtil.post(getConfigConstantUrl(), request,
                typeReference);
            HashMap<String, String> result = response.getResult();
            return result.get(SystemServerDef.PrintServer.NETWORK_ADDRESS);
        }

        /**
         * 异步打印pdf接口
         *
         * @return
         */
        public static String getAsyncGenerateSinglePDF() {
            return getServerUrl(SystemServerDef.PrintServer.MODULE_NAME,
                SystemServerDef.PrintServer.ASYNC_GENERATE_SINGLE_PDF);
        }

        /**
         * 异步生成excel接口
         */
        public static String getAsyncGenerateSingleExcel() {
            return getServerUrl(SystemServerDef.PrintServer.MODULE_NAME,
                SystemServerDef.PrintServer.ASYNC_GENERATE_SINGLE_EXCEL_SERVER_NAME);
        }

    }

    public static class mailServerManager {
        /**
         *
         * 打印pdf
         * 
         * @return打印pdf
         */
        public static String getSendTextMail() {
            return getServerUrl(SystemServerDef.mskMail.MODULE_NAME, SystemServerDef.mskMail.SEND_TEXT_MAIL);
        }
    }

    /*
     * SoInventoryServer
     */
    public static class SoInventoryServerManager {
        /**
         * 根据pdTypeCode查询卖家库存
         */
        public static String getGetStockListByPdCode() {
            return getServerUrl(SystemServerDef.SoInventoryServer.MODULE_NAME,
                SystemServerDef.SoInventoryServer.GET_STOCK_LIST_BY_PD_CODE_SERVER_NAME);
        }

        /**
         * 库存产品入库
         */
        public static String getInboundInventory() {
            return getServerUrl(SystemServerDef.SoInventoryServer.MODULE_NAME,
                SystemServerDef.SoInventoryServer.INBOUND_INVENTORY_SERVER_NAME);
        }

        /**
         * 卖家库存订单占用
         */
        public static String getAllocateSlInventory() {
            return getServerUrl(SystemServerDef.SoInventoryServer.MODULE_NAME,
                SystemServerDef.SoInventoryServer.ALLOCATE_SL_INVENTORY_SERVER_NAME);
        }

        /**
         * 供应商库存订单占用
         */
        public static String getAllocateOwnerInventory() {
            return getServerUrl(SystemServerDef.SoInventoryServer.MODULE_NAME,
                SystemServerDef.SoInventoryServer.ALLOCATE_OWNER_INVENTORY_SERVER_NAME);
        }

        /**
         * 卖家库存订单占用减少
         */
        public static String getUndoAllocateSlInventory() {
            return getServerUrl(SystemServerDef.SoInventoryServer.MODULE_NAME,
                SystemServerDef.SoInventoryServer.UNDO_ALLOCATE_SL_INVENTORY_SERVER_NAME);
        }

        /**
         * 供应商库存占用减少
         */
        public static String getUndoAllocateOwnerInventory() {
            return getServerUrl(SystemServerDef.SoInventoryServer.MODULE_NAME,
                SystemServerDef.SoInventoryServer.UNDO_ALLOCATE_OWNER_INVENTORY_SERVER_NAME);
        }

        /**
         * 库存产品出库
         */
        public static String getOutboundInventory() {
            return getServerUrl(SystemServerDef.SoInventoryServer.MODULE_NAME,
                SystemServerDef.SoInventoryServer.OUTBOUND_INVENTORY_SERVER_NAME);
        }

        /**
         * 产品库存查询(原名卖家库存查询)
         */
        public static String getFindProductStock() {
            return getServerUrl(SystemServerDef.SoInventoryServer.MODULE_NAME,
                SystemServerDef.SoInventoryServer.FIND_PRODUCT_STOCK_SERVER_NAME);
        }

        /**
         * 卖家产品库存查询列表
         */
        public static String getFindSlProductIvList() {
            return getServerUrl(SystemServerDef.SoInventoryServer.MODULE_NAME,
                SystemServerDef.SoInventoryServer.FIND_SL_PRODUCT_IV_LIST_SERVER_NAME);
        }

        /**
         * 供应商产品库存查询列表
         */
        public static String getFindSpProductIvList() {
            return getServerUrl(SystemServerDef.SoInventoryServer.MODULE_NAME,
                SystemServerDef.SoInventoryServer.FIND_SP_PRODUCT_IV_LIST_SERVER_NAME);
        }

        /**
         * 卖家商品管理查询接口
         */
        public static String getFindSlProductList() {
            return getServerUrl(SystemServerDef.SoInventoryServer.MODULE_NAME,
                SystemServerDef.SoInventoryServer.FIND_SL_PRODUCT_LIST_SERVER_NAME);
        }

        /**
         * 买手囤货库存更新接口
         */
        public static String getAssignInventoryForSl() {
            return getServerUrl(SystemServerDef.SoInventoryServer.MODULE_NAME,
                SystemServerDef.SoInventoryServer.ASSIGN_INVENTORY_FOR_SL_SERVER_NAME);
        }

        /**
         * 仓库列表接口
         */
        public static String getGetWarehouseList() {
            return getServerUrl(SystemServerDef.SoInventoryServer.MODULE_NAME,
                SystemServerDef.SoInventoryServer.GET_WAREHOUSE_LIST_SERVER_NAME);
        }

        /**
         * 查询所有有过库存的供应商列表
         */
        public static String getOwnersInHistory() {
            return getServerUrl(SystemServerDef.SoInventoryServer.MODULE_NAME,
                SystemServerDef.SoInventoryServer.OWNERS_IN_HISTORY_SERVER_NAME);
        }

        /**
         * 库存产品发货取消
         */
        public static String getUndoDispatch() {
            return getServerUrl(SystemServerDef.SoInventoryServer.MODULE_NAME,
                SystemServerDef.SoInventoryServer.UNDO_DISPATCH_SERVER_NAME);
        }

        /**
         * 分销正常库存管理页面接口
         */
        public static String getGetDistributionList() {
            return getServerUrl(SystemServerDef.SoInventoryServer.MODULE_NAME,
                SystemServerDef.SoInventoryServer.GET_DISTRIBUTION_LIST_SERVER_NAME);
        }

        /**
         * 卖家库存管理页面接口
         */
        public static String getGetSellerInventoryList() {
            return getServerUrl(SystemServerDef.SoInventoryServer.MODULE_NAME,
                SystemServerDef.SoInventoryServer.GET_SELLER_INVENTORY_LIST_SERVER_NAME);
        }

        /**
         * 产品库存查询(多品查询)
         */
        public static String getFindProductsInventory() {
            return getServerUrl(SystemServerDef.SoInventoryServer.MODULE_NAME,
                SystemServerDef.SoInventoryServer.FIND_PRODUCTS_INVENTORY_SERVER_NAME);
        }

    }

    /*
     * SoOrderApi Server Manager
     */
    public static class SoOrderApiServerManager {
        /**
         * 买家在下订单之前创建的订单我们称之为需求订单，需求订单不是真正的订单，它只是买家意愿订单
         */
        public static String getCreateSoPro() {
            return getServerUrl(SystemServerDef.SoOrderApiServer.MODULE_NAME,
                SystemServerDef.SoOrderApiServer.CREATE_SO_PRO_SERVER_NAME);
        }

        /**
         * 出力详细信息报表
         */
        public static String getQuerySoDetailPrintInfo() {
            return getServerUrl(SystemServerDef.SoOrderApiServer.MODULE_NAME,
                SystemServerDef.SoOrderApiServer.QUERY_SO_DETAIL_PRINT_INFO_SERVER_NAME);
        }

        /**
         * 收款方查询接口
         */
        public static String getQueryReceiptInfo() {
            return getServerUrl(SystemServerDef.SoOrderApiServer.MODULE_NAME,
                SystemServerDef.SoOrderApiServer.QUERY_RECEIPT_INFO_SERVER_NAME);
        }

        /**
         * 产品销量查询接口
         */
        public static String getQueryPdSalesVolumnList() {
            return getServerUrl(SystemServerDef.SoOrderApiServer.MODULE_NAME,
                SystemServerDef.SoOrderApiServer.QUERY_PD_SALES_VOLUMN_LIST_SERVER_NAME);
        }

        /**
         * 买家平台下单数量统计
         */
        public static String getQueryOrderSourceCount() {
            return getServerUrl(SystemServerDef.SoOrderApiServer.MODULE_NAME,
                SystemServerDef.SoOrderApiServer.QUERY_ORDER_SOURCE_COUNT_SERVER_NAME);
        }

        /**
         * 客户前台网站申请退货
         */
        public static String getCreateSoReturnInvoice() {
            return getServerUrl(SystemServerDef.SoOrderApiServer.MODULE_NAME,
                SystemServerDef.SoOrderApiServer.CREATE_SO_RETURN_INVOICE_SERVER_NAME);
        }

        /**
         * 根据参数中存在的条件查询退货单并返回结果列表。
         */
        public static String getQuerySoReturnInvoice() {
            return getServerUrl(SystemServerDef.SoOrderApiServer.MODULE_NAME,
                SystemServerDef.SoOrderApiServer.QUERY_SO_RETURN_INVOICE_SERVER_NAME);
        }

        /**
         * 订单状态在没有发货的情况下可以进行整单取消
         */
        public static String getCancelTotalSoOrder() {
            return getServerUrl(SystemServerDef.SoOrderApiServer.MODULE_NAME,
                SystemServerDef.SoOrderApiServer.CANCEL_TOTAL_SO_ORDER_SERVER_NAME);
        }

        /**
         * 删除或恢复指定的订单
         */
        public static String getDeleteSoOrder() {
            return getServerUrl(SystemServerDef.SoOrderApiServer.MODULE_NAME,
                SystemServerDef.SoOrderApiServer.DELETE_SO_ORDER_SERVER_NAME);
        }

        /**
         * 线上付款，付款成功调用
         */
        public static String getPaySoOrder() {
            return getServerUrl(SystemServerDef.SoOrderApiServer.MODULE_NAME,
                SystemServerDef.SoOrderApiServer.PAY_SO_ORDER_SERVER_NAME);
        }

        /**
         * 根据买家选购产品信息、买家信息、配送信息、发票要求，创建标准分销订单或者买手销售订单。
         */
        public static String getCreateDistributeSdo() {
            return getServerUrl(SystemServerDef.SoOrderApiServer.MODULE_NAME,
                SystemServerDef.SoOrderApiServer.CREATE_DISTRIBUTE_SDO_SERVER_NAME);
        }

        /**
         * 根据买手选购产品信息、买家信息、配送信息、发票要求，创建买手标准囤货订单。
         */
        public static String getCreateBuyerSdo() {
            return getServerUrl(SystemServerDef.SoOrderApiServer.MODULE_NAME,
                SystemServerDef.SoOrderApiServer.CREATE_BUYER_SDO_SERVER_NAME);
        }

        /**
         * 根据买手选购产品信息、买家信息、配送信息、发票要求，创建第三方标准订单或者第三方买手销售订单。
         */
        public static String getCreateThirdPartySdo() {
            return getServerUrl(SystemServerDef.SoOrderApiServer.MODULE_NAME,
                SystemServerDef.SoOrderApiServer.CREATE_THIRD_PARTY_SDO_SERVER_NAME);
        }

        /**
         * 根据买手选购产品信息、买家信息、配送信息、发票要求，创建第三方标准买手囤货订单
         */
        public static String getCreateThirdBuyerSdo() {
            return getServerUrl(SystemServerDef.SoOrderApiServer.MODULE_NAME,
                SystemServerDef.SoOrderApiServer.CREATE_THIRD_BUYER_SDO_SERVER_NAME);
        }

        /**
         * WMS系统将实际发货及配送信息返回OMS系统，
         */
        public static String getCeliverSoOrder() {
            return getServerUrl(SystemServerDef.SoOrderApiServer.MODULE_NAME,
                SystemServerDef.SoOrderApiServer.CELIVER_SO_ORDER_SERVER_NAME);
        }

        /**
         * OMS中可接收收货信息
         */
        public static String getReceiptSoOrder() {
            return getServerUrl(SystemServerDef.SoOrderApiServer.MODULE_NAME,
                SystemServerDef.SoOrderApiServer.RECEIPT_SO_ORDER_SERVER_NAME);
        }

        /**
         * 订单列表查询接口
         */
        public static String getQuerySdoList() {
            return getServerUrl(SystemServerDef.SoOrderApiServer.MODULE_NAME,
                SystemServerDef.SoOrderApiServer.QUERY_SDO_LIST_SERVER_NAME);
        }

        /**
         * 查询买家订单
         */
        public static String getQueryBySdoList() {
            return getServerUrl(SystemServerDef.SoOrderApiServer.MODULE_NAME,
                SystemServerDef.SoOrderApiServer.QUERY_BY_SDO_LIST_SERVER_NAME);
        }

        /**
         * 查询卖家订单
         */
        public static String getQuerySlSdoList() {
            return getServerUrl(SystemServerDef.SoOrderApiServer.MODULE_NAME,
                SystemServerDef.SoOrderApiServer.QUERY_SL_SDO_LIST_SERVER_NAME);
        }

        /**
         * 查询买手销售订单
         */
        public static String getQueryBssSdoList() {
            return getServerUrl(SystemServerDef.SoOrderApiServer.MODULE_NAME,
                SystemServerDef.SoOrderApiServer.QUERY_BSS_SDO_LIST_SERVER_NAME);
        }

        /**
         * 查询囤货订单
         */
        public static String getQueryBssgSdoList() {
            return getServerUrl(SystemServerDef.SoOrderApiServer.MODULE_NAME,
                SystemServerDef.SoOrderApiServer.QUERY_BSSG_SDO_LIST_SERVER_NAME);
        }

        /**
         * 订单明细查询
         */
        public static String getQuerySdoDetail() {
            return getServerUrl(SystemServerDef.SoOrderApiServer.MODULE_NAME,
                SystemServerDef.SoOrderApiServer.QUERY_SDO_DETAIL_SERVER_NAME);
        }

        /**
         * 买家订单明细
         */
        public static String getQueryBySdoDetail() {
            return getServerUrl(SystemServerDef.SoOrderApiServer.MODULE_NAME,
                SystemServerDef.SoOrderApiServer.QUERY_BY_SDO_DETAIL_SERVER_NAME);
        }

        /**
         * 卖家订单明细
         */
        public static String getQuerySlSdoDetail() {
            return getServerUrl(SystemServerDef.SoOrderApiServer.MODULE_NAME,
                SystemServerDef.SoOrderApiServer.QUERY_SL_SDO_DETAIL_SERVER_NAME);
        }

        /**
         * 买手销售订单明细
         */
        public static String getQueryBssSdoDetail() {
            return getServerUrl(SystemServerDef.SoOrderApiServer.MODULE_NAME,
                SystemServerDef.SoOrderApiServer.QUERY_BSS_SDO_DETAIL_SERVER_NAME);
        }

        /**
         * 买手囤货订单明细
         */
        public static String getQueryBssgSdoDetail() {
            return getServerUrl(SystemServerDef.SoOrderApiServer.MODULE_NAME,
                SystemServerDef.SoOrderApiServer.QUERY_BSSG_SDO_DETAIL_SERVER_NAME);
        }

        /**
         * 卖家已卖出商品查询
         */
        public static String getQuerySoSlProductList() {
            return getServerUrl(SystemServerDef.SoOrderApiServer.MODULE_NAME,
                SystemServerDef.SoOrderApiServer.QUERY_SO_SL_PRODUCT_LIST_SERVER_NAME);
        }

        /**
         * 接收WMS系统处理完退货后，返回的入库信息接口，处理完成后，系统内库存增加
         */
        public static String getQoOrderInbound() {
            return getServerUrl(SystemServerDef.SoOrderApiServer.MODULE_NAME,
                SystemServerDef.SoOrderApiServer.QO_ORDER_INBOUND_SERVER_NAME);
        }

        /**
         * 卖家快捷信息查询
         */
        public static String getQuerySellerInfo() {
            return getServerUrl(SystemServerDef.SoOrderApiServer.MODULE_NAME,
                SystemServerDef.SoOrderApiServer.QUERY_SELLER_INFO_SERVER_NAME);
        }

        /**
         * 买手快捷信息查询
         */
        public static String getQueryBuyerInfo() {
            return getServerUrl(SystemServerDef.SoOrderApiServer.MODULE_NAME,
                SystemServerDef.SoOrderApiServer.QUERY_BUYER_INFO_SERVER_NAME);
        }

        /**
         * 管家快捷信息查询
         */
        public static String getQueryHouseKeepingInfo() {
            return getServerUrl(SystemServerDef.SoOrderApiServer.MODULE_NAME,
                SystemServerDef.SoOrderApiServer.QUERY_HOUSE_KEEPING_INFO_SERVER_NAME);
        }

        /**
         * 管家订单查询接口
         */
        public static String getQueryHouseKeepingDetail() {
            return getServerUrl(SystemServerDef.SoOrderApiServer.MODULE_NAME,
                SystemServerDef.SoOrderApiServer.QUERY_HOUSE_KEEPING_DETAIL_SERVER_NAME);
        }

        /**
         * 提供结算详情
         */
        public static String getQuerySdoSettlementDetail() {
            return getServerUrl(SystemServerDef.SoOrderApiServer.MODULE_NAME,
                SystemServerDef.SoOrderApiServer.QUERY_SDO_SETTLEMENT_DETAIL_SERVER_NAME);
        }

        /**
         * 提供购买查询
         */
        public static String getQuerySdoBuyRecord() {
            return getServerUrl(SystemServerDef.SoOrderApiServer.MODULE_NAME,
                SystemServerDef.SoOrderApiServer.QUERY_SDO_BUY_RECORD_SERVER_NAME);
        }

        /**
         * WMS中将发货单取消，将取消信息通知OMS系统
         */
        public static String getCancelSdoShipment() {
            return getServerUrl(SystemServerDef.SoOrderApiServer.MODULE_NAME,
                SystemServerDef.SoOrderApiServer.CANCEL_SDO_SHIPMENT_SERVER_NAME);
        }

        /**
         * 获取本月上半月分销量（价盘调用）
         */
        public static String getQueryOrderHalfMonthCount() {
            return getServerUrl(SystemServerDef.SoOrderApiServer.MODULE_NAME,
                SystemServerDef.SoOrderApiServer.QUERY_ORDER_HALF_MONTH_COUNT_SERVER_NAME);
        }

        /**
         * 获取订单相关信息（资金池调用）
         */
        public static String getGetSoOrderInfo() {
            return getServerUrl(SystemServerDef.SoOrderApiServer.MODULE_NAME,
                SystemServerDef.SoOrderApiServer.GET_SO_ORDER_INFO_SERVER_NAME);
        }

        /**
         * 接收司机PDA系统的迟收退货信息，OMS处理后可再通知WMS发货
         */
        public static String getReceiveSoOrderLater() {
            return getServerUrl(SystemServerDef.SoOrderApiServer.MODULE_NAME,
                SystemServerDef.SoOrderApiServer.RECEIVE_SO_ORDER_LATER_SERVER_NAME);
        }

        /**
         * 接收司机PDA系统的现场拒收退货信息
         */
        public static String getReceiveSoOrderReject() {
            return getServerUrl(SystemServerDef.SoOrderApiServer.MODULE_NAME,
                SystemServerDef.SoOrderApiServer.RECEIVE_SO_ORDER_REJECT_SERVER_NAME);
        }

        /**
         * 提供订单发货明细信息查询
         */
        public static String getQuerySdoShipDetail() {
            return getServerUrl(SystemServerDef.SoOrderApiServer.MODULE_NAME,
                SystemServerDef.SoOrderApiServer.QUERY_SDO_SHIP_DETAIL_SERVER_NAME);
        }

        /**
         * 退货原因查询接口,分Json版和Xml版
         */
        public static String getQueryReturnReason() {
            return getServerUrl(SystemServerDef.SoOrderApiServer.MODULE_NAME,
                SystemServerDef.SoOrderApiServer.QUERY_RETURN_REASON_SERVER_NAME);
        }

        /**
         * 订单列表查询接口
         */
        public static String getFindPageOrderList() {
            return getServerUrl(SystemServerDef.SoOrderApiServer.MODULE_NAME,
                SystemServerDef.SoOrderApiServer.FIND_PAGE_ORDER_LIST_SERVER_NAME);
        }

        /**
         * 订单查询详情接口
         */
        public static String getFindPageOrderDetail() {
            return getServerUrl(SystemServerDef.SoOrderApiServer.MODULE_NAME,
                SystemServerDef.SoOrderApiServer.FIND_PAGE_ORDER_DETAIL_SERVER_NAME);
        }

        /**
         * 退货单列表查询接口
         */
        public static String getFindPageReturnOrderList() {
            return getServerUrl(SystemServerDef.SoOrderApiServer.MODULE_NAME,
                SystemServerDef.SoOrderApiServer.FIND_PAGE_RETURN_ORDER_LIST_SERVER_NAME);
        }

        /**
         * 退货单明细列表查询接口
         */
        public static String getFindPageReturnOrderDetail() {
            return getServerUrl(SystemServerDef.SoOrderApiServer.MODULE_NAME,
                SystemServerDef.SoOrderApiServer.FIND_PAGE_RETURN_ORDER_DETAIL_SERVER_NAME);
        }

        /**
         * 退货单基本信息查询接口
         */
        public static String getFindPageBaseReturn() {
            return getServerUrl(SystemServerDef.SoOrderApiServer.MODULE_NAME,
                SystemServerDef.SoOrderApiServer.FIND_PAGE_BASE_RETURN_SERVER_NAME);
        }

        /**
         * 查询订单明细列表及买家基本信息接口
         */
        public static String getFindByBasicAndOrderDetail() {
            return getServerUrl(SystemServerDef.SoOrderApiServer.MODULE_NAME,
                SystemServerDef.SoOrderApiServer.FIND_BY_BASIC_AND_ORDER_DETAIL_SERVER_NAME);
        }

        /**
         * 查询物流区信息
         */
        public static String getQuerySellerProductInfo() {
            return getServerUrl(SystemServerDef.SoOrderApiServer.MODULE_NAME,
                SystemServerDef.SoOrderApiServer.QUERY_SELLER_PRODUCT_INFO_SERVER_NAME);
        }

        /**
         * 卖家库存接口
         */
        public static String getQuerySupplierProductInfo() {
            return getServerUrl(SystemServerDef.SoOrderApiServer.MODULE_NAME,
                SystemServerDef.SoOrderApiServer.QUERY_SUPPLIER_PRODUCT_INFO_SERVER_NAME);
        }

        /**
         * 供应商库存接口
         */
        public static String getQueryDeliverInfo() {
            return getServerUrl(SystemServerDef.SoOrderApiServer.MODULE_NAME,
                SystemServerDef.SoOrderApiServer.QUERY_DELIVER_INFO_SERVER_NAME);
        }

        /**
         * 发货单查询接口
         */
        public static String getCancelOrderDeliver() {
            return getServerUrl(SystemServerDef.SoOrderApiServer.MODULE_NAME,
                SystemServerDef.SoOrderApiServer.CANCEL_ORDER_DELIVER_SERVER_NAME);
        }

        /**
         * 订单明细导出excel接口
         */
        public static String getExportOrderDetail() {
            return getServerUrl(SystemServerDef.SoOrderApiServer.MODULE_NAME,
                SystemServerDef.SoOrderApiServer.EXPORT_ORDER_DETAIL_SERVER_NAME);
        }

    }
}
