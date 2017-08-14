<%-- 
    Title:卖家产品技术标准档案卡审核页面
    author:yuan_chen
    createDate:2015-12-10
    updateDate:2015-12-10
    updateAuthor:yuan_chen
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/comm/taglib.jsp" %>
<%@ taglib prefix="msk2" uri="/msk" %>
<link rel="stylesheet" href="${ctx}/static/js/treegrid/css/jquery.treegrid.css">
<script type="text/javascript" src="${ctx}/static/js/treegrid/js/jquery.treegrid.js"></script>
<c:if test="${examineStatus eq '1'}">
    <navigation:header title="卖家产品技术标准审核" backTitleArray="卖家产品卫生标准　卖家编号:${sl241104Bean.slCode}"
                       backUrlArray="${ctx}/SL241104/init"></navigation:header>
</c:if>
<c:if test="${examineStatus eq '2'}">
    <navigation:header title="卖家产品技术标准定级结果确认" backTitleArray="卖家产品卫生标准　卖家编号:${sl241104Bean.slCode}" backUrlArray="${ctx}/SL241104/init"></navigation:header>
</c:if>
<c:if test="${examineStatus eq '3'}">
    <navigation:header title="卖家产品技术标准审核结果" backTitleArray="卖家产品卫生标准　卖家编号:${sl241104Bean.slCode}" backUrlArray="${ctx}/SL241104/init"></navigation:header>
</c:if>
<div class="page-content list-page">
    <form id="SL241108ExamineForm">
        <div class="group-accordion" collapsible="false" active="false">
            <h3>
                <label>卖家产品信息</label>
            </h3>
            <table class="tree dataTable no-footer" style="width: 100%">   
                <tr height="1px"><td colspan="6"></td></tr>
                <tr height="30px">
                    <td width="5%" style="text-align:right;background:#DBDBDB">生产商</td>
                    <td width="25%">${sl241104Bean.manufacturer}</td>
                    <td width="5%" style="text-align:right;background:#DBDBDB">品牌</td>
                    <td width="25%">${sl241104Bean.brand}</td>
                    <td width="5%" style="text-align:right;background:#DBDBDB">产品编码</td>
                    <td width="35%">${sl241104Bean.pdCode}</td>
                </tr>
                <tr height="1px"><td colspan="6"></td></tr>
                <tr height="30px">
                    <td style="text-align:right;background:#DBDBDB">产品类别</td>
                    <td>${sl241104Bean.classesName}</td>
                    <td style="text-align:right;background:#DBDBDB">产品品种</td>
                    <td>${sl241104Bean.breedName}</td>
                    <td style="text-align:right;background:#DBDBDB">产品特征</td>
                    <td>${sl241104Bean.feature}</td>
                </tr>
                <tr height="1px"><td colspan="6"></td></tr>
            </table>
        </div>
        <div class="group-accordion" collapsible="false" active="false">
            <h3>
                <label>卖家产品技术标准详细</label>
            </h3>
            <table class="tree dataTable no-footer" style="width: 100%">   
                <tr style="background:#DBDBDB">
                    <td width="20%"></td>
                    <td width="20%" style="font-weight:bold;">卖家产品技术标准申报值</td>
                    <td width="20%">A1技术级别参考值</td>
                    <td width="20%">A2技术级别参考值</td>
                    <td width="20%">A3技术级别参考值</td>
                </tr>
                <c:forEach items="${technicalStdClaList}" var="technicalStdCla">
                    <tr class="treegrid-${technicalStdCla.tncStdClaId}" style="background-color:#F8F8FF">
                        <td width="20%">${technicalStdCla.tncStdClaName}</td>
                        <td width="20%"></td>
                        <td width="20%"></td>
                        <td width="20%"></td>
                        <td width="20%"></td>
                    </tr>
                    <c:if test="${technicalStdCla.isCatalog eq '0'}">
	                    <c:forEach items="${technicalStdCla.technicalStdClaList}" var="subTechnicalStdCla" varStatus="i">
	                    <tr class="treegrid-${i.index} treegrid-parent-${technicalStdCla.tncStdClaId}">  
	                        <td>
	                        ${subTechnicalStdCla.tncStdClaName}
	                        <input type="hidden" name="pdTncStdIdArray" value="${subTechnicalStdCla.technicalStandard.pdTncStdId}">
	                        </td>
	                        <td style="font-weight:bold;">${subTechnicalStdCla.technicalStandard.content1}</td>
	                        <td>${subTechnicalStdCla.technicalStandard.content1}</td>
	                        <td>${subTechnicalStdCla.technicalStandard.content2}</td>
	                        <td>${subTechnicalStdCla.technicalStandard.content3}</td>
	                    </tr> 
	                    </c:forEach>
	                </c:if>
	                <c:if test="${technicalStdCla.isCatalog eq '1'}">
                        <tr class="treegrid-${1} treegrid-parent-${technicalStdCla.tncStdClaId}">  
                            <td>
                            <input type="hidden" name="pdTncStdIdArray" value="${technicalStdCla.technicalStandard.pdTncStdId}">
                            </td>
                            <td style="font-weight:bold;">${technicalStdCla.technicalStandard.content1}</td>
                            <td>${technicalStdCla.technicalStandard.content1}</td>
                            <td>${technicalStdCla.technicalStandard.content2}</td>
                            <td>${technicalStdCla.technicalStandard.content3}</td>
                        </tr> 
	                </c:if>
                </c:forEach>
            </table>
        </div>
    <c:if test="${examineStatus eq '1'}">
        <div class="group-accordion" collapsible="false" active="false">
            <h3><label>卖家产品技术标准定级</label></h3>
            <table style="width: 100%">   
                <tr>
	                <td width="10%">
                        <msk2:button buttonType="button" buttonId="SL241108.Examine1" buttonValue="A1级"/>
                        <%--<msk:button buttonValue="A1级" buttonId="SL241108.Examine1"/>--%>
                    </td>
	                <td width="10%">
                        <msk2:button buttonType="button" buttonId="SL241108.Examine2" buttonValue="A2级"/>
                        <%--<msk:button buttonValue="A2级" buttonId="SL241108.Examine2"/>--%>
                    </td>
	                <td width="10%">
                        <msk2:button buttonType="button" buttonId="SL241108.Examine3" buttonValue="A3级"/>
                        <%--<msk:button buttonValue="A3级" buttonId="SL241108.Examine3"/>--%>
                    </td>
	                <td width="10%">
                        <msk2:button buttonType="button" buttonId="SL241108.Examine4" buttonValue="不通过"/>
                        <%--<msk:button buttonValue="不通过" buttonId="SL241108.Examine4"/>--%>
                    </td>
	                <td width="30%" style="text-align:right">不通过理由</td>
	                <td width="30%"><input name="unExamineReason"></td>
                </tr>
            </table>
        </div>
    </c:if>
    <c:if test="${examineStatus eq '2'}">
        <div class="group-accordion" collapsible="false" active="false">
            <h3><label>卖家产品技术标准定级结果确认</label></h3>
            <table style="width: 100%">   
                <tr>
	                <td width="10%">
                        <msk2:button buttonType="button" buttonId="SL241108.Confirm" buttonValue="正确"/>
                        <%--<msk:button buttonValue="正确" buttonId="SL241108.Confirm"/>--%>
                    </td>
	                <td width="10%"></td>
	                <td width="10%">
                        <msk2:button buttonType="button" buttonId="SL241108.UnConfirm" buttonValue="不正确"/>
                        <%--<msk:button buttonValue="不正确" buttonId="SL241108.UnConfirm"/>--%>
                    </td>
	                <td width="30%" style="text-align:right">不正确理由</td>
	                <td width="40%"><input name="unConfirmReason"></td>
                </tr>
            </table>
        </div>
    </c:if>
    <div>
        <msk2:button buttonType="button" buttonId="SL241108.Return" buttonValue="返回"/>
        <%--<msk:button buttonValue="返回" buttonId="SL241108.Return"/>--%>
    </div>
    <input type="hidden" name="slCode" value="${sl241104Bean.slCode}>
    <input type="hidden" name="slPdId" value="${sl241104Bean.slPdId}>
    <input type="hidden" name="flag">
    </form>
</div>
<script type="text/javascript" src="${ctx}/static/sl/js/SL241108.js"></script>
