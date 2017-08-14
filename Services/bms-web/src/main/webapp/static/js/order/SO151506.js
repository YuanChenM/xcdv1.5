/**
 * Created by wang_shuai on 2016/8/3.
 */
var $List_Grid;
var SO151506 = {
    searchButtonId: "SO151506_SEARCH",
    formId: "SO151506Form",
    init: function () {
        $("#returnSource").prepend("<option value=''>-----请选择-----</option>");
        $("#returnType").prepend("<option value=''>-----请选择-----</option>");
        $("#returnSource").val("");
        $("#returnType").val("");
        $("#returnSource").selectmenu({width:"100%"});
        $("#returnType").selectmenu({width:"100%"});
        FormUtils.init(SO151506.formId, "SO151506");
        $List_Grid = $('#SO151506_list_grid').grid({
            linkHandler: SO151506.linkHandler
        });
        this.bindDatePicker('#startTime');
        this.bindDatePicker('#endTime');
        this.bindSearchButton();
        this.enterSearchData();
        $("select").attr("style","width:100%;border-color: #888888;border-style:solid;border-width: 1px;"); //  设置宽度 边框线
        this.setSelect();
        $("#checkbox-returnStatus").checkboxSelect({width:"100%"});
        //$("#checkbox-returnStatus-button").attr("style","width:135px");
        //$("#checkbox-returnStatus").selectmenu({width:"135px"});
        this.closeDate();
        $("#returnType").hide();
        $("#returnSource").hide();
        $("#checkbox-returnStatus").hide();


        $("#returnCode").focus();
    },
    bindDatePicker : function(orderTimeId){
        $(orderTimeId).datepicker({
            showButtonPanel: true,
            dateFormat:'yy-mm-dd',
            changeMonth: true,
            changeYear: true,
            closeText: '清空',
            onSelect:function(dateText,inst){
                if(inst.id == "startTime" && $("#endTime").val()!=""){
                    if($("#startTime").val()> $("#endTime").val()){
                        $("#startTime").val("");
                        $.alertMessage.info("开始时间不应大于结束时间!");
                    }
                }
                if(inst.id == "endTime" && $("#startTime").val()!=""){
                    if($("#startTime").val()> $("#endTime").val()){
                        $("#endTime").val("");
                        $.alertMessage.info("开始时间不应大于结束时间!");
                    }
                }
            },
            onClose:function(dateText,inst){
                var reg=/^(?:(?!0000)[0-9]{4}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)-02-29)$/;
                if(!reg.test(dateText) && dateText != ""){
                    $.alertMessage.info("请按YYYY-MM-DD格式输入时间！");
                    if(inst.id == "endTime"){
                        $("#endTime").val("");
                    }
                    if(inst.id == "startTime"){
                        $("#startTime").val("");
                    }
                }
            }
        });
    },
    closeDate : function(){
        $(document).on("click","button.ui-datepicker-close",function(){
            $.datepicker._clearDate($.datepicker._curInst.input);
        });
    },
    // 绑定按钮
    bindSearchButton: function () {
        $("#" + SO151506.searchButtonId).click(function () {
            FormUtils.setFormValue(SO151506.formId, "SO151506");
            $List_Grid.fnDraw()
        });
    },
    setSelect:function () {
        $("#returnOrderStatus option").each(function(){ //遍历全部option
            var txt = $(this).text(); //获取option的内容
            var val = $(this).val();
            var opt = "<option value='"+val+"'>"+txt+"</option>";
            $("#checkbox-returnStatus").append(opt);
        });
    },
    enterSearchData:function(){
        //绑定回车键
        document.onkeydown=function enterDown(e){
            //兼容火狐,chrome和ie的事件对象
            e = e||event;

            if(e.keyCode == 13) {
                FormUtils.setFormValue(SO151506.formId, "SO151506");
                $List_Grid.fnDraw()
            }
            return;
        }
    },
    linkHandler: function (rowdata, colname, row, col) {
        if (colname == "returnCode") {
            var data = new Object();
            data['orderId'] = rowdata.orderId;
            Main.detailWindow(Main.contextPath + "/SO151507/init/" + rowdata.returnId, data, "退货明细");
            //$('#main-content').postUrl(Main.contextPath + "/SO151507/init/" + rowdata.returnId,{orderId:rowdata.orderId});
        }
        if (colname == "orderCode") {
            $('#main-content').postUrl(Main.contextPath + "/SO151501/init/",{orderId:rowdata.orderId});
        }
    }
}

$(document).ready(function () {
    // 初始化调用
    SO151506.init();
    var returnSourceWidth = $("#orderCode").width()+4;
    var returnTypeWidth = $("#returnCode").width()+4;
    var returnStatus = $("#buyerCode").width()+4;
    $("#returnSource-button").attr("style","width:"+returnSourceWidth+"px");
    $("#returnType-button").attr("style","width:"+returnTypeWidth+"px");
    $("#checkbox-returnStatus-button").attr("style","width:"+returnStatus+"px");
});
