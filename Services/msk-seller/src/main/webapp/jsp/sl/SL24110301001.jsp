<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
    <div class="page-content detail-page" id="showEp">
        <form:form action="${ctx}/SL24110301001/search" id="SL24110301001Form"
               method="post">
            <input type="hidden" id="flg" value="${flg}"/>
        <table width="100%" border="0" cellpadding="0" cellspacing="0" id="SL24110301001Grid">
            <thead>
            <tr>
                <th coltype="radio" width="7%" name="breedName" filter="false" onclick=""></th>
                <th coltype="text" width="7%" name="epId" filter="false">企业ID</th>
                <th coltype="text" width="7%" name="epName" filter="true">企业名称</th>
                <th coltype="text" width="7%" name="slMainClass" filter="false">企业类型</th>
            </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
    </form:form>
    </div>
</div>
<script src="${ctx}/static/sl/js/SL24110301001.js"></script>
