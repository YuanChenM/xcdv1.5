<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<style>
    .edu_title{
        text-align: right;
    }
    .edu_input{
        width: 271px;
    }
</style>
<div class="page-content list-page">
    <%--冻品管家自我介绍教育经历--%>
    <div id="eduExperience" style="display:block">
        <form:form id="BS2102117FormId" method="post">
            <input type="hidden" id="slCode" value="${slCode}"/>
            <input type="hidden" id="houseCode" value="${houseCode}"/>
            <input type="hidden" id="eduId" value="${edu.eduId}"/>
            <table style="border-collapse:separate;border-spacing:10px;width: 100%;border: 0px;">
                <tr>
                    <td class="edu_title">学习时间</td>
                    <td>
                        <input type="text" id="eduStart"
                               value="<fmt:formatDate value="${edu.eduStart}" pattern="yyyy-MM-dd"/>" class="dataTime"
                               readonly='readonly'/>至<input type="text" id="eduEnd"
                                                            value="<fmt:formatDate value="${edu.eduEnd}" pattern="yyyy-MM-dd"/>"
                                                            class="dataTime" readonly='readonly'/>
                    </td>
                </tr>
                <tr>
                    <td class="edu_title">教育单位</td>
                    <td><input class="edu_input" id="eduComp" value="${edu.eduComp}" type='text'/></td>
                </tr>
                <tr>
                    <td class="edu_title">学历</td>
                    <td><input class="edu_input" id="eduRecord" value="${edu.eduRecord}" type='text'/></td>
                </tr>
                <tr>
                    <td class="edu_title">学位</td>
                    <td><input class="edu_input" id="eduDegree" value="${edu.eduDegree}" type='text'/></td>
                </tr>
                <tr>
                    <td class="edu_title">
                        <msk:button buttonValue="保存" buttonId="BS2102117.SAVE" buttonType="button"/>
                    </td>
                </tr>
            </table>
        </form:form>
    </div>
</div>
<script src="${ctx}/static/bs/js/BS2102117.js"></script>
