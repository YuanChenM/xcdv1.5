/**
 * Created by ni_shaotang on 2016/9/20.
 */
var backUrl = 'BA21412031.html?houseCode=' + commonUtil.QueryString("houseCode");
var opt = {
    preset: 'date', //日期
    theme: 'ios71 light', //皮肤样式
    display: 'bottom', //显示方式
    dateFormat: 'yy-mm-dd', // 日期格式
    setText: '确定', //确认按钮名称
    cancelText: '取消',//取消按钮名籍我
    dateOrder: 'yymmdd', //面板中日期排列格式
    dayText: '日', monthText: '月', yearText: '年', //面板中年月日文字
    endYear:2020 //结束年份
};
var trainId = "";
var houseCode = "";
var BA2141206 = {
    init: function () {
        BA2141206.bindBtn();
        trainId = commonUtil.QueryString("trainId");
        houseCode = commonUtil.QueryString("houseCode");
        if (trainId != "" && trainId != null) {
            $("#titleName").html("修改");
            BA2141206.loadTrain(trainId);
        }
        $("#trainStart").mobiscroll(opt);
        $("#trainEnd").mobiscroll(opt);
    },
    loadTrain: function (trainId) {
        var url = ConstantDef.queryTrainingInfoUrl();
        var data = {"param": trainId};
        HttpClient.post(url, data, function (data) {
            var houseTrain = data.result;
            if(houseTrain.trainTime !=""){
                var trainTime = houseTrain.trainTime.split("至");
                $("#trainStart").val(trainTime[0]);
                $("#trainEnd").val(trainTime[1]);
            }
            $("#trainComp").val(houseTrain.trainComp);
            $("#trainCertificate").val(houseTrain.trainCertificate);
        })
    },
    saveTrain : function(){
        var url = ConstantDef.saveHouseTrainingUrl();
        if (trainId != "" && trainId != null) {
            url = ConstantDef.modifyHouseTrainingUrl();
        }
        var slCode = localStorage.slCode;
        var trainStart = $("#trainStart").val();
        var trainEnd = $("#trainEnd").val();
        var trainComp = $("#trainComp").val();
        var trainCertificate = $("#trainCertificate").val();
        if (!slCode) {
            webToast("登录信息失效，请查询登录", "middle");
            window.location.href = 'BA2141101.html';
            return;
        }
        if (!trainStart) {
            webToast("请选择开始时间", "middle");
            return;
        }
        if (!trainEnd) {
            webToast("请选择结束时间", "middle");
            return;
        }
        if (trainEnd<trainStart) {
            webToast("开始时间不能大于结束时间", "middle");
            return;
        }
        if (!trainComp) {
            webToast("请输入培训机构", "middle");
            return;
        }
        if (!trainCertificate) {
            webToast("请输入证书名称", "middle");
            return;
        }
        var data = {"param": {
            "trainId":trainId,
            "slCode":slCode,
            "trainStart":trainStart,
            "trainEnd":trainEnd,
            "trainComp":trainComp,
            "trainCertificate":trainCertificate,
            "houseCode":houseCode
        }};
        HttpClient.post(url, data, function (data) {
            webToast(data.message, "middle");
        })
    },
    bindBtn: function () {
        $('#fanhui').bind("touchstart", function () {
            window.location.href = backUrl;
        })
    }
}
//页面入口

window.onload = window.setTimeout(BA2141206.init, 500);