/**
 * Created by yang_chunyan on 2016/3/29.
 */

var $List_Grid;
var SP171108 = {
    formId:"SP171108Form",
    searchButtonId:"SP171108_SEARCH",
    init : function() {
        $List_Grid = $('#SP171108_list_grid').grid({
            //actionHandler:SP171108.actionHandler
        });
        FormUtils.init(SP171108.formId,"SP171108");
        this.bindSearchButtonId();
    },

    bindSearchButtonId : function(){
        $("#"+SP171108.searchButtonId).click(function(){
            FormUtils.setFormValue(SP171108.formId, "SP171108");
            $List_Grid.fnDraw()
        });
    }
}
$(document).ready(function() {
    // 初始化调用
    SP171108.init();

});
