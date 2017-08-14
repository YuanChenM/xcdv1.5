/**
 * Created by ni_shaotang on 2016/7/13.
 */
//产品分类
var pdClassCommon = [];
var machiningCommon = [];
var breedCommon = [];
var backUrl='BA2141105.html';
$("#messageLoading").modal();
$('#messageLoading').addClass('animated zoomIn');
var BA2141106 = {
    init: function () {
        BA2141106.loadPdClass();
        BA2141106.bindIndex();
    },
    loadPdClass: function () {
        var classesCode = '01';
        var url = ConstantDef.getFindClassesServerUrl();
        var data = {
            param: {
                "lgcsCode":localStorage.lgcsCode
            }
        };
        HttpClient.post(url, data, function (data) {
            pdClassCommon = data.result;
            var str = "";
            var width = 0;
            var classCopy = $("#classCopy").html();
            for (var i = 0; i < pdClassCommon.length; i++) {
                if (i == 0) {
                    str += classCopy.replace("copyCode", pdClassCommon[i].classesCode).replace("copyCode", pdClassCommon[i].classesCode).replace("copyClass", pdClassCommon[i].classesName).replace("off", "on");
                } else {
                    str += classCopy.replace("copyCode", pdClassCommon[i].classesCode).replace("copyCode", pdClassCommon[i].classesCode).replace("copyClass", pdClassCommon[i].classesName);
                }
                width += (pdClassCommon[i].classesName.length * 18 + 25);
            }
            $("#pdClass").html(str);
            $("#pdClass").width(width);
            //产品类别选择
            $(".pr-menu li").click(function () {
                $(".pr-menu li").removeClass("on");
                $(this).addClass("on");
            });
            BA2141106.loadPdInfo(classesCode);
        }, function (data) {
            webToast("操作失败", "middle");
        });
    },
    loadPdInfo: function (classCode) {
        $("#pdClass li").removeClass('checked');
        $("#pdClass li").addClass('noChecked');
        $("#class" + classCode).removeClass("noChecked");
        $("#class" + classCode).addClass("checked");

        var url = ConstantDef.getFindPdInfoServerUrl();
        var data = {
            param: {
                "lgcsCode":localStorage.lgcsCode,
                "pdCode":classCode
            }
        };
        HttpClient.post(url, data, function (data) {
            machiningCommon = data.result;
            var pdStr = "";
            var breedStr = "";
            var copyMachining = $("#copyMachining").html();
            var copyBreed = $("#copyBreed").html();
            for (var i = 0; i < machiningCommon.length; i++) {
                var machiningCode = machiningCommon[i].machiningCode;
                breedCommon = machiningCommon[i].breedList;
                var num = breedCommon.length % 3;
                if (num == 0) {
                    num = 3;
                }
                breedStr = "";
                for (var j = 0; j < breedCommon.length; j++) {
                    var breedCode = breedCommon[j].breedCode;
                    var breedName = breedCommon[j].breedName;
                    var breeds = copyBreed.replace("copyPdCode", classCode + machiningCode + breedCode).replace("copyBreed", breedName);
                    if (j % 3 == 2 || j == breedCommon.length - 1) {
                        breeds = breeds.replace("is-bg", "no-bg");
                    }
                    if (j >= breedCommon.length - num) {
                        breeds = breeds.replace("off", "no-bor");
                    }
                    breedStr += breeds;
                }
                pdStr += copyMachining.replace("copyMachining", machiningCommon[i].machiningName).replace("breedList", breedStr);

            }
            $("#pdInfo").html(pdStr);

            $('.loader-inner').addClass('animated zoomOut');
            $("#messageLoading").addClass('animated zoomOut');
            $("#simplemodal-overlay").remove();
        }, function (data) {
            webToast("操作失败", "middle");
        });
    },
    jumpUrl: function (pdCode) {
        localStorage.html = 'BA2141106.html';
        window.location.href = "BA2141107.html?pdCode=" + pdCode;
    },
    bindIndex: function () {
        $("#fanhui").bind("touchstart", function () {
            window.location.href = backUrl;
        })
        $("#index").bind("touchstart", function () {
            $(this).addClass("current");
            window.location.href = "BA2141105.html";
        })
        $("#car").bind("touchstart", function () {
            $(this).addClass("current");
            window.location.href = "BA2141108.html";
        })
        $("#center").bind("touchstart", function () {
            $(this).addClass("current");
            var accessType = localStorage.accessType;
            if (accessType == 3) {
                window.location.href = "BA2141121.html";
            } else if (accessType == 2) {
                window.location.href = "BA2141201.html";
            } else {
                window.location.href = 'BA2141101.html';
            }
        })
        $("#setting").bind("touchstart", function () {
            $(this).addClass("current");
            window.location.href = "BA2141199.html";
        })
    }
}
//页面入口

window.onload = window.setTimeout(BA2141106.init, 200);