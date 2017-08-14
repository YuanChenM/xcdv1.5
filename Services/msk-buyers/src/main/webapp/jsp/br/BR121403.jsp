<%--
    Title:单一买家标准产品池画面
    author:yuan_zhifei
    createDate:2016-07-08
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<navigation:header title="单一买家池列表" backTitleArray="" backUrlArray=""></navigation:header>
<div class="page-content list-page">
    <form action="${ctx}/BR121403/search" id="BR121403Form" method="post">
        <div>
            <table width="100%" id="BR121403_list_grid">
                <thead>
                <tr>
                <th coltype="sno" filter="false">编号</th>
                <th coltype="link" filter="true" name="buyerName">买家名称</th>
                <th coltype="text" filter="true" name="buyerCode">买家主码</th>
                <th coltype="text" filter="true"name="marketName">批发市场</th>
                <th coltype="action">
                </th>
                </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
        </div>
    </form>
</div>

<script src="${ctx}/static/br/js/BR121403.js"></script>

