<%@ taglib prefix="navigation" uri="/msk" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<div class="page-content list-page" style="position:relative; height:auto; overflow:auto">
  <!--加工质量标准指标 START-->
  <div class="group-accordion"  active="true" collapsible="true">
    <h3>
      <label>加工质量标准指标</label>
    </h3>
    <div width="100%" style="padding: 0px; border-right: none; border-bottom: none; border-top: none;">
        <table class="tree dataTable no-footer" style="width: 100%">
          <tr style="background:#DBDBDB">
            <td width="20%"></td>
            <td width="15%"> <c:if test="${slPdBean.slTncGradeCode eq 1}"><input type="radio" checked="checked"/></c:if>A1</td>
            <td width="15%"> <c:if test="${slPdBean.slTncGradeCode eq 2}"><input type="radio" checked="checked"/></c:if>A2</td>
            <td width="15%"> <c:if test="${slPdBean.slTncGradeCode eq 3}"><input type="radio" checked="checked"/></c:if>A3</td>
            <td width="15%">是否同意</td>
            <td>卖家产品加工质量标准</td>
            <td width="15%">备注</td>
          </tr>
          <c:forEach items="${slPdBean.slPdTncStdList}" var="tncBean" varStatus="i">
            <tr>
              <td>${tncBean.tncStdItemName}</td>
              <td style="white-space:pre;">${tncBean.tncStdVal1}</td>
              <td style="white-space:pre;">${tncBean.tncStdVal2}</td>
              <td style="white-space:pre;">${tncBean.tncStdVal3}</td>
              <td>
                <c:if test="${tncBean.agreeFlg eq 1}">同意</c:if>
                <c:if test="${tncBean.agreeFlg ne 1}">不同意</c:if>
              </td>
              <td style="white-space:pre;">${tncBean.stdVal}</td>
              <td style="white-space:pre;">${tncBean.remark}</td>
            </tr>
          </c:forEach>
        </table>
      </div>
  </div>
  <!--加工质量标准指标 END-->

  <!--加工技术标准指标 START-->
    <div class="group-accordion" active="true" collapsible="true">
      <h3>
        <label>加工技术标准指标</label>
      </h3>
      <div width="100%" style="padding: 0px; border-right: none; border-bottom: none; border-top: none;">
        <table class="tree dataTable no-footer" style="width: 100%">
          <tr style="background:#DBDBDB">
            <td width="20%"></td>
            <td width="15%"> <c:if test="${slPdBean.slQltGradeCode eq 2}"><input type="radio" checked="checked"/></c:if>合格值</td>
            <td width="15%"> <c:if test="${slPdBean.slQltGradeCode eq 3}"><input type="radio" checked="checked"/></c:if>不合格值</td>
            <td width="15%">是否同意</td>
            <td>卖家产品加工技术标准</td>
            <td width="15%">备注</td>
          </tr>
          <c:forEach items="${slPdBean.slPdMctStdList }" var="mctBean" varStatus="i">
          <tr>
            <td>${mctBean.mctStdItemName}</td>
            <td style="white-space:pre;">${mctBean.mctOkVal}</td>
            <td style="white-space:pre;">${mctBean.mctNgVal}</td>
            <td>
              <c:if test="${mctBean.agreeFlg ne 0}">同意</c:if>
              <c:if test="${mctBean.agreeFlg eq 0}">不同意</c:if>
            </td>
            <td style="white-space:pre;">${mctBean.stdVal}</td>
            <td style="white-space:pre;">${mctBean.remark}</td>
          </tr>
          </c:forEach>
          </table>
        </div>
    </div>
  <!--加工技术标准指标 END-->

  <!--包装标准指标 START-->
  <div class="group-accordion" collapsible="true" active="true">
    <h3>
      <label>包装标准指标</label>
    </h3>
    <div width="100%" style="padding: 0px; border-right: none; border-bottom: none; border-top: none;">
        <table class="tree dataTable no-footer" style="width: 100%">
          <tr style="background:#DBDBDB">
            <td rowspan="2">生产商</td>
            <td rowspan="2">品牌</td>
            <td rowspan="2">产品一级分类</td>
            <td rowspan="2">产品二级分类</td>
            <td rowspan="2">产品品种</td>
            <td rowspan="2">产品特征</td>
            <td rowspan="2">包装编码</td>
            <td colspan="5">内包装</td>
            <td colspan="4">外包装</td>
          </tr>
          <tr style="background:#DBDBDB">
            <td>单个产品规格净重</td>
            <td>单个产品规格净重误差范围</td>
            <td>内包装净重/个数</td>
            <td>内包装尺寸</td>
            <td>内包装材质及技术标准</td>
            <td>外包装规格</td>
            <td>外包装净重/毛重</td>
            <td>外包装尺寸</td>
            <td>外包装材质及技术标准</td>
          </tr>
          <c:forEach items="${slPdBean.slPdPkgList}" var="pkgBean" varStatus="i">
            <tr>
              <td>${slPdBean.prodEpName}</td>
              <td>${slPdBean.brandName}</td>
              <td>${slPdBean.pdClassesName}</td>
              <td>${slPdBean.machiningName}</td>
              <td>${slPdBean.pdBreedName}</td>
              <td>${slPdBean.pdFeatureName}</td>
              <td>${pkgBean.pkgCode}</td>
              <td>${pkgBean.inSglNw}</td>
              <td>${pkgBean.inSglNwRange}</td>
              <td>${pkgBean.inNumber}</td>
              <td>${pkgBean.inSize}</td>
              <td>${pkgBean.inMts}</td>
              <td>${pkgBean.outSpec}</td>
              <td>${pkgBean.outGw}</td>
              <td>${pkgBean.outSize}</td>
              <td>${pkgBean.outMts}</td>
            </tr>
          </c:forEach>
        </table>
      </div>
  </div>
  <!--包装标准指标 END-->

  <c:if test="${(slPdBean.pdClassesCode eq '01' || slPdBean.pdClassesCode eq '02') && slPdBean.machiningCode eq '1'}">
    <!--原种种源标准指标 START-->
    <div class="group-accordion" active="true" collapsible="true">
      <h3>
        <label>原种种源标准指标</label>
      </h3>
      <div width="100%" style="padding: 0px; border-right: none; border-bottom: none; border-top: none;">
          <table class="tree dataTable no-footer" style="width: 100%">
            <tr style="background: #DBDBDB;width:100%">
              <td width="20%" rowspan="2" align="center" height="">指标类容</td>
              <td colspan="6" align="center">等级</td>
            </tr>
            <tr style="background: #DBDBDB;width:100%">
              <td align="center" width="10"></td>
              <td align="center" width="25%">优良</td>
              <td align="center" width="10"></td>
              <td align="center" width="25%">一般</td>
              <td align="center" width="10"></td>
              <td align="center" width="25%">差</td>
            </tr>
            <c:forEach items="${slPdBean.slPdOrgStdList}" var="orgBean" varStatus="i">
              <tr>
                <td width="25%">${orgBean.orgStdItemName}</td>
                <td><input type="radio" <c:if test="${orgBean.agreeFlg eq '1'}">checked="checked"</c:if>/>同意</td>
                <td width="25%" style="white-space:pre;">${orgBean.orgGoodVal}</td>
                <td><input type="radio" <c:if test="${orgBean.agreeFlg eq '2'}">checked="checked"</c:if>/>同意</td>
                <td width="25%" style="white-space:pre;">${orgBean.orgNormalVal}</td>
                <td><input type="radio" <c:if test="${orgBean.agreeFlg eq '3'}">checked="checked"</c:if>/>同意</td>
                <td style="white-space:pre;">${orgBean.orgBadVal}</td>
              </tr>
            </c:forEach>
          </table>
        </div>
    </div>
    <!--原种种源标准指标 END-->
    <!--原种饲养标准指标 START-->
    <div class="group-accordion" collapsible="true" active="true">
      <h3>
        <label>原种饲养标准指标</label>
      </h3>
      <div width="100%" style="padding: 0px; border-right: none; border-bottom: none; border-top: none;">
          <table class="tree dataTable no-footer" style="width: 100%">
            <tr style="background: #DBDBDB;width:100%">
              <td width="25%" rowspan="2" align="center" height="">指标内容</td>
              <td colspan="6" align="center">等级</td>
            </tr>
            <tr style="background: #DBDBDB;width:100%">
              <td align="center" width="10"></td>
              <td align="center" width="25%">优良</td>
              <td align="center" width="10"></td>
              <td align="center" width="25%">一般</td>
              <td align="center" width="10"></td>
              <td align="center" width="25%">差</td>
            </tr>
            <c:forEach items="${slPdBean.slPdFedStdList}" var="fetBean" varStatus="i">
              <tr>
                <td width="25%" style="white-space:pre;">${fetBean.fedStdItemName}</td>
                <td><input type="radio" <c:if test="${fetBean.agreeFlg eq '1'}">checked="checked"</c:if>/>同意</td>
                <td width="25%" style="white-space:pre;">${fetBean.fedGoodVal}</td>
                <td><input type="radio" <c:if test="${fetBean.agreeFlg eq '2'}">checked="checked"</c:if>/>同意</td>
                <td width="25%" style="white-space:pre;">${fetBean.fedNormalVal}</td>
                <td><input type="radio" <c:if test="${fetBean.agreeFlg eq '3'}">checked="checked"</c:if>/>同意</td>
                <td style="white-space:pre;">${fetBean.fedBadVal}</td>
              </tr>
            </c:forEach>
          </table>
        </div>
    </div>
    <!--原种饲养标准指标 END-->
  </c:if>

  <!--通用质量标准指标 START-->
  <div class="group-accordion" collapsible="true" active="true">
    <h3>
      <label>通用质量标准指标</label>
    </h3>
    <div width="100%" style="padding: 0px; border-right: none; border-bottom: none; border-top: none;">
        <table class="tree dataTable no-footer" style="width: 100%">
          <tr style="background:#DBDBDB">
            <td></td>
            <td align="center" width="10"></td>
            <td width="25%">合格</td>
            <td align="center" width="10"></td>
            <td width="25%">不合格</td>
          </tr>
          <c:forEach items="${slPdBean.slPdGnqStdList}" var="gnqBeanParent" varStatus="j">
            <tr class="treegrid-${gnqBeanParent.gnqStdItemId}" style="background-color:#F8F8FF">
              <td width="25%">${gnqBeanParent.gnqStdItemName}</td>
              <td align="center" width="10"></td>
              <td></td>
              <td align="center" width="10"></td>
              <td width="25%"></td>
            </tr>
            <c:forEach items="${gnqBeanParent.pdGnqStds}" var="gnqBean" varStatus="i">
              <tr>
                <td>${gnqBean.gnqStdItemName}</td>
                <td><input type="radio" <c:if test="${gnqBean.agreeFlg eq '2'}">checked="checked"</c:if>/>同意</td>
                <td width="25%" style="white-space:pre;">${gnqBean.gnqOkVal}</td>
                <td><input type="radio" <c:if test="${gnqBean.agreeFlg eq '3'}">checked="checked"</c:if>/>同意</td>
                <td width="25%" style="white-space:pre;">${gnqBean.gnqNgVal}</td>
              </tr>
            </c:forEach>
          </c:forEach>
        </table>
      </div>
  </div>
  <!--通用质量标准指标 END-->

  <!--储存运输标准指标 START-->
  <div class="group-accordion" collapsible="true" active="true">
    <h3>
      <label>储存运输标准指标</label>
    </h3>
    <div width="100%" style="padding: 0px; border-right: none; border-bottom: none; border-top: none;">
        <table class="tree dataTable no-footer" style="width: 100%">
          <tr style="background:#DBDBDB">
            <td></td>
            <td align="center" width="10"></td>
            <td width="25%">合格</td>
            <td align="center" width="10"></td>
            <td width="25%">不合格</td>
          </tr>
          <c:forEach items="${slPdBean.slPdTspStdList}" var="tspBeanParent" varStatus="j">
            <tr style="background-color:#F8F8FF">
              <td width="25%">${tspBeanParent.tspStdItemName}</td>
              <td align="center" width="10"></td>
              <td></td>
              <td align="center" width="10"></td>
              <td width="25%"></td>
            </tr>
            <c:forEach items="${tspBeanParent.pdTspStds}" var="tspBean" varStatus="i">
              <tr>
                <td>${tspBean.tspStdItemName}</td>
                <td><input type="radio" <c:if test="${tspBean.agreeFlg eq '2'}">checked="checked"</c:if>/>同意</td>
                <td width="25%" style="white-space:pre;">${tspBean.tspOkVal}</td>
                <td><input type="radio" <c:if test="${tspBean.agreeFlg eq '3'}">checked="checked"</c:if>/>同意</td>
                <td width="25%" style="white-space:pre;">${tspBean.tspNgVal}</td>
              </tr>
            </c:forEach>
          </c:forEach>
        </table>
      </div>
  </div>
  <!--储存运输标准指标 END-->

  <!--安全标准指标 START-->
  <div class="group-accordion" collapsible="true" active="true">
    <h3>
      <label>安全标准指标</label>
    </h3>
    <div width="100%" style="padding: 0px; border-right: none; border-bottom: none; border-top: none;">
        <table class="tree dataTable no-footer" style="width: 100%">
          <tr style="background:#DBDBDB">
            <td></td>
            <td align="center" width="10"></td>
            <td width="25%">合格</td>
            <td align="center" width="10"></td>
            <td width="25%">不合格</td>
          </tr>
          <c:forEach items="${slPdBean.slPdSftStdList}" var="sftBeanParent" varStatus="j">
            <tr style="background-color:#F8F8FF">
              <td width="25%">${sftBeanParent.sftStdItemName}</td>
              <td align="center" width="10"></td>
              <td></td>
              <td align="center" width="10"></td>
              <td width="25%"></td>
            </tr>
            <c:forEach items="${sftBeanParent.pdSftStds}" var="sftBean" varStatus="i">
              <tr>
                <td>${sftBean.sftStdItemName}</td>
                <td><input type="radio" <c:if test="${sftBean.agreeFlg eq '2'}"> checked="checked"</c:if>/>同意</td>
                <td width="25%" style="white-space:pre;">${sftBean.sftOkVal}</td>
                <td><input type="radio" <c:if test="${sftBean.agreeFlg eq '3'}"> checked="checked"</c:if>/>同意</td>
                <td width="25%" style="white-space:pre;">${sftBean.sftNgVal}</td>
              </tr>
            </c:forEach>
          </c:forEach>
        </table>
      </div>
  </div>
  <!--安全标准指标 END-->
</div>