/**
 * Created by fjm on 2016/1/27.
 */
var SL24110103 = {
    init : function(){
        this.bindAddButton();
        this.bindUpdatebutton();
    },
    bindAddButton : function(){
        $("#SL24110103_ADD").click(function(){
            $.pdialog.open("企业资质证书添加", Main.contextPath + "/SL2411010301/init",{width:600,height:200});
        });
    },
    bindUpdatebutton : function() {
        for(var i=0;i<listSize;i++){
            (function(i){
                $("#SL24110103_UPDATE"+i).click(function() {
                    var $uploadFile = $("#SL24110103Form"+i);
                    $.core.uploadForm($uploadFile,true);
                });

                $("#SL24110103_DELETE"+i).click(function() {
                    formData = $("#SL24110103Form"+i).serializeArray();
                    $('#main-content').postUrl(
                        Main.contextPath + "/SL24110103/delete",
                        formData,
                        function() {
                            //Modified by xia_xiaojie on 2016/6/23. Modified start.
                            //$('#main-content').postUrl(Main.contextPath + "/SL241101/init");
                            $('#main-content').postUrl(Main.contextPath + "/SL241101/initShow");
                            //Modified end.
                        });
                });
            })(i);
        }
    }
}

$(document).ready(function() {
    SL24110103.init();
});
