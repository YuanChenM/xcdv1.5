<%--
    Title:营销期公众买家报表
    author:tao_zhifa
    createDate:2016-08-08
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<%@ taglib prefix="msktag" uri="/msk" %>
<%@ taglib prefix="msk2" uri="/codemaster" %>
<%@ taglib prefix="msk1" uri="/codemaster" %>
<msk2:codemaster codeType="FileStatus" viewType="json"/>
<navigation:header title="营销期公众买家报表" backTitleArray="" backUrlArray=""></navigation:header>
<div class="page-content list-page">
    <input type="hidden" id="fileServerIp" value="${fileServerIp}">
        <div class="group-accordion" collapsible="true" active="true">
            <h3>
                <label>生成报表</label>
            </h3>
            <table width="100%">
                <tr>
                    <td align="right" width="12.5%">报表开始时间 : </td>
                    <td width="6%">
                        <input type="text" id="fileStartTime" name="fileStartTime" readonly="readonly"/>
                    </td>
                    <td align="right" width="12.5%">报表结束时间 : </td>
                    <td width="6%">
                        <input type="text" id="fileEndTime" name="fileEndTime" readonly="readonly"/>
                    </td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                <tr>
                    <td align="right">物流区 : </td>
                    <td width="6%">
                        <select name="filterMap['lgcsAreaCode']" id="lgcsAreaCode" style="width: 100%">
                            <option value="">--请选择--</option>
                            <c:forEach items="${logisticsAreaList}" var="logisticsArea">
                                <c:choose>
                                    <c:when test="${buyerDetail.lgcsAreaCode eq logisticsArea.lgcsAreaCode}">
                                        <option value="${logisticsArea.lgcsAreaCode}" selected>${logisticsArea.lgcsAreaName}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${logisticsArea.lgcsAreaCode}">${logisticsArea.lgcsAreaName}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </td>

                    <td align="right">地区(城市) : </td>
                    <td width="6%">
                        <select name="filterMap['cityCode']" id="cityCode" style="width: 100%">
                            <option value="">--请选择--</option>
                            <c:forEach items="${cityList}" var="city">
                                <c:choose>
                                    <c:when test="${buyerDetail.cityCode eq city.cityCode}">
                                        <option value="${city.cityCode}" selected>${city.cityName}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${city.cityCode}">${city.cityName}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td align="right" width="10%">产品一级分类 : </td>
                    <td  width="6%">
                        <select name="filterMap['classesCode']" id="classesCode" style="width: 100%">
                            <option value="">--请选择--</option>
                            <c:forEach items="${classList}" var="classes">
                                <option value="${classes.classesCode}">${classes.classesName}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td align="right" width="10%">产品二级分类 : </td>
                    <td width="6%">

                        <select name="filterMap['machiningCode']" id="machiningCode" style="width: 100%">
                            <option value="">--请选择--</option>
                            <c:forEach items="${machiningList}" var="machining">
                                <option value="${machining.machiningCode}">${machining.machiningName}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td></td>

                </tr>
                <tr>
                    <td align="right">
                        <msk:button buttonValue="生成" buttonId="BR121408.SAVEBTN" buttonType="button" coltype="save"
                                    class="h-button" align="center" useable="can_generate"/>
                    </td>
                </tr>
            </table>
        </div>
    <form action="${ctx}/BR121408/search" id="BR121408Form" method="post">
        <div>
            <table width="100%" id="BR121408_list_grid">
                <thead>
                <tr>
                    <th coltype="sno" align="center" width="15%">编号</th>
                    <th coltype="text" filter="true" name="fileName" align="center" WIDTH="40%">分销买家池营销期公众买家分池买家注册管控表</th>
                    <th coltype="code" name="fileStatus" code2name="FILESTATUS_JSON" width="15%">文件状态</th>
                    <th coltype="text" name="fileCreateTimeStr">文件生成时间</th>
                    <th coltype="action" width="10%" align="center">操作
                    </th>
                </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
        </div>
    </form>

</div>

<script src="${ctx}/static/util/js/download.js"></script>
<script src="${ctx}/static/br/js/BR121408.js"></script>
