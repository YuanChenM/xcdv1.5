<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<%@ taglib prefix="msk1" uri="/codemaster" %>
<script type="text/javascript" src="${ctx}/static/upload/fileupload.js"></script>
<msk1:codemaster codeType="SalesTarget" viewType="json"/>
<%--<msk1:codemaster codeType="ReceivePeriodType" viewType="json"/>--%>
<navigation:header title="新增买家" backTitleArray="买家列表" backUrlArray="${ctx}/BY121303/init/"></navigation:header>
<input type="hidden" value="${buyerId}" name="buyerId" id="buyerId"/>

<div class="page-content list-page">
    <form:form id="BY121307Form" action="${ctx}/BY121307/save/${buyerId}" method="post" enctype="multipart/form-data">

        <input type="hidden" value=" " name="buyerBasicInfo.cityName" id="cityName"/>
        <input type="hidden" value=" " name="buyerBasicInfo.districtName" id="districtName"/>
        <input type="hidden" value=" " name="buyerBasicInfo.paymentTypeName" id="paymentTypeName"/>
        <input type="hidden" value=" " name="buyerBasicInfo.marketingsStatusName" id="marketingsStatusName"/>
        <input type="hidden" value=" " name="buyerBasicInfo.superiorName" id="superiorName"/>
        <input type="hidden" value=" " name="buyerLicence.licDesc" id="licDes"/>
        <input type="hidden" value=" " name="buyerLicence.legalLicDesc" id="legalLicDes"/>

        <div class="group-accordion" collapsible="true" active="true">
            <h3>
                <label>买家类型</label>
            </h3>
            <table style="width: 100%;" CellSpacing=8>
                <tr>
                    <td width="12.5%" align="right"><span style="color:red;display: inline; vertical-align: sub">*&nbsp;</span>买家类型 : </td>
                    <td width="12.5%" align="left">
                        <select id="superiorType" name="buyerBasicInfo.superiorType">
                            <option value="">--请选择--</option>
                            <c:forEach items="${buyerTypeList}" var="buyerType">
                                <option value="${buyerType.key}" dd="">${buyerType.value}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td id="secondType" style="display:none" width="12.5%" align="right"><span style="color:red;display: inline; vertical-align: sub">*&nbsp;</span>二级类型 : </td>
                    <td id="secondTypeSelect" style="display:none" width="12.5%" align="left">
                        <select id="buyerTypeId" name="buyerBasicInfo.superiorSubType">
                            <option value="">--请选择--</option>
                        </select>
                    </td>
                    <td width="12.5%" align="right">
                        <div id="isFoodMarket" style="display: none">
                            <input type="checkbox" class="isFoodMarket" value="1"
                                   name="buyerBasicInfo.isMarketFlg"/><span style="color:red;display: inline; vertical-align: sub">*&nbsp;</span>菜场
                        </div>
                    </td>
                    <td width="12.5%" align="left"><input type="hidden" id="superiorSubName"
                                                          name="buyerBasicInfo.superiorSubName"></td>
                    <td width="12.5%" align="right"></td>
                    <td width="12.5%" align="left"></td>
                </tr>
                <tr>
                    <td width="12.5%" align="right"><span style="color:red;display: inline; vertical-align: sub">*&nbsp;</span>物流区 : </td>
                    <td width="12.5%" align="left">
                        <select id="lgcsAreaCode" name="buyerBasicInfo.lgcsAreaCode">
                            <option value="">--请选择--</option>
                            <c:forEach items="${logisticsAreaList}" var="logisticsArea">
                                <option value="${logisticsArea.lgcsAreaCode}">${logisticsArea.lgcsAreaName}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <input type="hidden" value=" " name="buyerBasicInfo.lgcsAreaName" id="lgcsAreaName"/>
                    <td width="12.5%" align="right"><span style="color:red;display: inline; vertical-align: sub">*&nbsp;</span>地区(城市) : </td>
                    <td width="12.5%" align="left">
                        <select id="cityCode" name="buyerBasicInfo.cityCode">
                            <option value="">--请选择--</option>
                        </select>
                    </td>
                    <td width="12.5%" align="right"><span style="color:red;display: inline; vertical-align: sub">*&nbsp;</span>区(县) : </td>
                    <td width="12.5%" align="left">
                        <select id="districtCode" name="buyerBasicInfo.districtCode">
                            <option value="">--请选择--</option>
                        </select>
                    </td>
                    <td width="12.5%"/>
                    <td width="12.5%"/>
                </tr>
                <tr>
                    <td width="12.5%" align="right">
                        <span id="superiorDec"><span style="color:red;display: inline; vertical-align: sub">*&nbsp;</span>批发市场 : </span>
                    </td>
                    <td width="12.5%" align="left">
                        <select id="superiorId" name="buyerBasicInfo.superiorId">
                            <option value="">--请选择--</option>
                        </select>
                    </td>
                    <td width="12.5%" align="right">
                        <span  id="superiorQuaDec">批发市场等级 : </span>
                    </td>
                    <td width="12.5%" align="left">
                        <input value="" name="buyerBasicInfo.superiorQua" id="superiorQua" readonly />
                    </td>
                    <td width="12.5%"></td>
                    <td width="12.5%"></td>
                    <td width="12.5%"></td>
                    <td width="12.5%"></td>
                </tr>
            </table>
        </div>
        <div class="group-accordion" collapsible="true" active="true">
            <h3>
                <label>买家基本信息</label>
            </h3>
            <table style="width: 100%;" CellSpacing=8>
                <tr>
                    <td width="12.5%" align="right"><span style="color:red;display: inline; vertical-align: sub">*&nbsp;</span>买家名称 : </td>
                    <td width="12.5%" align="left"><input type="text" value="" id="buyerName" required="true"
                                                          requiredMessage="买家名称不能为空"
                                                          name="buyerBasicInfo.buyerName" maxlength="50"/></td>
                    <td width="12.5%" align="right"><span style="color:red;display: inline; vertical-align: sub">*&nbsp;</span>买家地址 : </td>
                    <td width="12.5%" align="left"><input type="text" value="" id="buyerAddr" required="true"
                                                          requiredMessage="买家地址不能为空"
                                                          name="buyerBasicInfo.buyerAddr" maxlength="100"/></td>
                    <td width="12.5%" align="right"></td>
                    <td width="12.5%" align="left"></td>
                    <td width="12.5%" align="right"></td>
                    <td width="12.5%" align="left"></td>
                </tr>
                <tr>
                    <td width="12.5%" align="right">店铺号 : </td>
                    <td width="12.5%" align="left"><input type="text" value="" id="storeNo"
                                                          name="buyerBasicInfo.storeNo" maxlength="50"/></td>
                    <td width="12.5%" align="right">营业电话 : </td>
                    <td width="12.5%" align="left"><input type="text" value="" id="busiTel"
                                                          name="buyerBasicInfo.busiTel" maxlength="50" digits="true"
                                                          digitsMessage="营业电话为数字类型" min="0"/></td>
                    <td width="12.5%" align="right">员工人数 : </td>
                    <td width="12.5%" align="left"><input type="text" value="" id="employeesNum"
                                                          name="buyerBasicInfo.employeesNum" digits="true"
                                                          digitsMessage="员工人数是正整数" min="0" max="2147483647"
                                                          maxMessage="员工人数超出范围限制"/></td>
                </tr>
                <tr>
                    <td width="12.5%" align="right">买家网站 : </td>
                    <td width="12.5%" align="left"><input type="url" value="" id="buyerWebsite"
                                                          name="buyerBasicInfo.buyerWebsite" maxlength="100"
                                                          urlMessage="买家网站输入错误"/></td>
                    <td width="12.5%" align="right">买家微信公众号 : </td>
                    <td width="12.5%" align="left"><input type="text" value="" id="buyerWechat"
                                                          name="buyerBasicInfo.buyerWechat" maxlength="50"/></td>
                    <td width="12.5%" align="right">买家QQ号 : </td>
                        <%--Modif for Bug#2638 at 2016/09/13 by yuan_zhifei Start--%>
                    <td width="12.5%" align="left"><input type="text" value="" id="buyerQq"
                                                          name="buyerBasicInfo.buyerQq" maxlength="20" digits="true"
                                                          digitsMessage="买家QQ号为数字类型" min="0"/></td>
                        <%--Modif for Bug#2638 at 2016/09/13 by yuan_zhifei End--%>
                    <td width="12.5%" align="right">买家微信号 : </td>
                        <%--Modif for Bug#2785 at 2016/09/19 by yuan_zhifei Start--%>
                    <td width="12.5%" align="left"><input type="text" value="" id="buyerSingleWechat"
                                                          name="buyerBasicInfo.buyerSingleWechat" maxlength="50"/></td>
                        <%--Modif for Bug#2785 at 2016/09/19 by yuan_zhifei End--%>
                </tr>
            </table>
        </div>
        <div class="group-accordion" collapsible="true" active="true">
            <h3>
                <label>买家池归属</label>
            </h3>
            <table style="width:100%;" id="checkTable">
                <c:forEach items="${claAndMachinList}" var="pdCla">
                    <tr>
                        <td width="1%"/>
                        <td width="10%" class="buyerClaId">
                            <input type="checkbox" id="buyerPdCla${pdCla.classCode}" name="buyerPdCla"
                                   value="${pdCla.classCode}"/>${pdCla.className}
                        </td>
                        <td width="1%" class="td_css">
                                ${pdCla.className}
                        </td>
                        <c:forEach items="${pdCla.pdMachiningList}" var="pdc">
                            <td class="td_css" width="2%">
                                <input type="checkbox" id="buyerPdMac${pdCla.classCode}${pdc.machiningCode}"
                                       name="buyerPdMac" value="${pdc.machiningCode}_${pdCla.classCode}"
                                       bylClassCode="${byl.classCode}"/>${pdc.machiningName}
                            </td>
                        </c:forEach>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <div class="group-accordion" collapsible="false" active="true">
            <h3>
                <label>买家证照信息</label>
            </h3>
            <table style="width: 100%;" CellSpacing=8>
                <tr>
                    <td width="12.5%" align="right">营业执照类型 : </td>
                    <td width="12.5%" align="left">
                        <msk1:codemaster codeType="LicType" viewType="select" id="licName" name="buyerLicence.licName"/>
                    </td>
                    <td width="12.5%" align="right">营业执照号码 : </td>
                    <td width="12.5%" align="left">
                        <input type="text" id="licNumber" name="buyerLicence.licNumber" value="" maxlength="20"
                               digits="true" digitsMessage="营业执照号码书数字类型" min="0">
                    </td>
                    <td width="12.5%"/>
                    <td width="12.5%"/>
                    <td width="12.5%"/>
                    <td width="12.5%"/>
                </tr>
                <tr>
                    <td width="12.5%" align="right">法定代表人姓名 : </td>
                    <td width="12.5%" align="left">
                        <input type="text" id="legalName" name="buyerLicence.legalName" value="" maxlength="20"></td>
                    <td width="12.5%" align="right">法定代表人证件类型 : </td>
                    <td width="12.5%" align="left">
                        <msk1:codemaster codeType="LegalLicType" viewType="select" id="legalLicType"
                                         name="buyerLicence.legalLicType"/>
                    </td>
                    <td width="12.5%" align="right">法定代表人证件号码 : </td>
                    <td width="12.5%" align="left">
                        <input type="text" id="legalLicNumber" name="buyerLicence.legalLicNumber" value=""
                               maxlength="20" digits="true" digitsMessage="法定代表人证件号码为数字类型" min="0"></td>
                    </td>
                    <td width="12.5%"/>
                    <td width="12.5%"/>
                </tr>
                <tr>
                    <td width="12.5%" align="right">营业执照 : </td>
                    <td width="12.5%" align="left">
                        <msk:uploadFile fileLinkId="busLicPicId" fileName="busLicPicName" fileSize="5"
                                        fileSizeMessage="营业执照文件不能大于5M" fileType="png,jpg,jpeg,bmp,gif,PNG,JPG,JPEG,BMP,GIF"
                                        fileTypeMessage="营业执照文件格式错误" uploadButtonId="BY121307_UPLOAD"
                                        callbackFunction="BY121307CallBack"></msk:uploadFile>
                    </td>
                    <td width="12.5%" align="right">组织机构代码证 : </td>
                    <td width="12.5%" align="left">
                        <msk:uploadFile fileLinkId="orgCertificatePicId" fileName="orgCertificatePicName" fileSize="5"
                                        fileSizeMessage="组织机构代码证文件不能大于5M" fileType="png,jpg,jpeg,bmp,gif,PNG,JPG,JPEG,BMP,GIF"
                                        fileTypeMessage="组织机构代码证文件格式错误" uploadButtonId="BY121307_UPLOAD"
                                        callbackFunction="BY121307CallBack" bindUpload="false"></msk:uploadFile>
                    </td>
                    <td width="12.5%"></td>
                    <td width="12.5%"></td>
                    <td width="12.5%"></td>
                    <td width="12.5%"></td>
                </tr>
                <tr>
                    <td width="12.5%" align="right">税务登记证 : </td>
                    <td width="12.5%" align="left">
                        <msk:uploadFile fileLinkId="taxCertificatePicId" fileName="taxCertificatePicName" fileSize="5"
                                        fileSizeMessage="税务登记证文件不能大于5M" fileType="png,jpg,jpeg,bmp,gif,PNG,JPG,JPEG,BMP,GIF"
                                        fileTypeMessage="税务登记证文件格式错误" uploadButtonId="BY121307_UPLOAD"
                                        callbackFunction="BY121307CallBack" bindUpload="false"></msk:uploadFile>
                    </td>
                    <td width="12.5%" align="right">食品流通许可证 : </td>
                    <td width="12.5%">
                        <msk:uploadFile fileLinkId="foodCertificatePicId" fileName="foodCertificatePicName" fileSize="5"
                                        fileSizeMessage="食品流通许可证文件不能大于5M" fileType="png,jpg,jpeg,bmp,gif,PNG,JPG,JPEG,BMP,GIF"
                                        fileTypeMessage="食品流通许可证文件格式错误" uploadButtonId="BY121307_UPLOAD"
                                        callbackFunction="BY121307CallBack" bindUpload="false"></msk:uploadFile>
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
                                        fileSize="5" fileSizeMessage="法定代表人证文件不能大于5M"
                                        fileType="png,jpg,jpeg,bmp,gif,PNG,JPG,JPEG,BMP,GIF" fileTypeMessage="法定代表人证文件格式错误"
                                        uploadButtonId="BY121307_UPLOAD" callbackFunction="BY121307CallBack"
                                        bindUpload="false"></msk:uploadFile>
                    </td>
                    <td width="12.5%"></td>
                    <td width="12.5%"></td>
                    <td width="12.5%"></td>
                    <td width="12.5%"></td>
                    <td width="12.5%"></td>
                    <td width="12.5%"></td>
                </tr>
            </table>
        </div>
    </form:form>
    <div class="group-accordion" collapsible="true" active="true">
        <h3>
            <label>买家雇员信息</label>
        </h3>

        <form action="${ctx}/BY121304/search/${buyerId}" method="post">
            <table id="BY121307_Grid">
                <thead>
                <tr>
                    <th coltype="sno" width="20px">编号</th>
                    <th coltype="text" name="employeeTypeName" filter=false>员工类型</th>
                    <th coltype="text" name="employeeName" filter=true>员工姓名</th>
                    <th coltype="text" name="employeeTel" filter=true>手机号</th>
                    <th coltype="text" name="employeeQq" filter=true>QQ号</th>
                    <th coltype="text" name="employeeWechat" filter=true>微信号</th>
                    <th coltype="text" name="busCardFlgName" filter=false>有无名片照</th>
                    <th coltype="text" name="contactPersonName" filter=false>是否联络人</th>
                    <th coltype="text" name="purchaseName" filter=false>是否采购人</th>
                    <th coltype="text" name="receivePersonName" filter=false>是否收货人</th>
                </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
            <msk:button buttonValue="新增" buttonId="BY121307.ADD" buttonType="button"/>
        </form>
    </div>
    <msk:button buttonValue="完成" buttonId="BY121307.SAVE" buttonType="button"/>
    <msk:button buttonValue="上传" buttonId="BY121307.UPLOAD" buttonType="hidden"/>
</div>
<script type="text/javascript" src="${ctx}/static/buyers/js/BY121307.js"></script>