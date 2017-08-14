/**
 * Created by jiangnan on 15/12/14.
 * Update by gyh 16/1/8
 *
 */
var SL241104Grid;
var SL241104 = {
    detailRows: [],
    trIndex: 0,
    initDataGrid: function () {
        SL241104Grid = $('#SL241104_Grid').grid({
            fnRowCallback: SL241104.rowCallback,
            actionHandler: SL241104.actionHandler
        });
        //this.bindOpenEnven();

    },
    actionHandler: function (rowdata, coltype, row, col) {
        if (coltype == "save") {
            var $check = $("#check" + rowdata.slPdId).val();
            var $checkResult = $("#checkResult" + rowdata.slPdId).val();
            var $trId = "trId" + rowdata.slPdId;
            var $reasonValue = $("#" + $trId).children('td').eq(8).text();
            $("#main-content").postUrl(Main.contextPath + "/SL241105/verifyTnc/" + CHECK_RESULT_TYPE,
                {
                    slTncGradeCode: $check
                    , tncMonitorResult: $checkResult
                    , tncNgReason: $reasonValue
                    ,slTncGradeCodeOld:rowdata.slTncGradeCode
                    ,tncMonitorResultOld:rowdata.tncMonitorResult
                    , slCode: rowdata.slCode
                    , pdClassesCode: rowdata.pdClassesCode
                    ,machiningCode:rowdata.machiningCode
                    , 'pdBreedCode': rowdata.pdBreedCode
                    , 'prodEpId': rowdata.prodEpId
                    , 'brandEpId': rowdata.brandEpId
                    , 'brandId': rowdata.brandId
                },function(data){
                    $.alertMessage.info("审核成功!");
                    SL241104Grid.fnDraw();
                },{refreshHtml:false});
        } else if (coltype == 'check') {
            var $check = $("#check" + rowdata.slPdId).val();
            var $trId = "trId" + rowdata.slPdId;
            var $reasonValue = $("#" + $trId).children('td').eq(8).text();
            $("#main-content").postUrl(Main.contextPath + "/SL241105/verifyTnc/" + CHECK_TYPE,
                {
                    slTncGradeCode: $check
                    ,slTncGradeCodeOld:rowdata.slTncGradeCode
                    , tncNgReason: $reasonValue
                    , slCode: rowdata.slCode
                    , pdClassesCode: rowdata.pdClassesCode
                    ,machiningCode:rowdata.machiningCode
                    , 'pdBreedCode': rowdata.pdBreedCode
                    , 'prodEpId': rowdata.prodEpId
                    , 'brandEpId': rowdata.brandEpId
                    , 'brandId': rowdata.brandId
                },function(data){
                    $.alertMessage.info("定级成功!");
                    SL241104Grid.fnDraw();
                },{refreshHtml:false});
        } else if (coltype == 'detail') {
            $.pdialog.open("特征信息", Main.contextPath + "/SL241105/featureInit/1", {
                width: 700,
                height: 500
            }, {
                'filterMap[slCode]': rowdata.slCode, 'filterMap[classesCode]': rowdata.pdClassesCode,
                'filterMap[machiningCode]': rowdata.machiningCode,
                'filterMap[breedCode]': rowdata.pdBreedCode, 'filterMap[breedName]': rowdata.breedName,
                'filterMap[classesName]': rowdata.classesName, 'filterMap[prodEpId]': rowdata.prodEpId,
                'filterMap[brandEpId]': rowdata.brandEpId, 'filterMap[brandId]': rowdata.brandId
            });
        }else if (coltype == 'audit') {
            $.pdialog.open("其他标准信息", Main.contextPath + "/SL241122/init/2", {
                width: 700,
                height: 500
            },rowdata);
        }else if (coltype == 'search') {
            //产品品种图片
            //if(rowdata.qltAuditDate) {
            //    var qltAuditDate = new Date(rowdata.qltAuditDate);
            //    rowdata.qltAuditDate = qltAuditDate.format('yyyy-MM-dd hh:mm:ss');
            //}
            //if(rowdata.qltMonitorDate) {
            //    var qltMonitorDate = new Date(rowdata.qltMonitorDate);
            //    rowdata.qltMonitorDate = qltMonitorDate.format('yyyy-MM-dd hh:mm:ss');
            //}
            //if(rowdata.tncAuditDate) {
            //    var tncAuditDate = new Date(rowdata.tncAuditDate);
            //    rowdata.tncAuditDate = tncAuditDate.format('yyyy-MM-dd hh:mm:ss');
            //}
            //if(rowdata.tncMonitorDate) {
            //    var tncMonitorDate = new Date(rowdata.tncMonitorDate);
            //    rowdata.tncMonitorDate = tncMonitorDate.format('yyyy-MM-dd hh:mm:ss');
            //}
            //rowdata.slShowName = slShowName;

            // zhang_chi  20160701 meger代码
            var imageDate={};
            //imageDate.slShowName = slShowName;
            imageDate.prodEpId=rowdata.prodEpId;
            imageDate.pdClassesCode=rowdata.pdClassesCode;
            imageDate.machiningCode=rowdata.machiningCode;
            imageDate.pdBreedCode=rowdata.pdBreedCode;
            $.pdialog.open(rowdata.classesName + rowdata.breedName + "图片信息", Main.contextPath + "/SL241116/showImage", {
                width: 700,
                height: 500
            }, imageDate);
        }
    },
    bindOpenEnven: function () {
        $('#SL241104_Grid tbody').on('click', 'tr td.details-control', function () {
            var tr = $(this).closest('tr');
            var $trClass = $(tr).attr("class");
            var trSeq = $(this).attr("trIndex");
            if ($trClass.indexOf("details") >= 0) {
                $(tr).removeClass("details");
                $("#appendTr" + trSeq).remove();
            } else {
                if (isDebug == "true") {
                    $(tr).addClass("details");
                    $(tr).after(SL241104.appendTr(trSeq));
                    $('.tree').treegrid();
                } else {
                    $('#main-content').postUrl(Main.contextPath + "/SL241104/findSlPdTncStd", {
                        'filterMap[slCode]': SL241104.detailRows[trSeq].slCode,
                        'filterMap[slPdId]': SL241104.detailRows[trSeq].slPdId
                        //'filterMap[slCode]': '0620100001',
                        //'filterMap[slPdId]': 32
                    }, function (data) {
                        $(tr).addClass("details");
                        $(tr).after(SL241104.appendTrShow(trSeq, data));
                        $('.tree').treegrid();
                    }, {refreshHtml: false});
                }
            }
        });
    },
    appendTrShow: function (index, data) {
        var trHtml = "<tr id='appendTr" + index + "'>";
        trHtml += "<td colspan='12'>";
        if (data!=null) {
            trHtml += "<table class='tree dataTable no-footer' width='width: 100%'>";
            trHtml += '<tr style="background:#DBDBDB">';
            trHtml += '<td width="20%"></td>';
            trHtml += '<td width="15%">A1</td>'
                + '<td width="15%">A2</td>'
                + '<td width="15%">A3</td>'
                + '<td width="15%">是否同意</td>'
                + '<td>卖家产品加工质量标准</td>'
                + '<td width="15%">备注</td>';
            trHtml += '</tr>';
            $.each(data, function (i, sl01bean) {
                trHtml += '<tr>';
                trHtml += '<td>' + sl01bean.tncStdItemName + '</td>';
                trHtml += '<td>' + sl01bean.tncStdVal1 + '</td>';
                trHtml += '<td>' + sl01bean.tncStdVal2 + '</td>';
                trHtml += '<td>' + sl01bean.tncStdVal3 + '</td>';
                trHtml += '<td>';
                if(sl01bean.agreeFlg==1){
                    trHtml += '同意</td>';
                }else{
                    trHtml += '不同意</td>';
                }
                trHtml += '<td>' + sl01bean.stdVal + '</td>';
                trHtml += '<td>' + sl01bean.remark + '</td>';
                trHtml += '</tr>';
            });
            trHtml += "</table>";
        } else {
            trHtml += "无";
        }
        trHtml += "</td>";
        trHtml += "</tr>";
        return trHtml;
    },
    appendTr: function (index) {
        var trHtml = "<tr id='appendTr" + index + "'>";
        trHtml += "<td colspan='12'>";
        trHtml += "<table class='tree dataTable no-footer' width='width: 100%'>";
        trHtml += '<tr>';
        trHtml += '<td width="20%"></td>';
        trHtml += '<td width="15%">A1</td>'
            + '<td width="15%">A2</td>'
            + '<td width="15%">A3</td>'
            + '<td width="15%">是否同意</td>'
            + '<td>卖家产品卫生标准</td>';
        trHtml += '</tr>';
        trHtml += '<tr>';
        trHtml += '<td>产品解冻失水率指标</td>';
        trHtml += '<td>≤9.5%</td>';
        trHtml += '<td>≤8%</td>';
        trHtml += '<td>≤6.5%</td>';
        trHtml += '<td>同意</td>';
        trHtml += '<td>6</td>';
        trHtml += '</tr>';

        trHtml += '<tr>';
        trHtml += '<td>产品均匀度指标</td>';
        trHtml += '<td>160+10g</td>';
        trHtml += '<td>160+10g</td>';
        trHtml += '<td>160+5g</td>';
        trHtml += '<td>不同意</td>';
        trHtml += '<td>160+5g</td>';
        trHtml += '</tr>';
        trHtml += '<tr class="treegrid-1-1">';
        trHtml += '<td>产品感官指标</td>';
        trHtml += '<td></td>';
        trHtml += '<td></td>';
        trHtml += '<td></td>';
        trHtml += '<td></td>';
        trHtml += '<td></td>';
        trHtml += '</tr>';

        trHtml += '<tr class="treegrid-2-1 treegrid-parent-1-1">';
        trHtml += '<td>色泽</td>';
        trHtml += '<td>粉红</td>';
        trHtml += '<td>粉红</td>';
        trHtml += '<td>暗黄</td>';
        trHtml += '<td>同意</td>';
        trHtml += '<td></td>';
        trHtml += '</tr>';
        trHtml += '<tr class="treegrid-2-2 treegrid-parent-1-1">';
        trHtml += '<td>淤血</td>';
        trHtml += '<td>无</td>';
        trHtml += '<td>无</td>';
        trHtml += '<td>≤0.5cm²</td>';
        trHtml += '<td>同意</td>';
        trHtml += '<td></td>';
        trHtml += '</tr>';


        trHtml += '<tr>';
        trHtml += '<td>产品加工配方</td>';
        trHtml += '<td>/</td>';
        trHtml += '<td>/</td>';
        trHtml += '<td>/</td>';
        trHtml += '<td>同意</td>';
        trHtml += '<td>/</td>';
        trHtml += '</tr>';
        trHtml += '<tr>';
        trHtml += '<td>产品工艺流程</td>';
        trHtml += '<td>精修</td>';
        trHtml += '<td>精修</td>';
        trHtml += '<td>带边油</td>';
        trHtml += '<td>不同意</td>';
        trHtml += '<td>带边油</td>';
        trHtml += '</tr>';
        trHtml += "</table>";
        trHtml += "</td>";
        trHtml += "</tr>";

        return trHtml;
    },
    checkResultChange: function (tag, trId, index) {
        var $value = $(tag).val();
        if ($value == "2") {
            $.alertMessage.info("<center>不正确原因:<input id='reason' maxlength='200'></center>", function () {
                var $reason = $("#reason").val();
                $("#" + trId).children('td').eq(index + 1).html($reason);
                $("#reasonDiv").html("<input type='hidden' id='reason" + trId + "'/>");
                $.alertMessage.close();
            });
        } else {
            $("#" + trId).children('td').eq(index + 1).html("");
        }
    },
    checkChange: function (tag, trId, index) {
        var $value = $(tag).val();
        if ($value == "4") {
            $.alertMessage.info("<center>不通过原因:<textarea rows='3' cols='20' id='reason'  maxlength='200'></textarea></center>", function () {
                var $reason = $("#reason").val();
                $("#" + trId).children('td').eq(index + 1).css("white-space","pre");
                $("#" + trId).children('td').eq(index + 1).html($reason);
                var hiddenReason = $("#reason" + trId);
                if (!hiddenReason.val()) {
                    $("#reasonDiv").html("<input value='" + $reason + "' type='hidden' id='reason" + trId + "'/>");
                } else {
                    hiddenReason.val($reason);
                }
                $.alertMessage.close();
            });
        } else {
            var hiddenReason = $("#reason" + trId);
            if (!hiddenReason.val()) {
                $("#reasonDiv").html("<input value='' type='hidden' id='reason" + trId + "'/>");
            } else {
                hiddenReason.val("");
            }
            $("#" + trId).children('td').eq(index + 1).html("");
        }
    },
    rowCallback: function (tr, data) {
        var $trId = "trId" + data.slPdId;
        $(tr).attr("id", $trId);
        var $td = $(tr).children('td').eq(0);
        $td.attr("trIndex", SL241104.trIndex);
        //$td.html("");
        $(tr).children('td').eq(8).css("white-space","pre");
        $(tr).children('td').eq(8).html(data.tncNgReason);
        //$td.addClass("details-control");
        $td = $(tr).children('td').eq(7);
        //卫生标准定级
        if (data.slTncGradeCode == 1) {
            $td.html("<select onchange='SL241104.checkChange(this,\"" + $trId + "\",7)' id='check" + data.slPdId + "'><option value=''></option><option value='1' selected='selected'>A1</option><option value='2'>A2</option><option value='3'>A3</option><option value='4'>不通过</option></select>");
        } else if (data.slTncGradeCode == 2) {
            $td.html("<select  onchange='SL241104.checkChange(this,\"" + $trId + "\",7)' id='check" + data.slPdId + "'><option value=''></option><option value='1'>A1</option><option value='2' selected='selected'>A2</option><option value='3'>A3</option><option value='4'>不通过</option></select>");
        } else if (data.slTncGradeCode == 3) {
            $td.html("<select  onchange='SL241104.checkChange(this,\"" + $trId + "\",7)' id='check" + data.slPdId + "'><option value=''></option><option value='1'>A1</option><option value='2'>A2</option><option value='3' selected='selected'>A3</option><option value='4'>不通过</option></select>");
        }else if (data.slTncGradeCode == 4) {
            $td.html("<select  onchange='SL241104.checkChange(this,\"" + $trId + "\",7)' id='check" + data.slPdId + "'><option value=''></option><option value='1'>A1</option><option value='2'>A2</option><option value='3'>A3</option><option value='4' selected='selected'>不通过</option></select>");
        }
        else{
            $td.html("<select name='check' onchange='SL241104.checkChange(this,\"" + $trId + "\",7)' id='check" + data.slPdId + "'><option value='' selected='selected'></option><option value='1'>A1</option><option value='2'>A2</option><option value='3'>A3</option><option value='4'>不通过</option></select>");
        }
        $td = $(tr).children('td').eq(9);
        //审核结果
        if (data.tncMonitorResult == 1) {
            $td.html("<select  id='checkResult" + data.slPdId + "'><option></option><option value='1' selected='selected'>正确</option><option value='2'>不正确</option><option value='3'>未审核</option></select>");
        } else if (data.tncMonitorResult == 2) {
            $td.html("<select  id='checkResult" + data.slPdId + "'><option></option><option value='1'>正确</option><option value='2' selected='selected'>不正确</option><option value='3'>未审核</option></select>");
        } else if (data.tncMonitorResult == 3) {
            $td.html("<select  id='checkResult" + data.slPdId + "'><option></option><option value='1'>正确</option><option value='2'>不正确</option><option value='3' selected='selected'>未审核</option></select>");
        } else {
            $td.html("<select  id='checkResult" + data.slPdId + "'><option></option><option value='1'>正确</option><option value='2'>不正确</option><option value='3'>未审核</option></select>");
        }
        SL241104.detailRows[SL241104.trIndex] = data;
        SL241104.trIndex++;
    }
}
$(document).ready(function () {
    //初始化调用
    SL241104.initDataGrid();
});
