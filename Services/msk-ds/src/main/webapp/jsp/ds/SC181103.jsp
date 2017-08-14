<%--
    Title:供应商月度管控表（临时）
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/comm/taglib.jsp" %>
<%@ taglib prefix="msk2" uri="/codemaster" %>
<msk2:codemaster codeType="HalfCodeType" viewType="json"/>
<msk2:codemaster codeType="BrandType" viewType="json"/>
<msk2:codemaster codeType="GradeCode" viewType="json"/>
<navigation:header title="发货计划单录入" backTitleArray="" backUrlArray="${ctx}"></navigation:header>

<div class="page-content list-page">
    <form action="${ctx}/SC181103/search" id="SC181103Form" method="post">
        <div class="group-accordion" collapsible="true" active="true">
            <h3>
                <label>查询条件</label>
            </h3>
            <table WIDTH="100%">
                <tr>
                    <td width="10%" align="right">物流地区</td>
                    <td align="left">
                        <select name="lgcsCode" id="lgcsCode">
                            <%--<option value='-1'>--请选择--</option>--%>
                            <c:forEach items="${areaList}" var="areaInfo">
                                <c:choose>
                                    <c:when test="${lgcsCode eq areaInfo.lgcsAreaCode}">
                                        <option value="${areaInfo.lgcsAreaCode}" selected>${areaInfo.lgcsAreaName}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${areaInfo.lgcsAreaCode}">${areaInfo.lgcsAreaName}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </td>
                    <input type="hidden" name="lgcsName" id ="lgcsName" value="${lgcsName}"/>
                    <td width="8%" align="right">供应商</td>
                    <td align="left">
                        <select name="suppCode" id="suppCode">
                            <%--<option value='-1'>--请选择--</option>--%>
                            <c:forEach items="${suppList}" var="suppInfo">
                                <c:choose>
                                    <c:when test="${suppCode eq suppInfo.suppCode}">
                                        <option value="${suppInfo.suppCode}" selected>${suppInfo.suppName}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${suppInfo.suppCode}">${suppInfo.suppName}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </td>
                    <td width="10%" align="right">分销月度</td>
                    <td  align="left">　
                        <input type="text" id="distMonth" name="distMonth" value="${distMonth}" readonly="true"/>
                    </td>
                    <%--<td align="left"><msk:button buttonValue="查询" buttonId="SC181103.SEARCH" buttonType="button"/></td>--%>
                </tr>
            </table>
        </div>
        <div class="group-accordion" collapsible="true" active="true">
            <h3>
                <label>计划发货明细（按车次发货）</label>
            </h3>
            <div>
                <table class="dataTable no-footer" id="SC181103_list_grid" width="100%">
                    <thead>
                    <tr>
                        <th coltype="sno">序号</th>
                        <th coltype="text" width="10%" name="epName">生产商</th>

                        <th coltype="text" width="8%" name="pdCode">产品编码</th>
                        <th coltype="text" width="8%" name="classesName">分类</th>
                        <th coltype="text" width="8%" name="breedName" filter="true">品种</th>

                        <th coltype="text" width="8%" name="featureName" filter="true">特征</th>

                        <%--<th coltype="select" width="9%" name="gradeName" cellEditMode="true" edit="true" selectValue="gradeCode">等级--%>
                            <%--<select>--%>
                                <%--<c:forEach items="${pdGradeList}" var="pdGrade">--%>
                                    <%--<option value="${pdGrade.gradeCode}">${pdGrade.gradeName}</option>--%>
                                <%--</c:forEach>--%>
                            <%--</select>--%>
                        <%--</th>--%>
                        <th coltype="code"  width="7%" name="gradeCode" code2name="GRADECODE_JSON">产品等级</th>
                        <th coltype="code" width="8%" name="brandType" code2name="BRANDTYPE_JSON" filter="true">品牌类型</th>
                        <th coltype="text" width="7%" name="outSpec">外包装规格</th>
                        <th coltype="number" width="8%" name="outNetWeight">外包装净重</th>
                        <th coltype="code" width="8%" name="halfCode" code2name="HALFCODETYPE_JSON" edit="true">半旬</th>
                        <th coltype="number" name="deliveryBoxes" cellEditMode="true" edit="true" width="10%"  maxlength="14">箱数</th>
                        <th coltype="money" accuracy="2" name="packNum" cellEditMode="false" edit="false" width="10%"  maxlength="18">数量(KG)</th>
                        <%--<th coltype="action" width="9%">操作--%>
                            <%--<msk:button buttonValue="保存" buttonId="SC181103.SAVE" buttonType="hidden" coltype="detail" class="h-button" title="保存"/>--%>
                        <%--</th>--%>
                    </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
                <msk:button buttonValue="保存" buttonId="SC181103.SAVE" buttonType="button" title="保存"/>
            </div>
        </div>

    </form>
</div>
<script src="${ctx}/static/ds/js/SC181103.js"></script>
