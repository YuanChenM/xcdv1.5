<%-- 
    Title:企业信息列表
    author:peng_hao
    createDate:2016-08-31
--%>
<%@ taglib prefix="navigation" uri="/msk" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>

<msk:codemaster codeType="SscEpMainClass" viewType="json" modelName="SSC"/>
<navigation:header title="企业信息列表" backTitleArray="" backUrlArray=""></navigation:header>
<div class="page-content list-page">
    <form action="<c:url value="/SSC11327/search"/>" id="SSC11327Form" method="post" >
        <div class="group-accordion" collapsible="true" active="true">
            <h3>
                <label>查询条件</label>
            </h3>
            <table WIDTH="100%">
                <tr>
                    <td  align="right" >企业账号 :</td>
                    <td  align="left">　
                        <input type="text" id="slAccount" name="filterMap['slAccount']" value="${param.filterMap.slAccount}"/>
                    </td>
                    <td  align="right" >企业名称 :</td>
                    <td  align="left">　
                        <input type="text" id="epName" name="filterMap['epName']" value="${param.filterMap.epName}"/>
                    </td>
                    <td  align="right" >联系人姓名 :</td>
                    <td  align="left">　
                        <input type="text" id="slContact" name="filterMap['slContact']" value="${param.filterMap.slContact}"/>
                    </td>
                    <td  align="right" >联系电话 :</td>
                    <td  align="left">　
                        <input type="text" id="slTel" name="filterMap['slTel']" value="${param.filterMap.slTel}"/>
                    </td>
                </tr>
                <tr>
                    <td  align="right" >行政区划 :</td>
                    <td  align="left">　
                        <input type="text" id="cityName" name="filterMap['cityName']" value="${param.filterMap.cityName}"/>
                    </td>
                    <td  align="right" >物流区划 :</td>
                    <td  align="left">　
                        <input type="text" id="lgcsAreaName" name="filterMap['lgcsAreaName']" value="${param.filterMap.lgcsAreaName}"/>
                    </td>
                    <td  align="right" >企业主类型 :</td>
                    <td  align="left">　
                        <msk:codemaster codeType="SscEpMainClass" modelName="SSC" id="slMainClass" name='filterMap["slMainClass"]' viewType="select"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="8" align="right"><msk:button buttonValue="查询" buttonId="SSC11327.SEARCH" buttonType="button"/></td>
                </tr>
            </table>
        </div>
        <div class="group-accordion" collapsible="true" active="true">
            <h3>
                <label>企业信息列表</label>
            </h3>
            <table width="100%" id="SSC11327_list_grid">
                <thead>
                <tr>
                    <th coltype="text" width="10%" name="slAccount">企业账号</th>
                    <th coltype="link" width="20%" name="epName">企业名称</th>
                    <th coltype="code" width="10%" name="slMainClass" code2name="SSCEPMAINCLASS_JSON">企业主类型</th>
                    <th coltype="text" width="10%" name="slContact">联系人姓名</th>
                    <th coltype="text" width="20%" name="slTel">联系电话</th>
                    <th coltype="text"  width="20%" name="cityName">行政区划</th>
                    <th coltype="text"  width="20%" name="lgcsAreaName">物流区划</th>
                    <th coltype="action" width="10px">产品列表
                        <msk:button buttonValue="企业产品信息" buttonId="SSC11327.DETAIL" buttonType="hidden" coltype="detail"/>
                    </th>
                </tr>
              </thead>
                <tbody></tbody>
            </table>
        </div>
    </form>
</div>
<script src="<c:url value="/static/js/ssc/SSC11327.js"/>"></script>