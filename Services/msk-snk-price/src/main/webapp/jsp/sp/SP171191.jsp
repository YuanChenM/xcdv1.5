<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<%@ taglib prefix="msk2" uri="/codemaster" %>
<msk2:codemaster codeType="GradeCode" viewType="json" />
<msk2:codemaster codeType="Untis" viewType="json"/>
<navigation:header title="物流区产品等级信息管理" backTitleArray="" backUrlArray=""></navigation:header>
<div class="page-content list-page">
    <form:form action="${ctx}/SP171191/search" id="SP171191Form" metdod="post">
        <div class="group-accordion" collapsible="" active="false" style="min-width: 1425px">
            <h3>
                <label>批量设置</label>
            </h3>
            <table WIDTH="100%" border="0">
                <tr>
                    <td align="left" width="6%">需求等级</td>
                    <td align="left" width="5%"> <select width="25px"name="wayCode">
                        <c:forEach items="${wayList}" var="way"  varStatus="status">
                            <option value="${way.wayCode}">${way.wayName}</option>
                        </c:forEach>
                    </select></td>
                    <td align="left" width="10%"> <msk:button buttonType="button" buttonId="SP171191.SAVEWAY" buttonValue="设置需求等级"/></td>
                    <td align="left">&nbsp;</td>
                    <td align="left" width="6%">营销状态</td>
                    <td align="left" width="3%">
                        <input type="text" width="100%" name="MarketingName" id="MarketingName"/>
                    </td>
                    <td align="left" width="10%"> <msk:button buttonType="button" buttonId="SP171191.SAVEMARKET" buttonValue="设置营销状态"/></td>
                    <td align="left">&nbsp;</td>
                </tr>
            </table>
        </div>
        <table id="SP171191_Grid">
            <thead>
            <tr>
                <th coltype="sno"  width="5%">序号</th>

                <th coltype="select"  filter="true" name="lgcsName" width="12%" align="center" style="padding-right: 10px; padding-left: 10px;">区域
                    <select  name="filterMap['lgcsCode']"  id="districtName" style="width: 100%">
                        <c:forEach items="${lgcsinfo}" var="lgcs" varStatus="status">
                            <option value="${lgcs.lgcsAreaCode}">${lgcs.lgcsAreaName}</option>
                        </c:forEach>
                    </select>
                </th>
                <th coltype="text" name="pdCode"  filter="true"  width="10%">产品编码</th>
                <th coltype="text" name="pdName"  filter="true" width="10%" >产品名称</th>
                <th coltype="code" filter="true" name="pdGrage" code2name="GRADECODE_JSON" width="10%">等级</th>
                <th coltype="text" edit="true" name="minVal" width="10%" >最小单位数</th>
                <th coltype="code" width="7%" cellEdit="true" name="units" edit="true"  filter="true" code2name="UNTIS_JSON" >单位
                </th>
                <th coltype="text"  name="wayCode"  filter="true"   width="10%" >需求等级编码</th>
                <th coltype="text" name="wayName"   filter="true"  width="10%">需求等级</th>
                <th coltype="text" width="8%" edit="true" cellEdit="true" name="marketingName" filter="true">营销状态
                </th>
                <th coltype="action" width="10%">操作
                    <msk:button buttonType="hidden" buttonId="SP171191.SELECT" buttonValue="选择需求等级" coltype="add"/>
                </th>
            </tr>
            </thead>

            <tbody>
            </tbody>
        </table>
    </form:form>
    <input type="hidden" id="rowId">
    <msk:button buttonType="button" buttonId="SP171191.CONFIRM" buttonValue="保存"/>
    <msk:button buttonType="button" buttonId="SP171191.LGCSPRODUCT" buttonValue="物流产品同步"/>
</div>

<script src="${ctx}/static/sp/js/SP171191.js"></script>
