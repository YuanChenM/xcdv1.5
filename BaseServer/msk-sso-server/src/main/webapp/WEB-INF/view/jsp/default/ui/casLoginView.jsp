<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="sso" uri="/sso" %>
<jsp:directive.include file="includes/top.jsp"/>
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
                    <div id="login-bg" class="form login-backgroud-color" style="top: 20px; height:45px;">
                        <span style="margin-left:9%;font-size:24px;line-height:20px;">用户登录</span>
                    </div>
                    <div id="newVersionLogin" class="form login-backgroud-color">

                        <table class="all_input" style="border-collapse: separate;">
                            <tbody>
                            <tr>
                                <td></td>
                                <td style="height:45px;padding: 5px;">
                                    <label id="lblUserType" for="userType"></label>
                                    <i class="iconfont icon-login">&#xe630;</i>
                                    <sso:userType/>
                                    <%--<select id="userType" name="userType">--%>
                                        <%--<option value="1">员工</option>--%>
                                        <%--<option value="2">卖家/供应商</option>--%>
                                        <%--<option value="99">VIP GUEST</option>--%>
                                    <%--</select>--%>
                                </td>
                                <td></td>
                            </tr>
                            <tr class="line">
                                <td></td>
                                <td class="tdline_2" width="30" style="height: 0px;"></td>
                                <td></td>
                            </tr>
                            <tr>
                                <td></td>
                                <td style="height:40px;padding: 5px;">
                                    <label id="lblNewUserName" for="loginId"></label>
                                    <i class="iconfont icon-login">&#xe630;</i>
                                    <c:choose>
                                        <c:when test="${not empty sessionScope.openIdLocalId}">
                                            <strong>${sessionScope.openIdLocalId}</strong>
                                            <input type="hidden" id="username" name="username" value="${sessionScope.openIdLocalId}"/>
                                        </c:when>
                                        <c:otherwise>
                                            <spring:message code="screen.welcome.label.netid.accesskey" var="userNameAccessKey"/>
                                            <form:input
                                                    cssClass="module_app_input___gray_neoskin"
                                                    style="margin-left:15px; margin-top: 0; width:200px;"
                                                    placeholder="用户名"
                                                    cssErrorClass="error"
                                                    id="username"
                                                    size="25"
                                                    tabindex="1"
                                                    accesskey="${userNameAccessKey}"
                                                    path="username"
                                                    autocomplete="off"
                                                    autocapitalize="off"
                                                    htmlEscape="true"/>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td></td>
                            </tr>
                            <tr class="line">
                                <td></td>
                                <td class="tdline_2" width="30" style="height: 0px;"></td>
                                <td></td>
                            </tr>
                            <tr>
                                <td></td>
                                <td style="height:40px;padding: 5px;">
                                    <label id="lblNewPassword" for="loginPwd"></label>
                                    <i class="iconfont icon-login">&#xe61f;</i>
                                    <form:password
                                            cssClass="module_app_input___gray_neoskin"
                                            style="margin-left:15px; margin-top: 0; width:200px;"
                                            placeholder="密码"
                                            cssErrorClass="error"
                                            id="password"
                                            size="25"
                                            tabindex="1"
                                            path="password"
                                            accesskey="${passwordAccessKey}"
                                            htmlEscape="true"
                                            autocomplete="off"
                                            autocapitalize="off"
                                    />
                                    <a class="forget" href="#"></a>
                                </td>
                                <td></td>
                            </tr>
                            </tbody>
                        </table>
                        <div style="margin-left:9%;height:20px;">
                            <span style="font-size:12px;color:red;line-height:20px;">
                                <form:errors path="*" id="msg" cssClass="errors" element="div" htmlEscape="false" />
                            </span>
                        </div>
                        <div class="btn_wrap login-backgroud-color">
                            <input type="hidden" name="lt" value="${loginTicket}"/>
                            <input type="hidden" name="execution" value="${flowExecutionKey}"/>
                            <input type="hidden" name="_eventId" value="submit"/>
                            <input type="button" name="submitNew" value="登录" id="submitNew" class="btn_login_submit">
                        </div>
                    </div>
                </div>
            </form:form>
        </div>
        <jsp:directive.include file="includes/bottom.jsp"/>
    </div>
</div>
</body>




