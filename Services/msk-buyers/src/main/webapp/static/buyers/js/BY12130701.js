/**
 * 二级产品信息
 * Created by marshall on 16/6/24.
 */
var BY12130701 = {
    BY12130701Grid: null,
    saveButtonId:"BY12130701_SAVE",
    formId:"BY12130701Form",
    initDataGrid: function () {
        this.bindSaveButton();
    },
    bindSaveButton:function(){
        $("#" + BY12130701.saveButtonId).click(function(){

            var flag = false;

            var isAllChecked = true;

            var pdMachiningCode = "";

            $("input[type='checkbox']").each(function(index,element){
                if($(element).attr("name") == "pdMachining") {
                    if ($(element)[0].checked) {

                        pdMachiningCode = pdMachiningCode.concat($(this).val());
                        flag= true;
                    }else{
                        isAllChecked = false;
                    }
                }

            })

            if(flag) {
                var classCode = $("#classesCode").val();

                $("#buyerPdMac"+classCode).val(pdMachiningCode);

                $.pdialog.close();

                var pd = document.getElementById("buyerPdCla"+classCode);

                //判断是不是用勾选checkbox触发事件
                if(!isAllChecked){
                    pd.checked = true;
                    pd.indeterminate= true;
                }else{

                    pd.checked = true;
                    pd.indeterminate= false;
                }
            }else{
                $.alertMessage.confirm("请选择产品信息", function () {
                    $.alertMessage.close();
                });
                return false;
            }
        });
    }
}
$(document).ready(function () {
    // 初始化调用
    BY12130701.initDataGrid();
});