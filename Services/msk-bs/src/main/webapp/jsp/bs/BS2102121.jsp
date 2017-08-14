<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<div class="group-accordion" active="true">
    <h3>
        <label>培训经历</label>
    </h3>

    <div id="" style="display:block">
        <form action="${ctx}/BS2102113/searchTrain" method="post" id="bs2102121FormId">
        <input type="hidden" id="slCode" name="slCode" value="${slCode}">
        <input type="hidden" id="houseCode" name="houseCode" value="${houseCode}">
            <table id="BS2102121_list_grid" style="width: 100%;">
                <thead>
                <tr style="text-align: left;">
                    <th coltype="text" name="trainTime">培训时间</th>
                    <th coltype="text" name="trainComp">培训机构</th>
                    <th coltype="text" name="trainCertificate">所获证书</th>
                    <th coltype="action">操作
                        <msk:button buttonValue="删除"  buttonType="hidden" coltype="delete" title="删除" class="h-button" buttonId="BS2102121.DELETE"/>
                        <msk:button buttonValue="编辑"  buttonType="hidden" coltype="edit" title="编辑" class="h-button" buttonId="BS2102121.EDIT"/>
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
                    <td> <msk:button buttonValue="新增" buttonId="BS2102121.ADD" buttonType="button"/></td>
                </tr>
            </table>
        </div>
    </div>
</div>
<script src="${ctx}/static/bs/js/BS2102121.js"></script>
