<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<div class="page-content list-page">
   <%--     <input type="hidden" name="type" id="buyerStoreNo" value="${buyerStoreNo}">--%>
    <form action="${ctx}/BY12141301/init/" method="post" id="BY12141301SaveForm">
        <input type="hidden"  name="marketId" id="marketId" value="${marketId}">
        <input type="hidden"  name="isTargetMerchant" id="isTargetMerchant" value="${isTargetMerchant}">
        <input type="hidden"  name="isChecked" id="isChecked" value="${isChecked}">
        <input type="hidden"  name="addFlg" id="addFlg" value="${addFlg}">
        <input type="hidden"  name="storeId" id="storeId" value="${storeId}">
        <input type="hidden"  name="storeNumber" id="storeNumber" value="${buyerStoreNo}">
        
        <div>
        <table style="width: 100%;">
            <tr>
                <td  width="8%" align="right"><span style="color:red;display: inline; vertical-align: sub">*&nbsp;</span>买家门牌/摊位号 : </td>
                <td align="left" width="10%">
                 <c:choose>
                     <c:when test="${isChecked eq '0'}">
                         <input type="text" id="buyerStoreNo" name="buyerStoreNo"  value="${buyerStoreNo}" maxlength="10" editmodel = "false" disabled />
                     </c:when>
                     <c:otherwise>
                         <input type="text" id="buyerStoreNo" name="buyerStoreNo"  value="${buyerStoreNo}" maxlength="10" required  requiredMessage="买家门牌/摊位号不能为空"/>
                     </c:otherwise>
                 </c:choose>
                </td>
            </tr>
            <tr>
                <td align="right"><span style="color:red;display: inline; vertical-align: sub">*&nbsp;</span>经营产品品类 ：</td>
                <td></td>
            </tr>
            <c:forEach items="${pdClaShowList}" var="byl">
            <tr>
                <td></td>
                <td >
                    <c:choose>
                        <c:when test="${byl.isChecked eq '1'}">
                            <input type="checkbox" id="buyerPdCla${byl.salePd}"  name="salePds" value="${byl.salePdCode}_${byl.salePd}" checked="checked">${byl.salePd}
                        </c:when>

                        <c:otherwise>
                            <input type="checkbox" id="buyerPdCla${byl.salePd}"  name="salePds" value="${byl.salePdCode}_${byl.salePd}">${byl.salePd}
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
           </c:forEach>
            <tr>
                <td align="right">备注 : </td>
                <td  align="left"><textarea type="text" id="remark" name="remark"  value="${remark}" maxlength="20"/></td>
            </tr>
            <tr>
                <td width="8%" >
                <msk:button buttonValue="保存" buttonId="BY12141301.SAVE" buttonType="button"/>
                </td>
            </tr>
        </table>

    </div>
</form>
</div>
<script type="text/javascript" src="${ctx}/static/buyers/js/BY12141301.js"></script>


