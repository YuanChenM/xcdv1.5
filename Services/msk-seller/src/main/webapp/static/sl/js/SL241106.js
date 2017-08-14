/**
 * Created by jiangnan on 15/12/15.
 */
var SL241106 = {
    SL241106Grid : null,
    trIndex : 0,
    initDataGrid : function(){
        SL241106.SL241106Grid = $('#SL241106_Grid').grid({
            fnRowCallback : SL241106.rowCallback,
            actionHandler:SL241106.actionHandler
        });
    },
    actionHandler: function (rowdata, coltype, row, col) {
        if (coltype == "save") {
            if(col==26){
                var $check=$('#check'+row).val();
                var $checkResult=$('#checkResult'+row).val();
                $("#main-content").postUrl(Main.contextPath + "/SL241106/save/check",
                    {
                        slPdPkgId:rowdata.slPdPkgId,
                        auditResult: $check
                    });
            }else if(col==27){
                var $check=$('#check'+row).val();
                var $checkResult=$('#checkResult'+row).val();
                $("#main-content").postUrl(Main.contextPath + "/SL241106/save/checkResult",
                    {
                        slPdPkgId:rowdata.slPdPkgId,
                        auditResult: $check,
                        monitorResult: $checkResult
                    });
            }
        }
    },
    rowCallback : function(tr,data){
        var $td = $(tr).children('td').eq(24);
        if(data.auditResult==1){
            $td.html("<select style='width:110px;' id='check"+SL241106.trIndex+"'><option></option><option value='1' selected='selected'>同意</option><option value='2'>增加标准包装规格</option><option value='3'>要求执行神龙客标准</option></select>");
        }else if(data.auditResult==2){
            $td.html("<select style='width:110px;' id='check"+SL241106.trIndex+"'><option></option><option value='1'>同意</option><option value='2' selected='selected'>增加标准包装规格</option><option value='3'>要求执行神龙客标准</option></select>");
        }else if(data.auditResult==3){
            $td.html("<select style='width:110px;' id='check"+SL241106.trIndex+"'><option></option><option value='1'>同意</option><option value='2'>增加标准包装规格</option><option value='3' selected='selected'>要求执行神龙客标准</option></select>");
        }else{
            $td.html("<select style='width:110px;' id='check"+SL241106.trIndex+"'><option></option><option value='1'>同意</option><option value='2'>增加标准包装规格</option><option value='3'>要求执行神龙客标准</option></select>");
        }

        var $td = $(tr).children('td').eq(25);
        if(data.monitorResult==0){
            $td.html("<select style='width:70px;' id='checkResult"+SL241106.trIndex+"'><option></option><option value='1'>正确</option><option value='2'>不正确</option><option selected='selected' value='0'>未审核</option></select>");
        }else if(data.monitorResult==1){
            $td.html("<select style='width:70px;' id='checkResult"+SL241106.trIndex+"'><option></option><option selected='selected' value='1'>正确</option><option value='2'>不正确</option><option value='0'>未审核</option></select>");
        }else if(data.monitorResult==2){
            $td.html("<select style='width:70px;' id='checkResult"+SL241106.trIndex+"'><option></option><option value='1'>正确</option><option value='2' selected='selected'>不正确</option><option value='0'>未审核</option></select>");
        }else{
            $td.html("<select style='width:70px;' id='checkResult"+SL241106.trIndex+"'><option></option><option value='1'>正确</option><option value='2'>不正确</option><option value='0'>未审核</option></select>");
        }
        SL241106.trIndex++;
    }
}
$(document).ready(function() {
    //初始化调用
    SL241106.initDataGrid();
});