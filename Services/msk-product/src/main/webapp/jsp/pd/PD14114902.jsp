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

    <form:form action="${ctx}/pd14114902/save" id="PD14114902Form"
               method="post">
        <br/>
        <table>
            <tr>
                <td>提出日:　${proRaiseDateShow}</td>
            </tr>
            <tr>
                <td>结案日:　<input type="text" id="proFixDateShow" name="proFixDateString" value="${proFixDateShow}"/></td>
                <td><input type="hidden" name="keyId" value="${bean.keyId}"/></td>
                <td><input type="hidden" id="proRaiseDateShow" value="${proRaiseDateShow}"/></td>
                <td><input type="hidden" name="getDivName" value="${bean.getDivName}"/></td>
                <td><input type="hidden" name="standardId" value="${bean.standardId}"/></td>
                <td><input type="hidden" name="tncStdItemId" value="${bean.tncStdItemId}"/></td>
            </tr>
        </table>
    </form:form>
    <br/>

    <div>
        <msk:button buttonValue="保存" buttonId="PD14114902.SAVE" buttonType="button"/>
    </div>

<script src="${ctx}/static/js/pd/PD14114902.js"></script>
