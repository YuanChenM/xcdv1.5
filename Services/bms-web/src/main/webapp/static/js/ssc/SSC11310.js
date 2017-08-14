/**
 * 生产商入库单详细JS
 *
 * @author liu_yan2 2016-07-04
 */
var $List_Grid;
var SSC11310 = {
    formId:"SSC11310Form",
    init: function () {
        $List_Grid = $('#SSC11310_list_grid').grid({
            actionHandler: SSC11310.actionHandler,
            editCellOnBlurHandler: SSC11310.editCellOnBlurHandler
        });
    },
    actionHandler: function (rowdata, coltype, row, col) {
        if (coltype == "save") {
            $.alertMessage.confirm("你确定要修改这条数据吗？", function() {
                $.alertMessage.close();
                $('#main-content').postUrl(Main.contextPath + "/SSC11310/modify", {
                    "id": rowdata.id,
                    "receiveWeight": rowdata.receiveWeight,
                    "receiveBox": rowdata.receiveBox
                });
                /*},function(data){
                    if(data == 1) {
                        $.alertMessage.info("操作成功!");
                        $List_Grid.fnDraw();
                    }
                },{refreshHtml:false});*/
            });
        }
    },
    /**
     * 修改receiveBox时联动修改receiveWeight
     * liu_yan2
     * @param $comp
     */
    editCellOnBlurHandler: function($comp) {
        //判断是否修改的是receiveBox
        var colIndexReceiveBox = $comp.parents('tr').children().index($comp.parent());
        var colNameReceiveBox = $List_Grid.children('thead').find('th[coltype]').eq(colIndexReceiveBox).attr('name');
        if(colNameReceiveBox != 'receiveBox') {
            return;
        }
        if (isNaN($comp.val())){
            $.alertMessage.error("不是数字");
            $List_Grid.fnDraw();
            return;
        }
        var $td = $comp.parent().next();
        var $tr = $td.parent();
        var $trs = $tr.parent();
        var rowIndex = $trs.children().index($tr);
        var rowData = $List_Grid.fnGetData(rowIndex);
        var weightName = rowData.weightName.toLocaleLowerCase().substr(0, aa.indexOf('k'));
        var receiveWeight = $comp.val()* parseFloat(weightName);
        //修改行数据
        rowData['receiveWeight'] = receiveWeight.toFixed(2);
    }
}
/**
 * 更新发货确认产品信息
 * @param data
 */
function goToContractDetail(contractCode)
{
    $('#main-content').postUrl(Main.contextPath + "/SSC11304/init", {
        contractCode : contractCode
    });
}

$(document).ready(function () {
    // 初始化调用
    SSC11310.init();
});

