<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
    Title:品种种源标准指标
    autdor:xhy
    createDate:2016-2-26
    updateDate:2016-2-26
    updateAutdor:xhy
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<navigation:header title="产品原种种源标准指标修改" backTitleArray="产品分类目录管理" backUrlArray="${ctx}/PD141124/init?classesCode=${classesCode}&machiningCode=${machiningCode}&breedCode=${breedCode}&featureCode=${featureCode}&weightCode=${weightCode}"></navigation:header>
<div class="page-content list-page" style="height:100%">
<form action="${ctx}/pd141146/saveAndEdit" metdod="post" id="PD141146Form">
    <input type="hidden" name="standardId" value="${standardId}"/>
    <input type="hidden" name="classesCode" value="${classesCode}"/>
    <div class="page-content list-page">
        <div>
            </p>
            <table WIDTH="100%">
               <tr>
                   <td width="50%">产品品种:${breedName}</td>
                   <c:if test="${feaNames != null and feaNames!=''}">
                       <td width="50%">产品特征:${feaNames}</td>
                   </c:if>
               </tr>
            </table>
            </p>
        </div>
        <div class="group-accordion" collapsible="false" active="false">
            <h3>
                <label>产品原种种源标准指标修改</label>
            </h3>

            <div>
            <table class="tree dataTable no-footer" id="PD141146_list_grid" WIDTH="100%">
                <tr style="background: #DBDBDB;width:100%" >
                    <td  width="25%" rowspan="2" align="center" height="">指标类容</td>
                    <td colspan="3" align="center">等级</td>
                </tr>
                <tr style="background: #DBDBDB;width:100%">
                    <td  align="center" width="25%" >优良</td>
                    <td  align="center" width="25%">一般</td>
                    <td  align="center" width="25%">差</td>
                </tr>
                <c:forEach items="${list}" var="org" >
                    <tr >
                        <td width="25%"  align="center">${org.orgStdItemName}</td>
                        <td width="25%"><textarea name="orgGoodValArray"  rows="3" cols="30" style="resize: none;">${org.orgGoodVal}</textarea></td>
                        <td width="25%"><textarea name="orgNormalValArray"  rows="3" cols="30" style="resize: none;">${org.orgNormalVal}</textarea></td>
                        <td ><textarea name="orgBadValArray"  rows="3" cols="30" style="resize: none;">${org.orgBadVal}</textarea></td>
                        <td style="display:none;"><input type="hidden"  name="orgStdItemIdArray" id="orgStdItemIdArray" value="${org.orgStdItemId}"/></td>
                    </tr>
                </c:forEach>
            </table>
            </div>
            </div>

        <div>
            <msk:button buttonValue="保存" buttonId="PD141146.SAVE" buttonType="button"/>
        </div>
     </div>
</form>
</div>
<script type="text/javascript" src="${ctx}/static/js/pd/PD141146.js"></script>
