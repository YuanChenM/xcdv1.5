<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: marshall
  Date: 16/3/9
  Time: 下午8:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<script type="text/javascript" src="${ctx}/static/upload/fileupload.js"></script>
<div class="group-accordion" collapsible="true" active="true">
    <h3>
        <label>买家证照信息</label>
    </h3>
    <form:form id="licEditForm" metdod="post" enctype="multipart/form-data">
        <input type="hidden" id="buyerId" name="buyerId" value="${buyerId}"/>
        <input type="hidden" value=" " name="licDesc" id="licDes"/>
        <input type="hidden" value=" " name="legalLicDesc" id="legalLicDes"/>
        <table id="licShowTable" style="width: 100%" CellSpacing=8>
            <tr>
                <td width="12.5%" align="right">营业执照类型 : </td>
                <td width="12.5%" align="left">
                    <c:choose>
                        <c:when test="${empty licenceInfo.licDes}">
                            --请选择--
                        </c:when>
                        <c:otherwise>
                            ${licenceInfo.licDes}
                        </c:otherwise>
                    </c:choose>
                </td>
                <td width="12.5%" align="right">营业执照号码 : </td>
                <td width="12.5%" align="left">${licenceInfo.licNumber}</td>
                <td width="12.5%"/>
                <td width="12.5%"/>
                <td width="12.5%"/>
                <td width="12.5%"/>
            </tr>
            <tr>
                <td width="12.5%" align="right">法定代表人姓名 : </td>
                <td width="12.5%" align="left">${licenceInfo.legalName}</td>
                <td width="12.5%" align="right">法定代表人证件类型 : </td>
                <td width="12.5%" align="left">
                    <c:choose>
                        <c:when test="${empty licenceInfo.legalLicDes}">
                            --请选择--
                        </c:when>
                        <c:otherwise>
                            ${licenceInfo.legalLicDes}
                        </c:otherwise>
                    </c:choose>
                </td>
                <td width="12.5%" align="right">法定代表人证件号码 : </td>
                <td width="12.5%" align="left">${licenceInfo.legalLicNumber}</td>
                <td width="12.5%"/>
                <td width="12.5%"/>
            </tr>
            <c:if test="${licencePic.picLicensePath != null && licencePic.picLicensePath != ''}">
                <tr align="center">
                    <td width="12.5%" align="right">营业执照 : </td>
                    <td colspan="3" width="62.5%" align="right"><img src="${licencePic.picLicensePath}" height="200px"
                                                                     width="200px"/></td>
                </tr>
            </c:if>
            <c:if test="${licencePic.picOrgStructurePath != null && licencePic.picOrgStructurePath != ''}">
                <tr align="center">
                    <td width="12.5%" align="right">组织机构代码证 : </td>
                    <td colspan="3" width="62.5%" align="right"><img src="${licencePic.picOrgStructurePath}"
                                                                     height="200px" width="200px"/></td>
                </tr>
            </c:if>
            <c:if test="${licencePic.picTaxRegistrationPath != null && licencePic.picTaxRegistrationPath != ''}">
                <tr align="center">
                    <td width="12.5%" align="right">税务登记证 : </td>
                    <td colspan="3" width="62.5%" align="right"><img src="${licencePic.picTaxRegistrationPath}"
                                                                     height="200px" width="200px"/></td>
                </tr>
            </c:if>
            <c:if test="${licencePic.picFoodCirculationPath != null && licencePic.picFoodCirculationPath != ''}">
                <tr align="center">
                    <td width="12.5%" align="right">食品流通许可证 : </td>
                    <td colspan="3" width="62.5%" align="right"><img src="${licencePic.picFoodCirculationPath}"
                                                                     height="200px" width="200px"/></td>
                </tr>
            </c:if>
            <c:if test="${licencePic.picCertPath != null && licencePic.picCertPath != ''}">
                <tr align="center">
                    <td width="12.5%" align="right">法定代表人证 : </td>
                    <td colspan="3" width="62.5%" align="right"><img src="${licencePic.picCertPath}" height="200px"
                                                                     width="200px"/></td>
                </tr>
            </c:if>
            <tr>
                <td width="12.5%" align="right">
                    <msk:button buttonValue="编辑" buttonId="BUYERLIC.EDIT" buttonType="button"/>
                </td>
            </tr>
            <tr></tr>
            <tr></tr>
            <tr></tr>
            <tr></tr>
            <tr></tr>
            <tr></tr>
            <tr></tr>
            <tr></tr>
        </table>
        <table id="licEditTable" style="width: 100%;display:none;" CellSpacing=8>
            <tr>
                <td width="12.5%" align="right">营业执照类型 : </td>
                <td width="12.5%" align="left">
                    <select id="licName" name="licName">
                        <option value="0" selected>--请选择--</option>
                        <c:forEach items="${licTypeList}" var="licType">
                            <c:choose>
                                <c:when test="${licenceInfo.licName eq licType.key}">
                                    <option value="${licType.key}" selected >${licType.value}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${licType.key}">${licType.value}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                </td>
                <td width="12.5%" align="right">营业执照号码 : </td>
                <td width="12.5%" align="left">
                    <input type="text" id="licNumber" name="licNumber" value="${licenceInfo.licNumber}" maxlength="20">
                </td>
                <td width="12.5%"/>
                <td width="12.5%"/>
                <td width="12.5%"/>
                <td width="12.5%"/>
            </tr>
            <tr>
                <td width="12.5%" align="right">法定代表人姓名 : </td>
                <td width="12.5%" align="left">
                    <input type="text" id="legalName" name="legalName" value="${licenceInfo.legalName}" maxlength="20">
                </td>
                <td width="12.5%" align="right">法定代表人证件类型 : </td>
                <td width="12.5%" align="left">
                    <select id="legalLicType" name="legalLicType">
                        <option value="0" >--请选择--</option>
                        <c:forEach items="${legalLicTypeList}" var="legalLicType">
                            <c:choose>
                                <c:when test="${licenceInfo.legalLicType eq legalLicType.key}">
                                    <option value="${legalLicType.key}" selected>${legalLicType.value}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${legalLicType.key}">${legalLicType.value}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                </td>
                <td width="12.5%" align="right">法定代表人证件号码 : </td>
                <td width="12.5%" align="left">
                    <input type="text" id="legalLicNumber" name="legalLicNumber" maxlength="20"
                           value="${licenceInfo.legalLicNumber}"></td>
                </td>
                <td width="12.5%"/>
                <td width="12.5%"/>
            </tr>
            <tr>
                <td width="12.5%" align="right">营业执照 : </td>
                <td width="12.5%" align="left">
                    <msk:uploadFile fileLinkId="busLicPicId" fileName="busLicPicName" uploadButtonId="BUYERLIC_UPLOAD"
                                    fileSize="5" fileSizeMessage="营业执照文件不能大于5M"
                                    fileType="png,jpg,jpeg,bmp,gif,PNG,JPG,JPEG,BMP,GIF" fileTypeMessage="营业执照文件格式错误"
                                    callbackFunction="callBack"></msk:uploadFile>
                </td>
                <td width="12.5%" align="right">组织机构代码证 : </td>
                <td width="12.5%" align="left">
                    <msk:uploadFile fileLinkId="orgCertificatePicId" fileName="orgCertificatePicName"
                                    uploadButtonId="BUYERLIC_UPLOAD" fileSize="5" fileSizeMessage="组织机构代码证文件不能大于5M"
                                    fileType="png,jpg,jpeg,bmp,gif,PNG,JPG,JPEG,BMP,GIF" fileTypeMessage="组织机构代码证文件格式错误"
                                    callbackFunction="callBack" bindUpload="false"></msk:uploadFile>
                </td>
                <td width="12.5%"></td>
                <td width="12.5%"></td>
                <td width="12.5%"></td>
                <td width="12.5%"></td>
            </tr>
            <tr>
                <td width="12.5%" align="right">税务登记证 : </td>
                <td width="12.5%" align="left">
                    <msk:uploadFile fileLinkId="taxCertificatePicId" fileName="taxCertificatePicName"
                                    uploadButtonId="BUYERLIC_UPLOAD" fileSize="5" fileSizeMessage="税务登记证文件不能大于5M"
                                    fileType="png,jpg,jpeg,bmp,gif,PNG,JPG,JPEG,BMP,GIF" fileTypeMessage="税务登记证文件格式错误"
                                    callbackFunction="callBack" bindUpload="false"></msk:uploadFile>
                </td>
                <td width="12.5%" align="right">食品流通许可证 : </td>
                <td width="12.5%">
                    <msk:uploadFile fileLinkId="foodCertificatePicId" fileName="foodCertificatePicName"
                                    uploadButtonId="BUYERLIC_UPLOAD" fileSize="5" fileSizeMessage="食品流通许可证文件不能大于5M"
                                    fileType="png,jpg,jpeg,bmp,gif,PNG,JPG,JPEG,BMP,GIF" fileTypeMessage="食品流通许可证文件格式错误"
                                    callbackFunction="callBack" bindUpload="false"></msk:uploadFile>
                </td>
                <td width="12.5%"></td>
                <td width="12.5%"></td>
                <td width="12.5%"></td>
                <td width="12.5%"></td>
            </tr>
            <tr>
                <td width="12.5%" align="right">法定代表人证 : </td>
                <td width="12.5%" align="left">
                    <msk:uploadFile fileLinkId="legalCertificatePicId" fileName="legalCertificatePicName"
                                    uploadButtonId="BUYERLIC_UPLOAD" fileSize="5" fileSizeMessage="法定代表人证文件不能大于5M"
                                    fileType="png,jpg,jpeg,bmp,gif,PNG,JPG,JPEG,BMP,GIF" fileTypeMessage="法定代表人证文件格式错误"
                                    callbackFunction="callBack" bindUpload="false"></msk:uploadFile>
                </td>
                <td width="12.5%"></td>
                <td width="12.5%"></td>
                <td width="12.5%"></td>
                <td width="12.5%"></td>
                <td width="12.5%"></td>
                <td width="12.5%"></td>
            </tr>
            <tr>
                <td width="12.5%" align="right">
                    <msk:button buttonValue="保存" buttonId="BUYERLIC.SAVE" buttonType="button"/>
                    <msk:button buttonValue="上传" buttonId="BUYERLIC.UPLOAD" buttonType="hidden"/>
                </td>
            </tr>
        </table>
    </form:form>
</div>
<script type="text/javascript" src="${ctx}/static/buyers/js/comm/BASE_BUYER_LIC_INFO.js"></script>