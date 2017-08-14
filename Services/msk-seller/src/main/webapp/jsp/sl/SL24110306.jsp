<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/comm/taglib.jsp"%>

		<table class="tree dataTable no-footer" style="widtd: 100%">
		   <thead>
             <tr align="center" style="background:#DBDBDB">
                   <th coltype="text" width="25%" name="pdSpec">证照</th>
                   <th coltype="text" width="25%" name="pdSpec">图片</th>
                   <th coltype="text" width="50%" name="pdSpec">资质描述</th>
             </tr>
           </thead>
                 <c:forEach items="${list0306}" var="list" varStatus="i">
	                <tr>
	                     <td coltype="text" width="20%" height="50px" name="pdSpec">${list.memberDuties}</td>
	                     <td coltype="text" width="20%" height="50px" name="s0014" align="center"><a name="epTeam" class="tooltip" title="${list.memberDuties}" href="${list.image}" target="_blank" src="${list.image}"></a></td>
	                     <td coltype="text" width="20%" height="60px" name="S00113">1.姓名：${list.memberName}<br>  2.年龄：${list.memberAge}岁<br> 3.联系方式：${list.memberTel}<br> 4.文化程度：${list.memberEduc}</td>
	                </tr>                                                                                                                          
                </c:forEach>
        </table>
<script type="text/javascript" src="${ctx}/static/js/core/utils.js"></script>
<script type="text/javascript" src="${ctx}/static/sl/js/SL24110306.js"></script>