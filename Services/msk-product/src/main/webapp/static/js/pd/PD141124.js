/**
 * 产品基础数据列表JS
 *
 * @author xhy
 */
var PD141124 = {
    search: "PD141124_SEARCH",
    PD141124Grid: null,
    init: function () {
        //this.initSelect();
        this.searchData();
        this.selectChange();
        var classesCode = $("#classtxt").val();
        var machiningCode = $("#machiningtxt").val();
        var breedCode = $("#breedtxt").val();
        var featureCode = $("#featuretxt").val();
        var weightCode = $("#weighttxt").val();
        if (classesCode == '') {
         $("#PD14112406Id").postUrl(Main.contextPath + "/PD14112406/init");
        } else {
            $("#PD14112406Id").postUrl(Main.contextPath + "/PD14112406/init",{
                classesCode: classesCode,
                machiningCode: machiningCode,
                breedCode: breedCode,
                featureCode: featureCode,
                weightCode: weightCode
            });
        }
    },
    initSelect:function() {
        var classesCode = $("#oneClass").val();
        var machining = $("#machiningtxt").val();
        if (classesCode != '') {
            var treeLevel = "2";
            $('#main-content').postUrl(Main.contextPath + "/PD141126/query", {
                classestreeCode: classesCode,
                treeLevel: treeLevel
            }, function (data) {
                $("#machiningSel option").remove();
                $(data).each(function (i, val) {
                    if(machining ==  val.levelCode) {
                        $("#twoClass").append($('<option>', {value: val.classestreeCode,selected:"selected"}).text(val.levelName));
                    }else{
                        $("#twoClass").append($('<option>', {value: val.classestreeCode}).text(val.levelName));
                    }
                });
            }, {refreshHtml: false});
        }
        var breed = $("#breedtxt").val();
        if (machining != '') {
            var treeLevel = "3";
            //$("#breedSel").find("option").not(":first").remove();
            $('#main-content').postUrl(Main.contextPath + "/PD141126/query", {
                classestreeCode: classesCode + machining,
                treeLevel: treeLevel
            }, function (data) {
                $("#breedSel option").remove();
                $(data).each(function (i, val) {
                    if(breed ==  val.levelCode) {
                        $("#threeClass").append($('<option>', {value: val.classestreeCode,selected:"selected"}).text(val.levelName));
                    }else{
                        $("#threeClass").append($('<option>', {value: val.classestreeCode}).text(val.levelName));
                    }
                });
            }, {refreshHtml: false});
        }
        var feature = $("#featuretxt").val();
        if (breed != '') {
            var treeLevel = "4";
            //$("#featureSel").find("option").not(":first").remove();
            $('#main-content').postUrl(Main.contextPath + "/PD141126/query", {
                classestreeCode: classesCode + machining + breed,
                treeLevel: treeLevel
            }, function (data) {
                $("#featureSel option").remove();
                $("#fourClass").append($('<option>', {value: ''}).text("---请选择---"));
                $(data).each(function (i, val) {
                    if(feature ==  val.levelCode) {
                        $("#fourClass").append($('<option>', {value: val.classestreeCode,selected:"selected"}).text(val.levelName));
                    }else{
                        $("#fourClass").append($('<option>', {value: val.classestreeCode}).text(val.levelName));
                    }
                });
            }, {refreshHtml: false});
        }
        var weight = $("#weighttxt").val();
        if (feature != '') {
            var treeLevel = "5";
            //$("#weightSel").find("option").not(":first").remove();
            $('#main-content').postUrl(Main.contextPath + "/PD141126/query", {
                classestreeCode: classesCode + machining + breed + feature,
                treeLevel: treeLevel
            }, function (data) {
                $("#weightSel option").remove();
                $("#fiveClass").append($('<option>', {value: ''}).text("---请选择---"));
                $(data).each(function (i, val) {
                    if(weight ==  val.levelCode) {
                        $("#fiveClass").append($('<option>', {value: val.classestreeCode,selected:"selected"}).text(val.levelName));
                    }else{
                        $("#fiveClass").append($('<option>', {value: val.classestreeCode}).text(val.levelName));
                    }
                });
            }, {refreshHtml: false});
        }
    },
    searchData: function () {
        $("#" + PD141124.search).click(function () {
            var classesCode = $("#oneClass").val();
            var classesName = $("#oneClass").find("option:selected").text();
            var machiningCode = $("#twoClass").val();
            var machiningName = $("#twoClass").find("option:selected").text();
            var breedCode = $("#threeClass").val();
            var breedName = $("#threeClass").find("option:selected").text();
            var featureCode = $("#fourClass").val();
            var featureName = $("#fourClass").find("option:selected").text();
            var weightCode = $("#fiveClass").val();
            var weightName = $("#fiveClass").find("option:selected").text();
            if (classesCode == 0) {
                $.alertMessage.info("请指定查询类别!");
                return;
            } else {
                $("#PD14112406Id").postUrl(Main.contextPath + "/PD14112406/init", {
                    classesCode: classesCode,
                    machiningCode: machiningCode,
                    breedCode: breedCode,
                    featureCode: featureCode,
                    weightCode: weightCode,
                    classesName: classesName,
                    machiningName: machiningName,
                    breedName: breedName,
                    featureName: featureName,
                    weightName: weightName
                });
            }
        });
    },
    selectChange:function(){
        $("#oneClass").change(function(){
            $("#twoClass").find("option").not(":first").remove();
            $("#threeClass").find("option").not(":first").remove();
            $("#fourClass").find("option").not(":first").remove();
            $("#fiveClass").find("option").not(":first").remove();
            var classestreeCode=$("#oneClass").val();
            var treeLevel="2";
            $('#main-content').postUrl(Main.contextPath + "/PD141126/query",{classestreeCode:classestreeCode,treeLevel:treeLevel},function(data){
                $(data).each(function(i,val){
                    $("#twoClass").append($('<option>',{value:val.classestreeCode}).text(val.levelName));
                });
            },{refreshHtml:false});
        });
        $("#twoClass").change(function(){
            $("#threeClass").find("option").not(":first").remove();
            $("#fourClass").find("option").not(":first").remove();
            $("#fiveClass").find("option").not(":first").remove();
            var classestreeCode=$("#twoClass").val();
            var treeLevel="3";
            $('#main-content').postUrl(Main.contextPath + "/PD141126/query",{classestreeCode:classestreeCode,treeLevel:treeLevel},function(data){
                $(data).each(function(i,val){
                    $("#threeClass").append($('<option>',{value:val.classestreeCode}).text(val.levelName));
                });
            },{refreshHtml:false});
        });
        $("#threeClass").change(function(){
            $("#fourClass").find("option").not(":first").remove();
            $("#fiveClass").find("option").not(":first").remove();
            var classestreeCode=$("#threeClass").val();
            var treeLevel="4";
            $('#main-content').postUrl(Main.contextPath + "/PD141126/query",{classestreeCode:classestreeCode,treeLevel:treeLevel},function(data){
                $(data).each(function(i,val){
                    $("#fourClass").append($('<option>',{value:val.classestreeCode}).text(val.levelName));
                });
            },{refreshHtml:false});
        });
        $("#fourClass").change(function(){
            $("#fiveClass").find("option").not(":first").remove();
            var classestreeCode=$("#fourClass").val();
            var treeLevel="5";
            $('#main-content').postUrl(Main.contextPath + "/PD141126/query",{classestreeCode:classestreeCode,treeLevel:treeLevel},function(data){
                $(data).each(function(i,val){
                    $("#fiveClass").append($('<option>',{value:val.classestreeCode}).text(val.levelName));
                });
            },{refreshHtml:false});
        });
    }
}
$(document).ready(function () {
    // 初始化调用
    PD141124.init();
});
