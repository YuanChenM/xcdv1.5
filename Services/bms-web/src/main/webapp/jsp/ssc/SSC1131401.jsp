<%--
  Created by IntelliJ IDEA.
  User: wang_shuai
  Date: 2016/7/4
  Time: 16:19
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="navigation" uri="/msk" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglib.jsp" %>
<div style="white-space:nowrap;">
    <table width="100%">
        <%--<tr>
            <td>
                <label for="tags">选择合同:</label>
                <input id="tags" style="width: 324px">
                <input id="contractCode" type="hidden">
                <img src="../static/images/action/search.png" title="选择合同" id="chooseContractInfo" style="cursor:pointer;" />
            </td>
        </tr>--%>
        <tr>
            <td>选择发货订单:
                <%--<select style="width:79%" name="deliveryCode" id="delivery_select">
                    <option value="0">请选择</option>
                </select>--%>
                <input id="tags" type="hidden" style="width: 324px">
                <input id="delivery_select" name="deliveryCode" style="width: 200px" readonly>
                <img src="../static/images/action/search.png" title="选择发货订单" id="chooseDeliveryOrder" style="cursor:pointer;" />
                <%--<br>
                选择核销单:
                <input id="verificationCode" name="verificationCode" style="width: 300px" readonly>
                <img src="../static/images/action/search.png" title="选择核销单" id="chooseVerification" style="cursor:pointer;" />--%>
            </td>
        </tr>
    </table>
    <msk:button buttonType="button" buttonId="SSC1131401.CONFIRM" buttonValue="确定"/>
</div>
<script src="<c:url value='/static/js/ssc/SSC1131401.js'/>"></script>




