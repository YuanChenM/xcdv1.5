/**
 * 发货计划单录入
 */
var SC181103 = {
    //$List_Grid:null,
    SC181103Grid: null,
    searchButtonId: "SC181103_SEARCH",
    saveButtonId: "SC181103_SAVE",
    formId: "SC181103Form",
    THISROWDATA : null,
    THISROWINDEX : null,
    TRINDEX : 0,
    init: function () {
        SC181103.SC181103Grid = $('#SC181103_list_grid').grid({
            //actionHandler: SC181103.actionHandler,
            //iDisplayLength:10
            editCellOnBlurHandler :SC181103.cellOnBlurHandler,
        });
        SC181103.bindSearchButton();
        SC181103.bindSelectChange();
        SC181103.bindSaveButton();
    },
    cellOnBlurHandler:  function($comp) {
        //获得TD
        var $td = $comp.parent();
        //获得TR
        var $tr = $td.parent();
        //获得所有行
        var $trs = $tr.parent();
        //获得当前行
        SC181103.THISROWINDEX = $trs.children().index($tr);
        SC181103.THISROWDATA = SC181103.SC181103Grid.fnGetData(SC181103.THISROWINDEX);
        //获取净重
        var netWeight = SC181103.THISROWDATA["outNetWeight"];
        if (isNaN(netWeight) || Number(netWeight) == 0) {
            $.alertMessage.info("产品的净重必须为非零数字!");
            return;
        }
        //获得当前列数据
        var num = $comp.val();
        var name = $comp.attr('name');
        // if (name == 'packNum') {
        //     // SC181103.THISROWDATA.packNum = num;
        //     var packNum = Number(num);
        //     var netWeightNumber = Number(netWeight).toFixed(2);
        //     if (isNaN(num) || Number(packNum % netWeightNumber) != 0 || packNum > 99999999999999.99) {
        //         // SC181103.THISROWDATA.packBoxes = 0;
        //         $comp.val(0);
        //         SC181103.THISROWDATA['packNum'] = 0;
        //         SC181103.THISROWDATA['deliveryBoxes'] = 0;
        //         $.alertMessage.confirm("发货数量必须是数字,且为对应产品净重的整数倍,最大长度14!您也可选择输入'箱数'!",function(){
        //                 $.alertMessage.close();
        //                 SC181103.SC181103Grid.fnUpdate(SC181103.THISROWDATA,SC181103.THISROWINDEX,undefined,false,false);
        //         }
        //         );
        //         return;
        //     }else {
        //         SC181103.THISROWDATA["deliveryBoxes"] = Number(packNum / netWeightNumber);
        //     }
        // }
        if (name == 'deliveryBoxes'){
            var packBoxes = Number(num);
            var netWeightNumber = Number(netWeight).toFixed(2);
            var packNum = Number(packBoxes * netWeightNumber).toFixed(2);
            if (isNaN(num) || (!/^-?[1-9]\d*$/.test(num) && num != '0')) {
                $comp.val(0);
                SC181103.THISROWDATA['packNum'] = '0.00';
                SC181103.THISROWDATA['deliveryBoxes'] = 0;
                $.alertMessage.info("发货箱数必须是整数!",function () {
                    $.alertMessage.close();
                    SC181103.SC181103Grid.fnUpdate(SC181103.THISROWDATA,SC181103.THISROWINDEX,undefined,false,false);
                });
                return;
            }else if(packNum > 99999999999999.99){
                $comp.val(0);
                SC181103.THISROWDATA['packNum'] = '0.00';
                SC181103.THISROWDATA['deliveryBoxes'] = 0;
                $.alertMessage.info("输入的箱数导致数量超过最大长度:14!",function () {
                    $.alertMessage.close();
                    SC181103.SC181103Grid.fnUpdate(SC181103.THISROWDATA,SC181103.THISROWINDEX,undefined,false,false);
                });
                return;
            }else {
                SC181103.THISROWDATA["packNum"] =packNum;
            }
        }
    },
    bindSearchButton: function () {
        $("#" + SC181103.searchButtonId).click(function () {
            FormUtils.setFormValue(SC181103.formId, "SC181103");
            SC181103.$List_Grid.fnDraw()
        });
    },
    bindSelectChange:function(){        
        $("#lgcsCode").change(function(){
            var lgcsname = $("#lgcsCode").find("option:selected").text();
            $('#lgcsName').val(lgcsname);
            $('#main-content').postUrl(Main.contextPath + "/SC181103/selectChange/", {
                lgcsCode:$("#lgcsCode").val()
            },function(data){
                var suppCode = $("#suppCode");
                suppCode.html('');
                /* suppCode.append("<option value='-1'>--请选择--</option>");*/

                $.each(data,function(i,item){
                    suppCode.append("<option value='" + item.suppCode + "'>"+ item.suppName+ "</option>");
                }),
                    FormUtils.setFormValue(SC181103.formId, "SC181103"),
                    SC181103.SC181103Grid.fnDraw()
            },{refreshHtml:false})
        });
        $("#suppCode").change(function(){
            FormUtils.setFormValue(SC181103.formId, "SC181103");
            SC181103.SC181103Grid.fnDraw();
            //{refreshHtml:false};
        })
    },
    bindSaveButton: function () {

        $("#" + SC181103.saveButtonId).click(function () {
            var changeData = SC181103.SC181103Grid.getChangeData();// 获取改动的数据对象  是数组
            if (changeData.length == 0) {
                $.alertMessage.confirm("请先填写发货箱数再保存数据！", function () {
                    $.alertMessage.close();
                })
            } else {
                $.alertMessage.confirm("你确定要保存当前数据吗？", function () {
                    $.alertMessage.close();
                    var json = {};// 创建json 对象
                    for (i = 0; i < changeData.length; i++) {//  把数组的对象封装到json
                        if (isNaN(changeData[i].packNum)){
                            $.alertMessage.info("发货数量必须是数字！");
                            return;

                        } else if (changeData[i].packNum != "0" && changeData[i].packNum != "" && Number(changeData[i].packNum) != 0) {
                            json[i] = changeData[i];
                        }
                    }
                    if(JSON.stringify(json).length != 2){
                        var jsonStr = JSON.stringify(json);//  转成jsonStr
                        $('#main-content').postUrl(Main.contextPath + "/SC181103/save", {"jsonStr": jsonStr}, function (data) {
                            $.alertMessage.info("发货计划保存成功，请到发货入库通知单一览页面进行正式发货。");
                            // $('#main-content').postUrl(Main.contextPath + "/SC181103/init/")
                            SC181103.SC181103Grid.fnDraw(1);
                        },{refreshHtml:false});
                    }else{
                        $.alertMessage.info("请输入发货箱数!");
                    }


                })
            }
        })
    }
}
$(document).ready(function () {
    // 初始化调用
    SC181103.init();
    //$List_Grid.fnDraw();
});
