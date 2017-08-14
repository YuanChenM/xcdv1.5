<%-- 
    Title:品种产品目录报表
    author:xhy

--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<div class="group-accordion" collapsible="false" active="false" id="pd14210101accordion"
     style=" width:auto;overflow:auto; ">
    <h3 style="position: relative">
        <label>品种产品目录报表</label>
    </h3>

    <form action="${ctx}/PD14210101/search" method="post">
        <input type="hidden" name="classesCode" value=${classesCode}>
        <table id="PD14210101_list_grid" showAddBtn="true">
            <thead class="filterHeader">
            <tr role="row">
                <td class="sno sorting_disabled"></td>
                <td class="text sorting_disabled">
                    <select class="cellFilter cellFilterText" id="machiningInfo" name="filterMap['machiningName']">
                        <option value=''>请选择</option>
                        <c:forEach items="${machiningInfo}" var="machining">
                            <option value="${machining.machiningCode}">${machining.machiningName}</option>
                        </c:forEach>
                    </select>
                </td>
                <td><input type="text" class="cellFilter cellFilterText" name="filterMap['breedName']"/>
                    <input type="text" style='display:none' />
                </td>
                <td class="text sorting_disabled"></td>
                <td class="text sorting_disabled"></td>
                <td><msk:button buttonValue="查询" buttonId="PD14210101.SEARCH" buttonType="button"/></td>
            </tr>
            </thead>
            <thead>
            <tr>
                <th width="10%"></th>
                <th width="10%"></th>
                <th colspan="3" width="60%">品种名</th>
                <th></th>
            </tr>
            <tr>
                <th coltype="sno" width="7%" filter="false">序号</th>
                <th coltype="text" width="15%" name="machiningName">产品二级分类</th>
                <th coltype="text" width="20%" name="breedName">标准市场销售名</th>
                <th coltype="text" width="20%" name="scientificName">学名</th>
                <th coltype="text" width="20%" name="localName">俗名</th>
                <th coltype="text" width="18%" name="classestreeCode">品种产品阅读码</th>
            </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
    </form>
</div>
<script src="${ctx}/static/js/pd/PD14210101.js"></script>
