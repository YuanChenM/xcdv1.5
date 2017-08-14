<%--
    Title:选择合同/发货订单/核销单
    author:wu_honglei
    createDate:2016-08-15
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>


<form id="SSC1130802Form"  method="post" >
    <input type="hidden" name="contractId" id="contractId"  />
    <input type="hidden" name="contractCode" id="contractCode"  />
    <input type="hidden" name="supplierId" id="supplierId"  />
    <input type="hidden" name="supplierName" id="supplierName"  />
    <input type="hidden" name="supplierCode" id="supplierCode"  />
    <input type="hidden" name="purchaserId" id="purchaserId" />
    <input type="hidden" name="purchaserName" id="purchaserName"  />
    <input type="hidden" name="purchaserCode" id="purchaserCode"  />
    <input type="hidden" name="deliveryId" id="deliveryId" />
    <input type="hidden" name="deliveryCode" id="deliveryCode"/>
    <input type="hidden" name="verificationId" id="verificationId"/>
    <input type="hidden" name="verificationCode" id="verificationCode"/>
    <input type="hidden" name="moneyFlag" id="moneyFlag"/>
    <input type="hidden" name="paymentType" id="paymentType" value="${ssc1130802RsBean.paymentType}"/>

<div class="page-content list-page" >
    <div style="white-space:nowrap;">
        <table style="width: 100%;" >
            <tr>
                <th align="right">
                    付款类型:
                </th>
                <td  align="left" colspan="6">
                   <%-- <input type="radio" name="paymentType" class="paymentTypeRadio" value="0"  <c:if test="${ssc1130802RsBean.paymentType==0}">checked</c:if> >预付款</input>
                    <input type="radio" name="paymentType" class="paymentTypeRadio" value="1"  <c:if test="${ssc1130802RsBean.paymentType==1}">checked</c:if> >进度款</input>
                    <input type="radio" name="paymentType" class="paymentTypeRadio" value="2"  <c:if test="${ssc1130802RsBean.paymentType==2}">checked</c:if> >余款</input>--%>
                    <input type="radio" name="paymentType" class="paymentTypeRadio" value="0"   checked>预付款</input>
                    <input type="radio" name="paymentType" class="paymentTypeRadio" value="1"   >进度款</input>
                    <input type="radio" name="paymentType" class="paymentTypeRadio" value="2"   >余款</input>
                </td>
            </tr>


            <tr>
                <th align="right">选择合同:</th>
                <td align="left">
                    <input type="text" id="contractCode2"  name="contractCode2" readonly="readonly"/>
                   <%-- <select id="contractList"  style="width:150px" >
                        <option selected  value="" >请选择合同</option>
                        <c:forEach  items="${ssc1130802RsBean.contractList}" var="ctt" varStatus="status">
                            <option value="${ctt.contractCode}"
                                    contractCode="${ctt.contractCode}"
                                    contractName="${ctt.contractName}"
                                    contractId="${ctt.contractId}"
                                    supplierId="${ctt.supplierId}"
                                    supplierName="${ctt.supplierName}"
                                    supplierCode="${ctt.supplierCode}"
                                    purchaserId="${ctt.purchaserId}"
                                    purchaserName="${ctt.purchaserName}"
                                    purchaserCode="${ctt.purchaserCode}"
                                   >${ctt.contractCode}</option>
                        </c:forEach>
                    </select>--%>
                </td>
                <td align="left">
                    <div id="contractDiv">
                        <img  src="../static/images/action/search.png" title="选择合同" id="chooseContractInfo" style="cursor:pointer;" />
                    </div>
                </td>
                <th align="right">订单合同总金额(元):</th>
                <td align="left">
                    <input type="text" name="contractTotalAmount" id="contractTotalAmount" />

                </td>
                <th align="right">订单合同预付款金额(元):</th>
                <td align="left">
                    <input type="text" id="contractFirstAmount"  readonly disabled  />
                </td>
            </tr>

            <tr>
                <th align="right" >选择发货订单:</th>
                <td align="left">
                    <input type="text" id="deliveryCode2"  name="deliveryCode2" readonly="readonly"/>

                   <%-- <select id="deliverList" style="width:150px">
                        <option selected value="">选择发货订单</option>
                        <c:forEach items="${ssc1130802RsBean.deliverList}" var="dlv" varStatus="status">
                            <option value="${dlv.deliveryCode}"
                                    deliveryCode="${dlv.deliveryCode}"
                                    deliveryId="${dlv.deliveryId}"
                                    transportCost="${dlv.transportCost}"
                                    contractId="${dlv.contractId}"
                                    contractCode="${dlv.contractCode}"
                                    supplierId="${dlv.supplierId}"
                                    supplierName="${dlv.supplierName}"
                                    supplierCode="${dlv.supplierCode}"
                                    purchaserId="${dlv.purchaserId}"
                                    purchaserName="${dlv.purchaserName}"
                                    purchaserCode="${dlv.purchaserCode}"
                                    amount="${dlv.amount}"
                                    >${dlv.deliveryCode}</option>
                        </c:forEach>
                    </select>--%>
                </td>
                <td align="left">
                    <div id="deliveryDiv">
                        <img  src="../static/images/action/search.png" title="选择发货订单" id="chooseDeliveryInfo" style="cursor:pointer;" />
                    </div>
                </td>
                <th align="right" >本次发货订单总金额(元):</th>
                <td align="left"> <input type="text" id="deliverTotalAmount" /></td>
                <th align="right" >预付款按比例已支付金额(元):</th>
                <td align="left" >
                    <input type="text" id="paidDownPaymentPercentage"  readonly disabled value="${ssc11308RsBean.paidDownPaymentPercentage}" />
                </td>
            </tr>
             <tr>
                 <th align="right"></th>
                 <td align="left"></td>
                 <td align="left"></td>
                 <th align="right" > 本次运输费用实际发生额(元):</th>
                 <td align="left"><input type="text" id="transportAmount" /></td>

                 <th align="right">包材使用费实际发生额(元):</th>
                 <td align="left"><input type="text" id="packageAmount"  name="packageAmount" /></td>
             </tr>


            <tr>
                <th align="right">选择核销单:</th>
                <td align="left">
                    <input type="text" id="verificationCode2"  name="verificationCode2" readonly="readonly"/>
                    <%--<select id="verificationList" style="width:150px">
                        <option selected value="">选择核销单</option>
                        <c:forEach items="${ssc1130802RsBean.verificationList}" var="vft" varStatus="status">
                            <option value="${vft.verificationCode}"
                                    verificationCode="${vft.verificationCode}"
                                    verificationId="${vft.verificationId}"
                                    verificationAmount="${vft.verificationAmount}"
                                    contractId ="${vft.contractId}"
                                    >${vft.verificationCode}</option>
                        </c:forEach>
                    </select>--%>
                </td>
                <td align="left">
                    <div id="verificationDiv">
                        <img  src="../static/images/action/search.png" title="选择核销单" id="chooseVerificationInfo" style="cursor:pointer;" />
                    </div>
                </td>
                <th align="right">核销金额:</th>
                <td align="left" colspan="5"><input type="text" id="verificationAmount"  name="verificationAmount" /></td>
            </tr>
            <tr>
                <td colspan="7" align="right">
                    <msk:button buttonValue="保存" buttonId="SSC1130802.SAVE" buttonType="button"/>
                </td>
            </tr>
        </table>
    </div>
</div>
</form>
<script src="<c:url value="/static/js/ssc/SSC1130802.js"/>"></script>
<script src="<c:url value="/static/js/ssc/SSCCommon.js"/>"></script>