var Index={
    bindBtnMS:function(){
        $("#btnMS").bind("touchstart",function(){
            console.info("touchstart")
            var url = "http://10.20.16.152:8888/msk-config-server/api/load/config/constant/list";
            var data = {
                param: {
                    environment:"development",
                    key:"",
                    modelName:"PD",
                    type:"ConfigConstant"
                }
            };
            HttpClient.post(url,data,function(data){
                alert(data.returnCode);
            },function(data){
                alert("失败")
            });
        });
    }
}
jQuery(document).ready(function() {
    Index.bindBtnMS();
});