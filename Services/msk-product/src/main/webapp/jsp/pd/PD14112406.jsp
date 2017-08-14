<%-- 
    Title:产品类别信息页面
    author:xhy
    createDate:2015-2-18
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<link rel="stylesheet" href="${ctx}/static/js/treegrid/css/jquery.treegrid.css">
<script type="text/javascript" src="${ctx}/static/js/treegrid/js/jquery.treegrid.js"></script>
<style type="text/css">
	.tree .row_active {
		background-color: #B9D3EE;
	}
</style>
<script type="text/javascript">
	var CLASSES_CODE = "${classesCode}";
</script>
	<div class="group-accordion" collapsible="false" active="false" id="pd14112406accordion" style="width:auto;overflow:auto;">
		<h3 style="position: relative">
			<label>产品分类目录列表</label>
		</h3>

		<div style="padding-left: 0px;padding-top: 0px;padding-bottom: 0px;padding-right: 0px;">
			<div style="height: 300px">
				<input type = "hidden" id="class" value="${classesCode}">
				<input type = "hidden" id="machining" value="${machiningCode}">
				<input type = "hidden" id="breed" value="${breedCode}">
				<input type = "hidden" id="feature" value="${featureCode}">
				<input type = "hidden" id="weight" value="${weightCode}">
				<input type = "hidden" id="classname" value="${classesName}">
				<input type = "hidden" id="machiningname" value="${machiningName}">
				<input type = "hidden" id="breedname" value="${breedName}">
				<input type = "hidden" id="featurename" value="${featureName}">
				<input type = "hidden" id="weightname" value="${weightName}">
			<table class="tree dataTable no-footer">
				<tr style="background:#DBDBDB">
					<td width="33%" align="center">编码</td>
					<td width="33%" align="center">名称</td>
					<td align="center"></td>
				</tr>
				<c:forEach items="${listTree}" var="tree">
					<!-- 一级类别CLASSES -->
					<tr class="treegrid-${tree.classesTreeCode}">
						<td name="classes" value="${tree.levelCode}">${tree.levelCode}</td>
						<td value="${tree.levelName}">${tree.levelName}</td>
						<td width="40px"></td>
					</tr>

					<!--二级类别 MAC-->
					<c:forEach items="${tree.level2Beans}" var="macList">
						<c:if test="${not empty macList.classesTreeCode}">
							<tr class="treegrid-${macList.classesTreeCode} treegrid-parent-${macList.parentCode}">
								<td name="mac" value="${macList.level2Code}">${macList.level2Code}</td>
								<td style="display:none;" value="${macList.parentCode}"></td>
								<td style="display:none;" value="${tree.levelName}"></td>
								<td value="${macList.level2Name}">　${macList.level2Name}</td>
								<td><a href="javascript:void(0)" name="PD141125"  classesTreeCode="${macList.classesTreeCode}" machiningName="${macList.level2Name}" title="原种"><img border="0px" src="${ctx}/static/core/images/action/yuanliaomiaoshu.png"/></a></td>
							</tr>
						</c:if>
						<!--三级类别 breed-->
						<c:forEach items="${macList.level3Beans}" var="pdBreed">
							<c:if test="${not empty pdBreed.classesTreeCode}">
								<tr class="treegrid-${pdBreed.classesTreeCode} treegrid-parent-${pdBreed.parentCode}">
									<td name="breed" value="${pdBreed.level3Code}">${pdBreed.level3Code}</td>
									<td style="display:none;" value="${tree.classesTreeCode}"></td>
									<td style="display:none;" value="${tree.levelName}"></td>
									<td style="display:none;" value="${macList.level2Code}"></td>
									<td style="display:none;" value="${macList.level2Name}"></td>
									<td value="${pdBreed.level3Name}">　　${pdBreed.level3Name}</td>
									<td>
										<c:if test="${tree.levelCode == '01' or tree.levelCode == '02'}">
											<a href="javascript:void(0)" name="PD141125" classesTreeCode="${pdBreed.classesTreeCode}" breedName="${pdBreed.level3Name}" title="修改"><img border="0px" src="${ctx}/static/core/images/action/yuanliaomiaoshu.png"/></a>
											<c:if test="${macList.level2Code == '1'}">
												<a href="javascript:void(0)" name="PD141146" classesTreeCode="${pdBreed.classesTreeCode}" breedName="${pdBreed.level3Name}" title="原种种源标准指标"><img border="0px" src="${ctx}/static/core/images/action/yuanliaozhongyuan.png"/></a>
												<a href="javascript:void(0)" name="PD141147" classesTreeCode="${pdBreed.classesTreeCode}" breedName="${pdBreed.level3Name}" title="原种饲养标准指标"><img border="0px" src="${ctx}/static/core/images/action/siyangbz.png"/></a>
											</c:if>
											<a href="javascript:void(0)" name="PD141148" classesTreeCode="${pdBreed.classesTreeCode}" breedName="${pdBreed.level3Name}" title="产品加工技术标准指标"><img border="0px" src="${ctx}/static/core/images/action/jiagongjishu.png"/></a>
											<a href="javascript:void(0)" name="PD141149" classesTreeCode="${pdBreed.classesTreeCode}" breedName="${pdBreed.level3Name}" title="产品加工质量标准指标"><img border="0px" src="${ctx}/static/core/images/action/jiagongzhiliang.png"/></a>
											<a href="javascript:void(0)" name="PD141150" classesTreeCode="${pdBreed.classesTreeCode}" breedName="${pdBreed.level3Name}" title="产品通用质量标准指标"><img border="0px" src="${ctx}/static/core/images/action/tongyongzhiliang.png"/></a>
											<a href="javascript:void(0)" name="PD141151" classesTreeCode="${pdBreed.classesTreeCode}" breedName="${pdBreed.level3Name}" title="产品安全标准指标"><img border="0px" src="${ctx}/static/core/images/action/anquanbz.png"/></a>
											<a href="javascript:void(0)" name="PD141152" classesTreeCode="${pdBreed.classesTreeCode}" breedName="${pdBreed.level3Name}" title="储存运输标准指标"><img border="0px" src="${ctx}/static/core/images/action/yunshubz.png"/></a>
											<c:if test="${macList.level2Code == '1'}">
												<a href="javascript:void(0)" name="PD14114601" classesTreeCode="${pdBreed.classesTreeCode}" breedName="${pdBreed.level3Name}" title="原种种源标准图片档案卡"><img border="0px" src="${ctx}/static/core/images/action/yuanliaozhongyuanimg.png"/></a>
												<a href="javascript:void(0)" name="PD14114701" classesTreeCode="${pdBreed.classesTreeCode}" breedName="${pdBreed.level3Name}" title="原种饲养标准图片档案卡"><img border="0px" src="${ctx}/static/core/images/action/siyangbzimg.png"/></a>
											</c:if>
										</c:if>
										<c:if test="${tree.levelCode != '01' and tree.levelCode != '02'}">
											<a href="javascript:void(0)" name="PD141125" classesTreeCode="${pdBreed.classesTreeCode}" breedName="${pdBreed.level3Name}" title="修改"><img border="0px" src="${ctx}/static/core/images/action/yuanliaomiaoshu.png"/></a>
											<a href="javascript:void(0)" name="PD141148" classesTreeCode="${pdBreed.classesTreeCode}" breedName="${pdBreed.level3Name}" title="产品加工技术标准指标"><img border="0px" src="${ctx}/static/core/images/action/jiagongjishu.png"/></a>
											<a href="javascript:void(0)" name="PD141149" classesTreeCode="${pdBreed.classesTreeCode}" breedName="${pdBreed.level3Name}" title="产品加工质量标准指标"><img border="0px" src="${ctx}/static/core/images/action/jiagongzhiliang.png"/></a>
											<a href="javascript:void(0)" name="PD141150" classesTreeCode="${pdBreed.classesTreeCode}" breedName="${pdBreed.level3Name}" title="产品通用质量标准指标"><img border="0px" src="${ctx}/static/core/images/action/tongyongzhiliang.png"/></a>
											<a href="javascript:void(0)" name="PD141151" classesTreeCode="${pdBreed.classesTreeCode}" breedName="${pdBreed.level3Name}" title="产品安全标准指标"><img border="0px" src="${ctx}/static/core/images/action/anquanbz.png"/></a>
											<a href="javascript:void(0)" name="PD141152" classesTreeCode="${pdBreed.classesTreeCode}" breedName="${pdBreed.level3Name}" title="储存运输标准指标"><img border="0px" src="${ctx}/static/core/images/action/yunshubz.png"/></a>
											<a href="javascript:void(0)" name="PD14114601" classesTreeCode="${pdBreed.classesTreeCode}" breedName="${pdBreed.level3Name}" title="原种种源标准图片档案卡"><img border="0px" src="${ctx}/static/core/images/action/yuanliaozhongyuanimg.png"/></a>
											<a href="javascript:void(0)" name="PD14114701" classesTreeCode="${pdBreed.classesTreeCode}" breedName="${pdBreed.level3Name}" title="原种饲养标准图片档案卡"><img border="0px" src="${ctx}/static/core/images/action/siyangbzimg.png"/></a>
										</c:if>
									</td>
								</tr>
							</c:if>
							<!-- 四级类别 feature-->
							<c:forEach items="${pdBreed.level4Beans}" var="pdFeature">
								<c:if test="${not empty pdFeature.classesTreeCode}">
									<tr class="treegrid-${pdFeature.classesTreeCode} treegrid-parent-${pdFeature.parentCode}">
										<td name="feature"  value="${pdFeature.level4Code}">${pdFeature.level4Code}</td>
										<td style="display:none;" value="${tree.classesTreeCode}"></td>
										<td style="display:none;" value="${tree.levelName}"></td>
										<td style="display:none;" value="${macList.level2Code}"></td>
										<td style="display:none;" value="${macList.level2Name}"></td>
										<td style="display:none;" value="${pdBreed.level3Code}"></td>
										<td style="display:none;" value="${pdBreed.level3Name}"></td>
										<td value="${pdFeature.level4Name}">　　　${pdFeature.level4Name}</td>
										<td></td>
									</tr>
								</c:if>
								<!-- 循环显示品种5级种类  weight-->
								<c:forEach items="${pdFeature.level5Beans}" var="weight">
									<c:if test="${not empty weight.classesTreeCode}">
										<tr class="treegrid-${weight.classesTreeCode} treegrid-parent-${weight.parentCode}">
											<td name="weight"  value="${weight.level5Code}">${weight.level5Code}</td>
											<td style="display:none;" value="${tree.classesTreeCode}"></td>
											<td style="display:none;" value="${tree.levelName}"></td>
											<td style="display:none;" value="${macList.level2Code}"></td>
											<td style="display:none;" value="${macList.level2Name}"></td>
											<td style="display:none;" value="${pdBreed.level3Code}"></td>
											<td style="display:none;" value="${pdBreed.level3Name}"></td>
											<td style="display:none;" value="${pdFeature.level4Code}"></td>
											<td style="display:none;" value="${pdFeature.level4Name}"></td>
											<td value="${weight.level5Name}">　　　　${weight.level5Name}</td>
											<td></td>
										</tr>
									</c:if>
									<!-- 循环显示品种6级种类  norms-->
									<c:forEach items="${weight.level6Beans}" var="norms">
										<c:if test="${not empty norms.classesTreeCode}">
											<tr class="treegrid-${norms.classesTreeCode} treegrid-parent-${norms.parentCode}">
												<td name="norms"  value="${norms.level6Code}">${norms.level6Code}</td>
												<td style="display:none;" value="${tree.classesTreeCode}"></td>
												<td style="display:none;" value="${tree.levelName}"></td>
												<td style="display:none;" value="${macList.level2Code}"></td>
												<td style="display:none;" value="${macList.level2Name}"></td>
												<td style="display:none;" value="${pdBreed.level3Code}"></td>
												<td style="display:none;" value="${pdBreed.level3Name}"></td>
												<td style="display:none;" value="${pdFeature.level4Code}"></td>
												<td style="display:none;" value="${pdFeature.level4Name}"></td>
												<td style="display:none;" value="${weight.level5Code}"></td>
												<td style="display:none;" value="${weight.level5Name}"></td>
												<td value="${norms.level6Name}">　　　　　${norms.level6Name}</td>
												<td>
													<a href="javascript:void(0)" classesTreeCode="${norms.classesTreeCode}" breedName="${pdBreed.level3Name}" featureName="${pdFeature.level4Name}"  name="PD141153" title="包装标准指标"><img border="0px" src="${ctx}/static/core/images/action/baozhuanbz.png"/></a>
												</td>
											</tr>
										</c:if>
									</c:forEach>
								</c:forEach>
							</c:forEach>
						</c:forEach>
					</c:forEach>
				</c:forEach>
			</table>
		</div>
		</div>
	</div>
<div>
	<table>
		<tr>
			<td><msk:button buttonValue="新建" buttonId="PD14112406.NEW" buttonType="button"/></td>
			<td><msk:button buttonValue="修改" buttonId="PD14112406.MODIFY" buttonType="button"/></td>
			<td><msk:button buttonValue="删除" buttonId="PD14112406.DELETE" buttonType="button"/></td>
			<!--Add 分类目录管理页面加载慢问题 2016/09/14 BY 杨春艳 Start-->
			<td><msk:button buttonValue="快速录入" buttonId="PD14112406.FASTNEW" buttonType="button"/></td>
			<!--Add 分类目录管理页面加载慢问题 2016/09/14 BY 杨春艳 End-->
		</tr>
	</table>
</div>

<script src="${ctx}/static/js/pd/PD14112406.js"></script>
