<%-- 
    Title:产品特征维护
    author:gyh
    createDate:2015-12-09
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/comm/taglib.jsp" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script type="text/javascript">
	var BREED_CODE = "${breedCode}";
	var CLASSES_CODE = "${classesCode}";
</script>
<div class="page-content detail-page">
	<div class="group-accordion" collapsible="false" active="false">
		<h3>
			<label>产品特征维护</label>
		</h3>
		<form:form action="${ctx}/PD141104/${editModel}" id="PD141104Form"
			method="post" commandName="pdFeature" modelAttribute="pdFeature">
			<table width="100%">
				<tr>
				    <td width="100px" style="display:none;" align="right">类别编码</td>
                    <td align="left" style="display:none;"><form:input path="classesCode" id="classesCode"/></td>
					<td width="100px" style="display:none;" align="right">品种编码</td>
					<td align="left" style="display:none;"><form:input path="breedCode" id="breedCode"/></td>
					<td width="100px" style="display:none;" align="right">特征编码</td>
                    <td align="left" style="display:none;"><form:input path="featureCode" id="featureCode"/></td>
					<td width="100px" align="right">特征名称</td>
					<td align="left"><form:input path="featureName" id="featureName" />
                    </td>
				</tr>
			</table>
		</form:form>
	</div>
	<div>
		<msk:button buttonValue="保存" buttonId="PD141104.SAVE" buttonType="button"/>
		<msk:button buttonValue="返回" buttonId="PD141104.BACK" url="${ctx}/PD141101/init" buttonType="button"/>
	</div>
</div>
<script src="${ctx}/static/js/pd/PD141104.js"></script>