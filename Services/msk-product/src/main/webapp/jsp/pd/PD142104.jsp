<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
    Title:卖家批次产品目录报表
    author:gyh
    createDate:2016-4-20
    updateDate:2016-4-20
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<c:if test="${yesOrNo != 'yes' }">
    <navigation:header title="卖家批次产品目录报表" backTitleArray="" backUrlArray=""></navigation:header>
</c:if>
<div class="page-content detail-page">
    <form action="${ctx}/PD142104/search" method="post" id="PD142104Id">
        <div class="group-accordion" collapsible="false" active="false">
            <h3 style="position: relative">
                <label>查询条件</label>
            </h3>

            <div>
                一级分类:
                <c:forEach items="${classesList}" var="classes">
                    <a href="JavaScript:void(0)" style="padding-right: 10px;" class="oneClassCode" labelValue="${classes.levelName}" value="${classes.classestreeCode}">${classes.levelName}</a>
                </c:forEach>
            </div>
        </div>
        <div class="group-accordion" collapsible="false" active="false">
            <h3 style="position: relative">
                <label id="labelVal">卖家批次产品目录报表——鸡产品</label>
            </h3>

            <div width="100%">
                <table id="PD142104_grid" showAddBtn="true" WIDTH="100%">
                    <thead class="filterHeader">
                    <tr role="row">
                        <td></td>
                        <td class="text sorting_disabled">
                            <%--<input type="text" class="cellFilter cellFilterText" id="machiningInfo" name="filterMap['machiningName']"/>--%>
                            <select class="cellFilter cellFilterText" id="machiningInfo" name="filterMap['machiningName']">
                                <option value=''>请选择</option>
                                <c:forEach items="${machiningInfo}" var="machining">
                                    <option value="${machining.machiningCode}">${machining.machiningName}</option>
                                </c:forEach>
                            </select>
                            <input type="hidden" name="filterMap[classesCode]" value="01" id="classesCode">
                        </td>
                        <td><input type="text" class="cellFilter cellFilterText" name="filterMap['scientificName']"/>
                        </td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td><input type="text" disabled="disabled" style="-webkit-box-shadow: 0 0 0px 1000px #D1D1D1 inset;" class="cellFilter cellFilterText" name="filterMap['gradeName']"/>
                        </td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td>
                            <select class="cellFilter cellFilterText" name="filterMap['slMainClass']">
                                <option value=""></option>
                                <option value="0">生产型</option>
                                <option value="1">自产型</option>
                                <option value="2">代理型</option>
                                <option value="3">OEM型</option>
                            </select>
                        </td>
                        <td><input type="text" class="cellFilter cellFilterText" name="filterMap['slLicAddr']"/>
                        </td>
                        <td><input type="text" class="cellFilter cellFilterText" name="filterMap['slName']"/>
                        </td>
                        <td><input type="text" class="cellFilter cellFilterText" name="filterMap['sllfAddr']"/>
                        </td>
                        <td><input type="text" class="cellFilter cellFilterText" name="filterMap['sllfName']"/>
                        </td>
                        <td>
                            <select class="cellFilter cellFilterText" name="filterMap['brandClass']">
                                <option value=""></option>
                                <option value="0">卖家独立品牌</option>
                                <option value="1">神农先生联合品牌</option>
                                <option value="2">神农客联合品牌</option>
                                <option value="3">神农人家联合品牌</option>
                            </select>
                        </td>
                        <td><input type="text" class="cellFilter cellFilterText" name="filterMap['brandName']"/>
                        </td>
                        <td><input type="text" class="cellFilter cellFilterText" name="filterMap['lotYear']"/>
                        </td>
                        <td><input type="text" class="cellFilter cellFilterText" name="filterMap['lotMonth']"/>
                        </td>
                        <td><input type="text" class="cellFilter cellFilterText" name="filterMap['lotDate']"/>
                        </td>
                        <td><msk:button buttonValue="查询" buttonId="PD142104.SEARCH" buttonType="button"/></td>
                    </tr>
                    </thead>
                    <thead>
                    <tr>
                        <th></th>
                        <th></th>
                        <th colspan="3">品种名</th>
                        <th></th>
                        <th></th>
                        <th></th>
                        <th></th>
                        <th></th>
                        <th colspan="3">卖家信息</th>
                        <th colspan="2">生产商信息</th>
                        <th colspan="2">品牌信息</th>
                        <th colspan="3">批次信息</th>
                        <th></th>
                    </tr>
                    <tr>
                        <th coltype="sno" rowspan="2">序号</th>
                        <th coltype="text" name="machiningName" rowspan="2">产品二级分类</th>
                        <th coltype="text" name="breedName">标准市场销售名</th>
                        <th coltype="text" name="scientificName">学名</th>
                        <th coltype="text" name="localName">俗名</th>
                        <th coltype="text" name="featureName" rowspan="2">产品特征</th>
                        <th coltype="text" name="gradeName" rowspan="2">产品等级</th>
                        <th coltype="text" name="attributeCode" rowspan="2">产品属性码</th>
                        <th coltype="text" name="weightName" rowspan="2">单箱净重</th>
                        <th coltype="text" name="normsOut" rowspan="2">包装规格</th>
                        <th coltype="text" name="slMainClassName">类型</th>
                        <th coltype="text" name="slLicAddr">地区</th>
                        <th coltype="text" name="slName">名称</th>
                        <th coltype="text" name="sllfAddr">地区</th>
                        <th coltype="text" name="sllfName">名称</th>
                        <th coltype="text" name="brandClassName">类型</th>
                        <th coltype="text" name="brandName">品牌</th>
                        <th coltype="text" name="lotYear">年</th>
                        <th coltype="text" name="lotMonth">月</th>
                        <th coltype="text" name="lotDate">半旬</th>
                        <th coltype="text" name="lotPdCode" rowspan="2">卖家批次产品阅读码</th>
                    </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
            </div>
        </div>
    </form>
</div>
<script src="${ctx}/static/js/pd/PD142104.js"></script>
