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
var workId = "";
var houseCode = "";
var BA2141204 = {
    init: function () {
        workId = commonUtil.QueryString("workId");
        houseCode = commonUtil.QueryString("houseCode");
        console.log("workId:" + workId);
        console.log("houseCode:" + houseCode);
        if (workId != "" && workId != null) {
            $("#titleName").html("修改");
            BA2141204.loadWork(workId);
        }
        $("#workStart").mobiscroll(opt);
        $("#workEnd").mobiscroll(opt);
        BA2141204.bindBtn();
    },
    loadWork: function (workId) {
        var url = ConstantDef.queryWorkInfoUrl();
        var data = {"param": workId};
        HttpClient.post(url, data, function (data) {
            var houseWork = data.result;
            console.log("houseWork:" + houseCode);
            if(houseWork.workTime !=""){
                var workTime = houseWork.workTime.split("至");
                $("#workStart").val(workTime[0]);
                $("#workEnd").val(workTime[1]);
            }
            $("#workComp").val(houseWork.workComp);
            $("#workPosition").val(houseWork.workPosition);
            $("#workStation").val(houseWork.workStation);
        })
    },
    saveWork : function(){
        var url = ConstantDef.saveHouseWorkUrl();
        if (workId != "" && workId != null) {
            url = ConstantDef.modifyHouseWorkUrl();
        }
        var slCode = localStorage.slCode;
        var workStart = $("#workStart").val();
        var workEnd = $("#workEnd").val();
        var workComp = $("#workComp").val();
        var workStation = $("#workStation").val();
        var workPosition = $("#workPosition").val();
        if (!slCode) {
            webToast("登录信息失效，请查询登录", "middle");
            window.location.href = 'BA2141101.html';
            return;
        }
        if (!workStart) {
            webToast("请选择开始时间", "middle");
            return;
        }
        if (!workEnd) {
            webToast("请选择结束时间", "middle");
            return;
        }
        if (workEnd<workStart) {
            webToast("开始时间不能大于结束时间", "middle");
            return;
        }
        if (!workComp) {
            webToast("请输入工作单位", "middle");
            return;
        }
        if (!workStation) {
            webToast("请输入工作岗位", "middle");
            return;
        }
        if (!workPosition) {
            webToast("请输入工作职务", "middle");
            return;
        }
        var data = {"param": {
            "workId":workId,
            "slCode":slCode,
            "workStart":workStart,
            "workEnd":workEnd,
            "workComp":workComp,
            "workStation":workStation,
            "workPosition":workPosition,
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

window.onload = window.setTimeout(BA2141204.init, 500);