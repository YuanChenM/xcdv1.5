<%-- 
    Title:技术标准设置
    author:yuan_chen
    createDate:2015-12-09
    updateDate:2015-12-09
    updateAuthor:yuan_chen
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<link rel="stylesheet" href="${ctx}/static/js/treegrid/css/jquery.treegrid.css">
<script type="text/javascript" src="${ctx}/static/js/treegrid/js/jquery.treegrid.js"></script>
<script type="text/javascript">
    var BREED_CODE = "${breedCode}";
    var CLASSES_CODE = "${classesCode}";
    var FEATURE_CODE = "${featureCode}";
    var INFO = "${yesOrNo}";
</script>
<form action="${ctx}/PD141107/save" method="post" id="PD141107Form">
    <input type="hidden" name="pdStdId" value="${pdStd.pdStdId}"/>
    <c:if test="${yesOrNo != 'yes' }">
        <navigation:header title="技术标准设置" backTitleArray="标准档案卡列表" backUrlArray="${ctx}/PD141113/init?classesCode=${classesCode}&breedCode=${breedCode}&featureCode=${featureCode}" ></navigation:header>
    </c:if>
    <c:if test="${yesOrNo == 'yes'}">
        <navigation:header title="技术标准设置" backTitleArray="产品类别信息,标准档案卡列表" backUrlArray="${ctx}/PD141101/init?classesCode=${classesCode}&breedCode=${breedCode}&featureCode=${featureCode},${ctx}/PD141113/init?classesCode=${classesCode}&breedCode=${breedCode}&featureCode=${featureCode}&yesOrNo=${yesOrNo}&filterMap[classesCode]=${classesCode}&filterMap[breedCode]=${breedCode}&filterMap[featureCode]=${featureCode}" ></navigation:header>
    </c:if>
    <div class="page-content list-page">
        <div class="group-accordion" collapsible="false" active="false">
            <h3>
                <label>技术标准档案卡基本信息</label>
            </h3>
            <table style="width: 100%" class="dataTable no-footer">
                <tr>
                    <td colspan="4" align="center"><strong>神农客</strong></td>
                </tr>
                <tr>
                    <td width="80px" align="right" style="background:#DBDBDB">产品类别</td>
                    <td width="40%">${pdStd.classesName}</td>
                    <td width="80px" align="right" style="background:#DBDBDB">产品品种</td>
                    <td>${pdStd.breedName}</td>
                    <input type="hidden" name="classesCode" value="${pdStd.classesCode}">
                    <input type="hidden" name="classesName" value="${pdStd.classesName}">
                    <input type="hidden" name="breedCode" value="${pdStd.breedCode}">
                    <input type="hidden" name="breedName" value="${pdStd.breedName}">
                </tr>
            </table>
        </div>
        <div class="group-accordion" collapsible="false" active="false">
            <h3>
                <label>技术标准录入</label>
            </h3>
            <table class="tree dataTable no-footer" style="width: 100%">
                <tr style="background:#DBDBDB">
                    <td width="25%"></td>
                    <td width="25%">添加为技术标准　<input type="checkbox" id="allCheck"/></td>
                    <td width="25%">A1级技术标准</td>
                    <td width="25%">A2级技术标准</td>
                    <td width="25%">A3级技术标准</td>
                </tr>
                <c:forEach items="${pd141107Beans}" var="pdTncTd" varStatus="j">
                    <tr class="treegrid-${pdTncTd.tncStdItemId}" style="background-color:#F8F8FF">
                        <td width="25%">${pdTncTd.tncStdItemName}</td>
                        <td></td>
                        <td width="25%"></td>
                        <td width="25%"></td>
                        <td width="25%"></td>
                    </tr>
                    <c:if test="${pdTncTd.isCatalog eq '0'}">
                        <c:forEach items="${pdTncTd.pdTncStdList}" var="pdTncTdValue" varStatus="i">
                            <tr class="treegrid-${i.index} treegrid-parent-${pdTncTd.tncStdItemId}">
                                <td>${pdTncTdValue.tncStdItemName}
                                    <input type="hidden" name="pdTncTdItemIdArray" value="${pdTncTdValue.tncStdItemId}">
                                </td>
                                <td>
                                    <c:if test="${pdTncTdValue.isCheck eq '0'}">
                                    <input type="checkbox" isCheck="${j.index+1}_${i.index+1}" name="checkArray"
                                           value="0"/>有
                                <td><input name="content1Array" value="${pdTncTd.tncStdVal1}"
                                           id="input1_${j.index+1}_${i.index+1}" readonly="readonly"/></td>
                                <td><input name="content2Array" value="${pdTncTd.tncStdVal2}"
                                           id="input2_${j.index+1}_${i.index+1}" readonly="readonly"/></td>
                                <td><input name="content3Array" value="${pdTncTd.tncStdVal3}"
                                           id="input3_${j.index+1}_${i.index+1}" readonly="readonly"/></td>
                                </c:if>
                                <c:if test="${pdTncTdValue.isCheck eq '1'}">
                                    <input type="checkbox" isCheck="${j.index+1}_${i.index+1}" name="checkArray"
                                           value="1" checked="checked"/>有
                                    <td><input name="content1Array" value="${pdTncTdValue.tncStdVal1}"
                                               id="input1_${j.index+1}_${i.index+1}"/></td>
                                    <td><input name="content2Array" value="${pdTncTdValue.tncStdVal2}"
                                               id="input2_${j.index+1}_${i.index+1}"/></td>
                                    <td><input name="content3Array" value="${pdTncTdValue.tncStdVal3}"
                                               id="input3_${j.index+1}_${i.index+1}"/></td>
                                </c:if>
                                </td>

                            </tr>
                        </c:forEach>
                    </c:if>
                    <c:if test="${pdTncTd.isCatalog eq '1'}">
                        <tr class="treegrid-${1} treegrid-parent-${pdTncTd.tncStdItemId}">
                            <td>
                                <input type="hidden" name="pdTncTdItemIdArray" value="${pdTncTd.tncStdItemId}">
                            </td>
                            <c:if test="${pdTncTd.isCheck eq '0'}">

                             <td>
                                <input type="checkbox" name="checkArray" isCheck="${j.index+1}_${i.index+1}" value="0"/>有
                            </td>
                            <td><input name="content1Array" value="${pdTncTd.tncStdVal1}"
                                       id="input1_${j.index+1}_${i.index+1}" readonly="readonly"/></td>
                            <td><input name="content2Array" value="${pdTncTd.tncStdVal2}"
                                       id="input2_${j.index+1}_${i.index+1}" readonly="readonly"/></td>
                            <td><input name="content3Array" value="${pdTncTd.tncStdVal3}"
                                       id="input3_${j.index+1}_${i.index+1}" readonly="readonly"/></td>
                            </c:if>
                            <c:if test="${pdTncTd.isCheck eq '1'}">
                                <td><input type="checkbox" name="checkArray" isCheck="${j.index+1}_${i.index+1}" value="1"
                                       checked="checked"/>有
                                </td>
                                <td><input name="content1Array" value="${pdTncTd.tncStdVal1}"
                                           id="input1_${j.index+1}_${i.index+1}"/></td>
                                <td><input name="content2Array" value="${pdTncTd.tncStdVal2}"
                                           id="input2_${j.index+1}_${i.index+1}"/></td>
                                <td><input name="content3Array" value="${pdTncTd.tncStdVal3}"
                                           id="input3_${j.index+1}_${i.index+1}"/></td>
                            </c:if>
                            </td>

                        </tr>
                    </c:if>
                </c:forEach>
            </table>
        </div>
        <div>
            <msk:button buttonValue="保存" buttonId="PD141107.SAVE" buttonType="button"/>
        </div>
    </div>
</form>
<script type="text/javascript" src="${ctx}/static/js/pd/PD141107.js"></script>