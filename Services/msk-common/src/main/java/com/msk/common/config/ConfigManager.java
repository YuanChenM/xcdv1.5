package com.msk.common.config;

import com.hoperun.core.consts.StringConst;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.redis.BaseRedisDao;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.consts.RedisDataBaseDef;
import com.msk.core.entity.CommConfig;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ConfigManager
 *
 * @author jiang_nan
 * @version 1.0
 **/
@Component("configManager")
public class ConfigManager {
    private static ConfigInfo config;
    private static Map<String, String> CONFIG_VALUES_MAP = new HashMap<>();
    /** 表示开启的开关标志值 */
    private static final String[] TRUE_FLAG = { "1", "on", "true" };
    private static BaseRedisDao redisConfigDao;
    @Autowired
    public void setConfigInfo(final ConfigInfo config) {
        ConfigManager.config = config;
    }
    @Qualifier("redisDao")
    @Autowired
    public void setRedisConfigDao(final BaseRedisDao redisConfigDao){
        ConfigManager.redisConfigDao = redisConfigDao;
    }

    /**
     * 初始化Config Map中数据
     *
     * @param configList
     */
    public static void initConfigValue(List<CommConfig> configList) {
        if (!CollectionUtils.isEmpty(configList)) {
            for (CommConfig config : configList) {
                CONFIG_VALUES_MAP.put(config.getSystemKey(), config.getSystemValue());
            }
        }
    }

    public static String getStandardCoefficientQuotiety() {
        return config.getStandardCoefficientQuotiety();
    }

    public static String getLevelStandMinZero() {
        return config.getLevelStandMinZero();
    }

    public static String getLevelStandMinOne() {
        return config.getLevelStandMinOne();
    }

    public static String getLevelStandMinTwo() {
        return config.getLevelStandMinTwo();
    }

    public static String getLevelStandMinThree() {
        return config.getLevelStandMinThree();
    }

    public static String getLevelStandMinFour() {
        return config.getLevelStandMinFour();
    }

    public static String getLevelStandMinFive() {
        return config.getLevelStandMinFive();
    }

    public static String getLevelStandMinSix() {
        return config.getLevelStandMinSix();
    }

    public static String getLevelStandMinSeven() {
        return config.getLevelStandMinSeven();
    }

    public static String getLevelStandMinEight() {
        return config.getLevelStandMinEight();
    }

    public static String getLevelStandMinNine() {
        return config.getLevelStandMinNine();
    }

    public static String getLevelStandMaxZero() {
        return config.getLevelStandMaxZero();
    }

    public static String getLevelStandMaxOne() {
        return config.getLevelStandMaxOne();
    }

    public static String getLevelStandMaxTwo() {
        return config.getLevelStandMaxTwo();
    }

    public static String getLevelStandMaxThree() {
        return config.getLevelStandMaxThree();
    }

    public static String getLevelStandMaxFour() {
        return config.getLevelStandMaxFour();
    }

    public static String getLevelStandMaxFive() {
        return config.getLevelStandMaxFive();
    }

    public static String getLevelStandMaxSix() {
        return config.getLevelStandMaxSix();
    }

    public static String getLevelStandMaxSeven() {
        return config.getLevelStandMaxSeven();
    }

    public static String getLevelStandMaxEight() {
        return config.getLevelStandMaxEight();
    }

    public static String getLevelStandMaxNine() {
        return config.getLevelStandMaxNine();
    }

    public static String getStandardCoefficientZero() {
        return config.getStandardCoefficientZero();
    }

    public static String getStandardCoefficientOne() {
        return config.getStandardCoefficientOne();
    }

    public static String getStandardCoefficientTwo() {
        return config.getStandardCoefficientTwo();
    }

    public static String getStandardCoefficientThree() {
        return config.getStandardCoefficientThree();
    }

    public static String getStandardCoefficientFour() {
        return config.getStandardCoefficientFour();
    }

    public static String getStandardCoefficientFive() {
        return config.getStandardCoefficientFive();
    }

    public static String getStandardCoefficientSix() {
        return config.getStandardCoefficientSix();
    }

    public static String getStandardCoefficientSeven() {
        return config.getStandardCoefficientSeven();
    }

    public static String getStandardCoefficientEight() {
        return config.getStandardCoefficientEight();
    }

    public static String getStandardCoefficientNine() {
        return config.getStandardCoefficientNine();
    }

    public static String getDisCountZero() {
        return config.getDisCountZero();
    }

    public static String getDisCountOne() {
        return config.getDisCountOne();
    }

    public static String getDisCountTwo() {
        return config.getDisCountTwo();
    }

    public static String getDisCountThree() {
        return config.getDisCountThree();
    }

    public static String getDisCountFour() {
        return config.getDisCountFour();
    }

    public static String getDisCountFive() {
        return config.getDisCountFive();
    }

    public static String getDisCountSix() {
        return config.getDisCountSix();
    }

    public static String getDisCountSeven() {
        return config.getDisCountSeven();
    }

    public static String getDisCountEight() {
        return config.getDisCountEight();
    }

    public static String getDisCountNine() {
        return config.getDisCountNine();
    }

    /**
     * 获得FTP IP地址
     * 
     * @return FTP IP地址
     */
    @Deprecated
    public static String getFtpIp() {
        /*redisConfigDao.setDatabase(RedisDataBaseDef.CONFIG_DB);
        return redisConfigDao.get(ConfigConstantDef.FTP_IP);*/
        return SystemServerManager.CommonServerManager.getFtpIp();
    }

    /**
     * 获得FTP用户名
     * 
     * @return FTP用户名
     */
    @Deprecated
    public static String getFtpUser() {
        /*redisConfigDao.setDatabase(RedisDataBaseDef.CONFIG_DB);
        return redisConfigDao.get(ConfigConstantDef.FTP_USER);*/
        return SystemServerManager.CommonServerManager.getFtpUser();
    }

    /**
     * 获得FTP密码
     * 
     * @return FTP密码
     */
    @Deprecated
    public static String getFtpPwd() {
        /*redisConfigDao.setDatabase(RedisDataBaseDef.CONFIG_DB);
        return redisConfigDao.get(ConfigConstantDef.FTP_PWD);*/
        return SystemServerManager.CommonServerManager.getFtpPwd();
    }

    /**
     * 获得FTP图片根目录
     * 
     * @return FTP图片根目录
     */
    @Deprecated
    public static String getFtpImageRootPath() {
        /*redisConfigDao.setDatabase(RedisDataBaseDef.CONFIG_DB);
        return redisConfigDao.get(ConfigConstantDef.FTP_IMAGE_ROOT_PATH);*/
        return SystemServerManager.CommonServerManager.getFtpImageRootPath();
    }

    /**
     * 获得WMS FTP IP
     * 
     * @return WMS FTP IP
     */
    @Deprecated
    public static String getWmsFtpIp() {
        /*redisConfigDao.setDatabase(RedisDataBaseDef.CONFIG_DB);
        return redisConfigDao.get(ConfigConstantDef.WMS_FTP_IP);*/
        return SystemServerManager.CommonServerManager.getWmsFtpIp();
    }

    /**
     * 获得WMS FTP User
     * 
     * @return WMS FTP User
     */
    @Deprecated
    public static String getWmsFtpUser() {
        /*redisConfigDao.setDatabase(RedisDataBaseDef.CONFIG_DB);
        return redisConfigDao.get(ConfigConstantDef.WMS_FTP_USER);*/
        return SystemServerManager.CommonServerManager.getWmsFtpUser();
    }

    /**
     * 获得WMS FTP Pwd
     * 
     * @return WMS FTP Pwd
     */
    @Deprecated
    public static String getWmsFtpPwd() {
        /*redisConfigDao.setDatabase(RedisDataBaseDef.CONFIG_DB);
        return redisConfigDao.get(ConfigConstantDef.WMS_FTP_PWD);*/
        return SystemServerManager.CommonServerManager.getWmsFtpPwd();
    }

    /**
     * 获得供应商的部门ID固定值
     * 
     * @return 供应商的部门ID固定值
     */
    public static String getSupplierDeptId() {
        redisConfigDao.setDatabase(RedisDataBaseDef.CONFIG_DB);
        return config.getSupplierDeptId();
    }

    /**
     * 获得供应商的角色ID固定值
     * 
     * @return 供应商的角色ID固定值
     */
    public static String getSupplierRoleId() {
        redisConfigDao.setDatabase(RedisDataBaseDef.CONFIG_DB);
        return config.getSupplierRoleId();
    }


    /**
     * 获得价盘同步URL
     * 
     * @return WMS FTP IP
     */
    public static String getBatchPlateUrl() {
        redisConfigDao.setDatabase(RedisDataBaseDef.CONFIG_DB);
        return redisConfigDao.get(ConfigConstantDef.BATCH_PLATE_URL);
    }

    /**
     * 获得分单URL
     * 
     * @return WMS FTP IP
     */
    public static String getDistributeUrl() {
        redisConfigDao.setDatabase(RedisDataBaseDef.CONFIG_DB);
        return redisConfigDao.get(ConfigConstantDef.BATCH_DISTRIBUTE_URL);
    }

    /**
     * SMS發送的URL
     * 
     * @return SMS發送的URL
     */
/*
    public static String getSmsSendUrl() {
        redisConfigDao.setDatabase(RedisDataBaseDef.CONFIG_DB);
        return redisConfigDao.get(ConfigConstantDef.SMS_SEND_URL);
    }
*/

    /**
     * 文件服务器上传URL
     *
     * @return 文件服务器上传URL
     */
    @Deprecated
    public static String getFileServerUpload() {
        /*redisConfigDao.setDatabase(RedisDataBaseDef.CONFIG_DB);
        return redisConfigDao.get(ConfigConstantDef.FILE_SERVER_UPLOAD);*/
        return SystemServerManager.CommonServerManager.getFlieServerUpload();
    }

    /**
     * 文件服务器下载URL
     *
     * @return 文件服务器下载URL
     */
    @Deprecated
    public static String getFileServer() {
        /*redisConfigDao.setDatabase(RedisDataBaseDef.CONFIG_DB);
        return redisConfigDao.get(ConfigConstantDef.FILE_SERVER);*/
        return SystemServerManager.CommonServerManager.getFlieServer();
    }
    /**
     * 获得系统版本
     *
     * @return 系统版本
     */
    public static String getSystemVersion() {

        // 系统版本：工程版本号.SVN版本号
        String projectVersion = config.getProjectVersion();
        String svnVersion = config.getSvnVersion();
        String svnSubVersion = config.getSvnSubVersion();
        if ("-1".equals(svnVersion) || "${svn.revision}".equals(svnVersion)) {
            svnVersion = "0";
        }
        String buildDate = config.getBuildTime();
        if ("${maven.timestamp}".equals(buildDate)) {
            buildDate = "0";
        }
        // 指定的系统版本标识
        String versionMark = config.getVersionMark();
        if (StringUtil.isEmpty(versionMark) || "${version.mark}".equals(versionMark)) {
            versionMark = "GM";
        }

        return StringUtil.formatMessage("{0}.{1}_{2}_{3} ({4})", projectVersion, buildDate, versionMark, svnVersion, svnSubVersion);
    }

    /**
     * 获取工程版本
     * @return
     */
    public static String getProjectVersion(){
        return config.getProjectVersion();
    }

    /**
     * 获取SVN版本号
     * @return
     */
    public static String getSvnVersion(){
        return config.getSvnVersion();
    }

    /**
     * 获取SVN小版本号
     * @return
     */
    public static String getSvnSubVersion(){
        return config.getSvnSubVersion();
    }

    /**
     * 获取SVN版本发布时间
     * @return
     */
    public static String getBuildTime(){
        return config.getBuildTime();
    }
    /**
     * 是否调试模式
     *
     * @return true：调试模式
     */
    public static boolean isDebug() {
        boolean isDebug = false;

        String debugFlag = config.getDebugFlag();
        for (String flag : TRUE_FLAG) {
            if (flag.equalsIgnoreCase(debugFlag)) {
                isDebug = true;
                break;
            }
        }

        return isDebug;
    }

    /**
     * 是否需要权限校验
     *
     * @return true：需要权限校验
     */
    public static boolean needAuthCheck() {
        boolean needAuthCheck = false;

        String authFlag = config.getAuthFlag();
        for (String flag : TRUE_FLAG) {
            if (flag.equalsIgnoreCase(authFlag)) {
                needAuthCheck = true;
                break;
            }
        }
        return needAuthCheck;
    }

    /**
     * 获得系统环境区分
     *
     * @return 系统环境区分
     */
    public static String getSystemEnvTitle() {
        // 系统环境区分
        String env = CONFIG_VALUES_MAP.get(ConfigConstantDef.SYS_ENV);
        String envTitle = "";
        if (!StringUtil.isEmpty(env) && !"prod".equalsIgnoreCase(env)) {
            envTitle = StringUtil.formatMessage("[{0}]", env.toUpperCase());
        }
        return envTitle;
    }

    /**
     * 获取 redisget url
     * @return url
     */
    public static String getRedisGet(){return config.getRedisGet();}
    
    /**
     * 获取 redisset url
     * @return url
     */
    public static String getRedisSet(){return config.getRedisSet();}
    
    /**
     * 获取 redisGetValues url
     * @return url
     */
    public static String getRedisGetValues(){return config.getRedisGetValues();}
    
    /**
     * 获取 redis del url
     * @return url
     */
    public static String getRedisDelValue(){return config.getRedisDelValue();}
    
    /**
     * 获取 redis timerdel url
     * @return url
     */
    public static String getRedisTimerDel(){return config.getRedisTimerDel();}

    /**
     * 总连接数
     * @return 总连接数
     */
    public static Integer getMaxTotal() {
        return StringUtil.toInteger(config.getMaxTotal());
    }
    /**
     * 同路由的并发数
     * @return 同路由的并发数
     */
    public static Integer getMaxPerRoute() {
        return StringUtil.toInteger(config.getMaxPerRoute());
    }
    /**
     * 长连接保持30秒
     * @return 长连接保持30秒
     */
    public static Integer getTimeToLive() {
        return StringUtil.toInteger(config.getTimeToLive());
    }
    /**
     * 获得Redis Base Url
     * @return Redis Base Url
     */
    public static String getRedisBaseUrl() {
        return config.getRedisBaseUrl();
    }

    /**
     * 获得mq队列key
     * @return mq key
     */
    public static String getQueueKey() {
        return config.getQueueKey();
    }

    /**
     * 获得mq指定id
     * @return mq id
     */
    public static String getMqId() {
        return config.getMqId();
    }

    /**
     * 根据系统Code获得系统名称
     * @param sysCode 系统Code
     * @return 系统名称
     */
    public static String getSysNameByCode(String sysCode){
        redisConfigDao.setDatabase(RedisDataBaseDef.SYSTEM_PAGE_DB);
        return redisConfigDao.get(sysCode);
    }
    /**
     * MSK ORG Services
     * @return MSK ORG Services
     */
    public static String getMskOrgServices() {
       /* redisConfigDao.setDatabase(RedisDataBaseDef.CONFIG_DB);
        return redisConfigDao.get(ConfigConstantDef.MSK_ORG_SERVICES);*/
        return StringConst.EMPTY;
    }
    /**
     * MSK ORG Login Services
     * @return MSK ORG Services
     */
    @Deprecated
    public static String getOrgLoginServices() {
        /*redisConfigDao.setDatabase(RedisDataBaseDef.CONFIG_DB);
        return redisConfigDao.get(ConfigConstantDef.ORG_LOGIN_SERVICES);*/
        return SystemServerManager.OrgServerManager.getLogin();
    }

//    /**
//     * 供应商登录接口
//     * @return MSK ORG Services
//     */
//    @Deprecated
//    public static String getOrgLoginSuppServices() {
//        /*redisConfigDao.setDatabase(RedisDataBaseDef.CONFIG_DB);
//        return redisConfigDao.get(ConfigConstantDef.ORG_LOGIN_SUPP_SERVICES);*/
//        return SystemServerManager.OrgServerManager.getLoginForSupp();
//    }

    /**
     * MSK ORG supplier Login Services
     * @return MSK ORG Services
     */
    public static String getOrgLoginSuppDao() {
       /*redisConfigDao.setDatabase(RedisDataBaseDef.CONFIG_DB);
       return redisConfigDao.get(ConfigConstantDef.ORG_LOGIN_SUPP_DAO);*/
//        return SystemServerManager.OrgServerManager.getLoginForSupp();
        return SystemServerManager.SellerServerManage.getQueryAccount();
    }

    /**
     * Page List Services
     * @return Page List Services
     */
    @Deprecated
    public static String getOrgPageServices() {
        /*redisConfigDao.setDatabase(RedisDataBaseDef.CONFIG_DB);
        return redisConfigDao.get(ConfigConstantDef.ORG_PAGE_SERVICES);*/
        return SystemServerManager.OrgServerManager.getSearchPageList();
    }

//    /**
//     * Org Menu Services
//     * @return Org Menu Service
//     */
//    @Deprecated
//    public static String getOrgMenuServices(){
//        /*redisConfigDao.setDatabase(RedisDataBaseDef.CONFIG_DB);
//        return redisConfigDao.get(ConfigConstantDef.ORG_MENU_SERVICES);*/
//        return SystemServerManager.OrgServerManager.getSearch();
//    }

    /**
     * 卖家可用库存列表
     * @return Org Menu Service
     */
    @Deprecated
    public static String getStockBysellerListService(){
        /*redisConfigDao.setDatabase(RedisDataBaseDef.CONFIG_DB);
        return redisConfigDao.get(ConfigConstantDef.STOCK_BYSELLER_LIST_SERVICES);*/
        return SystemServerManager.SoStockServerManage.getGetUsedStock();
    }

    /**
     * 根据pdTypCode查询卖家库存
     * @return Org Menu Service
     */
    @Deprecated
    public static String getStockByPdTypeCodeListService(){
        /*redisConfigDao.setDatabase(RedisDataBaseDef.CONFIG_DB);
        return redisConfigDao.get(ConfigConstantDef.STOCK_BYPDTYPECODE_LIST_SERVICES);*/
        return SystemServerManager.SoStockServerManage.getGetUsedStock();
    }

    /**
     * snk-price Slpdcode Search Services
     * @return Org Menu Service
     */
    @Deprecated
    public static String getSnkPriceSlPdCodeSearchService(){
        /*redisConfigDao.setDatabase(RedisDataBaseDef.CONFIG_DB);
        return redisConfigDao.get(ConfigConstantDef.SNK_PRICE_SLPDCODE_SEARCH_SERVICES);*/
        return SystemServerManager.SellerServerManage.getSlPdCodeSearch();
    }

    /**
     * 卖家可用库存列表
     * @return Org Menu Service
     */
    @Deprecated
    public static String getSnkPriceQueryStockBySellerListService(){
        /*redisConfigDao.setDatabase(RedisDataBaseDef.CONFIG_DB);
        return redisConfigDao.get(ConfigConstantDef.SNK_PRICE_QUERYSTOCKBYSELLER_LIST_SERVICES);*/
        return SystemServerManager.SoStockServerManage.getGetUsedStock();
    }

    /**
     * 获取下一个价盘周期编码
     * @return Org Menu Service
     */
    @Deprecated
    public static String getSnkPricePriceCycleService(){
        /*redisConfigDao.setDatabase(RedisDataBaseDef.CONFIG_DB);
        return redisConfigDao.get(ConfigConstantDef.SNK_PRICE_PRICECYCLE_SERVICES);*/
        return SystemServerManager.SnkPriceServerManage.getPriceCycle();
    }

    /**
     * 查询供应商产品信息
     * @return Org Menu Service
     */
    @Deprecated
    public static String getProductGetPdSuppService(){
        /*redisConfigDao.setDatabase(RedisDataBaseDef.CONFIG_DB);
        return redisConfigDao.get(ConfigConstantDef.PRODUCT_GETPDSUPP_SERVICES);*/
        return SystemServerManager.PdServerManager.getGetPDSupp();
    }

    /**
     * msk Stock Services
     * @return Org Menu Service
     */
    public static String getMskStockService(){
        redisConfigDao.setDatabase(RedisDataBaseDef.CONFIG_DB);
        return StringConst.EMPTY;
    }

    /**
     * Msk Snk Price Services
     * @return Org Menu Service
     */
    public static String getMskSnkPriceService(){
        redisConfigDao.setDatabase(RedisDataBaseDef.CONFIG_DB);
        return StringConst.EMPTY;
    }

    /**
     * Msk Seller Services
     * @return Org Menu Service
     */
    public static String getMskSellerService(){
        redisConfigDao.setDatabase(RedisDataBaseDef.CONFIG_DB);
        return StringConst.EMPTY;
    }

    /**
     * Msk Product Services
     * @return Org Menu Service
     */
    public static String getMskProductService(){
        redisConfigDao.setDatabase(RedisDataBaseDef.CONFIG_DB);
        return StringConst.EMPTY;
    }

    /**
     * Msk Order Services
     * @return Org Menu Service
     */
    public static String getMskOrderService(){
        redisConfigDao.setDatabase(RedisDataBaseDef.CONFIG_DB);
        return StringConst.EMPTY;
    }

    /**
     * Msk District Services
     * @return Org Menu Service
     */
    public static String getMskDistrictService(){
        redisConfigDao.setDatabase(RedisDataBaseDef.CONFIG_DB);
        return StringConst.EMPTY;
    }

    /**
     * Msk Cash Pooling Services
     * @return Org Menu Service
     */
    public static String getMskCashPoolingService(){
        redisConfigDao.setDatabase(RedisDataBaseDef.CONFIG_DB);
        return StringConst.EMPTY;
    }

    /**
     * Msk Buyers Services
     * @return Org Menu Service
     */
    public static String getMskBuyersService(){
        redisConfigDao.setDatabase(RedisDataBaseDef.CONFIG_DB);
        return StringConst.EMPTY;
    }

    /**
     * 查询物流区信息
     * @return Org Menu Service
     */
    @Deprecated
    public static String getDistrictQueryLgcsAreaService(){
        /*redisConfigDao.setDatabase(RedisDataBaseDef.CONFIG_DB);
        return redisConfigDao.get(ConfigConstantDef.DISTRICT_QUERY_LGCSAREA_SERVICES);*/
        return SystemServerManager.DistrictServerManage.getDistrictQueryLgcsArea();
    }

    /**
     * Seller Ep Data Search Services
     * @return Org Menu Service
     */
    @Deprecated
    public static String getSellerEpDataSearchServices(){
        /*redisConfigDao.setDatabase(RedisDataBaseDef.CONFIG_DB);
        return redisConfigDao.get(ConfigConstantDef.SELLER_EPDATA_SEARCH_SERVICES);*/
        return SystemServerManager.SellerServerManage.getEpDataSearch();
    }

    /**
     * 查询某区域中所有的买家
     * @return Msk Buyers Services
     */
    @Deprecated
    public static String getBuyersAllInfoByDisirictCodeServices(){
        /*redisConfigDao.setDatabase(RedisDataBaseDef.CONFIG_DB);
        return redisConfigDao.get(ConfigConstantDef.BUYERS_DISIRICTCODE_ALLBUYERS_SERVICES);*/
        return  SystemServerManager.BuyersServerManage.getFindBuyerList();
    }

    /**
     * 查询买家所有相关的信息
     * @return Msk Buyers Services
     */
    @Deprecated
    public static String getBuyersInfoRelationInfoServices(){
        /*redisconfigdao.setdatabase(redisdatabasedef.config_db);
        return redisconfigdao.get(configconstantdef.buyers_buyerinfo_relationinfo_services);*/
        return SystemServerManager.BuyersServerManage.getSearchBuyerInfo();

    }

    /**
     * 查询公共买家池买家信息
     * @return Msk Buyers Services
     */
    @Deprecated
    public static String getBuyersSearchBuyerServices(){
        /*redisConfigDao.setDatabase(RedisDataBaseDef.CONFIG_DB);
        return redisConfigDao.get(ConfigConstantDef.BUYERS_SEARCH_BUYER_SERVICES);*/
        return SystemServerManager.BuyersServerManage.getSearchBuyer();
    }

    /**
     * 查询买手店管家会员信息
     * @return Msk Buyers Services
     */
    @Deprecated
    public static String getBuyersSearchBuyerShopServices(){
        /*redisConfigDao.setDatabase(RedisDataBaseDef.CONFIG_DB);
        return redisConfigDao.get(ConfigConstantDef.BUYERS_SEARCH_BUYER_SHOP_SERVICES);*/
        return SystemServerManager.BuyersServerManage.getSearchBuyerShop();
    }

    /**
     * 查询买手冻品管家的买家信息
     * @return Msk Buyers Services
     */
    @Deprecated
    public static String getBuyersSearchBuyerExclusiveServices(){
        /*redisConfigDao.setDatabase(RedisDataBaseDef.CONFIG_DB);
        return redisConfigDao.get(ConfigConstantDef.BUYERS_SEARCH_BUYER_EXCLUSIVE_SERVICES);*/
        return SystemServerManager.BuyersServerManage.getQuerySearchExclusive();

    }

    /**
     * 供应商可用库存
     * @return Msk Stock Services
     */
    @Deprecated
    public static String getStockBySupplierListServices(){
        /*redisConfigDao.setDatabase(RedisDataBaseDef.CONFIG_DB);
        return redisConfigDao.get(ConfigConstantDef.STOCK_BYSUPPLIER_LIST_SERVICES);*/
        return SystemServerManager.SoStockServerManage.getGetUsedStock();
    }

    /**
     * 批量查询产品信息
     * @return Msk Product Services
     */
    @Deprecated
    public static String getProductBatchNameServices(){
        /*redisConfigDao.setDatabase(RedisDataBaseDef.CONFIG_DB);
        return redisConfigDao.get(ConfigConstantDef.PRODUCT_BATCHNAME_SERVICES);*/
        return SystemServerManager.PdServerManager.getPdBatchName();
    }

    /**
     * 查询档案卡信息
     * @return Msk Product Services
     */
    @Deprecated
    public static String getProductStdServices(){
        /*redisConfigDao.setDatabase(RedisDataBaseDef.CONFIG_DB);
        return redisConfigDao.get(ConfigConstantDef.PRODUCT_STD_SERVICES);*/
        return SystemServerManager.PdServerManager.getPdProductStd();
    }

    /**
     * 获取价盘区域接口
     * @return Msk Product Services
     */
    @Deprecated
    public static String getProductPricePrdLogiareaServices(){
        /*redisConfigDao.setDatabase(RedisDataBaseDef.CONFIG_DB);
        return redisConfigDao.get(ConfigConstantDef.PRODUCT_PRICE_PRD_LOGIAREA_SERVICES);*/
        return SystemServerManager.PdServerManager.getGetPriceprdLogiarea();
    }

    /**
     * 根据条件插入技术标准信息
     * @return Msk Product Services
     */
    @Deprecated
    public static String getSaveTncDiscussProviderServices(){
        /*redisConfigDao.setDatabase(RedisDataBaseDef.CONFIG_DB);
        return redisConfigDao.get(ConfigConstantDef.PRODUCT_SAVE_TNCSTD_DISCUSS_PROVIDER_SERVICES);*/
        return SystemServerManager.PdServerManager.getSaveTncStdDiscussProvider();
    }

    /**
     * 根据条件插入申请审核产品信息
     * @return Msk Product Services
     */
    @Deprecated
    public static String getSaveProviderPackageServices(){
        /*redisConfigDao.setDatabase(RedisDataBaseDef.CONFIG_DB);
        return redisConfigDao.get(ConfigConstantDef.PRODUCT_SAVE_PROVIDER_PACKAGE_SERVICES);*/
        return SystemServerManager.PdServerManager.getSaveProviderPackage();
    }

    /**
     * 查询产品原料产地信息
     * @return Msk Product Services
     */
    @Deprecated
    public static String getProductClassesTreeMatServices(){
        /*redisConfigDao.setDatabase(RedisDataBaseDef.CONFIG_DB);
        return redisConfigDao.get(ConfigConstantDef.PRODUCT_CLASSTREEMAT_SEARCH_SERVICES);*/
        return SystemServerManager.PdServerManager.getGetPdClassesTreeMatInfo();
    }

    /**
     * 查询卖家申请产品审核数据
     * @return Msk Product Services
     */
    @Deprecated
    public static String getProductProviderPackageServices(){
        /*redisConfigDao.setDatabase(RedisDataBaseDef.CONFIG_DB);
        return redisConfigDao.get(ConfigConstantDef.PRODUCT_PROVIDER_PACKAGE_SEARCH_SERVICES);*/
        return SystemServerManager.PdServerManager.getGetProviderPackageInfo();
    }

    /**
     * 查询卖家申请产品审核数据
     * @return Msk Product Services
     */
    @Deprecated
    public static String getProductProviderListServices(){
        /*redisConfigDao.setDatabase(RedisDataBaseDef.CONFIG_DB);
        return redisConfigDao.get(ConfigConstantDef.PRODUCT_PROVIDER_LIST_SEARCH_SERVICES);*/
        return SystemServerManager.PdServerManager.getFindProviderList();
    }

    /**
     * 查询产品标准
     * @return Msk Product Services
     */
    @Deprecated
    public static String getProductStandardSearchServices(){
        /*redisConfigDao.setDatabase(RedisDataBaseDef.CONFIG_DB);
        return redisConfigDao.get(ConfigConstantDef.PRODUCT_STANDARD_SEARCH_SERVICES);*/
        return SystemServerManager.PdServerManager.getFindProductStandard();
    }

    /**
     * 保存产品加工质量标准表
     * @return Msk Product Services
     */
    @Deprecated
    public static String getProductSaveMctProviderServices(){
        /*redisConfigDao.setDatabase(RedisDataBaseDef.CONFIG_DB);
        return redisConfigDao.get(ConfigConstantDef.PRODUCT_SAVE_MCT_PROVIDER_SERVICES);*/
        return SystemServerManager.PdServerManager.getSaveMctProvider();
    }

    /**
     * 查询产品主码查询接口
     * @return Msk Product Services
     */
    @Deprecated
    public static String getProductAllPdCodeSearchServices(){
        /*redisConfigDao.setDatabase(RedisDataBaseDef.CONFIG_DB);
        return redisConfigDao.get(ConfigConstantDef.PRODUCT_ALL_PDCODE_SERVICES);*/
        return SystemServerManager.PdServerManager.getFindAllPdCode();
    }

    /**
     * 查询产品一级分类信息
     * @return
     */
    @Deprecated
    public static String getProductClassesInfo(){
        /*redisConfigDao.setDatabase(RedisDataBaseDef.CONFIG_DB);
        return redisConfigDao.get(ConfigConstantDef.PRODUCT_CLASSES_SEARCH_SERVICES);*/
        return SystemServerManager.PdServerManager.getFindPdClasses();
    }

    /**
     * 查询产品二级分类信息
     * @return
     */
    @Deprecated
    public static String getProductMachiningInfo(){
        /*redisConfigDao.setDatabase(RedisDataBaseDef.CONFIG_DB);
        return redisConfigDao.get(ConfigConstantDef.PRODUCT_MACHINING_SEARCH_SERVICES);*/
        return SystemServerManager.PdServerManager.getFindPdMachining();
    }

    /**
     * 查询产品品种信息
     * @return
     */
    @Deprecated
    public static String getProductBreedInfo(){
        /*redisConfigDao.setDatabase(RedisDataBaseDef.CONFIG_DB);
        return redisConfigDao.get(ConfigConstantDef.PRODUCT_BREED_SEARCH_SERVICES);*/
        return SystemServerManager.PdServerManager.getFindPdBreed();
    }

    /**
     * 查询产品特征信息
     * @return
     */
    @Deprecated
    public static String getProductFeatureInfo(){
        /*redisConfigDao.setDatabase(RedisDataBaseDef.CONFIG_DB);
        return redisConfigDao.get(ConfigConstantDef.PRODUCT_FEATURE_SEARCH_SERVICES);*/
        return SystemServerManager.PdServerManager.getFindPdFeature();
    }

    /**
     * 查询产品净重信息
     * @return
     */
    @Deprecated
    public static String getProductWeightInfo(){
        /*redisConfigDao.setDatabase(RedisDataBaseDef.CONFIG_DB);
        return redisConfigDao.get(ConfigConstantDef.PRODUCT_WEIGHT_SEARCH_SERVICES);*/
        return SystemServerManager.PdServerManager.getFindPdWeight();
    }

    /**
     * 查询产品包装信息
     * @return
     */
    @Deprecated
    public static String getProductNormsInfo(){
        /*redisConfigDao.setDatabase(RedisDataBaseDef.CONFIG_DB);
        return redisConfigDao.get(ConfigConstantDef.PRODUCT_NORMS_SEARCH_SERVICES);*/
        return SystemServerManager.PdServerManager.getFindPdNormsStd();
    }

    /**
     * 查询卖家产品编码
     * @return Msk Seller Services
     */
    @Deprecated
    public static String getSellerSlPdCodeSearchServices(){
       /* redisConfigDao.setDatabase(RedisDataBaseDef.CONFIG_DB);
        return redisConfigDao.get(ConfigConstantDef.SELLER_SLPDCODE_SEARCH_SERVICES);*/
        return SystemServerManager.SellerServerManage.getSlPdCodeSearch();
    }

    /**
     * 查询供应商名称
     * @return Msk Seller Services
     */
    @Deprecated
    public static String getSellerSlEpNameSearchServices(){
        /*redisConfigDao.setDatabase(RedisDataBaseDef.CONFIG_DB);
        return redisConfigDao.get(ConfigConstantDef.SELLER_SLEPNAME_SEARCH_SERVICES);*/
        return SystemServerManager.SellerServerManage.getEpNameSearch();
    }

    /**
     * 批量查询卖家身份企业信息
     * @return Msk Seller Services
     */
    @Deprecated
    public static String getSellerSlEpDataListSearchServices(){
        /*redisConfigDao.setDatabase(RedisDataBaseDef.CONFIG_DB);
        return redisConfigDao.get(ConfigConstantDef.SELLER_SLEPDATALIST_SEARCH_SERVICES);*/
        return SystemServerManager.SellerServerManage.getEpDataListSearch();
    }

    /**
     * 根据查询卖家(显示)编码
     * @return Msk Seller Services
     */
    @Deprecated
    public static String getSellerSlSellerCodeSearchServices(){
        /*redisConfigDao.setDatabase(RedisDataBaseDef.CONFIG_DB);
        return redisConfigDao.get(ConfigConstantDef.SELLER_SLSELLERCODE_SEARCH_SERVICES);*/
        return SystemServerManager.SellerServerManage.getSellerCodeSearch();
    }

    /**
     * 查询卖家银行卡信息
     * @return Msk Seller Services
     */
    @Deprecated
    public static String getSellerSlBaseInfoSearchServices(){
        /*redisConfigDao.setDatabase(RedisDataBaseDef.CONFIG_DB);
        return redisConfigDao.get(ConfigConstantDef.SELLER_SLBASEINFO_SEARCH_SERVICES);*/
        return SystemServerManager.SellerServerManage.getSlPdInfoCodeSearch();
    }

    /**
     * 查询卖家银行卡信息
     * @return Msk Seller Services
     */
    @Deprecated
    public static String getSellerSlBsBuyerSearchServices(){
       /* redisConfigDao.setDatabase(RedisDataBaseDef.CONFIG_DB);
        return redisConfigDao.get(ConfigConstantDef.SELLER_SLBSBUYER_SEARCH_SERVICES);*/
        return SystemServerManager.SellerServerManage.getBsBuyerSearch();

    }

    /**
     * 查询卖家产品货号信息
     * @return Msk Seller Services
     */
    @Deprecated
    public static String getSellerSlPdArtnoSearchServices(){
        /*redisConfigDao.setDatabase(RedisDataBaseDef.CONFIG_DB);
        return redisConfigDao.get(ConfigConstantDef.SELLER_SLPDARTNO_SEARCH_SERVICES);*/
        return SystemServerManager.SellerServerManage.getPdArtnoSearch();
    }

    /**
     * 查询卖家产品品牌信息（多个)
     * @return Msk Seller Services
     */
    @Deprecated
    public static String getSellerSlPdBrandSearchServices(){
        /*redisConfigDao.setDatabase(RedisDataBaseDef.CONFIG_DB);
        return redisConfigDao.get(ConfigConstantDef.SELLER_SLPDBRAND_SEARCH_SERVICES);*/
        return SystemServerManager.SellerServerManage.getPdBrandSearch();
    }

    /**
     * 查询冻品管家账户信息
     * @return Msk Seller Services
     */
    @Deprecated
    public static String getSellerSlHouseAccountSearchServices(){
        /*redisConfigDao.setDatabase(RedisDataBaseDef.CONFIG_DB);
        return redisConfigDao.get(ConfigConstantDef.SELLER_SLHOUSEACCOUNT_SEARCH_SERVICES);*/
        return SystemServerManager.SellerServerManage.getSlHouseAccountSearch();
    }

    /**
     * 查询卖家产品信息
     * @return Msk Seller Services
     */
    @Deprecated
    public static String getSellerSlProductSearchServices(){
        /*redisConfigDao.setDatabase(RedisDataBaseDef.CONFIG_DB);
        return redisConfigDao.get(ConfigConstantDef.SELLER_SLPRODUCT_SEARCH_SERVICES);*/
        return SystemServerManager.SellerServerManage.getProductSearch();
    }

    /**
     * 新增卖家产品状态履历
     * @return Msk Seller Services
     */
    @Deprecated
    public static String getSellerSlPdStatusHisNewServices(){
       /* redisConfigDao.setDatabase(RedisDataBaseDef.CONFIG_DB);
        return redisConfigDao.get(ConfigConstantDef.SELLER_SLPDSTATUSHIS_NEW_SERVICES);*/
        return SystemServerManager.SellerServerManage.getCreateSlPdStatusHis();
    }

    /**
     * 获取生产商,品牌ID
     * @return Msk Seller Services
     */
    @Deprecated
    public static String getSellerManufactureAndBrandSearchServices(){
        /*redisConfigDao.setDatabase(RedisDataBaseDef.CONFIG_DB);
        return redisConfigDao.get(ConfigConstantDef.SELLER_MANUFACTUREANDBRAND_SEARCH_SERVICES);*/
        return SystemServerManager.SellerServerManage.getFindManuAndBrand();
    }

    /**
     * 根据卖家产品查询产品生产商
     * @return Msk Seller Services
     */
    @Deprecated
    public static String getSellerSlPropSearchServices(){
       /* redisConfigDao.setDatabase(RedisDataBaseDef.CONFIG_DB);
        return redisConfigDao.get(ConfigConstantDef.SELLER_SLPROP_SEARCH_SERVICES);*/
        return SystemServerManager.SellerServerManage.getSearch();
    }

    /**
     * 查询创建时间范围内的卖家用户
     * @return Msk Seller Services
     */
    @Deprecated
    public static String getSellerSoSalesRankingSearchServices(){
        /*redisConfigDao.setDatabase(RedisDataBaseDef.CONFIG_DB);
        return redisConfigDao.get(ConfigConstantDef.SELLER_SOSALESRANKING_SEARCH_SERVICES);*/
        return SystemServerManager.SellerServerManage.getSoSalesRankingSearch();
    }

    /**
     * 查询卖家名称和编码
     * @return Msk Seller Services
     */
    @Deprecated
    public static String getSellerSlCodeNameSearchServices(){
        /*redisConfigDao.setDatabase(RedisDataBaseDef.CONFIG_DB);
        return redisConfigDao.get(ConfigConstantDef.SELLER_SLCODENAME_SEARCH_SERVICES);*/
        return SystemServerManager.SellerServerManage.getSlPdCodeNameSearch();
    }

    /**
     * 查询卖家供应商名称和编码
     * @return Msk Seller Services
     */
    @Deprecated
    public static String getSellerSlCodeShowNameSearchServices(){
        /*redisConfigDao.setDatabase(RedisDataBaseDef.CONFIG_DB);
        return redisConfigDao.get(ConfigConstantDef.SELLER_SLCODESHOWNAME_SEARCH_SERVICES);*/
        return SystemServerManager.SellerServerManage.getSlPdCodeSearch1();
    }

    /**
     * 查询卖家产品分页信息
     * @return Msk Seller Services
     */
    @Deprecated
    public static String getSellerSlSellerProductSearchServices(){
        /*redisConfigDao.setDatabase(RedisDataBaseDef.CONFIG_DB);
        return redisConfigDao.get(ConfigConstantDef.SELLER_SLSELLERPRODUCT_SEARCH_SERVICES);*/
        return SystemServerManager.SellerServerManage.getGetLgcsSellerProductInfoSearch();
    }

    /**
     * 查询企业名称和企业ID
     * @return Msk Seller Services
     */
    @Deprecated
    public static String getSellerSlenterpriseListServices(){
        /*redisConfigDao.setDatabase(RedisDataBaseDef.CONFIG_DB);
        return redisConfigDao.get(ConfigConstantDef.SELLER_SLENTERPRISE_LIST_SERVICES);*/
       return SystemServerManager.SellerServerManage.getSearchSlEnterprise();
    }



    /**
     * 更新库存
     * @return Msk Stock Services
     */
    @Deprecated
    public static String getStockOfSupplierListSaveServices(){
        /*redisConfigDao.setDatabase(RedisDataBaseDef.CONFIG_DB);
        return redisConfigDao.get(ConfigConstantDef.STOCK_OF_SUPPLIER_LIST_SAVE_SERVICES);*/
        return SystemServerManager.SoStockServerManage.getSaveStockOfSupplierList();
    }

    /**
     *  取消供应商冻结库存
     * @return Msk Stock Services
     */
    @Deprecated
    public static String stockSuppCancelFrozenServices(){
        /*redisConfigDao.setDatabase(RedisDataBaseDef.CONFIG_DB);
        return redisConfigDao.get(ConfigConstantDef.STOCK_SUPP_CANCEL_FROZEN_SERVICES);*/
        return SystemServerManager.SoStockServerManage.getCancelFrozenStockSupp();
    }

    /**
     *  取消卖家冻结库存
     * @return Msk Stock Services
     */
    @Deprecated
    public static String stockSlCancelFrozenServices(){
        /*redisConfigDao.setDatabase(RedisDataBaseDef.CONFIG_DB);
        return redisConfigDao.get(ConfigConstantDef.STOCK_SL_CANCEL_FROZEN_SERVICES);*/
        return SystemServerManager.SoStockServerManage.getCancelFrozenStockSupp();
    }

    /**
     *  按条件查询供应商库存
     * @return Msk Stock Services
     */
    @Deprecated
    public static String queryStockQtyStockBySupplier(){
        /*redisConfigDao.setDatabase(RedisDataBaseDef.CONFIG_DB);
        return redisConfigDao.get(ConfigConstantDef.STOCK_QTY_QUERY_STOCK_BYSUPPLIER_SERVICES);*/
        return SystemServerManager.SoStockServerManage.getQueryStockQty();
    }

    /**
     * 查询产品分类接口
     * @return
     */
    @Deprecated
    public static String getPdClassesTreeInfoSearchServices() {
        /*redisConfigDao.setDatabase(RedisDataBaseDef.CONFIG_DB);
        return redisConfigDao.get(ConfigConstantDef.PRODUCT_CLASSTREE_SERVICES);*/
        return SystemServerManager.PdServerManager.getGetPdClassesTreeInfo();
    }
    /**
     * 查询大区信息
     * @return District Area Service
     */
    @Deprecated
    public static String getDistrictQueryAreaService(){
        /*redisConfigDao.setDatabase(RedisDataBaseDef.CONFIG_DB);
        return redisConfigDao.get(ConfigConstantDef.DISTRICT_QUERY_AREA_SERVICES);*/
        return SystemServerManager.DistrictServerManage.getDistrictQueryArea();
    }
    /**
     * 查询省信息
     * @return District Province Service
     */
    @Deprecated
    public static String getDistrictQueryProvinceService(){
        /*redisConfigDao.setDatabase(RedisDataBaseDef.CONFIG_DB);
        return redisConfigDao.get(ConfigConstantDef.DISTRICT_QUERY_PROVINCE_SERVICES);*/
        return SystemServerManager.DistrictServerManage.getDistrictQueryProvince();
    }
    /**
     * 查询城市信息
     * @return District City Service
     */
    @Deprecated
    public static String getDistrictQueryCityService(){
        /*redisConfigDao.setDatabase(RedisDataBaseDef.CONFIG_DB);
        return redisConfigDao.get(ConfigConstantDef.DISTRICT_QUERY_CITY_SERVICES);*/
        return SystemServerManager.DistrictServerManage.getDistrictQueryCity();
    }
    /**
     * 查询区县信息
     * @return District District Service
     */
    @Deprecated
    public static String getDistrictQueryDistrictService(){
        /*redisConfigDao.setDatabase(RedisDataBaseDef.CONFIG_DB);
        return redisConfigDao.get(ConfigConstantDef.DISTRICT_QUERY_DISTRICT_SERVICES);*/
        return SystemServerManager.DistrictServerManage.getDistrictQueryDistrict();
    }

    /**
     * 查询行政规划信息
     * @return District District Service
     */
    @Deprecated
    public static String getDistrictQueryRegionService(){
       /* redisConfigDao.setDatabase(RedisDataBaseDef.CONFIG_DB);
        return redisConfigDao.get(ConfigConstantDef.DISTRICT_QUERY_REGION_SERVICES);*/
        return SystemServerManager.DistrictServerManage.getDistrictQueryRegion();
    }

    /**
     * Seller OneClass Search Services
     * @return Seller OneClass Service
     */
    @Deprecated
    public static String getSellerOneClassSearchServices(){
        /*redisConfigDao.setDatabase(RedisDataBaseDef.CONFIG_DB);
        return redisConfigDao.get(ConfigConstantDef.SELLER_ONECLASS_SEARCH_SERVICES);*/
        return SystemServerManager.SellerServerManage.getSlOneClassSearch();
    }

    /**
     * 查询产品包装
     * @return
     */
    @Deprecated
    public static String getPdPackageInfoSearchServices() {
        /*redisConfigDao.setDatabase(RedisDataBaseDef.CONFIG_DB);
        return redisConfigDao.get(ConfigConstantDef.PRODUCT_PDPACKAGE_SERVICES);*/
        return SystemServerManager.PdServerManager.getFindProductPackage();
    }

    /**
     * 根据编号查询名称
     * @return
     */
    @Deprecated
    public static String getPdTypeNameInfoSearchService(){
        /*redisConfigDao.setDatabase(RedisDataBaseDef.CONFIG_DB);
        return redisConfigDao.get(ConfigConstantDef.PRODUCT_PDTYPENAME_SERVICES);*/
        return SystemServerManager.PdServerManager.getPdTypeName();
    }

    /**
     * 根据编码查询产品信息（编码可变）
     * @return
     */
    @Deprecated
    public static String getPdInfoSearchService(){
        /*redisConfigDao.setDatabase(RedisDataBaseDef.CONFIG_DB);
        return redisConfigDao.get(ConfigConstantDef.PRODUCT_PDINFO_SERVICES);*/
        return SystemServerManager.PdServerManager.getFindProductInfo();
    }

    @Deprecated
    public static String getPdInfosSearchService(){
        /*redisConfigDao.setDatabase(RedisDataBaseDef.CONFIG_DB);
        return redisConfigDao.get(ConfigConstantDef.PRODUCT_PDINFOS_SERVICES);*/
        return SystemServerManager.PdServerManager.getFindProductInfos();
    }
    /**
     * 记录交易明细
     * @return
     */
    @Deprecated
    public static String cpTransactionServices(){
        /*redisConfigDao.setDatabase(RedisDataBaseDef.CONFIG_DB);
        return redisConfigDao.get(ConfigConstantDef.CP_TRANSACTION_SERVICES);*/
        return SystemServerManager.SoCashPoolingServerManage.getTransaction();
    }


    /**
     * 记录支付明细
     * @return
     */
    @Deprecated
    public static String cpRunningServices(){
       /* redisConfigDao.setDatabase(RedisDataBaseDef.CONFIG_DB);
        return redisConfigDao.get(ConfigConstantDef.CP_RUNNING_SERVICES);*/
        return SystemServerManager.SoCashPoolingServerManage.getRunning();
    }

    /**
     * 记录卖家计费项
     * @return
     */
    @Deprecated
    public static String cpSellerChargingServices(){
        /*redisConfigDao.setDatabase(RedisDataBaseDef.CONFIG_DB);
        return redisConfigDao.get(ConfigConstantDef.CP_SELLERCHARGING_SERVICES);*/
        return SystemServerManager.SoCashPoolingServerManage.getSellerCharging();
    }



    /**
     * 获得modelName
     **/
    public static  String getModelName() {
        return config.getModelName();
    }

    /**
     * 获得environment
     **/
    public static String getEnvironment() {
        return config.getEnvironment();
    }


    /**
     * 查询订单信息
     * @return
     */
    @Deprecated
    public static String getOrderDetailService(){
        /*redisConfigDao.setDatabase(RedisDataBaseDef.CONFIG_DB);
        return redisConfigDao.get(ConfigConstantDef.ORDER_DETAIL_SERVICES);*/
        return SystemServerManager.SoOrderServerManage.getQuerySdoDetail();
    }

    /**
     * 供应商上半月销售排名
     * @return
     */
    @Deprecated
    public static String getOrderHalfMonthService(){
       /* redisConfigDao.setDatabase(RedisDataBaseDef.CONFIG_DB);
        return redisConfigDao.get(ConfigConstantDef.ORDER_HALF_MONTH_SERVICES);*/
        return SystemServerManager.SoOrderServerManage.getQueryOrderHalfMonthCount();
    }

    /**
     * 文件上传接口
     * @return
     */
    @Deprecated
    public static String getFileUploadService(){
        /*redisConfigDao.setDatabase(RedisDataBaseDef.CONFIG_DB);
        return redisConfigDao.get(ConfigConstantDef.FILE_UPLOAD_SERVICES);*/
        return SystemServerManager.CommonServerManager.getFlieUploadServices();
    }

    /**
     * 日志分析接口
     * @return
     */
/*    public static String getLogAnalysisService(){
        redisConfigDao.setDatabase(RedisDataBaseDef.CONFIG_DB);
        return redisConfigDao.get(ConfigConstantDef.LOG_ANALYSIS_SERVICES);
    }*/
    /**
     * 查询物流区供应商信息
     * @return
     */
    @Deprecated
    public static String getLgcsslinfoService(){
      /*  redisConfigDao.setDatabase(RedisDataBaseDef.CONFIG_DB);
        return redisConfigDao.get(ConfigConstantDef.SELLER_LGCSSLINFO_SEARCH_SERVICES);*/
        return SystemServerManager.SellerServerManage.getGetLgcsSellerInfoSearch();
    }

    /**
     * 更新买手店管家专属会员表
     * @return
     */
    @Deprecated
    public static String getSlBsBuyerUpdateService(){
        /*redisConfigDao.setDatabase(RedisDataBaseDef.CONFIG_DB);
        return redisConfigDao.get(ConfigConstantDef.SELLER_SLBSBUYER_UPDATE_SERVICES);*/
        return SystemServerManager.SellerServerManage.getSlBsBuyerUpdate();
    }


    /**
     * 获取截止本月15日止的已列入供应计划尚未入库的供应量
     * @return
     */
    @Deprecated
    public static String getUndonumService(){
        /*redisConfigDao.setDatabase(RedisDataBaseDef.CONFIG_DB);
        return redisConfigDao.get(ConfigConstantDef.DS_UNDONUM_SERVICES);*/
        return SystemServerManager.DsServerManage.getQueryProductLotInfo();
    }

    /**
     * MSK DS Services
     * @return MSK DS Services
     */
    public static String getMskDsServices() {
        redisConfigDao.setDatabase(RedisDataBaseDef.CONFIG_DB);
        return StringConst.EMPTY;
    }

    /**
     * 文件下载接口
     * @return 文件下载接口
     */
    @Deprecated
    public static String getMskFileDownLoadServices() {
        /*redisConfigDao.setDatabase(RedisDataBaseDef.CONFIG_DB);
        return redisConfigDao.get(ConfigConstantDef.MSK_FILE_DOWNLOAD_SERVICES);*/
        return SystemServerManager.CommonServerManager.getMskFlieDownLoadServers();
    }

    /**
     * 取得SSO服务器外网地址。
     *
     * @return SSO服务器地址
     */
    public static String getSsoUrlOutside() {
        return CONFIG_VALUES_MAP.get(ConfigConstantDef.SSO_URL_OUTSIDE);
    }

    /**
     * 取得SSO服务器内网地址。
     *
     * @return SSO服务器地址
     */
    public static String getSsoUrlInside() {
        return CONFIG_VALUES_MAP.get(ConfigConstantDef.SSO_URL_INSIDE);
    }

    /**
     * 取得Web站点地址。
     *
     * @return Web站点地址
     */
    public static String getWebServerUrl() {
        return CONFIG_VALUES_MAP.get(ConfigConstantDef.WEB_SERVER);
    }

    /**
     * 取得内部API调用根地址。
     *
     * @return 内部API调用根地址
     */
    public static String getApiServerUrl() {
        return CONFIG_VALUES_MAP.get(ConfigConstantDef.API_SERVER);
    }

}

