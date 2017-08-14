<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<%@ taglib prefix="msk" uri="/msk" %>
<input type="hidden" id="printUrl" value="/excel/async/print/start/faster">
<div class="page-content list-page">
    <form action="<c:url value="/SO152502/search/"/>" id="SO152502Form" method="post">
        <div class="group-accordion" collapsible="true" active="true">
            <h3>
                <label>查询条件</label>
            </h3>

            <div>
                <table>
                    <tr>
                        <td width="15%" align="right">物流区编码</td>
                        <td><input type="text" name="filterMap['lgcsCode']" id="lgcsCode"
                                   value="${param.filterMap.lgcsCode}" maxlength="20"/></td>
                        <td coltype="text" width="15%" align="right">仓库编码</td>
                        <td><input type="text" name="filterMap['warehouseCode']" id="warehouseCode"
                                   value="${param.filterMap.warehouseCode}" maxlength="20"/>
                        <input type="hidden" name="filterMap['salePlatform']" id="salePlatform" />
                        </td>

                        <%--<td coltype="text" width="15%" align="right">销售平台</td>--%>
                        <%--<td>--%>
                            <%--<select width="25px" name="filterMap['salePlatform']"  id="salePlatform">--%>
                                <%--<option value="" >--请选择--</option>--%>

                                    <%--<option value="1">1</option>--%>

                            <%--</select>--%>
                        <%--</td>--%>

                        <td coltype="text" width="15%" align="right">卖家编码</td>
                        <td><input type="text" name="filterMap['slCodeDis']" id="slCodeDis"
                                   value="${param.filterMap.slCodeDis}" maxlength="20"/></td>
                        <td width="15%" align="right">产品编码</td>
                        <td><input type="text" align="right" name="filterMap['pdCode']" id="pdCode"
                                   value="${param.filterMap.pdCode}" maxlength="10"/></td>
                        <td></td>

                    </tr>
                    <tr>
                        <td width="15%" align="right">物流区名称</td>
                        <td><input type="text" name="filterMap['lgcsName']" id="lgcsName"
                                   value="${param.filterMap.lgcsName}" maxlength="100"/></td>
                        <td coltype="text" width="15%" align="right">仓库名称</td>


                        <td><input type="text" name="filterMap['warehouseName']" id="warehouseName"
                                   value="${param.filterMap.warehouseName}" maxlength="100"/></td>
                        <td coltype="text" width="15%" align="right">卖家名称</td>
                        <td><input type="text" name="filterMap['slName']" id="slName"
                                   value="${param.filterMap.slName}" maxlength="100"/></td>
                        <td width="15%" align="right">产品名称</td>
                        <td><input type="text" align="right" name="filterMap['pdName']" id="pdName"
                                   value="${param.filterMap.pdName}" maxlength="100"/></td>
                        <td width="15%"></td>
                        <td align="left"><msk:button buttonValue="查询" buttonId="SO152502.SEARCH" buttonType="button"/>
                        <td></td>
                    </tr>
                </table>
            </div>
        </div>
        <div>
            <table id="SO152502_list_grid">
                <thead>
                <tr>
                    <th coltype="text" width="40px" name="lgcsCode">物流区编码</th>
                    <th coltype="text" width="40px" name="lgcsName">物流区名称</th>
                    <th coltype="text" width="7%" name="warehouseCode">仓库编码</th>
                    <th coltype="text" width="10%" name="warehouseName">仓库名称</th>
                    <th coltype="text" width="10%"  name="salePlatform" >供货平台</th>
                    <th coltype="text" width="40px" name="slCodeDis">卖家编码</th>
                    <th coltype="text" width="10%" name="slName">卖家名称</th>
                    <th coltype="text" width="10%" name="pdCode">产品编码</th>
                    <th coltype="text" width="30px" name="pdName">产品名称</th>
                    <th coltype="text" width="30px" name="enabledStockQty">可用库存</th>
                    <th coltype="text" width="30px"  name="stockQty">库存数量</th>
                </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
        </div>
    </form>
    <msk:button buttonValue="分销库存数据导出" buttonId="SO152501.EXPORTORDER" buttonType="button" align="left"/>
</div>

<script src='<c:url value="/static/js/core/utils.js"/>'/>
<script src='<c:url value="/static/js/loading/download.js"/>'/>
<script src='<c:url value="/static/js/inventory/SO152502.js" />'></script>