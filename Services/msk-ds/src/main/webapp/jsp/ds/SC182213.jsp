<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/comm/taglib.jsp" %>
<navigation:header title="查询所有批次流水号" backTitleArray="产品批次入库及标签打印" backUrlArray="${ctx}/SC182211/init/"></navigation:header>
<div class="page-content list-page">
    <form action="${ctx}/SC182213/search" method="post" id="sc182213FormId">
        <%--<input type="hidden" name="filterMap[hideSlMainClass]" value="1">--%>
        <div>
            <table id="sc182213_list_grid" showAddBtn="true" width="100%">
                <thead>
                <tr>
                    <th coltype="text"  name="lotId" filter="false" >批次ID</th>
                    <th coltype="text"  name="serialId" filter="false">流水ID</th>
                    <th coltype="text"  name="lotCode" filter="false">阅读码</th>
                    <th coltype="text"  name="readCode" filter="false">批次码</th>
                    <th coltype="text"  name="crtTime" filter="false">插入时间</th>
                    <th coltype="action" >
                        <%--<input type="hidden" value="删除" coltype="delete" title="删除该批次标签" class="h-button" />--%>
                        <msk:button buttonType="hidden" buttonValue="删除该批次标签" coltype="delete" class="h-button" buttonId="SC182213.DELETE" />
                    </th>
                </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
        </div>
    </form>
</div>

<script type="text/javascript" src="${ctx}/static/ds/js/SC182213.js"></script>
