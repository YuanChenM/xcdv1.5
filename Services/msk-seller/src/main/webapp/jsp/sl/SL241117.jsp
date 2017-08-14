<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
    Title:卖家产品加工质量标准设置
    author:gyh
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<%@ taglib prefix="msk2" uri="/msk" %>

<link rel="stylesheet" href="${ctx}/static/js/treegrid/css/jquery.treegrid.css">
<script type="text/javascript" src="${ctx}/static/js/treegrid/js/jquery.treegrid.js"></script>

<form action="${ctx}/SL241117/save" method="post" id="SL241117Form">
    <input type="hidden" name="slCode" id="slCode" value="${sl241116Bean.slCode}"/>
    <input type="hidden" name="slPdId" value="${sl241116Bean.slPdId}"/>
    <input type="hidden" name="slShowName" value="${sl241116Bean.slShowName}"/>
    <input type="hidden" name="prodEpId" value="${sl241116Bean.prodEpId}"/>
    <input type="hidden" name="brandEpId" value="${sl241116Bean.brandEpId}"/>
    <input type="hidden" name="brandId" value="${sl241116Bean.brandId}"/>
    <input type="hidden" name="pdClassesCode" value="${sl241116Bean.pdClassesCode}"/>
    <input type="hidden" name="machiningCode" value="${sl241116Bean.machiningCode}"/>
    <input type="hidden" name="pdBreedCode" value="${sl241116Bean.pdBreedCode}"/>
    <navigation:header
            title="加工质量标准档案卡"
            backTitleArray="卖家信息列表,卖家产品信息维护"
            backUrlArray="${ctx}/SL241101/initShow,${ctx}/SL241116/init/${sl241116Bean.slCode}"></navigation:header>
    <div class="page-content list-page">
        <div class="group-accordion" collapsible="false" active="false">
            <h3>
                <label>加工质量标准录入</label>
            </h3>
            <table class="tree dataTable no-footer" style="width: 100%">
                <tr style="background:#DBDBDB">
                    <td width="25%"></td>
                    <td width="10%">是否同意</td>
                    <td>A1</td>
                    <td>A2</td>
                    <td>A3</td>
                    <td>卖家加工质量标准</td>
                </tr>
                <tr id="tr_1">
                    <td width="25%"></td>
                    <td width="10%"></td>
                    <c:choose>
                        <c:when test="${sl241116Bean.slTncGradeCode eq '2'}">
                            <td><input type="radio" name="agreeFlg" value="1"/>A1</td>
                            <td><input type="radio" checked="checked" name="agreeFlg" value="2"/>A2</td>
                            <td><input type="radio" name="agreeFlg" value="3"/>A3</td>
                            <td></td>
                        </c:when>
                        <c:when test="${sl241116Bean.slTncGradeCode eq '3'}">
                            <td><input type="radio" name="agreeFlg" value="1"/>A1</td>
                            <td><input type="radio" name="agreeFlg" value="2"/>A2</td>
                            <td><input type="radio" checked="checked" name="agreeFlg" value="3"/>A3</td>
                            <td></td>
                        </c:when>
                        <c:when test="${sl241116Bean.slTncGradeCode eq '4'}">
                            <td><input type="radio" name="agreeFlg" value="1"/>A1</td>
                            <td><input type="radio" name="agreeFlg" value="2"/>A2</td>
                            <td><input type="radio" name="agreeFlg" value="3"/>A3</td>
                            <td>不通过</td>
                        </c:when>
                        <c:when test="${sl241116Bean.slTncGradeCode eq '5'}">
                            <td><input type="radio" name="agreeFlg" value="1"/>A1</td>
                            <td><input type="radio" name="agreeFlg" value="2"/>A2</td>
                            <td><input type="radio" name="agreeFlg" value="3"/>A3</td>
                            <td>论证中</td>
                        </c:when>
                        <c:otherwise>
                            <td><input type="radio" checked="checked" name="agreeFlg" value="1"/>A1</td>
                            <td><input type="radio" name="agreeFlg" value="2"/>A2</td>
                            <td><input type="radio" name="agreeFlg" value="3"/>A3</td>
                            <td></td>
                        </c:otherwise>
                    </c:choose>
                </tr>
                <c:forEach items="${sl241117Beans}" var="pdTncTd" varStatus="j">
                    <input type="hidden" name="standardId" id="standardId" value="${pdTncTd.standardId}"/>
                    <tr>
                        <td width="25%">${pdTncTd.tncStdItemName}
                            <input type="hidden" name="pdTncStdItemIdArray" value="${pdTncTd.tncStdItemId}"/>
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${pdTncTd.agreeFlg eq '0'}">
                                    <input type="checkbox" isCheck="${j.index+1}" name="checkArray"
                                           value="0" checked="checked"/>不同意
                                </c:when>
                                <c:otherwise>
                                    <input type="checkbox" isCheck="${j.index+1}" name="checkArray"
                                           value="0"/>不同意
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td style="white-space:pre;">${pdTncTd.tncStdVal1}</td>
                        <td style="white-space:pre;">${pdTncTd.tncStdVal2}</td>
                        <td style="white-space:pre;">${pdTncTd.tncStdVal3}</td>
                        <td>
                            <textarea name="contentArray" maxlength="400" rows="3" id="input_${j.index+1}" <c:if test="${pdTncTd.agreeFlg != '0'}">readonly="readonly"</c:if>>${pdTncTd.stdVal}</textarea>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <div>
            <span>
                <msk2:button buttonType="button" buttonId="SL241117.SAVE" buttonValue="保存"/>
                <%--<msk:button buttonValue="保存" buttonId="SL241117.SAVE" buttonType="button"/>--%>
            </span>
        </div>
    </div>
</form>
<script type="text/javascript" src="${ctx}/static/sl/js/SL241117.js"></script>