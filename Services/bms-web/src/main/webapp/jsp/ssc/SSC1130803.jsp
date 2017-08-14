<%-- 
    Title:企业信息列表
    author:wu_honglei
    createDate:2016-09-09
--%>
<%@ taglib prefix="navigation" uri="/msk" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<msk:codemaster codeType="SlMainClass" viewType="json" modelName="Seller"/>

<div class="page-content list-page">
    <form action="<c:url value="/SSC11327/search"/>" id="SSC1130803Form" method="post" >

        <input type="hidden" id="supplierInputId" value="${ssc1130803RsBean.supplierInputId}"/>
        <input type="hidden" id="bankInputId" value="${ssc1130803RsBean.bankInputId}"/>
        <input type="hidden" id="bankAccountInputId" value="${ssc1130803RsBean.bankAccountInputId}"/>

        <div>
            <table width="100%" id="SSC1130803_list_grid">
                <thead>
                <tr>
                    <th coltype="radio"></th>
                    <th coltype="text" width="10%" name="slAccount" filter="true">企业账号</th>
                    <th coltype="text" width="20%" name="epName" filter="true">企业名称</th>
                    <th coltype="code" width="10%" filter="true" name="slMainClass" code2name="SLMAINCLASS_JSON">企业主类型</th>
                    <th coltype="text" width="20%" name="slTel" filter="true">联系电话</th>
                    <th coltype="text"  width="20%" name="cityName" filter="true">行政区划</th>
                    <th coltype="text"  width="20%" name="lgcsAreaName" filter="true">物流区划</th>
                    <th coltype="action" width="10px"></th>
                </tr>
              </thead>
               <tbody></tbody>
            </table>
        </div>
    </form>
    <msk:button buttonValue="确定" buttonId="SSC1130803.CONFIRM" buttonType="button"/>
</div>
<script src="<c:url value="/static/js/ssc/SSC1130803.js"/>" />