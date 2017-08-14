<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
    Title:卖家货号列表
    author:pxg
    createDate:2016-1-27
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<%@ taglib prefix="msk2" uri="/msk" %>
<navigation:header title="卖家货号列表" backTitleArray="卖家信息列表" backUrlArray="${ctx}/SL241101/initShow"></navigation:header>
<div class="page-content list-page">
    <form:form action="${ctx}/SL241132/search" id="SL241132Form" method="post">
            <input type="hidden" name="filterMap[slCode]" value="${slCode}"/>
            <div>
                <table id="SL241132_list_grid">
                    <thead>
                    <tr>
                        <th coltype="text" width="7%" name="slPdArtno" filter="true">卖家货号码</th>
                        <th coltype="text" width="7%" name="slCodeDis" filter="false">卖家编码</th>
                        <th coltype="text" width="7%" name="pdClassesName" filter="false">产品一级分类</th>
                        <th coltype="text" width="7%" name="machiningName" filter="false">产品二级分类</th>
                        <th coltype="text" width="7%" name="pdBreedName" filter="false">产品品种</th>
                        <th coltype="text" width="7%" name="pdFeatureName" filter="false">产品特征</th>
                        <th coltype="text" width="7%" name="weightName" filter="false">包装净重</th>
                        <th coltype="text" width="7%" name="normsName" filter="false">包装名称</th>
                        <th coltype="text" width="7%" name="salePlatformName" filter="false">销售平台</th>
                        <th coltype="text" width="7%" name="epName" filter="false">品牌商</th>
                        <th coltype="text" width="7%" name="brandName" filter="false">品牌名称</th>
                        <th coltype="text" width="7%" name="manufactureCode" filter="false">生产商编码</th>
                        <th coltype="text" width="7%" name="pdCountryName" filter="false">国别</th>
                        <th coltype="text" width="7%" name="pdPlace" filter="false">产地</th>
                        <th coltype="text" width="7%" name="pdStandard" filter="false">产品标准号</th>
                        <th coltype="text" width="7%" name="saleStatusName" filter="false">销售状态
                        </th>
                        <th coltype="action" width="60px">
                            <msk2:button buttonType="hidden" buttonId="SL241132.DETAILBTN" coltype="detail" buttonValue="详细" class="h-button"/>
                            <%--<input type="hidden" coltype="detail" title="详细" class="h-button" />--%>
                        </th>
                    </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
            </div>
    </form:form>
</div>
</div>
<script src="${ctx}/static/sl/js/SL241132.js"></script>
