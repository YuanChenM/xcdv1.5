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
    .test li{float:left;}
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

<navigation:header title="报表中心" backTitleArray="" backUrlArray=""></navigation:header>
<div class="page-content list-page">
    <input type="hidden" id="hidProvinceCode" value="${bean.provinceCode}">
    <input type="hidden" id="hidCityCode" value="${bean.cityCode}">
    <input type="hidden" id="hidDistrictCode" value="${bean.districtCode}">
    <input type="hidden" id="hidSlCode" value="${bean.slCode}">
    <input type="hidden" id="hidSlCodeDis" value="${bean.slCodeDis}">
    <input type="hidden" id="hidSlContact" value="${bean.slContact}">
    <input type="hidden" id="hidSlAccount" value="${bean.slAccount}">

    <div class="group-accordion" collapsible="false" active="false" >
        <h3>
            <label>报表中心</label>
        </h3>

        <table width="100%">
            <tr >
                <td class="td_house_table report" style="vertical-align: middle;width:500px;height:40px; background-image: url('${ctx}/static/bs/images/report_bg.png')">
                    <img style="margin-left: 10px;" src="${ctx}/static/bs/images/report_ico.png"/>
                    <span class="word_font">冻品管家总控表下载画面</span>
                </td>
                <td></td>
            </tr>
            <tr>
                <td class="td_houseGroup_table report" style="vertical-align: middle;width:500px;height:40px; background-image: url('${ctx}/static/bs/images/report_bg.png')">
                    <img style="margin-left: 10px;" src="${ctx}/static/bs/images/report_ico.png"/>
                    <span class="word_font">冻品管家组总控表下载画面</span>
                </td>
                <td></td>
            </tr>
        </table>

    </div>
</div>
<script src="${ctx}/static/bs/js/BS2102115.js"></script>