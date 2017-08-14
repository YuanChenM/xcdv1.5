<%-- 
    Title:产品包装信息编辑
    author:pxg
    createDate:2015-53-09
    updateDate:2015-53-09
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<navigation:header title="产品包装指标" backTitleArray="产品分类目录管理" backUrlArray="${ctx}/PD141124/init?classesCode=${classesCode}&machiningCode=${machiningCode}&breedCode=${breedCode}&featureCode=${featureCode}&weightCode=${weightCode}"></navigation:header>
<div class="page-content detail-page">
    <div>
        </p>
        <table WIDTH="100%">
            <tr>
                <td width="50%">产品品种:${breedName}</td>
                <td width="50%">产品特征:${featureName}</td>
            </tr>
        </table>
        </p>
    </div>
    <div class="group-accordion" collapsible="false" active="false">
        <h3>
            <label>产品包装信息编辑</label>
        </h3>
        <form:form action="${ctx}/pd141153/save" id="PD141153Form"
                   method="post" commandName="pdNorms" modelAttribute="pdNorms">
            <input type="hidden" id="classestreeCode" name="classestreeCode" value="${classestreeCode}"/>
            <input type="hidden" id="standardId" name="standardId" value="${pdNorms.standardId}"/>
            <input type="hidden" name="classesCode" value="${classesCode}"/>
            <table width="100%">
                <tr>
                    <td width="10%">单个产品规格净重:</td>
                    <td><input type="text" name="normsSuttle" value="${pdNorms.normsSuttle}"/></td>
                    <td filter="false" width="10%">单个产品规格净重误差范围:</td>
                    <td><input type="text" name="normsError" value="${pdNorms.normsError}"/></td>
                </tr>
                <tr>
                    <td filter="false">内包装净重/个数:</td>
                    <td><input type="text" name="normsNumber" value="${pdNorms.normsNumber}"/></td>
                    <td filter="false">内包装尺寸:</td>
                    <td><input type="text" name="normsSize" value="${pdNorms.normsSize}"/></td>
                </tr>
                <tr>
                    <td filter="false">内包装材质及技术标准:</td>
                    <td><input type="text" name="normsTexture" value="${pdNorms.normsTexture}"/></td>
                    <td filter="false">外包装规格:</td>
                    <td><input type="text" name="normsOut" value="${pdNorms.normsOut}"/></td>
                </tr>
                <tr>
                    <td filter="false">外包装净重/毛重:</td>
                    <td><input type="text" name="normsKg" value="${pdNorms.normsKg}"/></td>
                    <td filter="false">外包装尺寸:</td>
                    <td><input type="text" name="normsOutSize" value="${pdNorms.normsOutSize}"/></td>
                </tr>
                <tr>
                    <td filter="false">外包装材质及技术标准:</td>
                    <td><input type="text" name="normsOutTexture" value="${pdNorms.normsOutTexture}"/></td>
                    <td filter="false">内包装净重数值:</td>
                    <td><input type="text" name="netweightInner" value="${pdNorms.netweightInner}"/>(kg)</td>
                </tr>
                <tr>
                    <td filter="false">外包装净重数值:</td>
                    <td><input type="text" name="netweightOut" value="${pdNorms.netweightOut}"/>(kg)</td>
                    <td filter="false">外包装毛重数值:</td>
                    <td><input type="text" name="grossweightOut" value="${pdNorms.grossweightOut}"/>(kg)</td>

                </tr>
                <tr>
                    <td filter="false">外包装长度:</td>
                    <td><input type="text" id="normsLength" name="normsLength" value="${pdNorms.normsLength}"/></td>
                    <td filter="false">外包装宽度:</td>
                    <td><input type="text" id="normsWidth" name="normsWidth" value="${pdNorms.normsWidth}"/></td>

                </tr>
                <tr>
                    <td filter="false">外包装高度:</td>
                    <td><input type="text" id="normsHeight" name="normsHeight" value="${pdNorms.normsHeight}"/></td>
                    <td filter="false">外包装体积:</td>
                    <td><input type="text" name="normsVolume" id="normsVolume" value="${pdNorms.normsVolume}"/>
                        <a href="javascript:void(0)" name="PD141153"  title="外包装体积值">
                            <img valign="middle" src="${ctx}/static/core/images/action/zidongjisuan.png"/>
                        </a>
                    </td>
                </tr>
            </table>
        </form:form>
    </div>
    <div>
        <msk:button buttonValue="保存" buttonType="button" buttonId="PD141153.SAVE"/>
    </div>
</div>
<script src="${ctx}/static/js/pd/PD141153.js"></script>