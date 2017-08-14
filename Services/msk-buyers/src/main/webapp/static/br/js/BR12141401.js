/**
 * Created by tao_zhifa on 2016/9/30.
 */

var BR12141401 ={
    editNameButton : "BR12141401_EDITNAME",
    init : function(){
        this.bindEditNameButton();
    },

    bindEditNameButton: function () {
        $("#" + BR12141401.editNameButton).click(function(){
            var formData = getFormData($("#BR121414UpdateNameForm"));
            $('#main-content').postUrl(Main.contextPath + "/BR121414/save",formData,function(data){
                if(data == 0){
                    $.alertMessage.info("保存失败");
                    return false;
                }else{
                    $.alertMessage.info("保存成功");
                    $.pdialog.close();
                    $('#main-content').postUrl(Main.contextPath + "/BR121414/init",formData,function(data){
                    })

                }
            },{refreshHtml: false});
        })
    }

};



$(document).ready(function () {
    BR12141401.init();
    reSizeInput();
});

function reSizeInput(){
    $("input[type='text']").each(function(){
        var text = $(this).val().length;
        text = parseInt(text) * 2;
        $(this).attr('size',text);
    });
}