<%-- 
    Title:产品类别信息页面
    author:gyh
    createDate:2015-12-09
    updateDate:2015-12-20
    updateAuthor:gyh
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<link rel="stylesheet" href="${ctx}/static/js/treegrid/css/jquery.treegrid.css">
<script type="text/javascript" src="${ctx}/static/js/treegrid/js/jquery.treegrid.js"></script>
<style>
    .tree .row_active {
        background-color: #B9D3EE;
    }
</style>
<script type="text/javascript">
    var BREED_CODE = "${breedCode}";
    var CLASSES_CODE = "${classesCode}";
    var FEATURE_CODE = "${featureCode}";
    var INFO = "${yesOrNo}";
</script>
<navigation:header title="产品类别信息" backTitleArray="" backUrlArray=""></navigation:header>
<div class="page-content list-page">
    <div class="group-accordion" collapsible="false">
        <h3>
            <label>查询条件</label>
        </h3>
        <form:form action="${ctx}/PD141101/init" id="PD141101Form" method="post" commandName="pdFeature"
                   modelAttribute="pdFeature">
            <table width="100%">
                <tr>
                    <td width="50px" align="right">类别</td>
                    <td align="left"><form:input path="classesCode" id="classesCode"/></td>
                    <td width="50px" align="right">品种</td>
                    <td align="left"><form:input path="breedCode" id="breedCode"/></td>
                    <td width="50px" align="right">特征</td>
                    <td align="left"><form:input path="featureCode" id="featureCode"/></td>
                    <td align="left"><msk:button buttonValue="查询" buttonId="PD141101.SEARCH" buttonType="button"/></td>
                </tr>
            </table>
        </form:form>
    </div>

    <div class="group-accordion" collapsible="false" active="false" style="height:75%; width:100%;overflow:auto; ">
        <h3 style="position: relative">
            <label>产品分类信息</label>
        </h3>

        <div style="padding-left: 0px;padding-top: 0px;padding-bottom: 0px;padding-right: 0px;">
            <table class="tree dataTable no-footer" style="width: 100%">
                <tr style="background:#DBDBDB">
                    <td name="newClasses" width="20%">编码</td>
                    <td width="20%">名称</td>
                    <td width="20%">废除标志</td>
                    <td width="40px">操作</td>
                </tr>
                <c:forEach items="${productClasses}" var="pdBaseData">
                    <!-- 显示品种类别 -->
                    <tr class="treegrid-${pdBaseData.classesCode}">
                        <td name="classes" value="${pdBaseData.classesCode}">${pdBaseData.classesCode}</td>
                        <td>${pdBaseData.classesName}</td>
                        <td>${pdBaseData.delFlg}</td>
                        <td width="40px"></td>
                    </tr>
                    <!-- 循环显示品种种类 -->
                    <c:forEach items="${pdBaseData.pdBreedList}" var="pdBreed">
                        <c:if test="${not empty pdBreed.breedCode}">
                            <tr class="treegrid-${pdBreed.classesCode}${pdBreed.breedCode} treegrid-parent-${pdBreed.classesCode}">
                                <td name="breed" value="${pdBreed.breedCode}">${pdBreed.breedCode}</td>
                                <td style="display:none;" value="${pdBreed.classesCode}"></td>
                                <td style="display:none;" value="${pdBreed.classestreeCode}"></td>
                                <td>${pdBreed.breedName}</td>
                                <td>${pdBreed.delFlg}</td>
                                <td><a href="javascript:void(0)" name="PD141113" classesCode="${pdBreed.classesCode}"
                                       breedCode="${pdBreed.breedCode}" featureCode="">标准产品注册</a></td>
                            </tr>
                        </c:if>
                        <!-- 循环显示品种特征 -->
                        <c:forEach items="${pdBreed.pdFeatureList}" var="pdFeature">
                            <c:if test="${not empty pdFeature.featureCode}">
                                <tr class="treegrid-${pdFeature.classesCode}${pdFeature.breedCode}${pdFeature.featureCode} treegrid-parent-${pdFeature.classesCode}${pdFeature.breedCode}">
                                    <td name="feature" value="${pdFeature.featureCode}">${pdFeature.featureCode}</td>
                                    <td style="display:none;" value="${pdFeature.classesCode}"></td>
                                    <td style="display:none;" value="${pdFeature.breedCode}"></td>
                                    <td>${pdFeature.featureName}</td>
                                    <td>${pdFeature.delFlg}</td>
                                    <td><a href="javascript:void(0)" name="PD141113"
                                           classesCode="${pdFeature.classesCode}" breedCode="${pdFeature.breedCode}"
                                           featureCode="${pdFeature.featureCode}">标准产品注册</a></td>
                                </tr>
                            </c:if>
                        </c:forEach>
                    </c:forEach>
                </c:forEach>
            </table>
        </div>
    </div>
    <div>
        <table>
            <tr>
                <td><msk:button buttonValue="新建" buttonId="PD141101.NEW" buttonType="button"/></td>
                <td><msk:button buttonValue="修改" buttonId="PD141101.MODIFY" buttonType="button"/></td>
                <td><msk:button buttonValue="删除" buttonId="PD141101.DELETE" buttonType="button"/></td>
                <td><msk:button buttonValue="废除" buttonId="PD141101.REMOVE" buttonType="button"/></td>
            </tr>
        </table>
    </div>
</div>
</div>
<script src="${ctx}/static/js/pd/PD141101.js"></script>
