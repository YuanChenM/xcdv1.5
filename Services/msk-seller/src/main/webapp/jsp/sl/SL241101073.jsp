<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="msk2" uri="/msk" %>
<div class="group-accordion" active="false">
    <h3>
        <label>卖家品牌</label>
    </h3>
    <div id="proxyBrand" style="display:none">
        <form:form action="${ctx}/SL241103007/update" id="SL2411030073Form"
                   metdod="post" commandName="proxy" modelAttribute="proxy">
            <table width="100%" border="0" cellpadding="0" cellspacing="0">
                <tr>
                    <td align="right" width="50%">生产商列表</td>
                    <td align="left" width="50%">
                        <form:select path="producerEpId" style="width:120px" id="prolist">
                            <form:option value="" label="请选择"/>
                            <form:options items="${proxyBean}" itemValue="producerEpId" itemLabel="epName"/>
                        </form:select>
                    </td>
                </tr>

                <tr>
                    <td align="right" width="50%">品牌列表</td>
                    <td align="left" width="50%">
                        <form:select path="brandName" style="width:120px" id="brandlist">
                            <form:option value="" label="请选择"/>
                        </form:select>
                    </td>
                </tr>


                <tr>
                    <td align="center" width="100%">
                        <msk2:button buttonType="button" buttonId="SL2411030073.SAVE" buttonValue="保存"/>
                        <%--<msk:button buttonValue="保存" buttonId="SL2411030073.SAVE" buttonType="button"/>--%>
                    </td>
                        <%--<td align="left" width="50%">
                            <msk:button buttonValue="添加" buttonId="SL2411030073.ADD" buttonType="button"/>
                        </td>--%>
                </tr>
            </table>
        </form:form>
    </div>
</div>
<%--<script src="${ctx}/js/sl/SL24110107.js"></script>--%>
