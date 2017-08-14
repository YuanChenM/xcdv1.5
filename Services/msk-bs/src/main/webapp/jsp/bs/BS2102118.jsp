<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<style>
    .train_title{
        text-align: right;
    }
    .train_input{
        width: 271px;
    }
</style>
<div class="page-content list-page">
    <%--冻品管家自我介绍培训经历--%>
    <div id="trainExperience" style="display:block">
        <form:form id="BS2102118FormId" method="post">
            <input type="hidden" id="slCode" value="${slCode}"/>
            <input type="hidden" id="houseCode" value="${houseCode}"/>
            <input type="hidden" id="trainId" value="${train.trainId}"/>
            <table style="border-collapse:separate;border-spacing:10px;width: 100%;border: 0px;">
                <tr>
                    <td class="train_title">培训时间</td>
                    <td>
                        <input type="text" id="trainStart"
                               value="<fmt:formatDate value="${train.trainStart}" pattern="yyyy-MM-dd"/>"
                               class="dataTime" readonly='readonly'/>至<input type="text" id="trainEnd"
                                                                             value="<fmt:formatDate value="${train.trainEnd}" pattern="yyyy-MM-dd"/>"
                                                                             class="dataTime" readonly='readonly'/>
                    </td>
                </tr>
                <tr>
                    <td class="train_title">培训机构</td>
                    <td><input class="train_input" id="trainComp" value="${train.trainComp}" type='text'/></td>
                </tr>
                <tr>
                    <td class="train_title">所获证书</td>
                    <td><input class="train_input" id="trainCertificate" value="${train.trainCertificate}" type='text'/></td>
                </tr>
                <tr>
                    <td class="train_title">
                        <msk:button buttonValue="保存" buttonId="BS2102118.SAVE" buttonType="button"/>
                    </td>
                </tr>
            </table>
        </form:form>
    </div>
</div>
<script src="${ctx}/static/bs/js/BS2102118.js"></script>
