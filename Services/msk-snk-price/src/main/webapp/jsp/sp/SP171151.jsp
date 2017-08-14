<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<navigation:header title="神农客供应商价格申报详细" backTitleArray="" backUrlArray=""></navigation:header>
<div class="page-content list-page">
<form action="${ctx}/SP171151/save" id="SP171151Form" method="post">
        <div class="group-accordion" collapsible="true" active="true">
            <table WIDTH="100%">
                <tr style="width:100%">
                    <td width="20%" align="right">${sp171151Param.lacsName}</td>
                    <td width="8%" style="float:left" align="right">供应商 ：</td>
                    <td style="float:left" width="20%">${sp171151Param.slName}</td>
                    <td width="20%"></td>
                </tr>

                <tr style="width:100%">
                    <td width="20%" align="right">第${sp171151Param.dayAmount}期  ${sp171151Param.startDate}  ~   ${sp171151Param.endDate}</td>
                    <td width="13%" style="float:left" align="right">填报时间 ：</td>
                    <td style="float:left" width="20%">${sp171151Param.fillDate}</td>
                </tr>

                <tr style="width:100%">
                    <td width="20%" align="right">产品编码 ：</td>
                    <td align="left" width="16%">${sp171151Param.pdCode}</td>
                    <td width="8%" style="float:left" align="right">产品一级分类 ：</td>
                    <td style="float:left" width="40%">${sp171151Param.pdClasses}</td>
                    <td width="8%" style="float:left" align="right">产品二级分类 ：</td>
                    <td style="float:left" width="20%">${sp171151Param.pdMachining}</td>
                </tr>

                <tr style="width:100%">
                    <td width="20%" align="right">品名：</td>
                    <td align="left" width="16%">${sp171151Param.pdName}</td>
                    <td width="8%" style="float:left" align="right">配料 ：</td>
                    <td style="float:left" width="40%">${sp171151Param.machining}</td>
                    <td width="8%" style="float:left" align="right">产品等级 ：</td>
                    <td style="float:left" width="20%">${sp171151Param.pdLevel}</td>
                </tr>
            </table>
        </div>

    <div>
        <table class="dataTable no-footer" id="" style="width: 100%">
            <thead>
            <tr style="background:#DBDBDB">
                <th align="center" coltype="checkbox" width="5%" name="checkFlag" rowspan="2">参与的分销通道</th>
                <th align="center" coltype="text" width="10%" name="" rowspan="2">等级</th>
                <th align="center" coltype="text" width="18%" name="" colspan="2" rowspan="1">等级标准(箱)</th>
                <th align="center" coltype="text" width="8%" name="" rowspan="2">通道等级平衡系数</th>
                <th align="center" coltype="text" width="8%" name="" rowspan="2">下限价</br>(元/KG)</th>
                <th align="center" coltype="text" width="8%" name="" rowspan="2">价格</br>(元/KG)</th>
                <th align="center" coltype="text" width="8%" name="" rowspan="2">上限价</br>(元/KG)</th>
                <th align="center" coltype="text" width="8%" name="" rowspan="2">上期价盘</br>(元/KG)</th>
            </tr>

            </thead>
            <tbody id="SP171151Tbody">
            <c:choose>
                <c:when test="${SP171151BeanList eq null}">
                    <tr>
                        <td align="center" colspan="12">
                            对不起，没有查询到数据！
                        </td>
                    </tr>
                </c:when>
                <c:otherwise>
                        <tr>
                    <c:forEach items="${SP171151BeanList}" var="item" varStatus="status">
                        <td align="center"  <c:choose>
                            <c:when test="${status.index eq 0}">rowspan="1"</c:when>
                            <c:otherwise>rowspan="2"</c:otherwise></c:choose>>
                            <input type="hidden" name="SP171151Beans[${status.index}].wayCode" value="${item.wayCode}">
                            <input type="checkbox" id="Valid${status.index}"
                            <c:if test="${item.isValid == '1'}">
                                   checked="checked"
                            </c:if> style="width:100%" onchange="isValidChange(${status.index})">
                            <input type="hidden" name="SP171151Beans[${status.index}].isValid" value="${item.isValid}" id="isValid${status.index}">
                        </td>
                            <td align="center" id="wayGradeCodeTd${status.index}"
                                style="
                                <c:choose>
                                <c:when test="${item.isValid eq '1'}">
                                        background-color:#AA7428
                                </c:when>
                                <c:otherwise>
                                        background-color:#DBDBDB
                                </c:otherwise>
                                        </c:choose>">
                                <input type="hidden" value="${item.wayGradeCode}" name="SP171151Beans[${status.index}].wayGradeCode" id="wayGradeCode${status.index}"/>
                                <input type="text" id="wayGradeName${status.index}" name="SP171151Beans[${status.index}].wayGradeName"
                                       style="width: 100%;border-style: solid; border-width: 0;
                                       <c:choose>
                                       <c:when test="${item.isValid eq '1'}">
                                               ;background-color:#AA7428
                                       </c:when>
                                       <c:otherwise>
                                               ;background-color:#DBDBDB
                                       </c:otherwise>
                                               </c:choose>" value="${item.wayGradeName}" readonly="readonly">
                            </td>
                            <td>
                                <input id="wayGradeStart${status.index}" value="${item.wayGradeStart}" style="width:100%" name="SP171151Beans[${status.index}].levelStandMin"  maxlength="19">
                            </td>
                            <td style="background-color: #DBDBDB">
                                <input type="text" id="wayGradeEnd${status.index}" name="SP171151Beans[${status.index}].wayGradeEnd" style="border-style: solid; border-width: 0;width: 100%;background-color: #DBDBDB" value="${item.wayGradeEnd}" readonly="readonly">
                            </td>
                            <td>
                                <input type="text" id="wayGradePriceRatio${status.index}" style="width: 100%" name="SP171151Beans[${status.index}].wayGradePriceRatio" value="${item.wayGradePriceRatio}"  maxlength="8">
                            </td>

                            <td id="wayGradePriceTd">
                                <input type="text" id="wayGradePrice${status.index}" name="SP171151Beans[${status.index}].wayGradePrice" value="${item.wayGradePrice}" onchange="wayGradePriceChange(${status.index})"
                                       style="width: 100%
                                       <c:if test="${item.wayGradeCode eq '5'}">
                                               ;background-color:#AA758B
                                               </c:if>" maxlength="7">
                            </td>
                            <td style="background-color: #DBDBDB">
                                <c:if test="${empty item.lastWayGradePrice}">
                                    <input type="text" id="lastWayGradePrice${status.index}" name="SP171151Beans[${status.index}].lastWayGradePrice" style="border-style: solid; border-width: 0;width: 100%;background-color: #DBDBDB" value="0.00" readonly="readonly">
                                </c:if>
                                <c:if test="${not empty item.lastWayGradePrice}">
                                    <input type="text" id="lastWayGradePrice${status.index}" name="SP171151Beans[${status.index}].lastWayGradePrice" style="border-style: solid; border-width: 0;width: 100%;background-color: #DBDBDB" value="${item.wayGradePrice}" readonly="readonly">
                                </c:if>
                            </td>
                    </c:forEach>
                        </tr>
                </c:otherwise>
            </c:choose>
            </tbody>
        </table>
    </div>
    </form>
</div>
<script src="${ctx}/static/sp/js/SP171151.js"></script>
