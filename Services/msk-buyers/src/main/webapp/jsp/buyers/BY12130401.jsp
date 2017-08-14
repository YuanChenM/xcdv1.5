<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<div class="page-content list-page">
    <div>
        <form:form action="${ctx}/BY12130401/save/" id="BY12130401Form" method="post" enctype="multipart/form-data">
            <input type="hidden" id="id" name="id" value="${id}"/>
            <input type="hidden" id="buyerId" name="buyerId" value="${buyerId}"/>
            <input type="hidden" id="editType" name="editType" value="${editType}"/>
            <input type="hidden" id="employeeTypeName" name="employeeTypeName" value=" "/>
            <input type="hidden" id="busCardPicPath" name="busCardPicPath" value="">
            <input type="hidden" id="isFlag" name="isFlag" value="">
            <table style="width: 100%;" CellSpacing=8>
                <tr>
                    <td align="right" width="50%"><span style="color:red;display: inline; vertical-align: sub">*&nbsp;</span>员工姓名 : </td>
                    <td align="left" width="50%">
                        <%--<c:choose>
                            <c:when test="${editType eq 'BY121307Add' || editType eq 'BY121304Add'}">--%>
                                <input type="text" id="employeeName" name="employeeName"
                                       value="${buyerEmployee.employeeName}" maxlength="50" required requiredMessage="员工姓名不能为空"/>
                            <%--</c:when>
                            <c:otherwise>
                                <input type="text" id="employeeName" name="employeeName"
                                       value="${buyerEmployee.employeeName}" maxlength="50"  required requiredMessage="员工姓名不能为空"/>
                            </c:otherwise>
                        </c:choose>--%>
                    </td>
                </tr>
                <tr>
                    <td align="right" width="50%"><span style="color:red;display: inline; vertical-align:sub">*&nbsp;</span>员工类型 : </td>
                    <td align="left" width="50%">
                        <select id="employeeType" name="employeeType" style="width:133px">
                            <option value="--请选择--">--请选择--</option>
                            <c:forEach items="${employeeTypeList}" var="employeeType">
                                <c:choose>
                                    <c:when test="${employeeType.key eq buyerEmployee.employeeType}">
                                        <option value="${employeeType.key}" selected>${employeeType.value}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${employeeType.key}">${employeeType.value}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td align="right" width="50%"><span style="color:red;display: inline; vertical-align: sub">*&nbsp;</span>手机号 : </td>
                    <td align="left" width="50%">
                        <input type="text" id="employeeTel" name="employeeTel" value="${buyerEmployee.employeeTel}"
                               required requiredMessage="手机号不能为空"  maxlength="50"/>
                    </td>
                </tr>
                <tr>
                    <td align="right" width="50%">QQ号 : </td>
                    <td align="left" width="50%">
                        <input type="text" id="employeeQq" name="employeeQq" value="${buyerEmployee.employeeQq}"
                               maxlength="50"/>
                    </td>
                </tr>
                <tr>
                    <td align="right" width="50%">微信号 : </td>
                    <td align="left" width="50%">
                        <input type="text" id="employeeWechat" name="employeeWechat"
                               value="${buyerEmployee.employeeWechat}" maxlength="50"/>
                    </td>
                </tr>
                <tr>
                    <td align="right" width="50%">
                        <c:choose>
                            <c:when test="${buyerEmployee.busCardFlg eq '1'}">
                                <input type="checkbox" id="busCardFlg" name="busCardFlg" checked/>有无名片照
                            </c:when>
                            <c:otherwise>
                                <input type="checkbox" id="busCardFlg" name="busCardFlg"/>有无名片照
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td align="left" width="50%">
                        <c:choose>
                            <c:when test="${buyerEmployee.contactPerson eq '1'}">
                                <input type="checkbox" id="contactPerson" name="contactPerson" checked/>是否联络人
                            </c:when>
                            <c:otherwise>
                                <input type="checkbox" id="contactPerson" name="contactPerson"/>是否联络人
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
                <tr>
                    <td align="right" width="50%">
                        <c:choose>
                            <c:when test="${buyerEmployee.purchase eq '1'}">
                                <input type="checkbox" id="purchase" name="purchase" checked/>是否采购人
                            </c:when>
                            <c:otherwise>
                                <input type="checkbox" id="purchase" name="purchase"/>是否采购人
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td align="left" width="50%">
                        <c:choose>
                            <c:when test="${buyerEmployee.receivePerson eq '1'}">
                                <input type="checkbox" id="receivePerson" name="receivePerson" checked/>是否收货人
                            </c:when>
                            <c:otherwise>
                                <input type="checkbox" id="receivePerson" name="receivePerson"/>是否收货人
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
                <c:if test="${buyerEmployee.busCardFlg eq '1'}">
                    <tr align="center">
                        <td width="50%" align="right">雇员名片照 : </td>
                        <td width="50%" align="left"><img src="${buyerEmployee.busCardPicPath}" height="100px"
                                                          width="100px"/></td>
                    </tr>
                </c:if>
                <tr>
                    <td width="40%" align="right"></td>
                    <td width="60%" align="left">
                        <msk:uploadFile fileLinkId="busCardPicId" fileName="busCardPicName"
                                        uploadButtonId="BY12130401_UPLOAD" fileSize="5" fileSizeMessage="不能大于5M"
                                        fileType="png,jpg,jpeg,bmp,gif,PNG,JPG,JPEG,BMP,GIF" fileTypeMessage="文件格式错误"
                                        callbackFunction="callBack"></msk:uploadFile>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td width="60%" align="left">
                        <span id="errorMessage" style="color:#FF0000"></span>
                    </td>
                </tr>
                <tr>
                    <td align="right">
                    </td>
                    <td align="left">
                        <msk:button buttonValue="保存" buttonId="BY12130401.SAVE" buttonType="button"/>
                        <msk:button buttonValue="上传" buttonId="BY12130401.UPLOAD" buttonType="hidden"/>
                    </td>
                </tr>
            </table>
        </form:form>
    </div>
</div>
<script type="text/javascript" src="${ctx}/static/buyers/js/BY12130401.js"></script>