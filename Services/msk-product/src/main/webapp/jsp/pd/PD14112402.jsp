<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
    Title:三级分类操作
    author:xhy
    createDate:2016-2-19
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>

<div class="page-content detail-page">
    <div class="group-accordion" collapsible="false" active="false">
        <h3>
            <label>三级分类维护</label>
        </h3>
        <form:form action="${ctx}/PD14112402/addAndEdit" id="PD14112402Form"
                   method="post">
            <table width="100%">
                <tr>
                    <td width="3%" height="5%" align="right">一级类别:</td>
                    <td width="100px" align="left">
                        <select style="width:120px" name="classestreeCode1">
                            <option value="${classestree.classestreeCode1}">${classestree.classestreeName1}</option>
                        </select>
                    </td>
                    <td typeof="hidden"><input type="hidden" name="classestreeName1"
                                               value="${classestree.classestreeName1}"/></td>
                </tr>
                <tr>
                    <td width="3%" height="5%" align="right">二级类别:</td>
                    <td width="100px" align="left">
                        <select style="width:120px" name="classestreeCode2">
                            <option value="${classestree.classestreeCode2}">${classestree.classestreeName2}</option>
                        </select>
                    </td>
                    <td typeof="hidden"><input type="hidden" name="classestreeName2"
                                               value="${classestree.classestreeName2}"/></td>
                </tr>
                <tr>
                    <td width="15%" align="right">三级类别:</td>
                    <td><input type="text" name="classestreeName3" value="${classestree.classestreeName3}"
                               id="classestreeName3"/></td>
                    <td><input type="hidden" name="classestreeCode3" value="${classestree.classestreeCode3}"/></td>
                </tr>
            </table>
        </form:form>
    </div>
    <div>
        <msk:button buttonValue="保存" buttonId="PD14112402.SAVE" buttonType="button"/>
       <%-- <msk:button buttonValue="返回" buttonId="PD14112402.BACK" url="${ctx}/PD141124/init" buttonType="button"/>--%>
    </div>
</div>
<script src="${ctx}/static/js/pd/PD14112402.js"></script>