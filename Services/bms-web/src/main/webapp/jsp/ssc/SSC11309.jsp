<%--
    Title:生产商入库单管理
    author:liu_yan2
    createDate:2016-07-04
--%>

<%@ taglib prefix="navigation" uri="/msk" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<c:if test="${type eq '1'}">
    <navigation:header title="生产商入库单一览" backTitleArray="" backUrlArray=""></navigation:header>
</c:if>
<c:if test="${type eq '2'}">
    <navigation:header title="生产商入库单一览" backTitleArray="合同管理一览" backUrlArray="${pageContext.request.contextPath}/SSC11303/init"></navigation:header>
</c:if>
<div class="page-content list-page">
    <form action='<c:url value="/SSC11309/search"/>' method="post" id="SSC11309Form">
        <input type="hidden" name="contractId" value="${sscContractBasic.contractId}">
        <input type="hidden" name="type" value="${type}" id="type">
        <%--<input type="hidden" name="contractName" value="${sscContractBasic.contractName}">
        <input type="hidden" name="producer" value="${sscContractBasic.producer}">--%>
        <div>
            <c:if test="${type eq '2'}">
                <table width="100%">
                    <tr>
                        <th width="120px" align="left">合同编号：</th>
                        <td>${sscContractBasic.contractCode}</td>
                    </tr>
                    <tr>
                        <th align="left">合同名称：</th>
                        <td> ${sscContractBasic.contractName}</td>
                    </tr>
                    <tr>
                        <th align="left">甲方(采购商)：</th>
                        <td>${sscContractBasic.purchaserName} </td>
                    </tr>
                    <tr>
                        <th align="left">乙方(生产商)：</th>
                        <td>${sscContractBasic.supplierName} </td>
                    </tr>
                </table>
            </c:if>
            <br/>
            <table id="SSC11309_list_grid" showAddBtn="true" width="100%">
                <thead>
                <tr>
                    <%--<th coltype="text" name="intoId">编号</th>--%>
                        <th coltype="sno">序号</th>
                    <c:if test="${type eq '1'}">
                        <th coltype="text" name="contractCode" filter="true">合同编号</th>
                        <th coltype="text" name="contractName" filter="true">合同名称</th>
                        <th coltype="text" name="supplierName" filter="true">甲方(采购商)</th>
                        <th coltype="text" name="purchaserName" filter="true">乙方(生产商)</th>
                    </c:if>
                    <th coltype="text" name="intoCode" filter="true">入库单编号</th>
                    <th coltype="select" name="intoType" filter="true">入库方式
                        <select>
                            <option value="0">采购入库</option>
                            <option value="1">调拨入库</option>
                            <option value="2">样品入库</option>
                            <option value="3">退货入库</option>
                        </select>
                    </th>
                    <th coltype="text" name="arriveWarehouse">到货仓库</th>
                    <th coltype="text" name="expectArriveDate">预计入库日期</th>
                    <th coltype="text" name="realArriveDate">实际入库日期</th>
                    <th coltype="text" name="deliveryCode" filter="true">发货订单编码</th>
                    <th coltype="action" width="60px">操作
                        <msk:button buttonType="hidden" buttonId="SSC11309.DETAILBTN" coltype="detail" buttonValue="入库单详细" class="h-button"/>
                    </th>

                </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
        </div>
        <%--<table><tr align="left"><msk:button buttonValue="新建" buttonId="SSC11309.NEW" buttonType="button"/></tr></table>--%>
    </form>
</div>
<script src="<c:url value="/static/js/ssc/SSC11309.js"/>"></script>
