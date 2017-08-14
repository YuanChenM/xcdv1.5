/**
 * Created by fjm on 2016/1/26.
 */
var SL2411030071 = {

    formId : "SL2411030071Form",
    formId2 : "SL24110300711Form",
    saveButtonId : "SL2411030071_SAVE",
    saveButtonId2:"SL24110300711_SAVE",
    init : function(){
        this.bindSavebutton();
        this.bindDatePicber('#certDate71');
        this.bindDatePicber('#termBegin71');
        this.bindDatePicber('#termEnd71');
        this.bindDatePicber('#authTermBegin711');
        this.bindDatePicber('#authTermEnd711');
        this.closeDate();
        this.changeSelect();

    },
    changeSelect:function(){
        var pdClasses=$('#prolist');
        var pdBreed=$('#brandlist');
        pdClasses.change(function(){
            var pdClassesVal=pdClasses.val();
            var pdBreedVal=pdBreed.val();
            $('#main-content').postUrl(Main.contextPath + "/SL241103000/findBrand",{'filterMap[producerEpId]':pdClassesVal},function(data){
                $('#classesName').val(pdClasses.find("option:selected").text());
                pdBreed.html('');
                pdBreed.append("<option value=''>请选择</option>");
                $.each(data, function(i, item) {
                    if (item.brandId === pdBreedVal) {
                        pdBreed.append("<option selected='selected' value='" + item.brandName + "'>"+ item.brandName+ "</option>");
                    } else {
                        pdBreed.append("<option value='" + item.brandName + "'>"+ item.brandName+ "</option>");
                    }
                });
            },{refreshHtml:false});
        });
    },
    bindDatePicber : function(orderTimeId){
        $(orderTimeId).datepicker({
            showButtonPanel: true,
            dateFormat:'yy-mm-dd',
            changeMonth: true,
            changeYear: true,
            closeText: 'Clear'
        });

        $(orderTimeId).change(function(){
            var str = $(orderTimeId).val();
            if(str.length >= 8){
                // 判断年月日的格式2010-01-01
                var reg = /^(\d{4})-(0\d{1}|1[0-2])-(0\d{1}|[12]\d{1}|3[01])$/;
                if(reg.test(str)){
                    if(orderTimeId.endsWith("End711")){
                        var startTime = $(orderTimeId.substring(0,orderTimeId.length-6) + "Begin711").val();
                        var endTime = $(orderTimeId).val();
                        if(startTime != null && startTime != 'underfined' && startTime != '' && startTime>endTime){
                            $(orderTimeId).val("");
                            $.alertMessage.info("输入正确的时间范围");
                        }
                    }
                    if(orderTimeId.endsWith("Begin711")){
                        var startTime = $(orderTimeId).val();
                        var endTime = $(orderTimeId.substring(0,orderTimeId.length-8) + "End711").val();
                        if(endTime != null && endTime != 'underfined' && endTime != '' && startTime>endTime){
                            $(orderTimeId).val("");
                            $.alertMessage.info("输入正确的时间范围");
                        }
                    }
                    if(orderTimeId.endsWith("End71")){
                        var startTime = $(orderTimeId.substring(0,orderTimeId.length-5) + "Begin71").val();
                        var endTime = $(orderTimeId).val();
                        if(startTime != null && startTime != 'underfined' && startTime != '' && startTime>endTime){
                            $(orderTimeId).val("");
                            $.alertMessage.info("输入正确的时间范围");
                        }
                    }
                    if(orderTimeId.endsWith("Begin71")){
                        var startTime = $(orderTimeId).val();
                        var endTime = $(orderTimeId.substring(0,orderTimeId.length-7) + "End71").val();
                        if(endTime != null && endTime != 'underfined' && endTime != '' && startTime>endTime){
                            $(orderTimeId).val("");
                            $.alertMessage.info("输入正确的时间范围");
                        }
                    }
                }else{
                    $(orderTimeId).val("");
                }
            }else{
                $(orderTimeId).val("");
            }

        });
    },
    closeDate : function(){
        $(document).on("click","button.ui-datepicker-close",function(){
            $.datepicker._clearDate($.datepicker._curInst.input);
        });
    },
    bindSavebutton : function() {
        $("#" + SL2411030071.saveButtonId).click(function() {
            SL2411030071.uploadData();
        });
        $("#" + SL2411030071.saveButtonId2).click(function() {
            SL2411030071.saveData2();
        });
    },
    saveData2 : function(){
        var authTermBegin711=$("#authTermBegin711").val();
        var authTermEnd711=$("#authTermEnd711").val();
        if($("#" + SL2411030071.formId2).validateForm()) {
            var dateMsg = "";
            if(authTermBegin711==null || authTermBegin711==""){
                dateMsg += "授权期限开始日期不能为空<br>";
                //$("input[name='authTermBegin']").remove();
                //$("#authTermBegin711").val($("#authTermBegin711").val()+"0000-00-00 00:00:00");
            }else{
                $("#authTermBegin711Temp").val($("#authTermBegin711").val()+" 00:00:00");
            }

            if(authTermEnd711==null || authTermEnd711==""){
                dateMsg += "授权期限截止日期不能为空";
               // $("input[name='authTermEnd']").remove();
               // $("#authTermEnd711").val($("#authTermEnd711").val()+"0000-00-00 00:00:00");
            }else{
                $("#authTermEnd711Temp").val($("#authTermEnd711").val()+" 00:00:00");
            }

            if(dateMsg != null && dateMsg != ""){
                $.alertMessage.info(dateMsg);
                return;
            }

            var $uploadFile = $("#"+SL2411030071.formId2);
            // 添加   jsp_slCode
            var jsp_slCodeUp = $("input#jsp_slCode").val();
            var jspSlCodeUp  =  "<input type='hidden' id='jsp_slCodeUp' name='jsp_slCodeUp' value = '"+jsp_slCodeUp+"' />";
            $uploadFile.append(jspSlCodeUp);

            $.core.uploadForm($uploadFile, true);

            // 去除  jsp_slCode
            $("input[name='jsp_slCodeUp']").remove();
        };
    },
    uploadData : function() {
        if ($("#" + SL2411030071.formId).validateForm()) {
            var $uploadFile = $("#SL2411030071Form");
            var certDate71 = $("#certDate71").val();
            var termBegin71 = $("#termBegin71").val();
            var termEnd71 = $("#termEnd71").val();
            var dateMsg = "";
            if (certDate71 == null || certDate71 == "") {
                dateMsg += "发证日期不能为空<br>";
                //$("input[name='certDate']").remove();
                //$("#certDate71").val($("#certDate71").val()+"0000-00-00 00:00:00");
            } else {
                $("#certDate71Temp").val($("#certDate71").val() + " 00:00:00");
            }
            if (termBegin71 == null || termBegin71 == "") {
                dateMsg += "有效期开始不能为空<br>";
                //$("input[name='termBegin']").remove();
                //$("#termBegin71").val($("#termBegin71").val()+"0000-00-00 00:00:00");
            } else {
                $("#termBegin71Temp").val($("#termBegin71").val() + " 00:00:00");
            }
            if (termEnd71 == null || termEnd71 == "") {
                dateMsg += "有效期截止不能为空";
                //$("input[name='termEnd']").remove();
                //$("#termEnd71").val($("#termEnd71").val()+"0000-00-00 00:00:00");
            } else {
                $("#termEnd71Temp").val($("#termEnd71").val() + " 00:00:00");
            }
            //if ($("#brandNo2").val() == null || $("#brandNo2").val() == "") {
            //    $.alertMessage.info("商标注册证编码不能为空!");
            //    return;
            //}

            if(dateMsg != null && dateMsg != ""){
                $.alertMessage.info(dateMsg);
                return;
            }

            // 添加 jsp_epId2  jsp_slCode
            var jsp_epIdUp2 = $("input#jsp_epId2").val();
            var jsp_slCodeUp = $("input#jsp_slCode").val();
            var jspEpIdUp2 = "<input type='hidden' id='jsp_epIdUp2' name='jsp_epIdUp2' value = '" + jsp_epIdUp2 + "' />";
            var jspSlCodeUp = "<input type='hidden' id='jsp_slCodeUp' name='jsp_slCodeUp' value = '" + jsp_slCodeUp + "' />";
            $uploadFile.append(jspEpIdUp2);
            $uploadFile.append(jspSlCodeUp);
            $.core.uploadForm($uploadFile, true);
            // 去除 jsp_epId2 jsp_slCode
            $("input[name='jsp_epIdUp2']").remove();
            $("input[name='jsp_slCodeUp']").remove();
        }
    }
}

$(document).ready(function() {
    SL2411030071.init();
});
