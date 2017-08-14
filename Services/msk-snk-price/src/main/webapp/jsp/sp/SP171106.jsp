<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<%@ taglib prefix="msk2" uri="/codemaster" %>
<msk2:codemaster codeType="GradeCode" viewType="json" />
<msk2:codemaster codeType="Role" viewType="json" />
<navigation:header title="供应商数量申报详细" backTitleArray="供应商待申报产品一览" backUrlArray="${ctx}/SP171105/init"></navigation:header>
<div class="page-content">
    <form:form action="${ctx}/SP171106/init" id="SP171106Form" method="post">
        <div class="group-accordion" active="true">
            <h3>
                <label>申报详细</label>
            </h3>
            <div>
                <input type="hidden" name="demandId" id="demandId" value="${sp171106Param.demandId}"/>
                <input type="hidden" name="demandYearMonth" id="demandYearMonth" value="${sp171106Param.demandYearMonth}"/>
                <input type="hidden" name="lgcsCode" id="lgcsCode" value="${sp171106Param.lgcsCode}"/>
                <input type="hidden" name="passNum" id="passNum" value="${sp171106Param.passNum}"/>
                <input type="hidden" name="slAccount" id="slAccount" value="${sp171106Param.slAccount}"/>
                <input type="hidden" name="slId" id="slId" value="${sp171106Param.slId}"/>
                <input type="hidden" name="slCode" id="slCode" value="${sp171106Param.slCode}"/>
                <input type="hidden" name="epId" id="epId" value="${sp171106Param.epId}"/>
                <input type="hidden" name="epName" id="epName" value="${sp171106Param.epName}"/>
                <input type="hidden" name="canSetFlg" id="canSetFlg" value="${sp171106Param.canSetFlg}">
                <input type="hidden" name="updateHisFlg" id="updateHisFlg" value="${sp171106Param.updateHisFlg}"/>
                <input type="hidden" name="gradeCode" id="gradeCode" value="${sp171106Param.gradeCode}"/>
                <input type="hidden" name="applyNumHidden" id="applyNumHidden" value="${sp171106Param.applyNum}">
                <input type="hidden" name="ver" id="ver" value="${sp171106Param.ver}">
                <input type="hidden" name="pdCode" id="pdCode" value="${sp171106Param.pdCode}">

                <table class="dataTable no-footer">
                    <tr>
                        <td colspan="6" align="left">
                            <input type="text" name="lgcsName" style="border-style: solid; border-width: 0;" value="${sp171106Param.lgcsName}" readonly="readonly"/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" align="left">
                            <input type="text" name="demandYearMonthShow" style="border-style: solid; border-width: 0; width: 100%" value="${sp171106Param.demandYearMonthShow}" readonly="readonly"/>
                        </td>
                        <td coltype="text" align="left" width="10%">填报时间</td>
                        <td colspan="3" align="left">
                            <input type="text" name="demandLimitedDateShow" style="border-style: solid; border-width: 0;width: 180px;" value="${sp171106Param.demandLimitedDateShow}" readonly="readonly"/>
                        </td>
                    </tr>
                    <tr>
                        <td coltype="text" align="left">产品编码:</td>
                        <td coltype="text" align="left">
                            <input type="text" name="pdTypeCode" id="pdTypeCode" style="border-style: solid; border-width: 0;" value="${sp171106Param.pdTypeCode}" readonly="readonly"/>
                        </td>

                        <td coltype="text" align="left">产品一级分类:</td>
                        <td coltype="text" align="left" width="20%">
                            <input type="text" name="classesName" style="border-style: solid; border-width: 0;" value="${sp171106Param.classesName}" readonly="readonly"/>
                        </td>
                        <td coltype="text" align="left" width="10%">产品二级分类:</td>
                        <td coltype="text" align="left">
                            <input type="text" name="machiningName" style="border-style: solid; border-width: 0;" value="${sp171106Param.machiningName}" readonly="readonly"/>
                        </td>
                    </tr>
                    <tr>
                        <td coltype="text" align="left" width="10%">品种:</td>
                        <td coltype="text" align="left" width="20%">
                            <input type="text" name="breedName" style="border-style: solid; border-width: 0;width: 100%" value="${sp171106Param.breedName}" readonly="readonly"/>
                        </td>
                        <td coltype="text" align="left">特征</td>
                        <td coltype="text" align="left">
                            <input type="text" name="featureName" style="border-style: solid; border-width: 0;" value="${sp171106Param.featureName}" readonly="readonly"/>
                        </td>
                        <td coltype="text" align="left">产品等级</td>
                        <td coltype="text" align="left">
                            <msk2:codemaster codeType="GradeCode" viewType="label" codeValue="${sp171106Param.gradeCode}" />
                        </td>
                    </tr>
                    <tr>
                        <td coltype="text" align="left">状态</td>
                        <td align="left" colspan="5">
                            <msk2:codemaster codeType="IsConfirm" viewType="label" codeValue="${sp171106Param.isConfirm}" />
                        </td>
                    </tr>
                </table>
                <table class="tree dataTable no-footer" style="margin-top: 20px;" align="center">
                    <thead>
                    <tr height="30px">
                        <td coltype="text" width="20%" align="center">等级</td>
                        <td coltype="text" width="15%">分配平衡系数（%）</td>
                        <td coltype="text" width="15%">A1品（${sp171106Param.gradeRatio1}%）</td>
                        <td coltype="text" width="15%">A2品（${sp171106Param.gradeRatio2}%）</td>
                        <td coltype="text" width="15%">A3品（${sp171106Param.gradeRatio3}%）</td>
                        <td coltype="text" width="20%">总量合计</td>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td align="center">超级大宗订单</td>
                        <td align="center" rowspan="3">${sp171106Param.wayRatio1}</td>
                        <td align="center" rowspan="3">${sp171106Param.a1Way1Num}</td>
                        <td align="center" rowspan="3">${sp171106Param.a2Way1Num}</td>
                        <td align="center" rowspan="3">${sp171106Param.a3Way1Num}</td>
                        <td align="center" rowspan="3">${sp171106Param.way1Num}</td>
                    </tr>
                    <tr>
                        <td align="center">大宗订单1级</td>
                    </tr>
                    <tr>
                        <td align="center">大宗订单2级</td>
                    </tr>
                    <tr>
                        <td align="center">大额订单3级</td>
                        <td align="center" rowspan="3">${sp171106Param.wayRatio2}</td>
                        <td align="center" rowspan="3">${sp171106Param.a1Way2Num}</td>
                        <td align="center" rowspan="3">${sp171106Param.a2Way2Num}</td>
                        <td align="center" rowspan="3">${sp171106Param.a3Way2Num}</td>
                        <td align="center" rowspan="3">${sp171106Param.way2Num}</td>
                    </tr>
                    <tr>
                        <td align="center">大额订单4级</td>
                    </tr>
                    <tr>
                        <td align="center">大额订单5级</td>
                    </tr>
                    <tr>
                        <td align="center">标准订单6级</td>
                        <td align="center" rowspan="4">${sp171106Param.wayRatio3}</td>
                        <td align="center" rowspan="4">${sp171106Param.a1Way3Num}</td>
                        <td align="center" rowspan="4">${sp171106Param.a2Way3Num}</td>
                        <td align="center" rowspan="4">${sp171106Param.a3Way3Num}</td>
                        <td align="center" rowspan="4">${sp171106Param.way3Num}</td>
                    </tr>
                    <tr>
                        <td align="center">标准订单7级</td>
                    </tr>
                    <tr>
                        <td align="center">标准订单8级</td>
                    </tr>
                    <tr>
                        <td align="center">标准订单9级</td>
                    </tr>
                    <tr>
                        <td align="center" colspan="2">通道合计</td>
                        <td align="center">${sp171106Param.a1Num}</td>
                        <td align="center">${sp171106Param.a2Num}</td>
                        <td align="center">${sp171106Param.a3Num}</td>
                        <td align="center">${sp171106Param.publishTotalNum}</td>
                    </tr>
                    </tbody>
                </table>
                <table class="dataTable no-footer" style="margin-top: 20px;">
                    <tr>
                        <td align="left" width="10%">采购员建议</td>
                        <td align="left">
                            ${sp171106Param.passNum}
                            <c:if test="${!empty sp171106Param.passNum}">
                                &nbsp;吨
                            </c:if>
                        </td>
                    </tr>
                    <tr>
                        <td align="left">申报数量</td>
                        <td>
                            <input type="text" name="applyNum" id="applyNum" value="${sp171106Param.applyNum}">&nbsp;吨
                        </td>
                    </tr>
                    <tr>
                        <td align="left" colspan="2">备注</td>
                    </tr>
                    <tr>
                        <td align="left" colspan="2">
                            <textarea name="remark" id="remark" value="${sp171106Param.remark}" style="width: 70%;resize: none;"></textarea>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
    </form:form>
<form:form action="${ctx}/SP171106/search?demandId=${sp171106Param.demandId}" id="SP171106Search" method="post">
        <div class="group-accordion" active="true">
            <h3>
                <label>磋商履历</label>
            </h3>
            <table id="SP171106_list_grid">
                <thead>
                <tr>
                    <th coltype="sno" width="40px">序号</th>
                    <th coltype="text" width="10%" name="editTime">编辑时间</th>
                    <th coltype="code" width="10%" name="roleCode" code2name="ROLE_JSON">编辑人</th>
                    <th coltype="code" width="10%" name="gradeCode" code2name="GRADECODE_JSON">申报等级</th>
                    <th coltype="text" width="10%" name="modifyNum">申报或建议数量(吨)</th>
                    <th coltype="text" width="30px" name="remark">备注</th>
                </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
        </div>
</form:form>
        <msk:button buttonType="button" buttonId="SP171106.SAVE" buttonValue="提交"/>
    <%-- Modfiy:  一览页面到详情页面打开方式横展开,将返回按钮注释   2016/10/11   BY  zhukai1  Start --%>
        <%--<msk:button buttonType="button" buttonId="SP171106.BACK" url="${ctx}/SP171105/init" buttonValue="返回"/>--%>
    <%-- Modfiy:  一览页面到详情页面打开方式横展开,将返回按钮注释   2016/10/11   BY  zhukai1  end --%>
        <msk:button buttonType="button" buttonId="SP171106.OTHER" buttonValue="其他供应商申报数量"/>
</div>
<script src="${ctx}/static/sp/js/SP171106.js"></script>
