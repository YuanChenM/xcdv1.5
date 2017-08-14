<div id="Person-1" class="box">
    <div class="box-header">
        <i class="icon-list-alt icon-large"></i>
        <h5>Batch定义列表</h5>
    </div>
    <div class="box-content box-table">
        <table class="table table-hover tablesorter">
            <thead>
            <tr>
                <th>Batch编码</th>
                <th>Batch名称</th>
                <th>执行频率</th>
                <th>Batch版本</th>
                <th>Batch参数</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
                <#if batchDefinitionList ??>
                    <#list batchDefinitionList as batchDefinition>
                    <tr>
                        <td>${batchDefinition.batchCode!}</td>
                        <td>${batchDefinition.batchName!}</td>
                        <td>${batchDefinition.runFrequency!}</td>
                        <td>${batchDefinition.ver!}</td>
                        <td><input id="${batchDefinition.batchCode!}" value="${batchDefinition.batchCode!}"/></td>
                        <td><input type="button" value="执行" onclick="runBatch('${batchDefinition.batchCode!}')"/></td>
                    </tr>
                    </#list>
                </#if>
            </tbody>
        </table>
    </div>
</div>
<script type="text/javascript">
    function runBatch($batchCode){
        var $paramVal = $('#'+$batchCode).val();
        var $batchSaveUrl = "batch/record/save";
        var $paramJson = {batchCode:$batchCode,parameter:$paramVal};
        $.ajax({
            url:$batchSaveUrl,
            data:$paramJson,
            type:"post",
            success:function(){
                alert("执行操作成功");
            },
            error:function(){
                alert("执行操作失败")
            },
            dataType:"json"
        });
    }
</script>