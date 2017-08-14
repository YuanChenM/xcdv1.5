<%-- 
    Title:新增修改产品分类
    author:xhy
    createDate:2016-2-19
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/comm/taglib.jsp" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="page-content detail-page">
	<div class="group-accordion" collapsible="false" active="false">
		<h3>
			<label>产品分类维护</label>
		</h3>
		<form:form action="${ctx}/PD14112401/addAndEdit" id="PD14112401Form"
			method="post">
			<table width="100%">
				<tr>
					<td width="3%" height="5%" align="right">一级类别:</td>
					<td width="100px" align="left">
						<select style="width:120px"  name="classestreeCode1">
							<option value="${classestree.classestreeCode1}">${classestree.classestreeName1}</option>
						</select>
					</td>
					<td typeof="hidden"><input type="hidden"  name="classestreeName1" value="${classestree.classestreeName1}"/></td>
				</tr>
				<tr>
					<td width="15%" align="right">二级类别:</td>
					<td><input type="text"  name="classestreeName2" value="${classestree.classestreeName2}" id="classestreeName2"/></td>
					<td><input type="hidden"  name="classestreeCode2" value="${classestree.classestreeCode2}"/></td>
				</tr>
			</table>
		</form:form>
	</div>
	<div>
		<msk:button buttonValue="保存" buttonId="PD14112401.SAVE" buttonType="button" />
		<%--<msk:button buttonValue="返回" buttonId="PD14112401.BACK" url="${ctx}/PD141124/init" buttonType="button"/>--%>
	</div>
</div>
<script src="${ctx}/static/js/pd/PD14112401.js"></script>