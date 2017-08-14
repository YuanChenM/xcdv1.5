<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/comm/taglib.jsp" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="page-content list-page">
    <form:form action="${ctx}/SC183101/save" method="post" id="SC183101Form">
        <div>
            <table width="100%">
                <tr>
                    <td align="right">分销月度</td>
                    <td>
                        <input type="text" name="filterMap['distMonth']" id="distMonth" value="${halfParam.distMonth}" readonly="readonly"/>
                    </td>
                    <td></td>
                    <td>区域</td>
                    <td>
                        <select style="width: 133px" name="filterMap['areaCode']" id="areaCode" disabled>
                            <option value="${halfParam.areaCode}">${halfParam.areaName}</option>
                            <input type="hidden" name ="filterMap['areaCode']" value ="${halfParam.areaCode}"/>

                        </select>
                    </td>
                    <td></td>
                    <td>供应商</td>
                    <td>
                        <select style="width: 133px" name="filterMap['supplierCode']" id="supplierCode" disabled>
                            <option value="${halfParam.supplierCode}">${halfParam.supplierName}</option>
                            <input type="hidden" name ="filterMap['supplierCode']" value ="${halfParam.supplierCode}"/>
                        </select>
                    </td>
                    <td></td>
                    <td>产品</td>
                    <td><input type="text" name="filterMap['productName']" id="productFullName" value="${halfParam.className}" readonly="readonly"></td>
                </tr>
                <tr>
                    <td>计划类型</td>
                    <td>
                        <select style="width: 133px" name="filterMap['planType']" id="planType" disabled>
                            <c:forEach items="${halfParam.planList}" var="planType">
                                <c:choose>
                                    <c:when test="${halfParam.planType eq (planType.planType)}">
                                        <option value="${planType.planType}" selected>${planType.planTypeName}</option>
                                        <input type="hidden" name ="filterMap['planType']" value ="${planType.planType}"/>
                                    </c:when>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </td>
                    <td></td>
                    <td>半旬</td>
                    <td>
                        <select style="width: 133px" name="filterMap['halfCode']" id="halfCode" disabled    >
                            <c:forEach items="${halfParam.halfNameList}" var="half">
                                <c:choose>
                                    <c:when test="${halfParam.currentHalfCode eq half.halfCode}">
                                        <option value="${half.halfCode}" selected>${half.halfName}</option>
                                        <input type="hidden" name ="filterMap['halfCode']" value ="${half.halfCode}"/>
                                    </c:when>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </td>
                    <td></td>
                    <td>调整日期</td>
                    <td><input type="text" name="filterMap['adjustDate']" id="adjustDate" value="${halfParam.adjustDate}" readonly="readonly"></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
            </table>
        </div>
        <div align="right">
            <a href="javascript:void(0)" name="DS173103P" >过往计划调整历史</a>
        </div>
        <br>
        <div>
            <table class="dataTable no-footer" width="100%" id="SC183101Table">
                <thead>
                <tr style="background:#DBDBDB">
                    <th>规格</th>
                    <th>调整前值</th>
                    <th>调整值</th>
                    <th>调整箱数</th>
                    <th>调整后值</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${halfParam.planChangeProductList}" var="planChangeProduct" varStatus="i">
                    <tr>
                        <td style="background:#DBDBDB">${planChangeProduct.productName}
                            <input type="hidden" name="productParamList[${i.index}].productCode" id="productCode${planChangeProduct.productCode}" value="${planChangeProduct.productCode}"/>
                            <input type="hidden" name="productParamList[${i.index}].normsCode" id="normsCode${planChangeProduct.productCode}" value="${planChangeProduct.normsCode}"/>
                            <input type="hidden" name="productParamList[${i.index}].adjustDate" id="adjustDate${planChangeProduct.productCode}" value="${planChangeProduct.adjustDateOld}"/>
                            <input type="hidden" name="productParamList[${i.index}].pdOutNw" id="pdOutNw${planChangeProduct.productCode}" value="${planChangeProduct.pdOutNw}"/>
                        </td>
                        <td>
                            <input type="text" name="productParamList[${i.index}].changeBeforeNum" id="changeBeforeNum${planChangeProduct.productCode}"
                                   value="<fmt:formatNumber value="${planChangeProduct.changeBeforeNum}" pattern="#,##0.00" />" readonly="readonly" style='background:#DBDBDB;text-align: right'/>
                        </td>
                        <td>
                            <input type="text" name="productParamList[${i.index}].changeNum" id="changeNum${planChangeProduct.productCode}" value="0.00" readonly="readonly" style='background:#DBDBDB;text-align: right'
                                   maxlength="18" max="99999999999999.99" number="true" numberMessage="计算后的调整值必须为数字,且长度不能大于14(可带两位小数)!"/>
                        </td>
                        <td>
                            <input type="text" name="changeBoxes" id="changeBoxes${planChangeProduct.productCode}" style="text-align: right" value="0"
                                   maxlength="15" 	number="true" max="99999999999999" digitsMessage="调整箱数必须为整数,最大长度14!"/>
                        </td>
                        <td>
                            <input type="text" name="productParamList[${i.index}].changeOverNum" id="changeOverNum${planChangeProduct.productCode}"
                                   value="<fmt:formatNumber value="${planChangeProduct.changeBeforeNum}" pattern="#,##0.00" />" readonly="readonly" style='background:#DBDBDB;text-align: right'
                                   maxlength="18" max="99999999999999.99" number="true" numberMessage="调整后的值必须为数字,长度不能大于14(可带两位小数)!"/>
                        </td>
                    </tr>
                </c:forEach>
                <tr>
                    <td style="background:#DBDBDB">合计</td>
                    <td>
                        <input type="text" name="sumChangeBeforeNum" id="sumChangeBeforeNum"
                               value="<fmt:formatNumber value="${halfParam.sumChangeBeforeNum}" pattern="#,##0.00" />" readonly="readonly" style='background:#DBDBDB;text-align: right'/>
                    </td>
                    <td>
                        <input type="text" name="sumChangeNum" id="sumChangeNum" value="0.00"  readonly="readonly" style='background:#DBDBDB;text-align: right'/>
                    </td>
                    <td>
                        <input type="text" name="sumChangeBoxes" id="sumChangeBoxes" value="0"  readonly="readonly" style='background:#DBDBDB;text-align: right'/>
                    </td>
                    <td>
                        <input type="text" name="sumChangeOverNum" id="sumChangeOverNum"
                               value="<fmt:formatNumber value="${halfParam.sumChangeBeforeNum}" pattern="#,##0.00" />" readonly="readonly" style='background:#DBDBDB;text-align: right'/>
                    </td>
                </tr>
                </tbody>
            </table>
            <br/>
            <input type="hidden" name="halfCode" id ="halfCodeOld"  value="${halfParam.halfCode}"/>
            <input type="hidden" name="filterMap['entryMark']" id ="entryMark"  value="${halfParam.entryMark}"/>
            <input type="hidden" name="filterMap['suppDsId']"  value="${halfParam.suppDsId}"/>
            <input type="hidden" name="filterMap['virtualStockPlanId']"  value="${halfParam.virtualStockPlanId}"/>
            <msk:button buttonValue="保存" name="SC183101.SAVE"  buttonId="SC183101.SAVE" buttonType="button" />
        </div>
    </form:form>
</div>
<script type="text/javascript" src="${ctx}/static/ds/js/SC183101.js"></script>
