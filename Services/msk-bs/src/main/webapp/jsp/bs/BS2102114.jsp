<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>

<navigation:header title="定星管理" backTitleArray="冻品管家一览,冻品管家管控" backUrlArray="${ctx}/BS2102102/init,${ctx}/BS2102112/init?slCode=${slCode}&houseCode=${houseCode}"></navigation:header>
<div class="page-content list-page">
  <div class="group-accordion" active="true">
    <h3>
      <label>定星管理</label>
    </h3>
    <form:form action="${ctx}/BS2102114/updateStar" id="BS2102114Form" method="post" enctype="multipart/form-data">
        <input name="slCode" id="slCode" value="${slCode}" type="hidden" />
        <input name="validYearMonth" id="validYearMonth" value="${validYearMonth}" type="hidden" />
            <table border="0" width="100%">
              <thead>
              <tr>
                <td align="right" width="">冻品管家名称　</td>
                <td align="left" width="">
                  <input style="width:200px;" type="text" id="houseShowName" name="houseShowName"
                         value="${bs2102114Bean.houseShowName}" readonly/>
                </td>
              </tr>
              <tr>
                <td align="right" width="">冻品管家ID　</td>
                <td align="left" width="">
                  <input style="width:200px;" type="text" id="houseCode" name="houseCode"
                         value="${houseCode}" readonly/>
                </td>
              </tr>
              <tr>
                <td align="right" width="">冻品管家编码　</td>
                <td align="left" width="">
                  <input style="width:200px;" type="text" id="houseCodeDis" name="houseCodeDis"
                         value="${bs2102114Bean.houseCodeDis}" readonly/>
                </td>
              </tr>
              <tr>
                <td align="right" width="">星级　</td>
                <td align="left" width="">
                  <input style="width:200px;"    id="houseStar" name="houseStar"
                         value="${bs2102114Bean.houseStar}"/>
                </td>
                <td align="left">(填数字)</td>
              </tr>
              <tr>
                <td></td>
                <td align="left" width="100px">
                  <msk:button buttonValue="保存" buttonId="BS2102114.SAVE" buttonType="button"/>
                </td>
              </tr>
              </thead>
            </table>
    </form:form>
  </div>
</div>
<script src="${ctx}/static/bs/js/BS2102114.js"></script>
