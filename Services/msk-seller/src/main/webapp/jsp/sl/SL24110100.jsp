<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/comm/taglib.jsp"%>
<navigation:header title="卖家企业信息修改" backTitleArray="卖家信息维护" backUrlArray="${ctx}/SL241101/initShow"></navigation:header>
<div class="page-content detail-page" style="margin-top:35px;">
    <input type="hidden" id="jsp_slAccount" name="jsp_slAccount" value="${jsp_slAccount}"/>
    <input type="hidden" id="jsp_epId2" name="jsp_epId2" value="${jsp_epId2}"/>
    <input type="hidden" id="jsp_slCode" name="jsp_slCode" value="${jsp_slCode}"/>

    <%@include file="SL24110101.jsp"%>
    <%@include file="SL24110102.jsp"%>
    <%@include file="SL24110103.jsp"%>
    <%@include file="SL2411010401.jsp"%>
    <%@include file="SL2411010402.jsp"%>
    <%@include file="SL2411010501.jsp"%>
    <%@include file="SL2411010502.jsp"%>
    <%@include file="SL24110106.jsp"%>
    <%@include file="SL24110112.jsp"%>
    <%@include file="SL24110108.jsp"%>
    <%@include file="SL24110109.jsp"%>
    <%@include file="SL24110107.jsp"%>
    <%@include file="SL241101010.jsp"%>
    <br/><br/>
</div>

