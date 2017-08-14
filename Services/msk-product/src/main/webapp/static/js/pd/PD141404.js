/**
 * 卖家单个产品及状态管控详细
 *
 * @author xhy
 */

var PD141404 = {
    saveButtonId: "PD141404_SAVE",
    detection: "#checkDate",
    formId: "PD141404Form",
    init: function () {
        $('.tree').treegrid();
        this.clickForData();
        this.bindDatePicber(PD141404.detection);
        this.changeCheckBox();
        this.saveButton();
       /* $("#zhongYuan").accordion({heightStyle: "content"});*/
        $("#zhongYuan").accordion({ heightStyle:"center"});
    },
    bindDatePicber: function (orderTimeId) {
        $(orderTimeId).datepicker({
            showButtonPanel: true,
            dateFormat: 'yy-mm-dd',
            changeMonth: true,
            changeYear: true
        });
    },
    changeCheckBox: function () {
        $(":checkbox").click(function () {
            if($(this).attr("class").indexOf("resultNormsFlg") == -1){
                var ids = $(this).attr("class");
                if ($(this).prop("checked")) {
                    $("." + ids).not($(this)).removeAttr("checked");
                    $('#'+ids).val("");
                    $('#'+ids).prop('readonly','readonly');
                } else {
                    $("." + ids).not($(this)).removeAttr("checked");
                    $("." + ids).last().prop("checked", "checked");
                    $('#'+ids).removeAttr('readonly');
                }
            }else{
                var ids = $(this).attr("class");
                if ($(this).prop("checked")) {
                    $("." + ids).prop("checked", "checked");
                    $("#normsTable :checkbox").not($("." + ids)).removeAttr("checked");
                    $("#defalutValue").removeAttr("name");
                    $("#normsNames").removeAttr("name");
                } else {
                    $("." + ids).removeAttr("checked");
                    $(".resultNormsFlg").prop("checked", "checked");
                    $("#defalutValue").attr("name","normsCode");
                    $("#normsNames").removeAttr("name");
                }
            }

        });
    },
    clickForData: function () {
        $("a[name='mctProNeedBtn']").click(function () {
            var value = $(this).attr("class").match(/\d+/g).toString();
            var url = $("#divMctProNeed" + value).css("display");
            if (url.indexOf("none") != -1) {
                $("#pd141148accordion").accordion({heightStyle: "fill"});
                $("#pd141148accordion").accordion("refresh");
                $("#mctProNeedImg" + value).attr('src', Main.contextPath + "/static/core/images/action/details_close.png");
                $("#divMctProNeed" + value).css("display", "block");
            } else {
                $("#mctProNeedImg" + value).attr('src', Main.contextPath + "/static/core/images/action/details_open.png");
                $("#divMctProNeed" + value).css("display", "none");
            }
        });
        $("a[name='argMarNeedBtn']").click(function () {
            var value = $(this).attr("class").match(/\d+/g).toString();
            var url = $("#divArgMarNeed" + value).css("display");
            if (url.indexOf("none") != -1) {
                $("#pd141149accordion").accordion({heightStyle: "fill"});
                $("#pd141149accordion").accordion("refresh");
                $("#argMarNeedImg" + value).attr('src', Main.contextPath + "/static/core/images/action/details_close.png");
                $("#divArgMarNeed" + value).css("display", "block");
            } else {
                $("#argMarNeedImg" + value).attr('src', Main.contextPath + "/static/core/images/action/details_open.png");
                $("#divArgMarNeed" + value).css("display", "none");
            }
        });

        $("a[name='argProNeedBtn']").click(function () {
            var value = $(this).attr("class").match(/\d+/g).toString();
            var url = $("#divArgProNeed" + value).css("display");
            if (url.indexOf("none") != -1) {
                $("#pd141149accordion").accordion({heightStyle: "fill"});
                $("#pd141149accordion").accordion("refresh");
                $("#argProNeedImg" + value).attr('src', Main.contextPath + "/static/core/images/action/details_close.png");
                $("#divArgProNeed" + value).css("display", "block");
            } else {
                $("#argProNeedImg" + value).attr('src', Main.contextPath + "/static/core/images/action/details_open.png");
                $("#divArgProNeed" + value).css("display", "none");
            }
        });

        /*市场禁止日	*/
        $("a[name='argMarNeedNoBtn']").click(function () {
            var value = $(this).attr("class").match(/\d+/g).toString();
            var url = $("#divArgMarNoNeed" + value).css("display");
            if (url.indexOf("none") != -1) {
                $("#argMarNeedNoImg" + value).attr('src', Main.contextPath + "/static/core/images/action/details_close.png");
                $("#divArgMarNoNeed" + value).css("display", "block");
            } else {
                $("#argMarNeedNoImg" + value).attr('src', Main.contextPath + "/static/core/images/action/details_open.png");
                $("#divArgMarNoNeed" + value).css("display", "none");
            }
        });
        /*供应商禁止日	*/
        $("a[name='argProNeedNoBtn']").click(function () {
            var value = $(this).attr("class").match(/\d+/g).toString();
            var url = $("#divArgProNoNeed" + value).css("display");
            if (url.indexOf("none") != -1) {
                $("#argProNeedNoImg" + value).attr('src', Main.contextPath + "/static/core/images/action/details_close.png");
                $("#divArgProNoNeed" + value).css("display", "block");
            } else {
                $("#argProNeedNoImg" + value).attr('src', Main.contextPath + "/static/core/images/action/details_open.png");
                $("#divArgProNoNeed" + value).css("display", "none");
            }
        });
    },
    saveButton: function () {
        $("#" + PD141404.saveButtonId).click(function () {
            var validator = mainValidation($("#" + PD141404.formId));
            var isValid = validator.form();
            if (isValid) {
                $.alertMessage.confirm("你确定要保存当前数据吗？", function () {
                    $.alertMessage.close();
                    formData = getFormData($("#" + PD141404.formId));
                    if(formData.normsCode == '0'){
                        if(formData.normsCodeOld!=null){
                            $(".resultNormsFlg").removeAttr("checked");
                            formData = getFormData($("#" + PD141404.formId));
                            formData.normsCode=formData.normsCodeOld;
                        }else{
                            $.alertMessage.info("未选中产品包装特征,请选择!");
                            return false;
                        }
                    }
                    if(formData.resultGrade == '0'){
                        $.alertMessage.info("请评定等级!");
                        return false;
                    }
                    $('#main-content').postUrl($("#" + PD141404.formId).attr("action"), formData, function (data) {
                        if (data == '1') {
                            $.alertMessage.info("数据修改成功!");
                        } else if (data == '2') {
                            $.alertMessage.info("数据新增成功!");
                        } else {
                            $.alertMessage.info("数据异常!");
                        }
                    }, {refreshHtml: false});
                });
            }
        });
    }
}
$(document).ready(function () {
    // 初始化调用
    PD141404.init();
});

