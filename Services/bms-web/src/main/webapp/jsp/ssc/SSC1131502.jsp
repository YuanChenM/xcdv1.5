<%--
    Title: 生产商入库差异单
    author: xia_xiaojie
    createDate: 2016-08-22
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglib.jsp" %>

<div   style="position:relative; height:auto; overflow:auto">
    <table class="dataTable no-footer" width="100%">
        <thead>
        <tr>
            <th>序号</th>
            <th>产品信息</th>
            <th>发货箱数</th>
            <th>发货重量(kg)</th>
            <th>结算标准价(元/kg)</th>
            <th>货值(元)</th>
            <th>修改人</th>
            <th>修改时间</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${productHistories}" var="history" varStatus="status">
            <tr>
                <td align="center">${status.index + 1}</td>
                <td>${history.productName}</td>
                <td>${history.productConfirmBox}</td>
                <td>${history.productQua}</td>
                <td>${history.settkementStandardPrice}</td>
                <td>${history.productValue}</td>
                <td>${history.crtName}</td>
                <td><fmt:formatDate value="${history.crtTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>