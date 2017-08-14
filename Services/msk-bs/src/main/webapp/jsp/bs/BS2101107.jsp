<%-- 
    Title:冻品管家新增
    author:pxg
    createDate:2016-4-5
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/comm/taglib.jsp" %>
<c:if test="${flagNum =='2'}">
    <navigation:header title="冻品管家基本信息" backTitleArray="冻品管家一览"
                       backUrlArray="${ctx}/BS2102102/init/"
    ></navigation:header>
</c:if>
<c:if test="${flagNum =='3'}">
    <navigation:header title="冻品管家基本信息" backTitleArray="冻品管家一览,冻品管家管控"
                       backUrlArray="${ctx}/BS2102102/init/,${ctx}/BS2102112/init/?slCode=${slHouseAccount.slCode}&houseCode=${slHouseAccount.houseCode}&houseStar=${slHouseAccount.houseStar}&houseCodeDis=${slHouseAccount.houseCodeDis}"
    ></navigation:header>
</c:if>
<c:if test="${flagNum =='4'}">
    <navigation:header title="冻品管家基本信息" backTitleArray="冻品管家组一览,冻品管家组成员一览"
                       backUrlArray="${ctx}/BS2102104/init,${ctx}/BS2102105/init/?hkGroupId=${groupId}&isHk=${isHk})"
    ></navigation:header>
</c:if>
<c:if test="${flagNum =='10'}">
    <navigation:header title="冻品管家基本信息" backTitleArray="买手列表,冻品管家列表"
                       backUrlArray="${ctx}/BS2101101/init,${ctx}/BS2101102/init/?slCode=${slCode}&slCodeDis=${slCodeDis}&slContact=${slContact}&flagNum=${flagNum}"
    ></navigation:header>
</c:if>
<c:if test="${flagNum =='11'}">
    <navigation:header title="冻品管家基本信息" backTitleArray="买手店列表,买手管控,冻品管家列表"
                       backUrlArray="${ctx}/BS2101101/init,${ctx}/BS2101199/init/?slCode=${slCode}&slCodeDis=${slCodeDis}&slContact=${slContact}&flagNum=${flagNum},${ctx}/BS2101102/init/?slCode=${slCode}&slCodeDis=${slCodeDis}&slContact=${slContact}&flagNum=${flagNum}"
    ></navigation:header>
</c:if>
<style>
    .main{
        padding: 0 20px 0 20px;
    }
    .textRight{
        text-align: right;
    }
</style>
<div class="page-content list-page" style="height:100%">
    <form action="${ctx}/BS2101107/save" id="BS2101107FormInfo" method="post" enctype="multipart/form-data">
        <input type="hidden" name="slCode" id="slCode" value="${slCode}"/>
        <input type="hidden" name="slContact" id="slContact" value="${slContact}"/>
        <input type="hidden" name="houseCode" id="houseCode" value="${slHouseAccount.houseCode}"/>
        <input type="hidden" name="vlgcsAreaCode" id="vlgcsAreaCode" value="${slHouseAccount.vlgcsAreaCode}"/>
        <input type="hidden"  id="flagNum" value="${flagNum}"/>
        <div>
            <div class="group-accordion" active="true">
                <h3>
                    <label>冻品管家账号信息</label>
                </h3>
                <table border="0" width="100%">
                    <tr>
                        <td style="width: 125px;">管家账号<label style="color: #b81900;">（*必填）</label></td>
                        <td><input name="houseAccount" id="houseAccount" type="text" autocomplete="off"
                                   value="${slHouseAccount.houseAccount}" <c:if test="${!empty slHouseAccount.houseCode}">readonly="readonly" style="border: none;" </c:if> /></td>
                    </tr>
                    <tr>
                        <td style="width: 125px;">登录密码<label style="color: #b81900;">（*必填）</label></td>
                        <td><input name="accountPsd" id="accountPsd" type="text" autocomplete="off"
                                   value="${slHouseAccount.accountPsd}"/></td>
                    </tr>
                </table>
            </div>
            <div class="group-accordion" active="true">
                <h3>
                    <label>冻品管家基本信息</label>
                </h3>
                <table style="border-collapse:separate;border-spacing:10px;width: 100%;border: 0px;">
                    <tr>
                        <td class="textRight">姓名<label style="color: #b81900;">（*必填）</label></td>
                        <td colspan="4">
                            <input style="width: 175px;" name="houseShowName" id="houseShowName" value="${slHouseAccount.houseShowName}"/>

                            <label class="main">性别</label>
                            <label style="padding: 0 28px;">
                            <c:choose>
                                <c:when test="${slHouseAccount.flag1 == '1'}">
                                    <input type="radio" name="sex" value="1" checked="checked" />男
                                    <input type="radio" name="sex" value="2"  />女
                                </c:when>
                                <c:when test="${slHouseAccount.flag1 == '2'}">
                                    <input type="radio" name="sex" value="1"  />男
                                    <input type="radio" name="sex" value="2" checked="checked" />女
                                </c:when>
                                <c:otherwise>
                                    <input type="radio" name="sex" value="1" checked="checked" />男
                                    <input type="radio" name="sex" value="2"  />女
                                </c:otherwise>
                            </c:choose>
                            </label>
                            <label class="main">出生日期</label>
                            <input id="birthday" name="flag7" readonly="readonly" value="${slHouseAccount.flag7}"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="textRight">手机<label style="color: #b81900;">（*必填）</label></td>
                        <td colspan="4">
                            <input style="width: 175px;" name="houseTel" id="houseTel" value="${slHouseAccount.houseTel}"/>
                            <label class="main">微信</label>
                            <input style="width: 135px;" name="wechat" id="wechat" value="${slHouseAccount.wechat}"/>
                            <label class="main">QQ号</label>
                            <input name="qq" id="qq" value="${slHouseAccount.qq}"/>
                        </td>
                    </tr>
                    <tr>
                        <td rowspan="2" class="textRight">户籍地址</td>
                        <td colspan="4">
                            省（含省、直辖市）
                            <select name="provinceCode" class="province" id="rprovinceCode">
                                <option value="0">请选择</option>
                                <c:forEach items="${mdProvinces}" var="mdProvince" varStatus="i">
                                    <c:choose>
                                        <c:when test="${mdProvince.provinceCode eq slHouseAccount.rprovinceCode}">
                                            <option value="${mdProvince.provinceCode}"
                                                    selected="selected">${mdProvince.provinceName}</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${mdProvince.provinceCode}">${mdProvince.provinceName}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>
                            &nbsp;&nbsp;&nbsp;&nbsp;地区（含地级市）
                            <select name="cityCode" class="city" id="rcityCode">
                                <option value="0">请选择</option>
                                <c:forEach items="${rCityList}" var="cityList" varStatus="i">
                                    <c:choose>
                                        <c:when test="${cityList.cityCode eq slHouseAccount.rcityCode}">
                                            <option value="${cityList.cityCode}"
                                                    selected="selected">${cityList.cityName}</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${cityList.cityCode}">${cityList.cityName}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>
                            &nbsp;&nbsp;&nbsp;&nbsp;区（含县级市、县、区）
                            <select name="districtCode" class="district" id="rdistrictCode">
                                <option value="0">请选择</option>
                                <c:forEach items="${rDistrictList}" var="mdDistrictList" varStatus="i">
                                    <c:choose>
                                        <c:when test="${mdDistrictList.districtCode eq slHouseAccount.rdistrictCode}">
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
                    <tr>
                        <td colspan="5">
                            <input name="rhouseAddress" id="rhouseAddress" style="width: 587px;"
                                   value="${slHouseAccount.rhouseAddress}"/>
                        </td>
                    </tr>
                    <tr>
                        <td rowspan="2" class="textRight">工作地址</td>
                        <td colspan="5">
                            省（含省、直辖市）
                            <select name="provinceCode" class="province" id="provinceCode">
                                <option value="0">请选择</option>
                                <c:forEach items="${mdProvinces}" var="mdProvince" varStatus="i">
                                    <c:choose>
                                        <c:when test="${mdProvince.provinceCode eq slHouseAccount.provinceCode}">
                                            <option value="${mdProvince.provinceCode}"
                                                    selected="selected">${mdProvince.provinceName}</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${mdProvince.provinceCode}">${mdProvince.provinceName}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>
                            &nbsp;&nbsp;&nbsp;&nbsp;地区（含地级市）
                            <select name="cityCode" class="city" id="cityCode">
                                <option value="0">请选择</option>
                                <c:forEach items="${cityList}" var="cityList" varStatus="i">
                                    <c:choose>
                                        <c:when test="${cityList.cityCode eq slHouseAccount.cityCode}">
                                            <option value="${cityList.cityCode}"
                                                    selected="selected">${cityList.cityName}</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${cityList.cityCode}">${cityList.cityName}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>
                            &nbsp;&nbsp;&nbsp;&nbsp;区（含县级市、县、区）
                            <select name="districtCode" class="district" id="districtCode">
                                <option value="0">请选择</option>
                                <c:forEach items="${districtList}" var="mdDistrictList" varStatus="i">
                                    <c:choose>
                                        <c:when test="${mdDistrictList.districtCode eq slHouseAccount.districtCode}">
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
                    <tr>
                        <td colspan="5">
                            <input name="houseAddress" id="houseAddress" style="width: 587px;"
                                   value="${slHouseAccount.houseAddress}"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="textRight">身份证号<label style="color: #b81900;">（*必填）</label></td>
                        <td colspan="5">
                            <input name="slIdcard" id="slIdcard" style="width: 587px;"
                                   value="${slHouseAccount.slIdcard}"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="textRight">所属买手<label style="color: #b81900;">（*必填）</label></td>
                        <td colspan="5">
                            <label <c:if test="${empty slCodeDis}">style="border: 1px solid #aaa;padding: 2px;"</c:if>>
                            <label id="lab_slContact">${slContact}</label>
                            <c:choose>
                                <c:when test="${!empty basicInfo.slCodeDis}">
                                    <input type="text" readonly name="slCodeDis" id="slCodeDis" style="border: none;margin-top: -3px;"
                                           value="${basicInfo.slCodeDis}"/>
                                </c:when>
                                <c:when test="${!empty slCodeDis}">
                                    <input type="text" readonly name="slCodeDis" id="slCodeDis" style="border: none;margin-top: -3px;"
                                           value="${slCodeDis}"/>
                                </c:when>
                                <c:otherwise>
                                    <input type="text" readonly name="slCodeDis" id="slCodeDis" style="border: none;margin-top: -3px;" />
                                </c:otherwise>
                            </c:choose>
                            </label>
                            <c:if test="${empty slCodeDis}">
                                <img src="${ctx}/static/images/action/details_open.png" title="绑定买手"
                                     id="BS2101107_ADDHOUSE" style="vertical-align: middle;"/>
                            </c:if>
                        </td>
                    </tr>
                    <tr>
                        <td rowspan="2" class="textRight">虚拟经营地址<label style="color: #b81900;">（*必填）</label></td>
                        <td colspan="5">
                            省（含省、直辖市）
                            <select name="provinceCode" class="province" id="vprovinceCode" >
                                <option value="0">请选择</option>
                                <c:forEach items="${mdProvinces}" var="mdProvince" varStatus="i">
                                    <c:choose>
                                        <c:when test="${mdProvince.provinceCode eq slHouseAccount.vprovinceCode}">
                                            <option value="${mdProvince.provinceCode}"
                                                    selected="selected">${mdProvince.provinceName}</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${mdProvince.provinceCode}">${mdProvince.provinceName}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>
                            &nbsp;&nbsp;&nbsp;&nbsp;地区（含地级市）
                            <select name="cityCode" class="city" id="vcityCode" >
                                <option value="0">请选择</option>
                                <c:forEach items="${vCityList}" var="cityList" varStatus="i">
                                    <c:choose>
                                        <c:when test="${cityList.cityCode eq slHouseAccount.vcityCode}">
                                            <option value="${cityList.cityCode}"
                                                    selected="selected">${cityList.cityName}</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${cityList.cityCode}">${cityList.cityName}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>
                            &nbsp;&nbsp;&nbsp;&nbsp;区（含县级市、县、区）
                            <select name="districtCode" class="district" id="vdistrictCode" >
                                <option value="0">请选择</option>
                                <c:forEach items="${vDistrictList}" var="mdDistrictList" varStatus="i">
                                    <c:choose>
                                        <c:when test="${mdDistrictList.districtCode eq slHouseAccount.vdistrictCode}">
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
                    <tr>
                        <td colspan="5">
                            <input name="vhouseAddress" id="vhouseAddress" style="width: 587px;"
                                   value="${slHouseAccount.vhouseAddress}"/>
                        </td>
                    </tr>


                    <tr>
                        <td class="textRight">冻品管家分类申请<label style="color: #b81900;">（*必选）</label></td>
                        <td colspan="5">
                            <c:if test="${!empty houseTypeMap}">
                                <c:forEach items="${houseTypeMap}" var="item" varStatus="status">
                                    <div style="width: 100%;float: left;">
                                        <div>
                                            <p style="float: left;">${item.key.typeName } :</p>
                                            <c:if test="${!empty item.value}">
                                                <ul style="list-style-type:none;float: left;width:40%;" class="parentType"
                                                    data-parentTypeCode="${item.key.typeCode }">
                                                    <c:forEach items="${item.value}" var="v">
                                                        <li style="float: left;width: 32%">
                                                            <c:set var="cleckFlag" value="2" />
                                                            <c:forEach items="${houseManage}" var="hm">
                                                                <c:if test="${hm.houseReclassifyCode == v.typeCode and hm.houseCategoryCode == item.key.typeCode}">
                                                                    <c:set var="cleckFlag" value="1" />
                                                                </c:if>
                                                            </c:forEach>
                                                            <c:if test="${cleckFlag == '1'}">
                                                                <input name="son${status.index}" value="${v.typeCode}" checked="checked" type="checkbox"/>${v.typeName}
                                                            </c:if>
                                                            <c:if test="${cleckFlag != '1'}">
                                                                <input name="son${status.index}" value="${v.typeCode}" type="checkbox"/>${v.typeName}
                                                            </c:if>
                                                        </li>
                                                    </c:forEach>
                                                </ul>
                                            </c:if>
                                        </div>
                                    </div>
                                </c:forEach>
                            </c:if>
                        </td>
                    </tr>
                    <c:choose>
                        <c:when test="${!empty hkGroupList}">
                            <c:forEach items="${hkGroupList}" var="group" varStatus="status">
                                <c:if test="${status.index == 0}">
                                    <tr>
                                        <td class="textRight">冻品管家分类申请管理区域申报</td>
                                        <td><input style="width: 587px;border: none;" value="${group.hkGroupName}"  readonly="readonly"/></td>
                                    </tr>
                                </c:if>
                                <c:if test="${status.index != 0}">
                                    <tr>
                                        <td class="textRight"></td>
                                        <td><input style="width: 587px;border: none;" value="${group.hkGroupName}"  readonly="readonly"/></td>
                                    </tr>
                                </c:if>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <tr>
                                <td class="textRight">冻品管家分类申请管理区域申报</td>
                                <td><input style="width: 587px;border: none;" value=""  readonly="readonly"/></td>
                            </tr>
                        </c:otherwise>
                    </c:choose>


                </table>
            </div>
            <c:if test="${!empty slHouseAccount.houseCode}">
                <div >
                        <table style="border-collapse:separate;border-spacing:10px;width: 100%;border: 0px;margin-left: 2%;">
                            <tr>
                                <td class="textRight">冻品管家编码</td>
                                <td>
                                    <input type="text" value="${slHouseAccount.houseCodeDis}" readonly="readonly"
                                           style="height: 30px;width: 587px;border: none;"/>
                                </td>
                            </tr>
                        </table>
                </div>
            </c:if>
        </div>
    </form>
    <div>
        <table>
            <tr>
                <td><msk:button buttonValue="保存" buttonType="button" buttonId="BS2102107.SAVE"/></td>
            </tr>
        </table>
    </div>
</div>
<script src="${ctx}/static/bs/js/BS2101107.js"></script>