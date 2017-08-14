<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/comm/taglib.jsp"%>
    <form action="${ctx}/SL2411010301/save" id="SL2411010301Form"
               method="post" enctype="multipart/form-data">
        <input type="hidden" id="crtId" name="crtId" value="${loginUser.emplId}"/>
        <input type="hidden" id="updId" name="updId" value="${loginUser.emplId}"/>
        <div>
            <table width="100%">
                <tr>
                    <td width="50px" align="right">请选择需要添加的证书</td>
                    <td width="50px" align="left">
                        <select  style="width:120px" id="slmsk_select1">
                            <option value="" label="请选择"/>
                            <c:forEach items="${slMstCerts}" var="slMstCert" varStatus="i">
                                <option value="${slMstCert.certId}">${slMstCert.certName}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
            </table>
        </div>
    </form>
<script src="${ctx}/static/sl/js/SL2411010301.js"></script>