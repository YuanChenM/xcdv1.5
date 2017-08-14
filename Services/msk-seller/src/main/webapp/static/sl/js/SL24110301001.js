/**
 * Created by fjm on 2016/1/30.
 */
var SL24110301001 = {
    formId:"SL24110301001Form",
    SL24110301001:null,
    init : function(){
        SL24110301001.SL24110301001 = $('#SL24110301001Grid').grid({
            redionOnclickName : "SL24110301001.checkData"
        });
    },
    checkData:function(tag){
        var data=SL24110301001.SL24110301001.getChoiceOne();
        var epName=data.epName;
        var epId=data.epId;
        $("#epNameId").val(epName);
        $("#producerEpId").val(epId);
        if($("#flg").val()=="1"){
            $.pdialog.close("showEp");
        }else{
            $.pdialog.close();
        }
    }
}

$(document).ready(function() {
    SL24110301001.init();
});
document.onkeydown = function mykeyDown(e){
    var e = window.event || e;
    if(e.keyCode == 13) {
        return false;
    }
}