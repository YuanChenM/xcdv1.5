<%-- 
    Title:分销章程预览
    author:gyh
    createDate:2015-12-18
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>

<style type="text/css">
    .edui-default .edui-editor {
        margin: 0px auto;
        text-align: center;
        border: none;
    }
</style>
<c:if test="${chapClass eq 1}">
    <navigation:header title="分销章程预览" backTitleArray="分销章程列表" backUrlArray="${ctx}/SL241109/init/1"></navigation:header>
</c:if>
<c:if test="${chapClass eq 2}">
    <navigation:header title="卖家协议预览" backTitleArray="分销章程列表" backUrlArray="${ctx}/SL241109/init/2"></navigation:header>
</c:if>
<script type="text/javascript" src="${ctx}/static/js/ueditor/ueditor.config.js"></script>
<script type="text/javascript" src="${ctx}/static/js/ueditor/ueditor.all.js"></script>
<script type="text/javascript">
    var count = '${info.size()}';
    for (var i = 1; i <= count; i++) {
        var ue = UE.getEditor('content' + i, {
            initialFrameHeight: '100%',
            initialFrameWidth: '60%',
            elementPathEnabled: false,
            wordCount: false,
            readonly: true,
            toolbars: false,
            enableContextMenu: false
        });
    }
</script>
<
<div class="page-content list-page">
    <input type="hidden" id="infoSize" value="${info.size()}">
    <c:forEach items="${info}" var="bean" varStatus="i">
        <div class="group-accordion" collapsible="true" active="false" id="accordion${i.index+1}">
            <h3>
                <label>${bean.chapNo}、${bean.chapTitle}</label>
            </h3>
            <script id="content${i.index+1}" name="content" type="text/plain">
                ${bean.content}

            </script>
        </div>
    </c:forEach>
</div>
