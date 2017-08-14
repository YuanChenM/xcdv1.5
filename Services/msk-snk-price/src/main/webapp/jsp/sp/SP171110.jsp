<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<navigation:header title="供应商价格申报详细" backTitleArray="供应商待报价一览"  backUrlArray="${ctx}/SP171109/init/"></navigation:header>

<div class="page-content list-page">
    <form action="${ctx}/SP171110/save" id="SP171110Form" method="post">
        <input type="hidden" name="lgcsName" value="${sp171110Param.lgcsName}">
        <input type="hidden" name="slId" value="${sp171110Param.slId}" id="slId">
        <input type="hidden" name="sellerCode" value="${sp171110Param.sellerCode}" id="sellerCode">
        <input type="hidden" name="sellerName" value="${sp171110Param.sellerName}">
        <input type="hidden" name="lastPricePeriodTime" value="${sp171110Param.lastPricePeriodTime}">
        <input type="hidden" name="lastPricePeriod" value="${sp171110Param.lastPricePeriod}">
        <input type="hidden" name="pdName" value="${sp171110Param.pdName}">
        <input type="hidden" name="classesName" value="${sp171110Param.classesName}">
        <input type="hidden" name="machining" value="${sp171110Param.machining}">
        <input type="hidden" name="feature" value="${sp171110Param.feature}">
        <input type="hidden" name="grade" value="${sp171110Param.grade}">
        <input type="hidden" name="priceId" value="${sp171110Param.priceId}">
        <input type="hidden" id="lgcsCode" name="lgcsCode" value="${sp171110Param.lgcsCode}">
        <input type="hidden" name="pdCode" value="${sp171110Param.pdCode}">
        <input type="hidden" name="pricePeriod" value="${sp171110Param.pricePeriod}">
        <input type="hidden" name="pricePeriodStart" value="${sp171110Param.pricePeriodStart}">
        <input type="hidden" name="pricePeriodEnd" value="${sp171110Param.pricePeriodEnd}">
        <input type="hidden" name="classesCode" value="${sp171110Param.classesCode}">
        <input type="hidden" name="machiningCode" value="${sp171110Param.machiningCode}">
        <input type="hidden" name="breedCode" value="${sp171110Param.breedCode}">
        <input type="hidden" name="breed" value="${sp171110Param.breed}">
        <input type="hidden" name="featureCode" value="${sp171110Param.featureCode}">
        <input type="hidden" name="gradeCode" value="${sp171110Param.gradeCode}">
        <input type="hidden" name="weight" value="${sp171110Param.weight}">
        <input type="hidden" name="weightCode" value="${sp171110Param.weightCode}">
        <input type="hidden" id="defaultWayGrade" name="defaultWayGrade" value="${defaultWayGrade}">
    <div class="group-accordion" collapsible="false" active="false">
        <h3>
            <label>供应商价格申报详细</label>
        </h3>
        <table style="width: 100%;" CellSpacing=3>

            <tr>
                <td width="15%" align="right">地区:</td>
                <td width="15%" align="left"><input type="text" value="${sp171110Param.lgcsName}" name="lgcsName" style="width: 200px;" disabled/></td>
                <td width="15%" align="right">供应商:</td>
                <td width="15%" align="left"><input type="text" value="${sp171110Param.sellerName}" name="sellerName" style="width: 200px;" disabled/></td>
            </tr>
            <tr>
                <td width="15%" align="right">价盘周期:</td>
                <td width="15%" align="left"><input type="text" value="${sp171110Param.pricePeriod}" id="pricePeriod" name="pricePeriod" style="width: 200px;" disabled/></td>
                <td width="15%" align="right">填报时间:</td>
                <td width="15%" align="left"><input type="text" value="${sp171110Param.lastPricePeriodTime}" name="lastPricePeriodTime" style="width: 200px;" disabled/></td>
            </tr>
            <tr>
                <td width="13.3%" align="right">产品编码:</td>
                <td width="20%" align="left"><input type="text" value="${sp171110Param.pdCode}" id="pdCode" name="pdCode" style="width: 200px;" disabled/></td>

                <td width="13.3%" align="right">产品一级分类:</td>
                <td width="20%" align="left"><input type="text" value="${sp171110Param.classesName}" name="classesName" style="width: 200px;" disabled/></td>
                <td width="13.3%" align="right">产品二级分类:</td>
                <td width="20%" align="left"><input type="text" value="${sp171110Param.machining}" name="machining" style="width: 200px;" disabled/></td>
            </tr>
            <tr>
                <td width="13.3%" align="right">品种:</td>
                <td width="20%" align="left"><input type="text" value="${sp171110Param.breed}" name="pdName" style="width: 200px;" disabled/></td>
                <td width="13.3%" align="right">特征:</td>
                <td width="20%" align="left"><input type="text" value="${sp171110Param.feature}" name="feature" style="width: 200px;" disabled/></td>
                <td width="13.3%" align="right">产品等级:</td>
                <td width="20%" align="left"><input type="text" value="${sp171110Param .grade}" name="grade" style="width: 200px;" disabled/></td>
            </tr>
        </table>
    </div>
    <br>
    <div class="group-accordion" collapsible="false" active="false">
        <h3>
            <label>价盘一览</label>
        </h3>
            <table class="dataTable no-footer" id="SP171110Table" style="width: 100%">
                <thead>
                <tr>
                    <th align="center" coltype="text">参与的分销通道</th>
                    <th align="center" coltype="text">等级</th>
                    <th align="center" coltype="text">等级标准(箱)</th>
                    <th align="center" coltype="text">通道等级平衡系数(%)</th>
                    <th align="center" coltype="text">下限价</br>(元/KG)</th>
                    <th align="center" coltype="text">价格</br>(元/KG)</th>
                    <th align="center" coltype="text">上限价</br>(元/KG)</th>
                    <th align="center" coltype="text">上期价盘</br>(元/KG)</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${SP171110BeanList}" var="priceDetailInfo" varStatus="status">
                    <tr>
                        <c:if test="${status.index == '0'}">
                             <td align="center"><input type="checkbox" name="isValidArray"  value="${status.index}" <c:if test="${priceDetailInfo.isValid eq '1'}">checked</c:if>></td>
                        </c:if>
                        <c:if test="${status.index == '1'||status.index == '4'||status.index == '7'}">
                            <td align="center" rowspan="3"><input type="checkbox" name="isValidArray" value="${status.index}" <c:if test="${priceDetailInfo.isValid eq '1'}">checked</c:if>></td>
                        </c:if>
                        <td align="center">${priceDetailInfo.wayGradeName}</td>
                        <td align="center">
                            <c:if test="${status.index == '0'}">
                                ≥${priceDetailInfo.wayGradeStart}
                            </c:if>
                            <!--Modif for Bug#3345 at 2016/10/14 by ni_shaotang Start-->
                            <c:if test="${status.index != '0'}">
                                ${priceDetailInfo.wayGradeStart}-${priceDetailInfo.wayGradeEnd}
                            </c:if>
                            <!--Modif for Bug#3345 at 2016/10/14 by ni_shaotang End-->
                        </td>
                        <td align="center">${priceDetailInfo.wayGradePercent}</td>
                        <td align="center">${priceDetailInfo.downPrice}</td>
                        <c:if test="${priceDetailInfo.wayGradeCode eq defaultWayGrade}">
                            <td align="center">
                                <input type="hidden" id="downPrice" value="${priceDetailInfo.downPrice}">
                                <input type="hidden" id="upPrice" value="${priceDetailInfo.upPrice}">
                                <input type="hidden" id="wayGradePercent" value="${priceDetailInfo.wayGradePercent}">
                                <input type="number" id="price${status.index}" min="0" name="wayGradePriceArray" value="${priceDetailInfo.wayGradePrice}" <c:if test="${overDataFlag eq '1' && viewFlg ne '1'}">disabled</c:if>>
                            </td>
                        </c:if>
                        <c:if test="${priceDetailInfo.wayGradeCode ne defaultWayGrade}">
                            <td align="center">${priceDetailInfo.wayGradePrice}</td>
                            <input type="hidden" name="wayGradePriceArray" id="price${status.index}" value="${priceDetailInfo.wayGradePrice}"/>
                        </c:if>
                        <input type="hidden" name="wayCodeArray" value="${priceDetailInfo.wayCode}">
                        <input type="hidden" name="wayGradeCodeArray" value="${priceDetailInfo.wayGradeCode}">
                        <input type="hidden" name="wayGradeNameArray" value="${priceDetailInfo.wayGradeName}">
                        <input type="hidden" name="downPriceArray" value="${priceDetailInfo.downPrice}">
                        <input type="hidden" name="upPriceArray" value="${priceDetailInfo.upPrice}">
                        <input type="hidden" name="wayGradePercentArray" value="${priceDetailInfo.wayGradePercent}">
                        <td align="center">${priceDetailInfo.upPrice}</td>
                        <td align="center">${priceDetailInfo.lastWayGradePrice}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <br>

    </div>
        <div style="font-size: 14px;color: #0066cc;font-weight: 900"> ★报价中包含1%的城市分拨费、0.6%的银行服务费、1%的代收服务费(包括产品检测费、代开动检证等第三方收取的费用)、2%的平台管理费，
            以上费用率均以销售额计；不包括干线物流费、产品包装与标签费用；卖家需将以上费用计入销售成本中</div>
    </form>
    <br>
     <span>
         <c:if test="${userType eq '0' || overDataFlag eq '0'}">
            <msk:button buttonValue="保存" buttonId="SP171110.SAVE"  buttonType="button" />
         </c:if>
         <%-- Modfiy:  一览页面到详情页面打开方式横展开,将返回按钮注释   2016/10/11   BY  zhukai1  Start --%>
            <%--<msk:button buttonValue="返回" buttonId="SP171110.RETURN" url="${ctx}/SP171109/init" buttonType="button"/>--%>
         <%-- Modfiy:  一览页面到详情页面打开方式横展开,将返回按钮注释   2016/10/11   BY  zhukai1  end --%>
            <msk:button buttonValue="其他供应商价格查看" buttonId="SP171110.OTHER" buttonType="button" />
       </span>
</div>
<script src="${ctx}/static/sp/js/SP171110.js"></script>

