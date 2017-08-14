<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/comm/taglib.jsp"%>
	<table class="tree dataTable no-footer" style="widtd: 100%">
	  <thead>
		<tr align="center" style="background: #DBDBDB">
			<th coltype="text" width="25%" name="pdSpec">证照</th>
			<th coltype="text" width="25%" name="pdSpec">图片</th>
			<th coltype="text" width="50%" name="pdSpec">资质描述</th>
		</tr>
	  </thead>	
		<!--品牌证书-->
		<c:forEach items="${list0308}" var="list">
			<tr>
				<td coltype="text" width="20%" height="50px" name="pdSpec">品牌证书</td>
                <td coltype="text" width="20%" height="50px" name="s0014" align="center"><a name="brandBook" class="tooltip" title="品牌证书" href="${list.brandPath}" target="_blank" src="${list.brandPath}"></a></td>
					
				<td coltype="text" width="20%" height="60px" name="S00113">1.品牌名称：${list.brandName}
					<br> 2.商标注册证：${list.brandNo}<br> 3.所属企业：${list.epName }
				</td>
			</tr>

			<!--包装-->
				<tr>
					<td coltype="text" width="20%" height="50px" name="pdSpec">包装图片</td>
					 <td coltype="text" width="20%" height="50px" name="s0014" align="center"><a name="normsPhoto" class="tooltip" title="包装图片" href="${list.brandPacPath}" target="_blank" src="${list.brandPacPath}"></a></td>
					<td coltype="text" width="20%" height="60px" name="S00113"></td>
						
				</tr>
			<!--品牌荣誉-->
			<c:forEach items="${list.sl2411030801BeanList}" var="list030801">
				<tr>
					<td coltype="text" width="20%" height="50px" name="pdSpec">品牌荣誉</td>
					 <td coltype="text" width="20%" height="50px" name="s0014" align="center"><a name="brandHonor" class="tooltip" title="品牌荣誉" href="${list030801.brandHonorPath}" target="_blank" src="${list030801.brandHonorPath}"></a></td>
					<td coltype="text" width="20%" height="60px" name="S00113">1.证书编码：${list030801.honorNo}
						<br> 2.发证单位：${ list030801.certIssuer} <br>3.发证时间：${ list030801.certDate}
					</td>
				</tr>
			</c:forEach>
		</c:forEach>
	</table>
<script type="text/javascript" src="${ctx}/static/js/core/utils.js"></script>
<script type="text/javascript" src="${ctx}/static/sl/js/SL24110308.js"></script>
