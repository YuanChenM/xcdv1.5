<%--
    Title:卖家产品列表
    author:gyh
    createDate:2016-1-27
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<%@ taglib prefix="msk2" uri="/msk" %>
<%@ taglib prefix="msktag" uri="/codemaster" %>
<script type="text/javascript">
    var slShowName='${slShowName}';
    var slCode='${slCode}';
    function callbackFun(message){
        $.alertMessage.info(message);
        HDF.closeLoadingMask();
        $List_Grid.fnDraw();
    }
</script>
<msktag:codemaster codeType="YESNO" viewType="json"/>

<navigation:header title="卖家产品信息维护" backTitleArray="卖家信息列表" backUrlArray="${ctx}/SL241101/initShow"></navigation:header>
<div class="page-content list-page">
    <form:form action="${ctx}/SL241116/save" method="post" commandName="slProduct"
               modelAttribute="slProduct" id="SL241116UploadForm" enctype="multipart/form-data">
        <input type="hidden"   name="crtId" value="${loginUser.emplId}"/>
        <input type="hidden"   name="updId" value="${loginUser.emplId}"/>
        <input type="hidden" name="slCode" value="${slCode}">
        <div class="group-accordion" active="true" collapsible="false">
            <h3>
                <label>新增卖家产品</label>
            </h3>

            <div>
                <table width="100%">
                    <tr>
                        <td width="100px" align="right">生产商</td>
                        <td align="left">
                            <form:select path="prodEpId" style="width:120px" id="epId">
                                <form:option value="" label="请选择"/>
                                <form:options items="${slEnterprise}" itemValue="epId" itemLabel="epName"/>
                            </form:select>
                        </td>
                        <td width="100px" align="right">品牌</td>
                        <td align="left">
                            <form:select path="concatInfo" style="width:120px" id="brandId">
                                <form:option value="" label="请选择"/>
                                <form:options items="${slPdBrand}" itemValue="contractNo" itemLabel="brandName"/>
                                <input type="hidden" id="brandEpName" name="brandEpName" value="" />
                            </form:select>
                        </td>
                        <td width="100px" align="right">类别</td>
                        <td align="left">
                            <form:select path="pdClassesCode" style="width:120px" id="pdClasses">
                                <form:option value="" label="请选择"/>
                                <form:options items="${pdClasses}" itemValue="classesCode" itemLabel="classesName"/>
                                <input type="hidden" id="pdClassesName" name="pdClassesName" value="" />
                            </form:select>
                        </td>
                        <td width="100px" align="right">二级分类</td>
                        <td align="left">
                            <form:select path="machiningCode" style="width:120px" id="pdMachining">
                                <form:option value="" label="请选择"/>
                            </form:select>
                            <input type="hidden" id="machiningName" name="machiningName" value="" />
                        </td>
                    </tr>
                    <tr>
                        <td width="100px" align="right">品种</td>
                        <td align="left">
                            <form:select path="pdBreedCode" style="width:120px" id="pdBreed">
                                <form:option value="" label="请选择"/>
                            </form:select>
                            <input type="hidden" id="pdBreedName" name="pdBreedName" value="" />
                        </td>
                        <td align="right">特征</td>
                        <td align="left">
                            <form:select path="pdFeatureCode" style="width:120px" id="pdFeature">
                                <form:option value="" label="请选择"/>
                            </form:select>
                            <input type="hidden" id="pdFeatureName" name="pdFeatureName" value="" />
                        </td>
                        <td width="100px" align="right">净重</td>
                        <td align="left">
                            <form:select path="weightCode" style="width:120px" id="pdWeight">
                                <form:option value="" label="请选择"/>
                            </form:select>
                            <input type="hidden" id="weightName" name="weightName" value="" />
                        </td>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td align="right"><input type="checkbox" value="1" name="distmskFlg" checked="checked"/></td>
                        <td align="left">是否参与美侍客分销</td>
                        <td align="right"><input type="checkbox" value="1" name="distFlg" checked="checked"/></td>
                        <td align="left">是否参与神农客分销</td>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td align="right">盘装图</td>
                        <td align="left"><input type="file" name="labFile1" style="width:160px"/></td>
                        <td align="right">内袋图</td>
                        <td align="left"><input type="file" name="labFile2" style="width:160px"/></td>
                        <td align="right">外箱开箱图</td>
                        <td align="left"><input type="file" name="labFile3" style="width:160px"/></td>
                        <td align="right">外箱外观图</td>
                        <td align="left"><input type="file" name="labFile4" style="width:160px"/></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td align="right">
                            <msk2:button buttonType="button" buttonId="PD141116.ADD" buttonValue="添加"/>
                            <%--<msk:button buttonType="button" buttonValue="添加" buttonId="PD141116.ADD"/>--%>
                        </td>
                        <td align="right">
                            <msk2:button buttonType="button" buttonId="PD141116.LIST" buttonValue="新品种/特征/净重/包装列表"/>
                            <%--<msk:button buttonType="button" buttonValue="新品种/特征/净重/包装列表" buttonId="PD141116.LIST"/>--%>
                        </td>
                        <td align="right">
                            <msk2:button buttonType="button" buttonId="PD141116.NEW" buttonValue="新品种/特征/净重/包装申请"/>
                            <%--<msk:button buttonType="button" buttonValue="新品种/特征/净重/包装申请" buttonId="PD141116.NEW"/>--%>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
    </form:form>

    <form:form action="${ctx}/SL241116/search/${slCode}" id="SL241116Form" method="post">


        <input type="hidden" name="crtId" value="${loginUser.emplId}"/>
        <input type="hidden" name="updId" value="${loginUser.emplId}"/>
        <div class="group-accordion" active="true" collapsible="false">
            <h3>
                <label>卖家产品列表</label>
            </h3>

            <div>
                    <%--<form action="${ctx}/SL241116/search/${slCode}" method="post">--%>
                <table id="SL241116_list_grid">
                    <thead>
                    <tr>
                        <th coltype="text" width="10%" name="slPdArtNo" filter="true">卖家货号码</th>
                        <th coltype="text" width="10%" name="prodEpName" filter="true">生产商</th>
                        <th coltype="text" width="10%" name="brandName" filter="true">品牌</th>
                        <th coltype="text" width="10%" name="pdClassesName" filter="true">产品一级分类</th>
                        <th coltype="text" width="10%" name="machiningName" filter="true">产品二级分类</th>
                        <th coltype="text" width="10%" name="pdBreedName" filter="true">产品品种</th>
                        <th coltype="text" width="10%" name="pdFeatureName" filter="true">产品特征</th>
                        <th coltype="text" width="10%" name="weightName" filter="true">包装净重</th>
                        <th coltype="text" width="10%" name="distmskFlg">参与美侍客分销</th>
                        <th coltype="code" code2Name ="YESNO_JSON" width="10%" name="distFlg" >参与神农客分销</th>
                        <th coltype="text" width="10%" name="slTncGradeCodeName">产品等级</th>
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
                            <msk2:button buttonType="hidden" buttonId="SL241116.SAVEBTN" coltype="save" buttonValue="产品状态审核" class="h-button"/>
                            <%--<input type="hidden" coltype="save" title="产品状态审核" class="h-button" />--%>
                        </th>
                        <th coltype="action" width="60px">产品品种图片
                            <msk2:button buttonType="hidden" buttonId="SL241116.SEARCHBTN" coltype="search" buttonValue="产品品种图片" class="h-button"/>
                            <%--<input type="hidden" coltype="search" title="产品品种图片" class="h-button" />--%>
                        </th>
                        <th coltype="action" width="60px">包装标准
                            <msk2:button buttonType="hidden" buttonId="SL241116.DETAILBTN" coltype="detail" buttonValue="包装标准" class="h-button"/>
                            <%--<input type="hidden" coltype="detail" title="包装标准" class="h-button" />--%>
                        </th>
                        <th coltype="action" width="60px">加工技术标准
                            <msk2:button buttonType="hidden" buttonId="SL241116.CHECKBTN" coltype="check" buttonValue="加工技术标准" class="h-button"/>
                            <%--<input type="hidden" coltype="check" title="加工技术标准" class="h-button" />--%>
                        </th>
                        <th coltype="action" width="60px">加工质量标准
                            <msk2:button buttonType="hidden" buttonId="SL241116.EDITBTN" coltype="edit" buttonValue="加工质量标准" class="h-button"/>
                            <%--<input type="hidden" coltype="edit" title="加工质量标准" class="h-button" />--%>
                        </th>
                        <th coltype="action" width="60px">其他标准
                            <msk2:button buttonType="hidden" buttonId="SL241116.AUDITBTN" coltype="audit" buttonValue="其他标准" class="h-button"/>
                            <%--<input type="hidden" coltype="audit" title="其他标准" class="h-button" />--%>
                        </th>
                    </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
            </div>
        </div>
    </form:form>
</div>
<script src="${ctx}/static/sl/js/SL241116.js"></script>
