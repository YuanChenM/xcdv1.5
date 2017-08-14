<%--
  Created by IntelliJ IDEA.
  User: jiangnan
  Date: 15/12/14
  Time: 上午11:11
  卖家产品加工技术标准
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<%@ taglib prefix="msk2" uri="/msk" %>
<link rel="stylesheet" href="${ctx}/static/js/treegrid/css/jquery.treegrid.css">
<script type="text/javascript" src="${ctx}/static/js/treegrid/js/jquery.treegrid.js"></script>
<style>
    td.details-control {
        background: url('${ctx}/static/images/action/details_open.png') no-repeat center center;
        cursor: pointer;
    }

    tr.details td.details-control {
        background: url('${ctx}/static/images/action/details_close.png') no-repeat center center;
    }

    .hideInfo {
        display: none;
    }
</style>
<script type="text/javascript">
    var CHECK_TYPE = "${checkType}";
    var CHECK_RESULT_TYPE = "${checkResultType}";
    var TYPE = "${type}";
    var isDebug = "${isDebug}";
    var showType = "${showType}";
    var slShowName='${slName}';
</script>
<navigation:header title="卖家产品加工技术标准" backTitleArray="产品待审批审核卖家列表"
                   backUrlArray="${ctx}/SL241101/init"></navigation:header>
<div class="page-content list-page">
    <form id="searchForm" action="${ctx}/SL241105/search/${isFeature}" method="post">
        <input type="hidden" name="filterMap[slCode]" value="${slCode}">
        <table id="SL241105_Grid">
            <thead>
            <tr>
                <th coltype="sno" align="center" width="10px"></th>
                <th filter="true" coltype="text" name="prodEpName" align="center">生产商</th>
                <th filter="true" coltype="text" name="brandName" align="center">品牌</th>
                <th filter="true" coltype="text" name="classesName" align="center">产品一级分类</th>
                <th filter="true" coltype="text" name="machiningName" align="center">产品二级分类</th>
                <th filter="true" coltype="text" name="breedName" align="center">产品品种</th>
                <th filter="true" coltype="text" name="productCode" align="center">产品编号</th>
                <th coltype="select" filter="true" name="slQltGradeCode" align="center">产品定级
                    <select>
                        <option value='2'>合格</option>
                        <option value='3'>不合格</option>
                    </select>
                </th>
                <th coltype="text" align="center" name="qltNgReason" width="30px">不合格原因</th>
                <th coltype="select" filter="true" name="qltMonitorResult" align="center">监控人审核
                    <select>
                        <option value='1'>正确</option>
                        <option value='2'>不正确</option>
                        <option value='3'>未审核</option>
                    </select>
                </th>
                <th coltype="action">定级确认
                    <msk2:button buttonType="hidden" buttonId="SL241105.SEARCHBTN" coltype="check" buttonValue="确认" class="h-button"/>
                    <%--<input type="hidden" coltype="check" title="确认" class="h-button"/>--%>
                </th>
                <th coltype="action">审核确认
                    <msk2:button buttonType="hidden" buttonId="SL241105.SAVEBTN" coltype="save" buttonValue="确认" class="h-button"/>
                    <%--<input type="hidden" coltype="save" title="确认" class="h-button"/>--%>
                </th>
                <th coltype="action">特征信息
                    <msk2:button buttonType="hidden" buttonId="SL241105.DETAILBTN" coltype="detail" buttonValue="特征" class="h-button"/>
                    <%--<input type="hidden" coltype="detail" title="特征" class="h-button"/>--%>
                </th>
                <th coltype="action" width="60px">产品品种图片
                    <msk2:button buttonType="hidden" buttonId="SL241105.SEARCHBTN" coltype="search" buttonValue="产品品种图片" class="h-button"/>
                    <%--<input type="hidden" coltype="search" title="产品品种图片" class="h-button" />--%>
                </th>
                <%--<th coltype="action">其他标准--%>
                    <%--<input type="hidden" coltype="audit" title="其他标准" class="h-button"/>--%>
                <%--</th>--%>
            </tr>
            </thead>
            <tbody></tbody>
        </table>
    </form>
    <div id="reasonDiv"></div>


</div>
<script src="${ctx}/static/sl/js/SL241105.js"></script>
