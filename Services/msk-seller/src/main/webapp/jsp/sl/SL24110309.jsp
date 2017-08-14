<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/comm/taglib.jsp"%>
<%@ taglib prefix="msk2" uri="/msk" %>
	<div id="accordionSL24110309" class="group-accordion" collapsible="true" active="false"  onclick="javascript:SL24110309.initSL24110309Grid();">
		<h3>
			<label>生产商列表</label>
		</h3>
		<form action="${ctx}/SL241103/search" method="post">
			<input type="hidden" name="slCode" value="${slCode}"/>
		    <input type="hidden" name="epId" value="${epId}"/>
			<input type="hidden" name="url" value="${fileSerUrl}/sl/${epId}/epAge.png" id="url">
		  <table id="SL24110309_grid">
				<thead>
					<th coltype="text" width="21%" name="slCodeManufacture">生产商编码</th>
					<th coltype="text" width="21%" name="epName">生产商名称</th>
					<th coltype="text" width="21%" name="onTime">授权期限</th>
					<th coltype="text" width="21%" name="slAreaCode">行政区划</th>
					<th coltype="text" width="21%" name="licAddr">生产商地址</th>
					<th coltype="action" width="8%">授权证书
						<msk2:button buttonType="hidden" buttonId="SL24110309.SEARCHBTN" coltype="search" buttonValue="授权证书" class="h-button"/>
						<%--<input type="button" hidden="true" id="editBtn1" value="授权证书" coltype="search" title="授权证书" class="h-button" />--%>
					</th>
					<th coltype="action" width="8%">企业资质
						<msk2:button buttonType="hidden" buttonId="SL24110309.REFRESHBTN" coltype="refresh" buttonValue="企业资质" class="h-button"/>
						<%--<input type="button" hidden="true" id="editBtn2" value="企业资质" coltype="refresh" title="企业资质" class="h-button" />--%>
					</th>
				</thead>
				<tbody>
				</tbody>
			</table>
		</form>
	</div>
<script src="${ctx}/static/sl/js/SL24110309.js"></script>

