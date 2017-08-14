/**
 * Created by jiangnan on 15/12/14.
 * Update by gyh 16/1/8
 *
 */
var SL241105Grid;
var SL241105 = {
    detailRows: [],
    trIndex: 0,
    initDataGrid: function () {
        SL241105Grid = $('#SL241105_Grid').grid({
            fnRowCallback: SL241105.rowCallback,
            actionHandler: SL241105.actionHandler
        });
        //this.bindOpenEnven();
    },
    actionHandler: function (rowdata, coltype, row, col) {
        if (coltype == "save") {
            var $check = $("#check" + rowdata.slPdId).val();
            var $checkResult = $("#checkResult" + rowdata.slPdId).val();
            var $trId = "trId" + rowdata.slPdId;
            //var $reasonValue = $("#reason" + $trId).val();
            var $reasonValue = $("#" + $trId).children('td').eq(8).text();
            $("#main-content").postUrl(Main.contextPath + "/SL241105/verifyQly/" + CHECK_RESULT_TYPE,
                {
                    slQltGradeCode: $check
                    , qltMonitorResult: $checkResult
                    , qltNgReason: $reasonValue
                    ,slQltGradeCodeOld:rowdata.slQltGradeCode
                    ,qltMonitorResultOld:rowdata.qltMonitorResult
                    , slCode: rowdata.slCode
                    , pdClassesCode: rowdata.pdClassesCode
                    ,machiningCode:rowdata.machiningCode
                    , 'pdBreedCode': rowdata.pdBreedCode
                    , 'prodEpId': rowdata.prodEpId
                    , 'brandEpId': rowdata.brandEpId
                    , 'brandId': rowdata.brandId
                },function(data){
                    $.alertMessage.info("审核成功!");
                    SL241105Grid.fnDraw();
                },{refreshHtml:false});
        } else if (coltype == 'check') {
            var $check = $("#check" + rowdata.slPdId).val();
            var $trId = "trId" + rowdata.slPdId;
            var $reasonValue = $("#" + $trId).children('td').eq(8).text();
            $("#main-content").postUrl(Main.contextPath + "/SL241105/verifyQly/" + CHECK_TYPE,
                {
                    slQltGradeCode: $check
                    ,slQltGradeCodeOld:rowdata.slQltGradeCode
                    , qltNgReason: $reasonValue
                    , slCode: rowdata.slCode
                    , pdClassesCode: rowdata.pdClassesCode
                    ,machiningCode:rowdata.machiningCode
                    , 'pdBreedCode': rowdata.pdBreedCode
                    , 'prodEpId': rowdata.prodEpId
                    , 'brandEpId': rowdata.brandEpId
                    , 'brandId': rowdata.brandId
                },function(data){
                    $.alertMessage.info("定级成功!");
                    SL241105Grid.fnDraw();
                },{refreshHtml:false});
        } else if (coltype == 'detail') {
            $.pdialog.open("特征信息", Main.contextPath + "/SL241105/featureInit/2", {
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
            }, {
                'slCode': rowdata.slCode,'slPdId': rowdata.slPdId
            });
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
        $('#SL241105_Grid tbody').on('click', 'tr td.details-control', function () {
            var tr = $(this).closest('tr');
            var $trClass = $(tr).attr("class");
            var trSeq = $(this).attr("trIndex");
            if ($trClass.indexOf("details") >= 0) {
                $(tr).removeClass("details");
                $("#appendTr" + trSeq).remove();
            } else {
                if (isDebug == "true") {
                    $(tr).addClass("details");
                    $(tr).after(SL241105.appendTr(trSeq));
                    $('.tree').treegrid();
                } else {
                    $('#main-content').postUrl(Main.contextPath + "/SL241105/findSlPdQltStd", {
                        'filterMap[slCode]': SL241105.detailRows[trSeq].slCode,
                        'filterMap[slPdId]': SL241105.detailRows[trSeq].slPdId
                        //'filterMap[slCode]': '0620100001',
                        //'filterMap[slPdId]': 32
                    }, function (data) {
                        $(tr).addClass("details");
                        $(tr).after(SL241105.appendTrShow(trSeq, data));
                        $('.tree').treegrid();
                    }, {refreshHtml: false});
                }
            }
        });
    },
    appendTrShow: function (index, data) {
        var trHtml = "<tr id='appendTr" + index + "'>";
        trHtml += "<td colspan='12'>";
        if (data != null) {
            trHtml += "<table class='tree dataTable no-footer' width='width: 100%'>";
            trHtml += '<tr style="background:#DBDBDB">';
            trHtml += '<td width="20%"></td>';
            trHtml += '<td width="15%">合格值</td>'
                + '<td width="15%">不合格值</td>'
                + '<td width="15%">是否同意</td>'
                + '<td>卖家产品加工技术标准</td>'
                + '<td width="15%">备注</td>';
            trHtml += '</tr>';
            $.each(data, function (i, sl02bean) {
                trHtml += '<tr>';
                trHtml += '<td>' + sl02bean.mctStdItemName + '</td>';
                trHtml += '<td>' + sl02bean.mctOkVal + '</td>';
                trHtml += '<td>' + sl02bean.mctNgVal + '</td>';
                trHtml += '<td>';
                if(sl02bean.agreeFlg==0){
                    trHtml += '不同意';
                }else{
                    trHtml += '同意';
                }
                trHtml+='</td>';
                trHtml += '<td>' + sl02bean.stdVal + '</td>';
                trHtml += '<td>' + sl02bean.remark + '</td>';
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
        trHtml += "<td colspan='13'>";
        trHtml += "<table class='tree dataTable no-footer' width='width: 100%'>";
        trHtml += '<tr class="treegrid-1" style="background:#DBDBDB">';
        trHtml += '<td width="20%">一类质量标准指标</td>';
        trHtml += '<td width="20%">优良值</td>'
            + '<td width="20%">合格值</td>'
            + '<td width="20%">不合格值</td>'
            + '<td width="20%">卖家产品技术标准</td>';
        trHtml += '</tr>';
        trHtml += '<tr class="treegrid-100 treegrid-parent-1">';
        trHtml += '<td>微生物指标</td>';
        trHtml += '<td></td>';
        trHtml += '<td></td>';
        trHtml += '<td></td>';
        trHtml += '<td></td>';
        trHtml += '</tr>';
        trHtml += '<tr class="treegrid-200 treegrid-parent-100">';
        trHtml += '<td>菌落总数</td>';
        trHtml += '<td>5*102cfu/g</td>';
        trHtml += '<td>5*102cfu/g</td>';
        trHtml += '<td>5*102cfu/g</td>';
        trHtml += '<td>5*102cfu/g</td>';
        trHtml += '</tr>';
        trHtml += '<tr class="treegrid-201 treegrid-parent-100">';
        trHtml += '<td>大肠菌群</td>';
        trHtml += '<td>5*10 MPN/100g</td>';
        trHtml += '<td>5*10 MPN/100g</td>';
        trHtml += '<td>5*10 MPN/100g</td>';
        trHtml += '<td>5*10 MPN/100g</td>';
        trHtml += '</tr>';
        trHtml += '<tr class="treegrid-202 treegrid-parent-100">';
        trHtml += '<td>沙门氏菌</td>';
        trHtml += '<td>不得检出</td>';
        trHtml += '<td>不得检出</td>';
        trHtml += '<td>不得检出</td>';
        trHtml += '<td>不得检出</td>';
        trHtml += '</tr>';
        trHtml += '<tr class="treegrid-202 treegrid-parent-100">';
        trHtml += '<td>出血性大肠埃希氏菌</td>';
        trHtml += '<td>不得检出</td>';
        trHtml += '<td>不得检出</td>';
        trHtml += '<td>不得检出</td>';
        trHtml += '<td>不得检出</td>';
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
        if ($value == "3") {
            $.alertMessage.info("<center>不合格原因:<textarea rows='3' cols='20' id='reason' maxlength='200'></textarea>", function () {
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
        $(tr).children('td').eq(8).css("white-space","pre");
        $(tr).children('td').eq(8).html(data.qltNgReason);
        var $td = $(tr).children('td').eq(7);
        //技术标准定级
        if (data.slQltGradeCode == 2) {
            $td.html("<select onchange='SL241105.checkChange(this,\"" + $trId + "\",7)' id='check" + data.slPdId + "'><option value=''></option><option value='2' selected='selected'>合格</option><option value='3'>不合格</option></select>");
        } else if (data.slQltGradeCode == 3) {
            $td.html("<select onchange='SL241105.checkChange(this,\"" + $trId + "\",7)' id='check" + data.slPdId + "'><option value=''></option><option value='2'>合格</option><option value='3' selected='selected'>不合格</option></select>");
        } else{
            $td.html("<select onchange='SL241105.checkChange(this,\"" + $trId + "\",7)' id='check" + data.slPdId + "'><option value='' selected='selected'></option><option value='2'>合格</option><option value='3'>不合格</option></select>");
        }
        var $td = $(tr).children('td').eq(9);
        //审核结果
        if (data.qltMonitorResult == 1) {
            $td.html("<select  id='checkResult" + data.slPdId + "'><option></option><option value='1' selected='selected'>正确</option><option value='2'>不正确</option><option value='3'>未审核</option></select>");
        } else if (data.qltMonitorResult == 2) {
            $td.html("<select  id='checkResult" + data.slPdId + "'><option></option><option value='1'>正确</option><option value='2' selected='selected'>不正确</option><option value='3'>未审核</option></select>");
        } else if (data.qltMonitorResult == 3) {
            $td.html("<select  id='checkResult" + data.slPdId + "'><option></option><option value='1'>正确</option><option value='2'>不正确</option><option value='3' selected='selected'>未审核</option></select>");
        } else {
            $td.html("<select  id='checkResult" + data.slPdId + "'><option></option><option value='1'>正确</option><option value='2'>不正确</option><option value='3'>未审核</option></select>");
        }
    }
}
$(document).ready(function () {
    //初始化调用
    SL241105.initDataGrid();
});
