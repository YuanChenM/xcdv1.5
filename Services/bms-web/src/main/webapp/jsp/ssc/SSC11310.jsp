<%--
    Title:生产商入库单详细
    author:liu_yan2
    createDate:2016-07-04
--%>

<%@ taglib prefix="navigation" uri="/msk" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<c:if test="${type eq '1'}">
    <navigation:header title="生产商入库单详细" backTitleArray="生产商入库单一览" backUrlArray="${pageContext.request.contextPath}/SSC11309/init/1"></navigation:header>
</c:if>
<c:if test="${type eq '2'}">
    <navigation:header title="生产商入库单详细"
                       backTitleArray="合同管理一览,生产商入库单一览"
                       backUrlArray="${pageContext.request.contextPath}/SSC11309/init/1,${pageContext.request.contextPath}/SSC11309/init/2"
                       backParamArray='/{"contractCode":"${sscIntoBasic.contractCode}","contractName":"${sscIntoBasic.contractName}","supplierName":"${sscIntoBasic.supplierName}","purchaserName":"${sscIntoBasic.purchaserName}"}'></navigation:header>
<%--    <navigation:header title="生产商入库单详细"
                       backTitleArray="合同管理一览画面,生产商入库单管理"
                       backUrlArray="${pageContext.request.contextPath}/SSC11303/init,${pageContext.request.contextPath}/SSC11309/init/2?contractCode=${sscIntoBasic.contractCode}&contractName=${sscIntoBasic.contractName}&producer=${sscIntoBasic.producer}"></navigation:header>--%>
</c:if>
<div class="page-content list-page">
    <form action='<c:url value="/SSC11310/search/${sscIntoBasic.intoId}"/>' method="post" id="SSC11310Form">
        <div>
            <table width="100%">
                <tr>
                 <th width="100px" align="left">入库单编号：</th>
                 <td colspan="3">${sscIntoBasic.intoCode}</td>
                </tr>
                <tr>
                    <th width="100px" align="left">采购商：</th>
                    <td>${sscIntoBasic.purchaserName}</td>
                    <th width="100px" align="left">生产商：</th>
                    <td>${sscIntoBasic.supplierName}</td>
                </tr>
                <tr>
                 <th width="100px" align="left">发货仓库：</th>
                 <td>${sscIntoBasic.deliveryWarehouse}</td>
                 <th width="100px" align="left">目标仓库：</th>
                 <td>${sscIntoBasic.arriveWarehouse}</td>
                </tr>
                <tr>
                 <th width="100px" align="left">计划到货日期：</th>
                 <td>${sscIntoBasic.expectArriveDate}</td>
                 <th width="100px" align="left">实际到货日期：</th>
                 <td>${sscIntoBasic.realArriveDate}</td>
                </tr>
                <tr>
                 <th width="100px" align="left">合同编号：</th>
                 <td><a title="查看合同详情" href="javascript:void(0)" onclick="goToContractDetail(${sscIntoBasic.contractCode});">${sscIntoBasic.contractCode}</a></td>
                 <th width="100px" align="left">发货订单编号：</th>
                 <td>${sscIntoBasic.deliveryCode}</td>
                </tr>
                <tr>
                     <th width="100px" align="left">入库方式：</th>
                     <td colspan="3">
                     <%--<select>--%>
                        <%--<option value="0" <c:if test="${'采购入库'== sscIntoBasic.intoType}"> selected="selected" </c:if>>采购入库</option>--%>
                        <%--<option value="1" <c:if test="${'调拨入库'== sscIntoBasic.intoType}"> selected="selected" </c:if>>调拨入库</option>--%>
                        <%--<option value="2" <c:if test="${'样品入库'== sscIntoBasic.intoType}"> selected="selected" </c:if>>样品入库</option>--%>
                        <%--<option value="3" <c:if test="${'退货入库'== sscIntoBasic.intoType}"> selected="selected" </c:if>>退货入库</option>--%>
                        <%--</select>--%>
                        <input type="radio" name="intoType" value="0" disabled <c:if test="${'采购入库'== sscIntoBasic.intoType}">checked="checked"</c:if>/>采购入库
                        <input type="radio" name="intoType" value="1" disabled <c:if test="${'调拨入库'== sscIntoBasic.intoType}">checked="checked"</c:if>/>调拨入库
                        <input type="radio" name="intoType" value="2" disabled <c:if test="${'样品入库'== sscIntoBasic.intoType}">checked="checked"</c:if>/>样品入库
                        <input type="radio" name="intoType" value="3" disabled <c:if test="${'退货入库'== sscIntoBasic.intoType}">checked="checked"</c:if>/>退货入库
                    </td>
                </tr>
                <tr>
                    <th width="100px" align="left">备注：</th>
                    <td colspan="3">${sscIntoBasic.remark}</td>
                </tr>
                <%--<tr>--%>
                    <%--<th width="100px" align="left">合计：</th>--%>
                    <%--<td colspan="3">${sscIntoBasic.intoCode}(箱)</td>--%>
                <%--</tr>--%>
            </table>
            <br/>
            <table id="SSC11310_list_grid" showAddBtn="true" width="100%">
                <thead>
                <tr>
                   <%-- <th coltype="text" name="id">编号</th>--%>
                    <th coltype="sno">序号</th>
                    <th coltype="text" name="productName">产品</th>
                    <th coltype="text" name="gradeName">等级</th>
                    <th coltype="text" name="productPlanBox">计划到货（箱）</th>
                    <th coltype="text" name="productQua">计划到货重量（KG）</th>
                    <th coltype="number" name="receiveBox" edit="true">实际到货（箱）</th>
                    <th coltype="number" name="receiveWeight">实际到货重量（KG）</th>
                    <th coltype="action" width="60px">操作
                        <msk:button buttonType="hidden" buttonId="SSC11310.SAVEBTN" coltype="save" buttonValue="保存" class="h-button"/>
                    </th>
                </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
            <br/>
            <%--<table width="100%">--%>
                <%--<tr>--%>
                    <%--<th width="15%" align="right">合计：</th>--%>
                    <%--<td>${sscIntoBasic.intoCode}(箱)</td>--%>
                <%--</tr>--%>
            <%--</table>--%>
        </div>
        <%--<table><tr align="left"><msk:button buttonValue="新建" buttonId="SSC11309.NEW" buttonType="button"/></tr></table>--%>
    </form>
</div>
<script src="<c:url value="/static/js/ssc/SSC11310.js"/>"></script>
