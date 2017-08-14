<%--
  Created by IntelliJ IDEA.
  Date: 2016/8/3
  Time: 9:54
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="navigation" uri="/msk" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<c:if test="${disableBtn ne 1}">
<navigation:header title="发票申请详细" backTitleArray="发票申请一览" backUrlArray="../SSC11323/init"></navigation:header>
</c:if>
<script src="<c:url value="/static/js/upload/fileupload.js"/>"></script>
<form action="">
  <input name="invoiceAmountCopy" type="hidden" id="invoiceAmountCopy" value="${ssc11324Bean.invoiceAmount}" style="width:200px;"/>
</form>
<div class="page-content list-page">
  <div class="group-accordion" collapsible="true" active="true">
    <h3><label>发票申请详情</label></h3>
    <form action="" method="post" id="SSC11324Form" >
      <table width="100%">
        <tr>
          <td align="right" width="14%">合同编号：</td>
          <td width="14%">${ssc11324Bean.contractCode}</td>
          <td width="14%" align="right" >合同名称：</td>
          <td width="14%">${ssc11324Bean.contractName}</td>
          <td> </td>
        </tr>
        <tr>
          <td align="right" width="14%">合同生效日期：</td>
          <td width="14%">${ssc11324Bean.contractActDateStr}</td>
          <td align="right" width="14%" >合同总金额(元)：</td>
          <td name="money">${ssc11324Bean.contractAmount}</td>
        </tr>
        <tr>
          <td align="right" width="14%">甲方(采购商)：</td>
          <td >${ssc11324Bean.purchaserName}</td>
          <td align="right" width="14%">申请状态：</td>
          <td ><msk:codemaster id="invoiceStatus" codeType="InvoiceStatus" viewType="label" modelName="Order"  codeValue="${ssc11324Bean.status}"/></td>
        </tr>
        <tr>
          <td align="right" width="14%">乙方(生产商)：</td>
          <td >${ssc11324Bean.supplierName}</td>
        </tr>
        <tr>
          <td align="right" width="14%">发票申请号：</td>
          <td width="14%"><input  name="invoiceRequestCode" disabled="true"   type="text" id="invoiceRequestCode" value="${ssc11324Bean.invoiceRequestCode}" style="width:200px;"/></td>
          <td align="right" width="14%">发票项目名称：</td>
          <td width="14%"><input maxlength="100" <c:if test="${ssc11324Bean.statusCtr eq'1'}">disabled="disabled" </c:if>  name="invoiceRequestDesc" type="text" id="invoiceRequestDesc" value="${ssc11324Bean.invoiceRequestDesc}" style="width:200px;"/></td>
          <td><span style="color: red;">*</span></td>
        </tr>
        <tr>
          <td  width="14%" align="right">申请人：</td>
          <td><input maxlength="100" <c:if test="${ssc11324Bean.statusCtr eq'1'}">disabled="disabled" </c:if> name="requester" type="text" id="requester"  value="${ssc11324Bean.requester}" style="width:200px;"/></td>
          <td  width="14%" align="right"><span style="float:left; color: red;">*</span>申请时间：</td>
          <td><input maxlength="100" <c:if test="${ssc11324Bean.statusCtr eq'1'}">disabled="disabled" </c:if>  name="requestTimeStr" type="text" id="requestTimeStr" value="${ssc11324Bean.requestTimeStr}" style="width:200px;"/></td>
          <td><span style="color: red;">*</span></td>
        </tr>
        <tr>
          <td  width="14%"align="right">付款单位(个人)：</td>
          <td ><input maxlength="100" <c:if test="${ssc11324Bean.statusCtr eq '1'}">disabled="disabled" </c:if>  name="payer" type="text" id="payer" <c:if test="${ssc11324Bean.statusCtr eq 9}">value="${ssc11324Bean.purchaserName}"</c:if><c:if test="${ssc11324Bean.statusCtr ne 9}"> value="${ssc11324Bean.payer}"</c:if> style="width:200px;"/></td>
          <td align="right" width="14%"><span style="float:left; color: red;">
            <c:choose>
              <c:when test="${ssc11324Bean.statusCtr eq '9'||ssc11324Bean.status eq '0'}">
                <img  src="../static/images/action/search.png" title="选择企业信息" id="chooseEpInfo" style="cursor:pointer;" />
              </c:when>
              <c:otherwise>*</c:otherwise>
            </c:choose>
          </span>收款单位：</td>
          <td width="14%"><input maxlength="100" <c:if test="${ssc11324Bean.statusCtr eq'1'}">disabled="disabled"</c:if> name="receiving" type="text" id="receiving" <c:if test="${ssc11324Bean.statusCtr eq 9}">value="${ssc11324Bean.supplierName}"</c:if><c:if test="${ssc11324Bean.statusCtr ne 9}">value="${ssc11324Bean.receiving}"</c:if> style="width:200px;"/></td>
          <td><span style="color: red;">
                  <c:choose>
                    <c:when test="${ssc11324Bean.statusCtr eq '9'||ssc11324Bean.status eq '0'}">
                      <img  src="../static/images/action/search.png" title="选择企业信息" id="chooseRpInfo" style="cursor:pointer;" />
                    </c:when>
                    <c:otherwise>*</c:otherwise>
                  </c:choose>
            </span></td>
        </tr>
        <tr>
          <td align="right" style="white-space:nowrap;" width="14%">
            购货方纳税人识别号：
          </td >
          <td>
            <input maxlength="100" <c:if test="${ssc11324Bean.statusCtr eq'1'}">disabled="disabled" </c:if> value="${ssc11324Bean.buyerTaxpayerCode}"  name="buyerTaxpayerCode" type="text" id="buyerTaxpayerCode"  style="width:200px;"/>
          </td>
          <td align="right" width="14%" style="white-space:nowrap;">
            <span style="float:left; color: red;">*</span>收货方纳税人识别号：
          </td>
          <td>
            <input maxlength="100" <c:if test="${ssc11324Bean.statusCtr eq'1'}">disabled="disabled" </c:if> value="${ssc11324Bean.receiverTaxpayerCode}"  name="receiverTaxpayerCode" type="text" id="receiverTaxpayerCode"  style="width:200px;"/>
          </td>
          <td><span style="color: red;">*</span></td>
        </tr>
        <tr>
          <td align="right" width="14%">
            购货方地址：
          </td >
          <td>
            <input maxlength="100" <c:if test="${ssc11324Bean.statusCtr eq'1'}">disabled="disabled" </c:if> value="${ssc11324Bean.buyerAddr}"  name="buyerAddr" type="text" id="buyerAddr"  style="width:200px;"/>
          </td>
          <td align="right" width="14%">
            <span style="float:left; color: red;">*</span>销售方地址：
          </td>
          <td>
            <input  maxlength="100" <c:if test="${ssc11324Bean.statusCtr eq'1'}">disabled="disabled" </c:if> value="${ssc11324Bean.sellerAddr}"  name="sellerAddr" type="text" id="sellerAddr"  style="width:200px;"/>
          </td>
          <td><span style="color: red;">*</span></td>
        </tr>
        <tr>
          <td align="right" width="14%">
            购货方电话：
          </td >
          <td>
            <input maxlength="100" <c:if test="${ssc11324Bean.statusCtr eq'1'}">disabled="disabled" </c:if> value="${ssc11324Bean.buyerTel}"  name="buyerTel" type="text" id="buyerTel"  style="width:200px;"/>
          </td>
          <td align="right" width="14%">
            <span style="float:left; color: red;">*</span>销售方电话：
          </td>
          <td>
            <input maxlength="100" <c:if test="${ssc11324Bean.statusCtr eq'1'}">disabled="disabled" </c:if> value="${ssc11324Bean.sellerTel}"  name="sellerTel" type="text" id="sellerTel"  style="width:200px;"/>
          </td>
          <td><span style="color: red;">*</span></td>
        </tr>
        <tr>
          <td align="right" width="14%">
            购货方开户银行：
          </td >
          <td>
            <input maxlength="100" <c:if test="${ssc11324Bean.statusCtr eq'1'}">disabled="disabled" </c:if> value="${ssc11324Bean.buyerBank}"  name="buyerBank" type="text" id="buyerBank"  style="width:200px;"/>
          </td>
          <td align="right" width="14%">
            <span style="float:left; color: red;">*</span>销售方开户银行：
          </td>
          <td>
            <input maxlength="100" <c:if test="${ssc11324Bean.statusCtr eq'1'}">disabled="disabled" </c:if> value="${ssc11324Bean.sellerBank}"  name="sellerBank" type="text" id="sellerBank"  style="width:200px;"/>
          </td>
          <td><span style="color: red;">*</span></td>
        </tr>
        <tr>
          <td  align="right" width="14%">
            购货方账号：
          </td >
          <td>
            <input maxlength="100" <c:if test="${ssc11324Bean.statusCtr eq'1'}">disabled="disabled" </c:if> value="${ssc11324Bean.buyerAccount}"  name="buyerAccount" type="text" id="buyerAccount"  style="width:200px;"/>
          </td>
          <td align="right" width="14%">
            <span style="float:left; color: red;">*</span>销售方账号：
          </td>
          <td>
            <input maxlength="100" <c:if test="${ssc11324Bean.statusCtr eq'1'}">disabled="disabled" </c:if> value="${ssc11324Bean.sellerAccount}"  name="sellerAccount" type="text" id="sellerAccount"  style="width:200px;"/>
          </td>
          <td><span style="color: red;">*</span></td>
        </tr>
        <tr>
          <td align="right" width="14%">发票金额(元)：</td>
          <td width="14%"><input onchange="SSC11324.changeAmount()" maxlength="21" <c:if test="${ssc11324Bean.statusCtr eq'1'}">disabled="disabled" </c:if>  name="invoiceAmount" type="text" id="invoiceAmount" value="${ssc11324Bean.invoiceAmount}" style="width:200px;text-align: right;"/></td>
          <td width="14%" align="right"><span style="float:left; color: red;">*</span>发票类型：</td>
          <td >
            <%--<input type="radio" id="invoiceType1" <c:if test="${ssc11324Bean.statusCtr eq'1'}">disabled="disabled" </c:if>  name="invoiceType" value="0"  <c:if test="${ssc11324Bean.invoiceType=='0'}">checked="checked"</c:if>--%>
                   <%--<c:if test="${ssc11324Bean.statusCtr=='9'}">checked="checked"</c:if>/>普通发票--%>
            <%--<input type="radio" id="invoiceType2" <c:if test="${ssc11324Bean.statusCtr eq'1'}">disabled="disabled" </c:if> name="invoiceType" value="1"--%>
                   <%--<c:if test="${ssc11324Bean.invoiceType=='1'}">checked="checked"</c:if>/> 增值税普通发票--%>
            <%--<input type="radio" id="invoiceType3" <c:if test="${ssc11324Bean.statusCtr eq'1'}">disabled="disabled" </c:if> name="invoiceType" value="2"--%>
                   <%--<c:if test="${ssc11324Bean.invoiceType=='2'}">checked="checked"</c:if>/>--%>
              <%--<select id="invoiceType" <c:if test="${ssc11324Bean.statusCtr eq 1}">disabled="disabled"</c:if>>--%>
                <%--<option value ="1" <c:if test="${ssc11324Bean.statusCtr=='9'||ssc11324Bean.invoiceType=='1'}">selected="selected"</c:if>>普通发票</option>--%>
                <%--<option value ="2" <c:if test="${ssc11324Bean.invoiceType=='2'}">selected="selected"</c:if>>增值税普通发票</option>--%>
                <%--<option value="3" <c:if test="${ssc11324Bean.invoiceType=='3'}">selected="selected"</c:if>>增值税专用发票</option>--%>
              <%--</select>--%>
              <c:if test="${ssc11324Bean.statusCtr=='9'||ssc11324Bean.status=='0'}">
              <msk:codemaster id="invoiceType" codeType="InvoiceType" viewType="select" modelName="Order"/>
              </c:if>
              <c:if test="${ssc11324Bean.statusCtr!='9'&&ssc11324Bean.status!='0'}">
              <msk:codemaster id="invoiceType" codeType="InvoiceType" viewType="label" modelName="Order"  codeValue="${ssc11324Bean.invoiceType}"/>
              </c:if>
          </td>
          <%--<td><span>增值税专用发票</span></td>--%>
        </tr>
        <tr>
          <td align="right" width="14%">备注：</td>
          <td width="14%"><input maxlength="100" <c:if test="${ssc11324Bean.statusCtr eq'1'}">disabled="disabled" </c:if>  name="remark" type="text" id="remark" value="${ssc11324Bean.remark}" style="width:200px;"/></td>
        </tr>
        <tr>
          <td>
            <input type="hidden" name="invoiceRequestId" value="${ssc11324Bean.invoiceRequestId}" id="invoiceRequestId">
            <input type="hidden" name="contractId" value="${ssc11324Bean.contractId}" id="contractId">
            <input type="hidden" name="supplierId" value="${ssc11324Bean.supplierId}" id="supplierId">
            <input type="hidden" name="supplierCode" value="${ssc11324Bean.supplierCode}" id="supplierCode">
            <input type="hidden" name="purchaserId" value="${ssc11324Bean.purchaserId}" id="purchaserId">
            <input type="hidden" name="purchaserCode" value="${ssc11324Bean.purchaserCode}" id="purchaserCode">
            <input type="hidden" name="contractName" id="contractName" value="${ssc11324Bean.contractName}">
            <input type="hidden" name="contractCode" id="contractCode"  value="${ssc11324Bean.contractCode}">
            <input type="hidden" name="purchaserName" id="purchaserName"  value="${ssc11324Bean.purchaserName}">
            <input type="hidden" name="supplierName" id="supplierName" value="${ssc11324Bean.supplierName}">
            <input type="hidden" name="contractActDateStr" id="contractActDateStr"  value="${ssc11324Bean.contractActDateStr}">
            <input type="hidden" name="contractAmount" id="contractAmount" value="${ssc11324Bean.contractAmount}">
            <input type="hidden" name="status" id="status" value="${ssc11324Bean.status}">
            <input type="hidden" name="statusCtr" id="statusCtr" value="${ssc11324Bean.statusCtr}">
            <input type="hidden" name="invoiceTypeVal" id="invoiceTypeVal" value="${ssc11324Bean.invoiceType}">
            <input type="hidden" name="ver" id="ver" value="${ssc11324Bean.ver}">

          <%--<input type="text" name="requester" value="${ssc11324Bean.requester}" id="requester">--%>
            <%--<input type="text" name="invoiceDate" value="${ssc11324Bean.invoiceDate}" id="invoiceDate">--%>
          </td>
        </tr>
        <tr>
          <td colspan="4"  style="white-space:pre;">
      <p>本合同已付总金额(元)：<span name="money" id="PayAmount">${ssc11324Bean.amount}</span>，已开发票<span>${ssc11324Bean.invoiceRequestCount}</span>张，合计金额(元)：<span name="money">${ssc11324Bean.contractInvoiceAmount}</span>，可申请金额<c:if test="${ssc11324Bean.status ne 4}">(含本次发票)</c:if>(元)：<span name="money" id="remainInvoiceAmount">${ssc11324Bean.remainContractInvoiceAmount}</span>。</p>
          </td>
        </tr>
      </table>
    </form>
  </div>
  <c:if test="${ssc11324Bean.status eq '3'||ssc11324Bean.status eq '4'}">
    <div div class="group-accordion" collapsible="true" active="true">
      <h3><label>上传发票信息</label></h3>
      <%--<form action='<c:url value="/SSC11324/fileUpload/${ssc11324Bean.invoiceRequestCode}"/>' id="SSC11324Upload" method="post" enctype="multipart/form-data">--%>
        <table width="100%">
          <tr>
            <td align="right" width="14%">发票接收人：</td>
            <td width="14%"><input maxlength="100" name="receiver" type="text" <c:if test="${ssc11324Bean.status eq '4'}"> disabled="disabled" </c:if>id="receiver" value="${ssc11324Bean.receiver}" style="width:200px;"/></td>
            <td  width="14%" align="right">发票接收时间：</td>
            <td><input name="receiveDate"  type="text" id="receiveDate" <c:if test="${ssc11324Bean.status eq '4'}"> disabled="disabled" </c:if> value="${ssc11324Bean.receiveDateStr}" style="width:200px;"/></td>
          </tr>
          <c:if test="${ssc11324Bean.status eq '4'}">
            <tr>
            </tr>
            <tr>
              <td align="right" width="14%">
                下载发票文件:
              </td>
              <td align="left">
                <a href="${downLoadUrl}?fid=${ssc11324Bean.uploadFileId}&fileName=${ssc11324Bean.uploadFileNameStr}" download="" style="text-decoration:none;">

                  <msk:button buttonType="button" coltype="download"  buttonValue="" buttonId="SSC11324.DOWNLOAD" />
              </a>
              </td>
              <%--<td ><input type="hidden" id="download" name="${ssc11324Bean.uploadFileId}" value="${ssc11324Bean.uploadFileName}"></td>--%>
              <%--<td><input type="hidden" id="downLoadUrl" value="${downLoadUrl}"></td>--%>
            </tr>
            <tr>
              <td align="right" width="14%">
                文件名:
              </td>
              <td>
                <a href="javascript:void(0);" onclick="SSCCommon.showPic('${downLoadUrl}?fid=${ssc11324Bean.uploadFileId}&fileName=${ssc11324Bean.uploadFileNameStr}')">
                <label for="SSC11324_DOWNLOAD">${ssc11324Bean.uploadFileName}</label>
                </a>
              </td>
            </tr>
          </c:if>
          <c:if test="${ssc11324Bean.status eq '3'&&disableBtn ne 1}">
            <tr>
              <td align="right">上传发票信息：</td>
              <td>
                <msk:uploadFile fileLinkId="invoiceFileId" fileName="invoiceFileName" uploadButtonId="SSC11324_UPLOAD" callbackFunction="callback"></msk:uploadFile>
              </td>
              <%--<td><input type="file" id="myfile" name="myfile"></td>--%>
            </tr>
            <td><msk:button buttonValue="提交发票文件与信息" buttonType="button" buttonId="SSC11324.UPLOAD"/></td>
          </c:if>
        </table>
      <%--</form>--%>
    </div>
  </c:if>



  <c:if test="${ssc11324Bean.status ne 9&&disableBtn ne 1}">
  <div class="group-accordion" collapsible="true" active="true">
    <h3>
      <label>审批/审核信息</label>
    </h3>
    <div>
      <table width="100%">
        <tr>
          <th width="15%" align="right">
            审批信息:
          </th>
          <th align="right" width="10%">
            <input type="radio" name="approvalFlag" value="1" id="approvalFlag1" <c:if test="${ssc11324Bean.status eq '1'||ssc11324Bean.status eq '6'}" >checked</c:if>>同意</input>
            <input type="radio" name="approvalFlag" value="5" id="approvalFlag2" <c:if test="${ssc11324Bean.status eq '5'}">checked</c:if>>不同意</input>
          </th>
          <td width="25%">
            <input maxlength="100" type="text" name="approvalRemark" id="approvalRemark" value="${ssc11324Bean.approvalRemark}" style="width: 300px" />
          </td>
          <td width="5%">
            <c:if test="${ssc11324Bean.status eq 0&&ssc11324Bean.statusCtr ne 9}">
            <msk:button buttonValue="审批" buttonId="SSC11324.APPROVAL" buttonType="button" useable="can_approval"/>
            </c:if>
          </td>
          <td align="right" width="5%">
            审批人：
          </td>
          <td width="10%">
            <c:if test="${ssc11324Bean.statusCtr eq 1||ssc11324Bean.status eq 5||ssc11324Bean.status eq 6}">
          ${ssc11324Bean.approvalPerson}
            </c:if>
          </td>
          <td align="right" width="8%">
            审批时间：
          </td>
          <td>
            <c:if test="${ssc11324Bean.statusCtr eq 1||ssc11324Bean.status eq 5||ssc11324Bean.status eq 6}">
              <fmt:formatDate value="${ssc11324Bean.approvalDate}" pattern="yyyy-MM-dd hh:mm:ss"/>
            </c:if>
          </td>
        </tr>
        <tr>
          <th align="right">
            审核信息:
          </th>
          <th  width="8%" align="right">
            <input type="radio" name="auditingFlag" value="2" id="auditingFlag1" <c:if test="${ssc11324Bean.status ne '5'}">checked</c:if> >同意</input>
            <input type="radio" name="auditingFlag" value="6" id="auditingFlag2" <c:if test="${ssc11324Bean.status eq '5'||ssc11324Bean.status eq '6'}">checked</c:if> >不同意</input>
          </th>
          <td width="25%">
            <input maxlength="100" type="text" name="auditingRemark" id="auditingRemark" value="${ssc11324Bean.auditingRemark}" style="width: 300px"/>
          </td>
          <td width="5%">
            <c:if test="${ssc11324Bean.status eq 1}">
            <msk:button buttonValue="审核" buttonId="SSC11324.AUDIT" buttonType="button"/>
            </c:if>
            </td>
          <td align="right" width="5%">
            审核人：
          </td>
          <td width="10%">
            <c:if test="${ssc11324Bean.statusCtr ne 9&&ssc11324Bean.status ne 0&&ssc11324Bean.status ne 1&&ssc11324Bean.status ne 5}">
          ${ssc11324Bean.auditingPerson}
            </c:if>
          </td>
          <td align="right" width="8%">
            审核时间：
          </td>
          <td>
            <c:if test="${ssc11324Bean.statusCtr ne 9&&ssc11324Bean.status ne 0&&ssc11324Bean.status ne 1&&ssc11324Bean.status ne 5}">
              <fmt:formatDate value="${ssc11324Bean.auditingDate}" pattern="yyyy-MM-dd hh:mm:ss"/>
            </c:if>
          </td>
        </tr>
      </table>
    </div>
  </div>
  <br>
  <div>
    <tr>&nbsp&nbsp
      <%--<td><msk:button buttonValue="申请" buttonType="button" buttonId="SSC11324.APPLY"/></td>--%>
      <c:choose>
        <c:when test="${ssc11324Bean.status eq 3||ssc11324Bean.status eq 4}">
          <td>
          </td>
        </c:when>
        <c:when test="${ssc11324Bean.status eq 2}">
          <td><msk:button buttonValue="再修改" buttonType="button"  buttonId="SSC11324.CHANGE"/></td>
        </c:when>
        <c:otherwise>
          <td><msk:button buttonValue="保存" buttonType="button"  buttonId="SSC11324.CHANGE"/></td>
        </c:otherwise>
      </c:choose>



    <%--<td><msk:button buttonValue="审批" buttonType="button" buttonId="SSC11324.APPORVAL"/></td>--%>
      <%--<td><msk:button buttonValue="审核" buttonType="button" buttonId="SSC11324.AUDIT"/></td>--%>
      <c:if test="${ssc11324Bean.status eq 2}">
      <td><msk:button buttonValue="开票" buttonType="button" buttonId="SSC11324.INVOICE"/></td>
      </c:if>
    </tr>
  </div>
  </c:if>
  <br>
  <br>
  <br>
</div>
<script src="<c:url value='/static/js/ssc/SSCCommon.js'/>"></script>
<script src="<c:url value='/static/js/ssc/SSC11324.js'/>"></script>
