<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<script type="text/javascript">
    var maSize = "${maSize}";
</script>
<div class="group-accordion" active="false">
    <h3>
        <label>公司团队</label>
    </h3>

    <div>
        <div>
            <tr>
                <td align="center">
                    <msk2:button buttonType="button" buttonId="SL24110108.ADD" buttonValue="添加"/>
                    <%--<msk:button buttonValue="添加" buttonId="SL24110108.ADD" buttonType="button"/>--%>
                </td>
            </tr>
        </div>
        <msk2:roleArea pageCode="SL24110108" areaCode="SAVEBUTTON" needAuth="true">
            <c:forEach items="${slEpManager}" var="ma" varStatus="i">
                <div>
                    <form:form action="${ctx}/SL24110108/update" id="SL24110108Form${i.index}"
                               metdod="post" enctype="multipart/form-data">
                        <input type="hidden" id="crtId" name="crtId" value="${loginUser.emplId}"/>
                        <input type="hidden" id="updId" name="updId" value="${loginUser.emplId}"/>
                        <table width="100%" border="0" cellpadding="0" cellspacing="0">

                            <tr>
                                <td align="right" width="50%">职务</td>
                                <td align="left" width="50%">
                                    <input type="text" name="memberDuties" value="${ma.memberDuties}" readonly/>
                                </td>
                            </tr>
                            <tr>
                                <td align="right" width="50%">姓名</td>
                                <td align="left" width="50%">
                                    <input type="text" id="memberName" name="memberName" value="${ma.memberName}"
                                           maxlength="20"/>
                                    <input type="hidden" name="epId" value="${ma.epId}"/>
                                    <input type="hidden" name="memberId" value="${ma.memberId}"/>
                                </td>
                            </tr>

                            <tr>
                                <td align="right" width="50%">年龄</td>
                                <td align="left" width="50%">
                                    <input type="text" id="memberAge" name="memberAge" value="${ma.memberAge}"
                                           maxlength="3" digits="true" digitsMessage="年龄为整数"/>
                                </td>
                            </tr>
                            <tr>
                                <td align="right" width="50%">联系方式</td>
                                <td align="left" width="50%">
                                    <input type="text" id="memberTel" name="memberTel" value="${ma.memberTel}"
                                           maxlength="20"/>
                                </td>
                            </tr>
                            <tr>
                                <td align="right" width="50%">教育程度</td>
                                <td align="left" width="50%">
                                    <input type="text" id="memberEduc" name="memberEduc" value="${ma.memberEduc}"
                                           maxlength="20"/>
                                </td>
                            </tr>
                            <tr>
                                <td align="right" width="50%">头像上传</td>
                                <td align="left" width="50%">
                                    <input type="file" name="file" id="image"/>
                                </td>
                            </tr>

                            <tr>
                                <td align="right">
                                    <msk2:button buttonType="button" buttonId="SL24110108.UPDATE${i.index}"
                                                 buttonValue="保存" name="${i.index}"/>
                                        <%--<msk:button buttonValue="保存" name="${i.index}" buttonId="SL24110108.UPDATE${i.index}" buttonType="button"/>--%>
                                </td>
                                <td align="left">
                                    <msk2:button buttonType="button" buttonId="SL24110108.DELETE${i.index}"
                                                 buttonValue="删除" name="${i.index}"/>
                                        <%--<msk:button buttonValue="删除" name="${i.index}" buttonId="SL24110108.DELETE${i.index}" buttonType="button"/>--%>
                                </td>

                            </tr>

                        </table>
                    </form:form>
                </div>
            </c:forEach>
        </msk2:roleArea>
    </div>
</div>
<script src="${ctx}/static/sl/js/SL24110108.js"></script>
