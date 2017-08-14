<%--
  买家基本信息
  User: zhangqiang1

--%>
<%@include file="/common/taglib.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div class="group-accordion" active="true">
            <h3>
                <label>实际配送信息</label>
            </h3>
                <table class="tree dataTable no-footer" id="detailTable" style="width: 100%">
                    <tr style="background:#DBDBDB">
                        <td>配送单编码</td>
                        <td>分批订单ID</td>
                        <td>配送人</td>
                        <td>配送人电话</td>
                        <td>配送时间</td>
                        <td>配送方式</td>
                        <td>预计到货时间</td>
                        <td>实际到货时间</td>
                    </tr>
                    <c:forEach items="${delivers}" var="deliver">
                        <tr>
                            <td >${deliver.deliverCode}</td>
                            <td >${deliver.subOrderId }</td>
                            <td >${deliver.deliverPerson }</td>
                            <td >${deliver.personMobile }</td>
                            <td ><fmt:formatDate value="${deliver.deliverDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                            <td ><c:if test="${not empty deliver.deliverMode }"><msk:codemaster codeType="DeliveryType" viewType="label" modelName="ORDER" codeValue="${deliver.deliverMode}"/></c:if> </td>
                            <td ><fmt:formatDate value="${deliver.expectReceiveDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                            <td ><fmt:formatDate value="${deliver.actualReceiveDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                        </tr>
                    </c:forEach>
                </table>
</div>