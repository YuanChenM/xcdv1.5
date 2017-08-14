<%--
  Created by IntelliJ IDEA.
  User: yuan_chen
  Date: 16/2/22
  Time: 上午10:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/comm/taglib.jsp" %>
<navigation:header title="区(县)列表" backTitleArray="" backUrlArray=""></navigation:header>
<div class="page-content list-page">
    <form action="${ctx}/BY121105/search/${cityId}" method="post">
        <table id="BY121105_Grid">
            <thead>
            <tr>
                <th coltype="sno" width="20px">编号</th>
                <th coltype="text" name="districtCode" edit="false" filter=true>区(县)编号</th>
                <th coltype="text" name="districtName" edit="false" filter=true>区(县)名称</th>
                <th coltype="text" name="spell" edit="true" filter=true>拼音拼写</th>
                <th coltype="text" name="shortSpell" edit="true" filter=true>拼音缩写</th>
                <th coltype="text" name="shortCode" filter=true>短编码</th>
                <th coltype="text" name="shortName" filter=true>短名称</th>
                <th coltype="text" name="fullCodeP" filter=true>全编码(省)</th>
                <th coltype="text" name="fullNameP" filter=true>全名称(省)</th>
                <th coltype="text" name="fullCodeL" filter=true>全编码(物)</th>
                <th coltype="text" name="fullNameL" filter=true>全名称(物)</th>
                <th coltype="select" name="delFlg" filter=true>是否废除
                    <select>
                        <option value="0">否</option>
                        <option value="1">是</option>
                    </select>
                </th>
                <th coltype="text" name="updId">最后更新人</th>
                <th coltype="datetime" name="updTime">最后更新时间</th>
                <th coltype="action">
                    <input type="hidden"coltype="add" title="添加" class="h-button" useable="can_add"/>
                    <input type="hidden"coltype="save" title="保存" class="h-button" />
                    <input type="hidden"coltype="delete" title="废除" class="h-button" useable="can_abolish"/>
                    <input type="hidden"coltype="check" title="恢复" class="h-button" useable="can_restore"/>
                </th>
            </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
    </form>
</div>
<script type="text/javascript" src="${ctx}/static/district/js/BY121105.js"></script>
