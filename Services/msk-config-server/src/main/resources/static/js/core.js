function loadUrl(url,param,func){
    $.ajax({
        url:encodeURI(url),
        data : param,
        cache : false,
        success : function(response){
            $("#mainContext").html(response);
            if(func != null && func != ''){
                eval(func);
            }
        }
    });
}

function initUI(){
    $("a[target=ajax]").each(function() {
        $(this).click(function(event) {
            var $this = $(this);
            var paramStr = $this.attr("param");
            var funcStr = $this.attr("func");
            var param = {};
            if(paramStr != undefined){
                param=eval("("+paramStr+")");//转换为json对象
            }
            if(funcStr != null && funcStr != ''){
                loadUrl($this.attr("href"),param, funcStr);
            }else{
                loadUrl($this.attr("href"),param);
            }
            event.preventDefault();
        });
    });
    App.init(); // initlayout and core plugins
    Index.init();
    //Index.initCharts(); // init index page's custom scripts
    //Index.initChat();
    //Index.initMiniCharts();
    //Index.initDashboardDaterange();
    //Index.initIntro();

}
$(function() {
    initUI();
});

