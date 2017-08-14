<%--
    Title:配置管理画面
    author:zhao_chen1
    createDate:2016-06-14
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<%@ taglib prefix="msk2" uri="/codemaster" %>
<msk2:codemaster codeType="SettingType" viewType="json"/>
<navigation:header title="买家产品池配置管理" backTitleArray="" backUrlArray=""></navigation:header>
<div class="page-content list-page">

    <div class="group-accordion" collapsible="true" active="true">
        <h3>
            <label>新增配置</label>
        </h3>
        <table width="100%">
            <tr>
                <td align="right">配置类型</td>
                <td>
                    <select width="25px" name="settingType" id="settingType2">
                        <c:forEach items="${settingTypeMap}" var="settingType">
                            <option value="${settingType.key}">${settingType.value}</option>
                        </c:forEach>
                    </select>
                </td>
                <td></td>
                <td width="10%"></td>
                <td></td>
                <td></td>
            </tr>
            <tr>
                <td align="right">配置名称</td>
                <td>
                    <input type="text" id="settingName" name="settingName"/>
                </td>
                <td align="right">配置值</td>
                <td>
                    <input type="text" id="settingValue" name="settingValue"/>
                </td>
                <td></td>
                <td></td>
            </tr>
            <tr>
                <td align="right">配置开始值</td>
                <td>
                    <input type="text" id="settingStartValue" name="settingStartValue"/>
                </td>
                <td align="right">配置结束值</td>
                <td>
                    <input type="text" id="settingEndValue" name="settingEndValue"/>
                </td>
            </tr>
            <tr>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td>
                    <msk:button buttonValue="新增" buttonId="BR121402.ADD" buttonType="button"/></td>
                <td></td>
            </tr>

        </table>
    </div>
    <form action="${ctx}/BR121402/search" id="BR121402Form" method="post">
        <div>
            <table width="100%" id="BR121402_list_grid">
                <thead>
                <tr>
                <th coltype="sno" filter="false">编号</th>
                <th coltype="code" filter="true" width="20%" name="settingType" code2name="SETTINGTYPE_JSON">配置类型</th>
                <th coltype="text" filter="false" width="20%" name="settingName" edit="true">配置名称</th>
                <th coltype="text" filter="false" width="15%" name="settingValue" edit="true">配置值</th>
                <th coltype="text" filter="false" width="15%" name="settingStartValue" edit="true">配置开始值</th>
                <th coltype="text" filter="false" width="15%" name="settingEndValue" edit="true">配置结束值</th>
                <th coltype="action">操作
                    <msk:button buttonValue="保存" buttonId="BR121402.SAVE" buttonType="hidden" coltype="save"
                                class="h-button"/>
                    <msk:button buttonValue="删除" buttonId="BR121402.EDIT" buttonType="hidden" coltype="delete"
                                class="h-button"/>
                </th>
                </tr>
                </thead>
                <tbody id="BR121402Tbody">
                </tbody>
            </table>
        </div>
    </form>
</div>

<script src="${ctx}/static/br/js/BR121402.js"></script>

