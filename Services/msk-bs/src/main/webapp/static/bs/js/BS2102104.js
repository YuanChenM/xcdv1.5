/**
 * 买手店列表JS
 *
 * @author cx
 */
var $List_Grid;
var BS2102104 = {
    formId: "bs2102104FormId",
    searchData :"BS2102104_SEARCH",
    saveDate:"BS2102104_SAVE",
    editButtonId: "BS2102104_EDIT",
    newBtnId:"BS2102104_NEW",
    init: function () {
        $List_Grid = $('#bs2102104_list_grid').grid({
            actionHandler: BS2102104.actionHandler
        });
        this.changeSelect();
        this.queryData();
        this.bindSearchButton();
        this.bindConfirmButton();
        this.bingNewBtn();
    /*    this.bindStartDatePicber('#taxablePeriodBegin');
        this.bindEndDatePicber('#taxablePeriodEnd');*/
    },
    // 绑定按钮
    bindSearchButton: function () {
        $("#" +BS2102104.searchData).click(function () {
            FormUtils.setFormValue(BS2102104.formId, "BS2102104");
            $List_Grid.fnDraw();
        });
    },

    bindConfirmButton : function(){
        $("#"+  BS2102104.saveDate).click(function(){
            var changeData = $List_Grid.getChangeData();// 获取改动的数据对象  是数组
            if(changeData.length==0){
                $.alertMessage.confirm("请先编辑要保存的数据再保存！", function() {
                    $.alertMessage.close();
                })
            }else{
                $.alertMessage.confirm("你确定要保存当前数据吗？", function() {
                    $.alertMessage.close();
                    var json = {};// 创建json 对象
                    for(i=0;i<changeData.length;i++){//  把数组的对象封装到json
                        json[i] =changeData[i];
                    }
                    var jsonStr = JSON.stringify(json);//  转成jsonStr
                    $('#main-content').postUrl(Main.contextPath +"/BS2102104/save", {"jsonStr":jsonStr},function(data) {
                        $.alertMessage.info("修改已提交");
                        FormUtils.setFormValue(BS2102104.formId, "BS2102104");
                        $List_Grid.fnDraw();
                    }, {refreshHtml: false});
                })

            }
        });
    },
    actionHandler: function (rowdata, coltype, row, col) {
        /** 操作按钮 */
        if (coltype == "edit") {
            $('#main-content').postUrl(Main.contextPath + "/BS2102105/init", {
                lgcsAreaCode:rowdata.lgcsAreaCode,
                lgcsAreaName:rowdata.lgcsAreaName,
                cityCode:rowdata.cityCode,
                cityName:rowdata.cityName,
                buyerType:rowdata.buyerType,
                buyerTypeName:rowdata.buyerTypeName,
                classesCode:rowdata.classesCode,
                classesName:rowdata.classesName,
                machiningCode:rowdata.machiningCodeU,
                machiningName:rowdata.machiningNameU,
                hkGroupId : rowdata.hkGroupId,
                hkGroupName:rowdata.hkGroupName
            });
        }
    },
    changeSelect: function () {
        var citySelect = $('#citySel');
        var machiningSel = $("#machiningCode_sel");
       $("#wl_select").change(function () {
            
            $("#citySel").find("option").not(":first").remove();
            var lgcsAreaCode = $('#wl_select').val();
            if (lgcsAreaCode != null&&lgcsAreaCode!="") {
                $('#main-content').postUrl(Main.contextPath + '/BS2102104/findCityLst', {lgcsAreaCode: lgcsAreaCode},
                    function (data) {
                        $.each(data, function (i, item) {
                            citySelect.append("<option value='" + item.cityCode + "'>" + item.cityName + "</option>");
                        });
                    }, {refreshHtml: false});
            }
        });
        $("#classesCode_sel").change(function () {
            $("#machiningCode_sel").find("option").not(":first").remove();
            var classesCode = $("#classesCode_sel").val();
            if(classesCode != null && classesCode != ""){
                $('#main-content').postUrl(Main.contextPath + '/BS2102104/findPdMachiningLst', {classesCode: classesCode},
                    function (data) {
                        $.each(data, function (i, item) {
                            machiningSel.append("<option value='" + item.machiningCodeU + "'>" + item.machiningNameU + "</option>");
                        });
                    }, {refreshHtml: false});
            }
        });
    },
    queryData: function () {
        $("#" + BS2102104.searchData).click(function () {
            formData = getFormData($("#" + BS2102104.formId));
            $('#main-content').postUrl(
                $("#" + BS2102104.formId).attr("action"), formData, function () {
                    $List_Grid.fnDraw();
                }, {refreshHtml: false});
        });
    },
    bingNewBtn: function () {
        $("#" + BS2102104.newBtnId).click(function () {
            $.pdialog.open("新增冻品管家组", Main.contextPath + "/BS2102123/init",{resizable:true, width:500, height:320},
                {

                })
        })
    }

}
$(document).ready(function () {
    // 初始化调用
    BS2102104.init();
});
