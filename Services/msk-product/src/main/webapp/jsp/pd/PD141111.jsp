<%-- 
    Title:产品包装
    author:pxg
    createDate:2015-12-09
    updateDate:2015-12-09
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>

<script type="text/javascript">
    var BREED_CODE = "${breedCode}";
    var CLASSES_CODE = "${classesCode}";
    var FEATURE_CODE = "${featureCode}";
    var INFO = "${yesOrNo}";
</script>
<c:if test="${yesOrNo != 'yes' }">
    <navigation:header title="产品包装" backTitleArray="标准档案卡列表" backUrlArray="${ctx}/PD141113/init?classesCode=${classesCode}&breedCode=${breedCode}&featureCode=${featureCode}" ></navigation:header>
</c:if>
<c:if test="${yesOrNo == 'yes'}">
    <navigation:header title="产品包装" backTitleArray="产品类别信息,标准档案卡列表" backUrlArray="${ctx}/PD141101/init?classesCode=${classesCode}&breedCode=${breedCode}&featureCode=${featureCode},${ctx}/PD141113/init?classesCode=${classesCode}&breedCode=${breedCode}&featureCode=${featureCode}&yesOrNo=${yesOrNo}&filterMap[classesCode]=${classesCode}&filterMap[breedCode]=${breedCode}&filterMap[featureCode]=${featureCode}" ></navigation:header>
</c:if>
<div class="page-content list-page">
    <form action="${ctx}/PD141111/search" method="post">
        <input type="hidden" id="standardIds" name="filterMap['standardId']" value="${standardId}">
        <table id="pd141111_grid" showAddBtn="true">
            <thead>
            <tr>
                <th coltype="text" width="5%" name="standardId" id="standardId" >产品标准ID</th>
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
                <th coltype="text" width="8%" name="updateTimeString" id="updTime" filter="false">更新日期</th>
                <th coltype="text" width="8%" name="crateTimeString" id="actTime" filter="false">生效日期</th>
                <th coltype="text" width="8%" name="normsCode">编码</th>
                <th coltype="action" width="60px">
                    <msk:button buttonValue="修改" buttonType="hidden" coltype="edit" class="h-button" buttonId="PD141111.EDIT"/>
                    <msk:button buttonValue="删除" buttonType="hidden" coltype="delete" class="h-button" buttonId="PD141111.DELETE"/>
                </th>
            </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
    </form>
    <msk:button buttonValue="新建" buttonType="button" buttonId="PD141111.NEW"/>
</div>
<script src="${ctx}/static/js/pd/PD141111.js"></script>
