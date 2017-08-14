package com.msk.common.config.server;

/*
 * Created by jackjiang on 16/6/28.
 */
public interface SystemServerDef {
    /*
     * Org 模块提供的服务接口名称
     */
    interface OrgServer {
        /** 模块名称 */
        String MODULE_NAME = "ORG";
        String USER_LOGIN_SERVER_NAME = "UserLogin";
        String GET_LOGIN_EMPLOY_INFO_SERVER_NAME = "GetLoginEmployInfo";
        String SEARCH_MENU_LIST_SERVER_NAME = "SearchMenuList";
        String SEARCH_PAGE_LIST_SERVER_NAME = "SearchPageList";
        String SEARCH_SYSTEM_MODULE_SERVER_NAME = "SearchSystemModule";

        String LOGIN_USER_INFO_SERVER = "LoginUserInfo";
        String DEPARTMENT_ROLE_SERVER = "DepartmentRole";
        /***/
        String MODIFY_PASSWORD_SERVER_NAME = "ModifyPassword";

    }

    /*
     * Bs 模块提供的服务接口名称
     */
    interface BsServer {
        /** 模块名称 */
        String MODULE_NAME = "BS";
        /** 查找 */
        String SEARCH_SERVER_NAME = "Search";
        /** 编辑卖家账号 */
        String EDIT_ACCOUNT_SERVER_NAME = "EditSLAccount";
        /** 查询卖家店铺账号 */
        String QUERY_SHOP_ACCOUNT_SERVER_NAME = "QuerySLShopAccount";
        /** 编辑卖家 */
        String EDIT_SELLER_SERVER_NAME = "EditBSSeller";
        /** 编辑买手冻品管家的买家信息 */
        String EDIT_EXCLUSIVE_SERVER_NAME = "EditBSExclusive";
        // String QUERY_EXCLUSIVE_SERVER_NAME = "Query_Exclusive";
        // String QUERY_EXCLUSIVE_SERVER_NAME = "Query_Exclusive";
        // String UPDATE_PSD_SERVER_NAME = "Update_Psd";
        // String UPDATE_PSD_SERVER_NAME = "Update_Psd";
        /** 更新PSD */
        String UPDATE_PSD_SERVER_NAME = "UpdatePsd";
        /** 查询买手冻品管家的买家信息 */
        String QUERY_EXCLUSIVE_SERVER_NAME = "QuerySlExclusive";
        /** 查询买家的管家详情列表 */
        String SEARCH_HOUSE_INFO_SERVER_NAME = "SearchHouseInfo";
        /** 解除买手和冻品管家之间的关系 */
        String RELEASE_BUYER_SERVER_NAME = "ReleaseBuyer";
        /**/
        String RELEASE_HOUSE_KEEPER_SERVER_NAME = "ReleaseHouseKeeper";

        /** 同步买家模块买手信息 */
        String SYNC_BUYER_INFO = "SyncBuyerInfo";
        /** 同步买家模块买手信息 */
        String SEARCH_BUYER_ID = "SearchBuyerId";
        /** 查询冻品管家销售信息 */
        String GET_SALE_BY_BUYER_ID_SERVER_NAME = "GetSaleByBuyerId";
        /** 通过管家ID列表获得管家信息列表 */
        String GET_HOUSE_INFO_BY_ID_SERVER_NAME = "GetHouseInfoById";
        /** 根据用户帐号获得专属买手ID和CODE */
        String FIND_SL_CODE_AND_DIS_LIST_SERVER_NAME = "FindSlcodeAndDisList";
        /** 更新买家和冻品管家关系有效期时间 */
        String UPDATE_BUYER_VALID_DATE_SERVER_NAME = "UpdateBuyerValidDate";
        /** 找管家-我的服务经历以及心得查询 */
        String SEARCH_HK_EXPERIENCE_SERVER_NAME = "SearchHkExperience";
        /** 买家管家关系解除接口 */
        String UNBIND_RELATION_SERVER_NAME = "UnbindRelation";
        /**查询买手账号和基本信息*/
        String SEARCH_BS_INFO_SERVER_NAME = "SearchBsInfo";

    }

    /*
     * BatchManage 模块提供的服务接口名称
     */
    interface BatchManage {
        /** 模块名称 */
        String MODULE_NAME = "BATCH-MANAGE";
        /** 启动Batch */
        String SAVE_BATCH_SERVER_NAME = "SaveBatch";
        /** 修改batch */
        String MODIFY_BATCH_SERVER_NAME = "ModifyBatch";
    }

    /*
     * Buyers 模块提供的服务接口名称
     */
    interface Buyers {
        /** 模块名称 */
        String MODULE_NAME = "BUYERS";
        /** 查询买家信息 */
        String SEARCH_BUYER_INFO_SERVER_NAME = "SearchBuyerInfo";
        /** 查询买家列表 */
        String FIND_BUYER_LIST_SERVER_NAME = "FindBuyerList";
        // String FIND_MARKET_LIST_SERVER_NAME = "Find_Market_List";
        /** 查询市场列表 */
        String FIND_MARKET_LIST_SERVER_NAME = "FindMarketList";
        /** 买家注册 */
        String BUYER_REGISTER_SERVER_NAME = "BuyerRegister";
        /** 买家登录 */
        String BUYER_LOGIN_SERVER_NAME = "BuyerLogin";
        /** 修改密码 */
        String RESET_PASSWORD_UPDATE_SERVER_NAME = "ResetPasswordUpdate";
        /** 买家更新 */
        String BUYER_UPDATE_SERVER_NAME = "BuyerUpdate";
        /** 前台手机验证成功后,通过手机号直接修改密码 */
        String BUYER_UPDATE_BYTEL_SERVER_NAME = "BuyerUpdateByTel";
        /** 通路人员登录 */
        String ACCESS_LOGIN_SERVER_NAME = "AccessLogin";
        /** SSO登录验证接口 */
        String SSO_LOGIN_SERVER_NAME = "SsoLogin";
        /** 买家基本信息更新接口 */
        String BUYER_INFO_UPDATE_SERVER_NAME = "BuyerInfoUpdate";
        /** 查询买家基本信息 */
        String find_buyer_detail_info_SERVER_NAME = "FindBuyerDetailInfo";
        /** 查询买家基本信息列表 */
        // String FIND_BUYER_LIST_SERVER_NAME = "FindBuyerList";
        /** 根据指定条件获取买家 */
        String FIND_CONDITION_BUYER_LIST_SERVER_NAME = "FindConditionBuyerList";
        /** 根据指定条件获取批发市场 */
        String Find_Condition_Market_Terminal_SERVER_NAME = "FindConditionMarketTerminal";
        /** 根据指定条件获取菜场 */
        String Find_Condition_Market_Food_SERVER_NAME = "FindConditionMarketFood";
        /** 查询批发市场或菜场中的买家信息 */
        String FIND_BUYER_BY_MARKETID_SERVER_NAME = "FindBuyerByMarketId";
        /** 买家基本和全部销售对象信息 */
        String FIND_BASIC_INFO_SERVER_NAME = "FindBasicInfo";
        /** 批发市场信息更新接口 */
        String BY_MARKETTER_UPDATE_SERVER_NAME = "ByMarketTerUpdate";
        /** 菜场信息更新 */
        String BY_MARKET_FOOD_UPDATE_SERVER_NAME = "ByMarketFoodUpdate";
        /** 买家经营产品类别更新 */
        String BUYER_PD_CLASSIFICATION_UPDATE_SERVER_NAME = "BuyerPdClassificationUpdate";
        /** 买家销售产品信息 */
        String BUYER_PDCLASSIFICATION_FIND_SERVER_NAME = "BuyerPdClassificationFind";
        /** 买家销售对象更新 */
        String BUYER_SALES_TARGET_UPDATE_SERVER_NAME = "BuyerSalesTargetUpdate";
        /** 买家销售对象查询接口 */
        String BUYER_SALES_TARGET_FIND_LIST_SERVER_NAME = "BuyerSalesTargetFindList";
        /** 买家证照信息更新接口 */
        String BUYER_LICENCE_UPDATE_SERVER_NAME = "BuyerLicenceUpdate";
        /** 买家证照信息查询接口 */
        String BUYER_LICENCE_FIND_SERVER_NAME = "BuyerLicenceFind";
        /** 买家证照图片更新接口 */
        String BUYER_LICENCE_PIC_UPDATE_SERVER_NAME = "BuyerLicencePicUpdate";
        /** 买家证照图片信息 */
        String BUYER_LICENCE_PIC_FIND_SERVER_NAME = "BuyerLicencePicFind";
        /** 买家雇员信息更新接口 */
        String BUYER_EMPLOYEE_UPDATE_SERVER_NAME = "BuyerEmployeeUpdate";
        /** 买家雇员信息查询接口 */
        String BUYER_EMPLOYEE_FIND_LIST_SERVER_NAME = "BuyerEmployeeFindList";
        // String BUYER_EMPLOYEE_PHONE_SAVE = "Buyer_Employee_Phone_Save";
        /** 买家收货地址更新接口 */
        String BUYER_RECEIVE_ADDR_UPDATE_SERVER_NAME = "BuyerReceiveAddrUpdate";
        /** 买家收货地址查询接口 */
        String BUYER_RECEIVE_ADDR_FIND_LIST_SERVER_NAME = "BuyerReceiveAddrFindList";
        /** 通路注册雇员信息新增 */
        String BUYER_EMPLOYEE_PHONE_SAVE_SERVER_NAME = "BuyerEmployeePhoneSave";
        /** 通路注册雇员信息删除 */
        String BUYER_RECEIVE_ADDR_DELETE_SERVER_NAME = "BuyerReceiveAddrDelete";
        /** 通路注册雇员信息更新 */
        String BUYER_RECEIVETIME_UPDATE_SERVER_NAME = "BuyerReceiveTimeUpdate";
        /** 通路注册雇员信息查询 */
        String BUYER_RECEIVE_TIME_FIND_SERVER_NAME = "BuyerReceiveTimeFind";
        /** 添加新品种需求接口 */
        String ADD_NEW_CATEGORY_SERVER_NAME = "AddNewCategory";
        /** 买家产品品类和需求调研明细查询 */
        String FIND_BUYER_CAT_DETAIL_SERVER_NAME = "FindBuyerCatDetail";
        /** 买家产品品类和需求调研明细表更新接口 */
        String RESEARCH_CAT_DETAIL_UPDATE_SERVER_NAME = "ResearchCatDetailUpdate";
        // String FIND_BUYER_STD_ORG = "Find_Buyer_Std_Org";
        /** 买家产品原种种源标准更新接口 */
        String UPDATE_BUYER_STD_ORG_SERVER_NAME = "UpdateBuyerStdOrg";
        // String FIND_BUYER_STD_ORG = "Find_Buyer_Std_Org";
        /** 买家产品加工技术标准更新接口 */
        String UPDATE_BUYER_STD_MCT_SERVER_NAME = "UpdateBuyerStdMct";
        /** 产品原种种源标准查询接口 */
        String FIND_BUYER_STD_ORG_SERVER_NAME = "FindBuyerStdOrg";
        /** 买家产品加工质量标准更新接口 */
        String UPDATE_BUYER_STD_TNC_SERVER_NAME = "UpdateBuyerStdTnc";
        /** 买家产品饲养标准调研接口 */
        String FIND_BUYER_STD_FED_SERVER_NAME = "FindBuyerStdFed";
        /** 买家产品饲养标准调研更新接口 */
        String UPDATE_BUYER_STD_FED_SERVER_NAME = "UpdateBuyerStdFed";
        /** 买家产品品种通用质量标准调研接口 */
        String FIND_BUYER_STD_GNQ_SERVER_NAME = "FindBuyerStdGnq";
        /** 买家产品品种通用质量标准更新接口 */
        String UPDATE_BUYER_STD_GNQ_SERVER_NAME = "UpdateBuyerStdGnq";
        /** 买家产品品种安全标准调研接口 */
        String FIND_BUYER_STD_SFT_SERVER_NAME = "FindBuyerStdSft";
        /** 买家产品品种安全标准更新接口 */
        String UPDATE_BUYER_STD_SFT_SERVER_NAME = "UpdateBuyerStdSft";
        /** 买家产品品种储存运输标准调研接口 */
        String FIND_BUYER_STD_TSP_SERVER_NAME = "FindBuyerStdTsp";
        /** 买家产品品种储存运输标准更新接口 */
        String UPDATE_BUYER_STD_TSP_SERVER_NAME = "UpdateBuyerStdTsp";
        /** 买家产品品种包装标准调研接口 */
        String FIND_BUYER_STD_NOR_SERVER_NAME = "FindBuyerStdNor";
        /** 买家产品品种安全标准更新接口 */
        String UPDATE_BUYER_STD_NOR_SERVER_NAME = "UpdateBuyerStdNor";
        // String FIND_BUYER_RESEARCH = "Find_Buyer_Research";
        /** 买家产品新品种调研状态查询 */
        String FIND_BUYER_RESEARCH_NEW_SERVER_NAME = "FindBuyerResearchNew";
        /** 买家产品品种调研状态查询 */
        String FIND_BUYER_RESEARCH_SERVER_NAME = "FindBuyerResearch";
        /** 买家产品第三方品牌调研查询接口 */
        String FIND_BUYER_BRAND_SERVER_NAME = "FindBuyerBrand";
        /** 买家产品品类和需求调研明细表更新接口 */
        String RESEARCH_BRAND_UPDATE_SERVER_NAME = "ResearchBrandUpdate";
        /** 买家产品品类和需求调研明细表删除接口 */
        String RESEARCH_BRAND_DELETE_SERVER_NAME = "ResearchBrandDelete";
        /** 取得买家类型 */
        String FIND_BUYER_TYPES_LIST_SERVER_NAME = "FindBuyerTypesList";
        /** 买家上线状态管控接口 */
        String MARKETING_STATUS_CONTROL_SERVER_NAME = "MarketingStatusControl";
        /** 买家上线状态更新接口 */
        String MODIFY_MARKETING_STATUS_SERVER_NAME = "ModifyMarketingStatus";
        /** 买家收货信息查询 */
        String QUERY_DELIVERY_TIME_AND_PAY_SERVER_NAME = "QueryDeliveryTimeAndPay";
        /** 买家配送地址信息查询 */
        String QUERY_DELIVERY_ADDR_SERVER_NAME = "QueryDeliveryAddr";
        /** 买家收货信息更新 */
        String UPDATE_DELIVERY_TIME_AND_PAY_SERVER_NAME = "UpdateDeliveryTimeAndPay";
        /** 买家配送地址信息删除 */
        String DELETE_DELIVERY_ADDR_SERVER_NAME = "DeleteDeliveryAddr";
        /** 更新买家配送信息 */
        String UPDATE_DELIVERY_ADDR_SERVER_NAME = "UpdateDeliveryAddr";
        /** 根据id获取买家配送地址信息 */
        String QUERY_DDELIVERY_ADDR_BY_ID_SERVER_NAME = "QueryDdeliveryAddrById";
        /***/
        String SEARCH_BUYER = "SearchBuyer";
        /***/
        String SEARCH_BUYER_SHOP = "SearchBuyerShop";
        /***/
        String SEARCH_EXCLUSIVE = "SearchExclusive";
        /** 通过手机号码查询买家基本信息 */
        String QUERY_BUYERY_BASIC_INFO_BYTEL_SERVER_NAME = "QueryBuyeryBasicInfoByTel";
        /** 通过buyerId 查询管理工具 */
        String FIND_TOOL_TO_BUYER_ID_SERVER_NAME = "FindToolToBuyerId";
        /** 保存管控工具和时间 */
        String SAVE_TOOL_TO_DATA_BASE_SERVER_NAME = "SaveTOOLToDataBase";
        /** 保存管控工具和时间 */
        String GET_BUYERS_ALL_TIMES_LIST_SERVER_NAME = "GetBuyersAllTimesList";
        /** 获取买家报表信息 */
        String BY_REPORT_MANAGE_QUERY_SERVER_NAME = "ByReportManageQuery";
        /** 生成excel文件 */
        String EXCEL_FILE_CREATE_SERVER_NAME = "ExcelFileCreate";
        /** 删除excel文件 */
        String BY_REPORT_DELETE_SERVER_NAME = "ByReportDelete";
        /** 通过买家ID，省市区，配送地址查询买家编码 */
        String QUERY_BUYER_CODE_WITH_RING_CODE_SERVER_NAME = "QueryBuyerCodeWithRingCode";
        /** 通过买家编码查询买家ID */
        String QUERY_BUYER_ID_BY_BUYER_CODE_SERVER_NAME = "QueryBuyerIdByBuyerCode";
        /** 根据买家ID更新买家上线状态 */
        String MODIFY_MARKET_STATUS_BY_BUYER_ID_SERVER_NAME = "ModifyMarketStatusByBuyerId";
        /** 更新销售期买家产品表接口 */
        String UPDATE_SALE_PERIOD_SERVER_NAME = "UpdateSalePeriod";
        /** 新增发票 */
        String ADD_INVOICE_SERVER_NAME = "AddInvoice";
        /** 删除发票 */
        String DELETE_INVOICE_SERVER_NAME = "DeleteInvoice";
        /** 修改发票 */
        String UPDATE_INVOICE_SERVER_NAME = "UpdateInvoice";
        /** 查询发票 */
        String SEARCH_INVOICE_SERVER_NAME = "SearchInvoice";
        /** 买家信息列表查询接口 */
        String QUERY_BUYERS_SERVER_NAME = "QueryBuyers";
        /** 删除批发市场文件信息 */
        String UPDATE_MARKET_TERMINAL_FILE_INFO_SERVER_NAME = "UpdateMarketTerminalFileInfo";
        /** 删除菜场文件信息 */
        String UPDATE_MARKET_FOOD_FILE_INFO_SERVER_NAME = "UpdateMarketFoodFileInfo";
        /**买家经营产品类别查询接口*/
        String BUYER_PD_CLASSIFICATION_FIND_SERVER_NAME = "BuyerPdClassificationFind";

    }

    /*
     * District 模块提供的服务接口名称
     */
    interface District {
        /** 模块名称 */
        String MODULE_NAME = "DISTRICT";
        /** 查询县区信息 */
        String GET_DISTRICTS_SERVER_NAME = "GetDistricts";
        /** 根据名称查询编码 */
        String GET_CODES_FROM_NAMES_SERVER_NAME = "GetCodesFromNames";
        /***/
        String DISTRICT_QUERY_AREA = "DistrictQueryArea";
        /***/
        String DISTRICT_QUERY_CITY = "DistrictQueryCity";
        /***/
        String DISTRICT_QUERY_LGCSAREA = "DistrictQueryLgcsArea";
        /***/
        String DISTRICT_QUERY_PROVINCE = "DistrictQueryProvince";
        /***/
        String DISTRICT_QUERY_REGION = "DistrictQueryRegion";
        /***/
        String DISTRICT_QUERY_DISTRICT = "DistrictQueryDistrict";
    }

    /*
     * Ds 模块提供的服务接口名称
     */
    interface Ds {
        /** 模块名称 */
        String MODULE_NAME = "DS";
        /** 接口获取截止本月15日止的已列入供应计划尚未入库的供应量 */
        String QUERY_PRODUCT_LOT_INFO_SERVER_NAME = "QueryProductLotInfo";
        /**/
        String MODIFY_SERVER_NAME = "Modify";
        /* 卖家采供链生成美迪福接口xml文件 */
        String CREATE_XML_SERVER_NAME = "CreateXML";
        /** 发货入库详细列表PDF数据查询 */
        String GET_DELIVERY_PDF_DATA_SERVER_NAME = "GetDeliveryPDFData";
    }

    /*
     * Seller 模块提供的服务接口名称
     */
    interface Seller {
        /** 模块名称 */
        String MODULE_NAME = "SELLER";
        // String Create_Account = "Create_Account";
        /** 查询卖家信息 */
        String QUERY_INFO_SERVER_NAME = "QuerySlInfo";
        /** 创建账号 */
        String CREATE_ACCOUNT_SERVER_NAME = "CreateSLAccount";
        /** 更新账号 */
        String UPDATE_ACCOUNT_SERVER_NAME = "UpdateSLAccount";
        /** 修改账号 */
        String QUERY_ACCOUNT_SERVER_NAME = "QuerySLAccount";
        /** 编辑卖家产品标准 */
        String NEW_QLT_SERVER_NAME = "NewSlQlt";
        // String Modify_Db_Order = "Modify_Db_Order";
        // String Search_Pd = "Search_Pd";
        /** 查询 */
        String QUERY_SERVER_NAME = "Query";
        /** 增加卖家基本信息接口 */
        String CREATE_SELLER_SERVER_NAME = "CreateSlSeller";
        /** 更新卖家基本信息接口 */
        String UPDATE_SELLER_SERVER_NAME = "UpdateSLSeller";
        // String QUERY_SELLER = "Query_Seller";
        /** 增加企业基本资质接口 */
        String CREATE_ENTERPRISE_SERVER_NAME = "CreateSLEnterprise";
        /** 更新企业基本资质接口 */
        String UPDATE_ENTERPRISE_SERVER_NAME = "UpdateSLEnterprise";
        /** 查询卖家基本信息接口 */
        String QUERY_SELLER_SERVER_NAME = "QuerySLSeller";
        // String Search = "Search";
        /** 增加新的企业专业资质证照 */
        String CREATE_EP_CERTITEM_SERVER_NAME = "CreateSLEpCertItem";
        /** 更新新的企业专业资质证照 */
        String UPDATE_EP_CERTITEM_SERVER_NAME = "UpdateSLEpCertItem";
        /** 查询新的企业专业资质证照 */
        String QUERY_EP_CERTITEM_SERVER_NAME = "QuerySLEpCertItem";
        // String Delete_Ep_Cert = "Delete_Ep_Cert";
        // String Delete_Ep_Cert = "Delete_Ep_Cert";
        /** 删除新的企业专业资质证照 */
        String DELETE_EP_CERT_SERVER_NAME = "DeleteSLEpCert";
        // String Search = "Search";
        /** 增加企业管理团队接口 */
        String CREATE_EP_MANAGER_SERVER_NAME = "CreateSLEpManager";
        /** 更新企业管理团队接口 */
        String UPDATE_EP_MANAGER_SERVER_NAME = "UpdateSLEpManager";
        // String Delete_Ep_Manager = "Delete_Ep_Manager";
        // String Query_Ep_Manager = "Query_Ep_Manager";
        /** 增加卖家电商团队接口 */
        String CREATE_EC_TEAM_SERVER_NAME = "CreateSLEcTeam";
        /** 更新卖家电商团队接口 */
        String UPDATE_EC_TEAM_SERVER_NAME = "UpdateSLEcTeam";
        /** 删除卖家电商团队接口 */
        String DELETE_EC_TEAM_SERVER_NAME = "DeleteSLEcTeam";
        /** 查询企业管理团队接口 */
        String QUERY_EP_MANAGER_SERVER_NAME = "QuerySLEpManager";
        /** 创建企业管理团队接口 */
        String CREATE_EP_BRANDC_SERVER_NAME = "CreateSLEpBrandc";
        /** 更新企业管理团队接口 */
        String UPDATE_EP_BRANDC_SERVER_NAME = "UpdateSLEpBrandc";
        /** 删除企业管理团队接口 */
        String DELETE_EP_BRANDC_SERVER_NAME = "DeleteSLEpBrandc";
        /** 查询企业产品品牌接口 */
        String QUERY_EP_BRANDC_SERVER_NAME = "QuerySLEpBrandc";
        /** 创建企业产品品牌接口 */
        String CREATE_PD_BRAND_SERVER_NAME = "CreateSLPdBrand";
        // String Update_Pd_Brandc = "Update_Pd_Brandc";
        /** 删除卖家产品品牌 */
        String DELETE_PD_BRANDC_SERVER_NAME = "DeleteSLPdBrandc";
        /** 查询卖家产品品牌信息 */
        String SL_PD_BRAND_SEARCH_SERVER_NAME = "SlPdBrandSearch";
        /** 增加企业产品品牌荣誉接口 */
        String CREATE_EP_BRAND_HONOR_SERVER_NAME = "CreateSLEpBrandHonor";
        /** 修改卖家产品品牌接口 */
        String UPDATE_PD_BRANDC_SERVER_NAME = "UpdateSLPdBrandc";
        /** 删除企业产品品牌荣誉 */
        String DELETE_EP_BRAND_HONOR_SERVER_NAME = "DeleteSLEpBrandHonor";
        /** 查询企业产品品牌荣誉 */
        String QUERY_EP_BRAND_HONOR_SERVER_NAME = "QuerySLEpBrandHonor";
        /** 创建卖家产品类别 */
        String CREATE_PD_CLASSES_SERVER_NAME = "CreateSLPdClasses";
        /** 删除卖家产品类别 */
        String DELETE_PD_CLASSES_SERVER_NAME = "DeleteSLPdClasses";
        /** 查询卖家产品类别 */
        String QUERY_PD_CLASSES_SERVER_NAME = "QuerySLPdClasses";
        /** 创建卖家产品 */
        String CREATE_PRODUCT_SERVER_NAME = "CreateSLProduct";
        /** 删除卖家产品 */
        String UPDATE_PRODUCT_SERVER_NAME = "UpdateSLProduct";
        // String Delete_Product = "Delete_Product";
        /** 卖家申请新产品品种/特征/净重 */
        String CREATE_PD_BFW_SERVER_NAME = "CreateSLPdBFW";
        /** 卖家申请新产品包装 */
        String CREATE_PD_PKG_SERVER_NAME = "CreateSLPdPkg";
        /** 删除卖家产品 */
        String DELETE_PRODUCT_SERVER_NAME = "UpdateSLProduct";
        /** 删除企业管理团队接口 */
        String DELETE_EP_MANAGER_SERVER_NAME = "DeleteSLEpManager";
        /** 卖家账号密码修改 */
        String PSD_UPDATE_SERVER_NAME = "PsdUpdate";
        /** 查询营业执照_注册号 */
        String CHECK_LIC_NO_SERVER_NAME = "CheckLicNo";
        /** 查询卖家区域的code */
        String QUERY_CODE_SERVER_NAME = "QuerySLCode";
        // String Search = "Search";
        /** 查询卖家产品货号信息 */
        String SL_PD_ARTNO_SEARCH_SERVER_NAME = "SlPdArtnoSearch";
        // String Search = "Search";
        // String Pd_Code_Search = "Pd_Code_Search";
        /* 查询创建时间范围内的卖家用户 */
        String SO_SALES_RANKING_SEARCH_SERVER_NAME = "SoSalesRankingSearch";
        /** 查询供应商名称 */
        String SL_EP_NAME_SEARCH_SERVER_NAME = "SlEpNameSearch";
        /** 查询卖家身份企业信息 */
        String Sl_EP_DATA_SEARCH_SERVER_NAME = "SlEpDataSearch";
        /** 批量查询卖家身份企业信息 */
        String SL_EP_DATA_LIST_SEARCH_SERVER_NAME = "SlEpDataListSearch";
        /** 查询产品 */
        String SEARCH_PD_SERVER_NAME = "SearchSlPd";
        /** 查询卖家产品信息 */
        String SL_PRODUCT_SEARCH_SERVER_NAME = "SlProductSearch";
        /** 查询卖家（显示）编码 */
        String SL_SELLER_CODE_SEARCH_SERVER_NAME = "SlSellerCodeSearch";
        // String Pd_Code_Search = "Pd_Code_Search";
        /** 查询买手信息 */
        String SL_BS_BUYER_SEARCH_SERVER_NAME = "SlBsBuyerSearch";
        /** 更新买手店管家专属会员表 */
        String SLBS_BUYER_UPDATE_SERVER_NAME = "SlBsBuyerUpdate";
        /** 查询冻品管家账户信息 */
        String SL_HOUSE_ACCOUNT_SEARCH_SERVER_NAME = "SlHouseAccountSearch";
        /** 新增卖家产品状态履历 */
        String CREATE_SLPD_STATUS_HIS_SERVER_NAME = "CreateSlPdStatusHis";
        // String Get_Lgcs_Seller_Info_Search = "Get_Lgcs_Seller_Info_Search";
        /** 查询卖家区域code和name */
        String PD_CODE_SEARCH_SERVER_NAME = "SlPdCodeSearch";
        /***/
        String SL_PD_INFO_CODE_SEARCH_SERVER_NAME = "SlPdInfoCodeSearch";
        /** 查询卖家name */
        String PD_CODE_SEARCH1_SERVER_NAME = "SlPdCodeSearch1";
        /** 查询物流区供应商信息 */
        String GET_LGCS_SELLER_INFO_SEARCH_SERVER_NAME = "GetLgcsSellerInfoSearch";
        // String Modify_Db_Order = "Modify_Db_Order";
        /** 修改神农客分销章程信息 */
        String MODIFY_DB_ORDER_SERVER_NAME = "ModifyDbOrder";
        /** 取得神农客分销章程信息 */
        String UPDATE_DB_ORDER_SERVER_NAME = "UpdateDbOrder";
        /** 获取生产商和品牌ID */
        String FIND_MANU_AND_BRAND_SERVER_NAME = "FindManuAndBrand";
        /** 根据卖家ID查询卖家一级分类 */
        String SL_ONE_CLASS_SEARCH_SERVER_NAME = "SlOneClassSearch";
        /** 查询 */
        String SEARCH_SERVER_NAME = "Search";
        /** 查询企业名称和企业ID */
        String SEARCH_SLENTERPRISE_SERVER_NAME = "SearchSlEnterprise";
        /** 查询卖家产品编码和名称 */
        String SEARCH_SLPRODUCT_SERVER_NAME = "SearchSlProduct";
        /***/
        String SL_PD_CODE_NAME_SEARCH_SERVER_NAME = "SlPdCodeNameSearch";
        /***/
        String GET_LGCS_SELLER_PRODUCT_INFO_SEARCH_SERVER_NAME = "GetLgcsSellerProductInfoSearch";

        /** 买手信息维护卖家同步 */
        String DEAL_SL_SELLER_ACCOUNT = "DealSlSellerAccount";

        String QUERY_EP_NAME_SERVER_NAME = "QueryEPName";
        /** 查询卖家产品属性 */
        String GET_PD_CODE_SERVER_NAME = "GetPdCode";
        /** 根据卖家编码关联对应的生产商 */
        String GET_SL_ENTERPRISE_BY_SL_CODE_SERVER_NAME = "GetSlEnterpriseBySlCode";
        /** 卖家产品状态变更记录 */
        String STATUS_CHANGE_SERVER_NAME = "StatusChange";
        /** 根据卖家编码批量查询卖家信息 */
        String QUERY_SELLER_LIST_SERVER_NAME = "QuerySellerList";
        /** 根据卖家、销售平台、物流区域、产品查询产品对应的SKU信息 */
        String SKU_CODE_SEARCH_SERVER_NAME = "SkuCodeSearch";
        /** 查询卖家所有信息 */
        String SEARCH_ALL_S_L_EP_MANAGER_SERVER_NAME = "SearchAllSLEpManager";
        /***/
        String SEARCH_SL_PROP_SERVER_NAME = "SearchSlProp";
        /** 分页获取所有企业信息列表 */
        String QUERY_ENTERPRISE_INFO_SERVER_NAME = "QueryEnterpriseInfo";
        /** 分页获取企业产品信息列表 */
        String QUERY_PRODUCT_INFO_SERVER_NAME = "QueryProductInfo";
        /** 查询供应商对应的分销资格 */
        String QUERY_SL_SELLER_DIS_QUA_SERVER_NAME = "QuerySlSellerDisQua";
        /** 查询新增卖家对应产品信息 */
        String QUERY_SL_SELLER_PRODUCT_SERVER_NAME = "QuerySlSellerProduct";
        /***/
        String CREATE_S_L_ACCOUNT_BY_BUYER_SERVER_NAME = "CreateSLAccountByBuyer";
        /***/
        String CREATE_S_L_ACCOUNT_SERVER_NAME = "CreateSLAccount";
        /***/
        String DELETE_S_L_EP_CERT_SERVER_NAME = "DeleteSLEpCert";
        /***/
        String INSERT_SL_EP_CERT_SERVER_NAME = "InsertSlEpCert";
        /***/
        String ADD_SL_EP_INFO_SERVER_NAME = "AddSlEpInfo";
        /***/
        String DELETE_S_L_EP_MANAGER_SERVER_NAME = "DeleteSLEpManager";
        /***/
        String DELETE_S_L_PRODUCT_SERVER_NAME = "DeleteSLProduct";
        /***/
        String EDIT_S_L_ACCOUNT_INFO_SERVER_NAME = "EditSLAccountInfo";
        /***/
        String FIND_SL_SELLER_STD_SERVER_NAME = "FindSlSellerStd";
        /***/
        String FIND_DB_ORDER_INFO_SERVER_NAME = "FindDbOrderInfo";
        /***/
        String QUERY_S_L_EP_MANAGER_SERVER_NAME = "QuerySLEpManager";
        /***/
        String QUERY_SL_EC_TEAM_SERVER_NAME = "QuerySlEcTeam";
        /***/
        String QUERY_S_L_SELLER_SERVER_NAME = "QuerySLSeller";
        /***/
        String QUERY_SL_ENTERPRISE_INFO_SERVER_NAME = "QuerySlEnterpriseInfo";
        /***/
        String SEARCH_SL_MST_CERT_INFO_SERVER_NAME = "SearchSlMstCertInfo";
        /***/
        String SEARCH_SL_EP_AUTH_SERVER_NAME = "SearchSlEpAuth";
        /***/
        String SEARCH_SL_ART_NO_SERVER_NAME = "SearchSlArtNo";
        /***/
        String QUERY_SL_PD_ARTNO_INFO_SERVER_NAME = "QuerySlPdArtnoInfo";

        /***/
        String UPDATE_SL_PD_BRANDC_TEAM_SERVER_NAME = "UpdateSlPdBrandcTeam";
        /***/
        String UPDATE_SL_EP_BRAND_HONOR_SERVER_NAME = "UpdateSlEpBrandHonor";

    }

    /*
     * Seller 模块提供的服务接口名称
     */
    interface SnkPrice {
        /** 模块名称 */
        String MODULE_NAME = "SNK-PRICE";
        /** 获取供应商分销数量 */
        String GET_SUP_DISTRIBUTE_DEMAND_SERVER_NAME = "GetSupDistributeDemand";
        // String SEARCH = "Search";
        /** 神农客产品查询价盘接口调用 */
        String GET_PRICE_CYCLE_SERVER_NAME = "GetPriceCycle";
        /** 查询 */
        String SEARCH_SERVER_NAME = "Search";
        /***/
        String PRICE_CYCLE = "PriceCycle";
        /** 查询价盘通道接口 */
        String GET_PRICE_WAY = "GetPriceWay";
        /** 根据产品列表信息获取价盘通道和价格列表 */
        String GETPRICEWAYLIST_SERVER_NAME = "GetPriceWayList";

    }

    /*
     * SoCashPooling 模块提供的服务接口名称
     */
    interface SoCashPooling {
        /** 模块名称 */
        String MODULE_NAME = "SO-CASH-POOLING";
        /**/
        String TRANSACTION_SERVER_NAME = "Transaction";
        /**/
        String RUNNING_SERVER_NAME = "Running";
        /** 卖家计费项接口调用 */
        String SELLER_CHARGING_SERVER_NAME = "SellerCharging";
        /** 查询卖家结算列表接口调用 */
        String QUERY_BUYER_LIST_SERVER_NAME = "QueryBuyerList";
        /** 买手详细页面打印pdf回调 */
        String QUERY_BUYER_PDF = "QueryBuyerPDF";
        /** 卖家详细信息回调接口 */
        String QUERY_SELLER_PDF = "QuerySellerPDF";
    }

    /*
     * SoOrder 模块提供的服务接口名称
     */
    interface SoOrder {
        /** 模块名称 */
        String MODULE_NAME = "SO-ORDER";
        // String MODIFY_DB_ORDER = "Modify_Db_Order";
        // String CREATE_ORDER = "Create_Order";
        /***/
        /*
         * String CANCEL_SERVER_NAME = "Cancel";
         *//** 更新订单状态 */
        /*
         * String MODIFY_ORDER_STATUS_SERVER_NAME = "ModifyOrderStatus";
         *//** 订单收发货操作 */
        /*
         * String ORDER_RECEIVING_OR_DELIVER_SERVER_NAME = "OrderReceivingOrDeliver";
         * //String DETAIL = "Detail";
         * //String LIST = "List";
         * //String DETAIL = "Detail";
         *//** 列表 */
        /*
         * String LIST_SERVER_NAME = "List";
         *//** 获取卖家已卖出商品列表 */
        /*
         * String ORDER_PRODUCT_LIST_SERVER_NAME = "OrderProductList";
         *//** 分销订单统计 */
        /*
         * String STATISTICS_SERVER_NAME = "Statistics";
         *//** 修改退货单状态已经退款生成退款单 */
        /*
         * String RETRUN_ORDER_INBOUND_SERVER_NAME = "RetrunOrderInbound";
         *//** 查询 */
        /*
         * String SEARCH_SERVER_NAME = "Search";
         *//** 查询卖家产品 */
        /*
         * String FIND_PRODUCT_SERVER_NAME = "FindProduct";
         *//** 细节 */
        /*
         * String DETAIL_SERVER_NAME = "Detail";
         *//** 获取上半旬销售排名 */
        /*
         * String HALF_MONTH_ORDER_SERVER_NAME = "HalfMonthOrder";
         *//** 创建标准订单 */
        /*
         * String CREATE_ORDER_SERVER_NAME = "CreateOrder";
         *//** 创建标准订单标准分销订单 */
        /*
         * String CREATE_DB_ORDER_SERVER_NAME = "CreateDbOrder";
         *//** 查询标准分销订单详细 */
        /*
         * String QUERY_DB_ORDER_DETAIL_JSON_SERVER_NAME = "QueryDbOrderDetailJson";
         *//** 查询标准分销订单详细 */
        /*
         * String QUERY_DB_ORDER_DETAIL_XML_SERVER_NAME = "QueryDbOrderDetailXml";
         *//** 查询标准分销订单详细 */
        /*
         * String CANCEL_DB_ORDER_SERVER_NAME = "CancelDbOrder";
         * //String MODIFY_DB_ORDER = "Modify_Db_Order";
         * //String MODIFY_DB_ORDER = "Modify_Db_Order";
         * //String MODIFY_DB_ORDER = "Modify_Db_Order";
         * //String MODIFY_DB_ORDER = "Modify_Db_Order";
         *//** 查询卖家质量标准 *//*
                          * String MODIFY_DB_ORDER_SERVER_NAME = "ModifyDbOrder";
                          * String SEARCH_ORDER_SOURCE_COUNT_SERVER_NAME = "SearchOrderSourceCount";
                          */
    }

    /*
     * SoStock 模块提供的服务接口名称
     */
    interface SoStock {
        /** 模块名称 */
        String MODULE_NAME = "SO-STOCK";
        String SEARCH_SERVER_NAME = "Search";
        /** 查询卖家质量标准 */
        String MODIFY_DB_ORDER_SERVER_NAME = "ModifyDbOrder";
        // String GET_USED_STOCK = "Get_Used_Stock";
        // String UPDATE_STOCK = "Update_Stock";
        // String CANCEL_FROZEN_STOCK_SUPP = "Cancel_Frozen_Stock_Supp";
        // String GET_USED_STOCK = "Get_Used_Stock";
        // String GET_USED_STOCK = "Get_Used_Stock";
        /** 更新库存 */
        String UPDATE_STOCK_SERVER_NAME = "UpdateStock";
        /** 查询库存 */
        String QUERY_STOCK_QTY_SERVER_NAME = "QueryStockQty";
        /** 批量供应商库存数据 */
        String SAVE_STOCK_OF_SUPPLIER_LIST_SERVER_NAME = "SaveStockOfSupplierList";
        /** 批量更新供应商库存数据 */
        String CANCEL_FROZEN_STOCK_SUPP_SERVER_NAME = "CancelFrozenStockSupp";
        // String GET_USED_STOCK = "Get_Used_Stock";
        /** 获得卖家所有的产品和库存信息 */
        String GET_USED_STOCK_SERVER_NAME = "GetUsedStock";
    }

    interface SellerSupplyChain {
        /** 模块名称 */
        String MODULE_NAME = "SSC";
        /** 查询中标成交确认书详细 */
        String FIND_BID_PRODUCT_DETAIL_SERVER_NAME = "FindBidProductDetail";
        /** 查询中标成交确认书一览信息 */
        String FIND_SSC_BID_BASIC_INFO_LIST_SERVER_NAME = "FindSscBidBasicInfoList";
        /** 查询合同管理一览信息 */
        String FIND_CONTRACT_BASIC_SERVER_NAME = "FindContractBasic";
        /***/
        // String FIND_SSC_BID_BASIC_INFO_LIST_SERVER_NAME = "FindSscBidBasicInfoList";
        /***/
        String FIND_SSC_DELIVERY_ORDER_BASICLIST_SERVER_NAME = "FindSscDeliveryOrderBasicList";
        /***/
        String SEARCH_ORDERPD_SERVER_NAME = "SearchOrderPd";
        /***/
        String FIND_ORDER_BASIC_SERVER_NAME = "FindOrderBasic";
        /***/
        String MODIFY_ORDER_BASIC_SERVER_NAME = "ModifyOrderBasic";
        /***/
        String MODIFY_ORDER_BASIC_INFO_SERVER_NAME = "ModifyOrderBasicInfo";
        /***/
        String MODIFY_ORDER_PD_SERVER_NAME = "ModifyOrderPd";
        /***/
        String MODIFY_ORDER_PD_INFO_SERVER_NAME = "ModifyOrderPdInfo";
        /***/
        String SAVE_ORDER_BASIC_SERVER_NAME = "SaveOrderBasic";
        /***/
        String SAVE_ORDER_PD_SERVER_NAME = "SaveOrderPd";
        /***/
        String BATCH_SAVE_SSC_INTO_BASIC_SERVER_NAME = "BatchSaveSscIntoBasic";
        /***/
        String FIND_SSC_INTO_BASIC_SERVER_NAME = "FindSscIntoBasic";
        /***/
        String FIND_SSC_INTO_DETAIL_SERVER_NAME = "FindSscIntoDetail";
        /***/
        String MODIFY_SSC_INTO_DETAIL_SERVER_NAME = "ModifySscIntoDetail";
        /***/
        String FIND_DELIVERY_CONFIRM_SERVER_NAME = "FindDeliveryConfirm";
        /***/
        String FIND_DELIVERY_CONFIRM_DETAIL_TOTAL_SERVER_NAME = "FindDeliveryConfirmDetailTotal";
        /***/
        String MODIFY_DELIVERY_CONFIRM_SERVER_NAME = "ModifyDeliveryConfirm";
        /***/
        String MODIFY_DELIVERY_CONFIRM_DETAIL_SERVER_NAME = "ModifyDeliveryConfirmDetail";
        /***/
        String FIND_DELIVERY_CONFIRM_DETAIL_SERVER_NAME = "FindDeliveryConfirmDetail";
        /***/
        // String FIND_DELIVERY_CONFIRM_DETAIL_SERVER_NAME = "FindDeliveryConfirmDetail";
        /***/
        String INSERT_DELIVERY_PRE_INTO_SERVER_NAME = "InsertDeliveryPreInto";
        /***/
        String DELETE_PRODUCT_SERVER_NAME = "DeleteProduct";
        /***/
        String MODIFY_PRODUCT_SERVER_NAME = "ModifyProduct";
        /***/
        String MODIFY_BID_STATUS_SERVER_NAME = "ModifyBidStatus";
        /***/
        String INSERT_BID_BASIC_INFO_SERVER_NAME = "InsertBidBasicInfo";
        /***/
        String INSERT_BID_PRODUCT_DETAILT_SERVER_NAME = "InsertBidProductDetail";
        /***/
        String FIND_SSC_PAYMENT_BASIC_SERVER_NAME = "FindSscPaymentBasic";
        /***/
        String SAVE_PAYMENT_SERVER_NAME = "SavePayment";
        /***/
        String UPDATE_PAYMENT_SERVER_NAME = "UpdatePayment";
        /***/
        String FIND_PAYMENT_DETAIL_SERVER_NAME = "FindPaymentDetail";
        /***/
        String TO_ADD_PAYMENT_DETAIL_SERVER_NAME = "ToAddPaymentDetail";
        /***/
        String FIND_PAYMENT_DETAIL_BY_ID_SERVER_NAME = "FindPaymentDetailById";
        /***/
        String FIND_SSC_PAYMENT_DETAIL_SERVER_NAME = "FindSscPaymentDetail";
        /***/
        String SAVE_PAYMENT_DETAIL_SERVER_NAME = "SavePaymentDetail";
        /** 根据id查询合同基本信息 */
        // String FIND_SSC_BID_BASIC_INFO_LIST_SERVER_NAME = "FindSscBidBasicInfoList";
        /** 根据合同编号查询合同产品详细信息 */
        String FIND_CONTRACT_PD_DETAIL_LIST_SERVER_NAME = "FindContractPdDetailList";
        /** 根据id查询合同基本信息 */
        // String FIND_SSC_BID_BASIC_INFO_LIST_SERVER_NAME = "FindSscBidBasicInfoList";
        /** 根据合同编号查询合同包材信息列表 */
        // String FIND_SSC_BID_BASIC_INFO_LIST_SERVER_NAME = "FindSscBidBasicInfoList";
        /** 修改包材信息 */
        String MODIFY_CONTRACT_PACKING_SERVER_NAME = "ModifyContractPacking";
        /** 根据合同编号查询合同产品交货计划信息 */
        String FIND_DELIVERY_PLAN_LIST_SERVER_NAME = "FindDeliveryPlanList";
        /** save合同产品交货计划信息批量保存 */
        String SAVE_DELIVERY_PLAN_SERVER_NAME = "SaveDeliveryPlan";
        /** save合同产品交货计划信息批量保存 */
        String SAVE_CONTRACT_ORDER_SERVER_NAME = "SaveContractOrder";
        /** save合同产品交货计划信息批量保存 */
        String SAVE_CONTRACT_PACKAGE_M_SERVER_NAME = "SaveContractPackageM";
        /** modify合同产品交货计划信息 */
        String MODIFY_DELIVERY_PLAN_SERVER_NAME = "ModifyDeliveryPlan";
        /** 修改包材信息 */
        String UPDATE_CONTRACT_ORDER_SERVER_NAME = "UpdateContractOrder";
        /** 更新合同信息 */
        String UPDATE_CONTRACT_BASIC_SERVER_NAME = "UpdateContractBasic";
        /** 根据id删除对应的合同订单 */
        String DEL_CONTRACT_PD_SERVER_NAME = "DelContractPd";
        /** 根据id删除对应的合同订单 */
        String DEL_CONTRACT_PACKGE_M_SERVER_NAME = "DelContractPackgeM";
        /** 保存合同基础信息 */
        String SAVE_CONTRACT_BASCI_SERVER_NAME = "SaveContractBasci";
        /** check发货 */
        String CHECK_EFFEC_BOX_NUM_SERVER_NAME = "CheckEffecBoxNum";
        /** 保存合同商务条款 */
        String SAVE_CONTRACT_BUSINESS_SERVER_NAME = "SaveContractBusiness";
        /** 修改合同商务条款 */
        String UPDATE_CONTRACT_BUSINESS_SERVER_NAME = "UpdateContractBusiness";
        /***/
        String FIND_DELIVERY_CONFIRM_INFO_LIST_SERVER_NAME = "FindDeliveryConfirmInfoList";
        /***/
        String FIND_CHOOSE_CONTRACT_SERVER_NAME = "FindChooseContract";
        /***/
        String FIND_CHOOSE_DELIVERY_SERVER_NAME = "FindChooseDelivery";
        /***/
        String INSERT_DELIVERY_CONFIRM_SERVER_NAME = "InsertDeliveryConfirm";
        /***/
        String FIND_DELIVERY_PRE_INFO_SERVER_NAME = "FindDeliveryPreInfo";
        /***/
        String FIND_DELIVERY_PRE_PD_SERVER_NAME = "FindDeliveryPrePd";
        /***/
        String MODIFY_DELIVERY_INTO_INFO_SERVER_NAME = "ModifyDeliveryIntoInfo";
        /* 查询商务条款 **/
        String FIND_SSC_CONTRACT_BUSINESS_SERVER_NAME = "FindSscContractBusiness";
        /* 根据id 查询合同基本信息 **/
        String FIND_SSC_BID_BASIC_SERVER_NAME = "FindSscBidBasic";
        /* 根据合同编号查询合同包材信息列表 **/
        String FIND_CONTRACT_PACKING_LIST_SERVER_NAME = "FindContractPackingList";
        /***/
        String FIND_ORDER_PD_SERVER_NAME = "FindOrderPd";
        /* 根据合同id,pdCode 查询 **/
        String FIND_PD_SERVER_NAME = "FindPd";
        /***/
        String CREATE_DIFFER_SERVER_NAME = "CreateDiffer";
        /***/
        String QUERY_DIFFER_BASICS_SERVER_NAME = "QueryDifferBasics";
        /***/
        String QUERY_DIFFER_DETAILS_SERVER_NAME = "QueryDifferDetails";
        /***/
        String UPDATE_PREINTO_SERVER_NAME = "UpdatePreInto";
        /***/
        String CREAT_CONTRACTS_SERVER_NAME = "CreatContracts";
        /***/
        String CHECK_BID_SERVER_NAME = "CheckBid";
        /** 创建发货订单信息 */
        String CREATE_SSC_DELIVERY_ORDER_INFO_SERVER_NAME = "CreateSscDeliveryOrderInfo";
        /** 查询发货订单列表 */
        String FIND_DELIVERY_BATCH_LIST_SERVER_NAME = "FindDeliveryBatchList";
        /** 查询预入库基本表信息 */
        String QUERY_PREINTO_BASICS_SERVER_NAME = "QueryPreintoBasics";
        /** 查询合同编号 */
        String FIND_DB_CONTRACT_CODE_SERVER_NAME = "FindDBContractCode";
        /** 查询包材产品 */
        String FIND_PACK_SERVER_NAME = "FindPack";
        /** 查询指定预入库通知单(单个查询) */
        String FIND_PRE_INTO_SERVER_NAME = "FindPreInto";
        /** 删除预入库通知单(逻辑删除) */
        String DELETE_PRE_INTO = "DeletePreInto";
        /** 删除发货确认单(逻辑删除) */
        String DELETE_CONFIRM_BASIC = "DeleteConfirmBasic";
        /** 新增或更新交货期计划 */
        String SAVE_OR_UPDATE_DP_SERVER_NAME = "SaveOrUpdateDP";
        /** 查询发货确认单中待装车产品列表 */
        String CHOOSE_CONFIRM_PDS_SERVER_NAME = "ChooseConfirmPds";
        /** 检查确认单产品是否全部装车 */
        String CHECK_PD_PLAN_BOX_SERVER_NAME = "CheckPdPlanBox";
        /** 根据合同 ID，查询合同订单中的产品明细，排除已有包材信息的产品 */
        String FIND_CONTRACT_PRODUCTS_SERVER_NAME = "FindContractProducts";
        /** 查询合同是否有发货订单 */
        String CHECK_HAS_ORDER_BASIC_SERVER_NAME = "CheckHasOrderBasic";
        /** 根据合同ID删除合同 */
        String DELETE_CONTRACT_BASIC_SERVER_NAME = "DeleteContractBasic";
        /** 插入预入库文件信息 */
        String SAVE_PRE_INTO_FILE = "SavePreIntoFile";
        /** 更新差异单主表信息 */
        String UPDATE_DIFFER_BASIC_SERVER_NAME = "UpdateDifferBasic";
        /** 查询合同是否生成 */
        String CHECK_IS_CONTRACT_SERVER_NAME = "CheckIsContract";
        /** 删除中标成交书基础表 */
        String DELETE_BID_BASE_SERVER_NAME = "DeleteBidBase";
        /** 根据中标id,pdCode查询产品是否存在 */
        String FIND_BID_PD_SERVER_NAME = "FindBidPd";
        /** 批量新增交货计划 */
        String SAVE_BATCH_DPS = "SaveBatchDps";
        /** 更新合同状态为待审核，使合同相关信息可以再次修改 */
        String ENABLE_TO_MODIFY = "EnableToModify";
        /** 将差异单状态更新为已确认 */
        String CONFIRM_DIFFER_BASIC_SERVER_NAME = "ConfirmDifferBasic";
        /** 查询资金池列表 */
        String SEARCH_PAYMENT_SERVER_NAME = "SearchPayment";
        /** 查询核销单列表 */
        String SEARCH_CONTRACT_VERIFICATION_SERVER_NAME = "SearchContractVerification";
        /** 查询采供链资金池详细 */
        String FIND_SSC_CASH_POO_LING_DETAIL_SERVER_NAME = "FindSscCashPoolingDetail";
        /** 查询采供链付款申请一览 */
        String FIND_SSC_PAYMENT_REQUEST_SERVER_NAME = "FindSscPaymentRequest";
        /** 查询支付申请列表 */
        String FIND_PAYMENT_REQUEST_SERVER_NAME = "FindPaymentRequest";
        /** 新增/编辑支付申请 */
        String SAVE_OR_MODIFY_PAYMENT_REQUEST_SERVER_NAME = "SaveOrModifyPaymentRequest";
        /** 查询付款记录列表 */
        String FIND_PAYMENT_INFO_SERVER_NAME = "FindPaymentInfo";
        /** 新增/编辑支付记录 */
        String SAVE_OR_MODIFY_PAYMENT_INFO_SERVER_NAME = "SaveOrModifyPaymentInfo";
        /** 删除付款申请 */
        String DELETE_SSC_PAYMENT_REQUEST_SERVER_NAME = "DeleteSscPaymentRequest";
        /** 核销发货入库差异 */
        String CALC_DELY_INTO_DIFF_SERVER_NAME = "CalcDelyIntoDiff";
        /** 核销运费差异 */
        String CALC_TRANSP_EXP_DIFF_SERVER_NAME = "CalcTranspExpDiff";
        /** 查询发票申请数据列表 */
        String FIND_SSCINVOICE_REQUEST_LIST_SERVER_NAME = "FindSscinvoiceRequestList";
        /** 删除发票申请数据 */
        String MODIFY_INVOICE_REQUEST_SERVER_NAME = "ModifyInvoiceRequest";
        /** 通过合同创建新的发票申请单 */
        String CONTRACT_TO_NEW_INVOICE_REQUEST_DETAIL_SERVER_NAME = "ContractToNewInvoiceRequestDetail";
        /** 查找发票详细 */
        String FIND_INVOICE_REQUEST_DETAIL_SERVER_NAME = "FindInvoiceRequestDetail";
        /** 通过合同编号查询发票单是否存在 */
        String CONTRACT_FIND_INVOICE_REQUEST_DETAIL_EXIST_SERVER_NAME = "ContractFindInvoiceRequestDetailExist";
        /** 添加发票申请单 */
        String INSERT_INVOICE_REQUEST_SERVER_NAME = "InsertInvoiceRequest";
        /** 更新发票信息 */
        String MODIFY_INVOICE_REQUEST_UP_SERVER_NAME = "ModifyInvoiceRequestUp";
        /** 通过发票申请单号查询合同号与合同ID */
        String SEARCH_CONTRACT_FOR_INVOICE_SERVER_NAME = "SearchContractForInvoice";
        /** 保存发票申请上传的文件信息 */
        String SAVE_INVOICE_REQUEST_FILE_INFO_SERVER_NAME = "SaveInvoiceRequestFileInfo";
        /** 更新核销单审核状态 */
        String AUDIT_VERIFICATION_SERVER_NAME = "AuditVerification";
        /* 新增或更新核销单及其详情 */
        String SAVE_OR_UPDATE_VERIFICATION_SERVER_NAME = "SaveOrUpdateVerification";
        /* 查询核销单明细 */
        String FIND_VERIFICATION_DETAILS_SERVER_NAME = "FindVerificationDetails";
        /** 查询合同基本信息、生效日及交货期中的最后交货日 */
        String FIND_CONTRACT_PLANINFO_SERVER_NAME = "FindContractPlanInfo";
        /** 查询生产期/待运期产品管控 */
        String FIND_PRODUCE_PD_CONTROL_SERVER_NAME = "FindProducePdControl";
        /** 查询入库产品管控 */
        String FIND_STOCK_PRODUCT_DETAIL_SERVER_NAME = "FindStockProductDetail";
        /** 批量保存或更新生产期/待运期产品管控 */
        String BATCH_SAVE_OR_UPDATE_PRODUCE_PLAN_SERVER_NAME = "BatchSaveOrUpdateProducePlan";
        /** 批量保存或更新运输期产品监控 */
        String BATCH_SAVE_OR_UPDATE_PD_CONTROL_SERVER_NAME = "BatchSaveOrUpdatePdControl";
        /* 查询运输期产品管控 */
        String FIND_DELIVERY_PD_CONTROL_SERVER_NAME = "FindDeliveryPdControl";
        /** 查询首付款按比例已支付金额 */
        String FIND_DELIVERY_PD_LIST_SERVER_NAME = "FindDeliveryPDList";
        /** 查询发货确认单产品修改历史 */
        String FIND_PRODUCT_HISTORIES_SERVER_NAME = "FindProductHistories";
        /** 查询未关联合同的中标产品信息 */
        String FIND_NO_RELATED_BID_BASE_SERVER_NAME = "FindNoRelatedBidBase";
        /** 根据中标编号，查询中标数据是否存在 */
        String CHECK_BID_BASE_EXIST_SERVER_NAME = "CheckBidBaseExist";
        /** 查询发货订单关联的预入库单基本信息 */
        String FIND_PREINTO_LIST_BYDE_LIVERYID_SERVER_NAME = "FindPreIntoListByDeliveryId";
        /** 关闭合同和核销单 */
        String CLOSE_CONTRACT_SERVER_NAME = "CloseContract";
        /** 删除核销单 */
        String DELETE_VERIFICATION_SERVER_NAME = "DeleteVerification";

    }

    interface BuyersReport {
        /** 模块名称 */
        String MODULE_NAME = "BR";
        /** 取得买家买家池已购产品 */
        String FIND_ORDER_INFO_PRODUCT_CATALOG_SERVER_NAME = "FindOrderInfoProductCatalog";
        /** 取得买家买家池归属 */
        String FIND_POOL_ATTRIBUTION_SERVER_NAME = "FindPoolAttribution";
        /** 取得买家订单汇总信息 */
        String FIND_ORDER_INFO_LIST_SERVER_NAME = "FindOrderInfoList";
        /** 买家标准产品池列表查询接口 */
        String FIND_BR_BY_POOL_FILE_INFO_SERVER_NAME = "FindBrByPoolFileInfo";
        /** 买家产品池配置管理列表查询接口 */
        String FIND_BR_SETTING_SERVER_NAME = "FindBrSetting";
        /** 买家产品池配置管理列表编辑数据接口 */
        String MODIFY_SETTING_SERVER_NAME = "ModifySetting";
        /** 买家产品池配置管理列表删除数据接口 */
        String DELETE_SETTING_DATE_SERVER_NAME = "DeleteSettingDate";
        /** 买家产品池配置管理列表新增数据接口 */
        String INSERT_DEMAND_PUBLISH_DETAIL_SERVER_NAME = "InsertDemandPublishDetail";
        /** 单一买家池列表查询接口 */
        String FIND_BRO_BUYERINFO_LIST_SERVER_NAME = "FindBrOBuyerInfoList";
        /** 单一买家标准产品池列表查询接口 */
        String FIND_BR_SINGLE_BY_FILE_INFO_LIST_SERVER_NAME = "FindBrSingleByFileInfoList";
        /** 生成单一买家标准产品池在线管控表文件接口 */
        String FIND_DATA_RESOLVE_SERVER_NAME = "FindDataResolve";
        /** 删除冻品管家组管家信息 */
        String DELETE_HK_LIST_IN_HK_GROUP = "DeleteHkListInHkGroup";
        /** 查询冻品管家组管家信息 */
        String QUERY_HK_LIST_IN_HK_GROUP = "QueryHkListInHkGroup";
        /** 查询冻品管家组信息 */
        String QUERY_HK_GROUP_INFO = "QueryHkGroupInfo";
        /** 批量添加冻品管家组的冻品管家 */
        String UPDATE_HK_GROUP_INFOS = "UpdateHkGroupInfos";
        /** 编辑冻品管家组名称 */
        String UPDATE_HK_GROUP_NAME = "UpdateHkGroupName";
        /** 同步订单数据 */
        String UPDATE_SYNCHRONIZED_ORDER_DATA_SERVER_NAME = "UpdateSynchronizedOrderData";
        /** 同步买家数据 */
        String UPDATE_SYNCHRONIZED_BUYER_DATA_SERVER_NAME = "UpdateSynchronizedBuyerData";
        /** 同步卖家产品数据 */
        String UPDATE_SYNCHRONIZED_SL_PRODUCT_SERVER_NAME = "UpdateSynchronizedSlProduct";
        /** 同步产品数据 */
        String UPDATE_SYNCHRONIZED_PRODUCT_SERVER_NAME = "UpdateSynchronizedProduct";
        /** 买家产品分类查询接口 */
        String FIND_MACHINING_CODE_U_SERVER_NAME = "FindMachiningCodeU";
        /** 根据管家所属期查询管家组信息 */
        String QUERY_HK_GROUP_FOR_HK_INFO = "QueryHkGroupForHkInfo";
        /** 分类买家标准产品池管控列表查询 */
        String QUERY_FILE_BUYER_POOLS_SERVER_NAME = "QueryFileBuyerPools";
        /** 分类买家标准产品池管控表生成 */
        String GENERATE_AND_UPLOAD_EXCEL_FILES_SERVER_NAME = "GenerateAndUploadExcelFiles";
        /** 分销分类买家池生成并上传上线状态excel文件 */
        String GENERATE_AND_UPLOAD_ONLINE_EXCEL_FILES_SERVER_NAME = "GenerateAndUploadOnlineExcelFiles";
        /** 获取分销分类买家池文件信息 */
        String BY_REPORT_SEARCH_SERVER_NAME = "BrReportSearch";
        /** 分销分类买家池生成excel文件 */
        String BUYER_POOL_FILE_CREATE_SERVER_NAME = "BuyerPoolFileCreate";
        /** 根据批发市场等级获取批发市场信息 */
        String FIND_MARKET_NAME_LIST_BY_LEVEL_SERVER_NAME = "FindMarketNameListByLevel";
        /** 生成营销期报表接口 */
        String CREATE_MARKETING_PERIOD_EXCEL_SERVER_NAME = "CreateMarketingPeriodExcel";
        /** 生成销售期报表接口 */
        String CREATE_SALES_PERIOD_EXCEL_SERVER_NAME = "CreateSalesPeriodExcel";
        /***/
        String SEARCH_PUBLI_BUYER_POOL_INFORMATION_SERVER_NAME = "SearchPubliBuyerPoolInformation";
        /** 专属会员报表生成接口 */
        String EXCLUSIVE_EXCEL_FILES_SAVE_SERVER_NAME = "ExclusiveExcelFilesSave";
        /** 查询冻品管家销售买家池补全接口 */
        String GET_SALE_INFO_BY_BUYER_ID_SERVER_NAME = "GetSaleInfoByBuyerId";
        /** 根据买家ID获取所属买家池 */
        String QUERY_BR_BUYER_POOL_BY_BUYER_ID_SERVER_NAME = "QueryBrBuyerPoolByBuyerId";
        /** 新增冻品管家组信息接口 */
        String ADD_HK_GROUP_SERVER_NAME = "AddHkGroup";
        /** 获取同步上线状态买家列表 */
        String SYN_MARKETING_STATUS_BY_ORDER_SERVER_NAME = "SynMarketingStatusByOrder";
        /** 更新营销期和销售期买家买家池关系接口 */
        String UPDATE_MARKETING_PERIOD_SERVER_NAME = "UpdateMarketingPeriod";
        /** 根据订单获取买家所属买家池 */
        String SYN_BUYER_PD_CLASSES_SERVER_NAME = "SynBuyerPdClasses";
        /** 根据管家查询所属管家组信息接口 */
        String QUERY_HOUSE_KEEPER_OF_HK_GROUP_SERVER_NAME = "QueryHouseKeeperOfHkGroup";
        /** 更新买家上线状态履历表 */
        String UPDATE_BUYER_MARKETING_STATUS_HISTORY_SERVER_NAME = "UpdateBuyerMarketingStatusHistory";
        /** 更新买家池产品分类基础表 */
        String UPDATE_M_PD_CLASSES_SERVER_NAME = "UpdateMPdClasses";
        /** 查询买家池产品分类基础表 */
        String SELECT_M_PD_CLASSES_SERVER_NAME = "SelectMPdClasses";
        /** 通过BuyerId批量获取所属买家池名称 */
        String QUERY_POOLS_BY_BUYER_IDS_SERVER_NAME = "QueryPoolsByBuyerIds";
        /**建立管家和买家关系接口*/
        String UPDATE_BINDING_RELATIONSHIP_SERVER_NAME = "UpdateBindingRelationship";
        /**解除管家和买家关系接口*/
        String UPDATE_UN_BINDING_RELATIONSHIP_SERVER_NAME = "UpdateUnBindingRelationship";
        /**更新买家池买家基本信息*/
        String UPDATE_BUYER_REPORT_INFO_SERVER_NAME = "UpdateBuyerReportInfo";
    }

    /*
     * Sso 模块提供的服务接口名称
     */
    interface SsoServer {
        /** 模块名称 */
        String MODULE_NAME = "SSO";
        String CAS_SERVER_LOGOUT_URL = "CasServerLogoutUrl";
        String CAS_SERVER_LOGIN_URL = "CasServerLoginUrl";
        String CAS_SERVER_URL_PREFIX_URL = "CasServerUrlPrefix";
    }

    /*
     * PD 模块提供的服务接口名称
     */
    interface PdServer {
        /** 模块名称 */
        String MODULE_NAME = "PD";
        /** 产品等级一览查询接口 */
        String FIND_LIST_GRADES_SERVER_NAME = "FindListGrades";
        /***/
        String FIND_PRODUCT_INFO = "FindProductInfo";
        /***/
        String Find_Product_Package = "FindProductPackage";
        /***/
        String GET_PD_CLASSES_TREE_INFO = "GetPdClassesTreeInfo";
        /***/
        String GET_PD_SUPP = "GetPDSupp";
        /***/
        String GET_PRICEPRD_LOGIAREA = "GetPriceprdLogiarea";
        /***/
        String PD_BATCH_NAME = "PdBatchName";
        /***/
        String PD_PRODUCT_STD = "PdProductStd";
        /***/
        String PD_TYPE_NAME = "PdTypeName";
        /***/
        String FIND_ALL_PD_CODE = "FindAllPdCode";
        /***/
        String Find_Pd_Breed = "FindPdBreed";
        /***/
        String FIND_PD_CLASSES = "FindPdClasses";
        /***/
        String FIND_PD_FEATURE = "FindPdFeature";
        /***/
        String FIND_PD_MACHINING = "FindPdMachining";
        /***/
        String FIND_PD_NORMS_STD = "FindPdNormsStd";
        /***/
        String FIND_PD_WEIGHT = "FindPdWeight";
        /***/
        String FIND_PRODUCT_INFOS = "FindProductInfos";
        /***/
        String FIND_PRODUCT_STANDARD = "FindProductStandard";
        /***/
        String FIND_PROVIDER_LIST = "FindProviderList";
        /***/
        String GET_PD_CLASSES_TREE_MAT_INFO = "GetPdClassesTreeMatInfo";
        /***/
        String GET_PROVIDER_PACKAGE_INFO = "GetProviderPackageInfo";
        /***/
        String SAVE_MCT_PROVIDER = "SaveMctProvider";
        /***/
        String SAVE_PROVIDER_PACKAGE = "SaveProviderPackage";
        /***/
        String SAVE_TNC_STD_DISCUSS_PROVIDER = "SaveTncStdDiscussProvider";
        /** 产品运营状态查询 */
        String SEARCH_PRO_YY_STATUS = "SearchProYyStatus";
        /** 根据产品编码查询档案卡 */
        String GET_PD_STANDARD_BY_CODES = "GetPdStandardByCodes";
        /** 查询所有产品分类，例如鸡产品，鸭产品 */
        String FIND_CLASSES_SERVER_NAME = "FindClasses";
        /** 查询产品的定义等级和编码，例如A1，A2，A3 */
        String FIND_LIST_GRADE_SERVER_NAME = "FindListGrade";
        /** 查询产品的销售状态定义一览，例如规划，研发，试销 */
        String FIND_LIST_SALE_STATUS_SERVER_NAME = "FindListSaleStatus";
        /** 查询产品加工程度名称和编码 */
        String FIND_MACHINING_SERVER_NAME = "FindMachining";
        /** 查询产品名称和主码，按照有无编码和有无生效日期来区分返回的数据量 */
        String FIND_STANDARD_SERVER_NAME = "FindStandard";
        /** 查询指定产品品种的标准技术档案卡信息 */
        String FIND_PD_QLT_STD_SERVER_NAME = "FindPdQltStd";
        /** 把卖家定义的包装标准添加到标准产品包装档案卡里 */
        String CREATE_PD_NORMS_STD_SERVER_NAME = "CreatePdNormsStd";
        /** 1产品分类 */
        String FIND_PD_CLASSES_AND_LIST_SERVER_NAME = "FindPdClassesAndList";
        /** 根据制定的物流区编码查询此物流区下的所有产品信息（待定义,供分销管理系统使用） */
        String QUERY_BID_LOGI_AREA_SERVER_NAME = "QueryBidLogiArea";
        /** 3产品信息 */
        String FIND_PD_INFORMATION_SERVER_NAME = "FindPdInformation";
        /** 查询产品的加工技术档案卡信息 */
        String FIND_LIST_MCT_SERVER_NAME = "FindListMct";
        /** 查询产品的原料种源信息 */
        String SELECT_MAT_SOURCE_SERVER_NAME = "SelectMatSource";
        /** 查询产品的原种种源档案卡信息信息 */
        String SELECT_ORG_SOURCE_SERVER_NAME = "SelectOrgSource";
        /** 查询产品的饲养指标档案卡信息 */
        String SELECT_FED_SOURCE_SERVER_NAME = "SelectFedSource";
        /** 查询产品的通用质量指标档案卡信息 */
        String SELECT_GNQ_SOURCE_SERVER_NAME = "SelectGnqSource";
        /** 查询产品的储存运输指标档案卡信息 */
        String SELECT_TSP_SOURCE_SERVER_NAME = "SelectTspSource";
        /** 查询产品的安全卫生指标档案卡信息 */
        String FIND_PD_GRADE_HEALTH_SERVER_NAME = "FindPdGradeHealth";
        /** 查询产品品种名称和编码 */
        String FIND_BREED_LIST_SERVER_NAME = "FindBreedList";
        /** 提供神农客电商平台产品档案卡同步接口 */
        String FIND_ORD_LIST_SERVER_NAME = "FindOrdList";
        /** 提供神农客电商平台产品档案卡同步接口 */
        String FIND_FED_LIST_SERVER_NAME = "FindFedList";
        /** 提供神农客电商平台产品档案卡同步接口 */
        String FIND_PD_MCT_LIST_SERVER_NAME = "FindPdMctList";
        /** 提供神农客电商平台产品档案卡同步接口 */
        String FIND_TNC_LIST_SERVER_NAME = "FindTncList";
        /** 提供神农客电商平台产品档案卡同步接口 */
        String FIND_GNQ_LIST_SERVER_NAME = "FindGnqList";
        /** 提供神农客电商平台产品档案卡同步接口 */
        String FIND_SFT_LIST_SERVER_NAME = "FindSftList";
        /** 提供神农客电商平台产品档案卡同步接口 */
        String FIND_TSP_LIST_SERVER_NAME = "FindTspList";
        /** 查询产品包装名称和编码，详细信息 */
        String FIND_PACKAGE_LIST_SERVER_NAME = "FindPackageList";
        /** 提供美侍客电商平台产品同步接口 */
        String FIND_MAT_LIST_SERVER_NAME = "FindMatList";
        /** 根据制定的物流区编码查询此物流区下的所有包含等级的产品信息 */
        String QUERY_PD_GRADE_BID_LOGI_AREA_SERVER_NAME = "QueryPdGradeBidLogiArea";
        /** 提供美侍客电商平台StandardID产品同步接口 */
        String QUERY_PD_STANDARD_LIST_SERVER_NAME = "QueryPdStandardList";
        /** 提供神农客电商平台价盘等级通道同步接口 */
        String FIND_PD_PRICE_WAY_SERVER_NAME = "FindPdPriceWay";
        /** 提供卖家产品库存查询接口 */
        String FIND_PD_STOCK_SERVER_NAME = "FindPdStock";
        /** 提供卖家申请产品审核状态查询 */
        String FIND_SL_PD_AUDIT_STATUS_SERVER_NAME = "FindSlPdAuditStatus";
        /** 产品对应生产商信息查询 */
        String FIND_PD_MANUFACTURE_INFO_SERVER_NAME = "FindPdManufactureInfo";
        /** 提供美迪福系统数据同步用的接口，返回产品包装信息 */
        String FIND_PRODUCT_NORMS_STD_SERVER_NAME = "FindProductNormsStd";
        /** 查询产品特征名称和编码 */
        String FIND_PRODUCT_FEATURE_SERVER_NAME = "FindProductFeature";
        /** 查询产品净重名称和编码 */
        String FIND_PRODUCT_WEIGHT_SERVER_NAME = "FindProductWeight";
        /** 举报类型查询 */
        String SEARCH_REPORT_TYPE_SERVER_NAME = "SearchReportType";
        /** 新增修改举报 */
        String INSERT_REPORT_INFO_SERVER_NAME = "InsertReportInfo";
        /** 举报一览查询 */
        String SEARCH_REPORT_LIST_SERVER_NAME = "SearchReportList";
        /***/
        String FIND_PRODUCT_CLASSES_SERVER_NAME = "FindProductClasses";
        /***/
        String FIND_PRODUCT_GRADE_SERVER_NAME = "FindProductGrade";
        /***/
        String FIND_PRODUCT_MACHINING_SERVER_NAME = "FindProductMachining";
        /***/
        String FIND_PRODUCT_BREED_SERVER_NAME = "FindProductBreed";

    }

    interface CommonServer {
        /** 模块名称 */
        String MODULE_NAME = "COMM";
        /***/
        String FTP_IP = "FtpIp";
        /***/
        String FTP_USER = "FtpUser";
        /***/
        String FTP_PWD = "FtpPwd";
        /***/
        String FTP_IMAGE_ROOT_PATH = "FtpImageRootPath";
        /***/
        String WMS_FTP_IP = "WmsFtpIp";
        /***/
        String WMS_FTP_USER = "WmsFtpUser";
        /***/
        String WMS_FTP_PWD = "WmsFtpPwd";

        String FILE_SERVER_UPLOAD = "FlieServerUpload";

        String FILE_SERVER = "FlieServer";

        String FILE_UPLOAD_SERVICES = "FlieUploadServices";

        String MSK_FILE_DOWNLOAD_SERVICES = "MskFlieDownLoadServers";

        String SELLER_SERVER = "SellerServer";
        /** 文件服务器 */
        String GET_FLIE_SERVER_UPLOAD_FOR_JSP_SERVER_NAME = "FlieServerUploadForJsp";
        /** 内网 */
        String MSK_FLIE_DOWN_LOAD_SERVER_NAME = "MskFlieDownLoad";

        String FILE_SERVER_DOWNLOAD_PROXY_SERVER_NAME = "FileServerDownloadProxy";
        /***/
        String IS_FORCE_MODIFY_PASSWORD_SERVER_NAME = "IsForceModifyPassword";
        /***/
        String IS_CHECK_PASSWORD_TIME_SERIES_SERVER_NAME = "IsCheckPasswordTimeSeries";
        /***/
        String IS_INIT_PASSWORD_SERVER_NAME = "IsInitPassword";

        String MAIL_HTML_SEND_SERVER_NAME = "mailHtmlSend";

        String MAILSIMPLESEND_SERVER_NAME = "mailSimpleSend";

        String MAIL_TEMPLATE_SEND_SERVER_NAME = "mailtemplateSend";
    }

    interface SoOrderV1 {
        String MODULE_NAME = "V1-SO-ORDER";
        /** 查询供应商列表 */
        /* String FIND_SELLERS_SERVER_NAME = "FindSellers"; */
        /** 验证退货单号 */
        /* String CHECK_RETURN_CODE_SERVER_NAME = "CheckReturnCode"; */
        /** 查询冻品管家旗下买家月度销售额接口 */
        String SEARCH_SALES_SERVER_NAME = "SearchSales";
        /** 创建买手囤货订单 */
        String CREATE_BUYER_SDO_SERVER_NAME = "CreateBuyerSdo";
        /** 管家下委托订单 */
        String CREATE_DISTRIBUTION_ORDER_SERVER_NAME = "CreateDistributionOrder";
        /** 查询委托订单 */
        String QUERY_SDO_DETAIL_SERVER_NAME = "QuerySdoDetail";
        /** 买手囤货订单列表接口 */
        String QUERY_BSSG_SDO_LIST_SERVER_NAME = "QueryBssgSdoList";
        /** 买手囤货订单明细接口 */
        String QUERY_BSSG_SDO_DETAI_SERVER_NAME = "QueryBssgSdoDetai";
        /** 买手销售订单列表接口 */
        String QUERY_BSS_SDO_LIST_SERVER_NAME = "QueryBssSdoList";
        /** 买手销售订单明细接口 */
        String QUERY_BSS_SDO_DETAIL_SERVER_NAME = "QueryBssSdoDetail";
        /** 收款方查询接口 */
        String QUERY_RECEIPT_INFO_SERVER_NAME = "QueryReceiptInfo";
        /** 验证退货单号 */
        String CHECK_RETURN_CODE_SERVER_NAME = "CheckReturnCode";
        /** 查询冻品管家旗下买家月度销售额 */
        String HOUSE_ACCOUNT_SALES_SEARCH_SERVER_NAME = "HouseAccountSalesSearch";
        /** 买家平台下单数量统计 */
        String QUERY_ORDER_SOURCE_COUNT_SERVER_NAME = "QueryOrderSourceCount";
        /** 产品库存查询接口 */
        String FIND_PD_STOCK_SERVER_NAME = "FindPdStock";
        /** 获取本月上半月分销量 */
        String QUERY_ORDER_HALF_MONTH_COUNT_SERVER_NAME = "QueryOrderHalfMonthCount";
        /**买家订单明细接口*/
        String QUERY_BY_SDO_DETAIL_SERVER_NAME = "QueryBySdoDetail";
    }

    /*
     * 缓存
     */
    interface Cache {
        String MODULE_NAME = "CACHE";
        /***/
        String PUT_CONFIG_CONST_CACHE_SERVER_NAME = "PutConfigConstCache";
        /***/
        String GET_CONFIG_CONST_CACHE_SERVER_NAME = "GetConfigConstCache";
        /***/
        String REMOVE_CONFIG_CONST_CACHE_VALUE_SERVER_NAME = "RemoveConfigConstCacheValue";
        /***/
        String REMOVE_ALL_CONFIG_CONST_CACHE_SERVER_NAME = "RemoveAllConfigConstCache";
    }

    /*
     * Mq
     */
    interface Mq {
        String MODULE_NAME = "MQ-SERVER";
        /***/
        String SEND_MQ_MESSAGE = "SendMqMessage";
    }

    /*
     * Mq
     */
    interface MqQueues {
        String MODULE_NAME = "MQ-QUEUES";
        /***/
        String ORDER_CREATE_QUEUE = "OrderCreateQueue";
        /***/
        String ORDER_PAYMENT_QUEUE = "OrderPaymentQueue";
        /***/
        String ORDER_DELIVERY_QUEUE = "OrderDeliveryQueue";
    }

    /*
     * print server
     */
    interface PrintServer {
        String MODULE_NAME = "MSK-PRINT";
        /** 打印pdf */
        String PRINT_PDF = "PrintPdf";

        String NETWORK_ADDRESS = "NetworkAddress";

        String ASYNC_GENERATE_SINGLE_PDF = "AsyncGenerateSinglePDF";

        /** 异步生成excel接口 */
        String ASYNC_GENERATE_SINGLE_EXCEL_SERVER_NAME = "AsyncGenerateSingleExcel";
    }

    /*
     * mskMail
     */
    interface mskMail {
        String MODULE_NAME = "MSK-MAIL";
        /***/
        String SEND_TEXT_MAIL = "SendTextMail";
    }

    /*
     * so-inventory
     */
    interface SoInventoryServer {
        String MODULE_NAME = "SO-INVENTORY";
        /** 根据pdTypeCode查询卖家库存 */
        String GET_STOCK_LIST_BY_PD_CODE_SERVER_NAME = "GetStockListByPdCode";
        /** 库存产品入库 */
        String INBOUND_INVENTORY_SERVER_NAME = "InboundInventory";
        /** 卖家库存订单占用 */
        String ALLOCATE_SL_INVENTORY_SERVER_NAME = "AllocateSlInventory";
        /** 供应商库存订单占用 */
        String ALLOCATE_OWNER_INVENTORY_SERVER_NAME = "AllocateOwnerInventory";
        /** 卖家库存订单占用减少 */
        String UNDO_ALLOCATE_SL_INVENTORY_SERVER_NAME = "UndoAllocateSlInventory";
        /** 供应商库存占用减少 */
        String UNDO_ALLOCATE_OWNER_INVENTORY_SERVER_NAME = "UndoAllocateOwnerInventory";
        /** 库存产品出库 */
        String OUTBOUND_INVENTORY_SERVER_NAME = "OutboundInventory";
        /** 产品库存查询(原名卖家库存查询) */
        String FIND_PRODUCT_STOCK_SERVER_NAME = "FindProductStock";
        /** 卖家产品库存查询列表 */
        String FIND_SL_PRODUCT_IV_LIST_SERVER_NAME = "FindSlProductIvList";
        /** 供应商产品库存查询列表 */
        String FIND_SP_PRODUCT_IV_LIST_SERVER_NAME = "FindSpProductIvList";
        /** 卖家商品管理查询接口 */
        String FIND_SL_PRODUCT_LIST_SERVER_NAME = "FindSlProductList";
        /** 买手囤货库存更新接口 */
        String ASSIGN_INVENTORY_FOR_SL_SERVER_NAME = "AssignInventoryForSl";
        /** 仓库列表接口 */
        String GET_WAREHOUSE_LIST_SERVER_NAME = "GetWarehouseList";
        /** 查询所有有过库存的供应商列表 */
        String OWNERS_IN_HISTORY_SERVER_NAME = "OwnersInHistory";
        /** 库存产品发货取消 */
        String UNDO_DISPATCH_SERVER_NAME = "UndoDispatch";
        /** 分销正常库存管理页面接口 */
        String GET_DISTRIBUTION_LIST_SERVER_NAME = "GetDistributionList";
        /** 卖家库存管理页面接口 */
        String GET_SELLER_INVENTORY_LIST_SERVER_NAME = "GetSellerInventoryList";
        /** 产品库存查询(多品查询) */
        String FIND_PRODUCTS_INVENTORY_SERVER_NAME = "FindProductsInventory";

    }

    /*
     * so-order-api
     */
    interface SoOrderApiServer {
        String MODULE_NAME = "ORDER-API";
        /** 买家在下订单之前创建的订单我们称之为需求订单，需求订单不是真正的订单，它只是买家意愿订单 */
        String CREATE_SO_PRO_SERVER_NAME = "CreateSoPro";
        /** 出力详细信息报表 */
        String QUERY_SO_DETAIL_PRINT_INFO_SERVER_NAME = "QuerySoDetailPrintInfo";
        /** 收款方查询接口 */
        String QUERY_RECEIPT_INFO_SERVER_NAME = "QueryReceiptInfo";
        /** 产品销量查询接口 */
        String QUERY_PD_SALES_VOLUMN_LIST_SERVER_NAME = "QueryPdSalesVolumnList";
        /** 买家平台下单数量统计 */
        String QUERY_ORDER_SOURCE_COUNT_SERVER_NAME = "QueryOrderSourceCount";
        /** 客户前台网站申请退货 */
        String CREATE_SO_RETURN_INVOICE_SERVER_NAME = "CreateSoReturnInvoice";
        /** 根据参数中存在的条件查询退货单并返回结果列表。 */
        String QUERY_SO_RETURN_INVOICE_SERVER_NAME = "QuerySoReturnInvoice";
        /** 订单状态在没有发货的情况下可以进行整单取消 */
        String CANCEL_TOTAL_SO_ORDER_SERVER_NAME = "CancelTotalSoOrder";
        /** 删除或恢复指定的订单 */
        String DELETE_SO_ORDER_SERVER_NAME = "DeleteSoOrder";
        /** 线上付款，付款成功调用 */
        String PAY_SO_ORDER_SERVER_NAME = "PaySoOrder";
        /** 根据买家选购产品信息、买家信息、配送信息、发票要求，创建标准分销订单或者买手销售订单。 */
        String CREATE_DISTRIBUTE_SDO_SERVER_NAME = "CreateDistributeSdo";
        /** 根据买手选购产品信息、买家信息、配送信息、发票要求，创建买手标准囤货订单。 */
        String CREATE_BUYER_SDO_SERVER_NAME = "CreateBuyerSdo";
        /** 根据买手选购产品信息、买家信息、配送信息、发票要求，创建第三方标准订单或者第三方买手销售订单。 */
        String CREATE_THIRD_PARTY_SDO_SERVER_NAME = "CreateThirdPartySdo";
        /** 根据买手选购产品信息、买家信息、配送信息、发票要求，创建第三方标准买手囤货订单 */
        String CREATE_THIRD_BUYER_SDO_SERVER_NAME = "CreateThirdBuyerSdo";
        /** WMS系统将实际发货及配送信息返回OMS系统， */
        String CELIVER_SO_ORDER_SERVER_NAME = "CeliverSoOrder";
        /** OMS中可接收收货信息 */
        String RECEIPT_SO_ORDER_SERVER_NAME = "ReceiptSoOrder";
        /** 订单列表查询接口 */
        String QUERY_SDO_LIST_SERVER_NAME = "QuerySdoList";
        /** 查询买家订单 */
        String QUERY_BY_SDO_LIST_SERVER_NAME = "QueryBySdoList";
        /** 查询卖家订单 */
        String QUERY_SL_SDO_LIST_SERVER_NAME = "QuerySlSdoList";
        /** 查询买手销售订单 */
        String QUERY_BSS_SDO_LIST_SERVER_NAME = "QueryBssSdoList";
        /** 查询囤货订单 */
        String QUERY_BSSG_SDO_LIST_SERVER_NAME = "QueryBssgSdoList";
        /** 订单明细查询 */
        String QUERY_SDO_DETAIL_SERVER_NAME = "QuerySdoDetail";
        /** 买家订单明细 */
        String QUERY_BY_SDO_DETAIL_SERVER_NAME = "QueryBySdoDetail";
        /** 卖家订单明细 */
        String QUERY_SL_SDO_DETAIL_SERVER_NAME = "QuerySlSdoDetail";
        /** 买手销售订单明细 */
        String QUERY_BSS_SDO_DETAIL_SERVER_NAME = "QueryBssSdoDetail";
        /** 买手囤货订单明细 */
        String QUERY_BSSG_SDO_DETAIL_SERVER_NAME = "QueryBssgSdoDetail";
        /** 卖家已卖出商品查询 */
        String QUERY_SO_SL_PRODUCT_LIST_SERVER_NAME = "QuerySoSlProductList";
        /** 接收WMS系统处理完退货后，返回的入库信息接口，处理完成后，系统内库存增加 */
        String QO_ORDER_INBOUND_SERVER_NAME = "QoOrderInbound";
        /** 卖家快捷信息查询 */
        String QUERY_SELLER_INFO_SERVER_NAME = "QuerySellerInfo";
        /** 买手快捷信息查询 */
        String QUERY_BUYER_INFO_SERVER_NAME = "QueryBuyerInfo";
        /** 管家快捷信息查询 */
        String QUERY_HOUSE_KEEPING_INFO_SERVER_NAME = "QueryHouseKeepingInfo";
        /** 管家订单查询接口 */
        String QUERY_HOUSE_KEEPING_DETAIL_SERVER_NAME = "QueryHouseKeepingDetail";
        /** 提供结算详情 */
        String QUERY_SDO_SETTLEMENT_DETAIL_SERVER_NAME = "QuerySdoSettlementDetail";
        /** 提供购买查询 */
        String QUERY_SDO_BUY_RECORD_SERVER_NAME = "QuerySdoBuyRecord";
        /** WMS中将发货单取消，将取消信息通知OMS系统 */
        String CANCEL_SDO_SHIPMENT_SERVER_NAME = "CancelSdoShipment";
        /** 获取本月上半月分销量（价盘调用） */
        String QUERY_ORDER_HALF_MONTH_COUNT_SERVER_NAME = "QueryOrderHalfMonthCount";
        /** 获取订单相关信息（资金池调用） */
        String GET_SO_ORDER_INFO_SERVER_NAME = "GetSoOrderInfo";
        /** 接收司机PDA系统的迟收退货信息，OMS处理后可再通知WMS发货 */
        String RECEIVE_SO_ORDER_LATER_SERVER_NAME = "ReceiveSoOrderLater";
        /** 接收司机PDA系统的现场拒收退货信息 */
        String RECEIVE_SO_ORDER_REJECT_SERVER_NAME = "ReceiveSoOrderReject";
        /** 提供订单发货明细信息查询 */
        String QUERY_SDO_SHIP_DETAIL_SERVER_NAME = "QuerySdoShipDetail";
        /** 退货原因查询接口,分Json版和Xml版 */
        String QUERY_RETURN_REASON_SERVER_NAME = "QueryReturnReason";
        /** 订单列表查询接口 */
        String FIND_PAGE_ORDER_LIST_SERVER_NAME = "FindPageOrderList";
        /** 订单查询详情接口 */
        String FIND_PAGE_ORDER_DETAIL_SERVER_NAME = "FindPageOrderDetail";
        /** 退货单列表查询接口 */
        String FIND_PAGE_RETURN_ORDER_LIST_SERVER_NAME = "FindPageReturnOrderList";
        /** 退货单明细列表查询接口 */
        String FIND_PAGE_RETURN_ORDER_DETAIL_SERVER_NAME = "FindPageReturnOrderDetail";
        /** 退货单基本信息查询接口 */
        String FIND_PAGE_BASE_RETURN_SERVER_NAME = "FindPageBaseReturn";
        /** 查询订单明细列表及买家基本信息接口 */
        String FIND_BY_BASIC_AND_ORDER_DETAIL_SERVER_NAME = "FindByBasicAndOrderDetail";
        /** 查询物流区信息 */
        String QUERY_SELLER_PRODUCT_INFO_SERVER_NAME = "QuerySellerProductInfo";
        /** 卖家库存接口 */
        String QUERY_SUPPLIER_PRODUCT_INFO_SERVER_NAME = "QuerySupplierProductInfo";
        /** 供应商库存接口 */
        String QUERY_DELIVER_INFO_SERVER_NAME = "QueryDeliverInfo";
        /** 发货单查询接口 */
        String CANCEL_ORDER_DELIVER_SERVER_NAME = "CancelOrderDeliver";
        /** 订单明细导出excel接口 */
        String EXPORT_ORDER_DETAIL_SERVER_NAME = "ExportOrderDetail";

    }
}
