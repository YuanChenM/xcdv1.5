/**
 *卖家批次产品目录报表JS
 *@author GYH
 */
var PD142104 = {
    PD142104Grid: null,
    initDataGrid: function () {
        this.PD142104Grid = $('#PD142104_grid').grid();
        this.searchData();
    },
    searchData: function () {
        $("a[class='oneClassCode']").click(function () {
            var classesCode=$(this).attr("value");
            $('#classesCode').val(classesCode);
            $('#labelVal').html("卖家批次产品目录报表——"+$(this).attr("labelValue"));
            $('#main-content').postUrl(Main.contextPath + "/PD142104/findPdMachining", {'filterMap[classesCode]':classesCode}, function (data) {
                var pdMachining=$('#machiningInfo');
                pdMachining.html('');
                pdMachining.append("<option value=''>请选择</option>");
                $.each(data, function (i, item) {
                    pdMachining.append("<option value='" + item.machiningCode + "'>" + item.machiningName + "</option>");
                });
            }, {refreshHtml: false});
            PD142104.PD142104Grid.fnDraw();
        });
        $("#PD142104_SEARCH").click(function () {
            PD142104.PD142104Grid.fnDraw();
        });
        $('#PD142104_grid').find('thead.filterHeader').on('keydown', 'input,select',function(evt){
            if(evt.keyCode == 13 || evt.keyCode == 37 || evt.keyCode==39)
            {
                $('.hasDatepicker').datepicker('hide');
                switch(evt.keyCode){
                    case 13:    // Enter
                        if($(this).attr("type") == "text"){
                            PD142104.PD142104Grid.fnDraw();
                        }
                        break;
                }
            }
        });
    }

}
$(document).ready(function () {
    //初始化调用
    PD142104.initDataGrid();
});