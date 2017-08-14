<%--
  Created by IntelliJ IDEA.
  Date: 2016/8/3
  Time: 15:46
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="navigation" uri="/msk" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<div style="white-space:nowrap;">
  <table width="100%">
    <input type="hidden" id="contractId" value="${ssc11301Param.contractId}" />
    <tr>
      <td>
        <label for="tags">选择中标:</label>
        <input id="tags" style="width: 80%">
      </td>
    </tr>

  </table>
  <msk:button buttonType="button" buttonId="SSC1130204.CONFIRM" buttonValue="确定"/>
</div>


<div class="ui-widget">
</div>

<script src="<c:url value="/static/js/ssc/SSC1130204.js" /> "></script>