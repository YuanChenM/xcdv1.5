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
var eduId = "";
var houseCode = "";
var BA2141205 = {
    init: function () {

        BA2141205.bindBtn();

        eduId = commonUtil.QueryString("eduId");
        houseCode = commonUtil.QueryString("houseCode");
        if (eduId != "" && eduId != null) {
            $("#titleName").html("修改");
            BA2141205.loadEdu(eduId);
        }
        $("#eduStart").mobiscroll(opt);
        $("#eduEnd").mobiscroll(opt);
    },
    loadEdu: function (eduId) {
        var url = ConstantDef.queryEducationInfoUrl();
        var data = {"param": eduId};
        HttpClient.post(url, data, function (data) {
            var houseEdu = data.result;
            if(houseEdu.eduTime !=""){
                var eduTime = houseEdu.eduTime.split("至");
                $("#eduStart").val(eduTime[0]);
                $("#eduEnd").val(eduTime[1]);
            }
            $("#eduComp").val(houseEdu.eduComp);
            $("#eduRecord").val(houseEdu.eduRecord);
            $("#eduDegree").val(houseEdu.eduDegree);
        })
    },
    saveEdu : function(){
        var url = ConstantDef.saveHouseEducationUrl();
        if (eduId != "" && eduId != null) {
            url = ConstantDef.modifyHouseEducationUrl();
        }
        var slCode = localStorage.slCode;
        var eduStart = $("#eduStart").val();
        var eduEnd = $("#eduEnd").val();
        var eduComp = $("#eduComp").val();
        var eduRecord = $("#eduRecord").val();
        var eduDegree = $("#eduDegree").val();
        if (!slCode) {
            webToast("登录信息失效，请查询登录", "middle");
            window.location.href = 'BA2141101.html';
            return;
        }
        if (!eduStart) {
            webToast("请选择开始时间", "middle");
            return;
        }
        if (!eduEnd) {
            webToast("请选择结束时间", "middle");
            return;
        }
        if (eduEnd<eduStart) {
            webToast("开始时间不能大于结束时间", "middle");
            return;
        }
        if (!eduComp) {
            webToast("请输入教育单位", "middle");
            return;
        }
        if (!eduRecord) {
            webToast("请输入学历", "middle");
            return;
        }
        if (!eduDegree) {
            webToast("请输入学位", "middle");
            return;
        }
        var data = {"param": {
            "eduId":eduId,
            "slCode":slCode,
            "eduStart":eduStart,
            "eduEnd":eduEnd,
            "eduComp":eduComp,
            "eduRecord":eduRecord,
            "eduDegree":eduDegree,
            "actId":houseCode,
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

window.onload = window.setTimeout(BA2141205.init, 500);