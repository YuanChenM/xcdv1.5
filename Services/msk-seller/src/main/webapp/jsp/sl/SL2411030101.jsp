<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/comm/taglib.jsp"%>
  
    <form:form action="${ctx}/SL24110301/init" id="SL241103Form"
            metdod="post">
            <table class="tree dataTable no-footer" style="width: 100%">
                <tr align="center" style="background:#DBDBDB">
                    <th coltype="text" width="25%" name="pdSpec">证照</th>
                    <th coltype="text" width="25%" name="pdSpec">图片</th>
                    <th coltype="text" width="50%" name="pdSpec">资质描述</th>
                </tr>
                <tr>
                     <td coltype="text" width="20%" height="50px" name="pdSpec">营业执照</td>
                     <td coltype="text" width="20%" height="50px" name="s0014"><a id="test" class="tooltip" title="${ctx}/images/01.png" href="${ctx}/images/01.png" target="_blank" src="${ctx}/images/01.png"><img src="${ctx}/images/01.png" height="100px" width="200px"/></a></td>
                     <td coltype="text" width="20%" height="60px" name="S00113">${sL0301Bean.licSpeck}</td>
                </tr>
                <tr>
                    <td coltype="text" width="20%" height="50px" name="pdSpec">税务登记证</td>
                    <td coltype="text" width="20%" height="50px" name="pdSpec"><img src="${ctx}/images/images03/2.png" height="100px" width="200px"/></td>
                    <td coltype="text" width="20%" height="60px" name="pdSpec">${sL0301Bean.taxSpeck}</td>
                </tr>
                <tr>
                    <td coltype="text" width="20%" height="50px" name="pdSpec">组织代码证</td>
                    <td coltype="text" width="20%" height="50px" name="pdSpec"><img src="${ctx}/images/images03/3.png" height="100px" width="200px"/></td>
                    <td coltype="text" width="20%" height="60px" name="pdSpec">${sL0301Bean.orgSpeck}</td>
                </tr>
                <tr>
                    <td coltype="text" width="20%" height="50px" name="pdSpec">银行开户许可证</td>
                    <td coltype="text" width="20%" height="50px" name="pdSpec"><img src="${ctx}/images/images03/4.png" height="100px" width="200px"/></td>
                    <td coltype="text" width="20%" height="60px" name="pdSpec">${sL0301Bean.balSpeck}</td>
                </tr>
                <tr>
                    <td coltype="text" width="20%" height="50px" name="pdSpec">三证合一营业执照</td>
                    <td coltype="text" width="20%" height="50px" name="pdSpec"><img src="${ctx}/images/images03/5.png" height="100px" width="200px"/></td>
                    <td coltype="text" width="20%" height="60px" name="pdSpec">${sL0301Bean.licTypeSpeck}</td>
                </tr>
            </table>
        </form:form>
    
<script src="${ctx}/static/sl/js/SL24110301.js"></script>
