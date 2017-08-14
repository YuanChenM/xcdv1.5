/**
 * 买手店列表JS
 *
 * @author cx
 */

var BS2102112 = {
    baseInfo: "baseInfo",
    init: function () {
        this.bindToPage();
    },
    // 绑定按钮
    bindToPage: function () {
        var slCode = $("#slCode").val();
        var houseCode = $("#houseCode").val();
        var validYearMonth = $("#validYearMonth").val();
        var param = {
            slCode:slCode,
            houseCode:houseCode
        };
        var param1 = {
            slCode:slCode,
            houseCode:houseCode,
            validYearMonth:validYearMonth
        };
        //定级管理
        $("#levelManage").click(function () {
            $('#main-content').postUrl(Main.contextPath + "/BS2102111/init",param);
        });
        //自我介绍
        $("#selfIntroManage").click(function () {
            $('#main-content').postUrl(Main.contextPath + "/BS2102113/init",param);
        });
        //基本信息
        $("#baseInfo").click(function () {
            param["flagNum"] = 3;
            $('#main-content').postUrl(Main.contextPath + "/BS2101107/init",param);
        });
        //定星管理
        $("#starManage").click(function () {
            $('#main-content').postUrl(Main.contextPath + "/BS2102114/init",param1);
        });
        //冻品管家设置
        $("#houseSetting").click(function () {
            $('#main-content').postUrl(Main.contextPath + "/BS2102112/houseSetting/",param,function (data) {
                var parameter = {
                    "slCode":data.slCode,
                    "houseCode":data.houseCode,
                    "houseShowName":data.houseShowName,
                    "flag1":data.flag1,
                    "houseTel":data.houseTel,
                    "wechat":data.wechat,
                    "houseGreade":data.houseGreade,
                    "houseStar":data.houseStar,
                    "vhouseAddress":data.vhouseAddress,
                    "lgcsAreaCode":data.vlgcsAreaCode,
                    "lgcsAreaName":data.flag20,
                    "vcityCode": data.vcityCode,
                    "vprovinceCode": data.vprovinceCode,
                    "vdistrictCode": data.vdistrictCode,
                    "flagNum" : '3'
                }
                $('#main-content').postUrl(Main.contextPath + "/BS2102103/init/",parameter);
            },{refreshHtml: false});
        });
        //专属买家管理
        $("#exclusiveSlManage").click(function () {
            $('#main-content').postUrl(Main.contextPath + "/BS2102112/slManage",param,function (data) {
                data.applyStatus = '2';
                data.flagNum = '1';
                $('#main-content').postUrl(Main.contextPath + "/BS2101103/init/",data);
            },{refreshHtml: false});
        });
        //锁定期买家管理
        $("#lockSlManage").click(function () {
            $('#main-content').postUrl(Main.contextPath + "/BS2102112/slManage",param,function (data) {
                data.applyStatus = '1';
                data.flagNum = '2';
                $('#main-content').postUrl(Main.contextPath + "/BS2101103/init/",data);
            },{refreshHtml: false});
        });

        // 买家履历信息管理
        $("#slRecordManage").click(function () {
            $('#main-content').postUrl(Main.contextPath + "/BS2102112/slManage",param,function (data) {
                data.flagNum = '3';
                $('#main-content').postUrl(Main.contextPath + "/BS2101112/init/",data);
            },{refreshHtml: false});
        });

        // 冻品管家基本信息注册及编码生成管控表
        $("#houseAccountInfo").click(function () {
            $('#main-content').postUrl(Main.contextPath + "/BS2102116/init",param,function (data) {
                downloadAsync("houseAccountTemp","BS2102121Logic","msk-bs","冻品管家基本信息注册及编码生成管控表.xlsx",data);
            },{refreshHtml: false});
        })
    }


}

$(document).ready(function () {
    // 初始化调用
    BS2102112.init();
});
