/**
 * 买家详细信息
 * Created by marshall on 16/3/9.
 */
var BY121304 = {
    BY121304Grid: null,
    addButton:"BY121304_ADD",
    buyerId:null,
    initDataGrid: function () {
        BY121304.buyerId = $("#buyerId").val();
        //$("#baseBuyerBasicInfo").postUrl(Main.contextPath+"/by/baseBuyerBasicInfo/init/"+BY121304.buyerId);
        //$("#baseBuyerLicInfo").postUrl(Main.contextPath+"/by/baseBuyerLicInfo/init/"+BY121304.buyerId);
        /*$("#baseBuyerRecInfo").postUrl(Main.contextPath+"/by/baseBuyerRecInfo/init/"+BY121304.buyerId);*/
        BY121304.BY121304Grid = $('#BY121304_Grid').grid({
            actionHandler: BY121304.actionHandler
        });
        this.bindAddButton();
    },
    actionHandler: function (rowdata, coltype, row, col) {
        if (coltype == "edit") {
            var editType = "BY121304Modify";
            $.pdialog.open("买家雇员信息编辑画面", Main.contextPath + "/BY12130401/init/" + editType, {
                    width: 450,
                    height: 500
                },
                {"id":rowdata.id,"buyerId":BY121304.buyerId}
            )
        }
        if(coltype == "delete"){
            $.alertMessage.confirm("你确定要删除该雇员信息吗？", function () {
                $.alertMessage.close();
                $('#main-content').postUrl(Main.contextPath + "/BY121304/delete", {"id":rowdata.id,"buyerId":BY121304.buyerId},function () {
                    BY121304.BY121304Grid.fnDraw();
                }, {refreshHtml: false});
            });
        }
    },
    bindAddButton :function(){
        $("#" + BY121304.addButton).click(function(){
            var editType = "BY121304Add";
            $.pdialog.open("买家雇员信息新增画面", Main.contextPath + "/BY12130401/init/" + editType, {
                    width: 450,
                    height: 500
                },
                {"buyerId":BY121304.buyerId}
            )
        });
    }
}
$(document).ready(function () {
    // 初始化调用
    BY121304.initDataGrid();
});