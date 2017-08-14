/**
 * SSC系统JS共通代码
 */

var SSCCommon = {
    NATURAL_NUMBER: /^\d+$/,                //自然数：零和正整数
    POSITIVE_INTEGER: /^[0-9]*[1-9][0-9]*$/,  //正整数,不能为纯0(0000000),可以是000001

    INT11: 99999999,                        //int(11)
    MONEY_REG: /^\d{1,15}(\.\d{1,2})?$/,    //金额位数校验 decimal(20, 2)，0~999999999999999999.99
    MILEAGE_REG: /^\d{1,8}(\.\d{1,2})?$/,   //公里数位数校验 decimal(10, 2)
    WEIGHT_REG: /^\d{1,12}(\.\d{1,4})?$/,   //重量位数校验 decimal(20, 4)，0~9999999999999999.9999
    PRODUCE_WEIGHT_REG: /^\d{1,9}(\.\d{1,2})?$/, //生产期计划日生产量
    FORMAT_MONEY_REG:/^(\d{1,3}(,\d\d\d)*(\.\d+)?|\d+(\.\d+)?)$/, //千分位格式金额 校验
    /*
     匹配格式：
     11位手机号码
     3-4位区号，7-8位直播号码，1－4位分机号
     如：12345678901、1234-12345678-1234
     */
    PHONE_REG:/^((\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$)/,
    FAX_REG: /^[+]{0,1}(\d){1,3}[ ]?([-]?((\d)|[ ]){1,12})+$/,  //传真号，匹配的字符串如：+123 -999 999，+123-999 999，123 999 999，+123 999999等
    EMAIL_REG: /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/,
    QQ_REQ: /^\d{5,100}$/,

    /**
     * 判断比例非空，且在0~100范围内的数字
     */
    isPercent: function(p) {
        return /^(100|(([1-9]\d|\d)(\.\d{1,2})?))$/.test(p);
    },

    /**
     * 判断公里数非空，格式是否正确，且大于0
     */
    isMileage: function(m) {
        if (this.MILEAGE_REG.test(m) && this.fgt(m, 0)) {
            return true;
        }
        return false;
    },

    /**
     * 判断金钱非空，格式是否正确，且大于0
     */
    isMoney: function(m) {
        if (this.MONEY_REG.test(m) && this.fgt(m, 0)) {
            return true;
        }
        return false;
    },

    /**
     * 判断金钱格式是否正确，且大于或等于0
     */
    isMoneyGe0: function(m) {
        if (this.MONEY_REG.test(m) && (this.feq0(m) || this.fgt(m, 0))) {
            return true;
        }
        return false;
    },

    /**
     * 判断重量非空，格式是否正确，且大于0
     */
    isWeight: function(w) {
        if (this.WEIGHT_REG.test(w) && this.fgt(w, 0)) {
            return true;
        }
        return false;
    },

    /**
     * 判断箱数非空，是正整数，且小于或等于99999999
     */
    isBox: function(b) {
        if (SSCCommon.POSITIVE_INTEGER.test(b) && b <= SSCCommon.INT11) {
            return true;
        }
        return false;
    },

    /**
     * 判断箱数非空，是自然数，且小于或等于99999999
     */
    isBoxGe0: function(b) {
        if (SSCCommon.NATURAL_NUMBER.test(b) && b <= SSCCommon.INT11) {
            return true;
        }
        return false;
    },

    /**
     * 浮点数比较大小，f1 > f2
     */
    fgt: function(f1, f2) {
        if (parseFloat(f1) - parseFloat(f2) > 0.000001) {
            return true;
        }
        return false;
    },

    /**
     * 判断等于0
     */
    feq0: function(f) {
        f = parseFloat(f);
        if (f >= -0.000001 && f <= 0.000001) {
            return true;
        }
        return false;
    },

    /**
     * 加法精确计算
     */
    add: function(arg1, arg2) {
        var r1, r2, m;
        try {
            r1 = arg1.toString().split(".")[1].length;
        } catch (e) {
            r1 = 0
        }
        try {
            r2 = arg2.toString().split(".")[1].length;
        } catch (e) {
            r2 = 0
        }
        m = Math.pow(10, Math.max(r1, r2));
        return (arg1 * m + arg2 * m) / m;
    },

    /**
     * 减法精确计算
     */
    subtract: function(arg1, arg2) {
        var r1, r2, m, n;
        try {
            r1 = arg1.toString().split(".")[1].length
        } catch (e) {
            r1 = 0
        }
        try {
            r2 = arg2.toString().split(".")[1].length
        } catch (e) {
            r2 = 0
        }
        m = Math.pow(10, Math.max(r1, r2));
        n = (r1 >= r2) ? r1 : r2;   //动态控制精度长度
        return ((arg1 * m - arg2 * m) / m).toFixed(n);
    },

    /**
     * 乘法精确计算
     */
    multiply: function(arg1, arg2) {
        var m = 0, s1 = arg1.toString(), s2 = arg2.toString();
        try {
            m += s1.split(".")[1].length;
        } catch (e) {
        }
        try {
            m += s2.split(".")[1].length;
        } catch (e) {
        }
        return Number(s1.replace(".", "")) * Number(s2.replace(".", "")) / Math.pow(10, m);
    },

    /**
     * 除法精确计算
     */
    divide: function(arg1, arg2) {
        var t1 = 0, t2 = 0, r1, r2;
        try {
            t1 = arg1.toString().split(".")[1].length;
        } catch (e) {
        }
        try {
            t2 = arg2.toString().split(".")[1].length;
        } catch (e) {
        }
        r1 = Number(arg1.toString().replace(".", ""));
        r2 = Number(arg2.toString().replace(".", ""));
        return this.multiply((r1 / r2), Math.pow(10, t2 - t1));
    },

    /**
     * 精确四舍五入
     */
    roundFixed: function(num, fixed) {
        var pos = num.toString().indexOf('.');
        var decimal_places = num.toString().length - pos - 1;
        var _int = num * Math.pow(10, decimal_places);
        var divisor_1 = Math.pow(10, decimal_places - fixed);
        var divisor_2 = Math.pow(10, fixed);
        return Math.round(_int / divisor_1) / divisor_2;
    },

    /**
     * 格式化金额，每三位加逗号
     */
    formatMoney: function(num) {
        num = num.toString().replace(/\$|\,/g, '');
        if (isNaN(num)) {
            num = "0";
        }
        var sign = (num == (num = Math.abs(num)));
        num = Math.floor(num * 100 + 0.50000000001);
        var cents = num % 100;
        num = Math.floor(num / 100).toString();
        if (cents < 10) {
            cents = "0" + cents;
        }
        for (var i = 0; i < Math.floor((num.length - (1 + i)) / 3); i++) {
            num = num.substring(0, num.length - (4 * i + 3)) + ',' + num.substring(num.length - (4 * i + 3));
        }
        return (((sign) ? '' : '-') + num + '.' + cents);
    },

    /**
     * 清除金额中的逗号
     */
    clearComma: function(moneyStr) {
        moneyStr = $.trim(moneyStr);
        if (moneyStr.length <= 0) {
            return 0;
        }
        return moneyStr.replaceAll(",", "");
    },

    /**
     * 仅清除金额中的逗号
     */
    replaceComma: function(moneyStr) {
        moneyStr = $.trim(moneyStr);
        if (moneyStr.length <= 0) {
            return null;
        }
        return moneyStr.replaceAll(",", "");
    },

    /**
     * input输入框中禁用enter键
     */
    disableEnterKey: function() {
        $("input").keypress(function(e) {
            if (13 == e.keyCode) {
                e.preventDefault();
            }
        });
    },

    /**
     * 图片展示
     */
    showPic: function (picPath) {
        $.pdialog.open("图片展示", Main.contextPath + "/SSC11312/showPic", {
            width: 720,
            height: 600
        },{
            picPath: picPath
        });
    },

    /**
     * 弹出窗口
     */
    openWin: function(url) {
        //弹出窗口的宽度
        var iWidth = 720;
        //弹出窗口的高度
        var iHeight = 600;
        //获得窗口的垂直位置
        var iTop = (window.screen.availHeight - 30 - iHeight) / 2;
        //获得窗口的水平位置
        var iLeft = (window.screen.availWidth - 10 - iWidth) / 2;
        window.open(url, '_blank', 'height=' + iHeight + ',innerHeight=' + iHeight + ',width=' + iWidth +
            ',innerWidth=' + iWidth + ',top=' + iTop + ',left=' + iLeft +
            ',status=no,toolbar=no,menubar=no,location=no,resizable=no,scrollbars=0,titlebar=no');
    },

    /**
     * 展示格式化后的金额，箱数，重量
     */
    showFormatMoney: function () {
        //格式化金额
        obj = document.getElementsByName("money");
        for (i = 0; i < obj.length; i++) {
            if (SSCCommon.MONEY_REG.test(obj[i].innerHTML.trim())) {
                obj[i].innerHTML = SSCCommon.formatMoney(obj[i].innerHTML);
            }
        }
        //格式化箱数
        box = document.getElementsByName("box");
        for (i = 0; i < box.length; i++) {
            if (SSCCommon.INT11 > box[i].innerHTML.trim()) {
                box[i].innerHTML = fmoney(box[i].innerHTML,0);
            }
        }
        //格式化重量
        weight = document.getElementsByName("weight");
        for (i = 0; i < weight.length; i++) {
            if (SSCCommon.WEIGHT_REG.test(weight[i].innerHTML.trim())) {
                weight[i].innerHTML = fmoney(weight[i].innerHTML,4);
            }
        }
    }

};


