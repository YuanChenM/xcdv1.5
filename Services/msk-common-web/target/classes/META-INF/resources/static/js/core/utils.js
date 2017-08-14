/**
 * Utils JS
 */
var MainUtils = {
    /**
     * 计算时间只差,返回天
     * @param startDateString 开始时间
     * @param endDateString 结束时间
     * @returns 天数
     */
    calculateDate : function(startDateString,endDateString){
        if(endDateString==""||endDateString==undefined){
            endDateString = this.getCurrentDate();
        }
        var startDateSplit = startDateString.split("-");
        var endDateSplit = endDateString.split("-");
        //时间格式mm-dd-yyyy
        var startDate = new  Date(startDateSplit[1]  +  '-'  +  startDateSplit[2]  +  '-'  +  startDateSplit[0]);
        var endDate = new  Date(endDateSplit[1]  +  '-'  +  endDateSplit[2]  +  '-'  +  endDateSplit[0]);
        var calculateDateDays  =  parseInt(Math.abs(endDate  -  startDate)  /  1000  /  60  /  60  /24)
        return calculateDateDays;
    },
    /**
     * 获得当期时间
     * @returns 当前时间，时间格式为yyyy-mm-dd
     */
    getCurrentDate: function () {
        var currentDate = new Date();
        var year = currentDate.getFullYear();
        //JS 月份从0开始的，所有月份必须+1
        var month = currentDate.getMonth()+1;
        var day = currentDate.getDay();
        return year + "-" + (month<10?("0"+month):month) + "-" + day;
    },
    /**
     * 时间格式验证(yyyy-mm-dd)
     * @param date 时间
     * @returns true格式正确,false格式不正确
     */
    dateFormatValidation : function(date){
        //时间格式的正则表达式
        var dateRegular = /^(\d{4})-(\d{2})-(\d{2})$/
        if (!dateRegular.test(date)){
            return false
        }
        return true;
    },

    /**
     * js加载页面图片
     * @path 路径
     * @属性值
     */
    loadImages:function(path,name){
        var arrayList = ["png","jpg","jpeg","bmp","gif"];
        $(arrayList).each(function(i,val){
            var img = new Image();
            var pathUrl=path+val;
            img.onload = function() {
                name.html("<img src='"+pathUrl+"' height='150px' width='250px'/>");
                name.attr("href",pathUrl);
                name.attr("src",pathUrl);
            }
            img.src= pathUrl;
        });
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
