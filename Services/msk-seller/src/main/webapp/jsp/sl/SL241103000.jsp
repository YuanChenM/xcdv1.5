<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<navigation:header title="卖家企业信息新建" backTitleArray="卖家信息维护"
                   backUrlArray="${ctx}/SL241101/initShow"></navigation:header>
<div class="page-content detail-page" style="margin-top:35px;">
    <input type="hidden" id="jsp_slAccount" name="jsp_slAccount" value="${jsp_slAccount}"/>
    <input type="hidden" id="jsp_epId" name="jsp_epId" value="${jsp_epId}"/>
    <input type="hidden" id="jsp_slCode" name="jsp_slCode" value="${jsp_slCode}"/>

    <%@include file="SL241103001.jsp" %>
    <%@include file="SL241103003.jsp" %>
    <%@include file="SL24110300401.jsp" %>
    <%@include file="SL24110300402.jsp" %>
    <%@include file="SL24110300501.jsp" %>
    <%@include file="SL24110300502.jsp" %>
    <%@include file="SL241103006.jsp" %>
    <%@include file="SL241103012.jsp" %>
    <%@include file="SL241103008.jsp" %>
    <%@include file="SL241103009.jsp" %>
    <%@include file="SL241103007.jsp" %>
    <%@include file="SL241103010.jsp" %>
    <br/><br/>
</div>
<%--<script src="${ctx}/js/sl/SL241103.js"></script>--%>
