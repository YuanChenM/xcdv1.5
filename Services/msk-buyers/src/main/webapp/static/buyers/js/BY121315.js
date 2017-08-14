/**
 * 买家上线状态
 * Created by zhou_yajun on 2016/7/20.
 */
var BY121315 = {
    saveButton : "BY121315_SAVE",
    initDataGrid : function () {
        this.saveHandler();
    },
    //批发市场基本信息保存
    saveHandler: function(){
        $("#" + BY121315.saveButton).click(function(){
            if(!BY121315.dataCheck()){
                return false;
            }
            if($("#exception31").is(":checked")){
                var marketingsStatus = $("#exception31").val();
                if(marketingsStatus != "" && marketingsStatus != null){
                    $("#marketingsStatus").attr("name","marketStatus");
                }
            }
            var formData = getFormData($("#BY121315SaveForm"));
            $('#main-content').postUrl($("#BY121315SaveForm").attr("action"), formData, function(data){
                if(data.status == "S"){
                    $.alertMessage.info("买家上线状态变更成功！",function(){
                        $.alertMessage.close();
                    });
                }
            },{refreshHtml:false});
        })
    },
    //简单必须项check
    dataCheck: function(){
        var marketExceptionRemark = $("#marketExceptionRemark").val();
        if($("#exception32").is(":checked")){
            if(marketExceptionRemark == ''){
                $.alertMessage.info("请填写具体的错误信息！",function(){
                    $.alertMessage.close();
                });
                return false;
            }
        }else{
            if(marketExceptionRemark != ''){
                $.alertMessage.info("必须选择了错误信息买家,才可以填写具体错误信息！",function(){
                    $.alertMessage.close();
                });
                return false;
            }
        }
        return true;
    }
}

$(document).ready(function(){
    BY121315.initDataGrid();
})