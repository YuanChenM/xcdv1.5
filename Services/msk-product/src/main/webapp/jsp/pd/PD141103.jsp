<%--
    Title:产品品种维护
    author:gyh
    createDate:2015-12-09
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<script type="text/javascript">
    var BREED_CODE = "${breedCode}";
    var CLASSES_CODE = "${classesCode}";
</script>
<div class="page-content detail-page">
    <div class="group-accordion" collapsible="false" active="false">
        <h3>
            <label>产品品种维护</label>
        </h3>
        <form:form action="${ctx}/PD141103/${editModel}" id="PD141103Form"
                   method="post" commandName="pdBreed" modelAttribute="pdBreed">
            <table width="100%">
                <tr>
                    <td width="3%" height="5%" align="right">一级类别</td>
                    <td width="100px" align="left">
                        <select style="width:120px" id="level1" name="classestreeCode1">
                            <option value="${classesCode}" label="${classesName}"/>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td width="3%" height="5%" align="right">二级类别</td>
                    <td width="100px" align="left">
                        <select style="width:120px" id="level2" name="classestreeCode2">
                            <c:if test="${pd141103ParamBean.classestreeName2 != null and pd141103ParamBean.classestreeName2!=''}">
                                <option value="">${pd141103ParamBean.classestreeName2}</option>
                            </c:if>
                            <c:if test="${tree2List != null}">
                                <option value="" label="请选择"/>
                                <c:forEach items="${tree2List}" var="list">
                                    <option value="${list.classestreeCode}">${list.levelName}</option>
                                </c:forEach>
                            </c:if>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td width="3%" height="5%" align="right">三级类别</td>
                    <td width="100px" align="left">
                        <select style="width:120px" id="level3" name="classestreeCode3">
                            <option value="" label="${pd141103ParamBean.classestreeName3}"/>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td width="3%" height="5%" align="right">四级类别</td>
                    <td width="100px" align="left">
                        <select style="width:120px" id="level4" name="classestreeCode4">
                            <option value="" label="${pd141103ParamBean.classestreeName4}"/>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td width="3%" height="5%" align="right">五级类别</td>
                    <td width="100px" align="left">
                        <select style="width:120px" id="level5" name="classestreeCode5">
                            <option value="" label="${pd141103ParamBean.classestreeName5}"/>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td width="100px" style="display:none;" align="right">类别编码</td>
                    <td align="left" style="display:none;"><form:input path="classesCode" id="classesCode"/></td>
                    <td width="100px" style="display:none;" align="right">品种编码</td>
                    <td align="left" style="display:none;"><form:input path="breedCode" id="breedCode"/></td>
                    <td width="100px" align="right">品种名称</td>
                    <td align="left"><form:input path="breedName" id="breedName"/>
                    </td>
                </tr>
            </table>
        </form:form>
    </div>
    <div>
        <msk:button buttonValue="保存" buttonId="PD141103.SAVE" buttonType="button"/>
        <msk:button buttonValue="返回" buttonId="PD141103.BACK" url="${ctx}/PD141101/init" buttonType="button"/>
    </div>
</div>
<script src="${ctx}/static/js/pd/PD141103.js"></script>