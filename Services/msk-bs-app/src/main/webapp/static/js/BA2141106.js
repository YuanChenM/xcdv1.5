/**
 * Created by ni_shaotang on 2016/7/13.
 */
//产品分类
var pdClassCommon = [];
var machiningCommon = [];
var breedCommon = [];
function loadPdClass() {
    var classesCode = '01';
    var flickerAPI = url + 'api/ba/productClass';
    var paramData = {"client": "abcd", "auth": "xxxx", "loginid": "a124"};
    $.ajax({
        type: "POST",
        async: false,
        url: flickerAPI,
        timeout: 60,
        dataType: 'JSON',
        contentType: "application/json",
        data: JSON.stringify(paramData),
        success: function (data) {
            pdClassCommon = data.result;
            var str = "";
            var copyClass = $("#pdClass").html();
            for (var i = 0; i < pdClassCommon.length; i++) {
                if (i == (pdClassCommon.length - 1)) {
                    str += copyClass.replace("display: none", "float: none").replace("copyCode", pdClassCommon[i].classesCode).replace("copyCode", pdClassCommon[i].classesCode).replace("copyClass", pdClassCommon[i].classesName);
                } else {
                    str += copyClass.replace("display: none", "display:block").replace("copyCode", pdClassCommon[i].classesCode).replace("copyCode", pdClassCommon[i].classesCode).replace("copyClass", pdClassCommon[i].classesName);
                }
            }
            $("#pdClass").html(str);
        },
        error: function () {
            alert("error");
        }
    });
    loadPdInfo(classesCode);
}

function loadPdInfo(classCode) {
    $("#pdClass div").removeClass('checked');
    $("#pdClass div").addClass('noChecked');
    $("#class" + classCode).removeClass("noChecked");
    $("#class" + classCode).addClass("checked");

    var flickerAPI = url + 'api/ba/productInfo';
    var paramData = {"client": "abcd", "auth": "xxxx", "loginid": "a124", "param": classCode};
    $.ajax({
        type: "POST",
        async: false,
        url: flickerAPI,
        timeout: 60,
        dataType: 'JSON',
        contentType: "application/json",
        data: JSON.stringify(paramData),
        success: function (data) {
            machiningCommon = data.result;
            var pdStr = "";
            var copyMachining = $("#copyMachining").html();
            var copyBreed = $("#copyBreed").html();
            for (var i = 0; i < machiningCommon.length; i++) {
                pdStr += copyMachining.replace("copyMachining", machiningCommon[i].machiningName);
                var machiningCode = machiningCommon[i].machiningCode;
                breedCommon = machiningCommon[i].breedList;
                for (var j = 0; j < breedCommon.length; j++) {
                    var breedCode = breedCommon[j].breedCode;
                    var breedName = breedCommon[j].breedName;
                    pdStr += copyBreed.replace("copyPdCode", classCode + machiningCode + breedCode).replace("copyBreed", breedName);
                }
            }
            $("#pdInfo").html(pdStr);
        },
        error: function () {
            alert("error");
        }
    });
}
function jumpUrl(pdCode) {
    window.location.href = "BA2141107.html?pdCode=" + pdCode;
}
//页面入口
$(function () {
    loadPdClass();
});