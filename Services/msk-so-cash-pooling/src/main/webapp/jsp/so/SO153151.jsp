<%--
    Title:支付一览
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/comm/taglib.jsp" %>
<%@ taglib prefix="msk" uri="/codemaster" %>
<msk:codemaster codeType="TransType" viewType="json" />
<msk:codemaster codeType="AmountType" viewType="json" />
<msk:codemaster codeType="PaidType" viewType="json" />
<navigation:header title="支付一览" backTitleArray="" backUrlArray="${ctx}"></navigation:header>

<div class="page-content list-page">
    <form action="${ctx}/SO153151/search" id="SO153151Form" method="post">
        <table width="100%"><tr><td>
        <div class="group-accordion" collapsible="true" active="true">
            <h3>
                <label>查询条件</label>
            </h3>
            <table WIDTH="100%">
                <tr>
                    <td  align="right">支付日期 :</td>
                    <td  align="left" colspan="8">　
                        <input type="text" id="paidTimeStart" name="filterMap['paidTimeStart']" value="${param.filterMap.paidTimeStart}"/>
                        &emsp;~&emsp;
                        <input type="text" id="paidTimeEnd" name="filterMap['paidTimeEnd']" value="${param.filterMap.paidTimeEnd}" />
                    </td>
                </tr>
            </table>
        </div>
        </td></tr>
        <tr><td>
        <div>
            <table id="SO153151_list_grid" WIDTH="100%">
                <thead>
                <tr>
                    <th coltype="sno">序号</th>
                    <th coltype="text" name="transCode" filter="true">订单号</th>
                    <!--<th coltype="code" width="100px" filter="true" name="transType" code2name="TRANSTYPE_JSON">交易类型</th>-->
                    <th coltype="text" name="refundCode" filter="true" onmousemove="this.title('test')">退款编码</th>
                    <th coltype="text" name="paidSeq" filter="true">支付流水号</th>
                    <th coltype="money" name="paidAmount">支付金额</th>
                    <th coltype="code" width="100px" filter="true" name="amountType" code2name="AMOUNTTYPE_JSON">金额类型</th>
                    <th coltype="code" width="100px" filter="true" name="paidType" code2name="PAIDTYPE_JSON">支付方式</th>
                    <th coltype="datetime" name="paidTime">支付日期</th>
                    <th coltype="text" name="remark" filter="true" width="300px">备注</th>
                    <th coltype="action" width="15%">操作</th>
                </tr>
                </thead>
                <tbody>
                </tbody>
                <tr>
                    <td></td>
                    <td style="border-left:none"></td>
                    <td style="border-left:none"></td>
                    <td class="text" style="background-color: rgb(245, 255, 250);">当前页合计:</td>
                    <td class="money" id="currentPaid" name="currentPaid"></td>
                    <td></td>
                    <td style="border-left:none"></td>
                    <td style="border-left:none"></td>
                    <td style="border-left:none"></td>
                    <td style="border-left:none"></td>
                </tr>
                <tr>
                    <td style="border-top:none"></td>
                    <td style="border-top:none;border-left:none"></td>
                    <td style="border-top:none;border-left:none"></td>
                    <td class="text" style="background-color: rgb(245, 255, 250);">总合计:</td>
                    <td class="money" id="totalPaid" name="totalPaid"></td>
                    <td style="border-top:none"></td>
                    <td style="border-top:none;border-left:none"></td>
                    <td style="border-top:none;border-left:none"></td>
                    <td style="border-top:none;border-left:none"></td>
                    <td style="border-top:none;border-left:none"></td>
                </tr>
            </table>
        </div>
        </td></tr>
        </table>
    </form>
      <%--页面添加导出按钮 modify by renyi on 2016/8/10 start--%>
    <input type="hidden" id="printUrl" value="/excel/async/print/start/faster">
    <msk2:button buttonValue="支付一览数据导出" buttonId="SO153151.EXPORT" buttonType="button" align="left"/>
    <%--页面添加导出按钮 modify by renyi on 2016/8/10 end --%>
</div>
<script src="${ctx}/static/so/js/SO153151.js"></script>
<script type="text/javascript" src="${ctx}/static/js/core/utils.js"></script>
<script type="text/javascript" src="${ctx}/static/js/loading/download.js"></script>
<%-- modify for bug#2863 by li_huiqian on 2016/9/26 start --%>
<!--<style type="text/css">
    table { table-layout: fixed; }
    td {overflow:hidden;text-overflow:ellipsis;}
</style>-->
<%-- modify for bug#2863 by li_huiqian on 2016/9/26 end --%>

