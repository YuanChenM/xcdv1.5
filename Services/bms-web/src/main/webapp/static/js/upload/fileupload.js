//@ sourceURL=fileupload.js
$.upload = {
    process: function (s, ufArray) {
        var defaultParam = {
            id: "_js",
            data: null,
            url: null,
            // 上传结束后是否清空File控件
            autoReset: false,
            // 解析返回值
            parseIframeResult: function () {
                //return JSON.parse($(iframeDoc.body).find("preResult").html());
                console.log(a+"12");
                return a;
            },
            // 上传中执行的方法
            loading: function () {
                // console.log("loading...");
            },
            // 上传结束时执行的方法（无论是否成功上传, 最先执行的回调函数）
            uploaded: function () {
                // console.log("loadEnding!");
            },
            // 上传成功时执行的方法（parseIframeResult返回值中的success为true时）
            success: function (data) {
                console.log(111);
            },
            // 上传失败时执行的方法（parseIframeResult返回值中的success不为true时）
            failure: function (data) {
                // console.log("failure!");
                if (!data) {
                    return;
                }
            },
            // 上传异常时执行的方法（超时等无法正常返回时）
            ex: function (iframe, ex) {
                console.log(ex);
            }
        };
        param = $.extend({}, defaultParam, s);
        // 创建iframe
        var createIframe = function (id) {
            var frameId = 'fileUploadFrame' + id;
            var iframe = $('<iframe id="' + frameId + '" name="' + frameId + '" style="display:none" />');
            iframe.appendTo(document.body);
            return iframe;
        };
        // 创建form
        var createForm = function (id, data, ufArray) {
            var formId = 'fileUploadForm' + id;
            var form = $('<form style="display:none" action="" method="POST" name="' + formId + '"enctype="multipart/form-data" encoding="multipart/form-data"></form>');
            form.appendTo(document.body);
            if (!ufArray) {
                $.each($.upload.handle, function (i, n) {
                    form.append($("#" + n.prop.id));
                });
            } else if ($.isArray(ufArray)) {
                $.each($.upload.handle, function (i, n) {
                    if ($.inArray(n.id, ufArray) > -1) {
                        form.append($("#" + n.prop.id));
                    }
                });
            }
            if (data) {
                for (var dn in data) {
                    form.append($("<input type='hidden' />").prop("name", dn).prop("value", data[dn]));
                }
            }
            return form;
        };
        // 上传文件
        iframe = createIframe(param.id);
        form = createForm(param.id, param.data, ufArray);
        form.prop("target", iframe.prop("id"));
        form.prop("action", param.url);
        var validate= form.validateForm();
        if(!validate){
            return;
        }
        form.submit();
        // 回调
        iframe.bind("load", function (event) {
            param.uploaded();
        });
        param.loading();
    },
    create: function (fileLinkId, ch, fileProp,fileSize,fileType,fileSizeMessage,fileTypeMessage) {
        var allType = '*/*';
        if(fileType ){
            var fileAcceptType ="."+fileType;
            allType = fileAcceptType.replace(",",",.")
        }
debugger;
        var prop = $.extend({
            id: "file_" + fileLinkId,
            name: "file",
            accept: allType,
            fileSize: fileSize,
            fileType: fileType,
            fileSizeMessage: fileSizeMessage,
            fileTypeMessage: fileTypeMessage
        }, fileProp);
        var fileLink = $("#" + fileLinkId);
        var file = $('<input style="display:none" type="file" name="'+fileLinkId+'">');
        for (var pi in prop) {
            file.attr(pi, prop[pi]);
        }
        fileLink.after(file);
        var cleve = function () {
            file.click();
        };
        fileLink.bind('click', cleve);
        file.bind('change', function () {
            ch(fileLink, file.val());
        });
        this.handle.push({id: fileLinkId, prop: prop, cleve: cleve, cheve: ch});
        return prop;
    },
    destory: function (fileLinkId) {
        var destoryObj = null;
        this.handle = $.grep(this.handle, function (n, i) {
            if (n.id == fileLinkId) {
                $("#" + n.prop.id).remove();
                $("#" + n.id).unbind('click', n.cleve);
                destoryObj = n;
                return true;
            }
            return false;
        }, true);
        return destoryObj;
    },
    reset: function (fileLinkId) {
        var resetObj = this.destory(fileLinkId);
        if (resetObj) {
            this.create(resetObj.id, resetObj.cheve, resetObj.prop);
        }
    },
    handle: []
};
this.window.onmessage = function getMessage(message) {
    try {
        var result = message.data;
        console.log(result.fids);
        if (result.success) {
            param.success(result.fids);
        } else {
            $.alertMessage.info("上传失败!");
            //param.failure(result);
        }
    } catch (ex) {
        try {
            param.ex(this, ex);
        } catch (eex) {
//					console.log(eex);
            alert("param.ex(this, ex):run error!");
        }
    }
    if (param.autoReset) {
        $.each($.upload.handle, function (i, n) {
            $.upload.reset(n.id, n.cheve, n.prop);
        });
    } else {
        $.each($.upload.handle, function (i, n) {
            $("#" + n.id).after($("#" + n.prop.id));
        });
    }
    iframe.remove();
    form.remove();
};