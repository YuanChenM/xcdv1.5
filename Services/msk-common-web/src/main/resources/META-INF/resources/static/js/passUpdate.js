(function ($) {

    $("#PASSWORD_ClOSE").click(function(){
        $.pdialog.close();
    });

    $("#PASSWORD_CONFIRM").click(function(){
        var oldVal = $("#oldPassword").val();
        var newVal = $("#newPassword").val();
        var conVal = $("#confirmPassword").val();
        $("#message").empty();
        if(oldVal==null || oldVal=="") {
            $("#message").append("当前密码不能为空！");
            return;
        }
        if(newVal==null || newVal=="") {
            $("#message").append("新密码不能为空！");
            return;
        }
        if (!/^[0-9a-zA-Z_]{6,16}$/.test(newVal)) {
            $("#message").append("新密码由6-16个数字字母组成，请填写正确的格式！");
            return;
        }
        if(oldVal == newVal){
            $("#message").append("当前密码和新密码不能一样，请重新填写！");
            return;
        }
        if(conVal==null || conVal=="") {
            $("#message").append("确认新密码不能为空！");
            return;
        }
        if(newVal != conVal){
            $("#message").append("请保持新密码和确认密码一致！");
            return;
        }
        $("#main-content").postUrl(Main.contextPath +"/comm/update",
            {oldPassword:oldVal,newPassword:newVal},
            function(result){
                if(result.result == true) {
                    $.pdialog.close();
                    $.alertMessage.info("密码修改成功！");
                } else {
                    $("#message").append(result.message);
                }
            },{refreshHtml: false});
    });
})(jQuery);