<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<%@ taglib prefix="msk2" uri="/msk" %>
<script type="text/javascript">
    var listSize = "${workshoplistSize}";
</script>
<div class="group-accordion" active="false">
    <h3>
        <label>企业生产车间、设备、产品工艺流程描述</label>
    </h3>

    <div>
        <div>
            <tr>
                <td align="center">
                    <msk2:button buttonType="button" buttonId="SL2411010501.ADD" buttonValue="添加"/>
                    <%--<msk:button buttonValue="添加" buttonId="SL2411010501.ADD" buttonType="button"/>--%>
                </td>
            </tr>
        </div>

        <msk2:roleArea pageCode="SL2411010501" areaCode="SAVEBUTTON" needAuth="true">
            <c:forEach items="${slEpWorkshops}" var="workshop" varStatus="i">
                <div>
                    <form action="${ctx}/SL2411010501/update" id="SL2411010501Form${i.index}" method="post"
                          enctype="multipart/form-data">
                        <input type="hidden" id="jsp_epId2" name="jsp_epId2" value="${jsp_epId2}"/>
                        <table width="100%" border="0" cellpadding="0" cellspacing="0">
                            <tr>
                                <td align="right" width="50%">车间名称(必填)　</td>
                                <td align="left" width="50%">
                                    <input type="text" name="workshopName" value="${workshop.workshopName}"
                                           maxlength="30" required="true" requiredMessage="车间名称不能为空"/>
                                </td>
                            </tr>
                            <tr>
                                <td align="right" width="50%">车间生产产品　</td>
                                <td align="left" width="50%">
                                    <input type="text" name="product" value="${workshop.product}" maxlength="200"/>
                                </td>
                            </tr>
                            <tr>
                                <td align="right" width="50%">车间工艺流程特点　</td>
                                <td align="left" width="50%">
                                    <input type="text" name="process" value="${workshop.process}" maxlength="200"/>
                                </td>
                            </tr>
                            <tr>
                                <td align="right" width="50%">车间图片上传　</td>
                                <td align="left" width="50%">
                                    <input type="file" name="file"/>
                                </td>
                            </tr>
                            <tr>
                                <td align="right" width="50%">
                                    <input type="hidden" name="workshopId" value="${workshop.workshopId}"/>
                                </td>
                            </tr>
                            <tr>
                                <td align="right">
                                    <msk2:button buttonType="button" buttonId="SL2411010501.UPDATE${i.index}"
                                                 buttonValue="保存"/>
                                        <%--<msk:button buttonValue="保存" buttonId="SL2411010501.UPDATE${i.index}" buttonType="button"/>--%>
                                </td>
                                <td align="left">
                                    <msk2:button buttonType="button" buttonId="SL2411010501.DELETE${i.index}"
                                                 buttonValue="删除"/>
                                        <%--<msk:button buttonValue="删除" buttonId="SL2411010501.DELETE${i.index}" buttonType="button"/>--%>
                                </td>
                            </tr>
                        </table>
                    </form>
                </div>
            </c:forEach>
        </msk2:roleArea>
    </div>
</div>
<script src="${ctx}/static/sl/js/SL2411010501.js"></script>
