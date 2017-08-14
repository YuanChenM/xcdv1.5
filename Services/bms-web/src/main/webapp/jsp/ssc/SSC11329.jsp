<%--
  Created by IntelliJ IDEA.
  Date: 2016/8/3
  Time: 9:54
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="navigation" uri="/msk" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<msk:codemaster codeType="SaleStatus" viewType="json" modelName="Seller"/>
<msk:codemaster codeType="SlTncGradeCode" viewType="json" modelName="Seller"/>
<navigation:header title="企业产品信息" backTitleArray="企业信息列表" backUrlArray="../SSC11327/init"></navigation:header>
<div class="page-content list-page">
  <form action="<c:url value="/SSC11329/search"/>" method="post">
    <input type="hidden" id="slCode" name="slCode" value="${ssc11329Param.slCode}" />
    <div class="group-accordion" collapsible="true" active="true">
      <h3>
        <label>查询条件</label>
      </h3>
      <table WIDTH="100%">
        <tr>
          <td  align="right" >产品货号 :</td>
          <td  align="left">　
            <input type="text" id="slPdArtNo" name="filterMap['slPdArtNo']" value="${param.filterMap.slPdArtNo}"/>
          </td>
          <td  align="right" >生产商 :</td>
          <td  align="left">　
            <input type="text" id="prodEpName" name="filterMap['prodEpName']" value="${param.filterMap.prodEpName}"/>
          </td>
          <td  align="right" >产品一级分类 :</td>
          <td  align="left">　
            <input type="text" id="pdClassesName" name="filterMap['pdClassesName']" value="${param.filterMap.pdClassesName}"/>
          </td>
          <td  align="right" >产品二级分类 :</td>
          <td  align="left">　
            <input type="text" id="machiningName" name="filterMap['machiningName']" value="${param.filterMap.machiningName}"/>
          </td>
        </tr>
        <tr>
          <td  align="right" >产品品种 :</td>
          <td  align="left">　
            <input type="text" id="pdBreedName" name="filterMap['pdBreedName']" value="${param.filterMap.pdBreedName}"/>
          </td>
          <td  align="right" >产品特征 :</td>
          <td  align="left">　
            <input type="text" id="pdFeatureName" name="filterMap['pdFeatureName']" value="${param.filterMap.pdFeatureName}"/>
          </td>
          <td  align="right" >包装净重 :</td>
          <td  align="left">　
            <input type="text" id="weightName" name="filterMap['weightName']" value="${param.filterMap.weightName}"/>
          </td>
          <td  align="right" >产品等级 :</td>
          <td  align="left">　
            <msk:codemaster codeType="SlTncGradeCode" modelName="Seller" id="slTncGradeCode" name='filterMap["slTncGradeCode"]' viewType="select"/>
          </td>
        </tr>
        <tr>
        <td  align="right" >产品状态 :</td>
        <td  align="left">　
          <msk:codemaster codeType="SaleStatus" modelName="Seller" id="status" name='filterMap["status"]' viewType="select"/>
        </td>
        <tr>
          <td colspan="8" align="right"><msk:button buttonValue="查询" buttonId="SSC11329.SEARCH" buttonType="button"/></td>
        </tr>
      </table>
    </div>
    <div class="group-accordion" collapsible="true" active="true">
      <h3>
        <label>企业产品信息列表</label>
      </h3>
      <table width="100%" id="SSC11329_list_grid">
        <thead>
        <tr>
          <th coltype="sno">序号</th>
          <th coltype="text" width="10%" name="slPdArtNo">产品货号</th>
          <th coltype="text" width="12%" name="prodEpName">生产商</th>
          <th coltype="text" width="12%" name="brandName">品牌</th>
          <th coltype="text" width="5%" name="pdClassesName">产品一级分类</th>
          <th coltype="text" width="5%" name="machiningName"  >产品二级分类</th>
          <th coltype="text" width="5%" name="pdBreedName">产品品种</th>
          <th coltype="text" name="pdFeatureName" width="8%">产品特征</th>
          <th coltype="text" width="8%" name="weightName" >包装净重</th>
          <th coltype="code" width="8%" code2name="SLTNCGRADECODE_JSON" name="slTncGradeCode" >产品等级</th>
          <th coltype="code" width="8%" code2name="SALESTATUS_JSON"  name="status">产品状态
          </th>
          <th coltype="action" width="10%">产品品种图片
            <msk:button buttonValue="产品品种图片" buttonId="SSC11329.SEARCHBTN" buttonType="hidden"
                        coltype="search"/>
          </th >
          <th coltype="action" width="10%">产品标准
            <msk:button buttonValue="产品标准" buttonId="SSC11329.AUDITBTN" buttonType="hidden"
                        coltype="audit"/>
          </th>
        </tr>
        </thead>
        <tbody></tbody>
      </table>
    </div>
  </form>
  </div>
<script src="<c:url value="/static/js/ssc/SSC11329.js"/>"></script>