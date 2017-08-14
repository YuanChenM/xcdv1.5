<%--
  Created by IntelliJ IDEA.
  User: tao_zhifa
  Date: 2016/7/5
  Time: 10:03
  To change this template use File | Settings | File Templates.
   * 买家买家池已购产品目录
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<navigation:header title="买家池已购产品目录" backTitleArray="买家列表,买家信息总控画面" backUrlArray="${ctx}/BY121303/init/,${ctx}/BY121313/init/${buyerId}"></navigation:header>
<div class="page-content list-page">
  <input type="hidden" value="${buyerId}"/>
    <form action="${ctx}/BY121309/search/${buyerId}" method="post">
      <table id="BY121309_Grid">
        <thead>
          <tr>
            <th coltype="sno" name="Id">序号</th>
            <th coltype="text" name="classesName">产品一级分类</th>
            <th coltype="text" name="machiningName">产品二级分类</th>
            <th coltype="text" name="breedName">产品名称</th>
            <th coltype="text" name="featureName">产品特征</th>
            <th coltype="text" name="weightVal">单箱净重(kg)</th>
            <th coltype="text" name="normsName">包装规格</th>
            <th coltype="text" name="gradeName">产品等级</th>
          </tr>
        </thead>
      </table>
    </form>
</div>
<script type="text/javascript" src="${ctx}/static/buyers/js/BY121309.js"></script>