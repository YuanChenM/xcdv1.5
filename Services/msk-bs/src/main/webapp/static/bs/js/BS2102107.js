/**
 * 冻品管家管理设置新增
 *
 * @author wang_haichun
 */
var BS2102107 = {
    formId: "bs2102107FormId",
    saveButtonId: "BS2102107_ADD",
    init: function () {
        this.bindSaveButton();
        this.changeSelect();
    },

    bindSaveButton : function(){
        /** 操作按钮 */
        $("#" + BS2102107.saveButtonId).click(function () {
            BS2102107.saveData();
        });
    },

    changeSelect :function(){
        var selectLeverOne = $("#selectLeverOne");
        selectLeverOne.change(function (){
            var parentTypeCode = $(this).val();
            $("#selectLeverTwo").html("<option value='0'>请选择</option>");
            if(parentTypeCode == 0){
                return false;
            }
            $('#main-content').postUrl(Main.contextPath + '/BS2102103/findLeverTwo', {parentTypeCode:parentTypeCode},
                function (data) {
                    $.each(data, function (i, item) {
                        $("#selectLeverTwo").append("<option value='" + item.typeCode + "'>" + item.typeName + "</option>");
                    });
                }, {refreshHtml: false});
        });
    },

    saveData : function() {
        var lgcsArea = $("#lgcsArea");
        var lgcsAreaName = lgcsArea.html();
        var lgcsAreaCode = lgcsArea.data("val");
        var cityCode = $("#selectCity").val();
        var cityName = $("#selectCity").find("option:selected").text();
        var houseCategoryCode = $("#selectLeverOne").val();
        var houseReclassifyCode = $("#selectLeverTwo").val();
        var houseShowName  =$("#bs2102107_houseShowName").val();
        var flag1 = $("#bs2102107_flag1").val();
        var houseTel = $("#bs2102107_houseTel").val();
        var wechat = $("#bs2102107_wechat").val();
        var houseGreade  =$("#bs2102107_houseGreade").val();
        var houseStar  =$("#bs2102107_houseStar").val();
        var vhouseAddress= $("#bs2102107_vhouseAddress").val();
        var flagNum= $("#bs2102107_flagNum").val();
        var groupId= $("#bs2102107_groupId").val();

        var slCode = $("#input_slCode").val();
        var houseCode = $("#input_houseCode").val();

        if(!lgcsAreaName || !lgcsAreaCode){
            $.alertMessage.info("暂无物流区信息");
            return false;
        }
        if(cityCode == 0){
            $.alertMessage.info("请选择地区");
            return false;
        }
        if(houseCategoryCode == 0){
            $.alertMessage.info("请选择一级分类");
            return false;
        }
        if(houseReclassifyCode == 0){
            $.alertMessage.info("请选择二级分类");
            return false;
        }

        var param = {
            slCode:slCode,
            houseCode:houseCode,
            lgcsAreaName: lgcsAreaName,
            lgcsAreaCode: lgcsAreaCode,
            cityCode: cityCode,
            cityName: cityName,
            houseCategoryCode: houseCategoryCode,
            houseReclassifyCode: houseReclassifyCode,
            houseShowName:houseShowName,
            flag1:flag1,
            houseTel:houseTel,
            wechat:wechat,
            houseGreade:houseGreade,
            houseStar:houseStar,
            vhouseAddress:vhouseAddress,
            flagNum:flagNum,
            groupId:groupId
        };

        $('#main-content').postUrl(Main.contextPath + "/BS2102103/addHouseManager",param,function(date){
            if(date == -1){
                $.alertMessage.info("数据已经存在!");
            }
            if(date > 0){
                $.alertMessage.info("保存成功!");
            }
            $.pdialog.close("areaInfo");
            $('#main-content').postUrl(Main.contextPath + "/BS2102103/init",param);
        });
    }
}
$(document).ready(function () {
    // 初始化调用
    BS2102107.init();
});
