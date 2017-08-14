<%--
  Created by IntelliJ IDEA.
  User: marshall
  Date: 16/3/9
  Time: 下午8:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<div class="page-content list-page">
    <form action="${ctx}/BY12130301/search/${buyerId}" method="post">
        <table id="BY12130301_Grid">
            <thead>
            <tr>
                <th coltype="sno" width="20px">编号</th>
                <th coltype="text" name="buyerPoolName" filter=false>买家池名称</th>
                <th coltype="text" name="classesName" filter=false>产品一级分类名称</th>
                <th coltype="text" name="machiningNameU" filter=false>产品二级分类名称</th>
                <th coltype="text" name="buyerTypeName" filter=false>买家类型名称</th>
                <th coltype="text" name="lgcsAreaName" filter=false>物流区</th>
                <th coltype="text" name="cityName" filter=false>城市</th>
            </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
    </form>
</div>
<script type="text/javascript" src="${ctx}/static/buyers/js/BY12130301.js"></script>