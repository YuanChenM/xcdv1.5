<div class="span12" id="mainContext">

                    <div id="Person-1" class="box">
                        <div class="box-header">
                            <i class="icon-list-alt icon-large"></i>
                                <h5>队列详情列表</h5>

                        </div>
                        <div class="box-content box-table">
                            <table class="table table-hover tablesorter">
                                <thead>
                                <tr>
                                    <th>队列名</th>
                                    <th>监听者</th>
                                    <th>监听接口</th>
                                    <th>创建时间</th>
                                    <th>更新时间</th>
                                    <th>操作</th>

                                </tr>
                                </thead>
                                <tbody>
                                <#if pageResult ??>
                                  <#if pageResult.data ??>
                                    <#list pageResult.data as queueRecord>
                                    <tr>
                                        <td >${queueRecord.queueName!}</td>
                                        <td>${queueRecord.observerName!}</td>
                                        <td id="editText" >${queueRecord.notifyUrl!}</td>
                                        <td  style="display: none" id="editInput"><input id="${queueRecord.observerName!}" value="${queueRecord.notifyUrl!}"></td>
                                        <td>${queueRecord.createdTime!}</td>
                                        <td>${queueRecord.updateTime!}</td>
                                        <td>
                                            <input type="button" value="删除" onclick="deleteObserver('${queueRecord.observerName!}','${queueRecord.queueName!}')"/>
                                           <input type="button" value="编辑" id="editButton" onclick="edit()">
                                            <input id="saveButton" style="display: none"  type="button" value="保存" onclick="updateObserver('${queueRecord.observerName!}','${queueRecord.queueName!}')"/>
                                        </td>

                                    </tr>
                                    </#list>
                                  </#if>
                                </#if>
                                <tr style="display: none" id="addDetail">
                                    <td >${pageResult.queueName}</td>
                                    <td><input id="observerName_new" value=""></td>
                                    <td><input id="notifyUrl_new" value=""></td>
                                    <td></td>
                                    <td></td>
                                    <td>
                                        <input type="button" value="取消" onclick="cancel()">
                                        <input type="button" value="保存" onclick="saveNew('${pageResult.queueName}')">
                                    </td>

                                </tr>
                                <tr  id="addDetail">
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td>
                                        <input type="button" value="添加" onclick="addNew()">
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
</div>



<script type="text/javascript">
function deleteObserver($observerName,$queueName){

    var $deleteUrl = "delete";
    var $paramJson = {observerName:$observerName,queueName:$queueName};
    $.ajax({
        url: $deleteUrl,
        data: $paramJson,
        type: "post",
        success:function(data){

            alert("删除成功");
            $("#"+$queueName+"_detail").click();
        },
        error:function(){
            alert("删除失败")
        },
        dataType:"json"
    })
}
    function updateObserver($observerName,$queueName){
        var $updateUrl="update"
        var $notifyUrl= $('#'+$observerName).val();
        var $paramJson={observerName:$observerName,queueName:$queueName,notifyUrl:$notifyUrl}
        if(!$notifyUrl){
            alert("请填写监听接口!!!");
            return;
        }
        $.ajax({
            url: $updateUrl,
            data: $paramJson,
            type: "post",
            success:function(data){
                alert("更新成功");
                $("#"+$queueName+"_detail").click();
            },
           error:function(){
               alert("更新失败")
           }
        })
    }
function addNew(){
    $("#addDetail").show();
}
function cancel(){
    $("#addDetail").hide();
}
function edit(){
    $("#editText").hide();
    $("#editInput").show();
    $("#editButton").hide();
    $("#saveButton").show();
}
function saveNew($queueName){
   var $notifyUrl=$("#notifyUrl_new").val();
   var $observerName=$("#observerName_new").val();
    var $paramJson={queueName:$queueName,notifyUrl:$notifyUrl,observerName:$observerName};
    var $saveNewUrl="register";
    if(!$observerName){
        alert("请给该监听者取个名字吧!!!")
        return;
    }
    if(!$notifyUrl){
        alert("请填写监听接口!!!")
        return;
    }

    $.ajax({
        url: $saveNewUrl,
        data: $paramJson,
        type: "post",
        success:function(data){
            if("false"==data.flag){
                alert("监听者名字重复！");
                return;
            }
            alert("添加成功");
            $("#"+$queueName+"_detail").click();
        },
        error:function(){
            alert("添加失败")
        },
        dataType:"json"
    })
}
</script>
