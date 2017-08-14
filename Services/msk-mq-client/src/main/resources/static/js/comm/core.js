/**
 * Created by jackjiang on 16/7/1.
 */
function loadUrl(url,param){
    $.ajax({
        url:encodeURI(url),
        data : param,
        cache : false,
        success : function(response){
            $("#mainContext").html(response);
            var run = function(){
                var vTour;
                var hash = window.location.hash.substring(1);
                if(hash == "vtour"){
                    $('html').find('[pageVTourUrl]').each(function(index){
                        var tourUrl = $(this).attr("pageVTourUrl");
                        vTour = new VTour(tourUrl == "" || tourUrl == null ? undefined : tourUrl);
                        vTour.tour();
                        return false;
                    });
                }else if(hash == "vguide"){
                    $('html').find('[pageVGuideUrl]').each(function(index){
                        var tourUrl = $(this).attr("pageVGuideUrl");
                        var vTour = new VTour(tourUrl == "" || tourUrl == null ? undefined : tourUrl);
                        vTour.tourGuide();
                        return false;
                    });
                }
            };

            $(window).load(function(){
                if(window.location.hash) {
                    run();
                }
            });
            $(window).bind('hashchange', function() {
                run();
            });
        }
    });
}

function initUI(){
    $("a[target=ajax]").each(function() {
        $(this).click(function(event) {
            var $this = $(this);
            var rel = $this.attr("rel");
            var paramStr = $this.attr("param");
            var param = {};
            if(paramStr != undefined){
                param=eval("("+paramStr+")");//转换为json对象
            }
            loadUrl($this.attr("href"),param);
            event.preventDefault();
        });
    });
}
$(function() {
    initUI();
});

