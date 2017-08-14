var $List_Grid;
var BY121320 = {
    addButton : "BY121320_ADD",
    init: function () {
        
        this.bindAddBtn();
    },
    actionHandler: function (rowdata, coltype, row, col) {
    },

    bindAddBtn: function () {
        $("#" + BY121320.addButton).click(function () {
            $.alertMessage.info("系统升级中，该功能暂未开放！");
        })

    }

}

$(document).ready(function () {
    // 初始化调用
    BY121320.init();
});

