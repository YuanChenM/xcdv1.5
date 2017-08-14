/**
 * Created by fjm on 2016/1/27.
 */
var SL2411010401 = {
    init : function(){
        this.bindUpdateButton();
        this.bindAddbutton();
        for(var i=0;i<slepHonorList;i++) {
            this.bindDatePicber('#certDate'+i);
        };
        this.closeDate();
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
    bindAddbutton : function(){
        $("#SL2411010401_ADD").click(function(){
            $.pdialog.open("企业荣誉信息添加", Main.contextPath + "/SL2411030040101/init",{width:600,height:600});
        });
    },
    bindUpdateButton : function() {
        for(var i=0;i<slepHonorList;i++){
            (function(i){
                $("#SL2411010401_UPDATE"+i).click(function() {
                    if($("#SL2411010401Form"+i).validateForm()) {
                        var certDate=$("#certDate" + i).val();
                        if(certDate==null || certDate==""){
                          //  $("input[name='certDate']").remove();
                            $.alertMessage.info("荣誉证书发证日期不能为空");
                            return;
                            //$("#certDate"+i).val($("#certDate"+i).val()+"0000-00-00 00:00:00");
                        }else{
                            $("#certDateTemp"+i).val($("#certDate"+i).val()+" 00:00:00");
                        }
                        var $uploadFile = $("#SL2411010401Form"+i);
                        $.core.uploadForm($uploadFile,true);
                    }
                });
                $("#SL2411010401_DELETE"+i).click(function() {
                    var validator = mainValidation($("#SL2411010401Form"+i));
                    var isValid = validator.form();
                    var certDate=$("#certDate" + i).val();
                    if (isValid) {
                        if(certDate==null || certDate==""){
                            $("input[name='certDate']").remove();
                            //$("#certDate"+i).val($("#certDate"+i).val()+"0000-00-00 00:00:00");
                        }else{
                            $("#certDateTemp"+i).val($("#certDate"+i).val()+" 00:00:00");
                        }
                        formData = getFormData($("#SL2411010401Form"+i));
                        $('#main-content').postUrl(
                            Main.contextPath + "/SL2411010401/delete",
                            formData,
                            function() {
                                //Modified by xia_xiaojie on 2016/6/23. Modified start.
                                //$('#main-content').postUrl(Main.contextPath + "/SL241101/init");
                                $('#main-content').postUrl(Main.contextPath + "/SL241101/initShow");
                                //Modified end.
                            });
                    }
                });

            })(i);
        }
    }
}

$(document).ready(function() {
    SL2411010401.init();
});
