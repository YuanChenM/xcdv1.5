`<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/comm/taglib.jsp" %>
<navigation:header title="产品批次明细生成" backTitleArray="" backUrlArray=""></navigation:header>
<div class="page-content list-page">
    <form:form action="${ctx}/SC182204/save" id="SC182204Form" method="post">
    <div class="group-accordion" collapsible="" active="false">
        <h3>
            <label>查询条件</label>
        </h3>
        <div>
            <table width="100%">
                    <tbody>
                    <tr>
                        <td width="10%" align="right">半旬期</td>
                        <td align="left">
                            <input type="text" name="halfPeriod" value="${halfPeriod}" readonly="true" style="background:#DBDBDB" />
                        </td>
                        <td width="10%" align="right">销售区域</td>
                        <td align="left">
                            <select name="lgcsCode" id="lgcsCode">
                                <c:forEach items="${sc182204Bean.lgcsAreaList}" var="areaInfo">
                                    <c:choose>
                                        <c:when test="${sc182204Bean.lgcsCode eq areaInfo.lgcsCode}">
                                            <option value="${areaInfo.lgcsCode}" selected>${areaInfo.lgcsName}</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${areaInfo.lgcsCode}">${areaInfo.lgcsName}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>
                        </td>
                        <td width="8%" align="right">供应商</td>
                        <td align="left">
                            <select name="suppCode" id="suppCode" style="min-width: 50px">
                                <c:forEach items="${sc182204Bean.supplyList}" var="suppInfo">
                                    <c:choose>
                                        <c:when test="${sc182204Bean.suppCode eq suppInfo.suppCode}">
                                            <option value="${suppInfo.suppCode}" selected>${suppInfo.suppName}</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${suppInfo.suppCode}">${suppInfo.suppName}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>
                        </td>
                        <td width="8%" align="right">&nbsp;&nbsp;</td>
                        <td align="left"><msk:button buttonValue="查询" buttonId="SC182204.SEARCH" buttonType="button"/></td>
                    </tr>
                    </tbody>
            </table>
        </div>
    </div>
    <div class="group-accordion" collapsible="true" active="true" >
        <h3>
            <label>产品批次明细</label>
        </h3>

        <div>
            <table class="dataTable no-footer" id="SC182204ProductTable" style="width: 100%">
                <tr>
                    <td width="55" align="center" bgcolor="#CCCCCC" style="font-weight:bold">选择</td>
                    <td width="85" align="center" bgcolor="#CCCCCC" style="font-weight:bold">产品类别</td>
                    <td width="85" align="center" bgcolor="#CCCCCC" style="font-weight:bold">产品名称</td>
                    <td width="85" align="center" bgcolor="#CCCCCC" style="font-weight:bold">配料</td>
                    <td width="85" align="center" bgcolor="#CCCCCC" style="font-weight:bold">规格</td>
                    <td width="85" align="center" bgcolor="#CCCCCC" style="font-weight:bold">净重</td>
                    <td width="85" align="center" bgcolor="#CCCCCC" style="font-weight:bold">产品等级</td>
                    <td width="85" align="center" bgcolor="#CCCCCC" style="font-weight:bold">总箱数</td>
                    <td width="85" align="center" bgcolor="#CCCCCC" style="font-weight:bold">品牌Logo</td>
                    <td width="200" align="center" bgcolor="#CCCCCC" style="font-weight:bold">操作码</td>
                    <td width="200" align="center" bgcolor="#CCCCCC" style="font-weight:bold">阅读码</td>
                    <td width="85" align="center" bgcolor="#CCCCCC" style="font-weight:bold">打印页码</td>
                </tr>
                <c:if test="${ fn:length(stockActualList)== 0}">
                <tr>
                    <td width="100%" align="center" colspan="12"> 对不起，没有查询到数据。</td>
                </tr>
                </c:if>
                <c:if test="${fn:length(stockActualList) > 0}">
                <c:forEach items="${stockActualList}" var="stockActual" varStatus="i">
                <tr>
                    <td height="30" align="center"><input type="checkbox" name="productParamList[${i.index}].checkbox" id="checkbox" value="0"/>&nbsp;</td>
                    <td>&nbsp;${stockActual.productBean.classesName}</td>
                    <td>&nbsp;${stockActual.productBean.breedName}</td>
                    <td>&nbsp;${stockActual.productBean.machiningName}</td>
                    <td>&nbsp;${stockActual.productBean.featureName}</td>
                    <td>&nbsp;${stockActual.productBean.weightName}</td>
                    <td>&nbsp;${stockActual.productBean.gradeName}</td>
                    <input type="hidden" name="productParamList[${i.index}].classesCode" id="classesCode" value="${stockActual.productBean.classesCode}"/>
                    <input type="hidden" name="productParamList[${i.index}].machiningCode" id="machiningCode" value="${stockActual.productBean.machiningCode}"/>
                    <input type="hidden" name="productParamList[${i.index}].breedCode" id="breedCode" value="${stockActual.productBean.breedCode}"/>
                    <input type="hidden" name="productParamList[${i.index}].featureCode" id="featureCode" value="${stockActual.productBean.featureCode}"/>
                    <input type="hidden" name="productParamList[${i.index}].weightCode" id="weightCode" value="${stockActual.productBean.weightCode}"/>
                    <input type="hidden" name="productParamList[${i.index}].gradeCode" id="gradeCode" value="${stockActual.productBean.gradeCode}"/>
                    <input type="hidden" name="productParamList[${i.index}].sumNewActNum" id="sumNewActNum" value="${stockActual.sumNewActNum}"/>
                    <input type="hidden" name="productParamList[${i.index}].proLotNum" id="proLotNum" value="${stockActual.proLotNum}"/>
                    <input type="hidden" name="productParamList[${i.index}].readProLotNum" id="readProLotNum" value="${stockActual.readProLotNum}"/>
                    <input type="hidden" name="productParamList[${i.index}].productPrintNum" id="productPrintNum" value="${stockActual.productPrintNum}"/>
                    <input type="hidden" name="productParamList[${i.index}].lgcsCode" id="lgcs" value="${stockActual.lgcsCode}"/>
                    <input type="hidden" name="productParamList[${i.index}].suppCode" id="supp" value="${stockActual.suppCode}"/>
                    <td style="text-align: right">&nbsp;<fmt:formatNumber value="${stockActual.sumNewActNum}" pattern="#,##0.00"/> </td>
                    <td>&nbsp;</td>
                    <td>&nbsp;${stockActual.proLotNum}</td>
                    <td>&nbsp;${stockActual.readProLotNum}</td>
                    <td>&nbsp;${stockActual.productPrintNum}</td>
                </tr>
                </c:forEach>
                </c:if>
                </table>

            <c:if test="${stockActualList!= null && fn:length(stockActualList) > 0}">
          <td align="right"><msk:button buttonValue="标签数据产生" name="SC182204.SAVE"  buttonId="SC182204.SAVE" buttonType="button"/></td>
            </c:if>
      </div>
  </div>
  </form:form>
</div>
<script type="text/javascript" src="${ctx}/static/ds/js/SC182204.js"></script>

