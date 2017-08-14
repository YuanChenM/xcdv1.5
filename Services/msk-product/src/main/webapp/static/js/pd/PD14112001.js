/**
 * 产品品种维护JS
 *
 * @author gyh
 */
var PD14112001 = {
    init: function () {
        this.changeSelect();
    },
    changeSelect: function () {
        //公斤价设置监听事件

        $("input[name='priceofkgArray']").keydown(function (evt) {
            if (evt.keyCode == 13 || evt.keyCode == 37 || evt.keyCode == 39) {
                //获取下标值
                var indexNum = $(this).attr('indexNum');
                //根据下标值得出平衡系数
                var theNumber = parseFloat($('#PD14112001Table tr:eq(' + indexNum + ') td:nth-child(3)').html()) / 100;
                //输入值/平衡系数
                var rs = parseFloat($(this).val()) / theNumber;
                var priceofkgVal1 = (parseFloat($('#PD14112001Table tr:eq(1) td:nth-child(3)').html()) / 100 * rs).toFixed(4);
                var priceofkgVal2 = (parseFloat($('#PD14112001Table tr:eq(2) td:nth-child(3)').html()) / 100 * rs).toFixed(4);
                var priceofkgVal3 = (parseFloat($('#PD14112001Table tr:eq(3) td:nth-child(3)').html()) / 100 * rs).toFixed(4);
                var priceofkgVal4 = (parseFloat($('#PD14112001Table tr:eq(4) td:nth-child(3)').html()) / 100 * rs).toFixed(4);
                var priceofkgVal5 = (parseFloat($('#PD14112001Table tr:eq(5) td:nth-child(3)').html()) / 100 * rs).toFixed(4);
                var priceofkgVal6 = (parseFloat($('#PD14112001Table tr:eq(6) td:nth-child(3)').html()) / 100 * rs).toFixed(4);
                var priceofkgVal7 = (parseFloat($('#PD14112001Table tr:eq(7) td:nth-child(3)').html()) / 100 * rs).toFixed(4);
                var priceofkgVal8 = (parseFloat($('#PD14112001Table tr:eq(8) td:nth-child(3)').html()) / 100 * rs).toFixed(4);
                var priceofkgVal9 = (parseFloat($('#PD14112001Table tr:eq(9) td:nth-child(3)').html()) / 100 * rs).toFixed(4);
                var priceofkgVal10 = (parseFloat($('#PD14112001Table tr:eq(10) td:nth-child(3)').html()) / 100 * rs).toFixed(4);
                $('#priceofkg1').val(priceofkgVal1);
                $('#priceofkg2').val(priceofkgVal2);
                $('#priceofkg3').val(priceofkgVal3);
                $('#priceofkg4').val(priceofkgVal4);
                $('#priceofkg5').val(priceofkgVal5);
                $('#priceofkg6').val(priceofkgVal6);
                $('#priceofkg7').val(priceofkgVal7);
                $('#priceofkg8').val(priceofkgVal8);
                $('#priceofkg9').val(priceofkgVal9);
                $('#priceofkg10').val(priceofkgVal10);
            }
        });


        //var priceofkg4=$('#priceofkg4');
        //priceofkg4.keyup(function(){
        //
        //priceofkg4Val=priceofkg4.val();
        //if(!priceofkg4Val){
        //	priceofkg4Val=0;
        //}
        //    var priceofkgVal4=parseFloat(priceofkg4Val);
        //    var priceofkgVal1=(parseFloat($('#PD14112001Table tr:eq(1) td:nth-child(3)').html())/100*priceofkgVal4).toFixed(4);
        //var priceofkgVal2=(parseFloat($('#PD14112001Table tr:eq(2) td:nth-child(3)').html())/100*priceofkgVal4).toFixed(4);
        //var priceofkgVal3=(parseFloat($('#PD14112001Table tr:eq(3) td:nth-child(3)').html())/100*priceofkgVal4).toFixed(4);
        //var priceofkgVal5=(parseFloat($('#PD14112001Table tr:eq(5) td:nth-child(3)').html())/100*priceofkgVal4).toFixed(4);
        //var priceofkgVal6=(parseFloat($('#PD14112001Table tr:eq(6) td:nth-child(3)').html())/100*priceofkgVal4).toFixed(4);
        //var priceofkgVal7=(parseFloat($('#PD14112001Table tr:eq(7) td:nth-child(3)').html())/100*priceofkgVal4).toFixed(4);
        //var priceofkgVal8=(parseFloat($('#PD14112001Table tr:eq(8) td:nth-child(3)').html())/100*priceofkgVal4).toFixed(4);
        //var priceofkgVal9=(parseFloat($('#PD14112001Table tr:eq(9) td:nth-child(3)').html())/100*priceofkgVal4).toFixed(4);
        //var priceofkgVal10=(parseFloat($('#PD14112001Table tr:eq(10) td:nth-child(3)').html())/100*priceofkgVal4).toFixed(4);
        //    $('#PD14112001Table tr:eq(1) td:nth-child(4)').html(priceofkgVal1);
        //    $('#PD14112001Table tr:eq(2) td:nth-child(4)').html(priceofkgVal2);
        //    $('#PD14112001Table tr:eq(3) td:nth-child(4)').html(priceofkgVal3);
        //    $('#PD14112001Table tr:eq(5) td:nth-child(4)').html(priceofkgVal5);
        //    $('#PD14112001Table tr:eq(6) td:nth-child(4)').html(priceofkgVal6);
        //    $('#PD14112001Table tr:eq(7) td:nth-child(4)').html(priceofkgVal7);
        //    $('#PD14112001Table tr:eq(8) td:nth-child(4)').html(priceofkgVal8);
        //    $('#PD14112001Table tr:eq(9) td:nth-child(4)').html(priceofkgVal9);
        //    $('#PD14112001Table tr:eq(10) td:nth-child(4)').html(priceofkgVal10);
        //    $('#priceofkg1').val(priceofkgVal1);
        //    $('#priceofkg2').val(priceofkgVal2);
        //    $('#priceofkg3').val(priceofkgVal3);
        //    $('#priceofkg5').val(priceofkgVal5);
        //    $('#priceofkg6').val(priceofkgVal6);
        //    $('#priceofkg7').val(priceofkgVal7);
        //    $('#priceofkg8').val(priceofkgVal8);
        //    $('#priceofkg9').val(priceofkgVal9);
        //    $('#priceofkg10').val(priceofkgVal10);
        //});
    }
};
$(document).ready(function () {
    // 初始化调用
    PD14112001.init();
});