<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/comm/taglib.jsp"%>
<navigation:header title="产品批次管理" backTitleArray=" " backUrlArray=" "></navigation:header>
<div class="page-content list-page">

    <form action="${ctx}/SC182203/search" method="post">
        <table id="SC182203_Grid">
            <thead>
            <tr>
                <th coltype="text" name="halfPeriod" filter="true" width="500px">半旬期</th>
                <th coltype="select" width="" name="lgcsName" style="min-width: 80px;" filter="true">销售区域
                    <select>
                        <option value="上海">上海</option>
                        <option value="无锡">无锡</option>
                        <option value="南京">南京</option>
                        <option value="杭州">杭州</option>
                        <option value="宁波">宁波</option>
                        <option value="合肥">合肥</option>
                        <option value="广州">广州</option>
                        <option value="深圳">深圳</option>
                        <option value="福州">福州</option>
                        <option value="厦门">厦门</option>
                        <option value="南昌">南昌</option>
                        <option value="长沙">长沙</option>
                        <option value="武汉">武汉</option>
                        <option value="重庆">重庆</option>
                        <option value="成都">成都</option>
                        <option value="郑州">郑州</option>
                        <option value="西安">西安</option>
                        <option value="太原">太原</option>
                        <option value="北京">北京</option>
                        <option value="石家庄">石家庄</option>
                        <option value="济南">济南</option>
                        <option value="青岛">青岛</option>
                        <option value="沈阳">沈阳</option>
                        <option value="大连">大连</option>
                        <option value="长春">长春</option>
                        <option value="海尔滨">海尔滨</option>
                        <option value="其他">其他</option>
                    </select>
                </th>
                <th coltype="text" name="proLotNum" filter="true" width="500px">操作码</th>
                <th coltype="action" width="10px">
                    <%--<c:if test="${userType == 2}">
                        <input type="button" id="deleteBtn" hidden="true" title="删除" coltype="delete" class="h-button"/>
                    </c:if>--%>
                    <!--<input type="button" id="editBtn" hidden="true" title="打印明细" coltype="edit" class="h-button" />-->
                    <msk:button buttonType="hidden" buttonId="SC182203.editBtn" buttonValue="打印明细" coltype="edit" class="h-button" />
                </th>
            </tr>
            </thead>

            <tbody>
            </tbody>
        </table>

    </form>
</div>

<script src="${ctx}/static/ds/js/SC182203.js"></script>