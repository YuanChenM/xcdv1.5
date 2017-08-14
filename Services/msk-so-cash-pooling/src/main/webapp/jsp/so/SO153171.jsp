<%--
    账期设置一览
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/comm/taglib.jsp" %>
<%@ taglib prefix="msk" uri="/codemaster" %>
<msk:codemaster codeType="RoleType" viewType="json" />

<navigation:header title="账期设置" backTitleArray="" backUrlArray="${ctx}"></navigation:header>
<script type="text/javascript">
    function callBack(message){
        $.alertMessage.info(message);
        HDF.closeLoadingMask();
        //$('#main-content').postUrl(Main.contextPath + "/SL24110100/init");
    }

</script>

<div class="page-content list-page">
    <table width="100%">
        <tr><td>
    <form action="${ctx}/SO153171/upload" id="SO153171UploadForm"   method="post" enctype="multipart/form-data">
        <div class="group-accordion" collapsible="true" active="true">
            <h3>
                <label>上传文件</label>
            </h3>
        <table WIDTH="100%">
            <tr>
                <td  align="right">选择文件：</td>
                <td  align="left" colspan="6">　
                    <%--改善bug1968 modify by renyi on 2016/8/25 start--%>
                    <input type="hidden" id="userId" value="${loginUser.emplId}"/>
                    <%--改善bug1968 modify by renyi on 2016/8/25 end--%>
                    <input type="file" name="file" id="importFile" style="width:180px"/>
                    <msk2:button buttonValue="导入" buttonId="SO153171.UPLOAD" buttonType="button"/>
                    <msk2:button buttonValue="导出模板" buttonId="SO153171.EXPORT" buttonType="button"/>
                </td>
          </tr>
            </table>
        </div>
    </form>
    </td></tr>
    <tr><td>
    <form action="${ctx}/SO153171/search" id="SO153171Form" method="post">
        <div class="group-accordion" collapsible="true" active="true">
            <h3>
                <label>查询条件</label>
            </h3>
            <table WIDTH="100%">
                <tr>

                    <td  align="right">上个结束日 :</td>
                    <td  align="left" colspan="6">　
                        <input type="text" id="lastPeriodEndStart" name="filterMap['lastPeriodEndStart']" value="${param.filterMap.lastPeriodEndStart}"/>
                        &emsp;~&emsp;
                        <input type="text" id="lastPeriodEndEnd" name="filterMap['lastPeriodEndEnd']" value="${param.filterMap.lastPeriodEndEnd}"/>
                    </td>
                 </tr>
                <tr>
                    <td  align="right">账期周期(天) :</td>
                    <td  align="left" colspan="2">　
                        <input type="text" id="periodStart" name="filterMap['periodStart']" value="${param.filterMap.periodStart}" maxlength="11"/>
                        &emsp;~&emsp;
                        <input type="text" id="periodEnd" name="filterMap['periodEnd']" value="${param.filterMap.periodEnd}" maxlength="11"/>
                    </td>
                    <td  align="right">启用日期 :</td>
                    <td  align="left" colspan="3">　
                        <input type="text" id="commDateStart" name="filterMap['commDateStart']" value="${param.filterMap.commDateStart}"/>
                        &emsp;~&emsp;
                        <input type="text" id="commDateEnd" name="filterMap['commDateEnd']" value="${param.filterMap.commDateEnd}"/>
                    </td>
                </tr>
            </table>
        </div>
        <div>
            <table id="SO153171_list_grid" WIDTH="100%">
                <thead>
                <tr>
                    <%--<th coltype="checkbox"></th>--%>
                    <th coltype="sno" width="10%">序号</th>
                    <th coltype="text" name="userNo" filter="true" width="15%">用户编号</th>
                    <th coltype="code" width="10%" filter="true" name="userRole" code2name="ROLETYPE_JSON">用户角色</th>
                    <th coltype="text" name="userName" filter="true" width="15%">用户名称</th>
                    <th coltype="date" name="commDate" width="15%">启用日期</th>
                    <th coltype="date" name="lastPeriodEnd" width="15%">上个结束日</th>
                    <th coltype="text" name="period" width="20%">账期周期(天)</th>
                </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
        </div>
    </form></td></tr>
    </table>
</div>
<input type="hidden" id="printUrl" value="/excel/async/print/start">

<script src="${ctx}/static/so/js/SO153171.js"></script>
<script type="text/javascript" src="${ctx}/static/js/core/utils.js"></script>
<script type="text/javascript" src="${ctx}/static/js/loading/download.js"></script>
<%--改善bug1968 modify by renyi on 2016/8/25 start--%>
<script type="text/javascript" src='<c:url value="/static/upload/upload2.js"/>'/>
<script type="text/javascript" src='<c:url value="/static/js/core/utils.js"/>'/>
<%--改善bug1968 modify by renyi on 2016/8/25 end--%>


