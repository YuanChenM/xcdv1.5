
<%--
买家买家池归属
  Created by IntelliJ IDEA.
  User: tao_zhifa
  Date: 2016/7/11
  Time: 9:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<navigation:header title="买家买家池归属" backTitleArray="买家列表,买家信息总控画面" backUrlArray="${ctx}/BY121303/init/,${ctx}/BY121313/init/${buyerId}"></navigation:header>
<div class="page-content list-page">
  <form action="${ctx}/BY121310/init/${buyerId}" method="post" id="BY121310SaveForm">
      <input type="hidden" name="buyerId" value="${buyerId}">
    <table class="dataTable no-footer" id="checkTable">
        <thead>
            <tr>
                <th>产品一级分类</th>
                <c:if test="${sTypeAndMStatus.superiorType eq '01'}">
                    <th>产品二级分类</th>
                </c:if>
           </tr>
        </thead>
        <c:forEach items="${by121310List}" var="byl">
            <tr>
                <td>
                    <c:if test="${sTypeAndMStatus.superiorType ne '01'}">
                        <c:choose>
                            <c:when test="${byl.isChecked eq '1'}">
                                <c:if test="${sTypeAndMStatus.marketingsStatus ne '01' && sTypeAndMStatus.marketingsStatus ne '02'}">
                                    <input type="checkbox" id="buyerPdCla${byl.classCode}"  name="buyerPdCla" value="${byl.classCode}" checked="checked" disabled editmodel = "false">${byl.className}
                                </c:if>
                                <c:if test="${sTypeAndMStatus.marketingsStatus eq '01' || sTypeAndMStatus.marketingsStatus eq '02'}">
                                    <input type="checkbox" id="buyerPdCla${byl.classCode}"  name="buyerPdCla" value="${byl.classCode}" checked="checked" editmodel = "false">${byl.className}
                                </c:if>
                            </c:when>
                            <c:otherwise>
                                <c:if test="${sTypeAndMStatus.marketingsStatus ne '01' && sTypeAndMStatus.marketingsStatus ne '02'}">
                                    <input type="checkbox" id="buyerPdCla${byl.classCode}"  name="buyerPdCla" value="${byl.classCode}" disabled editmodel = "false">${byl.className}
                                </c:if>
                                <c:if test="${sTypeAndMStatus.marketingsStatus eq '01' || sTypeAndMStatus.marketingsStatus eq '02'}">
                                    <input type="checkbox" id="buyerPdCla${byl.classCode}"  name="buyerPdCla" value="${byl.classCode}" >${byl.className}
                                </c:if>

                            </c:otherwise>
                        </c:choose>
                    </c:if>
                    <c:if test="${sTypeAndMStatus.superiorType eq '01'}">
                        ${byl.className}
                    </c:if>
                </td>
                <c:if test="${sTypeAndMStatus.superiorType eq '01'}">
                    <td>
                    <c:forEach items="${byl.pdMachiningList}" var="pdc" >

                            <c:choose>
                                <c:when test="${pdc.isMachinChecked eq '1'}">
                                    <c:if test="${sTypeAndMStatus.marketingsStatus ne '01' && sTypeAndMStatus.marketingsStatus ne '02'}">
                                        <input type="checkbox" id="buyerPdMac${pdc.machiningCode}"  name="buyerPdMac" value="${pdc.machiningCode}_${byl.classCode}" checked="checked" disabled editmodel = "false">${pdc.machiningName}
                                    </c:if>
                                    <c:if test="${sTypeAndMStatus.marketingsStatus eq '01' || sTypeAndMStatus.marketingsStatus eq '02'}">
                                        <input type="checkbox" id="buyerPdMac${pdc.machiningCode}"  name="buyerPdMac" value="${pdc.machiningCode}_${byl.classCode}" checked="checked" bylClassCode="${byl.classCode}">${pdc.machiningName}
                                    </c:if>
                                </c:when>
                                <c:otherwise>
                                    <c:if test="${sTypeAndMStatus.marketingsStatus ne '01' && sTypeAndMStatus.marketingsStatus ne '02'}">
                                        <input type="checkbox" id="buyerPdMac${pdc.machiningCode}"  name="buyerPdMac" value="${pdc.machiningCode}_${byl.classCode}" disabled editmodel = "false">${pdc.machiningName}
                                    </c:if>
                                    <c:if test="${sTypeAndMStatus.marketingsStatus eq '01' || sTypeAndMStatus.marketingsStatus eq '02'}">
                                        <input type="checkbox" id="buyerPdMac${pdc.machiningCode}"  name="buyerPdMac" value="${pdc.machiningCode}_${byl.classCode}" bylClassCode="${byl.classCode}" >${pdc.machiningName}
                                    </c:if>

                                </c:otherwise>
                            </c:choose>

                    </c:forEach>
                    </td>
                </c:if>
            </tr>
        </c:forEach>
        <tbody></tbody>
    </table>
  </form>

    <c:if test="${sTypeAndMStatus.marketingsStatus eq '01' || sTypeAndMStatus.marketingsStatus eq '02'}">
        <div><msk:button buttonType="button" coltype="edit" buttonId="BY121310.EDIT" buttonValue="保存"/></div>
    </c:if>
</div>
<script type="text/javascript" src="${ctx}/static/buyers/js/BY121310.js"></script>