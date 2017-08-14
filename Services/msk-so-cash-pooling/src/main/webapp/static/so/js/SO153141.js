/**
 * 账套一览
 *
 */
var SO153141 = {
    SO153141Grid: null,
    formId: "SO153141Form",
    commDateStart: "#commDateStart",
    commDateEnd: "#commDateEnd",
    init: function () {
        $List_Grid = $('#SO153141_list_grid').grid({
            actionHandler: SO153141.actionHandler
        });
        this.bindDatePicber(SO153141.commDateStart);
        this.bindDatePicber(SO153141.commDateEnd);

    },
    bindDatePicber: function (timeId) {
        $(timeId).datepicker({
            showButtonPanel: true,
            dateFormat: 'yy-mm-dd',
            changeMonth: true,
            changeYear: true
        });
        $(timeId).change(function(){
            var str = $(timeId).val();
            if(str.length >= 8){
                // 判断年月日的格式2010-01-01
                var reg = /^(\d{4})-(0\d{1}|1[0-2])-(0\d{1}|[12]\d{1}|3[01])$/;
                if(reg.test(str)){
                    if(timeId.endsWith("End")){
                        var startTime = $(timeId.substring(0,timeId.length-3) + "Start").val();
                        var endTime = $(timeId).val();
                        if(startTime != null && startTime != 'underfined' && startTime != '' && startTime>endTime){
                            $(timeId).val("");
                            $.alertMessage.info("输入正确的时间范围");
                        }
                    }
                    if(timeId.endsWith("Start")){
                        var startTime = $(timeId).val();
                        var endTime = $(timeId.substring(0,timeId.length-5) + "End").val();
                        if(endTime != null && endTime != 'underfined'&& endTime != '' && startTime>endTime){
                            $(timeId).val("");
                            $.alertMessage.info("输入正确的时间范围");
                        }
                    }
                }else{
                    $(timeId).val("");
                }
            }else{
                $(timeId).val("");
            }
        });
    },
    actionHandler: function (rowdata, coltype, row, col) {
        if (coltype == 'edit') {
            //期初调整
            $.pdialog.open("期初调整", Main.contextPath + "/SO153142/init?accountBookId=" + rowdata.accountBookId + "&&ver=" + rowdata.ver  + "&&userNo="+ rowdata.userNo, {
                resizable: false,
                width: 400,
                height: 300
            });
        }
    }
 }
$(document).ready(function () {
    // 初始化调用
    SO153141.init();
});
