<%-- 
    Title:三级分类操作
    author:xhy
    createDate:2016-2-19
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="page-content detail-page">
    <div class="group-accordion" collapsible="false" active="false">
        <h3>
            <label>产品分类维护</label>
        </h3>
        <form:form action="${ctx}/PD14112404/addAndEdit" id="PD14112404Form"
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
                    <td width="3%" height="5%" align="right">三级类别:</td>
                    <td width="100px" align="left">
                        <select style="width:120px" name="classestreeCode3">
                            <option value="${classestree.classestreeCode3}">${classestree.classestreeName3}</option>
                        </select>
                    </td>
                    <td typeof="hidden"><input type="hidden" name="classestreeName3"
                                               value="${classestree.classestreeName3}"/></td>
                </tr>
                <tr>
                    <td width="3%" height="5%" align="right">四级类别:</td>
                    <td width="100px" align="left">
                        <select style="width:120px" name="classestreeCode4">
                            <option value="${classestree.classestreeCode4}">${classestree.classestreeName4}</option>
                        </select>
                    </td>
                    <td><input type="hidden" name="classestreeName4" value="${classestree.classestreeName4}"/></td>
                </tr>
                <tr>
                    <c:if test="${classestree.classestreeName5 == null or classestree.classestreeName5 ==''}">
                        <td width="15%" align="right"><input type="checkbox" class="selectWeight"/>选择已有净重:</td>
                        <td width="100px" align="left">
                            <select style="width:120px" id="selectWeight" name="weightCode" disabled>
                                <option value="0">---请选择---</option>
                                <c:forEach items="${WeightList}" var="weightBean">
                                    <option value="${weightBean.weightCode}">${weightBean.weightName}</option>
                                </c:forEach>
                            </select><span id="showText"></span>
                        </td>
                        <td><input type="hidden" name="weightName"/></td>
                    </c:if>
                    <td width="15%" align="right"><c:if
                            test="${classestree.classestreeName5 == null or classestree.classestreeName5 ==''}"><input
                            type="checkbox" class="classestreeName5"/>新增确定净重:</c:if><c:if
                            test="${classestree.classestreeName5 != null and classestree.classestreeName5 !=''}">修改净重:</c:if>
                    </td>
                    <td><input type="text" name="classestreeName5" value="${classestree.classestreeName5}"
                               id="classestreeName5"
                               <c:if test="${classestree.classestreeName5 == null or classestree.classestreeName5 ==''}">disabled</c:if>/><c:choose>
                        <c:when test="${classestree.classestreeName5 != null and classestree.classestreeName5 !=''}">
                            <c:if test="${showKg!=null}">
                                (kg)
                            </c:if>
                        </c:when>
                        <c:otherwise>
                            (kg)
                        </c:otherwise>
                    </c:choose></td>
                    <td><input type="hidden" name="classestreeCode5" value="${classestree.classestreeCode5}"/></td>
                </tr>
                <c:if test="${classestree.classestreeName5 == null or classestree.classestreeName5 ==''}">
                    <tr>
                        <td width="15%" align="right"></td>
                        <td width="100px" align="left"></td>
                        <td><input type="hidden"/></td>
                        <td width="15%" align="right"><input type="checkbox" class="copyCode"/>新增抄码净重:</td>
                        <td align="left"></td>
                    </tr>
                    <tr>
                        <td width="15%" align="right"></td>
                        <td width="100px" align="left"></td>
                        <td><input type="hidden"/></td>
                        <td width="15%" align="right">新增抄码编码:</td>
                        <td align="left"><input type="text" name="copyCodeId" id="copyCodeId" disabled/></td>
                    </tr>
                    <tr>
                        <td width="15%" align="right"></td>
                        <td width="100px" align="left"></td>
                        <td><input type="hidden"/></td>
                        <td width="15%" align="right">新增抄码名称:</td>
                        <td align="left"><input type="text" name="copyCodeName" id="copyCodeName" disabled/></td>
                    </tr>
                    <tr>
                        <td width="15%" align="right"></td>
                        <td width="100px" align="left"></td>
                        <td><input type="hidden"/></td>
                        <td width="15%" align="right">新增净重数值:</td>
                        <td align="left"><input type="text" name="copyCodeVal" id="copyCodeVal" disabled/></td>
                    </tr>
                </c:if>
            </table>
        </form:form>
    </div>
    <div>
        <msk:button buttonValue="保存" buttonId="PD14112404.SAVE" buttonType="button"/>
    </div>
</div>
<script src="${ctx}/static/js/pd/PD14112404.js"></script>