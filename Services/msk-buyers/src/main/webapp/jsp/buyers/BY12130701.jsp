<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<div class="page-content list-page">
    <div>
        <form:form action="#" id="BY12130701Form"
                   method="post" enctype="multipart/form-data">
            <input type="hidden" id="classesCode" name="classesCode" value="${classesCode}"/>
            <table style="width: 100%;" CellSpacing=8>
                <c:choose>
                    <c:when test="${flag eq 'true'}">
                        <c:forEach items="${pdMacShowList}" var="pdMacShow">
                            <tr>
                                <c:choose>
                                    <c:when test="${pdMacShow.isChecked eq '1'}">
                                        <td align="right" width="50%">
                                            <input type="checkbox"  name="pdMachining" value="${pdMacShow.machiningCode}" checked/>
                                        </td>
                                        <td align="left" width="50%">${pdMacShow.machiningName}</td>
                                    </c:when>
                                    <c:otherwise>
                                        <td align="right" width="50%">
                                            <input type="checkbox"  name="pdMachining" value="${pdMacShow.machiningCode}" />
                                        </td>
                                        <td align="left" width="50%">${pdMacShow.machiningName}</td>
                                    </c:otherwise>
                                </c:choose>
                            </tr>
                        </c:forEach>
                        <tr>
                            <td align="center">
                                <msk:button buttonValue="保存" buttonId="BY12130701.SAVE" buttonType="button"/>
                            </td>
                        </tr>
                    </c:when><c:otherwise>
                    <tr>
                        <td align="center">
                            没有产品信息
                        </td>
                    </tr>
                </c:otherwise>
                </c:choose>
            </table>
        </form:form>
    </div>
</div>
<script type="text/javascript" src="${ctx}/static/buyers/js/BY12130701.js"></script>