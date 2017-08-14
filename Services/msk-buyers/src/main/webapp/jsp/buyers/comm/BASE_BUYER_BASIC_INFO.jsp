<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--<%@ taglib prefix="msk" uri="/msk" %>--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<div class="group-accordion" collapsible="true" active="true">
    <h3>
        <label>买家类型</label>
    </h3>
    <form:form id="buyerTypeForm" metdod="post" enctype="multipart/form-data">
    <input type="hidden" name="buyerId" id="buyerId" value="${buyerId}">
        <input type="hidden" value=" " name="lgcsAreaName" id="lgcsAreaName"/>
        <input type="hidden" value=" " name="cityName" id="cityName"/>
        <input type="hidden" value=" " name="districtName" id="districtName"/>
        <input type="hidden" value=" " name="superiorName" id="superiorName"/>
    <table id="buyerTypeShow" style="width: 100%;" CellSpacing=8>
        <tr>
            <td width="12.5%" align="right"><span style="color:red;display: inline; vertical-align: sub">*&nbsp;</span>买家类型 : </td>
            <td width="12.5%" align="left">${buyerDetail.superiorName}</td>
            <c:if test="${buyerDetail.superiorType eq '05' || buyerDetail.superiorType eq '06'}">
                <td width="12.5%" align="right"><span style="color:red;display: inline; vertical-align: sub">*&nbsp;</span>二级类型 : </td>
                <td width="12.5%" align="left">${buyerDetail.superiorSubName}</td>
                <td width="12.5%" align="right">
                    <c:if test="${buyerDetail.superiorType eq '05' && buyerDetail.superiorSubType eq '01'}">
                        <input class="isFoodMarket" type="checkbox" value="1" disabled  editmodel = "false"<c:if test="${buyerDetail.superiorSubType=='01'}">checked="true" </c:if>/><span style="color:red;display: inline; vertical-align: sub">*&nbsp;</span>菜场
                    </c:if>
                </td>
            </c:if>
            <td width="12.5%" align="left"></td>
            <td width="12.5%" align="right"></td>
            <td width="12.5%" align="left"></td>
        </tr>
        <tr>
            <td width="12.5%" align="right"><span style="color:red;display: inline; vertical-align: sub">*&nbsp;</span>物流区 : </td>
            <td width="12.5%" align="left">${buyerDetail.lgcsAreaName}</td>
            <td width="12.5%" align="right"><span style="color:red;display: inline; vertical-align: sub">*&nbsp;</span>地区(城市) : </td>
            <td width="12.5%" align="left">${buyerDetail.cityName}</td>
            <td width="12.5%" align="right"><span style="color:red;display: inline; vertical-align: sub">*&nbsp;</span>区(县) :
            </td>
            <td width="12.5%" align="left">${buyerDetail.districtName}</td>
            <td width="12.5%"/>
            <td width="12.5%"/>
        </tr>
        <tr>
            <c:choose>
                <c:when test="${buyerDetail.superiorType eq '01'}">
                    <td width="12.5%" align="right"><span style="color:red;display: inline; vertical-align: sub">*&nbsp;</span>批发市场 : </td>
                    <td width="12.5%" align="left">${buyerDetail.superiorIdName}</td>
                    <td width="12.5%" align="right">批发市场等级 : </td>
                    <td width="12.5%" align="left">${buyerDetail.superiorQua}</td>
                </c:when>
                <c:when test="${buyerDetail.superiorType eq '02'||((buyerDetail.superiorType eq '05')&&(buyerDetail.superiorSubType=='01'))}">
                    <td width="12.5%" align="right"><span style="color:red;display: inline; vertical-align: sub">*&nbsp;</span>菜场:</td>
                    <td width="12.5%" align="left">${buyerDetail.superiorIdName}</td>
                    <td width="12.5%" align="right">菜场类型 : </td>
                    <td width="12.5%" align="left">${buyerDetail.superiorQua}</td>
                </c:when>
                <c:otherwise>
                    <td width="12.5%" align="right"></td>
                    <td width="12.5%" align="left"></td>
                    <td width="12.5%" align="right"></td>
                    <td width="12.5%" align="left"></td>
                </c:otherwise>
            </c:choose>
            <td width="12.5%" align="right"></td>
            <td width="12.5%" align="left"></td>
            <td width="12.5%" align="right"></td>
            <td width="12.5%" align="left"></td>
        </tr>
        <%--<tr>
            <td width="12.5%" align="right">买家池归属:<span style="color:red">*</span></td>
            <td colspan="7" width="87.5%" align="left">
                <c:forEach items="${pdClaList}" var="pdCla">
                    ${pdCla.className}
                </c:forEach>
            </td>
        </tr>
        <c:if test="${buyerDetail.superiorType == '01'}">
            <tr>
                <td width="12.5%" align="right">销售二级产品:<span style="color:red">*</span></td>
                <td colspan="7" width="87.5%" align="left">
                    <c:forEach items="${pdMacList}" var="pdMac">
                        ${pdMac}
                    </c:forEach>
                </td>
            </tr>
        </c:if>--%>
        <tr>
            <td width="12.5%" align="right">
                <msk:button buttonValue="编辑" buttonId="BY121304.BUYERTYPE.EDIT" buttonType="button"/>
            </td>
        </tr>
        <tr></tr>
        <tr></tr>
        <tr></tr>
        <tr></tr>
        <tr></tr>
        <tr></tr>
    </table>
    <table id="buyerTypeEdit" style="width: 100%;display: none;" CellSpacing=8>
        <tr>
            <td width="12.5%" align="right"><span style="color:red;display: inline; vertical-align: sub">*&nbsp;</span>买家类型 : </td>
            <td width="12.5%" align="left">
                <select id="superiorType" name="superiorType">
                    <option value="">--请选择--</option>
                    <c:forEach items="${buyerTypeList}" var="buyerType">
                        <c:choose>
                            <c:when test="${buyerDetail.superiorType eq buyerType.key}">
                                <option value="${buyerType.key}" selected>${buyerType.value}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${buyerType.key}">${buyerType.value}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
            </td>
            <td id="secondType" style="display:none" width="12.5%" align="right"><span style="color:red;display: inline; vertical-align: sub">*&nbsp;</span>二级类型:</td>
            <td id="secondTypeSelect" style="display:none" width="12.5%" align="left">
                <select id="buyerTypeId" name="superiorSubType">
                    <option value="${buyerDetail.superiorSubType}" selected>${buyerDetail.superiorSubName}</option>
                </select></td>
            </td>
            <td width="12.5%" align="right">
                <div id="isFoodMarket" <c:if test="${(buyerDetail.superiorType ne '05')}"> style="display: none" </c:if>>
                    <%--<c:if test="${(buyerDetail.superiorType eq '05')}">--%>
                        <input class ="isFoodMarket" type="checkbox" value="1"
                           <c:if test="${buyerDetail.superiorSubType eq '01'}">
                               checked="true" disabled
                           </c:if>
                           name="isMarketFlg"/>
                        <span style="color:red;display: inline; vertical-align: sub">*&nbsp;</span>菜场
                    <%--</c:if>--%>
                </div>
            </td>
                <td width="12.5%" align="left"><input type="hidden" id="superiorSubName" value="${buyerDetail.superiorSubName}" name="superiorSubName"></td>
            <td width="12.5%" align="right"></td>
            <td width="12.5%" align="left"></td>
        </tr>
        <tr>
            <td width="12.5%" align="right"><span style="color:red;display: inline; vertical-align: sub">*&nbsp;</span>物流区 : </td>
            <td width="12.5%" align="left">
                <select id="lgcsAreaCode" name="lgcsAreaCode">
                    <option value="">--请选择--</option>
                    <c:forEach items="${logisticsAreaList}" var="logisticsArea">
                        <c:choose>
                            <c:when test="${buyerDetail.lgcsAreaCode eq logisticsArea.lgcsAreaCode}">
                                <option value="${logisticsArea.lgcsAreaCode}" selected>${logisticsArea.lgcsAreaName}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${logisticsArea.lgcsAreaCode}">${logisticsArea.lgcsAreaName}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
            </td>
            <td width="12.5%" align="right"><span style="color:red;display: inline; vertical-align: sub">*&nbsp;</span>地区(城市) : </td>
            <td width="12.5%" align="left">
                <select id="cityCode" name="cityCode">
                    <option value="">--请选择--</option>
                    <c:forEach items="${cityList}" var="city">
                        <c:choose>
                            <c:when test="${buyerDetail.cityCode eq city.cityCode}">
                                <option value="${city.cityCode}" selected>${city.cityName}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${city.cityCode}">${city.cityName}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
            </td>
            <td width="12.5%" align="right"><span style="color:red;display: inline; vertical-align: sub">*&nbsp;</span>区(县) : </td>
            <td width="12.5%" align="left">
                <select id="districtCode" name="districtCode">
                    <option value="">--请选择--</option>
                    <c:forEach items="${districtList}" var="district">
                        <c:choose>
                            <c:when test="${buyerDetail.districtCode eq district.districtCode}">
                                <option value="${district.districtCode}" selected>${district.districtName}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${district.districtCode}">${district.districtName}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
            </td>
            <td width="12.5%"/>
            <td width="12.5%"/>
        </tr>
        <tr>
            <c:choose>
                <c:when test="${buyerDetail.superiorType eq '01'}">
                    <%--<span style="color:red;display: inline; vertical-align: sub">*&nbsp;</span>--%>
                    <td width="12.5%" align="right"><span id="superiorDec"><span style="color:red;">*&nbsp;</span>批发市场 : </span></td>
                    <td width="12.5%" align="left">
                        <select id="superiorId" name="superiorId">
                            <option value="">--请选择--</option>
                            <c:forEach items="${marketTerminalList}" var="marketTerminal">
                                <c:choose>
                                    <c:when test="${marketTerminal.terMarketId eq buyerDetail.superiorId}">
                                        <option value="${marketTerminal.terMarketId}" selected>${marketTerminal.marketName}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${marketTerminal.terMarketId}">${marketTerminal.marketName}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </td>
                    <td width="12.5%" align="right"><span id="superiorQuaDec">批发市场等级 : </span></td>
                    <td width="12.5%" align="left">
                        <input value="${buyerDetail.superiorQua}" name="superiorQua" id="superiorQua" readonly/>
                    </td>
                </c:when>
                <c:when test="${buyerDetail.superiorType eq '02' ||((buyerDetail.superiorType eq '05')&&(buyerDetail.superiorSubType=='01'))}">
                    <td width="12.5%" align="right"><span id="superiorDec"> <span style="color:red;display: inline; vertical-align: sub">*&nbsp;</span>菜场:</span></td>
                    <td width="12.5%" align="left">
                        <select id="superiorId" name="superiorId">
                            <option value="">--请选择--</option>
                            <c:forEach items="${marketFoodList}" var="marketFood">
                                <c:choose>
                                    <c:when test="${marketFood.fodMarketId eq buyerDetail.superiorId}">
                                        <option value="${marketFood.fodMarketId}" selected>${marketFood.marketName}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${marketFood.fodMarketId}">${marketFood.marketName}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </td>
                    <td width="12.5%" align="right"><span id="superiorQuaDec">菜场类型 : </span></td>
                    <td width="12.5%" align="left">
                        <input value="${buyerDetail.superiorQua}" name="superiorQua" id="superiorQua" readonly/>
                    </td>
                </c:when>
                <c:otherwise>
                    <td width="12.5%" align="right"><span id="superiorDec" style="display: none;"></span></td>
                    <td width="12.5%" align="left"><select id="superiorId" name="superiorId" style="display: none;"></select></td>
                    <td width="12.5%" align="right"><span id="superiorQuaDec" style="display: none"></span></td>
                    <td width="12.5%" align="left"><input value="" name="superiorQua" id="superiorQua" style="display: none" readonly/></td>
                </c:otherwise>
            </c:choose>
            <td width="12.5%"></td>
            <td width="12.5%"></td>
            <td width="12.5%"></td>
            <td width="12.5%"></td>
        </tr>
        <%--<tr id="distriType" <c:if test="${!(buyerDetail.superiorType eq '01')}">style="display: none"</c:if> >
            <td width="12.5%" align="right">买家池归属:<span style="color:red">*</span></td>
            <td colspan="7" width="87.5%" align="left">
                <c:forEach items="${pdClaShowList}" var="pdClaShow">
                    <c:choose>
                        <c:when test="${pdClaShow.isChecked eq '1'}">
                            <input type="checkbox" class="selectPdCla" id="buyerPdCla${pdClaShow.classCode}" name="buyerPdCla" value="${pdClaShow.classCode}" checked/><a href="#" class="buyerCk_a" >${pdClaShow.className}</a>
                            <input type="hidden" id="buyerPdMac${pdClaShow.classCode}" name="buyerPdMac" value="${pdClaShow.machiningCode}">
                        </c:when>
                        <c:otherwise>
                            <input type="checkbox" class="selectPdCla" id="buyerPdCla${pdClaShow.classCode}" name="buyerPdCla" value="${pdClaShow.classCode}"/><a href="#" class="buyerCk_a" >${pdClaShow.className}</a>
                            <input type="hidden" id="buyerPdMac${pdClaShow.classCode}" name="buyerPdMac" value="">
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </td>
        </tr>
        <tr id="otherType" <c:if test="${buyerDetail.superiorType eq '01'}">style="display: none"</c:if> >
            <td width="12.5%" align="right">买家池归属:<span style="color:red">*</span></td>
            <td colspan="7" width="87.5%" align="left">
                <c:forEach items="${pdClaShowList}" var="pdClaShow">
                    <c:choose>
                        <c:when test="${pdClaShow.isChecked eq '1'}">
                            <input type="checkbox" class="selectPdCla" name="buyerPdCla" value="${pdClaShow.classCode}" checked/>${pdClaShow.className}
                            <input type="hidden" id="buyerPdMac${pdClaShow.classCode}" name="buyerPdMac" value="${pdClaShow.machiningCode}">
                        </c:when>
                        <c:otherwise>
                            <input type="checkbox" class="selectPdCla" name="buyerPdCla" value="${pdClaShow.classCode}"/>${pdClaShow.className}
                            <input type="hidden" id="buyerPdMac${pdClaShow.classCode}" name="buyerPdMac" value="">
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </td>
        </tr>--%>
        <tr>
            <td width="12.5%" align="right">
                <msk:button buttonValue="完成" buttonId="BY121304.BUYERTYPE.SAVE" buttonType="button"/>
            </td>
        </tr>
    </table>
    </form:form>
</div>
<div class="group-accordion" collapsible="true" active="true">
    <h3>
        <label>买家基本信息</label>
    </h3>
    <form:form id="basicEditForm" metdod="post" enctype="multipart/form-data">
        <input type="hidden" name="buyerId" id="buyerId" value="${buyerId}">
        <input type="hidden" value=" " name="paymentTypeName" id="paymentTypeName"/>
        <input type="hidden" value=" " name="marketingsStatusName" id="marketingsStatusName"/>
        <table id="showTable" style="width:100%;" CellSpacing=8>
            <tr>
                <td width="12.5%" align="right">买家编码 : </td>
                <td width="12.5%" align="left" >${buyerDetail.buyerCode}</td>
                <td width="12.5%" align="right"><span style="color:red;display: inline; vertical-align: sub">*&nbsp;</span>买家名称 : </td>
                <td width="12.5%" align="left" >${buyerDetail.buyerName}</td>
                <td width="12.5%" align="right"><span style="color:red;display: inline; vertical-align: sub">*&nbsp;</span>买家地址 : </td>
                <td width="12.5%" align="left" >${buyerDetail.buyerAddr}</td>
                <td width="12.5%" align="right"></td>
                <td width="12.5%" align="left"></td>
            </tr>
            <tr>
                <td width="12.5%" align="right">店铺号 : </td>
                <td width="12.5%" align="left">${buyerDetail.storeNo}</td>
                <td width="12.5%" align="right">注册来源 : </td>
                <td width="12.5%" align="left">${buyerDetail.registerSourceName}</td>
                <td width="12.5%" align="right">买家网站 : </td>
                <td width="12.5%" align="left">${buyerDetail.buyerWebsite}</td>
                <td width="12.5%" align="right">买家微信公众号 : </td>
                <td width="12.5%" align="left">${buyerDetail.buyerWechat}</td>
            </tr>
            <tr>
                <td width="12.5%" align="right">买家QQ号 : </td>
                <td width="12.5%" align="left">${buyerDetail.buyerQq}</td>
                <td width="12.5%" align="right">买家微信号 : </td>
                <td width="12.5%" align="left">${buyerDetail.buyerSingleWechat}</td>
                <td width="12.5%"/>
                <td width="12.5%"/>
                <td width="12.5%"/>
                <td width="12.5%"/>
            </tr>
            <tr>
                <td width="12.5%" align="right">
                    <msk:button buttonValue="编辑" buttonId="BY121304.BASIC.EDIT" buttonType="button"/>
                </td>
                <td width="12.5%" align="right">
                    <msk:button buttonValue="查看账号" buttonId="BY121304.BASIC.SEEACCOUNT" buttonType="button"/>
                </td>
            </tr>
            <tr></tr>
            <tr></tr>
            <tr></tr>
            <tr></tr>
            <tr></tr>
        </table>
        <table id="editTable" style="width: 100%;display: none;" CellSpacing=8>
            <tr>
                <td width="12.5%" align="right">买家编码 : </td>
                <td width="12.5%" align="left"><input type="text" value="${buyerDetail.buyerCode}" size="30"
                                                      editmodel="false" disabled="disabled" id="buyerCode"
                                                      maxlength="200" name="buyerCode"/></td>
                <td width="12.5%" align="right"><span style="color:red;display: inline; vertical-align: sub">*&nbsp;</span>买家名称 : </td>
                <td width="12.5%" align="left"><input type="text" value="${buyerDetail.buyerName}" id="buyerName"
                                                      maxlength="50" name="buyerName" required
                                                      requiredMessage="买家名称不能为空"/></td>
                <td width="12.5%" align="right"><span style="color:red;display: inline; vertical-align: sub">*&nbsp;</span>买家地址 : </td>
                <td width="12.5%" align="left"><input type="text" value="${buyerDetail.buyerAddr}" id="buyerAddr" required requiredMessage="买家地址不能为空"
                                                      name="buyerAddr"
                                                      maxlength="100" name="buyerAddr"/></td>
                <td width="12.5%" align="right"></td>
                <td width="12.5%" align="left"></td>
            </tr>
            <tr>
                <td width="12.5%" align="right">店铺号 : </td>
                <td width="12.5%" align="left"><input type="text" value="${buyerDetail.storeNo}" id="storeNo" maxlength="50" name="storeNo"/></td>
                <td width="12.5%" align="right">注册来源 : </td>
                <td width="12.5%" align="left">${buyerDetail.registerSourceName}<input type="hidden" value="${buyerDetail.registerSource}" maxlength="1" name="registerSource" id="registerSource"/></td>
                <td width="12.5%" align="right">买家网站 : </td>
                <td width="12.5%" align="left"><input type="url" value="${buyerDetail.buyerWebsite}" id="buyerWebsite" maxlength="100" name="buyerWebsite"  urlMessage="买家网站输入错误"/></td>
                <td width="12.5%" align="right">买家微信公众号 : </td>
                <%--Modif for Bug#2785 at 2016/09/19 by yuan_zhifei Start--%>
                <td width="12.5%" align="left"><input type="text" value="${buyerDetail.buyerWechat}" id="buyerWechat" maxlength="50" name="buyerWechat"/></td>
                <%--Modif for Bug#2785 at 2016/09/19 by yuan_zhifei End--%></tr>
            <tr>
                <td width="12.5%" align="right">买家QQ号 : </td>
                <%--Modif for Bug#2638 at 2016/09/13 by yuan_zhifei Start--%>
                <td width="12.5%" align="left"><input type="text" value="${buyerDetail.buyerQq}" id="buyerQq" name="buyerQq" maxlength="20" digits="true" digitsMessage="买家QQ号为数字类型"/></td>
                <%--Modif for Bug#2638 at 2016/09/13 by yuan_zhifei End--%>
                <%--Modif for Bug#2785 at 2016/09/19 by yuan_zhifei Start--%>
                <td width="12.5%" align="right">买家微信号 : </td>
                <td width="12.5%" align="left"><input type="text" value="${buyerDetail.buyerSingleWechat}" id="buyerSingleWechat" maxlength="50" name="buyerSingleWechat"/></td>
                <%--Modif for Bug#2785 at 2016/09/19 by yuan_zhifei End--%>
                <td width="12.5%"/>
                <td width="12.5%"/>
                <td width="12.5%"/>
                <td width="12.5%"/>
            </tr>
            <tr>
                <td width="12.5%" align="right">
                    <msk:button buttonValue="完成" buttonId="BY121304.BASIC.SAVE" buttonType="button"/>
                </td>
            </tr>
        </table>
    </form:form>
</div>
<script type="text/javascript" src="${ctx}/static/buyers/js/comm/BASE_BUYER_BASIC_INFO.js"></script>