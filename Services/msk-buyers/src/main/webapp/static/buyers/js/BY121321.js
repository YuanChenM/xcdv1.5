var $List_Grid;
var BY121321 = {
    formId: "BY121321Form",
    init: function () {
        $List_Grid = $('#BY121321_list_grid').grid({
            paging:false,
            actionHandler: BY121321.actionHandler
        });
        this.bindAddBtn();
        this.bindDatePicber(startTime);
        this.bindDatePicber(endTime);
        this.bindSearchButton();
        this.closeDate();
    },

    bindDatePicber : function(date){
        $(date).datepicker({
            showButtonPanel: true,
            dateFormat:'yy-mm-dd',
            currentText: "Today",
            closeText: "Clear",
            changeMonth: true,
            changeYear: true
        });
    },
    closeDate : function(){
        $(document).on("click","button.ui-datepicker-close",function(){
            $.datepicker._clearDate($.datepicker._curInst.input);
        });
    },

    // 绑定按钮
    bindSearchButton: function () {
        $("#BY121321_SEARCH").click(function () {
            var startTime=$("#startTime").val() + " 00:00:00";
            var endTime= $("#endTime").val() + " 23:59:59";
            if(startTime==""  && endTime !="" ){
                $.alertMessage.info("请输入开始时间！");
                return false;
            }
            if(startTime !=""  && endTime == "" ){
                $.alertMessage.info("请输入结束时间！");
                return false;
            }
            if(startTime > endTime){
                $.alertMessage.info("开始时间不能大于结束时间！");
                return false;
            }
            FormUtils.setFormValue(BY121321.formId, "BY121321");
            $List_Grid.fnDraw()
        });
    },

    actionHandler: function (rowdata, coltype, row, col) {
    },

    bindAddBtn: function () {

    }

}

$(document).ready(function () {
    // 初始化调用
    BY121321.init();
});

