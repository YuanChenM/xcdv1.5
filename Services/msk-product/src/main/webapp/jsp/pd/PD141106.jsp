<%-- 
    Title:实际情况下产品质量数据录入页面
    author:jiang_nan
    createDate:2015-12-09
    updateDate:2015-12-09
    updateAuthor:jiang_nan
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/comm/taglib.jsp" %>
<link rel="stylesheet" href="${ctx}/static/js/treegrid/css/jquery.treegrid.css">
<script type="text/javascript" src="${ctx}/static/js/treegrid/js/jquery.treegrid.js"></script>

<div class="page-header">
    <span class="page-title">
        <label>
            <c:if test="${!isFirst}">实际质量值录入</c:if>
            <c:if test="${isFirst}">实际质量申报</c:if>
       </label>
    </span>
</div>
<div class="page-content list-page">
    <div class="group-accordion" collapsible="false" active="false">
        <h3>
             <label>质量标准</label>
        </h3>
        <c:if test="${init}">
            <%@include file="PD141106-Init-header.jspf" %>
        </c:if>
        <c:if test="${not init}">
            <%@include file="PD141106-header.jspf" %>
        </c:if>
    </div>
    <c:if test="${not init}">
        <form action="${ctx}/PD141106/save" id="PD141106SaveForm">
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
            <table style="width: 100%" class="dataTable no-footer">
                <tr>
                     <c:if test="${empty entity}">
                        <td width="80px" align="right" style="background:#DBDBDB">产品编号</td>
                        <td width="40%">
                            <input name="pdCode" value="${code}"/>
                        </td>
                        <td width="80px" align="right" style="background:#DBDBDB">批次编号</td>
                        <td>
                            <input name="pdBatchCode" value="${code}"/>
                        </td>
                     </c:if>
                     <c:if test="${not empty entity}">
                     <td width="80px" align="right" style="background:#DBDBDB">产品编号</td>
                        <td width="40%">
                            <input name="pdCode" value="${entity.pdCode}" readonly="readonly"/>
                        </td>
                        <td width="80px" align="right" style="background:#DBDBDB">批次编号</td>
                        <td>
                            <input name="pdBatchCode" value="${entity.pdBatchCode}" readonly="readonly"/>
                        </td>
                     </c:if>
                </tr>
            </table>
        </div>
        </c:if>
        <c:forEach items="${qualityStandardList}" var="qualityStandard">
        <div class="group-accordion" collapsible="false" active="false">
            <h3>
                <label>${qualityStandard.pdQuaStdName}录入</label>
            </h3>
            <table class="tree dataTable no-footer" style="width: 100%">  
                <c:forEach items="${qualityStandard.qualitySubStandardList}" var="qualitySubStandard">
                    <tr class="treegrid-${qualitySubStandard.pdQuaStdSubId}" style="background:#DBDBDB">
                        <td width="20%">${qualitySubStandard.pdQuaStdSubName}</td>
                        <td width="20%">实际值</td>
                        <td width="20%">参考值</td>
                        <td width="20%">备注</td>
                    </tr>
                    <c:forEach items="${qualitySubStandard.qualityStandardValueList}" var="qualityStandardValue" varStatus="i">
                    <tr class="treegrid-${i.index} treegrid-parent-${qualitySubStandard.pdQuaStdSubId}">  
                        <td>
                        ${qualityStandardValue.pdQuaStdValName}
                        <input type="hidden" name="pdQuaStdValIdArray" value="${qualityStandardValue.pdQuaStdValId}">
                        </td>
                        <c:if test="${not empty entity}">
                        <c:forEach items="${realityQualityValueList}" var="realityQualityValue">
                            <c:if test="${realityQualityValue.pdQuaStdValId eq qualityStandardValue.pdQuaStdValId}">
                                <c:set var="pdReaVal" value="${realityQualityValue.pdReaVal}"/>
                                <c:set var="remark" value="${realityQualityValue.remark}"/>
                            </c:if>
                        </c:forEach>
                        <td><input name="pdReaValArray" value="${pdReaVal}"/></td>
                        <td>${qualityStandardValue.pdQuaStdSuitVal}</td>
                        <td><input name="remarkArray" value="${remark}"/></td>
                        </c:if>
                        <c:if test="${empty entity}">
                        <td><input name="pdReaValArray"/></td>
                        <td>${qualityStandardValue.pdQuaStdSuitVal}</td>
                        <td><input name="remarkArray"/></td>
                        </c:if>
                    
                    </tr> 
                    </c:forEach>
                </c:forEach>
            </table>
        </div>
        </c:forEach>
        <div>
            <msk:button buttonValue="保存" buttonId="PD141106.SAVE"/>
        </div>
    </form>
    </c:if>
</div>
<script type="text/javascript" src="${ctx}/static/js/pd/PD141106.js"></script>
