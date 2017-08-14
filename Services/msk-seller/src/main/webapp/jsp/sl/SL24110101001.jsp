<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<%@ taglib prefix="msk2" uri="/msk" %>
<script type="text/javascript">
    function callbackFun(message) {
        $.alertMessage.info(message);
        HDF.closeLoadingMask();
    }
</script>
<div class="page-content detail-page" active="false">
    <div id="ordinary" style="display:block">
        <form:form action="${ctx}/SL24110101001/save" id="SL24110101001Form"
                   method="post">
            <input type="hidden" id="crtId" name="crtId" value="${loginUser.emplId}"/>

            <p align="center"><input type="radio" value="1" name="markFlg" checked="checked">代理商<input type="radio"
                                                                                                       value="2"
                                                                                                       name="markFlg">OEM代理商
            </p>

            <div align="center"><a href="#" id="agentCheck" style="color:red">点击选择生产商</a></div>
            <table width="100%" border="0" cellpadding="0" cellspacing="0">
                <tr>
                    <td align="right" width="50%">生产商名称</td>
                    <td align="left" width="50%">
                        <input type="text" name="epName" id="epNameId" disabled="disabled" style="width:180px"/>
                        <input type="text" name="producerEpId" id="producerEpId" style="display:none"/>
                    </td>
                </tr>
                <tr>
                    <td align="right" width="50%">授权经销合同号</td>
                    <td align="left" width="50%">
                        <input type="text" name="contractNo" style="width:180px" maxlength="30" />
                    </td>
                </tr>
                <tr>
                    <td align="right" width="50%">授权单位</td>
                    <td align="left" width="50%">
                        <input type="text" name="authEpName" style="width:180px" maxlength="50"/>
                    </td>
                </tr>
                <tr>
                    <td align="right" width="50%">授权期限开始(必填)</td>
                    <td align="left" width="50%">
                        <input type="text" id="authTermBeginThree" style="width:180px"
                               readonly="readonly"/>
                        <input type="hidden" name="authTermBegin" id="authTermBeginThreeTemp"/>
                    </td>
                </tr>
                <tr>
                    <td align="right" width="50%">授权期限结束(必填)</td>
                    <td align="left" width="50%">
                        <input type="text" id="authTermEndTwo" style="width:180px"
                               readonly="readonly"/>
                        <input type="hidden" name="authTermEnd" id="authTermEndTwoTemp"/>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td align="left">
                        <msk2:button buttonType="button" buttonId="SL24110101001.SAVE" buttonValue="保存"/>
                            <%--<msk:button buttonValue="保存" buttonId="SL24110101001.SAVE" buttonType="button"/>--%>
                    </td>
                </tr>
            </table>
        </form:form>
    </div>
</div>
<script src="${ctx}/static/sl/js/SL24110101001.js"></script>
