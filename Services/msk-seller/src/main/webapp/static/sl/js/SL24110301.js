/**
 *JS
 *@author puxigui 
 */
var SL24110301 = {
    initDataGrid:function(){
        this.loadImages();
    },
    loadImages:function(){
        /*var licPath=$("#licPathId").attr("href");
        var taxPath=$("#taxPathId").attr("href");
        var orgNoPath=$("#orgNoPathId").attr("href");
        var balPath=$("#balPathId").attr("href");
        var licTypePath=$("#licTypePathId").attr("href");*/
        var imgSrc=$("a[name='imgSrc']");
        var fdlPath=$(".fdlPathId").attr("href");
        var authPath=$(".authPathId").attr("href");
        var oemAuthPath=$(".oemAuthPathId").attr("href");
        var arrayList = ["png","jpg","jpeg","bmp","gif"];
        $(arrayList).each(function(i,val){
            $(imgSrc).each(function(j,src){
                var pathdata=$(this).attr("href");
                var srcPath=pathdata+val;
                var aTag = $(this);
                var img = new Image();
                img.onload = function() {
                    aTag.html("<img src='"+srcPath+"' title='"+srcPath+"' height='150px' width='250px'/>");
                    aTag.attr("href",srcPath);
                    aTag.attr("src",srcPath);
                    aTag.attr("title",srcPath);
                }
                img.src= srcPath;
            });
            var img = new Image();
            var fdlPathUrl=fdlPath+val;
            img.onload = function() {
                $(".fdlPathId").html("<img src='"+fdlPathUrl+"' height='150px' width='250px'/>");
                $(".fdlPathId").attr("href",srcPath);
                $(".fdlPathId").attr("src",srcPath);
            }
            img.src= fdlPathUrl;
            var authPathUrl=authPath+val;
            img.onload = function() {
                $(".authPathId").html("<img src='"+authPathUrl+"' height='150px' width='250px'/>");
                $(".authPathId").attr("href",srcPath);
                $(".authPathId").attr("src",srcPath);
            }
            img.src= authPathUrl;
            var oemAuthPathUrl=oemAuthPath+val;
            img.onload = function() {
                $(".oemAuthPathId").html("<img src='"+oemAuthPathUrl+"' height='150px' width='250px'/>");
                $(".oemAuthPathId").attr("href",srcPath);
                $(".oemAuthPathId").attr("src",srcPath);
            }
            img.src= oemAuthPathUrl;
           /* var licPathImg=licPath+val;
            var taxPathImg=taxPath+val;
            var img = new Image();
            img.onload = function() {
                alert("图片加载成功");
                $("#licPathId").html("<img src='"+path+"'/>");
            }*/
            /*img.src = licPathImg;
            img.src= taxPathImg;*/
        });
    }
}
$(document).ready(function() {
	//初始化调用
    SL24110301.initDataGrid();
});