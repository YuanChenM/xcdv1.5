var $List_Grid;
var groupId = "";
var BS2102106 = {
    formId: "BS2102106FormId",
    saveButtonId: "BS2102106_SAVE",
    searchButtonId:"BS2102106_SEARCH",
    init: function () {
        $List_Grid = $('#bs2102106_list_grid').grid({
            actionHandler: BS2102106.actionHandler
        });
        this.changeSelect();
        this.queryData();
        this.bindSaveButton();
    },
    actionHandler: function (rowdata, coltype, row, col) {

    },
    // 绑定按钮
    bindSaveButton: function () {
        $("#" + BS2102106.saveButtonId).click(function () {
            BS2102106.newData();
        });
    },
    newData: function () {
       $List_Grid.getChoiceData();
        groupId = $("#BS2102106_groupId").val();
        var objs = $List_Grid.getChoiceData();
        if(objs.length == 0){
            $.alertMessage.warn("请选择管家");
            return;
        }
        var json = JSON.stringify(objs);
        $.post(Main.contextPath + "/BS2102106/save",{groupId: groupId,param:json},function(data){
            //$List_Grid.fnDraw();
            $('#main-content').postUrl(Main.contextPath + "/BS2102105/init",{
                hkGroupId:$("#BS2102106_groupId").val(),
                lgcsAreaCode:$("#BS2102106_areaCode").val(),
                cityCode:$("#BS2102106_districtCode").val(),
                lgcsAreaName:$("#BS2102106_areaName").val(),
                cityName:$("#BS2102106_cityName").val(),
                buyerType:$("#BS2102106_buyerType").val(),
                buyerTypeName:$("#BS2102106_buyerTypeName").val(),
                classesName:$("#BS2102106_classesName").val(),
                machiningName:$("#BS2102106_machiningName").val(),
                hkGroupName:$("#BS2102106_groupName").val()
            });
            $.pdialog.close();
        });
        //$('#main-content').postUrl(Main.contextPath + "/BS2102106/init/",{slCode:formData.slCode,epId:formData.epId});
    },
    changeSelect: function () {
        var selectLeverOne = $("#houseCategory");
        selectLeverOne.change(function (){
            var parentTypeCode = $(this).val();
            $("#houseReclassify").html("<option value=''>请选择</option>");
            if(parentTypeCode == 0){
                return false;
            }
            $('#main-content').postUrl(Main.contextPath + '/BS2102103/findLeverTwo', {parentTypeCode:parentTypeCode},
                function (data) {
                    $.each(data, function (i, item) {
                        $("#houseReclassify").append("<option value='" + item.typeCode + "'>" + item.typeName + "</option>");
                    });
                }, {refreshHtml: false});
        });
    },
    queryData: function () {
        $("#" + BS2102106.searchButtonId).click(function () {
            FormUtils.setFormValue(BS2102106.formId, "BS2102106");
            $List_Grid.fnDraw();
        });
    }
}
$(document).ready(function () {
    BS2102106.init();
});
