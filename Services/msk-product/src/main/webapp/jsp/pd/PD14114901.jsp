<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
    Title:市场需求审核注册修改添加
    author:pxg
    createDate:2016-02-23
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<style type="text/css">
    #btn {
        position: relative;
        left: 80px;
    }
</style>

<form:form action="${ctx}/pd14114901/save" id="PD14114901Form"
           method="post">
    <br/>

    <table>
        <c:choose>
            <c:when test="${bean.keyId !=null and bean.keyId!=''}">
                <tr>
                    <td>提出日:　${raiseDateShow}</td>
                </tr>
            </c:when>
            <c:otherwise>
                <td>内容:　</td>
                <td><input type="text" id="tncStdVal" name="tncStdVal"/></td>
            </c:otherwise>
        </c:choose>
        <tr>
            <c:choose>
                <c:when test="${bean.keyId !=null and bean.keyId!=''}">
                    <td>结案日:　<input type="text" id="fixDateString" name="fixDateString" value="${fixDateShow}"/></td>
                    <td><input type="hidden" name="keyId" value="${bean.keyId}"/></td>
                    <td><input type="hidden" id="raiseDateShow" value="${raiseDateShow}"/></td>
                    <td><input type="hidden" name="getDivName" value="${bean.getDivName}"/></td>
                    <td><input type="hidden" name="standardId" value="${bean.standardId}"/></td>
                    <td><input type="hidden" name="tncStdItemId" value="${bean.tncStdItemId}"/></td>

                </c:when>
                <c:otherwise>
                    <td>提出日:　</td>
                    <td><input type="text" id="raiseDateString" name="raiseDateString"/></td>
                    <td><input type="hidden" name="getDivName" value="${bean.getDivName}"/></td>
                    <td><input type="hidden" name="standardId" value="${bean.standardId}"/></td>
                    <td><input type="hidden" name="tncStdItemId" value="${bean.tncStdItemId}"/></td>
                </c:otherwise>
            </c:choose>
        </tr>
    </table>
</form:form>
<br/>

<div>
    <msk:button buttonValue="保存" buttonId="PD14114901.SAVE" buttonType="button"/>
</div>

<script src="${ctx}/static/js/pd/PD14114901.js"></script>
