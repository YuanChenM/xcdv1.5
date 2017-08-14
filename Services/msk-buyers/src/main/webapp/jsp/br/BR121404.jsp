<%--
    Title:单一买家池列表画面
    author:yuan_zhifei
    createDate:2016-06-14
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<%@ taglib prefix="msk2" uri="/codemaster" %>
<%@ taglib prefix="msktag" uri="/msk" %>
<msk2:codemaster codeType="FileStatus" viewType="json"/>
<navigation:header title="单一买家标准产品池列表" backTitleArray="" backUrlArray=""></navigation:header>
<div class="page-content list-page">
    <input type="hidden" id="byId" value="${buyerId}">
    <form action="${ctx}/BR121404/search/${buyerId}" id="BR121404Form" method="post">
    <div class="group-accordion" collapsible="true" active="true">
        <h3>
            <label>查询条件</label>
        </h3>
        <table width="100%">
            <tr>
                <th align="right">买家名称</th>
                <td>
                    ${buyerName}
                </td>
            </tr>
            <tr>
                <th align="right">买家主码</th>
                <td>
                    ${buyerCode}
                </td>
            </tr>
            <tr>
                <th align="right">批发市场</th>
                <td>
                    ${marketName}
                </td>
            </tr>
        </table>
    </div>
        <div>
            <table width="100%" id="BR121404_list_grid">
                <thead>
                    <th coltype="sno" filter="false">编号</th>
                    <th coltype="text" filter="false" name="fileName">单一买家标准产品池在线管控表</th>
                    <th coltype="code" name="fileStatus" code2name="FILESTATUS_JSON">文件状态</th>
                    <th coltype="action" width="12%" align="center">操作

                    </th>
                </thead>
                <tbody>
                </tbody>
            </table>
        </div>
    </form>
</div>

<script src="${ctx}/static/br/js/BR121404.js"></script>
