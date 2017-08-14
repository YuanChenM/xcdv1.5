/**
 * 价盘卖家查看JS
 *
 * @author cx
 */

var SP171191 = {
    SP171191Grid: null,
    formId : "SP171191Form",
    detailRows: [],
    trIndex: 0,
    initDataGrid: function () {
        SP171191.SP171191Grid = $('#SP171191_Grid').grid({
            actionHandler: SP171191.actionHandler
        });
        SP171191.bindConfirmButton();
        SP171191. bindLgcsProductButton();
    },

    bindConfirmButton : function(){
        var reg = new RegExp("^\\d+(\\.\\d+)?$");
        $("#SP171191_CONFIRM").click(function(){
            var changeData = SP171191.SP171191Grid.getChangeData();// 获取改动的数据对象  是数组
            if(changeData.length==0){
                $.alertMessage.confirm("请先编辑要保存的数据再保存！", function() {
                    $.alertMessage.close();
                })
            }else{
                $.alertMessage.confirm("你确定要保存当前数据吗？", function() {
                    $.alertMessage.close();
                    var json = {};// 创建json 对象
                    for(i=0;i<changeData.length;i++){//  把数组的对象封装到json
                        json[i] =changeData[i];
                        var minVal=changeData[i]["minVal"];
                        if(minVal!=''){
                            if(!reg.test(minVal)){
                                alert("选中的第"+(i+1)+"行最小单位数请输入数字!");
                                return;
                            }
                        }
                    }
                    var jsonStr = JSON.stringify(json);//  转成jsonStr
                    $('#main-content').postUrl(Main.contextPath + "/SP171191/save", {"jsonStr":jsonStr},function(data) {
                        $.alertMessage.info("修改已提交");
                        FormUtils.setFormValue(SP171191.formId, "SP171191");
                        SP171191.SP171191Grid.fnDraw();
                    }, {refreshHtml: false});
                })
            }

        });
    },
      setPar:function(wayCode,wayName){//  设置对应的数值
        var row =$("#rowId").val();  // 获取行号
        var data=  SP171191.SP171191Grid.fnGetData();
          var rowdata=data[row];
          tr= $("#SP171191_Grid tbody").children("tr")[row];
          rowdata["wayCode"]=wayCode;
          rowdata["wayName"]=wayName;
          td7=$(tr).children("td")[7];
          $(td7).html(wayCode)
          td8=$(tr).children("td")[8];
          $(td8).html(wayName);
          rowdata.modifyStatus=2;// 设置可编辑
      },

    actionHandler: function (rowdata, coltype, row, col) {
        /** 操作按钮 */
        if (coltype == "add") {
            $("#rowId").val(row);
            $.pdialog.open("需求等级", Main.contextPath + "/SP171193/init", {width: "80%"},
                {
                });
        }
    },
    bindLgcsProductButton:function(){
        $("#SP171191_LGCSPRODUCT").click(function(){
            $('#main-content').postUrl(Main.contextPath + "/lgcsProduct/save",function(data) {
                $.alertMessage.info("同步已提交");
                FormUtils.setFormValue(SP171191.formId, "SP171191");
                SP171191.SP171191Grid.fnDraw();
            }, {refreshHtml: false});
        });
        $("#SP171191_SAVEWAY").click(function() {
            var formData = getFormData($("#" + SP171191.formId));
            $.alertMessage.confirm("是否对当前条件的产品设置该需求等级？", function () {
                $('#main-content').postUrl(Main.contextPath + "/SP171191/saveWay/0", formData, function (data) {
                    $.alertMessage.info("修改已提交");
                });
            });
        });
        $("#SP171191_SAVEMARKET").click(function() {
            var formData = getFormData($("#" + SP171191.formId));
            $.alertMessage.confirm("是否对当前条件的产品设置该营销状态？", function () {
                $('#main-content').postUrl(Main.contextPath + "/SP171191/saveWay/1", formData, function (data) {
                    $.alertMessage.info("修改已提交");
                });
            });
        });
    },

rowCallback: function (tr, data) {
    }

}
$(document).ready(function () {
    // 初始化调用
    SP171191.initDataGrid();


});

