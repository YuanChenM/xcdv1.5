<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/comm/taglib.jsp" %>

<%@ taglib prefix="msk2" uri="/codemaster" %>
<msk2:codemaster codeType="GradeCode" viewType="json" />
<msk2:codemaster codeType="Role" viewType="json" />

<navigation:header title="数量申报历史" backTitleArray="" backUrlArray="${ctx}"></navigation:header>
<div class="page-content list-page">
    <form:form action="${ctx}/SP171108/search" id="SP171108Form" metdod="post">
        <input name="filterMap['demandId']" type="hidden" value="${demandId}" id="demandId" />
        <input name="filterMap['type']" type="hidden" value="${type}" id="type" />
        <input name="loginId" type="hidden" id="loginId" value="${loginId}"/>
        <div class="group-accordion" collapsible="" active="false">
            <h3>
                <label>数量申报历史</label>
            </h3>
            <table WIDTH="100%" border="0">
                <tr>
                    <td width="10" align="left">物流区名称:</td>
                    <td width="35%" align="left">${lgcsName}</td>
                    <td width="75%" colspan="4"><td/>
                </tr>
                <tr>
                    <td width="10%" align="left">期数时间 :</td>
                    <td width="35%" align="left">${demandYearmonth}</td>
                    <td width="10%" align="left">填报时间 :</td>
                    <td width="25%" align="left">${fillTime}</td>
                    <td width="30%" colspan="2"><td/>
                </tr>
                <tr>
                    <td width="10%" align="left">品名 :</td>
                    <td width="35%" align="left">${pdName}</td>
                    <td width="10%" align="left">一级分类 :</td>
                    <td width="25%" align="left">${classesName}</td>
                    <td width="10%" align="left">二级分类 :</td>
                    <td width="20%" align="left">${machiningName}</td>
                </tr>
                <tr>
                    <td width="10%" align="left">产品编码 :</td>
                    <td width="35%" align="left">${pdCode}</td>
                    <td width="10%" align="left">产品等级 :</td>
                    <td coltype="text" width="25%" align="left">
                        <msk2:codemaster codeType="GradeCode" viewType="label" codeValue="${gradeCode}" />
                    </td>
                    <td width="30%" colspan="2"><td/>
                </tr>
                <tr>
                    <td width="10%" align="left">供应商：</td>
                    <td width="35%" align="left">${slName}</td>
                    <td width="75%" colspan="4"><td/>
                </tr>
            </table>
        </div>
        <div>
            <table id="SP171108_list_grid">
                <thead>
                    <tr>
                        <th coltype="sno" width="40px">序号</th>
                        <input type="hidden" name="demandDetailId">
                        <th coltype="text" width="10%" name="editTime">编辑时间</th>
                        <th coltype="code" width="10%" name="roleCode" code2name="ROLE_JSON">编辑人</th>
                        <th coltype="code" width="10%" name="gradeCode" code2name="GRADECODE_JSON">申报等级</th>
                        <th coltype="text" width="10%" name="modifyNum">申报或建议数量</th>
                        <th coltype="text" width="30px" name="remark">备注</th>
                    </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
        </div>
    </form:form>
</div>
<script type="text/javascript" src="${ctx}/static/sp/js/SP171108.js"></script>

