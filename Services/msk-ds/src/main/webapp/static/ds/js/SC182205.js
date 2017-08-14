
var SC182205 = {
    SC182205Grid: null,
    searchButtonId: "SC182205_SAVE",
    formId: "SC182205Form",
    initDataGrid: function () {

        SC182205.bindSearchButton();

    },
    bindSearchButton: function () {

        $("#" + SC182205.searchButtonId).click(function () {
            var reg = new RegExp("^[1-9][0-9]*(-|,)[1-9][0-9]*$");
            if(document.getElementById("productPrintNum").value==''||!reg.test(document.getElementById("productPrintNum").value)
                ||document.getElementById("productPrintNum").value.length>50){
                $.alertMessage.info("打印页码不能为空,且必须以','或'-'形式出现：如1,3或1-20,且长度不能超过50");
                document.getElementById("productPrintNum").value="";
                return false;
            }
            else{
                        $.alertMessage.confirm("确定要打印么？",function(){
                        var breedName = document.getElementById("breedName").value;
                        var gradeName = document.getElementById("gradeName").value;
                        var proLotNum = document.getElementById("proLotNum").value;
                        var readProLotNum = document.getElementById("readProLotNum").value;
                        var productPrintNum = document.getElementById("productPrintNum").value;
                        //var dateString = new Date().format('yyyyMMddhhmmss').substring(0,12);
                        var dateString = new Date().format('yyyyMMddhhmmss');
                        var validator = mainValidation($("#" + SC182205.formId));
                        var isValid = validator.form();
                        var pams = [];
                        pams.push($('<input>',{name:'breedName',value:breedName}));
                        pams.push($('<input>',{name:'gradeName',value:gradeName}));
                        pams.push($('<input>',{name:'proLotNum',value:proLotNum}));
                        pams.push($('<input>',{name:'readProLotNum',value:readProLotNum}));
                        pams.push($('<input>',{name:'productPrintNum',value:productPrintNum}));
                        var dform = $("<form>");   //定义一个form表单
                        dform.attr('style', 'display:none');   //在form表单中添加查询参数
                        dform.attr('target', '');
                        dform.attr('method', 'post');
                        dform.attr('action', Main.contextPath +"/SC182205/dataExport");
                        $('body').append(dform);
                        //将表单放置在web中
                        dform.append(pams).submit();
                        if (isValid) {
                            formData = getFormData($("#" + SC182205.formId));

                            $('#main-content').postUrl($("#" + SC182205.formId).attr("action"), formData, function (data) {
                            });

                        }
                            $.alertMessage.close();
                    });
                }




        });
    }
}
$(document).ready(function () {
    // 初始化调用
    SC182205.initDataGrid();
});