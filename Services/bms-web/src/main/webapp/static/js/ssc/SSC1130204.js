/**
 * Created by ding_guangjian on 2016/8/3.
 */

var SSC1130204 = {
    contractSelect: $("#bid_select"),
    contractData: null,
    init: function(){
       //监听input框
       // 调用SSC11314的查询接口
        $('#main-content').postUrl(Main.contextPath + '/SSC11302/findNoRelatedBidBase',null,function(data){
            if(data.bidFlag=="F"){
                <!--Modif for Bug#2601 at 2016/09/09 by peng_hao Start-->
                $.alertMessage.info("当前无中标成交确认书可关联");
                <!--Modif for Bug#2601 at 2016/09/09 by peng_hao -->
            }else{
                //使用jQueryUi的Autocomplete插件生成类似于百度搜索框的自动匹配模糊查询
                $("#tags").autocomplete({
                    source: data.bidList
                });
            }
        },{refreshHtml:false});
        this.bindBidButton();
    },
    bindBidButton:function(){
        $("#SSC1130204_CONFIRM").click(function() {
            var contractId = $("#contractId").val();
            var bidProjectNo = $("#tags").val().split("(")[0].trim();
            if(bidProjectNo==""){
                $.alertMessage.info("请输入一条数据!");
                return;
            }

            var bidProjectName = "";
            if($("#tags").val().split("(")[1]!=null&&$("#tags").val().split("(")[1]!=undefined&&$("#tags").val().split("(")[1]!=""){
                bidProjectName=$("#tags").val().split("(")[1].split(")")[0].trim();
            }
            $('#main-content').postUrl(Main.contextPath + '/SSC11302/checkBidBaseExist', {
                bidProjectNo: bidProjectNo
                }, function (data) {
                    if(data.status=="S"){
                        $.pdialog.close();
                        if(contractId==""){
                            $("#bidProjectNoHref").text(bidProjectNo);
                            $("#bidProjectNo").val(bidProjectNo);
                        }else{
                            //更新合同基础信息
                            $("#main-content").postUrl(Main.contextPath + "/SSC11304/saveOrUpdateContractBase", {
                                bidId:data.result.bidId,
                                bidProjectNo:bidProjectNo,
                                contractId:contractId
                            },function(data2){
                                $.alertMessage.info("操作成功!");
                                $("#headBreadCrumb").hide();
                            });
                        }
                    }else{
                        $.alertMessage.info("该中标成交确认书已有关联的合同，请重新选择！");
                    }
            },{refreshHtml:false});
        });
    }

}
$(document).ready(function(){
    SSC1130204.init();
})