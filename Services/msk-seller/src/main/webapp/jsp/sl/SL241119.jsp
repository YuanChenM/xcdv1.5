<%-- 
    Title:产品包装
    author:pxg
    createDate:2015-12-09
    updateDate:2015-12-09
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/comm/taglib.jsp" %>
<%@ taglib prefix="msk2" uri="/msk" %>
<navigation:header title="包装标准" backTitleArray="卖家信息列表,卖家产品信息维护" backUrlArray="${ctx}/SL241101/initShow,${ctx}/SL241116/init/${sl241116Bean.slCode}"></navigation:header>
<form>

</form>
<div class="page-content list-page">
    <form action="${ctx}/SL241119/search/${standardId}" id="SL241119Form" method="post">
        <input type="hidden" name="slCode" id="slCode" value="${sl241116Bean.slCode}"/>
        <input type="hidden" name="slPdId" value="${sl241116Bean.slPdId}"/>
        <input type="hidden" name="classesCode" value="${sl241116Bean.pdClassesCode}"/>
        <input type="hidden" name="machiningCode" value="${sl241116Bean.machiningCode}"/>
        <input type="hidden" name="breedCode" value="${sl241116Bean.pdBreedCode}"/>
        <input type="hidden" name="featureCode" value="${sl241116Bean.pdFeatureCode}"/>
        <input type="hidden" name="weightCode" value="${sl241116Bean.weightCode}"/>
        <input type="hidden" name="prodEpId" value="${sl241116Bean.prodEpId}"/>
        <input type="hidden" name="brandEpId" value="${sl241116Bean.brandEpId}"/>
        <input type="hidden" name="brandId" value="${sl241116Bean.brandId}"/>
        <input type="hidden" name="filterMap[slCode]" value="${sl241116Bean.slCode}"/>
        <input type="hidden" name="filterMap[slPdId]" value="${sl241116Bean.slPdId}"/>
        <input type="hidden" id="standardIds" name="standardId" value="${standardId}">
        <table id="SL241119_grid" showAddBtn="true">
            <thead>
                <tr>
                    <th coltype="checkbox" width="8%" name="checkFlag"></th>
                    <th coltype="text" width="8%" name="normsCode">编码</th>
                    <th coltype="text" width="10%" name="normsSuttle">单个产品规格净重</th>
                    <th coltype="text" width="10%" name="normsError" filter="false">单个产品规格净重误差范围</th>
                    <th coltype="text" width="9%" name="normsNumber" filter="false">内包装净重/个数</th>
                    <th coltype="text" width="9%" name="normsSize" filter="false">内包装尺寸</th>
                    <th coltype="text" width="9%" name="normsTexture" filter="false">内包装材质及技术标准</th>
                    <th coltype="text" width="9%" name="normsOut" filter="false">外包装规格</th>
                    <th coltype="text" width="9%" name="normsKg" filter="false">外包装净重/毛重</th>
                    <th coltype="text" width="9%" name="normsOutSize" filter="false">外包装尺寸</th>
                    <th coltype="text" width="9%" name="normsOutTexture" filter="false">外包装材质及技术标准</th>
                    <%--<th coltype="text" width="8%" name="normsTen" filter="false">第十种包装信息</th> 后续使用--%>
                </tr>
            </thead>    
            <tbody>
            </tbody>
        </table>
    </form>
    <msk2:button buttonType="button" buttonId="SL241119.SAVE" buttonValue="保存"/>
    <%--<msk:button buttonValue="保存" buttonType="button" buttonId="SL241119.SAVE"/>--%>
</div>
<script src="${ctx}/static/sl/js/SL241119.js"></script>
