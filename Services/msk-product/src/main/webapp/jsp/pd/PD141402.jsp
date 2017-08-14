<%--
  Created by IntelliJ IDEA.
  User: xhy
  Date: 15/3/15
  Time: 上午11:11
  卖家产品一览
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<script type="text/javascript">
    var slShowName = '${slShowName}';
</script>
<navigation:header title="卖家产品一览" backTitleArray="卖家一览"
                   backUrlArray="${ctx}/PD141401/init"></navigation:header>
<div class="page-content list-page">
    <form:form action="${ctx}/PD141402/search" id="PD141402Form" method="post">
        <input type="hidden" name="filterMap['slCode']" value=${slCode}>
        <input type="hidden" name="slCodeDis" value=${slCodeDis}>
            <table id="pd141402_list_grid" showAddBtn="true" width="100%">
                <thead>
                <tr>
                    <th coltype="text" width="13%" name="prodEpName" filter="true">生产商</th>
                    <th coltype="text" width="13%" name="brandEpName" filter="true">品牌</th>
                    <th coltype="text" width="13%" name="pdClassesName" filter="true">产品一级分类</th>
                    <th coltype="text" width="13%" name="machiningName" filter="true">产品二级分类</th>
                    <th coltype="text" width="13%" name="pdBreedName" filter="true">产品品种</th>
                    <th coltype="text" width="13%" name="pdFeatureName" filter="true">产品特征</th>
                    <th coltype="text" width="13%" name="weightName" filter="true">包装净重</th>
                    <th coltype="action" width="60px">在线管理信息
                        <msk:button buttonValue="在线管理信息" buttonType="hidden" coltype="detail" class="h-button" buttonId="PD141402.DETAIL"/>
                    </th>
                </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
    </form:form>
</div>
<script src="${ctx}/static/js/pd/PD141402.js"></script>
