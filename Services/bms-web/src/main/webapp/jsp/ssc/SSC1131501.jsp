<%--
  Created by IntelliJ IDEA.
  User: liu_yan2
  Date: 2016/7/26
  Time: 16:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="navigation" uri="/msk" %>
<%@ include file="/common/taglib.jsp" %>
<div class="page-content list-page">
    <form action='<c:url value="/SSC11315/insertDeliveryPreInto"/>' id="SSC1131501Form" method="post">
        <input type="hidden" id="deliveryConfirmId" name="deliveryConfirmId" value="${deliveryConfirmId}"/>

        <div class="group-accordion" active="true">
            <h3>
                <label>车辆基本信息</label>
            </h3>
            <table>
                <tr>
                    <td align="right">到货车次：</td>
                    <td>${deliveryBatch}</td>
                    <td align="right"></td>
                    <td></td>
                </tr>
                <tr>
                    <td align="right">运输单位执行人(司机)：</td>
                    <td><input type="text" name="driverName" id="driverName" maxlength="100"/></td>
                    <td align="right">运输单位执行人((司机)联系方式：</td>
                    <td><input type="text" name="driverTel" id="driverTel" maxlength="100"/></td>
                </tr>
                <tr>
                    <td align="right">车牌号码：</td>
                    <td><input type="text" name="licPlateNumber" id="licPlateNumber" maxlength="100"/></td>
                    <td align="right">车辆类型：</td>
                    <td><input type="text" name="vehicleType" id="vehicleType" maxlength="100"/></td>
                </tr>
            </table>
        </div>

        <div class="group-accordion" active="true">
            <h3>
                <label>选择待装车产品</label>
            </h3>
            <table cellspacing="8">
                <th>选择</th>
                <th>产品信息</th>
                <th>待分配箱数</th>
                <th>分配箱数</th>
                <c:forEach items="${pdBeans}" var="pdBean">
                    <tr>
                        <td style="text-align:center;"><input type="checkbox" name="pdCode"  value="${pdBean.pdCode}"/></td>
                        <td style="text-align:left;">${pdBean.pdName}</td>
                        <td style="text-align:center;"><fmt:formatNumber value="${pdBean.productConfirmBox}" pattern="#,###"/>
                            <input type="hidden" name="productConfirmBox" id="maxbox_${pdBean.pdCode}" value="${pdBean.productConfirmBox}"/></td>
                        <td style="text-align:center;"><input type="text" name="productPlanBox" id="box_${pdBean.pdCode}" style="width:80px;" value=""/></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <msk:button buttonValue="保存" buttonType="button" buttonId="SSC1131501.SAVE"/>
    </form>
</div>
<script src='<c:url value="/static/js/ssc/SSCCommon.js" />'></script>
<script src='<c:url value="/static/js/ssc/SSC1131501.js" />'></script>





