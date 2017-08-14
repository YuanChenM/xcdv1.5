var $List_Grid;
var SSC11301 = {
    uploadButtonId:"SSC11301_UPLOAD",
    newButtonId:"SSC11301_NEW",
    formId:"SSC11301Form",
    init: function () {
        $List_Grid = $('#SSC11301_list_grid').grid({
            actionHandler: SSC11301.actionHandler,
            can_abolish: SSC11301.canAbolish
        });
        this.closeDate();
        this.bindUploadButton();
        this.bindDatePicker($(":input[name*='startDate']"));
        this.bindDatePicker($(":input[name*='endDate']"));
        Main.hlLeftMainMenu("SSC11301");
        this.bindSearchButton();
    },

    bindDatePicker : function(timeId){
        $(timeId).datepicker({
            dateFormat: "yy-mm-dd",
            closeText: "清除",
            showButtonPanel: true
        });
        $(timeId).attr("readonly","readonly");
    },
    closeDate : function(){
        $(document).on("click","button.ui-datepicker-close",function(){
            $.datepicker._clearDate($.datepicker._curInst.input);
        });
    },
    actionHandler: function (rowdata, coltype, row, col) {
        //详情页面
        if(coltype == "detail"){
            //$('#main-content').postUrl(Main.contextPath + "/SSC11302/init/2" , {
            //    bidId:rowdata.bidId
            //});
            Main.detailWindow(Main.contextPath + "/SSC11302/init/2" , {
                    bidId:rowdata.bidId
                },"中标成交确认书详细");

        }else if(coltype == "delete"){
            //check是否已经生成合同
            var bidId = rowdata.bidId;
            $('#main-content').postUrl(Main.contextPath + "/SSC11302/checkIsContract" , {
                bidId:bidId
            },function(data){
                data =  eval("(" + data + ")");
                if(data.rsStatus=="S"){
                    $.alertMessage.confirm("确定要删除这条数据吗？", function() {
                        $.alertMessage.close();
                $('#main-content').postUrl(Main.contextPath + "/SSC11302/deleteBidBase" , {
                    bidId:rowdata.bidId,
                    ver:rowdata.ver
                },function(data){
                    data =  eval("(" + data + ")");
                    if(data.rsStatus=="S"){
                            $('#main-content').postUrl(Main.contextPath + "/SSC11301/init/" , {
                        },function(){
                            $.alertMessage.info(data.message);
                        });
                    }else{
                        $.alertMessage.info(data.message);
                    }
                },{refreshHtml: false});
                    })
                }else{
                    $.alertMessage.info("已经生成合同,无法删除！");
                }
            },{refreshHtml: false});

        }else if(coltype =="audit"){
            //check是否已经生成合同
            var bidId = rowdata.bidId;
            $('#main-content').postUrl(Main.contextPath + "/SSC11302/checkIsContract" , {
                bidId:bidId
            },function(data){
                data =  eval("(" + data + ")");
                if(data.rsStatus=="F"){
                    //条转到合同详情页面
                    Main.detailWindow(Main.contextPath + "/SSC11304/show",{
                        "contractId":data.contractId,
                        bidId:bidId
                    },"合同详细");
                }else{
                    $.alertMessage.info("未生成合同！");
                }
            },{refreshHtml: false});
        }


    },
    // 绑定上传按钮
    bindUploadButton: function(){
       $("#" + SSC11301.uploadButtonId).click(function () {
            var validator = mainValidation($("#" + SSC11301.formId));
            var isValid = validator.form();
            if(isValid){
                var uploadFile = $("#" + SSC11301.formId);
                $.core.uploadForm(uploadFile,true);
            }
        });

        $("#" + SSC11301.newButtonId).click(function () {
            Main.detailWindow(Main.contextPath + "/SSC11302/init/2",{},"中标成交确认书详细");
        });
    },

    //如果状态为已删除，就不显示删除按钮
    canAbolish: function (rowdata) {
        var bidStatus = rowdata.bidStatus;
        if (bidStatus == "9") {
            return false;
        }
        return true;
    },
    bindSearchButton:function(){
        //查询
        $("#SSC11301_SEARCH").click(function () {
            FormUtils.setFormValue("SSC11301Form", "SSC11301");
            $List_Grid.fnDraw();
        });
    }



}

$(document).ready(function () {
    // 初始化调用
    SSC11301.init()
});

