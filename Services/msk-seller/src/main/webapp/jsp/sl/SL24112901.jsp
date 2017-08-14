<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
    Title:包装申请信息
    author:gyh
    createDate:2016-3-22
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<div class="page-content list-page">
    <form:form action="${ctx}/SL241128/save" method="post" id="SL241128Form" commandName="bean"
               modelAttribute="bean">
        <input type="hidden" name="providerCode" value="${slCode}">
        <input type="hidden" name="providerName" value="${slShowName}">

        <div class="group-accordion" active="true" collapsible="false">
            <h3>
                <label>包装申请信息</label>
            </h3>
            <div>
                <table width="100%" id="SL241128_FORM">
                    <tr>
                        <td align="right">单个产品规格净重:</td>
                        <td>${bean.normsSuttle}</td>
                        <td align="right">单个产品规格净重误差范围:</td>
                        <td>${bean.normsError}</td>
                        <td align="right">内包装净重/个数:</td>
                        <td>${bean.normsNumber}</td>
                    </tr>
                    <tr>
                        <td align="right">内包装尺寸:</td>
                        <td>${bean.normsSize}</td>
                        <td align="right">内包装材质及技术标准:</td>
                        <td>${bean.normsTexture}</td>
                        <td align="right">外包装规格:</td>
                        <td>${bean.normsOut}</td>
                    </tr>
                    <tr>
                        <td align="right">外包装净重/毛重:</td>
                        <td>${bean.normsKg}</td>
                        <td align="right">外包装尺寸:</td>
                        <td>${bean.normsOutSize}</td>
                        <td align="right">外包装材质及技术标准:</td>
                        <td>${bean.normsOutTexture}</td>
                    </tr>
                    <tr>
                        <td align="right">内包装净重数值:</td>
                        <td>${bean.netweightInner}(kg)</td>
                        <td align="right">外包装净重数值:</td>
                        <td>${bean.netweightOut}(kg)</td>
                        <td align="right">外包装毛重数值:</td>
                        <td>${bean.grossweightOut}(kg)</td>
                    </tr>
                    <tr>
                        <td align="right">外包装长度:</td>
                        <td>${bean.normsLength}</td>
                        <td align="right">外包装宽度:</td>
                        <td>${bean.normsWidth}</td>
                        <td align="right">外包装高度:</td>
                        <td align="left">${bean.normsHeight}</td>
                    </tr>
                    <tr>
                        <td align="right">外包装体积:</td>
                        <td>${bean.normsVolume}</td>
                        <td align="right">其他包装信息:</td>
                        <td colspan="5" align="left">${bean.normsTen}</td>
                    </tr>
                </table>
            </div>
        </div>
    </form:form>
</div>
</div>
