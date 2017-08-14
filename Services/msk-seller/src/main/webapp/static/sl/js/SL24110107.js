/**
 * Created by fjm on 2016/1/27.
 */
var SL24110107 = {
    formId : "SL24110107Form",
    saveButtonId : "SL24110107_UPDATE",
    saveButtonId : "SL241101070_UPDATE",
    deleteButtonId : "SL24110107_DELETE",
    deleteButtonId : "SL241101070_DELETE",
    addButtonId : "SL24110107_ADD",

    init : function(){
        this.bindAddbutton();
        this.bindDeleteButton();
        this.bindUpdatebutton();
        this.bindUpdatebutton0();
        this.bindDeleteButton0();
        for(var i=0;i<listSize;i++){
            this.bindDatePicber('#certDatex'+i);
            this.bindDatePicber('#brandTermBegin'+i);
            this.bindDatePicber('#brandTermEnd'+i);
        };
        for(var j=0;j<sl2411030073BeansSize;j++){
            this.bindDatePicber('#termBegin0'+j);
            this.bindDatePicber('#termEnd0'+j);
        };
        this.closeDate();
    },
    bindAddbutton : function () {
        $("#" + SL24110107.addButtonId).click(function(){
            $.pdialog.open("品牌添加",Main.contextPath + "/SL2411030071/init",{width:800,height:600});
        });
    },
    bindDeleteButton0:function(){
        for(var j=0;j<sl2411030073BeansSize;j++){
            (function(j){
                $("#SL241101070_DELETE"+j).click(function() {
                    var validator = mainValidation($("#SL241101070Form"+j));
                    var isValid = validator.form();
                    var termBegin0=$('#termBegin0'+j).val();
                    var termEnd0=$('#termEnd0'+j).val();
                    if (isValid) {
                        if (null == termBegin0 || "" == termBegin0) {
                            $("input[name='termBegin']").remove();
                            //$("#termBegin0" + j).val($("#termBegin0" + j).val() + "0000-00-00 00:00:00");
                        }
                        if (null == termEnd0 || "" == termEnd0) {
                            $("input[name='termEnd']").remove();
                            //$("#termEnd0" + j).val($("#termEnd0" + j).val() + "0000-00-00 00:00:00");
                        }

                        $("input[name='certDate']").remove();
                        $("input[name='brandTermBegin']").remove();
                        $("input[name='brandTermEnd']").remove();
                        formData = getFormData($("#SL24110107Form"+i));
                        formData = getFormData($("#SL241101070Form"+j));
                        $('#main-content').postUrl(Main.contextPath+ "/SL24110107/delete2",formData,function() {
                            //Modified by xia_xiaojie on 2016/6/23. Modified start.
                            //$('#main-content').postUrl(Main.contextPath + "/SL241101/init");
                            $('#main-content').postUrl(Main.contextPath + "/SL241101/initShow");
                            //Modified end.
                        });
                    }
                });
            })(j);
        }
    },
    bindUpdatebutton0:function(){
        for(var j=0;j<sl2411030073BeansSize;j++){
            (function (j) {
                $("#SL241101070_UPDATE" + j).click(function () {
                    var termBegin0=$('#termBegin0'+j).val();
                    var termEnd0=$('#termEnd0'+j).val();
                    if($("#SL241101070Form"+j).validateForm()) {
                        var dateMsg = "";
                        if (null == termBegin0 || "" == termBegin0) {
                            dateMsg += "授权期限开始日期不能为空<br>";
                           // $("input[name='termBegin']").remove();
                            //$("#termBegin0" + j).val($("#termBegin0" + j).val() + "0000-00-00 00:00:00");
                        } else {
                            $("#termBegin0Temp" + j).val($("#termBegin0" + j).val() + " 00:00:00");
                        }
                        if (null == termEnd0 || "" == termEnd0) {
                            dateMsg += "授权期限截止日期不能为空";
                            //$("input[name='termEnd']").remove();
                            //$("#termEnd0" + j).val($("#termEnd0" + j).val() + "0000-00-00 00:00:00");
                        } else {
                            $("#termEnd0Temp" + j).val($("#termEnd0" + j).val() + " 00:00:00");
                        }

                        if(dateMsg != null && dateMsg != ""){
                            $.alertMessage.info(dateMsg);
                            return;
                        }

                        formData = getFormData($("#SL241101070Form" + j));
                        var $uploadFile = $("#SL241101070Form"+j);
                        $.core.uploadForm($uploadFile,true);
                    }
                });
            })(j);
        }
    },
    bindDeleteButton : function(){
        for(var i=0;i<listSize;i++){
            (function(i){
                $("#SL24110107_DELETE"+i).click(function() {
                    var validator = mainValidation($("#SL24110107Form"+i));
                    var isValid = validator.form();
                    var certDatex=$("#certDatex"+i).val();
                    var brandTermBegin=$('#brandTermBegin'+i).val();
                    var brandTermEnd=$('#brandTermEnd'+i).val();
                    if (isValid) {
                        $("input[name='certDate']").remove();
                        $("input[name='brandTermBegin']").remove();
                        $("input[name='brandTermEnd']").remove();
                        formData = getFormData($("#SL24110107Form"+i));
                        $('#main-content').postUrl(Main.contextPath+ "/SL24110107/delete",formData,function() {
                            //Modified by xia_xiaojie on 2016/6/23. Modified start.
                            //           $('#main-content').postUrl(Main.contextPath + "/SL241101/init");
                            $('#main-content').postUrl(Main.contextPath + "/SL241101/initShow");
                            //Modified end.
                        });
                    }
                });
            })(i);
        }
    },
    bindDatePicber : function(orderTimeId){
        $(orderTimeId).datepicker({
            showButtonPanel: true,
            dateFormat:'yy-mm-dd',
            changeMonth: true,
            changeYear: true,
            closeText: 'Clear'
        });
    },
    closeDate : function(){
        $(document).on("click","button.ui-datepicker-close",function(){
            $.datepicker._clearDate($.datepicker._curInst.input);
        });
    },
    bindUpdatebutton : function() {
        for (var i = 0; i < listSize; i++) {
            (function (i) {
                $("#SL24110107_UPDATE" + i).click(function () {
                    var certDatex = $("#certDatex" + i).val();
                    var brandTermBegin=$('#brandTermBegin'+i).val();
                    var brandTermEnd=$('#brandTermEnd'+i).val();
                    if($("#SL24110107Form"+i).validateForm()) {
                        var dateMsg = "";
                        if (null == certDatex || "" == certDatex) {
                            dateMsg += "发证日期不能为空<br>";
                           // $("input[name='certDate']").remove();
                            //$("#certDatex" + i).val($("#certDatex" + i).val() + "0000-00-00 00:00:00");
                        } else {
                            $("#certDatexTemp" + i).val($("#certDatex" + i).val() + " 00:00:00");
                        }
                        if (null == brandTermBegin || "" == brandTermBegin) {
                            dateMsg += "有效期开始不能为空<br>";
                           // $("input[name='brandTermBegin']").remove();
                            //$("#brandTermBegin" + i).val($("#brandTermBegin" + i).val() + "0000-00-00 00:00:00");
                        } else {
                            $("#brandTermBeginTemp" + i).val($("#brandTermBegin" + i).val() + " 00:00:00");
                        }
                        if (null == brandTermEnd || "" == brandTermEnd) {
                            dateMsg += "有效期截止不能为空";
                            //$("input[name='brandTermEnd']").remove();
                           // $("#brandTermEnd" + i).val($("#brandTermEnd" + i).val() + "0000-00-00 00:00:00");
                        } else {
                            $("#brandTermEndTemp" + i).val($("#brandTermEnd" + i).val() + " 00:00:00");
                        }

                        if(dateMsg != null && dateMsg != ""){
                            $.alertMessage.info(dateMsg);
                            return;
                        }

                        formData = getFormData($("#SL24110107Form" + i));
                        var $uploadFile = $("#SL24110107Form"+i);
                        $.core.uploadForm($uploadFile,true);
                    }
                });
            })(i);
        }
    }
}

$(document).ready(function() {
    SL24110107.init();
});