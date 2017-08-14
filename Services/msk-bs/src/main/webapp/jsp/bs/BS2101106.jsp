<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<div class="group-accordion" active="true">
    <h3>
        <label>买手基本信息编辑</label>
    </h3>

    <div id="" style="display:block">
        <form:form action="${ctx}/BS2101106/update" id="BS2101106Form" method="post" enctype="multipart/form-data">
            <table width="100%" border="0" cellpadding="0" cellspacing="10px">
                <tr>
                    <td align="right" width="">买手类型</td>
                    <td align="left" width="">
                        <input style="width:200px;" type="text" id="agentType" name="agentType" value="${bs2101105Bean.agentType}"/>
                    </td>
                </tr>
                <tr>
                    <td align="right" width="18%">买手编码</td>
                    <td>
                        <input style="width:200px;border:none" type="text" id="slCodeDis" name="slCodeDis"
                               value="${bs2101105Bean.slCodeDis}" readonly/>
                      </td>
                </tr>
                <tr>
                    <td align="right" width="">买手身份证号</td>
                    <td align="left" width="">
                        <input style="width:200px;" type="text" id="slIdcard" name="memo7"
                               value="${bs2101105Bean.memo7}"/>
                        <input style="width:200px;" type="hidden" id="slAccount" name="slAccount"
                               value="${bs2101105Bean.slAccount}"/>
                        <input style="width:200px;" type="hidden" id="slCode" name="slCode"
                               value="${bs2101105Bean.slCode}"/>
                    </td>
                </tr>

                <tr>
                    <td align="right" width="">买手性别</td>
                       <td align="left" width="">
                           <input type="radio" <c:if test="${bs2101105Bean.flag1=='1' || bs2101105Bean.flag1 ne '2'}">checked="checked"</c:if> name="flag1" value="1"/>男
                           <input type="radio" <c:if test="${bs2101105Bean.flag1=='2'}">checked="checked"</c:if> name="flag1" value="2"/>女
                       </td>
                </tr>
                <tr>
                    <td align="right" width="">物流区<span style="color: red">(*必填)</span></td>
                    <td align="left" width="">
                        <select style="width:120px" name="lgcsAreaCode" id="lgcsArea1">
                            <option value="0">请选择</option>
                            <c:forEach items="${logisticsAreasList}" var="logisticsAreas" varStatus="i">
                                <c:choose>
                                    <c:when test="${logisticsAreas.lgcsAreaCode eq bs2101105Bean.lgcsAreaCode}">
                                        <option value="${logisticsAreas.lgcsAreaCode}"
                                                selected="selected">${logisticsAreas.lgcsAreaName}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${logisticsAreas.lgcsAreaCode}">${logisticsAreas.lgcsAreaName}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                        <span id="lgcsArea_sp"></span>
                    </td>
                </tr>
                <tr>
                    <td align="right" width="">省(含省、直辖市)<span style="color: red">(*必填)</span></td>
                    <td align="left" width="">
                        <select style="width:120px" name="provinceCode" id="province1">
                            <option value="0">请选择</option>
                            <c:forEach items="${mdProvinces}" var="mdProvince" varStatus="i">
                                <c:choose>
                                    <c:when test="${mdProvince.provinceCode eq bs2101105Bean.provinceCode}">
                                        <option value="${mdProvince.provinceCode}"
                                                selected="selected">${mdProvince.provinceName}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${mdProvince.provinceCode}">${mdProvince.provinceName}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                        <span id="province_sp"></span>
                    </td>
                </tr>
                <tr>
                    <td align="right" width="">地区(含地级市)<span style="color: red">(*必填)</span></td>
                    <td align="left" width="">
                        <select style="width:120px" name="cityCode" id="city1">
                            <option value="0">请选择</option>
                            <c:forEach items="${cityList}" var="cityList" varStatus="i">
                                <c:choose>
                                    <c:when test="${cityList.cityCode eq bs2101105Bean.cityCode}">
                                        <option value="${cityList.cityCode}"
                                                selected="selected">${cityList.cityName}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${cityList.cityCode}">${cityList.cityName}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                        <span id="city_sp"></span>
                    </td>
                </tr>
                <tr>
                    <td align="right" width="">区(含县级市、县、区)<span style="color: red">(*必填)</span></td>
                    <td align="left" width="">
                        <select style="width:120px" name="districtCode" id="district1">
                            <option value="0">请选择</option>
                            <c:forEach items="${mdDistrictList}" var="mdDistrictList" varStatus="i">
                                <c:choose>
                                    <c:when test="${mdDistrictList.districtCode eq bs2101105Bean.districtCode}">
                                        <option value="${mdDistrictList.districtCode}"
                                                selected="selected">${mdDistrictList.districtName}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${mdDistrictList.districtCode}">${mdDistrictList.districtName}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                        <span id="district_sp"></span>
                    </td>
                </tr>
                <tr>
                    <td align="right" width="">买手地址<span style="color: red">(*必填)</span></td>
                    <td align="left" width="">
                        <input style="width:200px;" type="text" id="slAddress" name="memo9"
                               value="${bs2101105Bean.memo9}" onblur="slAddressTest()"/>
                        <span id="slAddress_sp"></span>
                    </td>
                </tr>
                <tr>
                    <td align="right" width="">买手开店资格</td>
                    <td align="left" width="">
                        <input type="radio" <c:if test="${bs2101105Bean.shopQua=='0' || bs2101105Bean.shopQua ne '1'}">checked="checked"</c:if> name="shopQua" value="0"/>无资格
                        <input type="radio" <c:if test="${bs2101105Bean.shopQua=='1'}">checked="checked"</c:if> name="shopQua" value="1"/>有资格
                    </td>
                </tr>
                <tr>
                    <td align="right" width="">配送站</td>
                    <td align="left" width="">
                        <input style="width:200px;" type="text" id="distribution" name="distribution" value="${bs2101105Bean.distribution}"/>
                    </td>
                </tr>
                <tr>
                    <td align="right" width="">领地</td>
                    <td align="left" width="">
                        <input style="width:200px;" type="text" id="demesne" name="demesne" value="${bs2101105Bean.demesne}"/>
                    </td>
                </tr>
                <tr>
                    <td align="right" width="">注册来源</td>
                    <td align="left" width="">
                        <input style="width:200px;" type="text" id="registerSource" name="registerSource" value="${bs2101105Bean.registerSource}"/>
                    </td>
                </tr>
                <tr>
                    <td align="right" width="">微信号码</td>
                    <td align="left" width="">
                        <input style="width:200px;" type="text" id="memo1" name="memo1" value="${bs2101105Bean.memo1}"/>
                    </td>
                </tr>
                <tr>
                    <td align="right" width="">QQ号码</td>
                    <td align="left" width="">
                        <input style="width:200px;" type="text" id="memo2" name="memo2" value="${bs2101105Bean.memo2}"/>
                    </td>
                </tr>
                <tr>
                    <td align="right" width="">邮箱</td>
                    <td align="left" width="">
                        <input style="width:200px;" type="text" id="memo3" name="memo3" value="${bs2101105Bean.memo3}"/>
                    </td>
                </tr>
                <tr>
                    <td align="right" width="">固定电话</td>
                    <td align="left" width="">
                        <input style="width:200px;" type="text" id="memo4" name="memo4" value="${bs2101105Bean.memo4}"/>
                    </td>
                </tr>
                <tr>
                    <td align="right" width="">传真号</td>
                    <td align="left" width="">
                        <input style="width:200px;" type="text" id="memo5" name="memo5" value="${bs2101105Bean.memo5}"/>
                    </td>
                </tr>

                <tr>
                    <td align="right" width="">买手一级分类<span style="color: red">(*必填)</span></td>
                    <td align="left" width="">
                        <select style="width:120px" name="classOneLevel" id="classOneLevel">
                            <option value="0">请选择</option>
                            <c:forEach items="${slHouseTypeList}" var="houseTypeList" varStatus="i">
                                <option value="${houseTypeList.typeCode}"<c:if test="${houseTypeList.typeCode eq oneLevel}">selected </c:if>>${houseTypeList.typeName}</option>
                            </c:forEach>
                        </select>
                        <span id="classOneLevel_sp"></span>
                    </td>
                </tr>
                <tr>
                    <td align="right" width="">买手二级分类<span style="color: red">(*必填)</span></td>
                    <td align="left" width="">
                        <select style="width:120px" name="classTwoLevel" id="classTwoLevel">
                            <option value="0">请选择</option>
                            <c:forEach items="${twoTypeList}" var="twoTypeList" varStatus="i">
                                <option value="${twoTypeList.typeCode}"<c:if test="${twoTypeList.typeCode eq twoLevel.typeCode}">selected </c:if>>${twoTypeList.typeName}</option>
                            </c:forEach>
                        </select>
                        <span id="classTwoLevel_sp"></span>
                    </td>
                </tr>
                <tr>
                    <td align="right" width="">买手三级分类<span style="color: red">(*必填)</span></td>
                    <td align="left" width="">
                        <select style="width:120px" name="memo8" id="classThreeLevel">
                            <option value="0">请选择</option>
                            <c:forEach items="${threeTypeList}" var="threeTypeList" varStatus="i">
                                <option value="${threeTypeList.typeCode}"<c:if test="${threeTypeList.typeCode eq threeLevel.typeCode}">selected </c:if>>${threeTypeList.typeName}</option>
                            </c:forEach>
                        </select>
                        <span id="classThreeLevel_sp"></span>
                    </td>
                </tr>
                <tr>
                    <td align="right" width="">绑定银行卡</td>
                    <td align="left" width="">
                        <input type="hidden" name="cardType" value="0" width="190px;">
                       <label>开户名</label>&nbsp;<input name="accountName" value="${bankaccount.accountName}" width="190px;">
                    </td>
                </tr>
                <tr>
                    <td align="right" width=""></td>
                    <td align="left" width="">
                        <label>开户行</label>&nbsp;<input name="bankName" value="${bankaccount.bankName}" width="190px;">
                    </td>
                </tr>
                <tr>
                    <td align="right" width=""></td>
                    <td align="left" width="">
                        <label>账号</label>&nbsp;&nbsp;&nbsp;&nbsp;<input name="bankNo" value="${bankaccount.bankNo}" width="190px;">
                    </td>
                </tr>

                <tr>
                    <td align="right" width="">工作履历及证明材料</td>
                    <td align="left" width="">
                        <c:if test="${not empty bs2101105Bean.memo15}">
                            <img src="${bs2101105Bean.memo15}" alt="买手头像" height="100px" width="100px">
                        </c:if>
                        <input type="file" name="file"/>
                        <input type="hidden" id="memo15" name="memo15" value="${bs2101105Bean.memo15}"/>
                       <%-- &nbsp;&nbsp;&nbsp;
                        <c:if test="${not empty bs2101105Bean.memo8}">
                            <a href="${bs2101105Bean.memo8}"><font color=blue>点击下载附件</font></a>
                        </c:if>--%>
                        <input type="hidden" id="loginId" name="loginId" value="${loginUser.emplId}"/>
                    </td>
                <tr>
                    <td></td>
                    <td align="left">
                        <msk:button buttonValue="保存" buttonId="BS2101106.SAVE" buttonType="button"/>
                    </td>
                </tr>
            </table>
        </form:form>
    </div>
</div>
<script src="${ctx}/static/bs/js/BS2101106.js"></script>
