
/**
 * Created by guan_zhongheng on 2016/8/11.
 */

var BY12140301 = {
    BY12140301_Grid : null,
    initDataGrid : function () {
        BY12140301.BY12140301_Grid = $("#BY12140301_Grid").grid({
        });
        BY12140301.bindConfirmButton();
    },
    bindConfirmButton : function(){
        $('#BY12140301_CONFIRM').click(function(){
            var selectProductList = BY12140301.BY12140301_Grid.getChoiceData();
            if(selectProductList.length > 1){
                $.alertMessage.info("只能选择一家批发市场");
                return false;
            }else{
                $('#marketName').val(selectProductList[0].marketName);
                $('#marketAddr').val(selectProductList[0].marketAddr);
                $('#lgcsAreaCode').val(selectProductList[0].lgcsAreaCode);
                $('#cityCode').val(selectProductList[0].cityCode);
                $('#lgcsAreaName').val(selectProductList[0].lgcsAreaName);
                $('#cityName').val(selectProductList[0].cityName);
                $('#marketId').val(selectProductList[0].terMarketId);
                $.pdialog.close();
            }
        });
    }
}
$(document).ready(function(){
    BY12140301.initDataGrid();
});
document.onkeydown = function(e){
    var e = e || window.event;
    if(e.keyCode == 13) {
        return false;
    }
};
