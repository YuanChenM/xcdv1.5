<%-- 
    Title:实际情况下产品技术数据录入页面
    author:yuan_chen
    createDate:2015-12-10
    updateDate:2015-12-10
    updateAuthor:yuan_chen
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/comm/taglib.jsp" %>
<link rel="stylesheet" href="${ctx}/static/js/treegrid/css/jquery.treegrid.css">
<script type="text/javascript" src="${ctx}/static/js/treegrid/js/jquery.treegrid.js"></script>
<div class="page-header">
    <span class="page-title">
        <label>
            <c:if test="${!isFirst}">实际技术值录入</c:if>
            <c:if test="${isFirst}">实际技术申报</c:if>
       </label>
    </span>
</div>
<div class="page-content list-page">
    <div class="group-accordion" collapsible="false" active="false">
        <h3>
             <label>技术标准</label>
        </h3>
        <c:if test="${init}">
            <%@include file="PD141108-Init-header.jspf" %>
        </c:if>
        <c:if test="${not init}">
            <%@include file="PD141108-header.jspf" %>
        </c:if>
    </div>
    <c:if test="${not init}">
        <form action="${ctx}/PD141108/save" id="PD141108SaveForm">
        <input type="hidden" name="sellerCode" value="${standard.sellerCode}"/>
        <input type="hidden" name="classesCode" value="${standard.classesCode}"/>
        <input type="hidden" name="breedCode" value="${standard.breedCode}"/>
        <input type="hidden" name="pdStdId" value="${standard.pdStdId}"/>
        <input type="hidden" name="pdRltMsrId" value="${pdRltMsrId}"/>
        <c:if test="${!isFirst}">
        <div class="group-accordion" collapsible="false" active="false">
            <h3>
                <label>产品信息</label>
            </h3>
            <table style="width: 100%">
                <tr>
                    <td width="50px">产品编号</td>
                    <td width="15%"><input name="pdCode" value="${code}"/></td>
                    <td width="50px">批次编号</td>
                    <td><input name="pdBatchCode" value="${code}"/></td>
                </tr>
            </table>
        </div>
        </c:if>
        <div class="group-accordion" collapsible="false" active="false">
            <h3>
                <label>技术指标测量值录入</label>
            </h3>
            <table class="tree dataTable no-footer" style="width: 100%">   
                <tr style="background:#DBDBDB">
                    <td width="20%"></td>
                    <td width="20%">测量值</td>
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
	                        <c:if test="${empty realityTechnicalList}">
	                           <td><input name="reaContentArray"/></td>
	                        </c:if>
	                        <c:if test="${not empty realityTechnicalList}">
		                        <c:forEach items="${realityTechnicalList}" var="realityTechnical">
		                            <c:if test="${realityTechnical.pdTncStdId eq subTechnicalStdCla.technicalStandard.pdTncStdId}">
		                                <c:set var="reaContent" value="${realityTechnical.content}"/>
		                            </c:if>
		                        </c:forEach>
	                           <td><input name="reaContentArray" value="${reaContent}"/></td>
	                        </c:if>
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
                            <c:if test="${empty realityTechnicalList}">
                               <td><input name="reaContentArray"/></td>
                            </c:if>
                            <c:if test="${not empty realityTechnicalList}">
                                <c:forEach items="${realityTechnicalList}" var="realityTechnical">
                                    <c:if test="${realityTechnical.pdTncStdId eq technicalStdCla.technicalStandard.pdTncStdId}">
                                        <c:set var="reaContent" value="${realityTechnical.content}"/>
                                    </c:if>
                                </c:forEach>
                               <td><input name="reaContentArray" value="${reaContent}"/></td>
                            </c:if>
                            <td>${technicalStdCla.technicalStandard.content1}</td>
                            <td>${technicalStdCla.technicalStandard.content2}</td>
                            <td>${technicalStdCla.technicalStandard.content3}</td>
                        </tr> 
	                </c:if>
                </c:forEach>
            </table>
        </div>
        <div>
            <msk:button buttonValue="保存" buttonId="PD141108.SAVE"/>
        </div>
    </form>
    </c:if>
</div>
<script type="text/javascript" src="${ctx}/static/js/pd/PD141108.js"></script>
