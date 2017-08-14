<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<%@ taglib prefix="msk2" uri="/msk" %>

<!-- Modified by xia_xiaojie on 2016/6/16. Modified start. -->
<%@ taglib prefix="mskcm" uri="/codemaster" %>
<!-- Modified end. -->

<script type="text/javascript">
    function callbackFun2(message, slAccount) {
        $.alertMessage.info(message);
        HDF.closeLoadingMask();
        $.pdialog.close();
        $('#main-content').postUrl(Main.contextPath + "/SL24110100/init/" + slAccount);
    }
</script>
<div class="page-content detail-page" id="SL2411030081_id">
    <form:form action="${ctx}/SL2411030081/insert" id="SL2411030081Form"
               metdod="post" enctype="multipart/form-data">
        <input type="hidden" id="crtId" name="crtId" value="${loginUser.emplId}"/>
        <input type="hidden" id="updId" name="updId" value="${loginUser.emplId}"/>
        <table id="SL2411030081DataGrid">
            <tr>
                <td align="right" width="50%">职务</td>
                <td align="left" width="50%">
                    <!-- Modified by xia_xiaojie on 2016/6/17. Modified start. -->
                        <%-- <select name="memberDuties" id="memberDuties" style="width:135px;">
                            <option value="">--请选择--</option>
                                <c:forEach items="${EpTeam}" var="list" varStatus="i">
                                    <option value="${list.constantValue}">${list.constantName}</option>
                                </c:forEach>
                            &lt;%&ndash;<option value="1">公司董事长</option>
                            <option value="2">公司总经理</option>
                            <option value="3">公司副总经理</option>
                            <option value="4">销售部经理</option>
                            <option value="5">品控部经理</option>&ndash;%&gt;
                        </select> --%>
                    <mskcm:codemaster codeType="TeamPosition" id="memberDuties" name="memberDuties" viewType="select"
                                      style="width:135px;"/>
                    <!-- Modified end. -->
                </td>
            </tr>

            <tr>
                <td align="right" width="50%">姓名</td>
                <td align="left" width="50%">
                    <input type="text" id="memberName" name="memberName" maxlength="20"/>
                </td>
            </tr>

            <tr>
                <td align="right" width="50%">年龄</td>
                <td align="left" width="50%">
                    <input type="text" id="memberAge" name="memberAge" maxlength="3" digits="true"
                           digitsMessage="年龄为整数"/>
                </td>
            </tr>
            <tr>
                <td align="right" width="50%">联系方式</td>
                <td align="left" width="50%">
                    <input type="text" id="memberTel" name="memberTel" maxlength="20"/>
                </td>
            </tr>
            <tr>
                <td align="right" width="50%">教育程度</td>
                <td align="left" width="50%">
                    <input type="text" id="memberEduc" name="memberEduc" maxlength="20"/>
                </td>
            </tr>
            <tr>
                <td align="right" width="50%">头像上传</td>
                <td align="left" width="50%">
                    <input type="file" name="file" id="image"/>
                </td>
            </tr>

            <tr>
                <td align="right">
                    <msk2:button buttonType="button" buttonId="SL2411030081.SAVE" buttonValue="保存"/>
                        <%--<msk:button buttonValue="保存" buttonId="SL2411030081.SAVE" buttonType="button"/>--%>
                </td>
                <td align="left">
                    <msk2:button buttonType="button" buttonId="SL2411030081.BACK" buttonValue="取消"/>
                        <%--<msk:button buttonValue="取消" buttonId="SL2411030081.BACK" buttonType="button"/>--%>
                </td>
            </tr>

        </table>
    </form:form>
</div>
<script src="${ctx}/static/sl/js/SL2411030081.js"></script>
