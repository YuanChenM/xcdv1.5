<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<style>
  .passWord{
    list-style-type:none;
  }
  .passWord li{
    width: 300px;
    height: 39px;
    margin-left: 30px;
  }
  li .textPass{
    width: 200px;
    height: 25px;
    line-height: 25px;
    float:right;
    margin-top: -4px;
  }
  .passWord li span{
    font-size: 14px;
  }
  .passWord b{
    color: red;
  }
</style>
<div class="page-content list-page">
  <ul class="passWord">
    <li>
      <span><b>*</b>当前密码：</span>
      <input class="textPass" type="password" id = "oldPassword"></li>
    <li>
      <span><b>*</b>新密码：</span>
      <input class="textPass" type="password" id = "newPassword"></li>
    <li>
      <span><b>*</b>确认新密码：</span>
      <input class="textPass" type="password" id = "confirmPassword"></li>
    <li>
      <msk:button style="width: 106px;margin-left: 20px;" buttonValue="确认" buttonId="PASSWORD.CONFIRM" buttonType="button"></msk:button>
      <msk:button style="width: 106px;float: right;margin-right: 20px;" buttonValue="取消" buttonId="PASSWORD.ClOSE" buttonType="button"></msk:button></li>
    <li id="message" style="color: red;font-size: 16px;margin-top: 10px;height: 29px;"></li>
  </ul>
</div>
<script src="<c:url value="/static/js/passUpdate.js"/>"></script>

