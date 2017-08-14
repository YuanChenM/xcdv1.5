<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<script type="text/javascript">
    function callbackFun(message) {
        $.alertMessage.info(message);
        HDF.closeLoadingMask();
    }
</script>
<div class="group-accordion" active="false">
    <h3>
        <label>生产商/OEM商信息编辑</label>
    </h3>

    <div>
        <div id="ordinary" style="display:block">
            <form:form action="${ctx}/SL241103010/save" id="SL241103010Form"
                       method="post">

                <input type="hidden" id="crtId" name="crtId" value="${loginUser.emplId}"/>
                <input type="hidden" id="updId" name="updId" value="${loginUser.emplId}"/>

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
                            <input type="text" id="authTermBeginThree" style="width:180px" readonly="readonly"/>
                            <input type="hidden" name="authTermBegin" id="authTermBeginThreeTemp"/>
                        </td>
                    </tr>
                    <tr>
                        <td align="right" width="50%">授权期限结束(必填)</td>
                        <td align="left" width="50%">
                            <input type="text" id="authTermEndTwo" style="width:180px" readonly="readonly"/>
                            <input type="hidden" name="authTermEnd" id="authTermEndTwoTemp"/>
                        </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td align="left">
                            <!-- Modified by xia_xiaojie on 2016/6/17. Modified start. -->
                            <msk2:button buttonType="button" buttonId="SL241103010.SAVE" buttonValue="保存"/>
                                <%--
                                <msk2:button buttonType="button" buttonId="SL2411010401.ADD" buttonValue="添加"/>
                                <%--<msk:button buttonValue="保存" buttonId="SL241103010.SAVE" buttonType="button"/>
                                --%>
                            <!-- Modified end. -->
                        </td>
                    </tr>
                </table>
            </form:form>
        </div>
    </div>
</div>
<script src="${ctx}/static/sl/js/SL241103010.js"></script>
