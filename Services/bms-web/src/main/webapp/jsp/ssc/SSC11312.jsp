<%--
    Title: 生产商入库差异单详情
    author: xia_xiaojie
    createDate: 2016-07-05
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglib.jsp" %>
<%@ taglib prefix="navigation" uri="/msk" %>

<style type="text/css">
    a:link {color: blue;}
</style>

<msk:codemaster codeType="DifferStatus" viewType="json" modelName="SSC"/>

<navigation:header title="生产商入库差异单详细" backTitleArray="生产商入库差异单一览" backUrlArray="../SSC11311/init"></navigation:header>
<div class="page-content list-page">
    <form id="SSC11312_form">
        <input type="hidden" id="differId" value="${ssc11311Bean.differId}" />
        <input type="hidden" id="differCode" value="${ssc11311Bean.differCode}" />
        <input type="hidden" id="contractId" value="${ssc11311Bean.contractId}" />
        <input type="hidden" id="contractCode" value="${ssc11311Bean.contractCode}">
        <input type="hidden" id="deliveryId" value="${ssc11311Bean.deliveryId}" />
        <input type="hidden" id="deliveryCode" value="${ssc11311Bean.deliveryCode}" />
        <input type="hidden" id="deliveryPreIntoId" value="${ssc11311Bean.deliveryPreIntoId}" />
        <input type="hidden" id="deliveryPreIntoCode" value="${ssc11311Bean.deliveryPreIntoCode}" />
        <input type="hidden" id="eta" value="${ssc11311Bean.eta}" />
        <input type="hidden" id="confirmStatus" value="${ssc11311Bean.confirmStatus}" />
    </form>

    <!-- 基本信息 -->
    <div class="group-accordion" collapsible="true" active="true">
        <h3><label>基本信息</label></h3>
        <div>
            <table width="100%">
                <tr>
                    <td style="width:12%; text-align:right;">差异单编号：</td>
                    <td>${ssc11311Bean.differCode}</td>
                    <td style="width:12%; text-align:right;">差异单状态：</td>
                    <td id="status_td"><msk:codemaster codeType="DifferStatus" viewType="label" modelName="SSC" codeValue="${ssc11311Bean.confirmStatus}" /></td>
                </tr>
                <tr>
                    <td align="right">合同编号：</td>
                    <td><a href="javascript:SSC11312.clickContractCodeLink();">${ssc11311Bean.contractCode}</a></td>
                </tr>
                <tr>
                    <td align="right">发货订单编号：</td>
                    <td><a href="javascript:SSC11312.clickDeliveryCodeLink();">${ssc11311Bean.deliveryCode}</a></td>
                    <td align="right">入库单编号：</td>
                    <td id="into_code_td"></td>
                </tr>
                <tr>
                    <td style="white-space:nowrap; text-align:right;">计划发货日期：</td>
                    <td>${ssc11311Bean.etd}</td>
                    <td style="white-space:nowrap; text-align:right;">实际发货日期：</td>
                    <td>${ssc11311Bean.deliveryDate}</td>
                </tr>
                <tr>
                    <td align="right">计划到货日期：</td>
                    <td style="white-space:nowrap;">${ssc11311Bean.eta}</td>
                    <td align="right">实际到货日期：</td>
                    <td style="white-space:nowrap;">${ssc11311Bean.arriveDate}</td>
                </tr>
                <tr>
                    <td align="right">确认人：</td>
                    <td style="white-space:nowrap;">${ssc11311Bean.confirmName}</td>
                    <td align="right">确认时间：</td>
                    <td style="white-space:nowrap;">
                        <fmt:formatDate var="confirmTime" value="${ssc11311Bean.confirmTime}" pattern="yyyy-MM-dd hh:mm:ss"/>
                        ${confirmTime}
                    </td>
                </tr>
            </table>
        </div>
    </div>

    <!-- 差异详情 -->
    <div class="group-accordion" collapsible="true" active="true">
        <h3><label>差异详情</label></h3>
        <div>
            <table class="dataTable no-footer" width="100%">
                <thead>
                    <tr>
                        <th rowspan="2">序号</th>
                        <th rowspan="2">产品信息</th>
                        <th colspan="4">入库单</th>
                        <th colspan="4">发货订单</th>
                        <th rowspan="2">单价差(元/kg)</th>
                        <th rowspan="2">重量差(kg)</th>
                        <th rowspan="2">金额差(元)</th>
                    </tr>
                    <tr>
                        <th>箱数</th>
                        <th>重量(kg)</th>
                        <th>单价(元/kg)</th>
                        <th>金额(元)</th>
                        <th>箱数</th>
                        <th>重量(kg)</th>
                        <th>单价(元/kg)</th>
                        <th>金额(元)</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${sscDifferDetails}" var="differDetail" varStatus="status">
                        <tr>
                            <td align="center">${status.index + 1}</td>
                            <td align="left">${differDetail.productName}</td>
                            <td align="right">${differDetail.receiveBoxStr}</td>
                            <td align="right">${differDetail.receiveWeightStr}</td>
                            <td align="right">${differDetail.receivePriceStr}</td>
                            <td align="right">${differDetail.receiveAmountStr}</td>
                            <td align="right">${differDetail.sendBoxStr}</td>
                            <td align="right">${differDetail.sendWeightStr}</td>
                            <td align="right">${differDetail.sendPriceStr}</td>
                            <td align="right">${differDetail.sendAmountStr}</td>
                            <td align="right">${differDetail.priceDiffStr}</td>
                            <td align="right">${differDetail.weightDiffStr}</td>
                            <td align="right">${differDetail.amountDiffStr}</td>
                        </tr>
                    </c:forEach>
                    <c:if test="${not empty sscDifferDetails}">
                        <tr>
                            <td colspan="2" style="font-weight:bold; text-align:center;">合计</td>
                            <td align="right">${sscDifferDetails[0].totalReceiveBoxesStr}</td>
                            <td align="right">${sscDifferDetails[0].totalReceiveWeightStr}</td>
                            <td></td>
                            <td align="right">${sscDifferDetails[0].totalReceiveAmountStr}</td>
                            <td align="right">${sscDifferDetails[0].totalSendBoxesStr}</td>
                            <td align="right">${sscDifferDetails[0].totalSendWeightStr}</td>
                            <td></td>
                            <td align="right">${sscDifferDetails[0].totalSendAmountStr}</td>
                            <td></td>
                            <td align="right">${sscDifferDetails[0].totalWeightDiffStr}</td>
                            <td align="right">${sscDifferDetails[0].totalAmountDiffStr}</td>
                        </tr>
                    </c:if>
                </tbody>
            </table>
        </div>
    </div>

    <div class="group-accordion" collapsible="true" active="true">
        <h3>随车资料</h3>
        <div>
            <table class="dataTable no-footer" width="100%" border="0">
                <input id="downLoadUrl" type="hidden" value="${downLoadUrl}"/>
                <thead>
                <tr>
                    <th>入库单编号</th>
                    <th>到货车次</th>
                    <th>第N车</th>
                    <th>企业盖章三证</th>
                    <th>动物检疫合格证明</th>
                    <th>出库清单</th>
                    <th>有效期官方检测报告</th>
                </tr>
                </thead>
                <tbody>
                    <msk:button buttonType="hidden" coltype="download"  buttonValue="" buttonId="SSC11312.DOWNLOAD" />
                    <c:forEach items="${deliveryPreIntoList}" var="deliveryPreInto" varStatus="index">
                        <tr>
                            <td>${deliveryPreInto.deliveryPreIntoCode}</td>
                            <td>${deliveryPreInto.deliveryBatch}</td>
                            <td>${deliveryPreInto.vehicleNumber}</td>
                            <td>
                                <c:if test="${!empty deliveryPreInto.businessFileName}">
                                    <c:choose>
                                        <c:when test="${deliveryPreInto.businessFileFlag}">
                                            <a href="${downLoadUrl}?fid=${deliveryPreInto.businessFileId}&fileName=${deliveryPreInto.businessFileNameStr}" download="" style="text-decoration:none;">
                                                <msk:button buttonType="button" coltype="download"  buttonValue="" buttonId="SSC11312.DOWNLOAD" />
                                            </a>
                                            <a href="javascript:void(0);" onclick="SSCCommon.showPic('${downLoadUrl}?fid=${deliveryPreInto.businessFileId}&fileName=${deliveryPreInto.businessFileNameStr}')">
                                                ${deliveryPreInto.businessFileName}
                                            </a>
                                        </c:when>
                                        <c:otherwise>
                                            <a href="${downLoadUrl}?fid=${deliveryPreInto.businessFileId}&fileName=${deliveryPreInto.businessFileNameStr}" download="" style="text-decoration:none;">
                                                <msk:button buttonType="button" coltype="download"  buttonValue="" buttonId="SSC11312.DOWNLOAD" />
                                            </a>
                                            ${deliveryPreInto.businessFileName}
                                        </c:otherwise>
                                    </c:choose>
                                </c:if>
                            </td>
                            <td>
                                <c:if test="${!empty deliveryPreInto.quarantineFileName}">
                                    <c:choose>
                                        <c:when test="${deliveryPreInto.quarantineFileFlag}">
                                            <a href="${downLoadUrl}?fid=${deliveryPreInto.quarantineFileId}&fileName=${deliveryPreInto.quarantineFileNameStr}" download="" style="text-decoration:none;">
                                                <msk:button buttonType="button" coltype="download"  buttonValue="" buttonId="SSC11312.DOWNLOAD" />
                                            </a>
                                            <a href="javascript:void(0);" onclick="SSCCommon.showPic('${downLoadUrl}?fid=${deliveryPreInto.quarantineFileId}&fileName=${deliveryPreInto.quarantineFileNameStr}')">
                                                ${deliveryPreInto.quarantineFileName}
                                            </a>
                                        </c:when>
                                        <c:otherwise>
                                            <a href="${downLoadUrl}?fid=${deliveryPreInto.quarantineFileId}&fileName=${deliveryPreInto.quarantineFileNameStr}" download="" style="text-decoration:none;">
                                                <msk:button buttonType="button" coltype="download"  buttonValue="" buttonId="SSC11312.DOWNLOAD" />
                                            </a>
                                            ${deliveryPreInto.quarantineFileName}
                                        </c:otherwise>
                                    </c:choose>
                                </c:if>
                            </td>
                            <td>
                                <c:if test="${!empty deliveryPreInto.inventoryFileName}">
                                    <c:choose>
                                        <c:when test="${deliveryPreInto.inventoryFileFlag}">
                                            <a href="${downLoadUrl}?fid=${deliveryPreInto.inventoryFileId}&fileName=${deliveryPreInto.inventoryFileNameStr}" download="" style="text-decoration:none;">
                                                <msk:button buttonType="button" coltype="download" buttonValue="" buttonId="SSC11312.DOWNLOAD" />
                                            </a>
                                            <a href="javascript:void(0);" onclick="SSCCommon.showPic('${downLoadUrl}?fid=${deliveryPreInto.inventoryFileId}&fileName=${deliveryPreInto.inventoryFileNameStr}')">
                                                ${deliveryPreInto.inventoryFileName}
                                            </a>
                                        </c:when>
                                        <c:otherwise>
                                            <a href="${downLoadUrl}?fid=${deliveryPreInto.inventoryFileId}&fileName=${deliveryPreInto.inventoryFileNameStr}" download="" style="text-decoration:none;">
                                                <msk:button buttonType="button" coltype="download" buttonValue="" buttonId="SSC11312.DOWNLOAD" />
                                            </a>
                                            ${deliveryPreInto.inventoryFileName}
                                        </c:otherwise>
                                    </c:choose>
                                </c:if>
                            </td>
                            <td>
                                <c:if test="${!empty deliveryPreInto.reportFileName}">
                                    <c:choose>
                                        <c:when test="${deliveryPreInto.reportFileFlag}">
                                            <a href="${downLoadUrl}?fid=${deliveryPreInto.reportFileId}&fileName=${deliveryPreInto.reportFileNameStr}" download="" style="text-decoration:none;">
                                                <msk:button buttonType="button" coltype="download"  buttonValue="" buttonId="SSC11312.DOWNLOAD" />
                                            </a>
                                            <a href="javascript:void(0);" onclick="SSCCommon.showPic('${downLoadUrl}?fid=${deliveryPreInto.reportFileId}&fileName=${deliveryPreInto.reportFileNameStr}')">
                                                ${deliveryPreInto.reportFileName}
                                            </a>
                                        </c:when>
                                        <c:otherwise>
                                            <a href="${downLoadUrl}?fid=${deliveryPreInto.reportFileId}&fileName=${deliveryPreInto.reportFileNameStr}" download="" style="text-decoration:none;">
                                                <msk:button buttonType="button" coltype="download"  buttonValue="" buttonId="SSC11312.DOWNLOAD" />
                                            </a>
                                            ${deliveryPreInto.reportFileName}
                                        </c:otherwise>
                                    </c:choose>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

    <div style="margin-top:15px;">
        <c:if test="${ssc11311Bean.confirmStatus ne 1}"><msk:button buttonValue="差异单确认" buttonId="SSC11312.CONFIRM" buttonType="button" /></c:if>
        <msk:button buttonValue="导出数据" buttonId="SSC11312.EXPORT" buttonType="button" />
    </div>
</div>
<script src="<c:url value="/static/js/ssc/SSCCommon.js"/>"></script>
<script src="<c:url value='/static/js/ssc/SSC11312.js' />"></script>