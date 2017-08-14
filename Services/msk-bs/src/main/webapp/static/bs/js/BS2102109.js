var $List_Grid;
var BS2102109 = {
    formId: "BS2102109Form",
    init: function () {
        this.bindDatePicber('#creationStartTime');
        this.bindDatePicber('#creationEndTime');
        this.exportData();
        this.changeSelect();
        this.changeLgcsSelect();
        /**Add: 横展开添加日期清除按钮 2016/09/09   BY  任强  Start */
        this.closeDate();
        /**Add: 横展开添加日期清除按钮 2016/09/09   BY  任强  End */
    },
    changeSelect :function(){
        var selectLeverOne = $("#houseCategory");
        selectLeverOne.change(function (){
            var parentTypeCode = $(this).val();
            $("#houseReclassify").html("<option value=''>请选择</option>");
            if(parentTypeCode == 0){
                return false;
            }
            $('#main-content').postUrl(Main.contextPath + '/BS2102103/findLeverTwo', {parentTypeCode:parentTypeCode},
                function (data) {
                    $.each(data, function (i, item) {
                        $("#houseReclassify").append("<option value='" + item.typeCode + "'>" + item.typeName + "</option>");
                    });
                }, {refreshHtml: false});
        });
    },

    changeLgcsSelect : function () {
        var lgcsArea = $("#logiareaCode");
        lgcsArea.change(function () {
            var logiareaCode = $(this).val();
            $("#cityCode").html("<option value='0'>请选择</option>");
            if(logiareaCode == 0 || !logiareaCode){
                return false;
            }
            $('#main-content').postUrl(Main.contextPath + '/BS2102109/findCity', {lgcsAreaCode:logiareaCode},
                function (data) {
                    $.each(data, function (i, item) {
                        $("#cityCode").append("<option value='" + item.cityCode + "'>" + item.cityName + "</option>");
                    });
                }, {refreshHtml: false});
        })
    },

    bindDatePicber: function (time) {
        $(time).datepicker({
            prevText: '上月',         // 前选按钮提示
            nextText: '下月',         // 后选按钮提示
            changeYear: true,          // 年下拉菜单
            changeMonth: true,             // 月下拉菜单
            showButtonPanel: true,         // 显示按钮面板
            showMonthAfterYear: true,  // 月份显示在年后面
            currentText: "本月",         // 当前日期按钮提示文字
            closeText: 'Clear',           // 关闭按钮提示文字
            dateFormat: "yy-mm-dd"    // 日期格式
            //minDate: new Date(),			//最小日期
        });
    },
    /**Add: 横展开添加日期清除按钮 2016/09/09   BY  任强  Start */
    closeDate : function(){
        $(document).on("click","button.ui-datepicker-close",function(){
            $.datepicker._clearDate($.datepicker._curInst.input);
        });
    },
    /**Add: 横展开添加日期清除按钮 2016/09/09   BY  任强  End */
    exportData:function(){
        $("#BS2102109_EXPORT").click(function () {
            var creationStartTime =$("#creationStartTime").val();
            var creationEndTime =$("#creationEndTime").val();
            var lgcsAreaCode =$("#logiareaCode option:selected").val();
            if(lgcsAreaCode == 0 || !lgcsAreaCode){
                $.alertMessage.info("请选择物流区");
                return false;
            }
            if(creationStartTime =='' || creationEndTime ==''){
                $.alertMessage.info("请选择所属期时间");
            } else if(creationStartTime && creationEndTime && creationStartTime > creationEndTime){
                $.alertMessage.warn("开始时间不能大于结束时间");
            } else{

                var lgcsAreaName =$("#logiareaCode option:selected").text();
                var cityCode = $("#cityCode option:selected").val();
                var cityName = $("#cityCode option:selected").text();
                var title="";
                if(lgcsAreaCode>0){
                    title = lgcsAreaName+"物流区冻品管家总控表[所属期："+creationStartTime+"-"+creationEndTime+"]"
                }else{
                    title ="冻品管家总控表[所属期："+creationStartTime+"-"+creationEndTime+"]"
                }
                var categoryCode = $("#houseCategory option:selected").val();
                var reclassifyCode = $("#houseReclassify option:selected").val();
                var param = new Object();
                param['lgcsAreaCode'] = lgcsAreaCode;
                if(cityCode != 0){
                    param['cityCode'] = cityCode;
                    param['cityName'] = cityName;
                }
                param['creationEndTime'] = creationEndTime;
                param['categoryCode'] = categoryCode;
                param['reclassifyCode'] = reclassifyCode;
                param['title'] = title;
                downloadAsync("houseTemp","BSExcelCommLogic","msk-bs",title+".xlsx",param);
            }
        });

    }
};
$(document).ready(function () {
    // 初始化调用
    BS2102109.init();
});
