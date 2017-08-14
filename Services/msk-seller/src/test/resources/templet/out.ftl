<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>输出结果</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->

</head>

<body>
<hr/>
<a href="#">输出结果</a><br/>
<table border="1">
    <tr>
        <td>接口方法</td>
        <td>接口url</td>
        <td>是否成功</td>
        <td>失败原因</td>
    </tr>
<#list out as result>
    <tr>
        <td>${result.interName!''}</td>
        <td>${result.interUrl!''}</td>
        <td>
            ${result.isSussFlag!''}
        </td>
        <td>${result.reason!''}</td>
    </tr>
</#list>

</table>
<hr/>
</body>
</html>