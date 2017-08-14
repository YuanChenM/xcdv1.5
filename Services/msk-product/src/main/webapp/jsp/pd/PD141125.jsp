<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
    Title:卖家审批审核列表
    author:xhy
    createDate:2015-12-14
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<navigation:header title="产品原料描述标准编辑" backTitleArray="产品分类目录管理" backUrlArray="${ctx}/PD141124/init?classesCode=${classesCode}&machiningCode=${machiningCode}&breedCode=${breedCode}&featureCode=${featureCode}&weightCode=${weightCode}"></navigation:header>
<div class="page-content list-page">
	<div>
		</p>
		<table WIDTH="100%">
			<c:choose>
				<c:when test="${machiningName != null and machiningName!=''}">
					<tr>
						<td width="50%">名称:${machiningName}</td>
					</tr>
				</c:when>
				<c:otherwise>
				<tr>
					<td width="50%">产品品种:${breedName}</td>
					<c:if test="${feaNames != null and feaNames != ''}">
						<td width="50%">产品特征:${feaNames}</td>
					</c:if>
				</tr>
				</c:otherwise>
			</c:choose>
		</table>
		</p>
	</div>
	<form action="${ctx}/pd141125/saveAndEdit" id="PD141125Form" method="post">
		<input type="hidden" name="classesCode" value="${classesCode}"/>
		<div class="group-accordion" collapsible="true" active="true">
			<h3>
				<label>添加数据</label>
			</h3>
			<table width="100%">
				<tr>
					<td  align="right" >名称:</td>
					<td align="left"><input type="text" name="levelName" value="${pd.levelName}"/></td>
					<td></td>
					<td><input type="hidden" name="classestreeCode" value="${pd.classestreeCode}"/></td>
				</tr>
				<tr><%--<td style="display:none;" value="${pd.classestreeCode}"></td>--%>
					<td  align="right">原料学名:</td>
					<td><input type="text" name="scientificName" align="left" value="${pd.scientificName}"/></td>
					<td  align="right" >俗名:</td>
					<td><input type="text" name="localName" align="left" value="${pd.localName}"/></td>
					<td  align="right">标准市场销售名:</td>
					<td><input type="text" name="salesName" align="left" value="${pd.salesName}"/></td>
				</tr>
				<tr>
					<td  align="right">原料原产地:</td>
					<td><input type="text" name="placeOrigin" align="left" value="${pd.placeOrigin}"/></td>
					<td align="right">现产地:</td>
					<td><input type="text" name="placeCurrent" align="left" value="${pd.placeCurrent}"/></td>
					<td  align="right">原料种源:</td>
					<td><input type="text" name="source" align="left" value="${pd.source}"/></td>

				</tr>
				<tr>
					<td  align="right">雏类:</td>
					<td><input type="text" name="childType" align="left" value="${pd.childType}"/></td>
					<td  align="right">饲养方式:</td>
					<td><input type="text" name="feedType" align="left" value="${pd.feedType}"/></td>
					<td  align="right">饲养周期:</td>
					<td><input type="text" name="feedPeriod" align="left" value="${pd.feedPeriod}"/></td>
					<td></td>
				</tr>
				<tr>
					<td  align="right">备注:</td>
					<td><input type="text" name="remark" align="left" value="${pd.remark}"/></td>
					<td></td>
					<td></td>
				</tr>
			</table>
		</div>
	</form>
	<msk:button buttonValue="保存" buttonType="button" buttonId="PD141125.SAVE" />
</div>
<script src="${ctx}/static/js/pd/PD141125.js"></script>
