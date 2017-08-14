/**
 * 买手店列表JS
 *
 * @author cx
 */
var $List_Grid;
var BS2101102 = {
    formId: "bs2101102FormId",
    searchData: "BS2101102_search",
    addButton:"BS2102102_SAVE",
    init: function () {
      $List_Grid = $('#bs2101102_list_grid').grid({
            actionHandler: BS2101102.actionHandler
        });
        this.changeSelect();
        this.queryData();
        this.bindAdd();
    },
    bindAdd:function(){
        $("#"+BS2101102.addButton).click(function(){
            var param = {
                slCode: slCode,
                slCodeDis: slCodeDis,
                slContact: slContact,
                flagNum: $("#hidFlagNum").val()
            };
            $('#main-content').postUrl(Main.contextPath + "/BS2101107/init",param);
        })
    },
    actionHandler: function (rowdata, coltype, row, col) {
        /** 操作按钮 */
        /**已有买家*/
        if (col == 8 || col==10) {
            // 专属买家
            var applyStatus=null;
            if(col == 8){
                applyStatus = "2"
            }
            // 锁定期买家
            if(col==10){
                applyStatus = "1"
            }
            $('#main-content').postUrl(Main.contextPath + "/BS2101103/init", {
                slCode: rowdata.slCode,
                houseAccount: rowdata.houseAccount,
                houseContact: rowdata.houseContact,
                slCodeDis: $('#slCodeDis').val(),
                slContact: $('#slContact').val(),
                houseCodeDis: rowdata.houseCodeDis,
                houseCode: rowdata.houseCode,
                provinceCode: $("#province").val(),
                cityCode: $("#city").val(),
                districtCode: $("#district").val(),
                houseShowName:rowdata.houseShowName,
                applyStatus:applyStatus,
                flagNum: $("#hidFlagNum").val()
            });
        }

        /**新增专属买家*/
        if (col == 7 || col ==9) {
            $('#main-content').postUrl(Main.contextPath + "/BS2101104/init/1", {
                slCode: rowdata.slCode,
                slContact: $('#slContact').val(),
                houseAccount: rowdata.houseAccount,
                houseCode: rowdata.houseCode,
                slCodeDis: $('#slCodeDis').val(),
                houseContact: rowdata.houseContact,
                flagNum: $("#hidFlagNum").val()
            });
        }
        /** 新增冻品管家 */
        if (col == 5) {
            $('#main-content').postUrl(Main.contextPath + "/BS2101107/init", {
                slCode: rowdata.slCode,
                slCodeDis: slCodeDis,
                slContact: slContact,
                provinceCode: $("#province").val(),
                cityCode: $("#city").val(),
                districtCode: $("#district").val(),
                flagNum: $("#hidFlagNum").val()
            });
        }
        /** 编辑冻品管家 */
        if (col == 6) {
            rowdata.slCodeDis = slCodeDis;
            rowdata.slContact = slContact;
            rowdata.flagNum = $("#hidFlagNum").val();
            $('#main-content').postUrl(Main.contextPath + "/BS2101107/init", rowdata);
        }


        /**解除关系 */
        if (col == 11) {
            $.alertMessage.confirm("确定要解除该管家关系？", function(){
                $.alertMessage.close();
                $('#main-content').postUrl(Main.contextPath + "/BS2101113/relieveRelation",{
                    houseCode: rowdata.houseCode,
                    slCode:$("input[name='filterMap[slCode]']").val()
                },function(data){
                    if(data=="S"){
                        FormUtils.setFormValue(BS2101102.formId, "BS2101102");
                        $List_Grid.fnDraw();
                    }
                }, {refreshHtml: false});
            });

        }
        if (col == 12) {
            $('#main-content').postUrl(Main.contextPath + "/BS2101112/init/", {
                    slCode: slCode,
                    buyerId: buyerId,
                    houseCode: rowdata.houseCode,
                    houseCodeDis: rowdata.houseCodeDis,
                    houseContact: rowdata.houseContact,
                    slContact: slContact,
                    houseAccount: rowdata.houseAccount,
                    slCodeDis: slCodeDis,
                    houseShowName:rowdata.houseShowName,
                    flagNum:$("#hidFlagNum").val()
                }
            );
        }
    },

    changeSelect: function () {
        var citySelect = $('#city');
        var districtSelect = $('#district');
        $("#province").change(function () {
            $("#city").find("option").not(":first").remove();
            $("#district").find("option").not(":first").remove();
            var provinceCode = $('#province').val();
            if (provinceCode != 0) {
                $('#main-content').postUrl(Main.contextPath + '/BS2101105/findCity', {provinceCode: provinceCode},
                    function (data) {
                        $.each(data, function (i, item) {
                            citySelect.append("<option value='" + item.cityCode + "'>" + item.cityName + "</option>");
                        });
                    }, {refreshHtml: false});
            }
        });
        $('#city').change(function () {
            $("#district").find("option").not(":first").remove();
            var cityCode = $("#city").val();
            if (cityCode != 0) {
                $('#main-content').postUrl(Main.contextPath + '/BS2101105/findDistrict', {cityCode: cityCode}, function (data2) {
                    $.each(data2, function (i, item) {
                        districtSelect.append("<option value='" + item.districtCode + "'>" + item.districtName + "</option>");
                    });
                }, {refreshHtml: false})
            }
        });
    },
    queryData: function () {
        $("#" + BS2101102.searchData).click(function () {
            formData = getFormData($("#" + BS2101102.formId));
            $('#main-content').postUrl(
                $("#" + BS2101102.formId).attr("action"), formData, function () {
                    $List_Grid.fnDraw();
                }, {refreshHtml: false});
        });
    }
}
$(document).ready(function () {
    // 初始化调用
    BS2101102.init();
    $(function () {
        $("a").each(function () {
            $(this).attr("href",encodeURI($(this).attr("href")));
        })
    })
});
