<%--
  Created by IntelliJ IDEA.
  User: jiangnan
  Date: 15/12/15
  Time: 下午4:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/comm/taglib.jsp" %>
<%--<div class="page-header">
    &lt;%&ndash;<span class="page-title"> <label>卖家产品包装标准&nbsp;&nbsp;&nbsp;&nbsp;卖家编号:1101010001&nbsp;&nbsp;&nbsp;&nbsp;卖家名称:上海浦东新区公司</label></span>&ndash;%&gt;
    <span class="page-title"> <label>卖家产品包装标准&nbsp;&nbsp;&nbsp;&nbsp;卖家编号:${slCode}&nbsp;&nbsp;&nbsp;&nbsp;卖家名称:${slName}</label></span>
</div>--%>
<%--<navigation:header title="卖家产品包装标准&nbsp;&nbsp;&nbsp;&nbsp;卖家编号:${slCode}&nbsp;&nbsp;&nbsp;&nbsp;卖家名称:${slName}" backTitleArray="产品待审批审核卖家列表" backUrlArray="${ctx}/SL241101/init"></navigation:header>--%>
<div class="page-content list-page">
    <form id="searchForm" action="${ctx}/SL241106/search/${slCode}" method="post">
        <table id="SL241106_Grid" style="min-width: 1024px">
            <input type="hidden" value="${bean.prodEpId}" name="filterMap[prodEpId]"/>
            <input type="hidden" value="${bean.brandEpId}" name="filterMap[brandEpId]"/>
            <input type="hidden" value="${bean.brandId}" name="filterMap[brandId]"/>
            <input type="hidden" value="${bean.pdClassesCode}" name="filterMap[pdClassesCode]"/>
            <input type="hidden" value="${bean.machiningCode}" name="filterMap[machiningCode]"/>
            <input type="hidden" value="${bean.pdBreedCode}" name="filterMap[pdBreedCode]"/>
            <thead>
                <tr>
                    <th rowspan="2" filter="true" name="epName" coltype="text">生产商</th>
                    <th rowspan="2" filter="true" name="brandName" coltype="text">品牌</th>
                    <th rowspan="2" filter="true" name="classesName" coltype="text">产品一级分类</th>
                    <th rowspan="2" filter="true" name="machiningName" coltype="text">产品二级分类</th>
                    <th rowspan="2" filter="true" name="breedName" coltype="text">产品品种</th>
                    <th rowspan="2" filter="true" name="featureName" coltype="text">产品特征</th>
                    <th rowspan="2" name="pkgCode" coltype="text">包装编码</th>
                    <th colspan="5">内包装</th>
                    <th colspan="4">外包装</th>
                    <%--<th></th>--%>
                    <%--<th></th>--%>
                    <%--<th></th>--%>
                    <%--<th></th>--%>
                </tr>
                <tr>
                    <th name="normsSuttle" coltype="text">单个产品规格净重</th>
                    <th name="normsError" coltype="text">单个产品规格净重误差范围</th>
                    <th name="normsNumber" coltype="text">内包装净重/个数</th>
                    <th name="normsSize" coltype="text">内包装尺寸</th>
                    <th name="normsTexture" coltype="text">内包装材质及技术标准</th>
                    <th name="normsOut" coltype="text">外包装规格</th>
                    <th name="normsKg" coltype="text">外包装净重/毛重</th>
                    <th name="normsOutSize" coltype="text">外包装尺寸</th>
                    <th name="normsOutTexture" coltype="text">外包装材质及技术标准</th>
                    <%--<th></th>--%>
                    <%--<th></th>--%>
                    <%--<th></th>--%>
                    <%--<th></th>--%>
                </tr>
            </thead>
            <tbody></tbody>
        </table>
    </form>
    <%--<msk:button buttonType="button" buttonValue="返回" buttonId="SL241103.Back"--%>
            <%--url="${ctx}/SL241101/init" />--%>
</div>
<script src="${ctx}/static/sl/js/SL241106.js"></script>



