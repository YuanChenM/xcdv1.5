<%-- 
    Title:卖家审批审核列表
    author:gyh
    createDate:2015-12-14
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/comm/taglib.jsp"%>
<%@ taglib prefix="msk2" uri="/msk" %>
<%--<div class="page-header">
    <span class="page-title">
        <label>产品待审批审核卖家列表</label>
    </span>
</div>--%>
<navigation:header title="产品待审批审核卖家列表" backTitleArray="" backUrlArray=""></navigation:header>
<div class="page-content list-page">
	<form action="${ctx}/SL241101/search" method="post" id="sl241101FormId">
        <input type="hidden" name="filterMap[hideSlMainClass]" value="1">
        <div>
		<table id="sl241101_list_grid" showAddBtn="true" width="100%">
			<thead>
				<tr>
					<th coltype="text" width="10%" name="slCodeDis" filter="true" >卖家编码</th>
					<th coltype="link" width="10%" name="slShowName" filter="true">卖家名称</th>
					<th coltype="select" width="10%" name="slMainClass">卖家分类
					   <select>
                            <option value="0">生产型</option>
                            <option value="1">自产型</option>
                            <option value="2">代理型</option>
                            <option value="3">OEM型</option>
                        </select>
					</th>
                    <th coltype="text" width="10%" name="slContact" filter="true">联系人</th>
                    <th coltype="text" width="10%" name="slTel" filter="true">联系电话</th>
                    <th coltype="text"  width="10%" name="cityName" filter="true">行政区划</th>
                    <th coltype="text"  width="10%" name="lgcsAreaName" filter="true">物流区划</th>
                    <th coltype="select"  width="10%" name="ddjsh" filter="true">待定级审核有无
                        <select>
	                        <option value="1">有</option>
	                        <option value="0">无</option>
                        </select>
                    </th>
                    <th coltype="select" width="10%" name="ddjkr" filter="true">待监控人审核有无
                        <select>
	                        <option value="1">有</option>
	                        <option value="0">无</option>
                        </select>
                    </th>
                    <!--<th coltype="action" width="60px">资质
                        <msk2:button buttonType="hidden" buttonId="SL241101.EDITBTN" coltype="edit" buttonValue="卖家资质预览" class="h-button"/>
                        <%--<input type="hidden" value="卖家资质预览" coltype="edit" title="卖家资质预览"  class="h-button" />--%>
                       </th>
                    -->
                    <th coltype="action" width="60px">加工技术标准
                        <msk2:button buttonType="hidden" buttonId="SL241101.EXPORTBTN" coltype="export" buttonValue="加工技术标准" class="h-button"/>
                        <%--<input type="hidden" value="加工技术标准" coltype="export" title="加工技术标准" class="h-button" />--%>
                    </th>
                    <th coltype="action" width="60px">加工质量标准
                        <msk2:button buttonType="hidden" buttonId="SL241101.CHECKBTN" coltype="check" buttonValue="加工质量标准" class="h-button"/>
                        <%--<input type="hidden" value="加工质量标准" coltype="check" title="加工质量标准" class="h-button" />--%>
                    </th>
                    <th coltype="action" width="60px">产品信息及状态审核
                        <msk2:button buttonType="hidden" buttonId="SL241101.DETAILBTN" coltype="detail" buttonValue="卖家产品信息及状态审核" class="h-button"/>
                        <%--<input type="hidden" value="产品信息及状态审核" coltype="detail" title="卖家产品信息及状态审核" class="h-button" />--%>
                    </th>
                    <%--<th coltype="action" width="60px">包装标准--%>
                        <%--<input type="hidden" value="包装标准" coltype="add" title="包装标准" class="h-button" />--%>
                    <%--</th>--%>
                    <%--<th coltype="action" width="60px">其他标准--%>
                        <%--<input type="hidden" value="其他标准" coltype="add" title="其他标准" class="h-button" />--%>
                    <%--</th>--%>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
        </div>
        <%--<table><tr align="left"><msk:button buttonValue="新建" buttonId="SL241101.NEW" buttonType="button"/></tr></table>--%>
	</form>
</div>
<script src="${ctx}/static/sl/js/SL241101.js"></script>
