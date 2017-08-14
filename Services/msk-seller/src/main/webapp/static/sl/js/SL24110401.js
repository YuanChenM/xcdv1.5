/**
 * Created by gyh on 2016.1.7.
 */
var SL24110401Grid;
var SL24110401 = {
    detailRows: [],
    trIndex: 0,
    backButtonId: 'SL24110401_BACK',
    initDataGrid: function () {
        SL24110401Grid = $('#SL24110401_Grid').grid({
            fnRowCallback: SL24110401.rowCallback,
            fnDraw : function(){
                SL24110401.detailRows = [];
            }
        });
        $('#SL24110401_Grid tbody').find("tr").find("td").unbind();
        this.bindOpenEnven();
        this.bindButton();

    },
    bindButton: function () {
        $("#" + SL24110401.backButtonId).click(function () {
            $.pdialog.close();
        });
    },
    bindOpenEnven: function () {
        $('#SL24110401_Grid tbody').on('click', 'tr td.details-control', function () {
            var tr = $(this).closest('tr');
            var $trClass = $(tr).attr("class");
            var trSeq = $(this).attr("trIndex");
            if ($trClass.indexOf("details") >= 0) {
                $(tr).removeClass("details");
                $("#appendTrDetail" + trSeq).remove();
            } else {
                if(isDebug=="true"){
                    $(tr).addClass("details");
                    $(tr).after(SL24110401.appendTr(trSeq))
                    $('.tree').treegrid();
                }else{
                    $('#main-content').postUrl(Main.contextPath + "/SL241104/findSlPdTncStd", {
                        'filterMap[slCode]': SL24110401.detailRows[trSeq].slCode,
                        'filterMap[slPdId]': SL24110401.detailRows[trSeq].slPdId
                    }, function (data) {
                        $(tr).addClass("details");
                        $(tr).after(SL24110401.appendTrShow(trSeq, data));
                        $('.tree').treegrid();
                    }, {refreshHtml: false});
                }
            }
        });
    },
    appendTrShow: function (index, data) {
        var trHtml = "<tr id='appendTrDetail" + index + "'>";
        trHtml += "<td colspan='4'>";
        if (data!=null) {
            trHtml += "<table class='tree dataTable no-footer' width='width: 100%'>";
            trHtml += '<tr style="background:#DBDBDB">';
            trHtml += '<td width="20%"></td>';
            //加工技术质量等级判断
            if(data.slTncGradeCode!=null){
                if(data.slTncGradeCode==1){
                    trHtml += '<td width="15%"><input type="radio" checked="checked"/>A1</td>'
                        + '<td width="15%">A2</td>'
                        + '<td width="15%">A3</td>'
                        + '<td width="15%">是否同意</td>'
                        + '<td>卖家产品加工质量标准</td>'
                        + '<td width="15%">备注</td>';
                }else if(data.slTncGradeCode==2){
                    trHtml += '<td width="15%">A1</td>'
                        + '<td width="15%"><input type="radio" checked="checked"/>A2</td>'
                        + '<td width="15%">A3</td>'
                        + '<td width="15%">是否同意</td>'
                        + '<td>卖家产品加工质量标准</td>'
                        + '<td width="15%">备注</td>';
                }else if(data.slTncGradeCode==3){
                    trHtml += '<td width="15%">A1</td>'
                        + '<td width="15%">A2</td>'
                        + '<td width="15%"><input type="radio" checked="checked"/>A3</td>'
                        + '<td width="15%">是否同意</td>'
                        + '<td>卖家产品加工质量标准</td>'
                        + '<td width="15%">备注</td>';
                }else{
                    trHtml += '<td width="15%">A1</td>'
                        + '<td width="15%">A2</td>'
                        + '<td width="15%">A3</td>'
                        + '<td width="15%">是否同意</td>'
                        + '<td>卖家产品加工质量标准</td>'
                        + '<td width="15%">备注</td>';
                }
            }else{
                trHtml += '<td width="15%">A1</td>'
                    + '<td width="15%">A2</td>'
                    + '<td width="15%">A3</td>'
                    + '<td width="15%">是否同意</td>'
                    + '<td>卖家产品加工质量标准</td>'
                    + '<td width="15%">备注</td>';
            }
            trHtml += '</tr>';
            $.each(data, function (i, sl01bean) {
                trHtml += '<tr>';
                trHtml += '<td>' + sl01bean.tncStdItemName + '</td>';
                trHtml += '<td style="white-space:pre;">' + sl01bean.tncStdVal1 + '</td>';
                trHtml += '<td style="white-space:pre;">' + sl01bean.tncStdVal2 + '</td>';
                trHtml += '<td style="white-space:pre;">' + sl01bean.tncStdVal3 + '</td>';
                trHtml += '<td>';
                if(sl01bean.agreeFlg==1){
                    trHtml += '同意</td>';
                }else{
                    trHtml += '不同意</td>';
                }
                trHtml += '<td style="white-space:pre;">' + sl01bean.stdVal + '</td>';
                trHtml += '<td style="white-space:pre;">' + sl01bean.remark + '</td>';
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
    rowCallback: function (tr, data) {
        var $trId = "trId" + data.slPdId;
        $(tr).attr("id", $trId);
        //$(tr).attr("trIndex",SL24110401.trIndex);
        var $td = $(tr).children('td').eq(0);
        $td.attr("trIndex", SL24110401.trIndex);
        $td.html("");
        $td.addClass("details-control");
        //$(tr).children('td').eq(4).html('<a title="特征" class="tooltip" href="'+fileSerUrl+'/sl/seller/264050107/1.png" target="_blank" src="'+fileSerUrl+'/sl/seller/264050107/1.png"><img src="'+fileSerUrl+'/sl/seller/264050107/1.png" height="100px" width="100px"/></a>');
        SL24110401.detailRows[SL24110401.trIndex] = data;
        SL24110401.trIndex++;
    }
}
$(document).ready(function () {
    //初始化调用
    SL24110401.initDataGrid();
});
