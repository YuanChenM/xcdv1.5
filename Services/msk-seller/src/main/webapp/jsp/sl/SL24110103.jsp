<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/comm/taglib.jsp"%>
<%@ taglib prefix="msk2" uri="/msk" %>
<script type="text/javascript">
    var listSize="${slEpCertListsize}";
    function callbackFun(message){
        $.alertMessage.info(message);
        HDF.closeLoadingMask();
       // $('#main-content').postUrl(Main.contextPath + "/SL241101/init");
    }
</script>
<div class="group-accordion" active="false">
    <h3>
        <label>企业专业资质</label>
    </h3>
    <div>
        <div>
            <tr>
                <td align="center">
                    <msk2:button buttonType="button" buttonId="SL24110103.ADD" buttonValue="添加"/>
                    <%--<msk:button buttonValue="添加" buttonId="SL24110103.ADD" buttonType="button"/>--%>
                </td>
            </tr>
        </div>
            <msk2:roleArea pageCode="SL24110103" areaCode="SAVEBUTTON" needAuth="true">
            <c:forEach items="${slEpCertList}" var="slEpCert" varStatus="i">
                <div>
                    <form action="${ctx}/SL24110103/update" id="SL24110103Form${i.index}"  method="post" enctype="multipart/form-data">
                        <input type="hidden" id="jsp_epId2" name="jsp_epId2" value="${jsp_epId2}"/>
                        <table width="100%" border="0" cellpadding="0" cellspacing="0">
                            <c:forEach items="${slEpCert.beanList}" var="lis">
                            <tr>
                                <td align="right" width="50%">${slEpCert.certName}${lis.certItemName}：</td>
                                <td align="left" width="50%">
                                    <input type="hidden" name="certItemSeq" value="${lis.certItemSeq}"/>
                                    <input type="text" name="certItemValue" value="${lis.certItemValue}" maxlength="100"/>
                                </td>
                            </tr>
                            </c:forEach>
                            <tr>
                                <td>
                                    <input type="hidden" name="certSeq" value="${slEpCert.certSeq}">
                                    <input type="hidden" name="certId" value="${slEpCert.certId}">
                                </td>
                            </tr>
                            <tr>
                                <td align="right" width="50%">${slEpCert.certName}图片上传</td>
                                <td align="left" width="50%">
                                    <input type="file"  name="file" id="importFile"/>
                                </td>
                            </tr>
                            <tr>
                                <td align="right">
                                    <msk2:button buttonType="button" buttonId="SL24110103.UPDATE${i.index}" buttonValue="保存"/>
                                    <%--<msk:button buttonValue="保存" buttonId="SL24110103.UPDATE${i.index}" buttonType="button"/>--%>
                                </td>
                                <td align="left">
                                    <msk2:button buttonType="button" buttonId="SL24110103.DELETE${i.index}" buttonValue="删除"/>
                                    <%--<msk:button buttonValue="删除" buttonId="SL24110103.DELETE${i.index}" buttonType="button"/>--%>
                                </td>
                            </tr>
                        </table>
                    </form>
                </div>
            </c:forEach>
            </msk2:roleArea>
    </div>
</div>
<script src="${ctx}/static/sl/js/SL24110103.js"></script>