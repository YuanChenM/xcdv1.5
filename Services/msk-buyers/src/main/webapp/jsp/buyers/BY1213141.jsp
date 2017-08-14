<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<div class="page-content list-page">

    <form id="BY1213141SaveForm" action="${ctx}/BY1213141/save/" method="post">
        <input type="hidden" name="buyerId" value="${buyerId}"/>
        <input type="hidden" name="id" value="${id}"/>
        <input type="hidden" name="isFlag" value="true">
        <table style="width: 100%;" CellSpacing=8>
            <tr>
                <td width="12.5%" align="right"><span style="color:red;display: inline; vertical-align: sub">*&nbsp;</span>省:</td>
                <td width="12.5%" align="left">
                    <select id="provinceCode" name="provinceCode">
                        <option value="999">--请选择--</option>
                        <c:forEach items="${provinceList}" var="province">
                            <c:choose>
                                <c:when test="${buyerDelivery.provinceCode eq province.provinceCode}">
                                    <option value="${province.provinceCode}" selected>${province.provinceName}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${province.provinceCode}">${province.provinceName}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                </td>
                <input type="hidden" id="provinceName" name="provinceName" value=""/>
            </tr>
            <tr>
                <td width="12.5%" align="right"><span style="color:red;display: inline; vertical-align: sub">*&nbsp;</span>市:</td>
                <td width="12.5%" align="left">
                    <select id="cityCode" name="cityCode">
                        <option value="999">--请选择--</option>
                        <c:forEach items="${cityList}" var="city">
                            <c:choose>
                                <c:when test="${buyerDelivery.cityCode eq city.cityCode}">
                                    <option value="${city.cityCode}" selected>${city.cityName}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${city.cityCode}">${city.cityName}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                </td>
                <input type="hidden" id="cityName" name="cityName" value=""/>
            </tr>
            <tr>
                <td width="12.5%" align="right"><span style="color:red;display: inline; vertical-align: sub">*&nbsp;</span>区(县):</td>
                <td width="12.5%" align="left">
                    <select id="districtCode" name="districtCode">
                        <option value="999">--请选择--</option>
                        <c:forEach items="${districtList}" var="district">
                            <c:choose>
                                <c:when test="${buyerDelivery.districtCode eq district.districtCode}">
                                    <option value="${district.districtCode}" selected>${district.districtName}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${district.districtCode}">${district.districtName}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                </td>
                <input type="hidden" id="districtName" name="districtName" value=""/>
            </tr>
            <tr>
                <td width="12.5%" align="right"><span style="color:red;display: inline; vertical-align: sub">*&nbsp;</span>买家配送详细地址:</td>
                <td width="12.5%" align="left"><input type="text" id="deliveryAddr" required requiredMessage="买家配送详细地址不能为空"
                                                      value="${buyerDelivery.deliveryAddr}" name="deliveryAddr"
                                                      maxlength="100"></td>
            </tr>
            <tr>
                <td width="12.5%" align="right">买家配送参照地址:</td>
                <td width="12.5%" align="left"><input type="text" value="${buyerDelivery.referenceAddr}" name="referenceAddr" maxlength="100"></td>
            </tr>
            <tr>
                <td width="12.5%" align="right">买家经营地址:</td>
                <td width="12.5%" align="left"><input type="text" value="${buyerDelivery.manageAddr}" name="manageAddr" maxlength="100"></td>
            </tr>
            <tr>
                <td width="12.5%" align="right"><span style="color:red;display: inline; vertical-align: sub">*&nbsp;</span>收货联系人:</td>
                <td width="12.5%" align="left"><input type="text" id="recPerName" value="${buyerDelivery.recPerName}"
                                                      required requiredMessage="收货联系人不能为空"
                                                      name="recPerName" maxlength="20"></td>
            </tr>
            <tr>
                <td width="12.5%" align="right"><span style="color:red;display: inline; vertical-align: sub">*&nbsp;</span>联系电话:</td>
                <td width="12.5%" align="left"><input type="text" id="recPerTel" value="${buyerDelivery.recPerTel}"
                                                      required requiredMessage="联系电话不能为空" name="recPerTel" digits="true" digitsMessage="联系电话为数字类型"
                                                      maxlength="20"></td>
            </tr>
            <tr>
                <td width="12.5%" align="right">微信账号:</td>
                <td width="12.5%" align="left"><input type="text" value="${buyerDelivery.recPerWechat}" name="recPerWechat" maxlength="20"></td>
            </tr>
            <tr>
                <td width="12.5%" align="right">QQ账号:</td>
                <td width="12.5%" align="left"><input type="text" value="${buyerDelivery.recPerQq}" name="recPerQq"  maxlength="20" digits="true" digitsMessage="联系电话为数字类型"></td>
            </tr>
            <tr id="deliveryArea" style="<c:if test="${buyerDelivery.provinceCode ne '01'}">display:none</c:if>">
                <td width="12.5%" align="right">订单配送区域:</td>
                <td width="12.5%" align="left">
                    <select id="shOdDeliveryArea" name="shOdDeliveryArea">
                        <option value="">--请选择---</option>
                        <<c:choose>
                        <c:when test="${buyerDelivery.shOdDeliveryArea == null or buyerDelivery.shOdDeliveryArea == '0'}">
                            <c:forEach items="${shOrderDeliveryAreas}" var="shOdDeliveryArea">
                                <option value="${shOdDeliveryArea.key}">${shOdDeliveryArea.value}</option>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <c:forEach items="${shOrderDeliveryAreas}" var="shOdDeliveryArea">
                                <c:choose>
                                    <c:when test="${buyerDelivery.shOdDeliveryArea eq shOdDeliveryArea.key}">
                                        <option value="${shOdDeliveryArea.key}" selected>${shOdDeliveryArea.value}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${shOdDeliveryArea.key}">${shOdDeliveryArea.value}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                    </select>
                </td>
            </tr>
            <tr>
                <td width="12.5%" align="right"></td>
                <td width="12.5%" align="left">
                    <c:choose>
                        <c:when test="${buyerDelivery.isDefault eq '1'}">
                            <input type="checkbox" value="${buyerDelivery.isDefault}" name="defaultFlg" checked>设为默认地址
                        </c:when>
                        <c:otherwise>
                            <input type="checkbox" value="1" name="defaultFlg">设为默认地址
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
            <tr></tr>
            <tr>
                <td width="12.5%" align="right">
                    <msk:button buttonValue="保存" buttonId="BY1213141.SAVE" buttonType="button"/>
                </td>
            </tr>
        </table>
    </form>
</div>
<script type="text/javascript" src="${ctx}/static/buyers/js/BY1213141.js"></script>