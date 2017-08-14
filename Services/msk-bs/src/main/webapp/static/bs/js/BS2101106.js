
var BS2101106 = {
    formId: "BS2101106Form",
    saveButtonId: "BS2101106_SAVE",
    init: function () {
        this.changeSelect();
        this.bindSavebutton();
    },
    changeSelect : function () {
        var citySelect = $('#city1');
        var districtSelect = $('#district1');
        $("#province1").change(function () {
            $("#city1").find("option").not(":first").remove();
            $("#district1").find("option").not(":first").remove();
            var provinceCode = $('#province1').val();
            if (provinceCode != 0) {
                $('#province_sp').html("");
                $('#main-content').postUrl(Main.contextPath + '/BS2101105/findCity', {provinceCode: provinceCode},
                    function (data) {
                        $.each(data, function (i, item) {
                            citySelect.append("<option value='" + item.cityCode + "'>" + item.cityName + "</option>");
                        });
                    }, {refreshHtml: false});
            }
        });
        $('#city1').change(function () {
            $("#district1").find("option").not(":first").remove();
            var cityCode = $("#city1").val();
            if (cityCode != 0) {
                $('#city_sp').html("");
                $('#main-content').postUrl(Main.contextPath + '/BS2101105/findDistrict', {cityCode: cityCode},
                    function (data2) {
                    $.each(data2, function (i, item) {
                        districtSelect.append("<option value='" + item.districtCode + "'>" + item.districtName + "</option>");
                    });
                }, {refreshHtml: false})
            }
        });
        $("#district1").change(function () {
            var district = $("#district1").val();
            if(district !=0){
                $('#district_sp').html("");
            }
        });

        var classTwoLevel = $('#classTwoLevel');
        $('#classOneLevel').change(function () {
            $("#classTwoLevel").find("option").not(":first").remove();
            $("#classThreeLevel").find("option").not(":first").remove();
            var parentTypeCode = $("#classOneLevel").val();
            if (parentTypeCode != 0) {
                $('#classOneLevel_sp').html("");
                $('#main-content').postUrl(Main.contextPath + '/BS2101106/findBuyersClassLevel', {parentTypeCode: parentTypeCode,typeLever:1},
                    function (data2) {
                        $.each(data2, function (i, item) {
                            classTwoLevel.append("<option value='" + item.typeCode + "'>" + item.typeName + "</option>");
                        });
                    }, {refreshHtml: false})
            }
        });
        var classThreeLevel = $('#classThreeLevel');
        $('#classTwoLevel').change(function () {
            $("#classThreeLevel").find("option").not(":first").remove();
            var parentTypeCode = $("#classTwoLevel").val();
            if (parentTypeCode != 0) {
                $('#classTwoLevel_sp').html("");
                $('#main-content').postUrl(Main.contextPath + '/BS2101106/findBuyersClassLevel', {parentTypeCode: parentTypeCode,typeLever:2},
                    function (data2) {
                        $.each(data2, function (i, item) {
                            classThreeLevel.append("<option value='" + item.typeCode + "'>" + item.typeName + "</option>");
                        });
                    }, {refreshHtml: false})
            }
        });
        $("#classThreeLevel").change(function () {
            var classThreeLevel =$("#classThreeLevel").val();
            if(classThreeLevel !=0){
                $('#classThreeLevel_sp').html("");
            }
        });
    },
    bindSavebutton : function() {
        $("#" + BS2101106.saveButtonId).click(function() {
            BS2101106.saveData();
        });
    },
    saveData : function() {
        var validator = mainValidation($("#" + BS2101106.formId));
        var isValid = validator.form();
    /*    var memo6 = $('input[name="memo6"]').filter(':checked').val();
        if (isNaN(memo6))
        {
            $('#memo6_sp').html('<font style="color:red">请选择买手类型！</font>');
            return;
        };*/
        var lgcsArea1 = $("lgcsArea1").val();
        var province1 = $("#province1").val();
        var city1 =$("#city1").val();
        var district1 =$("#district1").val();
        if(lgcsArea1 == 0){
            $('#lgcsArea_sp').html('<font style="color:red">请选择物流区！</font>');
            return;
        }
        if(province1 == 0){
            $('#province_sp').html('<font style="color:red">请选择省份！</font>');
            return;
        }
        if(city1 == 0){
            $('#city_sp').html('<font style="color:red">请选择地区！</font>');
            return;
        }
        if(district1 == 0){
            $('#district_sp').html('<font style="color:red">请选择区县！</font>');
            return;
        }
        var classOneLevel = $("#classOneLevel").val();
        var classTwoLevel =$("#classTwoLevel").val();
        var classThreeLevel =$("#classThreeLevel").val();
        if(classOneLevel == 0){
            $('#classOneLevel_sp').html('<font style="color:red">请选择买手一级分类！</font>');
            return;
        }
        if(classTwoLevel == 0){
            $('#classTwoLevel_sp').html('<font style="color:red">请选择买手二级分类！</font>');
            return;
        }
        if(classThreeLevel == 0){
            $('#classThreeLevel_sp').html('<font style="color:red">请选择买手三级分类！</font>');
            return;
        }
        if(slAddressTest()){
        if (isValid) {
            formData = getFormData($("#" + BS2101106.formId));
            var $uploadFile = $("#BS2101106Form");
            $.core.uploadForm($uploadFile, true);
        };
        }
    }
}

function slAddressTest(){
    var slAddress = $("#slAddress").val();
    if(slAddress ==''){
        $("#slAddress_sp").html('<font style="color:red">买手地址不能为空！</font>')
        return false;
    }else{
        $("#slAddress_sp").html("");
        return true;
    }
}

$(document).ready(function() {
    BS2101106.init();
});


