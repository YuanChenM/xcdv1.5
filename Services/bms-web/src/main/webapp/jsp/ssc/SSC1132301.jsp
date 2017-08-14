<%--
  Created by IntelliJ IDEA.
  Date: 2016/8/3
  Time: 15:46
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="navigation" uri="/msk" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglib.jsp" %>
<div  style="white-space:nowrap;">
    <table width="100%">
        <tr>
            <td>
                <label for="tags">选择合同:</label>
                <input readonly="true" id="tags"  style="width: 60%">
                <img  src="../static/images/action/search.png" title="选择合同信息" id="chooseContractInfo" style="cursor:pointer;" />
            </td>
        </tr>

    </table>
    <msk:button buttonType="button" buttonId="SSC1132301.CONFIRM" buttonValue="确定"/>
</div>
</div>
<script src="<c:url value='/static/js/ssc/SSC1132301.js'/>"></script>