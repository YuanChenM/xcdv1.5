<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:directive.include file="includes/top.jsp"/>
<script type="text/javascript">
  var i = 3;
  function remainTime(){
    if(i==0){
      location.href='${ctx}/login?service=<%=request.getParameter("service") %>';
    }
    $("#endtime").html(i--);
    setTimeout("remainTime()",1000);
  }
   remainTime();
  <%--$(function () {--%>
    <%--$("#submitBack").click(function(){--%>
      <%--location.href='${ctx}/login?service=<%=request.getParameter("service") %>';--%>
    <%--})--%>
  <%--})--%>
</script>
<body>
<div>
  <div id="popup_filter"></div>
  <div class="wrapper">
    <div id="login_hd">
      <div class="login_hd">
        <div id="login-logo"
             style="height:98px; background: url('${ctx}/static/sso/images/logo.png') no-repeat;background-size: 25%;vertical-align: central;">
        </div>
      </div>
    </div>
    <div class="login_middle" style="background: url('${ctx}/static/sso/images/wallpaper-1.png') no-repeat center;">
      <form:form method="post" id="fm1" commandName="${commandName}" htmlEscape="true">
        <div id="background" class="login_box_bg">

          <div id="newVersionLogin" class="form login-backgroud-color">
            <div style="margin-left:15%;height:150px;">
              <span style="font-size:40px;color:black;line-height:50px;">
                用户退出成功
              </span><br/>
              <span style="font-size:20px;color:black;line-height:50px;">
                系统将在<strong id="endtime">3</strong>秒内返回登录页面
              </span>
            </div>
            <div class="btn_wrap login-backgroud-color">
              <input type="button" name="submitBack" value="请等待。。。" id="submitBack" class="btn_login_submit">
            </div>
          </div>
        </div>
      </form:form>
    </div>
    <jsp:directive.include file="includes/bottom.jsp"/>
  </div>
</div>
</body>