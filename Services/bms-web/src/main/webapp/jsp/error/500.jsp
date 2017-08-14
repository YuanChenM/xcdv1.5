<%-- 
@screen core
@author ma_b 
--%>
<%@page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true"%>
<html>
<head>
    <title>500</title>
</head>
<body>
    系统内部错误,请联系管理员.<br/>
    异常类型:<%=exception.toString()%><br/>
    错误信息:<%=exception.getMessage()%>
</body>
</html>

