<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
    Title:产品加工类型维护
    author:xu_hongyang
    createDate:2016-2-15
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>

<div class="page-content detail-page">
    <div class="group-accordion" collapsible="false" active="true">
        <h3>
            <label>加工类型新增</label>
        </h3>
        <form:form action="${ctx}/PD14112301/save" id="PD14112301Form" method="post" >
            <table>
                <tr>
                    <td width="3%" height="5%" align="right">产品类别:</td>
                    <td width="50px" align="left">
                        <select style="width:120px"  name="classesCode" value="${bean.classesCode}">
                            <option value="${bean.classesCode}">${bean.classesName}</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td width="15%" align="right">加工类型名称:</td>
                    <td><input type="text" name="machiningName" id="machiningName"
                               value="${bean.machiningName}"/></td>
                    <td typeof="hidden"><input type="hidden"  name="machiningCode" id="machiningCode" value="${bean.machiningCode}"/></td>
                    <td typeof="hidden"><input type="hidden"  name="machiningRefId" id="machiningRefId" value="${bean.machiningRefId}"/></td>

                </tr>
            </table>
        </form:form>
    </div>
    <div>
        <msk:button buttonValue="保存" buttonType="button" buttonId="PD14112301.SAVE" />
        <msk:button buttonValue="返回" buttonType="button" buttonId="PD14112301.BACK" />
    </div>
</div>

</div>
</div>
<script src="${ctx}/static/js/pd/PD14112301.js"/>