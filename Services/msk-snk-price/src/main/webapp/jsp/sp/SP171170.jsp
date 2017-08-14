<%-- 
    Title:价盘详情报表

--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>

<navigation:header title="价盘详情报表" backTitleArray="" backUrlArray=""></navigation:header>
<script type="text/javascript">
    function callBack(message){
        $.alertMessage.info(message);
        HDF.closeLoadingMask();
    }

</script>
<div class="page-content list-page">
<form action="${ctx}/SP171170/search"  method="post" id="SP171170Form" >
    <input type="hidden" name="pricecyclePeriod" id="pricecyclePeriod" >
    <input type="hidden" id="flag" name="flag" value="${flag}">
    <input type="hidden" id="hidpdMachining" name="flag" value="${hidpdMachining}">
    <input type="hidden" id="printUrl" value="/excel/async/print/start/faster">
    <div class="group-accordion" collapsible="false" active="false" >
        <h3>
            <label>价盘详情</label>
        </h3>
            <table width="100%">
                <tr>
                    <td width="80px" align="right">物流区</td>
                    <td align="left">
                        <select path="logiareaCode" style="width:80px" id="logiareaCode" name="lgcsAreaCode">
                            <option value="">请选择</option>
                            <c:forEach items="${logisticsAreas}" var="lgcs" varStatus="status">
                                <option value="${lgcs.lgcsAreaCode}" <c:if test="${lgcs.lgcsAreaCode eq logcAreaCode}">selected</c:if>>${lgcs.lgcsAreaName}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td width="80px" align="right">产品一级分类</td>
                    <td align="left">
                        <select path="classesCode" style="width:120px" id="pdClasses" name="classesCode">
                            <option value="">请选择</option>
                            <c:forEach items="${pdClasses}" var="pdClass" varStatus="status">
                                <option value="${pdClass.classesCode}" <c:if test="${pdClass.classesCode eq classesCode}">selected</c:if>>${pdClass.classesName}</option>
                            </c:forEach>
                        </select>

                    </td>
                    <td width="80px" align="right">产品二级分类</td>
                    <td align="left">
                        <select path="machiningCode" style="width:80px" id="pdMachining" name="machiningCode" value="${machiningCode}">
                            <option value="">请选择</option>
                        </select>
                    </td>
                    <td width="80px" align="right">品种</td>
                    <td align="left">
                        <input type="text"  style="width:80px" id="breedName" name="breedName" value="${breedName}"/>
                    </td>
                    <td width="80px" align="right">价盘周期</td>
                    <td align="left">
                        <input path="pricecycleDate" style="width:40px" id="priceDate" name="priceDate" value="${priceDate}"/>
                        <select path="pricecycle" style="width:90px" id="pricecycle" name="pricecycle">
                            <option value="">请选择</option>
                            <option value="1" <c:if test="${pricecycle eq '1'}">selected</c:if>>1-5日</option>
                            <option value="2" <c:if test="${pricecycle eq '2'}">selected</c:if>>6-10日</option>
                            <option value="3" <c:if test="${pricecycle eq '3'}">selected</c:if>>11-15日</option>
                            <option value="4" <c:if test="${pricecycle eq '4'}">selected</c:if>>16-20日</option>
                            <option value="5" <c:if test="${pricecycle eq '5'}">selected</c:if>>21-25日</option>
                            <option value="6"  <c:if test="${pricecycle eq '6'}">selected</c:if>>26-月底</option>
                        </select>
                    </td>
                    <td><msk:button buttonValue="查询" buttonType="button" buttonId="SP171170.SEARCH"/></td>
                    <td><msk:button buttonValue="编辑" buttonType="button" buttonId="SP171170.UPDATE"/></td>
                </tr>
            </table>
    </div>
    <div style="width: 100%;overflow: scroll;">
        <table id="SP171170_list_grid" width="100%">
            <thead>
            <tr>
                <th coltype="text"  name="logiareaName" rowspan="3"  style="white-space : normal;">区域</th>
                <th coltype="text"   name="pdCode" rowspan="3" style="white-space : normal;">产品编码</th>
                <th coltype="text"    name="classesName" rowspan="3" style="white-space : normal;">产品一级分类</th>
                <th coltype="text"   name="machiningName" rowspan="3" style="white-space : normal;">产品二级分类</th>
                <th coltype="text"    name="breedName" rowspan="3" style="white-space : normal;">品种</th>
                <th coltype="text"   name="featureName" rowspan="3" style="white-space : normal;">特征</th>
                <th coltype="text"   name="weightName" rowspan="3" style="white-space : normal;">净重（KG/箱）</th>
                <th coltype="text"   name="gradeName" rowspan="3" style="white-space : normal;">等级</th>
                <th coltype="text"   name="wayGradeName" rowspan="3" style="white-space : normal;">需求等级</th>
                <th coltype="text"   name="marketingName" rowspan="3" style="white-space : normal;">营销状态</th>
                <th coltype="text"   name="pricecyclePeriod" rowspan="3" style="white-space : normal;">价盘周期</th>
                <th  colspan="20"  >营销通道</th>
            </tr>
            <tr>
                <th  colspan="2" >大宗超级</th>
                <th  colspan="2" >大宗1级</th>
                <th  colspan="2" >大宗2级</th>
                <th  colspan="2" >大宗3级</th>
                <th  colspan="2" >大额4级</th>
                <th  colspan="2" >大额5级</th>
                <th  colspan="2" >大额6级</th>
                <th  colspan="2" >标准7级</th>
                <th  colspan="2" >标准8级</th>
                <th  colspan="2" >标准9级</th>
            </tr>

            <c:choose>
                <c:when test="${flag eq 0}">
                    <tr id="priceEdit">
                        <th coltype="text" edit="true" id="supPriceofkg" name="supPriceofkg" style="min-width:60px;" >元/KG</th>
                        <th coltype="text" name="supPriceofbox" >元/箱</th>
                        <th coltype="text" edit="true" id="onePriceofkg" name="onePriceofkg"  style="min-width:60px;">元/KG</th>
                        <th coltype="text" name="onepriceofbox" >元/箱</th>
                        <th coltype="text" edit="true" id="twoPriceofkg" name="twoPriceofkg"  style="min-width:60px;">元/KG</th>
                        <th coltype="text" name="twoPriceofbox" >元/箱</th>
                        <th coltype="text" edit="true" id="threePriceofkg" name="threePriceofkg"  style="min-width:60px;">元/KG</th>
                        <th coltype="text" name="threepriceofbox" >元/箱</th>
                        <th coltype="text" edit="true" id="fourPriceofkg" name="fourPriceofkg"  style="min-width:60px;">元/KG</th>
                        <th coltype="text" name="fourPriceofbox" >元/箱</th>
                        <th coltype="text" edit="true" id="fivePriceofkg" name="fivePriceofkg"  style="min-width:60px;">元/KG</th>
                        <th coltype="text" name="fivepriceofbox" >元/箱</th>
                        <th coltype="text" edit="true" id="sixPriceofkg" name="sixPriceofkg"  style="min-width:60px;">元/KG</th>
                        <th coltype="text" name="sixPriceofbox" >元/箱</th>
                        <th coltype="text" edit="true" id="sevenPriceofkg" name="sevenPriceofkg"  style="min-width:60px;">元/KG</th>
                        <th coltype="text" name="sevenpriceofbox" >元/箱</th>
                        <th coltype="text" edit="true" id="eightPriceofkg" name="eightPriceofkg"  style="min-width:60px;">元/KG</th>
                        <th coltype="text" name="eightPriceofbox" >元/箱</th>
                        <th coltype="text" edit="true" id="ninePriceofkg" name="ninePriceofkg"  style="min-width:60px;">元/KG</th>
                        <th coltype="text" name="ninepriceofbox" >元/箱</th>
                    </tr>
                </c:when>
                <c:otherwise>
                    <tr id="priceEdit">
                        <th coltype="text"  id="supPriceofkg" name="supPriceofkg" >元/KG</th>
                        <th coltype="text" name="supPriceofbox" >元/箱</th>
                        <th coltype="text"  id="onePriceofkg" name="onePriceofkg">元/KG</th>
                        <th coltype="text" name="onepriceofbox">元/箱</th>
                        <th coltype="text" id="twoPriceofkg" name="twoPriceofkg">元/KG</th>
                        <th coltype="text" name="twoPriceofbox">元/箱</th>
                        <th coltype="text" id="threePriceofkg" name="threePriceofkg">元/KG</th>
                        <th coltype="text" name="threepriceofbox">元/箱</th>
                        <th coltype="text"  id="fourPriceofkg" name="fourPriceofkg">元/KG</th>
                        <th coltype="text" name="fourPriceofbox">元/箱</th>
                        <th coltype="text"  id="fivePriceofkg" name="fivePriceofkg">元/KG</th>
                        <th coltype="text" name="fivepriceofbox">元/箱</th>
                        <th coltype="text"  id="sixPriceofkg" name="sixPriceofkg">元/KG</th>
                        <th coltype="text" name="sixPriceofbox">元/箱</th>
                        <th coltype="text"  id="sevenPriceofkg" name="sevenPriceofkg">元/KG</th>
                        <th coltype="text" name="sevenpriceofbox">元/箱</th>
                        <th coltype="text"  id="eightPriceofkg" name="eightPriceofkg">元/KG</th>
                        <th coltype="text" name="eightPriceofbox">元/箱</th>
                        <th coltype="text"  id="ninePriceofkg" name="ninePriceofkg">元/KG</th>
                        <th coltype="text" name="ninepriceofbox">元/箱</th>
                    </tr>
                </c:otherwise>
            </c:choose>

            </thead>
            <tbody>
            </tbody>
        </table>
        </div>
</form>
   <div>
        <table>
            <tr>
                <td><msk:button buttonValue="保存" buttonType="button" buttonId="SP171170.SAVE"/></td>
                <td><msk:button buttonValue="价盘导出" buttonType="button" buttonId="SP171170.EXPORT"/></td>
                <td><msk:button buttonValue="价盘导入模板下载" buttonType="button" buttonId="SP171170.DOWNLOADMODEL"/></td>
                <td>
                    <form action="${ctx}/SP171171/upload" id="SP171170UploadForm"   method="post" enctype="multipart/form-data">
                            <table width="100%">
                                <tr>
                                    <td  align="left" colspan="6">　
                                        <input type="hidden" id="userId" value="${loginUser.emplId}"/>
                                        <input type="file" name="file" id="importFile" style="width:180px"/>
                                        <msk:button buttonValue="上传" buttonId="SP171170.UPLOAD" buttonType="button"/>
                                    </td>
                                </tr>
                            </table>
                    </form>
                </td>
            </tr>
        </table>
    </div>
</div>

<script src='<c:url value="/static/upload/upload2.js"/>'/>
<script src='<c:url value="/static/js/core/utils.js"/>'/>
<script src='<c:url value="/static/js/loading/download.js"/>'/>

<script src="${ctx}/static/sp/js/SP171170.js"></script>
