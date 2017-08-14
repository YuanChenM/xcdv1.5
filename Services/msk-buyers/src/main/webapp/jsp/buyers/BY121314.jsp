<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<%@ taglib prefix="msk2" uri="/codemaster" %>
<msk2:codemaster codeType="ShOrderDeliveryArea" viewType="json"/>
<navigation:header title="买家收货信息管控" backTitleArray="买家列表,买家信息总控画面"
                   backUrlArray="${ctx}/BY121303/init/,${ctx}/BY121313/init/${buyerId}"></navigation:header>
<div class="page-content list-page">

    <div class="group-accordion" collapsible="true" active="true">
        <h3>
            <label>买家收货信息管控</label>
        </h3>

        <form id="BY121314SaveForm" action="${ctx}/BY121314/save/" method="post">
            <input type="hidden" name="buyerId" value="${buyerId}"/>
            <table style="width: 100%;" CellSpacing=8>
                <tr>
                    <td width="12.5%" align="right">习惯收货时间段 : </td>
                    <td colspan="7" width="87.5%" align="left">
                        <select id="habitRecTime" name="habitRecTime">
                            <option value="">--请选择--</option>
                            <c:choose>
                                <c:when test="${byRecTime.habitRecTime == null}">
                                    <c:forEach items="${recTime}" var="recTime">
                                        <option value="${recTime.key}">${recTime.value}</option>
                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                    <c:forEach items="${recTime}" var="recTime">
                                        <c:choose>
                                            <c:when test="${byRecTime.habitRecTime eq recTime.key}">
                                                <option value="${recTime.key}" selected>${recTime.value}</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="${recTime.key}">${recTime.value}</option>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </c:otherwise>
                            </c:choose>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td width="12.5%" align="right">最早收货时间 : </td>
                    <td width="12.5%" align="left">
                        <select id="earliestRecTime" name="earliestRecTime">
                            <option value="">--请选择--</option>
                            <c:choose>
                                <c:when test="${latestRecTime == null}">
                                </c:when>
                                <c:otherwise>
                                    <c:forEach items="${earliestRecTime}" var="earliestRecTime">
                                        <c:choose>
                                            <c:when test="${byRecTime.earliestRecTime eq earliestRecTime}">
                                                <option value="${earliestRecTime}" selected>${earliestRecTime}</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="${earliestRecTime}">${earliestRecTime}</option>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </c:otherwise>
                            </c:choose>
                        </select>
                    </td>
                    <td width="12.5%" align="right">最晚收货时间 : </td>
                    <td width="12.5%" align="left">
                        <select id="latestRecTime" name="latestRecTime">
                            <option value="">--请选择--</option>
                            <c:choose>
                                <c:when test="${latestRecTime == null}">
                                </c:when>
                                <c:otherwise>
                                <c:forEach items="${latestRecTime}" var="latestRecTime">
                                    <c:choose>
                                        <c:when test="${byRecTime.latestRecTime eq latestRecTime}">
                                            <option value="${latestRecTime}" selected>${latestRecTime}</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${latestRecTime}">${latestRecTime}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                                </c:otherwise>
                            </c:choose>
                        </select>
                    </td>
                    <td width="12.5%" align="right"></td>
                    <td width="12.5%" align="right"></td>
                    <td width="12.5%" align="right"></td>
                    <td width="12.5%" align="right"></td>
                </tr>
                <tr>
                    <td width="12.5%" align="right">习惯支付方式 : </td>
                    <td colspan="7" width="87.5%" align="left">
                        <c:choose>
                            <c:when test="${byRecTime.paymentType == null or byRecTime.paymentType == ''}">
                                <c:forEach items="${paymentMethod}" var="payment">
                                            <input type="checkbox" name="selectPaymentType" value="${payment.key}"/>${payment.value}
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <c:forEach items="${paymentList}" var="payment">
                                    <c:choose>
                                        <c:when test="${payment.isPayChecked == '1'}">
                                            <input type="checkbox" name="selectPaymentType" value="${payment.payMethod}" checked/>${payment.payMethodName}
                                        </c:when>
                                        <c:otherwise>
                                            <input type="checkbox" name="selectPaymentType" value="${payment.payMethod}"/>${payment.payMethodName}
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div class="group-accordion" collapsible="true" active="true">
        <h3>
            <label>买家配送地址信息</label>
        </h3>

        <form action="${ctx}/BY121314/search/" method="post">
            <input type="hidden" id="buyerId" name="buyerId" value="${buyerId}"/>
            <table id="BY121314_Grid">
                <thead>
                <tr>
                    <th coltype="sno">序号</th>
                    <th coltype="text" name="provinceName" filter=false>省</th>
                    <th coltype="text" name="cityName" filter=false>市</th>
                    <th coltype="text" name="districtName" filter=false>区(县)</th>
                    <th coltype="text" name="deliveryAddr" filter=true>买家配送地址</th>
                    <th coltype="text" name="referenceAddr" filter=true>买家配送参照地址</th>
                    <th coltype="text" name="manageAddr" filter=true>买家经营地址</th>
                    <th coltype="text" name="recPerName" filter=true>收货联系人</th>
                    <th coltype="text" name="recPerTel" filter=true>联系电话</th>
                    <th coltype="text" name="recPerWechat" filter=true>微信账号</th>
                    <th coltype="text" name="recPerQq" filter=true>QQ账号</th>
                    <th coltype="code" name="shOdDeliveryArea" filter=false code2name="SHORDERDELIVERYAREA_JSON">上海订单配送区域</th>
                    <th coltype="text" name="isDefault" filter=false>默认配送地址</th>
                    <th coltype="action">
                        <msk:button buttonType="hidden" coltype="edit" buttonId="BY121314.EDIT" buttonValue="编辑"/>
                        <msk:button buttonType="hidden" coltype="delete" buttonId="BY121314.DELETE" buttonValue="删除"/>
                    </th>
                </tr>
                </thead>
                <tbody></tbody>
            </table>
            <msk:button buttonType="button" buttonId="BY121314.ADD" buttonValue="新增"/>
        </form>
    </div>
    <msk:button buttonType="button" buttonId="BY121314.SAVE" buttonValue="保存"/>
</div>
<script type="text/javascript" src="${ctx}/static/buyers/js/BY121314.js"></script>