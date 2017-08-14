/**
 * Created by fjm on 2016/1/27.
 */
var SL2411010501 = {
    init : function(){
        this.bindUpdatebutton();
        this.bindAddbutton();
    },
    bindAddbutton : function () {
        $("#SL2411010501_ADD").click(function(){
            $.pdialog.open("企业生产车间信息添加", Main.contextPath + "/SL2411030050101/init",{width:600,height:600});
        });
    },
    bindUpdatebutton : function() {
        for(var i=0;i<listSize;i++){
            (function(i){
                $("#SL2411010501_UPDATE"+i).click(function() {
                    if($("#SL2411010501Form"+i).validateForm()) {
                        var $uploadFile = $("#SL2411010501Form" + i);
                        $.core.uploadForm($uploadFile, true);
                    }
                });

                $("#SL2411010501_DELETE"+i).click(function() {
                    formData = getFormData($("#SL2411010501Form"+i));
                    $('#main-content').postUrl(
                        Main.contextPath + "/SL2411010501/delete",
                        formData,
                        function() {
                            //Modified by xia_xiaojie on 2016/6/23. Modified start.
                            //$('#main-content').postUrl(Main.contextPath + "/SL241101/init");
                            $('#main-content').postUrl(Main.contextPath + "/SL241101/initShow");
                            //Modified end.
                        },
                        {refreshHtml:false}
                    );
                });
            })(i);
        }
    }
}

$(document).ready(function() {
    SL2411010501.init();
});
