
var SC181102 = {
    SC181102Grid: null,
    searchButtonId: "SC181102_SEARCH",
    formId: "SC181102Form",
    initDataGrid: function () {
        SC181102.bindSearchButton();
        SC181102.bindSelectChange();
        SC181102.bindPlanDialogOpen();
        SC181102.bindActualDialogOpen();
    },
    bindSearchButton: function () {
        $("#" + SC181102.searchButtonId).click(function () {
            var validator = mainValidation($("#" + SC181102.formId));
            var isValid = validator.form();
            if (isValid) {
                formData = getFormData($("#" + SC181102.formId));
                $('#main-content').postUrl($("#" + SC181102.formId).attr("action"), formData);
            }
        });
    },
    bindSelectChange:function(){
        $("#distMonth").change(function(){
            $('#main-content').postUrl(Main.contextPath + "/SC181102/selectChange/", {
                distMonth:$("#distMonth").val()
            },function(data){
                var areaSelect = $("#areaCode");
                areaSelect.html('');
                var supplierCode = $("#supplierCode");
                supplierCode.html('');
                /*areaSelect.append("<option value='-1'>--请选择--</option>");
                supplierCode.append("<option value='-1'>--请选择--</option>");*/
                if(data.areaCode != 0 || data.areaCode !=null ){
                    $.each(data,function(i,item){
                        areaSelect.append("<option value='" + item.areaCode + "'>"+ item.areaName+ "</option>");
                        if (i==0) {
                            var supplierCodeList = item.supplierInfoList;
                            for(var j=0;j<supplierCodeList.length;j++){
                                supplierCode.append("<option value='" + supplierCodeList[j].supplierCode + "'>"+ supplierCodeList[j].supplierName+ "</option>");
                            }
                        }
                    })

                }

            },{refreshHtml:false})
        });
        $("#areaCode").change(function(){
            $('#main-content').postUrl(Main.contextPath + "/SC181102/selectChange/", {
                distMonth:$("#distMonth").val(),
                areaCode:$("#areaCode").val()
            },function(data){
                var supplierCode = $("#supplierCode");
                 supplierCode.html('');

               /* supplierCode.append("<option value='-1'>--请选择--</option>");*/

                $.each(data,function(i,item){

                    supplierCode.append("<option value='" + item.supplierCode + "'>"+ item.supplierName+ "</option>");
                })
            },{refreshHtml:false})
        });
    },
    bindPlanDialogOpen:function(){
        $("input[name *= 'SC181102Plan']").bind("click", function() {
            $.pdialog.open("供应商产品计划调整申请", Main.contextPath + "/SC183101/init", {
                    width: 800,
                    height: 550
                },
                {
                    distMonth:$("#distMonth").val(),
                    areaCode:$("#areaCode").val(),
                    areaName:$("#areaCode option:selected").text(),
                    supplierCode:$("#supplierCode").val(),
                    supplierName:$("#supplierCode option:selected").text(),
                    halfCode:$("#currenthalfCode").val(),
                    entryMark:"1",
                    buttonName:this.name
                });
        });
    },
    bindActualDialogOpen:function(){
        $("input[name *= 'SC181102Actual']").bind("click", function() {
            $.pdialog.open("供应商产品实际录入", Main.contextPath + "/SC183103/init", {
                    width: 800,
                    height: 550
                },
                {
                    distMonth:$("#distMonth").val(),
                    areaCode:$("#areaCode").val(),
                    areaName:$("#areaCode option:selected").text(),
                    supplierCode:$("#supplierCode").val(),
                    supplierName:$("#supplierCode option:selected").text(),
                    entryMark:"1",
                    buttonName:this.name
                });
        });
    }
}
$(document).ready(function () {
    // 初始化调用
    SC181102.initDataGrid();
});