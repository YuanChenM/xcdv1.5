<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/comm/taglib.jsp"%>

<table class="tree dataTable no-footer">
    <thead>
    <tr align="center" style="background: #DBDBDB">
        <th coltype="text" width="25%" name="pdSpec">证照</th>
        <th coltype="text" width="25%" name="pdSpec">图片</th>
        <th coltype="text" width="50%" name="pdSpec">资质描述</th>
    </tr>
    </thead>
    <tr>
        <td coltype="text" width="20%" height="50px">检测设备</td>
        <td coltype="text" width="20%" height="50px"  align="center">
            <c:forEach items="${slEpDdList}" var="list" varStatus="i">
                <div style="height:150px;" align="center">
                    <a name="testName" class="tooltip" title="${list.slEpDdPath}" href="${list.slEpDdPath}" target="_blank" src="${list.slEpDdPath}"><%--<img src="${qyry.imgUrl}" height="100px" width="200px"/>--%></a><br/>
                </div>
                <c:if test="${i.index+1<slEpDdList.size()}">
                    <div>
                        <hr/>
                    </div>
                </c:if>
            </c:forEach>
        </td>
        <td coltype="text" width="20%" height="50px">
            <c:forEach items="${slEpDdList}" var="list" varStatus="i">
                <div style="height:150px;">
                设备名称：${list.ddName}<br/>
                设备用途：${list.ddEquipment}
                    </div>
                <c:if test="${i.index+1<slEpDdList.size()}">
                    <hr/>
                </c:if>
            </c:forEach>
        </td>
    </tr>
</table>
<script type="text/javascript" src="${ctx}/static/js/core/utils.js"></script>
<script type="text/javascript" src="${ctx}/static/sl/js/SL24110312.js"></script>
