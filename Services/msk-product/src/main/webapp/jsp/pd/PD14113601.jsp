<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
    Title:正式上线修改添加
    author:pxg
    createDate:2016-02-23
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>

<div class="group-accordion" collapsible="false" active="false" id="pd14113601accordion" style="height:80%">
    <h3 style="position: relative">
        <label>卖家产品池注册总控列表</label>
    </h3>

    <form action="${ctx}/PD14113601/search" method="post">
        <input type="hidden" name="filterMap['classesCode']" value="${bean.classesCode}"/>
        <input type="hidden" name="filterMap['machiningCode']" value="${bean.machiningCode}"/>
        <input type="hidden" name="filterMap['breedCode']" value="${bean.breedCode}"/>
        <table id="pd14113601_grid" showAddBtn="true">
            <thead>
            <tr>
                <th coltype="text" name="classesCode" id="levelCode">一级分类编码</th>
                <th coltype="text" name="classesName">一级分类名称</th>
                <th coltype="text" name="machiningCode" id="levelCode2">二级分类编码</th>
                <th coltype="text" name="machiningName">二级分类名称</th>
                <th coltype="text" name="slCodeDis" filter="false">卖家编码</th>
                <th coltype="text" name="epName" filter="false">卖家名称</th>
                <th coltype="text" name="showDate" filter="false">申报日期</th>
                <th coltype="action">
                    <msk:button buttonValue="详细信息" buttonType="hidden" coltype="detail" class="h-button" buttonId="PD14113601.DETAIL"/>
                </th>
            </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
    </form>

</div>
<script src="${ctx}/static/js/pd/PD14113601.js"></script>
