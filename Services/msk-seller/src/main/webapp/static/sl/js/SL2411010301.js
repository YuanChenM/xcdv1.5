/**
 * Created by fjm on 2016/1/27.
 */
var SL2411010301 = {
    init: function () {
        this.changeSelect1();
    },
    changeSelect1: function () {
        $('#slmsk_select1').change(function () {
            var certId = $('#slmsk_select1').val();
            var crtId = $('#crtId').val();
            var jsp_epId2 = $('#jsp_epId2').val();
            $.pdialog.open("企业资质证书添加", Main.contextPath + "/SL2411010301/select",{width:400,height:400},
            {certId:certId,
             crtId:crtId,
             jsp_epId2:jsp_epId2
            });
        });
    }
}
$(document).ready(function() {
    SL2411010301.init();
});