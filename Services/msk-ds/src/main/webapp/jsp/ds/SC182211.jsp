.<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="navigation" uri="/msk-navigation" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/comm/taglib.jsp" %>
<navigation:header title="产品批次入库及标签打印" backTitleArray="" backUrlArray=""></navigation:header>
<div class="page-content list-page">
    <div class="group-accordion" collapsible="" active="false">
        <h3>
            <label>打印数据</label>
        </h3>
        <form:form action="${ctx}/SC182211/init" id="SC182211Form" method="post">
            <input type="hidden" name="currenthalfCode" id="currenthalfCode" value="${halfParam.currentHalfCode}"/>
            <table>
                <tbody>
                <tr>
                    <td align="left">卖家名称</td>
                    <td align="left">
                        <select style="width: 138px;" id="slCode" name="slCode">
                            <option value="">---请选择---</option>
                            <c:forEach items="${sc182211BeanList}" var="epNameList">
                                <option value="${epNameList.slCode}" valuetwo="${epNameList.slCodeDis}"  >${epNameList.epName}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td align="left">物流区名称</td>
                    <td align="left">
                        <select style="width: 138px;" id="lgcsCode" name="lgcsCode">
                            <option value="">---请选择---</option>
                            <c:forEach items="${lgcsCodeList}" var="lgcsList">
                                <option value="${lgcsList.lgcsAreaCode}">${lgcsList.lgcsAreaName}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td align="left">一级分类</td>
                    <td align="left">
                        <select style="width: 138px;" id="pdClassesCode" name="pdClassesCode">
                            <option value="">---请选择---</option>
                        </select>
                    </td>
                    <td align="left">二级分类</td>
                    <td align="left">
                        <select style="width: 138px;" id="machiningCode" name="machiningCode">
                            <option value="">---请选择---</option>
                        </select>
                    </td>
                    <td align="left">品种</td>
                    <td align="left">
                        <select style="width: 138px;" id="pdBreedCode" name="pdBreedCode">
                            <option value="">---请选择---</option>
                        </select>
                    </td>
                    <td  align="left">特征</td>
                    <td align="left">
                        <select style="width: 138px;" id="pdFeatureCode" name="pdFeatureCode">
                            <option value="">---请选择---</option>
                        </select>
                    </td>
                    <td  align="left">等级</td>
                    <td align="left">
                        <select style="width: 138px;" id="groupCode" name="groupCode">
                            <option value="">---请选择---</option>
                            <%--<option value="1">A1</option>
                            <option value="2">A2</option>
                            <option value="3">A3</option>--%>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td align="left">净重</td>
                    <td align="left">
                        <select style="width: 138px;" id="weightCode" name="weightCode">
                            <option value="">---请选择---</option>
                        </select>
                    </td>
                    <td align="left">包装规格</td>
                    <td align="left">
                        <select style="width: 138px;" id="normsCode" name="normsCode">
                            <option value="">---请选择---</option>
                        </select>
                    </td>
                    <td align="left">生产商</td>
                    <td align="left">
                        <select style="width: 138px;" id="prodEpId" name="prodEpId">
                            <option value="">---请选择---</option>
                        </select>
                    </td>
                    <td align="left">入库平台</td>
                    <td align="left">
                        <select style="width: 138px;" name="salePlatform" id="salePlatform">
                            <option value="">---请选择---</option>
                            <%--<option value="1">神农客</option>
                            <option value="2">美侍客</option>--%>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td align="left">货号</td>
                    <td align="left">
                        <select style="width: 138px;" name="slPdArtno" id="slPdArtno">
                            <option value="">---请选择---</option>
                        </select>
                    </td>
                    <td align="left">品牌</td>
                    <td align="left">
                        <select style="width: 138px;" id="brandId" name="brandId">
                            <option value="">---请选择---</option>
                        </select>
                    </td>
                    <td align="left">时间</td>
                    <td align="left">
                        <input type="text"  id="priceDate" name="priceDate" readonly="readonly"/>
                    </td>
                    <td align="left" >半旬</td>
                    <td align="left">
                        <select style="width: 138px;" name="half" id="half">
                            <option value="">---请选择---</option>
                            <%--<option value="1">1-5日</option>
                            <option value="2">6-10日</option>
                            <option value="3">11-15日</option>
                            <option value="4">16-20日</option>
                            <option value="5">21-25日</option>
                            <option value="6">26日-月底</option>--%>
                        </select>
                    </td>
                    <td align="left">流水号</td>
                    <td align="left">
                        <input type="text"  id="number" style="width:80px;color:#999" name="number" maxlength="5"  value="00001"
                               onFocus="if(value==defaultValue){value='';this.style.color='#000'}"
                               onBlur="if(!value){value=defaultValue;this.style.color='#999'}"
                               style="color:#999999"/>
                        &nbsp;&nbsp; ~
                        <input type="text" style="width:80px;color:#999" id="numberTwo" name="numberTwo" maxlength="5" value="00005"
                               onFocus="if(value==defaultValue){value='';this.style.color='#000'}"
                               onBlur="if(!value){value=defaultValue;this.style.color='#999'}" style="color:#999999"/>
                    </td>
                </tr>
                </tbody>
            </table>
            <msk:button buttonValue="生成打印数据" buttonId="SC182211.SAVE" buttonType="button" align="left"/>
            <msk:button buttonValue="导出数据文件" buttonId="SC182211.EXPORT" buttonType="button" align="left"/>
            <msk:button buttonValue="新打印标签" buttonId="SC182211.DELETE" buttonType="button" align="left"/>
            <msk:button buttonValue="查询上次批次号" buttonId="SC182211.SEARCH" buttonType="button" align="left"/>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <msk:button buttonValue="查询所有批次号" buttonId="SC182211.SEARCHLOT" buttonType="button" align="left"/>
            <msk:button buttonValue="查询所有批次流水号" buttonId="SC182211.SEARCHSERIAL" buttonType="button" align="left"/>
        </form:form>
    </div>
</div>
<script type="text/javascript" src="${ctx}/static/ds/js/SC182211.js"></script>