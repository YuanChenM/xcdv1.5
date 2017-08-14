<%-- 
    Title:产品包装信息编辑
    author:pxg
    createDate:2015-12-09
    updateDate:2015-12-09
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/comm/taglib.jsp" %>
<div class="page-content detail-page">
    <div class="group-accordion" collapsible="false" active="false">
        <h3>
            <label>产品包装信息编辑</label>
        </h3>
        <form:form action="${ctx}/PD141112/save" id="PD141112Form"
            method="post" commandName="pdNorms" modelAttribute="pdNorms">
            <input type="hidden" id="standardId" name="standardId" value="${standardId}"/>
            <input type="hidden" id="normsCode" name="normsCode" value="${normsCode}"/>
            <table>
                <tr>
                   <td >单个产品规格净重:</td>
                    <td><input type="text" name="normsSuttle" value="${pdNorms.normsSuttle}"/> </td>
                    <td  filter="false">单个产品规格净重误差范围:</td>
                     <td><input type="text" name="normsError" value="${pdNorms.normsError}"/></td>
                 </tr>
                 <tr>
                      <td  filter="false">内包装净重/个数:</td>
                   <td><input type="text" name="normsNumber" value="${pdNorms.normsNumber}"/></td>
                     <td  filter="false">内包装尺寸:</td>
                   <td><input type="text" name="normsSize" value="${pdNorms.normsSize}"/></td>
                 </tr>
                 <tr>
                   <td  filter="false">内包装材质及技术标准:</td>
                    <td><input type="text" name="normsTexture" value="${pdNorms.normsTexture}"/></td>
                 <td  filter="false">外包装规格:</td>
                    <td><input type="text" name="normsOut" value="${pdNorms.normsOut}"/></td>
                 </tr>
                 <tr>
                     <td  filter="false">外包装净重/毛重:</td>
                    <td><input type="text" name="normsKg" value="${pdNorms.normsKg}"/></td>
                 <td  filter="false">外包装尺寸:</td>
                    <td><input type="text" name="normsOutSize" value="${pdNorms.normsOutSize}"/></td>
                   </tr>
                 <tr>
                    <td  filter="false">外包装材质及技术标准:</td>
                    <td><input type="text" name="normsOutTexture"  value="${pdNorms.normsOutTexture}"/></td>
                    <%-- <td  filter="false">第十种包装:</td>
                    <td><input type="text" name="normsTen" value="${pdNorms.normsTen}"/></td>--%>
                     <td  filter="false">内包装净重数值:</td>
                     <td><input type="text" name="netweightInner"  value="${pdNorms.netweightInner}"/>(kg)</td>
                 </tr>
                 <tr>
                     <td  filter="false">外包装净重数值:</td>
                     <td><input type="text" name="netweightOut"  value="${pdNorms.netweightOut}"/>(kg)</td>
                     <td  filter="false">外包装长度:</td>
                     <td><input type="text" name="normsLength"  value="${pdNorms.normsLength}"/></td>
                 </tr>
                <tr>
                    <td  filter="false">外包装宽度:</td>
                    <td><input type="text" name="normsWidth"  value="${pdNorms.normsWidth}"/></td>
                    <td  filter="false">外包装高度:</td>
                    <td><input type="text" name="normsHeight"  value="${pdNorms.normsHeight}"/></td>
                </tr>
                <tr>
                    <td  filter="false">外包装体积:</td>
                    <td><input type="text" name="normsVolume"  value="${pdNorms.normsVolume}"/></td>
                </tr>
                 <tr>
                 <td  filter="false">产品规格:</td>
                     <td><input type="text" name="featureCode" readonly="readonly" value="${pdStandard.featureCode}"/></td>
                    <td  filter="false">产品分类:</td>
                    <td><input type="text" name="classesCode" readonly="readonly" value="${pdStandard.classesCode}"/></td>
                   </tr>
                   <tr>
                    <td  filter="false">产品品种:</td>
                    <td><input type="text" name="breedCode" readonly="readonly" value="${pdStandard.breedCode}"/></td>
                   </tr>
            </table>
        </form:form>
    </div>
    <div>
        <msk:button buttonValue="保存" buttonType="button" buttonId="PD141112.SAVE" />
        <msk:button buttonValue="返回" buttonType="button" buttonId="PD141112.Back" />
    </div>
 </div>
 <script src="${ctx}/static/js/pd/PD141112.js"></script>