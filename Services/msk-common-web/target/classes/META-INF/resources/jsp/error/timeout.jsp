<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/spring" prefix="spring"%>
<spring:message javaScriptEscape='true' code='system.error.timeout'/>
<script type="text/javascript">
window.location = "${pageContext.request.contextPath}" + "/login?message=sy01s01.login.timeout";
</script>