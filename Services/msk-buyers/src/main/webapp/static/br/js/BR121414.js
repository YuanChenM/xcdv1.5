/**
 * Created by tao_zhifa on 2016/9/28.
 */

var $List_Grid;

var BR121414 = {
    editButton : "BR121414_EDIT",
    editNameButton : "BR121414_EDITNAME",
    clickButton: "#product",
    init:function(){

        this.binEditButton();
        this.bindClickHref();
    },
    binEditButton :function(){
        $("#" + BR121414.editButton).click(function(){
            var formData = getFormData($("#BR121414Form"));
            if($("input[type='checkbox']").is(":checked")==false){
                $.alertMessage.info("产品分类不能为空,请选择!");
                return false;
            }
            $('#main-content').postUrl(Main.contextPath + "/BR121414/save",formData,function(data){
                if(data == 0){
                    $.alertMessage.info("保存失败");
                    return false;
                }else{
                    $.alertMessage.info("保存成功");

                    $('#main-content').postUrl(Main.contextPath + "/BR121414/init",formData,function(data){
                    })
                }

            },{refreshHtml: false});
        })

    },
    bindClickHref:function(){

        $("[id='product']").click(function () {
            var produceName = this.name;
            $.pdialog.open("修改名称", Main.contextPath + "/BR121414/updateName", {
                width : 500,
                height : 300
            },{
                "produceName":produceName
            });

        });

    }


};

$(document).ready(function(){
    BR121414.init();
    checkMacCheckBoxs();
    reSizeInput();

});

function checkMacCheckBoxs() {
    $("input[name='buyerPdMac']").click(function () {
        var bylClassCode = $(this).attr("bylClassCode");
        $("input:checkbox[name ='buyerPdMac']:checked").each(function () {
            if( $(this).attr("bylClassCode") != bylClassCode){
                $('#BR121414Table').find('input[type=checkbox]').attr("checked", false);
                //$(this).attr("checked",true)
            }
        });
    })
}

function reSizeInput(){
    $("input[type='text']").each(function(){
        var text = $(this).val().length;
        text = parseInt(text) * 2;
        $(this).attr('size',text);
    });
}



