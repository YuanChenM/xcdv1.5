
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<link rel="stylesheet" href="${ctx}/static/js/treegrid/css/jquery.treegrid.css">
<script type="text/javascript" src="${ctx}/static/js/treegrid/js/jquery.treegrid.js"></script>
<div class="page-content list-page">
    <%--冻品管家管理设置新增编辑--%>
        <div id="" style="display:block">
            <form:form  id="bs2102107FormId" method="post">
                <input type="hidden" id="bs2102107_houseShowName" name="houseShowName" value="${houseShowName}">
                <input type="hidden" id="bs2102107_flag1" name="flag1" value="${flag1}">
                <input type="hidden" id="bs2102107_houseTel" name="houseTel" value="${houseTel}">
                <input type="hidden" id="bs2102107_wechat" name="wechat" value="${wechat}">
                <input type="hidden" id="bs2102107_houseGreade" name="houseGreade" value="${houseGreade}">
                <input type="hidden" id="bs2102107_houseStar" name="houseStar" value="${houseStar}">
                <input type="hidden" id="bs2102107_vhouseAddress" name="vhouseAddress" value="${vhouseAddress}">
                <input type="hidden" id="bs2102107_flagNum" name="flagNum" value="${flagNum}">
                <input type="hidden" id="bs2102107_groupId" name="groupId" value="${groupId}">
                <table width="100%" border="0" cellpadding="0" cellspacing="10px">
                    <tr>
                        <td align="right" width="18%">物流区</td>
                        <td id="lgcsArea" width="18%" align="left" data-val="${slHouseManage.lgcsAreaCode}">${slHouseManage.lgcsAreaName}</td>
                        <c:if test="${empty slHouseManage.lgcsAreaCode || empty  slHouseManage.lgcsAreaName}">
                            <td style="color: #b81900">暂无物流区信息</td>
                        </c:if>
                    </tr>
                    <tr>
                        <td align="right" width="18%">地区<label style="color: #b81900;">(*必选)</label></td>
                        <td align="left" width="18%">
                            <select id="selectCity" style="width: 100px;">
                                <option value="0">请选择</option>
                                <c:forEach items="${cityBeanList}" var="cityBean" varStatus="i">
                                    <option value="${cityBean.cityCode}">${cityBean.cityName}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <c:if test="${empty cityBeanList}">
                            <td style="color: #b81900">暂无地区信息</td>
                        </c:if>
                    </tr>
                    <tr>
                        <td align="right" width="18%">一级分类<label style="color: #b81900;">(*必选)</label></td>
                        <td align="left" width="18%">
                            <select id="selectLeverOne" style="width: 100px;">
                                <option value="0">请选择</option>
                                <c:forEach items="${houseTypeList}" var="houseType" varStatus="i">
                                    <option value="${houseType.typeCode}">${houseType.typeName}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <c:if test="${empty houseTypeList}">
                            <td style="color: #b81900">暂无一级分类信息</td>
                        </c:if>
                    </tr>
                    <tr>
                        <td align="right" width="18%">二级分类<label style="color: #b81900;">(*必选)</label></td>
                        <td align="left" width="18%">
                            <select id="selectLeverTwo" style="width: 100px;">
                                <option>请选择</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td align="left" width="100px">
                            <msk:button buttonValue="保存" buttonId="BS2102107.ADD" buttonType="button"/>
                        </td>
                    </tr>
                </table>
            </form:form>
        </div>
    <input type="hidden" id="input_slCode" value="${slHouseManage.slCode}">
        <input type="hidden" id="input_houseCode" value="${slHouseManage.houseCode}">
</div>
<script src="${ctx}/static/bs/js/BS2102107.js"></script>
