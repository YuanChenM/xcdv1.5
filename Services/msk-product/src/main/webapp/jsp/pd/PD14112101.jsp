<%--
    Title:物流区产品管理
    author:yuan_chen
    createDate:2016-1-27
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<%@ taglib prefix="msk2" uri="/codemaster" %>
<msk2:codemaster codeType="ProductMarketType" viewType="json"/>
<div class="page-content list-page">
    <div class="group-accordion" collapsible="false" active="false">
        <h3>
            <label>物流区产品查看</label>
        </h3>

        <%--<form action="${ctx}/PD141121/search/${lgcsCode}" id="PD141121Form" metdod="post">--%>
        <form action="${ctx}/PD14112101/search/${lgcsAreaCode}" id="PD14112101Form" metdod="post">
            <div>
                <table id="PD14112101_Grid">
                    <thead>
                    <tr>
                        <th coltype="checkbox" name="checkFlag"></th>
                        <th coltype="sno" width="20px">编号</th>
                        <th coltype="text" filter="false" name="lgcsAreaCode">物流区编码</th>
                        <th coltype="text" filter="false" name="lgcsAreaName">物流区名称</th>
                        <th coltype="text" filter="true" name="classesCode">分类编码</th>
                        <th coltype="text" filter="true" name="classesName">产品分类</th>
                        <th coltype="text" filter="true" name="machiningCode">二级分类编码</th>
                        <th coltype="text" filter="true" name="machiningName">二级分类</th>
                        <th coltype="text" filter="true" name="breedCode">品种编码</th>
                        <th coltype="text" filter="true" name="breedName">产品品种</th>
                        <th coltype="text" filter="true" name="featureCode">特征编码</th>
                        <th coltype="text" filter="true" name="featureName">产品特征</th>
                        <th coltype="text" filter="true" name="weightCode">净重编码</th>
                        <th coltype="text" filter="true" name="weightName">净重名称</th>
                        <th coltype="text" filter="true" name="gradeCode">等级编码</th>
                        <th coltype="text" filter="true" name="gradeName">等级名称</th>
                        <th coltype="code" width="10%" cellEdit="false" name="pdMarketCode" code2name="PRODUCTMARKETTYPE_JSON" >营销状态
                        </th>
                        <th coltype="action">
                            <msk:button buttonValue="添加" buttonType="hidden" coltype="add" class="h-button" useable="can_add" buttonId="PD14112101.ADD"/>
                            <msk:button buttonValue="删除" buttonType="hidden" coltype="delete" class="h-button" useable="can_delete" buttonId="PD14112101.DELETE"/>
                        </th>
                    </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
            </div>
            <div>
                <input type="hidden" name="filterMap[lgcsAreaCode]" value="${lgcsAreaCode}" id="ss">
            </div>
        </form>
    </div>
</div>
<script type="text/javascript" src="${ctx}/static/js/pd/PD14112101.js"></script>