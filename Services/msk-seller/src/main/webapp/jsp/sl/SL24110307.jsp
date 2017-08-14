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
			<tr>
				<td coltype="text" width="20%" height="50px" name="pdSpec">电商团队负责人</td>
				<td coltype="text" width="20%" height="50px" name="s0014" align="center"><a
					name="teamMainName" class="tooltip" title="电商团队负责人"
					href="${bean.imagePath}"
					target="_blank"
					src="${bean.imagePath}"></a></td>
				<td coltype="text" width="20%" height="60px" name="S00113">
					1、姓名：${bean.memberName} <br />2、年龄：${bean.memberAge}<br />3、文化程度：${bean.memberEduc}<br />4、联系方式：${bean.memberTel}<br />
				</td>
			</tr>

			<tr>
				<td coltype="text" width="20%" height="50px" name="pdSpec">电商团队成员</td>
				<td coltype="text" width="20%" height="50px" name="pdSpec"><c:forEach
						items="${beanList}" var="lists" varStatus="i">
					<div style="height:150px;" align="center">
					<a name="teamOtherName" class="tooltip" title="电商团队成员" href="${lists.imagePath}" target="_blank" src="${lists.imagePath}"></a><br />
						</div>
						<c:if test="${i.index+1<beanList.size()}">
								<hr />
							</c:if>
					</c:forEach>
			  </td>
				<td coltype="text" width="20%" height="50px" name="pdSpec"><c:forEach
						items="${beanList}" var="li" varStatus="i">
					<div style="height:150px;">
                        <br/><br/><br/>${li.noLeaderDeil}<br/><br/><br/>
						</div>
                        <c:if test="${i.index+1<beanList.size()}">
							<hr/>
						</c:if>
					</c:forEach>
			  </td>
			</tr>
		</table>
<script type="text/javascript" src="${ctx}/static/js/core/utils.js"></script>
<script type="text/javascript" src="${ctx}/static/sl/js/SL24110307.js"></script>