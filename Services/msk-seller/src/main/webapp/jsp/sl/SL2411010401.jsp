<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<%@ taglib prefix="msk2" uri="/msk" %>
<script type="text/javascript">
    var slepHonorList = "${slepHonorList}";
</script>
<div class="group-accordion" active="false">
    <h3>
        <label>企业荣誉描述</label>
    </h3>

    <div>
        <div>
            <tr>
                <td align="center">
                    <msk2:button buttonType="button" buttonId="SL2411010401.ADD" buttonValue="添加"/>
                    <%--<msk:button buttonValue="添加" buttonId="SL2411010401.ADD" buttonType="button"/>--%>
                </td>
            </tr>
        </div>

        <msk2:roleArea pageCode="SL2411010401" areaCode="SAVEBUTTON" needAuth="true">
            <c:forEach items="${slEpHonors}" var="epHonor" varStatus="i">
                <div>
                    <form action="${ctx}/SL2411010401/update" id="SL2411010401Form${i.index}" method="post"
                          enctype="multipart/form-data">
                        <input type="hidden" id="jsp_epId2" name="jsp_epId2" value="${jsp_epId2}"/>
                        <table width="100%" border="0" cellpadding="0" cellspacing="0">
                            <tr>
                                <td align="right" width="50%">荣誉证书发证日期(必填)　</td>
                                <td align="left" width="50%">
                                    <c:if test="${epHonor.certDate == null}">
                                        <input type="text" id="certDate${i.index}" readonly="readonly"/>
                                        <input type="hidden" name="certDate" id="certDateTemp${i.index}"/>
                                    </c:if>
                                    <c:if test="${epHonor.certDate != null}">
                                        <input type="text" id="certDate${i.index}" readonly="readonly"
                                               value="<fmt:formatDate value="${epHonor.certDate}" pattern="yyyy-MM-dd" />"/>
                                        <input type="hidden" name="certDate" id="certDateTemp${i.index}"/>
                                    </c:if>
                                </td>
                            </tr>
                            <tr>
                                <td align="right" width="50%">荣誉证书发证单位　</td>
                                <td align="left" width="50%">
                                    <input type="text" name="certIssuer" value="${epHonor.certIssuer}" maxlength="50"/>
                                </td>
                            </tr>
                            <tr>
                                <td align="left" width="50%">
                                    <input type="hidden" name="honorId" id="honorId${i.index}"
                                           value="${epHonor.honorId}"/>
                                </td>
                            </tr>
                            <tr>
                                <td align="right" width="50%">证书荣誉描述(必填)　</td>
                                <td align="left" width="50%">
                                    <input type="text" name="honorDesc" id="honorDesc${i.index}"
                                           value="${epHonor.honorDesc}" maxlength="50" required="true"
                                           requiredMessage="证书荣誉描述不能为空"/>
                                </td>
                            </tr>
                            <tr>
                                <td align="right" width="50%">${slEpCert.certName}图片上传</td>
                                <td align="left" width="50%">
                                    <input type="file" name="file" id="importFile"/>
                                </td>
                            </tr>
                            <tr>
                                <td align="right">
                                    <msk2:button buttonType="button" buttonId="SL2411010401.UPDATE${i.index}"
                                                 buttonValue="保存"/>
                                        <%--<msk:button buttonValue="保存"  buttonId="SL2411010401.UPDATE${i.index}" buttonType="button"/>--%>
                                </td>
                                <td align="left">
                                    <msk2:button buttonType="button" buttonId="SL2411010401.DELETE${i.index}"
                                                 buttonValue="删除"/>
                                        <%--<msk:button buttonValue="删除" buttonId="SL2411010401.DELETE${i.index}" buttonType="button"/>--%>
                                </td>
                            </tr>

                        </table>
                    </form>
                </div>
            </c:forEach>
        </msk2:roleArea>
    </div>
</div>
<script src="${ctx}/static/sl/js/SL2411010401.js"></script>
