<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<navigation:header title="菜场报告列表" backTitleArray="菜场定性定级列表" backUrlArray="${ctx}/BY121407/init/"></navigation:header>
<script type="text/javascript">
    function callbackFun(message){
        var marketId=$("#marketId").val();
        HDF.closeLoadingMask();
        $.alertMessage.info(message);

        $('#main-content').postUrl(Main.contextPath+"/BY121408/init/",{ marketId :marketId});
    }
</script>
<div class="page-content list-page">
    <div>
        <form:form action="${ctx}/BY121408/search/${marketId}" id="BY121408Form"
                   method="post" enctype="multipart/form-data">
            <input type="hidden" id="marketId" value="${marketId}"/>
            <div style="margin: auto">
                <table cellspacing="8" width="100%">
                    <tr>
                        <td align="right" width="8%">菜场名称 : </td>
                        <td width="12.5%">${foodBasicInfo.marketName}</td>
                        <td></td>
                    </tr>
                    <tr>
                        <td align="right">菜场地址 : </td>
                        <td>${foodBasicInfo.marketAddr}</td>
                        <td></td>
                    </tr>
                    <tr>
                        <td align="right">菜场区划 : </td>
                        <td>${name}</td>
                        <td></td>
                    </tr>
                </table>

            </div>
            <br/>
            <br/>
            <table id="BY121408_Grid">
                <thead>
                <tr>
                    <th coltype="sno" width="20px">序号</th>
                    <th coltype="text" name="fileName" filter=true>文件名</th>
                    <th coltype="text" name="crtId" filter=true>上传人</th>
                    <th coltype="date" name="crtTimeStr" filter=true width="15%">上传时间</th>
                    <th coltype="action">
                        <%--<msk:button buttonType="button" coltype="download" buttonValue="" buttonId="BY121408.DOWNLOAD" />--%>
                    </th>
                </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
        </form:form>
        <form:form action="${ctx}/BY121408/fileUpload/${marketId}/${loginUser.emplNo}" id="BY121408Upload"
                   method="post" enctype="multipart/form-data">
            <input type="hidden"  id="fileServerIp" nam="fileServerIp" value="${fileServerIp}">
            <div>
                <input type="hidden" id="emplNo"  value="${loginUser.emplNo}"/>
                <span><input type="file"  id = "testExcelFile" name = "testExcelFile" fileSize ="20" fileSizeMessage="文件不能大于20M" /></span>
                <span><msk:button buttonType="button" buttonValue="上传" buttonId="BY121408.UPLOAD" /></span>
            </div>
        </form:form>
    </div>
</div>
<script type="text/javascript" src="${ctx}/static/buyers/js/BY121408.js"></script>