<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<div class="group-accordion" active="true">
    <h3>
        <label>教育经历</label>
    </h3>

    <div id="" style="display:block">
        <form action="${ctx}/BS2102113/searchEdu" method="post" id="bs2102120FormId">
        <input type="hidden" id="slCode" name="slCode" value="${slCode}">
        <input type="hidden" id="houseCode" name="houseCode" value="${houseCode}">
            <table id="BS2102120_list_grid" style="width: 100%;">
                <thead>
                <tr style="text-align: left;">
                    <th coltype="text" name="eduTime">学习时间</th>
                    <th coltype="text" name="eduComp">教育单位</th>
                    <th coltype="text" name="eduRecord">学历</th>
                    <th coltype="text" name="eduDegree">学位</th>
                    <th coltype="action">操作
                        <msk:button buttonValue="删除"  buttonType="hidden" coltype="delete" title="删除" class="h-button" buttonId="BS2102120.DELETE"/>
                        <msk:button buttonValue="编辑"  buttonType="hidden" coltype="edit" title="编辑" class="h-button" buttonId="BS2102120.EDIT"/>
                    </th>
                </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
        </form>
        <div>
            <table>
                <tr>
                    <td> <msk:button buttonValue="新增" buttonId="BS2102120.ADD" buttonType="button"/></td>
                </tr>
            </table>
        </div>
    </div>
</div>
<script src="${ctx}/static/bs/js/BS2102120.js"></script>
