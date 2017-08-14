<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/spring" prefix="spring"%>
<c:forEach items="${exception.validatorMessages}" var="message">

<c:if test="${message.rowNum != null && message.rowNum > 0}">
<spring:message code="validator.message.rowLabelBef" />${message.rowNum}<spring:message code="validator.message.rowLabelAft" />
</c:if>
<c:if test="${message.fieldName!='' && message.fieldName != null}">
<spring:message code="${message.fieldName}" />
</c:if>
<c:if test="${message.fieldName!='' || message.rowNum > 0}">
:
</c:if>
<spring:message code="${message.message}" /><br />
</c:forEach>
