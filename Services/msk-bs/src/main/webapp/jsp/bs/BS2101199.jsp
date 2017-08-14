<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
    Title:买手店列表
    author:cx
    createDate:2016-3-7
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<style>
    .test li{float:left;cursor: pointer;}
    .image_{
        margin-left: 10px;
    }
    .word_font{
        margin-left: 15px;
        color: white;
        font-size: 14px;

    }
    .report{
        cursor: pointer;
    }
</style>

<navigation:header title="买手管控" backTitleArray="买手列表" backUrlArray="${ctx}/BS2101101/init/"></navigation:header>
<div class="page-content list-page">
    <input type="hidden" id="hidProvinceCode" value="${bean.provinceCode}">
    <input type="hidden" id="hidCityCode" value="${bean.cityCode}">
    <input type="hidden" id="hidDistrictCode" value="${bean.districtCode}">
    <input type="hidden" id="hidSlCode" value="${bean.slCode}">
    <input type="hidden" id="hidSlCodeDis" value="${bean.slCodeDis}">
    <input type="hidden" id="hidSlContact" value="${bean.slContact}">
    <input type="hidden" id="hidSlAccount" value="${bean.slAccount}">
    <!--Modify 买手信息列表导出图片 by yangchunyan 2016/09/29 start-->
    <input type="hidden" id="hidSlAccountImg" value="${bean.accountImg}">
    <!--Modify 买手信息列表导出图片 by yangchunyan 2016/09/29 end-->
    <input type="hidden" id="flagNum" value="${flagNum}">
    <input type="hidden" id="printUrl" value="/excel/async/print/start/faster">

    <div class="group-accordion" collapsible="false" active="false" >
        <h3>
            <label>买手基本信息管控</label>
        </h3>
        <ul class="test"  id="baseInfoContainer" >
            <li id="baseInfo"><img height="88px" width="87px" src="${ctx}/static/bs/images/base_info.png"/></li>
        </ul>
    </div>
    <div class="group-accordion" collapsible="false" active="false" >
        <h3>
            <label>买手管理</label>
        </h3>

        <div class="test">
            <li id="toDpManager"><img height="88px" width="87px" src="${ctx}/static/bs/images/house_manage.png"/></li>
            <li id="toBuyerManager" style="margin-left: 40px;"><img height="88px" width="87px" src="${ctx}/static/bs/images/sl_manage.png"/></li>
        </div>
         <input  id="slCode" type="hidden" value="${bean.slCode}"/>
    </div>
    <div class="group-accordion" collapsible="false" active="false" >
        <h3>
            <label>报表中心</label>
        </h3>

        <table width="100%">
            <tr >
                <td class="report" id="slBsAccountInfo" style="vertical-align: middle;width:500px;height:40px; background-image: url('${ctx}/static/bs/images/report_bg.png')">
                    <img style="margin-left: 10px;" src="${ctx}/static/bs/images/report_ico.png"/>
                    <span class="word_font">买手基本信息注册及编码生成管控表</span>
                </td>
                <td></td>
            </tr>
            <tr style="display: none;">
                <td class="report" style="vertical-align: middle;width:500px;height:40px; background-image: url('${ctx}/static/bs/images/report_bg.png')">
                    <img style="margin-left: 10px;" src="${ctx}/static/bs/images/report_ico.png"/>
                    <span class="word_font">买手所属冻品管家及其专属会员所属期在线管控表</span>
                </td>
                <td></td>
            </tr>
        </table>

    </div>
</div>
<script type="text/javascript" src="${ctx}/static/js/core/utils.js"></script>
<script src='<c:url value="/static/js/loading/download.js"/>'/>
<script src="${ctx}/static/bs/js/BS2101199.js"></script>
