<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@include file="/comm/taglib.jsp" %>
<navigation:header title="供应商需求发布详细" backTitleArray="供应商需求发布" backUrlArray="${ctx}/SP171101/init"></navigation:header>
<div class="page-content list-page">
    <form:form  id="SP171102Form" method="post"  action="${ctx}/SP171102/save" >
        <input  type="hidden" id="publishId" name="publishId" value="${sp171102Bean.publishId}"/>
        <input  type="hidden" id="lgcsCode" name="lgcsCode" value="${sp171102Bean.lgcsCode}"/>
        <input  type="hidden" id="scientificName" name="scientificName" value="${sp171102Bean.scientificName}"/>
        <input  type="hidden" id="localName" name="localName" value="${sp171102Bean.localName}"/>
        <input  type="hidden" id="salesName" name="salesName" value="${sp171102Bean.salesName}"/>
        <input  type="hidden" id="classesCode" name="classesCode" value="${sp171102Bean.classesCode}"/>
        <input  type="hidden" id="machiningCode" name="machiningCode" value="${sp171102Bean.machiningCode}"/>
        <input  type="hidden" id="breedCode" name="breedCode" value="${sp171102Bean.breedCode}"/>
        <input  type="hidden" id="featureCode" name="featureCode" value="${sp171102Bean.featureCode}"/>
        <input  type="hidden" id="weightCode" name="weightCode" value="${sp171102Bean.weightCode}"/>
        <input  type="hidden" id="publishYm" name="publishYm" value="${sp171102Bean.publishYm}"/>
        <input  type="hidden" id="canSetFlg" name="canSetFlg" value="${sp171102Bean.canSetFlg}"/>
        <input  type="hidden" id="loginId" name="loginId"  value="${sp171102Bean.loginId}"/>
        <input  type="hidden" id="demandStartDate" name="demandStartDate"  value="${sp171102Bean.demandStartDate}"/>
        <input  type="hidden" id="demandEndDate" name="demandEndDate"  value="${sp171102Bean.demandEndDate}"/>
        <input type="hidden" id="greadSize" name="filterMap[greadSize]">
        <div class="group-accordion" collapsible="" active="true">
            <h3>
                <label>供应商需求发布详细</label>
            </h3>
            <div>
                <table width="90%">
                    <tr>
                        <td  width="33%" align="left">
                            <input type="hidden" name="lgcsName" id="lgcsName" value="${sp171102Bean.lgcsName}"/>${sp171102Bean.lgcsName}地区
                        </td>
                        <td  width="33%" style="text-align: left"></td>
                        <td  width="33%"  style="text-align: left"></td>
                    </tr>
                    <tr>
                        <td style="text-align: left">
                            <input type="hidden" name="demandYearMonthShow" id="demandYearMonthShow" value="${sp171102Bean.demandYearMonthShow}"/>${sp171102Bean.demandYearMonthShow}
                        </td>
                        <td style="text-align: left">填报时间:
                        ${sp171102Bean.demandStartDate}-${sp171102Bean.demandEndDate}
                        </td>
                        <td></td>
                    </tr>
                    <tr>
                        <td>&nbsp;&nbsp;</td>
                    </tr>
                    <tr>
                        <td style="text-align: left">产品编码:
                            <input type="hidden" name="pdTypeCode" id="pdTypeCode" value="${sp171102Bean.pdTypeCode}"/>${sp171102Bean.pdTypeCode}
                        </td>

                        <td style="text-align: left">产品一级分类:
                            <input type="hidden" name="classes" id="classes" value="${sp171102Bean.classes}"/>${sp171102Bean.classes}
                        </td>
                        <td style="text-align: left">产品二级分类:
                            <input type="hidden" name="machining" id="machining" value="${sp171102Bean.machining}"/>${sp171102Bean.machining}
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: left">品种:
                            <input type="hidden" name="breed" id="breed" value="${sp171102Bean.breed}"/>${sp171102Bean.breed}
                        </td>
                        <td style="text-align: left">特征:
                            <input type="hidden" name="feature" id="feature" value="${sp171102Bean.feature}"/>${sp171102Bean.feature}
                        </td>
                        <td style="text-align: left">净重(kg/箱):
                            <input type="hidden" name="weight" id="weight" value="${sp171102Bean.weight}"/>${sp171102Bean.weight}
                        </td>

                    </tr>
                    <tr>
                        <td>&nbsp;&nbsp;</td>
                    </tr>
                    <tr>
                        <td style="text-align: left">需求总量:
                            <c:choose>
                                <c:when test="${sp171102Bean.canSetFlg eq 'Y'}">
                                    <input type="text" name="publishTotalNum" id="publishTotalNum" value="${sp171102Bean.publishTotalNum}" onblur="numChangeTotalNum()"/>&nbsp;吨
                                </c:when>
                                <c:when test="${empty sp171102Bean.publishTotalNum}">
                                    <input type="hidden" id="publishTotalNum" value="${sp171102Bean.publishTotalNum}" onblur="numChangeTotalNum()"/>
                                </c:when>
                                <c:otherwise>
                                    <input type="hidden"  id="publishTotalNum" value="${sp171102Bean.publishTotalNum}"/>${sp171102Bean.publishTotalNum}&nbsp;吨
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td>&nbsp;&nbsp;</td>
                    </tr>
                    <tr id="greadList">
                        <td style="text-align: left">A1需求比例:
                            <c:choose>
                                <c:when test="${sp171102Bean.canSetFlg eq 'Y'}">
                                    <input type="text" id="gradeRatio1" name="gradeRatio1" value="${sp171102Bean.gradeRatio1}" onblur="numChange1()"/>
                                </c:when>
                                <c:otherwise>
                                    <input type="hidden"  id="gradeRatio1" value="${sp171102Bean.gradeRatio1}"/> ${sp171102Bean.gradeRatio1}
                                </c:otherwise>
                            </c:choose>
                        </td>

                        <td style="text-align: left">A2需求比例:

                            <c:choose>
                                <c:when test="${sp171102Bean.canSetFlg eq 'Y'}">
                                    <input type="text" id="gradeRatio2" name="gradeRatio2" value="${sp171102Bean.gradeRatio2}" onblur="numChange2()"/>
                                </c:when>
                                <c:otherwise>
                                    <input type="hidden"  id="gradeRatio2" value="${sp171102Bean.gradeRatio2}"/>${sp171102Bean.gradeRatio2}
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td style="text-align: left">A3需求比例:

                            <c:choose>
                                <c:when test="${sp171102Bean.canSetFlg eq 'Y'}">
                                    <input type="text" id="gradeRatio3" name="gradeRatio3" value="${sp171102Bean.gradeRatio3}" onblur="numChange3()"/>
                                </c:when>
                                <c:otherwise>
                                    <input type="hidden"  id="gradeRatio3" value="${sp171102Bean.gradeRatio3}"/>${sp171102Bean.gradeRatio3}
                                </c:otherwise>
                            </c:choose>
                        </td>

                    </tr>
                </table>
                <br>
                <br>
                <table class="tree dataTable no-footer" style="min-width: 800px" align="center">
                    <thead>
                    <tr height="30px">
                        <td coltype="text" id="" name="">等级<input type="text" name="" style="border-style: solid; border-width: 0;width: 0%;background-color: inherit;padding-top:0;padding-bottom:1.5px" value="" readonly="readonly"/></td>
                        <td coltype="text"  name="">分配平衡系数<input type="text"  name="" style="border-style: solid; border-width: 0;width: 0%;background-color: inherit;padding-top:0;padding-bottom:1.5px" value="" readonly="readonly"/></td>
                        <td coltype="text" name="">A1品（<input type="text" id="gradeRatio11" name="" style="border-style: solid; border-width: 0;width: 15%;background-color: inherit;padding-top:0;padding-bottom:1.5px" value="" readonly="readonly"/>%）</td>
                        <td coltype="text" name="">A2品（<input type="text" id="gradeRatio22" name="" style="border-style: solid; border-width: 0;width: 15%;background-color: inherit;padding-top:0;padding-bottom:1.5px" value="" readonly="readonly"/>%）</td>
                        <td coltype="text" name="">A3品（<input type="text" id="gradeRatio33" name="" style="border-style: solid; border-width: 0;width: 15%;background-color: inherit;padding-top:0;padding-bottom:1.5px" value="" readonly="readonly"/>%）</td>
                        <td coltype="text" name="">总量合计<input type="text"  name="" style="border-style: solid; border-width: 0;width: 0%;background-color: inherit;padding-top:0;padding-bottom:1.5px" value="" readonly="readonly"/></td>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td  style="text-align:center;background:#DBDBDB">超级大宗订单</td>
                        <td align="center" rowspan="3"><input type="text" id="ratioTypeDetailVal3" name="ratioTypeDetailVal3" style="border-style: solid; border-width: 0;width: 90%" value="${sp171102Bean.ratioTypeDetailVal3}" readonly="readonly"></td>
                        <td rowspan="3"><input type="text" id="way1A1"  style="border-style: solid; border-width: 0;width: 90%" value="" readonly="readonly"></td>
                        <td rowspan="3"><input type="text" id="way1A2" style="border-style: solid; border-width: 0;width: 90%" value="" readonly="readonly"></td>
                        <td rowspan="3"><input type="text" id="way1A3" style="border-style: solid; border-width: 0;width: 90%" value="" readonly="readonly"></td>
                        <td rowspan="3"><input type="text" id="way1Total"  style="border-style: solid; border-width: 0;width: 90%" value="" readonly="readonly"></td>
                    </tr>
                    <tr>
                        <td style="text-align:center;background:#DBDBDB">大宗订单1级</td>

                    </tr>
                    <tr>
                        <td  style="text-align:center;background:#DBDBDB">大宗订单2级</td>

                    </tr>
                    <tr>
                        <td  style="text-align:center;background:#DBDBDB">大额订单3级</td>
                        <td align="center" rowspan="3"><input type="text" id="ratioTypeDetailVal2" name="ratioTypeDetailVal2" style="border-style: solid; border-width: 0;width: 90%" value="${sp171102Bean.ratioTypeDetailVal2}" readonly="readonly"></td>
                        <td rowspan="3"><input type="text" id="way2A1"  style="border-style: solid; border-width: 0;width: 90%" value="" readonly="readonly"></td>
                        <td rowspan="3"><input type="text" id="way2A2"  style="border-style: solid; border-width: 0;width: 90%" value="" readonly="readonly"></td>
                        <td rowspan="3"><input type="text" id="way2A3"  style="border-style: solid; border-width: 0;width: 90%" value="" readonly="readonly"></td>
                        <td rowspan="3"><input type="text" id="way2Total"  style="border-style: solid; border-width: 0;width: 90%" value="" readonly="readonly"></td>
                    </tr>
                    <tr>
                        <td style="text-align:center;background:#DBDBDB">大额订单4级</td>
                    </tr>
                    <tr>
                        <td  style="text-align:center;background:#DBDBDB">大额订单5级</td>

                    </tr>
                    <tr>
                        <td  style="text-align:center;background:#DBDBDB">标准订单6级</td>
                        <td align="center" rowspan="4"><input type="text" id="ratioTypeDetailVal1" name="ratioTypeDetailVal1" style="border-style: solid; border-width: 0;width: 90%" value="${sp171102Bean.ratioTypeDetailVal1}" readonly="readonly"></td>
                        <td rowspan="4"><input type="text" id="way3A1"  style="border-style: solid; border-width: 0;width: 90%" value="" readonly="readonly"></td>
                        <td rowspan="4"><input type="text" id="way3A2"  style="border-style: solid; border-width: 0;width: 90%" value="" readonly="readonly"></td>
                        <td rowspan="4"><input type="text" id="way3A3"  style="border-style: solid; border-width: 0;width: 90%" value="" readonly="readonly"></td>
                        <td rowspan="4"><input type="text" id="way3Total"  style="border-style: solid; border-width: 0;width: 90%" value="" readonly="readonly"></td>
                    </tr>
                    <tr>
                        <td style="text-align:center;background:#DBDBDB">标准订单7级</td>

                    </tr>
                    <tr>
                        <td style="text-align:center;background:#DBDBDB">标准订单8级</td>

                    </tr>
                    <tr>
                        <td style="text-align:center;background:#DBDBDB">标准订单9级</td>
                    </tr>
                    <tr>
                        <td colspan="2" style="text-align:center;background:#DBDBDB">通道合计</td>
                        <td><input type="text" id="A1Total"  style="border-style: solid; border-width: 0;width: 90%" value="" readonly="readonly"></td>
                        <td><input type="text" id="A2Total"  style="border-style: solid; border-width: 0;width: 90%" value="" readonly="readonly"></td>
                        <td><input type="text" id="A3Total"  style="border-style: solid; border-width: 0;width: 90%" value="" readonly="readonly"></td>
                        <td><input type="text" id="totalNum"  style="border-style: solid; border-width: 0;width: 90%" value="" readonly="readonly"></td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <c:choose>
            <c:when test="${sp171102Bean.canSetFlg eq 'Y'}">
                <msk:button buttonValue="保存" buttonId="SP171102.SAVE" buttonType="button"/>
                <%-- Modfiy:  一览页面到详情页面打开方式横展开,将返回按钮注释   2016/10/11   BY  zhukai1  Start --%>
                <%-- <msk:button buttonValue="返回" buttonId="SP171102.BACK" buttonType="button"/>--%>
                <%-- Modfiy:  一览页面到详情页面打开方式横展开,将返回按钮注释   2016/10/11   BY  zhukai1  end--%>
            </c:when>
            <%-- Modfiy:  一览页面到详情页面打开方式横展开,将返回按钮注释   2016/10/11   BY  zhukai1  Start --%>
            <%--<c:otherwise>
                <msk:button buttonValue="返回" buttonId="SP171102.BACK" buttonType="button"/>
            </c:otherwise>--%>
            <%-- Modfiy:  一览页面到详情页面打开方式横展开,将返回按钮注释   2016/10/11   BY  zhukai1  end--%>
        </c:choose>
    </form:form>
</div>
<script src="${ctx}/static/sp/js/SP171102.js"></script>
