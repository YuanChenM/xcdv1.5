<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<navigation:header title="买家编码管控" backTitleArray="买家列表,买家信息总控画面" backUrlArray="${ctx}/BY121303/init/,${ctx}/BY121313/init/${buyerId}"></navigation:header>
<div class="page-content list-page">
    <div class="group-accordion" collapsible="" active="false">
        <h3>买家编码管控</h3>
        <form:form action="#" id="BY121311Form"
                   method="post" enctype="multipart/form-data">
            <%--分销买家--%>
            <c:if test="${buyerInfo.superiorType eq '01'}">
            <table style="width: 100%;" CellSpacing=8 class="dataTable no-footer">
                <tr style="background-color: rgba(68, 68, 68, 0.3)">
                    <td width="10%" align="left">买家编码:</td>
                    <td width="20%" colspan="7" align="left">${buyerInfo.buyerCode}</td>
                </tr>
                <tr style="background-color: rgba(68, 68, 68, 0.2)">
                    <td width="12.5%" colspan="5" align="center">买家基本码(主码)</td>
                    <td width="12.5%" colspan="2" align="center">买家营销码</td>
                    <td width="12.5%" rowspan="2">校正码</td>
                </tr>
                <tr style="background-color: rgba(68, 68, 68, 0.2)">
                    <td width="12.5%">买家分类</td>
                    <td width="12.5%">物流区</td>
                    <td width="12.5%">地区</td>
                    <td width="12.5%">批发市场</td>
                    <td width="12.5%">批发市场买家顺序码</td>
                    <td width="12.5%">买家上线状态</td>
                    <td width="12.5%">产品二级</td>
                </tr>
                <tr>
                    <td width="12.5%">${paramBean.superiorName}</td>
                    <td width="12.5%">
                        <c:forEach items="${logisticsAreaList}" var="logisticsArea">
                            <c:if test="${paramBean.lgcsAreaCode eq logisticsArea.lgcsAreaCode}">
                                ${logisticsArea.lgcsAreaName}
                            </c:if>
                        </c:forEach>
                    </td>
                    <td width="12.5%">
                        <c:forEach items="${cityList}" var="city">
                            <c:if test="${paramBean.cityCode eq city.cityCode}">
                                ${city.cityName}
                            </c:if>
                        </c:forEach>
                    </td>
                    <td width="12.5%">${paramBean.terminalName}</td>
                    <td width="12.5%">${paramBean.buyerSeq}</td>
                    <td width="12.5%">${paramBean.saleStatus}</td>
                    <td width="12.5%">
                        <c:forEach items="${paramBean.pdName}" var="product">
                            ${product.value}<br>
                        </c:forEach>
                    </td>
                    <td width="12.5%">${paramBean.identifyCode}</td>
                </tr>
            </table>
            </c:if>
            <%--菜场--%>
            <c:if test="${buyerInfo.superiorType eq '02' || (buyerInfo.superiorType eq '05'&& buyerInfo.isMarketFlg eq '1')}">
            <table style="width: 100%;" CellSpacing=8 class="dataTable no-footer">
                <tr style="background-color: rgba(68, 68, 68, 0.3)">
                    <td width="10%" align="left">买家编码:</td>
                    <td width="20%" colspan="8" align="left">${buyerInfo.buyerCode}</td>
                </tr>
                <tr style="background-color: rgba(68, 68, 68, 0.2)">
                    <td width="12.5%" colspan="6" align="center">买家基本码(主码)</td>
                    <td width="12.5%" colspan="2" align="center">买家营销码</td>
                    <td width="12.5%" rowspan="2">校正码</td>
                </tr>
                <tr style="background-color: rgba(68, 68, 68, 0.2)">
                    <td width="12.5%">买家分类</td>
                    <td width="12.5%">物流区</td>
                    <td width="12.5%">地区</td>
                    <td width="12.5%">区县</td>
                    <td width="12.5%">菜场</td>
                    <td width="12.5%">菜场买家顺序码</td>
                    <td width="12.5%">买家上线状态</td>
                    <td width="12.5%">产品一级</td>
                </tr>
                <tr>
                    <td width="12.5%">${paramBean.superiorName}</td>
                    <td width="12.5%">
                        <c:forEach items="${logisticsAreaList}" var="logisticsArea">
                            <c:if test="${paramBean.lgcsAreaCode eq logisticsArea.lgcsAreaCode}">
                                ${logisticsArea.lgcsAreaName}
                            </c:if>
                        </c:forEach>
                    </td>
                    <td width="12.5%">
                        <c:forEach items="${cityList}" var="city">
                            <c:if test="${paramBean.cityCode eq city.cityCode}">
                                ${city.cityName}
                            </c:if>
                        </c:forEach>
                    </td>
                    <td width="12.5%">
                        <c:forEach items="${districtList}" var="district">
                            <c:if test="${paramBean.districtCode eq district.districtCode}">
                                ${district.districtName}
                            </c:if>
                        </c:forEach>
                    </td>
                    <td width="12.5%">${paramBean.foodName}</td>
                    <td width="12.5%">${paramBean.buyerSeq}</td>
                    <td width="12.5%">${paramBean.saleStatus}</td>
                    <td width="12.5%">
                        <c:forEach items="${paramBean.pdName}" var="product">
                            ${product.value}<br>
                        </c:forEach>
                    </td>
                    <td width="12.5%">${paramBean.identifyCode}</td>
                </tr>
            </table>
            </c:if>
            <%--其他--%>
            <c:if test="${buyerInfo.superiorType != '01' && buyerInfo.superiorType != '02'}">
            <table style="width: 100%;" CellSpacing=8 class="dataTable no-footer">
                <tr style="background-color: rgba(68, 68, 68, 0.3)">
                    <td width="10%" align="left">买家编码:</td>
                    <td width="20%" colspan="7" align="left">${buyerInfo.buyerCode}</td>
                </tr>
                <tr style="background-color: rgba(68, 68, 68, 0.2)">
                    <td width="12.5%" colspan="5" align="center">买家基本码(主码)</td>
                    <td width="12.5%" colspan="2" align="center">买家营销码</td>
                    <td width="12.5%" rowspan="2">校正码</td>
                </tr>
                <tr style="background-color: rgba(68, 68, 68, 0.2)">
                    <td width="12.5%">买家分类</td>
                    <td width="12.5%">物流区</td>
                    <td width="12.5%">地区</td>
                    <td width="12.5%">区县</td>
                    <td width="12.5%">买家顺序码</td>
                    <td width="12.5%">买家上线状态</td>
                    <td width="12.5%">产品一级</td>
                </tr>
                <tr>
                    <td width="12.5%">${paramBean.superiorName}</td>
                    <td width="12.5%">
                        <c:forEach items="${logisticsAreaList}" var="logisticsArea">
                            <c:if test="${paramBean.lgcsAreaCode eq logisticsArea.lgcsAreaCode}">
                                ${logisticsArea.lgcsAreaName}
                            </c:if>
                        </c:forEach>
                    </td>
                    <td width="12.5%">
                        <c:forEach items="${cityList}" var="city">
                            <c:if test="${paramBean.cityCode eq city.cityCode}">
                                ${city.cityName}
                            </c:if>
                        </c:forEach>
                    </td>
                    <td width="12.5%">
                        <c:forEach items="${districtList}" var="district">
                            <c:if test="${paramBean.districtCode eq district.districtCode}">
                                ${district.districtName}
                            </c:if>
                        </c:forEach>
                    </td>
                    <td width="12.5%">${paramBean.buyerSeq}</td>
                    <td width="12.5%">${paramBean.saleStatus}</td>
                    <td width="12.5%">
                        <c:forEach items="${paramBean.pdName}" var="product">
                            ${product.value}<br>
                        </c:forEach>
                    </td>
                    <td width="12.5%">${paramBean.identifyCode}</td>
                </tr>
            </table>
            </c:if>
        </form:form>
    </div>
</div>
<script type="text/javascript" src="${ctx}/static/buyers/js/BY121311.js"></script>