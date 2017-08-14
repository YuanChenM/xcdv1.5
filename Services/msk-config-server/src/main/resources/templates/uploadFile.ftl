<!--
Created by IntelliJ IDEA.
User: shi_yuxi
Date: 2016/6/20
Time: 13:17
To change this template use File | Settings | File Templates.
-->
<script type="text/javascript">
    $(function () {
        $('#custom_file_upload').uploadify({
            'swf': 'static/upload/uploadify.swf',
            'uploader': 'uploadFile',
            'multi': 'false',
            'auto': false,
            'fileObjName': 'Filedata',
            'queueID': 'custom-queue',
            'method': 'POST',
            'queueSizeLimit': 10,
            'buttonText': '选择文件',
            'buttonClass': '',
            'fileTypeDesc' : 'excel Files',
            //'fileTypeExts' : '*.xls,*.xlsx',
            'removeCompleted': true,
            'onFallback': function () {//检测FLASH失败调用
                alert("您未安装FLASH控件，无法上传图片！请安装FLASH控件后再试。");
            },
            'onUploadSuccess': function (file, data, response) {
                treeLoad();
            }
        });
        $("#Up").click(function () {
            if ($("#custom-queue").html() == "") {
                alert("请选择要上传的文件");
            }
            else {
                $('#custom_file_upload').uploadify("upload", "*"); //一个一个传
            }
        });
        $("#Clear").click(function () {
            $('#custom_file_upload').uploadify("cancel", "*");
        });
    });
</script>
<div id="custom-demo" class="demo">
    <div class="demo-box">
        <div id="status-message">请选择要上传的文件:</div>
        <div id="custom-queue"></div>
        <input id="custom_file_upload" type="file" name="Filedata" class="button"/>
        <input type="button" id="Up" name="Up" value="上传" class="button"/>
        <input type="button" id="Clear" name="Clear" value="清空" class="button"/>
    </div>
</div>
