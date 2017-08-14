<%--
    Title:中标确认详细页面
    author:zhao_chen1
    createDate:2016-06-28
--%>
<%@ taglib prefix="navigation" uri="/msk" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<msk:codemaster codeType="BidStatus" viewType="json" modelName="SSC"/>

<c:if test="${type eq '1'}">
    <navigation:header title="中标成交确认书详细" backTitleArray="合同管理一览,合同详细" backUrlArray="../SSC11303/init,../SSC11304/init" backParamArray="/{contractId:${contractId}}"></navigation:header>
</c:if>
<c:if test="${type eq '2'}">
    <navigation:header title="中标成交确认书详细" backTitleArray="中标成交确认书一览" backUrlArray="../SSC11301/init"></navigation:header>
</c:if>

<div class="page-content list-page">
    <form id="SSC11302Form" action="<c:url value="/SSC11302/search/"/>" method="post">
        <input type="hidden" name="bidCode" id="bidCode" value="${ssc11301RsBean.bidId}">
        <input type="hidden" name="slCode" id="slCode" value="${ssc11301RsBean.slCode}">
        <input type="hidden" name="bidStatus" id="bidStatus" value="${ssc11301RsBean.bidStatus}">
        <input type="hidden" name="supplierId" id="supplierId" value="${ssc11301RsBean.supplierId}">
        <input type="hidden" name="supplierName" id="supplierName" value="${ssc11301RsBean.supplierName}">
        <input type="hidden" name="purchaserName" id="purchaserName" value="${ssc11301RsBean.purchaserName}">
        <input type="hidden" id="bidProjectNameChange" value="${ssc11301RsBean.bidProjectName}">
        <input type="hidden" id="startDateChange" value="${ssc11301RsBean.startDate}">
        <input type="hidden" id="endDateChange" value="${ssc11301RsBean.endDate}">
        <input type="hidden" id="ver" value="${ssc11301RsBean.ver}">
        <c:if test="${not empty ssc11301RsBean.bidId}">
            <input type="hidden" name="bidProjectNo" id="bidProjectNo" value="${ssc11301RsBean.bidProjectNo}" />
        </c:if>
        <input type="hidden" name="type" id="type" value="${type}">
        <%--上下表格保存传递的bidid值--%>
        <input type="hidden" name="bidId" id="bid" value="${bid}">

        <div class="group-accordion" collapsible="true" active="true" style="white-space:nowrap;">
            <h3>
                <label>中标项目详细</label>
            </h3>
            <table width="100%" >
            <c:choose>
                <c:when test="${type eq '1'}">
                        <tr >
                            <td align="right" >招标公司名称：</td>
                            <td > ${ssc11301RsBean.purchaserName}</td>
                            <td align="right" >中标公司名称：</td>
                            <td>${ssc11301RsBean.supplierName} </td>
                        </tr>

                        <tr>
                            <td align="right" width="12%" >招标项目编号：</td>
                            <td >${ssc11301RsBean.bidProjectNo}</td>
                            <td align="right" >招标项目名称：</td>
                            <td >
                                <input type="text" id="bidProjectName" name="bidProjectName"  value="${ssc11301RsBean.bidProjectName}" maxlength="100" style="width:295px"/>
                            </td>
                        </tr>

                        <tr>
                            <td align="right" >开标开始时间：</td>
                            <td >
                                <input name="startDate" id="startDate" readonly type="text" value="${ssc11301RsBean.startDate}" style="width:295px" />
                            </td>
                            <td align="right" >开标截止时间：</td>
                            <td >
                                <input name="endDate" id="endDate" readonly type="text" value="${ssc11301RsBean.endDate}" style="width:295px" />
                            </td>
                        </tr>
                         <tr>
                            <td align="right" >中标成交确认书状态：</td>
                            <td >
                                <c:if test="${not empty ssc11301RsBean}">
                                    <msk:codemaster codeType="BidStatus"   viewType="label" modelName="SSC"
                                                    codeValue="${ssc11301RsBean.bidStatus}"/>
                                </c:if>
                            </td>
                        </tr>
                    <tr>
                        <td align="right">
                            采购方确认人：
                        </td>
                        <td >
                            <c:if test="${ssc11301RsBean.bidStatus ne 0&&ssc11301RsBean.bidStatus ne 9&&ssc11301RsBean.bidStatus ne 2}">
                                ${ssc11301RsBean.purchaserConfirmName}
                            </c:if>
                        </td >
                        <td align="right" >
                            采购方确认时间：
                        </td>
                        <td >
                            <c:if test="${ssc11301RsBean.bidStatus ne 0&&ssc11301RsBean.bidStatus ne 9&&ssc11301RsBean.bidStatus ne 2}">
                               <%--<fmt:formatDate value=" ${ssc11301RsBean.purchaserConfirmTime}" type="both" dateStyle="default"/>--%>
                                <fmt:formatDate value='${ssc11301RsBean.purchaserConfirmTime}' pattern="yyyy-MM-dd hh:mm:ss" />
                            </c:if>
                        </td>
                    </tr>
                    <tr>
                        <td align="right" >
                            生产方确认人：
                        </td>
                        <td>
                            <c:if test="${ssc11301RsBean.bidStatus ne 0&&ssc11301RsBean.bidStatus ne 9&&ssc11301RsBean.bidStatus ne 1}">
                                ${ssc11301RsBean.supplierConfirmName}
                            </c:if>
                        </td >
                        <td align="right" >
                            生产方确认时间：
                        </td>
                        <td >
                            <c:if test="${ssc11301RsBean.bidStatus ne 0&&ssc11301RsBean.bidStatus ne 9&&ssc11301RsBean.bidStatus ne 1}">
                                <%--<fmt:formatDate value="${ssc11301RsBean.supplierConfirmTime}" type="date" dateStyle="default"/>--%>
                                <fmt:formatDate value='${ssc11301RsBean.supplierConfirmTime}' pattern="yyyy-MM-dd hh:mm:ss" />
                            </c:if>
                        </td>
                    </tr>
                </c:when>

                <c:otherwise>
                        <tr>
                            <td align="right" width="12%" >招标公司名称：</td>
                            <td align="left" >
                                <c:if test="${not empty ssc11301RsBean.bidId}">
                                    ${ssc11301RsBean.purchaserName}
                                </c:if>
                                <c:if test="${empty ssc11301RsBean.bidId}">
                                    <select name="purchaserName" id="purchaserNameSelect" style="width:300px">
                                        <option value="0">----请选择----</option>
                                        <c:forEach items="${purchaserNameList}" var="list">
                                            <option value="${list.slCode}" purchaserName="${list.epName}" purchaserId="${list.epId}" slMainClass="${list.slMainClass}">${list.epName}</option>
                                        </c:forEach>
                                    </select>
                                 </c:if>
                            </td>
                            <td align="right">中标公司名称：</td>
                            <td >
                                <c:if test="${not empty ssc11301RsBean.bidId}" >
                                    ${ssc11301RsBean.supplierName}
                                </c:if>
                                <c:if test="${empty ssc11301RsBean.bidId}">
                                    <select name="supplierName" id="supplierNameSelect"  style="width:300px">
                                        <option value="0">----请选择----</option>
                                    </select>
                                </c:if>

                            </td>
                        </tr>

                        <tr>
                            <td align="right">招标项目编号：</td>
                            <td>
                                 ${ssc11301RsBean.bidProjectNo}
                            </td>
                            <td align="right">招标项目名称：</td>
                            <td>
                                <c:if test="${ssc11301RsBean.bidStatus ne 9 && ssc11301RsBean.bidStatus ne 3}">
                                     <input type="text" id="bidProjectName" name="bidProjectName" value="${ssc11301RsBean.bidProjectName}" maxlength="100" style="width:295px" />
                                </c:if>
                                <c:if test="${ssc11301RsBean.bidStatus eq 9 || ssc11301RsBean.bidStatus eq 3}">
                                    ${ssc11301RsBean.bidProjectName}
                                </c:if>
                            </td>
                        </tr>
                        <tr>
                            <td align="right" width="20%" >开标开始时间：</td>
                            <td>
                                <c:if test="${ssc11301RsBean.bidStatus ne 9 && ssc11301RsBean.bidStatus ne 3}">
                                     <input type="text" id="startDate" readonly name="startDate" value="${ssc11301RsBean.startDate}" style="width:295px" />
                                </c:if>
                                <c:if test="${ssc11301RsBean.bidStatus eq 9 || ssc11301RsBean.bidStatus eq 3}">
                                    ${ssc11301RsBean.startDate}
                                </c:if>

                            </td>
                            <td align="right">开标截止时间：</td>
                            <td>
                                <c:if test="${ssc11301RsBean.bidStatus ne 9 && ssc11301RsBean.bidStatus ne 3}">
                                     <input type="text" id="endDate" readonly name="endDate" value="${ssc11301RsBean.endDate}" style="width:295px" />
                                </c:if>
                                <c:if test="${ssc11301RsBean.bidStatus eq 9 || ssc11301RsBean.bidStatus eq 3}">
                                    ${ssc11301RsBean.endDate}
                                </c:if>
                            </td>
                        </tr>
                        <tr>
                            <td align="right" >中标成交确认书状态：</td>
                            <td>
                                <c:if test="${not empty ssc11301RsBean}">
                                    <msk:codemaster codeType="BidStatus"   viewType="label" modelName="SSC"
                                                    codeValue="${ssc11301RsBean.bidStatus}"/>
                                </c:if>
                            </td>
                        </tr>
                    <tr>
                        <td align="right">
                            采购方确认人：
                        </td>
                        <td>
                            <c:if test="${ssc11301RsBean.bidStatus ne 0&&ssc11301RsBean.bidStatus ne 2}">
                            ${ssc11301RsBean.purchaserConfirmName}
                            </c:if>
                        </td>
                        <td align="right" >
                            采购方确认时间：
                        </td>
                        <td>
                            <c:if test="${ssc11301RsBean.bidStatus ne 0&&ssc11301RsBean.bidStatus ne 2}">
                            <fmt:formatDate value="${ssc11301RsBean.purchaserConfirmTime}"  pattern="yyyy-MM-dd hh:mm:ss"/>
                            </c:if>
                        </td>
                    </tr>
                    <tr>
                        <td align="right">
                            生产方确认人：
                        </td>
                        <td>
                            <c:if test="${ssc11301RsBean.bidStatus ne 0&&ssc11301RsBean.bidStatus ne 1}">
                                ${ssc11301RsBean.supplierConfirmName}
                            </c:if>
                        </td>
                        <td align="right" >
                            生产方确认时间：
                        </td>
                        <td>
                            <c:if test="${ssc11301RsBean.bidStatus ne 0&&ssc11301RsBean.bidStatus ne 1}">
                                <fmt:formatDate value="${ssc11301RsBean.supplierConfirmTime}"  pattern="yyyy-MM-dd hh:mm:ss" />
                            </c:if>
                        </td>
                    </tr>
                </c:otherwise>
            </c:choose>
            <c:if test="${ssc11301RsBean.bidStatus ne 9}">
                <tr>
                    <td align="left" colspan="2">
                        <c:if test="${ssc11301RsBean.bidStatus != 3}">
                            <msk:button buttonValue="&nbsp;&nbsp;&nbsp;保&nbsp;&nbsp;&nbsp;存&nbsp;&nbsp;&nbsp;" buttonId="SSC1130202.ADD" buttonType="button"/>
                        </c:if>
                    </td>
                </tr>
            </c:if>

            </table>

        </div>
        <div class="group-accordion" collapsible="true" active="true">
            <h3>
                中标产品清单
            </h3>
            <table width="100%" id="SSC11302_list_grid">
                <thead>
                <tr>
                    <th coltype="sno" rowspan="2">序号</th>

                    <th colspan="2">产品信息</th>
                    <th colspan="2">订单数量</th>
                    <th colspan="3">中标价(元/kg)</th>
                    <th colspan="2">到岸价组成</th>
                    <th colspan="2">货值</th>
                    <th  colspan="2" >其它</th>
                </tr>
                <tr>
                    <th coltype="text" name="pdDesc">产品</th>
                    <th coltype="action">产品质量标准
                        <msk:button buttonType="hidden" buttonId="SSC11302.AUDIT" coltype="audit" buttonValue="产品质量标准" class="h-button"/>
                    </th>
                    <th coltype="money" accuracy="4" width="8%" name="productQua">重量(吨)</th>
                    <th coltype="money" accuracy="0" <c:if test="${ssc11301RsBean.bidStatus ne 9 && ssc11301RsBean.bidStatus ne 3}"> edit="true"</c:if>  width="8%" name="productBox">&nbsp;&nbsp;&nbsp;箱数&nbsp;&nbsp;&nbsp;</th>
                    <th coltype="money" <c:if test="${ssc11301RsBean.bidStatus ne 9 && ssc11301RsBean.bidStatus ne 3}"> edit="true"</c:if>  width="8%" name="fobFreePackage" >不含包装离岸价</th>
                    <th coltype="money" <c:if test="${ssc11301RsBean.bidStatus ne 9 && ssc11301RsBean.bidStatus ne 3}"> edit="true"</c:if>  width="8%" name="packageCost" >包材成本</th>
                    <th coltype="money" <c:if test="${ssc11301RsBean.bidStatus ne 9 && ssc11301RsBean.bidStatus ne 3}"> edit="true"</c:if>  width="8%" name="fobIncludePackage" >含包装离岸价</th>
                    <th coltype="money" <c:if test="${ssc11301RsBean.bidStatus ne 9 && ssc11301RsBean.bidStatus ne 3}"> edit="true"</c:if>  width="8%" name="trunkFreight" >干线运费</th>
                    <th coltype="money" <c:if test="${ssc11301RsBean.bidStatus ne 9 && ssc11301RsBean.bidStatus ne 3}"> edit="true"</c:if>  width="8%" name="cif" >&nbsp;&nbsp;到岸价&nbsp;&nbsp;</th>
                    <th coltype="money" <c:if test="${ssc11301RsBean.bidStatus ne 9 && ssc11301RsBean.bidStatus ne 3}"> edit="true"</c:if>  width="8%" name="settkementStandardPrice" >本次结算标准价(元/kg)</th>
                    <th coltype="money" edit="false" width="8%" name="productValue">货值(元)</th>
                    <th coltype="text" <c:if test="${ssc11301RsBean.bidStatus ne 9 && ssc11301RsBean.bidStatus ne 3}"> edit="true"</c:if>  name="remark"  align="center" style="width:50px">&nbsp;&nbsp;备注&nbsp;&nbsp;</th>
                    <th coltype="action">操作
                        <c:if test="${ssc11301RsBean.bidStatus ne 9 &&  ssc11301RsBean.bidStatus ne 3 }">
                            <msk:button buttonValue="保存" buttonId="SSC11302.SAVE" buttonType="hidden" coltype="save"
                                    class="h-button"/>
                            <msk:button buttonValue="删除" buttonId="SSC11302.DELETE" buttonType="hidden" coltype="delete"
                                    class="h-button"/>
                        </c:if>
                    </th>

                </tr>
                </thead>
                <tbody id="productData"></tbody>
                <tbody>
                <tr>
                    <td style="text-align: center" colspan="3">合计:</td>
                    <td align="right">
                        <label id="sumProductQua" />
                    </td>
                    <td align="right">
                        <label id="sumProductBox" />
                    </td>
                    <td colspan="6"></td>
                    <td align="right"><label id="sumProductValue" /></td>
                    <td colspan="2"></td>
                </tr>
                </tbody>
            </table>
        </div>
            <div>
            <c:if test="${not empty ssc11301RsBean.bidId && ssc11301RsBean.bidStatus ne 9}">
                <c:if test="${ssc11301RsBean.bidStatus ne 3}">
                    <msk:button buttonValue="添加产品" buttonId="SSC11302.ADDPRODUCT" buttonType="button"/>
                    <c:if test="${ssc11301RsBean.bidStatus ne 1}">
                        <msk:button buttonValue="采购商确认" buttonId="SSC1130202.SCC" buttonType="button"/>
                    </c:if>
                    <c:if test="${ssc11301RsBean.bidStatus ne 2}">
                        <msk:button buttonValue="生产商确认" buttonId="SSC1130202.BID" buttonType="button"/>
                    </c:if>
                </c:if>
                <c:if test="${ssc11301RsBean.bidStatus eq 3}">
                    <msk:button buttonValue="再修改" buttonId="SSC1130202.REMODIFY" buttonType="button"/>
                    <msk:button buttonValue="生成合同" buttonId="SSC11302.CREATECONTRACTS" buttonType="button"/>
                </c:if>
            </c:if>
        </div>
    </form>
</div>
<script src="<c:url value="/static/js/ssc/SSC11302.js"/>" />
<script src="<c:url value="/static/js/ssc/SSCCommon.js"/>" />
