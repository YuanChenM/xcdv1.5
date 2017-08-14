<%--
    Title:生产商计划管理
    author:liu_yan2
    createDate:2016-08-08
--%>

<%@ taglib prefix="navigation" uri="/msk" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<navigation:header title="生产商计划管理" backTitleArray="" backUrlArray=""></navigation:header>
<div class="page-content list-page">
    <div>
        <table>
            <tr>
                <td>
                    <label for="tags"><b>选择合同：</b></label>
                </td>
                <c:if test="${ssc11326RsBean ne null}">
                    <td><input id="tags" style="width: 250px;" readonly="readonly" value="${ssc11326RsBean.contractCode}(${ssc11326RsBean.contractName})"></td>
                </c:if>
                <c:if test="${ssc11326RsBean eq null}">
                    <td><input id="tags" style="width: 250px;" readonly="readonly"></td>
                </c:if>
                <td><img  src="../static/images/action/search.png" title="选择合同" id="chooseContractInfo" style="cursor:pointer;" /></td>
                <td><msk:button buttonType="button" buttonId="SSC11326.SEARCH" buttonValue="查询"/></td>
            </tr>
        </table>
    </div>

    <br/>
    <c:if test="${ssc11326RsBean ne null}">
        <div class="group-accordion" collapsible="true" active="true">
            <h3>
                产品生产日期管理
            </h3>
            <form action='<c:url value="/SSC11326/save"/>' id="SSC11326Form" method="post">
                <input type="hidden" id="contractId" name="contractId" value="${ssc11326RsBean.contractId}"/>
                <input type="hidden" id="contractCode" name="contractCode" value="${ssc11326RsBean.contractCode}"/>
                <input type="hidden" id="ver" name="ver" value="${ssc11326RsBean.ver}"/>
                <table>
                    <tr>
                        <td>
                            合同生效日期：${ssc11326RsBean.contractActDate}
                        </td>
                    </tr>
                    <tr>
                        <td>
                            最迟交货日期：${ssc11326RsBean.lastDeliveryDate}
                        </td>
                    </tr>
                    <tr>
                        <td>
                            生产商计划开始日期：
                            <c:if test="${ssc11326RsBean.realProduceStartDate eq null}">
                                <input id="realProduceStartDateStr" name="realProduceStartDateStr" type="text" value="${ssc11326RsBean.contractActDate}" readonly/>
                            </c:if>
                            <c:if test="${ssc11326RsBean.realProduceStartDate ne null}">
                                <input id="realProduceStartDateStr" name="realProduceStartDateStr" type="text" value="${ssc11326RsBean.realProduceStartDate}" readonly/>
                            </c:if>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            生产商计划结束日期：
                            <c:if test="${ssc11326RsBean.realProduceEndDate eq null}">
                                <input id="realProduceEndDateStr" name="realProduceEndDateStr" type="text" value="${ssc11326RsBean.lastDeliveryDate}" readonly/>
                            </c:if>
                            <c:if test="${ssc11326RsBean.realProduceEndDate ne null}">
                                <input id="realProduceEndDateStr" name="realProduceEndDateStr" type="text" value="${ssc11326RsBean.realProduceEndDate}" readonly/>
                            </c:if>
                        </td>
                    </tr>
                </table>
                <msk:button buttonType="button" buttonId="SSC11326.SAVE" buttonValue="修改生产商生产日期"/>
                <msk:button buttonType="button" buttonId="SSC11326.VIEW" buttonValue="查看生产商生产计划"/>
            </form>
        </div>
    </c:if>
</div>
<script src="<c:url value="/static/js/ssc/SSC11326.js"/>"></script>
