<%-- 
    Title:产品类别维护
    author:gyh
    createDate:2015-12-09
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/comm/taglib.jsp"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<div class="page-content detail-page">
	<div class="group-accordion" collapsible="false" active="false">
		<h3>
			<label>产品类别维护</label>
		</h3>
		<form:form action="${ctx}/PD141102/${editModel}" id="PD141102Form"
			method="post" commandName="pdClasses" modelAttribute="pdClasses">
			<table width="100%">
				<tr>
					<td width="100px" style="display:none;" align="right">类别编码</td>
					<td align="left" style="display:none;"><form:input path="classesCode"
							id="classesCode" readonly="${readonlyModel}" /></td>
					<td width="100px" align="right">类别名称</td>
					<td align="left"><form:input path="classesName"
							id="classesName" /></td>
				</tr>
			</table>
		</form:form>
	</div>
	<div>
		<msk:button buttonValue="保存" buttonId="PD141102.SAVE" />
		<msk:button buttonValue="返回" buttonId="PD141102.BACK" />
	</div>
</div>
<script src="${ctx}/static/js/pd/PD141102.js"></script>