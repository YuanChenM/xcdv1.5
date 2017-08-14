/**
 * 买手店列表JS
 *
 * @author cx
 */

var BS2101101_1 = {
    baseInfo: "baseInfo",
    init: function () {
        this.bindToPage();
    },
    // 绑定按钮
    bindToPage: function () {
        var slCode = $("#slCode").val();
        var hidSlAccount =$("#hidSlAccount").val();
        $("#baseInfoContainer li:first").click(function () {
            var param = {
                slContact:$("#hidSlContact").val(),
                slCode:$("#hidSlCode").val(),
                slCodeDis:$("#hidSlCodeDis").val(),
                provinceCode:$("#hidProvinceCode").val(),
                cityCode:$("#hidCityCode").val(),
                districtCode:$("#hidDistrictCode").val(),
                slAccount:$("#hidSlAccount").val(),
                flagNum:$("#flagNum").val()
            };
            $('#main-content').postUrl(Main.contextPath + "/BS2101105/init",param);
        });
        $("#toDpManager").click(function () {
            $('#main-content').postUrl(Main.contextPath + "/BS2101102/init",{
                slContact:$("#hidSlContact").val(),
                slCode:$("#hidSlCode").val(),
                slCodeDis:$("#hidSlCodeDis").val(),
                provinceCode:$("#hidProvinceCode").val(),
                cityCode:$("#hidCityCode").val(),
                districtCode:$("#hidDistrictCode").val(),
                slAccount:$("#hidSlAccount").val(),
                flagNum:$("#flagNum").val()
            });
        });
        $("#toBuyerManager").click(function () {
            $('#main-content').postUrl(Main.contextPath + "/BS2101112/init",{
                slContact:$("#hidSlContact").val(),
                slCode:$("#hidSlCode").val(),
                slCodeDis:$("#hidSlCodeDis").val(),
                provinceCode:$("#hidProvinceCode").val(),
                cityCode:$("#hidCityCode").val(),
                districtCode:$("#hidDistrictCode").val(),
                slAccount:$("#hidSlAccount").val(),
                flagNum:$("#flagNum").val(),
                pageType : 1
            });
        });
        // 买手基本信息注册及编码生成管控表
        $("#slBsAccountInfo").click(function () {
            var slCode = $("#hidSlCode").val();
            var slAccount = $("#hidSlAccount").val();
            var accountImg = $("#hidSlAccountImg").val();
            var param = {
                slCode:slCode,
                slAccount:slAccount,
                //Modify 买手信息列表导出图片 by yangchunyan 2016/09/29 start
                accountImg:accountImg
                //Modify 买手信息列表导出图片 by yangchunyan 2016/09/29 end
            };
            $('#main-content').postUrl(Main.contextPath + "/BS2102124/init/",param,function (data) {
                downloadAsync("slBsAccountTemp","BS2102119Logic","msk-bs","买手基本信息注册及编码生成管控表.xlsx",data);
            },{refreshHtml: false});
        })
    }


}
$(document).ready(function () {
    // 初始化调用
    BS2101101_1.init();
});
