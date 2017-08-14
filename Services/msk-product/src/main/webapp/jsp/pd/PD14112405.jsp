<%-- 
    Title:三级分类操作
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
		<form:form action="${ctx}/PD14112405/addAndEdit" id="PD14112405Form"
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
					<td width="3%" height="5%" align="right">四级类别:</td>
					<td width="100px" align="left">
						<select style="width:120px"  name="classestreeCode4">
							<option value="${classestree.classestreeCode4}">${classestree.classestreeName4}</option>
						</select>
					</td>
					<td><input type="hidden"  name="classestreeName4" value="${classestree.classestreeName4}"/></td>
				</tr>
				<tr>
					<td width="3%" height="5%" align="right">五级类别:</td>
					<td width="100px" align="left">
						<select style="width:120px"  name="classestreeCode5">
							<option value="${classestree.classestreeCode5}">${classestree.classestreeName5}</option>
						</select>
					</td>
					<td><input type="hidden"  name="classestreeName5" value="${classestree.classestreeName5}"/></td>
				</tr>
					<tr>
						<td width="3%" height="5%" align="right"><input type="checkbox" class="codeName"/>手动指定包装编码名称:</td>
						<td align="left">
						<td width="3%" height="5%" align="right"><input type="checkbox" class="onlyName"/>自动指定包装编码:</td>
						<td align="left">
					</tr>
				<tr>
					<td width="15%" align="right">包装编码:</td>
					<td><input type="text"  name="normsCode"  id="normsCode" disabled/></td>
					<td width="15%" align="right">包装名称:</td>
					<td><input type="text"  name="normsOut"  id="onlyName" disabled/></td>
				</tr>

				<tr>
					<td width="15%" align="right">包装名称:</td>
					<td><input type="text"  name="normsOut"  id="normsOut" disabled/></td>

				</tr>
			</table>
		</form:form>
	</div>
	<div>
		<msk:button buttonValue="保存" buttonId="PD14112405.SAVE" buttonType="button"/>
	</div>
</div>
<script src="${ctx}/static/js/pd/PD14112405.js"></script>