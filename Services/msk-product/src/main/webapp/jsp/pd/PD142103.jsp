<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
    Title:产品总控目录在线管理表
    author:pxg
    createDate:2016-02-22
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<c:if test="${yesOrNo != 'yes' }">
    <navigation:header title="卖家产品目录报表" backTitleArray="" backUrlArray="" ></navigation:header>
</c:if>
<div class="page-content list-page" style="height:100%">
    <form action="${ctx}/PD142103/search" id="PD142103SearchData" method="post">
        <input type="hidden" name="filterMap[classestreeCode]" value="01" id="classCode"/>
    <div class="group-accordion" collapsible="false" active="false" id="accordionPD142103">
        <h3 style="position: relative">
            <label>查询条件</label>
        </h3>
       <div>
           <table>
                   <tr>
                       <td>
                           一级分类:
                           <c:forEach items="${listOne}" var="classes">
                               <a href="#" style="padding-right: 10px;" class="oneClassCode" value="${classes.classestreeCode}">${classes.levelName}</a>
                           </c:forEach>
                       </td>
                   </tr>
           </table>
           </div>
    </div>
    <div id="PD14210301Id" style="height: auto">
        <div class="group-accordion" collapsible="false" active="false">
            <h3 style="position: relative">
                <label>卖家产品目录报表</label>
            </h3>
            <div width="100%">
                <table id="PD142103_grid" showAddBtn="true" WIDTH="100%">
                    <thead class="filterHeader">
                    <tr role="row">
                        <td></td>
                        <td>
                            <select id="machiningName" name="filterMap['machiningName']">

                            </select>
                           <%-- <input type="text" class="cellFilter cellFilterText" name="filterMap['machiningName']"/>--%>
                        </td>
                        <td><input type="text" class="cellFilter cellFilterText" name="filterMap['breedName']"/>
                        </td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td>
                            <%--<select name="filterMap['gradeName']">
                                <option value="">请选择</option>
                                <option value="1">A1</option>
                                <option value="2">A2</option>
                                <option value="3">A3</option>
                            </select>--%>
                            <input type="text" class="cellFilter cellFilterText" name="filterMap['gradeName']" disabled="disabled" style="background-color: #DBDBDB"/>
                        </td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td>
                            <select name="filterMap['slMainClass']">
                                <option value="">请选择</option>
                                <option value="0">生产商</option>
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
                            <select name="filterMap['brandClass']">
                                <option value="">请选择</option>
                                <option value="0">卖家独立品牌</option>
                                <option value="1">神农先生联合</option>
                                <option value="2">神农客联合</option>
                                <option value="3">神农人家联合</option>
                            </select>
                        </td>
                        <td><input type="text" class="cellFilter cellFilterText" name="filterMap['brandName']"/>
                        </td>
                        <td><msk:button buttonValue="查询" buttonId="PD142103.SEARCH" buttonType="button"/></td>
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
                        <th coltype="text" name="classestreeCode" rowspan="2">卖家产品阅读码</th>
                    </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    </form>
    </div>
<script src="${ctx}/static/js/pd/PD142103.js"></script>
