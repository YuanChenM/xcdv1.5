<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
    Title:产品加工技术标准指标
    autdor:xhy
    createDate:2016-2-26
    updateDate:2016-2-26
    updateAutdor:xhy
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<navigation:header title="产品加工技术标准" backTitleArray="产品分类目录管理" backUrlArray="${ctx}/PD141124/init?classesCode=${classesCode}&machiningCode=${machiningCode}&breedCode=${breedCode}&featureCode=${featureCode}&weightCode=${weightCode}"></navigation:header>
<div class="page-content list-page" style="height:100%">
<form action="${ctx}/pd141148/saveAndEdit" metdod="post" id="PD141148Form">
    <input type="hidden" name="standardId" value="${standardId}"/>
    <input type="hidden" name="classesCode" value="${classesCode}"/>
    <input type="hidden" name="machiningCode" value="${machiningCode}"/>
    <input type="hidden" name="breedCode" value="${breedCode}"/>
    <input type="hidden" name="featureCode" value="${featureCode}"/>
    <input type="hidden" name="weightCode" value="${weightCode}"/>
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
                <label>产品加工技术标准指标修改</label>
            </h3>


            <table class="tree dataTable no-footer" id="PD141148_list_grid" WIDTH="100%">
                <tr style="background: #DBDBDB;width:100%" >
                    <td  width="20%" rowspan="2" align="center" height="">指标类容</td>
                    <td width="20%" colspan="2" align="center">等级</td>
                    <td rowspan="2" align="center">论证中<br/>技术</td>
                </tr>
                <tr style="background: #DBDBDB;width:100%">
                    <td  align="center" width="33%" >合格</td>
                    <td  align="center" width="34%">不合格</td>
                </tr>
                <c:forEach items="${list}" var="mct" varStatus="j">
                    <tr >
                        <td width="15%"  align="center">${mct.mctStdItemName}</td>
                        <td width="15%"><textarea name="mctOkValArray"  rows="3" cols="30" style="resize: none;">${mct.mctOkVal}</textarea></td>
                        <td width="15%"><textarea name="mctNgValArray"  rows="3" cols="30" style="resize: none;">${mct.mctNgVal}</textarea></td>
                        <td style="display:none;"><input type="hidden"  name="mctStdItemIdArray" value="${mct.mctStdItemId}"/></td>
                        <td id="mctProNeed">
                            <a class="mctProNeedBtn${j.index}" name="mctProNeedBtn" href="javascript:void(0);" col="10"><img src="${ctx}/static/core/images/action/details_open.png" id="mctProNeedImg${j.index}"/></a>
                            <div style="display:none;margin-left:15px;margin-right:0px;width:350px" id="divMctProNeed${j.index}" >
                                <table class="tree dataTable no-footer" style="min-width:250px" width="250px" showAddBtn="true">
                                    <tr style="background:#DBDBDB">
                                        <td align="center">供应商编码</td>
                                        <td align="center">内容</td>
                                        <td align="center">提出日</td>
                                        <td align="center">结案日</td>
                                        <td align="center">操作</td>
                                    </tr>
                                    <c:forEach items="${mct.mctProList}" var="mctPro">
                                        <tr>
                                            <td align="center">${mctPro.slPdId}</td>
                                            <td align="center">${mctPro.mctStdVal}</td>
                                            <td align="center">${mctPro.raiseDateShow}</td>
                                            <td align="center">${mctPro.fixDateShow}</td>
                                            <td width="10px">
                                                <c:if test="${mctPro.discussStatus eq '0'}">
                                                    <a class="mctProJan" keyId="${mctPro.keyId}" fixDate="${mctPro.fixDateShow}" needId="${j.index}" raiseDate="${mctPro.raiseDateShow}" mctStdItemId="${mctPro.mctStdItemId}" title="结案日" href="javascript:void(0);" col="10"><img src="${ctx}/static/core/images/action/jiean.png" style="width:13px;height:13px"></a>
                                                    <a class="mctProJin" keyId="${mctPro.keyId}" needId="${j.index}" mctStdItemId="${mctPro.mctStdItemId}"  title="禁止" href="javascript:void(0);" col="10"><img src="${ctx}/static/core/images/action/jinzhizhunren.png" style="width:13px;height:13px"></a>
                                                </c:if>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </table>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>

        <div>
            <msk:button buttonValue="保存" buttonId="PD141148.SAVE" buttonType="button"/>
        </div>
    </div>
</form>
    </div>
<script type="text/javascript" src="${ctx}/static/js/pd/PD141148.js"></script>
