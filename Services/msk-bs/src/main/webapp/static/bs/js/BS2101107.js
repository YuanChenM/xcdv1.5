/**
 * 冻品管家新增
 *
 * @author cx
 */
var idIndex = 1;
var BS2101107 = {
    formId: "BS2101107FormInfo",
    saveDataButtonId: "BS2102107_SAVE",
    init: function () {
        this.stopFormSubmit();
        this.bindDatePicber('#birthday');
        this.changeSelect();
        this.saveData();
        this.addHouse();
        /**Add: 横展开添加日期清除按钮 2016/09/09   BY  任强  Start */
        this.closeDate();
        /**Add: 横展开添加日期清除按钮 2016/09/09   BY  任强  End */
    },

    stopFormSubmit: function () {
        $("#" + BS2101107.formId).submit(function (e) {
            e.stopPropagation();
            return false;
        })
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
            dateFormat: "yy-mm-dd",    // 日期格式
            maxDate: new Date(),
            yearRange:'-50:+0'
        });
    },
    changeSelect: function () {
        $(".province").each(function (pIndex) {
            $(this).change(function () {
                $(".city").eq(pIndex).find("option").not(":first").remove();
                $(".district").eq(pIndex).find("option").not(":first").remove();
                var citySelect = $('.city').eq(pIndex);
                var provinceCode = $('.province').eq(pIndex).val();
                if (provinceCode != 0) {
                    $('#main-content').postUrl(Main.contextPath + '/BS2101105/findCity', {provinceCode: provinceCode},
                        function (data) {
                            $.each(data, function (i, item) {
                                citySelect.append("<option value='" + item.cityCode + "'>" + item.cityName + "</option>");
                            });
                        }, {refreshHtml: false});
                }
            })
        });

        $(".city").each(function (cIndex) {
            $(this).change(function () {
                $(".district").eq(cIndex).find("option").not(":first").remove();
                var cityCode = $(".city").eq(cIndex).val();
                var districtSelect = $('.district').eq(cIndex);
                if (cityCode != 0) {
                    $('#main-content').postUrl(Main.contextPath + '/BS2101105/findDistrict', {cityCode: cityCode}, function (data2) {
                        $.each(data2, function (i, item) {
                            districtSelect.append("<option value='" + item.districtCode + "'>" + item.districtName + "</option>");
                        });
                    }, {refreshHtml: false})
                }
            })
        });
    },
    saveData: function () {
        $("#" + BS2101107.saveDataButtonId).click(function () {
            //获取页面数据
            var parphone = /^1[34578]\d{9}$/;
            var houseAccount = $("#houseAccount").val();
            var accountPsd = $("#accountPsd").val();
            var houseShowName = $("#houseShowName").val();
            var flag1 = $('input:radio:checked').val();
            var flag7 = $("#birthday").val();
            var houseTel = $("#houseTel").val();
            var wechat = $("#wechat").val();
            var qq = $("#qq").val();
            var rprovinceCode = $("#rprovinceCode").val();
            var rcityCode = $("#rcityCode").val();
            var rdistrictCode = $("#rdistrictCode").val();
            var rhouseAddress = $("#rhouseAddress").val();
            var provinceCode = $("#provinceCode").val();
            var cityCode = $("#cityCode").val();
            var districtCode = $("#districtCode").val();
            var houseAddress = $("#houseAddress").val();
            var slIdcard = $("#slIdcard").val();
            var slCodeDis = $("#slCodeDis").val();
            var slCode = $("#slCode").val();
            var vprovinceCode = $("#vprovinceCode").val();
            var vcityName = $("#vcityCode").find("option:selected").text();
            var vcityCode = $("#vcityCode").val();
            var vdistrictName = $("#vdistrictCode").find("option:selected").text();
            var vdistrictCode = $("#vdistrictCode").val();
            var vhouseAddress = $("#vhouseAddress").val();
            var rcityName = "";
            var rdistrictName = "";
            var cityName = "";
            var districtName = "";
            var houseCode = $("#houseCode").val();
            var vlgcsAreaCode = $("#vlgcsAreaCode").val();

            if(!flag1){
                flag1 = "";
            }
            if(!flag7){
                flag7 = "";
            }
            if(!wechat){
                wechat = "";
            }
            if(!qq){
                qq = "";
            }
            if(!rhouseAddress){
                rhouseAddress = "";
            }
            if(rprovinceCode && parseInt(rprovinceCode) == 0){
                rprovinceCode = "";
            }
            if(!rprovinceCode){
                rprovinceCode = "";
            }
            if(rcityCode && parseInt(rcityCode) == 0){
                rcityCode = "";
            }
            if(!rcityCode){
                rcityCode = "";
            }
            if(rdistrictCode && parseInt(rdistrictCode) == 0){
                rdistrictCode = "";
            }
            if(!rdistrictCode){
                rdistrictCode = "";
            }
            if(provinceCode && parseInt(provinceCode) == 0){
                provinceCode = "";
            }
            if(!provinceCode){
                provinceCode = "";
            }
            if(cityCode && parseInt(cityCode) == 0){
                cityCode = "";
            }
            if(!cityCode){
                cityCode = "";
            }
            if(districtCode && parseInt(districtCode) == 0){
                districtCode = "";
            }
            if(!districtCode){
                districtCode = "";
            }


            if(rcityCode && parseInt(rcityCode) != 0){
                rcityName = $("#rcityCode").find("option:selected").text();
            }
            if(rdistrictCode && parseInt(rdistrictCode) != 0){
                rdistrictName = $("#rdistrictCode").find("option:selected").text();
            }
            if(cityCode && parseInt(cityCode) != 0){
                cityName = $("#cityCode").find("option:selected").text();
            }
            if(districtCode && parseInt(districtCode) != 0){
                districtName = $("#districtCode").find("option:selected").text();
            }

            if (!houseAccount) {
                $.alertMessage.info("管家账号不能为空");
                return false;
            }
            if (!houseCode &&  houseAccount.length > 20) {
                $.alertMessage.info("管家账号不能超过20位");
                return false;
            }
            if (!accountPsd) {
                $.alertMessage.info("登录密码不能为空");
                return false;
            }
            if (accountPsd.length < 6 || accountPsd.length > 11) {
                $.alertMessage.info("登录密码为6-11位");
                return false;
            }
            if (!houseShowName) {
                $.alertMessage.info("姓名不能为空");
                return false;
            }
            if (!houseTel) {
                $.alertMessage.info("手机不能为空");
                return false;
            }
            if (!parphone.test(houseTel)) {
                $.alertMessage.info("手机格式不正确");
                return false;
            }
            if (!slIdcard) {
                $.alertMessage.info("身份证号不能为空");
                return false;
            }
            if(slIdcard){
                if(slIdcard.length != 15 && slIdcard.length != 18){
                    $.alertMessage.info("身份证号格式不正确");
                    return false;
                }
            }
            if (!slCodeDis) {
                $.alertMessage.info("所属买手不能为空");
                return false;
            }
            if (!slCode) {
                $.alertMessage.info("买手code获取失败");
                return false;
            }
            if(wechat && wechat.length > 200){
                $.alertMessage.info("微信不能超过200个字");
                return false;
            }
            if(qq && qq.length > 200){
                $.alertMessage.info("QQ不能超过200个字");
                return false;
            }
            if(rhouseAddress && rhouseAddress.length > 100){
                $.alertMessage.info("户籍地址不能超过100个字");
                return false;
            }
            if(houseAddress && houseAddress.length > 100){
                $.alertMessage.info("工作地址不能超过100个字");
                return false;
            }
            if (!vprovinceCode || parseInt(vprovinceCode) == 0) {
                $.alertMessage.info("请选择虚拟经营地址省份");
                return false;
            }
            if (!vcityCode || parseInt(vcityCode) == 0) {
                $.alertMessage.info("请选择虚拟经营地址城市");
                return false;
            }
            if (!vdistrictCode || parseInt(vdistrictCode) == 0) {
                $.alertMessage.info("请选择虚拟经营地址区域");
                return false;
            }
            if (!vdistrictCode || parseInt(vdistrictCode) == 0) {
                $.alertMessage.info("请输入虚拟经营详细地址");
                return false;
            }
            //获取选中的管家分类
            var houseTypeList = new Array();
            var arrayIndex = 0;
            $(".parentType").each(function (index) {
                var houseType = {};
                var parentTypeCode = $(this).attr("data-parentTypeCode").toString();
                var ids = "";
                var cName = "son" + index;
                $("input[name=" + cName + "]:checkbox").each(function (checkIndex) {
                    var checkbox = $("input[name=" + cName + "]:checkbox").eq(checkIndex);
                    if (true == checkbox.prop("checked")) {
                        ids += checkbox.attr('value') + ',';
                    }
                })
                if (ids) {
                    ids = ids.substring(0, ids.length - 1)
                    houseType["parentTypeCode"] = parentTypeCode;
                    houseType["typeCode"] = ids;
                    houseTypeList[arrayIndex] = houseType;
                    arrayIndex++;
                }
            })
            if (houseTypeList.length == 0) {
                $.alertMessage.info("请选择冻品管家分类");
                return false;
            }

            var houseAccount = {
                houseAccount: houseAccount,
                accountPsd: accountPsd,
                houseShowName: houseShowName,
                flag1: flag1,
                flag7: flag7,
                houseTel: houseTel,
                wechat: wechat,
                qq: qq,
                rprovinceCode: rprovinceCode,
                rcityCode: rcityCode,
                rdistrictCode: rdistrictCode,
                rhouseAddress: rhouseAddress,
                provinceCode: provinceCode,
                cityCode: cityCode,
                districtCode: districtCode,
                houseAddress: houseAddress,
                slIdcard: slIdcard,
                slCodeDis: slCodeDis,
                slCode: slCode,
                vprovinceCode: vprovinceCode,
                vcityCode: vcityCode,
                vdistrictCode: vdistrictCode,
                vhouseAddress: vhouseAddress,
                houseCode : houseCode,
                vlgcsAreaCode : vlgcsAreaCode
            };

            var param = {
                slHouseAccount: houseAccount,
                houseTypeList: houseTypeList,
                vcityName: vcityName,
                vdistrictName: vdistrictName,
                rcityName: rcityName,
                rdistrictName: rdistrictName,
                cityName: cityName,
                districtName: districtName
            };
            $('#main-content').postUrl($("#" + BS2101107.formId).attr("action"), JSON.stringify(param), function (data) {
                var returnCode = data.returnCode;
                if (returnCode == "1001") {
                    var flagNum = $("#flagNum").val();
                    $.alertMessage.info("保存成功!",function () {
                        var param = {
                            slCode : data.result.slCode,
                            houseCode : data.result.houseCode,
                            flagNum : flagNum
                        };
                        $('#main-content').postUrl(Main.contextPath + "/BS2101107/init",param);
                        $.alertMessage.close();
                    });
                } else {
                    $.alertMessage.info(data.message);
                }
            }, {refreshHtml: false, dataType: "json", contentType: 'application/json;charset=utf-8'});

        });

    },

    addHouse: function () {
        $("#BS2101107_ADDHOUSE").click(function () {
            $.pdialog.open("冻品管家管理设置新增编辑", Main.contextPath + "/BS2102110/init", {width: "50%"});
        });
    },
    addBuyerCode: function (slCode, slCodeDis,slContact) {
        $("#slCodeDis").val(slCodeDis);
        $("#slCode").val(slCode);
        $("#slContact").val(slContact);
        $("#lab_slContact").text(slContact);
    },
    /**Add: 横展开添加日期清除按钮 2016/09/09   BY  任强  Start */
    closeDate : function(){
        $(document).on("click","button.ui-datepicker-close",function(){
            $.datepicker._clearDate($.datepicker._curInst.input);
        });
    }
    /**Add: 横展开添加日期清除按钮 2016/09/09   BY  任强  End */

};

$(document).ready(function () {
    // 初始化调用
    BS2101107.init();
    $(function () {
        $("a").each(function () {
            $(this).attr("href",encodeURI($(this).attr("href")));
        })
    })
});
