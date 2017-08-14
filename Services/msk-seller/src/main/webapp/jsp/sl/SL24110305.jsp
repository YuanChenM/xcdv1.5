<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/comm/taglib.jsp"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <table class="tree dataTable no-footer" style="widtd: 100%">
          <thead>
            <tr align="center" style="background:#DBDBDB">
                    <th coltype="text" width="25%" name="pdSpec">证照</th>
                    <th coltype="text" width="25%" name="pdSpec">图片</th>
                    <th coltype="text" width="50%" name="pdSpec">资质描述</th>
                </tr>
          </thead>   
                 <tr>
                     <td coltype="text" width="20%" height="50px" >实验室</td>
                     <td coltype="text" width="20%" height="50px" align="center">
                     <a name="systemSay" class="tooltip" title="${SL2411030302Bean.labImgUrl}" href="${SL2411030302Bean.labImgUrl}" target="_blank" src="${SL2411030302Bean.labImgUrl}"><%--<img src="${SL2411030302Bean.labImgUrl}" height="100px" width="200px"/>--%>
                     </a></td>
                     <td coltype="text" width="20%" height="60px" name="S00113">${SL2411030302Bean.aptitudeDesc}</td>
                </tr>
                <tr>
                    <td coltype="text" width="20%" height="50px">检测设备</td>
                    <td coltype="text" width="20%" height="50px"  align="center">
                    <a name="systemSay" class="tooltip" title="${SL2411030302Bean.ddEquipmentImgUrl}" href="${SL2411030302Bean.ddEquipmentImgUrl}" target="_blank" src="${SL2411030302Bean.ddEquipmentImgUrl}"><%--<img src="${SL2411030302Bean.ddEquipmentImgUrl}" height="100px" width="200px"/>--%>
                    </a></td>
                    <td coltype="text" width="20%" height="60px" name="pdSpec">${SL2411030302Bean.aptitudeDesc2}</td>
                </tr>
                <tr>
                    <td coltype="text" width="20%" height="50px">品控组织架构</td>
                    <td coltype="text" width="20%" height="50px"  align="center">
                    <a name="systemSay" class="tooltip" title="${SL2411030302Bean.controllerImgUrl}" href="${SL2411030302Bean.controllerImgUrl}" target="_blank" src="${SL2411030302Bean.controllerImgUrl}"><%--<img src="${SL2411030302Bean.controllerImgUrl}" height="100px" width="200px"/>--%>
                    </a></td>
                    <td coltype="text" width="20%" height="60px" name="pdSpec"></td>
                </tr>
                <tr>
                    <td coltype="text" width="20%" height="50px">质量控制系统图</td>
                    <td coltype="text" width="20%" height="50px" align="center">
                     <a name="systemSay" class="tooltip" title="${SL2411030302Bean.qualityImgUrl}" href="${SL2411030302Bean.qualityImgUrl}" target="_blank" src="${SL2411030302Bean.qualityImgUrl}"><%--<img src="${SL2411030302Bean.qualityImgUrl}" height="100px" width="200px"/>--%>
                    </a></td>
                    <td coltype="text" width="20%" height="60px" name="pdSpec"></td>
                </tr>
        </table>
<script type="text/javascript" src="${ctx}/static/js/core/utils.js"></script>
<script type="text/javascript" src="${ctx}/static/sl/js/SL24110305.js"></script>