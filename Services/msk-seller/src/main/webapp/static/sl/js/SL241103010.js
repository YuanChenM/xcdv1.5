/**
 * Created by fjm on 2016/1/30.
 */
var SL241103010 = {
    formId:"SL241103010Form",
    saveButton:"SL241103010_SAVE",
    init : function(){
        this.bindDatePicber("#authTermBeginThree");
        this.bindDatePicber("#authTermEndTwo");
        this.closeDate();
        this.saveData();
        this.agentCheck();
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
    saveData:function(){
        $("#"+SL241103010.saveButton).click(function(){
                if($("#" + SL241103010.formId).validateForm()) {
                    var authTermBeginThree = $("#authTermBeginThree").val();
                    var dateMsg = "";
                    if (authTermBeginThree == "" || authTermBeginThree == null) {
                        dateMsg += "授权期限开始不能为空<br>";
                        //$("input[name='authTermBegin']").remove();
                        //$("#authTermBeginThree").val($("#authTermBeginThree").val() + "0000-00-00 00:00:00");
                    } else {
                        $("#authTermBeginThreeTemp").val($("#authTermBeginThree").val() + " 00:00:00");
                    }
                    var authTermEndTwo = $("#authTermEndTwo").val();
                    if (authTermEndTwo == "" || authTermEndTwo == null) {
                        //$("input[name='authTermEnd']").remove();
                        dateMsg += "授权期限结束不能为空";
                        // $("#authTermEndTwo").val($("#authTermEndTwo").val() + "0000-00-00 00:00:00");
                    } else {
                        $("#authTermEndTwoTemp").val($("#authTermEndTwo").val() + " 00:00:00");
                    }

                    var producerEpId = $("#producerEpId").val()
                    if(producerEpId == "" || producerEpId == null){
                        dateMsg += "生产商不能为空";
                    }

                    if(dateMsg != null && dateMsg != ""){
                        $.alertMessage.info(dateMsg);
                        return;
                    }

                    // 添加  jsp_slCode
                    var $uploadFile = $("#SL241103010Form");
                    var jsp_slCode = $("input#jsp_slCode").val();
                    var jspSlCode = "<input type='hidden' id='jspSL241103010_slCode' name='jspSL241103010_slCode' value = '" + jsp_slCode + "' />";
                    $uploadFile.append(jspSlCode);

                    formData = getFormData($($uploadFile));
                    $('#main-content').postUrl($("#" + SL241103010.formId).attr("action"),
                        formData,
                        function (data) {
                            if (data == "1") {
                                $.alertMessage.info("添加成功!");
                            } else {
                                $.alertMessage.info("添加失败!");
                            }
                        }, {refreshHtml: false});

                    // 去除   jsp_slCode
                    $("input[name='jspSL241103010_slCode']").remove();
                }
        });
    },
    agentCheck:function(){
        $("#agentCheck").click(function(){
            $.pdialog.open("查询生产商", Main.contextPath + "/SL24110301001/init", {
                    width: 1000,
                    height: 450
                }
            );
        });
    }
}

$(document).ready(function() {
    SL241103010.init();
});

