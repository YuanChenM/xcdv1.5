<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<%@ taglib prefix="msk2" uri="/codemaster" %>
<msk2:codemaster codeType="CurrentPeriod" viewType="json"/>
<msk2:codemaster codeType="FileStatus" viewType="json"/>
<navigation:header title="分销分类买家池买家产品池与标准产品池对比管控表" backTitleArray="买家列表,买家信息总控画面" backUrlArray="${ctx}/BY121303/init/,${ctx}/BY121313/init/${buyerId}"></navigation:header>
<div class="page-content list-page">
    <div class="group-accordion" collapsible="true" active="true">
        <h3>
            <label>查询条件</label>
        </h3>

        <form action="${ctx}/BY121318/createXml/${buyerId}" id="createFileForm" method="post">
            <input type="hidden" id="buyerId" value="${buyerId}">
            <input type="hidden" value="${fileServerIp}" id="fileServerIp">
            <table CellSpacing=8>
                <tr>
                    <td align="right">所属期 : </td>
                    <td align="center"><input type="text" id="periodStart" value="" name="filterMap['periodStart']" readonly="readonly">
                    </td>
                    <td align="center">-</td>
                    <td align="center"><input type="text" id="periodEnd" value="" name="filterMap['periodEnd']" readonly="readonly"></td>
                </tr>
                <tr>
                    <td align="right">本期 : </td>
                    <td align="center"><input type="radio" id="currentHalfDist" value="1"
                                              name="filterMap['currentPeriod']">本半旬
                    </td>
                    <td align="center"><input type="radio" id="currentDist" value="2" name="filterMap['currentPeriod']">本旬
                    </td>
                    <td align="center"><input type="radio" id="currentMonth" value="3"
                                              name="filterMap['currentPeriod']">本月
                    </td>
                </tr>
                <tr></tr>
                <tr>
                    <td><msk:button buttonValue="生成文件" buttonId="BY121318.CREATE" buttonType="button"></msk:button></td>
                </tr>
                <tr></tr>
                <tr></tr>
            </table>
        </form>
    </div>
    <div>
        <form action="${ctx}/BY121318/search/${buyerId}" id="BY121318Form" method="post">
            <table width="100%" id="BY121318_list_grid">
                <thead>
                <tr>
                    <th coltype="sno" align="center">编号</th>
                    <th coltype="date" name="periodStartTime" filter="true" align="center">所属期开始时间</th>
                    <th coltype="date" name="periodEndTime" filter="true" align="center">所属期结束时间</th>
                    <th coltype="code" name="currentPeriod" filter="true" code2Name="CURRENTPERIOD_JSON">本期</th>
                    <th coltype="text" name="fileName" filter="true" align="center">文件名称</th>
                    <th coltype="code" name="fileStatus" code2name="FILESTATUS_JSON" width="15%">文件状态</th>
                    <th coltype="text" name="fileCreateTimeStr" filter="false" align="center">文件生成时间</th>
                    <th coltype="action" width="12%" align="center">操作

                    </th>
                </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
        </form>
    </div>

</div>

<script type="text/javascript" src="${ctx}/static/buyers/js/BY121318.js"></script>