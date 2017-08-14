/**
 * Created by fjm on 2016/1/27.
 */
var SL241103003 = {
    init: function () {
        this.changeSelect();
    },
    changeSelect: function () {
        $('#slmsk_select').change(function () {
            var certId = $('#slmsk_select').val();
            var jsp_epId = $('#jsp_epId').val();
            $.pdialog.open("企业资质证书添加", Main.contextPath + "/SL24110300301/init",{width:400,height:400},
                {certId:certId, jsp_epId:jsp_epId}
            );
        });
    }
}
$(document).ready(function() {
    SL241103003.init();
});