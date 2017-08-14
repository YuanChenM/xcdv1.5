<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/comm/taglib.jsp" %>
<navigation:header title="查询所有批次号" backTitleArray="产品批次入库及标签打印" backUrlArray="${ctx}/SC182211/init"></navigation:header>
<div class="page-content list-page">
    <form action="${ctx}/SC182212/search" method="post" id="sc182212FormId">
        <%--<input type="hidden" name="filterMap[hideSlMainClass]" value="1">--%>
        <div>
            <table id="sc182212_list_grid" showAddBtn="true" width="100%">
                <thead>
                <tr>
                    <th coltype="text"  name="lotId" filter="false" >批次ID</th>
                    <th coltype="text"  name="slCode" filter="false">卖家编码</th>
                    <th coltype="text"  name="slName" filter="false" >卖家名称</th>
                    <th coltype="text"  name="pdCode" filter="false">产品编码</th>
                    <th coltype="text"  name="pdName" filter="false">产品名称</th>
                    <th coltype="text"  name="brand" filter="false">品牌</th>

                    <th coltype="text"  name="classesCode" filter="true">产品类别编码</th>
                    <th coltype="text"  name="classesName" filter="false">产品类别名称</th>

                    <th coltype="text"  name="machiningCode" filter="true">二级分类编码</th>
                    <th coltype="text"  name="machiningName" filter="false">二级分类名称</th>

                    <th coltype="text"  name="breedCode" filter="true">产品品种编码</th>
                    <th coltype="text"  name="breedName" filter="false">产品品种名称</th>

                    <th coltype="text"  name="featureCode" filter="true" >产品特征编码</th>
                    <th coltype="text"  name="featureName" filter="false">产品特征名称</th>

                    <th coltype="text"  name="weightCode" filter="true">净重编码</th>
                    <th coltype="text"  name="weightName" filter="false">净重名称</th>

                    <th coltype="text"  name="gradeCode" filter="true">等级编码</th>
                    <th coltype="text"  name="gradeName" filter="false">等级名称</th>

                    <th coltype="text"  name="pkgCode" filter="false">包装编码</th>
                    <th coltype="text"  name="pkgSpec" filter="false">包装规格</th>
                    <th coltype="text"  name="pkgWay" filter="false">包装方式</th>
                    <th coltype="text"  name="netWeight" filter="false" >净重</th>

                    <th coltype="text"  name="lgcsCode" filter="false" >物流区编码</th>
                    <th coltype="text"  name="lgcsName" filter="false">物流区名称</th>

                    <th coltype="text"  name="slCodeDis" filter="true">7位卖家编号</th>
                    <th coltype="text"  name="slCodeManufacture" filter="true">卖家生产商编码</th>
                    <th coltype="text"  name="salesPlatform" filter="true">销售平台编号</th>

                    <th coltype="text"  name="dateCode" filter="true">日期编码</th>

                    <th coltype="text"  name="pdStatus" filter="false">产品状态</th>
                    <th coltype="text"  name="origin" filter="false">原产地</th>
                    <th coltype="text"  name="prodcingArea" filter="false">产地</th>
                    <th coltype="text"  name="manufacturer" filter="false">厂家</th>
                    <th coltype="text"  name="pdTime" filter="false">生产时间</th>
                    <th coltype="text"  name="shelfLife" filter="false">保质期</th>
                    <th coltype="text"  name="processingWay" filter="false">加工方式</th>
                    <th coltype="text"  name="storageWay" filter="false">储存方式</th>
                    <th coltype="text"  name="crtTime" filter="false">插入时间</th>
                    <th coltype="action" >
                        <%--<input type="hidden" value="删除" coltype="delete"  title="删除该批次" class="h-button" />--%>
                        <%--<input type="hidden" value="删除" coltype="delete2"  title="删除该批次和对应的标签" class="h-button" />--%>
                        <msk:button buttonType="hidden" buttonValue="删除该批次" buttonId="SC182212.DELETE" coltype="delete" class="h-button" />
                        <msk:button buttonType="hidden" buttonValue="删除该批次和对应的标签" buttonId="SC182212.DELETE2" coltype="delete2" class="h-button"
                                    icon="../static/images/action/delete.png"/>

                    </th>
                </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
        </div>
    </form>
</div>

<script type="text/javascript" src="${ctx}/static/ds/js/SC182212.js"></script>
