
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>

<navigation:header title="买手列表" backTitleArray="" backUrlArray=""></navigation:header>

<div class="page-content list-page">
  <form  action="${ctx}/BS2101101/search" method="post" id="BS2102110Form" >
    <div>
      <table>
        <tr>
          <td width="100px" style="background:#CCCCCC">省</td>
          <td align="center" width="150px">
            <select style="width:120px" name="provinceCode" id="provinceList">
              <option value="0">请选择</option>
              <c:forEach items="${provinceList}" var="province" varStatus="status">
                <option value="${province.provinceCode}">${province.provinceName}</option>
              </c:forEach>
            </select>
          </td>
          <td width="100px" style="background:#CCCCCC">地区</td>
          <td align="center" width="150px" >
            <select style="width:120px" name="cityCode" id="citySelect" >
              <option value="0">请选择</option>
            </select>
          </td>
          <td width="100px" style="background:#CCCCCC">区</td>
          <td align="center" width="150px">
            <select style="width:120px" name="districtCode" id="districtSelect">
              <option value="0">请选择</option>

            </select>
          </td>
        </tr>
      </table>
    </div>
    <div>
      <table id="BS2102110_list_grid" width="100%">
        <thead>
        <tr>
          <th coltype="radio">选择</th>
          <th coltype="text"  width="20%" name="slCodeDis" filter="true">买手编码</th>
          <th coltype="text"  width="20%" name="slContact" filter="true">买手名称</th>
          <th coltype="text"  width="20%" name="shopName" filter="true">店铺名称</th>
          <th coltype="text"  width="20%" name="cityName" filter="">行政区划</th>
          <th coltype="text"  width="20%" name="slIdcard">身份证号码</th>
        </tr>
        </thead>
        <tbody>
        </tbody>
      </table>
    </div>
  </form>
  <div>
    <table>
      <tr>
        <td><msk:button buttonValue="确定" buttonType="button" buttonId="BS2102110.SAVE"/></td>
      </tr>
    </table>
  </div>
</div>
<script src="${ctx}/static/bs/js/BS2102110.js"></script>
