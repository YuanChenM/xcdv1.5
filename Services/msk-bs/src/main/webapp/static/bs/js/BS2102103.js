/**
 * 冻品管家管理设置
 *
 * @author wang_haichun
 */

var BS2102103 = {
    BS2102103Grid:null,
    addButtonId: "BS2102103_NEW",
    formId: "bs2102103FormId",
    saveButtonId :"BS2102103_SAVE",
    init: function () {
        this.bindConfirmButton();
        this.bindSaveButton();
        this.stopFormSubmit();
        BS2102103.BS2102103Grid = $('#BS2102103_list_grid').grid({});
    },

    refresh:function(){
        FormUtils.setFormValue(BS2102103.formId, "BS2102103");
        BS2102103.BS2102103Grid.fnDraw();
    },


    stopFormSubmit : function(){
        $("#"+BS2102103.formId).submit(function (e){
            e.stopPropagation();
            return false;
        })
    },


    bindConfirmButton: function () {
        /** 操作按钮 */
        $("#" + BS2102103.addButtonId).click(function () {
            var param = {
                slCode: $("#input_slCode").val(),
                houseCode: $("#input_houseCode").val(),
                lgcsAreaCode: $("#input_lgcsAreaCode").val(),
                lgcsAreaName: $("#input_lgcsAreaName").val(),
                houseShowName:$("#houseShowName").text(),
                flag1:$("#flag1").val(),
                houseTel:$("#houseTel").text(),
                wechat:$("#wechat").text(),
                houseGreade:$("#houseGreade").text(),
                houseStar:$("#houseStar").text(),
                vhouseAddress:$("#vhouseAddress").text(),
                groupId:$("#input_groupId").val(),
                flagNum:$("#input_flagNum").val()
            };
            $.pdialog.open("冻品管家管理设置新增编辑", Main.contextPath + "/BS2102103/findLgcsAreaInfo", {width: "30%"}, param,"areaInfo");

        });
    },

    bindSaveButton : function () {
        $("#"+BS2102103.saveButtonId).click(function () {
            var changeData = BS2102103.BS2102103Grid.getChangeData();// 获取改动的数据对象  是数组
            if(changeData.length == 0){
                $.alertMessage.info("请先编辑要保存的数据再保存！");
            }else {
                var reg = new RegExp("^[0-9]*$");
                for(var i=0;i<changeData.length;i++){
                    if(!reg.test(changeData[i].publicBuyers)){
                        $.alertMessage.info("管理公众买家数不是正整数");
                        return false;
                    }
                    if(!reg.test(changeData[i].vipBuyers)){
                        $.alertMessage.info("管理会员买家数不是正整数");
                        return false;
                    }
                    if(!reg.test(changeData[i].marketingDays)){
                        $.alertMessage.info("营销所属期时长(天)不是正整数");
                        return false;
                    }
                    if(changeData[i].publicBuyers.toString().length > 10){
                        $.alertMessage.info("管理公众买家数字太长,请重新输入");
                        return false;
                    }
                    if(changeData[i].vipBuyers.toString().length > 10){
                        $.alertMessage.info("管理会员买家数字太长,请重新输入");
                        return false;
                    }
                    if(changeData[i].marketingDays.toString().length > 10){
                        $.alertMessage.info("营销所属期时长数字太长,请重新输入");
                        return false;
                    }
                    if(changeData[i].remark.length > 50){
                        $.alertMessage.info("备注字数不超过50字");
                        return false;
                    }
                    if(changeData[i].isChangeBuyersText == "1"){
                        changeData[i].isChangeBuyers = "1";
                    }
                    if(changeData[i].isChangeBuyersText == "0"){
                        changeData[i].isChangeBuyers = "0";
                    }
                }
                $.alertMessage.confirm("你确定要保存当前数据吗？", function() {
                    $.alertMessage.close();

                    $('#main-content').postUrl(Main.contextPath +"/BS2102103/update",JSON.stringify(changeData) ,function(data) {
                        if(data == 0){
                            $.alertMessage.info("修改失败");
                        }else {
                            $.alertMessage.info("修改成功");
                        }
                        FormUtils.setFormValue(BS2102103.formId, "BS2102103");
                        BS2102103.BS2102103Grid.fnDraw(true);
                    }, {refreshHtml: false, dataType:"json",contentType : 'application/json;charset=utf-8'});
                })
            }
        })
    }

}
$(document).ready(function () {
    // 初始化调用
    BS2102103.init();
});
