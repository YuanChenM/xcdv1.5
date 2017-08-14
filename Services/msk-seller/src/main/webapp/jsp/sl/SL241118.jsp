<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
    Title:卖家产品加工技术标准指标设置
    author:gyh
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<%@ taglib prefix="msk2" uri="/msk" %>
<link rel="stylesheet" href="${ctx}/static/js/treegrid/css/jquery.treegrid.css">
<script type="text/javascript" src="${ctx}/static/js/treegrid/js/jquery.treegrid.js"></script>
<form action="${ctx}/SL241118/save" method="post" id="SL241118Form">
    <input type="hidden" name="slCode" id="slCode" value="${sl241116Bean.slCode}"/>
    <input type="hidden" name="slPdId" value="${sl241116Bean.slPdId}"/>
    <input type="hidden" name="slShowName" value="${sl241116Bean.slShowName}"/>
    <navigation:header
            title="加工技术标准档案卡"
            backTitleArray="卖家信息列表,卖家产品信息维护"
            backUrlArray="${ctx}/SL241101/initShow,${ctx}/SL241116/init/${sl241116Bean.slCode}"></navigation:header>
    <div class="page-content list-page">
        <div class="group-accordion" collapsible="false" active="false">
            <h3>
                <label>加工技术标准录入</label>
            </h3>
            <table class="tree dataTable no-footer" style="width: 100%">
                <tr style="background:#DBDBDB">
                    <td width="25%"></td>
                    <td width="10%">是否同意</td>
                    <td>合格</td>
                    <td>不合格</td>
                    <td>卖家加工技术标准</td>
                </tr>
                <tr id="tr_1">
                    <td width="25%"></td>
                    <td width="10%"></td>
                    <c:choose>
                        <c:when test="${sl241116Bean.slQltGradeCode eq '3'}">
                            <td><input type="radio" name="agreeFlg" value="2"/>合格</td>
                            <td><input type="radio" checked="checked" name="agreeFlg" value="3"/>不合格</td>
                            <td></td>
                        </c:when>
                        <c:when test="${sl241116Bean.slQltGradeCode eq '5'}">
                            <td><input type="radio" name="agreeFlg" value="2"/>合格</td>
                            <td><input type="radio" name="agreeFlg" value="3"/>不合格</td>
                            <td>论证中</td>
                        </c:when>
                        <c:otherwise>
                            <td><input type="radio" name="agreeFlg" value="2" checked="checked"/>合格</td>
                            <td><input type="radio" name="agreeFlg" value="3"/>不合格</td>
                            <td></td>
                        </c:otherwise>
                    </c:choose>
                </tr>
                <c:forEach items="${sl241118Beans}" var="pdMctTd" varStatus="j">
                    <input type="hidden" name="standardId" id="standardId" value="${pdMctTd.standardId}"/>
                    <tr>
                        <td width="25%">${pdMctTd.mctStdItemName}
                            <input type="hidden" name="pdMctStdItemIdArray" value="${pdMctTd.mctStdItemId}"/>
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${pdMctTd.agreeFlg eq '0'}">
                                    <input type="checkbox" isCheck="${j.index+1}" name="checkArray"
                                           value="0" checked="checked"/>不同意
                                </c:when>
                                <c:otherwise>
                                    <input type="checkbox" isCheck="${j.index+1}" name="checkArray"
                                           value="0"/>不同意
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td style="white-space:pre;">${pdMctTd.mctOkVal}</td>
                        <td style="white-space:pre;">${pdMctTd.mctNgVal}</td>
                        <td>
                            <textarea name="contentArray" id="input_${j.index+1}" rows="3" maxlength="400"
                                      <c:if test="${pdMctTd.agreeFlg != '0'}">readonly="readonly"</c:if>>${pdMctTd.stdVal}</textarea>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <div>
            <span>
                <msk2:button buttonType="button" buttonId="SL241118.SAVE" buttonValue="保存"/>
                <%--<msk:button buttonValue="保存" buttonId="SL241118.SAVE" buttonType="button"/>--%>
            </span>
        </div>
    </div>
</form>
<script type="text/javascript" src="${ctx}/static/sl/js/SL241118.js"></script>