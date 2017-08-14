<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<script type="text/javascript">
    function callbackFun(message){
        $.alertMessage.info(message);
        HDF.closeLoadingMask();
        $.pdialog.close();
        $('#main-content').postUrl(Main.contextPath + "/SL241103000/init");
    }
</script>
<div class="group-accordion" active="false">
    <h3>
        <label>企业专业资质</label>
    </h3>
    <form action="${ctx}/SL241103003/save" id="SL241103003Form"
               method="post" enctype="multipart/form-data">
        <div>
            <table width="100%">
                <tr>
                    <td width="50px" align="right">请选择需要添加的证书</td>
                    <td width="50px" align="left">
                        <select  style="width:120px" id="slmsk_select">
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
</div>
<script src="${ctx}/static/sl/js/SL241103003.js"></script>