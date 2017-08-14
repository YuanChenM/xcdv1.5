<%--
    Title:发货订单明细-关联合同
    author:yang_yang
--%>
<%@ taglib prefix="navigation" uri="/msk" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<div>
  <table width="100%">
    <tr>
      <td>
        <label for="tags">选择合同:</label>
        <input id="tags" style="width: 80%">
      </td>
    </tr>

  </table>
  <msk:button buttonType="button" buttonId="SSC1130602.CONFIRM" buttonValue="确定"/>
</div>


<div class="ui-widget">
</div>

<script src="<c:url value="/static/js/ssc/SSC1130602.js" /> "></script>