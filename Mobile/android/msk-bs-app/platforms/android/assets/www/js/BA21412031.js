/**
 * Created by ni_shaotang on 2016/9/20.
 */

var backUrl = 'BA2141203.html?houseCode=' + commonUtil.QueryString("houseCode");
var houseWorkCommon = [];
var houseEducationCommon = [];
var houseTrainingCommon = [];
$("#messageLoading").modal();
$("#messageLoading").show();
$('#messageLoading').addClass('animated zoomIn');
var houseCode = "";
//用来记录 用户选的是正面还是反面
var choose;
//一个公用的方法
function animate(className) {
    $('' + className).one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function () {
        if ($('.footerbottom').attr("class").indexOf("fadeOutDown") != -1) {
            $(".gad").hide();
        }
    });
}
//当选择文件  被点击的时候 身份证正反面选项层出现
$(".minContiue").click(function () {
    if ($(this).attr("class").split(" ")[1] == "before") {
        //正面
        choose = 0;
    } else if ($(this).attr("class").split(" ")[1] == "after") {
        //反面
        choose = 1;
    } else {
        //工作照
        choose = 2;
    }
    $(".gad").css("display", "block");
    $(".footerbottom").removeClass("animated fadeOutDown");
    $(".footerbottom").addClass("animated fadeInUp");

})

$(".gad").click(function (e) {
    $(".footerbottom").removeClass("animated fadeInUp");
    $(".footerbottom").addClass("animated fadeOutDown");
    $('.footerbottom').one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function () {
        if ($('.footerbottom').attr("class").indexOf("fadeOutDown") != -1) {
            $(".gad").hide();
        }
    })
})
//当预览  被点击的时候 预览层出现
$(".beforeLook").click(function () {
    $(".bigCion").css("display", "flex");
})
//取消被点击  其对应层消失
$(".cancel").click(function () {
    $(".footerbottom").removeClass("animated fadeInUp");
    $(".footerbottom").addClass("animated fadeOutDown");
    animate(".footerbottom");
})
function loadImage() {
    //拍照并显示在屏幕
    $(".footerbottom").removeClass("animated fadeInUp");
    $(".gad").hide();
    navigator.camera.getPicture(onLoadImageSuccess, onLoadImageFail, {destinationType: Camera.DestinationType.DATA_URL});
}
//拍照成功后回调
function onLoadImageSuccess(imageURI) {
    var src = "data:image/jpeg;base64," + imageURI;
    //这里的图片经过了base64编码
    if (choose == 0) {
        $("#uploadUrl1").attr("src", src);
        $("#img1").val(src);
    } else if (choose == 1) {
        $("#uploadUrl2").attr("src", src);
        $("#img2").val(src);
    } else {
        $("#uploadUrl3").attr("src", src);
        $("#img3").val(src);
    }

}
//本地图片选择成功后回调此函数
function onLoadImageLocalSuccess(imageURI) {
    var fileURI = imageURI;
    var imgData = "";
    if(fileURI){
        if (fileURI.indexOf("file://") > -1) {
            fileURI = fileURI.substring(7);
        }
        //读取文件
        var image = new Image();
        image.src = fileURI;
        image.onload = function(){
            imgData = getBase64Image(image);
            if (choose == 0) {
                $("#uploadUrl1").attr("src", imageURI);
                $("#img1").val(imgData);
            } else if (choose == 1) {
                $("#uploadUrl2").attr("src", imageURI);
                $("#img2").val(imgData);
            } else {
                $("#uploadUrl3").attr("src", imageURI);
                $("#img3").val(imgData);
            }
        }
    }


}
function loadImageLocal() {
    //获取本地图片并显示在屏幕
    $(".footerbottom").removeClass("animated fadeInUp");
    $(".gad").hide();
    navigator.camera.getPicture(onLoadImageLocalSuccess, onLoadImageFail, {
        destinationType: Camera.DestinationType.FILE_URI,
        sourceType: Camera.PictureSourceType.PHOTOLIBRARY
    });
}
//所有获取图片失败都回调此函数
function onLoadImageFail(message) {
    navigator.notification.alert("拍照失败，原因：" + message, null, "警告");
}
var BA21412031 = {
    init: function () {
        houseCode = commonUtil.QueryString("houseCode");
        BA21412031.loadIntroduce();
        BA21412031.loadHouseWork();
        BA21412031.loadHouseEducation();
        BA21412031.loadHouseTraining();
        BA21412031.brackInfo();
    },
    loadIntroduce: function () {
        var url = ConstantDef.queryHouseIntroduceUrl();
        var data = {"param": houseCode};
        HttpClient.post(url, data, function (data) {
            var houseIntroduce = data.result;
            $("#intId").val(houseIntroduce.intId);
            $("#houseCode").val(houseIntroduce.houseCode);
            $("#uploadUrl1").attr("src",houseIntroduce.uploadUrl1);
            $("#uploadUrl2").attr("src",houseIntroduce.uploadUrl2);
            $("#uploadUrl3").attr("src",houseIntroduce.uploadUrl3);
            $("#serviceCommit").val(houseIntroduce.serviceCommit);
            $("#introduce").val(houseIntroduce.introduce);

            $("#simplemodal-container").hide();
            $('.loader-inner').addClass('animated zoomOut');
            $("#messageLoading").addClass('animated zoomOut');
            $("#simplemodal-overlay").hide();
        },function(){
            $("#simplemodal-container").hide();
            $('.loader-inner').addClass('animated zoomOut');
            $("#messageLoading").addClass('animated zoomOut');
            $("#simplemodal-overlay").hide();
            webToast("操作失败", "middle");
        })
    },
    loadHouseWork: function () {
        var url = ConstantDef.queryHouseWorkUrl();
        var data = {"param": {"houseCode": houseCode}};
        HttpClient.post(url, data, function (data) {
            houseWorkCommon = data.result.data;
            var copyWork = $("#copyWork").html();
            var str = "";
            for (var i = 0; i < houseWorkCommon.length; i++) {
                var work = copyWork.replace("workId", houseWorkCommon[i].workId);
                work = work.replace("workId", houseWorkCommon[i].workId);
                work = work.replace("workTime", houseWorkCommon[i].workTime);
                work = work.replace("workComp", houseWorkCommon[i].workComp);
                work = work.replace("workStation", houseWorkCommon[i].workStation);
                work = work.replace("workPosition", houseWorkCommon[i].workPosition);
                str += work;
            }
            $("#workInfo").html(str);
        })
    },
    loadHouseEducation: function () {
        var url = ConstantDef.queryHouseEducationUrl();
        var data = {"param": {"houseCode": houseCode}};
        HttpClient.post(url, data, function (data) {
            houseEducationCommon = data.result.data;
            var copyEdu = $("#copyEdu").html();
            var str = "";
            for (var i = 0; i < houseEducationCommon.length; i++) {
                var edu = copyEdu.replace("eduId", houseEducationCommon[i].eduId);
                edu = edu.replace("eduId", houseEducationCommon[i].eduId);
                edu = edu.replace("eduTime", houseEducationCommon[i].eduTime);
                edu = edu.replace("eduRecord", houseEducationCommon[i].eduRecord);
                edu = edu.replace("eduDegree", houseEducationCommon[i].eduDegree);
                edu = edu.replace("eduComp", houseEducationCommon[i].eduComp);
                str += edu;
            }
            $("#educationInfo").html(str);
        })
    },
    loadHouseTraining: function () {
        var url = ConstantDef.queryHouseTrainingUrl();
        var data = {"param": {"houseCode": houseCode, "slCode": localStorage.slCode}};
        HttpClient.post(url, data, function (data) {
            houseTrainingCommon = data.result.data;
            var copyTrain = $("#copyTrain").html();
            var str = "";
            for (var i = 0; i < houseTrainingCommon.length; i++) {
                var train = copyTrain.replace("trainId", houseTrainingCommon[i].trainId);
                train = train.replace("trainId", houseTrainingCommon[i].trainId);
                train = train.replace("trainTime", houseTrainingCommon[i].trainTime);
                train = train.replace("trainComp", houseTrainingCommon[i].trainComp);
                train = train.replace("trainCertificate", houseTrainingCommon[i].trainCertificate);
                str += train;
            }
            $("#trainingInfo").html(str);
        })
    },
    addWork: function () {
        window.location.href = 'BA2141204.html?houseCode=' + houseCode;
    },
    addEducation: function () {
        window.location.href = 'BA2141205.html?houseCode=' + houseCode;
    },
    addTraining: function () {
        window.location.href = 'BA2141206.html?houseCode=' + houseCode;
    },
    updateWork: function (workId) {
        window.location.href = 'BA2141204.html?houseCode=' + houseCode + "&workId=" + workId;
    },
    updateEducation: function (eduId) {
        window.location.href = 'BA2141205.html?houseCode=' + houseCode + "&eduId=" + eduId;
    },
    updateTraining: function (trainId) {
        window.location.href = 'BA2141206.html?houseCode=' + houseCode + "&trainId=" + trainId;
    },
    delHouseWork: function (workId) {
        var url = ConstantDef.delHouseWorkUrl();
        var data = {"param": {"slCode": localStorage.slCode, "houseCode": houseCode, "workId": workId}};
        popTipShow.confirm('工作经历删除', '确定删除工作经历?此操作不可逆！', ['确 定', '取 消'],
            function (e) {
                //callback 处理按钮事件
                var button = $(e.target).attr('class');
                if (button == 'ok') {
                    //按下确定按钮执行的操作
                    HttpClient.post(url, data, function (data) {
                        webToast(data.message, "middle");
                        BA21412031.loadHouseWork();
                    }, function (data) {
                        webToast("操作失败", "middle");
                    });
                    this.hide();
                }
                if (button == 'cancel') {
                    //按下取消按钮执行的操作
                    this.hide();
                }
            }
        );
    },
    delHouseEducation: function (eduId) {
        var url = ConstantDef.delHouseEducationUrl();
        var data = {"param": {"slCode": localStorage.slCode, "houseCode": houseCode, "eduId": eduId}};
        popTipShow.confirm('教育经历删除', '确定删除教育经历?此操作不可逆！', ['确 定', '取 消'],
            function (e) {
                //callback 处理按钮事件
                var button = $(e.target).attr('class');
                if (button == 'ok') {
                    //按下确定按钮执行的操作
                    HttpClient.post(url, data, function (data) {
                        webToast(data.message, "middle");
                        BA21412031.loadHouseEducation();
                    }, function (data) {
                        webToast("操作失败", "middle");
                    });
                    this.hide();
                }
                if (button == 'cancel') {
                    //按下取消按钮执行的操作
                    this.hide();
                }
            }
        );
    },
    delHouseTraining: function (trainId) {
        var url = ConstantDef.delHouseTrainingUrl();
        var data = {"param": {"slCode": localStorage.slCode, "houseCode": houseCode, "trainId": trainId}};
        popTipShow.confirm('培训经历删除', '确定删除培训经历?此操作不可逆！', ['确 定', '取 消'],
            function (e) {
                //callback 处理按钮事件
                var button = $(e.target).attr('class');
                if (button == 'ok') {
                    //按下确定按钮执行的操作
                    HttpClient.post(url, data, function (data) {
                        webToast(data.message, "middle");
                        BA21412031.loadHouseTraining();
                    }, function (data) {
                        webToast("操作失败", "middle");
                    });
                    this.hide();
                }
                if (button == 'cancel') {
                    //按下取消按钮执行的操作
                    this.hide();
                }
            }
        );
    },
    saveInfo: function () {
        $("#simplemodal-container").show();
        $('.loader-inner').addClass('animated zoomIn');
        $("#messageLoading").addClass('animated zoomIn');
        $("#simplemodal-overlay").show();
        $('.loader-inner').removeClass('animated zoomOut');
        $("#messageLoading").removeClass('animated zoomOut');
        var url = ConstantDef.updateSlHouseIntroduceUrl();
        var intId = $("#intId").val();
        var introduce = $("#introduce").val();
        var serviceCommit = $("#serviceCommit").val();
        var uploadUrl1 = $("#img1").val();
        var uploadUrl2 = $("#img2").val();
        var uploadUrl3 = $("#img3").val();
        var data = {
            "param": {
                "intId": intId,
                "houseCode": houseCode,
                "introduce": introduce,
                "uploadUrl1": getBase64Url(uploadUrl1),
                "uploadUrl2": getBase64Url(uploadUrl2),
                "uploadUrl3": getBase64Url(uploadUrl3),
                "serviceCommit": serviceCommit
            }
        };
        setTimeout(function() {
            HttpClient.post(url, data, function (data) {
                webToast(data.message, "middle");
                $("#simplemodal-container").hide();
                $('.loader-inner').addClass('animated zoomOut');
                $("#messageLoading").addClass('animated zoomOut');
                $("#simplemodal-overlay").hide();
            }, function () {
                webToast("操作失败", "middle");
                $("#simplemodal-container").hide();
                $('.loader-inner').addClass('animated zoomOut');
                $("#messageLoading").addClass('animated zoomOut');
                $("#simplemodal-overlay").hide();
            })
        },100)

    },
    brackInfo: function () {
        $('#fanhui').bind("touchstart", function () {
            window.location.href = backUrl;

        })
    }
}
//页面入口

window.onload = window.setTimeout(BA21412031.init, 500);


//图片的转base64
function getBase64Image(img) {
    var canvas = document.createElement("canvas");
    canvas.width = img.width;
    canvas.height = img.height;
    var ctx = canvas.getContext("2d");
    ctx.drawImage(img, 0, 0, img.width, img.height);
    var dataURL = canvas.toDataURL("image/jpg");
    return dataURL;
}

/**
 * @param base64Codes
 *            图片的base64编码
 */
function sumitImageFile(base64Codes){
    var paramData ={
        param: {
            "lgcsAreaCode":getBase64Url(base64Codes)
        }
    };
    HttpClient.post("http://10.20.16.146:8080/msk-bs/api/bs/houseAccount/uploadFile",paramData,function(data){
        console.log("后端返回-------------"+data)
    },function(data){
        webToast("操作失败","middle");
    });
}

/**
 * 将以base64的图片url数据转换为Blob
 * @param urlData
 *            用url方式表示的base64图片数据
 */
function getBase64Url(urlData){
    var arr = urlData.split(',')[1];
    return arr;
}