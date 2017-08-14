<%--
    Title:买家标准产品池列表
    author:zhao_chen1
    createDate:2016-06-06
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<%@ taglib prefix="codemaster" uri="/codemaster" %>
<%@ taglib prefix="msk2" uri="/codemaster" %>
<codemaster:codemaster codeType="FileStatus" viewType="json"/>
<msk2:codemaster codeType="MarketingsStatus" viewType="json"/>
<navigation:header title="分销买家池买家报表" backTitleArray="" backUrlArray=""></navigation:header>
<div class="page-content list-page">
    <div class="group-accordion" collapsible="true" active="true">
        <h3>
            <label>生成报表</label>
        </h3>
        <form action="${ctx}/BR121405/generateBuyerPoolFileInfo/${buyerId}" id="createFileForm" method="post">
            <input type="hidden" id="fileServerIp" value="${fileServerIp}">
            <table width="100%">
                <tr>
                    <td align="right" width="12.5%">报表时间 : </td>
                    <td width="6%">
                        <input type="text" id="fileStartTime" name="filterMap['fileStartTime']" readonly="readonly"/>
                    </td>
                    <td align="right" width="12.5%"></td>
                    <td width="6%">
                    </td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                <tr>
                    <td align="right" width="12.5%">物流区 : </td>
                    <td width="6%">
                        <select name="filterMap['lgcsAreaCode']" id="lgcsAreaCode">
                            <option value="">--请选择--</option>
                            <c:forEach items="${logisticsAreaList}" var="logisticsArea">
                                <option value="${logisticsArea.lgcsAreaCode}">${logisticsArea.lgcsAreaName}</option>
                            </c:forEach>
                        </select>
                        <input type="hidden" name="filterMap['lgcsAreaName']" id="lgcsAreaName" value=""/>
                    </td>
                    <td align="right" width="12.5%">地区(城市) : </td>
                    <td width="6%">
                        <select name="filterMap['cityCode']" id="cityCode">
                            <option value="">--请选择--</option>
                        </select>
                        <input type="hidden" name="filterMap['cityName']" id="cityName" value=""/>
                    </td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                <tr>
                    <td align="right" width="12.5%">产品一级分类 : </td>
                    <td>
                        <select name="filterMap['classesCode']" id="classesCode">
                            <option value="">--请选择--</option>
                            <c:forEach items="${pdClassesList}" var="pdClass">
                                <option value="${pdClass.classesCode}">${pdClass.classesName}</option>
                            </c:forEach>
                        </select>
                        <input type="hidden" name="filterMap['classesName']" id="classesName" value=""/>
                    </td>
                    <td align="right" width="12.5%">产品二级分类 : </td>
                    <td>
                        <select name="filterMap['machiningCode']" id="machiningCode">
                            <option value="">--请选择--</option>
                        </select>
                        <input type="hidden" name="filterMap['machiningName']" id="machiningName" value=""/>
                    </td>
                </tr>
                <tr>
                    <td align="right" width="12.5%">买家类型 : </td>
                    <td>
                        <select disabled>
                            <c:forEach items="${buyerType}" var="buyerType">
                                <option value="${buyerType.key}">${buyerType.value}</option>
                            </c:forEach>
                        </select>
                        <input type="hidden" value="01" name="filterMap['buyerType']"/>
                    </td>
                    <td></td>
                    <td></td>
                </tr>
                <tr>
                    <td align="right" width="12.5%">买家上线状态一级分类 : </td>
                    <td>
                        <select name="filterMap['marketingsStatusCla']" id="marketingsStatusCla">
                            <option value="">--请选择--</option>
                            <option value="1">营销期</option>
                            <option value="2">销售期</option>
                            <option value="3">异常</option>
                        </select>
                        <input type="hidden" name="filterMap['marketingsPeriodName']" id="marketingsPeriodName" value=""/>
                    </td>
                    <td align="right">买家上线状态二级分类 : </td>
                    <td>
                        <select name="filterMap['marketingsStatus']" id="marketingsStatus">
                            <option value="">--请选择--</option>
                        </select>
                    </td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                <tr>
                    <td align="right">批发市场等级 : </td>
                    <td align="left">
                        <select name="filterMap['marketLevel']" id="marketLevel">
                            <option value="">--请选择--</option>
                            <c:forEach items="${marketLevel}" var="marketLevel">
                                <option value="${marketLevel.key}">${marketLevel.value}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td width="12.5%" align="right">单个批发市场 : </td>
                    <td width="6%" align="left">
                        <select id="marketId" name="filterMap['marketId']">
                            <option value="">--请选择--</option>
                        </select>
                        <input type="hidden" name="filterMap['marketName']" value="" id="marketName"/>
                    </td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                <tr></tr>
                <tr></tr>
                <tr>
                    <td align="right"><msk:button buttonValue="生成" buttonId="BR121405.CREATE"
                                                  buttonType="button"/></td>
                </tr>
            </table>
        </form>
    </div>
    <div>
        <form action="${ctx}/BR121405/search" id="BR121405Form" method="post">
            <div>
                <table width="100%" id="BR121405_list_grid">
                    <thead>
                    <tr>
                        <th coltype="sno" align="center">编号</th>
                        <th coltype="text" name="fileName" align="center" filter="true">文件名称</th>
                        <th coltype="text" name="marketName" align="center" filter="true">批发市场名称</th>
                        <th coltype="text" filter="true" name="marketingsPeriodName" width="10%">买家上线状态期名称</th>
                        <th coltype="code" name="marketingsStatus" filter="false" code2name="MARKETINGSSTATUS_JSON" width="15%">买家上线状态</th>
                        <th coltype="code" name="fileStatus" code2name="FILESTATUS_JSON">文件状态</th>
                        <th coltype="text" name="fileCreateTimeStr">文件生成时间</th>
                        <th coltype="action" align="center">操作

                        </th>
                    </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
            </div>
        </form>
    </div>
</div>

<script src="${ctx}/static/util/js/download.js"></script>
<script src="${ctx}/static/br/js/BR121405.js"></script>
