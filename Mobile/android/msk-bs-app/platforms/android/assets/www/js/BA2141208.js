/**
 * Created by yang_chunyan on 2016/7/12.
 */
var BA2141208Flag = false;
        //用来记录 用户选的是正面还是反面  默认是正面0
         var choose=0;
    //一个公用的方法
    function animate(className){
        $(''+className).one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function(){
            if( $('.footerbottom').attr("class").indexOf("fadeOutDown")!=-1){
                $(".gad").hide();
            }
        });
    }
        //当选择文件  被点击的时候 身份证正反面选项层出现
        $(".jia").click(function(){
            $(".gadChoose").css("display", "block");
            $(".footerbottom").removeClass("animated fadeOutDown");
            $(".footerbottom").addClass("animated fadeInUp");

        })

        $(".carded").click(function(event){
            event.stopPropagation();
            if($(this).attr("class").split(" ")[1]=="rear"){
                choose=1;
            }else{
                choose=0;
            }
            $(".gad").css("display", "block");
            $(".footerbottom").removeClass("animated fadeOutDown");
            $(".footerbottom").addClass("animated fadeInUp");
        })
        //当预览  被点击的时候 预览层出现
        $(".beforeLook").click(function () {
            $(".bigCion").css("display","flex");
        })
        //取消被点击  其对应层消失
        $(".cancel").click(function(){
            $(this).parent().removeClass("animated fadeInUp");
            $(this).parent().addClass("animated fadeOutDown");
            $('.one').one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function(){
                if( $('.footerbottom').attr("class").indexOf("fadeOutDown")!=-1){
                    $(".gadChoose").hide();
                    $('.footerbottom').removeClass("animated fadeOutDown");
                }
            });

        })
        //取消被点击  其对应层消失
        $(".cancelTow").click(function(){
            $(this).parent().removeClass("animated fadeInUp");
            $(this).parent().addClass("animated fadeOutDown");
            $('.tow').one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function(){
                if( $('.tow').attr("class").indexOf("fadeOutDown")!=-1){
                    $(".gad").hide();
                    $('.footerbottom').removeClass("animated fadeOutDown");
                }
            });
        })
//点击空白处时
$(".gad").click(function (e) {
    $(".gad .footerbottom").removeClass("animated fadeInUp");
    $(".gad .footerbottom").addClass("animated fadeOutDown");
    $('.gad .footerbottom').one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function() {
        if ($('.gad .footerbottom').attr("class").indexOf("fadeOutDown") != -1) {
            $(".gad").hide();
        }
    })
})

$(".gadChoose").click(function (e) {
    $(".gadChoose .footerbottom").removeClass("animated fadeInUp");
    $(".gadChoose .footerbottom").addClass("animated fadeOutDown");
    $('.gadChoose .footerbottom').one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function() {
        if ($('.gadChoose .footerbottom').attr("class").indexOf("fadeOutDown") != -1) {
            $(".gadChoose").hide();
        }
    })
})
function loadImage() {
    //拍照并显示在屏幕
    console.log(navigator   );
    navigator.camera.getPicture(onLoadImageSuccess, onLoadImageFail, {destinationType: Camera.DestinationType.DATA_URL});
}
var haveFornt=0;
var haveRear=0;
//拍照成功后回调
function onLoadImageSuccess(imageURI) {
    //这里的图片经过了base64编码
    $(".beforeLook").css("display","inline-block");
    $(".gad,.gadChoose").css("display","none");
    var src = "data:image/jpeg;base64," + imageURI;
    if(choose==0){
        if(haveFornt==0){
            $(".swiper-wrapper").append(" <div class='swiper-slide getImageBetter'> <span><img src='"+src+"' class='fornt' style='max-width: 100%'/></span> </div>");
            haveFornt=1;
        }else{
            $(".fornt").attr("src",src);
            $("#img1").val(src);
        }
    }else {
        if(haveRear==0) {
            $(".swiper-wrapper").append("<div class='swiper-slide getImageBetter'> <span><img src='"+src+"' class='rear' style='max-width: 100%'/></span> </div>");
            haveRear=1;
        }else {
            $(".rear").attr("src", src);
            $("#img2").val(src);
        }
    }
}
//本地图片选择成功后回调此函数
function onLoadImageLocalSuccess(imageURI) {
    $(".beforeLook").css("display","inline-block");
    $(".gad").css("display","none");
    if(choose==0){
        if(haveFornt==0){
            $(".swiper-wrapper").append(" <div class='swiper-slide getImageBetter'> <span><img src='"+imageURI+"' class='fornt' style='max-width: 100%;background: white'/></span> </div>");
            haveFornt=1;
        }else{
            $(".fornt").attr("src",imageURI);
        }
        var imgData = "";
        if(imageURI){
            if (imageURI.indexOf("file://") > -1) {
                imageURI = imageURI.substring(7);
            }
            //读取文件
            var image = new Image();
            image.src = imageURI;
            image.onload = function(){
                imgData = getBase64Image(image);
                $("#img1").val(imgData);
            }
        }
    }else {
        if(haveRear==0) {
            $(".swiper-wrapper").append("<div class='swiper-slide getImageBetter'> <span><img src='"+imageURI+"' class='rear' style='max-width: 100%;background: white'/></span> </div>");
            haveRear=1;
        }else {
            $(".rear").attr("src", imageURI);
        }

        var imgData = "";
        if(imageURI){
            if (imageURI.indexOf("file://") > -1) {
                imageURI = imageURI.substring(7);
            }
            //读取文件
            var image = new Image();
            image.src = imageURI;
            image.onload = function(){
                imgData = getBase64Image(image);
                $("#img2").val(imgData);
            }
        }
    }
}
function loadImageLocal() {
    //获取本地图片并显示在屏幕
    $(".gad,.gadChoose").css("display","none");
    navigator.camera.getPicture(onLoadImageLocalSuccess, onLoadImageFail, {
        destinationType: Camera.DestinationType.FILE_URI,
        sourceType: Camera.PictureSourceType.PHOTOLIBRARY
    });
}
//所有获取图片失败都回调此函数
function onLoadImageFail(message) {
    navigator.notification.alert("拍照失败，原因：" + message, null, "警告");
}
function loadImageUpload() {
    //拍照上传并显示在屏幕
    navigator.camera.getPicture(onLoadImageUploadSuccess, onLoadImageFail, {
        destinationType: Camera.DestinationType.FILE_URI
    });
}
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
 * 将以base64的图片url数据转换为Blob
 * @param urlData
 *            用url方式表示的base64图片数据
 */
function getBase64Url(urlData){
    var arr = urlData.split(',')[1];
    return arr;
}
var BA2141208={
    /**确定按钮点击事件*/
    bindNextBtn:function(){
        if(BA2141208Flag) {
                console.info("touchstart");
                var slAccount = decodeURI(commonUtil.QueryString("slAccount"));
                var psd = commonUtil.QueryString("passWord");
                var tel = $("#tel").val();
                var name = $("#name").val();
                var photo = $("#photo").val();
                var sex = $("input[name='sex']:checked").val();
                var idCard = $("#idCard").val();
                var rhouseAddress = $("#rhouseAdress").val();
                var vprovinceCode = '';
                var vcityCode = '';
                var vdistrictCode = '';
                var rprovinceCode = '';
                var rcityCode = '';
                var rdistrictCode = '';
                if ($("#vhprovince")[0]) {
                    vprovinceCode = $("#vhprovince")[0].title;
                }
                if ($("#vhcity")[0]) {
                    vcityCode = $("#vhcity")[0].title;
                }
                if ($("#vhdistrict")[0]) {
                    vdistrictCode = $("#vhdistrict")[0].title;
                }
                if ($("#rhprovince")[0]) {
                    rprovinceCode = $("#rhprovince")[0].title;
                }
                if ($("#rhcity")[0]) {
                    rcityCode = $("#rhcity")[0].title;
                }
                if ($("#rhdistrict")[0]) {
                    rdistrictCode = $("#rhdistrict")[0].title;
                }
                var rhouseAddress = $("#rhouseAdress").val();
                var vhouseAddress = $("#vhouseAdress").val();
                var wechat = $("#wechat").val();
                var QQ = $("#QQ").val();
                var url = ConstantDef.getNewHouseAccountServerUrl();
                var catogery = $("#208hprovince")[0].title;
                var categorySub = $("#208hcity")[0].title;
                var img1 = $("#img1").val();
                var slCode = localStorage.slCode;
                var obj = new Object();
                var objs = [];
                obj.parentTypeCode = catogery;
                obj.typeCode = categorySub;
                objs.push(obj);
                var data = {
                    param: {
                        slHouseAccount: {
                            slCode: localStorage.slCode,
                            houseTel: tel,
                            houseShowName: name,
                            houseContact: name,
                            houseAccount: slAccount,
                            accountPsd: psd,
                            vprovinceCode: vprovinceCode,
                            vcityCode: vcityCode,
                            vdistrictCode: vdistrictCode,
                            rprovinceCode: rprovinceCode,
                            rcityCode: rcityCode,
                            rdistrictCode: rdistrictCode,
                            rhouseAddress:rhouseAddress,
                            vhouseAddress:vhouseAddress,
                            wechat: wechat,
                            slCodeDis:localStorage.slCodeDis,
                            qq: QQ,
                            slIdcard: idCard,
                            flag1: sex,
                            slConFlg: "1",
                            houseCategory: catogery,
                            houseCategorySub: categorySub,
                            authStatus:"2"
                        },
                        houseTypeList:objs,
                        slHouseIntroduce:{
                            uploadUrl1:getBase64Url($("#img1").val()),
                            uploadUrl2:getBase64Url($("#img2").val())
                        }

                    }
                };
                console.log(data);
                HttpClient.post(url, data, function (data) {
                    if (data.status == "S") {
                        //webToast("保存成功","middle");
                        popTipShow.confirm('保存成功','是否切换到冻品管家登录',['确 定','取 消'],
                            function(e){
                                //callback 处理按钮事件
                                var button = $(e.target).attr('class');
                                if(button == 'ok') {
                                    window.location = "BA2141103.html";
                                }
                                if(button == 'cancel') {
                                    window.location = "BA2141105.html";
                                    this.hide();
                                }
                            });

                    }
                    else {
                        webToast(data.message,"middle");
                    }
                }, function (data) {
                    webToast("操作失败","middle");
                });
        }
    },
    bindFh:function(){
        $('#BA2141208_return').bind("touchstart",function(){
            window.location.href = 'BA2141207.html';
        })
    },
    /**选择事件绑定*/
    bindCheck:function(){
        $(".checkSex").on("touchstart",function(){
            if($(this).hasClass("on")){
                $(this).removeClass("on");
                if($(this).attr("name")=='male'){
                    $(this).next().addClass("on");
                }else{
                    $(this).prev().addClass("on");
                }
            }else{
                $(this).addClass("on");
                if($(this).attr("name")=='male') {
                    $(this).next().removeClass("on");
                }else{
                    $(this).prev().removeClass("on");
                }
            }
        })
    },
    bindCity:function(){
        $(".address-jsj").bind("touchstart",function(){
            citySet.SelCity(this,event);
            return;
        });
        $(".addresst-js").bind("touchstart",function(){
            citySet.SelCity(this,event);
            return;
        });
        $(".typeZeroEight").bind("touchstart",function(){
            typeSet.SelCity(this,event);
            return;
        });
    },
    saveDatas:function(){
        BA2141208Flag = false;
        BA2141120Flag = true;
    },
    myinit:function(){
        BA2141208.bindNextBtn();
        BA2141208.bindCity();
        BA2141208.bindFh();
        $("#houseForm").html5Validate(function () {
        });
        document.addEventListener("backbutton", eventBackButton, false); //返回键
    }
}

window.onload=window.setTimeout(BA2141208.myinit,200);




