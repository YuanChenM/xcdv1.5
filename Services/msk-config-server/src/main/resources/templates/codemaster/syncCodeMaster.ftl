<!-- BEGIN PAGE HEADER-->
<div class="row-fluid">
    <div class="span12">
        <!-- BEGIN PAGE TITLE & BREADCRUMB-->
        <h3 class="page-title">
            Code Master
            <small>同步</small>
        </h3>
        <ul class="breadcrumb">
            <li>
                <i class="icon-home"></i>
                <a href="#">Code Master</a>
                <i class="icon-angle-right"></i>
            </li>
            <li><a href="#">同步</a></li>
        </ul>
        <!-- END PAGE TITLE & BREADCRUMB-->
    </div>
</div>
<div style="width: 100%">
    <div style="float:left;width: 49%;">
        <div class="portlet box blue">
            <div class="portlet-title">
                <div class="caption"><i class="icon-reorder"></i>DB数据源</div>
            </div>
            <div class="portlet-body form">
                <div class="control-group warning">
                    <label class="control-label" for="dbIp">数据库IP</label>

                    <div class="controls">
                        <input type="text" class="span6 m-wrap" id="dbIp" name="dbIp" onblur="dbIpChange();"/>
                        <span class="help-inline" id="dbIpTip" style="color: #ff0000"></span>
                    </div>
                </div>
                <div class="control-group warning">
                    <label class="control-label" for="dbUserName">数据库用户名</label>

                    <div class="controls">
                        <input type="text" class="span6 m-wrap" id="dbUserName" name="dbUserName" onblur="dbUserNameChange();"/>
                        <span class="help-inline" id="dbUserNameTip" style="color: #ff0000"></span>
                    </div>
                </div>
                <div class="control-group warning">
                    <label class="control-label" for="dbPwd">数据库密码</label>

                    <div class="controls">
                        <input type="text" class="span6 m-wrap" id="dbPwd" name="dbPwd" onblur="dbPwdChange();"/>
                        <span class="help-inline" id="dbPwdTip" style="color: #ff0000"></span>
                    </div>
                </div>
                <div class="control-group warning">
                    <label class="control-label" for="dbName">数据库名称</label>

                    <div class="controls">
                        <input type="text" class="span6 m-wrap" id="dbName" name="dbName" onblur="dbNameChange();"/>
                        <span class="help-inline" id="dbNameTip" style="color: #ff0000"></span>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div style="float:left;width: 49%;">
        <div class="portlet box blue">
            <div class="portlet-title">
                <div class="caption"><i class="icon-reorder"></i>Redis数据</div>
            </div>
            <div class="portlet-body form" style="height: 374px;">
                <div class="control-group warning">
                    <label class="control-label" for="redisIp">Redis IP</label>

                    <div class="controls">
                        <input type="text" class="span6 m-wrap" id="redisIp" name="redisIp" onblur="redisIpChange();"/>
                        <span class="help-inline" id="redisIpTip" style="color: #ff0000"></span>
                    </div>
                </div>
                <div class="control-group warning">
                    <label class="control-label" for="redisPwd">Redis密码</label>

                    <div class="controls">
                        <input type="text" class="span6 m-wrap" id="redisPwd" name="redisPwd""/>
                        <span class="help-inline" id="redisPwdTip" style="color: #ff0000"></span>
                    </div>
                </div>
                <div class="control-group warning">
                    <label class="control-label" for="redisDbNum">Redis num</label>

                    <div class="controls">
                        <input type="text" class="span6 m-wrap" id="redisDbNum" name="redisDbNum" value="0" onchange="redisDbNumChange();" readonly/>
                        <span class="help-inline" id="redisDbNumTip" style="color: #ff0000"></span>
                    </div>
                </div>
            </div>
        </div>
    </div>
        <div class="form-actions">
            <button type="submit" class="btn blue" onclick="syncCodemaster();">同步</button>
        </div>
</div>
<script type="text/javascript">
    function dbIpChange(){
        if($("#dbIp").val() == '' || $("#dbIp").val() == null){
            $("#dbIpTip").text("数据库ip不能为空！");
            return false;
        }else{
            $("#dbIpTip").text("");
            return true;
        }
    }
    function dbUserNameChange(){
        if($("#dbUserName").val() == '' || $("#dbUserName").val() == null){
            $("#dbUserNameTip").text("数据库用户名不能为空！");
            return false;
        }
        else{
            $("#dbUserNameTip").text("");
            return true;
        }
    }
    function dbPwdChange(){
        if($("#dbPwd").val() == '' || $("#dbPwd").val() == null){
            $("#dbPwdTip").text("数据库密码不能为空！");
            return false;
        }else{
            $("#dbPwdTip").text("");
            return true;
        }
    }
    function dbNameChange(){
        if($("#dbName").val() == '' || $("#dbName").val() == null){
            $("#dbNameTip").text("数据库名称不能为空！");
            return false;
        }else{
            $("#dbNameTip").text("");
            return true;
        }
    }
    function redisIpChange(){
        if($("#redisIp").val() == '' || $("#redisIp").val() == null){
            $("#redisIpTip").text("Redis ip不能为空！");
            return false;
        }else{
            $("#redisIpTip").text("");
            return true;
        }
    }
    function redisPwdChange(){
        if($("#redisPwd").val() == '' || $("#redisPwd").val() == null){
            $("#redisPwdTip").text("Redis密码不能为空！");
            return false;
        }else{
            $("#redisPwdTip").text("");
            return true;
        }
    }
    function redisDbNumChange(){
        if($("#redisDbNum").val() == '' || $("#redisDbNum").val() == null){
            $("#redisDbNumTip").text("Redis num不能为空！");
            return false;
        }else{
            $("#redisDbNumTip").text("");
            return true;
        }
    }
    function syncCodemaster(){
        if (!dbIpChange() |
                !dbUserNameChange() |
                !dbPwdChange() |
                !dbNameChange() |
                !redisIpChange() |
                !redisDbNumChange()) {
            return;
        }
        var obj = new Object();
        obj["dbIp"] = $("#dbIp").val();
        obj["dbUserName"] = $("#dbUserName").val();
        obj["dbPwd"] = $("#dbPwd").val();
        obj["dbName"] = $("#dbName").val();
        obj["redisIp"] = $("#redisIp").val();
        obj["redisPwd"] = $("#redisPwd").val();
        obj["redisDbNum"] = $("#redisDbNum").val();
        $.ajax({
            url: "sync/code/master/data",
            contentType: 'application/json',
            type: "post",
            dataType: "json",
            data: JSON.stringify({"siteCode": "111", "auth": "111", "loginId": "111", "param": obj}),
            success: function (data) {
                if (data.status == 'S') {
                    alert("同步成功!");
                } else {
                    alert("同步失败!");
                }
            }
        });
    }
</script>