<%-- 
    Title:产品价盘查询
    author:zhouling
    createDate:2016-1-18
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<style type="text/css">
    .ui-datepicker-calendar {
        display: none;
    }
</style>
<script type="text/javascript">
    var paramJson =${paramJson}.pd141199Param[0];
    function deleteFun(ob) {
        $.alertMessage.confirm("你确定要删除该产品的价盘信息吗？", function () {
            $.alertMessage.close();
            paramJson.pricecycleId = $(ob).attr('pricecycleId');
            $('#main-content').postUrl(Main.contextPath + "/PD141199/deletePriceprd", paramJson);
        });
    }
</script>
<navigation:header title="产品价盘查询" backTitleArray="产品价盘维护" backUrlArray="${ctx}/PD141120/init"></navigation:header>
<div class="page-content detail-page">
    <table id="PD141199_list_grid" class="tree dataTable no-footer">
        <thead>
        <tr>
            <th coltype="text" name="number" rowspan="3">序号</th>
            <th coltype="text" rowspan="3" style="white-space : normal;">产品一级分类名及编码</th>
            <th coltype="text" rowspan="3" style="white-space : normal;">产品二级分类名及编码</th>
            <th coltype="text" rowspan="3" style="white-space : normal;">产品品种名及编码</th>
            <th coltype="text" rowspan="3" style="white-space : normal;">产品特征名及编码</th>
            <th coltype="text" rowspan="3" style="white-space : normal;">产品净重编码</th>
            <th coltype="text" rowspan="3" style="white-space : normal;">产品包装编码</th>
            <th coltype="text" rowspan="3">等级</th>
            <th coltype="text" rowspan="3">价盘周期</th>
            <th coltype="text" colspan="6">大宗订单通道</th>
            <th coltype="text" colspan="6">大额订单通道</th>
            <th coltype="text" colspan="8">标准订单通道</th>
            <th coltype="text" rowspan="3">操作</th>
        </tr>
        <tr>
            <th coltype="text" colspan="2">超级</th>
            <th coltype="text" colspan="2">1级</th>
            <th coltype="text" colspan="2">2级</th>
            <th coltype="text" colspan="2">3级</th>
            <th coltype="text" colspan="2">4级</th>
            <th coltype="text" colspan="2">5级</th>
            <th coltype="text" colspan="2">6级</th>
            <th coltype="text" colspan="2">7级</th>
            <th coltype="text" colspan="2">8级</th>
            <th coltype="text" colspan="2">9级</th>
        </tr>
        <%--<tr>--%>
        <%--<th coltype="text" colspan="2">>=3001箱</th>--%>
        <%--<th coltype="text" colspan="2">2001-3000箱</th>--%>
        <%--<th coltype="text" colspan="2">1001-2000箱</th>--%>
        <%--<th coltype="text" colspan="2">501-1000箱</th>--%>
        <%--<th coltype="text" colspan="2">201-500箱</th>--%>
        <%--<th coltype="text" colspan="2">101-200箱</th>--%>
        <%--<th coltype="text" colspan="2">61-100箱</th>--%>
        <%--<th coltype="text" colspan="2">31-60箱</th>--%>
        <%--<th coltype="text" colspan="2">11-30箱</th>--%>
        <%--<th coltype="text" colspan="2">1-10箱</th>--%>
        <%--</tr>--%>
        <tr>
            <th coltype="text" name="superPriceofkg">元/KG</th>
            <th coltype="text" name="superPriceofbox">元/箱</th>
            <th coltype="text" name="onePriceofkg">元/KG</th>
            <th coltype="text" name="onePriceofbox">元/箱</th>
            <th coltype="text" name="twoPriceofkg">元/KG</th>
            <th coltype="text" name="twoPriceofbox">元/箱</th>
            <th coltype="text" name="threePriceofkg">元/KG</th>
            <th coltype="text" name="threePriceofbox">元/箱</th>
            <th coltype="text" name="fourPriceofkg">元/KG</th>
            <th coltype="text" name="fourPriceofbox">元/箱</th>
            <th coltype="text" name="fivePriceofkg">元/KG</th>
            <th coltype="text" name="fivePriceofbox">元/箱</th>
            <th coltype="text" name="sixPriceofkg">元/KG</th>
            <th coltype="text" name="sixPriceofbox">元/箱</th>
            <th coltype="text" name="sevenPriceofkg">元/KG</th>
            <th coltype="text" name="sevenPriceofbox">元/箱</th>
            <th coltype="text" name="eightPriceofkg">元/KG</th>
            <th coltype="text" name="eightPriceofbox">元/箱</th>
            <th coltype="text" name="ninePriceofkg">元/KG</th>
            <th coltype="text" name="ninePriceofbox">元/箱</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${priceCycleList}" var="priceCycle">
            <tr>
                <td>${priceCycle.number}</td>
                <td>${priceCycle.classesCode}/${priceCycle.classesName}</td>
                <td>${priceCycle.machiningCode}/${priceCycle.machiningName}</td>
                <td>${priceCycle.breedCode}/${priceCycle.breedName}</td>
                <td>${priceCycle.featureCode}/${priceCycle.featureName}</td>
                <td>${priceCycle.weightCode}</td>
                <td>${priceCycle.pkgCode}</td>
                <td>${priceCycle.gradeCode}</td>
                <td>${priceCycle.pricecyclePeriod}</td>
                <td>${priceCycle.superPriceofkg}</td>
                <td>${priceCycle.superPriceofbox}</td>
                <td>${priceCycle.onePriceofkg}</td>
                <td>${priceCycle.onePriceofbox}</td>
                <td>${priceCycle.twoPriceofkg}</td>
                <td>${priceCycle.twoPriceofbox}</td>
                <td>${priceCycle.threePriceofkg}</td>
                <td>${priceCycle.threePriceofbox}</td>
                <td>${priceCycle.fourPriceofkg}</td>
                <td>${priceCycle.fourPriceofbox}</td>
                <td>${priceCycle.fivePriceofkg}</td>
                <td>${priceCycle.fivePriceofbox}</td>
                <td>${priceCycle.sixPriceofkg}</td>
                <td>${priceCycle.sixPriceofbox}</td>
                <td>${priceCycle.sevenPriceofkg}</td>
                <td>${priceCycle.sevenPriceofbox}</td>
                <td>${priceCycle.eightPriceofkg}</td>
                <td>${priceCycle.eightPriceofbox}</td>
                <td>${priceCycle.ninePriceofkg}</td>
                <td>${priceCycle.ninePriceofbox}</td>
                <td><a href="javascript:void(0);" onclick="deleteFun(this);" pricecycleId="${priceCycle.pricecycleId}"
                       title="删除该产品价盘"><img src="${ctx}/static/core/images/action/delete.png"/></a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
