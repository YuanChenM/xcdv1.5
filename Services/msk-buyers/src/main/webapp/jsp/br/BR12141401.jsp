<%--
  Created by IntelliJ IDEA.
  User: tao_zhifa
  Date: 2016/9/30
  Time: 10:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<div class="page-content list-page">
  <form action="${ctx}/BR121414/updateName" method="post" id="BR121414UpdateNameForm">
    <table>
      <tr>${classesName}</tr>

      <tr>
        <input type="hidden" name="hideClassesCheck" value="${classesCode}">
        <input type="hidden" name="isChecked" value="1">
        <input type="hidden" name="hideMachinCheck" value="${machiningCodeU}">
        <td>
          <c:forEach items="${brMPdClassesList}" var="bcl">
            <input type="checkbox" id="pdMachin" name="buyerPdMac" value="${bcl.machiningCode}_${bcl.machiningName}_${bcl.classesCode}_${bcl.classesName}" checked>${bcl.machiningName}
          </c:forEach>
        </td>
      </tr>
      <tr>
        <td>请选择需要修改的名称</td>
        <td>
          <input type="text" value="${machiningNameU}" name="machiningNameU" id="machiningNameU">
        </td>
      </tr>
    </table>
    <tbody></tbody>
  </form>
  <div><msk:button buttonType="button" coltype="edit" buttonId="BR12141401.EDITNAME" buttonValue="保存"/></div>
</div>

<script type="text/javascript" src="${ctx}/static/br/js/BR12141401.js"></script>