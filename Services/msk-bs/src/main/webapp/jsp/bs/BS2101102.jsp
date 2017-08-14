<%--
    Title:冻品管家列表
    author:cx
    createDate:2016-3-8
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<script type="text/javascript">
    var slCode = "${slCode}";
    var slCodeDis = "${slCodeDis}";
    var slContact = "${slContact}";
    var buyerId = "${buyerId}";
    var provinceCode1 = "${provinceCode1}";
    var cityCode1 = "${cityCode1}";
    var districtCode1 = "${districtCode1}";
    var slAccount = "${slAccount}";
</script>
<style>
    #BS2102102_SAVE{
        width: 50px;
    }

</style>
<c:if test="${flagNum == '10'}">
    <navigation:header title="冻品管家列表" backTitleArray="买手列表" backUrlArray="${ctx}/BS2101101/init"></navigation:header>
</c:if>
<c:if test="${flagNum == '11'}">
    <navigation:header title="冻品管家列表" backTitleArray="买手列表,买手管控"
                       backUrlArray="${ctx}/BS2101101/init,${ctx}/BS2101199/init/?slAccount=${slAccount}&slCode=${slCode}&slCodeDis=${slCodeDis}&slContact=${slContact}&flagNum=${flagNum}"></navigation:header>
</c:if>
<div class="page-content list-page">
    <form action="${ctx}/BS2101102/search/" method="post" id="bs2101102FormId">
        <input type="hidden" id="hidFlagNum" name="hidFlagNum" value="${flagNum}">
        <div class="group-accordion" active="true">
            <h3>
                <label>冻品管家列表查询条件</label>
            </h3>
            <table width="100%">
                <tr>
                    <td align="right">买手编码</td>
                    <td>
                        <input type="input" id="slCodeDis" value="${slCodeDis}" name="filterMap[slCodeDis]"
                               readonly="readonly"/>
                        <input type="hidden" value="${slCode}" name="filterMap[slCode]">
                        <input type="hidden" value="${houseCode}" name="filterMap[houseCode]">
                        <input type="hidden" value="${provinceCode}">
                        <input type="hidden" value="${cityCode}">
                        <input type="hidden" value="${districtCode}">
                    <td align="right">买手名</td>
                    <td>
                        <input type="" id="slContact" value="${slContact}" readonly="readonly"/>
                    </td>
                </tr>
                <tr>
                    <td align="right">省</td>
                    <td >
                        <select style="width:120px" name="provinceCode1" id="province">
                            <option value="0">请选择</option>
                            <c:forEach items="${mdProvinces}" var="mdProvince" varStatus="i">
                                <c:choose>
                                    <c:when test="${mdProvince.provinceCode eq provinceCode1}">
                                        <option value="${mdProvince.provinceCode}"
                                                selected="selected">${mdProvince.provinceName}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${mdProvince.provinceCode}">${mdProvince.provinceName}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </td>
                    <td align="right">地区</td>
                    <td >
                        <select style="width:120px" name="cityCode1" id="city">
                            <option value="0">请选择</option>
                            <c:forEach items="${cityList}" var="cityList" varStatus="i">
                                <c:choose>
                                    <c:when test="${cityList.cityCode eq cityCode1}">
                                        <option value="${cityList.cityCode}"
                                                selected="selected">${cityList.cityName}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${cityList.cityCode}">${cityList.cityName}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </td>
                    <td align="right">区</td>
                    <td >
                        <select style="width:120px" name="districtCode1" id="district">
                            <option value="0">请选择</option>
                            <c:forEach items="${mdDistrictList}" var="mdDistrictList" varStatus="i">
                                <c:choose>
                                    <c:when test="${mdDistrictList.districtCode eq districtCode1}">
                                        <option value="${mdDistrictList.districtCode}"
                                                selected="selected">${mdDistrictList.districtName}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${mdDistrictList.districtCode}">${mdDistrictList.districtName}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
            </table>
        </div>
        <table id="bs2101102_list_grid" showAddBtn="true" width="100%">
            <thead>
            <tr>
                <%--   <input type="hidden" name="slCodeDis" value="${slCodeDis}"/>
                   <th coltype="text" width="10%" name="houseCodeDis" filter="true">冻品管家编码</th>
                   <%--<th coltype="text" width="10%" name="houseAccount" filter="true">冻品管家账号</th>--%>
                <th coltype="text" width="10%" name="houseShowName" filter="true">管家姓名</th>
                <th coltype="text" width="10%" name="slIdcard" filter="true">身份证号码</th>
                <th coltype="text" width="10%" name="cityName" filter="">行政区划</th>
                <th coltype="text" width="10%" name="vhouseAddress" filter="true">管家地址</th>
                <th coltype="text" width="10%" name="houseTel" filter="true">联系电话</th>
                <%--<th coltype="text" width="80px" name="greade" filter="true">管家等级</th>--%>
                <th coltype="text" width="10%" name="buyerNum">当前专属买家数</th>

                <th coltype="action" width="60px">修改管家
                    <msk:button buttonValue="修改管家" buttonType="hidden" coltype="edit" class="h-button" title="修改管家" buttonId="BS2101102.EDIT"/>
                </th>
                <th coltype="action" width="60px">新增专属买家
                    <msk:button buttonValue="新增专属买家" buttonType="hidden" coltype="add" title="新增专属买家" class="h-button" buttonId="BS2101102.ADD"/>
                </th>
                <th coltype="action" width="60px">已有专属买家
                    <msk:button buttonValue="已有专属买家" buttonType="hidden" coltype="edit" title="已有专属买家" class="h-button" buttonId="BS2101102.EDIT"/>
                </th>
                <th coltype="action" width="60px">新增锁定期买家
                    <msk:button buttonValue="新增锁定期买家" buttonType="hidden" coltype="add" title="新增锁定期买家" class="h-button" buttonId="BS2101102.ADD"/>
                </th>
                <th coltype="action" width="60px">已有锁定期买家
                    <msk:button buttonValue="已有锁定期买家" buttonType="hidden" coltype="edit" title="已有锁定期买家" class="h-button" buttonId="BS2101102.EDIT"/>
                </th>
                <th coltype="action" width="60px">解除关系
                    <msk:button buttonValue="解除关系" buttonType="hidden" coltype="check" title="解除关系" class="h-button" buttonId="BS2101102.CHECK"/>
                </th>
                <th coltype="action" width="60px">冻品管家买家履历信息
                    <msk:button buttonValue="冻品管家买家履历信息" buttonType="hidden" coltype="edit" title="冻品管家买家履历信息" class="h-button" buttonId="BS2101102.EDIT"/>
                </th>
            </tr>
            </thead>
            <tbody>
            </tbody>
        </table>

        <table><tr align="left"><msk:button buttonId="BS2102102.SAVE" buttonValue="新增" buttonType="button"></msk:button></tr></table>

    </form>
</div>
<script src="${ctx}/static/bs/js/BS2101102.js"></script>