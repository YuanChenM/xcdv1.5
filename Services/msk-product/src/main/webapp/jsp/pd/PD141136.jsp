<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
    Title:正式上线修改添加
    author:pxg
    createDate:2016-02-23
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<%--Modfiy: Bug #2182 : 详细页面：多个产品的时候，少了滚动条   20160906   BY  杨春艳  Start --%>
<navigation:header title="卖家产品池注册总控列表" backTitleArray="" backUrlArray="" ></navigation:header>
<%--Modfiy: Bug #2182 : 详细页面：多个产品的时候，少了滚动条   20160906   BY  杨春艳  End --%>
<div class="page-content detail-page" style="height:100%">
    <div class="group-accordion" collapsible="false" active="false">
        <h3 style="position: relative">
            <label>查询条件</label>
        </h3>

        <div>
            <form>
                <table>
                    <tr>
                        <td align="left" width="3%">第一级分类:</td>
                        <td align="right" width="1%">
                            <select id="classes" name="classestreeCode1" style="width:100px;">
                                <option value="0" label="--请选择--"/>
                                <c:forEach items="${classesList}" var="list">
                                    <option value="${list.levelCode}">${list.levelName}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td aalign="left" width="3%">第二级分类:</td>
                        <td align="right" width="1%">
                            <select id="machining" name="classestreeCode2" style="width:100px;">
                                <option value="0" label="--请选择--"/>
                            </select>
                        </td>
                        <td align="left" width="3%">第三级分类:</td>
                        <td align="right" width="1%">
                            <select id="breed" name="classestreeCode3" style="width:100px;">
                                <option value="" label="--请选择--"/>
                            </select>
                        </td>
                        <td align="left">
                            <msk:button buttonValue="查询" buttonId="PD141136.SEARCH" buttonType="button"/>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
    <div id="PD14113601Id" style="height: auto">
    </div>
</div>
<script src="<c:url value="/static/pd/js/PD141136.js" /> "></script>
