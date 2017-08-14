   <%-- Title:存储运输标准指标
    author:fjm
    createDate:2016-3-3
    updateDate:2016-3-3--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<link rel="stylesheet" href="${ctx}/static/js/treegrid/css/jquery.treegrid.css">
<script type="text/javascript" src="${ctx}/static/js/treegrid/js/jquery.treegrid.js"></script>

   <navigation:header title="储存运输标准指标编辑" backTitleArray="产品分类目录管理" backUrlArray="${ctx}/PD141124/init?classesCode=${classesCode}&machiningCode=${machiningCode}&breedCode=${breedCode}&featureCode=${featureCode}&weightCode=${weightCode}"></navigation:header>
<div class="page-content list-page" style="height:100%">
<form action="${ctx}/pd141152/saveAndEdit" method="post" id="PD141152Form">
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
                <label>存储运输标准录入</label>
            </h3>
            <table class="tree dataTable no-footer" style="width: 100%">
                <tr style="background:#DBDBDB">
                    <td></td>
                    <td width="25%">合格</td>
                    <td width="25%">不合格</td>
                </tr>
                <c:forEach items="${list}" var="listParent">
                    <tr class="treegrid-${listParent.tspStdItemId}" style="background-color:#F8F8FF">
                        <td width="25%">${listParent.tspStdItemName}</td>
                        <td></td>
                        <td width="25%"></td>
                    </tr>
                    <c:forEach items="${listParent.tspList}" var="sftBean">
                        <tr class="treegrid-${sftBean.tspStdItemId} treegrid-parent-${listParent.tspStdItemId}">
                            <td>${sftBean.tspStdItemName}</td>
                            <td style="display:none;"><input type="hidden" name="tspItemIdArray"
                                                             value="${sftBean.tspStdItemId}"/></td>
                            <td width="25%"><textarea name="tspOkValArray" rows="3" cols="30"
                                                      style="resize: none;">${sftBean.tspOkVal}</textarea></td>
                            <td width="25%"><textarea name="tspNgValArray" rows="3" cols="30"
                                                      style="resize: none;">${sftBean.tspNgVal}</textarea></td>
                        </tr>
                    </c:forEach>
                </c:forEach>
            </table>
        </div>
    </div>
    <div>
        <msk:button buttonValue="保存" buttonId="PD141152.SAVE" buttonType="button"/>
    </div>
</form>
</div>
<script type="text/javascript" src="${ctx}/static/js/pd/PD141152.js"></script>
