/**
 * 买手店列表JS
 *
 * @author cx
 */
var BS2102113 = {
    formId: "BS2102113FormInfo",
    addFileButtonId: "BS2102113_FILE",
    saveDataButtonId: "BS2102113_SAVE",
    updateButtonId : "BS2102113_UPDATE",
    init: function () {
        this.stopFormSubmit();
        this.bindDatePicber(".dataTime");
        this.updateResume();
        this.uploadFile();
        this.imgHover();
        $("#workExperience").postUrl(Main.contextPath + "/BS2102113/initWork", {
            slCode:$("#slCode").val(),
            houseCode:$("#houseCode").val()
        });
        $("#eduExperience").postUrl(Main.contextPath + "/BS2102113/initEdu", {
            slCode:$("#slCode").val(),
            houseCode:$("#houseCode").val()
        });
        $("#trainExperience").postUrl(Main.contextPath + "/BS2102113/initTrain", {
            slCode:$("#slCode").val(),
            houseCode:$("#houseCode").val()
        });
        /**Add: 横展开添加日期清除按钮 2016/09/09   BY  任强  Start */
        this.closeDate();
        /**Add: 横展开添加日期清除按钮 2016/09/09   BY  任强  End */
    },

    imgHover : function () {
        $(".uploadFiles").each(function () {
            $(this).find("img").hover(function () {
                var img_url = $(this).attr("src");
                if(img_url){
                    $("#big_img").attr("src",img_url);
                    $("#big_img").css("display","block");
                }
            },function () {
                $("#big_img").attr("src","");
                $("#big_img").css("display","none");
            })
        })
    },

    updateResume : function () {
        $("#"+BS2102113.updateButtonId).click(function () {
            var param = {
                slCode:$("#slCode").val(),
                houseCode:$("#houseCode").val()
            };
            $('#main-content').postUrl(Main.contextPath + "/BS2102117/init", param);
        });
    },

    uploadFile : function (formData,type) {
        if(formData && type){
            $('#main-content').postUrl(Main.contextPath + "/BS2102113/upload", formData, function (data) {
                if (data.returnCode != 1001) {
                    $.alertMessage.info(data.message);
                } else {
                    $("#"+type).val(data.result);
                    $("#"+type+"_img").attr("src",data.result);
                    $("#"+type+"_img").css({"width":"100px","height":"100px;"});
                }
            }, {refreshHtml: false,processData: false,contentType: false});
        }
    },




    stopFormSubmit: function () {
        $("#" + BS2102113.formId).submit(function (e) {
            e.stopPropagation();
            return false;
        })
    },

    bindDatePicber: function (time) {
        if (!time) {
            return false;
        }
        $(time).datepicker({
            prevText: '上月',         // 前选按钮提示
            nextText: '下月',         // 后选按钮提示
            changeYear: true,          // 年下拉菜单
            changeMonth: true,             // 月下拉菜单
            showButtonPanel: true,         // 显示按钮面板
            showMonthAfterYear: true,  // 月份显示在年后面
            currentText: "本月",         // 当前日期按钮提示文字
            closeText: 'Clear',           // 关闭按钮提示文字
            dateFormat: "yy-mm-dd",    // 日期格式
            maxDate: new Date()
        });
    },
    /**Add: 横展开添加日期清除按钮 2016/09/09   BY  任强  End */
    closeDate : function(){
        $(document).on("click","button.ui-datepicker-close",function(){
            $.datepicker._clearDate($.datepicker._curInst.input);
        });
    },
    /**Add: 横展开添加日期清除按钮 2016/09/09   BY  任强  End */
    //添加上传文件


    saveData: function () {
        var slCode = $("#slCode").val();
        var houseCode = $("#houseCode").val();
        var uploadUrl1 = $("#uploadUrl1").val();
        var uploadUrl2 = $("#uploadUrl2").val();

        if (!slCode || !houseCode) {
            $.alertMessage.info("管家code获取失败");
            return false;
        }
        var serviceCommit = $("#serviceCommit").val();
        var introduce = $("#introduce").val();
        if(serviceCommit){
            if(serviceCommit.length > 500){
                $.alertMessage.info("服务心得不能多于500字");
                return false;
            }
        }
        if(introduce){
            if(introduce.length > 500){
                $.alertMessage.info("感悟理想不能多于500字");
                return false;
            }
        }
        var houseIntroduce = {
            serviceCommit: serviceCommit,//服务
            introduce : introduce,//感悟
            slCode : slCode,
            houseCode : houseCode,
            uploadUrl1 : uploadUrl1,//证件照正面
            uploadUrl2 : uploadUrl2 //证件照反面
        };
        var param = {
            houseIntroduce : houseIntroduce
        };
        //save
        $('#main-content').postUrl(Main.contextPath + "/BS2102113/save", JSON.stringify(param), function (data) {
            console.log(data)
            if (data > 0) {
                $.alertMessage.info("保存成功!");
                var slCode = $("#slCode").val();
                var houseCode = $("#houseCode").val();
                $('#main-content').postUrl(Main.contextPath + "/BS2102113/init", {slCode:slCode,houseCode:houseCode});
            } else {
                $.alertMessage.info("保存失败!");
            }
        }, {refreshHtml: false, dataType: "json", contentType: 'application/json;charset=utf-8'});
    },

};

var uploadCallback = function(data){
    console.log(data);
    //解析json
    var obj = eval(data);
    //获得文件
    var fidFirst =obj.uploadUrl1;
    var fidSecond =obj.uploadUrl2;
    //进行业务处理，保存
    console.log("fidFirst--"+fidFirst);
    console.log("fidSecond--"+fidSecond);
    $("#uploadUrl1").val(fidFirst);
    $("#uploadUrl2").val(fidSecond);
    BS2102113.saveData();
};

$(document).ready(function () {
    // 初始化调用
    BS2102113.init();
});
