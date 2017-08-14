var BS2102105 = {
    formId: "bs2102105FormId",
    saveBtnId:"BS2102105_SAVE",
    newBtnId:"BS2102105_NEW",
    BS2102105Grid: null,
    init: function () {
        BS2102105Grid = $('#bs2102105_list_grid').grid({
            actionHandler: BS2102105.actionHandler
        });
        this.bingNewBtn();
        this.bingSaveBtn();
    },
    actionHandler: function (rowdata, coltype, row, col) {
        if (coltype == 'check') {
            rowdata["flagNum"] = "4";
            rowdata["groupId"] = $("#groupId").val();
            rowdata["lgcsAreaCode"] = $("#lgcsCode").val();
            rowdata["lgcsAreaName"] = $("#lgcsName").val();
            $('#main-content').postUrl(Main.contextPath + "/BS2102103/init/",rowdata);
        }
        if (coltype == 'delete') {
            $.alertMessage.confirm("确定要删除这条数据吗？", function() {
                $.alertMessage.close();
                $('#main-content').postUrl(Main.contextPath + "/BS2102105/delete" ,
                {hkGroupId:$("#groupId").val(),slCode:rowdata.slCode,houseCode:rowdata.houseCode},
                function (data) {
                    FormUtils.setFormValue(BS2102105.formId, "BS2102105");
                    BS2102105Grid.fnDraw();
                }
                ,{refreshHtml: false});
            })
        }
    },
    bingSaveBtn: function () {
        $("#" + BS2102105.saveBtnId).click(function () {
            var changeData = BS2102105Grid.getChangeData();// 获取改动的数据对象  是数组
            if(changeData.length == 0){
                $.alertMessage.confirm("请先编辑要保存的数据再保存！", function() {
                    $.alertMessage.close();
                })
            } else{
                $.alertMessage.confirm("你确定要保存当前数据吗？", function() {
                    $.alertMessage.close();
                    var json = {};// 创建json 对象
                    var groupId = $("#groupId").val();
                    for(i=0;i<changeData.length;i++){//  把数组的对象封装到json
                        json[i] = {"slCode":changeData[i]["slCode"],"houseCode":changeData[i]["houseCode"],"flag6":changeData[i]["flag6"]};
                    }
                    var jsonStr = JSON.stringify(json);//  转成jsonStr
                    $('#main-content').postUrl(Main.contextPath +"/BS2102105/update", {"jsonStr":jsonStr,"hkGroupId":groupId}, function (data) {
                        FormUtils.setFormValue(BS2102105.formId, "BS2102105");
                        BS2102105Grid.fnDraw();
                    },{refreshHtml: false});
                })
            }
        })
    },
    bingNewBtn: function () {
        $("#" + BS2102105.newBtnId).click(function () {
            $.pdialog.open("冻品管家组新增成员", Main.contextPath + "/BS2102106/init",{resizable:true, width:1200, height:600},
                {
                    hkGroupId:$("#groupId").val(),
                    lgcsAreaCode:$("#lgcsCode").val(),
                    cityCode:$("#districtCode").val(),
                    lgcsAreaName:$("#lgcsName").val(),
                    cityName:$("#cityName").val(),
                    buyerType:$("#buyerType").val(),
                    buyerTypeName:$("#buyerTypeName").val(),
                    classesName:$("#classesName").val(),
                    machiningName:$("#machiningName").val(),
                    hkGroupName:$("#groupName").val()
                })
        })
    }
}
$(document).ready(function () {
    BS2102105.init();
});
