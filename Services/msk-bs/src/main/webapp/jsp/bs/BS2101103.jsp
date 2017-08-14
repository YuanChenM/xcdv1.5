<%-- 
    Title:买手店管家会员列表
    author:cx
    createDate:2016-3-9
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<script type="text/javascript">
    var houseCodeDis = "${houseCodeDis}";
    var houseContact = "${houseContact}";
    var houseCode = "${houseCode}";
    var slContact = "${slContact}";
    var buyerId = "${buyerId}";
    var slCodeDis = "${slCodeDis}";
    var houseAccount = "${houseAccount}";
</script>
<%--<navigation:header title="买手店管家会员列表" backTitleArray="买手店列表,冻品管家列表" backUrlArray="${ctx}/BS2101101/init/,${ctx}/BS2101102/init"
                   backParamArray='/{"slCode":"${slCode}","slCodeDis":"${slCodeDis}","slContact":"${slContact}"}'></navigation:header>--%>


<c:if test="${flagNum == '1'}">
    <navigation:header title="冻品管家专属买家列表" backTitleArray="冻品管家一览,冻品管家管控"
                       backUrlArray="${ctx}/BS2102102/init,${ctx}/BS2102112/init/?slCode=${slCode}&houseCode=${houseCode}"
    ></navigation:header>
</c:if>
<c:if test="${flagNum == '2'}">
    <navigation:header title="冻品管家锁定期买家列表" backTitleArray="冻品管家一览,冻品管家管控"
                       backUrlArray="${ctx}/BS2102102/init,${ctx}/BS2102112/init/?slCode=${slCode}&houseCode=${houseCode}"
    ></navigation:header>
</c:if>
<c:if test="${flagNum == '10'}">
    <c:if test="${applyStatus == '2'}">
        <navigation:header title="冻品管家专属买家列表" backTitleArray="买手列表,冻品管家列表"
                           backUrlArray="${ctx}/BS2101101/init,${ctx}/BS2101102/init?slCode=${slCode}&slCodeDis=${slCodeDis}&slContact=${slContact}&flagNum=${flagNum}"
                ></navigation:header>
    </c:if>
    <c:if test="${applyStatus == '1'}">
        <navigation:header title="冻品管家锁定期买家列表" backTitleArray="买手列表,冻品管家列表"
                           backUrlArray="${ctx}/BS2101101/init,${ctx}/BS2101102/init?slCode=${slCode}&slCodeDis=${slCodeDis}&slContact=${slContact}&flagNum=${flagNum}"
                ></navigation:header>
    </c:if>
</c:if>
<c:if test="${flagNum == '11'}">
    <navigation:header title="买手管家会员列表" backTitleArray="买手列表,买手管控,冻品管家列表"
                       backUrlArray="${ctx}/BS2101101/init,${ctx}/BS2101199/init/?slCode=${slCode}&slCodeDis=${slCodeDis}&slContact=${slContact}&flagNum=${flagNum},${ctx}/BS2101102/init?slCode=${slCode}&slCodeDis=${slCodeDis}&slContact=${slContact}&flagNum=${flagNum}"
    ></navigation:header>
</c:if>
<div class="page-content list-page">
    <form action="${ctx}/BS2101103/search" method="post" id="bs2101103FormId">
        <input type="hidden" id="applyStatus" name="applyStatus" value="${applyStatus}"/>
        <div class="group-accordion" active="true">
            <h3>
                <label>查询条件</label>
            </h3>
            <table width="100%">
                <tr>
                    <td align="right">买手编码</td>
                    <td>
                        <input type="input" id="slCodeDis" value="${slCodeDis}" name="filterMap[slCodeDis]" readonly="readonly"/>
                        <input type="hidden" value="${slCode}" name="filterMap[slCode]">
                        <%--<input type="hidden" value="${buyerCode}" name="filterMap[buyerCode]">--%>
                        <input type="hidden" value="${houseCode}" name="filterMap[houseCode]">
                    </td>
                    <td align="right">买手名</td>
                    <td>
                        <input type="" id="slContact" value="${slContact}" readonly="readonly"/>
                    </td>
                </tr>
                <tr>
                    <td align="right">冻品管家编码</td>
                    <td>
                        <input type="" id="houseCodeDis" value="${houseCodeDis}" readonly="readonly"/>
                    </td>
                    <td align="right">冻品管家名</td>
                    <td>
                        <input type="" id="houseContact" value="${houseShowName}" readonly="readonly"/>
                    </td>
                </tr>
                <tr>
                    <td align="right">物流区</td>
                    <td>
                        <select style="width:120px" name="lgcsAreaCode" id="lgcsArea_select">
                            <option value="" label="请选择">请选择</option>
                            <c:forEach items="${LgcsArea}" var="lgcsArea" varStatus="i">
                                <option value="${lgcsArea.lgcsAreaCode}">${lgcsArea.lgcsAreaName}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td align="right">地区</td>
                    <td>
                        <select style="width:120px" name="cityCode" id="city_select">
                            <option value="" label="请选择">请选择</option>
                        </select>
                    </td>
                    <td align="right">区</td>
                    <td>
                        <select style="width:120px" name="districtCode" id="district_select">
                            <option value="" label="请选择">请选择</option>
                        </select>
                    </td>
                </tr>
            </table>
        </div>
        <table id="bs2101103_list_grid" showAddBtn="true" width="100%">
            <thead>
            <tr>
               <%-- <th coltype="text" width="10%" name="buyerId" filter="true">买家ID</th>--%>
                <th coltype="text" width="10%" name="buyerCode" filter="true">买家编码</th>
                <th coltype="text" width="10%" name="buyerName" filter="true">买家名</th>
                <th coltype="text" width="10%" name="cityName" filter="">所属行政区域</th>
                <th coltype="text" width="10%" name="buyerAddr" filter="true">买家地址</th>
                <th coltype="text" width="10%" name="busiTel" filter="true">联系电话</th>
              <%--  <th coltype="text" width="10%" name="applyStatus" filter="true"></th>--%>
                <th coltype="select" width="10%" name="applyStatusName" filter="true">状态
                    <select>
                        <option value="1">锁定期</option>
                        <option value="2">专属会员</option>
                    </select>
                </th>
                <%--<th coltype="text" width="10%" name="applyTime" filter="">申请日期</th>--%>
                <th coltype="text" width="80px" name="startTime" filter="">开始日期</th>
                <%--<th coltype="text" width="10%" name="endTime" filter="">结束日期</th>--%>
                <th coltype="action" width="60px">解除关系
                    <msk:button buttonValue="解除关系" buttonType="hidden" coltype="check" title="解除关系" class="h-button" buttonId="BS2101103.ADD"/>
                    <%--<input type="hidden" value="解除关系" coltype="add" title="解除关系" class="h-button"/>--%>
                </th>
                   <c:if test="${applyStatus eq 1}">
                       <th coltype="action" width="60px">成为专属买家
                           <msk:button buttonValue="成为专属买家" buttonType="hidden" coltype="edit" title="成为专属买家" class="h-button" buttonId="BS2101103.EDIT"/>
                               <%--<input type="hidden" value="成为专属买家" coltype="edit" title="成为专属买家" class="h-button"/>--%>
                       </th>
                   </c:if>
            </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
    </form>
</div>
<script src="${ctx}/static/bs/js/BS2101103.js"></script>