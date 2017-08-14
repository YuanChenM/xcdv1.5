/**
 * 买家配送信息编辑
 * Created by zhou_yajun on 2016/7/5.
 */
var BY1213141 = {
    saveButton : "BY1213141_SAVE",
    initDataGrid : function () {
        this.saveHandler();
        this.selectChange();
    },
    //批发市场基本信息保存
    saveHandler: function(){
        $("#" + BY1213141.saveButton).click(function(){
            if($("#BY1213141SaveForm").validateForm()){
            if(!BY1213141.showErrorMessage()){
                return false;
            }
            var provinceName = $("#provinceCode option:selected").text();
            var cityName = $("#cityCode option:selected").text();
            var districtName = $("#districtCode option:selected").text();
            //设置选中省，市，区县的名称
            $("#provinceName").val(provinceName);
            $("#cityName").val(cityName);
            $("#districtName").val(districtName);
            var formData = getFormData($("#BY1213141SaveForm"));
            $('#main-content').postUrl($("#BY1213141SaveForm").attr("action"), formData, function(){
                $.pdialog.close();
                BY121314.BY121314_Grid.fnDraw();
            },{refreshHtml: false});

          }
        })
    },
    //简单必须项check
    showErrorMessage: function(){
        var regPhone=/^1[34578]\d{9}$/;
        var deliveryAddr = $("#deliveryAddr").val();
        var recPerName = $("#recPerName").val();
        var recPerTel = $("#recPerTel").val();
        var provinceCode = $("#provinceCode option:selected").val();
        var cityCode = $("#cityCode option:selected").val();
        var districtCode = $("#districtCode option:selected").val();
        if(provinceCode == "999"){
            $.alertMessage.info("请选择省份。", function () {
                $.alertMessage.close();
            });
            return false;
        }
        if(provinceCode == "999"){
            $.alertMessage.info("请选择省份。", function () {
                $.alertMessage.close();
            });
            return false;
        }
        if(cityCode == "999"){
            $.alertMessage.info("请选择城市。", function () {
                $.alertMessage.close();
            });
            return false;
        }
        if(districtCode == ""){
            $.alertMessage.info("请选择区(县)。", function () {
                $.alertMessage.close();
            });
            return false;
        }

      /*  if(deliveryAddr == ""){
            $.alertMessage.info("请填写配送详细地址。", function () {
                $.alertMessage.close();
            });
            return false;
        }
        if(recPerName == ""){
            $.alertMessage.info("请填写收货人。", function () {
                $.alertMessage.close();
            });
            return false;
        }
        if(recPerTel == ""){
            $.alertMessage.info("请填写收货人联系电话。", function () {
                $.alertMessage.close();
            });
            return false;
        }*/
        if(!regPhone.test(recPerTel) && !recPerTel.isPhone()){
            $.alertMessage.info("收货人联系电话有误。", function () {
                $.alertMessage.close();
            });
            return false;
        }
        if($("#deliveryArea").is(":visible")){
            var deliveryArea = $("#shOdDeliveryArea option:selected").val();
            if(deliveryArea == ""){
                $.alertMessage.info("请选择订单配送区域。", function () {
                    $.alertMessage.close();
                });
                return false;
            }
        }else{
            $("#shOdDeliveryArea option:selected").val(null);
        }
        return true;
    },
    selectChange: function(){
        //物流区变更获取相应的城市信息
        $("#provinceCode").change(function(){
            var provinceCode = $("#provinceCode option:selected").val();
            if(provinceCode != '01'){
                $("#deliveryArea").css("display","none");
            }else{
                $("#deliveryArea").removeAttr("style");
            }
            $('#main-content').postUrl(Main.contextPath + "/BY1213141/provinceChange/" + provinceCode,null,
                function(data){
                    $("#cityCode").html("");
                    $("#cityCode").append("<option value=''>--请选择--</option>");
                    $("#districtCode").html("");
                    $("#districtCode").append("<option value=''>--请选择--</option>");
                    $.each(data,function(i,item){
                        $("#cityCode").append("<option value='" + item.cityCode + "'>"+ item.cityName+ "</option>");
                    });
                },{refreshHtml:false});
        });
        //城市变更获取相应的区县信息
        $("#cityCode").change(function(){
            var cityCode = $("#cityCode option:selected").val();
            $("#districtCode").html("");
            $("#districtCode").append("<option value=''>--请选择--</option>");
            $('#main-content').postUrl(Main.contextPath + "/BY1213141/cityChange/" + cityCode,null,
                function(data){
                    $.each(data,function(i,item){
                        $("#districtCode").append("<option value='" + item.districtCode + "'>"+ item.districtName+ "</option>");
                    });
                },{refreshHtml:false});
        });
    }
}

$(document).ready(function(){
    BY1213141.initDataGrid();
})