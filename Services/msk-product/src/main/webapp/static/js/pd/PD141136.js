/**
 * 产品基础数据列表JS
 *
 * @author xhy
 */
var PD141136 = {
    search: "PD141136_SEARCH",
    PD141136Grid: null,
    init: function () {
        this.linkage();
        this.searchData();
        $("#PD14113601Id").postUrl(Main.contextPath + "/PD14113601/init");
    },
    linkage: function () {
        $("#classes").change(function () {
            $("#machining").find("option").not(":first").remove();
            $("#breed").find("option").not(":first").remove();
            var classesCode = $("#classes").val();
            if(classesCode=='0'){return false;}
            $('#main-content').postUrl(Main.contextPath + "/PD141136/query", {classesCode: classesCode}, function (data) {
                if (data.length <= 0) {
                    $.alertMessage.info("没有查询到数据!");
                    return;
                }
                $(data).each(function (i, val) {
                    $("#machining").append($('<option>', {value: val.levelCode}).text(val.levelName));
                });
            }, {refreshHtml: false});
        });
        $("#machining").change(function () {
            $("#breed").find("option").not(":first").remove();
            var classes = $("#classes").val();
            var machining = $(this).val();
            if(machining=='0'){return false;}
            $('#main-content').postUrl(Main.contextPath + "/PD141136/query", {
                classesCode: classes,
                machiningCode: machining
            }, function (data) {
                if (data.length <= 0) {
                    $.alertMessage.info("没有查询到数据!");
                    return;
                }
                $(data).each(function (i, val) {
                    $("#breed").append($('<option>', {value: val.levelCode}).text(val.levelName));
                });
            }, {refreshHtml: false});
        });
    },
    searchData: function () {
        $("#" + PD141136.search).click(function () {
            var classesCode = $("#classes").val();
            var machiningCode = $("#machining").val();
            var breedCode = $("#breed").val();
            if(machiningCode==0){
                alert("第二级分类不能为空，请选择!");
            }else{
            $("#PD14113601Id").postUrl(Main.contextPath + "/PD14113601/init", {
                classesCode: classesCode,
                machiningCode: machiningCode,
                breedCode: breedCode
            });
            }
        });
    }
}
$(document).ready(function () {
    // 初始化调用
    PD141136.init();
});
