
var SC182204 = {
    SC182204Grid: null,
    saveButtonId: "SC182204_SAVE",
    searchButtonId: "SC182204_SEARCH",
    formId: "SC182204Form",
    //manuDate:"#manuDate",
    initDataGrid: function () {
      /*if(document.getElementById('manuDate')){
            manuDate.SC182204Grid = $('#SC182204_grid').grid({
                actionHandler:SC182204.actionHandler
            });
            this.bindDatePicber(SC182204.manuDate);
        }*/

        SC182204.bindSaveButton();
        SC182204.bindSelectChange();
        SC182204.bindSearchButton();
    },

    bindDatePicber: function(manuDate){
        $(manuDate).datepicker({
            showButtonPanel: true,
            dateFormat:'yy-mm-dd',
            changeMonth: true,
            changeYear: true
        });
    },
    bindSearchButton: function () {
        $("#" + SC182204.searchButtonId).click(function () {
            var validator = mainValidation($("#" + SC182204.formId));
            var isValid = validator.form();
            if (isValid) {
                formData = getFormData($("#" + SC182204.formId));
                $('#main-content').postUrl(Main.contextPath + "/SC182204/init/",{
                    lgcsCode:$("#lgcsCode").val(),
                    suppCode:$("#suppCode").val()
                });
            }
        });
    },
    bindSaveButton: function () {
        $("#" + SC182204.saveButtonId).click(function () {
            var checked = false;
            for(i=0; i<document.getElementById("SC182204Form").elements.length; i++){
                if(document.getElementById("SC182204Form").elements[i].type=='checkbox'&&document.getElementById("SC182204Form").elements[i].checked){
                    checked = true;
                    document.getElementById("SC182204Form").elements[i].value = "1";
                }
            }
            if(checked==true){
                var validator = mainValidation($("#" + SC182204.formId));
                var isValid = validator.form();
                if (isValid) {
                    formData = getFormData($("#" + SC182204.formId));
                    $('#main-content').postUrl($("#" + SC182204.formId).attr("action"), formData, function(data){
                        $('#main-content').postUrl(Main.contextPath + "/SC182203/init/");
                    });
                }
            }else{
                alert("请选择需要生成的产品批次明细！");
            }

        });
    },
    bindSelectChange:function(){
        $("#lgcsCode").change(function(){
            $('#main-content').postUrl(Main.contextPath + "/SC182204/selectChange/", {
                lgcsCode:$("#lgcsCode").val()
            },function(data){
                var suppCode = $("#suppCode");
                suppCode.html('');

                /* supplierCode.append("<option value='-1'>--请选择--</option>");*/

                $.each(data,function(i,item){

                    suppCode.append("<option value='" + item.suppCode + "'>"+ item.suppName+ "</option>");
                });
                $('#main-content').postUrl(Main.contextPath + "/SC182204/init/", {
                    lgcsCode:$("#lgcsCode").val(),
                    suppCode:$("#suppCode").val()
                },{refreshHtml:false})
            },{refreshHtml:false})
        });
    }
}
$(document).ready(function () {
    // 初始化调用
    SC182204.initDataGrid();
});