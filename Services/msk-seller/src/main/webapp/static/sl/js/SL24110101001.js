/**
 * Created by fjm on 2016/1/30.
 */
var SL24110101001 = {
    formId:"SL24110101001Form",
    saveButton:"SL24110101001_SAVE",
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
        $("#"+SL24110101001.saveButton).click(function(){
                if($("#" + SL24110101001.formId).validateForm()) {
                    var authTermBeginThree = $("#authTermBeginThree").val();
                    var dateMsg = "";
                    if (authTermBeginThree == "" || authTermBeginThree == null) {
                        //$("input[name='authTermBegin']").remove();
                        dateMsg += "授权期限开始不能为空<br>";
                        //$("#authTermBeginThree").val($("#authTermBeginThree").val() + "0000-00-00 00:00:00");
                    } else {
                        $("#authTermBeginThreeTemp").val($("#authTermBeginThree").val() + " 00:00:00");
                    }
                    var authTermEndTwo = $("#authTermEndTwo").val();
                    //$("input[name='authTermBegin']").remove();
                    if (authTermEndTwo == "" || authTermEndTwo == null) {
                       // $("input[name='authTermEnd']").remove();
                        dateMsg += "授权期限结束不能为空";
                        //$("#authTermEndTwo").val($("#authTermEndTwo").val() + "0000-00-00 00:00:00");
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

                    var $uploadFile = $("#" + SL24110101001.formId);
                    // 添加 jsp_epId2  jsp_slCode
                    var jsp_epIdUp2 = $("input#jsp_epId2").val();
                    var jsp_slCodeUp = $("input#jsp_slCode").val();
                    var jspEpIdUp2 = "<input type='hidden' id='jsp_epIdUp2' name='jsp_epIdUp2' value = '" + jsp_epIdUp2 + "' />";
                    var jspSlCodeUp = "<input type='hidden' id='jsp_slCodeUp' name='jsp_slCodeUp' value = '" + jsp_slCodeUp + "' />";
                    $uploadFile.append(jspEpIdUp2);
                    $uploadFile.append(jspSlCodeUp);

                    formData = getFormData($uploadFile);
                    $('#main-content').postUrl($("#" + SL24110101001.formId).attr("action"),
                        formData,
                        function (data) {
                            if (data == "1") {
                                $.alertMessage.info("添加成功!");
                                $.pdialog.close();
                            } else {
                                $.alertMessage.info("添加失败!");
                            }
                        }, {refreshHtml: false});

                    // 去除 jsp_epId2 jsp_slCode
                    $("input[name='jsp_epIdUp2']").remove();
                    $("input[name='jsp_slCodeUp']").remove();
                }
        });
    },
    agentCheck:function(){
        $("#agentCheck").click(function(){
            $.pdialog.open("查询生产商", Main.contextPath + "/SL24110301001/init", {
                    width: 1000,
                    height: 450
                },{flg:1},"showEp"
            );
        });
    }
}

$(document).ready(function() {
    SL24110101001.init();
});

