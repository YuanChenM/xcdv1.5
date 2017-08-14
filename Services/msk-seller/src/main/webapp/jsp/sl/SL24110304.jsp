<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/comm/taglib.jsp"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<table class="tree dataTable no-footer" style="widtd: 100%">
      <thead>
			<tr align="center" style="background: #DBDBDB">
				<th coltype="text" width="25%" name="pdSpec">证照</th>
				<th coltype="text" width="25%" name="pdSpec">图片</th>
				<th coltype="text" width="50%" name="pdSpec">资质描述</th>
			</tr>
	  </thead>
			<tr>
				<td coltype="text" width="20%" height="50px">车间概括</td>
                <td coltype="text" width="20%" height="50px"  align="center">
					     <c:forEach items="${SL24110304Bean.sL2411030303BeanList}" var="qyry" varStatus="i">
                             <div style="height:150px;" align="center">
		                     <a name="workshopName" class="tooltip" title="${qyry.imgUrl}" href="${qyry.imgUrl}" target="_blank" src="${qyry.imgUrl}"><%--<img src="${qyry.imgUrl}" height="100px" width="200px"/>--%></a><br/>
	                        </div>
                                 <c:if test="${i.index+1<SL24110304Bean.sL2411030303BeanList.size()}">
                                 <hr/>
                            </c:if>
	                     </c:forEach> 
                </td>
                <td coltype="text" width="20%" height="50px">
                    <c:forEach items="${SL24110304Bean.sL2411030303BeanList}" var="qyry" varStatus="i">
                        <div style="height:150px;">
                                 <br/><br/>${qyry.workshopDesc}<br/> <br/> <br/> <br/>
                            </div>
                          <c:if test="${i.index+1<SL24110304Bean.sL2411030303BeanList.size()}">
                            <hr/>
                            </c:if>
                    </c:forEach>
                </td>
			</tr>


			<tr>
				<td coltype="text" width="20%" height="50px">库容概括</td>
				<td coltype="text" width="25%" height="50px" align="center">
                      <c:forEach items="${SL24110304Bean.sL2411030302BeanList}" var="qyry">
                         <a name="capacityName" class="tooltip" title="${qyry.warehouseImgUrl}" href="${qyry.warehouseImgUrl}" target="_blank" src="${qyry.warehouseImgUrl}"><%--<img src="${qyry.warehouseImgUrl}" height="100px" width="200px" />--%></a><br>
                      </c:forEach>  
                    </td>

                <td coltype="text" width="50%" height="60px">
                    <c:forEach items="${SL24110304Bean.sL2411030302BeanList}" var="qyry">  
                               <br/> <br/>  ${qyry.scapDesc}<br/> <br/> <br/> <br/> 
                    </c:forEach>
                </td>
			</tr>
	</table>
<script type="text/javascript" src="${ctx}/static/js/core/utils.js"></script>
<script type="text/javascript" src="${ctx}/static/sl/js/SL24110304.js"></script>