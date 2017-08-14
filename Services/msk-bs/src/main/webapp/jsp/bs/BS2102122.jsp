<%--
    Title:冻品管家个人简历履历
    author:whc
    createDate:2016-9-7
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/comm/taglib.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<navigation:header title="冻品管家自我介绍更新履历" backTitleArray="冻品管家一览,冻品管家管控,冻品管家自我介绍"
                   backUrlArray="${ctx}/BS2102102/init,${ctx}/BS2102112/init?slCode=${slCode}&houseCode=${houseCode},${ctx}/BS2102113/init?slCode=${slCode}&houseCode=${houseCode}"
></navigation:header>
<style>
    /**{border:0; padding:0; margin:0;}*/
    /*.tabCon{width:800px; margin:50px auto;}
    .tabCon p.ht{background:url(img/ht.jpg) repeat-x; width:800px; height:26px; position:relative;}
    .tabCon p.ht i{background:url(img/1.jpg) no-repeat; width:8px; height:11px; position:absolute
    ; left:10px; top:8px;}*/
    .tabCon p.ht span{font-size:12px; margin-left:25px; line-height:26px;}

    /*表格*/
    .tableCon2{ margin-top:10px;}
    .tableCon2 table,.tabCon3{border-right:1px solid #c1c1c1; border-bottom:1px solid #c1c1c1; width:100%; box-sizing:border-box; font-size:12px; text-align:left; line-height: 25px;}
    .tableCon2 table tr td,.tabCon3 tr td{border-left:1px solid #c1c1c1; border-top:1px solid #c1c1c1;}
    .tableCon2 table tr td.td1{width:15%;}
    .tableCon2 table tr.trbg{background:#ddd;}
    .tabCon3{margin:15px; width: 96% !important;}
</style>

<div class="page-content list-page" style="height:100%">
        <input type="hidden" id="slCode" value="${slCode}"/>
        <input type="hidden" id="houseCode" value="${houseCode}"/>
        <div>
            <div class="group-accordion" active="true">
                <h3>
                    <label>冻品管家自我介绍更新在线管理</label>
                </h3>
                <div class="tableCon2">
                <table border="0" cellpadding="0" cellspacing="0" style="width: 100%;">
                    <tr class="trbg">
                        <td class="td1">项目</td>
                        <td>更新简历</td>
                    </tr>

                    <tr>
                        <td>我的工作照</td>
                        <td>
                            <table border="0" cellpadding="0" cellspacing="0" class="tabCon3">
                                <tr class="trbg">
                                    <td>序号</td>
                                    <td>更新时间</td>
                                    <td>更新内容</td>
                                </tr>
                                <%--<tr>
                                    <td>1</td>
                                    <td>2016-8-21</td>
                                    <td>更新xxxxxxxxxx</td>
                                </tr>--%>
                                <tr>
                                    <td colspan="3">暂无内容</td>
                                </tr>
                            </table>
                        </td>
                    </tr>

                    <tr>
                        <td>我的经历</td>
                        <td>
                            <table border="0" cellpadding="0" cellspacing="0" class="tabCon3">
                                <tr class="trbg">
                                    <td>序号</td>
                                    <td>个人经历</td>
                                    <td>更新时间</td>
                                    <td>操作</td>
                                    <td>更新内容</td>
                                </tr>
                                <c:if test="${!empty workHisList}">
                                    <c:set var="workHisCount" value="${fn:length(workHisList)}"/>
                                    <c:forEach items="${workHisList}" var="work" varStatus="statu">
                                        <tr>
                                            <td>${statu.index + 1}</td>
                                            <td>工作经历</td>
                                            <td><fmt:formatDate value="${work.crtTime}" pattern="yyyy-MM-dd"/></td>
                                            <td>
                                                <c:if test="${work.actFlg == '0'}">新增</c:if>
                                                <c:if test="${work.actFlg == '1'}">编辑</c:if>
                                                <c:if test="${work.actFlg == '2'}">删除</c:if>
                                            </td>
                                            <td>
                                                <c:if test="${!empty work.workStart || !empty work.workEnd}">
                                                    工作时间：<fmt:formatDate value="${work.workStart}" pattern="yyyy-MM-dd"/>
                                                    <c:if test="${!empty work.workEnd}">
                                                        至<fmt:formatDate value="${work.workEnd}" pattern="yyyy-MM-dd"/>&nbsp;&nbsp;
                                                    </c:if>
                                                </c:if>
                                                <c:if test="${!empty work.workComp}">
                                                    工作单位：${work.workComp}&nbsp;&nbsp;
                                                </c:if>
                                                <c:if test="${!empty work.workStation}">
                                                    岗位：${work.workStation}&nbsp;&nbsp;
                                                </c:if>
                                                <c:if test="${!empty work.workPosition}">
                                                    职位：${work.workPosition}&nbsp;&nbsp;
                                                </c:if>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </c:if>
                                <c:if test="${!empty eduHisList}">
                                    <c:forEach items="${eduHisList}" var="edu" varStatus="statu">
                                        <c:set var="eduHisCount" value="${statu.index + 1 + workHisCount}"></c:set>
                                        <tr>
                                            <td>${eduHisCount}</td>
                                            <td>教育经历</td>
                                            <td><fmt:formatDate value="${edu.crtTime}" pattern="yyyy-MM-dd"/></td>
                                            <td>
                                                <c:if test="${edu.actFlg == '0'}">新增</c:if>
                                                <c:if test="${edu.actFlg == '1'}">编辑</c:if>
                                                <c:if test="${edu.actFlg == '2'}">删除</c:if>
                                            </td>
                                            <td>
                                                <c:if test="${!empty edu.eduStart || !empty edu.eduEnd}">
                                                    学习时间：<fmt:formatDate value="${edu.eduStart}" pattern="yyyy-MM-dd"/>
                                                    <c:if test="${!empty edu.eduEnd}">
                                                        至<fmt:formatDate value="${edu.eduEnd}" pattern="yyyy-MM-dd"/>&nbsp;&nbsp;
                                                    </c:if>
                                                </c:if>
                                                <c:if test="${!empty edu.eduComp}">
                                                    教育单位：${edu.eduComp}&nbsp;&nbsp;
                                                </c:if>
                                                <c:if test="${!empty edu.eduRecord}">
                                                    学历：${edu.eduRecord}&nbsp;&nbsp;
                                                </c:if>
                                                <c:if test="${!empty edu.eduDegree}">
                                                    学位：${edu.eduDegree}&nbsp;&nbsp;
                                                </c:if>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </c:if>
                                <c:if test="${!empty trainHisList}">
                                    <c:forEach items="${trainHisList}" var="train" varStatus="statu">
                                        <c:set var="trainHisCount" value="${statu.index + 1 + eduHisCount}"></c:set>
                                        <tr>
                                            <td>${trainHisCount}</td>
                                            <td>培训经历</td>
                                            <td><fmt:formatDate value="${train.crtTime}" pattern="yyyy-MM-dd"/></td>
                                            <td>
                                                <c:if test="${train.actFlg == '0'}">新增</c:if>
                                                <c:if test="${train.actFlg == '1'}">编辑</c:if>
                                                <c:if test="${train.actFlg == '2'}">删除</c:if>
                                            </td>
                                            <td>
                                                <c:if test="${!empty train.trainStart || !empty train.trainEnd}">
                                                    培训时间：<fmt:formatDate value="${train.trainStart}" pattern="yyyy-MM-dd"/>
                                                    <c:if test="${!empty train.trainEnd}">
                                                        至<fmt:formatDate value="${train.trainEnd}" pattern="yyyy-MM-dd"/>&nbsp;&nbsp;
                                                    </c:if>
                                                </c:if>
                                                <c:if test="${!empty train.trainComp}">
                                                    培训机构：${train.trainComp}&nbsp;&nbsp;
                                                </c:if>
                                                <c:if test="${!empty train.trainCertificate}">
                                                    所获证书：${train.trainCertificate}&nbsp;&nbsp;
                                                </c:if>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </c:if>
                            </table>
                        </td>
                    </tr>

                    <tr>
                        <td>我的服务心得</td>
                        <td>
                            <table border="0" cellpadding="0" cellspacing="0" class="tabCon3">
                                <tr class="trbg">
                                    <td>序号</td>
                                    <td>更新时间</td>
                                    <td>更新内容</td>
                                </tr>
                                <tr>
                                    <td colspan="3">暂无内容</td>
                                </tr>
                            </table>
                        </td>
                    </tr>

                    <tr>
                        <td>我的感悟/理想</td>
                        <td>
                            <table border="0" cellpadding="0" cellspacing="0" class="tabCon3">
                                <tr class="trbg">
                                    <td>序号</td>
                                    <td>更新时间</td>
                                    <td>更新内容</td>
                                </tr>
                                <%--<tr>
                                    <td>1</td>
                                    <td>2016-8-21</td>
                                    <td>更新xxxxxxxxxx</td>
                                </tr>--%>
                                <tr>
                                    <td colspan="3">暂无内容</td>
                                </tr>

                            </table>
                        </td>
                    </tr>
                </table>
                </div>
            </div>
        </div>
</div>
<%--<script src="${ctx}/static/bs/js/BS2102122.js"></script>--%>