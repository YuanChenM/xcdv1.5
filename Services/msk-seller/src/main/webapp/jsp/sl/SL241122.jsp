<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
    Title:卖家产品其他标准设置
    author:gyh
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<%@ taglib prefix="msk2" uri="/msk" %>
<link rel="stylesheet" href="${ctx}/static/js/treegrid/css/jquery.treegrid.css">
<script type="text/javascript" src="${ctx}/static/js/treegrid/js/jquery.treegrid.js"></script>
<script type="text/javascript" src="${ctx}/static/sl/js/SL24110401.js"></script>
<script type="text/javascript" src="${ctx}/static/sl/js/SL24110501.js"></script>
<style type="text/css">
    td {
        white-space: pre
    }
</style>
<form action="${ctx}/SL241122/save" method="post" id="SL241122Form">
    <input type="hidden" name="slCode" id="slCode" value="${sl241116Bean.slCode}"/>
    <input type="hidden" name="slPdId" value="${sl241116Bean.slPdId}"/>
    <input type="hidden" id="slTncGradeCode" value="${sl241116Bean.slTncGradeCode}"/>
    <input type="hidden" id="slQltGradeCode" value="${sl241116Bean.slQltGradeCode}"/>
    <input type="hidden" value="${sl241116Bean.prodEpId}" id="prodEpId"/>
    <input type="hidden" value="${sl241116Bean.brandEpId}" id="brandEpId"/>
    <input type="hidden" value="${sl241116Bean.brandId}" id="brandId"/>
    <input type="hidden" value="${sl241116Bean.pdClassesCode}" id="pdClassesCode"/>
    <input type="hidden" value="${sl241116Bean.machiningCode}" id="machiningCode"/>
    <input type="hidden" value="${sl241116Bean.pdBreedCode}" id="pdBreedCode"/>
    <c:if test="${type eq '1'}">
        <navigation:header
                title="其他标准档案卡"
                backTitleArray="卖家信息列表,产品信息维护"
                backUrlArray="${ctx}/SL241101/initShow,${ctx}/SL241116/init/${sl241116Bean.slCode}"></navigation:header>
    </c:if>
    <c:if test="${type eq '2'}">
        <navigation:header
                title="产品标准档案卡"
                backTitleArray="产品待审批审核卖家列表,卖家产品信息及状态审核"
                backUrlArray="${ctx}/SL241101/init,${ctx}/SL241127/init/${sl241116Bean.slCode}"></navigation:header>
    </c:if>
    <div class="page-content list-page">
        <c:if test="${type eq '2'}">
            <div class="group-accordion" collapsible="true" active="false">
                <h3>
                    <label>加工质量标准指标</label>
                </h3>

                <div id="qltCard"></div>
            </div>
            <div class="group-accordion" collapsible="true" active="false">
                <h3>
                    <label>加工技术标准指标</label>
                </h3>

                <div id="tncCard"></div>
            </div>
            <div class="group-accordion" collapsible="true" active="false">
                <h3>
                    <label>包装标准指标</label>
                </h3>

                <div id="normsCard"></div>
            </div>
        </c:if>
        <c:if test="${sl241116Bean.machiningCode eq 1}">
            <div class="group-accordion" collapsible="true" active="false">
                <h3>
                    <label>原种种源标准指标</label>
                </h3>
                <table class="tree dataTable no-footer" style="width: 100%">
                    <tr style="background: #DBDBDB;width:100%">
                        <td width="20%" rowspan="2" align="center" height="">指标类容</td>
                        <td colspan="6" align="center">等级</td>
                    </tr>
                    <tr style="background: #DBDBDB;width:100%">
                        <td align="center" width="10"></td>
                        <td align="center" width="25%">优良</td>
                        <td align="center" width="10"></td>
                        <td align="center" width="25%">一般</td>
                        <td align="center" width="10"></td>
                        <td align="center" width="25%">差</td>
                    </tr>
                    <c:forEach items="${sl241122Beans}" var="org" varStatus="i">
                        <tr>
                            <td width="25%">${org.orgStdItemName}<input type="hidden" name="orgValArray"
                                                                        id="orgValArray${i.index}"
                                                                        value="${org.agreeFlg}"/></td>
                            <td><input type="radio" subInfo="orgValArray${i.index}" name="orgValArray${i.index+1}"
                                       value="0" hidden="hidden"
                                       <c:if test="${org.agreeFlg eq '0'}">checked="checked"</c:if>/>
                                <input type="radio" subInfo="orgValArray${i.index}" name="orgValArray${i.index+1}"
                                       value="1" <c:if test="${org.agreeFlg eq '1'}">checked="checked"</c:if>/>同意
                            </td>
                            <td width="25%" style="white-space:pre;">${org.orgGoodVal}</td>
                            <td><input type="radio" subInfo="orgValArray${i.index}" name="orgValArray${i.index+1}"
                                       value="2" <c:if test="${org.agreeFlg eq '2'}">checked="checked"</c:if>/>同意
                            </td>
                            <td width="25%" style="white-space:pre;">${org.orgNormalVal}</td>
                            <td><input type="radio" subInfo="orgValArray${i.index}" name="orgValArray${i.index+1}"
                                       value="3" <c:if test="${org.agreeFlg eq '3'}">checked="checked"</c:if>/>同意
                            </td>
                            <td style="white-space:pre;">${org.orgBadVal}</td>
                            <td style="display:none;"><input type="hidden" name="orgStdItemIdArray"
                                                             id="orgStdItemIdArray"
                                                             value="${org.orgStdItemId}_${org.standardId}"/></td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
            <div class="group-accordion" collapsible="true" active="false">
                <h3>
                    <label>原种饲养标准指标</label>
                </h3>
                <table class="tree dataTable no-footer" style="width: 100%">
                    <tr style="background: #DBDBDB;width:100%">
                        <td width="25%" rowspan="2" align="center" height="">指标内容</td>
                        <td colspan="6" align="center">等级</td>
                    </tr>
                    <tr style="background: #DBDBDB;width:100%">
                        <td align="center" width="10"></td>
                        <td align="center" width="25%">优良</td>
                        <td align="center" width="10"></td>
                        <td align="center" width="25%">一般</td>
                        <td align="center" width="10"></td>
                        <td align="center" width="25%">差</td>
                    </tr>
                    <c:forEach items="${sl241123Beans}" var="item" varStatus="i">
                        <td width="25%" style="white-space:pre;">${item.fedStdItemName}<input type="hidden"
                                                                                              name="fedValArray"
                                                                                              id="fedValArray${i.index}"
                                                                                              value="${item.agreeFlg}"/>
                        </td>
                        <td><input type="radio" subInfo="fedValArray${i.index}" name="fedValArray${i.index+1}" value="0"
                                   hidden="hidden"
                                   <c:if test="${item.agreeFlg eq '0'}">checked="checked"</c:if>/>
                            <input type="radio" subInfo="fedValArray${i.index}" value="1" name="fedValArray${i.index+1}"
                                   <c:if test="${item.agreeFlg eq '1'}">checked="checked"</c:if>/>同意
                        </td>
                        <td width="25%" style="white-space:pre;">${item.fedGoodVal}</td>
                        <td><input type="radio" subInfo="fedValArray${i.index}" name="fedValArray${i.index+1}" value="2"
                                   <c:if test="${item.agreeFlg eq '2'}">checked="checked"</c:if>/>同意
                        </td>
                        <td width="25%" style="white-space:pre;">${item.fedNormalVal}</td>
                        <td><input type="radio" subInfo="fedValArray${i.index}" name="fedValArray${i.index+1}" value="3"
                                   <c:if test="${item.agreeFlg eq '3'}">checked="checked"</c:if>/>同意
                        </td>
                        <td style="white-space:pre;">${item.fedBadVal}</td>
                        <td style="display:none;"><input type="hidden" name="fedStdItemIdArray" id="fedStdItemIdArray"
                                                         value="${item.fedStdItemId}_${item.standardId}"/></td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </c:if>
        <div class="group-accordion" collapsible="true" active="false">
            <h3>
                <label>通用质量标准</label>
            </h3>
            <table class="tree dataTable no-footer" style="width: 100%">
                <tr style="background:#DBDBDB">
                    <td></td>
                    <td align="center" width="10"></td>
                    <td width="25%">合格</td>
                    <td align="center" width="10"></td>
                    <td width="25%">不合格</td>
                </tr>
                <c:forEach items="${sl241124Beans}" var="listParent" varStatus="j">
                    <tr class="treegrid-${listParent.gnqStdItemId}" style="background-color:#F8F8FF">
                        <td width="25%">${listParent.gnqStdItemName}</td>
                        <td align="center" width="10"></td>
                        <td></td>
                        <td align="center" width="10"></td>
                        <td width="25%"></td>
                    </tr>
                    <c:forEach items="${listParent.pdGnqStds}" var="gnqBean" varStatus="i">
                        <tr class="treegrid-${gnqBean.gnqStdItemId} treegrid-parent-${listParent.gnqStdItemId}">
                            <td>${gnqBean.gnqStdItemName}<input type="hidden" name="gnqValArray"
                                                                id="gnqValArray${j.index+1}${i.index+1}"
                                                                value="${gnqBean.agreeFlg}"/></td>
                            <td style="display:none;"><input type="hidden" name="gnqItemIdArray" readonly="readonly"
                                                             value="${gnqBean.gnqStdItemId}_${gnqBean.standardId}"/></td>

                            <td><input type="radio" subInfo="gnqValArray${j.index+1}${i.index+1}"
                                       name="gnqValArray${j.index+1}${i.index+1}" value="0" hidden="hidden"
                                       <c:if test="${gnqBean.agreeFlg eq '0'}">checked="checked"</c:if>/>
                                <input type="radio" subInfo="gnqValArray${j.index+1}${i.index+1}"
                                       name="gnqValArray${j.index+1}${i.index+1}" value="2"
                                       <c:if test="${gnqBean.agreeFlg eq '2'}">checked="checked"</c:if>/>同意
                            </td>
                            <td width="25%" style="white-space:pre;">${gnqBean.gnqOkVal}</td>
                            <td><input type="radio" subInfo="gnqValArray${j.index+1}${i.index+1}"
                                       name="gnqValArray${j.index+1}${i.index+1}" value="3"
                                       <c:if test="${gnqBean.agreeFlg eq '3'}">checked="checked"</c:if>/>同意
                            </td>
                            <td width="25%" style="white-space:pre;">${gnqBean.gnqNgVal}</td>
                        </tr>
                    </c:forEach>
                </c:forEach>
            </table>
        </div>
        <div class="group-accordion" collapsible="true" active="false">
            <h3>
                <label>储存运输标准</label>
            </h3>
            <table class="tree dataTable no-footer" style="width: 100%">
                <tr style="background:#DBDBDB">
                    <td></td>
                    <td align="center" width="10"></td>
                    <td width="25%">合格</td>
                    <td align="center" width="10"></td>
                    <td width="25%">不合格</td>
                </tr>
                <c:forEach items="${sl241125Beans}" var="listParent" varStatus="j">
                    <tr class="treegrid-${listParent.tspStdItemId}" style="background-color:#F8F8FF">
                        <td width="25%">${listParent.tspStdItemName}</td>
                        <td align="center" width="10"></td>
                        <td></td>
                        <td align="center" width="10"></td>
                        <td width="25%"></td>
                    </tr>
                    <c:forEach items="${listParent.pdTspStds}" var="tspBean" varStatus="i">
                        <tr class="treegrid-${tspBean.tspStdItemId} treegrid-parent-${listParent.tspStdItemId}">
                            <td>${tspBean.tspStdItemName}<input type="hidden" name="tspValArray"
                                                                id="tspValArray${j.index+1}${i.index+1}"
                                                                value="${tspBean.agreeFlg}"/></td>
                            <td style="display:none;"><input type="hidden" readonly="readonly" name="tspItemIdArray"
                                                             value="${tspBean.tspStdItemId}_${tspBean.standardId}"/></td>
                            <td><input type="radio" subInfo="tspValArray${j.index+1}${i.index+1}"
                                       name="tspValArray${j.index+1}${i.index+1}" value="0" hidden="hidden"
                                       <c:if test="${tspBean.agreeFlg eq '0'}">checked="checked"</c:if>/>
                                <input type="radio" subInfo="tspValArray${j.index+1}${i.index+1}"
                                       name="tspValArray${j.index+1}${i.index+1}" value="2"
                                       <c:if test="${tspBean.agreeFlg eq '2'}">checked="checked"</c:if>/>同意
                            </td>
                            <td width="25%" style="white-space:pre;">${tspBean.tspOkVal}</td>
                            <td><input type="radio" subInfo="tspValArray${j.index+1}${i.index+1}"
                                       name="tspValArray${j.index+1}${i.index+1}" value="3"
                                       <c:if test="${tspBean.agreeFlg eq '3'}">checked="checked"</c:if>/>同意
                            </td>
                            <td width="25%" style="white-space:pre;">${tspBean.tspNgVal}</td>
                        </tr>
                    </c:forEach>
                </c:forEach>
            </table>
        </div>
        <div class="group-accordion" collapsible="true" active="false">
            <h3>
                <label>安全标准</label>
            </h3>
            <table class="tree dataTable no-footer" style="width: 100%">
                <tr style="background:#DBDBDB">
                    <td></td>
                    <td align="center" width="10"></td>
                    <td width="25%">合格</td>
                    <td align="center" width="10"></td>
                    <td width="25%">不合格</td>
                </tr>
                <c:forEach items="${sl241126Beans}" var="listParent" varStatus="j">
                    <tr class="treegrid-${listParent.sftStdItemId}" style="background-color:#F8F8FF">
                        <td width="25%">${listParent.sftStdItemName}</td>
                        <td align="center" width="10"></td>
                        <td></td>
                        <td align="center" width="10"></td>
                        <td width="25%"></td>
                    </tr>
                    <c:forEach items="${listParent.pdSftStds}" var="sftBean" varStatus="i">
                        <tr class="treegrid-${sftBean.sftStdItemId} treegrid-parent-${listParent.sftStdItemId}">
                            <td>${sftBean.sftStdItemName}<input type="hidden" name="sftValArray"
                                                                id="sftValArray${j.index+1}${i.index+1}"
                                                                value="${sftBean.agreeFlg}"/></td>
                            <td style="display:none;"><input type="hidden" readonly="readonly" name="sftItemIdArray"
                                                             value="${sftBean.sftStdItemId}_${sftBean.standardId}"/></td>
                            <td><input type="radio" subInfo="sftValArray${j.index+1}${i.index+1}"
                                       name="sftValArray${j.index+1}${i.index+1}" value="0" hidden="hidden"
                                       <c:if test="${sftBean.agreeFlg eq '0' || empty sftBean.agreeFlg }">checked="checked"</c:if>/>
                                <input type="radio" subInfo="sftValArray${j.index+1}${i.index+1}"
                                       name="sftValArray${j.index+1}${i.index+1}" value="2"
                                       <c:if test="${sftBean.agreeFlg eq '2'}">checked="checked"</c:if>/>同意
                            </td>
                            <td width="25%" style="white-space:pre;">${sftBean.sftOkVal}</td>
                            <td><input type="radio" subInfo="sftValArray${j.index+1}${i.index+1}"
                                       name="sftValArray${j.index+1}${i.index+1}" value="3"
                                       <c:if test="${sftBean.agreeFlg eq '3'}">checked="checked"</c:if>/>同意
                            </td>
                            <td width="25%" style="white-space:pre;">${sftBean.sftNgVal}</td>
                        </tr>
                    </c:forEach>
                </c:forEach>
            </table>
        </div>
        <c:if test="${type eq '1'}">
            <div>
            <span>
                <p><input type="checkbox" name="checkAgree">同意神农客标准</p>
                <msk2:button buttonType="button" buttonId="SL241122.SAVE" buttonValue="保存"/>
                <%--<msk:button buttonValue="保存" buttonId="SL241122.SAVE" buttonType="button"/>--%>
            </span>
            </div>
        </c:if>
    </div>
</form>
<script type="text/javascript" src="${ctx}/static/sl/js/SL241122.js"></script>