<%-- 
    Title:标准信息
    author:jiang_nan
    createDate:2015-12-10
    updateDate:2015-12-10
    updateAuthor:jiang_nan
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/comm/taglib.jsp" %>
<link rel="stylesheet" href="${ctx}/static/js/treegrid/css/jquery.treegrid.css">
<script type="text/javascript" src="${ctx}/static/js/treegrid/js/jquery.treegrid.js"></script>
<script type="text/javascript">
    $('.tree').treegrid(); 
</script>
<div class="page-header">
    <span class="page-title"> <label>${standard.classesName}类 ${standard.breedName} 神农客卫士技术标准和质量标准档案卡</label>
    </span>
</div>
<div class="page-content list-page">
    <div class="group-accordion" collapsible="false" active="false">
        <h3>
                <label>档案卡基本信息</label>
        </h3>
        <table style="width: 100%" class="dataTable no-footer">
            <tr>
                <td colspan="4" align="center"><strong>神农客</strong></td>
            </tr>
            <tr>
                <td width="80px" align="right" style="background:#DBDBDB">类别编号</td>
                <td width="40%">${standard.classesCode}</td>
                <td width="80px" align="right" style="background:#DBDBDB">类别名称</td>
                <td>${standard.classesName}</td>
            </tr>
            <tr>
                <td align="right" style="background:#DBDBDB">品种编号</td>
                <td>${standard.breedCode}</td>
                <td align="right" style="background:#DBDBDB">品种名称</td>
                <td>${standard.breedName}</td>
            </tr>
        </table>
    </div>
    <div class="group-accordion" collapsible="true" active="false">
        <h3>
                <label>质量标准信息</label>
        </h3>
            <table class="tree dataTable no-footer" style="width: 100%" > 
                <c:forEach items="${qualityStandardList}" var="qualityStandard">
                <tr class="treegrid-${qualityStandard.pdStdId}" style="background:#DBDBDB">
                    <td width="20%" align="center"><strong>${qualityStandard.pdQuaStdName}</strong></td>
                    <td width="20%" align="center"><strong>优良值</strong></td>
                    <td width="20%" align="center"><strong>合格值</strong></td>
                    <td width="20%" align="center"><strong>不合格值</strong></td>
                    <td width="20%" align="center"><strong>备注</strong></td>
                </tr>
                <c:forEach items="${qualityStandard.qualitySubStandardList}" var="qualitySubStandard">
                    <tr class="treegrid-${qualitySubStandard.pdQuaStdSubId}-${qualityStandard.pdStdId}">
                        <td width="20%">${qualitySubStandard.pdQuaStdSubName}</td>
                        <td width="20%"></td>
                        <td width="20%"></td>
                        <td width="20%"></td>
                        <td width="20%"></td>
                    </tr>
                    <c:forEach items="${qualitySubStandard.qualityStandardValueList}" var="qualityStandardValue" varStatus="i">
                    <tr class="treegrid-${i.index} treegrid-parent-${qualitySubStandard.pdQuaStdSubId}-${qualityStandard.pdStdId}">  
                        <td>${qualityStandardValue.pdQuaStdValName}</td>
                        <td>${qualityStandardValue.pdQuaStdExcVal}</td>
                        <td>${qualityStandardValue.pdQuaStdSuitVal}</td>
                        <td>${qualityStandardValue.pdQuaStdUnqualVal}</td>
                        <td>${qualityStandardValue.remark}</td>
                    </tr> 
                    </c:forEach>
                </c:forEach>
                </c:forEach>
            </table>
    </div>
    <div class="group-accordion" collapsible="true" active="false">
      <h3>
          <label>技术标准信息</label>
      </h3>
      <table class="tree dataTable no-footer" style="width: 100%">  
               <tr style="background:#DBDBDB">
                   <td width="25%"></td>
                   <td width="25%">A1级技术标准</td>
                   <td width="25%">A2级技术标准</td>
                   <td width="25%">A3级技术标准</td>
               </tr>
           <c:forEach items="${technicalStdClaList}" var="technicalStdCla">
               <tr class="treegrid-${technicalStdCla.tncStdClaId}" style="background-color:#F8F8FF">
                   <td width="25%">${technicalStdCla.tncStdClaName}</td>
                   <td width="25%"></td>
                   <td width="25%"></td>
                   <td width="25%"></td>
               </tr>
               <c:if test="${technicalStdCla.isCatalog eq '0'}">
                   <c:forEach items="${technicalStdCla.technicalStdClaList}" var="subTechnicalStdCla" varStatus="i">
                       <tr class="treegrid-${i.index} treegrid-parent-${technicalStdCla.tncStdClaId}">  
                           <td>${subTechnicalStdCla.tncStdClaName}</td>
                           <td>${subTechnicalStdCla.technicalStandard.content1}</td>
                           <td>${subTechnicalStdCla.technicalStandard.content2}</td>
                           <td>${subTechnicalStdCla.technicalStandard.content3}</td>
                       </tr> 
                   </c:forEach>
                  </c:if>
                  <c:if test="${technicalStdCla.isCatalog eq '1'}">
                   <tr class="treegrid-${1} treegrid-parent-${technicalStdCla.tncStdClaId}">  
                       <td></td>
                       <td>${technicalStdCla.technicalStandard.content1}</td>
                       <td>${technicalStdCla.technicalStandard.content2}</td>
                       <td>${technicalStdCla.technicalStandard.content3}</td>
                   </tr> 
                  </c:if>
           </c:forEach>
      </table> 
    </div>
</div>
