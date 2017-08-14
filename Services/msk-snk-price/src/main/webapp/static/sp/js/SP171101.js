/**
 *需求发布
 */
var $List_Grid;
var SP171101 = {
    searchButtonId: "SP171101_SEARCH",
    formId: "SP171101Form",
    demandStartDate:"#demandStartDate",
    demandStartDate:"#demandStartDate",
    demandEndDate:"#demandEndDate",
    DemandDate:"#DemandDate",
    init: function () {
        $List_Grid = $('#SP171101_list_grid').grid({
            actionHandler: SP171101.actionHandler
            /*fnDrawCallback :SO151401.fnDrawCallback*/
        });
        this.searchData();
        this.pubulishDatechange();
    },
    searchData: function () {
        $("#" + SP171101.searchButtonId).click(function () {
            FormUtils.setFormValue(SP171101.formId, "SP171101");
            $List_Grid.fnDraw();
        });
    },

    /*发布周期显示和刷新表格*/
    pubulishDatechange:function(){
        $("#cycle").change(function () {
            var limitWriteDate = $("#cycle").find("option:selected").attr("limitWriteDate");
            $("#publicDate").val(limitWriteDate);
           /* $('#main-content').postUrl(Main.contextPath + "/SP171101/init/");*/
            FormUtils.setFormValue(SP171101.formId, "SP171101");
            $List_Grid.fnDraw();
        });
    },

    /*跳转到详细页面*/
    actionHandler:function(rowdata,coltype,row,col){
        if(coltype=="edit"){
            /** Modfiy:  一览页面到详情页面打开方式横展开   2016/10/11   BY  zhukai1  Start */
            var publicDate = $("#publicDate").val();
            var arr = publicDate.split('-');
            var demandStartDate = arr[0].trim();
            var demandEndDate = arr[1].trim();
            var publishTotalNum = rowdata.publishTotalNum;
            if(publishTotalNum == "未发布"){
                publishTotalNum = 0;
            }
            var data = new Object();
                data['publishId']= rowdata.publishId,
                data['publishYm']=cycle.value,
                data['demandStartDate'] = demandStartDate,
                data['demandEndDate']= demandEndDate,
                data['lgcsCode'] = rowdata.lgcsCode,
                data['lgcsName'] = rowdata.lgcsName,
                data['pdTypeCode'] = rowdata.pdTypeCode,
                data['pdName'] = rowdata.pdName,
                data['scientificName'] = rowdata.scientificName,
                data['localName'] = rowdata.localName,
                data['salesName'] =  rowdata.salesName,
                data['classesCode'] = rowdata.classesCode,
                data['classes'] = rowdata.classes,
                data['machiningCode'] = rowdata.machiningCode,
                data['machining'] = rowdata.machining,
                data['breedCode'] =  rowdata.breedCode,
                data['breed']  =  rowdata.breed,
                data['featureCode'] = rowdata.featureCode,
                data['feature'] = rowdata.feature,
                data['weightCode'] = rowdata.weightCode,
                data['weight'] = rowdata.weight,
                data['publishTotalNum'] = publishTotalNum
            Main.detailWindow(Main.contextPath + "/SP171102/init/", data, "供应商需求发布详细");
            /** Modfiy:  一览页面到详情页面打开方式横展开   2016/10/11   BY  zhukai1  end */
        }
    }

}
$(document).ready(function () {
    // 初始化调用
    SP171101.init();

});