<%-- 
    Title:实际数据档案卡列表
    author:jiang_nan
    createDate:2015-12-10
    updateDate:2015-12-10
    updateAuthor:jiang_nan
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/comm/taglib.jsp" %>
<div class="page-header">
    <span class="page-title"> <label>产品实际数据档案卡列表</label>
    </span>
</div>
<div class="page-content list-page">
    <form action="${ctx}/PD141115/search" method="post">
    <input type="hidden" name="breedCode" value="${param.breedCode}"/>
    <input type="hidden" name="classesCode" value="${param.classesCode}"/>
        <table id="PD141115_Grid">
            <thead>
                <tr>
                    <th coltype="sno" width="20px">编号</th>
                    <th coltype="text" name="sellerName" align="center">卖家名称</th>
                    <th coltype="text" name="classesCode" align="center">类型编号</th>
                    <th coltype="text" name="classesName" align="center">类型名称</th>
                    <th coltype="text" name="breedCode" align="center">品种编号</th>
                    <th coltype="text" name="breedName" align="center">品种名称</th>
                    <th coltype="text" name="pdCode" align="center">产品编号</th>
                    <th coltype="text" name="pdBatchCode" align="center">产品批次</th>
                    <th coltype="action">
                        <msk:button buttonValue="录入质量标准" buttonType="hidden" coltype="check" class="h-button" buttonId="PD141115.CHECK"/>
                        <msk:button buttonValue="录入技术标准" buttonType="hidden" coltype="edit" class="h-button" buttonId="PD141115.EDIT"/>
                    </th>
                </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
    </form>
    <div>
        <c:if test="${not empty param.breedCode and not empty param.classesCode}">
            <msk:button buttonValue="新建质量信息" buttonId="PD141115.ADDQUALITYINFO" url=""/>
            <msk:button buttonValue="新建技术信息" buttonId="PD141115.ADDTECHNICALINFO" url=""/>
        </c:if>
    
    </div>
</div>
<script type="text/javascript" src="${ctx}/static/js/pd/PD141115.js"></script>