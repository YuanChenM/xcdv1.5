

var MainUtils = {

    init:function(){
        var insideOfFigure = $("a[name='insideOfFigure']");
        MainUtils.loadImageManys(insideOfFigure);
        var inTrayFigure = $("a[name='inTrayFigure']");
        MainUtils.loadImageManys(inTrayFigure);
        var outsideBoxFigure =  $("a[name='outsideBoxFigure']");
        MainUtils.loadImageManys(outsideBoxFigure);
        var cartonAppearanceFigure =  $("a[name='cartonAppearanceFigure']");
        MainUtils.loadImageManys(cartonAppearanceFigure);
    },
    /**
     * js加载同一属性页面图片
     * @path 路径
     * @属性值
     */
    loadImageManys:function(name){
        var arrayList = ["png","jpg","jpeg","bmp","gif"];
        $(arrayList).each(function(i,val){
            $(name).each(function(j,src){
                var pathdata=$(this).attr("href");
                var img = new Image();
                var pathUrl=pathdata+val;
                var aTag = $(this);
                img.onload = function() {
                    aTag.html("<img src='"+pathUrl+"' height='140px' width='200px'/>");
                    aTag.attr("href",pathUrl);
                    aTag.attr("src",pathUrl);
                }
                img.src= pathUrl;
            });
        });
    }
}

$(document).ready(function () {
  MainUtils.init();
});