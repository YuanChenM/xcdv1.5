(function ($) {
    $.fn.extend({
        validateForm: function (options) {
            //this._initTooltip();
            this._initValidate();
            //var $validate = this._setDefaultsValidate();
            var $validate = $(this).validate(options);
            return $validate.form();
        },
        _initValidate: function () {
           this._setDefaultsValidate();
            this._initTooltip();
            this._changeDefaultErrorMessage();
        },
        _setDefaultsValidate: function () {
            return $(this).validate({
                showErrors: this._showValidateErrorMessage,
                onfocusout: false,
                onkeyup: false,
                onclick: false,
                ignore :[]
            });
        },
        _initTooltip: function () {
            $(this).tooltip({
                position: {
                    my: "left top",
                    at: "right+5 top-5",
                    collision: "none"
                }
            });
        },
        _changeDefaultErrorMessage: function(){
            $.extend($.validator.messages, {
                required: "这是必填字段",
                remote: "请修正此字段",
                email: "请输入有效的电子邮件地址",
                url: "请输入有效的网址",
                date: "请输入有效的日期",
                dateISO: "请输入有效的日期 (YYYY-MM-DD)",
                number: "请输入有效的数字",
                digits: "只能输入数字",
                creditcard: "请输入有效的信用卡号码",
                equalTo: "你的输入不相同",
                extension: "请输入有效的后缀",
                maxlength: $.validator.format("最多可以输入 {0} 个字符"),
                minlength: $.validator.format("最少要输入 {0} 个字符"),
                rangelength: $.validator.format("请输入长度在 {0} 到 {1} 之间的字符串"),
                range: $.validator.format("请输入范围在 {0} 到 {1} 之间的数值"),
                max: $.validator.format("请输入不大于 {0} 的数值"),
                min: $.validator.format("请输入不小于 {0} 的数值")
            });
        },
        _showValidateErrorMessage: function (errorMap, errorList) {//处理Error Message
            var $checkErrorTagList = $(".check_error");
            for (var i = 0; i < $checkErrorTagList.length; i++) {
                var element = $checkErrorTagList[i];
                var $elementTag = $(element);
                $elementTag.removeAttr("title").removeClass("check_error");
            }
            var errorMessages = "";
            for (var i = 0; i < errorList.length; i++) {
                var error = errorList[i];
                var element = error.element;
                var method = error.method;
                var $elementTag = $(element);
                var errorMessage = $(this)._getErrorMessage(method, $elementTag);
                if (errorMessage == undefined) {
                    errorMessage = error.message;
                }
                errorMessages =errorMessages + errorMessage +"<br/>";
                $elementTag.removeAttr("title").removeClass("check_error");
                $elementTag.attr("title", errorMessage).addClass("check_error");
            }
            if (errorMessages == "" || errorMessages == undefined) {
                return;
            }
            $.alertMessage.error("<label style ='color:red'>" + errorMessages + "</label>");
        },
        _getErrorMessage: function (method, $elementTag) {
            return $elementTag.attr(method + "Message");
        }
    });
})(jQuery);

var ValidatorEx = {
    appendFileSizeMethod: function () {
        jQuery.validator.addMethod("fileSize", function (value, element, param) {
            param = param > 0 ? param : 20;
            var file = element.files[0];
            if (file) {
                var size = element.files[0].size;
                if (size > param * 1024 * 1024) {
                    return false;
                }
                return true;
            }
            return true;
        },$.validator.format( "文件大小不能超过{0}M"))
    },
    appendFileTypeMethod: function () {
        jQuery.validator.addMethod("fileType", function (value, element, param) {
            if(element.files[0] && value){
                var trueType = value.split(".");
                var type = param.split(",");
                var rs = $.inArray(trueType[1], type);
                return rs != -1 ;
            }
            return true;
        },$.validator.format("文件类型错误,请选择正确的文件格式{0}。"))
    }

}

$(document).ready(function () {
    ValidatorEx.appendFileSizeMethod();
    ValidatorEx.appendFileTypeMethod()
});







