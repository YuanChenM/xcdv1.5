<%-- 
    Title:标准档案卡列表
    author:jiang_nan
    createDate:2015-12-09
    updateDate:2015-12-09
    updateAuthor:jiang_nan
    updateDate:2015-12-23
    updateAuthor:gyh
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/comm/taglib.jsp" %>
<script type="text/javascript">
    var BREED_CODE = "${breedCode}";
    var CLASSES_CODE = "${classesCode}";
    var FEATURE_CODE = "${featureCode}";
    var INFO = "${yesOrNo}";
</script>
<c:if test="${yesOrNo != 'yes' }">
     <navigation:header title="标准档案卡列表" backTitleArray="" backUrlArray="" ></navigation:header>
 </c:if>
<c:if test="${yesOrNo == 'yes'}">
    <navigation:header title="标准档案卡列表" backTitleArray="产品类别信息" backUrlArray="${ctx}/PD141101/init?classesCode=${classesCode}&breedCode=${breedCode}&featureCode=${featureCode}" ></navigation:header>
</c:if>
<div class="page-content list-page">
    <form action="${ctx}/PD141113/search" method="post" >
        <table id="PD141113_Grid">
            <thead>
                <tr>
                    <th coltype="sno" width="20px">编号</th>
                    <th coltype="text" name="classesCode" align="center" defaultValue="${info.classesCode}" filter=true>类别编号</th>
                    <th coltype="text" name="classesName" filter=true>类别名称</th>
                    <th coltype="text" name="breedCode" defaultValue="${info.breedCode}" filter=true>品种编号</th>
                    <th coltype="text" name="breedName" filter=true>品种名称</th>
                    <th coltype="text" name="featureCode" defaultValue="${info.featureCode}" filter=true>特征编号</th>
                    <th coltype="text" name="featureName" filter=true>特征名称</th>
                    <th coltype="select" name="qltFlg" filter=true>质量标准
                        <select>
                            <option value="1">有</option>
                            <option value="0">无</option>
                        </select>
                    </th>
                    <th coltype="select" name="tncFlg" filter=true>技术标准
                        <select>
                            <option value="1">有</option>
                            <option value="0">无</option>
                        </select>
                    </th>
                    <th coltype="select" name="norFlg" filter=true>包装标准
                        <select>
                            <option value="1">有</option>
                            <option value="0">无</option>
                        </select>
                    </th>
                    <th coltype="action">
                        <msk:button buttonValue="包装标准" buttonType="hidden" coltype="detail" class="h-button" buttonId="PD141113.DETAIL"/>
                        <msk:button buttonValue="质量标准" buttonType="hidden" coltype="check" class="h-button" buttonId="PD141113.CHECK"/>
                        <msk:button buttonValue="技术标准" buttonType="hidden" coltype="edit" class="h-button" buttonId="PD141113.EDIT"/>
                    </th>
                </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
    </form>
   <%-- <msk:button buttonValue="返回" buttonId="PD141113.RETURN" buttonType="button"/>--%>
</div>
<script type="text/javascript" src="${ctx}/static/js/pd/PD141113.js" ></script>