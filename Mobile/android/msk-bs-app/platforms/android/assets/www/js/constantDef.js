var ConstantDef = {
    getParam: function () {
        return eval('(' + Param + ')');
    },
    getVersion: function () {
        return version;
    },
    getRootUrl: function () {
        //return Environment.test;//测试环境
        return Environment.development;
        //return Environment.test;//上海测试环境
    },
    getOrderRootUrl: function () {
        return Environment.orderDevelopment;
        //return Environment.qiang;//测试环境
        //return Environment.shTest;//上海测试环境
    },
    getStockRootUrl: function () {
        return Environment.test;//上海测试环境
    },
    getCreateOrderServerUrl: function () {
        return ConstantDef.getRootUrl() + OrderServerDef.projectRootUrl + OrderServerDef.CreateOrderServerUrl;
    },
    /**分销订单**/
    getCreateDistributeOrderServerUrl: function () {
        return ConstantDef.getOrderRootUrl() + OrderServerDef.projectRootUrl + OrderServerDef.CreateDistributeOrderServerUrl;
    },
    /**买手囤货订单**/
    createSdoBuyerOrderServerUrl: function () {
        return ConstantDef.getOrderRootUrl() + OrderServerDef.projectRootUrl + OrderServerDef.CreateSoSdoOrderServerUrl;
    },
    /**买手登录*/
    getAcountLoginServerUrl: function () {
        return ConstantDef.getRootUrl() + BsServerDef.projectRootUrl + BsServerDef.AcountLoginServerUrl;
    },
    /**冻品管家登录*/
    getHouseAcountLoginServerUrl: function () {
        return ConstantDef.getRootUrl() + BsServerDef.projectRootUrl + BsServerDef.HouseAcountLoginServerUrl;
    },
    getFindOrderDetailServerUrl: function () {
        return ConstantDef.getRootUrl() + BsServerDef.projectRootUrl + BsServerDef.FindOrderDetailUrl;
    },
    getOrderDetailInfoByCarIDServerUrl: function () {
        return ConstantDef.getRootUrl() + BsServerDef.projectRootUrl + BsServerDef.getOrderDetailByCarIDUrl;
    },
    getOrderDetailByPdInfoUrl: function () {
        return ConstantDef.getRootUrl() + BsServerDef.projectRootUrl + BsServerDef.getOrderDetailByPdInfoUrl;
    },
    getUpdateOrderServerUrl: function () {
        return ConstantDef.getRootUrl() + BsServerDef.projectRootUrl + BsServerDef.UpdateOrderUrl;
    },
    getFindClassesServerUrl: function () {
        return ConstantDef.getRootUrl() + BsServerDef.projectRootUrl + BsServerDef.FindPdClasses;
    },
    getFindPdInfoServerUrl: function () {
        return ConstantDef.getRootUrl() + BsServerDef.projectRootUrl + BsServerDef.FindPdInfo;
    },
    getFindPriceWayServerUrl: function () {
        return ConstantDef.getRootUrl() + BsServerDef.projectRootUrl + BsServerDef.FindPriceWay;
    },
    getFindPdFeatureServerUrl: function () {
        return ConstantDef.getRootUrl() + BsServerDef.projectRootUrl + BsServerDef.FindPdFeature;
    },
    getFindPdWeightServerUrl: function () {
        return ConstantDef.getRootUrl() + BsServerDef.projectRootUrl + BsServerDef.FindPdWeight;
    },
    getFindPdGradeServerUrl: function () {
        return ConstantDef.getRootUrl() + BsServerDef.projectRootUrl + BsServerDef.FindPdGrade;
    },
    getFindAddCarServerUrl: function () {
        return ConstantDef.getRootUrl() + BsServerDef.projectRootUrl + BsServerDef.AddCar;
    },

    getFindBuyerByTelServerUrl: function () {
        return ConstantDef.getRootUrl() + ByServerDef.projectRootUrl + ByServerDef.FindBuyerByTelServerUrl;
    },
    getSearchSlCodeServerUrl: function () {
        return ConstantDef.getRootUrl() + BsServerDef.projectRootUrl + BsServerDef.SearchSlCodeServerUrl;
    },
    getFindHouseBookByRecbookIdServeUrl: function () {
        return ConstantDef.getRootUrl() + BsServerDef.projectRootUrl + BsServerDef.FindHouseBookByRecbookIdServeUrl;
    },
    getFindHouseBookServerUrl: function () {
        return ConstantDef.getRootUrl() + BsServerDef.projectRootUrl + BsServerDef.FindHouseBookServerUrl;
    },
    getDeteleHouseBookServerUrl: function () {
        return ConstantDef.getRootUrl() + BsServerDef.projectRootUrl + BsServerDef.DeteleHouseBookServerUrl;
    },
    getSearchHouseListUrl: function () {
        return ConstantDef.getRootUrl() + BsServerDef.projectRootUrl + BsServerDef.SearchHouseListUrl;
    },
    getSaveHouseInfoUrlUrl: function () {
        return ConstantDef.getRootUrl() + BsServerDef.projectRootUrl + BsServerDef.SaveHouseInfoUrl;
    },
    FindProvinceServerUrl: function () {
        return ConstantDef.getRootUrl() + DistrictDef.projectRootUrl + DistrictDef.FindProvinceServerUrl;
    },
    getFindDistrictCityServerUrl: function () {
        return ConstantDef.getRootUrl() + DistrictDef.projectRootUrl + DistrictDef.FindDistrictCityServerUrl;
    },
    getFindDistrictServerUrl: function () {
        return ConstantDef.getRootUrl() + DistrictDef.projectRootUrl + DistrictDef.FindDistrictServerUrl;
    },
    getFindLgcsAreaServerUrl: function () {
        return ConstantDef.getRootUrl() + DistrictDef.projectRootUrl + DistrictDef.FindLgcsAreaServerUrl;
    },
    getFindModifyHouseBookServerUrl: function () {
        return ConstantDef.getRootUrl() + BsServerDef.projectRootUrl + BsServerDef.ModifyHouseBookServerUrl;
    },
    getFindAddHouseBookServerUrl: function () {
        return ConstantDef.getRootUrl() + BsServerDef.projectRootUrl + BsServerDef.AddHouseBookServerUrl;
    },
    GetProvinceListUrl: function () {
        return ConstantDef.getRootUrl() + BsServerDef.projectRootUrl + BsServerDef.GetProvinceListUrl;
    },
    GetProvinceCityListUrl: function () {
        return ConstantDef.getRootUrl() + BsServerDef.projectRootUrl + BsServerDef.GetProvinceCityListUrl;
    },
    GetDistrictList: function () {
        return ConstantDef.getRootUrl() + BsServerDef.projectRootUrl + BsServerDef.GetDistrictList;
    },
    getFindSlAccountServerUrl: function () {
        return ConstantDef.getRootUrl() + BsServerDef.projectRootUrl + BsServerDef.FindSlAccountServerUrl;
    },
    getNewOrUpdateBsInfoServerUrl: function () {
        return ConstantDef.getRootUrl() + BsServerDef.projectRootUrl + BsServerDef.NewOrUpdateBsInfoServerUrl;
    },
    getFindHouseAccountServerUrl: function () {
        return ConstantDef.getRootUrl() + BsServerDef.projectRootUrl + BsServerDef.FindHouseAccountServerUrl;
    },
    getFindHouseTelServerUrl: function () {
        return ConstantDef.getRootUrl() + BsServerDef.projectRootUrl + BsServerDef.FindHouseTelServerUrl;
    },
    getNewHouseAccountServerUrl: function () {
        return ConstantDef.getRootUrl() + BsServerDef.projectRootUrl + BsServerDef.NewHouseAccountServerUrl;
    },
    getFindHouseTypeServerUrl: function () {
        return ConstantDef.getRootUrl() + BsServerDef.projectRootUrl + BsServerDef.FindHouseTypeServerUrl;
    },
    getFindBsTypeServerUrl: function () {
        return ConstantDef.getRootUrl() + BsServerDef.projectRootUrl + BsServerDef.FindBsTypeServerUrl;
    },
    getFindPdMaxStockQtyServerUrl: function () {
        return ConstantDef.getStockRootUrl() + StockServerDef.projectRootUrl + StockServerDef.FindPdStockQtyServerUrl;
    },
    findHouseCodeByBuyerId: function () {
        return ConstantDef.getRootUrl() + BsServerDef.projectRootUrl + BsServerDef.FindHouseCodeByBuyerId;
    },
    delHouseTrainingUrl: function () {
        return ConstantDef.getRootUrl() + BsServerDef.projectRootUrl + BsServerDef.DelHouseTraining;
    },
    modifyHouseTrainingUrl: function () {
        return ConstantDef.getRootUrl() + BsServerDef.projectRootUrl + BsServerDef.ModifyHouseTraining;
    },
    saveHouseTrainingUrl: function () {
        return ConstantDef.getRootUrl() + BsServerDef.projectRootUrl + BsServerDef.SaveHouseTraining;
    },
    delHouseEducationUrl: function () {
        return ConstantDef.getRootUrl() + BsServerDef.projectRootUrl + BsServerDef.DelHouseEducation;
    },
    modifyHouseEducationUrl: function () {
        return ConstantDef.getRootUrl() + BsServerDef.projectRootUrl + BsServerDef.ModifyHouseEducation;
    },
    saveHouseEducationUrl: function () {
        return ConstantDef.getRootUrl() + BsServerDef.projectRootUrl + BsServerDef.SaveHouseEducation;
    },
    delHouseWorkUrl: function () {
        return ConstantDef.getRootUrl() + BsServerDef.projectRootUrl + BsServerDef.DelHouseWork;
    },
    modifyHouseWorkUrl: function () {
        return ConstantDef.getRootUrl() + BsServerDef.projectRootUrl + BsServerDef.ModifyHouseWork;
    },
    saveHouseWorkUrl: function () {
        return ConstantDef.getRootUrl() + BsServerDef.projectRootUrl + BsServerDef.SaveHouseWork;
    },
    updateSlHouseIntroduceUrl: function () {
        return ConstantDef.getRootUrl() + BsServerDef.projectRootUrl + BsServerDef.UpdateSlHouseIntroduce;
    },
    queryTrainingInfoUrl: function () {
        return ConstantDef.getRootUrl() + BsServerDef.projectRootUrl + BsServerDef.QueryTrainingInfo;
    },
    queryEducationInfoUrl: function () {
        return ConstantDef.getRootUrl() + BsServerDef.projectRootUrl + BsServerDef.QueryEducationInfo;
    },
    queryWorkInfoUrl: function () {
        return ConstantDef.getRootUrl() + BsServerDef.projectRootUrl + BsServerDef.QueryWorkInfo;
    },
    queryHouseTrainingUrl: function () {
        return ConstantDef.getRootUrl() + BsServerDef.projectRootUrl + BsServerDef.QueryHouseTraining;
    },
    queryHouseEducationUrl: function () {
        return ConstantDef.getRootUrl() + BsServerDef.projectRootUrl + BsServerDef.QueryHouseEducation;
    },
    queryHouseWorkUrl: function () {
        return ConstantDef.getRootUrl() + BsServerDef.projectRootUrl + BsServerDef.QueryHouseWork;
    },
    queryHouseIntroduceUrl: function () {
        return ConstantDef.getRootUrl() + BsServerDef.projectRootUrl + BsServerDef.QueryHouseIntroduce;
    },
    getFindProductStockUrl: function () {
        return ConstantDef.getRootUrl() + BsServerDef.projectRootUrl + BsServerDef.GetFindProductStock;
    },
    /**委托订单明细**/
    getBsFindSdoByDetailUrl: function () {
        return ConstantDef.getRootUrl() + BsServerDef.projectRootUrl + BsServerDef.GetSdoByDetailUrl;
    },
    /**获取囤货订单详情**/
    getFindBssgDetailUrl: function () {
        return ConstantDef.getOrderRootUrl() + OrderServerDef.projectRootUrl + OrderServerDef.FindBssgDetailUrl;
    },
    /**获取销售订单列表**/
    getFindBssListUrl: function () {
        //return ConstantDef.getOrderRootUrl() + OrderServerDef.projectRootUrl + OrderServerDef.FindBssListUrl;
        return ConstantDef.getRootUrl() + BsServerDef.projectRootUrl + BsServerDef.QueryBssListUrl;
    },
    /**获取销售订单详情**/
    getFindBssDetailUrl: function () {
        return ConstantDef.getOrderRootUrl() + OrderServerDef.projectRootUrl + OrderServerDef.FindBssDetailUrl;
        //return ConstantDef.getRootUrl() + BsServerDef.projectRootUrl + BsServerDef.QueryBssDetailUrl;
    },
    getCheckLoginTypeUrl: function () {
        return ConstantDef.getRootUrl() + BsServerDef.projectRootUrl + BsServerDef.CheckLoginType;
    },
    /**选择城市**/
    getChooseCityUrl: function () {
        return ConstantDef.getRootUrl() + BsServerDef.projectRootUrl + BsServerDef.ChooseCityUrl;
    },
    /**修改买手密码**/
    getUpdateBuyerPasswordUrl: function () {
        return ConstantDef.getRootUrl() + BsServerDef.projectRootUrl + BsServerDef.UpdateBuyerPassword;
    },
    /**修改冻品管家密码**/
    getUpdateHousePasswordUrl: function () {
        return ConstantDef.getRootUrl() + BsServerDef.projectRootUrl + BsServerDef.UpdateHousePassword;
    },
    /**获取买手短信验证码**/
    getBuyerMessCodeUrl: function () {
        return ConstantDef.getRootUrl() + BsServerDef.projectRootUrl + BsServerDef.BuyerMessCodeUrl;
    },
    /**获取管家短信验证码**/
    getHouseMessCodeUrl: function () {
        return ConstantDef.getRootUrl() + BsServerDef.projectRootUrl + BsServerDef.HouseMessCodeUrl;
    },
    /**获取图片验证码**/
    getCheckImageCodeUrl: function () {
        return ConstantDef.getRootUrl() + BsServerDef.projectRootUrl + BsServerDef.CheckImageCodeUrl;
    },
    // 获取轮播信息
    getSliderInfoUrl: function () {
        return ConstantDef.getOrderRootUrl() + StockServerDef.projectRootUrl + BsServerDef.SlideShow;
    },
    // 获取详情轮播信息
    getDetailSliderInfoUrl: function () {
        return ConstantDef.getOrderRootUrl() + StockServerDef.projectRootUrl + BsServerDef.DetailSlideShow;
    },
    //获取楼层信息
    getFloorInfoUrl:function(){
        return ConstantDef.getOrderRootUrl() + StockServerDef.projectRootUrl + BsServerDef.GetFloor;
    }
}

/** 订单服务 */
var OrderServerDef = {
    projectRootUrl: "/msk-web/api/v1",
    CreateOrderServerUrl: "/so/sdo/create",
    CreateDistributeOrderServerUrl: "/so/sdo/distribution/create",
    CreateSoSdoOrderServerUrl: "/so/sdo/buyer/create",
    FindBssgListUrl: "/so/sdo/bssg/list",
    FindBssgDetailUrl: "/so/sdo/bssg/detail",
    FindBssListUrl: "/so/sdo/bss/list",
    FindBssDetailUrl: "/so/sdo/bss/detail"
}

/** 库存服务 */
var StockServerDef = {
    projectRootUrl: "/msk-web/api/v1",
    FindPdStockQtyServerUrl: "/pd/pd_stock"
}


/**买手服务*/
var BsServerDef = {
    projectRootUrl: "/msk-bs/api",
    AcountLoginServerUrl: "/ba/account/login",
    HouseAcountLoginServerUrl: "/ba/houseAccount/login",
    FindOrderDetailUrl: "/bs/find/orderDetail",
    getOrderDetailByCarIDUrl: "/bs/get/orderDetailInfo",
    getOrderDetailByPdInfoUrl: "/ba/buyGoodsInfo",
    UpdateOrderUrl: "/bs/updateOrder",
    FindPdClasses: "/ba/productClass",
    FindPdInfo: "/ba/productInfo",
    FindPriceWay: "/ba/pdPriceWay",
    FindPdFeature: "/ba/productFeature",
    FindPdWeight: "/ba/productWeight",
    FindPdGrade: "/ba/productGrade",
    AddCar: "/ba/addCar",
    GetProvinceListUrl: "/ba/getProvinceList",
    GetProvinceCityListUrl: "/ba/getProvinceCityList",
    GetDistrictList: "/ba/getDistrictList",
    SearchSlCodeServerUrl: "/bs/searchSlCode",
    FindHouseBookByRecbookIdServeUrl: "/bs/find/HouseBookByRecbookId",
    FindHouseBookServerUrl: "/bs/search/houseBook",
    DeteleHouseBookServerUrl: "/bs/delete/houseBook",
    ModifyHouseBookServerUrl: "/bs/modify/houseBook",
    AddHouseBookServerUrl: "/bs/add/houseBook",
    SearchHouseListUrl: "/ba/searchHouseList",
    SaveHouseInfoUrl: "/ba/saveHouseInfo",

    FindSlAccountServerUrl: "/bs/checkBsInfoExist",
    NewOrUpdateBsInfoServerUrl: "/bs/slInfo/slAccount/newOrUpdate",
    FindHouseAccountServerUrl: "/bs/checkHouseInfoExist",
    FindHouseTelServerUrl: "/bs/checkHouseTelExist",
    NewHouseAccountServerUrl: "/bs/houseInfoNew",
    FindHouseTypeServerUrl: "/bs/findHouseTypeList",
    FindBsTypeServerUrl: "/bs/findBsTypeList",
    FindHouseCodeByBuyerId: "/ba/findHouseCodeByBuyerId",

    QueryHouseIntroduce: "/ba/queryHouseIntroduce",
    QueryHouseWork: "/ba/queryHouseWork",
    QueryHouseEducation: "/ba/queryHouseEducation",
    QueryHouseTraining: "/ba/queryHouseTraining",
    QueryWorkInfo: "/ba/queryWorkInfo",
    QueryEducationInfo: "/ba/queryEducationInfo",
    QueryTrainingInfo: "/ba/queryTrainingInfo",
    UpdateSlHouseIntroduce: "/ba/updateSlHouseIntroduce",
    SaveHouseWork: "/ba/saveHouseWork",
    ModifyHouseWork: "/ba/modifyHouseWork",
    DelHouseWork: "/ba/delHouseWork",
    SaveHouseEducation: "/ba/saveHouseEducation",
    ModifyHouseEducation: "/ba/modifyHouseEducation",
    DelHouseEducation: "/ba/delHouseEducation",
    SaveHouseTraining: "/ba/saveHouseTraining",
    ModifyHouseTraining: "/ba/modifyHouseTraining",
    DelHouseTraining: "/ba/delHouseTraining",
    GetFindProductStock: "/ba/getFindProductStock",//库存接口
    CheckLoginType: "/ba/checkLoginType",//系统可以信息获取
    ChooseCityUrl: "/ba/allCity/_search",
    CheckImageCodeUrl: "/ba/checkImageCode",//获取图片验证码
    BuyerMessCodeUrl: "/bs/buyer/verification/code/_query",//获取买手短信验证码
    HouseMessCodeUrl: "/bs/house/verification/code/_query",//获取管家短信验证码
    UpdateHousePassword: "/bs/house/password/_update",//修改冻品管家密码
    UpdateBuyerPassword: "/bs/buyer/password/_update",//修改买手密码
    QueryBssListUrl: "/bs/buyer/bss/list/_query",//买手销售订单列表

    SlideShow: "/pd/bspp/mainCarousel/_get",//首页轮播图片接口
    DetailSlideShow: "/pd/bspp/pdCarousel/_get",//详情轮播图片接口
    GetFloor:"/pd/bspp/floorInfo/_get",//产品楼层

    QueryBssDetailUrl: "/bs/buyer/bss/detail/_query",//买手销售订单详情
    GetSdoByDetailUrl: "/bs/orderDetail/_search"//委托订单列表  详情
}

var ByServerDef = {
    projectRootUrl: "/msk-buyers/api",
    FindBuyerByTelServerUrl: "/by/basicInfo/queryByTel"
}

var DistrictDef = {
    projectRootUrl: "/msk-district/api",
    FindProvinceServerUrl: "/district/query/province",
    FindDistrictCityServerUrl: "/district/query/city",
    FindDistrictServerUrl: "/district/query/district",
    FindLgcsAreaServerUrl: "/district/query/lgcsArea"
}

var Environment = {
    development: "http://10.20.16.57:8081",
    orderDevelopment: "http://10.20.16.57:8080",
    stockDevelopment: "http://10.20.16.57:8080",
    uat: "http://10.45.21.248",
    test: "http://10.20.16.83:8884",
    st: "http://10.171.246.164",
    prod: "http://10.20.10.100",
    shTest: "http://t-bms.xianchida.com",
    yan: "http://10.20.16.22:8081",
    qiang: "http://10.20.16.140:8080"
}

var Param = "[{'siteCode': 'abcd','auth': 'xxxx','loginId': 'a124'}]";
var version = "1.0.0";

