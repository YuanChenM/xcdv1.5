<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
    Title:产品总控目录在线管理表
    author:pxg
    createDate:2016-02-22
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<link rel="stylesheet" href="${ctx}/static/js/treegrid/css/jquery.treegrid.css">
<script type="text/javascript" src="${ctx}/static/js/treegrid/js/jquery.treegrid.js"></script>
<navigation:header title="产品加工质量标准" backTitleArray="产品分类目录管理" backUrlArray="${ctx}/PD141124/init?classesCode=${classesCode}&machiningCode=${machiningCode}&breedCode=${breedCode}&featureCode=${featureCode}&weightCode=${weightCode}"></navigation:header>
<div class="page-content list-page" style="height:100%">
<form action="${ctx}/pd141149/saveAndEdit" method="post" id="PD141149Form">
    <input type="hidden" name="standardId" value="${standardId}"/>
    <input type="hidden" name="classesCode" value="${classesCode}"/>
    <div class="page-content list-page" style="height:100%">
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
        <div class="group-accordion" collapsible="false" active="false" id="pd141149accordion">
            <h3 >
                <label>产品加工质量标准指标</label>
            </h3>

            <div>
                <table class="tree dataTable no-footer" style="width: 100%" id="PD141149_list_grid">
                    <tr style="background:#DBDBDB">
                        <td  width="12%" align="center" rowspan="3">指标目录</td>
                        <td  width="12%" align="center" rowspan="3">A1级技术标准<br/>(准入日)</td>
                        <td  width="12%" align="center" rowspan="3">A2级技术标准<br/>(准入日)</td>
                        <td  width="12%" align="center" rowspan="3">A3级技术标准<br/>(准入日)</td>
                        <td  width="12%" align="center" colspan="2" width="40px">论证中技术标准</td>
                        <td  width="12%" align="center" colspan="2" width="40px">禁止准入技术标准</td>
                        <td   align="center" rowspan="3">备注</td>
                    </tr>
                    <tr style="background:#DBDBDB">
                        <td align="center">市场需求标准<br/>(提出日、结案日)</td>
                        <td align="center" width="10px">供应商习惯性标准<br/>(提出日、结案日)</td>
                        <td align="center" width="15px">市场提出<br/>(禁止准入日)</td>
                        <td align="center">供应商提出<br/>(禁止准入日)</td>
                    </tr>
                    <tr></tr>
                    <%--     查询一级指标--%>
                    <c:forEach items="${pd141107Beans}" var="pdTncTd" varStatus="j">
                        <tr class="treegrid-${pdTncTd.tncStdItemId}" style="background-color:#F8F8FF">
                            <td width="8%">${pdTncTd.tncStdItemName}</td>
                            <td width="8%"></td>
                            <td width="8%"></td>
                            <td width="8%"></td>
                            <td width="8%"></td>
                            <td width="8%"></td>
                            <td width="8%"></td>
                            <td width="8%"></td>
                            <td></td>
                        </tr>
                        <c:if test="${pdTncTd.isCatalog eq '0'}">
                        <c:forEach items="${pdTncTd.pdTncStdList}" var="pdTncTdValue" varStatus="i">
                            <tr class="treegrid-${i.index} treegrid-parent-${pdTncTd.tncStdItemId}">

                                <td>${pdTncTdValue.tncStdItemName}
                                    <input type="hidden" name="pdTncTdItemIdArray" value="${pdTncTdValue.tncStdItemId}">
                                </td>

                                <td><textarea name="content1Array" rows="3" cols="25"
                                              style="resize: none;">${pdTncTdValue.tncStdVal1}</textarea></td>
                                <td><textarea name="content2Array" rows="3" cols="25"
                                              style="resize: none;">${pdTncTdValue.tncStdVal2}</textarea></td>
                                <td><textarea name="content3Array" rows="3" cols="25"
                                              style="resize: none;">${pdTncTdValue.tncStdVal3}</textarea></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td><textarea name="remarkArray" rows="3" cols="25" style="resize: none;">${pdTncTdValue.remark}</textarea></td>
                            </tr>
                        </c:forEach>
                        </c:if>
                        <c:if test="${pdTncTd.isCatalog eq '1'}">
                            <tr class="treegrid-${1} treegrid-parent-${pdTncTd.tncStdItemId}">
                                <td>
                                    <input type="hidden" name="pdTncTdItemIdArray" value="${pdTncTd.tncStdItemId}">
                                </td>
                                <td><textarea name="content1Array" rows="3" cols="25" style="resize: none;">${pdTncTd.tncStdVal1}</textarea></td>
                                <td><textarea name="content2Array" rows="3" cols="25" style="resize: none;">${pdTncTd.tncStdVal2}</textarea></td>
                                <td><textarea name="content3Array" rows="3" cols="25" style="resize: none;">${pdTncTd.tncStdVal3}</textarea></td>
                                <td id="argMarNeed" valign="top">
                                    <a class="argMarNeedBtn${j.index}" name="argMarNeedBtn" href="javascript:void(0);" col="10"><img border="0px" src="${ctx}/static/core/images/action/details_open.png" id="argMarNeedImg${j.index}"/></a>
                                    <div style="display:none;margin-left:20px;margin-right:0px;width:300px" id="divArgMarNeed${j.index}" >
                                        <table class="tree dataTable no-footer" style="min-width:250px" width="250px" showAddBtn="true">
                                            <tr style="background:#DBDBDB">
                                                <td align="center">内容 <a class="argTncMar" tncStdItemId="${pdTncTd.tncStdItemId}" needId="${j.index}" id="add" title="添加" href="javascript:void(0);" col="10"><img border="0px" src="${ctx}/static/core/images/action/add.png" style="width:13px;height:13px"></a></td>
                                                <td align="center">提出日</td>
                                                <td align="center">结案日</td>
                                                <td align="center">操作</td>
                                            </tr>
                                            <c:forEach items="${pdTncTd.pdTncMarkeyList}" var="tncMarkey">
                                                <tr>
                                                    <td align="center">${tncMarkey.tncStdVal}</td>
                                                    <td align="center">${tncMarkey.raiseDateShow}</td>
                                                    <td align="center">${tncMarkey.fixDateShow}</td>
                                                    <td width="10px">
                                                        <c:if test="${tncMarkey.discussStatus eq '0'}">
                                                         <a class="argTncMarJan" keyId="${tncMarkey.keyId}" fixDate="${tncMarkey.fixDateShow}" needId="${j.index}" tncStdItemId="${pdTncTd.tncStdItemId}" raiseDate="${tncMarkey.raiseDateShow}" title="结案日" href="javascript:void(0);" col="10"><img border="0px" src="${ctx}/static/core/images/action/jiean.png" style="width:13px;height:13px"></a>
                                                         <a class="argTncMarJin" keyId="${tncMarkey.keyId}" tncStdItemId="${pdTncTd.tncStdItemId}" needId="${j.index}" title="禁止" href="javascript:void(0);" col="10"><img border="0px" src="${ctx}/static/core/images/action/jinzhizhunren.png" style="width:13px;height:13px"></a>
                                                        </c:if>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </table>
                                    </div>
                                </td>
                                <!-- 供应商列表 -->
                                <td id="argProNeed" valign="top">
                                    <a class="argProNeedBtn${j.index}" name="argProNeedBtn" href="javascript:void(0);" col="10"><img border="0px" src="${ctx}/static/core/images/action/details_open.png" id="argProNeedImg${j.index}"/></a>
                                    <div style="display:none;margin-left:20px;margin-right:0px;width:350px" id="divArgProNeed${j.index}" >
                                        <table class="tree dataTable no-footer" style="min-width:250px" width="250px" showAddBtn="true">
                                            <tr style="background:#DBDBDB">
                                                <td align="center">供应商编码</td>
                                                <td align="center">内容</td>
                                                <td align="center">提出日</td>
                                                <td align="center">结案日</td>
                                                <td align="center">操作</td>
                                            </tr>
                                            <c:forEach items="${pdTncTd.pdTncProviderList}" var="tncProkey">
                                                <tr>
                                                    <td align="center">${tncProkey.slPdId}</td>
                                                    <td align="center">${tncProkey.tncStdVal}</td>
                                                    <td align="center">${tncProkey.proRaiseDateShow}</td>
                                                    <td align="center">${tncProkey.proFixDateShow}</td>
                                                    <td width="10px">
                                                        <c:if test="${tncProkey.discussStatus eq '0'}">
                                                        <a class="argTncProJan" keyId="${tncProkey.keyId}" fixDate="${tncProkey.proFixDateShow}" needId="${j.index}" raiseDate="${tncProkey.proRaiseDateShow}" tncStdItemId="${pdTncTd.tncStdItemId}" title="结案日" href="javascript:void(0);" col="10"><img border="0px" src="${ctx}/static/core/images/action/jiean.png" style="width:13px;height:13px"></a>
                                                        <a class="argTncProJin" keyId="${tncProkey.keyId}" tncStdItemId="${pdTncTd.tncStdItemId}" needId="${j.index}"  title="禁止" href="javascript:void(0);" col="10"><img border="0px" src="${ctx}/static/core/images/action/jinzhizhunren.png" style="width:13px;height:13px"></a>
                                                        </c:if>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </table>
                                    </div>
                                </td>
                                <!-- 市场禁止日 -->
                                <td id="argMarNeedNo" valign="top">
                                    <a class="argMarNeedNoBtn${j.index}" name="argMarNeedNoBtn" href="javascript:void(0);" col="10"><img border="0px" src="${ctx}/static/core/images/action/details_open.png" id="argMarNeedNoImg${j.index}"/></a>
                                    <div style="display:none;margin-left:20px;margin-right:0px;width:260px" id="divArgMarNoNeed${j.index}" >
                                        <table class="tree dataTable no-footer" style="min-width:250px" width="250px" showAddBtn="true">
                                            <tr style="background:#DBDBDB">
                                                <td align="center">内容</td>
                                                <td align="center">禁止准入日</td>
                                            </tr>
                                            <c:forEach items="${pdTncTd.pdTncMarkeyNoList}" var="tncMarkeyNo">
                                                <tr>
                                                    <td align="center">${tncMarkeyNo.tncStdVal}</td>
                                                    <td align="center">${tncMarkeyNo.fixDateShow}</td>
                                                </tr>
                                            </c:forEach>
                                        </table>
                                    </div>
                                </td>
                                <!-- 供应商禁止日 -->
                                <td valign="top">
                                    <a class="argProNeedNoBtn${j.index}" name="argProNeedNoBtn" href="javascript:void(0);" col="10"><img border="0px" src="${ctx}/static/core/images/action/details_open.png" id="argProNeedNoImg${j.index}"/></a>
                                    <div style="display:none;margin-left:20px;margin-right:0px;width:260px" id="divArgProNoNeed${j.index}" >
                                        <table class="tree dataTable no-footer" style="min-width:250px" width="250px" showAddBtn="true">
                                            <tr style="background:#DBDBDB">
                                                <td align="center">生产商编码</td>
                                                <td align="center">内容</td>
                                                <td align="center">禁止准入日</td>
                                            </tr>
                                            <c:forEach items="${pdTncTd.pdTncProviderNoList}" var="tncProkeyNo">
                                                <tr>
                                                    <td align="center">${tncProkeyNo.slPdId}</td>
                                                    <td align="center">${tncProkeyNo.tncStdVal}</td>
                                                    <td align="center">${tncProkeyNo.proFixDateShow}</td>
                                                </tr>
                                            </c:forEach>
                                        </table>
                                    </div>
                                </td>
                                <td><textarea name="remarkArray" rows="3" cols="25" style="resize: none;">${pdTncTd.remark}</textarea></td>
                            </tr>
                        </c:if>
                    </c:forEach>
                </table>
            </div>
        </div>
        <div>
            <msk:button buttonValue="保存" buttonId="PD141149.SAVE" buttonType="button"/>
        </div>
    </div>
</form>
</div>
<script src="${ctx}/static/js/pd/PD141149.js"></script>
