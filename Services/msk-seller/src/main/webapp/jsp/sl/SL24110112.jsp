<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<%@ taglib prefix="msk2" uri="/msk" %>
<script type="text/javascript">
    var maSize = "${slEpDdBeanList.size()}";
</script>
<div class="group-accordion" active="false">
    <h3>
        <label>检测设备</label>
    </h3>

    <div>
        <div>
            <tr>
                <td align="center">
                    <msk2:button buttonType="button" buttonId="SL24110112.ADD" buttonValue="添加"/>
                    <%--<msk:button buttonValue="添加" buttonId="SL24110112.ADD" buttonType="button"/>--%>
                </td>
            </tr>
        </div>

        <msk2:roleArea pageCode="SL24110112" areaCode="SAVEBUTTON" needAuth="true">
            <c:forEach items="${slEpDdBeanList}" var="slEpDd" varStatus="i">
                <div>
                    <form:form action="${ctx}/SL24110112/update" id="SL24110112Form${i.index}"
                               metdod="post" enctype="multipart/form-data">
                        <input type="hidden" id="jsp_epId2" name="jsp_epId2" value="${jsp_epId2}"/>
                        <input type="hidden" id="crtId" name="crtId" value="${loginUser.emplId}"/>
                        <input type="hidden" id="updId" name="updId" value="${loginUser.emplId}"/>
                        <table width="100%" border="0" cellpadding="0" cellspacing="0">
                            <tr>
                                <td align="right" width="50%">设备名称</td>
                                <td align="left" width="50%">
                                    <input type="text" name="ddName" value="${slEpDd.ddName}"  maxlength="200"/>
                                    <input type="hidden" name="ddId" value="${slEpDd.ddId}"/>
                                </td>
                            </tr>
                            <tr>
                                <td align="right" width="50%">主要用途</td>
                                <td align="left" width="50%">
                                    <input type="text" id="ddEquipmentId" name="ddEquipment"
                                           value="${slEpDd.ddEquipment}"  maxlength="500"/>
                                </td>
                            </tr>
                            <tr>
                                <td align="right" width="50%">检测设备图片</td>
                                <td align="left" width="50%">
                                    <input type="file" id="slEpDdPathId" name="file"/>
                                </td>
                            </tr>
                            <tr>
                                <td align="right">
                                    <msk2:button buttonType="button" buttonId="SL24110112.UPDATE${i.index}"
                                                 buttonValue="保存" name="${i.index}"/>
                                        <%--<msk:button buttonValue="保存" name="${i.index}" buttonId="SL24110112.UPDATE${i.index}" buttonType="button"/>--%>
                                </td>
                                <td align="left">
                                    <msk2:button buttonType="button" buttonId="SL24110112.DELETE${i.index}"
                                                 buttonValue="删除" name="${i.index}"/>
                                        <%--<msk:button buttonValue="删除" name="${i.index}" buttonId="SL24110112.DELETE${i.index}" buttonType="button"/>--%>
                                </td>

                            </tr>
                        </table>
                    </form:form>
                </div>
            </c:forEach>
        </msk2:roleArea>
    </div>
</div>
<script src="${ctx}/static/sl/js/SL24110112.js"></script>
