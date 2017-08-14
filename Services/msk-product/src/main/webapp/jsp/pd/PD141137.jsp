<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%--
    Title:产品总控目录在线管理表
    author:pxg
    createDate:2016-02-22
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>

<link rel="stylesheet" href="${ctx}/static/treegrid/css/jquery.treegrid.css">
<script type="text/javascript" src="${ctx}/static/treegrid/js/jquery.treegrid.js"></script>
<%--Modfiy: Bug #2182 : 详细页面：多个产品的时候，少了滚动条   20160906   BY  杨春艳  Start --%>
<navigation:header title="卖家产品池注册总控详情" backTitleArray="卖家产品池注册总控列表" backUrlArray="${ctx}/PD141136/init" ></navigation:header>
<div class="page-content detail-page" style="height:100%">
<%--Modfiy: Bug #2182 : 详细页面：多个产品的时候，少了滚动条   20160906   BY  杨春艳  End --%>
    <div class="group-accordion" collapsible="false" active="false">
        <h3 style="position: relative">
            <label>卖家信息</label>
        </h3>

        <div>
            <form>
                <table style="width: 100%">
                    <tr>
                        <td style="width: 100%;padding:5px">
                            &emsp;产品分类:&emsp;[一级:&emsp;${bean.classesName}&emsp;二级:&emsp;${bean.machiningName}&emsp;]
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 100%;padding:5px">&emsp;卖家分类:&emsp;<input align="bottom"
                                                           type="checkbox" ${bean.slMainclass eq "自产型卖家"?"checked":""}
                                                           onclick="return false;"/>自产型卖家,<input
                                type="checkbox" ${bean.slMainclass eq "代理型卖家"?"checked":""} onclick="return false;"/>代理型卖家,<input
                                type="checkbox" ${bean.slMainclass eq "OEM型卖家"?"checked":""} onclick="return false;"/>OEM型卖家;&emsp;卖家国籍/地区:&emsp;${bean.slConFlg};&emsp;卖家名称及编码:&emsp;${bean.slCodeDis}/${bean.epName}
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 100%;padding:5px">&emsp;申报日期:&emsp;${bean.showDate}</td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
    <div class="group-accordion" collapsible="false" active="false" style="height:80%" id="pd141137accordion">
        <h3 style="position: relative">
            <label>产品总控目录在线管理列表</label>
        </h3>

        <div style="padding-left: 0px;padding-top: 0px;padding-bottom: 0px;padding-right: 0px;">
            <div>
                <form>
                    <table style="width: 100%;padding:10px">
                        <tr>
                            <td style="width: 100%;padding:5px">&emsp;&emsp;原料描述：</td>
                        </tr>
                        <tr>
                            <td style="width: 100%;padding:5px">&emsp;&emsp;原料学名：${pdClassestreeMat.scientificName}，俗名：${pdClassestreeMat.localName}，标准市场销售名：${pdClassestreeMat.salesName}</td>
                        </tr>
                        <tr>
                            <td style="width: 100%;padding:5px">&emsp;&emsp;原料原产地：${pdClassestreeMat.placeOrigin}，现产地：${pdClassestreeMat.placeCurrent}</td>
                        </tr>
                        <tr>
                            <td style="width: 100%;padding:5px">&emsp;&emsp;原料种源：${pdClassestreeMat.source}，雏类：${pdClassestreeMat.childType}，饲养方式：${pdClassestreeMat.feedType}</td>
                        </tr>
                        <tr>
                            <td style="width: 100%;padding:5px">&emsp;&emsp;饲养周期：${pdClassestreeMat.feedPeriod}</td>
                        </tr>
                    </table>
                </form>
            </div>
            <div>
                <table class="tree dataTable no-footer" style="width: 100%;padding:10px" valign="i">
                    <tr style="background:#DBDBDB">
                        <td name="" align="center" rowspan="2">序号</td>
                        <td width="40px" align="center" colspan="3">品种名</td>
                        <td align="center" rowspan="2">供应备案注册线<br/>产品及日期</td>
                        <td align="center" rowspan="2">禁止注册线产品及日期</td>
                        <td align="center" rowspan="2" width="40px">试销线产品及日期</td>
                        <td align="center" rowspan="2" width="40px">正式上线有货产品<br/>及日期</td>
                        <td align="center" rowspan="2" width="40px">正式上线断货产品<br/>及日期</td>
                        <td align="center" rowspan="2" width="40px">下线产品及日期</td>
                        <td align="center" rowspan="2" width="40px">黑名单产品及日期</td>
                        <td align="center" rowspan="2" width="40px">产品等级</td>
                        <td align="center" rowspan="2" width="40px">单箱净重(kg)</td>
                        <td align="center" rowspan="2" width="40px">包装规格</td>
                        <td align="center" rowspan="2" width="40px">品牌类型</td>
                        <td align="center" rowspan="2" width="40px">品牌</td>
                        <td align="center" colspan="2" width="40px">生产商信息</td>
                        <td align="center" rowspan="2">备注</td>
                        <td align="center" rowspan="2">卖家标准产品码</td>
                        <td align="center" rowspan="2">在线库存(箱)</td>
                    </tr>
                    <tr style="background:#DBDBDB">
                        <td width="15px" align="center">标准市场销售名</td>
                        <td align="center" width="10px">学名</td>
                        <td align="center" width="15px">俗名</td>
                        <td align="center" width="15px">地区/国籍</td>
                        <td align="center" width="15px">生产商名称</td>
                    </tr>
                    <c:forEach items="${listAll}" var="thisList" varStatus="j">
                        <tr>
                            <td>${j.index+1}</td>
                            <td>${thisList.salesName}</td>
                            <td>${thisList.scientificName}</td>
                            <td>${thisList.localName}</td>
                            <td id="shen" valign="top">
                                <a class="zhuCeNeedBtn${j.index}" name="zhuCeNeed" href="javascript:void(0);"
                                   col="10"><img border="0px" src="${ctx}/static/core/images/action/details_open.png"
                                                 id="zhuCeImg${j.index}"/></a>
                                <div style="display:none;margin-left:20px;margin-right:0px;width:180px"
                                     id="divZhuCe${j.index}">
                                    <table class="tree dataTable no-footer" style="min-width:80px" width="230px"
                                           showAddBtn="true">
                                        <tr style="background:#DBDBDB">
                                            <td align="center">注册线产品</td>
                                            <td align="center">日期</td>
                                        </tr>
                                        <c:forEach items="${thisList.applyAndArgList}" var="applyAndArgList">
                                            <tr>
                                                <td align="center">${applyAndArgList.featureName}</td>
                                                <td align="center">${applyAndArgList.featureDate}</td>
                                            </tr>
                                        </c:forEach>
                                    </table>
                                </div>
                            </td>
                            <td id="jin" valign="top">
                                <a class="jinZhiNeedBtn${j.index}" name="jinZhiNeed" href="javascript:void(0);"
                                   col="10"><img border="0px" src="${ctx}/static/core/images/action/details_open.png"
                                                 id="jinZhiImg${j.index}"/></a>

                                <div style="display:none;margin-left:20px;margin-right:0px;width:180px"
                                     id="divJinZhi${j.index}">
                                    <table class="tree dataTable no-footer" style="min-width:80px" width="230px"
                                           showAddBtn="true">
                                        <tr style="background:#DBDBDB">
                                            <td align="center">禁止注册线产品</td>
                                            <td align="center">日期</td>
                                        </tr>
                                        <c:forEach items="${thisList.forbidList}" var="forbidList">
                                            <tr>
                                                <td align="center">${forbidList.featureName}</td>
                                                <td align="center">${forbidList.featureDate}</td>
                                            </tr>
                                        </c:forEach>
                                    </table>
                                </div>
                            </td>
                            <td id="buyer" valign="top">
                                <a class="shiXiaoNeedBtn${j.index}" name="shiXiaoNeed" href="javascript:void(0);"
                                   col="10"><img border="0px" src="${ctx}/static/core/images/action/details_open.png"
                                                 id="shiXiaoImg${j.index}"/></a>

                                <div style="display:none;margin-left:20px;margin-right:0px;width:180px"
                                     id="divShiXiao${j.index}">
                                    <table class="tree dataTable no-footer" style="min-width:80px" width="230px"
                                           showAddBtn="true">
                                        <tr style="background:#DBDBDB">
                                            <td align="center">试销线产品</td>
                                            <td align="center">日期</td>
                                        </tr>
                                        <c:forEach items="${thisList.marketingList}" var="marketingList">
                                            <tr>
                                                <td align="center">${marketingList.featureName}</td>
                                                <td align="center">${marketingList.featureDate}</td>
                                            </tr>
                                        </c:forEach>
                                    </table>
                                </div>
                            </td>
                            <td id="shangXian" valign="top">
                                <a class="shangXianNeedBtn${j.index}" name="shangXianNeed" href="javascript:void(0);"
                                   col="10"><img border="0px" src="${ctx}/static/core/images/action/details_open.png"
                                                 id="shangXianNeedImg${j.index}"/></a>

                                <div style="display:none;margin-left:20px;margin-right:0px;width:180px"
                                     id="divShangXianNeed${j.index}">
                                    <table class="tree dataTable no-footer" style="min-width:80px" width="230px"
                                           showAddBtn="true">
                                        <tr style="background:#DBDBDB">
                                            <td align="center">正式上线产品</td>
                                            <td align="center">日期</td>
                                        </tr>
                                        <c:forEach items="${thisList.topLineList}" var="topLineList">
                                            <tr>
                                                <td align="center">${topLineList.featureName}</td>
                                                <td align="center">${topLineList.featureDate}</td>
                                            </tr>
                                        </c:forEach>
                                    </table>
                                </div>
                            </td>
                            <td valign="top">
                                <a class="breakBtn${j.index}" name="duanHuoNeed" href="javascript:void(0);" col="10">
                                <img border="0px" src="${ctx}/static/core/images/action/details_open.png" id="breakImg${j.index}"/></a>
                                <div style="display:none;margin-left:20px;margin-right:0px;width:180px"
                                     id="divBreakGoods${j.index}">
                                    <table class="tree dataTable no-footer" style="min-width:80px" width="230px"
                                           showAddBtn="true">
                                        <tr style="background:#DBDBDB">
                                            <td align="center">线断货产品</td>
                                            <td align="center">日期</td>
                                        </tr>
                                        <c:forEach items="${thisList.blacklist}" var="breakList">
                                            <tr>
                                                <td align="center">${breakList.featureName}</td>
                                                <td align="center">${breakList.featureDate}</td>
                                            </tr>
                                        </c:forEach>
                                    </table>
                                </div>
                            </td>

                            <td valign="top">
                                <a class="xiaXianNeedBtn${j.index}" name="xiaXianNeed" href="javascript:void(0);"
                                   col="10"><img border="0px" src="${ctx}/static/core/images/action/details_open.png"
                                                 id="xiaXianNeedImg${j.index}"/></a>
                                <div style="display:none;margin-left:20px;margin-right:0px;width:180px"
                                     id="divXiaXianNeed${j.index}">
                                    <table class="tree dataTable no-footer" style="min-width:80px" width="230px"
                                           showAddBtn="true">
                                        <tr style="background:#DBDBDB">
                                            <td align="center">下线产品</td>
                                            <td align="center">日期</td>
                                        </tr>
                                        <c:forEach items="${thisList.downLineList}" var="downLineList">
                                            <tr>
                                                <td align="center">${downLineList.featureName}</td>
                                                <td align="center">${downLineList.featureDate}</td>
                                            </tr>
                                        </c:forEach>
                                    </table>
                                </div>
                            </td>

                            <td valign="top">
                                <a class="blacklistNeedBtn${j.index}" name="blacklistNeed" href="javascript:void(0);"
                                   col="10"><img border="0px" src="${ctx}/static/core/images/action/details_open.png"
                                                 id="blacklistImg${j.index}"/></a>
                                <div style="display:none;margin-left:20px;margin-right:0px;width:180px"
                                     id="divBlacklist${j.index}">
                                    <table class="tree dataTable no-footer" style="min-width:180px" width="230px"
                                           showAddBtn="true">
                                        <tr style="background:#DBDBDB">
                                            <td align="center">黑名单产品</td>
                                            <td align="center">日期</td>
                                        </tr>
                                        <c:forEach items="${thisList.blacklist}" var="blacklist">
                                            <tr>
                                                <td align="center">${blacklist.featureName}</td>
                                                <td align="center">${blacklist.featureDate}</td>
                                            </tr>
                                        </c:forEach>
                                    </table>
                                </div>
                            </td>
                            <td>A2</td>
                            <td colspan="2" valign="top">
                                <a class="product${j.index}" name="productName" href="javascript:void(0);" col="10"><img border="0px" src="${ctx}/static/core/images/action/details_open.png" id="productImg${j.index}"/></a>
                                <div style="display:none;margin-left:20px;margin-right:0px;width:220px" id="productDiv${j.index}">
                                    <table class="tree dataTable no-footer classTree" style="min-width:170px" width="170px" showAddBtn="true">
                                        <c:forEach items="${thisList.treeList}" var="pdFeature">
                                            <tr class="treegrid-${pdFeature.classestreeCode}">
                                                <td name="classes" value="${pdFeature.classestreeCode}">${pdFeature.classestreeCode}</td>
                                                <td value="${pdFeature.featureName}">${pdFeature.featureName}</td>
                                            </tr>
                                            <c:forEach items="${pdFeature.weightList}" var="weight">
                                                <c:if test="${not empty weight.classestreeCode}">
                                                    <tr class="treegrid-${weight.classestreeCode} treegrid-parent-${pdFeature.classestreeCode}">
                                                        <td name="weight"  value="${weight.weightCode}">${weight.weightCode}</td>
                                                        <td value="${weight.weightName}">${weight.weightName}</td>
                                                    </tr>
                                                </c:if>
                                                <c:forEach items="${weight.normsList}" var="norms">
                                                    <c:if test="${not empty norms.normsCode}">
                                                        <tr class="treegrid-${norms.normsCode} treegrid-parent-${weight.classestreeCode}">
                                                            <td name="norms"  value="${norms.normsCode}">${norms.normsCode}</td>
                                                            <td value="${norms.normsName}">${norms.normsName}</td>
                                                        </tr>
                                                    </c:if>
                                                </c:forEach>
                                            </c:forEach>
                                        </c:forEach>
                                    </table>
                                </div>
                            </td>
                            <td>${thisList.brandClass}</td>
                            <td>${thisList.brandName}</td>
                            <td>${thisList.cityName}</td>
                            <td>${thisList.epName}</td>
                            <td>${thisList.remark}</td>
                            <td colspan="2">
                                <a class="numberNeedBtn${j.index}" name="numberNeed" href="javascript:void(0);"
                                   col="10"><img border="0px" src="${ctx}/static/core/images/action/details_open.png"
                                                 id="numberImg${j.index}"/></a>
                                <div style="display:none;margin-left:20px;margin-right:0px;width:180px"
                                     id="divnumber${j.index}">
                                    <table class="tree dataTable no-footer" style="min-width:180px" width="230px"
                                           showAddBtn="true">
                                        <tr style="background:#DBDBDB">
                                            <td align="center">编码</td>
                                            <td align="center">库存数</td>
                                        </tr>
                                        <c:forEach items="${thisList.allNumber}" var="allNumber">
                                            <tr>
                                                <td align="center">${allNumber.stockQty}</td>
                                                <td align="center">${allNumber.slCodeName}</td>
                                            </tr>
                                        </c:forEach>
                                    </table>
                                </div>

                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>
</div>
<script src="${ctx}/js/pd/PD141137.js"></script>
