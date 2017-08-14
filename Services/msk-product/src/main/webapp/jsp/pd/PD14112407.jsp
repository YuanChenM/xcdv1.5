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
		<form:form action="${ctx}/PD14112407/addClassTree" id="PD14112407Form"
			method="post">
			<table width="100%">
				<tr>
					<td width="3%" height="5%" align="right">一级类别:</td>
					<td width="100px" align="left">
						<select id="classesSel" style="width:120px"  name="classestreeCode1">
							<option value="">---请选择---</option>
							<c:forEach items="${classesList}" var="list">
								<option value="${list.levelCode}">${list.levelName}</option>
							</c:forEach>
						</select>
					</td>
				</tr>

				<tr>
					<td width="15%" align="right"><input type="checkbox" class="machiningSel"/>选择已有二级类别:</td>
					<td width="100px" align="left">
						<select style="width:120px" disabled id="machiningSel" name="machiningCode">
							    <option value="">---请选择---</option>
						</select>
					</td>

					<td>
						<input type="checkbox" class="classestreeName2"/>新增二级类别:
					</td>
					<td><input type="text" name="classestreeName2"
							   id="classestreeName2" disabled/>
					</td>
				</tr>

				<tr>
					<td width="15%" align="right"><input type="checkbox" class="breedSel"/>选择已有品种:</td>
					<td width="100px" align="left">
						<select style="width:120px" disabled id="breedSel" name="breedCode">
							<option value="">---请选择---</option>
						</select>
					</td>

					<td>
						<input type="checkbox" class="classestreeName3"/>新增品种:
					</td>
					<td><input type="text" name="classestreeName3"
							   id="classestreeName3" disabled/>
					</td>
				</tr>
				<tr>
					<td width="15%" align="right"><input type="checkbox" class="featureSel"/>选择已有特征:</td>
					<td width="100px" align="left">
						<select style="width:120px" disabled id="featureSel" name="selectFeature">
							<option value="">---请选择---</option>
						</select>
					</td>

					<td>
						<input type="checkbox" class="classestreeName4"/>新增特征:
					</td>
					<td><input type="text" name="classestreeName4"
							   id="classestreeName4" disabled/>
					</td>
				</tr>
				<tr>
					<td width="15%" align="right"><input type="checkbox" class="selectWeight"/>选择已有净重:</td>
					<td width="100px" align="left">
						<select style="width:120px" id="selectWeight" name="weightCode" disabled>
							<option value="0">---请选择---</option>
						</select><span id="showText"></span>
					</td>
					<input type="hidden" name="weightName"/>

					<td><input type="checkbox" class="classestreeName5"/>新增确定净重:
					</td>
					<td><input type="text" name="classestreeName5" id="classestreeName5" disabled /> (kg)
					</td>
				</tr>
					<tr>
						<td width="15%" align="right"></td>
						<td width="100px" align="left"></td>
						<td><input type="checkbox" class="copyCode"/>新增抄码净重:</td>
					</tr>
					<tr>
						<td width="15%" align="right"></td>
						<td width="100px" align="left"></td>
						<td width="15%" align="right">新增抄码编码:</td>
						<td align="left"><input type="text" name="copyCodeId" id="copyCodeId" disabled/></td>
					</tr>
					<tr>
						<td width="15%" align="right"></td>
						<td width="100px" align="left"></td>
						<td width="15%" align="right">新增抄码名称:</td>
						<td align="left"><input type="text" name="copyCodeName" id="copyCodeName" disabled/></td>
					</tr>
					<tr>
						<td width="15%" align="right"></td>
						<td width="100px" align="left"></td>
						<td width="15%" align="right">新增净重数值:</td>
						<td align="left"><input type="text" name="copyCodeVal" id="copyCodeVal" disabled/></td>
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
		<msk:button buttonValue="保存" buttonId="PD14112407.SAVE" buttonType="button"/>
	</div>
</div>
<script src="${ctx}/static/js/pd/PD14112407.js"></script>