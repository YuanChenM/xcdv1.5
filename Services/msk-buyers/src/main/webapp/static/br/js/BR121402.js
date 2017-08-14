var $List_Grid;
var BR121402 = {
    init: function () {
        $List_Grid = $('#BR121402_list_grid').grid({
            actionHandler: BR121402.actionHandler/*,
            editCellOnBlurHandler :BR121402.cellOnBlurHandler*/
        });
        this.bindAddBtn();
    },
    actionHandler: function (rowdata, coltype, row, col) {
        var settingType = rowdata.settingType;
        var settingStartValue = rowdata.settingStartValue;
        var settingEndValue = rowdata.settingEndValue;
        var settingValue = rowdata.settingValue;
        var reg = /^[1-9]\d*$/;
        if (coltype == "save") {
            if(settingType=="1"){
                if(settingStartValue == "" || settingEndValue == "" || settingName==""){
                    $.alertMessage.warn("输入有空值，请重新输入 !");
                    return false;
                }else if(settingName.length > 20 ||settingStartValue.length > 10 || settingEndValue.length > 10 || settingValue.length>10){
                    $.alertMessage.warn("输入值长度过长，请重新输入");
                    return false;
                }else{
                    if(!reg.test(settingStartValue) ||!reg.test(settingEndValue) ){
                        $.alertMessage.warn("请输入数字类型");
                        return false;
                    }else{
                        if(eval(settingEndValue) >= eval(settingStartValue)){
                            $('#main-content').postUrl(Main.contextPath + "/BR121402/save/", rowdata, function (data) {
                                if (data == "S") {
                                    $.alertMessage.info("修改成功!");
                                    $List_Grid.fnDraw();
                                } else if(data == "F"){
                                    $.alertMessage.warn("修改失败");
                                    return false;
                                }else{
                                    $.alertMessage.warn("输入的数据范围不正确，请查证 !");
                                    return false;
                                }
                            }, {refreshHtml: false});
                        }else{
                            $.alertMessage.warn("错误！输入的开始值大于结束值");
                            return false;
                        }
                    }
                }

            }

        }
        if (coltype == "delete") {
            $.alertMessage.confirm("确认删除该条数据么", function () {
                $('#main-content').postUrl(Main.contextPath + "/BR121402/delete/" + rowdata.id);
                $.alertMessage.close();
                $List_Grid.fnDraw();
            })
        }
    },

    bindAddBtn: function () {
        $("#BR121402_ADD").click(function () {
            var settingType = $("#settingType2 option:selected").val();
            var settingName = $("#settingName").val();
            var settingValue = $("#settingValue").val();
            var settingStartValue = $("#settingStartValue").val();
            var settingEndValue = $("#settingEndValue").val();
            var reg = /^[1-9]\d*$/;
            
            if(settingName =="" || settingStartValue =="" || settingEndValue ==""){
                $.alertMessage.warn("输入有空值，请重新输入 !");
                return false;
            }else if(settingName.length > 20 ||settingStartValue.length > 10 || settingEndValue.length > 10 || settingValue.length>10){
                $.alertMessage.warn("输入值长度过长，请重新输入");
                return false;
            }else {
                if(!reg.test(settingStartValue) ||!reg.test(settingEndValue) ){
                    $.alertMessage.warn("请输入数字类型");
                    return false;
                }else{
                    if(eval(settingEndValue) >= eval(settingStartValue)){
                        $('#main-content').postUrl(Main.contextPath + "/BR121402/insert/", {
                            settingType: settingType,
                            settingName: settingName,
                            settingValue: settingValue,
                            settingStartValue: settingStartValue,
                            settingEndValue: settingEndValue
                        }, function (data) {
                            if (data == "S") {
                                $.alertMessage.info("新增数据成功 !");
                                $("#settingName").val("");
                                $("#settingValue").val("");
                                var settingStartValue = $("#settingStartValue").val("");
                                var settingEndValue = $("#settingEndValue").val("");
                                $List_Grid.fnDraw();
                            } else if(data == "F"){
                                $.alertMessage.warn("新增数据失败 !");
                                return false;
                            }else{
                                $.alertMessage.warn("输入的数据范围不正确，请查证 !");
                                return false;
                            }
                        }, {refreshHtml: false});
                    }else{
                        $.alertMessage.warn("错误！输入的开始值大于结束值");
                        return false;
                    }
                }
            }
        })
    }

}

$(document).ready(function () {
    // 初始化调用
    BR121402.init();
});

