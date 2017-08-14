<%-- 
    Title:质量标准设置
    author:jiang_nan
    createDate:2015-12-09
    updateDate:2015-12-09
    updateAuthor:jiang_nan
    updateAuthor:pxg
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/comm/taglib.jsp" %>
<link rel="stylesheet" href="${ctx}/static/js/treegrid/css/jquery.treegrid.css">
<script type="text/javascript" src="${ctx}/static/js/treegrid/js/jquery.treegrid.js"></script>
<script type="text/javascript">
	var BREED_CODE = "${breedCode}";
	var CLASSES_CODE = "${classesCode}";
	var FEATURE_CODE = "${featureCode}";
	var INFO = "${yesOrNo}";
</script>
<form action="${ctx}/PD141105/save" method="post" id="PD141105Form">
	<c:if test="${yesOrNo != 'yes' }">
		<navigation:header title="质量标准设置" backTitleArray="标准档案卡列表" backUrlArray="${ctx}/PD141113/init?classesCode=${classesCode}&breedCode=${breedCode}&featureCode=${featureCode}" ></navigation:header>
	</c:if>
	<c:if test="${yesOrNo == 'yes'}">
		<navigation:header title="质量标准设置" backTitleArray="产品类别信息,标准档案卡列表" backUrlArray="${ctx}/PD141101/init?classesCode=${classesCode}&breedCode=${breedCode}&featureCode=${featureCode},${ctx}/PD141113/init?classesCode=${classesCode}&breedCode=${breedCode}&featureCode=${featureCode}&yesOrNo=${yesOrNo}&filterMap[classesCode]=${classesCode}&filterMap[breedCode]=${breedCode}&filterMap[featureCode]=${featureCode}" ></navigation:header>
	</c:if>

<input type="hidden" name="standardId" value="${standardId}"/>

	<div class="page-content list-page">
	<div class="group-accordion" collapsible="false" active="false">
        <h3>
                <label>质量档案卡基本信息</label>
        </h3>
        <table style="width: 100%" class="dataTable no-footer">
            <tr>
                <td colspan="4" align="center"><strong>神农客</strong></td>
            </tr>
            <tr>
                <td width="80px" align="right" style="background:#DBDBDB">产品类别</td>
                       <td><input name="classesName" readonly="readonly" value="${classesName}"/></td>
                <td width="80px" align="right" style="background:#DBDBDB">产品品种</td>
                       <td><input name="breedName" readonly="readonly" value="${breedName}"/></td>
            </tr>
        </table>
	</div>
	<c:forEach items="${qualityStandardList}" var="qualityStandard" varStatus="k">
		<div class="group-accordion" collapsible="false" active="false">
			<h3>
				<label>${qualityStandard.qltStdClaName}</label>
			</h3>
			<table class="tree dataTable no-footer" style="width: 100%">
    		<c:forEach items="${qualityStandard.qltStdSubClaList}" var="qualitySubStandard" varStatus="j">
    			<tr class="treegrid-${qualitySubStandard.qltStdSubId}" style="background:#DBDBDB">
    				<td width="15%"  >${qualitySubStandard.qltStdSubName}</td>
    				<td width="20%">添加为质量标准　<input type="checkbox" name="flg${k.index}${j.index}" topone="true"/></td>
    				<td width="15%">优良值</td>
    				<td width="15%">合格值</td>
    				<td width="15%">不合格值</td>
    				<td width="15%">备注</td>
    			</tr>
    			<c:forEach items="${qualitySubStandard.qltStdItemAndQltstdList}" var="qualityStandardValue" varStatus="i">
    				<tr class="treegrid-${i.index} treegrid-parent-${qualitySubStandard.qltStdSubId}">
        				<td>
        				${qualityStandardValue.qltStdItemName}
        				<input type="hidden" name="pdQltStdValIdArray" value="${qualityStandardValue.qltStdItemId}">
        				</td>
        				<c:choose>
        				    <c:when test="${qualityStandardValue.delFlg eq 1}">
        				        <td><input checkFlg="flg${k.index}${j.index}" type="checkbox" isCheck="${k.index+1}_${j.index+1}_${i.index+1}" name="checks"  value="1"/></td>
								<td><input name="pdQltStdExcValArray" value="${qualityStandardValue.qltStdExcVal}"  id="input1_${k.index+1}_${j.index+1}_${i.index+1}" readonly="readonly"/></td>
								<td><input name="pdQltStdSuitValArray" value="${qualityStandardValue.qltStdSuitVal}"  id="input2_${k.index+1}_${j.index+1}_${i.index+1}" readonly="readonly"/></td>
								<td><input name="pdQltStdUnqualValArray" value="${qualityStandardValue.qltStdUnqualVal}"  id="input3_${k.index+1}_${j.index+1}_${i.index+1}" readonly="readonly"/></td>
								<td><input name="remarkArray" value="${qualityStandardValue.remark}"  id="input4_${k.index+1}_${j.index+1}_${i.index+1}" readonly="readonly"/></td>
        				    </c:when>
        				    <c:otherwise>
        				        <td><input  checkFlg="flg${k.index}${j.index}"  type="checkbox" isCheck="${k.index+1}_${j.index+1}_${i.index+1}" name="checks" checked="checked" value="0"/></td>
								<td><input name="pdQltStdExcValArray" value="${qualityStandardValue.qltStdExcVal}" id="input1_${k.index+1}_${j.index+1}_${i.index+1}"/></td>
								<td><input name="pdQltStdSuitValArray" value="${qualityStandardValue.qltStdSuitVal}" id="input2_${k.index+1}_${j.index+1}_${i.index+1}" /></td>
								<td><input name="pdQltStdUnqualValArray" value="${qualityStandardValue.qltStdUnqualVal}" id="input3_${k.index+1}_${j.index+1}_${i.index+1}"/></td>
								<td><input name="remarkArray" value="${qualityStandardValue.remark}" id="input4_${k.index+1}_${j.index+1}_${i.index+1}"/></td>
        				    </c:otherwise>
        				</c:choose>
    				</tr>
    			</c:forEach>
    		</c:forEach>
			</table>
		</div>
	</c:forEach>
	<div>
        <msk:button buttonValue="保存" buttonId="PD141105.SAVE" buttonType="button"/>
    </div>
</div>
</form>
<script type="text/javascript" src="${ctx}/static/js/pd/PD141105.js"></script>
