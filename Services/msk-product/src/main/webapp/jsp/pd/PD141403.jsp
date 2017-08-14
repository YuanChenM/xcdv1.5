<%--
  Created by IntelliJ IDEA.
  User: xhy
  Date: 15/3/15
  Time: 上午11:11
  卖家产品一览
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<script type="text/javascript">
    var PD_CLASSES_NAME = '${beans.pdClassesName}';
    var MACHINING_NAME = '${beans.machiningName}';
    var PD_BREED_NAME = '${beans.pdBreedName}';
    var PD_FEATURE_NAME = '${beans.pdFeatureName}';
    var WEIGHT_NAME = '${beans.weightName}';
    var SL_CODE = '${beans.slCode}';
    var SL_CODE_DIS = '${beans.slCodeDis}';
    var SL_PD_ID = '${beans.slPdId}';
    var STANDARD_ID = '${beans.standardId}';
    var CLASSESTREE_CODE='${beans.classestreeCode}';
    var SHOW_DATE ='';
</script>
<style type="text/css">
    td.text{
        text-align: center;
    }
    table.dataTable td.action{
        text-align: center;
    }
</style>
<navigation:header title="卖家单个产品及状态在线管控一览" backTitleArray="卖家一览,卖家产品一览"
                   backUrlArray="${ctx}/PD141401/init,${ctx}/PD141402/init?slCode=${beans.slCode}&slCodeDis=${beans.slCodeDis}"></navigation:header>
<div class="page-content list-page">
    <table >
        <tr>
            <td style="width: 50%;padding:7px" >一级分类:　${beans.pdClassesName}</td>
        </tr>
        <tr>
            <td style="width: 50%;padding:7px">二级分类:　${beans.machiningName}</td>
        </tr>
        <tr>
            <td style="width: 50%;padding:7px">品种:　${beans.pdBreedName}</td>
        </tr>
        <tr>
            <td style="width: 50%;padding:7px">特征:　${beans.pdFeatureName}</td>
        </tr>
        <tr>
            <td align="left" style="padding:7px">净重:　${beans.weightName}</td>
            <td align="right"><msk:button buttonValue="新增检测" beans="${beans}" buttonId="PD141403.NEW" buttonType="button"/></td>
        </tr>
    </table>

    <div class="group-accordion" collapsible="false" active="false">
        <h3>
            <label>检测结果一览</label>
        </h3>
    <form:form action="${ctx}/PD141403/search" id="PD141403Form" method="post" >
        <input type="hidden" name="filterMap['slCode']" value="${beans.slCode}"/>
        <input type="hidden" name="filterMap['slCodeDis']" value="${beans.slCodeDis}"/>
        <input type="hidden" name="filterMap['slPdId']" value="${beans.slPdId}"/>
        <input type="hidden" name="filterMap['standardId']" value="${beans.standardId}">
        <input type="hidden" name="pdClassesName" value="${beans.pdClassesName}"/>
        <input type="hidden" name="machiningName" value="${beans.machiningName}"/>
        <input type="hidden" name="pdBreedName" value="${beans.pdBreedName}"/>
        <input type="hidden" name="pdFeatureName" value="${beans.pdFeatureName}"/>
        <input type="hidden" name="weightName" value="${beans.weightName}"/>
        <input type="hidden" name="classestreeCode" value="${beans.classestreeCode}"/>
            <table id="pd141403_list_grid" showAddBtn="true" width="100%">
                <thead>
                <tr>
                    <th coltype="text" width="35%" name="showDate" filter="false" align="center">检测时间</th>
                    <th coltype="text" width="35%" name="resultGrade" filter="false" align="center" >检测结果</th>
                    <th coltype="action">详细
                        <msk:button buttonValue="详细" buttonType="hidden" coltype="detail" class="h-button" buttonId="PD141403.DETAIL"/>
                    </th>
                </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
        </div>
    </form:form>
</div>
<script src="${ctx}/static/js/pd/PD141403.js"></script>
