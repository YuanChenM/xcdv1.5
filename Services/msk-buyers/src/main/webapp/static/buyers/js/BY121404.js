/**
 * 批发市场商户新增
 * Created by zhou_yajun on 2016/7/8.
 */


var BY121404 = {
    saveButton : "BY121404_SAVE",
    initDataGrid : function () {
        this.marketSaveHandler();
    },
    //批发市场商户信息保存
    marketSaveHandler: function(){
        $("#" + BY121404.saveButton).click(function(){
           if($("#BY121404SaveForm").validateForm()){
       /*     if(!BY121404.showMessage()){
                return false;
            };*/
            var formData = getFormData($("#BY121404SaveForm"));
            $('#main-content').postUrl($("#BY121404SaveForm").attr("action"), formData, function(data){
                $.pdialog.close();
                if(data == "add"){
                    $.alertMessage.info("商户信息新增保存成功。",function(){
                        $.alertMessage.close();
                        BY121403.BY121403_Grid.fnDraw();
                    });
                }else if(data == "modify"){
                    $.alertMessage.info("商户信息编缉保存成功。",function(){
                        $.alertMessage.close();
                        BY121403.BY121403_Grid.fnDraw();
                    });
                }
            },{refreshHtml: false});

           }

        });
    },
    //简单必须项check
    showMessage : function(){
        var merchantName = $("#merchantName").val();
        /*if(merchantName == ""){
            $.alertMessage.info("请填写商户名称。",function(){
                $.alertMessage.close();
            });
            return false;
        }*/
        return true;
    }
}

$(document).ready(function(){
    BY121404.initDataGrid();
})