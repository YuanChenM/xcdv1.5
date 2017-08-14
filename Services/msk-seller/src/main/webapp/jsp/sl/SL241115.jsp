<%-- 
    Title:卖家列表
    author:gyh
    createDate:2016-1-27
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/comm/taglib.jsp"%>
<%@ taglib prefix="msk2" uri="/msk" %>
<%--<div class="page-header">
    <span class="page-title">
        <label>卖家信息列表</label>
    </span>
</div>--%>
<navigation:header title="卖家信息列表" backTitleArray="" backUrlArray=""></navigation:header>
<div class="page-content list-page">
    <input type="hidden" id="printUrl" value="/excel/async/print/start/faster">
	<form action="${ctx}/SL241101/search" method="post">
		<table id="sl241101_list_grid" showAddBtn="true" width="100%">
			<thead>
				<tr>
					<th coltype="text" width="10%" name="slCodeDis" filter="true" >卖家编码</th>
					<th coltype="link" width="20%" name="slShowName" filter="true">卖家名称</th>
					<th coltype="select" width="10%" name="slMainClass" filter="true">卖家主类型
					   <select>
                           <option value="0">生产型</option>
                           <option value="1">自产型</option>
                           <option value="2">代理型</option>
                           <option value="3">OEM型</option>
                        </select>
					</th>
                    <th coltype="text" width="10%" name="slContact" filter="true">联系人姓名</th>
                    <th coltype="text" width="10%" name="slTel" filter="true">联系电话</th>
                    <th coltype="text"  width="30%" name="cityName" filter="true">行政区划</th>
                    <th coltype="text"  width="10%" name="lgcsAreaName" filter="true">物流区划</th>
                    <!--<th coltype="action" width="50px">卖家资质编辑
                        <msk2:button buttonType="hidden" buttonId="SL241115.EDITBTN" coltype="edit" buttonValue="卖家资质编辑" class="h-button"/>
                        <%--<input type="hidden" value="卖家资质编辑" coltype="edit" title="卖家资质编辑"  class="h-button" />--%>
                    </th> -->
					<th coltype="action" width="50px">产品列表
                        <msk2:button buttonType="hidden" buttonId="SL241115.DETAILBTN" coltype="detail" buttonValue="产品列表" class="h-button"/>
                        <%--<input type="hidden" coltype="detail" title="产品列表"  class="h-button" />--%>
                    </th>
                    <th coltype="action" width="50px">货号列表
                        <msk2:button buttonType="hidden" buttonId="SL241115.INSUREDBTN" coltype="insured" buttonValue="货号列表" class="h-button"/>
                        <%--<input type="hidden" coltype="insured" title="货号列表"  class="h-button" />--%>
                    </th>
                    <th coltype="action" width="50px">查看账号信息
                        <msk2:button buttonType="hidden" buttonId="SL241115.SEARCHBTN" coltype="search" buttonValue="查看账号信息" class="h-button"/>
                        <%--<input type="hidden" coltype="search" title="查看账号信息"  class="h-button" />--%>
                    </th>
                    <th coltype="action" width="50px">卖家信息下载
                        <msk2:button buttonType="hidden" buttonId="SL241115.DOWNLOAD" coltype="download" buttonValue="卖家信息下载" class="h-button"/>
                        <%--<input type="hidden" coltype="search" title="查看账号信息"  class="h-button" />--%>
                    </th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
        <table><tr align="left">
            <msk2:button buttonType="button" buttonId="SL241115.NEW" buttonValue="新建"/>
            &nbsp;
            <msk2:button buttonValue="下载" buttonId="SL241115.DOWN" buttonType="button"/>
        </tr></table>
	</form>
</div>
<%--<script src="${ctx}/js/sl/SL241101.js"></script>--%>
<script src="${ctx}/static/sl/js/SL241115.js"></script>
<script type="text/javascript" src="${ctx}/static/js/core/utils.js"></script>
<script type="text/javascript" src="${ctx}/static/js/loading/download.js"></script>
