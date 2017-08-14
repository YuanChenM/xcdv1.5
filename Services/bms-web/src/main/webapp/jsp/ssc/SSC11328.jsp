<%--
  Created by IntelliJ IDEA.
  User: peng_hao
  Date: 2016/9/1
  Time: 16:19
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="navigation" uri="/msk" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@include file="/common/taglib.jsp" %>
<div class="page-content list-page">
<!--企业账号信息-->
<div class="group-accordion" collapsible="true" active="true">
    <h3>
        <label>企业账号信息</label>
    </h3>
    <table width="100%">
        <tr >
            <td coltype="text"  width="150px"  align="right">卖家账号：</td>
            <td coltype="text"  align="left"  width="120px">${result.slAccount.slAccount}</td>
            <td coltype="text"  width="250px" align="right">卖家头像图片：</td>
            <td coltype="text"   width="250px"  height="80px" name="pdSpec" rowspan="3" align="left"><a class="tooltip" title="卖家头像" href="${result.basePath}" target="_blank" src="${result.basePath}" id="basePathId" name="imgSrc"></a></td>
        </tr>
        <tr>
            <td coltype="text"  align="right">用户名：</td>
            <td coltype="text"  align="left" >${result.slAccount.slShowName}</td>
            <td coltype="text"  ></td>
        </tr>
        <tr>
            <td coltype="text"  align="right">手机号：</td>
            <td coltype="text"  align="left" >${result.slAccount.slTel}</td>
            <td coltype="text"  ></td>
        </tr>
    </table>
</div>
<!--企业基本信息-->
<div class="group-accordion" collapsible="true" active="true">
    <h3 style="position: relative">
        <label>企业基本信息</label>
    </h3>
    <div width="100%" style="padding: 0px; border-right: none;border-bottom: none; border-top: none;">
        <table  class="tree dataTable no-footer">
            <thead>
            <tr align="center" style="background:#DBDBDB">
                <th coltype="text" width="25%" name="pdSpec">证照</th>
                <th coltype="text" width="25%" name="pdSpec">图片</th>
                <th coltype="text" width="50%" name="pdSpec">资质描述</th>
            </tr>
            </thead>
            <c:if test="${result.slEnterprise.licType!=1}">
                <tr>
                    <td coltype="text" width="20%" height="50px" >营业执照</td>
                    <td coltype="text" width="20%" height="150px" align="center"><a class="tooltip" title="营业执照" href="${result.licPath}" target="_blank" src="${result.licPath}" id="licPathId" name="imgSrc"></a></td>
                    <td coltype="text" width="20%" height="60px">
                        <div style="height:150px;">
                                ${result.licSpeck}
                        </div>
                    </td>
                </tr>
                <tr>
                    <td coltype="text" width="20%" height="50px" name="pdSpec">税务登记证</td>
                    <td coltype="text" width="20%" height="150px" name="pdSpec" align="center"><a class="tooltip" title="税务登记证" href="${result.taxPath}" target="_blank" src="${result.taxPath}" id="taxPathId" name="imgSrc"></a></td>
                    <td coltype="text" width="20%" height="60px" name="pdSpec">
                        <div style="height:150px;">
                                ${result.taxSpeck}
                        </div>
                    </td>
                </tr>
                <tr>
                    <td coltype="text" width="20%" height="50px" name="pdSpec">组织代码证</td>
                    <td coltype="text" width="20%" height="150px" name="pdSpec"align="center"><a class="tooltip" title="组织代码证" href="${result.orgNoPath}" target="_blank" src="${result.orgNoPath}" id="orgNoPathId" name="imgSrc"></a></td>
                    <td coltype="text" width="20%" height="60px" name="pdSpec">
                        <div style="height:150px;">
                                ${result.orgSpeck}
                        </div>
                    </td>
                </tr>
            </c:if>
            <tr>
                <td coltype="text" width="20%" height="50px" name="pdSpec">银行开户许可证</td>
                <td coltype="text" width="25%" height="150px" name="pdSpec" align="center"><a class="tooltip" title="银行开户许可证" href="${result.balPath}" target="_blank" src="${result.balPath}" id="balPathId" name="imgSrc"></a></td>
                <td coltype="text" width="20%" height="60px" name="pdSpec">
                    <div style="height:150px;">
                        ${result.balSpeck}
                    </div>
                </td>
            </tr>
            <c:if test="${result.slEnterprise.licType==1}">
                <tr>
                    <td coltype="text" width="20%" height="50px" name="pdSpec">三证合一营业执照</td>
                    <td coltype="text" width="25%" height="150px" name="pdSpec" align="center"><a class="tooltip" title="三证合一营业执照" href="${result.licTypePath}" target="_blank" src="${result.licTypePath}" id="licTypePathId" name="imgSrc"></a></td>
                    <td coltype="text" width="20%" height="60px" name="pdSpec">
                        <div>
                                ${result.licTypeSpeck}
                        </div>
                    </td>
                </tr>
            </c:if>
            <c:choose>
                <c:when test="${result.slSeller.selfFlg==1 and result.slSeller.agentFlg==1 and result.slSeller.oemFlg==1}">
                    <tr>
                        <td coltype="text" width="20%" height="50px" name="pdSpec">食品流通许可证</td>
                        <td coltype="text" width="20%" height="150px" name="pdSpec" align="center"><a title="食品流通许可证" href="${result.fdlPath}" target="_blank" src="${result.fdlPath}" id="fdlPathId"  name="imgSrc"></a></td>
                        <td coltype="text" width="20%" height="60px" name="pdSpec">
                            <div style="height:150px;">
                                许可证编码：${result.slEnterprise.fdlNo}<br/>有效期限：${result.fdlTermEnds}
                            </div>
                        </td>
                    </tr>
                    <%--<tr>
                        <td coltype="text" width="20%" height="50px" name="pdSpec">代理及分销授权书</td>
                        <td coltype="text" width="20%" height="150px" name="pdSpec"><a title="代理及分销授权书" href="${result.slEnterprise.authPath}" target="_blank" src="${result.slEnterprise.authPath}" id="authPathId"></a></td>
                        <td coltype="text" width="20%" height="60px" name="pdSpec">授权单位：${result.slEnterprise.atuhEpName}</td>
                    </tr>--%>
                    <%--<tr>
                        <td coltype="text" width="20%" height="50px" name="pdSpec">OEM委托授权书</td>
                        <td coltype="text" width="20%" height="150px" name="pdSpec"><a title="OEM委托授权书" href="${result.slEnterprise.oemAuthPath}" target="_blank" src="${result.slEnterprise.oemAuthPath}" id="oemAuthPathId"></a></td>
                        <td coltype="text" width="20%" height="60px" name="pdSpec">委托单位：${result.slEnterprise.atuhEpName2}</td>
                    </tr>--%>
                </c:when>
                <c:when test="${result.slSeller.agentFlg==1 and result.slSeller.oemFlg==1}">
                    <tr>
                        <td coltype="text" width="20%" height="50px" name="pdSpec">食品流通许可证</td>
                        <td coltype="text" width="20%" height="150px" name="pdSpec" align="center"><a title="食品流通许可证" href="${result.fdlPath}" target="_blank" src="${result.fdlPath}" id="fdlPathId"  name="imgSrc"></a></td>
                        <td coltype="text" width="20%" height="60px" name="pdSpec">
                            <div style="height:150px;">
                                许可证编码：${result.slEnterprise.fdlNo}<br/>有效期限：${result.fdlTermEnds}
                            </div>
                        </td>
                    </tr>
                    <%--<tr>
                        <td coltype="text" width="20%" height="50px" name="pdSpec">代理及分销授权书</td>
                        <td coltype="text" width="20%" height="50px" name="pdSpec"><a title="代理及分销授权书" href="${result.slEnterprise.authPath}" target="_blank" src="${result.slEnterprise.authPath}" class="authPathId"></a></td>
                        <td coltype="text" width="20%" height="60px" name="pdSpec">授权单位：${result.slEnterprise.atuhEpName}</td>
                    </tr>--%>
                    <%--<tr>
                        <td coltype="text" width="20%" height="50px" name="pdSpec">OEM委托授权书</td>
                        <td coltype="text" width="20%" height="50px" name="pdSpec"><a title="OEM委托授权书" href="${result.slEnterprise.oemAuthPath}" target="_blank" src="${result.slEnterprise.oemAuthPath}" class="oemAuthPathId"></a></td>
                        <td coltype="text" width="20%" height="60px" name="pdSpec">委托单位：${result.slEnterprise.atuhEpName2}</td>
                    </tr>--%>
                </c:when>
                <c:when test="${result.slSeller.selfFlg==1 and result.slSeller.oemFlg==1}">
                    <tr>
                        <td coltype="text" width="20%" height="50px" name="pdSpec">食品流通许可证</td>
                        <td coltype="text" width="20%" height="150px" name="pdSpec" align="center"><a title="食品流通许可证" href="${result.fdlPath}" target="_blank" src="${result.fdlPath}" id="fdlPathId"  name="imgSrc"></a></td>
                        <td coltype="text" width="20%" height="60px" name="pdSpec">
                            <div style="height:150px;">
                                许可证编码：${result.slEnterprise.fdlNo}<br/>有效期限：${result.fdlTermEnds}
                            </div>
                        </td>
                    </tr>
                    <%--<tr>
                        <td coltype="text" width="20%" height="50px" name="pdSpec">OEM委托授权书</td>
                        <td coltype="text" width="20%" height="150px" name="pdSpec"><a title="OEM委托授权书" href="${result.slEnterprise.oemAuthPath}" target="_blank" src="${result.slEnterprise.oemAuthPath}" class="oemAuthPathId"></a></td>
                        <td coltype="text" width="20%" height="60px" name="pdSpec">委托单位：${result.slEnterprise.atuhEpName2}</td>
                    </tr>--%>
                </c:when>
                <c:when test="${result.slSeller.selfFlg==1 and result.slSeller.agentFlg==1}">
                    <tr>
                        <td coltype="text" width="20%" height="50px" name="pdSpec">食品流通许可证</td>
                        <td coltype="text" width="20%" height="150px" name="pdSpec" align="center"><a title="食品流通许可证" href="${result.fdlPath}" target="_blank" src="${result.fdlPath}" id="fdlPathId"  name="imgSrc"></a></td>
                        <td coltype="text" width="20%" height="60px" name="pdSpec">
                            <div style="height:150px;">
                                许可证编码：${result.slEnterprise.fdlNo}<br/>有效期限：${result.fdlTermEnds}
                            </div>
                        </td>
                    </tr>
                    <%--<tr>
                        <td coltype="text" width="20%" height="50px" name="pdSpec">代理及分销授权书</td>
                        <td coltype="text" width="20%" height="150px" name="pdSpec"><a title="代理及分销授权书" href="${result.authPath}" target="_blank" src="${result.authPath}" class="authPathId"></a></td>
                        <td coltype="text" width="20%" height="60px" name="pdSpec">授权单位：${result.slEnterprise.atuhEpName}</td>
                    </tr>--%>
                </c:when>
                <c:when test="${result.slSeller.agentFlg==1}">
                    <tr>
                        <td coltype="text" width="20%" height="50px" name="pdSpec">食品流通许可证</td>
                        <td coltype="text" width="20%" height="150px" name="pdSpec" align="center"><a title="食品流通许可证" href="${result.fdlPath}" target="_blank" src="${result.fdlPath}" id="fdlPathId"  name="imgSrc"></a></td>
                        <td coltype="text" width="20%" height="60px" name="pdSpec">
                            <div style="height:150px;">
                                许可证编码：${result.slEnterprise.fdlNo}<br/>有效期限：${result.fdlTermEnds}
                            </div>
                        </td>
                    </tr>
                    <%--<tr>
                        <td coltype="text" width="20%" height="50px" name="pdSpec">代理及分销授权书</td>
                        <td coltype="text" width="20%" height="150px" name="pdSpec"><a title="代理及分销授权书" href="${result.slEnterprise.authPath}" target="_blank" src="${result.slEnterprise.authPath}" class="authPathId"></a></td>
                        <td coltype="text" width="20%" height="60px" name="pdSpec">授权单位：${result.slEnterprise.atuhEpName}</td>
                    </tr>--%>
                </c:when>
                <c:when test="${result.slSeller.oemFlg==1}">
                    <tr>
                        <td coltype="text" width="20%" height="50px" name="pdSpec">食品流通许可证</td>
                        <td coltype="text" width="20%" height="150px" name="pdSpec" align="center"><a title="食品流通许可证" href="${result.fdlPath}" target="_blank" src="${result.fdlPath}" id="fdlPathId"  name="imgSrc"></a></td>
                        <td coltype="text" width="20%" height="60px" name="pdSpec">
                            <div style="height:150px;">
                                许可证编码：${result.slEnterprise.fdlNo}<br/>有效期限：${result.fdlTermEnds}
                            </div>
                        </td>
                    </tr>
                    <%-- <tr>
                         <td coltype="text" width="20%" height="50px" name="pdSpec">OEM委托授权书</td>
                         <td coltype="text" width="20%" height="150px" name="pdSpec"><a title="OEM委托授权书" href="${result.slEnterprise.oemAuthPath}" target="_blank" src="${result.slEnterprise.oemAuthPath}" class="oemAuthPathId"></a></td>
                         <td coltype="text" width="20%" height="60px" name="pdSpec">委托单位：${result.slEnterprise.atuhEpName2}</td>
                     </tr>--%>
                </c:when>
            </c:choose>
        </table>
    </div>
</div>
<!--企业专业资质-->
<c:if test="${result.slSeller.slMainClass eq 0 ||result.slSeller.slMainClass eq 1}">
    <div class="group-accordion"  collapsible="true" active="true" width="100%">
        <h3 style="position: relative">
            <label>企业专业资质</label>
        </h3>
        <div  style="padding: 0px; border-bottom: none; border-top: none;">
            <table class="tree dataTable no-footer">
                <thead>
                <tr align="center" style="background:#DBDBDB">
                    <th coltype="text" width="25%" name="pdSpec">证照</th>
                    <th coltype="text" width="25%" name="pdSpec">图片</th>
                    <th coltype="text" width="50%" name="pdSpec">资质描述</th>
                </tr>
                </thead>
                <c:forEach items="${result.certInfoList}" var="certInfo" varStatus="i">
                    <tr>
                        <td coltype="text" width="20%" height="50px">${certInfo.certName}</td>
                        <td coltype="text" width="20%" height="150px" align="center"><a class="tooltip" title="${certInfo.imgUrl}" href="${certInfo.imgUrl}" target="_blank" src="${certInfo.imgUrl}" id="imgUrlId"  name="imgSrc"></a></td>
                        <td coltype="text" width="50%" height="60px">
                            <div style="height:150px;">
                                <c:forEach items="${certInfo.certItemList}" var="certInfos">
                                    ${certInfos.certItemId}、${certInfos.certItemName}：${certInfos.certItemValue}<br/>
                                </c:forEach>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</c:if>
<!--企业基本能力描述-->
<c:if test="${result.slSeller.slMainClass eq 0 ||result.slSeller.slMainClass eq 1}">
    <div class="group-accordion"  collapsible="true" active="true" width="100%">
        <h3 style="position: relative">
            <label>企业基本能力描述</label>
        </h3>
        <div  style="padding: 0px; border-right: none; border-bottom: none; border-top: none;">
            <table class="tree dataTable no-footer">
                <thead>
                <tr align="center" style="background: #DBDBDB">
                    <th coltype="text" width="25%" name="pdSpec">证照</th>
                    <th coltype="text" width="25%" name="pdSpec">图片</th>
                    <th coltype="text" width="50%" name="pdSpec">资质描述</th>
                </tr>
                </thead>
                <!--start -->
                <c:forEach items="${result.slEpHonorList}" var="slEpHonor" varStatus="status">
                    <tr>
                        <c:if test="${status.index eq 0}">
                            <td rowspan="${fn:length(result.slEpHonorList)}" coltype="text" width="25%" height="50px">企业荣誉</td>
                        </c:if>
                        <td coltype="text" width="25%" height="150px" align="center"><a  class="tooltip" title="${slEpHonor.imgUrl}" href="${slEpHonor.imgUrl}" target="_blank" src="${slEpHonor.imgUrl}" id="imgUrlId2"  name="imgSrc"></a></td>
                        <td coltype="text" width="50%" height="50px">
                            <div style="height:150px;">
                                    ${slEpHonor.aptitudeDesc}<br/>${slEpHonor.certIssuer}<br/>${slEpHonor.honorDesc}
                            </div>
                        </td>
                    </tr>
                </c:forEach>
                <!-- end-->
                <tr>
                    <td coltype="text" width="20%" height="50px" name="pdSpec">厂房平面图</td>
                    <td coltype="text" width="25%" height="150px" name="pdSpec" align="center"><a title="厂房平面图" href="${result.slEpCap.workshopImgUrl}" target="_blank" src="${result.slEpCap.workshopImgUrl}" class="tooltip" id="workshopId" name="imgSrc"></a></td>
                    <td coltype="text" width="20%" height="60px" name="pdSpec">
                        <div style="height:150px;">
                                ${result.slEpCap.aptitudeDesc}
                        </div>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</c:if>
<!--企业生产车间、设备、产品工艺流程描述-->
<c:if test="${result.slSeller.slMainClass eq 0 ||result.slSeller.slMainClass eq 1}">
    <div class="group-accordion"  collapsible="true" active="true" width="100%">
    <h3 style="position: relative">
        <label>企业生产车间、设备、产品工艺流程描述</label>
    </h3>
    <div  style="padding: 0px; border-right: none; border-bottom: none; border-top: none;">
        <table class="tree dataTable no-footer">
            <thead>
            <tr align="center" style="background: #DBDBDB">
                <th coltype="text" width="25%" name="pdSpec">证照</th>
                <th coltype="text" width="25%" name="pdSpec">图片</th>
                <th coltype="text" width="50%" name="pdSpec">资质描述</th>
            </tr>
            </thead>
            <!--start -->
            <c:forEach items="${result.slEpWorkshopList}" var="slEpWorkshop" varStatus="status">
                <tr>
                    <c:if test="${status.index eq 0}">
                        <td rowspan="${fn:length(result.slEpWorkshopList)}" coltype="text" width="25%" height="50px">车间概括</td>
                    </c:if>
                    <td coltype="text" width="25%" height="150px" align="center"><a  class="tooltip" title="${slEpWorkshop.imgUrl}" href="${slEpWorkshop.imgUrl}" target="_blank" src="${slEpWorkshop.imgUrl}" id="imgUrId3" name="imgSrc"></a></td>
                    <td coltype="text" width="50%" height="50px">
                        <div style="height:150px;">
                                ${slEpWorkshop.workshopDesc}
                        </div>
                    </td>
                </tr>
            </c:forEach>
            <!-- end-->
            <tr>
                <td coltype="text" width="20%" height="50px" name="pdSpec">库容概括</td>
                <td coltype="text" width="25%" height="150px" name="pdSpec"  align="center"><a title="库容概括" href="${result.slEpCap.warehouseImgUrl}" target="_blank" src="${result.slEpCap.workshopImgUrl}" class="tooltip" id="workshopImgUrlId" name="imgSrc"></a></td>
                <td coltype="text" width="20%" height="60px" name="pdSpec">
                    <div style="height:150px;">
                            ${result.slEpCap.scapDesc}
                    </div>
                </td>
            </tr>
        </table>
    </div>
    </div>
</c:if>
<!--企业实验室、检测设备及产品质量控制系统描述-->
<c:if test="${result.slSeller.slMainClass eq 0 ||result.slSeller.slMainClass eq 1}">
    <div class="group-accordion"  collapsible="true" active="true" width="100%">
        <h3 style="position: relative">
            <label>企业实验室、检测设备及产品质量控制系统描述</label>
        </h3>
        <div  style="padding: 0px; border-right: none; border-bottom: none; border-top: none;">
            <table class="tree dataTable no-footer">
                <thead>
                <tr align="center" style="background:#DBDBDB">
                    <th coltype="text" width="25%" name="pdSpec">证照</th>
                    <th coltype="text" width="25%" name="pdSpec">图片</th>
                    <th coltype="text" width="50%" name="pdSpec">资质描述</th>
                </tr>
                </thead>
                <tr>
                    <td coltype="text" width="20%" height="50px" >实验室</td>
                    <td coltype="text" width="20%" height="150px" align="center">
                        <a  class="tooltip" title="${result.slEpCap.labImgUrl}" href="${result.slEpCap.labImgUrl}" target="_blank" src="${result.slEpCap.labImgUrl}" id="labImgUrlId" name="imgSrc">
                            </a></td>
                    <td coltype="text" width="20%" height="60px" name="S00113">
                        <div style="height:150px;">
                                ${result.slEpCap.aptitudeDesc2}
                        </div>
                    </td>
                </tr>
                <tr>
                    <td coltype="text" width="20%" height="50px">检测设备</td>
                    <td coltype="text" width="20%" height="150px"  align="center">
                        <a  class="tooltip" title="${result.slEpCap.ddEquipmentImgUrl}" href="${result.slEpCap.ddEquipmentImgUrl}" target="_blank" src="${result.slEpCap.ddEquipmentImgUrl}" id="ddEquipmentImgUrlId" name="imgSrc">
                            </a></td>
                    <td coltype="text" width="20%" height="60px" name="pdSpec">
                        <div style="height:150px;">
                                ${result.slEpCap.aptitudeDesc3}
                        </div>
                    </td>
                </tr>
                <tr>
                    <td coltype="text" width="20%" height="50px">品控组织架构</td>
                    <td coltype="text" width="20%" height="150px"  align="center">
                        <a  class="tooltip" title="${result.slEpCap.controllerImgUrl}" href="${result.slEpCap.controllerImgUrl}" target="_blank" src="${result.slEpCap.controllerImgUrl}" id="controllerImgUrlId" name="imgSrc">
                        </a></td>
                    <td coltype="text" width="20%" height="60px" name="pdSpec"></td>
                </tr>
                <tr>
                    <td coltype="text" width="20%" height="50px">质量控制系统图</td>
                    <td coltype="text" width="20%" height="150px" align="center">
                        <a  class="tooltip" title="${result.slEpCap.qualityImgUrl}" href="${result.slEpCap.qualityImgUrl}" target="_blank" src="${result.slEpCap.qualityImgUrl}" id="qualityImgUrlId" name="imgSrc">
                        </a></td>
                    <td coltype="text" width="20%" height="60px" name="pdSpec"></td>
                </tr>
            </table>
        </div>
    </div>
</c:if>
<!--检测设备描述-->
<c:if test="${result.slSeller.slMainClass eq 0 ||result.slSeller.slMainClass eq 1}">
    <div class="group-accordion"  collapsible="true" active="true" width="100%">
        <h3 style="position: relative">
            <label>检测设备描述</label>
        </h3>
        <div  style="padding: 0px;border-right: none; border-bottom: none; border-top: none;">
            <table class="tree dataTable no-footer">
                <thead>
                <tr align="center" style="background: #DBDBDB">
                    <th coltype="text" width="25%" name="pdSpec">证照</th>
                    <th coltype="text" width="25%" name="pdSpec">图片</th>
                    <th coltype="text" width="50%" name="pdSpec">资质描述</th>
                </tr>
                </thead>
                <!--start -->
                <c:forEach items="${result.slEpDdList}" var="slEpDd" varStatus="status">
                    <tr>
                        <c:if test="${status.index eq 0}">
                            <td rowspan="${fn:length(result.slEpDdList)}" coltype="text" width="25%" height="50px">检测设备</td>
                        </c:if>
                        <td coltype="text" width="25%" height="150px" align="center"> <a  class="tooltip" title="${slEpDd.slEpDdPath}" href="${slEpDd.slEpDdPath}" target="_blank" src="${slEpDd.slEpDdPath}" id="slEpDdPathId" name="imgSrc"></a></td>
                        <td coltype="text" width="50%" height="50px">
                            <div style="height:150px;">
                                设备名称：${slEpDd.ddName}<br/>
                                设备用途：${slEpDd.ddEquipment}
                            </div>
                        </td>
                    </tr>
                </c:forEach>
                <!-- end-->
            </table>
        </div>
    </div>
</c:if>
<!--企业管理团队描述-->
<c:if test="${result.slSeller.slMainClass eq 0 ||result.slSeller.slMainClass eq 1}">
    <div class="group-accordion"  collapsible="true" active="true" width="100%">
        <h3 style="position: relative">
            <label>企业管理团队描述</label>
        </h3>
        <div  style="padding: 0px;border-right: none; border-bottom: none; border-top: none;">
            <table class="tree dataTable no-footer">
                <thead>
                <tr align="center" style="background:#DBDBDB">
                    <th coltype="text" width="25%" name="pdSpec">证照</th>
                    <th coltype="text" width="25%" name="pdSpec">图片</th>
                    <th coltype="text" width="50%" name="pdSpec">资质描述</th>
                </tr>
                </thead>
                <c:forEach items="${result.slEpManagerList}" var="list" varStatus="i">
                    <tr>
                        <td coltype="text" width="20%" height="50px" name="pdSpec">${list.memberDuties}</td>
                        <td coltype="text" width="20%" height="150px" name="s0014" align="center"><a  class="tooltip" title="${list.memberDuties}" href="${list.image}" target="_blank" src="${list.image}" id="imageId" name="imgSrc"></a></td>
                        <td coltype="text" width="20%" height="60px" name="S00113">
                            <div style="height:150px;">
                                1.姓名：${list.memberName}<br>  2.年龄：${list.memberAge}岁<br> 3.联系方式：${list.memberTel}<br> 4.文化程度：${list.memberEduc}
                            </div>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</c:if>
<!--企业电商团队描述-->
<div class="group-accordion"  collapsible="true" active="true" width="100%">
    <h3 style="position: relative">
        <label>企业电商团队描述</label>
    </h3>
    <div  style="padding: 0px; border-right: none; border-bottom: none; border-top: none;">
        <table class="tree dataTable no-footer">
            <thead>
            <tr align="center" style="background: #DBDBDB">
                <th coltype="text" width="25%" name="pdSpec">证照</th>
                <th coltype="text" width="25%" name="pdSpec">图片</th>
                <th coltype="text" width="50%" name="pdSpec">资质描述</th>
            </tr>
            </thead>
            <tr>
                <td coltype="text" width="20%" height="50px" name="pdSpec">电商团队负责人</td>
                <td coltype="text" width="20%" height="150px" name="s0014" align="center"><a
                         class="tooltip" title="电商团队负责人"
                        href="${teamLeader.imagePath}"
                        target="_blank"
                        src="${teamLeader.imagePath}" id="imagePathId" name="imgSrc"></a></td>
                <td coltype="text" width="20%" height="60px" name="S00113">
                    <div style="height:150px;">
                        1、姓名：${teamLeader.memberName} <br />2、年龄：${teamLeader.memberAge}<br />3、文化程度：${teamLeader.memberEduc}<br />4、联系方式：${teamLeader.memberTel}
                    </div>

                </td>
            </tr>

            <!--start -->
            <c:forEach items="${slEcTeamList}" var="slEcTeam" varStatus="status">
                <tr>
                    <c:if test="${status.index eq 0}">
                        <td rowspan="${fn:length(slEcTeamList)}" coltype="text" width="25%" height="50px">电商团队成员</td>
                    </c:if>
                    <td coltype="text" width="25%" height="150px" align="center"><a  class="tooltip" title="电商团队成员" href="${slEcTeam.imagePath}" target="_blank" src="${slEcTeam.imagePath}" id="imagePathId2" name="imgSrc"></a></td>
                    <td coltype="text" width="50%" height="50px">
                        <div style="height:150px;">
                                ${slEcTeam.noLeaderDeil}
                        </div>
                    </td>
                </tr>
            </c:forEach>
            <!-- end-->
        </table>
    </div>
</div>
<!--企业产品品牌描述-->
<c:if test="${result.slSeller.slMainClass ne 2 }">
    <div class="group-accordion"  collapsible="true" active="true" width="100%">
        <h3 style="position: relative">
            <label>企业产品品牌描述</label>
        </h3>
        <div  style="padding: 0px; border-right: none; border-bottom: none; border-top: none;">
            <table class="tree dataTable no-footer">
                <thead>
                <tr align="center" style="background: #DBDBDB">
                    <th coltype="text" width="25%" name="pdSpec">证照</th>
                    <th coltype="text" width="25%" name="pdSpec">图片</th>
                    <th coltype="text" width="50%" name="pdSpec">资质描述</th>
                </tr>
                </thead>
                <!--品牌证书-->
                <c:forEach items="${slEpBrandList}" var="list">
                    <tr>
                        <td coltype="text" width="20%" height="50px" name="pdSpec">品牌证书</td>
                        <td coltype="text" width="20%" height="150px" name="s0014" align="center"><a  class="tooltip" title="品牌证书" href="${list.brandPath}" target="_blank" src="${list.brandPath}" id="brandPathId" name="imgSrc"></a></td>

                        <td coltype="text" width="20%" height="60px" name="S00113">
                            <div style="height:150px;">
                                1.品牌名称：${list.brandName}
                                <br> 2.商标注册证：${list.brandNo}
                                <br> 3.所属企业：${list.epName}
                            </div>
                        </td>
                    </tr>
                    <!--包装-->
                    <tr>
                        <td coltype="text" width="20%" height="50px" name="pdSpec">包装图片</td>
                        <td coltype="text" width="20%" height="150px" name="s0014" align="center"><a  class="tooltip" title="包装图片" href="${list.brandPacPath}" target="_blank" src="${list.brandPacPath}" id="brandPacPathId" name="imgSrc"></a></td>
                        <td coltype="text" width="20%" height="60px" name="S00113"></td>
                    </tr>
                    <!--品牌荣誉-->
                    <c:forEach items="${list.slEpBrandHonorList}" var="honorList">
                        <tr>
                            <td coltype="text" width="20%" height="50px" name="pdSpec">品牌荣誉</td>
                            <td coltype="text" width="20%" height="150px" name="s0014" align="center"><a  class="tooltip" title="品牌荣誉" href="${honorList.brandHonorPath}" target="_blank" src="${honorList.brandHonorPath}" id="brandHonorPathId" name="imgSrc"></a></td>
                            <td coltype="text" width="20%" height="60px" name="S00113">
                                <div style="height:150px;">
                                    1.证书编码：${honorList.honorNo}
                                    <br> 2.发证单位：${honorList.certIssuer}
                                    <br>3.发证时间：${honorList.crtDateStr}
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                </c:forEach>
            </table>
        </div>
    </div>
</c:if>
    <!--生产商列表-->
    <c:if test="${hasProducers}">
    <div class="group-accordion"  collapsible="true" active="true">
        <h3>
            <label>生产商列表</label>
        </h3>
        <form action="<c:url value="/SSC11328/searchProducers"/>" method="post">
            <input type="hidden" name="slCode" value="${result.slSeller.slCode}">
            <div width="100%" style="border-top: none;">
                    <table width="100%" id="SSC11328_list_grid_epProducers1">
                        <thead>
                        <tr height="10px">
                            <th coltype="text" width="21%" name="slCodeManufacture">生产商编码</th>
                            <th coltype="text" width="21%" name="epName">生产商名称</th>
                            <th coltype="text" width="21%" name="onTime">授权期限</th>
                            <th coltype="text" width="21%" name="slAreaCode">行政区划</th>
                            <th coltype="text" width="21%" name="licAddr">生产商地址</th>
                            <th coltype="action" width="8%">授权证书
                                <msk:button buttonType="hidden" buttonId="SSC11328.SEARCHBTN" coltype="search" buttonValue="授权证书" class="h-button"/>
                            </th>
                            <th coltype="action">企业资质
                                <msk:button buttonType="hidden" buttonId="SSC11328.REFRESHBTN" coltype="refresh" buttonValue="企业资质" class="h-button"/>
                            </th>
                        </tr>
                        </thead>
                        <tbody></tbody>
                    </table>
                    <table width="100%" id="SSC11328_list_grid_epProducers2">
                        <thead>
                        <tr height="10px">
                            <th coltype="text" width="21%" name="slCodeManufacture">生产商编码</th>
                            <th coltype="text" width="21%" name="epName">生产商名称</th>
                            <th coltype="text" width="21%" name="onTime">授权期限</th>
                            <th coltype="text" width="21%" name="slAreaCode">行政区划</th>
                            <th coltype="text" width="21%" name="licAddr">生产商地址</th>
                            <th coltype="action" width="8%">授权证书
                                <msk:button buttonType="hidden" buttonId="SSC11328.SEARCHBTN" coltype="search" buttonValue="授权证书" class="h-button"/>
                            </th>
                            <th coltype="action">企业资质
                            </th>
                        </tr>
                        </thead>
                        <tbody></tbody>
                    </table>
            </div>
        </form>
    </div>
    </c:if>
    <!--代理/OEM卖家列表-->
    <c:if test="${hasSellers}">
    <div class="group-accordion"  collapsible="true" active="true">
        <h3>
            <label>代理/OEM卖家列表</label>
        </h3>
        <form action="<c:url value="/SSC11328/searchSellers"/>" method="post">
            <input type="hidden" name="slCode" value="${result.slSeller.slCode}">
            <div width="100%" style="border-top: none;">
                <table width="100%" id="SSC11328_list_grid_epSellers1">
                    <thead>
                    <tr height="10px">
                        <th coltype="text" width="21%" name="slCodeDis">卖家编码</th>
                        <th coltype="text" width="21%" name="epName">代理/OEM卖家名称</th>
                        <th coltype="text" width="21%" name="onTime">授权期限</th>
                        <th coltype="text" width="21%" name="slAreaCode">行政区划</th>
                        <th coltype="text" width="21%" name="licAddr">生产商地址</th>
                        <th coltype="action" width="8%">授权证书
                            <msk:button buttonType="hidden" buttonId="SSC11328.SEARCHBTN" coltype="search" buttonValue="授权证书" class="h-button"/>
                        </th>
                        <th coltype="action">企业资质
                            <msk:button buttonType="hidden" buttonId="SSC11328.REFRESHBTN" coltype="refresh" buttonValue="企业资质" class="h-button"/>
                        </th>
                    </tr>
                    </thead>
                    <tbody></tbody>
                </table>
                <table width="100%" id="SSC11328_list_grid_epSellers2">
                    <thead>
                    <tr height="10px">
                        <th coltype="text" width="21%" name="slCodeDis">卖家编码</th>
                        <th coltype="text" width="21%" name="epName">代理/OEM卖家名称</th>
                        <th coltype="text" width="21%" name="onTime">授权期限</th>
                        <th coltype="text" width="21%" name="slAreaCode">行政区划</th>
                        <th coltype="text" width="21%" name="licAddr">生产商地址</th>
                        <th coltype="action" width="8%">授权证书
                            <msk:button buttonType="hidden" buttonId="SSC11328.SEARCHBTN" coltype="search" buttonValue="授权证书" class="h-button"/>
                        </th>
                        <th coltype="action">企业资质
                        </th>
                    </tr>
                    </thead>
                    <tbody></tbody>
                </table>
            </div>
        </form>
    </div>
    </c:if>
</div>
<script src="<c:url value="/static/js/ssc/SSC11328.js" /> "></script>


