<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/comm/taglib.jsp" %>
<navigation:header title="产品批次明细打印" backTitleArray="产品批次管理" backUrlArray="${ctx}/SC182203/init"></navigation:header>
<div class="page-content list-page">
    <form:form action="${ctx}/SC182205/save" id="SC182205Form" method="post">
    <div class="group-accordion" collapsible="true" active="true" >
        <h3>
            <label>产品批次明细</label>
        </h3>

        <div id ="my_show">
            <table class="dataTable no-footer" id="SC182204ProductTable" style="width: 100%">
                <tr>
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
                <tr>
                    <td>&nbsp;${classesName}</td>
                    <td>&nbsp;${breedName}</td>
                    <td>&nbsp;${machiningName}</td>
                    <td>&nbsp;${featureName}</td>
                    <td>&nbsp;${weightName}</td>
                    <td>&nbsp;${gradeName}</td>
                    <input type="hidden" name="classesName" id="classesName" value="${classesName}"/>
                    <input type="hidden" name="breedName" id="breedName" value="${breedName}"/>
                    <input type="hidden" name="machiningName" id="machiningName" value="${machiningName}"/>
                    <input type="hidden" name="featureName" id="featureName" value="${featureName}"/>
                    <input type="hidden" name="weightName" id="weightName" value="${weightName}"/>
                    <input type="hidden" name="gradeName" id="gradeName" value="${gradeName}"/>
                    <input type="hidden" name="sumNewActNum" id="sumNewActNum" value="${sc182205Bean.sumNewActNum}"/>
                    <input type="hidden" name="brandLogo" id="brandLogo" value="${sc182205Bean.brandLogo}"/>
                    <input type="hidden" name="proLotNum" id="proLotNum" value="${sc182205Bean.proLotNum}"/>
                    <input type="hidden" name="readProLotNum" id="readProLotNum" value="${sc182205Bean.readProLotNum}"/>
                    <td style="text-align: right">&nbsp;<fmt:formatNumber value="${sc182205Bean.sumNewActNum}" pattern="#,##0.00"/> </td>
                    <td>&nbsp;${sc182205Bean.brandLogo}</td>
                    <td>&nbsp;${sc182205Bean.proLotNum}</td>
                    <td>&nbsp;${sc182205Bean.readProLotNum}</td>
                    <td>&nbsp;<input type="text" name="productPrintNum" id="productPrintNum" value="${sc182205Bean.productPrintNum}"/></td>
                </tr>
            </table>

          <td align="right"><msk:button buttonValue="打印产品批次明细" name="SC182205.SAVE"  buttonId="SC182205.SAVE" buttonType="button" onclick="" /></td>
            <td>提示:打印页码必须以","或"-"形式出现：如1,3或1-20</td>
      </div>

  </div>
  </form:form>

</div>

<script type="text/javascript" src="${ctx}/static/ds/js/SC182205.js"></script>

