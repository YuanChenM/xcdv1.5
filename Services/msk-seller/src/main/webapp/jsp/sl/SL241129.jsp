<%-- 
    Title:产品包装
    author:gyh
    createDate:2015-3-22
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<%@ taglib prefix="msk2" uri="/msk" %>
<navigation:header title="卖家申请产品列表" backTitleArray="卖家信息列表,卖家产品信息维护"
                   backUrlArray="${ctx}/SL241101/initShow,${ctx}/SL241116/init/${slCode}"></navigation:header>
<div class="page-content list-page">
    <form action="${ctx}/SL241129/search" method="post" id="SL241129Id">
        <input type="hidden" value="${slCode}" name="filterMap[providerCode]">
        <div>
            <table id="SL241129_grid" showAddBtn="true">
                <thead>
                <th coltype="date" width="7%" name="applyDateTime" filter="false">申请时间</th>
                <th coltype="text" width="7%" name="providerCode">卖家编码</th>
                <th coltype="text" width="7%" name="providerName" filter="true">卖家名称</th>
                <th coltype="text" width="7%" name="classesCode" filter="true">分类编码</th>
                <th coltype="text" width="7%" name="classesName" filter="true">分类名称</th>
                <th coltype="text" width="7%" name="machiningCode" filter="true">二级分类编码</th>
                <th coltype="text" width="7%" name="machiningName" filter="true">二级分类名称</th>
                <th coltype="text" width="7%" name="breedCode" filter="true">品种编码</th>
                <th coltype="text" width="7%" name="breedName" filter="true">品种名称</th>
                <th coltype="text" width="7%" name="featureCode" filter="true">特征编码</th>
                <th coltype="text" width="7%" name="featureName" filter="true">特征名称</th>
                <th coltype="text" width="7%" name="weightCode" filter="true">净重编码</th>
                <th coltype="text" width="7%" name="weightName" filter="true">净重名称</th>
                <th coltype="text" width="7%" name="status" filter="false">审核状态</th>
                <th coltype="action" width="60px">
                    <msk2:button buttonType="hidden" buttonId="SL241129.DETAILBTN" coltype="detail" buttonValue="详细" class="h-button"/>
                    <%--<input type="button" id="detailBtn" hidden="true" title="详细" coltype="detail" class="h-button"/>--%>
                </th>
                </thead>
                <tbody>
                </tbody>
            </table>
        </div>
    </form>
</div>
<script src="${ctx}/static/sl/js/SL241129.js"></script>
