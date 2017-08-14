<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<navigation:header title="批发市场报告列表" backTitleArray="批发市场定性定级列表"
                   backUrlArray="${ctx}/BY121401/init/"></navigation:header>
<script type="text/javascript">
    function callbackFun(message) {
        var marketId = $("#marketId").val();
        HDF.closeLoadingMask();
        $.alertMessage.info(message);

        $('#main-content').postUrl(Main.contextPath + "/BY121402/init/", {marketId: marketId});
    }
</script>
<div class="page-content list-page">
    <div>

        <form:form action="${ctx}/BY121402/search/${marketId}" id="BY121402Form"
                   method="post" enctype="multipart/form-data">
            <input hidden="hidden" id="fileServiceIp" value="${fileServiceIp}">
            <input type="hidden" id="marketId" value="${marketId}"/>

            <div style="margin: auto">
                <table cellspacing="8" width="100%">
                    <tr>
                        <td align="right" width="8%">批发市场名称 : </td>
                        <td  width="12.5%">${terminalBasicInfo.marketName}</td>
                        <td></td>
                    </tr>
                    <tr>
                        <td align="right">批发市场地址 : </td>
                        <td>${terminalBasicInfo.marketAddr}</td>
                        <td></td>
                    </tr>
                    <tr>
                        <td align="right">物流区划 : </td>
                        <td>${name}</td>
                        <td></td>
                    </tr>
                </table>
            </div>
            <br/>
            <br/>
            <table id="BY121402_Grid">
                <thead>
                <tr>
                    <th coltype="sno" width="10%">序号</th>
                    <th coltype="text" name="fileName"  filter=true>文件名</th>
                    <th coltype="text" name="crtId"  filter=true>上传人</th>
                    <th coltype="date" name="crtTimeStr" width="15%" filter=true>上传时间</th>
                    <th coltype="action">

                    </th>
                </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
        </form:form>
        <form:form action="${ctx}/BY121402/fileUpload/${marketId}/${loginUser.emplNo}" id="BY121402Upload" method="post"
                   enctype="multipart/form-data">
            <div>
                <input type="hidden" id="userId" value="${loginUser.emplId}"/>
                <input type="hidden" id="userName" value="${loginUser.emplName}"/>
                <input type="hidden" id="emplNo" value="${loginUser.emplNo}"/>
                <span><input type="file" id="testExcelFile" name="testExcelFile" fileSize="20"
                             fileSizeMessage="文件不能大于20M"/></span>
                <span><msk:button buttonType="button" buttonValue="上传" buttonId="BY121402.UPLOAD"/></span>
            </div>
        </form:form>
    </div>
</div>
<script type="text/javascript" src="${ctx}/static/buyers/js/BY121402.js"></script>