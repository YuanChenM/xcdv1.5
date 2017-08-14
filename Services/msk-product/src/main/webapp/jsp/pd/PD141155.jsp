<%-- 
    Title:产品包装
    author:pxg
    createDate:2015-12-09
    updateDate:2015-12-09
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<c:if test="${yesOrNo != 'yes'}">
    <navigation:header title="卖家申请产品审核" backTitleArray="卖家申请产品审核详细审核" backUrlArray="${ctx}/PD141154/init"></navigation:header>
</c:if>
<div class="page-content list-page">
    <form action="${ctx}/PD141155/saveData" id="PD141126SearchData" method="post">
    <div class="group-accordion" collapsible="false" active="false" >
        <h3 style="position: relative">
            <label>卖家信息</label>
        </h3>
        <div>
            <input type="hidden" value="${list.tcProviderId}" name="tcProviderId"/>
            <input type="hidden" value="${packageParam.classestreeCode}" name="filterMap[classestreeCode]"/>
            <input type="hidden" value="${packageParam.providerCode}" name="filterMap[providerCode]"/>
            <input type="hidden" value="${packageParam.machiningCode}" name="filterMap[machiningCode]"/>
            <input type="hidden" value="${packageParam.breedCode}" name="filterMap[breedCode]"/>
            <input type="hidden" value="${packageParam.featureCode}" name="filterMap[featureCode]"/>
            <input type="hidden" value="${packageParam.weightCode}" name="filterMap[weightCode]"/>
                <table>
                    <tr>
                        <td>
                            卖家编码：
                        </td>
                        <td>
                            <input type="text" value="${list.providerCode}"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            卖家名称：
                        </td>
                        <td>
                            <input type="text" value="${list.providerName}"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            申请时间：
                        </td>
                        <td>
                            <input type="text" value="${list.applyDateTime}"/>
                        </td>
                    </tr>
                </table>
        </div>
    </div>
    <div class="group-accordion" collapsible="false" active="false" >
        <h3 style="position: relative">
            <label>产品信息</label>
        </h3>
            <div>
                <table>
                    <tr>
                        <td>
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;分类编码：
                        </td>
                        <td>
                            <input type="text" value="${list.classesCode}" id="classesCode"/>
                        </td>
                        <td>
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;分类名称：
                        </td>
                        <td>
                            <input type="text" value="${list.classesName}" id="classesName"/>
                        </td>
                    </tr>
                    <tr>
                        <td>二级分类编码：</td>
                        <td><input type="text" value="${list.machiningCode}" id="machiningCode"/></td>
                        <td>二级分类名称：</td>
                        <td><input type="text" value="${list.machiningName}" id="machiningName"/></td>
                    </tr>
                    <tr>
                        <td>
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;品种编码：
                        </td>
                        <td>
                            <input type="text" value="${list.breedCode}" id="breedCode"/>
                        </td>
                        <td>
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;品种名称：
                        </td>
                        <td>
                            <input type="text" value="${list.breedName}" id="breedName"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;特征编码：
                        </td>
                        <td>
                            <input type="text" value="${list.featureCode}" id="featureCode"/>
                        </td>
                        <td>
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;特征名称：
                        </td>
                        <td>
                            <input type="text" value="${list.featureName}" id="featureName"/>
                        </td>
                    </tr>
                    <tr>
                        <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;净重编码：</td><td><input type="text" value="${list.weightCode}" id="weightCode"/></td>
                        <td>
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;净重名称：
                        </td>
                        <td>
                            <input type="text" value="${list.weightName}" id="weightName"/>
                        </td>
                    </tr>
                </table>
        </div>
    </div>
    <div class="group-accordion" collapsible="false" active="false">
        <h3>
            <label>产品包装信息</label>
        </h3>
        <div>
            <table>
                <tr>
                    <td filter="false">包装编码:</td>
                    <td><input type="text" name="normsCode" value="${list.normsCode}" id="normsCode"/></td>
                    <td filter="false">外包装体积:</td>
                    <td><input type="text" name="normsVolume" value="${list.normsVolume}"/></td>
                </tr>
                <tr>
                    <td>单个产品规格净重:</td>
                    <td><input type="text" name="normsSuttle" value="${list.normsSuttle}"/></td>
                    <td filter="false">单个产品规格净重误差范围:</td>
                    <td><input type="text" name="normsError" value="${list.normsError}"/></td>
                </tr>
                <tr>
                    <td filter="false">内包装净重/个数:</td>
                    <td><input type="text" name="normsNumber" value="${list.normsNumber}"/></td>
                    <td filter="false">内包装尺寸:</td>
                    <td><input type="text" name="normsSize" value="${list.normsSize}"/></td>
                </tr>
                <tr>
                    <td filter="false">内包装材质及技术标准:</td>
                    <td><input type="text" name="normsTexture" value="${list.normsTexture}"/></td>
                    <td filter="false">外包装规格:</td>
                    <td><input type="text" name="normsOut" value="${list.normsOut}" id="normsOut"/></td>
                </tr>
                <tr>
                    <td filter="false">外包装净重/毛重:</td>
                    <td><input type="text" name="normsKg" value="${list.normsKg}"/></td>
                    <td filter="false">外包装尺寸:</td>
                    <td><input type="text" name="normsOutSize" value="${list.normsOutSize}"/></td>
                </tr>
                <tr>
                    <td filter="false">外包装材质及技术标准:</td>
                    <td><input type="text" name="normsOutTexture" value="${list.normsOutTexture}"/></td>
                    <td filter="false">内包装净重数值:</td>
                    <td><input type="text" name="netweightInner" value="${list.netweightInner}"/>(kg)</td>
                </tr>
                <tr>
                    <td filter="false">外包装净重数值:</td>
                    <td><input type="text" name="netweightOut" value="${list.netweightOut}"/>(kg)</td>
                    <td filter="false">外包装毛重数值:</td>
                    <td><input type="text" name="grossweightOut" value="${list.grossweightOut}"/>(kg)</td>
                </tr>
                <tr>
                    <td filter="false">外包装长度:</td>
                    <td><input type="text" name="normsLength" value="${list.normsLength}"/></td>
                    <td filter="false">外包装宽度:</td>
                    <td><input type="text" name="normsWidth" value="${list.normsWidth}"/></td>
                </tr>
                <tr>
                    <td filter="false">外包装高度:</td>
                    <td><input type="text" name="normsHeight" value="${list.normsHeight}"/></td>
                </tr>
            </table>
        </div>
    </div>
    <div class="group-accordion" collapsible="false" active="false" >
        <h3 style="position: relative">
            <label>审核</label>
        </h3>
        <div>
            <p>审核描述：</p>
            <textarea rows="10px" cols="80px" name="auditMemo">${list.auditMemo}</textarea>
        </div>
    </div>
        <input type="hidden" name="auditStatus"/>
        <c:if test="${list.auditStatus==0}">
            <msk:button buttonValue="同意" buttonId="PD141126.YES" buttonType="button"/>
            <msk:button buttonValue="不同意" buttonId="PD141126.NO" buttonType="button"/>
        </c:if>
    </form>
</div>
<script src="${ctx}/static/js/pd/PD141155.js"></script>
