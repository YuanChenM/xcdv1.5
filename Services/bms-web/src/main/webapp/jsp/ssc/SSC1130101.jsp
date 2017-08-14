
<%@ taglib prefix="navigation" uri="/msk" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<msk:codemaster codeType="BidStatus" viewType="json" modelName="SSC"/>
<div class="page-content list-page">
    <form action="<c:url value="/SSC11301/search"/>" method="post">
        <input type="hidden" name="bidStatus" id="bidStatus" value="${ssc1130101RsBean.bidStatus}">
        <input type="hidden" name="bidFlag" id="bidFlag" value="${ssc1130101RsBean.bidFlag}">
        <input type="hidden" name="delFlg" id="delFlg" value="${ssc1130101RsBean.delFlg}">
        <input type="hidden" name="bidInputId" id="bidInputId" value="${ssc1130101RsBean.bidInputId}">

        <div>
            <table width="100%" id="SSC1130101_list_grid">
                <thead>
                <tr>
                    <th coltype="radio"></th>
                    <th coltype="text" filter="true" width="15%" name="bidProjectNo">招标项目编号</th>
                    <th coltype="text" filter="true"width="15%" name="bidProjectName">招标项目名称</th>
                    <th coltype="text" filter="true" width="15%" name="purchaserName">招标方公司名</th>
                    <th coltype="text" filter="true" width="15%" name="supplierName">中标公司名</th>
                    <th coltype="text" filter="true" width="15%" name="startDate" id="startDate">开标开始时间</th>
                    <th coltype="text" filter="true" width="15%" name="endDate" id="endDate">开标结束时间</th>
                    <th coltype="code" filter="false" name="bidStatus" code2name="BIDSTATUS_JSON">中标成交确认书状态</th>
                    <th coltype="action" width="5%">操作</th>
                </tr>
                </thead>
                <tbody></tbody>
            </table>
        </div>
    </form>
    <msk:button buttonValue="确定" buttonId="SSC1130101.CONFIRM" buttonType="button"/>
</div>
</div>
<script src="<c:url value="/static/js/ssc/SSC1130101.js"/>"></script>
