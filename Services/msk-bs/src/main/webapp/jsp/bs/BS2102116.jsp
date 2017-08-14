<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<style>
    .work_title{
        text-align: right;
    }
    .work_input{
        width: 271px;
    }
</style>
<div class="page-content list-page">
    <%--冻品管家自我介绍工作经历--%>
    <div id="workExperience" style="display:block">
        <form:form id="BS2102116FormId" method="post">
            <input type="hidden" id="slCode" value="${slCode}"/>
            <input type="hidden" id="houseCode" value="${houseCode}"/>
            <input type="hidden" id="workId" value="${work.workId}"/>
            <table style="border-collapse:separate;border-spacing:10px;width: 100%;border: 0px;">
                <tr>
                    <td class="work_title">工作时间</td>
                    <td>
                        <input type="text" id="workStart"
                               value="<fmt:formatDate value="${work.workStart}" pattern="yyyy-MM-dd"/>" class="dataTime"
                               readonly='readonly'/>至<input type="text" id="workEnd"
                                                            value="<fmt:formatDate value="${work.workEnd}" pattern="yyyy-MM-dd"/>"
                                                            class="dataTime" readonly='readonly'/>
                    </td>
                </tr>
                <tr>
                    <td class="work_title">工作单位</td>
                    <td><input class="work_input" id="workComp" value="${work.workComp}" type='text'/></td>
                </tr>
                <tr>
                    <td class="work_title">岗位</td>
                    <td><input class="work_input" id="workStation" value="${work.workStation}" type='text'/></td>
                </tr>
                <tr>
                    <td class="work_title">职位</td>
                    <td><input class="work_input" id="workPosition" value="${work.workPosition}" type='text'/></td>
                </tr>
                <tr>
                    <td class="work_title">
                        <msk:button buttonValue="保存" buttonId="BS2102116.SAVE" buttonType="button"/>
                    </td>
                </tr>
            </table>
        </form:form>
    </div>
</div>
<script src="${ctx}/static/bs/js/BS2102116.js"></script>
