<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/comm/taglib.jsp"%>
        <table class="tree dataTable no-footer" style="widtd: 100%">
          <thead>
            <tr align="center" style="background:#DBDBDB">
                    <th coltype="text" width="25%" name="pdSpec">证照</th>
                    <th coltype="text" width="25%" name="pdSpec">图片</th>
                    <th coltype="text" width="50%" name="pdSpec">资质描述</th>
                </tr>
          </thead>
                 <c:forEach items="${listEpCert}" var="li" varStatus="i">
                <tr>
                     <td coltype="text" width="20%" height="50px">${li.certName}</td>
                     <td coltype="text" width="20%" height="50px" align="center"><a name="imgUrlName" class="tooltip" title="${li.imgUrl}" href="${li.imgUrl}" target="_blank" src="${li.imgUrl}"><%--<img src="${li.imgUrl}" height="100px" width="200px"/>--%></a></td>
                     <td coltype="text" width="50%" height="60px">
                        <c:forEach items="${li.beanList}" var="lis">
                            ${lis.certItemId}、${lis.certItemName}：${lis.certItemValue}<br/>
                        </c:forEach> 
                     </td>
                </tr>
                 </c:forEach>
        </table>
<script type="text/javascript" src="${ctx}/static/js/core/utils.js"></script>
<script type="text/javascript" src="${ctx}/static/sl/js/SL24110302.js"></script>
