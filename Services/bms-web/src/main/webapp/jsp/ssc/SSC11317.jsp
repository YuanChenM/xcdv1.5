<%--
  Created by IntelliJ IDEA.
  User: wang_shuai
  Date: 2016/7/8
  Time: 9:44
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="navigation" uri="/msk" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<script src="<c:url value="/static/js/upload/fileupload.js"/>"></script>
<msk:codemaster codeType="ProductRecvStatus" viewType="json" modelName="SSC"/>

<c:if test="${type eq '1'}">
<navigation:header title="预入库通知单详细" backTitleArray="预入库通知单一览" backUrlArray="../SSC11316/init/"></navigation:header>

</c:if>
<c:if test="${type eq '2'}">
  <navigation:header title="预入库通知单详细"
                     backTitleArray="生产商入库差异单一览,生产商入库差异单详细"
                     backUrlArray="../SSC11311/init/,../SSC11312/show/"
                     backParamArray='/{"differId":"${differId}"}'></navigation:header>
</c:if>
<c:if test="${type eq '3'}">
  <navigation:header title="预入库通知单详细" backTitleArray="核销单一览,核销单详细" backUrlArray="../SSC11321/init,../SSC11322/init" backParamArray="/{verificationId:${verificationId}}"></navigation:header>
</c:if>
<script type="text/javascript">
  function callbackFun(message){
    var deliveryPreIntoId = $("#deliveryPreIntoId").val();
    HDF.closeLoadingMask();
    $.alertMessage.info(message);

    $('#main-content').postUrl(Main.contextPath+"/SSC11317/init/1",{ deliveryPreIntoId :deliveryPreIntoId});
  }
</script>
<div class="page-content list-page" style="white-space:nowrap;">
  <form action='<c:url value="/SSC11317/search"/>' id="SSC11317Form" method="post">
    <input type="hidden" id="deliveryPreIntoId" name="deliveryPreIntoId" value="${ssc11317PreIntoBean.deliveryPreIntoId}">
    <input type="hidden" id="deliveryConfirmId" name="deliveryConfirmId" value="${ssc11317PreIntoBean.deliveryConfirmId}">
    <input type="hidden" id="ver" name="ver" value="${ssc11317PreIntoBean.ver}">
    <div class="group-accordion" collapsible="true" active="true">
      <h3>
        基本信息
      </h3>
      <table width="100%">
        <tr>
          <td align="right" style="white-space:nowrap;"  width="20%">预入库通知单编号：</td>
          <td id="deliveryPreIntoCode" width="30%"> ${ssc11317PreIntoBean.deliveryPreIntoCode}</td>
          <td align="right" width="20%">合同编号：</td>
          <td style="white-space:nowrap;">${ssc11317PreIntoBean.contractCode}</td>
        </tr>
        <tr>
          <td align="right">发货确认单编号：</td>
          <td style="white-space:nowrap;">
              <c:if test="${type ne '2'&&ssc11317PreIntoBean.productRecvStatus ne 9}">
              <a style="color:blue;" title="查看发货确认单详情" href="javascript:void(0)" onclick="goToDeliveryConfirmDetail(${ssc11317PreIntoBean.deliveryConfirmCode});">${ssc11317PreIntoBean.deliveryConfirmCode}</a>
              </c:if>
              <c:if test="${type eq '2'||ssc11317PreIntoBean.productRecvStatus eq 9}">
               ${ssc11317PreIntoBean.deliveryConfirmCode}
                </c:if>
                </td>
          <td style="white-space:nowrap;"  align="right">发货订单编号：</td>
          <td>${ssc11317PreIntoBean.deliveryCode}</td>
        </tr>
        <tr>
          <td align="right">采购商：</td>
          <td style="white-space:nowrap;" id="purchaserName">${ssc11317PreIntoBean.purchaserName}</td>
          <td style="display: none" id="purchaserCode">${ssc11317PreIntoBean.purchaserCode}</td>
          <td align="right">生产商：</td>
          <td style="white-space:nowrap;">${ssc11317PreIntoBean.supplierName}</td>
        </tr>
        <tr>
          <td align="right">物流区: </td>
          <td style="white-space:nowrap;" id="lgcsAreaName">${ssc11317PreIntoBean.lgcsAreaName}</td>
          <td ></td>
          <td ></td>
          <td ></td>
        </tr>
        <tr>
          <td align="right">收货状态：</td>
          <td><msk:codemaster codeType="ProductRecvStatus"  id="productRecvStatus" name='filterMap["productRecvStatus"]' viewType="label" modelName="SSC" codeValue="${ssc11317PreIntoBean.productRecvStatus}"/></td>
          <td align="right">实际到货日期：</td>
          <td id="arriveDate" colspan="3"><fmt:formatDate value="${ssc11317PreIntoBean.arriveDate}" type="both" pattern="yyyy-MM-dd"/></td>
        </tr>
        <tr>
          <td align="right">到货车次：</td>
          <td style="white-space:nowrap;">${ssc11317PreIntoBean.deliveryBatch}</td>
          <td align="right">第N车：</td>
          <td colspan="3">${ssc11317PreIntoBean.vehicleNumber}</td>
        </tr>

      </table>
    </div>

    <div class="group-accordion" collapsible="true" active="true" style="white-space:nowrap;">
      <h3>
        产品明细
      </h3>
      <table id="SSC11317_list_grid" width="100%">
        <thead>
        <tr>

          <th  coltype="sno"  rowspan="2" colspan="1">序号</th>
          <th  coltype="text" rowspan="2" colspan="1" name="pdName" width="10%">产品</th>
          <th  coltype="money" rowspan="2" colspan="1" name="settkementStandardPrice" width="5%">结算标准价(元/kg)</th>
          <th  colspan="2" >计划到货箱数</th>
          <th  colspan="2" >实际到货箱数</th>
        </tr>
        <tr>
          <th coltype="money" accuracy="0" name="productPlanBox">箱数</th>
          <th coltype="money" accuracy="4" name="productPlanWeight">重量KG</th>
          <th coltype="money" accuracy="0" name="productRecvBox">箱数</th>
          <th coltype="money" accuracy="4" name="productRecvWeight">重量KG</th>
        </tr>
        </thead>
        <tbody>
        </tbody>
        <tr>
          <td colspan="3" style="font-weight:bold; text-align:center;">合计</td>
          <td align="right"><label name="Box" id="totalProductPlanBox" /></td>
          <td align="right"><label name="weight" id="totalProductPlanWeight" /></td>
          <td align="right"><label name="Box" id="totalProductRecvBox" /></td>
          <td align="right"><label s name="weight" id="totalProductRecvWeight" /></td>
        </tr>


      </table>
    </div>
    <div class="group-accordion" collapsible="true" active="true" style="white-space:nowrap;">
      <h3>
        发货信息
      </h3>
      <table width="100%">
        <tr>
          <td align="right" style="white-space:nowrap;"　width="20%">发货仓库地址：</td>
          <td width="30%" style="white-space:nowrap;">${ssc11317PreIntoBean.deliveryWarehouseAddr}</td>
        </tr>
        <tr>
          <td align="right" width="20%">发货责任人：</td>
          <td width="30%">
            <c:choose>
              <c:when test="${ssc11317PreIntoBean.productRecvStatus == 0}">
                <input maxlength="100" id="deliveryResponsible" style="width:300px;" type="text" value="${ssc11317PreIntoBean.deliveryResponsible}"/>
              </c:when>
              <c:otherwise>
                ${ssc11317PreIntoBean.deliveryResponsible}
                <input id="deliveryResponsible" type="hidden" value="${ssc11317PreIntoBean.deliveryResponsible}"/>
              </c:otherwise>
            </c:choose>
          </td>
          <td align="right" style="white-space:nowrap;" width="20%">发货责任人联系方式：</td>
          <td style="white-space:nowrap;">
            <c:choose>
              <c:when test="${ssc11317PreIntoBean.productRecvStatus == 0}">
                <input maxlength="100" id="deliveryResponsibleTel" style="width:300px;" type="text" value="${ssc11317PreIntoBean.deliveryResponsibleTel}"/>
              </c:when>
              <c:otherwise>
                ${ssc11317PreIntoBean.deliveryResponsibleTel}
                <input id="deliveryResponsibleTel" type="hidden" value="${ssc11317PreIntoBean.deliveryResponsibleTel}"/>
              </c:otherwise>
            </c:choose>
          </td>
        </tr>
        <tr>
          <td align="right">发货执行人：</td>
          <td>
            <c:choose>
              <c:when test="${ssc11317PreIntoBean.productRecvStatus == 0}">
                <input maxlength="100" id="deliveryExecutor" type="text" style="width:300px;" value="${ssc11317PreIntoBean.deliveryExecutor}"/>
              </c:when>
              <c:otherwise>
                ${ssc11317PreIntoBean.deliveryExecutor}
                <input id="deliveryExecutor" type="hidden" value="${ssc11317PreIntoBean.deliveryExecutor}"/>
              </c:otherwise>
            </c:choose>
          </td>
          <td align="right">发货执行人联系方式：</td>
          <td>
            <c:choose>
              <c:when test="${ssc11317PreIntoBean.productRecvStatus == 0}">
                <input maxlength="100" id="deliveryExecutorTel" style="width:300px;" type="text" value="${ssc11317PreIntoBean.deliveryExecutorTel}"/>
              </c:when>
              <c:otherwise>
                ${ssc11317PreIntoBean.deliveryExecutorTel}
                <input id="deliveryExecutorTel" type="hidden" value="${ssc11317PreIntoBean.deliveryExecutorTel}"/>
              </c:otherwise>
            </c:choose>
          </td>
        </tr>
      </table>
      </div>

    <div class="group-accordion" collapsible="true" active="true" style="white-space:nowrap;">
      <h3>
        运输信息
      </h3>
      <table width="100%">
        <tr>
          <td align="right">运输单位名称：</td>
          <td style="white-space:nowrap;">
            <c:choose>
              <c:when test="${ssc11317PreIntoBean.productRecvStatus == 0}">
                <input maxlength="100" id="trafficCompanyName" style="width:300px;" type="text" value="${ssc11317PreIntoBean.trafficCompanyName}"/>
              </c:when>
              <c:otherwise>
                ${ssc11317PreIntoBean.trafficCompanyName}
                <input id="trafficCompanyName" type="hidden" value="${ssc11317PreIntoBean.trafficCompanyName}"/>
              </c:otherwise>
            </c:choose>
          </td>
        </tr>

        <tr>
          <td align="right" style="white-space:nowrap;" width="20%">运输单位责任人：</td>
          <td  width="30%" style="white-space:nowrap;">
            <c:choose>
              <c:when test="${ssc11317PreIntoBean.productRecvStatus == 0}">
                <input maxlength="100" id="trafficCompanyResponsible" style="width:300px;" type="text" value="${ssc11317PreIntoBean.trafficCompanyResponsible}"/>
              </c:when>
              <c:otherwise>
                ${ssc11317PreIntoBean.trafficCompanyResponsible}
                <input id="trafficCompanyResponsible" type="hidden" value="${ssc11317PreIntoBean.trafficCompanyResponsible}"/>
              </c:otherwise>
            </c:choose>
          </td>
          <td align="right" style="white-space:nowrap;" width="20%">运输单位责任人联系方式：</td>
          <td style="white-space:nowrap;">
            <c:choose>
              <c:when test="${ssc11317PreIntoBean.productRecvStatus == 0}">
                <input maxlength="100" id="trafficCompanyResponsibleTel" style="width:300px;" type="text" value="${ssc11317PreIntoBean.trafficCompanyResponsibleTel}"/>
              </c:when>
              <c:otherwise>
                ${ssc11317PreIntoBean.trafficCompanyResponsibleTel}
                <input id="trafficCompanyResponsibleTel" type="hidden" value="${ssc11317PreIntoBean.trafficCompanyResponsibleTel}"/>
              </c:otherwise>
            </c:choose>
          </td>
        </tr>
        <%--<tr>--%>
          <%--<td align="right">运输单位执行人：</td>--%>
          <%--<td>--%>
            <%--<c:choose>--%>
              <%--<c:when test="${ssc11317PreIntoBean.productRecvStatus == 0}">--%>
                <%--<input id="trafficCompanyExecutor" type="text" value="${ssc11317PreIntoBean.trafficCompanyExecutor}"/>--%>
              <%--</c:when>--%>
              <%--<c:otherwise>--%>
                <%--${ssc11317PreIntoBean.trafficCompanyExecutor}--%>
                <%--<input id="trafficCompanyExecutor" type="hidden" value="${ssc11317PreIntoBean.trafficCompanyExecutor}"/>--%>
              <%--</c:otherwise>--%>
            <%--</c:choose>--%>
          <%--</td>--%>
          <%--<td align="right">运输单位执行人联系方式：</td>--%>
          <%--<td>--%>
            <%--<c:choose>--%>
              <%--<c:when test="${ssc11317PreIntoBean.productRecvStatus == 0}">--%>
                <%--<input id="trafficCompanyExecutorTel" type="text" value="${ssc11317PreIntoBean.trafficCompanyExecutorTel}"/>--%>
              <%--</c:when>--%>
              <%--<c:otherwise>--%>
                <%--${ssc11317PreIntoBean.trafficCompanyExecutorTel}--%>
                <%--<input id="trafficCompanyExecutorTel" type="hidden" value="${ssc11317PreIntoBean.trafficCompanyExecutorTel}"/>--%>
              <%--</c:otherwise>--%>
            <%--</c:choose>--%>
          <%--</td>--%>
        <%--</tr>--%>
        <tr>
          <td align="right">运输单位执行人：</td>
          <td>
            <c:choose>
              <c:when test="${ssc11317PreIntoBean.productRecvStatus == 0}">
                <input maxlength="100" id="driverName" type="text" style="width:300px;" value="${ssc11317PreIntoBean.driverName}"/>
              </c:when>
              <c:otherwise>
                ${ssc11317PreIntoBean.driverName}
                <input id="driverName" type="hidden" value="${ssc11317PreIntoBean.driverName}"/>
              </c:otherwise>
            </c:choose>
          </td>
          <td align="right">运输单位执行人联系方式：</td>
          <td>
            <c:choose>
              <c:when test="${ssc11317PreIntoBean.productRecvStatus == 0}">
                <input id="driverTel" maxlength="100" type="text" style="width:300px;" value="${ssc11317PreIntoBean.driverTel}"/>
              </c:when>
              <c:otherwise>
                ${ssc11317PreIntoBean.driverTel}
                <input id="driverTel" type="hidden" value="${ssc11317PreIntoBean.driverTel}"/>
              </c:otherwise>
            </c:choose>
          </td>
        </tr>
        <tr>
          <td align="right">运输车辆车牌号：</td>
          <td>
            <c:choose>
              <c:when test="${ssc11317PreIntoBean.productRecvStatus == 0}">
                <input id="licPlateNumber" maxlength="100" type="text" style="width:300px;" value="${ssc11317PreIntoBean.licPlateNumber}"/>
              </c:when>
              <c:otherwise>
                ${ssc11317PreIntoBean.licPlateNumber}
                <input id="licPlateNumber" type="hidden" value="${ssc11317PreIntoBean.licPlateNumber}"/>
              </c:otherwise>
            </c:choose>
          </td>
          <td align="right">运输车辆类型：</td>
          <td>
            <c:choose>
              <c:when test="${ssc11317PreIntoBean.productRecvStatus == 0}">
                <input id="vehicleType" maxlength="100" type="text" style="width:300px;" value="${ssc11317PreIntoBean.vehicleType}"/>
              </c:when>
              <c:otherwise>
                ${ssc11317PreIntoBean.vehicleType}
                <input id="vehicleType" type="hidden" value="${ssc11317PreIntoBean.vehicleType}"/>
              </c:otherwise>
            </c:choose>
          </td>
        </tr>
      </table>
      </div>

      <div class="group-accordion" collapsible="true" active="true" style="white-space:nowrap;">
        <h3>
          仓库信息
        </h3>
        <table width="100%">
          <tr>
            <td align="right" width="20%" style="white-space:nowrap;">发货入库时间要求：</td>
            <td id="eta" width="30%"><fmt:formatDate value="${ssc11317PreIntoBean.eta}" type="both" pattern="yyyy-MM-dd"/></td>
            <td align="right" width="20%" style="white-space:nowrap;">目标仓库：</td>
            <td>${ssc11317PreIntoBean.arriveWarehouse}</td>
          </tr>
          <tr>
            <td align="right" width="20%">仓库责任人：</td>
            <td width="30%">
              <c:choose>
                <c:when test="${ssc11317PreIntoBean.productRecvStatus == 0}">
                  <input id="warehouseKeeper" maxlength="100" type="text" style="width:300px;" value="${ssc11317PreIntoBean.warehouseKeeper}"/>
                </c:when>
                <c:otherwise>
                  ${ssc11317PreIntoBean.warehouseKeeper}
                  <input id="warehouseKeeper" type="hidden" value="${ssc11317PreIntoBean.warehouseKeeper}"/>
                </c:otherwise>
              </c:choose>
            </td>
            <td align="right" style="white-space:nowrap;">仓库责任人联系方式：</td>
            <td>
              <c:choose>
                <c:when test="${ssc11317PreIntoBean.productRecvStatus == 0}">
                  <input id="warehouseKeeperTel" maxlength="100" type="text" style="width:300px;" value="${ssc11317PreIntoBean.warehouseKeeperTel}"/>
                </c:when>
                <c:otherwise>
                  ${ssc11317PreIntoBean.warehouseKeeperTel}
                  <input id="warehouseKeeperTel"  type="hidden" value="${ssc11317PreIntoBean.warehouseKeeperTel}"/>
                </c:otherwise>
              </c:choose>
            </td>
          </tr>

          <tr>
            <td align="right">验收责任人：</td>
            <td>
              <c:choose>
                <c:when test="${ssc11317PreIntoBean.productRecvStatus == 0}">
                  <input id="accepter" type="text" maxlength="100" style="width:300px;" value="${ssc11317PreIntoBean.accepter}"/>
                </c:when>
                <c:otherwise>
                  ${ssc11317PreIntoBean.accepter}
                  <input id="accepter" type="hidden" value="${ssc11317PreIntoBean.accepter}"/>
                </c:otherwise>
              </c:choose>
            </td>
            <td align="right">验收责任人联系方式：</td>
            <td>
              <c:choose>
                <c:when test="${ssc11317PreIntoBean.productRecvStatus == 0}">
                  <input id="accepterTel" type="text" maxlength="100" style="width:300px;" value="${ssc11317PreIntoBean.accepterTel}"/>
                </c:when>
                <c:otherwise>
                  ${ssc11317PreIntoBean.accepterTel}
                  <input id="accepterTel" type="hidden" value="${ssc11317PreIntoBean.accepterTel}"/>
                </c:otherwise>
              </c:choose>
            </td>
          </tr>
        </table>
    </div>

    <div class="group-accordion" collapsible="true" active="true" style="white-space:nowrap;">
      <h3>
        备注信息
      </h3>
      <table width="100%"　>
        <tr>
          <td align="right" style="white-space:nowrap;" width="20%">发货备注：</td>
          <td style="white-space:nowrap;">
            <c:choose>
              <c:when test="${ssc11317PreIntoBean.productRecvStatus == 0}">
                <textarea cols="60" maxlength="100" id="sendRemark">${ssc11317PreIntoBean.sendRemark}</textarea>
              </c:when>
              <c:otherwise>
                ${ssc11317PreIntoBean.sendRemark}
                <input id="sendRemark" type="hidden" value="${ssc11317PreIntoBean.sendRemark}"/>
              </c:otherwise>
            </c:choose>
          </td>
        </tr>
          <tr>
          <td align="right" width="20%">收货备注：</td>
          <td style="white-space:nowrap;">${ssc11317PreIntoBean.remark}</td>
        </tr>
        </table>
      </div>

  </form>
  <c:if test="${ssc11317PreIntoBean.productRecvStatus == 0}">
    <msk:button buttonType="button" buttonId="SSC11317.SAVE" buttonValue="保存"/>
    <%--<msk:button buttonType="button" buttonId="SSC11317.PRINT" buttonValue="打印产品标签"/>--%>
  </c:if>
  <%--<msk:button buttonType="button" buttonId="SSC11317.TEST" buttonValue="模拟产品入库"/>--%>

  <div class="group-accordion" collapsible="true" active="true" style="white-space:nowrap;">
    <h3>
      随车资料
    </h3>
    <form action='<c:url value="/SSC11317/fileUpload/${ssc11317PreIntoBean.deliveryPreIntoId}"/>' id="SSC11317Upload"
          method="post" enctype="multipart/form-data">
      <input id="downLoadUrl" type="hidden" value="${downLoadUrl}"/>
      <table width="100%" height="100px">
        <tr>
          <td align="right" style="white-space:nowrap;" width="20%">企业盖章三证：</td>
          <td width="30%">
            <c:if test="${ssc11317PreIntoBean.productRecvStatus == 0}">
              <msk:uploadFile fileLinkId="businessFileId" fileName="businessFileName" uploadButtonId="SSC11317_UPLOAD" callbackFunction="callback"></msk:uploadFile>
            </c:if>
            <c:if test="${!empty ssc11317PreIntoBean.businessFileName}">
              <a href="${downLoadUrl}?fid=${ssc11317PreIntoBean.businessFileId}&fileName=${ssc11317PreIntoBean.businessFileNameStr}" download="" style="text-decoration:none;">
                <msk:button buttonType="button" coltype="download"  buttonValue="" buttonId="SSC11317.DOWNLOAD1" />
              </a>
              ${ssc11317PreIntoBean.businessFileName}
            </c:if>
          </td>
          <td align="right" width="20%">动物检疫合格证明：</td>
          <td>
            <c:if test="${ssc11317PreIntoBean.productRecvStatus == 0}">
              <msk:uploadFile fileLinkId="quarantineFileId" fileName="quarantineFileName" uploadButtonId="SSC11317_UPLOAD" callbackFunction="callback" bindUpload ="false"></msk:uploadFile>
            </c:if>
            <c:if test="${!empty ssc11317PreIntoBean.quarantineFileName}">
              <a href="${downLoadUrl}?fid=${ssc11317PreIntoBean.quarantineFileId}&fileName=${ssc11317PreIntoBean.quarantineFileNameStr}" download="" style="text-decoration:none;">
                <msk:button buttonType="button" coltype="download"  buttonValue="" buttonId="SSC11317.DOWNLOAD2" />
              </a>
              ${ssc11317PreIntoBean.quarantineFileName}
            </c:if>
          </td>
        </tr>
        <tr>
          <td align="right">出库清单：</td>
          <td>
            <c:if test="${ssc11317PreIntoBean.productRecvStatus == 0}">
              <msk:uploadFile fileLinkId="inventoryFileId" fileName="inventoryFileName" uploadButtonId="SSC11317_UPLOAD" callbackFunction="callback" bindUpload ="false"></msk:uploadFile>
            </c:if>
            <c:if test="${!empty ssc11317PreIntoBean.inventoryFileName}">
              <a href="${downLoadUrl}?fid=${ssc11317PreIntoBean.inventoryFileId}&fileName=${ssc11317PreIntoBean.inventoryFileNameStr}" download="" style="text-decoration:none;">
                <msk:button buttonType="button" coltype="download"  buttonValue="" buttonId="SSC11317.DOWNLOAD3" />
              </a>
              ${ssc11317PreIntoBean.inventoryFileName}
            </c:if>
          </td>
          <td align="right" style="white-space:nowrap;">有效期官方检测报告：</td>
          <td>
            <c:if test="${ssc11317PreIntoBean.productRecvStatus == 0}">
              <msk:uploadFile fileLinkId="reportFileId" fileName="reportFileName" uploadButtonId="SSC11317_UPLOAD" callbackFunction="callback" bindUpload ="false"></msk:uploadFile>
            </c:if>
            <c:if test="${!empty ssc11317PreIntoBean.reportFileName}">
              <a href="${downLoadUrl}?fid=${ssc11317PreIntoBean.reportFileId}&fileName=${ssc11317PreIntoBean.reportFileNameStr}" download="" style="text-decoration:none;">
                <msk:button buttonType="button" coltype="download"  buttonValue="" buttonId="SSC11317.DOWNLOAD4" />
              </a>
              ${ssc11317PreIntoBean.reportFileName}
            </c:if>
          </td>
        </tr>
      </table>
    </form>
  </div>
  <c:if test="${ssc11317PreIntoBean.productRecvStatus == 0}">
    <msk:button buttonType="button" buttonValue="上传" buttonId="SSC11317.UPLOAD" />
    <br><br>
    <msk:button buttonType="button" buttonId="SSC11317.DEPARTURE" buttonValue="确认发车"/>
  </c:if>
  <%--<c:if test="${ssc11317PreIntoBean.productRecvStatus eq 1}">
    <msk:button buttonType="button" buttonId="SSC11317.TEST" buttonValue="模拟产品入库"/>
  </c:if>--%>
</div>
<script src="<c:url value="/static/js/ssc/SSCCommon.js"/>"></script>
<script src="<c:url value="/static/js/ssc/SSC11317.js"/>"></script>