<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/comm/taglib.jsp"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<table class="tree dataTable no-footer" style="widtd: 100%" id="SL24110303_grid">
	  <thead>
		<tr align="center" style="background: #DBDBDB">
			<th coltype="text" width="25%" name="pdSpec">证照</th>
			<th coltype="text" width="25%" name="pdSpec">图片</th>
			<th coltype="text" width="50%" name="pdSpec">资质描述</th>
		</tr>
	 </thead>
			     <tr>
				    <td coltype="text" width="25%" height="50px">企业荣誉</td>
				     <td coltype="text" width="25%" height="50px" align="center">
				         <c:forEach items="${SL24110303Bean.sL2411030301BeanList}" var="qyry" varStatus="i">
							 <div style="height:150px;" align="center">
                            <a name="honorName" class="tooltip" title="${qyry.imgUrl}" href="${qyry.imgUrl}" target="_blank" src="${qyry.imgUrl}"><%--<img src="${qyry.imgUrl}" height="100px" width="200px" />--%></a><br/>
                           </div>
						 <c:if test="${i.index+1<SL24110303Bean.sL2411030301BeanList.size()}">
								 <hr/>
                          </c:if>
                         </c:forEach>
				     </td>

				<td coltype="text" width="50%" height="50px">
				    <c:forEach items="${SL24110303Bean.sL2411030301BeanList}" var="qyry" varStatus="i">
						<div style="height:150px;">
						${qyry.aptitudeDesc}<br/>${qyry.certIssuer}<br/>${qyry.honorDesc}<br/><br/>
						</div>
		               <c:if test="${i.index+1<SL24110303Bean.sL2411030301BeanList.size()}">
                            <hr/>
					   </c:if>
					</c:forEach>
				</td>
			 </tr>
             

			<tr>
                <td coltype="text" width="25%" height="50px" name="pdSpec">厂房平面图</td>
                    
                    <td coltype="text" width="25%" height="50px" name="s0014" align="center">
                      <c:forEach items="${SL24110303Bean.sL2411030302BeanList}" var="qyry" varStatus="j">
						  <a name="workHouseName" class="tooltip" title="${qyry.workshopImgUrl}" href="${qyry.workshopImgUrl}" target="_blank" src="${qyry.workshopImgUrl}"><%--<img src="${qyry.workshopImgUrl}" height="100px"width="200px" />--%></a><br>
                      </c:forEach>  
                    </td>

                <td coltype="text" width="50%" height="60px" name="S00113">
                    <c:forEach items="${SL24110303Bean.sL2411030302BeanList}" var="qyry">  
                                 ${qyry.aptitudeDesc}<br/><br/>
                    </c:forEach>
                </td>
            </tr>
	</table>
<script type="text/javascript" src="${ctx}/static/js/core/utils.js"></script>
<script type="text/javascript" src="${ctx}/static/sl/js/SL24110303.js"></script>