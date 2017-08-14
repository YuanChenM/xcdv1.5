<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<ul>
<script type="text/javascript">
var msg = "";
</script>
<c:forEach items="${messages}" var="message">
    <script type="text/javascript">
    msg = msg + '${message}' + '<br />';
    </script>
</c:forEach>
</ul>
<script type="text/javascript">
//错误后画面上的处理
if (window.parent.uploadErrorCallback) {
	window.parent.uploadErrorCallback();
}
window.parent.$.alertMessage.error(msg,null); 
window.parent.HDF.closeLoadingMask();
</script>