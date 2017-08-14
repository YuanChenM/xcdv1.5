/**
 * 批发市场定性定级各阶段一览表列表JS
 * author:yuan_zhifei
 * createDate:2016-07-13
 */
var BY121406 = {
    BY121406Grid:null,
    saveButton:"BY121406_SAVENETMARKET",
    findButton :"BY121406_SELECT",
    init: function () {
        BY121406.BY121406Grid = $('#BY121406_list_grid').grid({
            actionHandler :BY121406.actionHandler
        });
        this.marketSaveHandler();
        this.bindSelectChange();
    },
    actionHandler : function(rowdata,coltype,row,col){

    },
    bindSelectChange : function () {
        $("#"+BY121406.findButton).click(function(){
            var lgcsAreaName =$("#lgcsAreaCode option:selected").text();
            var researchPhase =$("#researchPhase option:selected").text();
            var marketName = $("#marketName").val();
            var marketAddr = $("#marketAddr").val();

            if(lgcsAreaName == "--请选择--"){
                $("#lgcsAreaName").val("");
            }else{
                $("#lgcsAreaName").val(lgcsAreaName);
            }

            if(researchPhase == "--请选择--"){
                $("researchPhase").val("");
            }else{
                $("researchPhase").val(researchPhase);
            }


            var formData = getFormData($("#selectBuyerList"));

            $('#main-content').postUrl(Main.contextPath + "/BY121406/search",formData,
                function(){
                    BY121406.BY121406Grid.fnDraw();
                },{refreshHtml:false});

        });
    },
    marketSaveHandler:function(){
        $("#" + BY121406.saveButton).click(function (){
            var changeData = BY121406.BY121406Grid.getChangeData();
            if (changeData.length == 0) {
                $.alertMessage.confirm("请输入修改数据！", function () {
                    $.alertMessage.close();
                });
                return false;
            }else{
                $.alertMessage.confirm("你确定要保存当前数据吗？", function () {
                    $.alertMessage.close();
                    var json = {};// 创建json 对象
                    for (i = 0; i < changeData.length; i++) {//  把数组的对象封装到json
                        json[i] = changeData[i];
                    }
                    if (json == null || json == '') {
                        $.alertMessage.info("请输入金额数量!");
                        return false;
                    } else{
                        var jsonStr = JSON.stringify(json);//  转成jsonStr
                        $('#main-content').postUrl(Main.contextPath + "/BY121406/marketNatureSave", {"jsonStr": jsonStr}, function (data) {
                            $.alertMessage.info("网搜阶段信息保存成功。");
                        },{refreshHtml:false});
                    }
                })
            }
        });
    }

}

$(document).ready(function () {
    // 初始化调用
    BY121406.init();
});


document.onkeydown = function(e){
    var e = e || window.event;
    if(e.keyCode == 13) {
        $("#"+BY121406.findButton).click();
    }
};