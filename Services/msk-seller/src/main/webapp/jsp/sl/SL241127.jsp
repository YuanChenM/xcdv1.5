<%--
  Created by IntelliJ IDEA.
  User: jiangnan
  Date: 15/3/15
  Time: 上午11:11
  卖家产品信息及状态审核
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<%@ taglib prefix="msk2" uri="/msk" %>
<script type="text/javascript">
    var slShowName = '${slShowName}';
</script>
<navigation:header title="卖家产品信息及状态审核" backTitleArray="产品待审批审核卖家列表"
                   backUrlArray="${ctx}/SL241101/init"></navigation:header>
<div class="page-content list-page">
    <form:form action="${ctx}/SL241116/search/${slCode}" id="SL241127Form" method="post">
        <div>
            <table id="SL241127_list_grid">
                <thead>
                <tr>
                    <th coltype="text" width="10%" name="prodEpName" filter="true">生产商</th>
                    <th coltype="text" width="10%" name="brandName" filter="true">品牌</th>
                    <th coltype="text" width="10%" name="pdClassesName" filter="true">产品一级分类</th>
                    <th coltype="text" width="10%" name="machiningName" filter="true">产品二级分类</th>
                    <th coltype="text" width="10%" name="pdBreedName" filter="true">产品品种</th>
                    <th coltype="text" width="10%" name="pdFeatureName" filter="true">产品特征</th>
                    <th coltype="text" width="10%" name="weightName" filter="true">包装净重</th>
                    <th coltype="select" width="10%" name="status" filter="true">产品状态
                        <select>
                            <option value='1'>申请中</option>
                            <option value='2'>论证中</option>
                            <option value='3'>禁止准入</option>
                            <option value='4'>试销</option>
                            <option value='5'>正式上线</option>
                            <option value='6'>下线</option>
                            <option value='7'>黑名单</option>
                        </select>
                    </th>
                    <th coltype="action" width="60px">产品状态审核
                        <msk2:button buttonType="hidden" buttonId="SL241127.SAVEBTN" coltype="save" buttonValue="产品状态审核" class="h-button"/>
                        <%--<input type="hidden" coltype="save" title="产品状态审核" class="h-button"/>--%>
                    </th>
                    <th coltype="action" width="60px">产品品种图片
                        <msk2:button buttonType="hidden" buttonId="SL241127.SERACHBTN" coltype="search" buttonValue="产品品种图片" class="h-button"/>
                        <%--<input type="hidden" coltype="search" title="产品品种图片" class="h-button"/>--%>
                    </th>
                    <th coltype="action" width="60px">产品标准
                        <msk2:button buttonType="hidden" buttonId="SL241127.AUDITBTN" coltype="audit" buttonValue="其他标准" class="h-button"/>
                        <%--<input type="hidden" coltype="audit" title="其他标准" class="h-button"/>--%>
                    </th>
                </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
        </div>
    </form:form>
</div>
<script src="${ctx}/static/sl/js/SL241127.js"></script>
