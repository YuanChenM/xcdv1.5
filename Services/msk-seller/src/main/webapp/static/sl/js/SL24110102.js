/**
 * Created by fjm on 2016/1/28.
 */

var SL24110102 = {

    formId : "SL24110102Form",
    saveButtonId : "SL24110102_SAVE",

    init : function(){
        this.bindSavebutton();
        this.bindDatePicber('#licCrtDate');
        this.bindDatePicber('#licTermBegin');
        this.bindDatePicber('#licTermEnd');
        this.bindDatePicber('#orgTermBegin');
        this.bindDatePicber('#orgTermEnd');
        this.bindDatePicber('#fdlTermBegin');
        this.bindDatePicber('#fdlTermEnd');
        this.closeDate();
        this.changeSelect();
        this.checkRadio();
    },
    changeSelect:function(){
        var citySelect=$('#city_select');
        var districtSelect=$('#district_select');
        $('#province_select').change(function () {
            $("#province_select").find("option[selected='selected']").attr("selected",false);
            $("#city_select").find("option").not(":first").remove();
            $("#district_select").find("option").not(":first").remove();
            var provinceCode = $('#province_select').val();
            if(provinceCode!=0){
                $('#main-content').postUrl(Main.contextPath + '/SL241103000/findCity',{provinceCode:provinceCode},
                    function(data){
                        $.each(data, function(i, item) {
                            citySelect.append("<option value='" + item.cityCode+ "'>"+ item.cityName+ "</option>");
                        });
                    },{refreshHtml:false});
            }
        });
        $('#city_select').change(function(){
            $("#district_select").find("option").not(":first").remove();
            var cityCode=$("#city_select").val();
            if(cityCode!=0){
                $('#main-content').postUrl(Main.contextPath + '/SL241103000/findDistrict',{cityCode:cityCode},function(data2){
                    $.each(data2, function(i, item) {
                        districtSelect.append("<option value='" + item.districtCode+ "'>"+ item.districtName+ "</option>");
                    });
                },{refreshHtml:false})
            }
        });
    },
    bindDatePicber: function (orderTimeId) {
        $(orderTimeId).datepicker({
            showButtonPanel: true,
            dateFormat: 'yy-mm-dd',
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
    checkRadio : function() {
        var slMainClassHid = $("#slMainClassHid").val();
        if(slMainClassHid == "1" || slMainClassHid == "0"){
            $("input[name='slSecondaryClass'][value='1']").prop("checked",true);
            $("input[name='slSecondaryClass'][value='1']").attr("disabled",true);
        } else if(slMainClassHid == "2"){
            $("input[name='slSecondaryClass'][value='2']").prop("checked",true);
            $("input[name='slSecondaryClass'][value='2']").attr("disabled",true);
        } else if(slMainClassHid == "3"){
            $("input[name='slSecondaryClass'][value='3']").prop("checked",true);
            $("input[name='slSecondaryClass'][value='3']").attr("disabled",true);
        }

        $("input[name='slMainClass']").click(function(){
            $(this).attr("checked","checked");
            var dataValue=$(this).attr("value");
            if(dataValue==0){
                $("input[name='slSecondaryClass'][value='1']").prop("checked",true);
                $("input[name='slSecondaryClass'][value='2']").prop("checked",false);
                $("input[name='slSecondaryClass'][value='3']").prop("checked",false);

                $("input[name='slSecondaryClass'][value='1']").attr("disabled",true);
                $("input[name='slSecondaryClass'][value='2']").attr("disabled",false);
                $("input[name='slSecondaryClass'][value='3']").attr("disabled",false);
            }
            if(dataValue==1){
                $("input[name='slSecondaryClass'][value='1']").prop("checked",true);
                $("input[name='slSecondaryClass'][value='2']").prop("checked",false);
                $("input[name='slSecondaryClass'][value='3']").prop("checked",false);

                $("input[name='slSecondaryClass'][value='1']").attr("disabled",true);
                $("input[name='slSecondaryClass'][value='2']").attr("disabled",false);
                $("input[name='slSecondaryClass'][value='3']").attr("disabled",false);
            }
            if(dataValue==2){
                $("input[name='slSecondaryClass'][value='2']").prop("checked",true);
                $("input[name='slSecondaryClass'][value='1']").prop("checked",false);
                $("input[name='slSecondaryClass'][value='3']").prop("checked",false);

                $("input[name='slSecondaryClass'][value='1']").attr("disabled",false);
                $("input[name='slSecondaryClass'][value='2']").attr("disabled",true);
                $("input[name='slSecondaryClass'][value='3']").attr("disabled",false);
            }
            if(dataValue==3){
                $("input[name='slSecondaryClass'][value='3']").prop("checked",true);
                $("input[name='slSecondaryClass'][value='2']").prop("checked",false);
                $("input[name='slSecondaryClass'][value='1']").prop("checked",false);

                $("input[name='slSecondaryClass'][value='1']").attr("disabled",false);
                $("input[name='slSecondaryClass'][value='2']").attr("disabled",false);
                $("input[name='slSecondaryClass'][value='3']").attr("disabled",true);
            }
        });
        $("input[name='licType']").click(function(){
            if($(this).val()==1){
                $("#epthrfileId").attr("disabled",false);
                $("#licfileId").attr("disabled",true);
                $("#taxfileId").attr("disabled",true);
                $("#orgfileId").attr("disabled",true);
            }else if($(this).val()==0){
                $("#epthrfileId").attr("disabled",true);
                $("#licfileId").attr("disabled",false);
                $("#taxfileId").attr("disabled",false);
                $("#orgfileId").attr("disabled",false);
            }
        });
    },
    bindSavebutton : function() {
        $("#" + SL24110102.saveButtonId).click(function() {
            SL24110102.saveData();
        });
    },
    saveData : function() {
        var licCrtDate=$("#licCrtDate").val();
        var licBegin=$("#licTermBegin").val();
        var licEnd=$("#licTermEnd").val();
        var orgTermBegin=$("#orgTermBegin").val();
        var orgTermEnd =$("#orgTermEnd").val();
        var fdlTermBegin=$("#fdlTermBegin").val();
        var fdlTermEnd=$("#fdlTermEnd").val();

        if($("#" + SL24110102.formId).validateForm()) {
            var dateMsg = "";
            if(licCrtDate==null || licCrtDate==""){
                dateMsg += "成立日期不能为空<br>";
                //$("input[name='licCrtDate']").remove();
                //$("#licCrtDate").val($("#licCrtDate").val()+"0000-00-00 00:00:00");
            }else{
                $("#licCrtDateTemp").val($("#licCrtDate").val()+" 00:00:00");
            }

            if(licBegin==null || licBegin==""){
                dateMsg += "营业开始日期不能为空<br>";
                //$("input[name='licTermBegin']").remove();
               // $("#licTermBegin").val($("#licTermBegin").val()+"0000-00-00 00:00:00");
            }else{
                $("#licTermBeginTemp").val($("#licTermBegin").val()+" 00:00:00");
            }

            if(licEnd==null || licEnd==""){
                dateMsg += "营业截止日期不能为空<br>";
                //$("input[name='licTermEnd']").remove();
               // $("#licTermEnd").val($("#licTermEnd").val()+"0000-00-00 00:00:00");
            }else{
                $("#licTermEndTemp").val($("#licTermEnd").val()+" 00:00:00");
            }

            if(orgTermBegin==null || orgTermBegin==""){
                dateMsg += "组织代码证开始日期不能为空<br>";
               // $("input[name='orgTermBegin']").remove();
                //$("#orgTermBegin").val($("#orgTermBegin").val()+"0000-00-00 00:00:00");
            }else{
                $("#orgTermBeginTemp").val($("#orgTermBegin").val()+" 00:00:00");
            }

            if(orgTermEnd==null || orgTermEnd==""){
                dateMsg += "组织代码证截止日期不能为空<br>";
                //$("input[name='orgTermEnd']").remove();
                //$("#orgTermEnd").val($("#orgTermEnd").val()+"0000-00-00 00:00:00");
            }else{
                $("#orgTermEndTemp").val($("#orgTermEnd").val()+" 00:00:00");
            }

            if(fdlTermBegin==null || fdlTermBegin==""){
                dateMsg += "食品流通许可证_开始日期不能为空<br>";
                //$("input[name='fdlTermBegin']").remove();
               // $("#fdlTermBegin").val($("#fdlTermBegin").val()+"0000-00-00 00:00:00");
            }else{
                $("#fdlTermBeginTemp").val($("#fdlTermBegin").val()+" 00:00:00");
            }

            if(fdlTermEnd==null || fdlTermEnd==""){
                dateMsg += "食品流通许可证_截止日期不能为空";
                //$("input[name='fdlTermEnd']").remove();
               // $("#fdlTermEnd").val($("#fdlTermEnd").val()+"0000-00-00 00:00:00");
            }else{
                $("#fdlTermEndTemp").val($("#fdlTermEnd").val()+" 00:00:00");
            }

            if(dateMsg != null && dateMsg != ""){
                $.alertMessage.info(dateMsg);
                return;
            }

            formData = getFormData($("#" + SL24110102.formId));
            var $uploadFile = $("#SL24110102Form");
            $.core.uploadForm($uploadFile, true);
        };
    }
}

$(document).ready(function() {
    SL24110102.init();
});


