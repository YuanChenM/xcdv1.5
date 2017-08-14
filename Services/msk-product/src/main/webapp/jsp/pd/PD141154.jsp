<%-- 
    Title:产品包装
    author:pxg
    createDate:2015-12-09
    updateDate:2015-12-09
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<c:if test="${yesOrNo != 'yes' }">
    <navigation:header title="卖家申请产品审核" backTitleArray="" backUrlArray="" ></navigation:header>
</c:if>
<div class="page-content list-page">
    <form action="${ctx}/PD141154/search" method="post" id="PD141154Id">
        <table id="pd141154_grid" showAddBtn="true">
            <thead>
            <tr>
                <th coltype="text" width="7%" name="applyDateTime"  filter="false">申请时间</th>
                <th coltype="text" width="7%" name="providerCode" filter="true" defaultValue="${packageParam.providerCode}">卖家Id</th>
                <th coltype="text" width="7%" name="providerName" filter="false">卖家名称</th>
                <th coltype="text" width="7%" name="classesCode" filter="true" defaultValue="${packageParam.classestreeCode}">分类编码</th>
                <th coltype="text" width="7%" name="classesName" filter="false">分类名称</th>
                <th coltype="text" width="7%" name="machiningCode" filter="true" defaultValue="${packageParam.machiningCode}">二级分类编码</th>
                <th coltype="text" width="7%" name="machiningName" filter="false">二级分类名称</th>
                <th coltype="text" width="7%" name="breedCode" filter="true" defaultValue="${packageParam.breedCode}">品种编码</th>
                <th coltype="text" width="7%" name="breedName" filter="false">品种名称</th>
                <th coltype="text" width="7%" name="featureCode" filter="true" defaultValue="${packageParam.featureCode}">特征编码</th>
                <th coltype="text" width="7%" name="featureName" filter="false">特征名称</th>
                <th coltype="text" width="7%" name="weightCode" filter="true" defaultValue="${packageParam.weightCode}">净重编码</th>
                <th coltype="text" width="7%" name="weightName" filter="false">净重名称</th>
                <th coltype="text" width="7%" name="status" filter="false">审核状态</th>
                <th coltype="action" width="60px">
                    <msk:button buttonValue="详细" buttonType="hidden" coltype="detail" class="h-button" buttonId="PD141154.DETAIL"/>
                </th>
            </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
    </form>
</div>
<script src="${ctx}/static/js/pd/PD141154.js"></script>
