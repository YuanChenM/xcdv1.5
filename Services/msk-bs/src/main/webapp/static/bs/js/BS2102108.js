var $List_Grid;
var BS2102108 = {
    formId: "bs2102108FormId",
    init: function () {
        this.exportData();
        this.bindDatePicber('#timeStart');
        this.bindDatePicber('#timeEnd');
        this.changeSelect();
        this.changeAreaSelect();
        /**Add: 横展开添加日期清除按钮 2016/09/09   BY  任强  Start */
        this.closeDate();
        /**Add: 横展开添加日期清除按钮 2016/09/09   BY  任强  End */
    },
    changeSelect: function () {
        var machiningSel = $("#machiningCode");
        $("#classesCode").change(function () {
            $("#machiningCode").find("option").not(":first").remove();
            var classesCode = $("#classesCode").val();
            if(classesCode != null && classesCode != ""){
                $('#main-content').postUrl(Main.contextPath + '/BS2102104/findPdMachiningLst', {classesCode: classesCode},
                    function (data) {
                        $.each(data, function (i, item) {
                            machiningSel.append("<option value='" + item.machiningCodeU + "'>" + item.machiningNameU + "</option>");
                        });
                    }, {refreshHtml: false});
            }
        });
    },
    changeAreaSelect: function () {
        var citySelect = $('#cityCode');
        $("#lgcsAreaCode").change(function () {
            $("#cityCode").find("option").not(":first").remove();
            var lgcsCode = $('#lgcsAreaCode').val();
            if (lgcsCode != 0) {
                $('#main-content').postUrl(Main.contextPath + '/BS2102106/findDistrictByArea', {lgcsAreaCode: lgcsCode},
                    function (data) {
                        $.each(data, function (i, item) {
                            citySelect.append("<option value='" + item.cityCode + "'>" + item.cityName + "</option>");
                        });
                    }, {refreshHtml: false});
            }
        });
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
            dateFormat: "yy-mm-dd"     // 日期格式
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

    exportData: function () {
        $("#BS2102108_EXPORT").click(function () {
            var timestart = $("#timeStart").val();
            var timeend = $("#timeEnd").val();
            var lgcsAreaCode = $("#lgcsAreaCode option:selected").val();
            var lgcsAreaName = $("#lgcsAreaCode option:selected").text();
            var cityCode = $("#cityCode option:selected").val();
            var cityName = $("#cityCode option:selected").text();
            var classesCode = $("#classesCode").val();
            var machiningCode = $("#machiningCode").val();
            var machiningName = $("#machiningCode option:selected").text();
            var buyerType = $("#buyersType").val();
            //var reclassifyCode = $("#houseReclassify").val();
            var creationStartTime = $("#timeStart").val();
            var creationEndTime = $("#timeEnd").val();

            if(!lgcsAreaCode){
                $.alertMessage.warn("请选择物流区");
            }
            else if(!cityCode){
                $.alertMessage.warn("请选择地区");
                return false;
            }
            else if(!buyerType){
                $.alertMessage.warn("请选择买家类型");
                return false;
            }
            else if(!classesCode){
                $.alertMessage.warn("请选择产品一级分类");
                return false;
            }
            else if(!machiningCode){
                $.alertMessage.warn("请选择产品二级分类");
                return false;
            }
            else if (!timestart) {
                $.alertMessage.warn("请选择开始时间");
            }
            else if (!timeend) {
                $.alertMessage.warn("请选择结束时间");
            }
            else if(timestart && timeend && timestart > timeend){
                $.alertMessage.warn("开始时间大于结束时间");
            }else {

                var title = lgcsAreaName + "物流区" + cityName + "地区"+machiningName+"二级分类产品分销买家池-冻品管家组管控表[所属期：" + creationStartTime +"-" + creationEndTime + "]"
                var isHk = "true";
                var param = new Object();
                param['lgcsAreaCode'] = lgcsAreaCode;
                param['cityCode'] = cityCode;
                param['classesCode'] = classesCode;
                param['machiningCode'] = machiningCode;
                param['creationStartTime'] = creationStartTime;
                param['creationEndTime'] = creationEndTime;
                param['buyerType'] = buyerType;
                //param['reclassifyCode'] = reclassifyCode;
                param['title'] = title;
                param['isHk'] = isHk;
                downloadAsync("dpGroupTemp", "BSExcelCommLogic", "msk-bs",title + ".xlsx", param);
            }
        })
    }
}
$(document).ready(function () {
    BS2102108.init();
});
