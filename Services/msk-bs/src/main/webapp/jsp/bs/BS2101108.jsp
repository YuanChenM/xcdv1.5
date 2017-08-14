<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
    Title:买手囤货联盟申请
    author:gyh
    createDate:2016-3-3
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<navigation:header title="买手囤货联盟申请" backTitleArray="买手店列表" backUrlArray="${ctx}/BS2101101/init/"></navigation:header>
<style type="text/css">
    select {
        width: 130px;
    }

    .tdHeight {
        color: #8A8A8A;
        padding-top: 15px;
    }
</style>
<script type="text/javascript">
    var slCode='${bs2101101Bean.slCode}';
</script>
<div class="page-content list-page">
    <div class="group-accordion" active="true" collapsible="false">
        <h3>
            <label>买手囤货联盟申请信息编辑</label>
        </h3>

        <form action="${ctx}/BS2101108/save" method="post" id="BS2101108FormId">
            <div>
                <table>
                    <tr>
                        <td>买手编码</td>
                        <td align="left"><input type="text" value="${bs2101101Bean.slCodeDis}" readonly="readonly"></td>
                        <td align="right">买手姓名</td>
                        <td align="left"><input type="text" value="${bs2101101Bean.slContact}" readonly="readonly"></td>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td colspan="3" class="tdHeight">买手囤货联盟申请-买手列表</td>
                    </tr>
                    <tr>
                        <td>手买联盟方</td>
                        <td align="left"><input type="text" id="buyerother" readonly="readonly"></td>
                        <td><msk:button buttonValue="查询" buttonId="BS2101108.SEARCH" buttonType="button"/></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td colspan="3" class="tdHeight">囤货产品</td>
                    </tr>
                    <tr>
                        <td>产品一级分类</td>
                        <td align="left">
                            <select id="pdClasses" name="pdClasses">
                                <option value="">请选择</option>
                                <c:forEach items="${pdClassess}" var="pdClasses">
                                    <option value="${pdClasses.classesCode}">${pdClasses.classesName}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td align="right">产品二级分类</td>
                        <td align="left">
                            <select id="pdMachining" name="pdMachining">
                                <option value="">请选择</option>
                            </select>
                        </td>
                        <td align="right">产品品种</td>
                        <td align="left">
                            <select name="pdBreed" id="pdBreed">
                                <option value="">请选择</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>产品特征</td>
                        <td align="left">
                            <select name="pdFeature" id="pdFeature">
                                <option value="">请选择</option>
                            </select>
                        </td>
                        <td align="right">包装净重</td>
                        <td align="left">
                            <select name="pdWeight" id="pdWeight">
                                <option value="">请选择</option>
                            </select>
                        </td>
                        <td align="right">产品等级</td>
                        <td align="left">
                            <select name="pdGrade" id="pdGrade">
                                <option value="">请选择</option>
                                <c:forEach items="${pdGrades}" var="pdGrade">
                                    <option value="${pdGrade.gradeCode}">${pdGrade.gradeName}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>价差百分比</td>
                        <td align="left"><input type="text"></td>
                        <td colspan="2" align="left">%(囤货货主方与囤货共同使用方的囤货价差分配比例</td>
                        <td align="right">合营优先顺序</td>
                        <td align="left"><input type="number" min="0"/></td>
                    </tr>
                    <tr>
                        <td colspan="4"></td>
                        <td><msk:button buttonValue="作为货主申请" buttonId="BS2101108.APPLY" buttonType="button"/></td>
                        <td><msk:button buttonValue="作为囤货共同使用方申请" buttonId="BS2101108.APPLYTO"
                                        buttonType="button"/></td>
                    </tr>
                </table>
            </div>
        </form>
    </div>
    <div class="group-accordion" active="true" collapsible="false">
        <h3>
            <label>买手囤货联盟信息列表</label>
        </h3>
        <form action="${ctx}/BS2101108/search" method="post" id="BS2101108FormId2">
            <div>
                <table id="BS2101108_list_grid" showAddBtn="true" width="100%">
                    <thead>
                    <tr>
                        <th coltype="text" width="15%" name="classesName" filter="">产品一级分类</th>
                        <th coltype="text" width="15%" name="machiningName" filter="">产品二级分类</th>
                        <th coltype="text" width="15%" name="breedName" filter="">产品品种</th>
                        <th coltype="text" width="15%" name="featureName" filter="">产品特征</th>
                        <th coltype="text" width="15%" name="weightName" filter="">包装净重</th>
                        <th coltype="text" width="15%" name="gradeName" filter="">产品等级</th>
                        <th coltype="text" width="15%" name="divide" filter="">价差分红比例</th>
                        <th coltype="text" width="15%" name="ownerSlInfo[slContact]" filter="">提出申请人</th>
                        <th coltype="text" width="15%" name="ownerSlInfo[slContact]" filter="">货主/共同使用方</th>
                        <th coltype="text" width="15%" name="allianceSlInfo[slContact]" filter="">联盟方姓名</th>
                        <th coltype="text" width="15%" name="allianceSlInfo[areaName]" filter="">联盟方行政区划</th>
                        <th coltype="text" width="15%" name="allianceSlInfo[slTel]" filter="">联盟方联系电话</th>
                        <th coltype="text" width="15%" name="applyTime" filter="">申请时间</th>
                        <th coltype="text" width="15%" name="applyStatus" filter="">申请状态</th>
                        <th coltype="text" width="15%" name="applyStatus" filter="">拒绝理由</th>
                        <th coltype="action" width="60px">同意
                            <msk:button buttonValue="同意"  buttonType="hidden" coltype="add" title="同意" class="h-button" buttonId="BS2101108.ADD"/>
                            <%--<input type="hidden" value="同意" coltype="add" title="同意" class="h-button"/>--%>
                        </th>
                        <th coltype="action" width="60px">拒绝
                            <msk:button buttonValue="拒绝"  buttonType="hidden" coltype="edit" title="拒绝" class="h-button" buttonId="BS2101108.REJE"/>
                            <%--<input type="hidden" value="拒绝" coltype="edit" title="拒绝" class="h-button"/>--%>
                        </th>
                        <th coltype="action" width="60px">删除
                            <msk:button buttonValue="删除"  buttonType="hidden" coltype="edit" title="删除" class="h-button" buttonId="BS2101108.DEL"/>
                            <%--<input type="hidden" value="删除" coltype="edit" title="删除" class="h-button"/>--%>
                        </th>
                        <th coltype="action" width="60px">修改分红比例再申请
                            <input type="hidden" value="修改分红比例再申请" coltype="edit" title="成为抢单买家" class="h-button"/>
                        </th>
                    </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
            </div>
        </form>
    </div>
</div>
<script src="${ctx}/static/bs/js/BS2101108.js"></script>