<%--
    Title:质量标准设置
    author:jiang_nan
    createDate:2015-12-09
    updateDate:2015-12-09
    updateAuthor:jiang_nan
    updateAuthor:pxg
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<link rel="stylesheet" href="${ctx}/static/js/treegrid/css/jquery.treegrid.css">
<script type="text/javascript" src="${ctx}/static/js/treegrid/js/jquery.treegrid.js"></script>

<navigation:header title="产品安全指标修改" backTitleArray="产品分类目录管理" backUrlArray="${ctx}/PD141124/init?classesCode=${classesCode}&machiningCode=${machiningCode}&breedCode=${breedCode}&featureCode=${featureCode}&weightCode=${weightCode}"></navigation:header>
<div class="page-content list-page" style="height:100%">
<form action="${ctx}/pd141151/saveAndEdit" method="post" id="PD141151Form">
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
                <label>产品安全指标修改</label>
            </h3>
            <table class="tree dataTable no-footer" style="width: 100%">
                <tr style="background:#DBDBDB">
                    <td></td>
                    <td width="25%">合格</td>
                    <td width="25%">不合格</td>
                </tr>
                <c:forEach items="${list}" var="listParent">
                    <tr class="treegrid-${listParent.sftStdItemId}" style="background-color:#F8F8FF">
                        <td width="25%">${listParent.sftStdItemName}</td>
                        <td></td>
                        <td width="25%"></td>
                    </tr>
                    <c:forEach items="${listParent.sftList}" var="sftBean">
                        <tr class="treegrid-${sftBean.sftStdItemId} treegrid-parent-${listParent.sftStdItemId}">
                            <td>${sftBean.sftStdItemName}</td>
                            <td style="display:none;"><input type="hidden" name="sftItemIdArray"
                                                             value="${sftBean.sftStdItemId}"/></td>
                            <td width="25%"><textarea name="sftOkValArray" rows="3" cols="30"
                                                      style="resize: none;">${sftBean.sftOkVal}</textarea></td>
                            <td width="25%"><textarea name="sftNgValArray" rows="3" cols="30"
                                                      style="resize: none;">${sftBean.sftNgVal}</textarea></td>
                        </tr>
                    </c:forEach>
                </c:forEach>
            </table>
        </div>
    </div>
    <div>
        <msk:button buttonValue="保存" buttonId="PD141151.SAVE" buttonType="button"/>
    </div>
</form>
</div>
<script type="text/javascript" src="${ctx}/static/js/pd/PD141151.js"></script>
