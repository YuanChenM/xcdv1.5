<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
    Title:冻品管家管控页面
    author:wang_haichun
    createDate:2016-8-19
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<style>
    ul li {
        list-style: none;
    }

    .modular li {
        float: left;
        width: 150px;
    }

    .modular li img {
        height: 88px;
        width: 88px;
        cursor: pointer;
    }

    .word_font {
        margin-left: 15px;
        color: white;
        font-size: 14px;
    }
    .report{
        cursor: pointer;
    }
</style>
<navigation:header title="冻品管家管控" backTitleArray="冻品管家一览" backUrlArray="${ctx}/BS2102102/init"></navigation:header>
<div class="page-content list-page">
    <input type="hidden" id="slCode" value="${houseAccount.slCode}"/>
    <input type="hidden" id="houseCode" value="${houseAccount.houseCode}"/>
    <input type="hidden" id="validYearMonth" value="${houseAccount.validYearMonth}"/>
    <input type="hidden" id="printUrl" value="/excel/async/print/start/faster">
    <div class="group-accordion" collapsible="false" active="false">
        <h3>
            <label>冻品管家基本信息管控</label>
        </h3>
        <ul class="modular" id="baseInfoContainer">
            <li id="baseInfo"><img src="${ctx}/static/bs/images/base_info.png"/></li>
            <li id="houseSetting"><img src="${ctx}/static/bs/images/house_setting.png"/></li>
            <li id="selfIntroManage"><img src="${ctx}/static/bs/images/self_Intro_manage.png"/></li>
        </ul>
    </div>
    <div class="group-accordion" collapsible="false" active="false">
        <h3>
            <label>冻品管家管理</label>
        </h3>

        <ul class="modular">
            <li id="exclusiveSlManage"><img src="${ctx}/static/bs/images/exclusive_sl_manage.png"/></li>
            <li id="lockSlManage"><img src="${ctx}/static/bs/images/lock_sl_manage.png"/></li>
            <li id="slRecordManage"><img src="${ctx}/static/bs/images/sl_record_manage.png"/></li>
            <li id="starManage"><img src="${ctx}/static/bs/images/star_manage.png"/></li>
            <li id="levelManage"><img src="${ctx}/static/bs/images/level_manage.png"/></li>
        </ul>
    </div>
    <div class="group-accordion" collapsible="false" active="false">
        <h3>
            <label>报表中心</label>
        </h3>

        <table width="100%">
            <tr>
                <td class="report" id="houseAccountInfo" style="vertical-align: middle;width:500px;height:40px; background-image: url('${ctx}/static/bs/images/report_bg.png')">
                    <img style="margin-left: 10px;" src="${ctx}/static/bs/images/report_ico.png"/>
                    <span class="word_font">冻品管家基本信息注册及编码生成管控表</span>
                </td>
                <td></td>
            </tr>
            <tr style="display: none">
                <td class="report" style="vertical-align: middle;width:500px;height:40px; background-image: url('${ctx}/static/bs/images/report_bg.png')">
                    <img style="margin-left: 10px;" src="${ctx}/static/bs/images/report_ico.png"/>
                    <span class="word_font">冻品管家注册专属会员买家汇总在线管控表</span>
                </td>
                <td></td>
            </tr>
            <tr style="display: none">
                <td class="report" style="vertical-align: middle;width:500px;height:40px; background-image: url('${ctx}/static/bs/images/report_bg.png')">
                    <img style="margin-left: 10px;" src="${ctx}/static/bs/images/report_ico.png"/>
                    <span class="word_font">冻品管家专属会员发展买家确认表</span>
                </td>
                <td></td>
            </tr>
        </table>

    </div>
</div>
<script type="text/javascript" src="${ctx}/static/js/core/utils.js"></script>
<script src='<c:url value="/static/js/loading/download.js"/>'/>
<script src="${ctx}/static/bs/js/BS2102112.js"></script>