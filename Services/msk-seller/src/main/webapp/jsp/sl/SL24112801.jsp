<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<link rel="stylesheet" href="${ctx}/static/js/treegrid/css/jquery.treegrid.css">
<script type="text/javascript" src="${ctx}/static/js/treegrid/js/jquery.treegrid.js"></script>
        <div class="group-accordion" collapsible="false" active="false" id="sl24112801accordion" style="min-height:300px">
           <h3 style="position: relative">
               <label>产品总控目录在线管理表</label>
           </h3>
        <div>
            <p>
                原料描述：
            </p>
            <p>
                原料学名：${pdClassestreeMat.scientificName}，俗名：${pdClassestreeMat.localName}，标准市场销售名：${pdClassestreeMat.salesName}
            </p>
            <p>
                原料原产地：${pdClassestreeMat.placeOrigin}，现产地：${pdClassestreeMat.placeCurrent}
            </p>
            <p>
                原料种源：${pdClassestreeMat.source}，雏类：${pdClassestreeMat.childType}，饲养方式：${pdClassestreeMat.feedType}
            </p>
            <p>
                饲养周期：${pdClassestreeMat.feedPeriod}
            </p>
            <table class="tree dataTable no-footer" >
                <tr style="background:#DBDBDB">
                    <td name=""  align="center" rowspan="3">序号</td>
                    <td width="40px" align="center" colspan="3">品种名</td>
                    <td align="center" colspan="5">注册线产品</td>
                    <td align="center" colspan="2">正式上线产品</td>
                    <td align="center" rowspan="3" width="40px">禁止准入产品</td>
                    <td align="center" rowspan="3" width="40px">产品等级</td>
                    <td align="center" rowspan="3" width="40px">产品属性码</td>
                    <td align="center" rowspan="3" width="40px">单箱净重(kg)</td>
                    <td align="center" rowspan="3" width="40px">包装规格</td>
                    <td align="center" rowspan="3" width="40px">产品包装码</td>
                    <td align="center" rowspan="3" width="40px">产品销售对象</td>
                    <td align="center" rowspan="3" width="40px">产品加工方向</td>
                    <td align="center" rowspan="3" width="30px">备注(产地)</td>
                </tr>
                <tr style="background:#DBDBDB">
                    <td width="15px" align="center" rowspan="2">标准市场销售名</td>
                    <td align="center" width="10px" rowspan="2">学名</td>
                    <td align="center" width="15px" rowspan="2">俗名</td>
                    <td align="center" colspan="2">市场需求审核注册</td>
                    <td align="center" colspan="3">卖家供应备案注册</td>
                    <td align="center" width="20px" rowspan="2">OEM上线产品</td>
                    <td align="center" width="20px" rowspan="2">卖家上线产品</td>
                </tr>
                <tr style="background:#DBDBDB">
                    <td align="center">标准目录</td>
                    <td align="center">在线处理</td>
                    <td align="center">标准目录</td>
                    <td align="center">已注册</td>
                    <td align="center">在线处理</td>
                </tr>
                <c:forEach items="${listMat}" var="li" varStatus="i">
                <tr>
                    <td>${i.index+1}</td>
                    <td>${li.levelName}</td>
                    <td>${li.scientificName}</td>
                    <td>${li.localName}</td>
                    <td id="market" valign="top">
                        <div class="marketRegisterClass">
                        <a class="marketRegister${li.classestreeCode}" value="${li.classestreeCode}" name="marketRegisterName" href="javascript:void(0);" col="10"><img src="${ctx}/static/images/action/details_open.png" id="marketRegisterImg${li.classestreeCode}"/></a>
                        <div style="display:none;margin-left:20px;margin-right:0px;width:200px" id="marketRegisterDiv${li.classestreeCode}">
                            <table class="tree dataTable no-footer" style="min-width:150px" width="150px" showAddBtn="true">
                                <tr style="background:#DBDBDB">
                                    <td align="center">已注册</td>
                                </tr>
                                <c:forEach items="${li.pdTcMarketList}" var="listMatData">
                                        <c:if test="${listMatData.featureFlg eq 1}">
                                            <c:if test="${not empty listMatData.featureName}">
                                                <tr>
                                                <td align="center">${listMatData.featureName}</td>
                                                </tr>
                                            </c:if>
                                        </c:if>
                                </c:forEach>
                            </table>
                        </div>
                            </div>
                        </td>
                    <td valign="top">
                        <div class="noMarketRegisterClass">
                            <a class="noMarketRegister${li.classestreeCode}" value="${li.classestreeCode}" name="noMarketRegisterName" href="javascript:void(0);" col="10"><img src="${ctx}/static/images/action/details_open.png" id="noMarketRegisterImg${li.classestreeCode}"/></a>
                            <div style="display:none;margin-left:20px;margin-right:0px;width:200px" id="noMarketRegisterDiv${li.classestreeCode}">
                                <table class="tree dataTable no-footer" style="min-width:150px" width="150px" showAddBtn="true">
                                    <tr style="background:#DBDBDB">
                                        <td align="center">在线处理</td>
                                    </tr>
                                    <c:forEach items="${li.pdTcMarketList}" var="listMatData">
                                            <c:if test="${listMatData.featureFlg eq 0}">
                                                    <tr>
                                                    <td align="center">${listMatData.featureName}</td>
                                                    </tr>
                                            </c:if>
                                    </c:forEach>
                                </table>
                            </div>
                        </div>
                    </td>
                    <td class="contentClass" valign="top">
                        <a class="content${li.classestreeCode}" name="contentName" href="javascript:void(0);" col="10"><img src="${ctx}/static/images/action/details_open.png" id="contentImg${li.classestreeCode}"/></a>
                        <div style="display:none;margin-left:20px;margin-right:0px;width:300px" id="contentDiv${li.classestreeCode}" >
                            <table class="tree dataTable no-footer" style="min-width:250px" width="250px" showAddBtn="true">
                                <tr style="background:#DBDBDB">
                                    <td align="center">标准目录</td>
                                    <td align="center">产品销售对象</td>
                                    <td align="center">产品加工方向</td>
                                </tr>
                                <c:forEach items="${li.pdTcProviderContents}" var="content">
                                        <tr>
                                            <td align="center">${content.featureName}</td>
                                            <td align="center">${content.salesTarget}</td>
                                            <td align="center">${content.machiningWay}</td>
                                        </tr>
                                </c:forEach>
                            </table>
                        </div>
                    </td>
                <td class="sellerClass" valign="top">
                    <a class="seller${li.classestreeCode}" name="sellerName" href="javascript:void(0);" col="10"><img src="${ctx}/static/images/action/details_open.png" id="sellerImg${li.classestreeCode}"/></a>
                    <div style="display:none;margin-left:20px;margin-right:0px;width:200px" id="sellerDiv${li.classestreeCode}" >
                        <table class="tree dataTable no-footer" style="min-width:150px" width="150px" showAddBtn="true">
                            <tr style="background:#DBDBDB">
                                <td align="center">已注册</td>
                            </tr>
                            <c:forEach items="${li.pdTcProvider}" var="pdTcProvider">
                                    <c:if test="${pdTcProvider.featureFlg eq 1}">
                                        <tr>
                                        <td align="center">${pdTcProvider.featureName}</td>
                                        </tr>
                                    </c:if>
                            </c:forEach>
                        </table>
                    </div>
                </td>
                <td class="sellerLineClass" valign="top">
                    <a class="sellerLine${li.classestreeCode}" name="sellerLineName" href="javascript:void(0);" col="10"><img src="${ctx}/static/images/action/details_open.png" id="sellerLineImg${li.classestreeCode}"/></a>
                    <div style="display:none;margin-left:20px;margin-right:0px;width:200px" id="sellerLineDiv${li.classestreeCode}">
                        <table class="tree dataTable no-footer" style="min-width:150px" width="150px" showAddBtn="true">
                            <tr style="background:#DBDBDB">
                                <td align="center">在线处理</td>
                            </tr>
                            <c:forEach items="${li.pdTcProvider}" var="pdTcProvider">
                                    <c:if test="${pdTcProvider.featureFlg eq 0}">
                                    <tr>
                                    <td align="center">${pdTcProvider.featureName}</td>
                                    </tr>
                                    </c:if>
                            </c:forEach>
                        </table>
                        </div>
                    </td>
                    <td valign="top">
                        <a class="oneLineOem${li.classestreeCode}" name="oneLineOemName" href="javascript:void(0);" col="10"><img src="${ctx}/static/images/action/details_open.png" id="oneLineOemImg${li.classestreeCode}"/></a>
                        <div style="display:none;margin-left:20px;margin-right:0px;width:200px" id="oneLineOemDiv${li.classestreeCode}">
                            <table class="tree dataTable no-footer" style="min-width:150px" width="150px" showAddBtn="true">
                                <tr style="background:#DBDBDB">
                                    <td align="center">OEM上线产品</td>
                                </tr>
                                <c:forEach items="${li.pdTcOnlineOem}" var="pdTcOnlineOem">
                                    <tr>
                                            <td align="center">${pdTcOnlineOem.featureName}</td>
                                    </tr>
                                </c:forEach>
                                </tr>
                            </table>
                        </div>
                    </td>
                    <td valign="top">
                        <a class="oneLine${li.classestreeCode}" name="oneLineName" href="javascript:void(0);" col="10"><img src="${ctx}/static/images/action/details_open.png" id="oneLineImg${li.classestreeCode}"/></a>
                        <div style="display:none;margin-left:20px;margin-right:0px;width:200px" id="oneLineDiv${li.classestreeCode}">
                            <table class="tree dataTable no-footer" style="min-width:150px" width="150px" showAddBtn="true">
                                <tr style="background:#DBDBDB">
                                    <td align="center">卖家上线产品</td>
                                </tr>
                                <c:forEach items="${li.pdTcOnline}" var="pdTcOnline">
                                    <tr>
                                        <td align="center">${pdTcOnline.featureName}</td>
                                    </tr>
                                </c:forEach>
                                </tr>
                            </table>
                        </div>
                    </td>
                    <td valign="top">
                        <div class="tcForbidClass">
                            <a class="tcForbid${li.classestreeCode}" value="${li.classestreeCode}" name="tcForbidName" href="javascript:void(0);" col="10"><img src="${ctx}/static/images/action/details_open.png" id="tcForbidImg${li.classestreeCode}"/></a>
                            <div style="display:none;margin-left:20px;margin-right:0px;width:200px" id="tcForbidDiv${li.classestreeCode}">
                                <table class="tree dataTable no-footer" style="min-width:150px" width="150px" showAddBtn="true">
                                    <tr style="background:#DBDBDB">
                                        <td align="center">禁止准入产品</td>
                                    </tr>
                                    <c:forEach items="${li.pdTcForbidParams}" var="pdTcForbidParams">
                                            <tr>
                                                <td align="center">${pdTcForbidParams.featureName}</td>
                                            </tr>
                                    </c:forEach>
                                </table>
                            </div>
                        </div>
                    </td>
                    <td>A2</td>
                    <td valign="top" colspan="4" style="min-width:340px" width="320px">
                        <a class="product${li.classestreeCode}" name="productName" href="javascript:void(0);" col="10"><img src="${ctx}/static/images/action/details_open.png" id="productImg${li.classestreeCode}"/></a>
                        <div style="display:none;margin-left:20px;margin-right:0px;width:200px" id="productDiv${li.classestreeCode}">
                            <table class="tree dataTable no-footer classTree" style="min-width:150px" width="150px" showAddBtn="true">
                                <tr style="background:#DBDBDB">
                                    <td align="center">产品属性码</td>
                                    <td align="center">单箱净重(kg)</td>
                                    <td align="center">包装规格</td>
                                    <td align="center">产品包装码</td>
                                </tr>
                                <c:forEach items="${li.pd141124Level4Beans}" var="pdFeature">
                                    <tr >
                                        <td name="attributeCode">${pdFeature.attributeCode}</td>
                                        <td value="${pdFeature.featureName}">${pdFeature.featureName}</td>
                                        <td name="norms" value="${pdFeature.normsCode}">${pdFeature.normsOut}</td>
                                        <td>${pdFeature.pdNormCode}</td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </div>
                    </td>
                    <td>
                    ${li.salesTargetTwo}
                    </td>
                    <td>
                    ${li.machiningWayTwo}
                    </td>
                    <td>${pdClassestreeMat.placeCurrent}</td>
                </tr>
                </c:forEach>
            </table></div>
    </div>
<div class="group-accordion" collapsible="false" active="false" valign="top">
    <h3 style="position: relative">
        <label>省级卖家池卖家目录</label>
    </h3>
    <div>
        <table class="tree dataTable no-footer" style="width: 100%">
            <tr style="background:#DBDBDB">
                <td align="center">序号</td>
                <td align="center">省(市)</td>
                <td align="center">地区/国籍</td>
                <td align="center">卖家(生产商)名称</td>
                <td align="center">卖家(生产商)地址</td>
                <td align="center">品牌</td>
                <td align="center">卖家编码</td>
            </tr>
            <c:forEach items="${sl24112801Beans}" var="list" varStatus="j">
                <tr>
                    <td>${j.index+1}</td>
                    <td>${list.provinceName}</td>
                    <td>${list.cityName}</td>
                    <td>${list.epName}</td>
                    <td>${list.licAddr}</td>
                    <td>${list.brandName}</td>
                    <td>${list.slCodeDis}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <%--<div class="page-content list-page">
        <form action="${ctx}/SL24112801/search" method="post" id="SL24112801Id">
            <table id="pd141154_grid" showAddBtn="true">
                <thead>
                <tr>
                    <th coltype="text" width="7%" name="applyDateTime"  filter="false">序号</th>
                    <th coltype="text" width="7%" name="provinceName" filter="false">省(市)</th>
                    <th coltype="text" width="7%" name="cityName" filter="false">地区/国籍</th>
                    <th coltype="text" width="7%" name="epName" filter="false">卖家(生产商)名称</th>
                    <th coltype="text" width="7%" name="licAddr" filter="false">卖家(生产商)地址</th>
                    <th coltype="text" width="7%" name="brandName" filter="false">品牌</th>
                    <th coltype="text" width="7%" name="slCodeDis" filter="false">卖家编码</th>
                </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
        </form>
    </div>--%>
</div>
<script src="${ctx}/static/sl/js/SL24112801.js"></script>
