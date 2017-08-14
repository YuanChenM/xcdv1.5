<%--
  Created by IntelliJ IDEA.
  User: tao_zhifa
  Date: 2016/9/28
  Time: 10:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<navigation:header title="买家池产品分类配置" backUrlArray="" backTitleArray=""></navigation:header>
<div class="page-content list-page">
  <form action="${ctx}/BR121414/init" method="post" id="BR121414Form">
      <table class="dataTable no-footer" id="BR121414Table">
        <thead>
          <tr>
            <th>产品一级分类</th>
            <th>产品二级分类</th>
          </tr>
        </thead>
        <c:forEach items="${br121414List}" var="bl">
          <tr>
            <td>${bl.classesName}</td>
            <td>
              <c:forEach items="${bl.machiningList}" var="pdl">
                <c:choose>
                    <c:when test="${pdl.isChecked eq '1'}">
                      <input type="text" id="product"  name="${pdl.machiningCodeU}_${pdl.machiningNameU}_${bl.classesCode}_${bl.classesName}" value="${pdl.machiningNameU}"  style="border-style:none;" >
                    </c:when>
                    <c:otherwise>
                      <input type="checkbox" id="buyerPdMac${pdl.machiningCodeU}"  name="buyerPdMac" value="${pdl.machiningCodeU}_${pdl.machiningNameU}_${bl.classesCode}_${bl.classesName}" bylClassCode="${bl.classesCode}" >${pdl.machiningNameU}
                    </c:otherwise>
                </c:choose>

              </c:forEach>

            </td>
          </tr>
        </c:forEach>
      </table>
    <tbody></tbody>
  </form>

  <div><msk:button buttonType="button" coltype="edit" buttonId="BR121414.EDIT" buttonValue="保存"/></div>

</div>
<script type="text/javascript" src="${ctx}/static/br/js/BR121414.js"></script>