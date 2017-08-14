/**
 * Created by gyh on 2016.1.7.
 */
var SL24110501Grid;
var SL24110501 = {
    detailRows: [],
    trIndex: 0,
    backButtonId: 'SL24110501_BACK',
    initDataGrid: function () {
        SL24110501Grid = $('#SL24110501_Grid').grid({
            fnRowCallback: SL24110501.rowCallback,
            actionHandler: SL24110501.actionHandler,
            fnDraw : function(){
                SL24110501.detailRows = [];
            }
        });
        $('#SL24110501_Grid tbody').find("tr").find("td").unbind();
        this.bindOpenEnven();
        this.bindButton();

    },
    bindButton: function () {
        $("#" + SL24110501.backButtonId).click(function () {
            $.pdialog.close();
        });
    },
    actionHandler: function (rowdata, coltype, row, col) {
        if (coltype == "save") {
            var $check = $("#check1" + rowdata.pdCode).val();
            var $checkResult = $("#checkResult1" + rowdata.slPdId).val();
            var $trId = "trId" + rowdata.slPdId;
            var $reasonValue = $("#noResult" + $trId).val();
            $("#main-content").postUrl(Main.contextPath + "/SL24110501/verify/" + TYPE,
                {
                    manufacturer: rowdata.manufacturer
                    , classesCode: rowdata.classesCode
                    , breedCode: rowdata.breedCode
                    , pdCode: rowdata.pdCode
                    , check: $check
                    , checkResult: $checkResult
                    , reasonValue: $reasonValue
                });
        }
    },
    bindOpenEnven: function () {

        $('#SL24110501_Grid tbody').on('click', 'tr td.details-control', function () {
            var tr = $(this).closest('tr');
            var $trClass = $(tr).attr("class");
            var trSeq = $(this).attr("trIndex");
            if ($trClass.indexOf("details") >= 0) {
                $(tr).removeClass("details");
                $("#appendTrDetail" + trSeq).remove();
            } else {
                if(isDebug=="true"){
                    $(tr).addClass("details");
                    $(tr).after(SL24110501.appendTr(trSeq))
                    $('.tree').treegrid();
                }else{
                    var test1 = SL24110501.detailRows[trSeq].slCode;
                    var test2 = SL24110501.detailRows[trSeq].slPdId;

                    $('#main-content').postUrl(Main.contextPath + "/SL241105/findSlPdQltStd", {
                        'filterMap[slCode]': SL24110501.detailRows[trSeq].slCode,
                        'filterMap[slPdId]': SL24110501.detailRows[trSeq].slPdId
                    }, function (data) {
                        $(tr).addClass("details");
                        $(tr).after(SL24110501.appendTrShow(trSeq, data));
                        $('.tree').treegrid();
                    }, {refreshHtml: false});
                }
            }
        });
    },
    appendTrShow: function (index,data) {
        var trHtml = "<tr id='appendTrDetail" + index + "'>";
        trHtml += "<td colspan='4'>";
        if (data != null) {
            trHtml += "<table class='tree dataTable no-footer' width='width: 100%'>";
            trHtml += '<tr style="background:#DBDBDB">';
            trHtml += '<td width="20%"></td>';
            //加工技术标准等级判断
            if(data.slQltGradeCode!=null){
                if(data.slQltGradeCode==2){
                    trHtml += '<td width="15%"><input type="radio" checked="checked"/>合格值</td>'
                        + '<td width="15%">不合格值</td>'
                        + '<td width="15%">是否同意</td>'
                        + '<td>卖家产品加工技术标准</td>'
                        + '<td width="15%">备注</td>';
                }else if(data.slQltGradeCode==3){
                    trHtml += '<td width="15%">合格值</td>'
                        + '<td width="15%"><input type="radio" checked="checked"/>不合格值</td>'
                        + '<td width="15%">是否同意</td>'
                        + '<td>卖家产品加工技术标准</td>'
                        + '<td width="15%">备注</td>';
                }else{
                    trHtml += '<td width="15%">合格值</td>'
                        + '<td width="15%">不合格值</td>'
                        + '<td width="15%">是否同意</td>'
                        + '<td>卖家产品加工技术标准</td>'
                        + '<td width="15%">备注</td>';
                }
            }else{
                trHtml += '<td width="15%">合格值</td>'
                    + '<td width="15%">不合格值</td>'
                    + '<td width="15%">是否同意</td>'
                    + '<td>卖家产品加工技术标准</td>'
                    + '<td width="15%">备注</td>';
            }
            trHtml += '</tr>';
            $.each(data, function (i, sl02bean) {
                trHtml += '<tr>';
                trHtml += '<td>' + sl02bean.mctStdItemName + '</td>';
                trHtml += '<td style="white-space:pre;">' + sl02bean.mctOkVal + '</td>';
                trHtml += '<td style="white-space:pre;">' + sl02bean.mctNgVal + '</td>';
                trHtml += '<td>';
                if(sl02bean.agreeFlg==0){
                    trHtml += '不同意';
                }else{
                    trHtml += '同意';
                }
                trHtml+='</td>';
                trHtml += '<td style="white-space:pre;">' + sl02bean.stdVal + '</td>';
                trHtml += '<td style="white-space:pre;">' + sl02bean.remark + '</td>';
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
        var trHtml = "<tr id='appendTrDetail" + index + "'>";
        trHtml += "<td colspan='12'>";
        trHtml += "<table class='tree dataTable no-footer' width='width: 100%'>";
        trHtml += '<tr class="treegrid-1" style="background:#DBDBDB">';
        trHtml += '<td width="20%">一类质量标准指标</td>';
        trHtml += '<td width="20%">优良值</td>'
            + '<td width="20%">合格值</td>'
            + '<td width="20%">不合格值</td>'
            + '<td width="20%">卖家产品质量标准</td>';
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
            $.alertMessage.info("<center>不正确原因:<input id='noResult' maxlength='200'></center>", function () {
                var $reason = $("#noResult").val();
                $("#" + trId).children('td').eq(index + 1).html($reason);
                $("#reasonDiv").html("<input type='hidden' id='noResult" + trId + "'/>");
                $.alertMessage.close();
            });
        } else {
            $("#" + trId).children('td').eq(index + 1).html("");
        }
    },
    checkChange: function (tag, trId, index) {
        var $value = $(tag).val();
        if ($value == "3") {
            $.alertMessage.info("<center>不合格原因:<input id='noResult' maxlength='200'></center>", function () {
                var $reason = $("#noResult").val();
                $("#" + trId).children('td').eq(index + 1).html($reason);
                var hiddenReason = $("#noResult" + trId);
                if (!hiddenReason.val()) {
                    $("#reasonDiv").html("<input value='" + $reason + "' type='hidden' id='noResult" + trId + "'/>");
                } else {
                    hiddenReason.val($reason);
                }
                $.alertMessage.close();
            });
        } else {
            var hiddenReason = $("#noResult" + trId);
            if (!hiddenReason.val()) {
                $("#reasonDiv").html("<input value='' type='hidden' maxlength='200' id='noResult" + trId + "'/>");
            } else {
                hiddenReason.val("");
            }
            $("#" + trId).children('td').eq(index + 1).html("");
        }
    },
    rowCallback: function (tr, data) {
        var $trId = "trId" + data.slPdId;
        $(tr).attr("id", $trId);
        //$(tr).attr("trIndex",SL24110501.trIndex);
        var $td = $(tr).children('td').eq(0);
        $td.attr("trIndex", SL24110501.trIndex);
        $td.html("");
        $td.addClass("details-control");
        //$(tr).children('td').eq(4).html('<a title="特征" class="tooltip" href="'+fileSerUrl+'/sl/seller/264050107/1.png" target="_blank" src="'+fileSerUrl+'/sl/seller/264050107/1.png"><img src="'+fileSerUrl+'/sl/seller/264050107/1.png" height="100px" width="100px"/></a>');
        SL24110501.detailRows[SL24110501.trIndex] = data;
        SL24110501.trIndex++;
    }
}
$(document).ready(function () {
    //初始化调用
    SL24110501.initDataGrid();
});
