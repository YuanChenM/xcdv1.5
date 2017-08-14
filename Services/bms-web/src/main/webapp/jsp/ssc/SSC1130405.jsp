<%--
  Created by IntelliJ IDEA.
  User: peng_hao
  Date: 2016/7/11
  Time: 16:19
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="navigation" uri="/msk" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<div>
    <input type="hidden" name="contractCode" value="${contractCode}">
    <input  type="hidden"  id="contractIdTemp" value="${contractId}"/>
    <input type="hidden" name="purchaserCode" value="${purchaserCode}">
    <table width="100%">
        <tr>
            <td>选择到货车次:
                <select  style="width:79%"  name="etaStr" id="eta_select">
                    <option value="0">请选择</option>
                    <c:forEach items="${pdBeans}" var="pdBean">
                        <option value="${pdBean.arriveDateStr},${pdBean.batchCode}">${pdBean.arriveDateStr} 第 (${pdBean.batchCode}) 车</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
    </table>
    <msk:button buttonType="button" buttonId="SSC1130405.CONFIRM" buttonValue="确定生成发货订单"/>
</div>
<div class="ui-widget">
</div>

<script src="<c:url value="/static/js/ssc/SSC1130405.js" /> "></script>




