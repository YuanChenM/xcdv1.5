<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/comm/taglib.jsp"%>
<%@ taglib prefix="msk2" uri="/msk" %>
<script type="text/javascript">
    function callbackFun(message){
        $.alertMessage.info(message);
        HDF.closeLoadingMask();
       // $('#main-content').postUrl(Main.contextPath + "/SL24110300301/init");
    }
</script>
    <form action="${ctx}/SL24110300301/save" id="SL24110300301Form"
               method="post" enctype="multipart/form-data">

        <input type="hidden" id="crtId" name="crtId" value="${loginUser.emplId}"/>
        <input type="hidden" id="updId" name="updId" value="${loginUser.emplId}"/>
        <input type="hidden" id="jsp_epId" name="jsp_epId" value="${jsp_epId}"/>
        <div>
            <table width="100%">
                <c:forEach items="${slMstCertItems}" var="slMstCertItem">
                    <tr>
                        <td width="50px" align="right">${slMstCertItem.certItemName}</td>
                        <td width="50px" align="left">
                            <input type="text" name="certItemValue" maxlength="100" />
                        </td>
                    </tr>
                </c:forEach>
                <tr>
                    <td align="right" width="50%">图片上传</td>
                    <td align="left" width="50%">
                        <input type="file" name="file" id="importFile" />
                    </td>
                </tr>
                <tr>
                    <td align="right" width="50%">
                        <msk2:button buttonType="button" buttonId="SL24110300301.SAVE" buttonValue="保存"/>
                        <%--<msk:button buttonValue="保存" buttonId="SL24110300301.SAVE" buttonType="button"/>--%>
                    </td>
                    <td align="left" width="50%">
                        <msk2:button buttonType="button" buttonId="SL24110300301.BACK" buttonValue="取消"/>
                        <%--<msk:button buttonValue="取消" buttonId="SL24110300301.BACK" buttonType="button"/>--%>
                    </td>
                </tr>
            </table>
        </div>
    </form>
<script src="${ctx}/static/sl/js/SL24110300301.js"></script>
