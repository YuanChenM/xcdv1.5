<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
    Title:设置冻品管家
    author:yang_chunyan
    createDate:2016-8-2
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<%@ taglib prefix="msktag" uri="/codemaster" %>
<navigation:header title="冻品管家组总控表下载画面" backTitleArray="报表中心" backUrlArray="${ctx}/BS2102115/init"></navigation:header>
<div class="page-content list-page">
    <form action="${ctx}/BS2102106/export" method="post" id="bs2102108FormId">
        <input type="hidden" id="printUrl" value="/excel/async/print/start/faster">
        <div class="group-accordion" collapsible="false" active="false" >
            <h3>
                <label>冻品管家组总控表</label>
            </h3>
            <table width="100%">
                <tr>
                    <td width="100px" align="right">物流区&nbsp;&nbsp;<span style="color: #ff0000">(*必选)</span></td>
                    <td align="left">
                         <select id="lgcsAreaCode" style="width:135px;" path="lgcsAreaCode">
                            <option value="">请选择</option>
                            <c:forEach items="${lgcsAreaBeanList}" var="area">
                                <option value="${area.lgcsAreaCode}">${area.lgcsAreaName}</option>
                            </c:forEach>
                        </select>
                    </td>

                    <td width="80px" align="right">地区&nbsp;&nbsp;<span style="color: #ff0000">(*必选)</span></td>
                    <td align="left">
                        <select id="cityCode" style="width:135px;" path="cityCode">
                            <option value="">请选择</option>
                        </select>
                    </td>
                    <td align="right">买家类型&nbsp;&nbsp;<span style="color: #ff0000">(*必选)</span></td>
                    <td align="left">
                        <select style="width:138px;" id="buyersType" name='buyerType'>
                            <option value="">请选择</option>
                            <c:forEach items="${buyerTypeList}" var="buyerTypeDemo" varStatus="i">
                                <option value="${buyerTypeDemo.buyerType}">${buyerTypeDemo.buyerTypeName}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td width="130px" align="right">产品一级分类&nbsp;&nbsp;<span style="color: #ff0000">(*必选)</span></td>
                    <td align="left">
                        <select path="classesCode" style="width:135px" id="classesCode" >
                            <option value="">请选择</option>
                            <c:forEach items="${pdClasseslst}" var="pdClasses" varStatus="i">
                                <option value="${pdClasses.classesCode}">${pdClasses.classesName}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td width="130px" align="right">产品二级分类&nbsp;&nbsp;<span style="color: #ff0000">(*必选)</span></td>
                    <td align="left">
                        <select path="machiningCode" style="width:135px" id="machiningCode" name="machiningCode">
                            <option value="">请选择</option>
                        </select>
                    </td>
                    <td width="100px" align="right">所属期&nbsp;&nbsp;<span style="color: #ff0000">(*必选)</span></td>
                    <td align="left">
                        <input path="creationStartTime" style="width:135px" type="text" id="timeStart"/>—
                        <input  path="creationEndTime" style="width:135px" type="text" id="timeEnd"/>
                    </td>
                    <td><msk:button buttonValue="导出" buttonType="button" buttonId="BS2102108.EXPORT"/></td>
                </tr>
            </table>
        </div>

    </form>
</div>
<script type="text/javascript" src="${ctx}/static/js/core/utils.js"></script>
<script src='<c:url value="/static/js/loading/download.js"/>'/>
<script src="${ctx}/static/bs/js/BS2102108.js"></script>