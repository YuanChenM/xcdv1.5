<%-- 
    Title:四级分类操作
    author:xhy
    createDate:2016-2-25
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/comm/taglib.jsp" %>

<div class="page-content detail-page">
	<div class="group-accordion" collapsible="false" active="false">
		<h3>
			<label>四级分类维护</label>
		</h3>
		<form:form action="${ctx}/PD14112403/addAndEdit" id="PD14112403Form"
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
					<td width="3%" height="5%" align="right">二级类别:</td>
					<td width="100px" align="left">
						<select style="width:120px"  name="classestreeCode2">
							<option value="${classestree.classestreeCode2}">${classestree.classestreeName2}</option>
						</select>
					</td>
					<td typeof="hidden"><input type="hidden"  name="classestreeName2" value="${classestree.classestreeName2}"/></td>
				</tr>
				<tr>
					<td width="3%" height="5%" align="right">三级类别:</td>
					<td width="100px" align="left">
						<select style="width:120px"  name="classestreeCode3">
							<option value="${classestree.classestreeCode3}">${classestree.classestreeName3}</option>
						</select>
					</td>
					<td typeof="hidden"><input type="hidden"  name="classestreeName3" value="${classestree.classestreeName3}"/></td>
				</tr>
				<tr>
					<td width="14%" align="right">四级类别:</td>
					<td><input type="text"  name="classestreeName4" value="${classestree.classestreeName4}" id="classestreeName4"/></td>
					<td ><input type="hidden"  name="classestreeCode4" value="${classestree.classestreeCode4}"/></td>
				</tr>
			</table>
		</form:form>
	</div>
	<div>
		<msk:button buttonValue="保存" buttonId="PD14112403.SAVE" buttonType="button"/>
	<%--	<msk:button buttonValue="返回" buttonId="PD14112403.BACK" url="${ctx}/PD141124/init" buttonType="button"/>--%>
	</div>
</div>
<script src="${ctx}/static/js/pd/PD14112403.js"></script>